<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <title>hello,guy! welcome to spring mvc home!!!</title>
</head>
<body>
    <h1>welcome</h1>
    <a href="<c:url value="/users" />">Users</a> |
    <a href="<c:url value="/register" />">Register</a>
     这里是body!
</body>
</html>
