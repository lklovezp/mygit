<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>卷宗查看</title>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css" />
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
		<script type="text/javascript" src="${common}/All.js"></script>
	</head>
	<body>
		    <div class="headTitle" id="divTitle">卷宗查看</div>
		    <input type="hidden" id="applyId" name="applyId" value="${work.id}" />
		    <input type="hidden" id="id" name="id" value="${work.id}" />
			<div class="divContainer" style="padding:10px;">
			<!-- 任务信息 -->
			<div class="easyui-panel" title="卷宗信息" style="margin-bottom:10px;">
				<table width="100%" height="" border="0" align="left" cellpadding="0"
					cellspacing="0" class="formtable">
					<tr>
						<th width="100" align="right">
							卷宗名称：
						</th>
						<td align="left" colspan="3">
						    <label>${work.name}</label>
						</td>
					</tr>
					<tr>
						<th align="right">
							要求完成时限：
						</th>
						<td>
						    <label><fmt:formatDate value="${work.endTime}" pattern="yyyy-MM-dd"/></label>
						</td>
					</tr>
				</table>
			</div>
	</body>
</html>
