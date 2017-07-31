<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>修改检查项内容</title>
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
</head>
<body style="width: 90%;margin: 0 auto">
<div class="headTitle">修改检查项内容</div>
<form id="myform" name="myform" method="post" action="saveJcx.json">
<input type="hidden" id="applyId" name="applyId" value="${applyId}" />
<input type="hidden" id="jcmbid" name="jcmbid" value="${jcmbid}" />
<input type="hidden" id="itemid" name="itemid" value="${itemid}" />
<input type="hidden" id="orderby" name="orderby" value="${orderby}" />
	<div class="divContainer">

    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formtable">
		<tr>
			<td colspan="8">
				<textarea style="width:350px;height:80px;" class="i-text easyui-validatebox" data-options="required:true" id="itemcontent" name="itemcontent"/>${itemcontent}</textarea>
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

$(document).ready(function() {

});

function save() { 
    //问题内容校验
    if($("#itemcontent").validatebox("isValid")){
		$('#myform').ajaxSubmit(function(data){
	   		if(data.state){
			    $.messager.alert("修改检查项:", data.msg, "info", function () {
			         parent.window.returnValue = $('#itemcontent').val();
			         //window.dialogArguments.location.reload();
	                 window.close();
	            });		
	   		} else {
		   		alert(data.msg);
	   		}
	   	});
    }else{
        $("#itemcontent").focus();
    }
}

</script>