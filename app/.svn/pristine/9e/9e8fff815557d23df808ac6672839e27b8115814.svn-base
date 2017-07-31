<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>app</title>
		<script type="text/javascript" src="${app}/js.js"></script>
		<script type="text/javascript" src="${app}/data.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css"/>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css"/>
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css"/>
		<!-- app -->
		<link rel="stylesheet" type="text/css" href="${app}/home.css"/>
		<script type="text/javascript" src="${common}/All.js"></script>
	</head>
	<body>
		<div class="headTitle" id="divTitle">执法对象选择</div>
		<div id="main" class="main" style="padding:0px;margin:0 auto; width:850px; height:418px; border:1px solid #95b8e7;">
			<div class="left" style="width:500px;height:418px; border:0px solid #95b8e7; OVERFLOW-Y: auto; OVERFLOW-X:auto;">
				<div class="divContainer" id="questionContainer">
				<form id="queryForm" action="${url}" method="post">
					<input type="hidden" id="lawobjtype" name="lawobjtype" value="${lawobjtype}" />
					<input type="hidden" id="pageSize" name="pageSize"
						value="${pageSize}" /> 
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="formtable">
						<tr>
							<th width="120" align="right">
								执法对象名称：
							</th>
							<td>
								<input class="i-text" type="text" name="name" id=""/>
							</td>
							<th width="120" align="right">
								所属行政区：
							</th>
							<td>
								<input class="i-text" type="text" name="regionId" id="ssxzq"/>
							</td>
						</tr>
						<tr>
							<th width="120" align="right">
								控制类型：
							</th>
							<td colspan="3">
								<input class="i-text" type="text" id="kzlx" name="kzlx"/>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<span class="btn btn-ok"> <input id="query" type="submit"
										value="查询"> </span>
								&nbsp;
								<a href="#" id="J-from-reset" class="btslink">重置</a>&nbsp;
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="divContainer" id="infectlist">
				<table id="data"></table>
			</div>
			</div>
			<div class="right" style="width:349px;height:418px; border-bottom:0px solid #95b8e7;border-left:1px solid #95b8e7;border-top:none;">
				<table id="choseedata" title="您选择的执法对象"></table>
			</div>
		</div>
		<table style="width : 100%;">
			<tr>
				<td align="center" height="50">
					<span class="btn btn-ok"> <input id="save" type="submit"
						value="确定"> </span>
					&nbsp;
				</td>
			</tr>
		</table>
	</body>
</html>
<script LANGUAGE="JavaScript">

$(document).ready(function(){

	$('#ssxzq').combotree({
		url:'regionTree.json',
		onBeforeSelect:function(node){
			if(node.state){        
				$('#ssxzq').tree('unselect');    
			} 
		}
	});
	
	$('#kzlx').combobox({
		url:'kzlxList.json',
		valueField:'id',
		textField:'name'
	});
	
	$('#zt').combobox({
		url:'ztList.json',
		valueField:'id',
		textField:'name'
	});
	$("#J-from-reset").click(function(){
		$("#queryForm").form("clear");
		var p = $('#data').datagrid('getPager');
		$('#page').val($(p).pagination('options').pageNumber);
		$('#pageSize').val($(p).pagination('options').pageSize);
	});
	$("#infectlist").height($("#main").height() - $("#questionContainer").outerHeight());
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: false,
		pagination:true,
		fitColumns:true,
		nowrap: false,
		striped: true,
		collapsible:true,
		url:'${url}',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'ck',checkbox:true},
			{field:'address',hidden:true},
			{field:'fddbr',hidden:true},
			{field:'fddbrlxdh',hidden:true},
			{field:'hbfzr',hidden:true},
			{field:'hbfzrlxdh',hidden:true},
			{field:'name',title:'执法对象名称', align:"left", halign:'center', width:100},
			{field:'regionName',title:'所属行政区', align:"left", halign:'center', width:50},
			{field:'kzlx',title:'控制类型', align:"center", halign:'center', width:50},
		    {field:'isActive',title:'状态', align:"center", halign:'center', width:25},
		]],
		onSelect:function(rowIndex, rowData){
			addrow(rowData);
		},
		onUnselect:function(rowIndex, rowData){
			delrow(rowData.id);
		}
	});
	$('#choseedata').datagrid({
		rownumbers: true,
		singleSelect: true,
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'address',hidden:true},
			{field:'fddbr',hidden:true},
			{field:'fddbrlxdh',hidden:true},
			{field:'hbfzr',hidden:true},
			{field:'hbfzrlxdh',hidden:true},
			{field:'name',title:'工业污染源名称', align:"left", halign:'center'},
		    {field:'operate',title:'操作', align:"center", halign:'center',
		    	formatter:function(value,rowData,rowIndex){  
		    		return '<a onclick="delrow(\''+ rowData.id +'\')">删除</a> ';  
		       }  
			}
		]]
	});
	
	//确认返回数据
	$("#save").click(function(){
		var rows = $('#choseedata').datagrid('getRows');

		var ids = "";
		var names = "";
		var rowString = '[';
		for(var i=0;i<rows.length;i++){
			ids += rows[i].id;
			names += rows[i].name;
			rowString += "{'id':'"+rows[i].id+"'";
			rowString += ",'name':'"+rows[i].name+"'";
			rowString += ",'fddbr':'"+rows[i].fddbr+"'";
			rowString += ",'fddbrlxdh':'"+rows[i].fddbrlxdh+"'";
			rowString += ",'address':'"+rows[i].address+"'}";
			if(i!=rows.length-1){
				rowString += ",";
				ids += ",";
				names += ",";
			}
		}
		rowString += ']';
		// 调用父窗口方法
		var parentObj = window.dialogArguments;
		parentObj.setloawObj(ids, names);
		self.close();
	});
});
//添加行
function addrow(rowData){
	$('#choseedata').datagrid("appendRow",{id:rowData.id,name:rowData.name,fddbr:rowData.fddbr,fddbrlxdh:rowData.fddbrlxdh,address:rowData.address});
}
//删除行
function delrow(id){
	//删除行
	var rows = $('#choseedata').datagrid('getRows');
	for(var i=0;i<rows.length;i++){
		if(rows[i].id==id){
			$('#choseedata').datagrid("deleteRow",i);
			break;
		}
	}
	//取消选中状态
	var dataRows = $('#data').datagrid('getRows');
	for(var j=0;j<dataRows.length;j++){
		if(dataRows[j].id==id){
			var index = $('#data').datagrid('getRowIndex',dataRows[j]);
			if(index>=0){
				$('#data').datagrid('unselectRow',index);
			}
			return;
		}
	}
}
</script>