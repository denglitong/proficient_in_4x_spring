<%--
  Author: litong.deng@foxmail.com
  Date: 2021/6/27
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<title>新增用户</title>
	<style>
      .error {
          color: red;
      }
	</style>
</head>
<body>
<form method="post" action="<c:url value="/user/create"/>">
	<form:errors path="*"/>
	<table>
		<tr>
			<td>用户名：</td>
			<td>
				<form:errors path="userName" cssClass="error"/>
				<input type="text" name="userName">
			</td>
		</tr>
		<tr>
			<td>密码：</td>
			<td>
				<form:errors path="password" cssClass="error"/>
				<input type="password" name="password">
			</td>
		</tr>
		<tr>
			<td>姓名：</td>
			<td><input type="text" name="realName"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" name="提交"></td>
		</tr>
	</table>
</form>
</body>
</html>
