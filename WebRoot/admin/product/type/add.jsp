<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>添加分类</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/myValidate.js" type="text/javascript"></script>

<script type="text/javascript">
$().ready(function() {
	$("#myForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : "请输入分类名"
			}
		}
	});
});
	function addType(obj) {
		$(obj).parent().nextAll().remove();
		var id = obj.value;
		if (id > 0) {
			//ajax请求
			$.post("${pageContext.request.contextPath }/goods/getType.do",{
				id : id
			},
			function(data) {
				if (data != null && data.length > 0) {
					var content = "<div class='col-sm-2' ><select name='parentId' class='form-control' onchange='addType(this)' id='type0'><option value='0'>-- 请选择父类 --</option>";
					for ( var type in data) {
						content += "<option value='"+data[type].id+"'>"
								+ data[type].name + "</option>";
					}
					content += "</select></div>";
					$("#types").append(content);
				}
			}, "json");
		}
	}
</script>
</head>
<body>
	<div class="container">
		<h1 class="text-center text-danger">添加商品分类</h1>
		<form id="myForm" role="form" action="${pageContext.request.contextPath }/goods/add.do" method="post">
			<!-- 账号，密码，提交 -->
			<div class="form-group col-md-12">
				<label id="label1" class="col-md-2 control-label" for="name">分类名：</label>
				<div class="col-md-10">
					<input class="form-control" name="name" type="text" id="name" placeholder="classifyname"
						value="${productTypeBean.name }" />
				</div>
			</div>
			<div class="form-group col-md-12" id="types">
				<label id="label1" class="col-sm-2 control-label" for="name">父类：</label>
				<div class="col-sm-2">
					<select name="parentId" class="form-control" onchange="addType(this)" id="type0">
						<option value="0">请选择父类 </option>
						<c:forEach items="${productTypeList}" var="item">
							<option value="${item.id}">${item.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group col-md-12">
				<label id="label1" class="col-md-2 control-label" for="name">分类序号：</label>
				<div class="col-md-10">
					<input class="form-control" name="sort" type="text" id="sort" placeholder="分类序号"
						value="${productTypeBean.sort }" />
				</div>
			</div>
			<div class="form-group col-md-12">
				<label id="label1" class="col-md-2 control-label" for="name">分类描述：</label>
				<div class="col-md-10">
					<input class="form-control" name="desc" type="text" id="desc" placeholder="分类描述"
						value="${productTypeBean.intro }" />
				</div>
			</div>
			<div class="form-group col-md-12">
				<div class="col-md-offset-2 col-md-10">
					<button type="submit" id="subt" class="btn btn-primary btn-sm">提交</button>
				</div>
			</div>
			<input type="hidden" name="id" id="id" value="${productTypeBean.id }">
		</form>
	</div>
		${param.status==false?"<div class='alert alert-danger' role='alert'>添加分类失败</div>":"" }
		${param.status==true?"<div class='alert alert-success' role='alert'>添加分类成功</div>":"" }
</body>
</html>