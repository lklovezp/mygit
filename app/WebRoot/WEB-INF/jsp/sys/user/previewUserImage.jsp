<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
  </head>
  <body>
  	<img id="userImg" src="#" width="350" height="495" align="middle">
  </body>
</html>
<SCRIPT type="text/javascript">
$(document).ready(function(){
	var uri="viewUserImage.json?id=${id}&Rnd="+Math.random();
	$('#userImg').attr('src',uri);
});
</SCRIPT>