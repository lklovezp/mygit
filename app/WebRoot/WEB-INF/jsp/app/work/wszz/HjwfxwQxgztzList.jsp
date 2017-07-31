<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>环境违法行为限期改正通知</title>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
	    <script type="text/javascript" src="${layer}/layer.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css" />
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
		<script type="text/javascript" src="${common}/All.js"></script>
		<style type="text/css">

.ta-da{
    overflow:scroll; 
    overflow-x:hidden; 
    height:80px;
    width:95%;
}
.formtable {
    border: 1px solid #d4d4d4;
}
.formtable th {
    color: #666666;
    background-color: #ffffff;
    border: 1px solid #d4d4d4;
}
.formtable td {
	color: #666666;
	border: 1px solid #d4d4d4;
}
.i-text {
    border-color: #d4d4d4 #d4d4d4 #d4d4d4 #d4d4d4;
    border-style: solid;
    border-width: 1px;
}
.i-text:hover {
    border-color: #d4d4d4 #d4d4d4 #d4d4d4 #d4d4d4;
    border-style: solid;
    border-width: 1px;
}
.combo {
    border-color: #d4d4d4;
    background-color: #ffffff;
}
textarea.i-textarea {
    border: 1px solid #d4d4d4;
}
textarea.i-textarea:hover {
    border-color: #d4d4d4 #d4d4d4 #d4d4d4 #d4d4d4;
}
</style>
</head>
<body>
	<div class="headTitle" style="font-size:16px; padding-top:20px;">环境违法行为限期改正通知</div>
	<form id="queryForm" action="" method="post">
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="formtable">
			<input type="hidden" value="${hjwfxwtzForm.id}" id="id" name="id">
			<input type="hidden" value="${hjwfxwtzForm.taskId}" id="taskId" name="taskId">
			<input type="hidden" value="${hjwfxwtzForm.taskTypeId}" id="pid" name="taskTypeId">
			<input type="hidden" value="${Androw}" id="Androw" name="Androw">
			<tr>
				<td align="center" colspan="2">${hjwfxwtzForm.title}</td>
				
			</tr>
			<tr>
				<td align="center" colspan="2">
				<input type="hidden" value="${hjwfxwtzForm.shortName}" id="shortName" name="shortName">
				${hjwfxwtzForm.shortName}环限改字[
				<input class="i-text easyui-validatebox" maxlength="98"  style="width:183px;"  type="text" id="code" name="code" value=""/>
				]号</td>
				
			</tr>
			<tr>
				<td align="left" colspan="2">
				<input type="hidden" value="${hjwfxwtzForm.corpName}" id="corpName" name="corpName">
				${hjwfxwtzForm.corpName}：
				</td>
				
			</tr>
			<tr>
				<td align="center" colspan="2">
				<input type="hidden" value="${hjwfxwtzForm.title}" id="title" name="title">
				经${hjwfxwtzForm.title}
				<input class="easyui-datetimebox" id="dcDate" maxlength="98" data-options="editable:false" style="width:183px;"  type="text" id="dcDate" name="dcDate" value=""/>
				对你公司（单位）进行现场调查。</td>
				
			</tr>
			<tr>
			    <td align="right">经查，你单位（或者个人）存在以下问题：</td>
				<td>
				<textarea class="i-textarea easyui-validatebox" maxlength="2000" id="content" name="content" style="width:450px;" ></textarea></td>
				
			</tr>
			<tr>
			    <td align="right">违反了下列环境保护规定：</td>
				<td>
				 <textarea class="i-textarea easyui-validatebox" maxlength="2000" id="rules" name="rules" style="width:450px;" ></textarea></td>
				
			</tr>
			<tr>
			    <td>限你公司（单位）于
			    <input class="easyui-datetimebox" id="qxDate" maxlength="98" data-options="editable:false" style="width:183px;"  type="text" id="qxDate" name="qxDate" value=""/>前</td>
				<td><textarea class="i-textarea easyui-validatebox" maxlength="2000" id="xqqContent" name="xqqContent" style="width:450px;" ></textarea></td>
				
			</tr>
			<tr>
			    <td align="right">逾期未完成将根据 </td>
				<td><textarea class="i-textarea easyui-validatebox" maxlength="2000" id="ref" name="ref" style="width:450px;" ></textarea></td>
				
			</tr>
		</table>
		<div class="t-c" style="margin-top:25px" align="center">
			<span class="btn btn-ok"><input id="save" type="submit" value="保存" />
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
							url : "editHjwfxwtzForm.json",
							success : function(data) {
									alert(data.msg);
								parent.closeLayerIframe();							//
							}
						});
					}
				}
			});

		});
		//检查时间
    	$("#dcDate").datetimebox({
    		showSeconds:false,
	    	onSelect: function(date){
	    		var str=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()
	    		$('#dcDate').datebox('setValue', str);
	    	},
	    	onChange: function(date){
	    	}
		});
		//期限时间
    	$("#qxDate").datetimebox({
    		showSeconds:false,
	    	onSelect: function(date){
	    		var str=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()
	    		$('#qxDate').datebox('setValue', str);
	    	},
	    	onChange: function(date){
	    	}
		});
	</script>
</body>
</html>
