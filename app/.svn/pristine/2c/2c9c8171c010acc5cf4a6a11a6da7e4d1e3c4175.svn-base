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
						<td colspan="3">
							<textarea class="y-text easyui-validatebox" data-options="required:true" type="text" id="name" name="name">${orgForm.name}</textarea>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red"> * </font>部门类型
						</th>
						<td>
							<input class="y-text" type="text" id="bmlx" name="bmlx" value="${orgForm.bmlx}" />
						</td>
						<th>
							<font color="red"> * </font>所属区域
						</th>
						<td>
							<input type="hidden"  class="y-text" id="area" name="area">
							<input type="text"  class="y-text" id="areaName" name="areaName">
							<a href="#" style="color: #0088CC;" id="selectArea">选择区域</a>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red"> * </font>业务类型
						</th>
						<td>
							<input class="y-text" type="text" id="biztype" name="biztype" value="${orgForm.biztype}" />
						</td>
						<th>
							管辖区域
						</th>
						<td>
							<input type="hidden"   id="dominarea" name="dominarea">
							<input type="text"  class="y-text" id="dominareaName" name="dominareaName">
							<a href="#" style="color: #0088CC;" id="selectdominarea">选择管辖区域</a>
							
						</td>
					</tr>
					
					<tr>
						<th>
							<font color="red"> * </font>工作单位
						</th>
						<td>
							<input class="y-text easyui-validatebox" data-options="required:true"  type="text" value="${orgForm.gzdw}"
							id="gzdw" name="gzdw" />
						</td>
						<th>
							上级部门
						</th>
						<td>
							<input type="hidden"   id="org" name="org">
							<input type="text"  class="y-text" id="orgName" name="orgName">
							<a href="#" style="color: #0088CC;" id="selectorg">选择上级部门</a>
						</td>
					</tr>
					<tr>
						<th>
							备注
						</th>
						<td>
							<textarea class="y-text"  type="text"
							id="funcDesc" name="note" >${orgForm.note}</textarea>
						</td>
						<th>
							<font color="red"> * </font>部门主管
						</th>
						<td>
							<input type="hidden"  id="leader" name="leader">
							<input type="text"  class="y-text" id="leaderName" name="leaderName">
							<a href="#" style="color: #0088CC;" id="selectleader">选择部门主管</a>
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
function setUserInfo(id,name) {
	$("#leader").val(id);
	$("#leaderName").val(name);
	jQuery().colorbox.close();
}
function setAreaInfo(id,name){
	$("#area").val(id);
	$("#areaName").val(name);
	jQuery().colorbox.close();
}
function setMultiOrgInfo(id, name){
	$("#dominarea").val(id);
	$("#dominareaName").val(name);
	jQuery.colorbox.close();
}

var lastRowIndex;
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
		$("#leader").val('${orgForm.leader}');
		$("#leaderName").val('${orgForm.leaderName}');
		$("#area").val('${orgForm.area}');
		$("#areaName").val('${orgForm.areaName}');
		$("#dominarea").val('${orgForm.dominarea}');
		$("#dominareaName").val('${orgForm.dominareaName}');
	} else {
		$("#biztype").combobox("setValue", '0');
		$("#isActive").attr("checked", true);

		if ("${area}" != "") {
			$("#area").val('${orgForm.area}');
			$("#areaName").val('${orgForm.areaName}');
		}
	}

	if ("${admin}" == '0'){
		$("#area").attr("disabled", true);
	} else {
		$("#selectArea").colorbox({iframe:true,width:"300", height:"300",href:"${basePath}/areaPubQuery.htm?multi=false&id="+$('#area').val()});
	}
	$("#selectdominarea").colorbox({iframe:true,width:"300", height:"300",href:"${basePath}/areaPubQuery.htm?multi=true&id="+$('#area').val()});
	$("#selectorg").colorbox({iframe:true,width:"300", height:"300",href:"orgPubQuery.htm?id=${orgForm.id}"});
	$("#selectleader").colorbox({iframe:true,width:"300", height:"300",href:"${basePath}/userPubQuery.htm?all=true&methodname=setUserInfo&id="+$('#leader').val()+"&notShowSys=true"});
	
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
				if ($("#leader").val() == "" || $("#leader").val() == null) {
					alert("部门领导未选择，请建立人员后修改此部门。");
				}
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
						    	layer.close(tishi);
						    	parent.closeLayer();
						       
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