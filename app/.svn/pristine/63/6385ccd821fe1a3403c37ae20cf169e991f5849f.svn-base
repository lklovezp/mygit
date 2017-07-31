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
			<form id="queryForm" method="post" action="drafterSetQuery.json">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" />
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="formtable">
					<tr>
						<th width="100px;">
							审核人：
						</th>
						<td width="280px;">
							<input type="hidden" id="auditid" name="auditid" />
							<input class="i-text easyui-validatebox" data-options="required:true"  type="text" id="auditName" readonly="readonly" />
							<a href="#" id="selectAudit">选择人员</a> 
						</td>
						<th width="100px;">
							是否可用：
						</th>
						<td>
							<input class="i-text" style="width:50px;" type="text" id="isActive" name="isActive"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<span class="btn btn-ok"> <input id="query" type="submit"
									value="查询"> </span>&nbsp;&nbsp;&nbsp;
							<a href="#" id="J-from-reset" class="btslink">重置</a>&nbsp;&nbsp;&nbsp;
							<a id="Add" title="新建稿件审核人设置" class="btslink">新建</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="divContainer" id="drafterSetlist">
			<table id="data" fit="true"></table>
		</div>
		<p>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
	//选择审核人
	function setUserInfoDjr(id,name) {
		$("#auditid").val(id);
		$("#auditName").val(name);
		jQuery().colorbox.close();
	}
	function edit(curObj) {
		All.ShowModalWin("drafterSetAdd.htm?id=" + curObj.id, "", 600, 400);
		refresh();
	}
	function del(curObj) {
		$.messager.confirm('稿件审核人设置', '确定要删除吗？', function(r) {
			if (r) {
				$.ajax( {
					url : "delDrafterSet.json?id=" + curObj.id,
					success : function(data) {
						if (data.state) {
							alert(data.msg);
							refresh();
						} else {
							$.messager.alert('删除:', data.msg);
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
	
	$(document).ready(function() {
		$.ajaxSetup({cache:false});
		$("#drafterSetlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
		$("#Add").click(function(){
			All.ShowModalWin("drafterSetAdd.htm", "", 600, 400);
			refresh();
		});
		//选择审核人
		$("#selectAudit").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?all=false&notShowZj=false&methodname=setUserInfoDjr&multi=false"});
		//是否可用
		$('#isActive').combobox({
			data:[{'id':'Y','name':'是'},{'id':'N','name':'否'}],
			value : 'Y',
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
			url : 'drafterSetQuery.json',
			onLoadSuccess : function(data) {
				$(this).datagrid('doCellTip', {
					'max-width' : '300px',
					'delay' : 500
				});
			},
			columns : [ [
				{field : 'audit', align:'center',title : '审核人', width : 50}, 
				{field : 'isActive', align:'center',title : '是否可用', width : 50}, 
				{field : 'operate', align:'center',title : '操作', align : 'center', width : 100}
			] ]
		});
		initPager();
	});
</SCRIPT>