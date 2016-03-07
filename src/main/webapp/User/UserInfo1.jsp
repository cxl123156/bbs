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
						<form id="loginForum" action="" method="post">
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
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							onclick="loginCheck()">登录</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="context" class="container">

		<div id="mainContext" class="col-md-12" style="background: #f1f2f6;">
			<div class="collase">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingOne">
						<h4 class="panel-title">个人信息</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body" style="padding-bottom:0px;">

							<div class="row" id="userInfo">
								<div id="avatar" class=" center-block" style="float:left; ">
									<div class="text-center">
										<div class="thumbnail " style="width:150px; height:150px;">
											<img src="userUpload/${findUserResult.avatar }" width="150px"
												height="150px">

										</div>
										<p>${findUserResult.user_Group.groupName}</p>
									</div>
								</div>
								<div style="float:left; margin-left:10px; width:500px;">
									<div class="col-md-12">
										<div class="row">
											<div class="col-md-6 userInfo">
												<p>用户名：${findUserResult.userName}</p>
											</div>
											<div class="col-md-6 userInfo">
												<p>邮箱：${findUserResult.email }</p>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6 userInfo">
												<p>
													性别：
													<s:if test="#request.findUserResult.sex==0">男</s:if>
													<s:else>女</s:else>
												</p>
											</div>
											<div class="col-md-6 userInfo">
												<p>生日：${findUserResult.birthday}</p>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6 userInfo">
												<p>QQ：${findUserResult.QQ}</p>
											</div>
											<div class="col-md-6 userInfo">
												<p>
													个人主页：<a href="http:${findUserResult.personalWebsite}">${currentUser.personalWebsite}</a>
												</p>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6 userInfo">
												<p>所有精华贴：${findUserResult.totalEssence}</p>
											</div>
											<div class="col-md-6 userInfo">
												<p>总发帖量：${findUserResult.totalPost}</p>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6 userInfo">
												<p>上次登录IP：${findUserResult.lastLoginIp}</p>
											</div>
											<div class="col-md-6 userInfo">
												<p>上次登录时间：${findUserResult.lastLoginTime}</p>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6 userInfo">
												<p>本次登录IP：${findUserResult.loginIp}</p>
											</div>
											<div class="col-md-6 userInfo">
												<p>注册时间：${findUserResult.regTime}</p>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 userInfo">
												<p>个人说明：</p>
											</div>
											<div class="col-md-6 userInfo">
												<p>${findUserResult.selfAssessment}</p>
											</div>
										</div>
										<div class="row">
											<div class="col-md-3 userInfo">
												<p>个人签名：</p>
											</div>
											<div class="col-md-6 userInfo">
												<p>${findUserResult.selfSignature }</p>
											</div>
										</div>
									</div>
								</div>
							</div>
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
