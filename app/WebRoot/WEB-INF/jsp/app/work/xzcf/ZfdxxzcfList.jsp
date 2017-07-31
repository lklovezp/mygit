<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${common}/All.js"></script>
<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<!--jQuery-->
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
.panel-header{background-color: #cff1ff;}
.formtable, .formtable th, .formtable td{border-color:#dddddd; }

</style>
	</HEAD>

	<body>
		
		<div class="divContainer" id="questionContainer">
			<form id="queryForm" method="post" action="getXzcfList.json">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="lawobjid" name="lawobjid" value="${lawobjid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" />
			</form>
		</div>
		<div class="h1_1" id="divTitle">${title}</div>
		<div id="dbrwlist" class="divContainer">
			<table id="data" fit="true"></table>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
var lawobjid = $('#lawobjid').val();
//设置选择派发人
function setUserInfoPfr(id,name) {
	$("#pfrId").val(id);
	$("#pfr").val(name);
	jQuery().colorbox.close();
}

$('#queryForm').submit(function(){
      $("#queryForm").ajaxSubmit({
     	  success: function(data){
     	  	return true;
	      }
	 });
     return false;  
});

$(document).ready(function(){
	$("#dbrwlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
	$('#data').datagrid({
				nowrap : false,
				striped : true,
				collapsible : true,
				singleSelect : true,
				remoteSort:false,
				fitColumns : true,
				pagination : true,
				rownumbers : true,
				url:'getXzcfList.json?lawobjId='+lawobjid,
				onLoadSuccess:function(data){
					$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
				},
				columns:[[
					{field:'dbworkName',title:'任务名称',width:100},
					{field:'workNote',title:'任务内容',halign:'center',align:'left',width:100},
					{field:'rwly',title:'任务来源',align:'center',width:40},
					{field:'jjcd',title:'紧急程度',align:'center',width:40},
					{field:'pfsj',title:'派发时间',align:'center',width:50},
					{field:'yqwcsx',title:'要求完成时限',align:'center',width:50},
					{field:'createby',title:'派发人',align:'center',width:40},
					{field:'zxrName',title:'主办人',align:'center',width:40},
					{field:'state',title:'任务状态',align:'center',width:40},
					{field:'operate',title:'操作',align:'center',width:50}
				]]
			});
    		initPager()
		});

//行政处罚页面
function edit(curObj){
	//All.ShowModalWin('xzcfblInfo.htm?applyId='+curObj.id, '行政处罚查看');
	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	var title='行政处罚查看';
  	parent.parent.parent.layerIframe(2,'xzcfblInfo.htm?applyId='+curObj.id,title,width,height);
}

// 重置
$("#J-from-reset").click(function(){
	$("#queryForm").form("reset");
	//任务来源下拉框
	$('#rwly').combobox('setValues', '');
	//任务状态下拉框
	$('#rwzt').combobox('setValues', '');
	$("#pfrId").val("");
});
</SCRIPT>