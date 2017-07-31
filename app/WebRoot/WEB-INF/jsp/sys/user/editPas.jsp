<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/default/easyui.css">
        <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
		label{ margin-left:5px; margin-right:20px;}
		</style>
	</head>

	<body>
		<div style="color:red;margin: 10px 0px 10px 30px; width: 95%;">注：密码需以字母开头，长度6至16位，且只能包含字母、数字、下划线。</div>
		<div class="dataDiv" style="width:95%; margin:0px auto 25px; padding:10px 0px 30px; border:1px solid #acacac;">
			<form id="queryForm" action="savePas.json" method="post">
				<table 	 class="dataTable" width="100%" border="0"  cellpadding="0" cellspacing="0">
					<div id="neirong" style="padding: 5px;">
						<tr height="50">
							<td align="right"  width="45%"><font color="red"> * </font>当前密码</td>
							<td align="left" width="55%">
							<input class="y-text easyui-validatebox" data-options="required:true" type="password" id="oldPas" name="oldPas"
								value="${userForm.oldPas}" />
							</td>
						</tr>
						<tr height="50">
						   
							<td align="right">
								<font color="red"> * </font>新密码：
							</td>
							<td align="left">
							<input class="y-text easyui-validatebox" data-options="required:true" type="password" value="${userForm.password}"
								id="password" name="password"" />
							</td>
						</tr>
						<tr height="50">
							<td align="right">
								<font color="red"> * </font>确认密码
							</td>
							<td align="left">
							<input class="y-text easyui-validatebox" data-options="required:true" type="password" value="${userForm.confirmPassword}"
								id="confirmPassword" name="confirmPassword" />
							</td>
						</tr>
						<tr height="50">
							<td align="center" colspan="2">
								<span class="btn btn-ok"> <input type="submit" class="queryBlue" value="提交">
								</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" id="J-from-reset" class="queryOrange" value="重　置"/>
								<a href="j_spring_security_logout" id="logout"></a>
							</td>
						</tr>
					</div>
				</table>
			</form>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">

$(document).ready(function(){
	$("#J-from-reset").click(function(){
		$("#queryForm").reset();
	});
	//表单校验
	$("#queryForm").validate( {
		errorClass : "error",
		submitHandler : function(form) {
			if ($("#queryForm").form("validate")){
				$(form).ajaxSubmit( {
					type : "post",
					url : "savePas.json",
					dataType:"json",
					success : function(data) {
						if (data.state){
							alert("修改密码成功！");
							parent.logout();
						} else {
							alert(data.msg);
							window.location.reload("editPas.htm");
						}
					}
				});
			}
		}
	});
});
</SCRIPT>