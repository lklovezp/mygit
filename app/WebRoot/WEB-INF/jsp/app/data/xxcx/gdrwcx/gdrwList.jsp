<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<script type="text/javascript" src="${layer}/layer.js"></script>
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
<link href="${app}/css/easyUIReset.css" rel="stylesheet" type="text/css"/>
<link href="${app}/css/resetCSS.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="title" class="breadClumbs"> 查询统计&nbsp;&gt;&nbsp;${title}</div>
<div class="searchDiv" id="searchDiv" style="height: 65px;">
			<form id="queryForm" action="queryGdrwList.json" method="post">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" /> 
				<table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
					   <th width="10%" align="right">
							任务名称：
						</th>
						<td>
							<input class="y-text" type="text" id="taskname" name="taskname" />
						</td>
						<th width="10%" align="right">
							归档时间：
						</th>
						<td colspan="3" >
							<input type="text" class="y-dateTime" id="starttime" name="starttime" value="${starttime }" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\',{d:-1});}'})"/> 
                                                                      至 <input type="text" class="y-dateTime"  id="endtime" name="endtime" value="${endtime }" onclick="WdatePicker({minDate:'#F{$dp.$D(\'starttime\',{d:1});}'})"/>
						   <input type="submit" class="input_btnImgBlue" value="查　询" onclick="hideSearchForm()"/>
						   <a class="moreSearch" id="moreSearch">更多查询条件</a>
						</td>
					</tr>
					<tr height="72">
						<th width="10%" align="right">
							任务类型：
						</th>
						<td>
							<input class="y-text" type="text" name="tasktype" id="tasktype"/>
						</td>
						<th width="10%" align="right">
							任务来源：
						</th>
						<td>
							<input class="y-text" type="text" name="rwly" id="rwly"/>
						</td>
						<th width="10%" align="right">
							<a href="#" style="color:#3399CC;" id="selectZban">主办部门：</a>
						</th>
						<td>
							<input class="y-text" type="text" name="zbOrgName" id="zbOrgName" value="${zbOrgName}"/>
							<input class="y-text" type="hidden" id="zbOrgId" name="zbOrgId"/>
						</td>
					</tr>
					<tr>
						<th width="10%" align="right">
							执法对象类型：
						</th>
						<td>
							<input class="y-text" type="text" name="lawobjtype" id="lawobjtype"/>
						</td>
						<th width="120" align="right">
							所属行政区：
						</th>
						<td>
							<input class="y-text" type="text" name="regionId" id="regionId"/>
						</td>
						<th width="10%" align="right">
							执法对象名称：
						</th>
						<td>
							<input class="y-text" type="text" id="lawobjname" name="lawobjname"/>
						</td>
					</tr>
					<tr>
						<th width="10%" align="right">
							主办人员：
						</th>
						<td>
							<input class="y-text" type="text" id="zbUsername" name="zbUsername" />
						</td>
					</tr>
				</table>
		   </form>
		</div>
		 <div class="divContainer" id="rbblist" style=" width:100%; margin:10px auto 0px;">
			<table id="data" fit="true"></table>
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
	var hh=$(window).height() - $("#title").outerHeight()- $("#searchDiv").outerHeight()-10;
	$("#rbblist").height(hh);
}
initH();
//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});
$(document).ready(function(){
	
	$('#lawobjtype').combotree({
		height:34,
		url:'lawtypeTree.json'
	});
	
	$('#tasktype').combotree({
		height:34,
		multiple:true,
		url:'taskTypeTreeCombo.json'
	});
	
	$("#selectZban").colorbox({iframe:true,width:"300", height:"400",href:"${basePath}/orgPubQuery.htm?multi=true"});
	
	//选择人员
   // $("#zbUserId-link").colorbox({iframe:true,width:"300", height:"400",href:"${basePath}/userPubQuery.htm?all=true&methodname=setUserInfo&id="});

  	//任务来源下拉框
    $('#rwly').combobox({
    	height:34,
		url:'dicList.json?type=1',
		valueField:'id',
		textField:'name'
	});
  	
	$('#regionId').combotree({
		height:34,
		url:'regionTree.json'
		,onBeforeSelect:function(node){
			if(node.state){        
				$('#regionId').tree('unselect');    
			} 
		}
	});
	
	$('#kzlx').combobox({
		height:34,
		url:'kzlxList.json',
		valueField:'id',
		textField:'name'
	});
	
	$('#zt').combobox({
		height:34,
		url:'ztList.json',
		valueField:'id',
		textField:'name'
	});
	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
		   $("#zbOrgId").linkbox("setValue", {id:'',name:''});
		   var p = $('#data').datagrid('getPager');
		   $('#page').val($(p).pagination('options').pageNumber);
		   $('#pageSize').val($(p).pagination('options').pageSize);
	});
	
	$("#infectlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight()-10);
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		nowrap:false,
		fitColumns : true,
		url:'queryGdrwList.json',
		//queryParams:{lawobjtype:'01'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
		    {field:'id',hidden:true},
			{field:'workname',title:'任务名称', align:"left", halign:'center',width:100},
			{field:'tasktypename',title:'任务类型', align:"center", halign:'center',width:30},
			{field:'rwly',title:'任务来源', align:"center", halign:'center',width:40},
		    {field:'regionname',title:'所属行政区', align:"center", halign:'center',width:30},
		    {field:'lawobjname',title:'执法对象', align:"left", halign:'center',width:100},
		    {field:'orgname',title:'主办部门', align:"center", halign:'center',width:40},
		    {field:'username',title:'主办人', align:"center", halign:'center',width:30},
		    {field:'gdrq',title:'归档日期', align:"center", halign:'center',width:40},
			{field:'operate',title:'操作', align:"center", halign:'center',width:30,
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
	if(checkDate()){
	    $("#queryForm").ajaxSubmit({
	   	  success: function(data){
	   	      $('#data').datagrid('loadData',data)
		      }
		 });
	}
	return false;  
});

//查看
function info(curObj){
     //All.ShowModalWin('taskDetail.htm?applyId='+curObj, '任务详情');
	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	var title='任务详情';
  	parent.layerIframe(2,'taskDetail.htm?applyId='+curObj,title,width,height);
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

function setOrgInfo(id, name){
	//$("#zbOrgId").linkbox("setValue", {id:id,name:name})
	$("#zbOrgId").val(id);
	$("#zbOrgName").val(name);
	jQuery.colorbox.close();
}
</script>