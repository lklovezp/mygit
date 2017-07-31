<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/default/easyui.css">
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
		label{ margin-left:5px; margin-right:20px;}
		</style>
	</HEAD>

	<body>
		<div style="width:97%; margin:0px auto 15px; padding:10px 0px 10px; border:1px solid #acacac;">
			<form id="queryForm" name="queryForm" method="post"	action="saveMobile.json">
				<input type="hidden" value="${mobileForm.id}" id="id" name="id">
				<table class="dataTable" width="100%" border="0"  cellpadding="0" cellspacing="0">
					<tr>
						<th>
							<font color="red"> * </font>功能名称
						</th>
						<td colspan="3">
							<input class="y-text easyui-validatebox"  data-options="required:true" type="text" id="name" name="name"
								value="${mobileForm.name}" />
						</td>
					</tr>
					<tr>
						<th>
							功能描述
						</th>
						<td colspan="3">
							<textarea id="describe"  name="describe" class="y-textarea">${mobileForm.describe}</textarea>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red"> * </font>功能Activity 
						</th>
						<td colspan="3">
							<input class="y-text easyui-validatebox" data-options="required:true"  type="text" value="${mobileForm.activity}"
								id="activity" name="activity" />
						</td>
					</tr>
					<tr>
						<th>
							所属父功能
						</th>
						<td colspan="3">
							<input type="hidden" value="${mobileForm.pid}" id="pid"
								name="pid">
							<input class="y-text"  readonly="true" type="text" id="pname" name="pname"
								value="${mobileForm.pname}" />
							<a href="#" style="color: #0088CC;" id="selectMobile">选择父功能</a>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red"> * </font>显示顺序
						</th>
						<td>
							<input class="y-text easyui-numberbox" style="width:50px;" data-options="required:true" type="text" value="${mobileForm.orderby}"
								id="orderby" name="orderby" />
						</td>
						<th>
							是否有效
						</th>
						<td>
							<input type="checkBox" id="isActive" name="isActive"
								checked=${mobileForm.isActive == "Y"} />
						</td>
					</tr>
					<!-- 
					<tr>
						<th>
							展示类型：
						</th>
						<td>
							<input class="i-text" type="text" value="${mobileForm.style}"
								id="style" name="style" />
						</td>
					</tr>
					 -->
					<tr>
						<td align="center" colspan="4">
							<input id="mobileBtn" class="queryBlue" type="submit" value="提交"/>
							<input type="reset" id="J-from-reset" class="queryOrange" value="重　置"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
function setMobileInfo(id,name) {
	$('#pid').val(id)
	$('#pname').val(name)
	$('#pname').focus();
	jQuery().colorbox.close();
}
var lastRowIndex;
$(document).ready(function(){
	$("#selectMobile").colorbox({iframe:true,width:"300", height:"300",href:"mobilePubQuery.htm?id=${mobileForm.id}"});
	$("#J-from-reset").click(function(){
		$("#queryForm").reset();
	});
	//$('#style').combobox({
	//	url:'displayType.json',
	//	valueField:'id',
	//	textField:'name'
	//});
	//表单校验
	$("#queryForm").validate({
		errorClass: "error",
		submitHandler:function(form){
			if($("#queryForm").form("validate")){
				$("#mobileBtn").attr({"disabled":"disabled"});
				$(form).ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"saveMobile.json",
					success: function(data){
						if(data.state){
							var tishi=layer.alert(data.msg,{
						     	title:'信息提示',
						        icon:1,
						        shadeClose:true,
						     },
						     function(){
						    	parent.closeLayer();
						        layer.close(tishi);
						     }
						     );
						} else {
							alert(data.msg);
						}
					}
				});
			}
		}
	});
});
</SCRIPT>