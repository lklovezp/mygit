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
		<script type="text/javascript" src="${common}/All.js"></script>
		<link href="${app}/css/easyUIReset.css" rel="stylesheet" type="text/css"/>
		<style type="text/css">
		label{ margin-left:5px; margin-right:20px;}
		</style>
	</head>

	<body>
		 <h1 class="h1_1">${title}</h1>
		<div style="width:95%; margin:0px auto 15px; padding:10px 0px 10px; border:1px solid #acacac;">
			<form id="queryForm" action="saveIndustry.json" method="post">
				<input type="hidden" id="id" name="id" value="${industryForm.id}"/>
				<table id="industryTable" class="dataTable" width="100%" border="0"  cellpadding="0" cellspacing="0">
					<div id="neirong" style="padding: 5px;">
						<tr>
							<th width="130">
								<font color="red"> * </font>行业名称
							</th>
							<td>
								<input type="text" class="y-text easyui-validatebox" data-options="required:true"  style="width:200px" id="name" name="name" value="${industryForm.name}"/>
							</td>
						</tr>
						<tr>
							<th width="130">
								<font color="red"> * </font>行业代码
							</th>
							<td>
							<input class="y-text easyui-validatebox" style="width:200px;" data-options="required:true" type="text" id="code" name="code" value="${industryForm.code}"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red"> * </font>执法对象类型
							</th>
							<td>
							<input class="y-text" type="text" style="width:217px;" id="lawobjtype" name="lawobjtype" value="${industryForm.lawobjtype}"/>
							</td>
						</tr>
						<tr>
							<th>
							<font color="red"> * </font>可转执法对象类型
							</th>
							<td>
								<input class="y-text" type="text"  style="width:217px;"  id="tolawobjtype" name="tolawobjtype" value="${industryForm.tolawobjtype}"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red"> * </font>排序：
							</th>
							<td>
								<input class="y-text easyui-numberbox" data-options="required:true" style="width:200px;" type="text" id="orderby" name="orderby" value="${industryForm.orderby}"/>
							</td>
						</tr>
						<tr>
							
							<th>
								是否有效
							</th>
							<td>
								<input type="checkBox" id="isActive" name="isActive"
								<c:if test="${fn:trim(industryForm.isActive) eq 'Y'}" >checked</c:if> />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input id="pfbutton" class="queryBlue" type="submit" value="提交"/>
								<input type="reset" id="J-from-reset" class="queryOrange" value="重　置"/>
							</td>
						</tr>
					</div>
				</table>
			</form>
		</div>
	</body>
</HTML>
<script language="JavaScript">

$(document).ready(function(){
	$.ajaxSetup({cache:false});
	if ('${industryForm.id}' == ''){
		$("#isActive").attr("checked", true);
	}

	if ('${industryForm.tolawobjtype}' != ''){
		$("#tolawobjtype").validatebox({required : true});
		document.getElementById('industryTable').rows[3].style.display = "";
	} else {
		$("#tolawobjtype").validatebox({required : false});
		document.getElementById('industryTable').rows[3].style.display = "none";
	}
	$('#lawobjtype').combotree({
		height:34,
		required:true,
		type:"post",
		url:'lawtypeTree.json',
		onSelect:function(oldValue){
			if (oldValue.id == '2'){
				$("#tolawobjtype").combotree({required : true});
				document.getElementById('industryTable').rows[3].style.display = "";
			} else {
				$("#tolawobjtype").combotree({required : false});
				document.getElementById('industryTable').rows[3].style.display = "none";
			}
		}
	});
	$('#tolawobjtype').combotree({
		height:34,
		type:"post",
		url:'lawtypeTree.json'
	});

	$("#queryForm").validate( {
		errorClass : "error",
		submitHandler : function(form) {
			if($("#queryForm").form("validate")){
				$(form).ajaxSubmit( {
					url: "saveIndustry.json",
					type : "post",
					success : function(data) {
						if (data.state){
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


$("#J-from-reset").click(function(){
	$("#queryForm").form("reset");
})
</script>