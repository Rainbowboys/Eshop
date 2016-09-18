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
<script
	src="${pageContext.request.contextPath}/static/js/jquery.validate.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/myValidate.js"
	type="text/javascript"></script>
</head>
<body>
	<div class="container" style="margin-top: 30px;">
		<div class="row-fluid">
			<div class="col-md-8">

				<!-- 模态框（Modal） -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<form class="form-horizontal"
							action="${pageContext.request.contextPath}/front/user/userServlet?method=update"
							method="post" id="checkForm">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">修改个人信息</h4>
								</div>
								<div class="modal-body">
									<div class="form-group">
										<label class="col-md-3 control-label" for="nickname">新手机号：</label>
										<div class="col-md-7">
											<input type="text" class="form-control" name="account"
												id="account" placeholder="手机号">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label" for="nickname">昵称：</label>
										<div class="col-md-7">
											<input type="text" class="form-control" name="nickname"
												id="nickname" placeholder="${userBean.nickname }">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label" for="inputPassword">新密码：</label>
										<div class="col-md-7">
											<input type="password" class="form-control" name="password"
												id="inputPassword" placeholder="password">
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label" for="password2">确认密码：</label>
										<div class="col-md-7">
											<input type="password" class="form-control" name="password2"
												id="password2" placeholder="password">
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<div class="form-group">
										<div class="col-md-4"></div>
										<div class="col-md-2">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">关闭</button>
										</div>

										<div class="col-md-2">
											<button type="submit" class="btn btn-primary">提交更改</button>
										</div>
									</div>
								</div>
							</div>
						</form>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal -->
				</div>

				<form class="form-horizontal" style="background-color:#F5F5F5">
					<div class="form-group">
						<label class="col-md-3 control-label" for="truename">真实姓名：</label>
						<div class="col-md-3">
							<label class="control-label">${userBean.truename }</label>
						</div>
						<div class="col-md-3">
						<c:if test="${userBean.sex==0 }">
							<label class="control-label">男</label>
						</c:if>
						<c:if test="${userBean.sex==1 }">
							<label class="control-label">女</label>
						</c:if>
						</div>
						<div class="col-md-2">
							<a class="control-label" data-toggle="modal"
								data-target="#myModal">修改信息</a>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="nickname">手机号：</label>
						<div class="col-md-7">
							<label class="control-label">${userBean.username }</label>
						</div>
						
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label" for="nickname">昵称：</label>
						<div class="col-md-7">
							<label class="control-label">${userBean.nickname }</label>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label" for="inputPassword">当前密码：</label>
						<div class="col-md-9">
							<label class="control-label">${userBean.password }</label>
						</div>
					</div>
					<br /> <br />
					<div class="row-fluid">
						<div class="col-md-12">
							<c:if test="${param.status.equals('1')}">
								<div class="alert alert-success" role="alert">
									<span style="margin-left: 75px;">恭喜您，修改成功！！！</span>
								</div>
							</c:if>
						</div>
					</div>
				</form>
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-3" >
			<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/front/user/userServlet?method=uploadpic&id=${userBean.id }"
							method="post">
				<div class="row-fluid">
					<img alt="图片" src="${userBean.pic }" width="100px" height="100px" class="img-circle">
				</div>
				<div class="row-fluid">
				<div class="col-md-6"><input type="file" class="form-control" name="pic" id="pic" placeholder="pic" /></div>
				<div class="col-md-6"><button type="submit" class="btn">上传</button></div>
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>