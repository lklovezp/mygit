<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大气环境保护目标分布</title>
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
	<div class="headTitle" style="font-size:16px; padding-top:20px;">大气环境保护目标分布</div>
	<form id="queryForm" action="" method="post">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="formtable">
			<input type="hidden" value="${airProjectForm.id}" id="id" name="id">
			<input type="hidden" value="${airProjectForm.pid}" id="pid" name="pid">
			<tr>
				<th width="180" colspan="2"><em class="mark">*</em>保护目标名称：</th>
				<td><input class="i-text easyui-validatebox" maxlength="98" data-options="required:true" style="width:130px;"  type="text" id="bhmbmc" name="bhmbmc" value="${airProjectForm.bhmbmc}"/></td>
				<th width="180" colspan="2">距企业距离（m）：</th>
				<td><input class="i-text easyui-numberbox" maxlength="9" style="width:130px;"  type="text" id="jl" name="jl" value="${airProjectForm.jl}"/></td>
				
			</tr>
			<tr>
				<th rowspan="2" width="150">规  模：</th>
				<th width="40">类型：</th>
				<td><input class="i-text easyui-validatebox" maxlength="98" type="text" id="lx" name="lx" style="width:130px;" value="${airProjectForm.lx}"/></td>
				<th rowspan="2" width="180">地理坐标：</th>
				<th width="60">中心经度：</th>
				<td><input class="i-text easyui-validatebox" maxlength="24" type="text" id="jd" name="jd" style="width:130px;" value="${airProjectForm.jd}"/></td>
			</tr>
			<tr>
				<th width="60">数量/级别：</th>
				<td><input class="i-text easyui-numberbox" maxlength="9" type="text" id="sljb" name="sljb" style="width:130px;" value="${airProjectForm.sljb}"/></td>
				<th width="80">中心纬度：</th>
				<td><input class="i-text easyui-validatebox" maxlength="24" type="text" id="wd" name="wd" style="width:130px;" value="${airProjectForm.wd}"/></td>
			</tr>
			<tr>
				<th width="180" colspan="2">相对企业位置方位：</th>
				<td><input class="i-text easyui-validatebox" maxlength="98" style="width:130px;"  type="text" id="wzfw" name="wzfw" value="${airProjectForm.wzfw}"/></td>
				
				<th width="180" colspan="2">所属环境功能：</th>
				<td><input class="i-text easyui-validatebox" maxlength="98" style="width:130px;"  type="text" id="sswjgn" name="sswjgn" value="${airProjectForm.sswjgn}"/></td>
				
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
			
			//提交表单
			$("#queryForm").validate({
				
				errorClass : "error",
				submitHandler : function(form) {
					if ($("#queryForm").form("validate")){
						
					    // return;
						$(form).ajaxSubmit( {
							type : "post",
							url : "saveAirProjectForm.json?",
							success : function(data) {
								alert(data.msg);
								//self.close();
								parent.refAirProject();
								
							}
						});
					}
				}
			});

		});
	</script>
</body>
</html>
