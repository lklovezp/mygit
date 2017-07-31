<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/default/easyui.css">
        <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<style type="text/css">
		label{ margin-left:5px; margin-right:20px;}
		</style>
	</head>

	<body>
		<h1 class="h1_1">${title}</h1>
		<div class="dataDiv" style="width:95%; margin:0px auto 25px; padding:10px 0px 30px; border:1px solid #acacac;">
			<form id="myform" name="myform" method="post" action="saveWg.json">
				<table  class="dataTable" width="100%" border="0px"  cellpadding="0" cellspacing="0">
					<input type="hidden" value="${wgForm.id}" id="id" name="id">
					<input type="hidden"  id="orgid" name="orgid" value="${orgid}">
					<div id="neirong" style="padding: 5px;">
						<tr>
							<th width="130">
								网格名称:
							</th>
							<td>
								<input class="y-text" value="${wgForm.wgmc}"/>
							</td>
						</tr>
						<tr>
							<th>
								网格描述:
							</th>
							<td>
								<input class="y-text" value="${wgForm.ms}"/>
							</td>
						</tr>
						<tr>
							<th>
								网格责任人：

							</th>
							<td>
							<input class="y-text" value="${wgForm.username}"/>
							</td>
						</tr>
						
					</div>
				</table>
			</form>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
	
</SCRIPT>