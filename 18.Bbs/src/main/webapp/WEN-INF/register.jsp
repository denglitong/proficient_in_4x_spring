<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户注册</title>
	<script type="javascript">
      function myCheck() {
          if (document.all("user.password").value != document.all("user.again").value) {
              alert("两次输入的密码不正确，请重新输入。")
              return false;
          }
          return true;
      }
	</script>
</head>
<body>
用户注册信息：
<form action="<c:url value="/register.html"/>" method="post" onclick="return myCheck()">
	<c:if test="${!empty errorMsg}">
		<div style="color: red">${errorMsg}</div>
	</c:if>
	<table border="1px" width="60%">
		<tr>
			<td width="20%">用户名</td>
			<td width="80%"><input type="text" name="userName"></td>
		</tr>
		<tr>
			<td width="20%">密码</td>
			<td width="80%"><input type="text" name="password"></td>
		</tr>
		<tr>
			<td width="20%">密码确认</td>
			<td width="80%"><input type="text" name="again"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="保存">
				<input type="reset" value="重置">
			</td>
		</tr>
	</table>
</form>
</body>
</html>