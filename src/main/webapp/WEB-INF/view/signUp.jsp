<%--
  Created by IntelliJ IDEA.
  User: nstah
  Date: 20.09.23
  Time: 00:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<jsp:include page="common/header.jsp"/>
<body>
<form action="${pageContext.request.contextPath}?command=sign_up" method="post">
    <label for="email_form">Email</label><br/>
    <input id="email_form" type="email" name="email"><br/>
    <label for="username_form">Username</label><br/>
    <input id="username_form" type="text" name="username"><br/>
    <label for="password_form">Password</label><br/>
    <input id="password_form" type="password" name="password"><br/>
    <input id="submit_button" type="submit"><br/>
</form>
</body>
</html>
