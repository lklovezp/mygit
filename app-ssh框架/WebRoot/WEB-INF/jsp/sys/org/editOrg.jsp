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
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/default/easyui.css">
		<script type="text/javascript"	src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox2.css">
		<!-- app -->
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
	</HEAD>

	<body>
		<h1 class="h1_1">${title}</h1>
		<div style="width:95%; margin:0px auto 15px; padding:10px 0px 10px; border:1px solid #acacac;">
			<form id="queryForm" name="queryForm" method="post" action="saveOrg.json">
			<table class="dataTable" width="100%" border="0"  cellpadding="0" cellspacing="0">
				<input type="hidden" value="${orgForm.id}" id="id" name="id">
				<div id="neirong" style="padding: 5px;">
					<tr>
						<th>
							<font color="red"> * </font>部门名称
						</th>
						<td>
							<textarea class="y-text easyui-validatebox" data-options="required:true" type="text" id="name" name="name">${orgForm.name}</textarea>
						</td>
						<th>
							<font color="red"> * </font>工作单位
						</th>
						<td>
							<input class="y-text easyui-validatebox" data-options="required:true"  type="text" value="${orgForm.gzdw}"
							id="gzdw" name="gzdw" />
						</td>
					</tr>
					<tr>
						<th>
							上级部门
						</th>
						<td>
							<input type="hidden"   id="org" name="org">
							<input type="text"  class="y-text" id="orgName" name="orgName">
							<a href="#" style="color: #0088CC;" id="selectorg">选择上级部门</a>
						</td>
						<th>
							备注
						</th>
						<td>
							<textarea class="y-text"  type="text"
							id="funcDesc" name="note" >${orgForm.note}</textarea>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red"> * </font>排序
						</th>
						<td>
							<input class="y-text easyui-numberbox" data-options="required:true" type="text" value="${orgForm.orderby}"
							id="orderby" name="orderby" />
						</td>
						<th>
							是否有效
						</th>
						<td>
							<input type="checkBox" id="isActive" name="isActive"
								<c:if test="${fn:trim(orgForm.isActive) eq 'Y'}" >checked</c:if> />
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="submit" class="queryBlue" value="提　交"/>
       						<input type="reset"  class="queryOrange"  id="J-from-reset" value="重　置"/>
						</td>
					</tr>
					
				</div>
				</table>
			</form>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
function setOrgInfo(id,name) {
	$("#org").val(id);
	$("#orgName").val(name);
	jQuery().colorbox.close();
}

$(document).ready(function(){
	$.ajaxSetup({cache:false});

	$('#biztype').combobox({
		height:34,
		data:[{'id':'0','name':'执法'},{'id':'1','name':'其他'}],
		required:true,
		editable:false,
		valueField:'id',
		textField:'name'
	});
	$("#area").validatebox({required : true});
	
	if ("${orgForm.id}" != "") {
		$("#org").val('${orgForm.org}');
		$("#orgName").val('${orgForm.orgName}');
	} else {
		$("#isActive").attr("checked", true);
	}

	$("#selectorg").colorbox({iframe:true,width:"300", height:"300",href:"orgPubQuery.htm?id=${orgForm.id}"});
	
	$("#J-from-reset").click(function(){
		$("#queryForm").form('reset');
	});
	$('#bmlx').combobox({
		height:34,
		url:'orgType.json?orgid=${orgForm.id}',
		required:true,
		editable:false,
		valueField:'id',
		textField:'name'
	});
	
	//表单校验
	$("#queryForm").validate({
		errorClass: "error",
		submitHandler:function(form){
			if($("#queryForm").form("validate")){
				$(form).ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"saveOrg.json",
					success: function(data){
						if (data.state) {
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