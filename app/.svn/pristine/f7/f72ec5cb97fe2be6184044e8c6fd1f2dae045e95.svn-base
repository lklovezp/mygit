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
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/default/easyui.css">
		<script type="text/javascript"	src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css"	href="${colorbox}/colorbox2.css">
		<script type="text/javascript" src="${jquery}/json2.js"></script>
		<!-- app -->
		<script type="text/javascript" src="${common}/All.js"></script>
		<!-- 任务管理 css-->
	    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
	    <!--系统管理 css-->
	    <link rel="stylesheet" href="${app}/css/management.css" type="text/css">
	    <link rel="stylesheet" href="${app}/css/taskDispatch.css" type="text/css">
		<!-- app -->
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
        <style type="text/css">
        .panel-header {
        	border: 1px solid #d4d4d4;
        	background-color: #cff1ff;
        }
        </style>
	</HEAD>

	<body>
		<div class="headTitle">${title}</div>
		<div class="divContainer">
			<form id="queryForm" action="" method="post">
				<input type="hidden" id="id" name="id" value="${checkItemForm.id}"/>
				<input type="hidden" id="templateid" name="templateid" value="${checkItemForm.templateid}"/>
				<input type="hidden" id="data" name="data">
				<table class="queryTable" width="100%" border="1px" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
						<th>
							监察模板名称：
						</th>
						<td>
							<label>${checkItemForm.templatename}</label>
						</td>
					</tr>
					<tr>
						<th>
							<label class="requiredLabel">*</label>检查项内容：
						</th>
						<td>
							<textarea class="i-text easyui-validatebox" data-options="required:true" style="width:254px;" type="text" id="contents" name="contents">${checkItemForm.contents}</textarea>
						</td>
					</tr>
					<tr>
						<th>
							用于统计的检查项编号：
						</th>
						<td>
							<input class="y-text" type="text" id="code" name="code" value="${checkItemForm.code}"/>
						</td>
					</tr>
					<tr>
						<th>
							检查项内容单位：
						</th>
						<td>
							<input class="y-text" type="text" style="width:250px;" id="contentsunit" name="contentsunit" value="${checkItemForm.contentsunit}"/>
						</td>
					</tr>
					<tr>
						<th>
							<label class="requiredLabel">*</label>输入类型：
						</th>
						<td>
							<input class="y-text" type="text" style="width:256px;" id="inputtype" name="inputtype" value="${checkItemForm.inputtype}"/>
						</td>
					</tr>
					<tr>
						<th>
							描述：
						</th>
						<td>
							<textarea class="i-text" style="width:254px;" type="text" id="describe" name="describe">${checkItemForm.describe}</textarea>
						</td>
					</tr>
					<tr>
						<th>
							答案换行显示：
						</th>
						<td>
							<input type="checkBox" id="isanswernewline" name="isanswernewline"
								<c:if test="${fn:trim(checkItemForm.isanswernewline) eq 'Y'}" >checked</c:if> />
						</td>
					</tr>
					<tr>
						<th>
							答案备注是否必填：
						</th>
						<td>
							<input type="checkBox" id="isrequired" name="isrequired"
								<c:if test="${fn:trim(checkItemForm.isrequired) eq 'Y'}" >checked</c:if> />
						</td>
					</tr>
					<tr>
						<th>
							是否可用：
						</th>
						<td>
							<input type="checkBox" id="isActive" name="isActive"
								<c:if test="${fn:trim(checkItemForm.isActive) eq 'Y'}" >checked</c:if> />
						</td>
					</tr>
					<tr>
						<th>
							<label class="requiredLabel">*</label>排序：
						</th>
						<td>
							<input class="y-text easyui-numberbox" data-options="required:true" type="text" id="orderby" name="orderby" value="${checkItemForm.orderby}"/>
						</td>
					</tr>
				</table>
				<div class="divContainer" id="infectlist" style="height:180px;">
					<table id="ansdata" fit="true"></table>
				</div>
				<div class="bottomBtn" align="center">
					<input type="submit" id="query" class="bTn directory_save directory_comwidth" value="保  存"/>
				    <input type="reset" id="J-from-reset" class="bTn directory_reset directory_comwidth" value="重  置"/>
				</div>
			</form>
		</div>
	</body>
</html>

