<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localizations.localization" var="loc"/>
<html>
<head>
    <title>Sign In</title>
</head>
<jsp:include page="common/header.jsp"/>
<body>
    <form action="?command=sign_in" method="post">
        <label for="email_form">Email</label><br/>
        <input id="email_form" type="email" name="email"><br/>
        <label for="password_form"><fmt:message key="form.password" bundle="${loc}"/></label><br/>
        <input id="password_form" type="password" name="password"><br/>
        <input id="submit_button" type="submit" value="<fmt:message key="form.submitBtn" bundle="${loc}"/>"><br/>
    </form>
</body>
</html>
