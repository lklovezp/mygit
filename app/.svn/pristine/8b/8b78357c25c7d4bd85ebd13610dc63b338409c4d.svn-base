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
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
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
		<div class="headTitle" id="divTitle">
			任务派发
		</div>
		<div class="divContainer" id="questionContainer">
			<form id="queryForm" method="post" action="getRwpfList.json">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="formtable">
					<tr>
						<th width="350" align="right">
							任务名称：
						</th>
						<td width="200" align="left">
							<input class="i-text" type="text" id="rwmc" name="rwmc" />
						</td>
						<th width="140" align="right">
							任务来源：
						</th>
						<td>
							<input class="i-text" type="text" id="rwly" name="rwly" />
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<span class="btn btn-ok"> <input id="query" type="submit"
									value="查询"> </span> &nbsp;
							<a href="#" id="J-from-reset" class="btslink">重置</a>&nbsp;
						</td>
					</tr>
					<tr>
						<td align="right" colspan="4">
							<a href='#' onclick='javascript: distribute()' class='btslink'>任务派发</a>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="rwpflist" class="divContainer">
			<table id="data" fit="true"></table>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
	//派发
	function pf(curObj) {
		All.ShowModalWin('pfPage.htm?applyId=' + curObj.id, '任务派发');
		refresh();
	}
	//编辑
	function edit(curObj) {
		All.ShowModalWin('pfPage.htm?applyId=' + curObj.id, '任务派发');
		refresh();
	}
	//删除
	function del(curObj) {
		$.messager.confirm('任务删除', '确定要删除当前记录吗？', function(r) {
			if (r) {
				$.ajax( {
					url : "delpf.json?applyId=" + curObj.id,
					success : function(data) {
						refresh();
					}
				});
			}
		});
	}
	//查看
	function info(curObj) {
		All.ShowModalWin('pfPageInfo.htm?applyId=' + curObj.id, '任务派发查看');
		refresh();
	}

	$('#queryForm').submit(function() {
		$("#queryForm").ajaxSubmit( {
			success : function(data) {
				$('#data').datagrid('loadData', data)
			}
		});
		return false;
	});
	
	$(document).ready(function() {
		//任务来源下拉框
		$('#rwly').combobox( {
			url : 'dicList.json?type=1',
			valueField : 'id',
			textField : 'name'
		});

		$("#rwpflist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
		$('#data').datagrid( {
			nowrap : false,
			striped : true,
			collapsible : true,
			singleSelect : true,
			fitColumns : true,
			pagination : true,
			rownumbers : true,
			url : 'getRwpfList.json',
			onLoadSuccess : function(data) {
				$(this).datagrid('doCellTip', {
					'max-width' : '300px',
					'delay' : 500
				});
			},
			columns : [ [ {
				field : 'rwmc',
				title : '任务名称',
				width : 300
			}, {
				field : 'rwly',
				title : '任务来源',
				align : 'center',
				width : 100
			}, {
				field : 'jjcd',
				title : '紧急程度',
				align : 'center',
				width : 100
			}, {
				field : 'djr',
				title : '登记人',
				align : 'center',
				width : 100
			}, {
				field : 'scsj',
				title : '生成时间',
				align : 'center',
				width : 100
			}, {
				field : 'operate',
				title : '操作',
				align : 'center',
				width : 150
			} ] ]
		});
		initPager();
	});
	
	// 派发
	function distribute() {
		All.ShowModalWin('pfPage.htm', '任务派发');
		refresh();
	}
	// 批量派发
	function batchDistribute() {
		All.ShowModalWin('plpfPage.htm', '任务派发');
		refresh();
	}
	// 重置
	$("#J-from-reset").click(function() {
		$("#queryForm").reset();
		//任务来源下拉框
			$('#rwly').combobox('setValues', '');
		});
</SCRIPT>