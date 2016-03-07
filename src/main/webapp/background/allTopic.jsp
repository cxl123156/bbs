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
    <link rel="stylesheet" href="background/css/colpick.css" type="text/css">
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
    <script src="background/js/colpick.js" type="text/javascript"></script>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript">
function colorpick(){
	 $('#picker').colpick({
			layout:'hex',
			submit:0,
			colorScheme:'dark',
			onChange:function(hsb,hex,rgb,el,bySetColor) {
				$(el).css('border-color','#'+hex);
				// Fill the text box just if the color was set using the picker, and not the colpickSetColor function.
				if(!bySetColor) $(el).val(hex);
			}
		}).keyup(function(){
			$(this).colpickSetColor(this.value);
		});
	 
}

 
 
function edit(id){
	document.sethighlight.action="setHighLightTopic.action?topic.topicId="+id; 
	
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
              <li><a href="getForumAndCategoryIndex.action?admin1=1" class="active"><i class="batch comment"></i><br />帖子管理</a></li>
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
    <div class="span3">
      <h4>所有板块</h4>
      <div class="sidebar">
      <s:iterator value="#application.cateAndForum" id="category">
        <ul class="col-nav span3">
          <li><a href="#<s:property value="#category.key.categoryId"/>" data-toggle="collapse" class="accordion-toggle"> <s:property value="#category.key.tittle"/> </a></li>
          <li id="<s:property value="#category.key.categoryId"/>" class="collapse">       
            <ul>
            <s:iterator value="#category.value" id="forum">
              <li>
              
              <a href="showTopicForum?forum.forumId=<s:property value="#forum.forumId"/>&forumAdmin=1 "> <s:property value="#forum.tittle"/> </a>
              
              </li>
              </s:iterator>
            </ul>    
          </li>
        </ul>
        </s:iterator>
      </div>     
    </div>
        <div class="span9"><a href="background/createTopic.jsp" class="btn pull-right">创建话题</a>
          </div>
    <div class="span9">
      <h4 class="header">所有帖子</h4>
      <table class="table table-striped sortable">
        <thead>
          <tr>
            <th>话题ID</th>
            <th>标题</th>
            <th>创建时间</th>
            <th>创建者</th>
            <th>所属板块</th>
            <th>类型</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
        <s:iterator value="#request.adminCurrentForumTopic" id="topic">
          <tr>
            <form action="deleteCategory.action?categoryId=<s:property value="#category.categoryId"/>">
            <td> <s:property value="#topic.topicId"/></td>
            <td><a href="showAllPost.action?topicId=<s:property value="#topic.topicId"/>"><s:property value="#topic.topicTittle"/></a></td>
            <td><s:property value="#topic.createTime"/></td>
            <td><s:property value="#topic.topicUser"/></td>
            <td><s:property value="#topic.forumTittle"/></td>
            <td><s:property value="#topic.type"/></td>
            <td>
              <a href="#editTopic" data-toggle="modal" data-target="#editTopic">
              <img src="background/img/compose.png"/></a>
              <a href="#highLight" data-toggle="modal" data-target="#editTopic" onclick="edit(<s:property value="#topic.topicId"/>)">
              <img src="background/img/star-2.png"/></a>
              <a href="deleteTopic.action?topicId=<s:property value="#topic.topicId"/>&admin=1" ><img src="background/img/delete.png"></a>
              <a class="btn btn-s btn-primary">BUMP</a>
            </td>
            </form>
          </tr>
          </s:iterator>
        </tbody>
      </table>
      <hr>
    </div>
        
        <div class="modal hide fade" tabindex="1" id="editTopic" role="dialog"
			aria-hidden="true" aria-labelledby="myModalLabel" style="width:280px;">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title text-primary" id="myModalLabel">设置为精品贴</h4>
					</div>
					<div class="modal-body"  >
						<form id="sethighlight" action="setHighLightTopic.action" method="post"
							name="sethighlight">
							<div class="form-group">
								<label for="username" class="control-label">设置标题颜色：</label>
								<input id="picker" type="text" name="highLight.tittleColor" onclick="colorpick()"/>
							</div>
							<div class="form-group">
								<label for="password" class="control-label">加精原因：</label> 
								<textarea id="" rows="" cols="" name="highLight.highLightReason"></textarea> <label
									id="error" class="control-label"></label>
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
