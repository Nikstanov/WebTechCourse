<%--
  Created by IntelliJ IDEA.
  User: nstah
  Date: 20.09.23
  Time: 01:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie</title>
</head>
<jsp:include page="common/header.jsp"/>
<body>
    <p>${movie.getName()}</p>
    <br>
    <p>${movie.getAuthor()}</p>
    <br>
    <p>${movie.getDescription()}</p>
    <br>
    <p>${movie.getName()}</p>
    <br>
    <p>${movie.getAverageMark()}</p>
    <br>
</body>
</html>
