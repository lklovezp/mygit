<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${app}/js.js"></script>
		<script type="text/javascript" src="${app}/data.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<script type="text/javascript" src="${common}/All.js"></script>
		<!--派发列表-->
	    <link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
	    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
	    <link href="${app}/common.css" rel="stylesheet" type="text/css" />
	    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
	    <style type="text/css">
	    	.h1_1.topMask {
	    		padding-top: 5px;
			}
	    </style>
	</head>

	<body>
		<h1 id="title" class="h1_1 topMask">${title}</h1>
		<input type="hidden" id="tasktype" name="tasktype" value="${tasktype}"/>
		<div class="divContainer" id="rbblist" style=" width:100%; margin:0px auto 0px;">
			<table id="data" fit="true"></table>
		</div>
		<iframe name="download" id="download" src="" style="display: none"></iframe>
	</body>
</html>

<script language="JavaScript">

$(document).ready(function(){
	$("#rbblist").height($(window).height() - $("#title").outerHeight());
	
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		nowrap:false,
		fitColumns : true,
		url:'queryRwInfo.json?type=${type}'+'&tasktype='+$('#tasktype').val(),
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id', hidden:true},
			{field:'workname', title:'任务名称', align:'left', halign:'center',width:100},
			{field:'tasktypename', title:'任务类型', align:'center', halign:'center',width:40},
			{field:'lawobjname', title:'执法对象', align:'left', halign:'center',width:100},
			{field:'regionname', title:'所属行政区', align:'center', halign:'center',width:35},
			{field:'pfr', title:'派发人', align:'center', halign:'center',width:30},
			{field:'yqwcsx', title:'要求完成时限', align:'center', halign:'center',width:35},
			{field:'zbry', title:'主办人员', align:'center', halign:'center',width:30},
			{field:'rwzt', title:'任务状态', align:'center', halign:'center',width:30},
			{field:'gdsj', title:'归档时间', align:'center', halign:'center',width:35},
			{field:'operate', title:'操作', align:'center', halign:'center',width:20,
				formatter:function(value,rowData,rowIndex){  
		    		return '<a style="color: #0088CC;" onclick="info(\''+ rowData.id +'\')">查看</a> ';  
		       } 
			}
		]],
		onDblClickRow: function(rowIndex, rowData){
			info(rowData.id);
		}
	});
});

function info(id){
	var width=screen.width * 0.8-50;
  	var height=screen.height * 0.8-100;
	layer.open({
	  	  type: 2,
	  	  title: '任务执行过程分析',
	  	  maxmin: true,
	  	  shadeClose: false, //点击遮罩关闭层
	  	  area : [width+'px' , height+'px'],
	  	  content: 'rwzxgcfx.htm?id='+id
	  	  });
}
function layerIframe(types,href,title,width,height){
	width=screen.width * 0.8-80;
  	height=screen.height * 0.8-130;
	  layer.open({
	  	  type: types,
	  	  title: title,
	  	  maxmin: true,
	  	  shadeClose: false, //点击遮罩关闭层
	  	  area : [width+'px' , height+'px'],
	  	  content: href
	  	  });
	  
}
function closeLayer(){
	layer.closeAll('iframe');
}
</script>