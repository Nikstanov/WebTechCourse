<%--
  Created by IntelliJ IDEA.
  User: nstah
  Date: 20.09.23
  Time: 01:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localizations.localization" var="loc"/>
<html>
<head>
    <title>Movie</title>
    <style>
        <%@include file="styles/movie.css"%>
    </style>
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

    <c:if test="${role != 'NOT_AUTH'}">
        <style>
            <%@include file="styles/movie.css"%>
        </style>
        <form class="reviewForm" action="?command=review" method="post">
            <h2 style="text-align: center"><fmt:message key="review.reviewForm" bundle="${loc}"/></h2>
            <div class="inputReviewPart">
                <div class="markReviewPart">
                    <c:forEach begin="1" end="5" step="1" var="mark">
                        <div class="mark">
                            <label style="text-align: center; font-size: 1.5em" for="mark${mark}">${mark}</label>
                            <input type="radio" id="mark${mark}" name="mark" value="${mark}">
                        </div>
                    </c:forEach>
                </div>
                <textarea class="textArea" id="review" name="review" rows="4" cols="50"></textarea>
            </div>
            <input id="submit_button" type="submit" value="<fmt:message key="form.submitBtn" bundle="${loc}"/>">
        </form>
    </c:if>

    <br>
    <p><fmt:message key="review.reviewSection" bundle="${loc}"/></p>
    <c:forEach var="review" items="${movie.getReviewList()}">
        <hr>
        <div>
            <p>${review.getOwnerName()}(<fmt:message key="user.rating" bundle="${loc}"/>: ${review.getSocialCredit()})</p><br>
            <p>${review.getMark()}</p><br>
            <p>${review.getReview()}</p><br>
        </div>
    </c:forEach>

</body>
</html>
