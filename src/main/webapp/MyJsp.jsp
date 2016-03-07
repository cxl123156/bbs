<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    

	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">

    <script type="text/javascript" src="js/jquery-2.1.3.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
    body { padding-top: 60px; background: #f1f2f6;}
    </style>

  </head>
  
  <body>
     <div class="container">      
        <nav id="nav" class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">BBS论坛</a>
                </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
            <li><a href="http://www.w3cstudy.com">首页</a></li>
            <li><a href="#">查看所有分区</a></li>
            <li><a href="#">查看所有帖子</a></li>
            <li><a href="#">创建分区</a></li>
            <li><a href="#">创建板块</a></li>
            <li><a href="#">发表帖子</a></li>
            </ul>
            <div class="navbar-right">
                <button type="button" class="btn btn-sm btn-primary navbar-btn" data-toggle="modal" data-target="#loginModal">登录</button>
                <button type="button" class="btn btn-sm btn-default navbar-btn">注册</button>
            </div>  
        </div>
                </div>
        </nav> 
        <div class="modal fade" tabindex="-1"  id="loginModal" role="dialog" aria-hidden="true" aria-labelledby="myModalLabel">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title text-primary" id="myModalLabel">欢迎回来，很高兴再见到你！</h4>
                    </div>
                    <div class="modal-body">
                        <div class="alert alert-waring alert-dismissible fade in" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <p class="text-danger">用户名或密码错误</p>
                            <p class="text-danger">验证码错误</p>
                        </div>
                        <form action="loginUserAction.action" method="post">
                            <div class="form-group">
                                <label for="username" class="control-label">用户名：</label>
                                <input type="text" class="form-control" id="username" name="user.userName"> 
                            </div>
                            <div class="form-group has-error">
                                <label for="password" class="control-label">密码：</label>
                                <input type="password" class="form-control" id="password" name="user.password">
                                <label class="control-label" for="inputError1">用户名或密码错误</label>
                            </div>
                            <div class="form-group has-error">
                                <label for="validateCode" class="control-label">验证码：</label>
                                <input type="text" class="form-control" id="validateCode" name="validate">
                                <img src="validate/validate.jsp" id="code" onclick="reloadcode()" style="cursor: pointer;" alt="看不清楚,换一张">
                                <label class="control-label" for="inputError1">验证码错误</label>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary">登录</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div> 
    </div>
  </body>
</html>
