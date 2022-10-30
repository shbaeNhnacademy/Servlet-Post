<%@ page import="java.util.Map" %>
<%@ page import="java.util.Objects" %>
<%@ page import="java.util.Optional" %>
<%@ page import="com.nhnacademy.domain.user.LoginUser" %><%--
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
<fmt:message key="CURRENT_USERS" bundle="${message}" var="cur_users" />
<fmt:message key="VISITORS" bundle="${message}" var="visitors" />
<fmt:message key="REGISTER" bundle="${message}" var="register" />
<fmt:message key="POST" bundle="${message}" var="post" />
<fmt:message key="POST_LIST" bundle="${message}" var="post_list" />
<fmt:message key="HOME" bundle="${message}" var="home" />
<fmt:message key="ADMIN" bundle="${message}" var="admin_" />
<fmt:message key="USER" bundle="${message}" var="user_" />

<html>
<head>
    <title>Merge's Post</title>
</head>
<body>
<%
    pageContext.setAttribute("sMap",application.getAttribute("sessionMap"));
    pageContext.setAttribute("id", "");
    pageContext.setAttribute("admin", "");

    HttpSession session = request.getSession(false);
    if (!Objects.isNull(session)) {
        Object id = session.getAttribute("id");
        pageContext.setAttribute("id", id);
        if (Objects.isNull(id)) {
            Object admin = session.getAttribute("admin");
            pageContext.setAttribute("admin", admin);
        }
    }
    pageContext.setAttribute("current_users", LoginUser.getSessionCount());
%>
<p>
<h1>
    ${home}
</h1>

<span>
    <c:choose>
        <c:when test="${(empty id) && (empty admin)}">
            <br /><a href="login.do">LOGIN</a> <br />
        </c:when>
        <c:when test="${not empty admin}">
            <span>${admin_} : </span><span style="font-weight: bold; color: red">${admin}&nbsp;&nbsp;</span>
            <br /><a href="logout.do">LOGOUT</a> <br />
        </c:when>
        <c:otherwise>
            <span>${user_} : </span><span style="font-weight: bold; color: blue">${id}&nbsp;&nbsp;</span>
            <br /><a href="logout.do">LOGOUT</a> <br />
        </c:otherwise>
    </c:choose>
</span>
</p>


<hr>
<br /><a href="post/registerPost.jsp">${register} ${post}</a> <br />
<br />
<br /><a href="post/postList.jsp">${post_list}</a> <br />
<br />
<br />
<hr>
<c:if test="${sMap.containsKey('admin')}">
    <br /><a href="/admin/admin.jsp">${admin_page}</a> <br /><hr>
</c:if>




<br />
<br />
<br />
<br />
<p>
    <span>
        ${visitors} : ${applicationScope.visitCount}
    </span>
    &nbsp;/&nbsp;
    <span>
        ${cur_users} : ${current_users}
    </span>
</p>
<br />
<a href="/set-cookie.do?locale=ko">한국어</a>
<a href="/set-cookie.do?locale=en">English</a>

</body>
</html>
