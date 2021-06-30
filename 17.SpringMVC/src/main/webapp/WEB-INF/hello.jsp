<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<title>websocket测试</title>
	<script>
      const url = 'ws://' + window.location.host + '<%=request.getContextPath()%>/hello';
      const socket = new WebSocket(url); // 打开 websocket

      socket.onopen = function () {
          console.log('开启 websocket 连接！');
          sayHello();
      }

      socket.onmessage = function (event) {
          console.log('接收消息: ' + event.data);
          setTimeout(function () {
              sayHello()
          }, 2000)
      }

      socket.onclose = function () {
          console.log('关闭 websocket 连接！');
          socket.close();
      }

      function sayHello() {
          console.log('发送消息: hello world!');
          socket.send('hello world!');
      }
	</script>
</head>
<body>
hello world!
</body>
</html>