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
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><
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
<div class="container">
    <div align="center"><h1>欢迎来到BBS论坛系统后台！</h1></div><br>

      <div style="margin:0 auto; width:878px;">
       <div style="border-bottom:1px solid #ddd"><h4>当前系统信息：</h4></div> 
       <div style="margin-top:10px;">
               <div class="panel">
        <div class="top primary"><i class="batch-big b-category"></i>
          <h6>分区数</h6>
        </div>
        <div class="bottom">
          <h2><s:property value="#session.allInfo['categoryNum']"/></h2>
          <h6>个</h6>
        </div>
      </div>
      <div class="panel">
        <div class="top warning"><i class="batch-big b-forum"></i>
          <h6>板块数</h6>
        </div>
        <div class="bottom">
          <h2><s:property value="#session.allInfo['forumNum']"/></h2>
          <h6>个</h6>
        </div>
      </div>
      <div class="panel">
        <div class="top success"><i class="batch-big b-comment"></i>
          <h6>话题数：</h6>
        </div>
        <div class="bottom">
          <h2><s:property value="#session.allInfo['topicNum']"/></h2>
          <h6>个</h6>
        </div>
      </div>
      <div class="panel">
        <div class="top danger"><i class="batch-big b-users"></i>
          <h6>用户数：</h6>
        </div>
        <div class="bottom">
          <h2><s:property value="#session.allInfo['userNum']"/></h2>
          <h6>人</h6>
        </div>
      </div>
      <div class="panel">
        <div class="top"><i class="batch-big b-key"></i>
          <h6>管理员用户</h6>
        </div>
        <div class="bottom">
          <h2><s:property value="#session.allInfo['adminNum']"/></h2>
          <h6>人</h6>
        </div>
      </div>
       </div>

      </div>
 
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
