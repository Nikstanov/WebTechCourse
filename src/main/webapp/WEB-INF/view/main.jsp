<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localizations.localization" var="loc"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movie Hub</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        nav {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
        }

        nav a {
            color: #fff;
            text-decoration: none;
            margin: 0 15px;
        }

        .movies-container {
            margin: 20px;
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
        }

        .movie-card {
            width: 250px;
            margin: 15px;
            padding: 10px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .pagination {
            text-align: center;
            margin-top: 20px;
        }


        .pagination button {
            padding: 8px 16px;
            margin: 0 5px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .pagination button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<jsp:include page="common/header.jsp"/>
<body>

<div class="movies-container">

    <!-- Movie Cards will be dynamically generated here -->


    <c:forEach var="movie" items="${movies}">
        <div class="movie-card">
                <img src="${pageContext.request.contextPath}/images/image.jpg" alt="image"/>
            <a href="/movie/${movie.getId()}">${movie.getName()}</a>
            <p>${movie.getShortDescription()}</p>
        </div>
    </c:forEach>
    <!-- Repeat the movie card structure for other movies -->

</div>

<div class="pagination">
    <c:if test="${page > 0}">
        <a href="/main?page=${page-1}"><fmt:message key="main.prev" bundle="${loc}"/></a>
    </c:if>
    <c:if test="${page < maxPage}">
        <a href="/main?page=${page+1}"><fmt:message key="main.next" bundle="${loc}"/></a>
    </c:if>
</div>

</body>

</html>

