<%--
  Created by IntelliJ IDEA.
  User: suhan
  Date: 2022/10/26
  Time: 8:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="<%=(String) application.getAttribute(\"locale\")%>" />
<fmt:setBundle basename="message" var="message" />

<fmt:message key="LOGIN_SUCCESS" bundle="${message}" var="login" />
<html>
<head>
    <title>Login Complete</title>
</head>
<body>
    <h1>
        <fmt:bundle basename="message">
            <fmt:message key="LOGIN_SUCCESS" />
        </fmt:bundle>
    </h1>
    <br/>
    <a href='/'>HOME</a><br />
    <br/>
    <a href='/logout.do'>Logout</a><br />
</body>
</html>
