<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<jsp:useBean id="image" scope="page" class="edu.zsc.cxl.bbs.validate.validatePic"/> 

<%
String str = image.getValiPic(0, 0, response.getOutputStream());
session.setAttribute("valiCode", str);

out.clear();
out=pageContext.pushBody();

%>

