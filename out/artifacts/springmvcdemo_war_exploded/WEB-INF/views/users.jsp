<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<c:forEach items="${userList}" var="user">
    <li id="user_<c:out value="user.id" />">
        <div class="userMessage">
            <c:out value="${user.username}"/>
        </div>
        <div>
            <span class="userSex"><c:out value="${user.sex}"/></span>
            <span class="userAge"><c:out value="${user.age}" /></span>
        </div>
    </li>
</c:forEach>

</body>
</html>
