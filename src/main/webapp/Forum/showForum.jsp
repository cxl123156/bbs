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

<title>My JSP 'showForum.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/showForum.css">
<link rel="stylesheet" type="text/css" href="js/kindeditor/themes/default/default.css">
<script type="text/javascript" src="js/jquery-2.1.3.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/md5.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script charset="utf-8" src="js/kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="js/kindeditor/lang/zh_CN.js"></script>
	<script>

		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="post.text"]', {
				width : '567px',
				minWidth : '346px',
				items : [
							'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
							'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
							'insertunorderedlist', '|', 'emoticons', 'image', 'link'],
				allowFileManager : true,
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

		<div id="allPost" class="table-responsive">
			<div id="forumHeader">

				<ol class="breadcrumb text-center" id="headCrumb">
					当前位置：
					<s:iterator value="#session.currentForum" id="forumMap">
						<li><a href="index.jsp">首页</a></li>
						<li><a href="index.jsp"><s:property value="#forumMap.key.tittle"></s:property></a></li>
						<li class="active"><s:property value="#forumMap.value.tittle"></s:property></li>
					</s:iterator>
				</ol>
				<div id="postTopic">
					<a  class="btn btn-primary" href="Topic/createTopic.jsp">发表帖子</a>
				</div>
			</div>


			<table class="table table-hover">
			<s:iterator value="#request.currentForumTopic" id="topic">
				<tr>
					<td>
						<div style="float:left; width:48px; height:48px; background:#000066; margin-right:10px;">
							<img alt="" src="userUpload/<s:property value="#topic.userAvatar"/>"  width="48px" height="48p">
						</div>
						<div style="float:left;margin-left:10px; margin-top:10px;">
						<a href="showAllPost.action?topicId=<s:property value="#topic.topicId"/>"><s:property value="#topic.topicTittle"/></a>
						</div>
						<div style="float:right; margin-top:10px; margin-right:10px;"> <s:property value="#topic.topicUser"/> </div>
					</td>
				</tr>
			</s:iterator>
			</table>
		</div>

		<div id="rightInfo">
			<div>
				<div>
					<div class="row">
						<div class="collase">
							<div class="panel panel-default">
								<div class="panel-heading" role="tab" id="headingTwo">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion" href=""
											aria-expanded="true" aria-controls=""> 板块信息： </a>
									</h4>
								</div>
								<div id="" class="panel-collapse collapse in" role="tabpanel"
									aria-labelledby="headingTwo">
									<div class="panel-body" style="padding-bottom:0px;">

										<div class="row">
											<div class="col-md-12 text-center">
												<p>版规：</p>
												<p>禁止发言，禁止浏览，禁止点击，禁止置顶，禁止加精，禁止喧哗</p>
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

</body>
</html>
