<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>待办任务</title>
    
    <script type="text/javascript" src="${app}/js.js"></script>
	<script type="text/javascript" src="${app}/data.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<!-- colorbox -->
	<script type="text/javascript"	src="${colorbox}/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css"/>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <!--poshytip-->
    <link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
    <!--派发列表-->
    <link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${poshytip}/jquery.poshytip.min.js"></script>
    <script type="text/javascript" src="${common}/All.js"></script>
    <!--时间插件 my97-->
    <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
    <link href="${app}/common.css" rel="stylesheet" type="text/css" />
    
</head>
<body>
	<div id="title" style="width:99%;font-size:16px;margin:10px auto 10px;"align="center">${title}</div>
	<form id="queryForm" action="queryCountLawObjList.json" method="post">
		<input type="hidden" id="page" name="page" value="${page}" />
		<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
		<input type="hidden" id="areaid" name="areaid" value="${areaid}">
		<input type="hidden" id="year" name="year" value="${year}" >
		<input type="hidden" id="quarter" name="quarter" value="${quarter}" > 
		<input type="hidden" id="type" name="type" value="${type}" > 
	</form>
	 <div class="divContainer" id="rbblist" style=" width:99%; margin:10px auto 0px;">
		<table id="data" fit="true"></table>
	</div>
</body>
</html>
<script language="JavaScript">
$(document).ready(function(){
	
	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
		   $("#zt").combobox('setValue','Y');
		    var p = $('#data').datagrid('getPager');
		   $('#page').val($(p).pagination('options').pageNumber);
		   $('#pageSize').val($(p).pagination('options').pageSize);
	});
	
	$("#rbblist").height($(window).height() - $("#title").outerHeight()-30);
	
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		nowrap:false,
		fitColumns : true,
		pagination:true,
		url:'queryCountLawObjList.json?areaid='+$('#areaid').val()+'&year='+$('#year').val()+'&quarter='+$('#quarter').val()+'&type='+$('#type').val(),
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'name',title:'单位名称', align:"left", halign:'center',width:100},
			{field:'type',title:'企业类型', align:"center", halign:'center',width:40},
			{field:'times',title:'抽选次数', align:"center", halign:'center',width:40}
			
		]]
		
	});
	initPager();
});

function initPager(){
    var p = $('#data').datagrid('getPager');
	$(p).pagination({
				onSelectPage:function(pageNumber, pageSize){
				    $('#page').val(pageNumber);
				    $('#pageSize').val(pageSize);
					$(this).pagination('loading');
					$('#queryForm').submit();
					$(this).pagination('loaded');
				}
	});
}

$('#queryForm').submit(function(){  
    $("#queryForm").ajaxSubmit({
   	  success: function(data){
   	      intiTips();
   	      $('#data').datagrid('loadData',data)
	          initPager();
	      }
	 });
   return false;  
});

</script>