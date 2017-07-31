<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${title }</title>
	<script type="text/javascript" src="${app}/js.js"></script>
	<script type="text/javascript" src="${app}/data.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
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
	</head>
	<body>
	<div class="breadClumbs"> 系统管理&nbsp;&gt;&nbsp;${title}</div>
		<div id="searchMask" class="searchMask" style="top:30px;"><p>点击查看更多查询条件</p></div>
        <div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;top:30px;">
			<form id="queryForm" method="post" action="quarterChecktimeSetQuery.json">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" /> 
				<table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
						<th width="120" align="right">区域：</th>
						<td align="left" width="200">
							<input class="y-text" id="areaid" name="area"	type="text" value="${areaid}" />
						</td>
						<th width="120" align="right">
							年份：
						</th>
						<td width="200" >
							<input class="y-text" type="text" id="year" name="year"/>
						</td>
						
						<th width="120" align="right">
							是否可用：
						</th>
						<td>
							<input class="y-text" style="width:150px;" type="text" id="isActive" name="isActive"/>
						</td>
						
					</tr>
					<tr>
						<th width="120" align="right">
							季度：
						</th>
						<td width="200" >
							<input class="y-text" type="text" id="quarter" name="quarter"/>
						</td>
						<th width="120" align="right">
							是否已执行：
						</th>
						<td width="200" >
							<input class="y-text" type="text" id="executed" name="executed"/>
						</td>
						
					</tr>
					<tr>
						<td colspan="6" align="center">
							<input type="submit" id="query"  class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
                             <input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
						</td>
					</tr>
				</table>
			</form>
			<div class="closeBtn">收起</div>
			</div>
			<div id="layer1" class="layer"></div>
            <h1 id="divTitle" class="h1_1 topMask" style="padding-top: 10px;">${title}</h1>
			<div style="width:95%; margin:-7px  auto 0px;" class="t-r">
            <input type="button" class="bTn btnbgAdd directory_comwidth" id="Add" value="新建" />
           </div>
		  <div class="divContainer" id="rbblist" style=" width:100%; margin:10px auto 0px;">
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
function edit(curObj) {
	layer.open({
        type: 2,
        title: '编辑随机抽查时间设置',
        area: ['500px', '430px'],
        content: "quarterChecktimeSetAdd.htm?id=" + curObj.id, 
        end: function () {
        	refresh();
        }

    });
	//All.ShowModalWin("quarterChecktimeSetAdd.htm?id=" + curObj.id, "", 600, 500);
	
}
function del(curObj) {
	var index=layer.confirm('确定要删除当前抽查时间吗？', {
     	icon: 3, 
        title:'季度抽查时间设定'
     }, function(index){
    	 $.ajax( {
				url : "delQuarterChecktimeSet.json?id=" + curObj.id,
				success : function(data) {
					if(data.state){
						var tishi=layer.alert(data.msg,{
					     	title:'删除抽查时间',
					        icon:1,
					        shadeClose:true,
					     },
					     function(){
					    	 refresh();
					        layer.close(tishi);
					     }
					     );
					}else{
						var tishi=layer.alert(data.msg,{
					     	title:'删除抽查时间',
					        icon:1,
					        shadeClose:true,
					     },
					     function(){
					        layer.close(tishi);
					     }
					     );
					}
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}
$('#queryForm').submit(function() {
	$("#queryForm").ajaxSubmit( {
		success : function(data) {
			$('#data').datagrid('loadData', data)
		}
	});
	return false;
});

$("#J-from-reset").click(function() {
	$("#queryForm").form("reset");
});
function closeLayer(){
	layer.closeAll('iframe');
}
$(document).ready(function() {
	//区域
	$('#areaid').combobox({
		height:34,
		url : 'queryAreaComboWithCur.json?fid='+Math.random(),
		valueField : 'id',
		textField : 'name'
	});
	$.ajaxSetup({cache:false});
	$("#quarterChecktimeSetlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
	$("#Add").click(function(){
		layer.open({
            type: 2,
            title: '新建随机抽查时间设置',
            area: ['500px', '430px'],
            content: "quarterChecktimeSetAdd.htm", 
            end: function () {
            	refresh();
            }

        });
	});
	
	//是否可用下拉
	$('#isActive').combobox({
		height:34,
		data:[{'id':'Y','name':'是'},{'id':'N','name':'否'}],
		value : 'Y',
		editable:false,
		valueField:'id',
		textField:'name'
	});
	//是否可用下拉
	$('#executed').combobox({
		height:34,
		data:[{'id':'Y','name':'是'},{'id':'N','name':'否'}],
		value : 'N',
		editable:false,
		valueField:'id',
		textField:'name'
	});
	var year=new Date().getFullYear(); 
	//年度下拉
	$('#year').combobox({
		height:34,
		data:[{'id':year,'name':year+'年'},{'id':year+1,'name':year+1+'年'},
			  {'id':year+2,'name':year+2+'年'},{'id':year+3,'name':year+3+'年'},
			  {'id':year+4,'name':year+4+'年'}],
		editable:false,
		valueField:'id',
		textField:'name'
	});
	//季度下拉
	$('#quarter').combobox({
		height:34,
		url:'quarterList.json',
		editable:false,
		valueField:'id',
		textField:'name'
	});
	$('#data').datagrid( {
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		pagination : true,
		rownumbers : true,
		fitColumns:true,
		url : 'quarterChecktimeSetQuery.json',
		onLoadSuccess : function(data) {
			$(this).datagrid('doCellTip', {
				'max-width' : '300px',
				'delay' : 500
			});
		},
		columns : [ [
			{field : 'year', align:'center',title : '年份', width : 50}, 
			{field : 'quarter', align:'center',title : '季度', width : 50}, 
			{field : 'time', align:'center',title : '抽查时间', width : 50}, 
			{field : 'isActive', align:'center',title : '是否可用', width : 50}, 
			{field : 'executed', align:'center',title : '是否已执行', width : 50},
			{field : 'operate', align:'center',title : '操作', align : 'center', width : 100}
		] ]
	});
	initPager();
});
</script>