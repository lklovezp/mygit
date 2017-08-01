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
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox2.css">
		<!-- app -->
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
	</HEAD>
	<body>
		<div style="width:95%; margin:0px auto 0px; padding:10px 0px 0px; border:0px solid #acacac;">
			<form id="myform" name="myform" method="post" action="saveDic.json">
				<table class="dataTable" width="100%" border="0"  cellpadding="0" cellspacing="0">
					<input type="hidden" value="${dicForm.id}" id="id" name="id">
					<tr>
						<th>
							<font color="red"> * </font>字典名称
						</th>
						<td>
							<textarea class="y-text easyui-validatebox" data-options="required:true" type="text" id="name" name="name">${dicForm.name}</textarea>
						</td>
						<th>
							字典节点
						</th>
						<td>
							<input type="hidden"   id="pid" name="pid">
							<input type="text"  class="y-text" id="pName" name="pName" value="${dicForm.pName}">
							<a href="#" style="color: #0088CC;" id="selectorg">选择节点</a>
						</td>
					</tr>
					<tr>
						<th>
							备注
						</th>
						<td>
							<textarea class="y-text"  type="text" id="funcDesc" name="note" >${dicForm.note}</textarea>
						</td>
						<th>
							<font color="red"> * </font>排序
						</th>
						<td>
							<input class="y-text easyui-numberbox" data-options="required:true" type="text" value="${dicForm.order}"
							id="order" name="order" />
						</td>
					</tr>
					<tr>
						<th>
							字典类型
						</th>
						<td>
							<input type="hidden" value="${dicForm.type}" id="type" name="type" />
							<input class="y-text" type="text" value="${dicForm.typename}" id="typename" name="typename" readonly="readonly"/>
						</td>
						<th>
							是否有效
						</th>
						<td>
							<input type="checkBox" id="isActive" name="isActive"
								<c:if test="${fn:trim(dicForm.isActive) eq 'Y'}" >checked</c:if> />
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="submit" class="queryBlue" value="提　交"/>
       						<input type="reset"  class="queryOrange"  id="J-from-reset" value="重　置"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
var type = window.document.getElementById("type");
	function setType(id, name) {
		$('#typeId').val(id)
		$('#dicType').val(name)
		$('#dicType').focus();
		jQuery().colorbox.close();
	}
	var lastRowIndex;
	function setOrgInfo(id,name) {
		$("#pid").val(id);
		$("#pName").val(name);
		jQuery().colorbox.close();
	}
	
	$(document).ready(function() {
		$.ajaxSetup({cache:false});
		//监听键盘，只允许输入数字和小数点
		$('#order').numberbox( {
			min : 0,
			max : 99999999,
			precision : 0
		});
		$("#selectorg").colorbox({iframe:true,width:"300", height:"300",href:"dicPubQuery.htm?id=${dicForm.id}&type=${dicForm.type}"});
		$("#J-from-reset").click(function() {
			$("#myform").reset();
		});
		

		//表单校验
		$("#myform").validate( {
			errorClass : "error",
			submitHandler : function(form) {
				if ($("#myform").form("validate")) {
					$(form).ajaxSubmit( {
						type : "post",
						success : function(data) {
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
						}
					});
				}
			}
		});
	});
</SCRIPT>
