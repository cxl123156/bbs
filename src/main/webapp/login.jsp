<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'login.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/md5.js"></script>
</head>

<body>

	<form action="loginUserAction.action" method="post" id="loginForm"
		name="loginForm">
		<p>
			用户名：<input id="username" type="text" name="user.userName"> <span
				style="line-height: 120%; display: block; color:#cc0000;"></span>
		</p>
		<p>
			密码：<input id="password" type="password" name="user.password">
			<span style="line-height: 120%; display: block; color:#cc0000;"></span>
		</p>
		<p>
			验证码：<input id="validateCode" type="text" name="validate"> <span
				style="line-height: 120%; display: block; color:#cc0000;"></span> <img
				src="validate/validate.jsp" id="code" onclick="reloadcode()"
				style="cursor: pointer;" alt="看不清楚,换一张">

		</p>
		<span id="error"
			style="line-height: 120%; display: block; color:#cc0000;"></span>
	</form>
	<p>
		<input id="submit" type="button" value="登录" />
	</p>

	<script type="text/javascript">
		function reloadcode() {
			var verify = document.getElementById('code');
			verify.setAttribute('src', 'validate/validate.jsp?it='
					+ Math.random());
		}

		$("#submit").click(function() {
				var password = $("#password").val();
				var temp = hex_md5(password);
				$("#password").val(temp);
				$.ajax({
					type : "post",
					url : "loginValidate.action",
					data : {
						username : $("#username").val(),
						password : $("#password").val()
					},
					dataType : "json",
					success : function(data) {

						if (data) {
							alert(data);
							$("#loginForm").submit();
						} else {
							$('#error').html("用户名或密码错误");
						}
					}
				});
		});

		$("#loginForm").validate({
			rules : {
				"validate" : {
					required : true,
					remote : {
						type : "post",
						url : "codeValidate.action",
						dataType : "json",
						data : {
							validateCode : function() {
								return $("#validateCode").val();
							}
						}
					}
				}
			},
			messages : {
				"validate" : {
					required : ' 请输入验证码',
					remote : '验证码错误'
				}
			},
			errorPlacement : function(error, element) {
				error.appendTo(element.next());
			}
		});
	</script>


</body>




</html>

