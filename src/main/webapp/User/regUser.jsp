<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

<title>My JSP 'index.jsp' starting page</title>
<meta name="keywords" content="keyword1,keyword2,keyword3">
<meta name="description" content="this is my page">
<meta name="content-type" charset=UTF-8>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/regUser.css">
<script type="text/javascript" src="js/jquery-2.1.3.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/md5.js"></script>
<script type="text/javascript"
	src="/bbs/js/My97DatePicker/WdatePicker.js"></script>
<!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
<style type="text/css">
body {
	padding-top: 60px;
	background: #f1f2f6;
}
</style>

<script type="text/javascript">
	function reloadcode() {
		var verify = document.getElementById('code');
		verify.setAttribute('src', 'validate/validate.jsp?it=' + Math.random());
	}
	
	
	function loginCheck(){
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
					$('#wrong').html("用户名或密码错误");
				}
			}
		});
	}

	
    function checkCode() {
		var verify = document.getElementById('code');
		verify.setAttribute('src', 'validate/validate.jsp?it=' + Math.random());
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
				error.appendTo("#codeError");
			}
		});
    }

	
	function validate() {

		$("#regForm").validate({
			rules : {
				"user.userName" : {
					required : true,
					remote : {
						type : "post",
						url : "nameAvailableValidate.action",
						dataType : "json",
						data : {
							username : function() {
								alert("aaa");
								return $("#regUsername").val();
							}
						}
					}
				},
				"user.password" : {
					required : true,
					minlength : 6
				},
				"retypepassword" : {
					required : true,
					equalTo : '#regPassword',
					minlength : 6
				},
				"user.email" : {
					required : true,
					email : true
				},
				"user.QQ" : {
					maxlength : 10
				}
			},
			messages : {
				"user.userName" : {
					required : ' 请输入用户名',
					remote : '用户名已存在'
				},
				"user.password" : {
					required : ' 请输入密码',
					minlength : '密码不能小于6位'
				},
				"retypepassword" : {
					required : '请再次输入密码',
					minlength : '密码不能小于6位',
					equalTo : '两次输入的密码不一致'
				},
				"user.email" : {
					required : '请输入邮箱',
					email : '请输入正确格式的电子邮箱'
				},
				"user.QQ" : {
					maxlength : '超出长度范围'
				}
			},
			errorPlacement : function(error, element) {
				error.appendTo(element.next());
			}
		});
		
		if ($("#regForm").valid()){
			var password = $("#regPassword").val();
			var temp = hex_md5(password);
			$("#regPassword").val(temp);
			$("#retypepassword").val(temp);
			$("#regForm").submit();
		}
	}
	
</script>



</head>

