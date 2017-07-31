<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>化学需氧量（CODCr）污染源自动监控设施重点检查表</title>
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
<div class="headTitle">化学需氧量（CODCr）污染源自动监控设施重点检查表</div>
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
				1、取样管路位置应正确，管路畅通
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
				2、进水阀、排水阀等均正常打开
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
				3、取样管路损坏，或取样池干涸（污水间歇性排放除外）、锈蚀
				<c:if test="${zxzzForm.sf10000015 == '1'}">
					<input type="checkbox" name="sf10000015" id="sf10000015" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000015 != '1'}">
					<input type="checkbox" name="sf10000015" id="sf10000015" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="3" align="center">
				试剂单元
			</td>
			<td colspan="5" align="left">
				1、仪器各试剂瓶内，试剂量能保证运行一周以上
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
				1、试剂瓶内无试剂，试剂管未插入试剂液下
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
				2、仪器各试剂瓶内试剂在登记备案的使用有效期内
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
				2、试剂超过使用期限
				<c:if test="${zxzzForm.sf10000017 == '1'}">
					<input type="checkbox" name="sf10000017" id="sf10000017" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000017 != '1'}">
					<input type="checkbox" name="sf10000017" id="sf10000017" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
				3、实际使用的试剂的种类、浓度与登记备案相符
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
				3、实际使用的试剂的种类、浓度与登记备案不相符
				<c:if test="${zxzzForm.sf10000018 == '1'}">
					<input type="checkbox" name="sf10000018" id="sf10000018" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000018 != '1'}">
					<input type="checkbox" name="sf10000018" id="sf10000018" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="4" align="center">
				消解单元
			</td>
			<td colspan="5" align="left">
				1、消解单元应能实现试剂的快速加热
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
				1、不符合产品说明书要求的消解温度，或超出登记备案的范围；加热回流溶液不处于沸腾状态
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
				2、保持恒温消解控制
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
				2、消解瓶在非工作状态，内部有结晶、沉淀
				<c:if test="${zxzzForm.sf10000020 == '1'}">
					<input type="checkbox" name="sf10000020" id="sf10000020" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000020 != '1'}">
					<input type="checkbox" name="sf10000020" id="sf10000020" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
			</td>
			<td colspan="5" align="left">
				3、消解瓶下部有漏液现象
				<c:if test="${zxzzForm.sf10000021 == '1'}">
					<input type="checkbox" name="sf10000021" id="sf10000021" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000021 != '1'}">
					<input type="checkbox" name="sf10000021" id="sf10000021" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
			</td>
			<td colspan="5" align="left">
				4、消解时间密闭消解小于15min；加热回流小于120min，或与登记备案不符
				<c:if test="${zxzzForm.sf10000022 == '1'}">
					<input type="checkbox" name="sf10000022" id="sf10000022" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000022 != '1'}">
					<input type="checkbox" name="sf10000022" id="sf10000022" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="4" align="center">
				操作单元
			</td>
			<td colspan="5" align="left">
				1、仪器启动后，能够正常运转，添加试剂和水样 
				<c:if test="${zxzzForm.sf10000008 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check15" type="radio" name="sf10000008" value="true" checked="checked"/>
            			<label for="check15">是</label>
            		<input onclick="checkBePunished(this.value)" id="check16" type="radio" name="sf10000008" value="false" />
            			<label for="check16">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000008 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check15" type="radio" name="sf10000008" value="true" />
            			<label for="check15">是</label>
            		<input onclick="checkBePunished(this.value)" id="check16" type="radio" name="sf10000008" value="false" checked="checked"/>
            			<label for="check16">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000008 == null || zxzzForm.sf10000008 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check15" type="radio" name="sf10000008" value="true" />
            			<label for="check15">是</label>
            		<input onclick="checkBePunished(this.value)" id="check16" type="radio" name="sf10000008" value="false" />
            			<label for="check16">否</label>
            	</c:if>
			</td>
			<td colspan="5" align="left">
				1、仪器启动后电机不转动
				<c:if test="${zxzzForm.sf10000023 == '1'}">
					<input type="checkbox" name="sf10000023" id="sf10000023" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000023 != '1'}">
					<input type="checkbox" name="sf10000023" id="sf10000023" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
				2、仪器启动正常运转后，能排出废液 
				<c:if test="${zxzzForm.sf10000009 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check17" type="radio" name="sf10000009" value="true" checked="checked"/>
            			<label for="check17">是</label>
            		<input onclick="checkBePunished(this.value)" id="check18" type="radio" name="sf10000009" value="false" />
            			<label for="check18">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000009 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check17" type="radio" name="sf10000009" value="true" />
            			<label for="check17">是</label>
            		<input onclick="checkBePunished(this.value)" id="check18" type="radio" name="sf10000009" value="false" checked="checked"/>
            			<label for="check18">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000009 == null || zxzzForm.sf10000009 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check17" type="radio" name="sf10000009" value="true" />
            			<label for="check17">是</label>
            		<input onclick="checkBePunished(this.value)" id="check18" type="radio" name="sf10000009" value="false" />
            			<label for="check18">否</label>
            	</c:if>
			</td>
			<td colspan="5" align="left">
				2、仪器内部连接线路有松动脱落现象，连接管路有渗液、滴漏现象
				<c:if test="${zxzzForm.sf10000024 == '1'}">
					<input type="checkbox" name="sf10000024" id="sf10000024" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000024 != '1'}">
					<input type="checkbox" name="sf10000024" id="sf10000024" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
			</td>
			<td colspan="5" align="left">
				3、仪器启动后内部样品管路和试剂管路内无液体流动现象
				<c:if test="${zxzzForm.sf10000025 == '1'}">
					<input type="checkbox" name="sf10000025" id="sf10000025" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000025 != '1'}">
					<input type="checkbox" name="sf10000025" id="sf10000025" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
			</td>
			<td colspan="5" align="left">
				4 、仪器显示故障或报警信号
				<c:if test="${zxzzForm.sf10000026 == '1'}">
					<input type="checkbox" name="sf10000026" id="sf10000026" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000026 != '1'}">
					<input type="checkbox" name="sf10000026" id="sf10000026" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="2" align="center">
				测量单元
			</td>
			<td colspan="5" align="left">
				1、采用分光光度法测定，比色池表面无遮挡光路的污物
				<c:if test="${zxzzForm.sf10000010 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check19" type="radio" name="sf10000010" value="true" checked="checked"/>
            			<label for="check19">是</label>
            		<input onclick="checkBePunished(this.value)" id="check20" type="radio" name="sf10000010" value="false" />
            			<label for="check20">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000010 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check19" type="radio" name="sf10000010" value="true" />
            			<label for="check19">是</label>
            		<input onclick="checkBePunished(this.value)" id="check20" type="radio" name="sf10000010" value="false" checked="checked"/>
            			<label for="check20">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000010 == null || zxzzForm.sf10000010 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check19" type="radio" name="sf10000010" value="true" />
            			<label for="check19">是</label>
            		<input onclick="checkBePunished(this.value)" id="check20" type="radio" name="sf10000010" value="false" />
            			<label for="check20">否</label>
            	</c:if>
			</td>
			<td colspan="5" align="left">
				1、比色池表面有遮挡光路的污物
				<c:if test="${zxzzForm.sf10000027 == '1'}">
					<input type="checkbox" name="sf10000027" id="sf10000027" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000027 != '1'}">
					<input type="checkbox" name="sf10000027" id="sf10000027" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
				2、采用电极法测定，电极表面无污物，且能自动清洗电极。
				<c:if test="${zxzzForm.sf10000011 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check21" type="radio" name="sf10000011" value="true" checked="checked"/>
            			<label for="check21">是</label>
            		<input onclick="checkBePunished(this.value)" id="check22" type="radio" name="sf10000011" value="false" />
            			<label for="check22">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000011 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check21" type="radio" name="sf10000011" value="true" />
            			<label for="check21">是</label>
            		<input onclick="checkBePunished(this.value)" id="check22" type="radio" name="sf10000011" value="false" checked="checked"/>
            			<label for="check22">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000011 == null || zxzzForm.sf10000011 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check21" type="radio" name="sf10000011" value="true" />
            			<label for="check21">是</label>
            		<input onclick="checkBePunished(this.value)" id="check22" type="radio" name="sf10000011" value="false" />
            			<label for="check22">否</label>
            	</c:if>
			</td>
			<td colspan="5" align="left">
				2、电极表面玷污
				<c:if test="${zxzzForm.sf10000028 == '1'}">
					<input type="checkbox" name="sf10000028" id="sf10000028" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000028 != '1'}">
					<input type="checkbox" name="sf10000028" id="sf10000028" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="3" align="center">
				校准检验
			</td>
			<td colspan="5" align="left">
				1、水污染源自动监测仪频次每48小时自动进行零点和量程校准，每月至少进行一次实际水样比对试验和质控样试验 
				<c:if test="${zxzzForm.sf10000012 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check23" type="radio" name="sf10000012" value="true" checked="checked"/>
            			<label for="check23">是</label>
            		<input onclick="checkBePunished(this.value)" id="check24" type="radio" name="sf10000012" value="false" />
            			<label for="check24">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000012 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check23" type="radio" name="sf10000012" value="true" />
            			<label for="check23">是</label>
            		<input onclick="checkBePunished(this.value)" id="check24" type="radio" name="sf10000012" value="false" checked="checked"/>
            			<label for="check24">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000012 == null || zxzzForm.sf10000012 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check23" type="radio" name="sf10000012" value="true" />
            			<label for="check23">是</label>
            		<input onclick="checkBePunished(this.value)" id="check24" type="radio" name="sf10000012" value="false" />
            			<label for="check24">否</label>
            	</c:if>
			</td>
			<td colspan="5" align="left">
				1、水污染源自动监测仪零点、量程校准和比对的频次不符合HJ/T 355的相关要求
				<c:if test="${zxzzForm.sf10000029 == '1'}">
					<input type="checkbox" name="sf10000029" id="sf10000029" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000029 != '1'}">
					<input type="checkbox" name="sf10000029" id="sf10000029" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
			</td>
			<td colspan="5" align="left">
				2、现场采用零点校准液和量程校准液试验，零点和量程漂移不符合HJ/T 355的相关要求
				<c:if test="${zxzzForm.sf10000030 == '1'}">
					<input type="checkbox" name="sf10000030" id="sf10000030" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000030 != '1'}">
					<input type="checkbox" name="sf10000030" id="sf10000030" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
			</td>
			<td colspan="5" align="left">
				3、现场采用质控样试验，质控样测定的相对误差大于标准值的±10%
				<c:if test="${zxzzForm.sf10000031 == '1'}">
					<input type="checkbox" name="sf10000031" id="sf10000031" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000031 != '1'}">
					<input type="checkbox" name="sf10000031" id="sf10000031" value="1"/>
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