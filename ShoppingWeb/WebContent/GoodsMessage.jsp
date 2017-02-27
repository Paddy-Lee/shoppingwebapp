<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dao.GoodsDAO"%>
<%@page import="model.Good"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type=" text/css">
#goods{
float:left;
}
td{
margin:10px;
}
</style>
</head>
<body>
	<jsp:useBean id="goodsDao" class="dao.GoodsDAO" scope="page"></jsp:useBean>
	<%
		Good goods = goodsDao.get(Integer.valueOf(request.getParameter("id")));

		//设置Cookie
		String idString = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("viewedList")) {
					idString = cookie.getValue();
				}
			}
		}
		idString += request.getParameter("id") + "#";
		String[] idArr = idString.split("#");
		if (idArr.length >= 100) {
			idString = "";
		}
		Cookie viewedGoodsCookie = new Cookie("viewedList", idString);
		viewedGoodsCookie.setMaxAge(1000);
		response.addCookie(viewedGoodsCookie);
	%>
	<div id="goods" width="600px">
		<img src="./images/<%=goods.getPicture()%>" width="400px"
			height="276px"><br /> 名称：<%=goods.getName()%><br /> 价格：￥<%=goods.getPrice()%><br />
		产地：<%=goods.getCity()%><br /> 数量：<%=goods.getNumber()%><br />
	</div>
	</br>
	</br>
	<div>浏览记录</div>
	</br>
	<table id="viewedGoods">
		<tr>
			<%
				//查看Cookie
				List<Good> paramGoods = new ArrayList<>();
				paramGoods = goodsDao.getViewedList(idString);
				if (paramGoods != null && paramGoods.size() > 0) {
					for (Good goods2 : paramGoods) {
			%>

			<td>
				<div>
					<img src="./images/<%=goods2.getPicture()%>" width="150px"
						height="103px" /><br /> 名称：<%=goods2.getName()%><br /> 价格：￥<%=goods2.getPrice()%><br />
				</div>
			</td>
			<%
				}
				}
			%>
		</tr>
	</table>

</body>
</html>