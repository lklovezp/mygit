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
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/default/easyui.css">
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css">
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
    <link href="${app}/common.css" rel="stylesheet" type="text/css" />
    <!-- 任务管理 css-->
    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
    <!--时间插件 my97-->
    <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
	</head>

	<body>
		<div class="headTitle">${title}</div>
		<div  style="padding: 20px;">
			<form id="myform" name="myform" method="post" action="quarterChecktimeSetSave.json">
				<table class="dataTable" width="95%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<input type="hidden" value="${quarterChecktimeSetForm.id}" id="id" name="id">
					<div id="neirong" style="padding: 5px;">
						<tr>
							<td align="center"><span class="mark">*</span>区域：</td>
							<td>
							<input class="y-text" id="areaid" name="area" data-options="required:true" type="text" value="${areaid}" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<span class="mark">*</span>年份：
							</td>
							<td>
								<input class="y-text" data-options="required:true"  type="text" id="year" name="year" value="${quarterChecktimeSetForm.year}" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<span class="mark">*</span>季度：
							</td>
							<td>
								<input id="quarter" data-options="required:true" name="quarter" class="y-text"  value="${quarterChecktimeSetForm.quarter}" />
							</td>
						</tr>
						<tr>
							<td align="center">
								<span class="mark">*</span>抽查时间：
							</td>
							<td>
								<input type="text" class="y-dateTime" id="time" data-options="required:true" name="time" value="${quarterChecktimeSetForm.time}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:128px;"/>
							</td>
						</tr>
						<tr>
							<td align="center">
								是否有效：
							</td>
							<td>
								<input type="checkBox" id="isActive" name="isActive"
									<c:if test="${fn:trim(quarterChecktimeSetForm.isActive) eq 'Y'}" >checked</c:if> />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								 <input type="submit"  class="queryBlue"value="提交">
								&nbsp;
								<input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
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
	//区域
	$('#areaid').combobox({
		height:34,
		url : 'queryAreaComboWithCur.json?fid='+Math.random(),
		valueField : 'id',
		textField : 'name'
	});
	$.ajaxSetup({cache:false});
	if ("${quarterChecktimeSetForm.id}" == ''){
		$("#isActive").attr("checked", true);
	}else{
		$("#year").combobox({disabled: true});	
		$("#quarter").combobox({disabled: true});
	}
		var year=new Date().getFullYear(); 
		//年度下拉
		$('#year').combobox({
			height:34,
			data:[{'id':year,'name':year+'年'},{'id':year+1,'name':year+1+'年'},
				  {'id':year+2,'name':year+2+'年'},{'id':year+3,'name':year+3+'年'},
				  {'id':year+4,'name':year+4+'年'}],
			editable:false,
			valueField:'id',
			textField:'name'
		});
		//季度下拉
		$('#quarter').combobox({
			height:34,
			url:'quarterList.json',
			editable:false,
			valueField:'id',
			textField:'name'
		});		
	$("#J-from-reset").click(function(){
	   $("#myform").reset();
	});
   	//表单校验
    $("#myform").validate({
		errorClass: "error",
		submitHandler:function(form){
			if ($("#myform").form("validate")){
				$(form).ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"quarterChecktimeSetSave.json",
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
							
						}else{
							var tishi=layer.alert(data.msg,{
						     	title:'保存季度抽查时间',
						        icon:1,
						        shadeClose:true,
						     },
						     function(){
						        layer.close(tishi);
						     }
						     );
						}
					}
				});
			}
		}
	});
});
</SCRIPT>