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
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript">
function edit(id,tittle){
	document.getElementById('tittle').value=tittle;
	document.editGroup.action="updateGroup.action?group.groupId="+id; 
	
}

</script>

  </head>
  
  <body>
        <div id="in-nav">
      <div class="container">
        <div class="row">
          <div class="span12">
            <ul class="pull-right">
            <li><a href="login.html">${currentAdmin.username}</a></li>
            </ul>
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
              <li><a href="getAllUserAction.action"><i class="batch users"></i><br />用户管理</a></li>
              <li><a href="findAllGroup.action"  class="active"><i class="batch group"></i><br />用户组管理</a></li>
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
<div class="span12"><a href="#createGroup" data-toggle="modal" data-target="#createGroup" class="btn pull-right">创建用户组</a>
     
     
                <div class="modal hide fade" tabindex="-1" id="createGroup" role="dialog"
			aria-hidden="true" aria-labelledby="createGroup" style="width:280px; " >
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title text-primary" id="createGroup">创建用户组</h4>
					</div>
					<div class="modal-body" >
						<form id="createGroup" name="createGroup" action="createGroup.action" method="post">
							<div class="form-group">
								<label for="tittle" class="control-label">用户组名：</label> <input
									type="text" class="form-control" 
									name="group.groupName"
									oninvalid="this.setCustomValidity('用户组名不能为空');"
									oninput="setCustomValidity('');" required>
							</div>
					</div>
					<div class="modal-footer">
						<button id="save" type="submit" class="btn btn-primary">保存</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
					</form>

				</div>
			</div>
		</div>
     
                     <div class="modal hide fade" tabindex="-1" id="editGroup" role="dialog"
			aria-hidden="true" aria-labelledby="editGroup" style="width:280px; " >
     <div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title text-primary" id="editGroup">编辑用户组</h4>
					</div>
					<div class="modal-body" >
						<form id="editGroup" name="editGroup"  method="post">
							<div class="form-group">
								<label for="tittle" class="control-label">用户组名：</label> <input
									type="text" class="form-control" id="tittle"
									name="group.groupName"
									oninvalid="this.setCustomValidity('用户组名不能为空');"
									oninput="setCustomValidity('');" required>
							</div>
					</div>
					<div class="modal-footer">
						<button id="save" type="submit" class="btn btn-primary">保存</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
					</form>

				</div>
			</div>
     </div>
     
      <h4 class="header">所有用户组</h4>
      <table class="table table-striped sortable">
        <thead>
          <tr>
            <th>用户组ID</th>
            <th>用户组名</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
        <s:iterator value="#request.allGroup" id="group">
          <tr>
            <form action="deleteGroup.action?groupId=<s:property value="#group.groupId"/>">
            <td> <s:property value="#group.groupId"/></td>
            <td><s:property value="#group.groupName"/> </td>
            <td>
              <a href="#editGroup" data-toggle="modal" data-target="#editGroup" onclick="edit(<s:property value="#group.groupId"/>,'<s:property value="#group.groupName"/>')" >
              <img src="background/img/compose.png"/></a> &nbsp
              <a href="deleteGroup.action?group.groupId=<s:property value="#group.groupId"/>" ><img src="background/img/delete.png"></a>
            </td>
            </form>
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
