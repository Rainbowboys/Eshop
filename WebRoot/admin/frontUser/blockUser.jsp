<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>冻结解冻页面</title>
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
				<h1>用户</h1>
			</div>
			<div class="col-md-6">
				<form class="navbar-form navbar-left" role="search"
					action="${pageContext.request.contextPath}/user/details.do"
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
			<c:if test="${userBean!=null}">
				<div class="col-md-8 col-md-offset-2">
					<table class="table">
						<tr>
							<td>id</td>
							<td>${userBean.id}</td>
						</tr>
						<tr>
							<td>账号</td>
							<td>${userBean.username}</td>
						</tr>
						<tr>
							<td>头像</td>
							<td><img alt="图片" src="${userBean.pic }" width="50px"
								height="50px" class="img-circle"></td>
						</tr>
						<tr>
							<td>昵称</td>
							<td>${userBean.nickname}</td>
						</tr>
						<tr>
							<td>真实姓名</td>
							<td>${userBean.truename}</td>
						</tr>
						<tr>
							<td>性别</td>
							<td><c:if test="${userBean.sex==0 }">男</c:if> <c:if
									test="${userBean.sex==1 }">女</c:if></td>
						</tr>
						<tr>
							<td>冻结状态</td>
							<td><c:if test="${userBean.status==0}">已冻结</c:if> <c:if
									test="${userBean.status==1}">活跃</c:if></td>
						</tr>
						<tr>
							<td>创建时间</td>
							<td>${userBean.createDate}</td>
						</tr>
					</table>
				</div>
			</c:if>
		</div>
		<div class="row-fluid">
			<div class="col-md-4 col-md-offset-4">
				<c:if test="${userBean.status==1 }">
					<a type="button" class="btn btn-block btn-info"
						href="${pageContext.request.contextPath}/user/blockuser.do?&id=${userBean.id}&status=1">冻结</a>
				</c:if>
				<c:if test="${userBean.status==0 }">
					<a type="button" class="btn btn-block btn-info"
						href="${pageContext.request.contextPath}/user/blockuser.do?id=${userBean.id}&status=0">解冻</a>
				</c:if>
			</div>
		</div>

		<div class="row-fluid">
			<div class="col-md-12">
				<c:if test="${status.equals('0')}">
					<div class="alert alert-info" role="alert">没有该用户</div>
				</c:if>
				<c:if test="${status.equals('1')}">
					<div class="alert alert-info" role="alert">操作成功</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>