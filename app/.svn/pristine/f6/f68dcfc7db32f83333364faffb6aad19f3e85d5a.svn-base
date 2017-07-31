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
	<div class="headTitle" style="font-size:16px; padding-top:20px;">企业环境应急处置及救援资源</div>
	<form id="queryForm" action="" method="post">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="formtable">
			<input type="hidden" value="${qyhjyjczjjyzyForm.id}" id="id" name="id">
			<input type="hidden" value="${qyhjyjczjjyzyForm.pid}" id="pid" name="pid">
			<tr>
				<th><em class="mark">*</em>应急处置设施（备）：</th>
				<td colspan="2"><input class="i-text" type="text" id="yjczType"	name="type" value="${qyhjyjczjjyzyForm.type}"/></td>
				<th width="170">物资名称：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="98" type="text" id="wzmc" name="wzmc" value="${qyhjyjczjjyzyForm.wzmc}"/></td>
			</tr>
			
			
			<tr>
				<th rowspan="2" width="150">数量（个、台、套或Kg）：</th>
				<th width="90">现有数量：</th>
				<td><input class="i-text easyui-numberbox" maxlength="9" type="text" id="xysl" name="xysl" style="width:70px;" value="${qyhjyjczjjyzyForm.xysl}"/></td>
				<th rowspan="2" width="150">外部供应单位联系方式：</th>
				<th width="90">姓名：</th>
				<td><input class="i-text easyui-validatebox" maxlength="24" type="text" id="wbxm" name="wbxm" style="width:70px;" value="${qyhjyjczjjyzyForm.wbxm}"/></td>
			</tr>
			<tr>
				<th width="90">所需数量：</th>
				<td><input class="i-text easyui-numberbox" maxlength="9" type="text" id="sxsl" name="sxsl" style="width:70px;" value="${qyhjyjczjjyzyForm.sxsl}"/></td>
				<th width="90">电话：</th>
				<td><input class="i-text easyui-validatebox" maxlength="24" type="text" id="wbdh" name="wbdh" style="width:70px;" value="${qyhjyjczjjyzyForm.wbdh}"/></td>
			</tr>
			<tr>
				<th>外部供应单位的名称：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="98" type="text" id="wbName" name="wbName" value="${qyhjyjczjjyzyForm.wbName}"/></td>
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
			$("#yjczType").combobox({
				required:true,
				url : 'yjczType.json',
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
							url : "saveQyhjyjczjjyzyForm.json?",
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
