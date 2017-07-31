<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>送达回证</title>
	    <script type="text/javascript" src="${jquery}/jquery.js"></script>
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
</style>
</head>
<body style="width: 90%;margin: 0 auto">
<div class="headTitle">送达回证</div>
<form id="myform" name="myform" method="post" action="saveSdhz.json">
<input type="hidden" id="applyId" name="applyId" value="${work.id}" />
<input type="hidden" id="wflx" name="wflx" value="${wflx}" />
<input type="hidden" id="id" name="id" value="${sdhzForm.id}" />
	<div class="divContainer">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formtable">
		<tr>
			<th>
				送达文书名称及文号
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="sdwsmc" name="sdwsmc"/>${sdhzForm.sdwsmc}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				受送达人名称或姓名
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="jsrmc" name="jsrmc"/>${sdhzForm.jsrmc}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				送达地点
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="jsrdz" name="jsrdz"/>${sdhzForm.jsrdz}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				送达方式
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="sdfs" name="sdfs"/>${sdhzForm.sdfs}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				备注
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="remark" name="remark"/>${sdhzForm.remark}</textarea>
			</td>
		</tr>
	</table>
	</div>
	<div class="bottomBtn">
		<span class="btn btn-ok"><input id="but_save" type="button" value="保存" onclick="save()"></span>&nbsp;
	</div>
</form>

</body>
</html>
<script type="text/javascript">
var applyId = $('#applyId').val();
function save() {
   $("#but_save").attr({disabled:"true",value:"保存中"});//设置按钮属性
   $('#myform').ajaxSubmit({
	    success : function(data) {
			$("#but_save").removeAttr("disabled");//将按钮可用
			if (data.state) {
			    $.messager.alert("送达回证制作:", data.msg, "info", function () {
                     window.close();
                });
			}
		}
   });
}
</script>