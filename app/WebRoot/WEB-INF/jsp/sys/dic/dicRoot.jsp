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
        <script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
	</HEAD>

	<body>
		 <h1 class="h1_1">${title}</h1> 
		<div style="width:95%; margin:0px auto 15px; padding:10px 0px 10px; border:1px solid #acacac;">
			<form id="queryForm" name="queryForm" method="post" action="">
			<table class="dataTable" width="100%" border="0"  cellpadding="0" cellspacing="0">
				<input type="hidden" value="${dicFrom.id}" id="id" name="id">
				<input type="hidden" value="${dic}" id="dic" name="dic">
				<div id="neirong" style="padding: 5px;">
					<tr>
						<th>
							<font color="red"> * </font>字典序号
						</th>
						<td colspan="3">
							<input class="y-text easyui-numberbox" data-options="required:true" type="text" onblur="selectData()" value="${dicFrom.code}"
							id="xh" name="code"/>
						</td>
					</tr>
				
					
					<tr>
						<th>
							<font color="red"> * </font>字典名称
						</th>
						<td>
							<input class="y-text easyui-validatebox" data-options="required:true"  type="text" value="${dicFrom.name}"
							id="zdmc" name="name"/>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="button" onclick="subimtData()" class="queryBlue" value="提　交"/>
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
function subimtData(){
	$.ajax( {
					type : "post",
					dataType : "json",
					url : "addAndUpdate.json",
					data:$('#queryForm').serialize(),
					success : function(data) {
						var tishi=layer.alert(data.result,{
					     	title:'信息提示',
					        icon:1,
					        shadeClose:true,
					     },
					     function(){
					    	parent.closeLayer();
					     }
					     );
					     
					    
					}
				});
}
				
function selectData(){
var str = $("#xh").val();
	$.ajax( {
		url : "selectData.json",
		data:{
			pid:str
		},
		success : function(data) {
			if(data.result!=null&&data.result!=''){
				var tishi=layer.alert(data.result,{
						     	title:'信息提示',
						        icon:1,
						        shadeClose:true
						     })
			}
				
		}
	});
}		
$(document).ready(function(){
	$("#J-from-reset").click(function(){
		$("#queryForm").form('reset');
	});
	var dic = $("#dic").val();
	if(dic=='1'){
	
		$('#xh').attr("disabled",true); 
	}
});
	

</SCRIPT>