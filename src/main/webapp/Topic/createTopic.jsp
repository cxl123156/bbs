<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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

<title>My JSP 'createTopic.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/showForum.css">
<link rel="stylesheet" type="text/css"
	href="js/kindeditor/themes/default/default.css">
<script type="text/javascript" src="js/jquery-2.1.3.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/md5.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script charset="utf-8" src="js/kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="js/kindeditor/lang/zh_CN.js"></script>
<script>
	KindEditor.ready(function(K) {
		var editor1 = K.create('textarea["#postText.Text"]', {
			height : '300px',
			minWidth : '346px',
			allowFileManager : true,
			items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
						'bold', 'italic', 'underline', 'removeformat', '|',
						'justifyleft', 'justifycenter', 'justifyright',
						'insertorderedlist', 'insertunorderedlist', '|',
						'emoticons', 'image', 'link' ],
			afterBlur: function () { this.sync(); },
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['topicForum'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['topicForum'].submit();
				});
			}
		});
	});
	
	function checkUser(id) {
		alert(id);
		$.ajax({
			type : "post",
			url : "checkLoginValidate.action",
			success : function(data){
				document.getElementById(id).submit();
			},
			error: function(data){
				alert("请先登录！");
			}
		
		});
	}
	
</script>
<style type="text/css">
body {
	padding-top: 60px;
	background: #f1f2f6;
}
</style>
</head>

<body>
	<header class="container"> <nav id="nav"
		class="navbar navbar-inverse navbar-fixed-top">
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
				<li><a href="getForumAndCategory.action">查看所有分区</a></li>
				<li><a href="#">查看所有帖子</a></li>
				<li><a href="Category/creatCategory.jsp">创建分区</a></li>
				<li><a href="Forum/createForum.jsp">创建板块</a></li>
			</ul>
			<div class="navbar-right center-block">
				<s:if test="#session.currentUser">
					<p class="text-muted" style="margin-top:15px;">
						欢迎您 <a href="User/UserInfo.jsp">${currentUser.userName} </a> | <a
							href="logoutUserAction.action">注销</a>
					</p>
				</s:if>
				<s:else>
					<button type="button" class="btn btn-sm btn-primary navbar-btn"
						data-toggle="modal" data-target="#loginModal"
						onclick="checkCode()">登录</button>
					<a class="btn btn-default btn-sm" href="User/regUser.jsp">注册</a>
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
					<form id="loginForm" action="loginUserAction.action" method="post"
						name="loginForm">
						<div class="form-group">
							<label for="username" class="control-label">用户名：</label> <input
								type="text" class="form-control" id="username"
								name="user.userName"
								oninvalid="this.setCustomValidity('用户名不能为空');"
								oninput="setCustomValidity('');" required>
						</div>
						<div class="form-group">
							<label for="password" class="control-label">密码：</label> <input
								type="password" class="form-control" id="password"
								name="user.password"
								oninvalid="this.setCustomValidity('密码不能为空');"
								oninput="setCustomValidity('');" required> <label
								id="error" class="control-label"></label>
						</div>
						<div class="form-group">
							<label for="validateCode" class="control-label">验证码：</label> <input
								type="text" class="form-control" id="validateCode"
								name="validate" oninvalid="this.setCustomValidity('请输入验证码');"
								oninput="setCustomValidity('');" required> <img src=""
								id="code" onclick="reloadcode()" style="cursor: pointer;"
								alt="看不清楚,换一张"> <label id="codeError"
								class="control-label text-danger"></label>
						</div>
				</div>
				<div class="modal-footer">
					<button id="login" type="button" class="btn btn-primary"
						onclick="loginCheck()">登录</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
				</form>

			</div>
		</div>
	</div>
	</header>

	<div id="context" class="container">

		<div id="tittle" class="center-block">
			
		</div>
		
		<div class="container" style="margin-top:30px;">
		
			<form class="form-horizontal" id="topicForum"
				action="createTopic.action" method="post">
				<s:iterator value="#session.currentForum" id="forumMap">
				<div class="form-group ">
				
					<h4><label for="tittle" >帖子标题：</label> </h4>
					<input type="text" class="form col-md-6" id="tittle" name="topic.tittle" required>
					<label class="col-md-3">发表到：
					
						<s:property value="#forumMap.key.tittle"></s:property> |
						<s:property value="#forumMap.value.tittle"></s:property>
					
					</label>
					
				</div>
				</s:iterator>
				<div class="form-group ">
					<h4><label for="text" class="control-label">帖子内容：</label></h4>
					<textarea class="form-control col-md-12" id="postText.Text" name="postText.Text" rows="" cols=""></textarea>
				</div>
				
			<div class="form-group">
			<label class="col-md-4"></label>
			<div class="col-md-1 col-sm-10">
				<button type="button" class="btn btn-primary" id="reg"
					onclick="checkUser('topicForum')">发表</button>
			</div>
			<div class="col-md-1 col-sm-10">
				<button type="reset" class="btn btn-default">重置</button>
			</div>
		</div>	
		</form>
		
		</div>

	</div>
</body>
</html>
