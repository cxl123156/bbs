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
    <script type="text/javascript" src="js/jquery.validate.js"></script>
    <script type="text/javascript" src="js/md5.js"></script>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript">
function edit(id,username){
	alert(username);
	document.getElementById('username').value=username;
	document.editUserGroup.action="updateAdmin.action?adminId="+id; 
}

function validate() {

	$("#regForm").validate({
		rules : {
			"admin.username" : {
				required : true,
				remote : {
					type : "post",
					url : "adminNameAvailableValidate.action",
					dataType : "json",
					data : {
						username : function() {
							return $("#regUsername").val();
						}
					}
				}
			},
			"admin.password" : {
				required : true,
				minlength : 6
			},
			"retypepassword" : {
				required : true,
				equalTo : '#regPassword',
				minlength : 6
			}
		},
		messages : {
			"admin.username" : {
				required : ' 请输入用户名',
				remote : '用户名已存在'
			},
			"admin.password" : {
				required : ' 请输入密码',
				minlength : '密码不能小于6位'
			},
			"retypepassword" : {
				required : '请再次输入密码',
				minlength : '密码不能小于6位',
				equalTo : '两次输入的密码不一致'
			}
		},
		errorPlacement : function(error, element) {
			error.appendTo(element.next());
		}
	});
	
	if ($("#regForm").valid()){
		var password = $("#regPassword").val();
		var temp = hex_md5(password);
		$("#regPassword").val(temp);
		$("#retypepassword").val(temp);
		$("#regForm").submit();
	}
}

function validatePwd(){
	$("#editPwdForm").validate({
		rules : {
		"password": {
				required : true,
				minlength : 6
			},
		"confirmPwd" : {
				required : true,
				equalTo : '#editPassword',
				minlength : 6
			}
		},
		messages : {
			"password" : {
				required : ' 请输入密码',
				minlength : '密码不能小于6位'
			},
			"confirmPwd" : {
				required : '请再次输入密码',
				minlength : '密码不能小于6位',
				equalTo : '两次输入的密码不一致'
			}
		},
		errorPlacement : function(error, element) {
			error.appendTo(element.next());
		}
	});
	
	if ($("#editPwdForm").valid()){
		var password = $("#editPassword").val();
		var temp = hex_md5(password);
		$("#editPassword").val(temp);
		$("#confirmPwd").val(temp);
		$("#editPwdForm").submit();
	}
}

function editPwd(id){
	document.editPwdForm.action="editPasswordAdmin.action?adminId="+id; 
}

</script>

  </head>
  
  <body>
        <div id="in-nav">
      <div class="container">
        <div class="row">
          <div class="span12">
<a id="logo" href="background/index.jsp">
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
              <li><a href="getInfoAdmin.action" ><i class="batch home"></i><br />首页</a></li>
              <li><a href="adminFindAllCategory.action"><i class="batch category"></i><br />分区管理</a></li>
              <li><a href="getCategoryAndForum.action"><i class="batch forum"></i><br />板块管理</a></li>
              <li><a href="getForumAndCategoryIndex.action?admin1=1"><i class="batch comment"></i><br />帖子管理</a></li>
              <li><a href="getAllUserAction.action"  ><i class="batch users"></i><br />用户管理</a></li>
              <li><a href="findAllGroup.action"><i class="batch group"></i><br />用户组管理</a></li>
              <li><a href="getHighLightTopic.action"><i class="batch carouselIco"></i><br />轮播内容</a></li>
              <li><a href="getAllAdmin.action" class="active"><i class="batch key"></i><br />管理员用户</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="page">
      <div class="page-container">
      <div class="container">
      <div class="row">
    <div class="span12"><a href="#newUserModal" data-toggle="modal" class="btn pull-right">创建管理员</a>
      <h4 class="header">所有管理员</h4>
      <table class="table table-striped sortable">
        <thead>
          <tr>
            <th class="header">管理员ID</th>
            <th class="header">用户名</th>
            <th class="header">操作</th>
          </tr>
        </thead>
        <tbody>
        <s:iterator value="#request.allAdmin" id="admin">
          <tr>
            <td> <s:property value="#admin.adminId"/> </td>
            <td><s:property value="#admin.username"/></td>
            <td>
              <a href="#editAdmin" data-toggle="modal" onclick="edit(<s:property value="#admin.adminId"/>,'<s:property value="#admin.username"/>')">
              <img src="background/img/compose.png"/></a> &nbsp
              <a href="#editAdminPwd" data-toggle="modal" onclick="editPwd(<s:property value="#admin.adminId"/>)">
              <img src="background/img/key.png"/></a> &nbsp
              <a href="deleteAdmin.action?adminId=<s:property value="#admin.adminId"/>" ><img src="background/img/delete.png"></a>
            </td>
          </tr>
          </s:iterator>
        </tbody>
      </table>
      <div class="pagination pagination-centered">
        <ul>
          <li class="disabled"><a href="#">«</a></li>
          <li class="active"><a href="#">1</a></li>
          <li><a href="#">2</a></li>
          <li><a href="#">3</a></li>
          <li><a href="#">4</a></li>
          <li><a href="#">5</a></li>
          <li><a href="#">»</a></li>
        </ul>
      </div>
    </div>
  </div>
    </div>  
