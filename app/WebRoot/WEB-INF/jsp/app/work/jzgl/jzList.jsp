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
			新建卷宗
		</div>
		<div class="divContainer" id="questionContainer">
			<form id="queryForm" method="post" action="getJzList.json">
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
				<input type="hidden" id="sysVer" name="sysVer" value="${sysVer}" />
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="formtable">
					<tr>
						<th width="140" align="right">
							卷宗名称：
						</th>
						<td width="240" align="left">
							<input class="i-text" type="text" id="rwmc" name="rwmc" />
						</td>
						<th width="140" align="right">
							卷宗时间：
						</th>
						<td width="320" align="left">
							<input class="easyui-datebox" type="text" id="pfsj_from" name="pfStarttime" />
							至
							<input class="easyui-datebox" type="text" id="pfsj_to" name="pfEndtime" />
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<span class="btn btn-ok"> <input id="query" type="submit"
									value="查询"> </span> &nbsp;
							<a href="#" id="J-from-reset" class="btslink">重置</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href='#' onclick='javascript: distribute()' class='btslink'>新建卷宗</a>&nbsp;&nbsp;&nbsp;
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
	//新建卷宗的页面跳转
	function distribute() {
		All.ShowModalWin('xjjzPage.htm', '新建卷宗', 500 , 360);
		refresh();
	}
	//办理
	function pf(curObj) {
		All.ShowModalWin('xjjzPage.htm?applyId=' + curObj.id, '卷宗提交', 500 , 360);
		refresh();
	}
	//编辑
	function edit(curObj) {
		All.ShowModalWin('pfPage.htm?applyId=' + curObj.id, '卷宗编辑');
		refresh();
	}
	//删除
	function del(curObj) {
		$.messager.confirm('卷宗删除', '确定要删除当前卷宗吗？', function(r) {
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
		All.ShowModalWin('jzPageInfo.htm?applyId=' + curObj.id, '卷宗查看', 500, 360);
		refresh();
	}
	// 重置
	$("#J-from-reset").click(function(){
		$("#queryForm").reset();
		$('#pfsj_from').datebox('setValues', '');
		$('#pfsj_to').datebox('setValues', '');
	});

	$('#queryForm').submit(function() {
		$("#queryForm").ajaxSubmit( {
			success : function(data) {
				$('#data').datagrid('loadData', data)
			}
		});
		return false;
	});
	
	$(document).ready(function() {
		$("#rwpflist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
		$('#data').datagrid( {
			nowrap : false,
			striped : true,
			collapsible : true,
			singleSelect : true,
			fitColumns : true,
			pagination : true,
			rownumbers : true,
			url : 'getJzList.json',
			onLoadSuccess : function(data) {
				$(this).datagrid('doCellTip', {
					'max-width' : '300px',
					'delay' : 500
				});
			},
			columns : [ [ {
				field : 'rwmc',
				title : '卷宗名称',
				width : 300
			}, {
				field : 'scsj',
				title : '卷宗时间',
				align : 'center',
				width : 300
			}, {
				field : 'operate',
				title : '操作',
				align : 'center',
				width : 300
			} ] ]
		});
		initPager();
		
		$("#pfsj_from").datebox({
		onSelect:function(date){
			var starttime = date.getFullYear()+'-'+(date.getMonth()>9?(date.getMonth()+1):('0'+(date.getMonth()+1)))+'-'+(date.getDate()>9?date.getDate():('0'+date.getDate()));
			var endtime = $("input[name='pfEndtime']").val();
			if(starttime != ''&&endtime != '') {
				var start = parseInt(starttime.replace(new RegExp('-', 'gm'), ''));
				var end = parseInt(endtime.replace(new RegExp('-', 'gm'), ''));
				if(start > end) {
					alert("开始时间不能大于截止时间！");
					$("#pfsj_from").datebox('clear');
					return false;
				}
			}
			return true;
		}
	});
	$("#pfsj_to").datebox({
		onSelect:function(date){
			var endtime = date.getFullYear()+'-'+(date.getMonth()>9?(date.getMonth()+1):('0'+(date.getMonth()+1)))+'-'+(date.getDate()>9?date.getDate():('0'+date.getDate()));
			var starttime = $("input[name='pfStarttime']").val();
			if(starttime != ''&&endtime != '') {
				var start = parseInt(starttime.replace(new RegExp('-', 'gm'), ''));
				var end = parseInt(endtime.replace(new RegExp('-', 'gm'), ''));
				if(start > end) {
					alert("开始时间不能大于截止时间！");
					$("#pfsj_to").datebox('clear');
					return false;
				}
			}
			return true;
		}
	});

});
</SCRIPT>