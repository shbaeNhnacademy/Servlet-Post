<%--
  Created by IntelliJ IDEA.
  User: suhan
  Date: 2022/10/26
  Time: 5:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="<%=(String) application.getAttribute(\"locale\")%>" />
<fmt:setBundle basename="message" var="message" />
<fmt:message key="SUBMIT" bundle="${message}" var="submit" />

<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post" action="/login.do">
  ID      <input type="text"  name="id" placeholder="ID" />
  <br/>
  <br/>
  PWD <input type="password"  name="pwd" placeholder="Password" />
  <br/>
  <br/>
  <input type="submit" value="${submit}" />
</form>

<br /><a href='/'>HOME</a><br />
</body>
</html>
