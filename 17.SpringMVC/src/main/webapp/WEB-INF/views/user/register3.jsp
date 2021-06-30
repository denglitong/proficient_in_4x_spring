<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>新增用户</title>
	<style>
      .errorClass {
          color: red
      }
	</style>
</head>
<body>
<form:form modelAttribute="user" action="/mvc/user/handle91">
	<form:errors path="*" cssClass="errorClass" element="div"/>
	<table>
		<tr>
			<td>用户名：</td>
			<td>
				<form:errors path="userName" cssClass="errorClass" element="div"/>
				<input type="text" name="userName">
			</td>
		</tr>
		<tr>
			<td>密码：</td>
			<td>
				<form:errors path="password" cssClass="errorClass" element="div"/>
				<input type="text" name="password">
			</td>
		</tr>
		<tr>
			<td>姓名：</td>
			<td>
				<form:errors path="realName" cssClass="errorClass" element="div"/>
				<input type="text" name="realName">
			</td>
		</tr>
		<tr>
			<td>生日：</td>
			<td>
				<form:errors path="birthDay" cssClass="errorClass" element="div"/>
				<input type="text" name="birthDay">
			</td>
		</tr>
		<tr>
			<td>工资：</td>
			<td>
				<form:errors path="salary" cssClass="errorClass" element="div"/>
				<input type="text" name="salary">
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" name="提交"/></td>
		</tr>
	</table>
</form:form>
</body>
</html>