</div>
      </div>
      
<div id="newUserModal" class="modal hide fade">
  <div class="modal-header">
    <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
    <h3>创建新管理员</h3>
  </div>
  <div class="modal-body">
    <form id="regForm" class="form-horizontal" action="createAdmin.action" method="post"/>
      <div class="control-group">
        <label for="inputEmail" class="control-label">用户名： </label>
        <div class="controls">
          <input id="regUsername" name="admin.username" type="text" placeholder="输入用户名" />
          <label class="text-danger"></label>
        </div>
      </div>
      <div class="control-group">
        <label for="inputCurrentPassword" class="control-label">密码 ：</label>
        <div class="controls">
          <input id="regPassword" type="password" name="admin.password"  placeholder="输入密码" />
          <label class="text-danger"></label>
        </div>
      </div>
      <div class="control-group">
        <label for="inputCurrentPassword" class="control-label">确认密码 ：</label>
        <div class="controls">
          <input id="retypepassword" name="retypepassword" type="password" placeholder="再次输入密码" />
          <label class="text-danger"></label>
        </div>
      </div>
    </form>
  </div>
  <div class="modal-footer">
  <button type="button" data-dismiss="modal" class="btn">取消</button>
  <button type="button" class="btn btn-primary" onclick="validate()">创建</button>
  </div>
</div>
      
      <div id="editAdmin" class="modal hide fade">
  <div class="modal-header">
    <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
    <h3>修改管理员</h3>
  </div>
  <div class="modal-body">
    <form id="editUserGroup" name="editUserGroup" class="form-horizontal" method="post"/>
      <div class="control-group">
        <label for="inputEmail" class="control-label">用户名： </label>
        <div class="controls">
        <input id="username" name="admin.username"/>
        </div>
      </div>
    
  </div>
  <div class="modal-footer">
  <button type="button" data-dismiss="modal" class="btn">取消</button>
  <button type="submit" class="btn btn-primary">保存</button>
  </form>
  </div>
</div>
      
      <div id="editAdminPwd" class="modal hide fade">
  <div class="modal-header">
    <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
    <h3>修改管理员密码</h3>
  </div>
  <div class="modal-body">
    <form id="editPwdForm" name="editPwdForm" class="form-horizontal" method="post"/>
      <div class="control-group">
        <label for="inputEmail" class="control-label">新密码： </label>
        <div class="controls">
        <input id="editPassword" name="password" type="password"/>
         <label class="text-danger"></label>
        </div>
      </div>
      <div class="control-group">
        <label for="inputEmail" class="control-label" >确认密码： </label>
        <div class="controls">
        <input id="confirmPwd" name="confirmPwd" type="password"/>
         <label class="text-danger"></label>
        </div>
      </div>
    
  </div>
  <div class="modal-footer">
  </form>
    <button type="button" data-dismiss="modal" class="btn">取消</button>
  <button type="button" class="btn btn-primary" onclick="validatePwd()">保存</button>
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
