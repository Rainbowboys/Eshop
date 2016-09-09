<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
<script src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>

<style type="text/css">

</style>
</head>
<body>
	<div class="container">
		<h1>商品详情</h1>
		<div class="col-md-12">
		<img class="img-rounded" alt="商品图片" src="${productBean.pic}"  />
		</div>
		<table class="table table-striped">
			<tr>
				<td>商品名称</td>
				<td>${productBean.name}</td>
			</tr>
			<tr>
				<td>商品分类ID</td>
				<td>${productBean.productTypeId}</td>
			</tr>
			<tr>
				<td>商品原价</td>
				<td>${productBean.originalPrice}</td>
			</tr>
			<tr>
				<td>商品现价</td>
				<td>${productBean.price}</td>
			</tr>
			<tr>
				<td>商品总量</td>
				<td>${productBean.number}</td>
			</tr>
			<tr>
				<td>商品已售数量</td>
				<td>${productBean.sellNumber}</td>
			</tr>
			<tr>
				<td>创建时间</td>
				<td>${productBean.createDate}</td>
			</tr>
			<c:forEach items="${productBean.productOptionBeans}" var="item" varStatus="status">
				<tr>
					<td>${item.productPropertyBean.name}</td>
					<td>${item.name}</td>
				</tr>
			</c:forEach>
		</table>
		<article>
			<h3>商品介绍</h3>
			${productBean.intro}
		</article>
	</div>
</body>
</html>