<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${app}/js.js"></script>
		<script type="text/javascript" src="${app}/data.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<script type="text/javascript" src="${common}/All.js"></script>
	</head>

	<body>
		<div class="headTitle" id="divTitle">环评信息列表</div>
		<div class="divContainer" id="infectlist">
			<table id="data" fit="true"></table>
		</div>
	</body>
</html>

<script language="JavaScript">
$(document).ready(function(){
	
	$("#infectlist").height($(window).height() -  $("#divTitle").outerHeight());
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		fitColumns:true,
		nowrap: false,
		url:'hpxxList.json?pid=${pid}',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'name',title:'环评项目名称', align:'center', align:"left", halign:'center',width:100},
			{field:'hpspwh',title:'环评审批文号', align:"left", halign:'center',width:50},
			{field:'spsj',title:'审批时间', align:"center", halign:'center',width:50},
			{field:'operate',title:'操作', align:"center", halign:'center',width:30}
		]]
	});
});

function edit(obj){
	All.ShowModalWin('hpxxEdit.htm?id='+$(obj).attr("id"), '', 1000, 800);
	$('#data').datagrid('reload');
}

function info(obj){
	All.ShowModalWin('hpxxInfo.htm?id='+$(obj).attr("id"), '', 1000, 800);
}

function del(obj){
	$.messager.confirm('环评信息管理', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
			  url: "delHpxx.json?id="+obj.id,
			  success:function(data){
				 alert(data.msg);
				 $('#data').datagrid('reload');
			  }
			});
		}
	});
}
</script>