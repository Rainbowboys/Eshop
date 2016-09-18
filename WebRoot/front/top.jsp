<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <a class="navbar-brand" target="_parent" href="${pageContext.request.contextPath}/front/productShow/productShowServlet?method=sort&id=0">EShop</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="${pageContext.request.contextPath}/front/shopping/shoppingServlet?method=toCart">预留连接 <span class="sr-only">(current)</span></a></li>
      </ul>
      <form class="navbar-form navbar-left" action="${pageContext.request.contextPath}/front/productShow/productShowServlet?method=search" method="post" role="search">
        <div class="form-group">
          <input type="text" name="key"  class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">搜索</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="${pageContext.request.contextPath}/front/user/userInfo.jsp">用户名：${userBean.username}</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">设置<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a target="_parent" href="${pageContext.request.contextPath}/front/user/userInfo.jsp">账户信息</a></li>
            <li><a target="_parent" href="${pageContext.request.contextPath}/front/shopping/shoppingServlet?method=toCart">购物车</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a target="_parent" href="${pageContext.request.contextPath}/front/user/userServlet?method=end&status=1">注销</a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
