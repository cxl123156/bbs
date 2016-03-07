<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   <base href="<%=basePath%>">    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   <meta charset="utf8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
    <meta name="description" content="description of your site" />
    <meta name="author" content="author of the site" />
    <title>IndustryApp Template</title>
    <link rel="stylesheet" href="background/css/bootstrap.css" />
    <link rel="stylesheet" href="background/css/bootstrap-responsive.css" />
    <link rel="stylesheet" href="background/css/styles.css" />
    <link rel="stylesheet" href="background/css/toastr.css" />
    <link rel="stylesheet" href="background/css/fullcalendar.css" />
        <script type="text/javascript" src="js/jquery-2.1.3.js"></script>
    <script src="background/js/bootstrap.js"></script>
    <script src="background/js/jquery.knob.js"></script>
    <script src="background/js/jquery.sparkline.min.js"></script>
    <script src="background/js/toastr.js"></script>
    <script src="background/js/jquery.tablesorter.min.js"></script>
    <script src="background/js/jquery.peity.min.js"></script>
    <script src="background/js/fullcalendar.min.js"></script>
    <script src="background/js/gcal.js"></script>
    <script src="background/js/setup.js"></script>
    <script charset="utf-8" src="js/kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="js/kindeditor/lang/zh_CN.js"></script>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript">
KindEditor.ready(function(K) {
	var editor1 = K.create('textarea[name="postText.text"]', {
		height : '300px',
		minWidth : '346px',
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


function getForum(){
	alert($("#category").val());
	$.ajax({
		type : "post",
		url : "ajaxGetForum.action",
		data : {
			categoryId : $("#category").val(),
		},
		dataType: 'json',
		success: function(data){
			$("#forum option").remove();
		    	jQuery.each(data,function(key,value){
		    		alert(value);
		    		$("#forum").append("<option value="+key+">"+value+"</option>");
		    	});
		 },
	    error : function(data){
	    	alert(wrong);
	    }
	});
	
}

</script>


  </head>
  
  <body>
       <div id="in-nav">
      <div class="container">
        <div class="row">
          <div class="span12">
            <ul class="pull-right">
            <s:if test="#session.currentAdmin">
            <li><a href="login.html">${currentAdmin.username}</a></li>
            </s:if>
            <s:else>
                  <li><a href="background/login.jsp">登录</a></li>
            </s:else>
            </ul><a id="logo" href="background/index.jsp">
              <h4>BBS<strong>论坛</strong></h4></a>
          </div>
        </div>
      </div>
    </div>
    <div id="in-sub-nav">
      <div class="container">
        <div class="row">
          <div class="span12">
            <ul>
              <li><a href="index.html" class="active"><i class="batch home"></i><br />首页</a></li>
              <li><a href="adminFindAllCategory.action"><i class="batch category"></i><br />分区管理</a></li>
              <li><a href="getCategoryAndForum.action"><i class="batch forum"></i><br />板块管理</a></li>
              <li><a href="getForumAndCategoryIndex.action?admin1=1"><i class="batch comment"></i><br />帖子管理</a></li>
              <li><a href="getAllUserAction.action"><i class="batch users"></i><br />用户管理</a></li>
              <li><a href="findAllGroup.action"><i class="batch group"></i><br />用户组管理</a></li>
              <li><a href="getHighLightTopic.action"><i class="batch carouselIco"></i><br />轮播内容</a></li>
              <li><a href="getAllAdmin.action"><i class="batch key"></i><br />管理员用户</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    
    
        <div class="page">
      <div class="page-container">
<div class="container" style="margin-top:20px;">
		<div class="row" style="margin:0 auto;">
			<form class="form-horizontal" id="regForm" action="adminCreateTopic.action" method="post">
				

				<div class="span3">
					<h4 class="span12"><label>帖子标题：</label> </h4>
					<label class="span3"><input type="text"  id="tittle" name="topic.tittle" required=""></label>
				</div>
				<div class="span4">
					<h4 class=""><label>发表到:</label></h4>
					<select class="span2" name="category" id="category" onchange="getForum()">	
					<option>请选择分区</option>
					<s:iterator value="#application.cateAndForum" id="category">
					<option value="<s:property value="#category.key.categoryId"/>"><s:property value="#category.key.tittle"/> </option>
					</s:iterator>
					</select>	
					<select class="span2" name="forum.forumId" id="forum">
					<option>请选择板块</option>
					</select>	
				</div>

				<div class="span12" >	
				<div class="form-group span12">
					<h4><label for="text" class="">帖子内容：</label></h4>
					<textarea class="span10" rows="" cols="" name="postText.text"></textarea>
				</div>
				</div>

			<div class="form-group span12">
			<br>
			<div class="span1">
				<button type="submit" class="btn btn-primary" id="reg">发表</button>
			</div>
			<div class="span1">
				<button type="reset" class="btn btn-default">重置</button>
			</div>
		</div>	
		</form>
		</div>
		</div>
</div>
      </div>
    
        <footer>
      <div class="container">
        <div class="row">
          <div class="span12">
            <p class="pull-right">Admin Theme by Nathan Speller</p>
            <p>&copy; Copyright 2013 FreelanceLeague</p>
          </div>
        </div>
      </div>
    </footer>
  </body>
</html>
