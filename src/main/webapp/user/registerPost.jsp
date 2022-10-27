<%--
  Created by IntelliJ IDEA.
  User: suhan
  Date: 2022/10/27
  Time: 1:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="<%=(String) application.getAttribute(\"locale\")%>"/>
<fmt:setBundle basename="message" var="message"/>

<fmt:message key="TITLE" bundle="${message}" var="title" />
<fmt:message key="SUBMIT" bundle="${message}" var="submit" />

<html>
<head>
    <title>Make Post</title>
</head>
<body>
    <form method="post" action="/posts.do">
        ${title} <input type="text" name="title"  />
        <br />
        <br />
        <textarea name="content" rows="10" cols="80" placeholder="ex)123가나다라abc"></textarea>
        <br />
        <br />
        <input type="submit" value="${submit}"/>
    </form>
</body>
</html>
