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
function edit(id){
	alert(groupName);
	var ss = document.getElementById('select');
	for(var i=0; i<ss.options.length; i++){
		if(ss.options[i].value==groupId){
			ss.options[i].selected="selected";
		}
	}
	document.editUserGroup.action="editGroupUserAction.action?userId="+id; 
}

function validate() {

	$("#regForm").validate({
		rules : {
			"user.userName" : {
				required : true,
				remote : {
					type : "post",
					url : "nameAvailableValidate.action",
					dataType : "json",
					data : {
						username : function() {
							return $("#regUsername").val();
						}
					}
				}
			},
			"user.password" : {
				required : true,
				minlength : 6
			},
			"retypepassword" : {
				required : true,
				equalTo : '#regPassword',
				minlength : 6
			},
			"user.email" : {
				required : true,
				email : true
			},
			"user.QQ" : {
				maxlength : 10
			}
		},
		messages : {
			"user.userName" : {
				required : ' 请输入用户名',
				remote : '用户名已存在'
			},
			"user.password" : {
				required : ' 请输入密码',
				minlength : '密码不能小于6位'
			},
			"retypepassword" : {
				required : '请再次输入密码',
				minlength : '密码不能小于6位',
				equalTo : '两次输入的密码不一致'
			},
			"user.email" : {
				required : '请输入邮箱',
				email : '请输入正确格式的电子邮箱'
			},
			"user.QQ" : {
				maxlength : '超出长度范围'
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
              <li><a href="getAllUserAction.action"  class="active"><i class="batch users"></i><br />用户管理</a></li>
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
      <div class="row">
    <div class="span12"><a href="#newUserModal" data-toggle="modal" class="btn pull-right">创建用户</a>
      <h4 class="header">所有用户</h4>
      <table class="table table-striped sortable">
        <thead>
          <tr>
            <th class="header">用户ID</th>
            <th class="header">用户名</th>
            <th class="header">邮箱</th>
            <th class="header">性别</th>
            <th class="header">创建日期</th>
            <th class="header">发帖数</th>
            <th class="header">用户组</th>
            <th class="header">操作</th>
          </tr>
        </thead>
        <tbody>
        <s:iterator value="#request.allUsers" id="allUser">
          <tr>
            <td> <s:property value="#allUser.userId"/> </td>
            <td><s:property value="#allUser.userName"/></td>
            <td><s:property value="#allUser.email"/></td>
            <td>
            <s:if test="#allUser.sex==0"> 男</s:if>
           <s:else>女</s:else>
            </td>
            <td><s:property value="#allUser.regTime"/></td>
            <td><s:property value="#allUser.totalPost"/></td>
            <td><s:property value="#allUser.user_Group.groupName"/></td>
            <td>
              <a href="#editUser" data-toggle="modal" onclick="edit(<s:property value="#allUser.userId"/>">
              <img src="background/img/compose.png"/></a> &nbsp
              <a href="deleteUserAction.action?userId=<s:property value="#allUser.userId"/>" ><img src="background/img/delete.png"></a>
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
    <h3>创建新用户</h3>
  </div>
  <div class="modal-body">
    <form id="regForm" class="form-horizontal" action="regUserAction.action?admin=1" method="post"/>
      <div class="control-group">
        <label for="inputEmail" class="control-label">用户名： </label>
        <div class="controls">
          <input id="regUsername" name="user.userName" type="text" placeholder="输入用户名" />
        </div>
      </div>
      <div class="control-group">
        <label for="inputCurrentPassword" class="control-label">密码 ：</label>
        <div class="controls">
          <input id="regPassword" type="password" name="user.password"  placeholder="输入密码" />
        </div>
      </div>
      <div class="control-group">
        <label for="inputCurrentPassword" class="control-label">确认密码 ：</label>
        <div class="controls">
          <input id="retypepassword" type="password" placeholder="再次输入密码" />
        </div>
      </div>
      <div class="control-group">
        <label for="inputCurrentPassword"  class="control-label">邮箱：</label>
        <div class="controls">
          <input name="user.email" type="text" placeholder="输入您的邮箱" />
        </div>
      </div>
      <div class="control-group">
        <label for="inputCurrentPassword" class="control-label">性别：</label>
        <div class="controls">
          <input id="sex" name="user.sex" value="0" type="radio" />男
          <input id="sex" name="user.sex" value="1" type="radio" />女
        </div>
      </div>
      <div class="control-group">
        <label for="inputCurrentPassword"  class="control-label">QQ：</label>
        <div class="controls">
          <input id="qq" name="user.QQ" type="text" />
        </div>
      </div>
      <div class="control-group">
        <label for="inputCurrentPassword" class="control-label">个人介绍：</label>
        <div class="controls">
          <textarea rows="" cols="" name="user.selfAssessment"></textarea>
        </div>
      </div>
      <div class="control-group">
        <label for="inputCurrentPassword" class="control-label">个性签名：</label>
        <div class="controls">
          <textarea rows="" cols="" name="user.selfSignature"></textarea>
        </div>
      </div>
    </form>
  </div>
  <div class="modal-footer">
  <button type="button" data-dismiss="modal" class="btn">取消</button>
  <button type="button" class="btn btn-primary" onclick="validate()">创建</button>
  </div>
</div>
      
      <div id="editUser" class="modal hide fade">
  <div class="modal-header">
    <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
    <h3>修改用户组</h3>
  </div>
  <div class="modal-body">
    <form id="editUserGroup" name="editUserGroup" class="form-horizontal" method="post"/>
      <div class="control-group">
        <label for="inputEmail" class="control-label">用户组： </label>
        <div class="controls">
        
        <select name="groupId" id="select">
        <option>请选择用户组</option>
        <s:iterator value="#request.group" id="group">        
        <option value="<s:property value="#group.groupId"/>"> <s:property value="#group.groupName"/> </option>
        </s:iterator>
        </select>
        </div>
      </div>
    
  </div>
  <div class="modal-footer">
  <button type="button" data-dismiss="modal" class="btn">取消</button>
  <button type="submit" class="btn btn-primary" onclick="edit()">保存</button>
  </form>
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
