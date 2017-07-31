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
	<div class="breadClumbs"> 系统管理&nbsp;&gt;&nbsp;${title}</div>
		<h1 class="h1_1">随机抽查设置</h1>
		<div class="dataDiv" style="width:95%; margin:0px auto 25px; padding:10px 0px 30px; border:0px solid #acacac;">
			<form id="myform" name="myform" method="post">
				<table 	 class="dataTable" width="100%" border="1"  cellpadding="0" cellspacing="0">
					<input type="hidden" value="${configCheckForm.id}" id="id" name="id">
					<input type="hidden" value="${configCheckForm.ybqybl}" id="ybbl" name="ybbl">
					<div id="neirong" style="padding: 5px;">
						<tr height="50">
							<td align="right"  width="20%">区域：</td>
							<td align="left" width="73%"  colspan="6">
							<input class="y-text" id="areaid" name="areaid"	type="text" value="${areaid}" />
							</td>
						</tr>
						<tr height="50">
						   
							<td align="right">
								<font color="red"> * </font>随机抽查规则设置：
							</td>
							<td align="left" colspan="6">
								<input  type="radio" id="" 
								<c:if test="${fn:trim(configCheckForm.gzsz) eq '1'}" >checked="checked"</c:if>
								 name="gzsz" value="1" />随机抽选主办人
								<input type="radio" id="" 
								<c:if test="${fn:trim(configCheckForm.gzsz) eq '2'}" >checked="checked"</c:if>
								name="gzsz" value="2" />随机抽选主办人协办人
								<input  type="radio" id="" 
								<c:if test="${fn:trim(configCheckForm.gzsz) eq '3'}" >checked="checked"</c:if>
								name="gzsz" value="3" />随机任务派发到企业所属监管部门领导
								</br></br>
								<input  type="radio" id="" 
								<c:if test="${fn:trim(configCheckForm.gzsz) eq '4'}" >checked="checked"</c:if>
								name="gzsz" value="4" />随机派发到企业所属部门人员(主办人)
								<input  type="radio" id="" 
								<c:if test="${fn:trim(configCheckForm.gzsz) eq '5'}" >checked="checked"</c:if>
								name="gzsz" value="5" />随机派发到企业所属部门人员(主办人，协办人)
							</td>
						</tr>
						<tr height="50">
							<td align="right" rowspan="3" colspan="1">
								<font color="red"> * </font>一般企业季度抽查比例：
							</td>
							<td rowspan="1" colspan="1">
							    <span>一月&nbsp;&nbsp;1&nbsp;:&nbsp;</span>
								<input id="ybqyjan" type="text" name="ybqyjan" class="y-text easyui-numberbox" style="width:40px;" value="${configCheckForm.ybqyjan}" />
							</td>
							<td>
								<span>二月&nbsp;&nbsp;1&nbsp;:&nbsp;</span>
								<input id="ybqyfeb" type="text" name="ybqyfeb" class="y-text easyui-numberbox" style="width:40px;" value="${configCheckForm.ybqyfeb}" />
							</td>
							<td>
								<span>三月&nbsp;&nbsp;1&nbsp;:&nbsp;</span>
								<input id="ybqymar" type="text" name="ybqymar" class="y-text easyui-numberbox" style="width:40px;" value="${configCheckForm.ybqymar}" />
							</td>
							<td>
								<span>四月&nbsp;&nbsp;1&nbsp;:&nbsp;</span>
								<input id="ybqyapr" type="text" name="ybqyapr" class="y-text easyui-numberbox" style="width:40px;" value="${configCheckForm.ybqyapr}" />
							</td>
							<td>
								<span>五月&nbsp;&nbsp;1&nbsp;:&nbsp;</span>
								<input id="ybqymay" type="text" name="ybqymay" class="y-text easyui-numberbox" style="width:40px;" value="${configCheckForm.ybqymay}" />
							</td>
							<td>
								<span>六月&nbsp;&nbsp;1&nbsp;:&nbsp;</span>
								<input id="ybqyjun" type="text" name="ybqyjun" class="y-text easyui-numberbox" style="width:40px;" value="${configCheckForm.ybqyjun}" />
							</td>
						</tr>
						<tr>
							<td>
								<span>七月&nbsp;&nbsp;1&nbsp;:&nbsp;</span>
								<input id="ybqyjul" type="text" name="ybqyjul" class="y-text easyui-numberbox" style="width:40px;" value="${configCheckForm.ybqyjul}" />
							</td>
							<td>
								<span>八月&nbsp;&nbsp;1&nbsp;:&nbsp;</span>
								<input id="ybqyaug" type="text" name="ybqyaug" class="y-text easyui-numberbox" style="width:40px;" value="${configCheckForm.ybqyaug}" />
							</td>
							<td>
								<span>九月&nbsp;&nbsp;1&nbsp;:&nbsp;</span>
								<input id="ybqysep" type="text" name="ybqysep" class="y-text easyui-numberbox" style="width:40px;" value="${configCheckForm.ybqysep}" />
							</td>
							<td>
								<span>十月&nbsp;&nbsp;1&nbsp;:&nbsp;</span>
								<input id="ybqyoct" type="text" name="ybqyoct" class="y-text easyui-numberbox" style="width:40px;" value="${configCheckForm.ybqyoct}" />
							</td>
							<td>
								<span>十一月&nbsp;1&nbsp;:</span>
								<input id="ybqynov" type="text" name="ybqynov" class="y-text easyui-numberbox" style="width:44px;" value="${configCheckForm.ybqynov}" />
							</td>
							<td>
								<span>十二月&nbsp;1&nbsp;:</span>
								<input id="ybqydec" type="text" name="ybqydec" class="y-text easyui-numberbox" style="width:44px;" value="${configCheckForm.ybqydec}" />
								
							</td>
						</tr>
						<tr>
							<td colspan="6">			
								<font color="red">即在编在岗监察人员数量：一般企业季度抽查比例(不小于1)，其中在编在岗监察人员数为: ${userSize} </font>
							</td>
						</tr>
						<tr height="50">
							<td align="right" rowspan="2">
								<font color="red"> * </font>重点企业季度抽查比例：
							</td>
							<td align="left">
							    <span>一月&nbsp;&nbsp;</span>
								<input id="zdqyjan" type="text" style="width:40px;" name="zdqyjan" class="y-text easyui-numberbox" value="${configCheckForm.zdqyjan}" />
								<span>&nbsp;%</span>
							</td>
							<td>
								<span>二月&nbsp;&nbsp;</span>
								<input id="zdqyfeb" type="text" style="width:40px;" name="zdqyfeb" class="y-text easyui-numberbox" value="${configCheckForm.zdqyfeb}" />
								<span>&nbsp;%</span>
							</td>
							<td>
								<span>三月&nbsp;&nbsp;</span>
								<input id="zdqymar" type="text" style="width:40px;" name="zdqymar" class="y-text easyui-numberbox" value="${configCheckForm.zdqymar}" />
								<span>&nbsp;%</span>
							</td>
							<td>
								<span>四月&nbsp;&nbsp;</span>
								<input id="zdqyapr" type="text" style="width:40px;" name="zdqyapr" class="y-text easyui-numberbox" value="${configCheckForm.zdqyapr}" />
								<span>&nbsp;%</span>
							</td>
							<td>
								<span>五月&nbsp;&nbsp;</span>
								<input id="zdqymay" type="text" style="width:40px;" name="zdqymay" class="y-text easyui-numberbox" value="${configCheckForm.zdqymay}" />
								<span>&nbsp;%</span>
							</td>
							<td>
								<span>六月&nbsp;&nbsp;</span>
								<input id="zdqyjun" type="text" style="width:40px;" name="zdqyjun" class="y-text easyui-numberbox" value="${configCheckForm.zdqyjun}" />
								<span>&nbsp;%</span>
								
							</td>
						</tr>
						<tr>
							<td>
								<span>七月&nbsp;&nbsp;</span>
								<input id="zdqyjul" type="text" style="width:40px;" name="zdqyjul" class="y-text easyui-numberbox" value="${configCheckForm.zdqyjul}" />
								<span>&nbsp;%</span>
							</td>
							<td>
								<span>八月&nbsp;&nbsp;</span>
								<input id="zdqyaug" type="text" style="width:40px;" name="zdqyaug" class="y-text easyui-numberbox" value="${configCheckForm.zdqyaug}" />
								<span>&nbsp;%</span>
							</td>
							<td>
								<span>九月&nbsp;&nbsp;</span>
								<input id="zdqysep" type="text" style="width:40px;" name="zdqysep" class="y-text easyui-numberbox" value="${configCheckForm.zdqysep}" />
								<span>&nbsp;%</span>
							</td>
							<td>
								<span>十月&nbsp;&nbsp;</span>
								<input id="zdqyoct" type="text" style="width:40px;" name="zdqyoct" class="y-text easyui-numberbox" value="${configCheckForm.zdqyoct}" />
								<span>&nbsp;%</span>
							</td>
							<td>
								<span>十一月</span>
								<input id="zdqynov" type="text" style="width:33px;" name="zdqynov" class="y-text easyui-numberbox" value="${configCheckForm.zdqynov}" />
								<span>&nbsp;%</span>
							</td>
							<td>
								<span>十二月</span>
								<input id="zdqydec" type="text" style="width:33px;" name="zdqydec" class="y-text easyui-numberbox" value="${configCheckForm.zdqydec}" />
								<span>&nbsp;%</span>
							</td>
						</tr>
						<tr height="50">
							<td align="right">
								<font color="red"> * </font>特殊监管企业抽查比例：
							</td>
							<td align="left" colspan="6">
								<input  type="radio" id="" name="tsjgqybl"
								<c:if test="${fn:trim(configCheckForm.tsjgqybl) eq '1'}" >checked="checked"</c:if>
								 value="1" />按季度抽查一遍
								<input type="radio" id="" name="tsjgqybl"
								<c:if test="${fn:trim(configCheckForm.tsjgqybl) eq '2'}" >checked="checked"</c:if>
								 value="2" />按月份抽查一遍
							</td>
						</tr>
						
						<tr height="50">
							<td align="center" colspan="7">
								<span class="btn btn-ok"> <input type="submit" class="queryBlue" value="提交">
								</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								
							</td>
						</tr>
					</div>
				</table>
			</form>
		</div>
	</body>
