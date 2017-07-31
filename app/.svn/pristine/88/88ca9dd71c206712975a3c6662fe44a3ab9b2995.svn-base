<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<form id="queryForm"  method="post">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="formtable">
			<input type="hidden" value="${qyFrom.id}" id="id" name="id">
			<input type="hidden" value="${qyFrom.lawobjTypeId}" id="lawobjTypeId" name="lawobjTypeId">
			<input type="hidden" value="${qyFrom.lawobjId}" id="lawobjId" name="lawobjId">
			
			<tr>
				<th width="160"><em class="mark">*</em>单位名称：</th>
				<td colspan="2">${qyFrom.dwmc}</td>
				<th width="160">单位代码：</th>
				<td colspan="2">${qyFrom.code}</td>
			</tr>
			<tr>
				<th width="190">法定代表人：</th>
				<td colspan="2">${qyFrom.fddbr}</td>
				<th width="190">行政区划：</th>
				<td colspan="2">${qyFrom.xzqh}</td>
			</tr>
			<tr>
				<th width="190">单位所在地：</th>
				<td colspan="2">${qyFrom.dwszd}</td>
				<th width="190">行政区划代码：</th>
				<td colspan="2">${qyFrom.xzqhCode}</td>
			</tr>
			<tr>
				<th rowspan="2" width="60">地理坐标：</th>
				<th width="40">中心经度：</th>
				<td width="60">${qyFrom.zxjd}</td>
				<th rowspan="2" width="60">行业：</th>
				<th width="40">类别：</th>
				<td width="60">${qyFrom.hylb}</td>
			</tr>
			<tr>
				<th width="40">中心纬度：</th>
				<td width="60">${qyFrom.zxwd}</td>
				<th width="40">代码：</th>
				<td width="60">${qyFrom.hydm}</td>
			</tr>
			<tr>
				<th width="190">所在工业园区名称：</th>
				<td colspan="2">${qyFrom.yqmc}</td>
				<th width="190">区    号：</th>
				<td colspan="2">${qyFrom.qh}</td>
			</tr>
			<tr>
				<th width="190">电话号码：</th>
				<td colspan="2">${qyFrom.dhhm}</td>
				<th width="190">值班电话：</th>
				<td colspan="2">${qyFrom.zbdh}</td>
			</tr>
			<tr>
				<th width="190">传真号码：</th>
				<td colspan="2">${qyFrom.czhm}</td>
				<th width="190">邮政编码：</th>
				<td colspan="2">${qyFrom.yzbm}</td>
			</tr>
			<tr>
				<th width="190">企业环境负责人：</th>
				<td colspan="2">${qyFrom.qyhjglrName}</td>
				<th width="160">企业环境负责人联系电话：</th>
				<td colspan="2">${qyFrom.qyhjglrPhone}</td>
			</tr>
			<tr>
				<th width="190">年生产时间 （小时）：</th>
				<td colspan="2">${qyFrom.nscsj}</td>
				<th width="190">工业总产值（万元）：</th>
				<td colspan="2">${qyFrom.zcz}</td>
			</tr>
			<tr>
				<th width="190">厂区面积（m<sup>2</sup>）：</th>
				<td colspan="2">${qyFrom.cqmj}</td>
				<th width="190">是否编制突发环境事件应急预案：</th>
				<td colspan="2">${qyFrom.sfbzya}</td>
			</tr>
			<tr>
				<th width="190">环境风险评价专篇：</th>
				<td colspan="5">${qyFrom.sfpjwj}</td>
			</tr>
			<tr>
				<th width="190">发生突发环境事件情况</th>
				<td colspan="5">截至  ${qyFrom.sftfDate} &nbsp;&nbsp;&nbsp;&nbsp; ${qyFrom.sftf} &nbsp;&nbsp;&nbsp;&nbsp; 共   ${qyFrom.sftfcs}    次 </td>
			</tr>
			
		</table>
		<div class="t-c" style="margin-top:25px">
			<span class="btn btn-ok"><input id="cl" type="button" name="close" value="关闭" onclick="guanBi();"/> </span>&nbsp;
		</div>
	</form>
	<script type="text/javascript">
	 function guanBi(){
	    	//jQuery().colorbox.close();
	    	parent.ref();
	    	
	    };
		$(document).ready(function() {
			
		});
	</script>
</body>
</html>
