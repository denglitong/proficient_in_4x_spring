<%--
  Author: litong.deng@foxmail.com
  Date: 2021/6/2
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>小春论坛登录</title>
</head>
<body>
<c:if test="${!empty error}">
    <font color="red"><c:out value="${error}"/></font>
</c:if>
<form action="<c:url value="/loginCheck.html"/>" method="post">
    <p>
        <label>用户名：</label>
        <input type="text" name="userName">
    </p>
    <p>
        <label>密　码：</label>
        <input type="password" name="password">
    </p>
    <p>
        <button type="submit">登录</button>
        <button type="reset">重置</button>
    </p>
</form>
</body>
</html>
