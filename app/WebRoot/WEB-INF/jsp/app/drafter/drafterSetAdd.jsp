<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/default/easyui.css">
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css">

		<script type="text/javascript"
			src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
	</HEAD>

	<body>
		<div class="headTitle">${title}</div>
		<div style="padding: 20px;">
			<form id="myform" name="myform" method="post" action="drafterSetSave.json">
				<table width="95%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="formtable">
					<input type="hidden" value="${drafterSetForm.id}" id="id" name="id">
					<div id="neirong" style="padding: 5px;">
						<tr>
							<th>
								<span class="mark">*</span>稿件审核人：
							</th>
							<td>
								<input type="hidden" id="auditid" name="auditid" value="${drafterSetForm.auditid}" />
								<input class="i-text easyui-validatebox" data-options="required:true"  type="text" id="auditName" readonly="readonly"  value="${drafterSetForm.auditName}" />
								<a href="#" id="selectAudit">选择人员</a>
							</td>
						</tr>
						<tr>
							<th>
								<span class="mark">*</span>排序：
							</th>
							<td>
								<input class="i-text easyui-numberbox" data-options="required:true" style="width:100px;" type="text" id="orderby" name="orderby" value="${drafterSetForm.orderby}"/>
							</td>
						</tr>
						<tr>
							<th>
								是否有效：
							</th>
							<td>
								<input type="checkBox" id="isActive" name="isActive"
									<c:if test="${fn:trim(drafterSetForm.isActive) eq 'Y'}" >checked</c:if> />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<span class="btn btn-ok"> <input type="submit" value="提交">
								</span>&nbsp;
								<a href="#" id="J-from-reset">重置</a>
							</td>
						</tr>
					</div>
				</table>
			</form>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
//设置选择审核人
function setUserInfoDjr(id,name) {
	$("#auditid").val(id);
	$("#auditName").val(name);
	jQuery().colorbox.close();
}
$(document).ready(function(){
	//选择审核人
	$("#selectAudit").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?all=false&notShowZj=false&methodname=setUserInfoDjr&multi=false"});
	
	$.ajaxSetup({cache:false});
	if ("${drafterSetForm.id}" == ''){
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
					url:"drafterSetSave.json",
					success: function(data){
						if(data.state){
							alert(data.msg);
							self.close();
						}else{
							$.messager.alert('保存稿件审核人设置:',data.msg);
						}
					}
				});
			}
		}
	});
});
</SCRIPT>