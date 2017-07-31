<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- jquery -->
		<script src="${jquery}/jquery.js"></script>
		<script src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${common}/All.js"></script>
		<!-- colorbox -->
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<!-- easyui -->
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<!-- app -->
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<link rel="stylesheet" type="text/css" href="${app}/hnjz.css">
	</HEAD>

	<body>
		<div class="headTitle" id="divTitle">${title}</div>
		<div class="divContainer" id="questionContainer">
			<form id="queryForm" method="post" action="getDbrwList.json">
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formtable">
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
						<th width="100">
							主办人：
						</th>
						<td>
						    <input type="hidden" id="pfrId" name="pfrId"/>
							<input class="i-text" type="text" id="pfr" readonly="readonly" />
							<a href="#" id="selectpfr">选择人员</a>
						</td>
					</tr>
					<tr>
						<td colspan="8" align="center">
							<span class="btn btn-ok"> <input id="query" type="submit"
									value="查询"> </span>
							&nbsp;
							<a href="#" id="J-from-reset" class="btslink">重置</a>&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="dbrwlist" class="divContainer">
			<table id="data" fit="true"></table>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
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
		url:'dicList.json?type=1',
		valueField:'id',
		textField:'name'
	});
	//任务状态下拉框
    $('#rwzt').combobox({
		url:'queryRwztCombo.json',
		valueField:'id',
		textField:'name'
	});
    $('#tasktype').combotree({
		multiple:true,
		url:'taskTypeTreeCombo.json'
	});
	//选择派发人
	$("#selectpfr").colorbox({iframe:true,width:"300", height:"400",href:"taskUserPubQuery.htm?all=true&notShowZj=false&methodname=setUserInfoPfr&multi=false&condition=0"});
	
	$("#dbrwlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
	$('#data').datagrid({
				nowrap : false,
				striped : true,
				collapsible : true,
				singleSelect : true,
				remoteSort:false,
				fitColumns : true,
				pagination : true,
				rownumbers : true,
				url:'getDbrwList.json',
				onLoadSuccess:function(data){
      		        $(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
                },
                rowStyler: function(index,row){
			        if (row.isYQ==1){
			            return 'color:red;';
			        }
			    },
	            columns:[[
					{field:'dbworkName',title:'卷宗名称',width:300},
					{field:'yqwcsx',title:'卷宗时间',align:'center',width:150},
					{field:'zxrName',title:'主办人',align:'center',width:150},
					{field:'state',title:'任务状态',align:'center',width:150},
					{field:'operate',title:'操作',align:'center',width:200}
				]]
	});
    initPager();
});

// 重置
$("#J-from-reset").click(function(){
	$("#queryForm").form("reset");
	//任务来源下拉框
	$('#rwly').combobox('setValues', '');
	//任务状态下拉框
	$('#rwzt').combobox('setValues', '');
	$("#pfrId").val("");
});

function flowChart(piId){
    All.ShowModalWin("${ctx}/wf/process/flowChart?piId="+piId, '流程图',900,500);
}
//任务详情
function info(curObj){
     All.ShowModalWin('taskDetail.htm?applyId='+curObj.id, '任务详情');
     refresh();
}

function actionOper(code,action,actionType,applyId,taskId,text){
	if(actionType=='AJAX'){
			$.post('${ctx}/'+action, {applyId:applyId,taskId:taskId}, function(json){
				$.messager.alert('提示',json.msg,'info',function(){
					if(json.state){
						$('#data').datagrid('reload');
					}
				});
			},'json');
	}else if(actionType == 'NEW_WIN_S'){
			$.colorbox({
				iframe:true,
				width:"50%", 
				height:"90%",
				href:'${ctx}/'+action+'&applyId='+applyId+'&taskId='+taskId
			});
	}else if(actionType == 'NEW_WIN_B'){
		   parent.$.colorbox({
				iframe:true,
				width:"80%", 
				height:"100%",
				href:'${ctx}/'+action+'&applyId='+applyId+'&taskId='+taskId,
				onClosed:function(){
				   $('#queryForm').submit();
				}
			});
	}else if(actionType == 'NEW_WIN_D'){
			//改为弹出对话框窗口
			All.ShowModalWin('${ctx}/'+action+'&applyId='+applyId+'&taskId='+taskId, text);
			$('#queryForm').submit();
	}
}
</SCRIPT>