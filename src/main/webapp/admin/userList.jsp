<%@ page import="com.nhnacademy.domain.repository.UserRepository" %>
<%@ page import="com.nhnacademy.domain.user.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: suhan
  Date: 2022/10/27
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="<%=(String) application.getAttribute(\"locale\")%>"/>
<fmt:setBundle basename="message" var="message"/>

<fmt:message key="USER_LIST" bundle="${message}" var="u_list" />
<fmt:message key="USER_ADD" bundle="${message}" var="u_add" />
<fmt:message key="MODIFY" bundle="${message}" var="modify" />
<fmt:message key="DELETE" bundle="${message}" var="delete" />
<fmt:message key="ADMIN" bundle="${message}" var="admin" />

<%
  UserRepository userRepository = (UserRepository) application.getAttribute("userRepository");
  List<User> users = userRepository.getUsers();
  pageContext.setAttribute("users", (List<User>) users);
%>

<html>
<head>
    <title>User List</title>
</head>
<body>
<form method="post" >
  <c:forEach var="user" items="${users}">
    <input type="checkbox" name="${user.id}" > <span> ${user.name}  /  ${user.profileFileName}  </span> <br>
  </c:forEach>
  <br><br>
  <input type="submit" value="${modify}" onclick="javascript: form.action='/users/modify.do';"/>
  <input type="submit" value="${delete}" onclick="javascript: form.action='/users/delete.do';"/>
</form>

<br /> <br /> <a href='/admin/admin.jsp'>${admin}</a><br />

</body>
</html>
