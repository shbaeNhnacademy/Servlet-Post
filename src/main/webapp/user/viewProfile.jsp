<%@ page import="com.nhnacademy.command.CommandUtil" %>
<%@ page import="com.nhnacademy.domain.user.User" %>
<%@ page import="java.io.File" %><%--
  Created by IntelliJ IDEA.
  User: sh
  Date: 2022/10/30
  Time: 2:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="<%=(String) application.getAttribute(\"locale\")%>"/>
<fmt:setBundle basename="message" var="message"/>

<fmt:message key="NAME" bundle="${message}" var="name" />

<%
  pageContext.setAttribute("user", (User) request.getAttribute("user"));


%>

<html>
<head>
    <title>View User Profile</title>
</head>
<body>
  <p> ${name} :  <input type="text"  name="name" value="${user.name}" placeholder="Name" readonly /></p>
  <p><img src="${CommandUtil.UPLOAD_DIR+=File.separator+=user.profileFileName}"></p>

</body>
</html>
