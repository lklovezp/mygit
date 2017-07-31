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
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox2.css">
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<!-- app -->
		<link rel="stylesheet" type="text/css" href="${app}/home.css">
		<script type="text/javascript" src="${common}/All.js"></script>
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
			<form id="queryForm" action="saveConfig.htm" method="post" enctype="multipart/form-data">
				<input type="hidden" id="id" name="id" value="${configForm.id}"/>
				<table class="dataTable basicinfoTable" width="99%" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<th width="120">
							<span class="mark">*</span>任务类型：
						</th>
						<td colspan="2">
							<label id="tasktypeid" name="tasktypeid">${configForm.tasktypeid}</label>
						</td>
					</tr>
					<tr>
						<th>
							<span class="mark">*</span>执法对象类型：
						</th>
						<td colspan="2">
							<label id="lawobjtype" name="lawobjtype">${configForm.lawobjtype}</label>
						</td>
					</tr>
					<tr>
						<th>
							执行检查单方式：
						</th>
						<td colspan="2">
							<input type="i-text" id="isexecchecklist" name="isexecchecklist" value="${configForm.isexecchecklist}" />
						</td>
					</tr>
					<tr>
						<th>
							监察模板：
						</th>
						<td>
							<input class="i-text" style="width:256px;" type="file" id="file" name="file"/>
							<c:if test="${configForm.filePath != ''}">
								<br>
								${configForm.filePath}
							</c:if>
						</td>
						<td>
							<a href="javascript:downloadFile('${configForm.id}')" class="b-link" id="downrecord">下载</a>
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

	//任务类型执行方式
	$('#isexecchecklist').combobox({
		type:"post",
		url:'dicList.json?type=16',
		valueField:'id',
		textField:'name',
		onChange : function(newValue, oldValue){
			if (newValue != '1'){
				$("#file").attr("disabled", 'disabled');
			} else {
				$("#file").removeAttr("disabled");
			}
		}
	});
	
	if ('${configForm.isexecchecklist}' != '1'){
		$("#file").attr("disabled", 'disabled');
	} else {
		$("#file").removeAttr("disabled");
	}

	if ('${configForm.filePath}' == '') {
		$("#downrecord").hide();
	}
	
	$("#queryForm").validate( {
		errorClass : "error",
		submitHandler : function(form) {
			if ($("#queryForm").form("validate")){
				if ($("#isexecchecklist").value == 1 && '${configForm.filePath}' == '' && $("#file").val() == ''){
					alert("请选择文件");
					return;
				}
				if ($("#file").val() == ''){
					$("#file").attr("disabled", 'disabled');
				}
				$(form).ajaxSubmit( {
					type : "post",
					url:"saveConfig.htm",
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
	$("#queryForm").reset();
})

function downloadFile(fileId){
	$.messager.confirm('操作确认','确定下载此文件吗?',function(r){  
		if (r){
			$('#download').attr('src','downloadCheckTemplate?id='+fileId);
		}
	});
}
</script>