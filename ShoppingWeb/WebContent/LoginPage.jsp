<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
<style type="text/css">
*{
	font:14px "MicrosoftYaHei";
	padding:0px;margin: 0px;
}

.all{
	margin:20px;
	width:290px;
	height:150px;
	padding=5px;
}

.all ul{
	display:block;
	border-bottom:2px solid saddlebrown;
	margin:0px;
	
	line-height:20px;
	padding:0px auto;
}

.all ul li{
	border:1px solid #aaaaaa;
	border-bottom:none;
	padding:5px 10px;
	height:18px;
	line-height:18px;
	cursor:pointer;
	margin:0px 3px;;
	text-align:center;
	display:inline-block;
	background:#fff;
}

.all ul li.on{
	border-top:2px solid saddlebrown;
	border-bottom:2px solid #fff;
}

.all div{
border:1px solid #aaaaaa;
height:120px;
line-height:25px;
padding:5px;
border-top:none;
}

.hide{
display:none;
}
</style>
</head>
<body>
<%
	String username="";
	String password="";
	Cookie [] cookies = request.getCookies();
	if(cookies!=null&&cookies.length>0)
	{
		for(Cookie c:cookies)
		{
			if(c.getName().equals("username"))
			{
				username=c.getValue();	
			}
			else if(c.getName().equals("password"))
			{
				password=c.getValue();
			}
		}
	}
%>
<h1>登陆页面</h1>
<div class="all">
<ul>
<li class="on">登陆</li>
<li>注册</li>
</ul>
<div>
<form action="servlet/SigninServlet" method="post">
<table>
<tbody>
<tr>
<td>
<label for="username">账号：</label>
</td>
<td>
<input id="username" type="text" name="username" value="<%=username %>"/>
</td>
</tr>
<tr>
<td>
<label for="password">账号：</label>
</td>
<td>
<input id="password" type="password" name="password" value="<%=password %>"/>
</td>
</tr>
<tr>
<td colspan="2">
<input id="cookie" type="checkbox" value="useCookie" name="whetherCookie" checked="checked"/>
<label for="cookie">一天内记住密码</label>
</td>
</tr>
<tr>
<td colspan="2">
<input type="submit" value="确定" name="submit"/>
<input type="reset" value="重置" name="reset"/>
</td>
</tr>
</tbody>
</table>
</form>
<br></div>
<div class="hide">
<form action="servlet/SignupServlet" method="post">
<table>
<tbody>
<tr>
<td>用户名:</td>
<td><input type="text" name="name"/></td>
</tr>
<tr>
<td>密码:</td>
<td><input type="password" name="password1"></td>
</tr>
<tr>
<td>确认密码:</td>
<td><input type="password" name="password2"></td>
</tr>
<td colspan="2">
<input type="submit" value="注册" name="submit"/>
<input type="reset" value="取消" name="reset"/>
</td>
</tbody></table>
</form>
<br>
</div>

</div>
</body>
<script type="text/javascript">
function change(obj){
var li = document.getElementsByTagName("li");
var div = document.getElementsByTagName("div");
for(var i=0;i<li.length;i++){
li[i].className="";
div[i+1].className="hide"}
obj.className="on";
document.getElementsByTagName("div")[obj.index+1].className="";
}

window.onload =function (){
	var li = document.getElementsByTagName("li");
	for(var i=0;i<li.length;i++){
		li[i].index=i;
		li[i].setAttribute("onclick","change(this)");
	}
}
</script>
</html>