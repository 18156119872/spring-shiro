<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/27
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>这是登录界面</title>
    <style>
        #tips{
            color: red;
        }
    </style>
</head>
<body>
<h1>登录</h1>
<span id="tips">${errorMsg}</span>
<form id="myform" method="post" action="/login">
    账号:<input name="username" type="text"><br>
    密码:<input name="password" type="password"><br>
    <input type="submit" value="登录">
</form>
</body>
</html>
