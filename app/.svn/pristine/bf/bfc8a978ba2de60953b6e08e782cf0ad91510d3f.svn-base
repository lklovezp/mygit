<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>数据采集传输仪器重点检查表</title>
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
<div class="headTitle">数据采集传输仪器重点检查表</div>
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
				仪器参数
			</td>
			<td colspan="5" align="left">
				自动监控仪器和数据采集传输仪器中数据采集参数（如量程等）设置应一致，并与验收文件、登记备案或上一次有效性审核一致  
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
				1、参数设置与验收文件、登记备案或上一次有效性审核不一致
				<c:if test="${zxzzForm.sf10000004 == '1'}">
					<input type="checkbox" name="sf10000004" id="sf10000004" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000004 != '1'}">
					<input type="checkbox" name="sf10000004" id="sf10000004" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
			</td>
			<td colspan="5" align="left">
				2、数据采集参数高限设置过低或低限设置过高
				<c:if test="${zxzzForm.sf10000005 == '1'}">
					<input type="checkbox" name="sf10000005" id="sf10000005" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000005 != '1'}">
					<input type="checkbox" name="sf10000005" id="sf10000005" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="3" align="center">
				线路连接
			</td>
			<td colspan="5" align="left">
				自动监控仪器与数据采集传输仪器间的数据线路正常连接
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
				1、数据采集传输仪与自动监控仪器间加装有不明的数据处理设备（如可编程控制器）或信号处理设备（如滤波器等限制电流波动范围的设备）
				<c:if test="${zxzzForm.sf10000006 == '1'}">
					<input type="checkbox" name="sf10000006" id="sf10000006" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000006 != '1'}">
					<input type="checkbox" name="sf10000006" id="sf10000006" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
			</td>
			<td colspan="5" align="left">
				2、数据采集传输仪器与通信设备（调制解调器、无线发射器、光纤通讯设备）之间连接其他不明设备。
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
			</td>
			<td colspan="5" align="left">
				3、自动监控设施停止工作后，数据采集传输仪仍产生并自动发送与实际情况不相符的数据。
				<c:if test="${zxzzForm.sf10000008 == '1'}">
					<input type="checkbox" name="sf10000008" id="sf10000008" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000008 != '1'}">
					<input type="checkbox" name="sf10000008" id="sf10000008" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="1" align="center">
				数据传输
			</td>
			<td colspan="5" align="left">
				上位机与数据采集单元采集到实时数值应一致 
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
				加装软件限制数据大小和调整数据
				<c:if test="${zxzzForm.sf10000009 == '1'}">
					<input type="checkbox" name="sf10000009" id="sf10000009" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000009 != '1'}">
					<input type="checkbox" name="sf10000009" id="sf10000009" value="1"/>
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