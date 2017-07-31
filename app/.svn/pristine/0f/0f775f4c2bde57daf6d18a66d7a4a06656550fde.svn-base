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
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
	</HEAD>

	<body>
		<div style="font-size: 16px;height:30px;border: 1px;"  align="center" id="divTitle">${title}</div>
		<div id="divContainer" style=" ">
			<form id="queryForm" method="post" action="saveIllegalType.json">
				<input type="hidden" value="${illegalTypeForm.id}" id="id" name="id">
				<input id="pid" name="pid" type="hidden" value="${illegalTypeForm.pid}" />
				<table class="queryTable" width="100%" border="1px" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
						<th width="100">
							<label class="requiredLabel">*</label>
							违法类型：
						</th>
						<td>
							<textarea style="width: 400px; height: 50px;" id="name" name="name" data-options="required:true" class="i-text easyui-validatebox">${illegalTypeForm.name}</textarea>
						</td>
					</tr>
					<tr>
						<th width="100">
							描述：
						</th>
						<td>
							<textarea style="width: 400px; height: 50px;" id="describe" name="describe" class="i-text">${illegalTypeForm.describe}</textarea>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="submit" id="save" class="bTn directory_save directory_comwidth" value="保  存"/>
			                <input type="reset" id="J-from-reset" class="bTn directory_reset directory_comwidth" value="重  置"/>
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

	if('${illegalTypeForm.id}' == ''){
		$("#addSubType").hide();
		$("#del").hide();
	} else {
		$("#divTitle").hide();
	}
	
	$("#pid").combotree({
		height:34,
		type:"post",
		url:'illegalTypeList.json?ids=' + $("#id").val(),
		valueField:'id',
		textField:'name'
	});
	
	//表单校验
	$("#queryForm").validate({
		errorClass: "error",
		submitHandler:function(form){
			if ($("#queryForm").form("validate")){
				$(form).ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"saveIllegalType.json",
					success: function(data){
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
					}
				});
			}
		}
	});

	$("#del").click(function(){
		var index=layer.confirm('确定要删除当前违法类型吗？', {
	     	icon: 3, 
	        title:'违法类型管理'
	     }, function(index){
	    	 $.ajax( {
					url : "removeIllegalType.json?id="+$("#id").val(),
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
	
	$("#addSubType").click(function(){
		if ($("#id").val() != '' && $("#id").val() != null){
			parent.layerIframe(2,"editIllegalType.htm?pid=" + $("#id").val(),"添加子类型",600,420);
			//parent.location.reload();
		} else {
			alert("请选择节点。");
			return;
		}
	})
	
	$("#J-from-reset").click(function(){
		$("#queryForm").form("reset");
	})
});
</script>