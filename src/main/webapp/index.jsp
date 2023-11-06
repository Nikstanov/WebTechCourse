<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        <%@include file="WEB-INF/view/styles/main.css"%>
    </style>

<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/WEB-INF/view/styles/main.css">--%>
    <title>Test</title>
</head>
<jsp:include page="WEB-INF/view/common/header.jsp"/>
<body>
<br>
<div>
    <c:forEach var="movie" items="${movies}">
        <div class="movie_info">
            <a href="?command=movie&id=${movie.getId()}">${movie.getName()}</a>
            <p>${movie.getShortDescription()}</p>
        </div>
    </c:forEach>
</div>
<br>
<c:if test="${page > 0}">
    <a href="?command=main&page=${page-1}">Prev</a>
</c:if>
<c:if test="${page < maxPage}">
    <a href="?command=main&page=${page+1}">Next</a>
</c:if>
</body>
</html>
