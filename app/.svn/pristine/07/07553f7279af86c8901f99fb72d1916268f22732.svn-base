<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业环境风险防范措施</title>
        <script type="text/javascript" src="${app}/js.js"></script>
		<script type="text/javascript" src="${app}/data.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery }/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery }/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery }/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui }/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${easyui }/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox }/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui }/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui }/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${colorbox }/colorbox.css">
		<script type="text/javascript" src="${common}/All.js"></script>
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
		<link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
		<style>
        .basicinfoTable,.basicinfoTable td{ border: 1px solid #d4d4d4;}
        caption, th {text-align: right;}
      </style>
</head>
<body>
	<div class="h1_1" style="font-size:16px; padding-top:20px;">辐射安全基本信息</div>
	<form id="queryForm"  method="post">
		<table class="dataTable basicinfoTable" width="100%" cellpadding="0" cellspacing="0">
			<input type="hidden" value="${fsaqForm.id}" id="id" name="id">
			<input type="hidden" value="${fsaqForm.lawobjTypeId}" id="lawobjTypeId" name="lawobjTypeId">
			<input type="hidden" value="${fsaqForm.lawobjId}" id="lawobjId" name="lawobjId">
			
			<tr>
				<th width="190" bgcolor="#edfaff" align="right"><em class="mark">*</em>单位名称：</th>
				<td colspan="2">${fsaqForm.dwmc}
				<input type="hidden" value="${fsaqForm.dwmc}" id="id" name="dwmc" class="y-text">
				</td>
				
				<th width="130" bgcolor="#edfaff" align="right" >邮政编码：</th>
				<td colspan="5">${fsaqForm.yzbm}
				<input type="hidden" value="${fsaqForm.yzbm}" id="yzbm" name="yzbm">
				</td>
			</tr>
			<tr>
				<th width="190" bgcolor="#edfaff" align="right" bgcolor="#edfaff" align="right">单位地址：</th>
				<td colspan="5">${fsaqForm.adress}
				<input type="hidden" value="${fsaqForm.adress}" id="code" name="code">
				</td>
				
			</tr>
			<tr>
				<th width="190" bgcolor="#edfaff" align="right">法定代表人（负责人）：</th>
				<td colspan="2">${fsaqForm.fddbr}
				<input type="hidden" value="${fsaqForm.fddbr}" id="fddbr" name="fddbr">
				</td>
				<th width="130" bgcolor="#edfaff" align="right">法定代表人电话：</th>
				<td colspan="2">${fsaqForm.fddbrdh}
				<input type="hidden" value="${fsaqForm.fddbrdh}" id="fddbrdh" name="fddbrdh">
				</td>
			</tr>
			<tr>
				<th width="190" bgcolor="#edfaff" align="right">联 系 人：</th>
				<td colspan="2">${fsaqForm.hbfzr}
				<input type="hidden" value="${fsaqForm.hbfzr}" id="hbfzr" name="hbfzr">
				</td>
				<th width="130" bgcolor="#edfaff" align="right">电 话：</th>
				<td colspan="2">${fsaqForm.hbfzrdh}
				<input type="hidden" value="${fsaqForm.hbfzrdh}" id="hbfzrdh" name="hbfzrdh">
				</td>
			</tr>
			<tr>
				<th width="190" bgcolor="#edfaff" align="right">传 真：</th>
				<td colspan="2">
				<input class="i-text easyui-validatebox" value="${fsaqForm.hbfzrcz}" maxlength="16" id="hbfzrcz" name="hbfzrcz">
				</td>
				<th width="130" bgcolor="#edfaff" align="right">E-mail：</th>
				<td colspan="2">
				<input class="i-text easyui-validatebox" value="${fsaqForm.hbfzremail}" maxlength="16" id="hbfzremail" name="hbfzremail">
				</td>
			</tr>
			<tr>
				<th width="190" bgcolor="#edfaff" align="right">辐射安全许可证号：</th>
				<td colspan="2">
				<input class="i-text easyui-validatebox" value="${fsaqForm.xkzh}" maxlength="16" id="xkzh" name="xkzh">
				</td>
				<th width="130" bgcolor="#edfaff" align="right">许可种类与范围：</th>
				<td colspan="2">
				<input class="i-text easyui-validatebox" value="${fsaqForm.xkzlfw}" maxlength="99" id="xkzlfw" name="xkzlfw">
				</td>
			</tr>
			<tr>
				<th width="190" bgcolor="#edfaff" align="right">辐射安全与防护：</th>
				<td colspan="2">
				<input class="i-text easyui-validatebox" value="${fsaqForm.aqfh}" maxlength="99" id="aqfh" name="aqfh">
				</td>
				<th width="130" bgcolor="#edfaff" align="right">辐射安全与防护管理机构名称：</th>
				<td colspan="2">
				<input class="i-text easyui-validatebox" value="${fsaqForm.aqfhjgmc}" maxlength="99" id="aqfhjgmc" name="aqfhjgmc">
				</td>
			</tr>
			<tr>
				<th width="190" bgcolor="#edfaff" align="right">负责人：</th>
				<td colspan="2">
				<input class="i-text easyui-validatebox" value="${fsaqForm.fzr}" maxlength="15" id="fzr" name="fzr">
				</td>
				<th width="130" bgcolor="#edfaff" align="right">学历：</th>
				<td colspan="2">
				<input class="i-text easyui-validatebox" value="${fsaqForm.xl}" maxlength="15" id="xl" name="xl">
				</td>
			</tr>
			<tr>
				<th width="190" bgcolor="#edfaff" align="right">专业：</th>
				<td colspan="2">
				<input class="i-text easyui-validatebox" value="${fsaqForm.zy}" maxlength="49" id="zy" name="zy">
				</td>
				<th width="130" bgcolor="#edfaff" align="right">电 话：</th>
				<td colspan="2">
				<input class="i-text easyui-validatebox" value="${fsaqForm.dh}" maxlength="24" id="dh" name="dh">
				</td>
			</tr>
			<tr>
				<th width="190" bgcolor="#edfaff" align="right">辐射工作人员数量：</th>
				<td colspan="5">
				<input class="i-text easyui-numberbox" style="width:60px;" value="${fsaqForm.gzrysl}" maxlength="10" id="gzrysl" name="gzrysl">
				（其中：取得相应级别培训合格证人数：<input class="i-text easyui-numberbox" style="width:60px;" maxlength="10" value="${fsaqForm.hgzrs}" id="hgzrs" name="hgzrs">
				 在有效期内<input class="i-text easyui-numberbox" style="width:60px;" maxlength="10" value="${fsaqForm.yxqnrs}" id="yxqnrs" name="yxqnrs">人数）<br/>
				（其中：个人剂量监测人数：<input class="i-text easyui-numberbox" style="width:60px;" maxlength="10" value="${fsaqForm.jljcrs}" id="jljcrs" name="jljcrs">）
				</td>
				
			</tr>
			<tr>
				<th width="190" bgcolor="#edfaff" align="right">放射源及射线装置 在用放射源：</th>
				<td colspan="5">
				总数  <input class="i-text easyui-numberbox" style="width:60px;" maxlength="10" value="${fsaqForm.fsyzs}" id="fsyzs" name="fsyzs"> 枚，
				其中Ⅰ类 <input class="i-text easyui-numberbox" style="width:60px;" maxlength="10" value="${fsaqForm.fsyyil}" id="fsyyil" name="fsyyil">枚，
				Ⅱ类<input class="i-text easyui-numberbox" style="width:60px;" maxlength="10" value="${fsaqForm.fsyerl}" id="fsyerl" name="fsyerl">枚，
				Ⅲ类  <input class="i-text easyui-numberbox" style="width:60px;" maxlength="10" value="${fsaqForm.fsysanl}" id="fsysanl" name="fsysanl"> 枚，
				Ⅳ类  <input class="i-text easyui-numberbox" style="width:60px;" maxlength="10" value="${fsaqForm.fsysil}" id="fsysil" name="fsysil"> 枚，
				Ⅴ类 <input class="i-text easyui-numberbox" style="width:60px;" maxlength="10" value="${fsaqForm.fsywul}" id="fsywul" name="fsywul">枚。
				</td>
				
			</tr>
			<tr>
				<th width="190" bgcolor="#edfaff" align="right">废旧放射源：</th>
				<td colspan="2">
				Ⅲ类及以上<input class="i-text easyui-numberbox" style="width:60px;" maxlength="10" value="${fsaqForm.fjfsysanl}" id="fjfsysanl" name="fjfsysanl">枚，
				处理计划及资金落实情况：
				<textarea class="i-textarea easyui-validatebox"  style="height:60px;width:260px;" 
				 maxlength="900" onchange="this.value=this.value.substring(0, 900)" 
				 onkeydown="this.value=this.value.substring(0, 900)" 
				 onkeyup="this.value=this.value.substring(0, 900)" 
				 id="fjfsylsqk" name="fjfsylsqk">${fsaqForm.fjfsylsqk}</textarea>；
				</td>
				<td colspan="3">
				Ⅳ类及以下<input class="i-text easyui-numberbox" style="width:60px;" maxlength="10" value="${fsaqForm.fjfsysil}" id="fjfsysil" name="fjfsysil">枚，
				未知活度<input class="i-text easyui-numberbox" style="width:60px;" maxlength="10" value="${fsaqForm.fjfsysilwzhd}" id="fjfsysilwzhd" name="fjfsysilwzhd">枚；
				处理计划及资金落实情况：<br/>
				 <textarea class="i-textarea easyui-validatebox"  style="height:60px;width:260px;" 
				 maxlength="900" onchange="this.value=this.value.substring(0, 900)" 
				 onkeydown="this.value=this.value.substring(0, 900)" 
				 onkeyup="this.value=this.value.substring(0, 900)" 
				 id="fjfsysillsqk" name="fjfsysillsqk">${fsaqForm.fjfsysillsqk}</textarea>；
				</td>
				
			</tr>
			<tr>
				<th width="190" bgcolor="#edfaff" align="right">在用射线装置：</th>
				<td colspan="5">
				总数 <input class="i-text easyui-numberbox" style="width:60px;" maxlength="10" value="${fsaqForm.zysxzzzs}" id="zysxzzzs" name="zysxzzzs"> 台，
				其中Ⅰ类 <input class="i-text easyui-numberbox" style="width:60px;" maxlength="10" value="${fsaqForm.zysxzzyil}" id="zysxzzyil" name="zysxzzyil">台，
				Ⅱ类 <input class="i-text easyui-numberbox" style="width:60px;" maxlength="10" value="${fsaqForm.zysxzzerl}" id="zysxzzerl" name="zysxzzerl">台，
				Ⅲ类<input class="i-text easyui-numberbox" style="width:60px;" maxlength="10" value="${fsaqForm.zysxzzsanl}" id="zysxzzsanl" name="zysxzzsanl">台。
				</td>
							
			</tr>
			<tr align="center">
			<td colspan="6">
			<input id="save" type="submit" class="queryBlue" value="保存" />
			</td>
			<tr>
			
		</table>
	
	</form>
	<script type="text/javascript">

		$(document).ready(function() {
			//alert("pid==="+$('#pid').val());
			
			
			
			// alert("ceshi");
			//提交表单
			$("#queryForm").validate({
				
				errorClass : "error",
				submitHandler : function(form) {
					if ($("#queryForm").form("validate")){
						
					    // return;
						$(form).ajaxSubmit( {
							type : "post",
							url : "saveFsaqxx.json",
							success : function(data) {
								alert(data.msg);
								parent.ref();
								
							}
						});
					}
				}
			});

		});
	</script>
</body>
</html>
