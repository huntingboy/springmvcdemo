<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示上传文件</title>
</head>
<body>
 <h1>上传文件成功 !!</h1>
 <h2>所有已上传文件：</h2>
<c:forEach items="${fileNames}" var="fileName">
    <li>
        <a href="download?fileName=<c:out value="${fileName}"/>">${fileName}</a>
    </li>
</c:forEach>
</body>
</html>
