<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Test</title>
</head>
<jsp:include page="WEB-INF/view/common/header.jsp"/>
<body>
<c:forEach var="movie" items="${movies}">
    <br>
    <a href="?command=movie&id=${movie.getId()}">${movie.getName()}</a>
    <br>
    <p>${movie.getShortDescription()}</p>
    <br><br>
</c:forEach>
<br>
<c:if test="${page > 0}">
    <a href="?command=main&page=${page-1}">Prev</a>
</c:if>
<c:if test="${page < maxPage}">
    <a href="?command=main&page=${page+1}">Next</a>
</c:if>
</body>
</html>
