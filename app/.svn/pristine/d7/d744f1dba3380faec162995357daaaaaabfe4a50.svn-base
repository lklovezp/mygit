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

	<body>
		<div class="headTitle">导入离线执法信息</div>
		<div class="divContainer">
			<form id="queryForm" action="saveDjMessage.json" method="post" enctype="multipart/form-data">
				<input type="hidden" id="applyId" name="applyId" value="${applyId}"/>
				<table width="100%" border="0" cellpadding="0"
					cellspacing="0" class="formtable" align="center">
					<tr>
						<th width="38%">
							选择文件：
						</th>
						<td>
							<input class="i-text" type="file" id="file" name="file" accept="zip"/><font color="red">*只能上传.zip文件</font>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<span class="btn btn-ok"> <input id="query" type="submit"
									value="上传"> </span>
							&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
<script language="JavaScript">
$(document).ready(function(){
	$.ajaxSetup({cache:false});

	$("#queryForm").validate( {
		errorClass : "error",
		submitHandler : function(form) {
			$(form).ajaxSubmit( {
				type : "post",
				url:"saveDjMessage.htm",
				dataType:"json",
				success : function(data) {
					if (data.state){
						alert(data.msg);
						self.close();
					} else {
						alert(data.msg);
					}
				}
			});
		},
		rules : {
			"file" : {required : true}
		}
	});
});
</script>