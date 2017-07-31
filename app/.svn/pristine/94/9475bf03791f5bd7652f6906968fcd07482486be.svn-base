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
		<script type="text/javascript"	src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css"	href="${colorbox}/colorbox2.css">
		<!-- app -->
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
	</HEAD>

	<body>
		<!--  <h1 class="h1_1">${title}</h1>-->
		<div style="width:95%; margin:0px auto 15px; padding:10px 0px 10px; border:1px solid #acacac;">
			<form id="queryForm" name="queryForm" method="post" action="saveOrg.json">
				<input type="hidden" id="id" name="id" value="${areaForm.id}"/>
				<table  class="dataTable" width="100%" border="0"  cellpadding="0" cellspacing="0">
					<div id="neirong" style="padding: 5px;">
						<tr>
							<th width="150">
								<font color="red"> * </font>区域编号
							</th>
							<td>
								<input class="y-text" data-options="required:true"  type="text" id="code" name="code"
									value="${areaForm.code}" />
							</td>
							<th>
								<font color="red"> * </font>区域名称
							</th>
							<td>
								<textarea class="y-text easyui-validatebox" data-options="required:true"  type="text" id="areaName" name="areaName">${areaForm.name}</textarea>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red"> * </font>工作单位
							</th>
							<td>
								<textarea class="y-text easyui-validatebox" data-options="required:true"  type="text" id="unitName" name="unitName">${areaForm.unitName}</textarea>
							</td>
							<th>
								<font color="red"> * </font>区域类型
							</th>
							<td>
								<input class="y-text"  type="text" id="type" name="type"
									value="${areaForm.type}" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red"> * </font>上级区域
							</th>
							<td>
								<input type="hidden" value="${areaForm.pid}" id="pid" name="pid">
								<input class="y-text easyui-validatebox" data-options="required:true"  type="text" readonly="true"
									value="${areaForm.pname}" id="pname" name="pname" />
								<a href="#" style="color: #0088CC;" id="selectParent">选择上级区域</a>
							</td>
							<th>
								<font color="red"> * </font>区域服务器
							</th>
							<td>
								<input class="y-text"  type="text" readonly="true" value="${areaForm.server}" id="server" name="server" />
							</td>
						</tr>
						<tr>
							<th>
								描述
							</th>
							<td>
								<textarea class="y-text"  id="describe" name="describe">${areaForm.describe}</textarea>
							</td>
							<th>
								<font color="red"> * </font>排序
							</th>
							<td>
								<input class="y-text easyui-numberbox" data-options="required:true" type="text" value="${areaForm.orderby}"
									id="orderby" name="orderby" />
							</td>
						</tr>
						<tr>
							<th>
								是否有效
							</th>
							<td colspan="3">
								<input type="checkBox" id="isActive" name="isActive" <c:if test="${fn:trim(areaForm.isActive) eq 'Y'}" >checked</c:if> />
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
	function setAreaInfo(id, name) {
		$('#pid').val(id)
		$('#pname').val(name)
		$('#pname').focus();
		jQuery().colorbox.close();
	}
	var lastRowIndex;
	$(document).ready(function() {
		$.ajaxSetup({cache:false});

		if ('${areaForm.id}' == ''){
			$("#isActive").attr("checked", true);
		}
		
		$('#code').combotree({
			height:34,
			url:'regionTree.json',
			required:true,
			editable:false,
			onBeforeSelect:function(node){
				if(node.state){        
					$('#ssxzq').tree('unselect');    
				} 
			}
		});
		$("#selectParent").colorbox( {
			iframe : true,
			width : "300",
			height : "300",
			href : "areaPubQuery.htm?id=${areaForm.pid}"
		});
		$("#J-from-reset").click(function() {
			$("#queryForm").form("reset");
		});
		$('#type').combobox( {
			height:34,
			url : 'areaType.json',
			required:true,
			editable:false,
			valueField : 'id',
			textField : 'name'
		});
		$('#server').combobox( {
			height:34,
			url : 'queryServer.json',
			required:true,
			editable:false,
			valueField : 'id',
			textField : 'name'
		});
		//表单校验
		$("#queryForm").validate( {
			errorClass : "error",
			submitHandler : function(form) {
				if ($("#queryForm").form("validate")){
					$(form).ajaxSubmit( {
						type : "post",
						dataType : "json",
						url : "saveArea.json",
						success : function(data) {
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
							}else{
								alert(data.msg);
							}
						}
					});
				}
			}
		});
	});
</SCRIPT>