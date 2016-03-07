<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'creatCategory.jsp' starting page</title>
    
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
    <form action="createCategory.action" method="post" id="categoryForm">
     <p>
     分区标题：<input type="text" name="category.tittle" id="tittle"/>
     <span style="line-height: 120%; display: block; color:#cc0000;"></span>
     
     </p>
     
     <p>分区说明：<textarea name="category.description" id="description"></textarea></p>
    </form>
    <p><input type="button" value="创建" id="submit"></p>
  </body>
  
  <script type="text/javascript">
	$("#submit").click(function() {
		if ($("#categoryForm").valid()) {
						$("#categoryForm").submit();
		}
			});

	$("#categoryForm").validate({
		rules : {
			"category.tittle" : {
				required : true,
			}
		},
		messages : {
			"category.tittle" : {
				required : ' 请输入分区标题!',
			}
		},
		errorPlacement : function(error, element) {
			error.appendTo(element.next());
		}
	});
  
  </script>
  
</html>
