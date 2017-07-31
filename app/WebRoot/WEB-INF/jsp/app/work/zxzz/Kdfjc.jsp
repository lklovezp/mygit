<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>固定污染源废水自动监测系统现场核查表</title>
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
<div class="headTitle">固定污染源废水自动监测系统现场核查表 （满分100分）</div>
<form id="myform" name="myform" method="post" action="saveZxzz.json">
<input type="hidden" id="applyId" name="applyId" value="${work.id}" />
<input type="hidden" id="zzmblx" name="zzmblx" value="${zzmblx}" />
<input type="hidden" id="id" name="id" value="${id}" />
	<div align="right">
		编号：${wjbh}<input id="sf10000074" name="sf10000074" style="width:100px;" value="${zxzzForm.sf10000074 }"></input>
	</div>
	<div class="divContainer">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formtable">
		<tr>
			<td colspan="3" rowspan="2" align="center">
				企业基本信息
			</td>
			<td colspan="2" align="center">
				企业名称
			</td>
			<td colspan="4" align="center">
				${zfdwmc}
			</td>
			<td colspan="2" align="center">
				排污口编码
			</td>
			<td colspan="2" align="center">
				<input id="sf10000075" name="sf10000075" style="width:100px;" value="${zxzzForm.sf10000075 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				重点属性
			</td>
			<td colspan="4" align="center">
				${kzlx}
			</td>
			<td colspan="2" align="center">
				环保负责人
			</td>
			<td colspan="2" align="center">
				${blMainForm.blZfdxForm.bjcr}
			</td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				评分项目
			</td>
			<td colspan="4" align="center">
				评分内容
			</td>
			<td colspan="2" align="center">
				评分细则
			</td>
			<td colspan="1" align="center">
				得分
			</td>
			<td colspan="3" align="center">
				扣分原因
			</td>
		</tr>
		<tr>
			<td colspan="13" align="left">
				一、监测用房和仪器安装（满分25分）
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="6" align="center">
				监测用房（满分12分）
			</td>
			<td colspan="4" align="left">
				面积大于6m2
			</td>
			<td colspan="2" align="left">
				符合得2分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000001" name="sf10000001" style="width:100px;" value="${zxzzForm.sf10000001 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000002" name="sf10000002" style="width:100px;" value="${zxzzForm.sf10000002 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				配备专用配电以及空调
			</td>
			<td colspan="2" align="left">
				符合得2分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000003" name="sf10000003" style="width:100px;" value="${zxzzForm.sf10000003 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000004" name="sf10000004" style="width:100px;" value="${zxzzForm.sf10000004 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				具有防雷系统
			</td>
			<td colspan="2" align="left">
				符合得2分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000005" name="sf10000005" style="width:100px;" value="${zxzzForm.sf10000005 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000006" name="sf10000006" style="width:100px;" value="${zxzzForm.sf10000006 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				配备上下水
			</td>
			<td colspan="2" align="left">
				符合得2分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000007" name="sf10000007" style="width:100px;" value="${zxzzForm.sf10000007 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000008" name="sf10000008" style="width:100px;" value="${zxzzForm.sf10000008 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				管路清晰
			</td>
			<td colspan="2" align="left">
				符合得2分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000009" name="sf10000009" style="width:100px;" value="${zxzzForm.sf10000009 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000010" name="sf10000010" style="width:100px;" value="${zxzzForm.sf10000010 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				整齐干净，无闲杂物品
			</td>
			<td colspan="2" align="left">
				符合得2分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000011" name="sf10000011" style="width:100px;" value="${zxzzForm.sf10000011 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000012" name="sf10000012" style="width:100px;" value="${zxzzForm.sf10000012 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="3" align="center">
				排污口规范化（满分7分）
			</td>
			<td colspan="4" align="left">
				安装自动监测设备的取水口在总排污渠位置
			</td>
			<td colspan="2" align="left">
				符合得3分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000013" name="sf10000013" style="width:100px;" value="${zxzzForm.sf10000013 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000014" name="sf10000014" style="width:100px;" value="${zxzzForm.sf10000014 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				排污口建设规范并安装有标准计量堰槽
			</td>
			<td colspan="2" align="left">
				符合得3分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000015" name="sf10000015" style="width:100px;" value="${zxzzForm.sf10000015 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000016" name="sf10000016" style="width:100px;" value="${zxzzForm.sf10000016 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				有排污口标志牌、编号及特征污染物名称
			</td>
			<td colspan="2" align="left">
				符合得1分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000017" name="sf10000017" style="width:100px;" value="${zxzzForm.sf10000017 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000018" name="sf10000018" style="width:100px;" value="${zxzzForm.sf10000018 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="2" align="center">
				流量计安装（满分6分）
			</td>
			<td colspan="4" align="left">
				计量槽安装位置上游顺直段长度应大于水面宽度5-10倍；下游出水口无淹没流
			</td>
			<td colspan="2" align="left">
				符合得3分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000019" name="sf10000019" style="width:100px;" value="${zxzzForm.sf10000019 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000020" name="sf10000020" style="width:100px;" value="${zxzzForm.sf10000020 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				探头安装在堰槽断面中心线上，安装牢固，不易移动；仪器零点水位与堰槽计量零点一致
			</td>
			<td colspan="2" align="left">
				符合得3分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000021" name="sf10000021" style="width:100px;" value="${zxzzForm.sf10000021 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000022" name="sf10000022" style="width:100px;" value="${zxzzForm.sf10000022 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="13" align="left">
				二、WWMS系统组成的完全性（满分15分）
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="2" align="center">
				污染物自动监测系统（10分）
			</td>
			<td colspan="4" align="left">
				有pH计、流量计
			</td>
			<td colspan="2" align="left">
				符合得4分，缺1项扣2分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000023" name="sf10000023" style="width:100px;" value="${zxzzForm.sf10000023 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000024" name="sf10000024" style="width:100px;" value="${zxzzForm.sf10000024 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				有COD监测仪器；氨氮分析仪（列入上年国控名单的氨氮重点排放企业、省控以上污水处理厂应装）；总磷分析仪（省控以上污水处理厂）；如不需装，须已向责任环保部门备案
			</td>
			<td colspan="2" align="left">
				符合得6分，缺1项扣完
			</td>
			<td colspan="1" align="center">
				<input id="sf10000025" name="sf10000025" style="width:100px;" value="${zxzzForm.sf10000025 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000026" name="sf10000026" style="width:100px;" value="${zxzzForm.sf10000026 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="2" align="center">
				辅助系统（5分）
			</td>
			<td colspan="4" align="left">
				具有冲洗和反冲洗功能
			</td>
			<td colspan="2" align="left">
				符合得3分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000027" name="sf10000027" style="width:100px;" value="${zxzzForm.sf10000027 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000028" name="sf10000028" style="width:100px;" value="${zxzzForm.sf10000028 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				有废液回收装置
			</td>
			<td colspan="2" align="left">
				符合得2分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000029" name="sf10000029" style="width:100px;" value="${zxzzForm.sf10000029 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000030" name="sf10000030" style="width:100px;" value="${zxzzForm.sf10000030 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="13" align="left">
				三、设备运行维护（满分28分）
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="1" align="center">
				分析仪器检查（满分4分）
			</td>
			<td colspan="4" align="left">
				分析周期TOC为2小时一次；COD、氨氮、总磷、总氮为4小时1次
			</td>
			<td colspan="2" align="left">
				符合得4分，不符按比例扣分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000031" name="sf10000031" style="width:100px;" value="${zxzzForm.sf10000031 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000032" name="sf10000032" style="width:100px;" value="${zxzzForm.sf10000032 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="3" align="center">
				分析仪器检查（满分6分）
			</td>
			<td colspan="4" align="left">
				控制面板的灯光、指示器状态正常
			</td>
			<td colspan="2" align="left">
				符合得2分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000033" name="sf10000033" style="width:100px;" value="${zxzzForm.sf10000033 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000034" name="sf10000034" style="width:100px;" value="${zxzzForm.sf10000034 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				量程如可设，设为3倍排放标准值，如不可设，应能查看
			</td>
			<td colspan="2" align="left">
				符合得2分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000035" name="sf10000035" style="width:100px;" value="${zxzzForm.sf10000035 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000036" name="sf10000036" style="width:100px;" value="${zxzzForm.sf10000036 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				采样泵无腐蚀、非正常的噪声或漏水
			</td>
			<td colspan="2" align="left">
				符合得2分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000037" name="sf10000037" style="width:100px;" value="${zxzzForm.sf10000037 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000038" name="sf10000038" style="width:100px;" value="${zxzzForm.sf10000038 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="2" align="center">
				标准溶液检查（满分4分）
			</td>
			<td colspan="4" align="left">
				COD、氨氮标准溶液在有效期内其浓度与量程相匹配
			</td>
			<td colspan="2" align="left">
				符合得2分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000039" name="sf10000039" style="width:100px;" value="${zxzzForm.sf10000039 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000040" name="sf10000040" style="width:100px;" value="${zxzzForm.sf10000040 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				其他分析试剂在有效期内
			</td>
			<td colspan="2" align="left">
				符合得2分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000041" name="sf10000041" style="width:100px;" value="${zxzzForm.sf10000041 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000042" name="sf10000042" style="width:100px;" value="${zxzzForm.sf10000042 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="2" align="center">
				校准系统（满分14分）
			</td>
			<td colspan="4" align="left">
				TOC/COD转换系数与记录一致，无TOC分值计入下一项
			</td>
			<td colspan="2" align="left">
				符合得2分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000043" name="sf10000043" style="width:100px;" value="${zxzzForm.sf10000043 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000044" name="sf10000044" style="width:100px;" value="${zxzzForm.sf10000044 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				pH现场校验、COD、氨氮标准溶液校验符合要求
			</td>
			<td colspan="2" align="left">
				符合得12分，不符按比例扣分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000045" name="sf10000045" style="width:100px;" value="${zxzzForm.sf10000045 }" maxlength='2' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000046" name="sf10000046" style="width:100px;" value="${zxzzForm.sf10000046 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="13" align="left">
				四、数据采集、通讯系统系统准确性（满分15分）
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="2" align="center">
				联网情况（满分5分）
			</td>
			<td colspan="4" align="left">
				现场核查时正常联网
			</td>
			<td colspan="2" align="left">
				符合得3分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000047" name="sf10000047" style="width:100px;" value="${zxzzForm.sf10000047 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000048" name="sf10000048" style="width:100px;" value="${zxzzForm.sf10000048 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				数采仪显示器正常运行
			</td>
			<td colspan="2" align="left">
				符合得2分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000049" name="sf10000049" style="width:100px;" value="${zxzzForm.sf10000049 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000050" name="sf10000050" style="width:100px;" value="${zxzzForm.sf10000050 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="3" align="center">
				数据一致性（满分10分）
			</td>
			<td colspan="4" align="left">
				数采仪上的量程与对应因子的仪器量程相同
			</td>
			<td colspan="2" align="left">
				符合得2分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000051" name="sf10000051" style="width:100px;" value="${zxzzForm.sf10000051 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000052" name="sf10000052" style="width:100px;" value="${zxzzForm.sf10000052 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				一次仪表和数采的数据误差不大于2%，不允许大于5%
			</td>
			<td colspan="2" align="left">
				符合得4分,不符按比例扣分,有大于5%不得分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000053" name="sf10000053" style="width:100px;" value="${zxzzForm.sf10000053 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000054" name="sf10000054" style="width:100px;" value="${zxzzForm.sf10000054 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				用最近的校准记录校核数采仪同时刻数据
			</td>
			<td colspan="2" align="left">
				一致得4分,一项不符扣2分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000055" name="sf10000055" style="width:100px;" value="${zxzzForm.sf10000055 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000056" name="sf10000056" style="width:100px;" value="${zxzzForm.sf10000056 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="13" align="left">
				五、运行管理的规范性（满分17分）
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="2" align="center">
				管理制度（满分3分）
			</td>
			<td colspan="4" align="left">
				建立了站房管理制度、人员岗位职责
			</td>
			<td colspan="2" align="left">
				符合得1分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000057" name="sf10000057" style="width:100px;" value="${zxzzForm.sf10000057 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000058" name="sf10000058" style="width:100px;" value="${zxzzForm.sf10000058 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				企业人员有环保部颁发的有效性审核证书
			</td>
			<td colspan="2" align="left">
				符合得2分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000059" name="sf10000059" style="width:100px;" value="${zxzzForm.sf10000059 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000060" name="sf10000060" style="width:100px;" value="${zxzzForm.sf10000060 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="1" align="center">
				设备资料（满分4分）
			</td>
			<td colspan="4" align="left">
				仪器设备使用说明书、合格证、环保产品认证、操作规程
			</td>
			<td colspan="2" align="left">
				符合得4分，缺1项扣1分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000061" name="sf10000061" style="width:100px;" value="${zxzzForm.sf10000061 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000062" name="sf10000062" style="width:100px;" value="${zxzzForm.sf10000062 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="4" align="center">
				运行记录/电子档案（满分10分）
			</td>
			<td colspan="4" align="left">
				维护记录单独成册，完整、连续，平均每周1次
			</td>
			<td colspan="2" align="left">
				符合得2分，频次不足不得分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000063" name="sf10000063" style="width:100px;" value="${zxzzForm.sf10000063 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000064" name="sf10000064" style="width:100px;" value="${zxzzForm.sf10000064 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				维护记录中有废液处置记录
			</td>
			<td colspan="2" align="left">
				符合得1分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000065" name="sf10000065" style="width:100px;" value="${zxzzForm.sf10000065 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000066" name="sf10000066" style="width:100px;" value="${zxzzForm.sf10000066 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				维修记录单独成册，完整，最近1次响应至修复时间小于3天
			</td>
			<td colspan="2" align="left">
				符合得2分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000067" name="sf10000067" style="width:100px;" value="${zxzzForm.sf10000067 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000068" name="sf10000068" style="width:100px;" value="${zxzzForm.sf10000068 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="left">
				校准记录单独成册，完整、连续，质控记录每周至少1次，实样比对记录每月至少1次
			</td>
			<td colspan="2" align="left">
				符合得5分，频次不足不得分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000069" name="sf10000069" style="width:100px;" value="${zxzzForm.sf10000069 }" maxlength='1' onkeyup="value=value.replace(/[^\d\.]/g,'');SumNum();"></input>
			</td>
			<td colspan="3" align="center">
				<input id="sf10000070" name="sf10000070" style="width:100px;" value="${zxzzForm.sf10000070 }"></input>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="center">
				总分
			</td>
			<td colspan="1" align="center">
				<input id="sf10000071" name="sf10000071" style="width:100px;" value="${zxzzForm.sf10000071 }" readonly="readonly"></input>
			</td>
			<td colspan="1" align="left">
				核查人员
			</td>
			<td colspan="2" align="center">
				${jcrNames }
			</td>
			<td colspan="1" align="left">
				企业人员签字
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

function SumNum(){  
     var sumValue;  
     var a1 = eval(document.getElementById("sf10000001").value);
	 var a2 = eval(document.getElementById("sf10000003").value);
	 var a3 = eval(document.getElementById("sf10000005").value);
	 var a4 = eval(document.getElementById("sf10000007").value);
	 var a5 = eval(document.getElementById("sf10000009").value);
	 var a6 = eval(document.getElementById("sf10000011").value);
	 var a7 = eval(document.getElementById("sf10000013").value);
	 var a8 = eval(document.getElementById("sf10000015").value);
	 var a9 = eval(document.getElementById("sf10000017").value);
	 var a10 = eval(document.getElementById("sf10000019").value);
	 var a11 = eval(document.getElementById("sf10000021").value);
	 var a12 = eval(document.getElementById("sf10000023").value);
	 var a13 = eval(document.getElementById("sf10000025").value);
	 var a14 = eval(document.getElementById("sf10000027").value);
	 var a15 = eval(document.getElementById("sf10000029").value);
	 var a16 = eval(document.getElementById("sf10000031").value);
	 var a17 = eval(document.getElementById("sf10000033").value);
	 var a18 = eval(document.getElementById("sf10000035").value);
	 var a19 = eval(document.getElementById("sf10000037").value);
	 var a20 = eval(document.getElementById("sf10000039").value);
	 var a21 = eval(document.getElementById("sf10000041").value);
	 var a22 = eval(document.getElementById("sf10000043").value);
	 var a23 = eval(document.getElementById("sf10000045").value);
	 var a24 = eval(document.getElementById("sf10000047").value);
	 var a25 = eval(document.getElementById("sf10000049").value);
	 var a26 = eval(document.getElementById("sf10000051").value);
	 var a27 = eval(document.getElementById("sf10000053").value);
	 var a28 = eval(document.getElementById("sf10000055").value);
	 var a29 = eval(document.getElementById("sf10000057").value);
	 var a30 = eval(document.getElementById("sf10000059").value);
	 var a31 = eval(document.getElementById("sf10000061").value);
	 var a32 = eval(document.getElementById("sf10000063").value);
	 var a33 = eval(document.getElementById("sf10000065").value);
	 var a34 = eval(document.getElementById("sf10000067").value);
	 var a35 = eval(document.getElementById("sf10000069").value);
      
    if(a1==null){
        a1=0;
    }
    if(a1 > 2){
       alert("得分不能大于2！");
    }
    if(a2==null){
        a2=0;
    }
    if(a2 > 2){
       alert("得分不能大于2！");
    }
    if(a3==null){
        a3=0;
    }
    if(a3 > 2){
       alert("得分不能大于2！");
    }
    if(a4==null){
        a4=0;
    }
    if(a4 > 2){
       alert("得分不能大于2！");
    }
    if(a5==null){
        a5=0;
    }
    if(a5 > 2){
       alert("得分不能大于2！");
    }
    if(a6==null){
        a6=0;
    }
    if(a6 > 2){
       alert("得分不能大于2！");
    }
    if(a7==null){
        a7=0;
    }
    if(a7 > 3){
       alert("得分不能大于3！");
    }
    if(a8==null){
        a8=0;
    }
    if(a8 > 3){
       alert("得分不能大于3！");
    }
    if(a9==null){
        a9=0;
    }
    if(a9 > 1){
       alert("得分不能大于1！");
    }
    if(a10==null){
        a10=0;
    }
    if(a10 > 3){
       alert("得分不能大于3！");
    }
    if(a11==null){
        a11=0;
    }
    if(a11 > 3){
       alert("得分不能大于3！");
    }
    if(a12==null){
        a12=0;
    }
    if(a12 != 0 && a12 != 2 && a12 != 4){
       alert("得分只能为0、2、4！");
    }
    if(a13==null){
        a13=0;
    }
    if(a13 != 0 && a13 != 6){
       alert("得分只能为0、6！");
    }
    if(a14==null){
        a14=0;
    }
    if(a14 > 3){
       alert("得分不能大于3！");
    }
    if(a15==null){
        a15=0;
    }
    if(a15 > 2){
       alert("得分不能大于2！");
    }    
    if(a16==null){
        a16=0;
    }
    if(a16 > 4){
       alert("得分不能大于4！");
    }
    if(a17==null){
        a17=0;
    }
    if(a17 > 2){
       alert("得分不能大于2！");
    }
    if(a18==null){
        a18=0;
    }
    if(a18 > 2){
       alert("得分不能大于2！");
    }
    if(a19==null){
        a19=0;
    }
    if(a19 > 2){
       alert("得分不能大于2！");
    }
    if(a20==null){
        a20=0;
    }
    if(a20 > 2){
       alert("得分不能大于2！");
    }
    if(a21==null){
        a21=0;
    }
    if(a21 > 2){
       alert("得分不能大于2！");
    }
    if(a22==null){
        a22=0;
    }
    if(a22 > 2){
       alert("得分不能大于2！");
    }
    if(a23==null){
        a23=0;
    }
    if(a23 > 12){
       alert("得分不能大于12！");
    }
    if(a24==null){
        a24=0;
    }
    if(a24 > 3){
       alert("得分不能大于3！");
    }
    if(a25==null){
        a25=0;
    }
    if(a25 > 2){
       alert("得分不能大于2！");
    }
    if(a26==null){
        a26=0;
    }
    if(a26 > 2){
       alert("得分不能大于2！");
    }
    if(a27==null){
        a27=0;
    }
    if(a27 > 4){
       alert("得分不能大于4！");
    }
    if(a28==null){
        a28=0;
    }
     if(a28 != 2 && a28 != 0 && a28 != 4){
       alert("得分只能为0、2、4！");
    }
    if(a29==null){
        a29=0;
    }
    if(a29 > 1){
       alert("得分不能大于1！");
    }
    if(a30==null){
        a30=0;
    }
    if(a30 > 2){
       alert("得分不能大于2！");
    }
    if(a31==null){
        a31=0;
    }
    if(a31 > 4){
       alert("得分不能大于4！");
    }
    if(a32==null){
        a32=0;
    }
    if(a32 > 2){
       alert("得分不能大于2！");
    }
    if(a33==null){
        a33=0;
    }
    if(a33 > 1){
       alert("得分不能大于1！");
    }
    if(a34==null){
        a34=0;
    }
    if(a34 > 2){
       alert("得分不能大于2！");
    }
    if(a35==null){
        a35=0;
    }
    if(a35 > 5){
       alert("得分不能大于5！");
    }
    sumValue = parseInt(a1) + parseInt(a2) +parseInt(a3) +parseInt(a4) +parseInt(a5) +parseInt(a6)+parseInt(a7)+parseInt(a8)+parseInt(a9)+parseInt(a10)+parseInt(a11)+parseInt(a12)+parseInt(a13)+parseInt(a14)+parseInt(a15)+parseInt(a16)+parseInt(a17)+parseInt(a18)+parseInt(a19)+parseInt(a20)+parseInt(a21)+parseInt(a22)+parseInt(a23)+parseInt(a24)+parseInt(a25)+parseInt(a26)+parseInt(a27)+parseInt(a28)+parseInt(a29)+parseInt(a30)+parseInt(a31)+parseInt(a32)+parseInt(a33)+parseInt(a34)+parseInt(a35);
    $("#sf10000071").attr("value",sumValue);
   }
</script>