<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业化学物质产品_一主要产品</title>
<script type="text/javascript" src="${app}/js.js"></script>
<script type="text/javascript" src="${app}/data.js"></script>
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
	<div class="headTitle" style="font-size:16px; padding-top:20px;">主要产品</div>
	<form id="queryForm" action="" method="post">
		<table width="100%" border="0" align="center" cellpadding="0" font-size="50px"  cellpadding="0"	cellspacing="0" class="formtable">
			<input type="hidden" value="${qyhxwzqkzycpForm.id}" id="id" name="id">
			<input type="hidden" value="${qyhxwzqkzycpForm.pid}" id="pid" name="pid">
			<tr>
				<th width="150">商品名：</th>
				<td colspan="2" width="100" >
				<div style="width:290px;border:0px solid #95b8e7; overflow:auto;padding: 2px;word-wrap:break-word;word-break:break-all; ">${qyhxwzqkzycpForm.spm}</div> 
				
				</td>
				<th width="150">化学名：</th>
				<td colspan="2" width="100" >
				<div style="width:150px;border:0px solid #95b8e7; overflow:auto;padding: 2px;word-wrap:break-word;word-break:break-all; ">${qyhxwzqkzycpForm.hxm}</div> 
				</td>
			</tr>
			<tr>
				<th>CAS号：</th>
				<td colspan="2">
				<div style="width:290px;border:0px solid #95b8e7; overflow:auto;padding: 2px;word-wrap:break-word;word-break:break-all; ">${qyhxwzqkzycpForm.cas}</div> 
				</td>
				<th>物理状态：</th>
				<td colspan="2">${qyhxwzqkzycpForm.wlzt}</td>
			</tr>
			<tr>
				<th>物质分类（填写代码）：</th>
				<td colspan="2">
				<div style="width:290px;border:0px solid #95b8e7; overflow:auto;padding: 2px;word-wrap:break-word;word-break:break-all; ">${qyhxwzqkzycpForm.wzfl}</div> 
				</td>
				<th>设计生产能力（吨/年）：</th>
				<td colspan="2">${qyhxwzqkzycpForm.sjscnl}</td>
			</tr>
			<tr>
				<th>实际产量（吨）：</th>
				<td colspan="2">${qyhxwzqkzycpForm.sjcl}</td>
				<th rowspan="2">用途：</th>
				<td colspan="2" rowspan="2">
				<div style="width:150px; height:60px;border:0px solid #95b8e7; overflow:auto;padding: 2px;word-wrap:break-word;word-break:break-all; ">${qyhxwzqkzycpForm.yt}</div> 
				</td>
			</tr>
			<tr>
				<th>运输方式：</th>
				<td colspan="2">${qyhxwzqkzycpForm.ysfs}</td>
			</tr>
			<tr>
				<th rowspan="2" width="150">销售量（吨）：</th>
				<th width="90">国内：</th>
				<td>${qyhxwzqkzycpForm.gnxsl}</td>
				<th rowspan="2" width="150">贮存量（吨）：</th>
				<th width="90">生产区：</th>
				<td>${qyhxwzqkzycpForm.scqzcl}</td>
			</tr>
			<tr>
				<th width="90">出口：</th>
				<td>${qyhxwzqkzycpForm.ckxsl}</td>
				<th width="90">贮存区：</th>
				<td>${qyhxwzqkzycpForm.zcqzcl}</td>
			</tr>
			<tr>
				<th rowspan="2" width="150">贮存方式（代码）：</th>
				<th width="90">生产区：</th>
				<td>
				<div style="width:160px;height:55px;border:0px solid #95b8e7; overflow:auto;padding: 2px;word-wrap:break-word;word-break:break-all; ">${qyhxwzqkzycpForm.scqzcfs}</div> 
				</td>
				<th>设备状态：</th>
				<td colspan="2">${qyhxwzqkzycpForm.sbzt}</td>
			</tr>
			<tr>
				<th>贮存区：</th>
				<td>
				<div style="width:160px;border:0px solid #95b8e7; overflow:auto;padding: 2px;word-wrap:break-word;word-break:break-all; ">${qyhxwzqkzycpForm.zcqzcfs}</div> 
				</td>
				<th>生产方式：</th>
				<td colspan="2">${qyhxwzqkzycpForm.scfs}</td>
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
