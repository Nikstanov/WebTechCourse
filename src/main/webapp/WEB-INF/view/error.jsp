<%--
  Created by IntelliJ IDEA.
  User: nstah
  Date: 06.11.23
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localizations.localization" var="loc"/>
<html>
<head>
    <title>Some troubles</title>
</head>
<body>
    <p>Exception occurred while processing the request. Try reconnect later</p>
</body>
</html>
