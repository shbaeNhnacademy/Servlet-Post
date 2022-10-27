<%@ page import="com.nhnacademy.domain.repository.PostRepository" %><%--
  Created by IntelliJ IDEA.
  User: suhan
  Date: 2022/10/27
  Time: 3:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="<%=(String) application.getAttribute(\"locale\")%>"/>
<fmt:setBundle basename="message" var="message"/>

<fmt:message key="TITLE" bundle="${message}" var="title" />
<fmt:message key="WRITER_ID" bundle="${message}" var="writer" />
<fmt:message key="WRITE_TIME" bundle="${message}" var="time" />
<fmt:message key="VIEW" bundle="${message}" var="view" />


<%
    PostRepository postRepository = (PostRepository) application.getAttribute("postRepository");
%>

<html>
<head>
    <title>Post List</title>
</head>
<body>
    <table border="1" >
         <th>${title}</th> <th>${writer}</th> <th>${time}</th> <th>${view}</th>
        <tr>
            <td>1</td>
            <td>2</td>
            <td>3</td>
            <td>4</td>
        </tr>
    </table>
</body>
</html>
