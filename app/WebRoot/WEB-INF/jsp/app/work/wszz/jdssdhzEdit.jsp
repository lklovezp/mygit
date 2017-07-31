<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>行政处罚决定书送达回执</title>
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
	<div class="headTitle" style="font-size:16px; padding-top:20px;">行政处罚决定书送达回执</div>
	<form id="queryForm" action="" method="post">
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="formtable">
			<input type="hidden" value="${jdssdhzForm.id}" id="id" name="id">
			<input type="hidden" value="${jdssdhzForm.taskId}" id="taskId" name="taskId">
			<input type="hidden" value="${jdssdhzForm.taskTypeId}" id="pid" name="taskTypeId">
			<input type="hidden" value="${Androw}" id="Androw" name="Androw"/>
			<tr>
				<td align="center" colspan="2"><input class="i-text easyui-validatebox" type="text" value="${jdssdhzForm.title}" id="title" name="title">
				送达回执</td>
				
			</tr>
			<tr>
				<td align="center" colspan="2">
				师环罚决字【
				<input class="i-text easyui-validatebox" maxlength="98"  style="width:83px;"  type="text" id="timeName" name="timeName" value="2016"/>
				】<input class="i-text easyui-validatebox" maxlength="98"  style="width:183px;"  type="text" id="code" name="code" value=""/>
                                           号</td>
				
			</tr>
			<tr>
			    <th width="130px">送达文书名称及文号：</th>
				<td  colspan="">
				 <textarea class="i-textarea easyui-validatebox" maxlength="140" id="name" name="name" style="width:350px;"></textarea></td>
				</td>
			</tr>
			<tr>
			    <th>当事人名称或姓名：</th>
				<td  colspan="">
				<input class="i-text easyui-validatebox" type="text" style="width:350px;" maxlength="100" value="" id="userName" name="userName">
				</td>
			</tr>
			<tr>
			    <th>送达地点：</th>
				<td  colspan="">
				 <textarea class="i-textarea easyui-validatebox" maxlength="140" id="address" name="address" style="width:350px;"></textarea></td>
				</td>
			</tr>
			<tr>
			    <th>送达方式：</th>
				<td  colspan="">
				 <textarea class="i-textarea easyui-validatebox" maxlength="140" id="type" name="type" style="width:350px;"></textarea></td>
				</td>
			</tr>
			
		</table>
		<div class="t-c" style="margin-top:25px" align="center">
			<span class="btn btn-ok"><input id="save" type="submit" value="保存" />
			</span>
		</div>
	</form>
	<script type="text/javascript">

		$(document).ready(function() {
			

		});
		//提交表单
		$("#queryForm").validate({
			
			errorClass : "error",
			submitHandler : function(form) {
				if ($("#queryForm").form("validate")){
				    // return;
					$(form).ajaxSubmit( {
						type : "post",
						url : "editJdssdhzForm.json",
						success : function(data) {
							alert(data.msg);
							parent.closeLayerIframe();
						}
					});
				}
			}
		});
	</script>
</body>
</html>
