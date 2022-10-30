<%@ page import="com.nhnacademy.domain.post.Post" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
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
<fmt:message key="CONTENT" bundle="${message}" var="content" />
<fmt:message key="WRITER_ID" bundle="${message}" var="wr_id" />
<fmt:message key="WRITE_TIME" bundle="${message}" var="wr_time" />
<fmt:message key="VIEW" bundle="${message}" var="view" />
<fmt:message key="MODIFY" bundle="${message}" var="modify" />
<fmt:message key="DELETE" bundle="${message}" var="delete" />
<fmt:message key="POST_LIST" bundle="${message}" var="post_list" />


<%
    Post post = (Post) request.getAttribute("post");
    pageContext.setAttribute("post", post);
    pageContext.setAttribute("postId", post.getWriterUserId());

    HttpSession session = request.getSession(false);
    if (!Objects.isNull(session)) {
        pageContext.setAttribute("id", session.getAttribute("id"));
    }
%>

<html>
<head>
    <title>View Post Content</title>
</head>
<body>
<c:choose>
    <c:when test="${postId eq id}">
        <form method="post">
            <input type="hidden" name="id" value="${post.id}" />
            <p>${title} : <input style="width: 600px; table-layout: auto; overflow: auto;" type="text" name="title" value="${post.title}" /></p>
            <p>${wr_id} :
                <input style="width: 100px" type="text" name="writer_id" readonly value="${post.writerUserId}" />
                /   ${wr_time} :
                <span>
                <input type="text" name="write_time" readonly
                       value="${DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(post.writeTime)}" />
            </span>
            </p>
            <br><br>
            <p>
            <p>${content} : </p>
            <textarea name="content" rows="10" cols="80" >${post.content}</textarea>
            </p>
            <br><br>
            <p>${view} : <input type="text" name="view" readonly value="${post.viewCount}" /></p>
            <br><br>
            <input type="submit" value="${modify}" onclick="javascript: form.action='/posts/modify.do';"/>
            <input type="submit" value="${delete}" onclick="javascript: form.action='/posts/delete.do';"/>
        </form>
    </c:when>
    <c:otherwise>
        <p>${title} : <input style="width: 600px; table-layout: auto; overflow: auto;" type="text" name="title" readonly value="${post.title}" /></p>
        <p>${wr_id} :  <input style="width: 100px" type="text" name="writer_id" readonly value="${post.writerUserId}" />
            /   ${wr_time} : <span> <input type="text" name="write_time" readonly
                                           value="${DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(post.writeTime)}" />
    </span>
        </p>
        <br><br>
        <p>
        <p>${content} : </p>
        <textarea name="content" rows="10" cols="80" readonly >${post.content}</textarea>
        </p>
        <br><br>
        <p>${view} : <input type="text" name="view" readonly value="${post.viewCount}" /></p>
    </c:otherwise>
</c:choose>

<br /> <a href='/post/postList.jsp'>${post_list}</a><br />

</body>
</html>