<body>
	<div id="header" class="container">
		<nav id="nav" class="nav navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">BBS论坛</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="index.jsp">首页</a></li>
					<li><a href="Forum/createForum.jsp">创建板块</a></li>
				</ul>
				<div class="navbar-right">
            <s:if test="#session.currentUser">
            <p class="text-muted" style="margin-top:15px;">欢迎您 <a href="User/UserInfo.jsp">${currentUser.userName} </a> | <a href="logoutUserAction.action">注销</a></p>
            </s:if>
            <s:else>
                <button type="button" class="btn btn-sm btn-primary navbar-btn" data-toggle="modal" data-target="#loginModal" onclick="checkCode()">登录</button>
                <button type="button" class="btn btn-sm btn-default navbar-btn">注册</button>
            </s:else>
				</div>
			</div>
		</div>
		</nav>
		<div class="modal fade" tabindex="-1" id="loginModal" role="dialog"
			aria-hidden="true" aria-labelledby="myModalLabel">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title text-primary" id="myModalLabel">欢迎回来，很高兴再见到你！</h4>
					</div>
					<div class="modal-body">
						<p id="wrong" class="text-danger"></p>
						<form id="loginForm" action="loginUserAction.action" method="post" name="loginForm">
							<div class="form-group">
								<label for="username" class="control-label">用户名：</label> <input
									type="text" class="form-control" id="username"
									name="user.userName">
							</div>
							<div class="form-group">
								<label for="password" class="control-label">密码：</label> <input
									type="password" class="form-control" id="password"
									name="user.password"> <label id="error"
									class="control-label"></label>
							</div>
							<div class="form-group">
								<label for="validateCode" class="control-label">验证码：</label> <input
									type="text" class="form-control" id="validateCode"
									name="validate"> <img src="" id="code"
									onclick="reloadcode()" style="cursor: pointer;" alt="看不清楚,换一张">
								<label id="codeError" class="control-label text-danger"></label>
							</div>
						
					</div>
					<div class="modal-footer">
						<button id="login" type="button" class="btn btn-primary" onclick="loginCheck()">登录</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="context" class="container" style="background:#fff">
		<div id="tittle" class="center-block">
			<h1 class="bg-info text-info">注册新用户</h1>
		</div>
		<div class="container" style="margin-top:30px;">
			<form class="form-horizontal" id="regForm"
				action="regUserAction.action" method="post">
				<div class="form-group">
					<label for="username" class="col-md-4 col-sm-2 control-label">用户名:</label>
					<div class="col-md-4 col-sm-10">
						<input type="text" class="form-control" name="user.userName"
							id="regUsername" placeholder="请输入您的用户名" required>
							<label class="text-danger"></label>
					</div>
					
				</div>
				<div class="form-group">
					<label for="password" class="col-md-4 col-sm-2 control-label">密码:</label>
					<div class="col-md-4 col-sm-10">
						<input type="password" class="form-control" name="user.password"
							id="regPassword" placeholder="请输入您的密码" required>
					</div>
				</div>
				<div class="form-group">
					<label for="retypepassword" class="col-md-4 col-sm-2 control-label">确认密码:</label>
					<div class="col-md-4 col-sm-10">
						<input type="password" class="form-control" name="retypepassword"
							id="retypepassword" placeholder="请确认您的密码" required>
							<label class="text-danger"></label>
					</div>
				</div>
				<div class="form-group">
					<label for="email" class="col-md-4 col-sm-2 control-label">电子邮箱:</label>
					<div class="col-md-4 col-sm-10">
						<input type="text" class="form-control" name="user.email"
							id="regEmail" placeholder="请输入您的常用邮箱" required>
							<label class="text-danger"></label>
					</div>
				</div>
				<div class="form-group">
					<label for="sex" class="col-md-4 col-sm-2 control-label">性别:</label>
					<div class="col-md-4 col-sm-10">
						<div class="radio" style="float:left;">
							<label> <input type="radio" name="user.sex" id="regSex"
								value="0">男
							</label>
						</div>
						<div class="radio" style="float:left; margin-left:10px;">
							<label> <input type="radio" name="user.sex" id="regSex"
								value="1">女
							</label>
						</div>

					</div>
				</div>
				<div class="form-group">
					<label for="birthday" class="col-md-4 col-sm-2 control-label">生日:</label>
					<div class="col-md-4 col-sm-10">
						<input type="text" class="form-control" name="user.birthday"
							id="regBirthday" placeholder="您的生日" onclick="WdatePicker()">
					</div>
				</div>
				<div class="form-group">
					<label for="personalWebsite"
						class="col-md-4 col-sm-2 control-label">个人网站:</label>
					<div class="col-md-4 col-sm-10">
						<input type="text" class="form-control"
							name="user.personalWebsite" id="personalWebsite"
							placeholder="您的个人网站">
					</div>
				</div>
				<div class="form-group">
					<label for="QQ" class="col-md-4 col-sm-2 control-label">QQ:</label>
					<div class="col-md-4 col-sm-10">
						<input type="text" class="form-control" name="user.QQ" id="QQ"
							placeholder="QQ">
							<label class="text-danger"></label>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-4 col-sm-2 control-label">个人描述:</label>
					<div class="col-md-4 col-sm-10">
						<textarea name="user.selfAssessment" id="selfAssessment"
							class="form-control"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-md-4 col-sm-2 control-label">个性签名:</label>
					<div class="col-md-4 col-sm-10">
						<textarea name="user.selfSignature" id="selfSignature"
							class="form-control"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4"></label>
					<div class="col-md-1 col-sm-10">
						<button type="button" class="btn btn-primary" id="reg" onclick="validate()">注册</button>
					</div>
					<div class="col-md-1 col-sm-10">
						<button type="reset" class="btn btn-default">重置</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div id="bottom" class="container">
		<div id="copyRight">Copyright © 2013-2015 BBS论坛 chenxl.com All
			Rights Reversed.</div>
	</div>
</body>

</html>

