<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
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
	<div class="headTitle" style="font-size:16px; padding-top:20px;">主要产品</div>
	<form id="queryForm" action="" method="post">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="formtable">
			<input type="hidden" value="${hjfxffcsForm.id}" id="id" name="id">
			<input type="hidden" value="${hjfxffcsForm.pid}" id="pid" name="pid">
			<tr>
				<th width="100"><em class="mark">*</em>风险单元名称：</th>
				<td colspan="2" width="100">
				<div style="width:270px;height:55px;border:0px solid #95b8e7; overflow:auto;padding: 2px;word-wrap:break-word;word-break:break-all; ">${hjfxffcsForm.fxdymc}</div>
				</td>
				<th width="100">泄漏气体吸收装置：</th>
				<td colspan="2" width="100">
				<div style="width:280px;height:55px;border:0px solid #95b8e7; overflow:auto;padding: 2px;word-wrap:break-word;word-break:break-all; ">${hjfxffcsForm.xszz}</div>
				</td>
			</tr>
			<tr>
				<th rowspan="3" width="100">主要化学物质：</th>
				<th width="90">名称：</th>
				<td>
				<div style="width:190px;height:45px;border:0px solid #95b8e7; overflow:auto;padding: 2px;word-wrap:break-word;word-break:break-all; ">${hjfxffcsForm.zyhxwzmc}</div>
				</td>
				<th rowspan="4" width="100">风险特征：</th>
				<th width="90">反应条件高温高压：</th>
				<td>
				<div style="width:200px;height:45px;border:0px solid #95b8e7; overflow:auto;padding: 2px;word-wrap:break-word;word-break:break-all; ">${hjfxffcsForm.gwgy}</div>
				
				</td>
			</tr>
			<tr>
				<th width="90">现存量（吨）：</th>
				<td>${hjfxffcsForm.xcl}</td>
				<th width="90">易燃易爆：</th>
				<td>
				<div style="width:200px;height:45px;border:0px solid #95b8e7; overflow:auto;padding: 2px;word-wrap:break-word;word-break:break-all; ">${hjfxffcsForm.yryb}</div>
				
				</td>
			</tr>
			
			<tr>
				<th width="90">最大存储量（吨）：</th>
				<td>${hjfxffcsForm.zdccl}</td>
				<th width="90">化学物质易泄露：</th>
				<td>
				<div style="width:200px;height:45px;border:0px solid #95b8e7; overflow:auto;padding: 2px;word-wrap:break-word;word-break:break-all; ">${hjfxffcsForm.hxwzyxl}</div>
				
				</td>
			</tr>
			<tr>
				<th width="100">围堰：</th>
				 <td >
				    ${hjfxffcsForm.wy}
				 </td>
				 <td width="90">
				      容积（m3）: ${hjfxffcsForm.yxrj}				   
                </td>
				<th width="90">其他：</th>
				<td>
				<div style="width:200px;height:45px;border:0px solid #95b8e7; overflow:auto;padding: 2px;word-wrap:break-word;word-break:break-all; ">${hjfxffcsForm.qt}</div>
				
				</td>
			</tr>
			<tr>
				<th>专用排泄沟/管：</th>
				<td colspan="2">
					${hjfxffcsForm.zypxgg}
				    
				<th width="90">地面防渗：</th>
				<td >		
				${hjfxffcsForm.dmfs}
				 
				 <td>
				 材料: 
				 <div style="width:200px;height:45px;border:0px solid #95b8e7; overflow:auto;padding: 2px;word-wrap:break-word;word-break:break-all; ">${hjfxffcsForm.dmfscl}</div>
				 
                </td>
			</tr>
			<tr>
				<th width="100">气/液体泄漏侦测、报警系统：</th>
				<td colspan="2">
				${hjfxffcsForm.bjxt}
				</td>
				<th width="100">是否接入远程监控网：</th>
				<td colspan="2">
				${hjfxffcsForm.wcjkw}
				</td>
			</tr>
			<tr>
				<th width="100">事故废水排放去向：</th>
				<td colspan="2">
				<div style="width:270px;height:55px;border:0px solid #95b8e7; overflow:auto;padding: 2px;word-wrap:break-word;word-break:break-all; ">${hjfxffcsForm.pfqx}</div>
				
				</td>
				<th width="100">清净下水排水缓冲池：</th>
				<td colspan="2">
				<div style="width:280px;height:55px;border:0px solid #95b8e7; overflow:auto;padding: 2px;word-wrap:break-word;word-break:break-all; ">${hjfxffcsForm.hcc}</div>
				
				</td>
			</tr>
			<tr>
				<th width="100">清净下水排放切换阀门：</th>
				<td colspan="2" width="100">
				<div style="width:270px;height:55px;border:0px solid #95b8e7; overflow:auto;padding: 2px;word-wrap:break-word;word-break:break-all; ">${hjfxffcsForm.qhfm}</div>
				
				</td>
				
			    <th width="100">事故应急池：</th>
				<td >
				${hjfxffcsForm.sgyjc}
				 
				 <td width="130">
				  容积（m3）：  ${hjfxffcsForm.sgyjcrj}
                </td>
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
