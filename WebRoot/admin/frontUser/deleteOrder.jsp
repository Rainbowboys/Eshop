<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户订单删除页面</title>
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
				<h1>用户订单删除</h1>
			</div>
			<div class="col-md-6">
				<form class="navbar-form navbar-left" role="search"
					action="${pageContext.request.contextPath}/admin/userOrderServlet?method=listBycode"
					method="post">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="订单号"
							name="code">
					</div>
					<button type="submit" class="btn btn-primery">提交</button>
				</form>
			</div>
		</div>
		<c:if test="${orderBean!=null}">
		<div class="row-fluid">
			<div class="col-md-10 col-md-offset-1">
				<table class="table">
					<tr>
						<td>订单详情</td>
						<td>原价</td>
						<td>现价</td>
						<td>收货人</td>
						<td>联系方式</td>
						<td>支付方式</td>
						<td>订单状态</td>
					</tr>
					<tr>
						<td>${orderBean.create_date }&nbsp;${orderBean.code }</td>
						<td>￥${orderBean.original_price }</td>
						<td>￥${orderBean.price }</td>
						<td>${orderBean.addressBean.name }</td>
						<td>${orderBean.addressBean.cellphone }</td>
						<td><c:if test="${orderBean.payment_type==0 }">在线支付</c:if> <c:if
								test="${orderBean.payment_type==1 }">货到付款</c:if></td>
						<td><c:if test="${orderBean.status==0 }">未支付</c:if> <c:if
								test="${orderBean.status==1 }">已支付</c:if> <c:if
								test="${orderBean.status==2 }">未收货</c:if> <c:if
								test="${orderBean.status==3 }">已收货</c:if></td>
					</tr>
				</table>
				<table class="table">
					<tr>
						<td>id</td>
						<td>商品名称</td>
						<td>图片</td>
						<td>价格</td>
						<td>数量</td>
					</tr>
					<c:forEach items="${orderBean.orderProductBeans }" var="item">
						<tr>
							<td>${item.id }</td>
							<td>${item.productBean.name }</td>
							<td><img alt="图片" src="${item.productBean.pic }"
								width="70px" height="70px"></td>
							<td>￥${item.price }</td>
							<td>X${item.number }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="row-fluid">
			<div class="col-md-10 col-md-offset-2"><button class="btn" onclick="window.location.href='${pageContext.request.contextPath}/admin/userOrderServlet?method=delete&orderId=${orderBean.id }'">删除</button></div>
		</div>
		</c:if>
		<div class="row-fluid">
		<div class="col-md-12">
			<c:if test="${param.status.equals('0')}">
				<div class="alert alert-info" role="alert">没有该订单</div>
			</c:if>
			</div>
		</div>
	</div>
</body>
</html>