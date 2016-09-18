<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.rainbow/taglib/util" prefix="util"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
<script
	src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
<script
	src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>

<link
	href="${pageContext.request.contextPath }/static/typeList/typeList.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/typeList/typeList.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/js/bootstrap-spinner.min.js"></script>

<script type="text/javascript">
	function changeSum(obj) {
		$(obj).parents(".aForm").find(".select").attr('checked', 'checked');
		$(obj).parents(".aForm").find(".price").attr("name", "price");
		$(obj).parents(".aForm").find(".oPrice").attr("name", "oPrice");
		var productId = $(obj).attr('for');
		var number = $(obj).parents(".aForm").find(".input-number").val();
		$(obj).parents(".aForm").find(".sub").val(productId + "_" + number);

		var oSum = 0.0;
		var oAll = document.getElementsByName("oPrice");
		for ( var oPrice in oAll) {
			var oPrice = parseFloat(oAll[oPrice].innerHTML);
			if (oPrice > 0.0) {
				oSum += oPrice;
			}
		}
		var sum = 0.0;
		var all = document.getElementsByName("price");
		for ( var price in all) {
			var price = parseFloat(all[price].innerHTML);
			if (price > 0.0) {
				sum += price;
			}
		}
		$("#oSum").html(oSum);
		$("#sum").html(sum);
	}
	function changeItem(productId, obj, oPrice, price) {
		var num = obj.value;
		$.get("../shopping/shoppingServlet?method=addItem", {
			num : num,
			productId : productId
		}, function(data) {
		}, "text");
		$(obj).parents(".aForm").find(".price").html(num * price);
		$(obj).parents(".aForm").find(".oPrice").html(num * oPrice);
		changeSum(obj);
	}

	$().ready(
			function() {
				changeSum();
				$('.select').click(
						function() {
							if ($(this).attr('checked')) {
								$(this).removeAttr('checked');
								$(this).parents(".aForm").find(".price").attr(
										"name", "priceNull");
								$(this).parents(".aForm").find(".oPrice").attr(
										"name", "oPriceNull");
								$(this).parents(".aForm").find(".sub").val("");
							} else {
								$(this).attr('checked', 'checked');
								$(this).parents(".aForm").find(".price").attr(
										"name", "price");
								$(this).parents(".aForm").find(".oPrice").attr(
										"name", "oPrice");
								var productId = $(this).attr('for');
								var number = $(this).parents(".aForm").find(
										".input-number").val();
								$(this).parents(".aForm").find(".sub").val(
										productId + "_" + number);
							}
							changeSum();
						});
			});
</script>
<style>
form {
	width: 1009px;
	margin: 50px auto;
	border: solid 2px #f0f0f0;
}

.aForm .aForm0 {
	height: 200px;
	width: 200px;
	margin-left: 50px;
	float: left;
}

ul {
	matgin: auto;
}

li {
	font-size: 15px;
	line-height: 60px;
}

li div {
	float: left;
}

li input {
	height: 20px;
	width: 100px;
}

.spinner {
	width: 130px;
	float: right;
	position: relative;
	top: 10px;
}

.cSum {
	font-size: 20px;
}
</style>
</head>
<body>
	<%@include file="../top.jsp"%>
	<util:type path="${pageContext.request.contextPath}" />
	<form action="../shopping/shoppingServlet?method=makeOrder"
		method="post">
		<div>
			<c:forEach items="${productOrderBeans}" var="pItem">
				<div class="aForm" name="aForm">
					<img class="aForm0" alt="" src="${pItem.productBean.pic}">
					<ul class="aForm0">
						<li>商品名称：${pItem.productBean.name}</li>
						<li><span>数量：</span>
							<div class="input-group spinner">
								<span class="input-group-btn">
									<button type="button" class="btn" data-value="decrease"
										data-target="#items_${pItem.productBean.id}"
										data-toggle="spinner">
										<span class="glyphicon glyphicon-minus"></span>
									</button>
								</span> <input type="text" for="${pItem.productBean.id}"
									data-ride="spinner" id="items_${pItem.productBean.id}"
									name="number" class="form-control input-number"
									value="${pItem.number}"
									onchange="changeItem('${pItem.productBean.id}',this,'${pItem.productBean.originalPrice}','${pItem.productBean.price}')">
								<span class="input-group-btn">
									<button type="button" class="btn" data-value="increase"
										data-target="#items_${pItem.productBean.id}"
										data-toggle="spinner">
										<span class="glyphicon glyphicon-plus"></span>
									</button>
								</span> <input type="hidden" name="sub" class="sub"
									value="${pItem.productBean.id}_${pItem.number}">
							</div></li>
					</ul>
					<ul class="aForm0">
						<li>原价：<span type="text" name="oPrice" class="oPrice">${pItem.productBean.originalPrice*pItem.number}</span>元
						</li>
						<li>现价：<span type="text" name="price" class="price">${pItem.productBean.price*pItem.number}</span>元
						</li>
					</ul>
					<ul class="aForm0">
						<li><input type="checkbox" name="select" class="select"
							for="${pItem.productBean.id}" checked><span>选择商品</span></li>
						<li><a
							href="shoppingServlet?method=del&productId=${pItem.productBean.id}"><button
									class="btn btn-danger" type="button">删除商品</button></a></li>
					</ul>
				</div>
				<div>&nbsp;&nbsp</div>
				<hr>
			</c:forEach>
		</div>
		<div class="cSum col-md-4 col-md-offset-8">
			<div>
				原总价：<span class="oSum" id="oSum" name="oSum"></span>元
			</div>
			<div>
				现总价：<span class="sum" id="sum" name="sum"></span>元
			</div>
		</div>
		<button class="btn btn-default col-md-offset-9" type="submit">确认订单</button>
	</form>
</body>
</html>