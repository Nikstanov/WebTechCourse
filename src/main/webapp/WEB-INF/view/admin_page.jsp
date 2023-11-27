<%--
  Created by IntelliJ IDEA.
  User: nstah
  Date: 20.09.23
  Time: 01:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localizations.localization" var="loc"/>
<html>
<head>
    <title>Admin page</title>
</head>
<jsp:include page="common/header.jsp"/>
<body>
    <c:forEach var="user" items="${users}">
        <div>
            <p>${user.getLogin()}(<fmt:message key="user.rating" bundle="${loc}"/>: ${user.getSocialCredit()}) ${user.getRole().toString()}</p>
            <a href="/ban/${user.getId()}"><fmt:message key="user.ban" bundle="${loc}"/></a>
            <a href="/delete/${user.getId()}"><fmt:message key="user.delete" bundle="${loc}"/></a>
            <br>
        </div>
    </c:forEach>
</body>
</html>
