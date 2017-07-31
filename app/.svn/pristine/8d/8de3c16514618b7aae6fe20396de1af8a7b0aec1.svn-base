<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="/app/static/app/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="/app/static/app/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="/app/static/jquery/jquery.js"></script>
		<script type="text/javascript" src="/app/static/layer/layer.js"></script>
		<script type="text/javascript" src="/app/static/jquery/jquery.validate.js"></script>
		<script type="text/javascript" src="/app/static/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="/app/static/jquery/messages_cn.js"></script>
		<script type="text/javascript" src="/app/static/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="/app/static/easyui/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css"	href="/app/static/easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css"	href="/app/static/easyui/themes/default/easyui.css">
        <link href="/app/static/app/easyUIReset.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="/app/static/colorbox/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="/app/static/colorbox/colorbox.css">
		<script type="text/javascript" src="/app/static/common/All.js"></script>
		<link href="/app/static/app/css/easyUIReset.css" rel="stylesheet" type="text/css"/>
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
					<input type="hidden" value="${userForm.role}" id="roleId" name="roleId">
					<input type="hidden" value="${userForm.group}" id="groupId" name="groupId">
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
						<c:if test="${fn:trim(userForm.id) eq ''}" >
							<th>
								<font color="red"> * </font>密码
							</th>
							<td  class="password">
								<input class="y-text" style="width:165px;" type="password" value="${userForm.password}"	id="password" name="password" />
							</td>
						</c:if>
							<th>
								<font color="red"> * </font>角色
							</th>
							<td>
								<input class="y-text" style="width:180px;" type="text" id="role" name="role"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red"> * </font>业务类型
							</th>
							<td>
								<input class="y-text" style="width:180px;" type="text" id="bizType" name="bizType" value="${userForm.bizType}"/>
							</td>
							<th>
								<font color="red"> * </font>
								<a href="#" style="color:#3399CC;" id="selectbm">所属部门</a>
							</th>
							<td>
								<input type="hidden" id="gxOrg" name="gxOrg" value="${userForm.gxOrg}"/>
								<input type="text" class="y-text easyui-validatebox" style="width:165px;" value="${userForm.gxOrgName}" id="gxOrgName" readonly="readonly">
							</td>
						</tr>
						<tr>
							<th>
								<font color="red"> * </font>工作手机号
							</th>
							<td>
								<input class="y-text easyui-numberbox" data-options="required:true" style="width:165px;" type="text" maxlength="11"	value="${userForm.workmobile}" id="workmobile" name="workmobile" />
							</td>
							<th>
								个人手机号
							</th>
							<td>
								<input class="y-text easyui-numberbox" style="width:165px;" type="text" maxlength="11" value="${userForm.personmobile}" id="personmobile" name="personmobile" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red"> * </font>执法证号
							</th>
							<td>
								<input class="y-text easyui-validatebox" data-options="required:true" style="width:165px;" type="text" maxlength="10"
									value="${userForm.zfzh}" id="zfzh" name="zfzh" />
							</td>
							<th>
								上传照片
							</th>
							<td>
								<input class="y-text" style="width:180px;" type="file" id="file" name="file" accept="jpg,jpeg,JPG,JPEG,png,PNG,bmp,BMP"/>
								<a href="javascript:void(0)" onclick="shouImgWin();" class="b-link" id="preview" >预览</a>
								<c:if test="${userForm.filePath != ''}">
									<br>
									${userForm.filePath}
								</c:if>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red"> * </font>序号：
							</th>
							<td>
								<input class="y-text easyui-numberbox" data-options="required:true" style="width:165px;" type="text" value="${userForm.orderby}" id="orderby" name="orderby" />
							</td>
							<th>
								会商接收人分组
							</th>
							<td>
								<input class="y-text" style="width:180px;" type="text" id="group" name="group"/>
							</td>
						</tr>
						<tr>
							<th>
								是否为在编在岗人员
							</th>
							<td>
								<input type="checkBox" id="isZaiBian" name="isZaiBian"
									<c:if test="${fn:trim(userForm.isZaiBian) eq 'Y'}" >checked</c:if> />
							</td>
							<th>
								是否管理员
							</th>
							<td>
								<input type="checkBox" id="issys" name="issys"
									<c:if test="${fn:trim(userForm.issys) eq 'Y'}" >checked</c:if> />
							</td>
						</tr>
						<tr>
							<th>
								是否有效
							</th>
							<td>
								<input type="checkBox" id="isActive" name="isActive"
									<c:if test="${fn:trim(userForm.isActive) eq 'Y'}" >checked</c:if> />
							</td>
							<th>
								是否可接受随机抽查任务
							</th>
							<td colspan="2">
								<input type="checkBox" id="isRecWork" name="isRecWork"
									<c:if test="${fn:trim(userForm.isRecWork) eq 'Y'}" >checked</c:if> />
							</td>
						</tr>
					ssss	sss
						<!--  <tr>
						<th>
					          隶属上级
						</th>
						<td>
						<input class="y-text" type="text" style="width:217px;" id="fid" name="fid" value="${userForm.fid}"/>
						</td>
						</tr>-->
						
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
var roleId = $('#roleId').val();
var groupId = $('#groupId').val();
	function setOrgInfo(id, name) {
		$("#gxOrg").val(id);
		$('#gxOrgName').val(name);
		jQuery().colorbox.close();
	}
	
	var lastRowIndex;
	$(document).ready(function() {
		$.ajaxSetup({cache:false});
		$('#bizType').combobox({
			height:34,
			data:[{'id':'0','name':'执法'},{'id':'1','name':'其他'}],
			required:true,
			editable:false,
			valueField:'id',
			textField:'name'
		});
		
		$('#fid').combotree({
		height:34,
		type:"post",
		url:'userTree.json'
		
	});
		
		if ('${userForm.id}' == ''){
			$("#J-pass-reset").hide();
			$("#isActive").attr("checked", true);
			$("#password").validatebox({required : true});
			$("#bizType").combobox("setValue", '0');
		} else {
			$("#password").validatebox({required : false});
			// 如果修改用户隐藏密码框
			$("#gxOrg").val('${userForm.gxOrg}');
			$('#gxOrgName').val('${userForm.gxOrgName}');
			//$('#gxOrg').linkbox("setValue", {id:'${userForm.gxOrg}',name:'${userForm.gxOrgName}'});
		}
		if ('${userForm.filePath}' == '') {
			$("#preview").hide();
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
				    	 parent.layer.close(index);
							parent.closeLayer();
				     }
				     );
				}
			});
		});
		
		if(roleId == "" || roleId == null){
			$('#role').combobox( {
				height:34,
				type : "post",
				required:true,
				multiple: true,
				editable:false,
				url : 'queryAllRole.json',
				valueField : 'id',
				textField : 'name'
			});
		}else{
			$('#role').combobox( {
				height:34,
				type : "post",
				required:true,
				multiple: true,
				editable:false,
				url : 'queryAllRole.json',
				valueField : 'id',
				textField : 'name',
				value:'${roleId}'.split(',')
			});
		}
		
		if(groupId == "" || groupId == null){
			$('#group').combobox({
				height:34,
				type : "post",
				multiple: true,
				editable:false,
				url:'dicList.json?type=29',
				valueField:'id',
				textField:'name'
			});
		}else{
			$('#group').combobox({
				height:34,
				type : "post",
				multiple: true,
				editable:false,
				url:'dicList.json?type=29',
				valueField:'id',
				textField:'name',
				value:'${groupId}'.split(',')
			});
		}
		
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
							    	 layer.close(tishi);
							    	 
							    	 var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
										parent.layer.close(index);
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
	function shouImgWin(){
		layer.open({
		       type:2,
		       title:'预览',
		       area:['390px','420px'],
		       shadeClose:false,
		       maxmin:true,
		       content:"previewImage.htm?id=${userForm.id}"
		     });
		//All.ShowModalWin("previewImage.htm?id=${userForm.id}", "", 350, 496);
	/**
	
		$("#preview").colorbox( {
			iframe:true,
			width : "360",
			height : "526",
			initialWidth:"0", //设置初始化宽度
			initialHeight: "0", //设置初始化高度
			href : "previewImage.htm?id=${userForm.id}"
		});
	*/
	}
</SCRIPT>