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
		<link rel="stylesheet" type="text/css"
			href="${colorbox}/colorbox2.css">
		<!-- app -->
		<link rel="stylesheet" type="text/css" href="${app}/home.css">
		<script type="text/javascript" src="${common}/All.js"></script>
	</HEAD>

	<body style="background-color:rgb(224, 236, 255);">
		<div class="divContainer" style="">
			<form id="queryForm" action="uploadSingleFile" method="post" enctype="multipart/form-data">
				<input type="hidden" id="pid" name="pid" value="${fileForm.pid}">
				<input type="hidden" id="fileType" name="fileType" value="${fileForm.fileType}">
				<input type="hidden" id="path" name="path" value="${fileForm.path}">
				<table width="100%" height="120">
					<tr align="center">
						<td height="70">
							<input class="i-text easyui-validatebox" data-options="required:true" style="width:250px;margin-top:30px;" type="file" id="file" name="file"/>
						</td>
					</tr>
					<tr align="left">
						<td height="20">
							<label id="limit" style="color:red;margin-left:40px;"></label>
						</td>
					</tr>
					<tr align="center">
						<td height="40">
							<span class="btn btn-ok"> <input id="upload" type="submit" value="上传"> </span>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
<script language="JavaScript">
var ext = '${fileForm.extension}';
var exts = ext.split(";");
$(document).ready(function() {
	if ("${fileForm.extension}" != ""){
		$("#limit").html("只能上传${fileForm.extension}格式的文件。");
	}
	
	$("#queryForm").validate( {
		errorClass : "error",
		submitHandler : function(form) {
			if ($("#queryForm").form("validate")){
				var fileName = $("#file").val();
				var b = false;
				for(var i = 0; i < exts.length; i++){
					if (fileName.indexOf(exts[i]) != -1){
						b = true;
						break;
					}
				}
				if (!b){
					alert("只能上传" + "${fileForm.extension}" + "格式的文件。");
					return;
				}
				$(form).ajaxSubmit( {
					type : "post",
					url:"uploadSingleFile",
					success : function(data) {
						var json = jQuery.parseJSON(data);
						if (json.state){
							alert(json.msg);
							self.close();
						} else {
							alert(json.msg);
						}
					}
				});
			}
		}
	});
});

</script>