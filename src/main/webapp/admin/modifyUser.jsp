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

<fmt:message key="NAME" bundle="${message}" var="name" />
<fmt:message key="USER_LIST" bundle="${message}" var="u_list" />
<fmt:message key="MODIFY" bundle="${message}" var="modify" />
<fmt:message key="USER" bundle="${message}" var="user_" />

<%
    String id = (String) request.getAttribute("id");
%>

<html>
<head>
    <title>Modify User</title>
</head>
<body>

<h2>${modify} ${user_}</h2>
<hr>

<form method="post" action="/users/change.do" enctype="multipart/form-data">
    ID <input type="text"  name="id"  value="${id}" readonly style="color: red" />
    <br/>
    <br/>
    PWD <input type="password"  name="pwd" placeholder="Password" />
    <br/>
    <br/>
    ${name} <input type="text"  name="name" placeholder="Name" />
    <br/>
    <br/>
    <input type="file" id="files" name="file"/><br /><br />
    <input type="submit" value="${modify}" /><br />
</form>

<br /> <br /> <a href='/admin/userList.jsp'>${u_list}</a><br />
</body>
</html>
