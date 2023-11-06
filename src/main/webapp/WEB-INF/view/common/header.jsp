<%--
  Created by IntelliJ IDEA.
  User: nstah
  Date: 04.11.23
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="localizations.localization" var="loc"/>
<style>
  <%@include file="../styles/header.css"%>
</style>
<header>
  <nav>
    <a href="?command=main"><fmt:message key="bar.home" bundle="${loc}"/></a>
    <c:if test="${role == 'NOT_AUTH'}">

      <c:if test="${com_page != 'SIGN_IN_PAGE'}">
        <a href="?command=sign_in_page"><fmt:message key="bar.sign_in" bundle="${loc}"/></a>
      </c:if>
      <c:if test="${com_page != 'SIGN_UP_PAGE'}">
        <a href="?command=sign_up_page"><fmt:message key="bar.sign_up" bundle="${loc}"/></a>
      </c:if>
    </c:if>
    <c:if test="${role != 'NOT_AUTH'}">
      <a href="?command=log_out"><fmt:message key="bar.log_out" bundle="${loc}"/></a>
    </c:if>
    <c:if test="${role == 'ADMIN'}">
      <a href="?command=admin_page"><fmt:message key="bar.admin" bundle="${loc}"/></a>
    </c:if>
    <a href="?command=change_lang">${lang}</a>
  </nav>
</header>

