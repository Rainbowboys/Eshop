<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品属性选项查询</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/js/jquery-1.12.1.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
	function chType(obj) {
		$(obj).parent().nextAll().remove();
		var id = obj.value;
		if (id > 0) {
			//ajax请求
			$
					.post(
							"${pageContext.request.contextPath}/property/getType.do",
							{
								id : id
							},
							function(data) {
								if (data != null && data.length > 0) {
									var content = "<div class='col-sm-2' ><select name='parentId' class='form-control' onchange='chType(this)' id='type0'><option value='"+id+"'>-- 请选择分类 --</option>";
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
	function chProperty(obj) {
		$("#property0").empty();
		var id = obj.value;
		$.post("${pageContext.request.contextPath}/option/getProperty.do", {
			id : id
		}, function(data) {
			if (data != null && data.length > 0) {
				var content = "<option value='"+id+"'>-- 请选择属性 --</option>";
				for ( var type in data) {
					content += "<option value='"+data[type].id+"'>"
							+ data[type].name + "</option>";
				}
				document.getElementById("property0").innerHTML = content;
			}
		}, "json");
	}
	function showOption(obj) {
		var id = obj.value;
		$
				.post(
						"${pageContext.request.contextPath}/option/showOption.do",
						{
							id : id
						},
						function(data) {
							if (data != null && data.length > 0) {
								var content = "";
								content += "<tr><td>id</td>";
								content += "<td>选项名称</td>";
								content += "<td>排序</td>";
								content += "<td>所属属性id</td>";
								content += "<td>创建时间</td>";
								content += "<td>操作</td>";
								content += "<td>操作</td></tr>";
								for ( var option in data) {
									content += "<tr><td>" + data[option].id
											+ "</td>";
									content += "<td>" + data[option].name
											+ "</td>";
									content += "<td>" + data[option].sort
											+ "</td>";
									content += "<td>" + data[option].propertyId
											+ "</td>";
									content += "<td>" + data[option].createDate
											+ "</td>";
									content += "<td><a href='productOptionServlet?method=update&id="
											+ data[option].id + "'>修改</a></td>";
									content += "<td><a href='productOptionServlet?method=delete&id="
											+ data[option].id
											+ "'>删除</a></td></tr>";
								}
								document.getElementById("option0").innerHTML = content;
							}
						}, "json");
	}
</script>
<body>
	<div class="container">
		<h1>商品属性选项查询</h1>
		<div class="form-group col-md-12" id="types">
			<label id="label1" class="col-sm-2 control-label" for="name">分类：</label>
			<div class="col-sm-2">
				<select name="parentId" class="form-control" onchange="chType(this)"
					id="type0">
					<option value="0">-- 请选择分类 --</option>
					<c:forEach items="${productTypeList}" var="item">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group col-md-12">
			<label id="label1" class="col-md-2 control-label" for="name">属性名：</label>
			<div class="col-md-10" id="types">
				<select name="PropertyId" class="form-control"
					onchange="showOption(this)" id="property0">
				</select>
			</div>
		</div>
		<table class="table table-striped" id="option0">
		</table>
		<div class="form-group col-md-12">
			<div class="col-md-10 col-md-setoff-2" id="types">
				${param.status==1?"<div class='alert alert-success' role='alert'>修改属性选项成功</div>":"" }
			</div>
		</div>
	</div>
</body>
</html>