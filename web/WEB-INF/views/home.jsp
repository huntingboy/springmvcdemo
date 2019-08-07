<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <title>hello,guy! welcome to spring mvc home!!!</title>
</head>
<body>
    <h1>welcome</h1>
    <s:escapeBody htmlEscape="true">
        <h1>hello</h1>
        <%--<s:url href="/***" var="**" />不能用？？？ ${***}--%>
    </s:escapeBody>
    <a href="<c:url value="/users" />">Users</a> |
    <a href="<c:url value="/register" />">Register</a> |
    <a href="<c:url value="/register1" />">Register1</a>
     这里是body!
</body>
</html>
