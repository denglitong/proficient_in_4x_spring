<#--引入 spring 的 FreeMarker 宏定义文件-->
<#import "spring.ftl" as spring />
<html>
<head>
    <title>UserList</title>
</head>
<body>
<table>
    <#list userList as user>
        <tr>
            <#--使用 Spring 的宏对 URL 进行格式化-->
            <td><a href="<@spring.url '/user/showUser/${user.userName}'/>">${user.userName}</a></td>
            <td>${user.userName}</td>
            <td>${user.birthDay?string("yyyy-MM-dd")}</td>
        </tr>
    </#list>
</table>
</body>
</html>