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
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<link href="${app}/resetCSS.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />
<link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
<link href="${app}/css/easyUIReset.css" rel="stylesheet" type="text/css"/>

<style>
 .basicinfoTable,.basicinfoTable td,.basicinfoTable th{ border: 1px solid #d4d4d4;}
 .basicinfoTable th{background-color: #edfaff;}
 
caption, th { text-align:right;}
.h1_1 {
    height: 20px;
    line-height: 20px;
    font-size: 18px;
    text-align:center;
}

</style>
</HEAD>

	<body>
		<div class="h1_1" id='divtitle'>${title}</div>
		<div class="divContainer" id='divss' style="padding-top: 0px;">
			<form id="queryForm" action="saveRecord.json" method="post">
				<input type="hidden" id="id" name="id" value="${recordForm.id}"/>
				<input type="hidden" id="wflx" name="wflx" value="${recordForm.wflx}" />
				<table class="dataTable basicinfoTable" width="99%" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<th width="150">
							<span class="mark">*</span>问题项内容：
						</th>
						<td colspan="3">
							<textarea class="i-text easyui-validatebox" data-options="required:true" type="text" style="width:248px;height:40px;" id="content" name="content">${recordForm.content}</textarea>
						</td>
					</tr>
					<tr>
						<th>
							描述：
						</th>
						<td colspan="3">
							<textarea class="i-text" type="text" style="width:248px;height:40px;" id="describe" name="describe">${recordForm.describe}</textarea>
						</td>
					</tr>
					<tr>
						<th>
							提示内容：
						</th>
						<td colspan="3">
							<textarea class="i-text" type="text" style="width:248px;height:40px;" id="tsnr" name="tsnr">${recordForm.tsnr}</textarea>
						</td>
					</tr>
					
					<tr>
						<th>
							<span class="mark">*</span>执法对象类型：
						</th>
						<td colspan="3">
							<input class="i-text" type="text" style="width:250px" id="lawobjtype" name="lawobjtype"/>
						</td>
					</tr>
					<tr>
						<th>
							<span class="mark">*</span>任务类型：
						</th>
						<td colspan="3">
							<input class="i-text" type="text" style="width:250px" id="tasktype" name="tasktype" value="${recordForm.tasktype}" />
						</td>
					</tr>
					<tr>
						<th>
							<span class="mark">*</span>笔录类型：
						</th>
						<td colspan="3">
							<input class="i-text" type="text" style="width:250px" id="kcxwbj" name="kcxwbj" value="${recordForm.kcxwbj}"/>
						</td>
					</tr>
					<tr>
						<th>
							是否可删除：
						</th>
						<td>
							<input type="checkbox" id="isdel" name="isdel" <c:if test="${fn:trim(recordForm.isdel) eq 'Y'}" >checked</c:if>/>
						</td>
						<th width="100">
							是否自动加载：
						</th>
						<td>
							<input type="checkbox" id="iszdjz" name="iszdjz" <c:if test="${fn:trim(recordForm.iszdjz) eq 'Y'}" >checked</c:if>/>
						</td>
					</tr>
					
					<tr>
						<td colspan="4" align="center">
							<input type="submit" id="query"  class="queryBlue" value="保　存"/>
                            <input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
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
	$("#divss").height($(window).height()-$("#divtitle").outerHeight()-100);
	$('#wflx').combotree({
		required:true,
		type:"post",
		url:'illegalTypeList.json'
	});
	$('#lawobjtype').combotree({
		required:true,
		type:"post",
		multiple: true,
		url:'lawtypeTree.json',
	});
	if ('${recordForm.id}' == ''){
		$("#isActive").attr("checked", true);
		$("#iscurver").attr("checked", true);
		$("#iszdjz").attr("checked", true);
	} else {
		if ('${recordForm.lawobjtype}' != ''){
			$('#lawobjtype').combotree("setValues", '${recordForm.lawobjtype}'.split(","));
		}
	}
	$('#kcxwbj').combobox({
		data:[{'id':'0','name':'勘察笔录'},{'id':'1','name':'询问笔录'}],
		required:true,
		editable:false,
		valueField:'id',
		textField:'name'
	});

	$('#tasktype').combotree({
		required:true,
		editable:false,
		disabled:true,
		type:"post",
		url:'taskTypeTreeCombo.json',
		valueField:'id',
		textField:'name'
	});

	$("#queryForm").validate( {
		errorClass : "error",
		submitHandler : function(form) {
			if ($("#queryForm").form("validate")){
				$(form).ajaxSubmit( {
					type : "post",
					success : function(data) {
						alert(data.msg);
						//self.close();
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						parent.layer.close(index);
					}
				});
			}
		}
	});
});

$("#J-from-reset").click(function(){
	$("#queryForm").form('reset');
})
</script>