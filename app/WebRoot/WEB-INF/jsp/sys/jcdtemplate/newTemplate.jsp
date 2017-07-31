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
        <style type="text/css">
        .panel-header {
        	border: 1px solid #d4d4d4;
        	background-color: #cff1ff;
        }
        </style>
	</HEAD>

	<body>
		<div id="main" style="">
			<form id="queryForm" action="saveJcdTemplate.json" type="post">
				<div class="easyui-panel" title="${templateForm.title}" style="margin-bottom:10px;">
					<input type="hidden" id="id" name="id" value="${templateForm.id}"/>
					<input type="hidden" id="operate" name="operate" value="${templateForm.operate}"/>
					<table class="queryTable" width="100%" border="1px" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
						<tr>
							<th>
								<label class="requiredLabel">*</label>监察模板名称：
							</th>
							<td>
								<input class="y-text easyui-validatebox" data-options="required:true" style="width:244px;" type="text" id="name" name="name" value="${templateForm.name}"/>
							</td>
						</tr>
						<tr>
							<th>
								<label class="requiredLabel">*</label>行业：
							</th>
							<td>
								<input class="y-text" style="width:250px;" type="text" id="industry" name="industry"/>
							</td>
						</tr>
						<tr>
							<th>
								<label class="requiredLabel">*</label>任务类型：
							</th>
							<td>
								<input class="y-text" style="width:250px;" type="text" id="tasktype" name="tasktype"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" id="query" class="bTn directory_save directory_comwidth" value="保  存"/>
				                <input type="reset" id="J-from-reset" class="bTn directory_reset directory_comwidth" value="重  置"/>
				                <input type="button" id="copy" class="bTn directory_save directory_comwidth" value="复制模板"/>
				                <input type="button" id="del" class="bTn directory_reset directory_comwidth" value="删除模板"/>
				                <input type="button" id="newVersion" class="bTn directory_save directory_comwidth" value="新建版本"/>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>

<script language="JavaScript">
$(document).ready(function() {
	$.ajaxSetup({cache:false});

	$("#industry").combobox({
		height:34,
		type:"post",
		required:true,
		url:'industryList.json',
		panelHeight : 200,
		valueField:'id',
		textField:'name'
	});
	$("#tasktype").combotree({
		height:34,
		type:"post",
		required:true,
		disabled : true,
		url:'taskTypeTreeCombo.json',
		panelHeight : 200,
		valueField:'id',
		textField:'name'
	});
	
	/** 初始化页面 */
	// 如果为新建模板时
	if ('${templateForm.operate}' == '1'){
		$("#newVersion").hide();
		$("#del").hide();
		$("#copy").hide();
	}
	// 如果为编辑模板时
	else if ('${templateForm.operate}' == '2'){
		$("#industry").combobox("setValue", '${templateForm.industry}');
		var opts = $("#industry").combo("options");
		opts.originalValue = '${templateForm.industry}';
	}
	$("#tasktype").combotree("setValue", '${templateForm.tasktype}');
	var opts = $("#tasktype").combotree("options");
	opts.originalValue = '${templateForm.tasktype}';
	
	//表单校验
	$("#queryForm").validate( {
		errorClass : "error",
		submitHandler : function(form) {
			if ($("#queryForm").form("validate")){
				$(form).ajaxSubmit( {
					type:"post",
					dataType:"json",
					url:"saveTemplate.json",
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
	var index=layer.confirm('确定要删除当前模板吗？', {
     	icon: 3, 
        title:'监察模板管理'
     }, function(index){
    	 $.ajax( {
				url : "removeTemplate.json?&industry=${templateForm.industry}&tasktype=${templateForm.tasktype}",
				success : function(data) {
					if(data.state){
						parent.location.reload();
					}else{
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
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
	
});

$("#copy").click(function(){
	var href='copyTemplatePage.htm?oldIndustry=${templateForm.industry}&oldTaskType=${templateForm.tasktype}';
	parent.layerIframe(2,href,"复制模板",600,420);
	//All.ShowModalWin('copyTemplatePage.htm?oldIndustry=${templateForm.industry}&oldTaskType=${templateForm.tasktype}', '新建版本', 600, 400);
	//parent.location.reload();
})

$("#newVersion").click(function(){
	var href='editTemplateVersion.htm?isCopy=N&industry=${templateForm.industry}&tasktype=${templateForm.tasktype}';
	parent.layerIframe(2,href,"新建版本",600,420);
	//All.ShowModalWin('editTemplateVersion.htm?isCopy=N&industry=${templateForm.industry}&tasktype=${templateForm.tasktype}', '新建版本', 600, 400);
	//parent.location.reload();
})
</script>