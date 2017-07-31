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
	<script type="text/javascript" src="${layer}/layer.js"></script>
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
<div class="breadClumbs"> 查询统计&nbsp;&gt;&nbsp;${title}</div>
		<div id="searchMask" class="searchMask" style="top:30px"><p>点击查看更多查询条件</p></div>
			
    <div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;top:30px">
			<form id="queryForm" action="queryTaskTraceList.json" method="post">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
				<table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
						<th width="100">区域：</th>
						<td width="150"><input class="y-text" id="areaid" name="areaid" type="text" value="${areaid}" />
						</td>
						<th width="100" align="right">
						<a href="#" id="selectPfr" style="color:#3399CC;">派发人：</a>
						</th>
						<td width="150">
							<input class="y-text" type="text" id="pfr" name="pfr" value="${pfr }" />
							<input class="y-text" name="pfrid" type="hidden" id="pfrid" value="${pfrid }"/>
						</td>
						<th width="100" align="right">
							任务来源：
						</th>
						<td >
							<input class="y-text" type="text" id="rwly" name="rwly" />
						</td>
						
					</tr>
					<tr>
					<th align="right">
							操作类型：
						</th>
						<td>
							<input class="y-text" type="text" id="czlx" name="czlx" />
						</td>
						<th align="right">
							任务类型：
						</th>
						<td>
							<input class="y-text" type="text" id="tasktype" name="tasktype" value=""/>
						</td>
						<th align="right">
							任务名称：
						</th>
						<td>
							<input class="y-text" type="text" id="rwmc" name="rwmc" value=""/>
						</td>
						
					</tr>
					<tr>
					<th align="right">
							操作时间：
						</th>
						<td colspan="5">
							 <input type="text" class="y-dateTime" id="starttime" name="starttime" value="${starttime }" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\',{d:-1});}'})"/> 
                                                                    至 <input type="text" class="y-dateTime"  id="endtime" name="endtime" value="${endtime }" onclick="WdatePicker({minDate:'#F{$dp.$D(\'starttime\',{d:1});}'})"/>
						</td>
					</tr>
					
					<tr>
						<td colspan="6" align="center">
							 <input type="submit" id="query"  class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
                             <input type="button" id="J-from-reset" class="queryOrange" value="重　置"/>
						</td>
					</tr>
				</table>
			</form>
			<div class="closeBtn">收起</div>
		</div>
		
		<div id="layer1" class="layer"></div>
         <h1 id="divTitle" class="h1_1 topMask" style="padding-top: 10px;" >${title }</h1>
        <div class="divContainer" id="rbblist" style=" width:100%; margin:10px auto 0px;">
        <table id="data" fit="true" ></table>
      </div>
      
	</body>
</html>

<script language="JavaScript">
var searchMask=$("#searchMask");
var searchForm=$("#searchForm");
var layer1=$("#layer1");
layer1.hide();
searchForm.hide();
var hideSearchBtn=searchForm.find(".closeBtn");
function hideSearchForm(){
	searchForm.hide();
	layer1.hide();
}
function showSearchForm(){
	searchForm.show();
	layer1.show();
}
searchMask.bind("click",showSearchForm);
hideSearchBtn.bind("click",hideSearchForm);
////////////////////////////////////////////////////////////////////////////////////
function initH(){
	var hh=$(window).height() - $("#divTitle").outerHeight()-10;
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
	$('#endtime').val(formatterDate(new Date()));
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
		textField : 'name',
		onSelect: function (record) {//做人员区域的联动选择
             $("#selectPfr").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?showBj=true&all=false&notShowZj=false&methodname=setValues&multi=false&areaid="+record.id});
        }
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
	 $('#czlx').combobox({
		 height:34,
		url:'queryWorkLogTypeList.json',
		valueField:'id',
		textField:'name'
	});
	
	$("#selectPfr").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?showBj=true&all=false&notShowZj=false&methodname=setValues&multi=false"});
	 
	$("#infectlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight() -8);
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		nowrap:false,
		fitColumns : true,
		url:'queryTaskTraceList.json?areaid='+$('#areaid').combotree("getValue"),
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'workname',title:'任务名称', halign:'center',width:100},
			{field:'rwly',title:'任务来源', halign:'center',align:'center',width:30},
			{field:'rwlx',title:'任务类型', halign:'center',align:'center',width:30},
			{field:'lawobjname',title:'执法对象', halign:'center',width:100},
			{field:'pfr',title:'派发人', halign:'center',align:'center',width:30},
			{field:'czsj',title:'操作时间', halign:'center',align:'center',width:50},
			{field:'czlx',title:'操作类型', halign:'center',align:'center',width:30},
			{field:'rwzt',title:'任务状态', halign:'center',align:'center',width:30},
			{field:'operate',title:'操作', halign:'center',align:'center',width:30,
				formatter:function(value,rowData,rowIndex){  
		    		return '<a onclick="info(\''+ rowData.id +'\')" class="b-link">查看</a> ';  
		    		
		       } 	
			}
		]],
		onDblClickRow: function(rowIndex, rowData){
			info(rowData.id);
		}
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
	$("#pfr").val('');
	$("#pfrid").val('');
	
});

function info(id){
	//All.ShowModalWin('rwzxgcfx.htm?id='+id, '任务执行过程分析');
	window.location.href='rwzxgcfx.htm?id='+id;
	
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

/**
 * 选择后回填数据
 */
function setValues(id,name){
	
	$("#pfr").val(name);
	$("#pfrid").val(id);
	jQuery().colorbox.close();
}

//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});
</script>