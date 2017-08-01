<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/default/easyui.css">
        <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<style type="text/css">
		label{ margin-left:5px; margin-right:20px;color:red;}
		</style>
	</head>

	<body>
		<h1 class="h1_1">${title}</h1>
		<div style="width:95%; margin:0px auto 15px; padding:10px 0px 10px; border:1px solid #acacac;">
			<form id="queryForm"  method="post" enctype="multipart/form-data">
				<table  class="dataTable" width="100%" border="0"  cellpadding="0" cellspacing="0">
					<input type="hidden" value="${userForm.id}" id="id" name="id">
					<div id="neirong" style="padding: 5px;">
						<tr>
							<th>
								<font color="red"> * </font>用户名
							</th>
							<td>
								<input type="text" class="y-text easyui-validatebox" data-options="required:true"  style="width:165px" id="name" name="name" value="${userForm.name}" />
							</td>
							<th>
								<font color="red"> * </font>登录名
							</th>
							<td>
								<input class="y-text easyui-validatebox" data-options="required:true" style="width:165px;" type="text" value="${userForm.username}"	id="username" name="username" />
							</td>
						</tr>
						<tr>
							<th>
								<a href="#" style="color:#3399CC;" id="selectbm">所属部门</a>
							</th>
							<td>
								<input type="hidden" id="gxOrg" name="gxOrg" value="${userForm.gxOrg}"/>
								<input type="text" class="y-text easyui-validatebox" style="width:165px;" value="${userForm.gxOrgName}" id="gxOrgName" readonly="readonly">
							</td>
							<th>
								<font color="red"> * </font>序号：
							</th>
							<td>
								<input class="y-text easyui-numberbox" data-options="required:true" style="width:165px;" type="text" value="${userForm.orderby}" id="orderby" name="orderby" />
							</td>
						</tr>
						<tr>
							<th>
								是否管理员
							</th>
							<td>
								<input type="checkBox" id="issys" name="issys"
									<c:if test="${fn:trim(userForm.issys) eq 'Y'}" >checked</c:if> />
							</td>
							<th>
								是否有效
							</th>
							<td>
								<input type="checkBox" id="isActive" name="isActive"
									<c:if test="${fn:trim(userForm.isActive) eq 'Y'}" >checked</c:if> />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input id="pfbutton" class="queryBlue" type="submit" value="提交"/>
								<input type="reset" id="J-from-reset" class="queryOrange" value="重　置"/>
								<a href="#" id="J-pass-reset">重置密码为6个8</a>
							</td>
						</tr>
						
					</div>
				</table>
			</form>
		</div>
	</body>
</HTML>
<SCRIPT type="text/javascript">
	function setOrgInfo(id, name) {
		$("#gxOrg").val(id);
		$('#gxOrgName').val(name);
		jQuery().colorbox.close();
	}
	
	$(document).ready(function() {
		$.ajaxSetup({cache:false});
		
		if ('${userForm.id}' == ''){
			$("#J-pass-reset").hide();
			$("#isActive").attr("checked", true);
			$("#password").validatebox({required : true});
		} else {
			$("#password").validatebox({required : false});
			// 如果修改用户隐藏密码框
			$("#gxOrg").val('${userForm.gxOrg}');
			$('#gxOrgName').val('${userForm.gxOrgName}');
			//$('#gxOrg').linkbox("setValue", {id:'${userForm.gxOrg}',name:'${userForm.gxOrgName}'});
		}
		$("#selectbm").click(function(){
			$("#selectbm").colorbox( {
				iframe : true,
				width : "250",
				height : "300",
				type : 'POST',
				href : "orgPubQuery.htm"
			});
		});
		
		$("#J-from-reset").click(function() {
			$("#queryForm").form('reset');
			//$('#gxOrg').linkbox("setValue", {id:'${userForm.gxOrg}',name:'${userForm.gxOrgName}'});
			$("#gxOrg").val('${userForm.gxOrg}');
			$('#gxOrgName').val('${userForm.gxOrgName}');
			//$("#gxOrg-text").removeClass("validatebox-invalid");
		});
		$("#J-pass-reset").click(function() {
			$.ajax( {
				url : "resetPas.json?id=${userForm.id}",
				success : function(data) {
					//$.messager.alert('重置密码:', data.msg);
					var tishi=layer.alert(data.msg,{
				     	title:'重置密码',
				        icon:1,
				        shadeClose:true,
				     },
				     function(){
				    	window.location.href="userList.htm?title=用户管理&fid=40288ace4b80bdc0014b80e4ea67003b";
				        layer.close(tishi);
				     }
				     );
				}
			});
		});
		
		$("#queryForm").validate({
			errorClass : "error",
			submitHandler : function(form) {
				if ($("#queryForm").form("validate")){
				if ($("#file").val() == ''){
						$("#file").attr("disabled", 'disabled');
					}
					$(form).ajaxSubmit( {
						type : "post",
						url : "saveUser.json",
						success : function(data) {
							var json = jQuery.parseJSON(data);
							if (json.state){
								var tishi=layer.alert(json.msg,{
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
								alert(json.msg);
							}
						}
					});
			    
				}
			}
		});
	});
</SCRIPT>