<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品展示</title>
<script
	src="${pageContext.request.contextPath}/static/js/jquery-1.12.1.js"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
<script
	src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.js"
	type="text/javascript"></script>

</head>
<body>
	<div class="container">
		<form role="form" class="form-horizontal" id="myForm" action="shoppingServlet?method=addcomment" method="post">
			<div class="form-group col-md-12">
				<label class="col-md-2 control-label" for="name">商品名称：</label>
				<div class="col-md-2">
					<a href="">${productBean.name}</a>
				</div>
				<div class="col-md-4">价格：￥${productBean.price}</div>
			</div>
			<div class="form-group col-md-12">
				<label class="col-md-2 control-label" for="stars">星级：</label> <input
					type="radio" name="score" id="star1" value="1">一星 <input
					type="radio" name="score" id="star2" value="2">二星 <input
					type="radio" name="score" id="star3" value="3">三星 <input
					type="radio" name="score" id="star4" value="4">四星 <input
					type="radio" name="score" id="star5" value="5">五星
			</div>
			<div class="form-group col-md-12">
				<label class="col-md-2 control-label" for="name">评论：</label>
				<div class="col-md-10">
					<textarea name="comment"  style="width: 600px; height: 200px;"></textarea>
				</div>
			</div>
			<input type="hidden" name="productId" value="${productBean.id}">
			<div class="form-group col-md-12">
				<div class="col-md-offset-2 col-md-10">
					<button type="submit" id="subt" class="btn btn-primary">提交</button>
				</div>
			</div>
		</form>
	</div>
	${param.status==1?"<div  class='alert alert-danger' role='alert'>操作成功！</div>":"" }
</body>
</html>