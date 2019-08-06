<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册界面</title>
</head>
<body>
    <h1>Register</h1>
    <form action="" method="post">
        username: <input type="text" name="username" /><br>
        sex:      <label><input type="radio" name="sex" value="m" />男</label>
                  <label><input type="radio" name="sex" value="w" />女</label><br>
        age:      <input type="number" name="age" min="10" /><br>
        <input type="submit" value="Register" />
    </form>
</body>
</html>
