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
			<form id="myform" name="myform" method="post" action="checkProportionSave.json">
				<table width="95%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="formtable">
					<input type="hidden" value="${checkProportionForm.id}" id="id" name="id">
					<div id="neirong" style="padding: 5px;">
						<tr>
							<th>
								<span class="mark">*</span>年份：
							</th>
							<td>
								<input class="i-text" data-options="required:true" style="width:100px;" type="text" id="year" name="year"
									value="${checkProportionForm.year}" />
							</td>
						</tr>
						<tr>
							<th>
								<span class="mark">*</span>季度：
							</th>
							<td>
								<input id="quarter" data-options="required:true" name="quarter" class="i-text" style="width:100px;" value="${checkProportionForm.quarter}" />
							</td>
						</tr>
						<tr>
							<th>
								<span class="mark">*</span>抽查比例：
							</th>
							<td>
								<input id="proportion" data-options="required:true" style="width:100px;" maxlength="2" name="proportion" class="i-text easyui-numberbox" value="${checkProportionForm.proportion}" />%
							</td>
						</tr>
						<tr>
							<th>
								是否有效：
							</th>
							<td>
								<input type="checkBox" id="isActive" name="isActive"
									<c:if test="${fn:trim(checkProportionForm.isActive) eq 'Y'}" >checked</c:if> />
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
$(document).ready(function(){
	$.ajaxSetup({cache:false});
	if ("${checkProportionForm.id}" == ''){
		$("#isActive").attr("checked", true);
	}else{
		$("#year").combobox({disabled: true});	
		$("#quarter").combobox({disabled: true});
	}
		var year=new Date().getFullYear(); 
		//年度下拉
		$('#year').combobox({
			data:[{'id':year,'name':year+'年'},{'id':year+1,'name':year+1+'年'},
				  {'id':year+2,'name':year+2+'年'},{'id':year+3,'name':year+3+'年'},
				  {'id':year+4,'name':year+4+'年'}],
			editable:false,
			valueField:'id',
			textField:'name'
		});
		//季度下拉
		$('#quarter').combobox({
			url:'quarterList.json',
			editable:false,
			valueField:'id',
			textField:'name'
		});		
	$("#J-from-reset").click(function(){
	   $("#myform").reset();
	});
   	//表单校验
    $("#myform").validate({
		errorClass: "error",
		submitHandler:function(form){
			if ($("#myform").form("validate")){
				var proportion = $("#proportion").val();
				if(parseInt(proportion)<25){
					alert("季度抽查比例不能小于25%");
					return false;
				}
				$(form).ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"checkProportionSave.json",
					success: function(data){
						if(data.state){
							alert(data.msg);
							self.close();
						}else{
							$.messager.alert('保存季度抽查比例:',data.msg);
						}
					}
				});
			}
		}
	});
});
</SCRIPT>