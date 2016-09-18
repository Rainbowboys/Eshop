<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户地址</title>
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
<script type="text/javascript">
	function showAddress(obj) {
		$(obj).parent().nextAll().remove();
		$
				.get(
						"addressServlet",
						{
							method : "getProvince",
						},
						function(data) {
							if (data != null && data.length > 0) {
								var content = "<div class='col-sm-3'><select name='province' class='form-control' onchange='showcity(this)' ><option value='-1'>-- 请选择省份 --</option>";
								for ( var province in data) {
									content += "<option value='"+data[province].id+"'>"
											+ data[province].provincename
											+ "</option>";
								}
								content += "</select></div>";
								$("#province").append(content);
							}
						}, "json");
	};
	function showcity(obj) {
		$(obj).parent().nextAll().remove();
		id = obj.value;
		$
				.get(
						"addressServlet",
						{
							method : "getCity",
							id : id
						},
						function(data) {
							if (data != null && data.length > 0) {
								var content = "<div class='col-sm-3'><select name='city' class='form-control' onchange='showarea(this)' ><option value='-1'>-- 请选择市、区 --</option>";
								for ( var city in data) {
									content += "<option value='"+data[city].id+"'>"
											+ data[city].provincename
											+ "</option>";
								}
								content += "</select></div>";
								$("#province").append(content);
							}
						}, "json");
	};
	function showarea(obj) {
		$(obj).parent().nextAll().remove();
		id = obj.value;
		$
				.get(
						"addressServlet",
						{
							method : "getArea",
							id : id
						},
						function(data) {
							if (data != null && data.length > 0) {
								var content = "<div class='col-sm-3'><select name='region' class='form-control'  ><option value='-1'>-- 请选择县 --</option>";
								for ( var area in data) {
									content += "<option value='"+data[area].id+"'>"
											+ data[area].provincename
											+ "</option>";
								}
								content += "</select></div>";
								$("#province").append(content);
							}
						}, "json");
	};
</script>
<script type="text/javascript">
	$().ready(showAddress(null));
</script>
<style type="text/css">
.tee {
	background-color: #F5F5F5;
	margin: 20px;
	padding: 20px;
}

.taa {
	background-color: white;
	padding: 20px;
	border: 0;
}
ul{
list-style-type:none;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<button class="btn" data-toggle="modal" data-target="#myModal">新增收货地址</button>
		</div>
		<div class="row-fluid tee">
		<c:if test="${addressBeans!=null }">
			<c:forEach items="${addressBeans }" var="item">
			<table class="table taa">
				<tr>
					<td>
						<div class="row-fluid">
							<ul class="col-md-3">
								<li><p>收货人：</p></li>
								<li><p>所在地区：</p></li>
								<li><p>地址：</p></li>
								<li><p>联系手机：</p></li>
								<li><p>用户：</p></li>
							</ul>
							<ul class="col-md-7">
							<li><p>&nbsp${item.name }&nbsp</p></li>
								<li><p>&nbsp${item.provincename }${item.cityname }${item.areaname }&nbsp</p></li>
								<li><p>&nbsp${item.address }&nbsp</p></li>
								<li><p>&nbsp${item.cellphone }&nbsp</p></li>
								<li><p>&nbsp${userBean.nickname }&nbsp</p></li>
							</ul>
							<ul class="col-md-2">
							<li><a href="${pageContext.request.contextPath}/front/user/addressServlet?method=delete&id=${item.id }">删除</a></li>
							<c:if test="${item.status==0 }">
							<li><a href="${pageContext.request.contextPath}/front/user/addressServlet?method=update&id=${item.id }">设为默认地址</a></li>
							</c:if>
							<c:if test="${item.status==1 }">
							<li><p >默认地址</p></li>
							</c:if>
							</ul>
						</div>
					</td>
				</tr>
			</table>
			</c:forEach>
			</c:if>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<form class="form-horizontal"
				action="${pageContext.request.contextPath}/front/user/addressServlet?method=add"
				method="post" id="checkForm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">添加收货地址</h4>
					</div>
					<div class="form-group col-md-12" id="province">
						<label class="col-sm-3 control-label" for="name">地址：</label>
					</div>
					<div class="form-group col-md-12">
						<label class="col-md-3 control-label" for="name">详细地址：</label>
						<div class=" col-md-9">
							<input class="form-control" name="address" type="text"
								value="${addressBean.address}" />
						</div>
					</div>
					<div class="form-group col-md-12">
						<label class="col-md-3 control-label" for="name">收货人姓名：</label>
						<div class="col-md-9">
							<input class="form-control" name="name" type="text" id="name"
								value="${addressBean.name}" />
						</div>
					</div>

					<div class="form-group col-md-12">
						<label class="col-md-3 control-label" for="name">联系电话：</label>
						<div class="col-md-9">
							<input class="form-control" name="cellphone" type="text"
								value="${addressBean.cellphone}" />
						</div>
					</div>
					<div class="form-group col-md-12">
						<label class="col-md-3 control-label" for="name">默认收货地址</label>
						<div class="col-md-9">
							<select name='status' class='form-control'>
								<option value='0'>NO!</option>
								<option value='1'>YES!</option>
							</select>
						</div>
					</div>
					<input type="hidden" name="" value="">
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
</body>
</html>