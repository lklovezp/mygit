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
		label{ margin-left:5px; margin-right:20px;}
		</style>
	</head>

	<body>
		<h1 class="h1_1">${title}</h1>
		<div class="dataDiv" style="width:95%; margin:0px auto 25px; padding:10px 0px 30px; border:1px solid #acacac;">
			<form id="myform" name="myform" method="post" action="savezfdx.json">
				<table  class="dataTable" width="100%" border="0"  cellpadding="0" cellspacing="0">
					<input type="hidden" value="${zfdxForm.id}" id="id" name="id">
					<input type="hidden" value="${zfdxForm.lawobjtypeid}" id="lawobjtypeid" name="lawobjtypeid">
					<input type="hidden"  id="flawobjtypeid" name="flawobjtypeid" value="${lawobjtypeid}">
					<div id="neirong" style="padding: 5px;">
						<tr>
							<th width="130">
								<font color="red"> * </font>执法对象名称
							</th>
							<td>
								<input class="y-text" type="text" style="width:200px" id="name"
									name="name" value="${zfdxForm.name}" />
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
<SCRIPT LANGUAGE="JavaScript">
	function check(obj){
		if (obj.value.substring(obj.value.length - 1, obj.value.length) != "/"){
			obj.focus();
			return false;
		} else {
			return true;
		}
	}
	$(document).ready(function() {
		$("#J-from-reset").click(function() {
			$("#myform").reset();
		});
		
		$("#selectPfr").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?showBj=true&all=false&notShowZj=true&methodname=setValues&multi=true&condition=0"});

		//表单校验
		$("#myform").validate( {
			errorClass : "error",
			submitHandler : function(form) {
				$(form).ajaxSubmit( {
					type : "post",
					dataType : "json",
					url : "savezfdx.json",
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
			},
			rules : {
				"name" : {
					required : true
				}
			}
		});
	});
	
	
/**
 * 选择后回填数据
 
function setValues(id,name){
	$("#username").val(name);
	$("#userid").val(id);
	jQuery().colorbox.close();
}*/

</SCRIPT>