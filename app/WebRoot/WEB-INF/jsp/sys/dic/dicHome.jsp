<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/default/easyui.css">
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css"	href="${colorbox}/colorbox2.css">
		<!-- app -->
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />

	</HEAD>
	<body>
		<div style="width:95%; margin:30px auto 15px; padding:10px 0px 10px; border:1px solid #acacac;">
			<form id="myform" name="myform" method="post"  action="uploadDicFile.json">
				<table 	class="dataTable" width="100%" border="0"  cellpadding="0" cellspacing="0">
					<tr>
						<td align="center">
							选择文件：
						</td>
						<td>
							<input type="file" id="dicfilepath" name="dicfilepath" size="20">

						</td>
					</tr>
					<tr>
						<td align="center">
							是否删除现有数据：
						</td>
						<td>
							<input type="checkBox" id="isDel" name="isDel" checked="Y" />
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<input type="submit" class="queryBlue" value="提　交"/>
       						<input type="button" id="dicDataBackUp" name="dicDataBackUp" value="字典备份" class="queryOrange" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">

$(document).ready(function(){

		$('#myform').submit(function(){ 
			
		   $.ajax({
			   	 url: "uploadDicFile.json",
			   	 data : "dicfilepath="+encodeURI(encodeURI($('#dicfilepath').val()))+"&isDel="+$("#isDel").val(),  
			     success: function(data){
			           $.messager.alert('保存字典信息:',data.msg, "info");
			     }
			 });
	   		return false;  
		});
		$('#dicDataBackUp').click(function(){
			$.ajax({
				  url: "dicDataBackUp.json",
				  success:function(data){
					$.messager.alert('提示:',data.msg);
					ref();
				  }
			});
		});
});

</SCRIPT>
