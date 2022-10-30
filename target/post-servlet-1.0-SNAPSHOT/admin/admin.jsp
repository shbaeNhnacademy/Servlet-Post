<%--
  Created by IntelliJ IDEA.
  User: suhan
  Date: 2022/10/27
  Time: 10:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="<%=(String) application.getAttribute(\"locale\")%>"/>
<fmt:setBundle basename="message" var="message"/>

<fmt:message key="USER_LIST" bundle="${message}" var="u_list" />
<fmt:message key="USER_ADD" bundle="${message}" var="u_add" />
<fmt:message key="ADMIN_LINK" bundle="${message}" var="admin_link" />
<fmt:message key="HOME" bundle="${message}" var="home" />


<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<h1>${admin_link}</h1> <br>

<a href="/users.do">${u_list}</a> <br> <br> <br>
<a href="/admin/addUser.jsp">${u_add}</a> <br> <br> <br>
<br /> <a href='/'>${home}</a><br />
</body>
</html>
