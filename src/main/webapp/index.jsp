<%@ page import="java.util.Map" %>
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: suhan
  Date: 2022/10/27
  Time: 10:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="<%=(String) application.getAttribute(\"locale\")%>"/>
<fmt:setBundle basename="message" var="message"/>

<fmt:message key="ADMIN_LINK" bundle="${message}" var="admin_page" />

<html>
<head>
    <title>Merge's Post</title>
</head>
<body>
<%

    Map<String, HttpSession> sessionMap;
    Object objMap = application.getAttribute("sessionMap");
    if (!Objects.isNull(objMap)) {
        sessionMap = (Map<String, HttpSession>) objMap;
    }
    HttpSession session = request.getSession(false);
    String s_id = "";
    if (!Objects.isNull(session)) {
        s_id = (String) session.getAttribute("id");
        System.out.println(s_id);
    }
%>
Main
<br /><a href="user/registerPost.jsp">register post</a> <br />
<br />
<br />
<br />
<c:if test="${sessionMap.containsKey('admin')}">
    <br /><a href="/admin/admin.jsp">${admin_page}</a> <br />
</c:if>

<c:out value="${s_id}" default="11111" />
${s_id}
<c:choose>
    <c:when test="${(session.getAttribute('id') == null)}">
        <br /><a href="login.do">LOGIN</a> <br />
    </c:when>
    <c:otherwise>
        <br /><a href="logout.do">LOGOUT</a> <br />
    </c:otherwise>
</c:choose>



<br />
<br />
<br />
<a href="/set-cookie.do?locale=ko">한국어</a>
<a href="/set-cookie.do?locale=en">English</a>

</body>
</html>
