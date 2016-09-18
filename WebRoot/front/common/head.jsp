<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script
	src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
<nav class="navbar navbar-defult" role="navigation" style="background-color: #e45050;padding:3px  0 10px;  margin: 0 ; width:1350">
	<div class="navbar-header">
		<a target="_parent" class="navbar-brand" style="color:white" href="${pageContext.request.contextPath}/front/productShow/productShowServlet?method=sort&id=0">EShop</a>
	</div>
	<div>
		<form class="navbar-form navbar-left" role="search" action="${pageContext.request.contextPath}/front/productServlet?method=list" method="post">
			<div class="form-group" >
				<input type="text" class="form-control" placeholder="Search" name="search">
			</div>
			<button type="submit" class="btn btn-default">提交</button>
			
		</form>
		<ul class="nav navbar-nav navbar-right" style="margin-right: 0px;">
				<li><a style="color:white" target="_parent" href="${pageContext.request.contextPath}/front/productShow/productShowServlet?method=sort&id=0">首页</a></li>
				<li role="separator" class="divider"></li>
				<c:if test="${userBean.username!=null }">
					<li><a style="color:white" target="_parent" href="${pageContext.request.contextPath}/front/user/userInfo.jsp">${userBean.nickname }</a></li>		
					<li><a style="color:white" target="_parent" href="${pageContext.request.contextPath}/front/shopping/shoppingServlet?method=list">订单中心</a></li>		
				</c:if>
				<c:if test="${userBean.username!=null }">
				<li><a style="color:white" target="_parent" href="${pageContext.request.contextPath}/front/user/userServlet?method=end&status=1">退出登录</a></li>
				</c:if>
				<c:if test="${userBean.username==null }">
				<li><a style="color:white" target="_parent" href="${pageContext.request.contextPath}/front/user/login.jsp">登录</a></li>
				</c:if>
				<li><a style="color:white" type="button" target="_parent" href="${pageContext.request.contextPath}/front/productServlet?method=cart&userId=${userBean.id }">我的购物车</a></li>
				
		</ul>
	</div>
	</nav>