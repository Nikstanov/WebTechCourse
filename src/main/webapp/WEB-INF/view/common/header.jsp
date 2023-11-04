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

<fmt:setLocale value="ru"/>
<fmt:setBundle basename="localizations.localization" var="loc"/>
<header>
  <nav>
    <a href="?command=main"><fmt:message key="bar.home" bundle="${loc}"/></a>
  </nav>
</header>

