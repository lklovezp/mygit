<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="${layer}/layer.js"></script>
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox2.css">
		<!-- app -->
		<link rel="stylesheet" type="text/css" href="${app}/home.css">
		<script type="text/javascript" src="${common}/All.js"></script>
		<!-- 任务管理 css-->
	    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
	    <style>
	        .basicinfoTable,.basicinfoTable td,.basicinfoTable th{ border: 1px solid #d4d4d4;}
	    </style>
	</HEAD>

	<body>
		<div class="h1_1">导入收发文</div>
		<div class="divContainer">
			<form id="queryForm" action="importTemplate.htm" method="post" enctype="multipart/form-data">
				<table class="dataTable basicinfoTable" width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<th width="120" bgcolor="#edfaff" style="font-size: 15px;">
							选择文件：
						</th>
						<td>
							<input class="y-text" style="width:300px;"type="file" id="file" name="file"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							 <input type="submit" id="query"  class="queryBlue" value="上　传" onclick="hideSearchForm()"/>

						</td>
					</tr>
					<tr>
						<td colspan="2" style="font-size: 15px;">
							<span style="color: red;">*</span>注：如需下载批量导入的模板，请点击这里下载【<a style="font-size: 15px;" id="down" class="b-link" href="#">下载模板</a>】
						</td>
					</tr>
				</table>
			</form>
		</div>
		<iframe name="download" id="download" src="" style="display: none"></iframe>
	</body>
</html>
<script language="JavaScript">
$(document).ready(function(){
	$.ajaxSetup({cache:false});

	$("#queryForm").validate( {
		errorClass : "error",
		submitHandler : function(form) {
			if ($("#file").val() == ''){
				alert("请选择文件");
				return;
			}
			if ($("#file").val().indexOf(".xls") == -1 && $("#file").val().indexOf(".xlsx") == -1){
				alert("不是符合的文件格式。请重新选择。");
				return;
			}
			$(form).ajaxSubmit( {
				type : "post",
				url:"importTemplate.htm",
				dataType:"json",
				success : function(data) {
					if (data.state){
						alert(data.msg);
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						parent.layer.close(index);
						//self.close();
					} else {
						alert(data.msg);
					}
				}
			});
		}
	});
});

$("#down").click(function(){
	$('#download').attr('src','downTemplate?lawObjType=${lawObjType}');
})
</script>