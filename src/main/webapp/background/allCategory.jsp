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
function edit(id,tittle,description){
	document.getElementById('tittle').value=tittle;
	document.getElementById('description').value=description;
	document.editCategoryForm.action="editCategory.action?category.categoryId="+id; 
	
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
              <li><a href="adminFindAllCategory.action" class="active"><i class="batch category"></i><br />分区管理</a></li>
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
    <div class="row">
<div class="span12"><a href="#createCategory" data-toggle="modal" data-target="#createCategory" class="btn pull-right">创建分区</a>
     
     
                <div class="modal hide fade" tabindex="-1" id=createCategory role="dialog"
			aria-hidden="true" aria-labelledby="createCategory" style="width:280px; " >
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title text-primary" id="createCategory">创建分区</h4>
					</div>
					<div class="modal-body" >
						<form id="createCategoryForm" name="createCategoryForm" action="createCategory.action" method="post">
							<div class="form-group">
								<label for="tittle" class="control-label">分区名：</label> <input
									type="text" class="form-control" 
									name="category.tittle"
									oninvalid="this.setCustomValidity('分区名不能为空');"
									oninput="setCustomValidity('');" required>
							</div>
							<div class="form-group">
								<label for="password" class="control-label">分区描述：</label> 
								<textarea rows="" cols="" name="category.description"></textarea>
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
     
                     <div class="modal hide fade" tabindex="-1" id="editCategory" role="dialog"
			aria-hidden="true" aria-labelledby="editCategory" style="width:280px; " >
     <div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title text-primary" id="editCategory">编辑分区</h4>
					</div>
					<div class="modal-body" >
						<form id="editCategoryForm" name="editCategoryForm"  method="post"
							name="editCategoryForm">
							<div class="form-group">
								<label for="tittle" class="control-label">分区名：</label> <input
									type="text" class="form-control" id="tittle"
									name="category.tittle"
									oninvalid="this.setCustomValidity('分区名不能为空');"
									oninput="setCustomValidity('');" required>
							</div>
							<div class="form-group">
								<label for="password" class="control-label">分区描述：</label> 
								<textarea id="description" rows="" cols="" name="category.description"></textarea>
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
     
      <h4 class="header">所有分区</h4>
      <table class="table table-striped sortable">
        <thead>
          <tr>
            <th>分区ID</th>
            <th>分区名</th>
            <th>创建时间</th>
            <th>创建者</th>
            <th>分区描述</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
        <s:iterator value="#request.getCategory" id="category">
          <tr>
            <form action="deleteCategory.action?categoryId=<s:property value="#category.categoryId"/>">
            <td> <s:property value="#category.categoryId"/></td>
            <td><s:property value="#category.tittle"/> </td>
            <td><s:property value="#category.createTime"/></td>
            <td><s:property value="#category.createUser"/></td>
            <td> <s:property value="#category.describe"/> </td>
            <td>
              <a href="#createForum" data-toggle="modal" data-target="#editCategory" onclick="edit(<s:property value="#category.categoryId"/>,'<s:property value="#category.tittle"/>','<s:property value="#category.describe"/>')" >
              <img src="background/img/compose.png"/></a> &nbsp
              <a href="deleteCategory.action?category.categoryId=<s:property value="#category.categoryId"/>" ><img src="background/img/delete.png"></a>
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
