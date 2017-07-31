<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业环境风险防范措施</title>
<script type="text/javascript" src="${app}/js.js"></script>
<script type="text/javascript" src="${app}/data.js"></script>
<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css"	type="text/css" />
<script type="text/javascript" src="${poshytip}/jquery.poshytip.min.js"></script>
<link rel="stylesheet" href="${colorbox}/colorbox.css" type="text/css">
<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
<link href="${app}/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${common}/All.js"></script>
</head>
<body>
	<div class="headTitle" style="font-size:16px; padding-top:20px;">企业环境风险防范措施</div>
	<form id="queryForm" action="" method="post">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="formtable">
			<input type="hidden" value="${hjfxffcsForm.id}" id="id" name="id">
			<input type="hidden" value="${hjfxffcsForm.pid}" id="pid" name="pid">
			
			<tr>
				<th width="170"><em class="mark">*</em>风险单元名称：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="98" data-options="required:true" type="text" id="fxdymc"  name="fxdymc" value="${hjfxffcsForm.fxdymc}" /></td>
				<th width="170">泄漏气体吸收装置：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="98" type="text" id="xszz"  name="xszz" value="${hjfxffcsForm.xszz}" /></td>
			</tr>
			<tr>
				<th rowspan="3" width="150">主要化学物质：</th>
				<th width="120">名称：</th>
				<td><input class="i-text easyui-validatebox" maxlength="98" type="text" id="zyhxwzmc" name="zyhxwzmc" style="width:130px;" value="${hjfxffcsForm.zyhxwzmc}"/></td>
				<th rowspan="4" width="150">风险特征：</th>
				<th width="120">反应条件高温高压：</th>
				<td><input class="i-text easyui-validatebox" maxlength="98" type="text" id="gwgy" name="gwgy" style="width:130px;" value="${hjfxffcsForm.gwgy}"/></td>
			</tr>
			<tr>
				<th width="90">现存量（吨）：</th>
				<td><input class="i-text easyui-numberbox" maxlength="9" type="text" id="xcl" name="xcl" style="width:130px;" value="${hjfxffcsForm.xcl}"/></td>
				<th width="90">易燃易爆：</th>
				<td><input class="i-text easyui-validatebox" maxlength="98" type="text" id="yryb" name="yryb" style="width:130px;" value="${hjfxffcsForm.yryb}"/></td>
			</tr>
			
			<tr>
				<th width="90">最大存储量（吨）：</th>
				<td><input class="i-text easyui-numberbox" maxlength="9" type="text" id="zdccl" name="zdccl" style="width:130px;" value="${hjfxffcsForm.zdccl}"/></td>
				<th width="90">化学物质易泄露：</th>
				<td><input class="i-text easyui-validatebox" maxlength="98" type="text" id="hxwzyxl" name="hxwzyxl" style="width:130px;" value="${hjfxffcsForm.hxwzyxl}"/></td>
			</tr>
			<tr>
				<th>围堰：</th>
				 <td >
				    <span style="color:#666666;font-weight:bold;line-height:24px;background-color:#f9fcfe;border:0px dotted #95b8e7;font-size:12px;text-align:center;" >无/有，有效容积（m<sup>3</sup>）：<span/>
				    <input id="wy" name="wy" type="Checkbox" onclick="" value="1" />
				 </td>
				 <td>
				   <div id="wyYou" hidden="true">
				   <input class="i-text easyui-numberbox" maxlength="9" name="yxrj" style="width:130px;" value="${hjfxffcsForm.yxrj}"/>
				   </div>
                </td>
				<th width="90">其他：</th>
				<td><input class="i-text easyui-validatebox" maxlength="98" type="text" id="qt" name="qt" style="width:130px;" value="${hjfxffcsForm.qt}"/></td>
			</tr>
			<tr>
				<th>专用排泄沟/管：</th>
				<td colspan="2">
					 <span style="color:#666666;font-weight:bold;line-height:24px;background-color:#f9fcfe;border:0px dotted #95b8e7;font-size:12px;text-align:center;" >无/有:<span/>&nbsp;&nbsp;&nbsp;&nbsp;
				     <input  type="Checkbox" id="zypxgg"	name="zypxgg" value="1"/></td>
				<th width="90">地面防渗：</th>
				<td >		
				   <span style="color:#666666;font-weight:bold;line-height:24px;background-color:#f9fcfe;border:0px dotted #95b8e7;font-size:12px;text-align:center;" >无/有，材料：<span/>
				  <input  type="Checkbox" id="dmfs" name="dmfs" value="1"/></td>
				 <td><div id="dmfsYou" hidden="true">
				  <input class="i-text" maxlength="98" name="dmfscl" style="width:130px;" value="${hjfxffcsForm.dmfscl}"/></div>
                </td>
			</tr>
			<tr>
				<th width="220">气/液体泄漏侦测、报警系统：</th>
				<td colspan="2">
				<span style="color:#666666;font-weight:bold;line-height:24px;background-color:#f9fcfe;border:0px dotted #95b8e7;font-size:12px;text-align:center;" >无/有:<span/>&nbsp;&nbsp;&nbsp;&nbsp;
				<input  type="Checkbox" id="bjxt" name="bjxt" value="1"/></td>
				<th width="220">是否接入远程监控网：</th>
				
				<td colspan="2">
				<span style="color:#666666;font-weight:bold;line-height:24px;background-color:#f9fcfe;border:0px dotted #95b8e7;font-size:12px;text-align:center;" >否/是：<span/>&nbsp;&nbsp;&nbsp;&nbsp;
				<input  type="Checkbox" id="wcjkw" name="wcjkw" value="Y"/></td>
			</tr>
			<tr>
				<th width="220">事故废水排放去向：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="98" type="text" id="pfqx"  name="pfqx" value="${hjfxffcsForm.pfqx}" /></td>
				<th width="170">清净下水排水缓冲池：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="98" type="text" id="hcc"  name="hcc" value="${hjfxffcsForm.hcc}" /></td>
			</tr>
			<tr>
				<th width="170">清净下水排放切换阀门：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="98" type="text" id="qhfm"  name="qhfm" value="${hjfxffcsForm.qhfm}" /></td>
				
			    <th width="190">事故应急池：</th>
				<td >
				    <span style="color:#666666;font-weight:bold;line-height:24px;background-color:#f9fcfe;border:0px dotted #95b8e7;font-size:12px;text-align:center;" >无/有，容积（m<sup>3</sup>）：<span/>
				    <input  type="Checkbox" id="sgyjc"	name="sgyjc" value="1"/></td>
				 <td><div id="sgyjcYou" hidden="true">
				  <input class="i-text easyui-numberbox" maxlength="9" name="sgyjcrj" style="width:130px;" value="${hjfxffcsForm.sgyjcrj}"/></div>
                </td>
			</tr>
		</table>
		<div class="t-c" style="margin-top:25px">
			<span class="y-btn"><input id="save" type="submit" value="保存" />
			</span> 
		</div>
	</form>
	<script type="text/javascript">

		$(document).ready(function() {
			//alert("pid==="+$('#pid').val());
			if('${hjfxffcsForm.wcjkw}'=="Y"){
				$("#wcjkw").attr("checked",true);
			}
			
			if('${hjfxffcsForm.bjxt}'==1){
				$("#bjxt").attr("checked",true);
			}
			
			if('${hjfxffcsForm.zypxgg}'==1){
				$("#zypxgg").attr("checked",true);
			}
			
			
			if('${hjfxffcsForm.wy}'==1){
				$("#wy").attr("checked",true);
				$('#wyYou').show();
			}else{
				$('#wyYou').hide();
			}
			$("#wy").click(function(){
				if($("#wy").attr("checked")=="checked"){
					$('#wyYou').show();
					$('#wy').val()="1";
				}else{ 
					$('#wyYou').hide();
				}
					});
			
			if('${hjfxffcsForm.dmfs}'==1){
				$("#dmfs").attr("checked",true);
				
				$('#dmfsYou').show();
				
			}else{
				$('#dmfsYou').hide();
			}
			$("#dmfs").click(function(){
				if($("#dmfs").attr("checked")=="checked"){
					$('#dmfsYou').show();
					$('#dmfs').val()="1";
				}else{ 
					$('#dmfsYou').hide();
				}
					});
			
			if('${hjfxffcsForm.sgyjc}'==1){
				$("#sgyjc").attr("checked",true);
				
				$('#sgyjcYou').show();
				
			}else{
				$('#sgyjcYou').hide();
			}
			
			$("#sgyjc").click(function(){
				if($("#sgyjc").attr("checked")=="checked"){
					$('#sgyjcYou').show();
					$('#sgyjc').val()="1";
				}else{ 
					$('#sgyjcYou').hide();
				}
					});
				
		
			
			// alert("ceshi");
			//提交表单
			$("#queryForm").validate({
				
				errorClass : "error",
				submitHandler : function(form) {
					if ($("#queryForm").form("validate")){
						
					    // return;
						$(form).ajaxSubmit( {
							type : "post",
							url : "saveHjfxffcsForm.json?",
							success : function(data) {
								alert(data.msg);
								//self.close();
								parent.refEdit();
								
							}
						});
					}
				}
			});

		});
	</script>
</body>
</html>
