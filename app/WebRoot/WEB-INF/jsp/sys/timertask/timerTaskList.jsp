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
	<script type="text/javascript" src="${poshytip}/jquery.poshytip.min.js"></script>
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
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
    <link href="${app}/common.css" rel="stylesheet" type="text/css" />
    <style>
    .dataTable th{text-align: right;}
    </style>
	</HEAD>

	<body>
	<div class="breadClumbs"> 系统管理&nbsp;&gt;&nbsp;${title}</div>
		<div id="searchMask" class="searchMask" style="top:30px"><p>点击查看更多查询条件</p></div>
        <div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;top:30px;">
			<form id="queryForm" action="queryTimerTaskList.json" method="post">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
						<th width="100">
							任务名称：
						</th>
						<td>
							<input class="y-text" type="text" id="name" name="name"/>
						</td>
						<th width="100">
							<a href="#" style="color:#3399CC;" id="selectAccepter">接受人：</a>
						</th>
						<td>
						    <input class="y-text" type="text" id="acceptername" name="acceptername">
							<input class="y-text" type="hidden" id="accepter" name="accepter"/>
						</td>
						<th width="100">
							是否可用：
						</th>
						<td>
							<input class="y-text" type="text" id="isActive" name="isActive"/>
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
            <h1 id="divTitle" class="h1_1 topMask" style="padding-top: 10px;">${title}</h1>
			<div style="width:95%; margin:-7px  auto 0px;" class="t-r">
            <input type="button" class="bTn btnbgAdd directory_comwidth" id="new" value="新建" />
           </div>
		 <div class="divContainer" id="rbblist" style=" width:98%; margin:10px auto 0px;">
			<table id="data" fit="true"></table>
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
	var hh=$(window).height() - $("#divTitle").outerHeight()-40;
	$("#rbblist").height(hh);
}
initH();
//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});

function setUserInfo(id,name) {
	//$("#accepter").linkbox("setValue", {id:id,name:name});
	$("#accepter").val(id);
	$("#acceptername").val(name);
	jQuery().colorbox.close();
}
$(document).ready(function() {
	$.ajaxSetup({cache:false});
	
	$("#infectlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight() - 0.5);
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());

	$("#selectAccepter").colorbox({iframe:true,width:"300", height:"400",href:"${basePath}/userPubQuery.htm?all=true&methodname=setUserInfo&id="+$('#leader').val()+"&notShowSys=true"});

	$('#isActive').combobox({
		height:34,
		data:[{'id':'Y','name':'是'},{'id':'N','name':'否'}],
		value : 'Y',
		editable:false,
		valueField:'id',
		textField:'name'
	});
	
	$('#data').datagrid({
		rownumbers : true,
		singleSelect : true,
		pagination : true,
		fitColumns:true,
		url:'queryTimerTaskList.json',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id', hidden:true},
			{field:'name',title:'任务名称', halign:'center', align:'left', width:150},
			{field:'tasktype',title:'任务类型', align:'center', width:100},
			{field:'hander',title:'派发人', align:'center', width:50},
			{field:'accepter',title:'接受人', align:'center', width:50},
			{field:'type',title:'自动派发类型', align:'center', width:50},
			{field:'times',title:'派发频次', align:'center', width:50},
			{field:'updated',title:'更新时间', halign:'center', width:100},
			{field:'operate',title:'操作', align:'center', width:50}
		]]
	});
	initPager();
});
// 新建
$("#new").click(function() {
	//All.ShowModalWin('editTimerTask.htm', '新建自动派发任务', 700, 500);
	//refresh();
	parent.layer.open({
        type: 2,
        title: '新建自动派发任务',
       // area: ['900px', '600px'],
        area: ['1100px', ($(window).height()+110)+'px'],
        content: 'editTimerTask.htm', 
        end: function () {
            //location.reload();
            refresh();
        }

    });
});

$("#J-from-reset").click(function() {
	$("#accepter-value").val("");
	$("#queryForm").form("reset");
});

$('#queryForm').submit(function(){
	$("#queryForm").ajaxSubmit({
		success: function(data){
			$('#data').datagrid('loadData',data);
		}
	});
	return false;  
});

function edit(curObj){
	//All.ShowModalWin('editTimerTask.htm?id='+curObj.id, '编辑自动派发任务', 700, 500);
	//refresh();
	parent.layer.open({
        type: 2,
        title: '编辑自动派发任务',
        area: ['1100px', ($(window).height()+110)+'px'],
       // area: ['900px', '600px'],
        content: 'editTimerTask.htm?id='+curObj.id, 
        end: function () {
            //location.reload();
            refresh();
        }
    });
}

function del(curObj){
	/*$.messager.confirm('自动派发任务设置', '确定要删除当前自动派发任务吗？', function(r){
		if (r){
			$.ajax({
				url: "removeTimerTask.json?id="+curObj.id,
				success:function(data){
					refresh();
				}
			});
		}
	});*/
	var index=layer.confirm('确定要删除当前自动派发任务吗？', {
     	icon: 3, 
        title:'自动派发任务设置'
     }, function(index){
    	 $.ajax({
				url: "removeTimerTask.json?id="+curObj.id,
				success:function(data){
					alert(data.msg);
					refresh();
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     });
}
</script>