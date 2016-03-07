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
    
    <title>My JSP 'createForum.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="js/jquery.validate.js"></script>
  </head>
  
  <body>
    <form action="createForum.action" method="post" id="ForumForm">
    <p>板块名： <input type="text" name="forum.tittle" id="tittle"> <span id="error" style="line-height: 120%; color:#cc0000;"></span></p>
    <p>分区：
    <select name="categoryId" >
    <option>请选择分区</option>
    <s:iterator value="#application.category">
    <option value="<s:property value="categoryId"/>"> <s:property value="tittle"/></option>
    </s:iterator>
    </select>  </p>
    <p>板块说明： <textarea cols="20" rows="2" name="forum.description"></textarea> </p>    
    <p>版规： <textarea cols="20" rows="4" name="forum.forumRul"></textarea> </p>  
    </form>
       <input type="button" value="保存" id="submit">
  </body>
  <script type="text/javascript">
  $("#submit").click(function(){
	  if($("#ForumForm").valid()){
		  $("#ForumForm").submit();
	  }
  });
  
  $("#ForumForm").validate({
	  rules : {
		  "forum.tittle" : {
			  required : true,
		  }
	  },
	  messages : {
		  "forum.tittle" : {
			  required : '请输入板块名！',
		  }
	  },
		errorPlacement : function(error, element) {
			error.appendTo(element.next());
		}
  });
  
  </script>
</html>
