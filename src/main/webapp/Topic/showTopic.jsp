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

<title>My JSP 'showTopic.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="content-type" charset=UTF-8>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/showTopic.css">
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
	
</script>
<!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
<style type="text/css">
body {
	padding-top: 60px;
	background: #f1f2f6;
}
</style>

<script type="text/javascript">
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

	function reloadcode() {
		var verify = document.getElementById('code');
		verify.setAttribute('src', 'validate/validate.jsp?it=' + Math.random());
	}

	function loginCheck() {
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
	

</script>

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
				<li><a href="findAllTopic.action">查看所有帖子</a></li>
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



			<div id="postTittle" class="col-md-12">

				<div id="tittle" style="float:left;">
					<h1 style="">${currentTopic.tittle}</h1>
				</div>
				<div id="deleteTopic">
				<s:if test="#session.currentUser.userName==#session.currentTopic.createUser">
				<a href="deleteTopic.action?topicId=${currentTopic.topicId }">删除</a>
				</s:if>				
				</div>
			</div>

			<s:iterator value="#request.allPost" id="post">
				<div id="post">
					<table class="table">
						<tr>
							<td>
								<div id="postText" class="row">
									<div id="author" class="text-center">
										<div id="avatar" align="center">
											<img src="userUpload/<s:property value="#post.key.avatar" />" width="75px"
												height="75px">
										</div>
										<div id="authorName" class="text-center">
										<label ><s:property value="#post.key.createUser" /></label>
										</div>
									</div>
									<div class="" id="text">

										<%
											out.println(request.getAttribute("key.postText"));
										%>
									</div>
									<div>
									<ul>
									<s:iterator value="#post.value" id="review">
									<li>
									<s:property value="#review.reviewText"/>
									</li>
									</s:iterator>
									</ul>
									</div>
									<div id="postInfo">
									    <input id="postid" value="<s:property value="#post.key.postId"/>" style="visibility: hidden;">
									    <s:if test="#post.key.createUser==#session.currentUser.userName">
									    <label><a href="deletePost.action?postId=<s:property value="#post.key.postId"/>">删除</a></label>
									    </s:if>
										<label><a data-toggle="modal" data-target="#<s:property value="#post.key.postId"/>" onclick="getpostId()">回复</a></label><label><s:property value="#post.key.createTime" /></label>

									</div>
								</div>
							</td>
						</tr>
					</table>
					<div class="modal fade" tabindex="-1" id="<s:property value="#post.key.postId"/>" role="dialog"
		aria-hidden="true" aria-labelledby="myModalLabel">
		<div class="modal-dialog " style="width: 680px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title text-primary" id="myModalLabel">回复内容：</h4>
				</div>
				<div class="modal-body">
				<form action="createReview.action?postId=<s:property value="#post.key.postId"/>" id="reviewforum" name="reviewforum" method="post">
				<textarea id="reviewText.text" name="reviewText.text" rows="" cols="" style="width:0px;"></textarea>
				</div>
				<div class="modal-footer">
					<button id="login" type="button" onclick="checkUser('reviewforum')" class="btn btn-primary" >提交</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
				</form>
			</div>
		</div>
	</div>
				</div>
			</s:iterator>
			
			
			
			<div id="pageNum" class="text-center">
				<ul class="pagination pagination-md">
					<li><a href="#" aria-label="Previous"><span
							aria-hidden="true">«</span></a></li>
					<li><a href="showAllPost.action?page=1">1</a></li>
					<li><a href="showAllPost.action?page=2">2</a></li>
					<li><a href="showAllPost.action?page=3">3</a></li>
					<li><a href="showAllPost.action?page=4">4</a></li>
					<li><a href="showAllPost.action?page=5">5</a></li>
					<li><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
				</ul>
			</div>
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

		<div id="postReplayContainer">
			<div
				style="border-bottom:1px solid #fff; margin-left:75px; padding-top:10px;">
				<label>回复帖子：</label>
			</div>
			<div id="postReplay" align="center">
				<form action="createPost.action?topicId=${currentTopic.topicId}" method="post" id="replay">
					<textarea id="postText.Text" name="postText.Text" rows="" cols=""></textarea>
			</div>
			<div id="replayButton">
				<button type="button" onclick="checkUser('replay')" class="btn btn-md btn-primary">发表回复</button>
				</form>
			</div>
		</div>


	</div>

	<div id="bottom" class="container">
		<div id="copyRight">Copyright © 2013-2015 BBS论坛 chenxl.com All
			Rights Reversed.</div>
	</div>
</body>

</html>
