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
        <div id="searchForm" style="width: 100%;">
			<form id="queryForm" method="post" action="checkedLawobjQuery.json">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" />
				<table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
						<th width="13.3%" align="right">
							区域
						</th>
						<td width="22%" >
							<input class="y-text" type="text" id="areaid" name="areaid" type="text" value="${areaid}"/>
						</td>
						<th width="13.3%" align="right">
							年份
						</th>
						<td width="22%" >
							<input class="y-text" type="text" id="year" name="year"/>
						</td>
						
						<th width="13.3%" align="right">
							月份
						</th>
						<td width="22%">
							<input class="y-text" type="text" id="month" name="month"/>
						</td>
						<td>
							<input type="submit" id="query"  class="queryBlue" style="margin: 0px 9px;" value="查　询" onclick="hideSearchForm()"/>
                            
						</td>
						<td>
							<span><input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/></span>
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
////////////////////////////////////////////////////////////////////////////////////
function initH(){
	var hh=$(window).height()-100;
	$("#rbblist").height(hh);
}
initH();
//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});
//查询
$('#queryForm').submit(function() {
	$("#queryForm").ajaxSubmit( {
		success : function(data) {
			$('#data').datagrid('loadData', data)
		}
	});
	return false;
});
//重置
$("#J-from-reset").click(function() {
	$("#queryForm").form("reset");
});
//开始抽选
$("#check").click(function() {
	var year = $('#year').combobox('getValue');
	var quarter = $('#quarter').combobox('getValue');
	if(year==null || year==""){
		$.messager.alert('操作提示:', "年份不能为空");
		return false;
	}
	if(quarter==null || quarter==""){
		$.messager.alert('操作提示:', "季度不能为空");
		return false;
	}
	$.ajax( {
				url : "startCheck.json?year=" + year+"&quarter=" + quarter,
				success : function(data) {
					if (data.state) {
						alert(data.msg);
						refresh();
					} else {
						$.messager.alert('开始抽选:', data.msg);
					}
				}
			});
});
//生成任务
$("#create").click(function() {
	var year = $('#year').combobox('getValue');
	var quarter = $('#quarter').combobox('getValue');
	if(year==null || year==""){
		$.messager.alert('操作提示:', "年份不能为空");
		return false;
	}
	if(quarter==null || quarter==""){
		$.messager.alert('操作提示:', "季度不能为空");
		return false;
	}
	$.ajax( {
				url : "createWork.json?year=" + year+"&quarter=" + quarter,
				success : function(data) {
					if (data.state) {
						alert(data.msg);
						refresh();
					} else {
						$.messager.alert('生成任务:', data.msg);
					}
				}
			});
});

$(document).ready(function() {
	$.ajaxSetup({cache:false});
	$("#spotChecklist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
	var year=new Date().getFullYear(); 
	
	//区域
	$('#areaid').combobox({
		height:34,
		url : 'queryAreaComboWithCur.json',
		valueField : 'id',
		textField : 'name',
		onSelect: function (record) {//做区域部门的联动选择
             $("#orgid-link").colorbox({iframe:true,width:"300", height:"400",href:"${basePath}/orgPubQuery.htm?multi=true&areaid="+record.id});
        }
	});
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
	$('#month').combobox({
		height:34,
		url:'newMonthList.json',
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
		url : 'checkedLawobjQuery.json',
		onLoadSuccess : function(data) {
			$(this).datagrid('doCellTip', {
				'max-width' : '300px',
				'delay' : 500
			});
		},
		columns : [ [
				{field:'year',title:'年份', align:"center", halign:'center',width:20},
				{field:'month',title:'月份', align:"center", halign:'center',width:20},
				{field:'qymc',title:'企业名称', align:"left", halign:'center',width:80},
				{field:'ssjgbm',title:'所属监管部门', align:"center", halign:'center',width:30},
				{field:'zfdxType',title:'执法对象类型', align:"center", halign:'center',width:40},
				{field:'cxlx',title:'抽选类型', align:"center", halign:'center',width:30},
				{field:'isCreated',title:'是否已生成任务', align:"center", halign:'center',width:40}
		] ]
	});
	initPager();
});
</script>