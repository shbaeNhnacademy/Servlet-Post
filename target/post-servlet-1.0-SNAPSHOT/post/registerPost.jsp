<%@ page import="java.util.Objects" %><%--
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
<fmt:message key="WRITER_ID" bundle="${message}" var="wr_id" />
<fmt:message key="CONTENT" bundle="${message}" var="content" />
<fmt:message key="SUBMIT" bundle="${message}" var="submit" />
<fmt:message key="POST_LIST" bundle="${message}" var="post_list" />


<%
    HttpSession session = request.getSession(false);
    if (!Objects.isNull(session)) {
        pageContext.setAttribute("id", session.getAttribute("id"));
    }

%>

<html>
<head>
    <title>Make Post</title>
</head>
<body>
    <form method="post" action="/posts.do">
        <p> ${title} : <input type="text" name="title"  /></p>
        <p>${wr_id} :  <input type="text" name="writer_id" value="${id}" readonly /></p>
        <br />
        <p>
            <p>${content} : </p>
            <textarea name="content" rows="10" cols="80" placeholder="ex)123가나다라abc"></textarea>
        </p>
        <br />
        <br />
        <input type="submit" value="${submit}"/>
    </form>

<br>
<br>
<br /> <a href='/post/postList.jsp'>${post_list}</a><br />
</body>
</html>
