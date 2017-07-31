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
			<form id="queryForm" method="post" action="checkProportionQuery.json">
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
						<td width="150px;">
							<input class="i-text" type="text" id="year" name="year"/>
						</td>
						<th width="100px;">
							季度：
						</th>
						<td width="150px;">
							<input class="i-text" type="text" id="quarter" name="quarter"/>
						</td>
						<th width="100px;">
							抽查比例：
						</th>
						<td width="180px;">
							<input class="i-text easyui-numberbox" maxlength="2" type="text" id="proportion1" name="proportion1" style="width:50px;"/>%  至   
							<input class="i-text easyui-numberbox" maxlength="2" type="text" id="proportion2" name="proportion2" style="width:50px;"/>%
						</td>
						<th width="100px;">
							是否可用：
						</th>
						<td>
							<input class="i-text" style="width:50px;" type="text" id="isActive" name="isActive"/>
						</td>
					</tr>
					<tr>
						<td colspan="8" align="center">
							<span class="btn btn-ok"> <input id="query" type="submit"
									value="查询"> </span>&nbsp;&nbsp;&nbsp;
							<a href="#" id="J-from-reset" class="btslink">重置</a>&nbsp;&nbsp;&nbsp;
							<a id="Add" title="新建抽查比例" class="btslink">新建</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="divContainer" id="checkProportionlist">
			<table id="data" fit="true"></table>
		</div>
		<p>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
	function edit(curObj) {
		All.ShowModalWin("checkProportionAdd.htm?id=" + curObj.id, "", 600, 400);
		refresh();
	}
	function del(curObj) {
		$.messager.confirm('季度抽查比例设定', '确定要删除当前抽查比例吗？', function(r) {
			if (r) {
				$.ajax( {
					url : "delCheckProportion.json?id=" + curObj.id,
					success : function(data) {
						if (data.state) {
							alert(data.msg);
							refresh();
						} else {
							$.messager.alert('删除抽查比例:', data.msg);
						}
					}
				});
			}
		});
	}
	$('#queryForm').submit(function() {
		var proportion1 = $("#proportion1").val();
		var proportion2 = $("#proportion2").val();
		if(parseInt(proportion1)>parseInt(proportion2)){
				alert("季度抽查比例开始不能大于截止");
				return false;
		}
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
	
	$(document).ready(function() {
		$.ajaxSetup({cache:false});
		$("#checkProportionlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
		$("#Add").click(function(){
			All.ShowModalWin("checkProportionAdd.htm", "", 600, 400);
			refresh();
		});
		//是否可用下拉
		$('#isActive').combobox({
			data:[{'id':'Y','name':'是'},{'id':'N','name':'否'}],
			value : 'Y',
			editable:false,
			valueField:'id',
			textField:'name'
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
		//季度下拉
		$('#quarter').combobox({
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
			url : 'checkProportionQuery.json',
			onLoadSuccess : function(data) {
				$(this).datagrid('doCellTip', {
					'max-width' : '300px',
					'delay' : 500
				});
			},
			columns : [ [
				{field : 'year', align:'center',title : '年份', width : 50}, 
				{field : 'quarter', align:'center',title : '季度', width : 50}, 
				{field : 'proportion', align:'center',title : '抽查比例', width : 50}, 
				{field : 'isActive', align:'center',title : '是否可用', width : 50}, 
				{field : 'operate', align:'center',title : '操作', align : 'center', width : 100}
			] ]
		});
		initPager();
	});
</SCRIPT>