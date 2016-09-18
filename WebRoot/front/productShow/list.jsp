<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.rainbow/taglib/util" prefix="util"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品展示</title>
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

<style>
.ima {
	width: 250px;
	height: 180px;
	background-repeat: no-repeat;
	background-size: cover;
}

.product {
	float: left;
	padding: 27px;
	font-size: 16px;
	color: #ff7f50;
	font-weight: bold;
}

.blank {
	width: 30px;
}
</style>

</head>
<body>
	<%@include file="../top.jsp"%>
	<util:type path="${pageContext.request.contextPath}" />
	<div class="container">
		<c:forEach items="${productBeans}" var="item">
			<div class="product">
				<a
					href="${pageContext.request.contextPath}/show/info.do?id=${item.id }">
					<div class="ima" style="background-image:url('${item.pic}')"></div>
				</a>
				<div>
					商品名称：<a
						href="${pageContext.request.contextPath}/show/info.do?id=${item.id }">${item.name}</a>
				</div>
				<div>价格：${item.price}元！</div>
				<div>数量：${item.number}份!</div>
			</div>
		</c:forEach>

	</div>
	${param.status==1?"<div class='alert alert-danger' role='alert'>此类商品未上架</div>":"" }
</body>
</html>