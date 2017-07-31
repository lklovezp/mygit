<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<!--jQuery-->
<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
.panel-header{background-color: #cff1ff;}
.formtable, .formtable th, .formtable td{border-color:#dddddd;}
</style>
		<script language="JavaScript">
			$(document).ready(function(){
				$("#infectlist").height($(window).height() - $("#divTitle").outerHeight()-30);
				$("#infectlist").width($(window).width());
				$("#questionContainer").width($(window).width());
				
				$('#data').datagrid({
					rownumbers: true,
					singleSelect: true,
					nowrap:false,
					fitColumns : true,
					url:'queryJdysfxList.json?id=${id}',
					onLoadSuccess:function(data){
						$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
					},
					columns:[[
						{field:'stageType',title:'阶段类型', align:'center',width:50},
						{field:'oprateUser',title:'操作人', align:'center',width:50},
						{field:'starttime',title:'开始时间', align:'center',width:100},
						{field:'endtime',title:'结束时间', align:'center',width:100},
						{field:'usetime',title:'用时(小时)', align:'center',width:100}
					]]
				});
			});
			
			
			//返回
			function fanhui(){
				window.history.back();
				
			}
		</script>
	</head>

	<body>
		<div class="h1_1" id="divTitle">阶段用时分析</div>
		<div class="divContainer" id="infectlist" style=" width:100%; margin:10px auto 0px;">
			<table id="data" fit="true"></table>
		</div>
	</body>
</html>