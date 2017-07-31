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
		<script type="text/javascript" src="${common}/All.js"></script>
		<!-- 任务管理 css-->
	    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
	    <!--系统管理 css-->
	    <link rel="stylesheet" href="${app}/css/management.css" type="text/css">
	    <link rel="stylesheet" href="${app}/css/taskDispatch.css" type="text/css">
		<!-- app -->
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
	</HEAD>
	<body>
		<div style="font-size: 16px;height:30px;border: 1px;"  align="center" id="divTitle">${title}</div>
		<div id="divContainer" style="">
			<form id="myform" method="post" action="saveTaskType.json">
				<input type="hidden" value="${taskTypeForm.id}" id="id" name="id">
				<table class="queryTable" width="100%" border="1px" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
						<th width="150">
							<label class="requiredLabel">*</label>任务类型名称：
						</th>
						<td>
							<textarea style="width:400px;height:50px;" data-options="required:true" class="i-text easyui-validatebox" id="name" name="name">${taskTypeForm.name}</textarea>
						</td>
					</tr>
					<tr>
						<th width="100">
							<label class="requiredLabel">*</label>编号：
						</th>
						<td>
							<input class="y-text easyui-numberbox" maxlength="2" data-options="required:true" id="code" name="code" value="${taskTypeForm.code}"/>
						</td>
					</tr>
					<tr>
						<th width="100">
							所属父类型：
						</th>
						<td>
							<input class="y-text" style="width:402px" type="text" id="pid" name="pid" value="${taskTypeForm.code}"/>
						</td>
					</tr>
					<tr>
						<th width="100">
							执法对象类型：
						</th>
						<td>
							<input class="y-text" style="width:402px" type="text" id="lawobjtype" name="lawobjtype"/>
						</td>
					</tr>
					<tr>
						<th width="100">
							违法类型：
						</th>
						<td>
							<input class="y-text" type="text" style="width:402px" id="illegaltype" name="illegaltype"/>
						</td>
					</tr>
					<tr>
						<th width="100">
							描述：
						</th>
						<td>
							<textarea style="width: 400px; height: 50px;" id="describe" name="describe" class="i-text">${taskTypeForm.describe}</textarea>
						</td>
					</tr>
					<tr>
						<th width="100">
							<label class="requiredLabel">*</label>排序：
						</th>
						<td>
							<input class="y-text easyui-numberbox" data-options="required:true" type="text" id="orderby" name="orderby" value="${taskTypeForm.orderby}"/>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="submit" id="save" class="bTn directory_save directory_comwidth" value="保  存"/>
			                <input type="reset" id="J-from-reset" class="bTn directory_reset directory_comwidth" value="重  置"/>
			                <input type="button" id="addSubType" class="bTn directory_save directory_comwidth" value="添加子类型"/>
			                <input type="reset" id="del" class="bTn directory_reset directory_comwidth" value="删  除"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>

<script language="JavaScript">
$(document).ready(function(){
	$.ajaxSetup({cache:false});

	$("#pid").combotree({
		height:34,
		type:"post",
		url:'queryTaskTypeIdName.json?id=${taskTypeForm.id}',
		valueField:'id',
		textField:'text'
	});

	$('#lawobjtype').combotree({
		height:34,
		url:'lawtypeTree.json',
		multiple: true
	});
	
	$("#illegaltype").combotree({
		height:34,
		type:"post",
		multiple: true,
		url:'illegalTypeList.json',
		cascadeCheck : false,
		valueField:'id',
		textField:'name'
	});

	if ('${taskTypeForm.id}' != ''){
		$("#divTitle").hide();
		$("#pid").combotree("setValue", '${taskTypeForm.pid}');
		if ('${taskTypeForm.lawobjtype}' != ''){
			$("#lawobjtype").combotree("setValues", '${taskTypeForm.lawobjtype}'.split(","));
			var opts = $("#lawobjtype").combotree("options");
			opts.originalValue = '${taskTypeForm.lawobjtype}'.split(",");
		}
		if ('${taskTypeForm.illegaltype}' != ''){
			$("#illegaltype").combotree("setValues", '${taskTypeForm.illegaltype}'.split(","));
			var opts = $("#illegaltype").combotree("options");
			opts.originalValue = '${taskTypeForm.illegaltype}'.split(",");
		}
	} else {
		$("#addSubType").hide();
	}
	//表单校验
	$("#myform").validate({
		errorClass: "error",
		submitHandler:function(form){
			if ($("#myform").form("validate")){
				$(form).ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"saveTaskType.json",
					success: function(data){
						var tishi=layer.alert("保存成功！",{
					     	title:'信息提示',
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

	$("#addSubType").click(function(){
		if ($("#id").val() != '' && $("#id").val() != null){
			var code = $("#code").val();
			parent.layerIframe(2,"addSubTaskType.htm?pid=" + $("#id").val(),"添加子类型",600,420);
			//All.ShowModalWin("addSubTaskType.htm?pid=" + $("#id").val(), "", 600, 400);
			//parent.location.reload();
		} else {
			alert("请选择节点。");
			return;
		}
	});

	$("#del").click(function(){
		var index=layer.confirm('确定要删除当前违法类型吗？', {
	     	icon: 3, 
	        title:'违法类型'
	     }, function(index){
	    	 $.ajax( {
					url : "removeTaskType.json?id="+$("#id").val(),
					success : function(data) {
						if(data.state){
							parent.location.reload();
						}else{
							$.messager.alert('提示:',data.msg);
						}
					}
				});
	        layer.close(index);
	     },function(index){
	        layer.close(index);
	     }
	    );
	});
	
	$("#J-from-reset").click(function(){
		$("#myform").form("reset");
	})
});
</script>