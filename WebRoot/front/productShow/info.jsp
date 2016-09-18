<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.rainbow/taglib/util" prefix="util"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
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
	$().ready(function() {
		$("#addItem").click(function() {
			var num = document.getElementById("num").value;
			var productId = ${productBean.id};
			$.get("../shopping/shoppingServlet?method=addItem", {
				num : num,
				productId : productId
			}, function(data) {
				document.getElementById("status").innerHTML = "添加购物车成功";
			}, "text");
		});
		$('[data-spy="scroll"]').each(function() {
			var $spy = $(this).scrollspy('refresh');
		});
		window.onscroll = function() {
			var Y = $(document.documentElement).scrollTop();
			if (Y > 480) {
				$("#navbar1").show();
			} else {
				$("#navbar1").hide();
			}
		};
	});
</script>
<style>
body {
	position: relative;
}

.info {
	font-size: 20px;
	margin-top: 50px;
}

.navbar1 {
	font-size: 20px;
	background-color: #fff;
}

.hid {
	position: fixed;
}

article {
	font-size: 15px;
}

.commfor {
	font-size: 15px;
}
</style>


</head>
<body data-spy="scroll">
	<%@include file="../top.jsp"%>
	<util:type path="${pageContext.request.contextPath}" />
	<div class="info col-md-8 col-md-offset-2">
		<img class="col-sm-4 img-thumbnail" alt="商品图片未加载"
			src="${productBean.pic}">
		<dl class="col-sm-8 dl-horizontal">
			<dt>商品名称:</dt>
			<dd>${productBean.name}</dd>
			<dt class="originalPrice">原价:</dt>
			<dd>
				￥
				<del>${productBean.originalPrice }</del>
			</dd>
			<dt class="price">疯狂折扣价:</dt>
			<dd>￥${productBean.price }</dd>

			<dt class="number">库存:</dt>
			<dd>${productBean.number }</dd>
			<dt class="time">上市时间:</dt>
			<dd>${productBean.createDate }</dd>
			<dt></dt>
			<dd>
				<form
					action="../shopping/shoppingServlet?method=addItem&id=${productBean.id}"
					method="post">
					<div class="input-group col-xs-5">
						<span class="input-group-btn">
							<button type="button" class="btn" data-value="decrease"
								data-target="#num" data-toggle="spinner">
								<span class="glyphicon glyphicon-minus"></span>
							</button>
						</span> <input type="text" data-ride="spinner" name="items" id="num"
							class="form-control input-number" value="1"> <span
							class="input-group-btn">
							<button type="button" class="btn" data-value="increase"
								data-target="#num" data-toggle="spinner">
								<span class="glyphicon glyphicon-plus"></span>
							</button>
						</span>
					</div>
					<button type="button" class="btn btn-default" id="addItem">加入购物车</button>
					<div id="status"></div>
				</form>
			</dd>
		</dl>
	</div>
	<div hidden id="navbar1"
		class="navbar1 col-md-8 col-md-offset-2 navbar-fixed-top "
		data-target=".navbar-example" data-offset="0">
		<ul class="nav nav-tabs">
			<li role="presentation" class="active"><a href="#property">商品属性</a></li>
			<li role="presentation"><a href="#intro">商品简介</a></li>
			<li role="presentation"><a href="#comment">商品评价</a></li>
		</ul>
	</div>
	<div class="info col-md-8 col-md-offset-2">
		<div id="navbar0">
			<ul class="nav nav-tabs">
				<li role="presentation" class="active"><a href="#property">商品属性</a></li>
				<li role="presentation"><a href="#intro">商品简介</a></li>
				<li role="presentation"><a href="#comment">商品评价</a></li>
			</ul>
		</div>
		<div id="property" data-spy="scroll">
			<div style="height:50px">&nbsp;</div>
			<h3>商品属性：</h3>
			<c:forEach items="${productBean.productOptionBeans}" var="item"
				varStatus="status">
				<dl class="dl-horizontal">
					<dt>${item.productPropertyBean.name}:</dt>
					<dd>${item.name}:</dd>
				</dl>
			</c:forEach>
		</div>
		<div id="intro" data-spy="scroll">
			<div style="height:50px">&nbsp;</div>
			<h3>商品简介：</h3>
			<article>${productBean.intro }</article>
		</div>
		<div id="comment" data-spy="scroll">
			<div style="height:50px">&nbsp;</div>
			<h3>商品评论：</h3>
			<c:forEach items="${commentBeans}" var="comm" varStatus="status">
				<div style="height:30px">&nbsp;</div>
				<span class="commfor">用户：${comm.userBean.username}
					评分：${comm.score} 创建时间：${comm.createDate}</span>
				<div style="height:10px">&nbsp;</div>
				<article>${comm.content}</article>
			</c:forEach>
		</div>
	</div>

</body>
</html>
