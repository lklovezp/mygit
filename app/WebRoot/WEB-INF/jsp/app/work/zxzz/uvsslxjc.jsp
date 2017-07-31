<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>污染源自动监控设施例行检查表</title>
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
<div class="headTitle">污染源自动监控设施例行检查表</div>
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
			<td colspan="7" align="center">
				检查内容
			</td>
			<td colspan="3" width="90px" align="center">
				结果记录
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="3" align="center">
				排污口
			</td>
			<td colspan="7" align="left">
				1、排污口应与污染源自动监控设施竣工验收一致
			</td>
			<td colspan="3" align="center">
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
		</tr>
		<tr>
			<td colspan="7" align="left">
				2、排污口应与污染源自动监控设施登记备案一致
			</td>
			<td colspan="3" align="center">
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
		</tr>
		<tr>
			<td colspan="7" align="left">
				3、排污口应与最近一次污染源自动监测数据有效性审核一致
			</td>
			<td colspan="3" align="center">
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
		</tr>
		<tr>
			<td colspan="1" rowspan="5" align="center">
				采样点位
			</td>
			<td colspan="2" rowspan="2" align="center">
				废水
			</td>
			<td colspan="7" align="left">
				1、采样位置位于渠道计量水槽流路的中央，且采样口采水的前端设在下流的方向 
			</td>
			<td colspan="3" align="center">
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
		</tr>
		<tr>
			<td colspan="7" align="left">
				2、测量合流排水时，在合流后充分混合的场所采水 
			</td>
			<td colspan="3" align="center">
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
		</tr>
		<tr>
			<td colspan="2" rowspan="3" align="center">
				废气
			</td>
			<td colspan="7" align="left">
				1、采样点位应选择在垂直管段和烟道负压区域 
			</td>
			<td colspan="3" align="center">
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
		</tr>
		<tr>
			<td colspan="7" align="left">
				2、采样点位应避开烟道弯头和断面急剧变化的部位，尽可能选择在气流稳定的断面，且采样点位前直管段的长度应大于后直管段的长度 
			</td>
			<td colspan="3" align="center">
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
		</tr>
		<tr>
			<td colspan="7" align="left">
				3、若一个固定污染源排气先通过多个烟道后进入该固定污染源的总排气管时，采样点位应设置在该固定污染源的总排气管上 
			</td>
			<td colspan="3" align="center">
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
		</tr>
		<tr>
			<td colspan="3" align="center">
				监测站房
			</td>
			<td colspan="7" align="left">
				监测站房内应有空调、不间断电源、灭火设备、给排水设施。各项环境条件满足仪器设备正常工作的要求 
			</td>
			<td colspan="3" align="center">
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
		</tr>
		<tr>
			<td colspan="3" align="center">
				擅自拆除、闲置、关停检查
			</td>
			<td colspan="7" align="left">
				污染源自动监控设施是否未经环保部门批准拆除、闲置和关闭停运 
			</td>
			<td colspan="3" align="center">
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
		</tr>
		<tr>
			<td colspan="3" rowspan="6" align="center">
				变更情况
			</td>
			<td colspan="7" align="left">
				1、污染源自动监控设施及其辅助设备类型、型号、位置、数量等应与污染源自动监控设施竣工验收一致  
			</td>
			<td colspan="3" align="center">
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
		</tr>
		<tr>
			<td colspan="7" align="left">
				2、污染源自动监控设施及其辅助设备类型、型号、位置、数量等应与污染源自动监控设施登记备案一致  
			</td>
			<td colspan="3" align="center">
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
		</tr>
		<tr>
			<td colspan="7" align="left">
				3、污染源自动监控设施及其辅助设备类型、型号、位置、数量等应与最近一次污染源自动监测数据有效性审核一致  
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000013 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check25" type="radio" name="sf10000013" value="true" checked="checked"/>
            			<label for="check25">是</label>
            		<input onclick="checkBePunished(this.value)" id="check26" type="radio" name="sf10000013" value="false" />
            			<label for="check26">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000013 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check25" type="radio" name="sf10000013" value="true" />
            			<label for="check25">是</label>
            		<input onclick="checkBePunished(this.value)" id="check26" type="radio" name="sf10000013" value="false" checked="checked"/>
            			<label for="check26">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000013 == null || zxzzForm.sf10000013 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check25" type="radio" name="sf10000013" value="true" />
            			<label for="check25">是</label>
            		<input onclick="checkBePunished(this.value)" id="check26" type="radio" name="sf10000013" value="false" />
            			<label for="check26">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				4、污染源自动监控设施采样点位置应与验收一致  
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000014 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check27" type="radio" name="sf10000014" value="true" checked="checked"/>
            			<label for="check27">是</label>
            		<input onclick="checkBePunished(this.value)" id="check28" type="radio" name="sf10000014" value="false" />
            			<label for="check28">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000014 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check27" type="radio" name="sf10000014" value="true" />
            			<label for="check27">是</label>
            		<input onclick="checkBePunished(this.value)" id="check28" type="radio" name="sf10000014" value="false" checked="checked"/>
            			<label for="check28">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000014 == null || zxzzForm.sf10000014 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check27" type="radio" name="sf10000014" value="true" />
            			<label for="check27">是</label>
            		<input onclick="checkBePunished(this.value)" id="check28" type="radio" name="sf10000014" value="false" />
            			<label for="check28">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				5、污染源自动监控设施采样点位置应与登记备案一致  
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000015 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check29" type="radio" name="sf10000015" value="true" checked="checked"/>
            			<label for="check29">是</label>
            		<input onclick="checkBePunished(this.value)" id="check30" type="radio" name="sf10000015" value="false" />
            			<label for="check30">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000015 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check29" type="radio" name="sf10000015" value="true" />
            			<label for="check29">是</label>
            		<input onclick="checkBePunished(this.value)" id="check30" type="radio" name="sf10000015" value="false" checked="checked"/>
            			<label for="check30">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000015 == null || zxzzForm.sf10000015 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check29" type="radio" name="sf10000015" value="true" />
            			<label for="check29">是</label>
            		<input onclick="checkBePunished(this.value)" id="check30" type="radio" name="sf10000015" value="false" />
            			<label for="check30">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				6、污染源自动监控设施采样点位置应与最近一次有效性审核一致  
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000016 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check31" type="radio" name="sf10000016" value="true" checked="checked"/>
            			<label for="check31">是</label>
            		<input onclick="checkBePunished(this.value)" id="check32" type="radio" name="sf10000016" value="false" />
            			<label for="check32">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000016 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check31" type="radio" name="sf10000016" value="true" />
            			<label for="check31">是</label>
            		<input onclick="checkBePunished(this.value)" id="check32" type="radio" name="sf10000016" value="false" checked="checked"/>
            			<label for="check32">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000016 == null || zxzzForm.sf10000016 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check31" type="radio" name="sf10000016" value="true" />
            			<label for="check31">是</label>
            		<input onclick="checkBePunished(this.value)" id="check32" type="radio" name="sf10000016" value="false" />
            			<label for="check32">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="1" rowspan="13" align="center">
				运行状况检查
			</td>
			<td colspan="2" rowspan="2" align="center">
				工作状况
			</td>
			<td colspan="7" align="left">
				1、自动监控设施各组成部分处于完好状态，正常运转 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000017 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check33" type="radio" name="sf10000017" value="true" checked="checked"/>
            			<label for="check33">是</label>
            		<input onclick="checkBePunished(this.value)" id="check34" type="radio" name="sf10000017" value="false" />
            			<label for="check34">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000017 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check33" type="radio" name="sf10000017" value="true" />
            			<label for="check33">是</label>
            		<input onclick="checkBePunished(this.value)" id="check34" type="radio" name="sf10000017" value="false" checked="checked"/>
            			<label for="check34">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000017 == null || zxzzForm.sf10000017 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check33" type="radio" name="sf10000017" value="true" />
            			<label for="check33">是</label>
            		<input onclick="checkBePunished(this.value)" id="check34" type="radio" name="sf10000017" value="false" />
            			<label for="check34">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				2、各分析仪器产生的含有危险废物的废液有专门收集装置 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000018 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check35" type="radio" name="sf10000018" value="true" checked="checked"/>
            			<label for="check35">是</label>
            		<input onclick="checkBePunished(this.value)" id="check36" type="radio" name="sf10000018" value="false" />
            			<label for="check36">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000018 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check35" type="radio" name="sf10000018" value="true" />
            			<label for="check35">是</label>
            		<input onclick="checkBePunished(this.value)" id="check36" type="radio" name="sf10000018" value="false" checked="checked"/>
            			<label for="check36">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000018 == null || zxzzForm.sf10000018 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check35" type="radio" name="sf10000018" value="true" />
            			<label for="check35">是</label>
            		<input onclick="checkBePunished(this.value)" id="check36" type="radio" name="sf10000018" value="false" />
            			<label for="check36">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2" rowspan="3" align="center">
				数据传输及存储
			</td>
			<td colspan="7" align="left">
				1、污染源自动监控设施应按要求正常工作并传输数据 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000019 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check37" type="radio" name="sf10000019" value="true" checked="checked"/>
            			<label for="check37">是</label>
            		<input onclick="checkBePunished(this.value)" id="check38" type="radio" name="sf10000019" value="false" />
            			<label for="check38">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000019 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check37" type="radio" name="sf10000019" value="true" />
            			<label for="check37">是</label>
            		<input onclick="checkBePunished(this.value)" id="check38" type="radio" name="sf10000019" value="false" checked="checked"/>
            			<label for="check38">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000019 == null || zxzzForm.sf10000019 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check37" type="radio" name="sf10000019" value="true" />
            			<label for="check37">是</label>
            		<input onclick="checkBePunished(this.value)" id="check38" type="radio" name="sf10000019" value="false" />
            			<label for="check38">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				2、分析仪器数据、数采仪数据、监控中心数据应一致
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000020 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check39" type="radio" name="sf10000020" value="true" checked="checked"/>
            			<label for="check39">是</label>
            		<input onclick="checkBePunished(this.value)" id="check40" type="radio" name="sf10000020" value="false" />
            			<label for="check40">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000020 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check39" type="radio" name="sf10000020" value="true" />
            			<label for="check39">是</label>
            		<input onclick="checkBePunished(this.value)" id="check40" type="radio" name="sf10000020" value="false" checked="checked"/>
            			<label for="check40">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000020 == null || zxzzForm.sf10000020 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check39" type="radio" name="sf10000020" value="true" />
            			<label for="check39">是</label>
            		<input onclick="checkBePunished(this.value)" id="check40" type="radio" name="sf10000020" value="false" />
            			<label for="check40">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				3、历史数据完整，应保存一年以上 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000021 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check41" type="radio" name="sf10000021" value="true" checked="checked"/>
            			<label for="check41">是</label>
            		<input onclick="checkBePunished(this.value)" id="check42" type="radio" name="sf10000021" value="false" />
            			<label for="check42">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000021 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check41" type="radio" name="sf10000021" value="true" />
            			<label for="check41">是</label>
            		<input onclick="checkBePunished(this.value)" id="check42" type="radio" name="sf10000021" value="false" checked="checked"/>
            			<label for="check42">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000021 == null || zxzzForm.sf10000021 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check41" type="radio" name="sf10000021" value="true" />
            			<label for="check41">是</label>
            		<input onclick="checkBePunished(this.value)" id="check42" type="radio" name="sf10000021" value="false" />
            			<label for="check42">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2" rowspan="3" align="center">
				运行维护记录
			</td>
			<td colspan="7" align="left">
				1、废水自动监控设施运行维护管理符合HJ/T 355的有关规定 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000022 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check43" type="radio" name="sf10000022" value="true" checked="checked"/>
            			<label for="check43">是</label>
            		<input onclick="checkBePunished(this.value)" id="check44" type="radio" name="sf10000022" value="false" />
            			<label for="check44">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000022 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check43" type="radio" name="sf10000022" value="true" />
            			<label for="check43">是</label>
            		<input onclick="checkBePunished(this.value)" id="check44" type="radio" name="sf10000022" value="false" checked="checked"/>
            			<label for="check44">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000022 == null || zxzzForm.sf10000022 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check43" type="radio" name="sf10000022" value="true" />
            			<label for="check43">是</label>
            		<input onclick="checkBePunished(this.value)" id="check44" type="radio" name="sf10000022" value="false" />
            			<label for="check44">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				2、废气自动监控设施运行维护管理符合HJ/T 75的有关规定 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000023 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check45" type="radio" name="sf10000023" value="true" checked="checked"/>
            			<label for="check45">是</label>
            		<input onclick="checkBePunished(this.value)" id="check46" type="radio" name="sf10000023" value="false" />
            			<label for="check46">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000023 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check45" type="radio" name="sf10000023" value="true" />
            			<label for="check45">是</label>
            		<input onclick="checkBePunished(this.value)" id="check46" type="radio" name="sf10000023" value="false" checked="checked"/>
            			<label for="check46">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000023 == null || zxzzForm.sf10000023 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check45" type="radio" name="sf10000023" value="true" />
            			<label for="check45">是</label>
            		<input onclick="checkBePunished(this.value)" id="check46" type="radio" name="sf10000023" value="false" />
            			<label for="check46">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				3、自动监控设施运行维护记录应包括停运记录、故障及其处理、耗材更换等情况 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000024 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check47" type="radio" name="sf10000024" value="true" checked="checked"/>
            			<label for="check47">是</label>
            		<input onclick="checkBePunished(this.value)" id="check48" type="radio" name="sf10000024" value="false" />
            			<label for="check48">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000024 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check47" type="radio" name="sf10000024" value="true" />
            			<label for="check47">是</label>
            		<input onclick="checkBePunished(this.value)" id="check48" type="radio" name="sf10000024" value="false" checked="checked"/>
            			<label for="check48">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000024 == null || zxzzForm.sf10000024 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check47" type="radio" name="sf10000024" value="true" />
            			<label for="check47">是</label>
            		<input onclick="checkBePunished(this.value)" id="check48" type="radio" name="sf10000024" value="false" />
            			<label for="check48">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2" rowspan="2" align="center">
				有效性审核
			</td>
			<td colspan="7" align="left">
				1、自动监测数据应通过有效性审核 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000025 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check49" type="radio" name="sf10000025" value="true" checked="checked"/>
            			<label for="check49">是</label>
            		<input onclick="checkBePunished(this.value)" id="check50" type="radio" name="sf10000025" value="false" />
            			<label for="check50">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000025 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check49" type="radio" name="sf10000025" value="true" />
            			<label for="check49">是</label>
            		<input onclick="checkBePunished(this.value)" id="check50" type="radio" name="sf10000025" value="false" checked="checked"/>
            			<label for="check50">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000025 == null || zxzzForm.sf10000025 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check49" type="radio" name="sf10000025" value="true" />
            			<label for="check49">是</label>
            		<input onclick="checkBePunished(this.value)" id="check50" type="radio" name="sf10000025" value="false" />
            			<label for="check50">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				2、如未通过数据有效性审核，应落实整改措施 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000026 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check51" type="radio" name="sf10000026" value="true" checked="checked"/>
            			<label for="check51">是</label>
            		<input onclick="checkBePunished(this.value)" id="check52" type="radio" name="sf10000026" value="false" />
            			<label for="check52">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000026 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check51" type="radio" name="sf10000026" value="true" />
            			<label for="check51">是</label>
            		<input onclick="checkBePunished(this.value)" id="check52" type="radio" name="sf10000026" value="false" checked="checked"/>
            			<label for="check52">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000026 == null || zxzzForm.sf10000026 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check51" type="radio" name="sf10000026" value="true" />
            			<label for="check51">是</label>
            		<input onclick="checkBePunished(this.value)" id="check52" type="radio" name="sf10000026" value="false" />
            			<label for="check52">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2" rowspan="3" align="center">
				运行参数
			</td>
			<td colspan="7" align="left">
				1、自动监控设施运行参数与污染源自动监控设施竣工验收一致 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000027 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check53" type="radio" name="sf10000027" value="true" checked="checked"/>
            			<label for="check53">是</label>
            		<input onclick="checkBePunished(this.value)" id="check54" type="radio" name="sf10000027" value="false" />
            			<label for="check54">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000027 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check53" type="radio" name="sf10000027" value="true" />
            			<label for="check53">是</label>
            		<input onclick="checkBePunished(this.value)" id="check54" type="radio" name="sf10000027" value="false" checked="checked"/>
            			<label for="check54">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000027 == null || zxzzForm.sf10000027 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check53" type="radio" name="sf10000027" value="true" />
            			<label for="check53">是</label>
            		<input onclick="checkBePunished(this.value)" id="check54" type="radio" name="sf10000027" value="false" />
            			<label for="check54">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				2、自动监控设施运行参数与污染源自动监控设施登记备案一致
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000028 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check55" type="radio" name="sf10000028" value="true" checked="checked"/>
            			<label for="check55">是</label>
            		<input onclick="checkBePunished(this.value)" id="check56" type="radio" name="sf10000028" value="false" />
            			<label for="check56">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000028 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check55" type="radio" name="sf10000028" value="true" />
            			<label for="check55">是</label>
            		<input onclick="checkBePunished(this.value)" id="check56" type="radio" name="sf10000028" value="false" checked="checked"/>
            			<label for="check56">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000028 == null || zxzzForm.sf10000028 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check55" type="radio" name="sf10000028" value="true" />
            			<label for="check55">是</label>
            		<input onclick="checkBePunished(this.value)" id="check56" type="radio" name="sf10000028" value="false" />
            			<label for="check56">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				3、自动监控设施运行参数与最近一次数据有效性审核一致 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000029 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check57" type="radio" name="sf10000029" value="true" checked="checked"/>
            			<label for="check57">是</label>
            		<input onclick="checkBePunished(this.value)" id="check58" type="radio" name="sf10000029" value="false" />
            			<label for="check58">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000029 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check57" type="radio" name="sf10000029" value="true" />
            			<label for="check57">是</label>
            		<input onclick="checkBePunished(this.value)" id="check58" type="radio" name="sf10000029" value="false" checked="checked"/>
            			<label for="check58">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000029 == null || zxzzForm.sf10000029 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check57" type="radio" name="sf10000029" value="true" />
            			<label for="check57">是</label>
            		<input onclick="checkBePunished(this.value)" id="check58" type="radio" name="sf10000029" value="false" />
            			<label for="check58">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="1" rowspan="7" align="center">
				资质检查
			</td>
			<td colspan="2" rowspan="3" align="center">
				社会化运行单位资质
			</td>
			<td colspan="7" align="left">
				1、社会化运行单位是否符合《环境污染治理设施运营资质许可管理办法》相关规定 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000030 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check59" type="radio" name="sf10000030" value="true" checked="checked"/>
            			<label for="check59">是</label>
            		<input onclick="checkBePunished(this.value)" id="check60" type="radio" name="sf10000030" value="false" />
            			<label for="check60">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000030 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check59" type="radio" name="sf10000030" value="true" />
            			<label for="check59">是</label>
            		<input onclick="checkBePunished(this.value)" id="check60" type="radio" name="sf10000030" value="false" checked="checked"/>
            			<label for="check60">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000030 == null || zxzzForm.sf10000030 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check59" type="radio" name="sf10000030" value="true" />
            			<label for="check59">是</label>
            		<input onclick="checkBePunished(this.value)" id="check60" type="radio" name="sf10000030" value="false" />
            			<label for="check60">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				2、社会化运行单位是否具有污染源自动监控设施运营资质 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000031 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check61" type="radio" name="sf10000031" value="true" checked="checked"/>
            			<label for="check61">是</label>
            		<input onclick="checkBePunished(this.value)" id="check62" type="radio" name="sf10000031" value="false" />
            			<label for="check62">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000031 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check61" type="radio" name="sf10000031" value="true" />
            			<label for="check61">是</label>
            		<input onclick="checkBePunished(this.value)" id="check62" type="radio" name="sf10000031" value="false" checked="checked"/>
            			<label for="check62">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000031 == null || zxzzForm.sf10000031 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check61" type="radio" name="sf10000031" value="true" />
            			<label for="check61">是</label>
            		<input onclick="checkBePunished(this.value)" id="check62" type="radio" name="sf10000031" value="false" />
            			<label for="check62">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				3、社会化运营单位是否在有效期内并按资质规定从事运行活动 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000032 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check63" type="radio" name="sf10000032" value="true" checked="checked"/>
            			<label for="check63">是</label>
            		<input onclick="checkBePunished(this.value)" id="check64" type="radio" name="sf10000032" value="false" />
            			<label for="check64">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000032 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check63" type="radio" name="sf10000032" value="true" />
            			<label for="check63">是</label>
            		<input onclick="checkBePunished(this.value)" id="check64" type="radio" name="sf10000032" value="false" checked="checked"/>
            			<label for="check64">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000032 == null || zxzzForm.sf10000032 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check63" type="radio" name="sf10000032" value="true" />
            			<label for="check63">是</label>
            		<input onclick="checkBePunished(this.value)" id="check64" type="radio" name="sf10000032" value="false" />
            			<label for="check64">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2" rowspan="1" align="center">
				运维人员资质
			</td>
			<td colspan="7" align="left">
				从事污染源自动监控系统的运维、化验分析人员应通过培训并取得相应合格证书，持证上岗 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000033 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check65" type="radio" name="sf10000033" value="true" checked="checked"/>
            			<label for="check65">是</label>
            		<input onclick="checkBePunished(this.value)" id="check66" type="radio" name="sf10000033" value="false" />
            			<label for="check66">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000033 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check65" type="radio" name="sf10000033" value="true" />
            			<label for="check65">是</label>
            		<input onclick="checkBePunished(this.value)" id="check66" type="radio" name="sf10000033" value="false" checked="checked"/>
            			<label for="check66">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000033 == null || zxzzForm.sf10000033 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check65" type="radio" name="sf10000033" value="true" />
            			<label for="check65">是</label>
            		<input onclick="checkBePunished(this.value)" id="check66" type="radio" name="sf10000033" value="false" />
            			<label for="check66">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2" rowspan="3" align="center">
				仪器设备资质
			</td>
			<td colspan="7" align="left">
				1、具备环境保护部环境监测仪器质量监督检测中心出具的产品适用性检测合格报告 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000034 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check67" type="radio" name="sf10000034" value="true" checked="checked"/>
            			<label for="check67">是</label>
            		<input onclick="checkBePunished(this.value)" id="check68" type="radio" name="sf10000034" value="false" />
            			<label for="check68">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000034 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check67" type="radio" name="sf10000034" value="true" />
            			<label for="check67">是</label>
            		<input onclick="checkBePunished(this.value)" id="check68" type="radio" name="sf10000034" value="false" checked="checked"/>
            			<label for="check68">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000034 == null || zxzzForm.sf10000034 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check67" type="radio" name="sf10000034" value="true" />
            			<label for="check67">是</label>
            		<input onclick="checkBePunished(this.value)" id="check68" type="radio" name="sf10000034" value="false" />
            			<label for="check68">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				2、环境保护产品认证证书 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000035 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check69" type="radio" name="sf10000035" value="true" checked="checked"/>
            			<label for="check69">是</label>
            		<input onclick="checkBePunished(this.value)" id="check70" type="radio" name="sf10000035" value="false" />
            			<label for="check70">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000035 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check69" type="radio" name="sf10000035" value="true" />
            			<label for="check69">是</label>
            		<input onclick="checkBePunished(this.value)" id="check70" type="radio" name="sf10000035" value="false" checked="checked"/>
            			<label for="check70">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000035 == null || zxzzForm.sf10000035 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check69" type="radio" name="sf10000035" value="true" />
            			<label for="check69">是</label>
            		<input onclick="checkBePunished(this.value)" id="check70" type="radio" name="sf10000035" value="false" />
            			<label for="check70">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				3、仪器设备的名称、型号是否与上述各类证书相符合 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000036 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check71" type="radio" name="sf10000036" value="true" checked="checked"/>
            			<label for="check71">是</label>
            		<input onclick="checkBePunished(this.value)" id="check72" type="radio" name="sf10000036" value="false" />
            			<label for="check72">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000036 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check71" type="radio" name="sf10000036" value="true" />
            			<label for="check71">是</label>
            		<input onclick="checkBePunished(this.value)" id="check72" type="radio" name="sf10000036" value="false" checked="checked"/>
            			<label for="check72">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000036 == null || zxzzForm.sf10000036 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check71" type="radio" name="sf10000036" value="true" />
            			<label for="check71">是</label>
            		<input onclick="checkBePunished(this.value)" id="check72" type="radio" name="sf10000036" value="false" />
            			<label for="check72">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				自动监测数据相关性检查
			</td>
			<td colspan="7" align="left">
				企业生产负荷及工况、污染治理设施运行状况与自动监控设施显示数据变化的相关性，特别是其变化趋势应符合逻辑 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000037 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check73" type="radio" name="sf10000037" value="true" checked="checked"/>
            			<label for="check73">是</label>
            		<input onclick="checkBePunished(this.value)" id="check74" type="radio" name="sf10000037" value="false" />
            			<label for="check74">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000037 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check73" type="radio" name="sf10000037" value="true" />
            			<label for="check73">是</label>
            		<input onclick="checkBePunished(this.value)" id="check74" type="radio" name="sf10000037" value="false" checked="checked"/>
            			<label for="check74">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000037 == null || zxzzForm.sf10000037 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check73" type="radio" name="sf10000037" value="true" />
            			<label for="check73">是</label>
            		<input onclick="checkBePunished(this.value)" id="check74" type="radio" name="sf10000037" value="false" />
            			<label for="check74">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="8" align="center">
				数据异常检查
			</td>
			<td colspan="7" align="left">
				1、长期无正当理由无自动监控数据 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000038 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check75" type="radio" name="sf10000038" value="true" checked="checked"/>
            			<label for="check75">是</label>
            		<input onclick="checkBePunished(this.value)" id="check76" type="radio" name="sf10000038" value="false" />
            			<label for="check76">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000038 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check75" type="radio" name="sf10000038" value="true" />
            			<label for="check75">是</label>
            		<input onclick="checkBePunished(this.value)" id="check76" type="radio" name="sf10000038" value="false" checked="checked"/>
            			<label for="check76">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000038 == null || zxzzForm.sf10000038 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check75" type="radio" name="sf10000038" value="true" />
            			<label for="check75">是</label>
            		<input onclick="checkBePunished(this.value)" id="check76" type="radio" name="sf10000038" value="false" />
            			<label for="check76">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				2、自动监控数据长期在仪器分析方法检出限上下波动。 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000039 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check77" type="radio" name="sf10000039" value="true" checked="checked"/>
            			<label for="check77">是</label>
            		<input onclick="checkBePunished(this.value)" id="check78" type="radio" name="sf10000039" value="false" />
            			<label for="check78">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000039 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check77" type="radio" name="sf10000039" value="true" />
            			<label for="check77">是</label>
            		<input onclick="checkBePunished(this.value)" id="check78" type="radio" name="sf10000039" value="false" checked="checked"/>
            			<label for="check78">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000039 == null || zxzzForm.sf10000039 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check77" type="radio" name="sf10000039" value="true" />
            			<label for="check77">是</label>
            		<input onclick="checkBePunished(this.value)" id="check78" type="radio" name="sf10000039" value="false" />
            			<label for="check78">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				3、自动监控数据变化幅度长期在某一固定值上下小幅波动。 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000040 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check79" type="radio" name="sf10000040" value="true" checked="checked"/>
            			<label for="check79">是</label>
            		<input onclick="checkBePunished(this.value)" id="check80" type="radio" name="sf10000040" value="false" />
            			<label for="check80">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000040 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check79" type="radio" name="sf10000040" value="true" />
            			<label for="check79">是</label>
            		<input onclick="checkBePunished(this.value)" id="check80" type="radio" name="sf10000040" value="false" checked="checked"/>
            			<label for="check80">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000040 == null || zxzzForm.sf10000040 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check79" type="radio" name="sf10000040" value="true" />
            			<label for="check79">是</label>
            		<input onclick="checkBePunished(this.value)" id="check80" type="radio" name="sf10000040" value="false" />
            			<label for="check80">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				4、自动监控数据变化幅度长期在量程2%以内波动。 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000041 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check81" type="radio" name="sf10000041" value="true" checked="checked"/>
            			<label for="check81">是</label>
            		<input onclick="checkBePunished(this.value)" id="check82" type="radio" name="sf10000041" value="false" />
            			<label for="check82">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000041 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check81" type="radio" name="sf10000041" value="true" />
            			<label for="check81">是</label>
            		<input onclick="checkBePunished(this.value)" id="check82" type="radio" name="sf10000041" value="false" checked="checked"/>
            			<label for="check82">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000041 == null || zxzzForm.sf10000041 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check81" type="radio" name="sf10000041" value="true" />
            			<label for="check81">是</label>
            		<input onclick="checkBePunished(this.value)" id="check82" type="radio" name="sf10000041" value="false" />
            			<label for="check82">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				5、监督性监测数值与同时段自动监控数值的误差超过HJ/T 354及HJ/T 75规定的比对监测指标范围 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000042 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check83" type="radio" name="sf10000042" value="true" checked="checked"/>
            			<label for="check83">是</label>
            		<input onclick="checkBePunished(this.value)" id="check84" type="radio" name="sf10000042" value="false" />
            			<label for="check84">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000042 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check83" type="radio" name="sf10000042" value="true" />
            			<label for="check83">是</label>
            		<input onclick="checkBePunished(this.value)" id="check84" type="radio" name="sf10000042" value="false" checked="checked"/>
            			<label for="check84">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000042 == null || zxzzForm.sf10000042 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check83" type="radio" name="sf10000042" value="true" />
            			<label for="check83">是</label>
            		<input onclick="checkBePunished(this.value)" id="check84" type="radio" name="sf10000042" value="false" />
            			<label for="check84">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				6、分析仪器数据与数采仪数据偏差应小于1% 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000043 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check85" type="radio" name="sf10000043" value="true" checked="checked"/>
            			<label for="check85">是</label>
            		<input onclick="checkBePunished(this.value)" id="check86" type="radio" name="sf10000043" value="false" />
            			<label for="check86">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000043 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check85" type="radio" name="sf10000043" value="true" />
            			<label for="check85">是</label>
            		<input onclick="checkBePunished(this.value)" id="check86" type="radio" name="sf10000043" value="false" checked="checked"/>
            			<label for="check86">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000043 == null || zxzzForm.sf10000043 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check85" type="radio" name="sf10000043" value="true" />
            			<label for="check85">是</label>
            		<input onclick="checkBePunished(this.value)" id="check86" type="radio" name="sf10000043" value="false" />
            			<label for="check86">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				7、数采仪数据与监控中心数据偏差应小于1% 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000044 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check87" type="radio" name="sf10000044" value="true" checked="checked"/>
            			<label for="check87">是</label>
            		<input onclick="checkBePunished(this.value)" id="check88" type="radio" name="sf10000044" value="false" />
            			<label for="check88">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000044 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check87" type="radio" name="sf10000044" value="true" />
            			<label for="check87">是</label>
            		<input onclick="checkBePunished(this.value)" id="check88" type="radio" name="sf10000044" value="false" checked="checked"/>
            			<label for="check88">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000044 == null || zxzzForm.sf10000044 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check87" type="radio" name="sf10000044" value="true" />
            			<label for="check87">是</label>
            		<input onclick="checkBePunished(this.value)" id="check88" type="radio" name="sf10000044" value="false" />
            			<label for="check88">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				8、自动监控数据变化幅度无正当理由长期在某一固定值上下波动 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000045 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check89" type="radio" name="sf10000045" value="true" checked="checked"/>
            			<label for="check89">是</label>
            		<input onclick="checkBePunished(this.value)" id="check90" type="radio" name="sf10000045" value="false" />
            			<label for="check90">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000045 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check89" type="radio" name="sf10000045" value="true" />
            			<label for="check89">是</label>
            		<input onclick="checkBePunished(this.value)" id="check90" type="radio" name="sf10000045" value="false" checked="checked"/>
            			<label for="check90">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000045 == null || zxzzForm.sf10000045 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check89" type="radio" name="sf10000045" value="true" />
            			<label for="check89">是</label>
            		<input onclick="checkBePunished(this.value)" id="check90" type="radio" name="sf10000045" value="false" />
            			<label for="check90">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="4" align="center">
				仪器参数设置检查
			</td>
			<td colspan="7" align="left">
				1、仪器量程设置过大 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000046 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check91" type="radio" name="sf10000046" value="true" checked="checked"/>
            			<label for="check91">是</label>
            		<input onclick="checkBePunished(this.value)" id="check92" type="radio" name="sf10000046" value="false" />
            			<label for="check92">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000046 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check91" type="radio" name="sf10000046" value="true" />
            			<label for="check91">是</label>
            		<input onclick="checkBePunished(this.value)" id="check92" type="radio" name="sf10000046" value="false" checked="checked"/>
            			<label for="check92">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000046 == null || zxzzForm.sf10000046 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check91" type="radio" name="sf10000046" value="true" />
            			<label for="check91">是</label>
            		<input onclick="checkBePunished(this.value)" id="check92" type="radio" name="sf10000046" value="false" />
            			<label for="check92">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				2、实际监测条件发生变化，仪器参数未相应调整或变化调整未进行登记备案 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000047 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check93" type="radio" name="sf10000047" value="true" checked="checked"/>
            			<label for="check93">是</label>
            		<input onclick="checkBePunished(this.value)" id="check94" type="radio" name="sf10000047" value="false" />
            			<label for="check94">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000047 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check93" type="radio" name="sf10000047" value="true" />
            			<label for="check93">是</label>
            		<input onclick="checkBePunished(this.value)" id="check94" type="radio" name="sf10000047" value="false" checked="checked"/>
            			<label for="check94">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000047 == null || zxzzForm.sf10000047 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check93" type="radio" name="sf10000047" value="true" />
            			<label for="check93">是</label>
            		<input onclick="checkBePunished(this.value)" id="check94" type="radio" name="sf10000047" value="false" />
            			<label for="check94">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				3、自动监控数据换算公式与有关国家技术规定不一致 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000048 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check95" type="radio" name="sf10000048" value="true" checked="checked"/>
            			<label for="check95">是</label>
            		<input onclick="checkBePunished(this.value)" id="check96" type="radio" name="sf10000048" value="false" />
            			<label for="check96">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000048 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check95" type="radio" name="sf10000048" value="true" />
            			<label for="check95">是</label>
            		<input onclick="checkBePunished(this.value)" id="check96" type="radio" name="sf10000048" value="false" checked="checked"/>
            			<label for="check96">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000048 == null || zxzzForm.sf10000048 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check95" type="radio" name="sf10000048" value="true" />
            			<label for="check95">是</label>
            		<input onclick="checkBePunished(this.value)" id="check96" type="radio" name="sf10000048" value="false" />
            			<label for="check96">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				4、标准曲线发生改变未进行登记备案 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000049 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check97" type="radio" name="sf10000049" value="true" checked="checked"/>
            			<label for="check97">是</label>
            		<input onclick="checkBePunished(this.value)" id="check98" type="radio" name="sf10000049" value="false" />
            			<label for="check98">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000049 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check97" type="radio" name="sf10000049" value="true" />
            			<label for="check97">是</label>
            		<input onclick="checkBePunished(this.value)" id="check98" type="radio" name="sf10000049" value="false" checked="checked"/>
            			<label for="check98">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000049 == null || zxzzForm.sf10000049 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check97" type="radio" name="sf10000049" value="true" />
            			<label for="check97">是</label>
            		<input onclick="checkBePunished(this.value)" id="check98" type="radio" name="sf10000049" value="false" />
            			<label for="check98">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="3" align="center">
				自动监控设施状态检查
			</td>
			<td colspan="7" align="left">
				1、部分擅自停运或闲置 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000050 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check99" type="radio" name="sf10000050" value="true" checked="checked"/>
            			<label for="check99">是</label>
            		<input onclick="checkBePunished(this.value)" id="check100" type="radio" name="sf10000050" value="false" />
            			<label for="check100">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000050 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check99" type="radio" name="sf10000050" value="true" />
            			<label for="check99">是</label>
            		<input onclick="checkBePunished(this.value)" id="check100" type="radio" name="sf10000050" value="false" checked="checked"/>
            			<label for="check100">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000050 == null || zxzzForm.sf10000050 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check99" type="radio" name="sf10000050" value="true" />
            			<label for="check99">是</label>
            		<input onclick="checkBePunished(this.value)" id="check100" type="radio" name="sf10000050" value="false" />
            			<label for="check100">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				2、工作环境发生变化未进行登记备案 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000051 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check101" type="radio" name="sf10000051" value="true" checked="checked"/>
            			<label for="check101">是</label>
            		<input onclick="checkBePunished(this.value)" id="check102" type="radio" name="sf10000051" value="false" />
            			<label for="check102">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000051 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check101" type="radio" name="sf10000051" value="true" />
            			<label for="check101">是</label>
            		<input onclick="checkBePunished(this.value)" id="check102" type="radio" name="sf10000051" value="false" checked="checked"/>
            			<label for="check102">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000051 == null || zxzzForm.sf10000051 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check101" type="radio" name="sf10000051" value="true" />
            			<label for="check101">是</label>
            		<input onclick="checkBePunished(this.value)" id="check102" type="radio" name="sf10000051" value="false" />
            			<label for="check102">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="7" align="left">
				3、自动监控设施硬件、软件发生变化未进行登记备案 
			</td>
			<td colspan="3" align="center">
				<c:if test="${zxzzForm.sf10000052 == 'true'}">
					<input onclick="checkBePunished(this.value)" id="check103" type="radio" name="sf10000052" value="true" checked="checked"/>
            			<label for="check103">是</label>
            		<input onclick="checkBePunished(this.value)" id="check104" type="radio" name="sf10000052" value="false" />
            			<label for="check104">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000052 == 'false'}">
					<input onclick="checkBePunished(this.value)" id="check103" type="radio" name="sf10000052" value="true" />
            			<label for="check103">是</label>
            		<input onclick="checkBePunished(this.value)" id="check104" type="radio" name="sf10000052" value="false" checked="checked"/>
            			<label for="check104">否</label>
            	</c:if>
            	<c:if test="${zxzzForm.sf10000052 == null || zxzzForm.sf10000052 == '999'}">
					<input onclick="checkBePunished(this.value)" id="check103" type="radio" name="sf10000052" value="true" />
            			<label for="check103">是</label>
            		<input onclick="checkBePunished(this.value)" id="check104" type="radio" name="sf10000052" value="false" />
            			<label for="check104">否</label>
            	</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				备  注
			</td>
			<td colspan="7" align="left">
				<textarea class="i-text" style="width:90%;height:100px;" name="remark" id="remark">${zxzzForm.remark }</textarea>
			</td>
			<td colspan="3" align="center">
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