<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh_CN">
<head>
    <title>注册界面</title>
    <style>
        span.error{
            color: red;
        }
        div.errors {
            background-color: #ffcccc;
            border: 2px solid red;
        }
        input.error{
            color: red;
        }
    </style>
</head>
<body>
<h1><s:message code="user.welcome" /></h1>
    <sf:form method="post" commandName="user">
        <sf:errors path="*" cssClass="errors" element="div"/>
        <sf:label path="username" cssErrorClass="error">username: </sf:label><sf:input path="username" cssErrorClass="error"/> <sf:errors path="username" cssClass="error"/> <br>
        <sf:label path="sex" cssErrorClass="error">sex:      </sf:label><sf:radiobutton path="sex" value="m" cssErrorClass="error"/>男 <sf:radiobutton path="sex" value="w" cssErrorClass="error" />女 <sf:errors path="sex" cssClass="error" /><br>
        <sf:label path="age" cssErrorClass="error">age:      </sf:label><sf:input path="age" type="number"  min="10" cssErrorClass="error" /> <sf:errors cssClass="error" path="age" /> <br>
        <input type="submit" value="注册">
    </sf:form>
</body>
</html>
