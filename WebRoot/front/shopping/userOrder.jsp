<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单</title>
<script
	src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
<style type="text/css">
.tee {
	background-color: #F5F5F5;
	margin: 20px;
	padding: 20px;
}

.tbb {
	background-color: white;
	margin: 20px;
	padding: 25px;
}

.tdd {
	border: solid 1px black;
}

.taa {
	background-color: white;
}

.tcc {
	background-color: #F5F5F5;
}
</style>
</head>
<body>

	<div class="container">

		<div class="row-fluid tee">我的订单</div>
		<div class="row-fluid tee">
			<div class="row-fluid">
				<a
					href="${pageContext.request.contextPath}/front/shopping/shoppingServlet?method=list">&nbsp;
					全部订单&nbsp; </a> <a
					href="${pageContext.request.contextPath}/front/shopping/shoppingServlet?method=list&status=unpay">&nbsp;
					待付款&nbsp; </a>
			</div>
			<table class="table taa">
				<tr>
					<td colspan="5">订单详情&nbsp;&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;&nbsp;</td>
					<td>收货人</td>
					<td>原价</td>
					<td>现价</td>
					<td>状态</td>
					<!-- <td>操作</td> -->
				</tr>
			</table>

			<c:forEach items="${orderBeans }" var="item">
				<table class="table table-bordered taa">

					<tr class="tcc">
						<td colspan="5">${item.create_date }&nbsp&nbsp&nbsp${item.code
							}</td>
						<%-- <td><a
							href="${pageContext.request.contextPath}/front/shopping/shoppingServlet?method=del&code=${item.code}">删除</a>
						</td> --%>
					</tr>
					<tr>
						<td><c:forEach items="${item.orderProductBeans }" var="each">
								<table class="table table-hover">
									<tr>
										<td><img alt="图片" src="${each.productBean.pic }"
											width="70px" height="70px"></td>
										<td>${each.productBean.name }</td>
										<td>${each.price }</td>
										<td>x${each.number }</td>
										<td><a
											href="${pageContext.request.contextPath}/front/shopping/shoppingServlet?method=toAddcomment&productId=${each.productBean.id }">评价</a></td>
									<tr>
								</table>
							</c:forEach></td>
						<td>${item.addressBean.name }</td>
						<td>${item.original_price}</td>
						<td>${item.price}</td>
						<td>
							<div>
								<c:if test="${item.status==0 }">
									<a href="${pageContext.request.contextPath}/front/shopping/pay.jsp?orderId=${item.id }">未支付</a>
								</c:if>
								<c:if test="${item.status==1 }"><a href="${pageContext.request.contextPath}/front/shopping/shoppingServlet?method=update&status=2&orderId=${item.id }">已支付,提醒发货</a></c:if>
								<c:if test="${item.status==2 }"><a href="${pageContext.request.contextPath}/front/shopping/shoppingServlet?method=update&status=3&orderId=${item.id }">未收货</a></c:if>
								<c:if test="${item.status==3 }">已收货</c:if>
							</div>
							<!-- <div>
								<a href="">订单详情</a>
							</div> -->
						</td>
						<!-- <td>
						<div class="btn">
								<button type="button">立即购买</button>
							</div>
						</td> -->
					</tr>
				</table>
			</c:forEach>
		</div>
	</div>
</body>
</html>