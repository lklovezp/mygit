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
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
		<link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
		<link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${app}/home.css"/>
		<style>
		.basicinfoTable,.basicinfoTable td,.basicinfoTable th{ border: 1px solid #d4d4d4;}
		.basicinfoTable th{background-color: #edfaff;}
		.basicinfoTable td{text-align: left;}
		.basicinfoTable th{text-align: right;}
		</style>
	</head>

	<body>
		<div class="h1_1">${title}</div>
		<div class="divContainer">
		<form id="hfForm" name="hfForm" method="post" action="saveHfInfo.json">
		<input type="hidden" id="xfdjId" name="xfdjId" value="${xfdjId}" />
		<input type="hidden" name="id" value="${xfbjdForm.id}" />
		<center>
		
					<!-- 信访投诉回访和报出情况 -->
					<table class="dataTable basicinfoTable" width="100%" cellpadding="0" cellspacing="0">
						<tr bgcolor="#edfaff" height="30px" align="center">
						 <td colspan="8" style="text-align:center; font-size:20px;">信访回复和报出情况</td>
						</tr>
						<tr>
							<th width="120">
								回访形式：
							</th>
							<td colspan="3">
								<input class="i-text" type="text" id="hfxs" name="hfxs" value="${xfbjdForm.hfxs}"/>&nbsp;&nbsp;&nbsp;
								<input class="i-text" type="text" id="hfxsxm" name="hfxsxm" value="${xfbjdForm.hfxsxm}" tipMsg="姓名"/>&nbsp;&nbsp;
								<input class="i-text" type="text" id="hfxsdyrn" name="hfxsdyrn" value="${xfbjdForm.hfxsdyrn}" tipMsg="内容"/>
							</td>
							<th>
								回访人：
							</th>
							<td>
								<input type="hidden" id="hfr" name="hfr" value="${xfbjdForm.hfr}"/>
								<input class="i-text" type="text" id="hfrName" name="hfrName" value="${xfbjdForm.hfrName}" readOnly/>
								<a href="#" class="b-link" id="selectHfr">选择人员</a>
							</td>
						</tr>
						<tr>
							<th>
								回访日期：
							</th>
							<td>
								<input class="easyui-datetimebox" type="text" id="hfrq" name="hfrq" value="${xfbjdForm.hfrq}"/>
							</td>
							<th>
								返回人：
							</th>
							<td>
								<input class="i-text" type="text" id="fhr" name="fhr" value="${xfbjdForm.fhr}"/>
							</td>
							<th>
								返回日期：
							</th>
							<td>
								<input class="easyui-datetimebox" type="text" id="fhrq" name="fhrq" value="${xfbjdForm.fhrq}"/>
							</td>
						</tr>
						<tr>
							<th>
								接收人：
							</th>
							<td>
								<input type="hidden" id="jsr" name="jsr" value="${xfbjdForm.jsr}"/>
								${xfbjdForm.jsr}
							</td>
							<th>
								接收日期：
							</th>
							<td colspan="3">
								<input class="easyui-datetimebox" type="text" id="jssj" name="jssj" value="${xfbjdForm.jssj}"/>
							</td>
						</tr>
						<tr>
							<th>
								报出情况：
							</th>
							<td colspan="5">
								<textarea class="i-text" style="width:88%;height:100px;" name="bcqk" id="bcqk">${xfbjdForm.bcqk}</textarea>
							</td>
						</tr>
						<tr>
							<th>
								报出人：
							</th>
							<td>
								<input class="i-text" type="text" id="bcr" name="bcr" value="${xfbjdForm.bcr}"/>
							</td>
							<th>
								报出日期：
							</th>
							<td colspan="3">
								<input class="easyui-datetimebox" type="text" id="bcrq" name="bcrq" value="${xfbjdForm.bcrq}"/>
							</td>
						</tr>
					</table>
				<div class="bottomBtn" style="margin-top: 10px;">
					 <input type="button" id="savebutton"  class="queryBlue" value="保　存" onclick="baocun()"/>
                     <input type="button" id="J-from-reset" class="queryOrange" value="重　置"/>
				</div>
				</center>
			</form>
			</div>	
	</body>
</HTML>

<SCRIPT LANGUAGE="JavaScript">
$(function(){  
            inputTipText();  //初始化Input的灰色提示信息  
	}); 
function inputTipText(){   
    $("input[tipMsg]").each(function(){  
        if($(this).val() == ""){  
        var oldVal=$(this).attr("tipMsg");  
        if($(this).val()==""){$(this).attr("value",oldVal).css({"color":"#888"});}  
        $(this)
           .css({"color":"#888"})     //灰色  
           .focus(function(){  
            if($(this).val()!=oldVal){$(this).css({"color":"#000"})}else{$(this).val("").css({"color":"#888"})}  
           })  
           .blur(function(){  
            if($(this).val()==""){$(this).val(oldVal).css({"color":"#888"})}  
           })  
           .keydown(function(){$(this).css({"color":"#000"})});  
        }  
    });  
}   
$(document).ready(function(){
	$("#J-from-reset").click(function(){
	   $("#hfForm").form("reset");
	});
	//回访形式下拉框
	$('#hfxs').combobox({
		url:'dicList.json?type=26',
		valueField:'id',
		textField:'name'
	});
	//选择回访人
    $("#selectHfr").colorbox({iframe:true,width:"300", height:"380",href:"userPubQuery.htm?all=true&notShowZj=false&notShowSys=true&methodname=setUserInfoHfr&multi=false"});
	//任务来源下拉框
	$('#xfly').combobox({
		url:'dicList.json?type=20',
		valueField:'id',
		textField:'name'
	});
	
	$("#hfrq").datetimebox({
    		showSeconds:false
	});
	$("#fhrq").datetimebox({
    		showSeconds:false
	});
	$("#jssj").datetimebox({
    		showSeconds:false
	});
	$("#bcrq").datetimebox({
    		showSeconds:false
	});
		
});
//设置选择回访人
function setUserInfoHfr(id,name) {
	$("#hfr").val(id);
	$("#hfrName").val(name);
	jQuery().colorbox.close();
}
//保存
function baocun(){
    $('#hfForm').attr('action','saveHfInfo.json?xfdjId='+$("#xfdjId").val());
	$('#hfForm').ajaxSubmit(function(data){
   		if(data.state){
			alert(data.msg);
			//self.close();
			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
			parent.layer.close(index);
		}else{
			$.messager.alert('回访信息保存:',data.msg);
		}
   	});
}
</SCRIPT>