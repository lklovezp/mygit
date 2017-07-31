<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>固定污染源烟气自动监控设施CEMS重点检查表</title>
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

.ta-da{
    overflow:scroll; 
    overflow-x:hidden; 
    height:80px;
    width:95%;
}
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
</style>
</head>
<body style="width: 98%;margin: 0 auto;padding-bottom: 70px;">
<div class="headTitle">固定污染源烟气自动监控设施CEMS重点检查表</div>
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
				二氧化硫与氮氧化物分析单元
			</td>
			<td colspan="5" align="left">
				1、颗粒物过滤器干净
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
				1、颗粒物过滤器肮脏、积灰
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
				2、红外法及化学发光法的NO2转换器工作正常，其温度与登记备案一致
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
				2、仪器内部管路连接松动，管壁存在积灰及冷凝水
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
				3、仪器内部管路连接紧固，管壁无积灰及冷凝水
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
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="4" align="center">
				颗粒物分析单元
			</td>
			<td colspan="5" align="left">
				1、吹扫系统电机正常工作
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
				1、吹扫系统电机出现异常噪声、震动
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
				2、隔离烟气与光学探头的玻璃视窗清洁，仪器光路准直
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
				2、隔离烟气与光学探头的玻璃视窗表面积尘，仪器光路偏离
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
				3、吹扫系统的管道连接正常
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
				3、吹扫系统的管道有裂缝，连接松动
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
				4、 吹扫风机的净化风滤芯清洁
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
				4、吹扫风机的净化风滤芯积灰
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
				烟气参数分析单元
			</td>
			<td colspan="5" align="left">
				1、皮托管无变形，并与气流方向垂直，紧固法兰无松动
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
				1、皮托管变形、堵塞，与烟道气流方向偏离，不垂直
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
				2、 热敏温度计表面无积尘
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
				2、热敏温度计表面有腐蚀情况，有积尘
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
				3、 空气过量系数、皮托管系数K 值、烟道截面积、速度场系数与登记备案一致
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
				3、空气过量系数、皮托管系数K 值、烟道截面积、速度场系数与登记备案不一致
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
				4、废气排放量、气态污染物浓度等换算符合HJ/T 397的有关要求
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
				4、废气排放量、气态污染物浓度等换算不符合的相关要
				<c:if test="${zxzzForm.sf10000022 == '1'}">
					<input type="checkbox" name="sf10000022" id="sf10000022" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000022 != '1'}">
					<input type="checkbox" name="sf10000022" id="sf10000022" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="3" align="center">
				校准校验
			</td>
			<td colspan="5" align="left">
				固定污染源烟气CEMS运行过程中应当按照HJ/T 75的相关要求，开展定期校准和定期检验 
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
				1、零点和跨度校准频次和校验频次达不到HJ/T 75的要求
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
			</td>
			<td colspan="5" align="left">
				2、现场通入零气和标准气体测试，零点漂移和跨度漂移符合HJ/T 75规定的失控指标
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
				3、现场通入标准气体测试，准确度不符合HJ/T 75规定的参比方法验收技术指标要求。
				<c:if test="${zxzzForm.sf10000025 == '1'}">
					<input type="checkbox" name="sf10000025" id="sf10000025" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000025 != '1'}">
					<input type="checkbox" name="sf10000025" id="sf10000025" value="1"/>
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