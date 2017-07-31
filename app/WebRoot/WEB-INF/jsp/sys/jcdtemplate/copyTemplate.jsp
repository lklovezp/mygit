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
		<!-- app -->
		<link rel="stylesheet" type="text/css" href="${app}/home.css">
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
	</HEAD>

	<body>
		<div style="font-size: 16px;height:30px;border: 1px;"  align="center" id="divTitle">${title}</div>
		<div class="divContainer">
			<form id="queryForm" action="saveCopyTemplate.json" type="post">
				<input type="hidden" id="oldIndustry" name="oldIndustry" value="${oldIndustry}"/>
				<input type="hidden" id="oldTaskType" name="oldTaskType" value="${oldTaskType}"/>
				<table class="queryTable" width="100%" border="1px" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
						<th>
							<label class="requiredLabel">*</label>监察模板名称：
						</th>
						<td>
							<input class="y-text"  type="text" id="name" name="name" value="${name}"/>
						</td>
					</tr>
					<tr>
						<th>
							<label class="requiredLabel">*</label>行业：
						</th>
						<td>
							<input class="y-text"  type="text" id="industry" name="industry"/>
						</td>
					</tr>
					<tr>
						<th>
							<label class="requiredLabel">*</label>任务类型：
						</th>
						<td>
							<input class="y-text"  type="text" id="taskType" name="taskType"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" id="query" class="bTn directory_save directory_comwidth" value="保  存"/>
				            <input type="reset" id="J-from-reset" class="bTn directory_reset directory_comwidth" value="重  置"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>

<script language="JavaScript">
$(document).ready(function() {
	$.ajaxSetup({cache:false});
	
	$("#name").validatebox({
		required:true,
		editable : false
	});
	$("#industry").combobox({
		height:34,
		type:"post",
		required:true,
		url:'industryList.json',
		panelHeight : 200,
		valueField:'id',
		textField:'name'
	});
	$("#taskType").combotree({
		height:34,
		type:"post",
		required:true,
		url:'taskTypeTreeCombo.json',
		panelHeight : 200,
		valueField:'id',
		textField:'name'
	});
	
	$("#industry").combobox("setValue", '${oldIndustry}');
	var opts = $("#industry").combo("options");
	opts.originalValue = '${oldIndustry}';
	
	$("#taskType").combotree("setValue", '${oldTaskType}');
	var opts = $("#taskType").combotree("options");
	opts.originalValue = '${oldTaskType}';
	
	//表单校验
	$("#queryForm").validate( {
		errorClass : "error",
		submitHandler : function(form) {
			if ($("#queryForm").form("validate")){
				$(form).ajaxSubmit( {
					type:"post",
					dataType:"json",
					url:"saveCopyTemplate.json",
					success : function(data) {
						if (data.state){
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

$("#del").click(function(){
	$.messager.confirm('监察模板管理', '确定要删除当前模板吗？', function(r){
		if (r){
			$.ajax({
				url: "removeTemplate.json?&industry=${templateForm.industry}&tasktype=${templateForm.tasktype}",
				success:function(data){
					if (data.state){
						alert(data.msg);
						parent.location.reload();
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
	});
})

$("#copy").click(function(){
	All.ShowModalWin('copyTemplatePage.htm?industry=${templateForm.industry}&tasktype=${templateForm.tasktype}', '新建版本', 600, 400);
	parent.location.reload();
})

$("#newVersion").click(function(){
	All.ShowModalWin('editTemplateVersion.htm?isCopy=N&industry=${templateForm.industry}&tasktype=${templateForm.tasktype}', '新建版本', 600, 400);
	parent.location.reload();
})
</script>