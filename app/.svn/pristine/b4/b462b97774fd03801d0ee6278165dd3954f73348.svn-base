<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>经纬度</title>
	<script type="text/javascript" src="${jquery}/jquery.js"></script>
	<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${jquery }/jquery.form.js"></script>
	<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
	<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
  </head>
  <style type="text/css">
  input{
  	width:80px;
  }
  </style>
  <body>
  		<center>
    	<table style="margin-top:15px;">
    		<tr><td align="center">度</td><td align="center">分</td><td align="center">秒</td></tr>
    		<tr>
    			<td><input class="i-text" type="text" id="du"/></td>
    			<td><input class="i-text" type="text" id="fen"/></td>
    			<td><input class="i-text" type="text" id="miao"/></td>
    		</tr>
    	</table>
    	<div style="margin-top:15px;"><a id="btnConfirm" class="easyui-linkbutton">确定</a></div>
    	</center>
  </body>
</html>
<script LANGUAGE="JavaScript">
var jd = '';
var method = '${method}';
$(document).ready( function() {
	$('#btnConfirm').click(function() {
		jd = $("#du").val()+"°"+$("#fen").val()+"′"+$("#miao").val()+"″";
		window.parent." + method + "('" + jd + "');
	});
});
</script>
