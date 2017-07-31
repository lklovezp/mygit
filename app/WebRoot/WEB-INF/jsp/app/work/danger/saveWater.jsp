<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业环境应急处置及救援资源</title>
<script type="text/javascript" src="${app}/js.js"></script>
<script type="text/javascript" src="${app}/data.js"></script>
<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css"	type="text/css" />
<script type="text/javascript" src="${poshytip}/jquery.poshytip.min.js"></script>
<link rel="stylesheet" href="${colorbox}/colorbox.css" type="text/css">
<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
<link href="${app}/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${common}/All.js"></script>
</head>
<body>
	<div class="headTitle" style="font-size:16px; padding-top:20px;">水环境基本状况</div>
	<form id="queryForm" action="" method="post">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="formtable">
			<input type="hidden" value="${waterForm.id}" id="id" name="id">
			<input type="hidden" value="${waterForm.pid}" id="pid" name="pid">
			<tr>
				<th width="180" colspan="2"><em class="mark">*</em>废水主要排水去向类型代码：</th>
				<td colspan="3"><input class="i-text easyui-validatebox" maxlength="98" data-options="required:true" style="width:183px;"  type="text" id="qxlxdm" name="qxlxdm" value="${waterForm.qxlxdm}"/></td>
				
			</tr>
			
			
			<tr>
				<th rowspan="2" width="150">废水受纳水体：</th>
				<th width="40">名称：</th>
				<td><input class="i-text easyui-validatebox" maxlength="98" type="text" id="stmc" name="stmc" style="width:130px;" value="${waterForm.stmc}"/></td>
				<th rowspan="2" width="180">清净下水受纳水体：</th>
				<th width="60">名称：</th>
				<td><input class="i-text easyui-validatebox" maxlength="98" type="text" id="qjstmc" name="qjstmc" style="width:130px;" value="${waterForm.qjstmc}"/></td>
			</tr>
			<tr>
				<th width="60">代码：</th>
				<td><input class="i-text easyui-validatebox" maxlength="98" type="text" id="stdm" name="stdm" style="width:130px;" value="${waterForm.stdm}"/></td>
				<th width="60">代码：</th>
				<td><input class="i-text easyui-validatebox" maxlength="98" type="text" id="qjstdm" name="qjstdm" style="width:130px;" value="${waterForm.qjstdm}"/></td>
			</tr>
			<tr>
				<th rowspan="2" width="150">废水受纳水体功能类别：</th>
				<th width="60">地表水：</th>
				<td><input class="i-text" type="text" style="width:130px;" id="fsDbsType"	name="dbs" value="${waterForm.dbs}"/></td>
				<th rowspan="2" width="180">清净下水受纳水体功能类别：</th>
				<th width="60">地表水：</th>
				<td><input class="i-text" type="text" style="width:130px;"id="qsDbsType"	name="qjdbs" value="${waterForm.qjdbs}"/></td>
			</tr>
			<tr>
				<th width="60">海水：</th>
				<td><input class="i-text" type="text" style="width:130px;" id="fsHsType"	name="hs" value="${waterForm.hs}"/></td>
				<th width="60">海水：</th>
				<td><input class="i-text" type="text" style="width:130px;" id="qsHsType"	name="qjhs" value="${waterForm.qjhs}"/></td>
			</tr>
			
		</table>
		<div class="t-c" style="margin-top:25px">
			<span class="y-btn"><input id="save" type="submit" value="保存" />
			</span> <span class="y-btn"><input id="reset" type="reset" value="重置" />
			</span>
		</div>
	</form>
	<script type="text/javascript">

		$(document).ready(function() {
			//alert("pid==="+$('#pid').val());
			//类型下拉框
			$("#qsDbsType").combobox({
				//required:true,
				url : 'qsDbsType.json',
				method : 'get',
				valueField : 'id',
				textField : 'name'
			});
			//类型下拉框
			$("#qsHsType").combobox({
				//required:true,
				url : 'qsHsType.json',
				method : 'get',
				valueField : 'id',
				textField : 'name'
			});
			//类型下拉框
			$("#fsDbsType").combobox({
				//required:true,
				url : 'fsDbsType.json',
				method : 'get',
				valueField : 'id',
				textField : 'name'
			});
			//类型下拉框
			$("#fsHsType").combobox({
				//required:true,
				url : 'fsHsType.json',
				method : 'get',
				valueField : 'id',
				textField : 'name'
			});
			// alert("ceshi");
			//提交表单
			$("#queryForm").validate({
				
				errorClass : "error",
				submitHandler : function(form) {
					if ($("#queryForm").form("validate")){
						
					    // return;
						$(form).ajaxSubmit( {
							type : "post",
							url : "saveWaterForm.json?",
							success : function(data) {
								alert(data.msg);
								//self.close();
								parent.refEdit();
								
							}
						});
					}
				}
			});

		});
	</script>
</body>
</html>