<script language="JavaScript">
var lastIndex;
$(document).ready(function(){
	$.ajaxSetup({cache:false});

	$("#code").combobox({
		height:34,
		url:'getJcxEnumList.json',
		valueField:'id',
		textField:'name'
	});
	
	
	$("#infectlist").width($(window).width());
	
	$('#inputtype').combobox({
		height:34,
		data:[{'id':'0','name':'填空'},{'id':'1','name':'单选'},{'id':'2','name':'多选'}],
		required:true,
		editable:false,
		valueField:'id',
		textField:'name',
		onChange : function(newValue, oldValue) {
			if (newValue == '0'){
				$("#infectlist").hide();
			} else {
				$("#infectlist").show();
				$("#ansdata").datagrid("resize");
			}
		}
	});

	if ('${checkItemForm.id}' == ''){
		$('#inputtype').combobox('setValue', 0);
		$("#isActive").attr("checked", true);
		$("#isanswernewline").attr("checked", true);
		$("#isrequired").attr("checked", true);
		$("#infectlist").hide();
	} else {
		if ($('#inputtype').val() == '0'){
			$("#infectlist").hide();
		} else {
			$("#infectlist").show();
		}
	}
	
	var aaaa = $.extend($.fn.datagrid.defaults.editors, {
		text: {
			init: function(container, options){
				var input = $('<input type="text" class="datagrid-editable-input">').appendTo(container);
				return input;
			},
			getValue: function(target){
				return $(target).val();
			},
			setValue: function(target, value){
				$(target).val(value);
			},
			resize: function(target, width){
				var input = $(target);
				if ($.boxModel == true){
					input.width(width - (input.outerWidth() - input.width()));
				} else {
					input.width(width);
				}
			}
		}
	});
	$("#ansdata").datagrid( {
		singleSelect:true,
		rownumbers : true,
		fitColumns : true,
		title : '检查项答案',
		url : 'queryCheckItemAns.json?itemId=' + $("#id").val(),
		onLoadSuccess : function(data) {
			$(this).datagrid('doCellTip', {
				'max-width' : '300px',
				'delay' : 500
			});
		},
		toolbar:[{
			text:'添加',
			iconCls:'icon-add',
			handler:function(){
				$('#ansdata').datagrid('endEdit', lastIndex);
				$('#ansdata').datagrid('appendRow',{
					id:'',
					answer:'',
					answerdesc:'',
					isnormal:'Y',
					isrequired:'',
					describe:'',
					orderby:'',
					isActive:'Y'
				});
				lastIndex = $('#ansdata').datagrid('getRows').length-1;
				$('#ansdata').datagrid('selectRow', lastIndex);
				$('#ansdata').datagrid('beginEdit', lastIndex);
			}
		},{
			text:'删除',
			iconCls:'icon-remove',
				handler:function(){
					var row = $('#ansdata').datagrid('getSelected');
					if (row){
						var index = $('#ansdata').datagrid('getRowIndex', row);
						$('#ansdata').datagrid('deleteRow', index);
					}
			}
		},{
			text:'删除全部',
			iconCls:'icon-remove',
			handler:function(){
				var ll = $('#ansdata').datagrid('getRows').length - 1;
				for(i = ll; i >= 0;i--){
					$('#ansdata').datagrid('deleteRow', i);
				}
			},
		} ],
		columns : [ [ 
			{field : 'id', hidden : true},
			{field : 'answer', editor:{type : 'validatebox', options:{required:true}}, title : '答案', align : 'center', width : 100},
			{field : 'answerdesc', editor:{type : 'validatebox', options:{required:true}}, title : '答案描述', align : 'center', width : 100},
			{field : 'isnormal', 
				editor: {
					type:'combobox',
					options:{
						required:true,
						editable:false,
						valueField:'id',
						textField:'name',
						data:[{'id':'Y','name':'正常'},{'id':'N','name':'异常'}]
					}
				}, title : '正常异常', align : 'center', width : 100},
			{field : 'describe', editor:{type : 'validatebox', options:{required:true}}, title : '描述', align : 'center', width : 100},
			{field : 'orderby', editor:{type:'numberbox', options:{required:true}}, title : '排序', align : 'center', width : 50},
			{field : 'isActive',
				editor: {
					type:'checkbox',
					options:{
						on : 'Y',
						off : 'N'
					}
				},
				title : '是否可用', align : 'center', width : 60},
		] ],
		onClickRow:function(rowIndex){
			if (lastIndex != rowIndex){
				$('#ansdata').datagrid('endEdit', lastIndex);
				$('#ansdata').datagrid('beginEdit', rowIndex);
			}
			lastIndex = rowIndex;
		}
	});
	
	$("#queryForm").validate( {
		errorClass : "error",
		submitHandler : function(form) {
			if($("#queryForm").form("validate")){
				var dataG = $('#ansdata');
				dataG.datagrid('acceptChanges');
				var validData = dataG.datagrid('getData');
				
				if ($('#inputtype').combobox("getValue") != "0" && validData.rows.length == 0){
					alert("当前为选择题但未添加答案，请添加。");
					return;
				}
				// 删除空行
				for(i = validData.rows.length - 1; i >= 0; i--){
					var row = validData.rows[i];
					if (row.answer == "" || row.answerdesc == "" || row.isnormal == "" || row.describe == "" || row.orderby == "" || row.isActive == ""){
						alert("存在未填写完整的行，请填写完整或删除行。");
						return;
					}
				}
				var gridData = dataG.datagrid('getData');
				var data = JSON.stringify(gridData);
				$('#data').val(data);
				
				$(form).ajaxSubmit( {
					type : "post",
					url:"saveCheckListItem.json",
					success : function(data) {
						if (data.state) {
							var tishi=layer.alert(data.msg,{
						     	title:'信息提示',
						        icon:1,
						        shadeClose:true,
						     },
						     function(){
						    	parent.closeLayer();
						        layer.close(tishi);
						     }
						     );
						} else {
							var tishi=layer.alert(data.msg,{
						     	title:'信息提示',
						        icon:1,
						        shadeClose:true,
						     },
						     function(){
						        layer.close(tishi);
						     }
						     );
						}
					}
				});
			}
		}
	});
});

$("#J-from-reset").click(function(){
	$("#queryForm").form("reset");
})
</script>