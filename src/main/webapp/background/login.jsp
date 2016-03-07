<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
    <meta name="description" content="description of your site" />
    <meta name="author" content="author of the site" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
    <script type="text/javascript" src="js/md5.js"></script>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <script type="text/javascript">
  
	function loginCheck() {
		var password = $("#password").val();
		var temp = hex_md5(password);
		
		$.ajax({
			type : "post",
			url : "adminLoginValidate.action",
			data : {
				username : $("#username").val(),
				password : temp
			},
			dataType : "json",
			success : function(data) {

				if (data) {
					alert(data);
					
					$('#loginForum').submit();
				} else {
					$('#wrong').html("用户名或密码错误");
				}
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

              <h4>BBS<strong>论坛</strong></h4></a>
          </div>
        </div>
      </div>
    </div>
<div class="container">
  <div class="row">
    <div class="span6 offset2">
      <div class="login">
        <form id="loginForum" class="form-horizontal" action="loginAdmin.action" />
          <div class="control-group">
            <div class="controls">
              <h4>用户登录</h4>
            </div>
          </div>
          <div class="control-group">
            <label for="inputUser" class="control-label">用户名： </label>
            <div class="controls">
              <input id="username" type="text" placeholder="用户名" name="admin.username"/>
              <p id="wrong" style="color:#ddd;"></p>
            </div>
          </div>
          <div class="control-group">
            <label for="inputPassword" class="control-label">密码： </label>
            <div class="controls">
              <input id="password" type="password" placeholder="密码" name="admin.password"/>
            </div>
          </div>
        </form>
                  <div class="control-group"> 
            <div class="controls" style="margin-left:180px;"><button type="button" id="submit" onclick="loginCheck()">登录</div>
          </div>
      </div>
    </div>
  </div>
</div>
  </body><script>protocol = window.location.protocol === 'http:' ? 'ws://' : 'wss://'; address = protocol + window.location.host + window.location.pathname + '/ws'; socket = new WebSocket(address);
socket.onmessage = function(msg) { msg.data == 'reload' && window.location.reload() }</script>
</html>
