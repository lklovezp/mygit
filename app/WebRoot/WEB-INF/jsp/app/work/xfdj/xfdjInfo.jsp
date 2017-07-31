<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/default/easyui.css">
		<script type="text/javascript"	src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<!-- app -->
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
        <style type="text/css">
        	.h1_1 {
    			height: 130px;
    		}
        </style>
	</HEAD>

	<body>
		<h1 class="h1_1">${title}</h1>
		<div class="checkup" style="">
			<form id="myform" name="myform" method="post" action="xfdjSave.json">
				<div id="neirong" style="padding: 5px;">
				<input type="hidden" value="${xfdjForm.id}" id="id" name="id"/>
				<table class="dataTable" width="99%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
						<tr>
							<th>
								信访来源：
							</th>
							<td colspan="3">
								${xfdjForm.xfly}
							</td>
							<th>
								信访编号：
							</th>
							<td>
								${xfdjForm.xfbh}
							</td>
						</tr>
						<tr>
							<th>
								污染类型：
							</th>
							<td colspan="5" >
								${xfdjForm.wrlx}
							</td>
						</tr>
						<tr>
							<th width="15%">
								信访时间：
							</th>
							<td width="15%">
								${xfdjForm.xfsj}
							</td>
							<th width="15%">
								信访人：
							</th>
							<td width="15%">
								${xfdjForm.xfr}
							</td>
							<th width="15%">
								联系电话：
							</th>
							<td>
								${xfdjForm.lxdh}
							</td>
						</tr>
						<tr>
							<th>
								办结时限：
							</th>
							<td colspan="5">
								${xfdjForm.bjsx}
							</td>
						</tr>
						<tr>
							<th>
								来文名称：
							</th>
							<td colspan="5" style="word-wrap:break-word;word-break:break-all;">
								${xfdjForm.lwmc}
							</td>
						</tr>
						<tr>
							<th style="width:120px;">
								信访内容：
							</th>
							<td colspan="5" style="word-wrap:break-word;word-break:break-all;">
								${xfdjForm.xfnr}
							</td>
						</tr>
						<tr>
							<th>
								记录人：
							</th>
							<td>
								${xfdjForm.jlr}
							</td>
							<th>
								日期：
							</th>
							<td colspan="3">
								${xfdjForm.jlsj}
							</td>
						</tr>
				</table>
				</div>
			</form>
		</div>
	</body>
</HTML>