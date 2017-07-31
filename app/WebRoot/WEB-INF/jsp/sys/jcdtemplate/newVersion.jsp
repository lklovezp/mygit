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
			<div class="easyui-panel" title="${templateForm.title}" style="margin-bottom:10px;">
				<form id="queryForm" method="post" action="saveJcdTemplate.json">
					<input type="hidden" id="id" name="id" value="${templateForm.id}"/>
					<input type="hidden" id="name" name="name" value="${templateForm.name}"/>
					<input type="hidden" id="industry" name="industry" value="${templateForm.industry}"/>
					<input type="hidden" id="tasktype" name="tasktype" value="${templateForm.tasktype}"/>
					<table class="queryTable" width="100%" border="1px" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
						<tr>
							<th>
								<label class="requiredLabel">*</label>监察模板名称：
							</th>
							<td>
								<label>${templateForm.name}</label>
							</td>
						</tr>
						<tr>
							<th>
								<label class="requiredLabel">*</label>版本号：
							</th>
							<td>
								<input class="y-text easyui-numberbox" data-options="required:true" type="text" id="release" name="release" value="${templateForm.release}"/>
							</td>
						</tr>
						<tr>
							<th>
								描述：
							</th>
							<td>
								<textarea class="i-text" id="describe" name="describe" >${templateForm.describe}</textarea>
							</td>
						</tr>
						<tr>
							<th>
								<label class="requiredLabel">*</label>排序：
							</th>
							<td>
								<input class="y-text easyui-numberbox" data-options="required:true" type="text" id="orderby" name="orderby" value="${templateForm.orderby}"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" id="query" class="bTn directory_save directory_comwidth" value="保  存"/>
				                <input type="reset" id="J-from-reset" class="bTn directory_reset directory_comwidth" value="重  置"/>
				                <input type="button" id="copy" class="bTn directory_save directory_comwidth" value="复制版本"/>
				                <input type="button" id="del" class="bTn directory_reset directory_comwidth" value="删除版本"/>
				                <input type="button" id="addSub" class="bTn directory_save directory_comwidth" value="添加子模板"/>
				                <input type="button" id="setDef" class="bTn directory_reset directory_comwidth" value="设置为默认"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>

<script language="JavaScript">
$(document).ready(function() {
	$.ajaxSetup({cache:false});
	// 初始化页面
	if ('${templateForm.id}' == '' || '${templateForm.isCopy}' == 'Y'){
		$("#del").hide();
		$("#copy").hide();
		$("#addSub").hide();
		$("#setDef").hide();
	}
	//表单校验
	$("#queryForm").validate({
		errorClass: "error",
		submitHandler:function(form){
			if ($("#queryForm").form("validate")){
				var url = "saveTemplateVersion.json";
				if ("${templateForm.isCopy}" == "Y"){
					url = "saveCopyVersion.json";
				}
				$(form).ajaxSubmit({
					type : "post",
					dataType : "json",
					url : url,
					success: function(data){
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
							alert(data.msg);
						}
					}
				});
			}
		}
	});
});
// 删除版本
$("#del").click(function(){
	var index=layer.confirm('确定要删除当前版本吗？', {
     	icon: 3, 
        title:'监察模板管理'
     }, function(index){
    	 $.ajax( {
				url : "removeTempVersion.json?id="+$("#id").val(),
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
})
// 复制版本
$("#copy").click(function(){
	if ($("#id").val() != '' && $("#id").val() != null){
		var href="editTemplateVersion.htm?isCopy=Y&id=" + $("#id").val();
		parent.layerIframe(2,href,"复制版本",600,420);
		//All.ShowModalWin("editTemplateVersion.htm?isCopy=Y&id=" + $("#id").val(), "", 600, 400);
		//parent.location.reload();
	} else {
		alert("请选择节点。");
		return;
	}
})
// 添加子模板
$("#addSub").click(function(){
	if ($("#id").val() != '' && $("#id").val() != null){
		var href="addSubTemplate.htm?pid=" + $("#id").val();
		parent.layerIframe(2,href,"添加子模板",600,420);
		//All.ShowModalWin("addSubTemplate.htm?pid=" + $("#id").val(), "", 600, 400);
		//parent.location.reload();
	} else {
		alert("请选择节点。");
		return;
	}
})
// 设为默认版本
$("#setDef").click(function(){
	var index=layer.confirm('确定要设置为默认版本吗？', {
     	icon: 3, 
        title:'监察模板管理'
     }, function(index){
    	 $.ajax( {
				url : "saveDefaultVersion.json?id=${templateForm.id}",
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
})

$("#J-from-reset").click(function(){
	$("#queryForm").form("reset");
})
</script>