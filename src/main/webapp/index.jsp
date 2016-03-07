<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>index.html</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="keywords" content="keyword1,keyword2,keyword3">
<meta name="description" content="this is my page">
<meta name="content-type" charset=UTF-8>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="js/jquery-2.1.3.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/md5.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
<style type="text/css">
body {
	padding-top: 60px;
	background: #f1f2f6;
}
</style>

<script type="text/javascript">

   function onload(){
	   $.ajax({
		  url : "getForumAndCategory.action", 
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
	<header class="container">
		<nav id="nav" class="navbar navbar-inverse navbar-fixed-top">
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
						<s:if test="#session.currentUser.user_Group.groupName=='管理员用户'">
						<li><a href="getForumAndCategory.action">查看所有分区</a></li>
						<li><a href="findAllTopic.action">查看所有帖子</a></li>
						<li><a href="Category/creatCategory.jsp">创建分区</a></li>
						<li><a href="Forum/createForum.jsp">创建板块</a></li>
						<li><a href="findInPageTopic.action">分页查看帖子</a></li>
						<li><a href="getNewAndHotTopic.action">分页查看帖子</a></li>
						</s:if>
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
							<a class="btn btn-default btn-sm" href="background">后台</a>
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
		<div id="contextLeft">

			<div id="carousel-example-captions" class="carousel slide"
				data-ride="carousel">
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-captions" data-slide-to="0"
						class="active"></li>
					<li data-target="#carousel-example-captions" data-slide-to="1"
						class=""></li>
					<li data-target="#carousel-example-captions" data-slide-to="2"
						class=""></li>
				</ol>
				<div class="carousel-inner" role="listbox">

				<s:iterator value="#application.carousel" id="carousel" status='S'>
				  <s:if test="#S.first">
					<div class="item active">
					<a href="showAllPost.action?topicId=<s:property value="#carousel.topicId"/>">
						<img id="carousel" data-src="holder.js/900x500/auto/#777:#777"
							alt="900x500" src="userUpload/<s:property value="#application.carousel[0].cover"/>"
							data-holder-rendered="true">
							</a>
						<div class="carousel-caption">
							<a href="showAllPost.action?topicId=<s:property value="#application.carousel[0].topicId"/>"><h4><s:property value="#application.carousel[0].tittle"/></h4></a>
						</div>
					</div>
					</s:if><s:else>
					<div class="item">
					<a href="showAllPost.action?topicId=<s:property value="#carousel.topicId"/>">
						<img id="carousel" data-src="holder.js/900x500/auto/#777:#777"
							alt="900x500" src="userUpload/<s:property value="#carousel.cover"/>"
							data-holder-rendered="true">
							</a>
						<div class="carousel-caption">
							<a href="showAllPost.action?topicId=<s:property value="#carousel.topicId"/>"><h4><s:property value="#carousel.tittle"/> </h4></a>
						</div>
					</div>
					</s:else>
				</s:iterator>

					<div class="item">
						<img id="carousel" data-src="holder.js/900x500/auto/#555:#5555"
							alt="900x500" src="images/WP_20141109_004.jpg"
							data-holder-rendered="true">
						<div class="carousel-caption">
							<h3>Third slide label</h3>
							<p>Praesent commodo cursus magna, vel scelerisque nisl
								consectetur.</p>
						</div>
					</div>
				</div>
				<a class="left carousel-control" href="#carousel-example-captions"
					role="button" data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#carousel-example-captions"
					role="button" data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>

		</div>
		<div id="contextRight">
			<div role="tabpanel">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#home"
						aria-controls="home" role="tab" data-toggle="tab">最新帖子</a></li>
					<li role="presentation"><a href="#profile"
						aria-controls="profile" role="tab" data-toggle="tab">热门帖子</a></li>
				</ul>
				<div id="myTabContent" class="tab-content">
					<div role="tabpanel" class="tab-pane fade active in" id="home"
						aria-labelledby="home-tab">
						<ol>
						<s:iterator value="#application.NewTopic" id="NewTopic">
							<li><a href="showAllPost.action?topicId=<s:property value="#NewTopic.topicId"/>"> <s:property value="#NewTopic.tittle"/></a>
							    <a style="float:right; margin-right:10px;" href="findByNameUserAction.action?userName=<s:property value="#NewTopic.createUser"/>"> <s:property value="#NewTopic.createUser"/></a>
							 </li>
						</s:iterator>
						</ol>
					</div>
					<div role="tabpanel" class="tab-pane fade" id="profile"
						aria-labelledby="profile-tab">
						<ol>
						<s:iterator value="#application.HotTopic" id="HotTopic">
							<li><a href="showAllPost.action?topicId=<s:property value="#HotTopic.topicId"/>"> <s:property value="#HotTopic.tittle"/></a>
							<a style="float:right; margin-right:10px;" href="findByNameUserAction.action?userName=<s:property value="#HotTopic.createUser"/>"> <s:property value="#HotTopic.createUser"/></a>
							 </li>
						</s:iterator>
						</ol>
					</div>
				</div>
			</div>
		</div>


		<div id="rightInfo">
			<div>
				<s:iterator value="#application.cateAndForum" id="category">
				<div>
				<div class="row">
					<div class="collase">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingTwo">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#<s:property value="#category.key.categoryId"/>" aria-expanded="true"
										aria-controls="<s:property value="#category.key.categoryId"/>"> <s:property value="#category.key.tittle"/> </a>
								</h4>
							</div>
							<div id="<s:property value="#category.key.categoryId"/>" class="panel-collapse collapse in"
								role="tabpanel" aria-labelledby="headingTwo">
								<div class="panel-body" style="padding-bottom:0px;">
									
									<div class="row">
									<s:iterator value="#category.value" id="forum">
										<div id="forumList1" class="col-md-6 text-center">
											<a href="showTopicForum.action?forum.forumId=<s:property value="#forum.forumId"/>"> <s:property value="#forum.tittle"/></a>
										</div>

									</s:iterator>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
				
				
				</s:iterator>
				
			</div>
		</div>
		<div id="allPost" class="table-responsive">
			<table class="table table-hover">
			<s:iterator value="#application.AllTopic" id="allTopic">
				<tr style="border-bottom: 1px solid #ddd;">
					<td>
						<div style="float:left; width:48px; height:48px; background:#000066; margin-right:10px;">
						<img alt="" src="userUpload/<s:property value="#allTopic.userAvatar"/> " width="48px" height="48px" >
						</div>
						<div style="float:left;"><a href="showAllPost.action?topicId=<s:property value="#allTopic.topicId"/>"><s:property value="#allTopic.tittle"/></a></div>
						<div style="float:right; margin-right:20px; margin-top:10px;"> <a href="findByNameUserAction.action?userName=<s:property value="#allTopic.createUser"/>"><s:property value="#allTopic.createUser"/></a>  </div>
						<br><div style="float:left; margin-top:5px;">发表于：<label><s:property value="#allTopic.createTime"/></label>  </div>
						<div style="float:left; margin-top:5px; margin-left:10px;"> <s:property value="#allTopic.forumTittle"/> </div>
						<div style="float:left; margin-top:5px; margin-left:10px;">浏览次数：<label><s:property value="#allTopic.viewCount"/></label></div>
					</td>
				</tr>
			</s:iterator>
			</table>
			
					<div id="pageNum" class="text-center">
      <ul class="pagination pagination-md">
        <li><a href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
        <li><a href="findInPageTopic.action?page=1">1</a></li>
        <li><a href="findInPageTopic.action?page=2">2</a></li>
        <li><a href="findInPageTopic.action?page=3">3</a></li>
        <li><a href="findInPageTopic.action?page=4">4</a></li>
        <li><a href="findInPageTopic.action?page=5">5</a></li>
        <li><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
      </ul>
			</div>	
			
		</div>
		


	</div>
	<div id="bottom" class="container">
		<div id="copyRight">Copyright © 2013-2015 BBS论坛 chenxl.com All
			Rights Reversed.</div>
	</div>
</body>
</html>
