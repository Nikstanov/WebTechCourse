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

  .language-btn {
    float: right;
    margin-right: 20px;
    margin-top: 10px;
    padding: 8px 16px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
  }

  .language-btn:hover {
    background-color: #0056b3;
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
      <a href="?command=sign_out"><fmt:message key="bar.log_out" bundle="${loc}"/></a>
    </c:if>
    <c:if test="${role == 'ADMIN'}">
      <a href="?command=admin_page"><fmt:message key="bar.admin" bundle="${loc}"/></a>
    </c:if>
    <a class="language-btn" href="?command=change_lang">${lang}</a>
  </nav>
</header>

