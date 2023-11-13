<%--
  Created by IntelliJ IDEA.
  User: nstah
  Date: 20.09.23
  Time: 00:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localizations.localization" var="loc"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up - Movie Hub</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
        }

        form {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin: 10px 0 5px;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            width: 100%;
            padding: 8px 16px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>

<body>

<jsp:include page="common/header.jsp"/>

<form action="${pageContext.request.contextPath}?command=sign_up" method="post">
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>

    <label for="username"><fmt:message key="form.username" bundle="${loc}"/>:</label>
    <input type="text" id="username" name="username" required>

    <label for="password"><fmt:message key="form.password" bundle="${loc}"/>:</label>
    <input type="password" id="password" name="password" required>

    <button type="submit"><fmt:message key="form.submitBtn" bundle="${loc}"/></button>
</form>

</body>

</html>

