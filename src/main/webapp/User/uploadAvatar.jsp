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

<title>My JSP 'UserInfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/userInfo.css">
<script type="text/javascript" src="js/jquery-2.1.3.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/md5.js"></script>
<script type="text/javascript"
	src="/bbs/js/My97DatePicker/WdatePicker.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
body {
	padding-top: 60px;
	background: #f1f2f6;
}
</style>


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
						<p class="text-muted" style="margin-top:15px;">
							欢迎您 <a href="User/UserInfo.jsp">${currentUser.userName} </a> | <a
								href="logoutUserAction.action">注销</a>
						</p>
					</s:if>
					<s:else>
						<button type="button" class="btn btn-sm btn-primary navbar-btn"
							data-toggle="modal" data-target="#loginModal"
							onclick="checkCode()">登录</button>
						<button type="button" class="btn btn-sm btn-default navbar-btn">注册</button>
					</s:else>
				</div>
			</div>
		</div>
		</nav>
	</div>
	<div id="context" class="container" style="background:#fff;">
		<div id="leftMenu" class="col-md-4 text-center"
			style="padding-left:0px; padding-right:0px;">
			<ul class="nav nav-pills nav-stacked">
				<li role="presentation" style="background:#ddd; border-radius:4px;"><a
					href="User/UserInfo.jsp">个人资料</a></li>
				<li role="presentation" style="background:#ddd; border-radius:4px;"><a
					href="User/editUser.jsp">修改个人资料</a></li>
				<li role="presentation" 
					style="background:#ddd; border-radius:4px;"><a href="User/editPassword.jsp">更改密码</a></li>
				<li role="presentation" class="active" style="background:#ddd; border-radius:4px;"><a
					href="User/uploadAvatar.jsp">上传头像</a></li>
			</ul>
		</div>
		<div id="mainContext" class="col-md-8">
			<div class="collase">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingOne">
						<h4 class="panel-title">上传头像</h4>
					</div>
					<div id="collapseOne" class=" panel-collapse collapse in "
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" style="padding-bottom:0px;">


							<form class="form-horizontal" id="uploadAvatar" enctype="multipart/form-data"
								action="uploadUserAction.action" method="post">
								<div class="form-group">
									<label for="username" class="col-md-4 col-sm-2 control-label">上传头像:</label>
									<div class="col-md-4 col-sm-10">
										<input type="file" class="form-control" name="image"
											id="oldPassword"><label id="oldPwd" class="text-danger"></label>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-4"></label>
									<div class="col-md-1 col-sm-10">
										<button type="submit" class="btn btn-primary" id="reg">保存</button>
									</div>
									<div class="col-md-1 col-sm-10">
										<button type="button" class="btn btn-default">取消</button>
									</div>
								</div>
							</form>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div id="bottom" class="container">
		<div id="copyRight">Copyright © 2013-2015 BBS论坛 chenxl.com All
			Rights Reversed.</div>
	</div>
</body>
</html>
