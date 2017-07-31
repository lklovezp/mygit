<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- jquery -->
		<script src="${jquery}/jquery.js"></script>
		<script src="${jquery}/jquery.form.js"></script>
		<!-- colorbox -->
		<script type="text/javascript"
			src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<!-- easyui -->
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript"
			src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/default/easyui.css">
		<!-- app -->
		<link rel="stylesheet" type="text/css" href="${app}/hnjz.css">
		<script type="text/javascript" src="${common}/All.js"></script>
	</HEAD>

	<body>
		<div class="headTitle" id="divTitle">${title}</div>
		<div class="divContainer" id="questionContainer">
			<form id="queryForm" method="post" action="yearLawobjQuery.json">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" />
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="formtable">
					<tr>
						<th width="100px;">
							年份：
						</th>
						<td width="120px;">
							<input class="i-text" type="text" id="year" name="year"/>
						</td>
						<th width="120px;">
							执法对象名称：
						</th>
						<td width="200px;">
							<input class="i-text" style="width:200px;" type="text" id="lawobjname" name="lawobjname"/>
						</td>
						<th width="120px;">
							执法对象类型：
						</th>
						<td width="150px;">
							<input class="i-text" type="text" id="lawobjtype" name="lawobjtype"/>
						</td>
						<th width="120px;">
							抽选类型：
						</th>
						<td width="150px;">
							<input class="i-text" type="text" id="type" name="type"/>
						</td>
					</tr>
					<tr>
						<td colspan="8" align="center">
							<span class="btn btn-ok"> <input id="query" type="submit"
									value="查询"> </span>&nbsp;&nbsp;&nbsp;
							<a href="#" id="J-from-reset" class="btslink">重置</a>&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="8" align="right">
							<span class="btn btn-ok" > <input id="startCheck" type="button" title="开始抽选" value="开始抽选">
							</span>&nbsp;&nbsp;
							<span class="btn btn-ok" > <input id="Add" type="button" title="添加执法对象" value="添加执法对象">
							</span>&nbsp;&nbsp;
							<span class="btn btn-ok" > <input id="deleteResult" type="button" title="按年份删除抽选结果" value="按年份删除抽选结果">
							</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="divContainer" id="yearLawobjlist">
			<table id="data" fit="true"></table>
		</div>
		<p>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
	function del(curObj) {
		$.messager.confirm('年度抽查对象管理', '确定要删除当前抽查对象吗？', function(r) {
			if (r) {
				$.ajax( {
					url : "delYearLawobj.json?id=" + curObj.id,
					success : function(data) {
						if (data.state) {
							alert(data.msg);
							refresh();
						} else {
							$.messager.alert('删除年度抽查对象:', data.msg);
						}
					}
				});
			}
		});
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
	//开始抽选
	$("#startCheck").click(function() {
		var year = $('#year').combobox('getValue');
		if(year==null || year==""){
			$.messager.alert('操作提示:', "请选择年份");
			return false;
		}
		var year1=year-1;
		$.messager.confirm('年度抽查对象管理', '确定要抽选'+year+'年的抽查对象吗？抽查对象：1.全部国省控、重点污染源；2.'+year1+'年违法被调查及被投诉企业；3.其他一般企业按监察人数1：5的比例由系统随机抽选', function(r) {
			if(r){
					$.ajax({
						url : "yearCheck.json?year=" + year,
						success : function(data) {
							if (data.state) {
								alert(data.msg);
								refresh();
							} else {
								$.messager.alert('开始抽选:', data.msg);
							}
						}
					});
			}
		});	
	});
	//重新抽选
	$("#deleteResult").click(function() {
		var year = $('#year').combobox('getValue');
		if(year==null || year==""){
			$.messager.alert('操作提示:', "请选择年份");
			return false;
		}
		$.messager.confirm('年度抽查对象管理', '确定要删除'+year+'年的抽选结果吗？', function(r) {
			if(r){
					$.ajax({
						url : "deleteResult.json?year=" + year,
						success : function(data) {
							if (data.state) {
								alert(data.msg);
								refresh();
							} else {
								$.messager.alert('删除抽选结果:', data.msg);
							}
						}
					});
			}
		});	
	});
	$(document).ready(function() {
		$.ajaxSetup({cache:false});
		$("#yearLawobjlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
		$("#Add").click(function(){
			var year = $('#year').combobox('getValue');
			if(year==null || year==""){
				$.messager.alert('操作提示:', "请选择年份");
				return false;
			}
			$.ajax({
					url : "isChecked.json?year=" + year,
					success : function(data) {
						if (data.state) {
							All.ShowModalWin('yearLawobjAdd.htm?year='+year, '添加年度抽查对象');
							refresh();
						} else {
							$.messager.alert('添加年度抽查对象:', data.msg);
						}
					}
			});
		});
		var year=new Date().getFullYear(); 
		//年度下拉
		$('#year').combobox({
			data:[{'id':year,'name':year+'年'},{'id':year+1,'name':year+1+'年'},
				  {'id':year+2,'name':year+2+'年'},{'id':year+3,'name':year+3+'年'},
				  {'id':year+4,'name':year+4+'年'}],
			editable:false,
			valueField:'id',
			textField:'name'
		});
		//执法对象类型下拉框
		$('#lawobjtype').combobox({
			url:'dicList.json?type=5',
			valueField:'id',
			textField:'name'
		});
		//抽选类型下拉框
		$('#type').combobox({
			url:'cxlxList.json',
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
			url : 'yearLawobjQuery.json',
			onLoadSuccess : function(data) {
				$(this).datagrid('doCellTip', {
					'max-width' : '300px',
					'delay' : 500
				});
			},
			columns : [ [
				{field : 'year', align:'center',title : '年份', width : 30}, 
				{field : 'lawobjname', align:'left',title : '执法对象', width : 150}, 
				{field : 'lawobjtype', align:'center',title : '执法对象类型', width : 50}, 
				{field : 'type', align:'center',title : '抽选类型', width : 50}, 
				{field : 'operate', align:'center',title : '操作', align : 'center', width : 100}
			] ]
		});
		initPager();
	});
</SCRIPT>