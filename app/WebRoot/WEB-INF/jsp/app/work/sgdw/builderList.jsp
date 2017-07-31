<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- jquery -->
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
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
    <link href="${app}/common.css" rel="stylesheet" type="text/css" />
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<link href="${app}/resetCSS.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/easyUIReset.css" rel="stylesheet" type="text/css"/>
</HEAD>

	<body>
	<div class="breadClumbs"> 基础数据&nbsp;&gt;&nbsp;施工单位管理</div>
	<div class="searchDiv" id="searchDiv" style="height: 65px;">
	<form id="queryForm" method="post" action="builderQuery.json">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" />
			   <table width="100%" class="noBorder" border="0" cellpadding="0" cellspacing="0">
					 <tr height="72">
                      <td colspan="3" align="center">
                      	<input type="text" class="i-text" id="name" name="name" value="${name}" style="width:60%;" jzTips="请输入施工单位名称或地址"/>
                      </td>
                      <td colspan="2" align="left">
                      	<input type="submit" class="input_btnImgBlue" id="query" value="查　询"/>
                     </td>
					</tr>
				</table>
			</form>
		</div>
		<div style="padding:30px 25px;" id="czdiv">
		<input class="input_btnColorlBlue mr15" id="builderAdd" type="button" value="新建"/>
        </div>
        <div class="dataDiv" id="dataDiv" style="padding:0px 25px 30px ; height:216px;">
			<table id="data" fit="true"></table>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
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
	var hh=$(window).height()-98-50;	
	$("#dataDiv").height((hh-80)+"px");
}
initH();
//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});
$(document).ready(function(){
	$("#builderlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight() - 1);
	$("#builderlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	$("#builderAdd").click(function(){
		//All.ShowModalWin("editBuilder.htm", "修改/添加施工单位信息", 600, 520);
		//refresh();
		parent.layer.open({
	        type: 2,
	        title: '添加施工单位信息',
	        area: ['800px', '600px'],
	        content: 'editBuilder.htm', 
	        end: function () {
	            refresh();
	        }

	    });
	});
	$('#zt').combobox({
		height:34,
		url:'ztList.json',
		valueField:'id',
		textField:'name'
	});
	$('#data').datagrid({
		
		nowrap: false,
		striped: true,
		collapsible:true,
		singleSelect:true,
		remoteSort:false,
		pagination : true,
		rownumbers : true,
		fitColumns:true,
		url:'builderQuery.json?isActive=Y',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
		    {field:'id',hidden:true},
			{field:'name',title:'施工单位名称',sortable:true,width:60},
			{field:'adress',title:'施工单位地址',width:100},
			{field:'isActive',title:'状态',width:10, align:'center'},
			{field:'operate',title:'操作',align:'center',width:30}
		]]
	});
	initPager();
});
function edit(curObj){
	//All.ShowModalWin('editBuilder.htm?id='+curObj.id, '查看', 600, 520);
	//refresh();
  	parent.layer.open({
        type: 2,
        title: '编辑施工单位信息',
        area: ['800px', '600px'],
        content: 'editBuilder.htm?id='+curObj.id, 
        end: function () {
            refresh();
        }

    });
}
/*
function del(curObj){
	$.messager.confirm('施工单位管理', '确定要删除当前施工单位吗？', function(r){
		if (r){
			$.ajax({
				url: "delBuilder.json?id="+curObj.id,
				success:function(data){
					alert(data.msg);
					refresh();
				}
			});
		}
	});
}*/
//删除
function del(curObj) {
	var index=layer.confirm('确定删除吗？', {
     	icon: 3, 
        title:'删除任务'
     }, function(index){
    	 $.ajax( {
				url : "delBuilder.json?id="+curObj.id,
				success : function(data) {
					refresh();
					//parent.location.reload();
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}

function info(obj){
	//All.ShowModalWin('builderInfo.htm?id='+obj.id,"查看",600,520);
	var width=800;
  	var height=600;
  	var title='查看施工单位信息';
  	parent.layerIframe(2,'builderInfo.htm?id='+obj.id,title,width,height);
}

$('#queryForm').submit(function(){  
      $("#queryForm").ajaxSubmit({
     	  success: function(data){
     	      $('#data').datagrid('loadData',data)
	      }
	 });
     return false;  
});

$("#J-from-reset").click(function() {
	$("#queryForm").form("reset");
});
</SCRIPT>