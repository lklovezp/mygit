<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		 <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
    <link href="${app}/common.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${app}/js.js"></script>
	<script type="text/javascript" src="${app}/data.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link rel="stylesheet" href="${app}/easyUIReset.css"  type="text/css" />
    <link rel="stylesheet" href="${app}/attachFileList.css" type="text/css">
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
    <!--执法检查-->
    <link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
    <!--时间插件 my97-->
    <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
    <script type="text/javascript" src="${common}/All.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
	<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
	<link href="${app}/hnjz.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
	<link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
	<style>
		.basicinfoTable,.basicinfoTable td,.basicinfoTable th{ border: 1px solid #d4d4d4;}
		.basicinfoTable th{background-color: #edfaff;}
		.basicinfoTable td{text-align: left;}
		.basicinfoTable th{text-align: right;}
		</style>
	</HEAD>

	<body style="padding-bottom: 70px;">
		<div class="headTitle" style="font-weight: normal;">${title}</div>
		<div style="padding: 20px;">
			<form id="myform" name="myform" method="post" action="xfdjSave.json">
				<table class="dataTable basicinfoTable" width="100%" cellpadding="0" cellspacing="0">
					<input type="hidden" value="${xfdjForm.id}" id="id" name="id">
					<div id="neirong" style="padding: 5px;">
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
							<th style="width:120px;">
								信访时间：
							</th>
							<td style="width:120px;">
								${xfdjForm.xfsj}
							</td>
							<th style="width:120px;">
								信访人：
							</th>
							<td style="width:120px;">
								${xfdjForm.xfr}
							</td>
							<th style="width:120px;">
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
						<tr>
							<th width="120">
								办结情况：
							</th>
							<td colspan="5" style="word-wrap:break-word;word-break:break-all;">
								${xfbjdForm.bjqk}
							</td>
						</tr>
						<tr>
							<th width="120">
								环境信访办理情况：
							</th>
							<td colspan="5" style="word-wrap:break-word;word-break:break-all;">
								${xfbjdForm.hjxfblqk}
							</td>
						</tr>
						<tr>
							<th width="120">
								回访形式：
							</th>
							<td colspan="3">
								${xfbjdForm.hfxs}
							</td>
							<th>
								回访人：
							</th>
							<td>
								${xfbjdForm.hfrName}
							</td>
						</tr>
						<tr>
							<th>
								回访日期：
							</th>
							<td>
								${xfbjdForm.hfrq}
							</td>
							<th>
								返回人：
							</th>
							<td>
								${xfbjdForm.fhr}
							</td>
							<th>
								返回日期：
							</th>
							<td>
								${xfbjdForm.fhrq}
							</td>
						</tr>
						<tr>
							<th>
								接收人：
							</th>
							<td>
								${xfbjdForm.jsr}
							</td>
							<th>
								接收日期：
							</th>
							<td colspan="3">
								${xfbjdForm.jssj}
							</td>
						</tr>
						<tr>
							<th>
								报出情况：
							</th>
							<td colspan="5" style="word-wrap:break-word;word-break:break-all;">
								${xfbjdForm.bcqk}
							</td>
						</tr>
						<tr>
							<th>
								报出人：
							</th>
							<td>
								${xfbjdForm.bcr}
							</td>
							<th>
								报出日期：
							</th>
							<td colspan="3">
								${xfbjdForm.bcrq}
							</td>
						</tr>
					</div>
				</table>
			</form>
	</body>
</HTML>