</HTML>
<script type="text/javascript">

$(document).ready(function(){
	//区域
	$('#areaid').combobox({
		height:34,
		url : 'queryAreaComboWithCur.json?fid='+Math.random(),
		valueField : 'id',
		textField : 'name',
		onSelect: function (param) {//做区域部门的联动选择
		   window.location.href='randomSet.htm?areaid='+param.id;
		     
	       }
	});
   	//表单校验
    $("#myform").validate({
		errorClass: "error",
		submitHandler:function(form){
			var gzsz=document.getElementsByName("gzsz");
			var gzIs=false;
			for(var i=0;i<gzsz.length;i++){
				 if(gzsz[i].checked){
					 gzIs=true;
				 }
			}
			if(!gzIs){
				alert("请选择抽查规则！");
				return false;
			}
			
			var tsjgqybl=document.getElementsByName("tsjgqybl");
			var tsIs=false;
			for(var i=0;i<tsjgqybl.length;i++){
				 if(tsjgqybl[i].checked){
					 tsIs=true;
				 }
			}
			if(!tsIs){
				alert("请选择特殊企业抽查规则！");
				return false;
			}
			if ($("#myform").form("validate")){
				
				$(form).ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"configCheckSave.json",
					success: function(data){
						if(data.state){
							var tishi=layer.alert(data.msg,{
						     	title:'信息提示',
						        icon:1,
						        shadeClose:true,
						     },
						     function(){
						        layer.close(tishi);
						     }
						     );
						}else{
							var tishi=layer.alert(data.msg,{
						     	title:'信息提示',
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