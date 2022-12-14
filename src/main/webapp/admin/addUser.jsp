<%--
  Created by IntelliJ IDEA.
  User: suhan
  Date: 2022/10/27
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="<%=(String) application.getAttribute(\"locale\")%>"/>
<fmt:setBundle basename="message" var="message"/>

<fmt:message key="REGISTER" bundle="${message}" var="register" />
<fmt:message key="NAME" bundle="${message}" var="name" />
<fmt:message key="ADMIN" bundle="${message}" var="admin" />
<fmt:message key="USER_ADD" bundle="${message}" var="us_add" />

<html>
<head>
    <title>Add User</title>
</head>
<body>

<h2>${us_add}</h2>
<hr>

<form method="post" action="/users.do" enctype="multipart/form-data">
    ID <input type="text"  name="id" placeholder="ID"  maxlength="15" required />
    <br/>
    <br/>
    PWD <input type="password"  name="pwd" placeholder="Password" required />
    <br/>
    <br/>
    ${name} <input type="text"  name="name" placeholder="Name" required />
    <br/>
    <br/>
    <input type="file" name="file"/><br /><br />
    <input type="submit" value="${register}" /><br />

    <br /> <br /> <a href='/admin/admin.jsp'>${admin}</a><br />
</form>
</body>
</html>
