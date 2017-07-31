<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/default/easyui.css">
        <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<style type="text/css">
		label{ margin-left:5px; margin-right:20px;}
		</style>
	</head>

	<body>
		<h1 class="h1_1">${title}</h1>
		<div style="width:95%; margin:0px auto 15px; padding:10px 0px 10px; border:1px solid #acacac;">
			<form id="myform" name="myform" method="post" action="roleSave.json">
				<table  class="dataTable" width="100%" border="0"  cellpadding="0" cellspacing="0">
					<input type="hidden" value="${serverForm.id}" id="id" name="id">
					<div id="neirong" style="padding: 5px;">
						<tr>
							<th width="130">
								<font color="red"> * </font>角色名称
							</th>
							<td colspan="3">
								<input type="text" class="y-text easyui-validatebox" data-options="required:true"  style="width:200px" id="name" name="name" value="${roleForm.name}" />
							</td>
						</tr>
						<tr>
							<th width="130">
								角色描述
							</th>
							<td colspan="3">
							<textarea id="note" style="width:200px;" name="note" class="y-textarea">${roleForm.note}</textarea>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red"> * </font>排序
							</th>
							<td colspan="3">
								<input class="y-text easyui-numberbox" style="width:200px;" data-options="required:true" type="text" id="orderby" name="orderby" value="${roleForm.orderby}"/>
							</td>
						</tr>
						<tr>
							<th>
								是否管理角色
							</th>
							<td>
								<input type="checkBox" id="isSys" name="isSys"
								<c:if test="${fn:trim(roleForm.isSys) eq 'Y'}" >checked</c:if> />
							</td>
							<th>
								是否有效
							</th>
							<td>
								<input type="checkBox" id="isActive" name="isActive"
									<c:if test="${fn:trim(roleForm.isActive) eq 'Y'}" >checked</c:if> />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input id="pfbutton" class="queryBlue" type="submit" value="提交"/>
								<input type="reset" id="J-from-reset" class="queryOrange" value="重　置"/>
							</td>
						</tr>
					</div>
				</table>
			</form>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
$(document).ready(function(){
	$.ajaxSetup({cache:false});
	if ("${roleForm.id}" == ''){
		$("#isActive").attr("checked", true);
	}
	
	$("#J-from-reset").click(function(){
	   $("#myform").reset();
	});
   	//表单校验
    $("#myform").validate({
		errorClass: "error",
		submitHandler:function(form){
			if ($("#myform").form("validate")){
				$(form).ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"roleSave.json",
					success: function(data){
						if(data.state){
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
						}else{
							$.messager.alert('保存角色:',data.msg);
						}
					}
				});
			}
		}
	});
});
</SCRIPT>