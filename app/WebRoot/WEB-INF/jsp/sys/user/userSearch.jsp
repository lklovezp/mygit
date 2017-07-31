<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- jquery -->
		<script src="${jquery}/jquery.js"></script>
		<script src="${jquery}/jquery.form.js"></script>
		<!-- colorbox -->
		<script type="text/javascript"
			src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<!-- easyui -->
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript"
			src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/default/easyui.css">
		<!-- app -->
		<link rel="stylesheet" type="text/css" href="${app}/hnjz.css">
	</HEAD>

	<body>
		<div style="margin: 20px;">
			<form id="queryForm" method="post" action="reportOrg.htm">
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
				<div id="search" style="margin: 20px 10px 5px 10px;overflow:hidden;">
					<input class="i-text" type="text" id="name" name="name"
						value="${name}" />
					<span class="btn btn-ok"> <input id="query" type="submit" value="搜索">
					</span>
					
				</div>
				
			</form>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">

</SCRIPT>