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
	</head>

	<body>
	<div id="title" class="breadClumbs"> 系统管理&nbsp;&gt;&nbsp;${title}</div>
		<div class="searchDiv" id="searchDiv" style="height: 95px;">
			<form id="queryForm" action="queryConfigList.json" method="post">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
				<table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
						<th>
							执法对象类型：
						</th>
						<td>
							<input class="i-text" type="text" style="width:200px;" id="lawobjtype" name="lawobjtype"/>
						</td>
						<th>
							任务类型：
						</th>
						<td>
							<input class="i-text" style="width:200px;" type="text" id="tasktypeid" name="tasktypeid"/>
						</td>
						<th>
							执行方式：
						</th>
						<td>
							<input class="i-text" style="width:200px;" type="text" id="isexecchecklist" name="isexecchecklist"/>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="6">
							<input type="submit" id="query"  class="queryBlue" value="查　询" />
                            <input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
						</td>
					</tr>
				</table>
			</form>
		
		</div>
	    
		 <div class="dataDiv" id="dataDiv" style="padding:20px 25px 5px ;">
			<table id="data" fit="true"></table>
		</div>
	</body>
</html>

<script language="JavaScript">

////////////////////////////////////////////////////////////////////////////////////
function initH(){
	var hh=$(window).height() - $("#title").outerHeight()-$("#searchDiv").outerHeight()-25;
	$("#dataDiv").height(hh);
}
initH();
//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});

$(document).ready(function() {
	$("#infectlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());

	$('#data').datagrid( {
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		pagination : true,
		rownumbers : true,
		fitColumns:true,
		url : 'queryConfigList.json',
		onLoadSuccess : function(data) {
			$(this).datagrid('doCellTip', {
				'max-width' : '300px',
				'delay' : 500
			});
		},
		columns : [ [
			{field : 'id', hidden : true},
			{field:'tasktypeid',title:'任务类型', align:'center', halign:'center', width:30},
			{field:'lawobjtype',title:'执法对象类型', align:'center', halign:'center', width:30},
			{field:'isexecchecklist',title:'执行方式', align:'center', halign:'center', width:30},
			{field:'operate',title:'操作', align:'center', halign:'center', width:40}
		] ]
	});
	initPager();
});

$('#lawobjtype').combotree({
	height:34,
	type:"post",
	url:'lawtypeTree.json',
	valueField:'id',
	textField:'name',
	onChange : function(newValue, oldValue){
		$.ajax({
			url: "queryTaskTypeCodeName.json?id="+newValue,
			success:function(data){
				$("#tasktypeid").combotree("loadData", data);
			}
		});
	}
});

	//任务类型执行方式
	$('#isexecchecklist').combobox({
		height:34,
		type:"post",
		url:'dicList.json?type=16',
		valueField:'id',
		textField:'name'
	});

$("#tasktypeid").combotree({
	height:34,
		multiple:false,
		url:'taskTypeTreeCombo.json'
	});

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

function edit(curObj){
	//All.ShowModalWin('editConfig.htm?id='+curObj.id, '', 600, 450);
	//refresh();
	parent.layer.open({
        type: 2,
        title: '编辑检查模板',
        area: ['800px', '500px'],
        content: 'editConfig.htm?id='+curObj.id, 
        end: function () {
            //location.reload();
            refresh();
        }
    });
}

function del(curObj){
	/*$.messager.confirm('监察模板设置', '确定要删除当前配置吗？', function(r){
		if (r){
			$.ajax({
				url: "removeConfig.json?id="+curObj.id,
				success:function(data){
					refresh();
				}
			});
		}
	});*/
	var index=layer.confirm('确定要删除当前配置吗？', {
     	icon: 3, 
        title:'监察模板设置'
     }, function(index){
    	 $.ajax({
				url: "removeConfig.json?id="+curObj.id,
				success:function(data){
					alert(data.msg);
					refresh();
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     });
}
</script>