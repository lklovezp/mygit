<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业化学物质产品_一副产品</title>
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
	<div class="headTitle" style="font-size:16px; padding-top:20px;">副产品</div>
	<form id="queryForm" action="" method="post">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="formtable">
			<input type="hidden" value="${qyhxwzqkfcpForm.id}" id="id" name="id">
			<input type="hidden" value="${qyhxwzqkfcpForm.pid}" id="pid" name="pid">
			<tr>
				<th width="170"><em class="mark">*</em>商品名：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="98" data-options="required:true"  type="text" id="sp_name"  name="spm" value="${qyhxwzqkfcpForm.spm}" /></td>
				<th width="170">化学名：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="98" type="text" id="hx_name" name="hxm" value="${qyhxwzqkfcpForm.hxm}"/></td>
			</tr>
			<tr>
				<th>CAS号：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="98" type="text" id="cas" name="cas" value="${qyhxwzqkfcpForm.cas}"/></td>
				<th>物理状态：</th>
				<td colspan="2"><input class="i-text" type="text" id="wl_state" name="wlzt" value="${qyhxwzqkfcpForm.wlzt}"/></td>
			</tr>
			<tr>
				<th>实际产量（立方）：</th>
				<td colspan="2"><input class="i-text easyui-numberbox" maxlength="9" type="text" id="wzfl"	name="wzfl" value="${qyhxwzqkfcpForm.wzfl}"/></td>
				<th rowspan="2">用途：</th>
				<td colspan="2" rowspan="2"><textarea class="i-textarea easyui-validatebox" maxlength="248" id="yt" name="yt" style="width:170px;" >${qyhxwzqkfcpForm.yt}</textarea></td>
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
						
			//提交表单
			$("#queryForm").validate({
				
				errorClass : "error",
				submitHandler : function(form) {
					if ($("#queryForm").form("validate")){
						
					    // return;
						$(form).ajaxSubmit( {
							type : "post",
							url : "saveQyhxwzqkfcpForm.json?",
							success : function(data) {
								alert(data.msg);
								//self.close();
								parent.refFcp();
								
							}
						});
					}
				}
			});

		});
	</script>
</body>
</html>
