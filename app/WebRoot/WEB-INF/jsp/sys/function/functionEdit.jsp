<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${jquery}/json2.js"></script>
	</HEAD>
	<style type="text/css">
			.panel-header, .panel-body {
    			border-color: #acacac;
			}
			.panel-header {
			    padding: 0px;
			}
			.panel-title {
    			height: 48px;
				line-height: 48px;
				background: #cff1ff;
				border: 1px solid #D4D4D4;
				text-indent: 18px;
				border-right: 1px;
				border-bottom: 0px;
				font-size: 14px;
				color: #262626;
			}
			.datagrid-toolbar, .datagrid-pager {
   				 background: #FFFFFF;
			}
			.datagrid-htable, .datagrid-header-row {
   			 background-color: #ffffff;
			}
		</style>
	<body>
		<div class="dataDiv" style="border-right: 1px;width: 98%;padding: 0px 0px 0px 14px;">
			<form id="myform"  name="myform" method="post" action="functionSave.json">
				<input type="hidden" value="${funForm.id}" id="id" name="id">
				<input type="hidden" value="${funForm.data}" id="data" name="data">
				<table class="formtable" width="100%">
					<tr>
						<th style="width:40%;" align="right">
							<font color="red"> * </font>功能名称&nbsp;
						</th>
						<td>
							<input class="y-text easyui-validatebox" data-options="required:true" type="text" id="funcName" name="funcName"
								value="${funForm.funcName}" />
						</td>
					</tr>
					<tr>
						<th style="width:40%;" align="right">
							父功能&nbsp;
						</th>
						<td>
							<input type="hidden" value="${funForm.parent}" id="parent"
								name="parent">
							<input class="y-text" type="text" readonly="true"
								value="${funForm.parentName}" id="parentName" name="parentName" />
							<a href="#" style="color: #0088CC;" id="selectParent">选择父功能</a>
						</td>
					</tr>
					<tr>
						<th style="width:40%;" align="right">
							<font color="red"> * </font>功能连接&nbsp;
						</th>
						<td>
							<input class="y-text easyui-validatebox" data-options="required:true" type="text" value="${funForm.linkaddr}"
								id="linkaddr" name="linkaddr" />
						</td>
					</tr>
					<tr>
						<th style="width:40%;" align="right">
							<font color="red"> * </font>排序&nbsp;
						</th>
						<td>
							<input class="y-text easyui-numberbox" data-options="required:true" type="text" value="${funForm.orderby}"
								id="orderby" name="orderby" />
						</td>
					</tr>
					<tr>
						<th style="width:40%;" align="right">
							功能说明&nbsp;
						</th>
						<td>
							<input class="y-text" type="text" value="${funForm.funcDesc}"
								id="funcDesc" name="funcDesc" />
						</td>
					</tr>
					
					<div class="divContainer">
						<table id="functionOp" width="100%" title="添加菜单对应的操作，例如：增加，删除" style="height: 280px" iconCls="icon-edit">
						</table>
					</div>
					<div class="footerBtn t-c" id="bottomBtn" style="margin-top: 10px;margin-bottom: 10px;">
       					<input type="submit" class="queryBlue" value="提　交"/>
       					<input type="reset"  class="queryOrange"  id="J-from-reset" value="重　置"/>
    				</div>
				</table>
			</form>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
function setFunctionInfo(id,name) {
	$('#parent').val(id)
	$('#parentName').val(name)
	$('#parentName').focus();
	jQuery().colorbox.close();
}
var lastIndex;
var dataG;
$(document).ready(function(){
	dataG = $('#functionOp').datagrid({
		url:'functionOpt.json?id=${funForm.id}',
		toolbar:[{
			text:'添加',
			iconCls:'icon-add',
			handler:function(){
				$('#functionOp').datagrid('endEdit', lastIndex);
				$('#functionOp').datagrid('appendRow',{
					opid:'',
					name:'',
					eventName:'',
					linkAddr:''
				});
				lastIndex = $('#functionOp').datagrid('getRows').length-1;
				$('#functionOp').datagrid('selectRow', lastIndex);
				$('#functionOp').datagrid('beginEdit', lastIndex);
			}},{
			text:'删除',
			iconCls:'icon-remove',
				handler:function(){
				var row = $('#functionOp').datagrid('getSelected');
				if (row){
					var index = $('#functionOp').datagrid('getRowIndex', row);
					$('#functionOp').datagrid('deleteRow', index);
				}
				}},{
			text:'删除全部',
			iconCls:'icon-remove',
			handler:function(){
				var ll = $('#functionOp').datagrid('getRows').length - 1;
				for(i = ll; i >= 0;i--){
					$('#functionOp').datagrid('deleteRow', i);
				}
			},
		}],
		fitColumns : true,
		singleSelect : true,
		columns : [ [ 
						{field : 'name', title : '操作名称',editor:{type : 'text'}, align : 'center', width : 50},
						{field : 'eventName', title : '点击事件',editor:{type : 'text'}, align : 'center', width : 100},
						{field : 'linkAddr', title : '对应链接',editor:{type : 'text'}, align : 'center', width : 100}
					] ],
		onClickRow:function(rowIndex){
			if (lastIndex != rowIndex){
				$('#functionOp').datagrid('endEdit', lastIndex);
				$('#functionOp').datagrid('beginEdit', rowIndex);
			}
			lastIndex = rowIndex;
		}
	});
	$("#selectParent").colorbox({iframe:true,width:"300",href:"functionPubQuery.htm?id=${funForm.id}",height:"400"});
	$("#J-from-reset").click(function(){
		$("#myform").reset();
	});
	//表单校验
	$("#myform").validate({
		errorClass: "error",
		submitHandler:function(form){
			dataG.datagrid('acceptChanges');
			if($("#myform").form("validate")){
				var validData = dataG.datagrid('getData');
				// 删除空行
				for(i = validData.rows.length - 1; i >= 0; i--){
					if (validData.rows[i].name == "" || validData.rows[i].eventName == ""){
						dataG.datagrid('deleteRow', i);
					}
				}
				
				var chengedData = dataG.datagrid('getData');
				for(i = chengedData.rows.length - 1; i >= 0; i--){
					if (validData.rows[i].linkAddr == ""){
						alert("请为操作添加对应链接。");
						$('#functionOp').datagrid('beginEdit', i);
						return;
					}
				}
				var gridData = dataG.datagrid('getData');
				var data = JSON.stringify(gridData);
				$('#data').val(data);
				$(form).ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"functionSave.json",
					success: function(data){
						var tishi=layer.alert(data.msg,{
					     	title:'保存功能菜单',
					        icon:1,
					        shadeClose:true,
					     },
					     function(){
					    	parent.closeLayer();
					        layer.close(tishi);
					     }
					     );
					}
				});
			}	
		}
	});
});
</SCRIPT>