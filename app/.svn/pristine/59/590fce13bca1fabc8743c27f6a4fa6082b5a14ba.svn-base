<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>流量计重点检查表</title>
	    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
        <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
	    <script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css" />
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
		<script type="text/javascript" src="${common}/All.js"></script>
<style type="text/css">
.formtable {
    border: 1px solid #d4d4d4;
}
.formtable th {
    color: #666666;
    background-color: #ffffff;
    border: 1px solid #d4d4d4;
}
.formtable td {
	color: #666666;
	border: 1px solid #d4d4d4;
}
.i-text {
    border-color: #d4d4d4 #d4d4d4 #d4d4d4 #d4d4d4;
    border-style: solid;
    border-width: 1px;
}
.i-text:hover {
    border-color: #d4d4d4 #d4d4d4 #d4d4d4 #d4d4d4;
    border-style: solid;
    border-width: 1px;
}
.combo {
    border-color: #d4d4d4;
    background-color: #ffffff;
}
.ta-da{
    overflow:scroll; 
    overflow-x:hidden; 
    height:80px;
    width:95%;
}
</style>
</head>
<body style="width: 90%;margin: 0 auto;padding-bottom: 70px;">
<div class="headTitle">流量计重点检查表</div>
<form id="myform" name="myform" method="post" action="saveZxzz.json">
<input type="hidden" id="applyId" name="applyId" value="${work.id}" />
<input type="hidden" id="zzmblx" name="zzmblx" value="${zzmblx}" />
<input type="hidden" id="id" name="id" value="${id}" />
	<div>排污单位名称：${zfdwmc}</div>
	<div class="divContainer">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formtable">
		<tr>
			<td colspan="3" align="center">
				检查项目
			</td>
			<td colspan="5" align="center">
				检查结果记录
			</td>
			<td colspan="5" align="center">
				不正常运行情形包括（不限于）以下情况
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="2" align="center">
				参数设置
			</td>
			<td colspan="5" align="left">
				1、堰槽种类、堰槽规格、转换系数等参数设置情况与验收、登记备案、最近一次有效性审核一致（适用于超声波明渠流量计）
				<c:if test="${zxzzForm.sf10000001 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check1" type="radio" name="sf10000001" value="true" checked="checked"/>
            			<label for="check1">是</label>
            		<input onclick="checkBePunished(this.value)" id="check2" type="radio" name="sf10000001" value="false" />
            			<label for="check2">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000001 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check1" type="radio" name="sf10000001" value="true" />
            			<label for="check1">是</label>
            		<input onclick="checkBePunished(this.value)" id="check2" type="radio" name="sf10000001" value="false" checked="checked"/>
            			<label for="check2">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000001 == null || zxzzForm.sf10000001 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check1" type="radio" name="sf10000001" value="true" />
            			<label for="check1">是</label>
            		<input onclick="checkBePunished(this.value)" id="check2" type="radio" name="sf10000001" value="false" />
            			<label for="check2">否</label>
            	</c:if>
			</td>
			<td colspan="5" align="left">
				1、堰槽种类、堰槽规格、转换系数等参数设置与验收、登记备案、最近一次有效性审核不一致（适用于超声波明渠流量计）
				<c:if test="${zxzzForm.sf10000005 == '1'}">
					<input type="checkbox" name="sf10000005" id="sf10000005" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000005 != '1'}">
					<input type="checkbox" name="sf10000005" id="sf10000005" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
				2、管道管径、转换系数等参数设置与验收、登记备案、最近一次有效性审核一致（适用于超声波及电磁管道流量计）
				<c:if test="${zxzzForm.sf10000002 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check3" type="radio" name="sf10000002" value="true" checked="checked"/>
            			<label for="check3">是</label>
            		<input onclick="checkBePunished(this.value)" id="check4" type="radio" name="sf10000002" value="false" />
            			<label for="check4">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000002 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check3" type="radio" name="sf10000002" value="true" />
            			<label for="check3">是</label>
            		<input onclick="checkBePunished(this.value)" id="check4" type="radio" name="sf10000002" value="false" checked="checked"/>
            			<label for="check4">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000002 == null || zxzzForm.sf10000002 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check3" type="radio" name="sf10000002" value="true" />
            			<label for="check3">是</label>
            		<input onclick="checkBePunished(this.value)" id="check4" type="radio" name="sf10000002" value="false" />
            			<label for="check4">否</label>
            	</c:if>
			</td>
			<td colspan="5" align="left">
				2、管道管径、转换系数等参数设置应与验收、登记备案、最近一次有效性审核一致（适用于超声波及电磁管道流量计）
				<c:if test="${zxzzForm.sf10000006 == '1'}">
					<input type="checkbox" name="sf10000006" id="sf10000006" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000006 != '1'}">
					<input type="checkbox" name="sf10000006" id="sf10000006" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="2" align="center">
				测量单元
			</td>
			<td colspan="5" align="left">
				1、液位测量应准确。被测量介质表面无泡沫、杂物。探头位置安装在规定的点位。（适用于超声波明渠流量计）
				<c:if test="${zxzzForm.sf10000003 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check5" type="radio" name="sf10000003" value="true" checked="checked"/>
            			<label for="check5">是</label>
            		<input onclick="checkBePunished(this.value)" id="check6" type="radio" name="sf10000003" value="false" />
            			<label for="check6">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000003 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check5" type="radio" name="sf10000003" value="true" />
            			<label for="check5">是</label>
            		<input onclick="checkBePunished(this.value)" id="check6" type="radio" name="sf10000003" value="false" checked="checked"/>
            			<label for="check6">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000003 == null || zxzzForm.sf10000003 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check5" type="radio" name="sf10000003" value="true" />
            			<label for="check5">是</label>
            		<input onclick="checkBePunished(this.value)" id="check6" type="radio" name="sf10000003" value="false" />
            			<label for="check6">否</label>
            	</c:if>
			</td>
			<td colspan="5" align="left">
				1、测量液位后按照登记备案的参数折算为流量，其与仪器显示流量的差值超过仪器说明书流量精度的要求
				<c:if test="${zxzzForm.sf10000007 == '1'}">
					<input type="checkbox" name="sf10000007" id="sf10000007" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000007 != '1'}">
					<input type="checkbox" name="sf10000007" id="sf10000007" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
				2、非金属管道安装的变送器接地环与变送器接地线开路接地正常（适用于电磁管道流量计）
				<c:if test="${zxzzForm.sf10000004 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check7" type="radio" name="sf10000004" value="true" checked="checked"/>
            			<label for="check7">是</label>
            		<input onclick="checkBePunished(this.value)" id="check8" type="radio" name="sf10000004" value="false" />
            			<label for="check8">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000004 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check7" type="radio" name="sf10000004" value="true" />
            			<label for="check7">是</label>
            		<input onclick="checkBePunished(this.value)" id="check8" type="radio" name="sf10000004" value="false" checked="checked"/>
            			<label for="check8">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000004 == null || zxzzForm.sf10000004 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check7" type="radio" name="sf10000004" value="true" />
            			<label for="check7">是</label>
            		<input onclick="checkBePunished(this.value)" id="check8" type="radio" name="sf10000004" value="false" />
            			<label for="check8">否</label>
            	</c:if>
			</td>
			<td colspan="5" align="left">
				2、非金属管道安装的变送器接地环与变送器接地线开路接地点腐蚀、开裂或断裂（适用于电磁管道流量计）
				<c:if test="${zxzzForm.sf10000008 == '1'}">
					<input type="checkbox" name="sf10000008" id="sf10000008" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000008 != '1'}">
					<input type="checkbox" name="sf10000008" id="sf10000008" value="1"/>
				</c:if>
			</td>
		</tr>
		
		<tr>
			<td colspan="3" align="center">
				备  注
			</td>
			<td colspan="10" align="left">
				<textarea class="i-text" style="width:90%;height:100px;" name="remark" id="remark">${zxzzForm.remark }</textarea>
			</td>
		</tr>
	</table>
	</div>
	<div class="rb_btn_fix">
	  <input  class="queryBlue" id="but_save" type="button" value="保存" onclick="save()" />
	</div>
</form>
</body>
</html>
<script type="text/javascript">
var applyId = $('#applyId').val();
function save() { 
   $("#but_save").attr({disabled:"true",value:"保存中"});//设置按钮属性
   $('#myform').ajaxSubmit({
	    success : function(data) {
			$("#but_save").removeAttr("disabled");//将按钮可用
			if (data.state) {
				alert(data.msg);
				parent.closeLayerIframe();
			}
		}
   });
}

function  checkBePunished(c1){
	return true;
}
</script>