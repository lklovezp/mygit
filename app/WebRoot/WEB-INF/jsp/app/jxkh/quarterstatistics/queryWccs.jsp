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
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${common}/All.js"></script>
		<style type="text/css">
         .panel-header{background-color: #cff1ff;}
         .formtable, .formtable th, .formtable td{border-color:#dddddd; }
         .formtable td { border: 1px dotted;}
        </style>
	</head>

	<body>
		<div class="h1_1" id="divTitle">已归档任务列表</div>
		<form id="queryForm" action="queryGdrwList.json" method="post">
			<input type="hidden" id="yqwcStarttime" name="yqwcStarttime" value="${yqwcStarttime}" />
			<input type="hidden" id="yqwcEndtime" name="yqwcEndtime" value="${yqwcEndtime}" />
			<input type="hidden" id="page" name="page" value="${page}" />
			<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" /> 
			<input type="hidden" id="tasktype" name="tasktype" value="${tasktype}" /> 
		</form>
		<div class="divContainer" id="infectlist">
			<table id="data" fit="true"></table>
		</div>
	</body>
</html>

<script language="JavaScript">
$(document).ready(function(){
	
	//选择人员
   // $("#zbUserId-link").colorbox({iframe:true,width:"300", height:"400",href:"${basePath}/userPubQuery.htm?all=true&methodname=setUserInfo&id="});

  	
	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
		    var p = $('#data').datagrid('getPager');
		   $('#page').val($(p).pagination('options').pageNumber);
		   $('#pageSize').val($(p).pagination('options').pageSize);
	});
	
	$("#infectlist").height($(window).height() - $("#divTitle").outerHeight()-10);
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		nowrap:false,
		fitColumns : true,
		url:'queryGdrwList.json',
		queryParams:{tasktype:'${tasktype}',yqwcStarttime:'${yqwcStarttime}',yqwcEndtime:'${yqwcEndtime}',areaid:'${areaid}',zbOrgId:'${zbOrgId}',lawobjtype:'${lawobjtype}'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
		    {field:'id',hidden:true},
			{field:'workname',title:'任务名称', align:"left", halign:'center',width:100},
			{field:'tasktypename',title:'任务类型', align:"center", halign:'center',width:30},
			{field:'rwly',title:'任务来源', align:"center", halign:'center',width:20},
		    {field:'regionname',title:'所属行政区', align:"center", halign:'center',width:30},
		    {field:'lawobjname',title:'执法对象', align:"left", halign:'center',width:100},
		    {field:'orgname',title:'主办部门', align:"center", halign:'center',width:35},
		    {field:'username',title:'主办人', align:"center", halign:'center',width:20},
		    {field:'gdrq',title:'归档日期', align:"center", halign:'center',width:30},
			{field:'operate',title:'操作', align:"center", halign:'center',width:20,
		    	formatter:function(value,rowData,rowIndex){  
		    		return '<a class="b-link" onclick="info(\''+ rowData.id +'\')">查看</a> ';  
		       }  
		    }
		]],
		onDblClickRow: function(rowIndex, rowData){
			info(rowData.id);
		}
	});
	initPager();
	
});

$('#queryForm').submit(function(){  
    $("#queryForm").ajaxSubmit({
   	  success: function(data){
   	      $('#data').datagrid('loadData',data)
	      }
	 });
	return false;  
});

//查看
function info(curObj){
     //All.ShowModalWin('taskDetail.htm?applyId='+curObj, '任务详情');
	var title='任务详情';
	var width=screen.width*0.8;
	var height=screen.height*0.8-50;
	parent.layerIframe(2,'taskDetail.htm?applyId='+curObj,title,width,height);
}

function setOrgInfo(id, name){
	$("#zbOrgId").linkbox("setValue", {id:id,name:name})
	jQuery.colorbox.close();
}
</script>