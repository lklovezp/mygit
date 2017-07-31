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
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
    <link href="${app}/common.css" rel="stylesheet" type="text/css" />
    <style>
    .dataTable th{text-align: right;}
    </style>
	</HEAD>

	<body>
		<div id="searchMask" class="searchMask"><p>点击查看更多查询条件</p></div>
        <div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;">
			<form id="queryForm" method="post" action="getXzcfList.json">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="lawobjid" name="lawobjid" value="${lawobjid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" />
				<table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
						<th width="200">
							任务名称：
						</th>
						<td>
						    <input class="y-text" type="text" id="rwmc" name="rwmc" />
						</td>
						<th width="100">
							任务来源：
						</th>
						<td>
						    <input class="y-text" type="text" id="rwly" name="rwly" />
						</td>
					</tr>
					<tr>
						<th>
							<font color="red"> * </font><a href="#" style="color:#3399CC;" id="selectpfr">派发人：</a>
						</th>
						<td>
						    <input type="hidden" id="pfrId" name="pfrId"/>
							<input class="y-text" type="text" id="pfr" readonly="readonly" />
						</td>
						<th>
							任务状态：
						</th>
						<td>
						    <input class="y-text" type="text" id="rwzt" name="rwzt" />
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="submit" id="query"  class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
                             <input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
						</td>
					</tr>
				</table>
			</form>
			<div class="closeBtn">收起</div>
		</div>
		<div id="layer1" class="layer"></div>
		<h1 id="divTitle" class="h1_1 topMask">${title}</h1>
		 <div class="divContainer" id="rbblist" style=" width:98%; margin:10px auto 0px;">
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
	var hh=$(window).height() - $("#divTitle").outerHeight()-10;
	$("#rbblist").height(hh);
}
initH();
//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});

var lawobjid = $('#lawobjid').val();
//设置选择派发人
function setUserInfoPfr(id,name) {
	$("#pfrId").val(id);
	$("#pfr").val(name);
	jQuery().colorbox.close();
}

$('#queryForm').submit(function(){
      $("#queryForm").ajaxSubmit({
     	  success: function(data){
     	      $('#data').datagrid('loadData',data)
	      }
	 });
     return false;  
});

$(document).ready(function(){
    //任务来源下拉框
    $('#rwly').combobox({
    	height:34,
		url:'dicList.json?type=1',
		valueField:'id',
		textField:'name'
	});
	//任务状态下拉框
    $('#rwzt').combobox({
    	height:34,
		url:'queryRwztCombo.json',
		valueField:'id',
		textField:'name'
	});
    $('#tasktype').combotree({
    	height:34,
		multiple:true,
		url:'taskTypeTreeCombo.json'
	});
	//选择派发人
	$("#selectpfr").colorbox({iframe:true,width:"300", height:"400",href:"taskUserPubQuery.htm?all=true&notShowZj=false&methodname=setUserInfoPfr&multi=false&condition=0"});
	
	$("#dbrwlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
	$('#data').datagrid({
				nowrap : true,
				striped : true,
				collapsible : true,
				singleSelect : true,
				remoteSort:false,
				fitColumns : true,
				pagination : true,
				rownumbers : true,
				url:'getXzcfList.json?lawobjId='+lawobjid,
				onLoadSuccess:function(data){
					$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
				},
				columns:[[
					{field:'dbworkName',title:'任务名称',width:100},
					{field:'workNote',title:'任务内容',halign:'center',align:'left',width:100},
					{field:'rwly',title:'任务来源',align:'center',width:40},
					{field:'jjcd',title:'紧急程度',align:'center',width:40},
					{field:'pfsj',title:'派发时间',align:'center',width:50},
					{field:'yqwcsx',title:'要求完成时限',align:'center',width:50},
					{field:'createby',title:'派发人',align:'center',width:40},
					{field:'zxrName',title:'主办人',align:'center',width:40},
					{field:'state',title:'任务状态',align:'center',width:40},
					{field:'operate',title:'操作',align:'center',width:50}
				]]
			});
    		initPager()
		});

//行政处罚页面
function edit(curObj){
	//All.ShowModalWin('xzcfbl.htm?applyId='+curObj.id, '行政处罚');
	//refresh();
	parent.layer.open({
        type: 2,
        title: '行政处罚',
        maxmin:true,
        area: ['1100px', ($(window).height()+120)+'px'],
        content: 'xzcfbl.htm?applyId='+curObj.id, 
        end: function () {
           refresh();
        }

    });
}

// 重置
$("#J-from-reset").click(function(){
	$("#queryForm").form("reset");
	//任务来源下拉框
	$('#rwly').combobox('setValues', '');
	//任务状态下拉框
	$('#rwzt').combobox('setValues', '');
	$("#pfrId").val("");
});
</SCRIPT>