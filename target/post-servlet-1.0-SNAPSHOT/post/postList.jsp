<%@ page import="com.nhnacademy.domain.repository.PostRepository" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Objects" %><%--
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
<fmt:message key="POST_LIST" bundle="${message}" var="post_list" />
<fmt:message key="HOME" bundle="${message}" var="home" />

<%
    PostRepository postRepository = (PostRepository) application.getAttribute("postRepository");
    pageContext.setAttribute("postList",postRepository.getPosts());
%>

<html>

<style>
    * {
        width: 100%;
        height: 100%;
        margin: 0;
        box-sizing: border-box;
    }

    header{
        width: 100%;
        height: 10%;
    }
    main{
        width: 100%;
        height: 85%;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    footer{
        width: 100%;
        height: 5%;
    }
    table,th,td{
        border: 1px solid black;
        font-size: 13pt;
    }

    .table{
        width: 80%;
        height: 30%;
        table-layout: fixed;
        text-align: center;
        overflow: hidden;
    }
    .tableHead{
        height: 30px;
    }

</style>

<head>
    <title>Post List</title>
</head>


<body>
<header>
    <h1>${post_list}</h1><br>
</header>
<main>
    <table class="table">
        <th class="tableHead" style="width: 10%">No.</th>
        <th class="tableHead" style="width: 45%">${title}</th>
        <th class="tableHead" style="width: 15%">${writer}</th>
        <th class="tableHead" style="width: 20%">${time}</th>
        <th class="tableHead" style="width: 10%">${view}</th>
        <c:forEach var="post" items="${postList}">
            <tr class="tableRow" >
                <td onclick="location.href='/posts/${post.id}.do'">${post.id}</td>
                <td onclick="location.href='/posts/${post.id}.do'">${post.title}</td>
                <td onclick="location.href='/users/${post.writerUserId}.do'">${post.writerUserId}</td>
                <td onclick="location.href='/posts/${post.id}.do'">
                        ${DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(post.writeTime)}
                </td>
                <td onclick="location.href='/posts/${post.id}.do'">
                        ${post.viewCount}
                </td>
            </tr>
        </c:forEach>
    </table>
</main>
<a href='/'>${home}</a><br />
</body>
<footer>

</footer>


</html>
