<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业化学物质产品_一主要产品</title>
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
	<div class="headTitle" style="font-size:16px; padding-top:20px;">主要产品</div>
	<form id="queryForm" action="" method="post">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="formtable">
			<input type="hidden" value="${qyhxwzqkzycpForm.id}" id="id" name="id">
			<input type="hidden" value="${qyhxwzqkzycpForm.pid}" id="pid" name="pid">
			<tr>
				<th width="170"><em class="mark">*</em>商品名：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="98" data-options="required:true" type="text" id="sp_name"  name="spm" value="${qyhxwzqkzycpForm.spm}" /></td>
				<th width="170">化学名：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="98" type="text" id="hx_name" name="hxm" value="${qyhxwzqkzycpForm.hxm}"/></td>
			</tr>
			<tr>
				<th>CAS号：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="98" type="text" id="cas" name="cas" value="${qyhxwzqkzycpForm.cas}"/></td>
				<th>物理状态：</th>
				<td colspan="2"><input class="i-text"  type="text" id="wl_state" name="wlzt" value="${qyhxwzqkzycpForm.wlzt}"/></td>
			</tr>
			<tr>
				<th>物质分类（填写代码）：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="98" type="text" id="type" name="wzfl" value="${qyhxwzqkzycpForm.wzfl}"/></td>
				<th>设计生产能力（吨/年）：</th>
				<td colspan="2"><input class="i-text easyui-numberbox" maxlength="9" type="text" id="sjscnl" name="sjscnl" value="${qyhxwzqkzycpForm.sjscnl}"/></td>
			</tr>
			<tr>
				<th>实际产量（吨）：</th>
				<td colspan="2"><input class="i-text easyui-numberbox" maxlength="9" type="text" id="sjcl"	name="sjcl" value="${qyhxwzqkzycpForm.sjcl}"/></td>
				<th rowspan="2">用途：</th>
				<td colspan="2" rowspan="2"><textarea class="i-textarea easyui-validatebox" maxlength="248" id="yt" name="yt" style="width:170px;" >${qyhxwzqkzycpForm.yt}</textarea></td>
			</tr>
			<tr>
				<th>运输方式：</th>
				<td colspan="2"><input class="i-text" type="text" id="ysfs"	name="ysfs" value="${qyhxwzqkzycpForm.ysfs}"/></td>
			</tr>
			<tr>
				<th rowspan="2" width="150">销售量（吨）：</th>
				<th width="90">国内：</th>
				<td><input class="i-text easyui-numberbox" maxlength="9" type="text" id="xs_gn" name="gnxsl" style="width:70px;" value="${qyhxwzqkzycpForm.gnxsl}"/></td>
				<th rowspan="2" width="150">贮存量（吨）：</th>
				<th width="90">生产区：</th>
				<td><input class="i-text easyui-numberbox" maxlength="9" type="text" id="zc_sc" name="scqzcl" style="width:70px;" value="${qyhxwzqkzycpForm.scqzcl}"/></td>
			</tr>
			<tr>
				<th width="90">出口：</th>
				<td><input class="i-text easyui-numberbox" maxlength="9" type="text" id="xs_gw" name="ckxsl" style="width:70px;" value="${qyhxwzqkzycpForm.ckxsl}"/></td>
				<th width="90">贮存区：</th>
				<td><input class="i-text easyui-numberbox" maxlength="9" type="text" id="zc_zc" name="zcqzcl" style="width:70px;" value="${qyhxwzqkzycpForm.zcqzcl}"/></td>
			</tr>
			<tr>
				<th rowspan="2" width="150">贮存方式（代码）：</th>
				<th width="90">生产区：</th>
				<td><input class="i-text easyui-validatebox" maxlength="98" type="text" id="zcc_sc" name="scqzcfs" style="width:70px;" value="${qyhxwzqkzycpForm.scqzcfs}"/></td>
				<th>设备状态：</th>
				<td colspan="2"><input class="i-text" type="text" id="sb_state" name="sbzt" value="${qyhxwzqkzycpForm.sbzt}"/></td>
			</tr>
			<tr>
				<th>贮存区：</th>
				<td><input class="i-text easyui-validatebox" maxlength="98" type="text" id="zcc_zc" name="zcqzcfs" style="width:70px;" value="${qyhxwzqkzycpForm.zcqzcfs}"/></td>
				<th>生产方式：</th>
				<td colspan="2"><input class="i-text" type="text" id="sc_type" name="scfs" value="${qyhxwzqkzycpForm.scfs}"/></td>
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
			//物理状态 下拉框
			$("#wl_state").combobox({
				
				url : 'wlstate.json',
				//method: 'get',
				valueField : 'id',
				textField : 'name'
			});
			//运输方式  下拉框
			$("#ysfs").combobox({
				
				url : 'ysfs.json',
				method : 'get',
				valueField : 'id',
				textField : 'name'
			});
			//设备状态  下拉框
			$("#sb_state").combobox({
				
				url : 'sbState.json',
				method : 'get',
				valueField : 'id',
				textField : 'name'
			});
			//生产方式 下拉框
			$("#sc_type").combobox({
				
				url : 'scType.json',
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
							url : "saveQyhxwzqkzycpForm.json?",
							success : function(data) {
								alert(data.msg);
								//self.close();
								parent.ref();
								
							}
						});
					}
				}
			});

		});
	</script>
</body>
</html>
