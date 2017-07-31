<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>紫外（UV）吸收水质自动监测仪重点检查表</title>
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
<div class="headTitle">紫外（UV）吸收水质自动监测仪重点检查表</div>
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
			<td colspan="3" rowspan="3" align="center">
				水样采集单元
			</td>
			<td colspan="5" align="left">
				1、取样管路位置正确，管路畅通
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
				1、启动仪器后取样泵无水样进入管路
				<c:if test="${zxzzForm.sf10000008 == '1'}">
					<input type="checkbox" name="sf10000008" id="sf10000008" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000008 != '1'}">
					<input type="checkbox" name="sf10000008" id="sf10000008" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
				2、进水阀、排水阀等正常打开
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
				2、取样管路存在旁路
				<c:if test="${zxzzForm.sf10000009 == '1'}">
					<input type="checkbox" name="sf10000009" id="sf10000009" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000009 != '1'}">
					<input type="checkbox" name="sf10000009" id="sf10000009" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
			</td>
			<td colspan="5" align="left">
				3、取样管路损坏，或取样池干涸（污水间歇性排放除外）、锈蚀
				<c:if test="${zxzzForm.sf10000010 == '1'}">
					<input type="checkbox" name="sf10000010" id="sf10000010" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000010 != '1'}">
					<input type="checkbox" name="sf10000010" id="sf10000010" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="7" align="center">
				操作单元
			</td>
			<td colspan="5" align="left">
				1、仪器启动后，能够正常运转，添加试剂和水样 
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
				1、仪器启动后电机不转动
				<c:if test="${zxzzForm.sf10000011 == '1'}">
					<input type="checkbox" name="sf10000011" id="sf10000011" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000011 != '1'}">
					<input type="checkbox" name="sf10000011" id="sf10000011" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
				2、仪器启动正常运转后，能排出废液 
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
				2、仪器内部连接线路有松动脱落现象，连接管路有渗液、滴漏现象
				<c:if test="${zxzzForm.sf10000012 == '1'}">
					<input type="checkbox" name="sf10000012" id="sf10000012" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000012 != '1'}">
					<input type="checkbox" name="sf10000012" id="sf10000012" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
				3、仪器光吸收系数与化学需氧量相关性等参数设置情况应与登记备案一致 
				<c:if test="${zxzzForm.sf10000005 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check9" type="radio" name="sf10000005" value="true" checked="checked"/>
            			<label for="check9">是</label>
            		<input onclick="checkBePunished(this.value)" id="check10" type="radio" name="sf10000005" value="false" />
            			<label for="check10">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000005 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check9" type="radio" name="sf10000005" value="true" />
            			<label for="check9">是</label>
            		<input onclick="checkBePunished(this.value)" id="check10" type="radio" name="sf10000005" value="false" checked="checked"/>
            			<label for="check10">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000005 == null || zxzzForm.sf10000005 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check9" type="radio" name="sf10000005" value="true" />
            			<label for="check9">是</label>
            		<input onclick="checkBePunished(this.value)" id="check10" type="radio" name="sf10000005" value="false" />
            			<label for="check10">否</label>
            	</c:if>
			</td>
			<td colspan="5" align="left">
				3、仪器启动后内部样品管路和试剂管路内无液体流动现象
				<c:if test="${zxzzForm.sf10000013 == '1'}">
					<input type="checkbox" name="sf10000013" id="sf10000013" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000013 != '1'}">
					<input type="checkbox" name="sf10000013" id="sf10000013" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
				4、吸收池具有自动清洗功能，能自动清除附着在吸收池表面上遮挡光路的污物 
				<c:if test="${zxzzForm.sf10000006 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check11" type="radio" name="sf10000006" value="true" checked="checked"/>
            			<label for="check11">是</label>
            		<input onclick="checkBePunished(this.value)" id="check12" type="radio" name="sf10000006" value="false" />
            			<label for="check12">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000006 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check11" type="radio" name="sf10000006" value="true" />
            			<label for="check11">是</label>
            		<input onclick="checkBePunished(this.value)" id="check12" type="radio" name="sf10000006" value="false" checked="checked"/>
            			<label for="check12">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000006 == null || zxzzForm.sf10000006 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check11" type="radio" name="sf10000006" value="true" />
            			<label for="check11">是</label>
            		<input onclick="checkBePunished(this.value)" id="check12" type="radio" name="sf10000006" value="false" />
            			<label for="check12">否</label>
            	</c:if>
			</td>
			<td colspan="5" align="left">
				4、仪器光吸收系数与化学需氧量相关性等参数设置情况与登记备案不一致
				<c:if test="${zxzzForm.sf10000014 == '1'}">
					<input type="checkbox" name="sf10000014" id="sf10000014" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000014 != '1'}">
					<input type="checkbox" name="sf10000014" id="sf10000014" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
			</td>
			<td colspan="5" align="left">
				5、吸收池不具备自清洗功能
				<c:if test="${zxzzForm.sf10000015 == '1'}">
					<input type="checkbox" name="sf10000015" id="sf10000015" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000015 != '1'}">
					<input type="checkbox" name="sf10000015" id="sf10000015" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
			</td>
			<td colspan="5" align="left">
				6、吸收池表面上有遮挡光路的污物
				<c:if test="${zxzzForm.sf10000016 == '1'}">
					<input type="checkbox" name="sf10000016" id="sf10000016" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000016 != '1'}">
					<input type="checkbox" name="sf10000016" id="sf10000016" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
			</td>
			<td colspan="5" align="left">
				7、仪器显示故障或报警信号
				<c:if test="${zxzzForm.sf10000017 == '1'}">
					<input type="checkbox" name="sf10000017" id="sf10000017" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000017 != '1'}">
					<input type="checkbox" name="sf10000017" id="sf10000017" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="3" align="center">
				校准检验
			</td>
			<td colspan="5" align="left">
				1、水污染源自动监测仪频次每48小时进行自动进行零点和量程校准，每月至少进行一次实际水样比对试验和质控样试验 
				<c:if test="${zxzzForm.sf10000007 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check13" type="radio" name="sf10000007" value="true" checked="checked"/>
            			<label for="check13">是</label>
            		<input onclick="checkBePunished(this.value)" id="check14" type="radio" name="sf10000007" value="false" />
            			<label for="check14">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000007 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check13" type="radio" name="sf10000007" value="true" />
            			<label for="check13">是</label>
            		<input onclick="checkBePunished(this.value)" id="check14" type="radio" name="sf10000007" value="false" checked="checked"/>
            			<label for="check14">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000007 == null || zxzzForm.sf10000007 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check13" type="radio" name="sf10000007" value="true" />
            			<label for="check13">是</label>
            		<input onclick="checkBePunished(this.value)" id="check14" type="radio" name="sf10000007" value="false" />
            			<label for="check14">否</label>
            	</c:if>
			</td>
			<td colspan="5" align="left">
				1、水污染源自动监测仪零点、量程校准和比对的频次不符合HJ/T 355的相关要求
				<c:if test="${zxzzForm.sf10000018 == '1'}">
					<input type="checkbox" name="sf10000018" id="sf10000018" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000018 != '1'}">
					<input type="checkbox" name="sf10000018" id="sf10000018" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
			</td>
			<td colspan="5" align="left">
				2、现场采用零点校准液和量程校准液试验，零点和量程漂移不符合HJ/T 355的相关要求
				<c:if test="${zxzzForm.sf10000019 == '1'}">
					<input type="checkbox" name="sf10000019" id="sf10000019" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000019 != '1'}">
					<input type="checkbox" name="sf10000019" id="sf10000019" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
			</td>
			<td colspan="5" align="left">
				3、现场采用质控样试验，质控样测定的相对误差大于标准值的±10%
				<c:if test="${zxzzForm.sf10000020 == '1'}">
					<input type="checkbox" name="sf10000020" id="sf10000020" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000020 != '1'}">
					<input type="checkbox" name="sf10000020" id="sf10000020" value="1"/>
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