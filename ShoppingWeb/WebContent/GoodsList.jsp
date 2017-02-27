<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="database.GoodsUtil"%>
<%@ page import="model.Good"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>StartPage</title>
<style type="text/css">
li {
	float: left;
	margin: 10px;
}
</style>
</head>
<body>
	<jsp:useBean id="goodsDao" class="dao.GoodsDAO" scope="page"></jsp:useBean>
	<h1>商品列表</h1>
	<ul width="750" height="180">
		<%
			List<Good> list = goodsDao.query();
			if (list != null && list.size() > 0) {
				for (Good goods : list) {
		%>
		<li>
			<div>
				<a href="GoodsMessage.jsp?id=<%=goods.getId()%>"> <img
					src="./images/<%=goods.getPicture()%>" width="200px" height="138px"><br />
					名称：<span id="name"><%=goods.getName()%></span><br /> 价格：<span
					id="price"><%=goods.getPrice()%></span><br />
				</a>
			</div>
		</li>
		<%
			}
			}
		%>
	</ul>
</body>
</html>