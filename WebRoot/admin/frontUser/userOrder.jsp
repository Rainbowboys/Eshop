<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户订单页面</title>
<script
	src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />

</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-md-4">
				<h1>用户订单</h1>
			</div>
			<div class="col-md-6">
				<form class="navbar-form navbar-left" role="search"
					action="${pageContext.request.contextPath}/admin/userOrderServlet?method=list"
					method="post">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="用户账号"
							name="search">
					</div>
					<button type="submit" class="btn btn-primery">提交</button>

				</form>
			</div>
		</div>

		<div class="row-fluid">
			<c:if test="${orderBeans!=null}">
				<div class="col-md-10 col-md-offset-1">
					<table class="table">
						<tr>
						<td>id</td>
						<td>订单编号</td>
						<td>原价</td>
						<td>现价</td>
						<td>地址别名</td>
						<td>用户</td>
						<td>支付方式</td>
						<td>支付状态</td>
						<td>购买时间</td>
						<td>操作</td>
						</tr>
						<c:forEach items="${orderBeans }" var="item">
						<tr>
						<td>${item.id }</td>
						<td><a href="${pageContext.request.contextPath}/admin/userOrderServlet?method=list&orderId=${item.id }&search=${userBean.username }">${item.code }</a></td>
						<td>${item.original_price }</td>
						<td>${item.price }</td>
						<td>${item.addressBean.name }</td>
						<td>${item.userBean.username }</td>
						<td>
						<c:if test="${item.payment_type==0 }">在线支付</c:if>
						<c:if test="${item.payment_type==1 }">货到付款</c:if>
						</td>
						<td>
						<c:if test="${item.status==0 }">未支付</c:if>
						<c:if test="${item.status==1 }">已支付</c:if>
						<c:if test="${item.status==2 }">未收货</c:if>
						<c:if test="${item.status==3 }">已收货</c:if>
						</td>
						<td>${item.create_date }</td>
						<td><a href="${pageContext.request.contextPath}/admin/userOrderServlet?method=delete&orderId=${item.id }">删除</a></td>
						</tr>
						</c:forEach>
					</table>
				</div>
			</c:if>
		</div>
		<div class="row-fluid">
		<div class="col-md-12">
			<c:if test="${param.status.equals('0')}">
				<div class="alert alert-info" role="alert">没有该用户</div>
			</c:if>
			</div>
		</div>
	</div>
</body>
</html>