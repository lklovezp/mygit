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
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox2.css">
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
				<form id="queryForm" method="post" action="saveSubTemplate.json">
					<input type="hidden" id="id" name="id" value="${templateForm.id}"/>
					<input type="hidden" id="pid" name="pid" value="${templateForm.pid}"/>
					<table class="queryTable" width="100%" border="1px" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
						<tr>
							<th>
								<label class="requiredLabel">*</label>监察模板名称：
							</th>
							<td>
								<input class="y-text easyui-validatebox" data-options="required:true" type="text" id="name" name="name" value="${templateForm.name}"/>
							</td>
						</tr>
						<tr>
							<th>
								模板名称是否显示：
							</th>
							<td>
								<input type="checkbox" id="namevisiable" name="namevisiable" <c:if test="${fn:trim(templateForm.namevisiable) eq 'Y'}" >checked</c:if>/>
							</td>
						</tr>
						<tr>
							<th>
								描述：
							</th>
							<td>
								<textarea class="y-text" id="describe" name="describe">${templateForm.describe}</textarea>
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
				                <input type="button" id="del" class="bTn directory_reset directory_comwidth" value="删除"/>
				                <input type="button" id="addSub" class="bTn directory_save directory_comwidth" value="添加子模板"/>
				                <input type="button" id="newCheckItmes" class="bTn directory_reset directory_comwidth" value="添加检查项"/>
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
	if ("${templateForm.operate}" == "4"){
		$("#newCheckItmes").hide();
	}
	if ("${templateForm.id}" == ""){
		$("#del").hide();
		$("#addSub").hide();
		$("#newCheckItmes").hide();
		$("#namevisiable").attr("checked", true);
	}
	$.ajaxSetup({cache:false});
	//表单校验
	$("#queryForm").validate({
		errorClass: "error",
		submitHandler:function(form){
			if ($("#queryForm").form("validate")){
				$(form).ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"saveSubTemplate.json",
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
// 删除版本
$("#del").click(function(){
	var index=layer.confirm('确定要删除当前子模板吗？', {
     	icon: 3, 
        title:'监察模板管理'
     }, function(index){
    	 $.ajax( {
				url : "removeSubTemplate.json?id="+$("#id").val(),
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

//添加子模板
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

// 添加检查项
$("#newCheckItmes").click(function() {
	var href='editCheckListItem.htm?templateid=${templateForm.id}&';
	parent.layerIframe(2,href,"新建检查项",600,420);
	//All.ShowModalWin('editCheckListItem.htm?templateid=${templateForm.id}&', '新建检查项', 600, 460);
	//parent.location.reload();
});

$("#J-from-reset").click(function(){
	$("#queryForm").form("reset");
})
</script>