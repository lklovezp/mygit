<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>${title }</title>
    <script type="text/javascript" src="${app}/js.js"></script>
	<script type="text/javascript" src="${app}/data.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
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
    <link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />
    
	</head>

	<body>
	<div id="title" class="breadClumbs"> 查询统计&nbsp;&gt;&nbsp;${title}</div>
	<div class="searchDiv" id="searchDiv" style="height: 65px;">
			<form id="queryForm" action="queryOverdueTaskList.json" method="post">
			<input type="hidden" id="fid" name="fid" value="${fid}" />
			<input type="hidden" id="page" name="page" value="${page}" />
			<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" /> 
			<table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
				<tr>
				   <th align="right">
					任务名称：
				</th>
				<td>
					<input class="y-text" type="text" id="rwmc" name="rwmc" />
				</td>
				<th align="right">
					要求完成时限：
				</th>
				<td colspan="3">
					 <input type="text" class="y-dateTime" id="starttime" name="starttime" value="${starttime }" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\',{d:-1});}'})"/> 
                                                                  至 <input type="text" class="y-dateTime"  id="endtime" name="endtime" value="${endtime }" onclick="WdatePicker({minDate:'#F{$dp.$D(\'starttime\',{d:1});}'})"/>
				 <input type="submit" id="query"  class="input_btnImgBlue" value="查　询" onclick="hideSearchForm()"/>
				<a class="moreSearch" id="moreSearch">更多查询条件</a>
				</td>
				</tr>
					<tr height="72">
						<th width="11.3%">区域：</th>
						<td width="22%"><input class="y-text" id="areaid" name="areaid"
							type="text" value="${areaid}" />
						</td>
						
						<th width="11.3%">
							任务来源：
						</th>
						<td width="22%">
							<input class="y-text" type="text" id="rwly" name="rwly" />
						</td>
						<th width="11.3%">
							任务类型：
						</th>
						<td width="22%">
							<input class="y-text" type="text" id="tasktype" name="tasktype" />
						</td>
					</tr>
					<tr>
					   <th width="11.3%">
							紧急程度：
						</th>
						<td width="22%">
							<input class="y-text" type="text" id="jjcd" name="jjcd" />
						</td>
						<th width="11.3%"">
							是否完成：
						</th>
						<td width="22%">
							<input class="y-text" type="text" id="isComplete" name="isComplete" value=""/>
						</td>
						
					</tr>
				</table>
			</form>
		</div>
        <div class="divContainer" id="rbblist" style=" width:95%; margin:10px auto 0px;">
        <table id="data" fit="true" ></table>
      </div>
	</body>
</html>
<script language="JavaScript">
$("#searchDiv").height("65px");
$("#moreSearch").click(function(){
	if($(this).hasClass("show")){//展开变折叠
		$(this).removeClass("show").text("展开查询条件");
		$("#searchDiv").height("65px");
	}else{//折叠变展开
		$(this).addClass("show").text("折叠查询条件");
		$("#searchDiv").height("auto");
	}
	
});
////////////////////////////////////////////////////////////////////////////////////
function initH(){
	var hh=$(window).height() - $("#title").outerHeight()-$("#searchDiv").outerHeight()-10;
	$("#rbblist").height(hh);
}
initH();
	formatterDate = function(date) {
		 var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
		 var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
		 + (date.getMonth() + 1);
		 return date.getFullYear() + '-' + month + '-' + day;
	};

$(document).ready(function(){
	$('#endtime').val( formatterDate(new Date()));
	//任务类型
	$("#tasktype").combotree({
		height:34,
		multiple: true,
		url:'taskTypeTreeCombo.json'
	});
	//区域
	$('#areaid').combobox({
		height:34,
		url : 'queryAreaComboWithCur.json',
		valueField : 'id',
		textField : 'name'
	});
	//任务来源下拉框
    $('#rwly').combobox({
    	height:34,
		url:'dicList.json?type=1',
		valueField:'id',
		textField:'name'
	});
	//紧急程度
	 $('#jjcd').combobox({
		 height:34,
		url:'dicList.json?type=3',
		valueField:'id',
		textField:'name'
	});
	//是否
	$("#isComplete").combobox({
		height:34,
		url:'sfList.json',
		valueField:'id',
		textField:'name'
	});
	
	$("#infectlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight()-8);
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		nowrap:false,
		fitColumns : true,
		url:'queryOverdueTaskList.json?areaid='+$('#areaid').combotree("getValue"),
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'workname',title:'任务名称', halign:'center',width:100},
			{field:'taskname',title:'任务类型',align:'center', halign:'center',width:30},
			{field:'lawobjname',title:'执法对象', halign:'center',width:100},
			{field:'pfr',title:'派发人', align:'center',halign:'center',width:40},
			{field:'yqwcsx',title:'要求完成时限',align:'center', halign:'center',width:40},
			{field:'yqdjdlx',title:'逾期点阶段类型',align:'center', halign:'center',width:50},
			{field:'yqdczr',title:'逾期点操作人',align:'center', halign:'center',width:40},
			{field:'isComplete',title:'是否完成',align:'center', halign:'center',width:30},
			{field:'operate',title:'操作',align:'center', halign:'center',width:20}
		]]
	});
	initPager();
	$('#queryForm').submit(function(){  
		if(checkDate()){
		   	 $("#queryForm").ajaxSubmit({
		   	  success: function(data){
		   	      $('#data').datagrid('loadData',data)
			      }
			 });
		}
	   return false;  
	});
	
	
});

$('#J-from-reset').click(function(){
	$("#queryForm").form("reset");
	$('#endtime').val(formatterDate(new Date()));
});

//查看
function info(curObj){
	//All.ShowModalWin('rwzxgcfx.htm?id='+curObj.id, '任务执行过程分析');
	window.location.href='rwzxgcfx.htm?id='+curObj.id;
}

//校验查询条件开始时间不能大于结束时间
function checkDate(){
	var starttime = $("input[name='starttime']").val();
	var endtime = $("input[name='endtime']").val();
	if(starttime != ''&&endtime != '') {
		var start = parseInt(starttime.replace(new RegExp('-', 'gm'), ''));
		var end = parseInt(endtime.replace(new RegExp('-', 'gm'), ''));
		if(start > end) {
			alert("开始时间不能大于截止时间！");
			return false;
		}
	}
	return true;
}
//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});
</script>