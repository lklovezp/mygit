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
		<div class="headTitle" id="divTitle">${title }</div>
		<div class="divContainer" id="questionContainer">
			<form id="queryForm" action="queryComplaintList.json" method="post">
			<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" /> 
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="formtable">
					<tr>
						<th width="120" align="right">
							单位名称：
						</th>
						<td>
							<input class="i-text" type="text" name="lawobjname" id=""/>
						</td>
						<th width="120" align="right">
							地址：
						</th>
						<td>
							<input class="i-text" type="text" name="lawobjaddress" />
						</td>
					</tr>
					<tr>
						<th width="120" align="right">
							投诉时间：
						</th>
						<td>
							<input class="i-text easyui-datebox" data-options="editable:false" type="text" id="starttime" name="starttime"/>至<input class="i-text easyui-datebox" data-options="editable:false" type="text" id="endtime" name="endtime"/>
						</td>
						<th width="120" align="right">
							状态：
						</th>
						<td>
							<input class="i-text" type="text" id="zt" name="isActive" value="Y"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<span class="btn btn-ok"> <input id="query" type="submit"
									value="查询"> </span>
							&nbsp;
							<a href="#" id="J-from-reset" class="btslink">重置</a>&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="divContainer" id="infectlist">
			<table id="data" fit="true"></table>
		</div>
	</body>
</html>

<script language="JavaScript">
$(document).ready(function(){

	$('#zt').combobox({
		url:'ztList.json',
		valueField:'id',
		textField:'name'
	});
	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
		    var p = $('#data').datagrid('getPager');
		   $('#page').val($(p).pagination('options').pageNumber);
		   $('#pageSize').val($(p).pagination('options').pageSize);
	});
	
	$("#infectlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		nowrap:false,
		fitColumns : true,
		pagination:true,
		url:'queryComplaintList.json?isActive=Y',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'tssj',title:'投诉时间', align:"center", halign:'center',width:30},
			{field:'dwmc',title:'单位名称', align:"left", halign:'center',width:100},
			{field:'tsyy',title:'投诉原因', align:"center", halign:'center',width:100},
			{field:'tsr',title:'投诉人', align:"center", halign:'center',width:30},
			{field:'tsrlxdh',title:'投诉人联系电话', align:"center", halign:'center',width:30},
		    {field:'isActive',title:'状态', align:"center", halign:'center',width:20},
			{field:'operate',title:'操作', align:"center", halign:'center',width:30}
		]]
	});
	initPager();
	
	$("#starttime").datebox({
		onSelect:function(date){
			var starttime = date.getFullYear()+'-'+(date.getMonth()>9?(date.getMonth()+1):('0'+(date.getMonth()+1)))+'-'+(date.getDate()>9?date.getDate():('0'+date.getDate()));
			var endtime = $("input[name='endtime']").val();
			if(starttime != ''&&endtime != '') {
				var start = parseInt(starttime.replace(new RegExp('-', 'gm'), ''));
				var end = parseInt(endtime.replace(new RegExp('-', 'gm'), ''));
				if(start > end) {
					alert("开始时间不能大于截止时间！");
					$("#starttime").datebox('clear');
					return false;
				}
			}
			return true;
		}
	});
	$("#endtime").datebox({
		onSelect:function(date){
			var endtime = date.getFullYear()+'-'+(date.getMonth()>9?(date.getMonth()+1):('0'+(date.getMonth()+1)))+'-'+(date.getDate()>9?date.getDate():('0'+date.getDate()));
			var starttime = $("input[name='starttime']").val();
			if(starttime != ''&&endtime != '') {
				var start = parseInt(starttime.replace(new RegExp('-', 'gm'), ''));
				var end = parseInt(endtime.replace(new RegExp('-', 'gm'), ''));
				if(start > end) {
					alert("开始时间不能大于截止时间！");
					$("#endtime").datebox('clear');
					return false;
				}
			}
			return true;
		}
	});
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
	if(checkDate()){
	   	 $("#queryForm").ajaxSubmit({
	   	  success: function(data){
	   	      intiTips();
	   	      $('#data').datagrid('loadData',data)
		          initPager();
		      }
		 });
	}
   return false;  
});

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
$("#xinjian").click(function(){
	All.ShowModalWin('complaintEdit.htm', '新建投诉信息');
	$('#queryForm').submit();
});

function edit(obj){
	All.ShowModalWin('complaintEdit.htm?id='+$(obj).attr("id"), '查看工业污染源');
	$('#queryForm').submit();
}

function del(obj){
	$.messager.confirm('投诉信息查询', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
			  url: "deleteComplaint.json?id="+obj.id,
			  success:function(data){
				 alert(data.msg);
				 $('#queryForm').submit();
			  }
			});
		}
	});
}

$("#daoru").click(function(){
	All.ShowModalWin('../../comman/import.html', '工业污染源导入');
	$('#queryForm').submit();
});

function info(obj){
	All.ShowModalWin('complaintInfo.htm?id='+$(obj).attr("id"), '查看投诉信息');
	$('#queryForm').submit();
}
</script>