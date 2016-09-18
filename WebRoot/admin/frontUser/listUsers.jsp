<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.rainbow/taglib/util" prefix="util"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看管理员</title>
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
			<div class="span12">
				<h1>用户列表</h1>
			</div>
		</div>

		<div class="row-fluid">
			<div class="col-md-1 "></div>
			<div class="col-md-10 ">
				<table class="table table-striped ">
					<tr>
						<td>id</td>
						<td>img</td>
						<td>phonenumber</td>
						<td>nickname</td>
						<td>truename</td>
						<td>sex</td>
						<td>status</td>
						<td>操作</td>
					</tr>
					<!-- forEach遍历出adminBeans -->
					<c:forEach items="${userBeans}" var="item" varStatus="status">
						<tr>
							<td>${item.id }</td>
							<td><img alt="图片" src="${item.pic }" width="50px"
								height="50px" class="img-circle"></td>
							<td><a
								href="${pageContext.request.contextPath}/user/details.do?search=${item.username }">${item.username }</a></td>
							<td>${item.nickname }</td>
							<td>${item.truename }</td>
							<td><c:if test="${item.sex==0 }">男</c:if> <c:if
									test="${item.sex==1 }">女</c:if></td>
							<td><c:if test="${item.status==1 }">活跃</c:if> <c:if
									test="${item.status==0 }">冻结</c:if></td>
							<td><c:if test="${item.status==1 }">
									<a
										href="${pageContext.request.contextPath}/user/blockuser.do?status=1&id=${item.id }&flag=0">冻结</a>
								</c:if> <c:if test="${item.status==0 }">
									<a
										href="${pageContext.request.contextPath}/user/blockuser.do?status=0&id=${item.id }&flag=0">解冻</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class=" col-md-1"></div>
		</div>
	</div>
</body>
</html>