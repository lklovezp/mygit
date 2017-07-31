<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${common}/All.js"></script>
<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<!--jQuery-->
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<!--easyui-->
<link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
<!-- 任务管理 css-->
<link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
<style>
.basicinfoTable,.basicinfoTable td,.basicinfoTable th{ border: 1px solid #d4d4d4;}
.basicinfoTable th{background-color: #edfaff;}
.combo {border-color:  #578bd0 #95b8e7 #95b8e7 #578bd0;}
caption, th { text-align:right;}
</style>
</HEAD>

	<body>
		 <div class="h1_1">${title}</div> 
		<div class="divContainer">
			<form id="queryForm" action="saveVersion.htm" method="post" enctype="multipart/form-data">
				<input type="hidden" id="id" name="id" value="${versionForm.id}"/>
				<table class="dataTable basicinfoTable" width="99%" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<th width="120">
							<span class="mark">*</span>版本号：
						</th>
						<td colspan="2">
							<input class="i-text easyui-validatebox" style="width:250px;" data-options="required:true" type="text" id="code" name="code" value="${versionForm.code}"/>
						</td>
					</tr>
					<tr>
						<th>
							<span class="mark">*</span>版本名称：
						</th>
						<td colspan="2">
							<input class="i-text easyui-validatebox" style="width:250px;" data-options="required:true" type="text" id="name" name="name" value="${versionForm.name}"/>
						</td>
					</tr>
					<tr>
						<th>
							<span class="mark">*</span>类型：
						</th>
						<td colspan="2">
							<input class="i-text" style="width:256px;" type="text" id="type" name="type" value="${versionForm.type}"/>
						</td>
					</tr>
					<tr>
						<th>
							<span class="mark">*</span>安装包文件：
						</th>
						<td>
							<input class="i-text" style="width:256px;" type="file" id="file" name="file"/>
							<c:if test="${versionForm.filePath != ''}">
								<br>
								${versionForm.filePath}
							</c:if>
						</td>
						<td>
							<a href="javascript:downloadFile('${versionForm.id}')" class="b-link" id="downrecord">下载</a>
						</td>
					</tr>
					<tr>
						<th>
							版本说明：
						</th>
						<td colspan="2">
							<textarea class="i-text" style="width:254px;height:40px;" type="text" id="describe" name="describe">${versionForm.describe}</textarea>
						</td>
					</tr>
					<tr>
						<th>
							<span class="mark">*</span>排序：
						</th>
						<td colspan="2">
							<input class="i-text easyui-numberbox" data-options="required:true" type="text" id="orderby" name="orderby" value="${versionForm.orderby}"/>
						</td>
					</tr>
					<tr>
						<th>
							强制更新：
						</th>
						<td colspan="2">
							<input type="checkBox" id="isforce" name="isforce"
								<c:if test="${fn:trim(versionForm.isforce) eq 'Y'}" >checked</c:if> />
						</td>
					</tr>
					<tr>
						<th>
							是否有效：
						</th>
						<td colspan="2">
							<input type="checkBox" id="isActive" name="isActive"
								<c:if test="${fn:trim(versionForm.isActive) eq 'Y'}" >checked</c:if> />
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<input type="submit" id="query"  class="queryBlue" value="保　存"/>
                            <input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
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
	if ('${versionForm.id}' == '') {
		$("#downrecord").hide();
		$("#isActive").attr("checked", true);
	}
	$('#type').combobox({
		data:[{'id':'0','name':'终端'},{'id':'1','name':'离线版'},{'id':'2','name':'离线版数据包'},{'id':'3','name':'后台帮助文档'},{'id':'4','name':'终端帮助文档'}],
		required:true,
		editable:false,
		valueField:'id',
		textField:'name'
	});

	$("#queryForm").validate( {
		errorClass : "error",
		submitHandler : function(form) {
			if ($("#queryForm").form("validate")){
				if ($("#id").val() == '' && $("#file").val() == ''){
					alert("请选择文件");
					return;
				}
				if ($("#file").val() == ''){
					$("#file").attr("disabled", 'disabled');
				}
				$(form).ajaxSubmit( {
					type : "post",
					url:"saveVersion.htm",
					success : function(data) {
						var json = jQuery.parseJSON(data);
						if (json.state){
							alert(json.msg);
							//self.close();
							var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
							parent.layer.close(index);
						} else {
							alert(json.msg);
						}
					}
				});
			}
		}
	});
});

$("#J-from-reset").click(function(){
	$("#queryForm").form("reset");
})

function downloadFile(fileId){
	$.messager.confirm('操作确认','确定下载此文件吗?',function(r){  
		if (r){
			$('#download').attr('src','downloadVersion?id='+fileId);
		}
	});
}
</script>