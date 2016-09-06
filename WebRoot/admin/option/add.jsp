<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>添加分类</title>
<script src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.js"></script>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>

<script type="text/javascript">
	$("#myForm").validate({
		rules : {
			classifyname : {
				required : true
			}
		},
		messages : {
			adminname : {
				required : "请输入分类名",
			}
		}
	});
	function chType(obj) {
		$(obj).parent().nextAll().remove();
		var id = obj.value;
		if (id > 0) {
			//ajax请求
			$.post("../type/productTypeServlet",{
				method : "getType",
				id : id
			},
			function(data) {
				if (data != null && data.length > 0) {
					var content = "<div class='col-sm-2' ><select name='parentId' class='form-control' onchange='chType(this)' id='type0'><option value='-1'>-- 请选择父类 --</option>";
					for ( var type in data) {
						content += "<option value='"+data[type].id+"'>"
								+ data[type].name + "</option>";
					}
					content += "</select></div>";
					$("#types").append(content);
				}
			}, "json");
		}
		chProperty(obj);
	}
	function chProperty(obj){
		$("#property0").empty();
		var id = obj.value;
		$.post("../property/productPropertyServlet", {
			method : "getProperty",
			id : id
		}, function(data) {
			if (data != null && data.length > 0) {
				var content = "";
				for ( var type in data) {
					content += "<option value='"+data[type].id+"'>"
							+ data[type].name + "</option>";
				}
				document.getElementById("property0").innerHTML = content;
			}
		}, "json");
	}
</script>

</head>

<body>

	<!-- 页头 
	-->
	<div class="container">

		<h1 class="text-center text-danger">添加商品属性选项</h1>

		<form id="myForm" role="form" action="productOptionServlet?method=add" method="post">

			<div class="form-group col-md-12">
				<label id="label1" class="col-md-2 control-label" for="name">属性选项名：</label>
				<div class="col-md-10">
					<input class="form-control" name="name" type="text" id="name" placeholder="classifyname"
						value="${productOptionBean.name }" />
				</div>
			</div>
			<div class="form-group col-md-12" id="types">
				<label id="label1" class="col-sm-2 control-label" for="name">父类：</label>
				<div class="col-sm-2">
					<select name="parentId" class="form-control" onchange="chType(this)" id="type0">
						<option value="-1">-- 请选择分类 --</option>
						<c:forEach items="${productTypeList}" var="item">
							<option value="${item.id}">${item.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group col-md-12">
				<label id="label1" class="col-md-2 control-label" for="name">属性名：</label>
				<div class="col-md-10" id="types">
					<select name="PropertyId" class="form-control" onchange="" id="property0">
					</select>
				</div>
			</div>
			<div class="form-group col-md-12">
				<label id="label1" class="col-md-2 control-label" for="name">选项序号：</label>
				<div class="col-md-10">
					<input class="form-control" name="sort" type="text" id="sort" placeholder="选项序号"
						value="${productOptionBean.sort }" />
				</div>
			</div>
			<div class="form-group col-md-12">
				<div class="col-md-offset-2 col-md-10">
					<button type="submit" id="subt" class="btn btn-primary btn-sm">提交</button>
				</div>
			</div>
			<div class="form-group col-md-12">
				<div class="col-md-offset-2 col-md-10">
					${param.status==1?"<div class='alert alert-danger' role='alert'>添加分类成功</div>":"" }
				</div>
			</div>
			<input type="hidden" name="id" id="id" value="${productOptionBean.id }">
		</form>
	</div>
</body>
</html>