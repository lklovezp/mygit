<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业环境基本信息</title>
<script type="text/javascript" src="${layer}/layer.js"></script>
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
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<!--jQuery-->

<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<!--easyui-->
<link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
<!-- 任务管理 css-->
<link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>

<style type="text/css">
.basicinfoTable,.basicinfoTable td{ border: 1px solid #d4d4d4;}
.panel-header{background-color: #cff1ff;}
.formtable, .formtable th, .formtable td{border-color:#dddddd; }
</style>
</head>
<body>
	<div class="h1_1" style="font-size:16px; padding-top:20px;">企业环境基本信息</div>
	<form id="queryForm"  method="post">
		<table class="dataTable basicinfoTable" width="100%" cellpadding="0" cellspacing="0">
			<input type="hidden" value="${qyFrom.id}" id="id" name="id">
			<input type="hidden" value="${qyFrom.lawobjTypeId}" id="lawobjTypeId" name="lawobjTypeId">
			<input type="hidden" value="${qyFrom.lawobjId}" id="lawobjId" name="lawobjId">
			
			<tr>
				<th width="120" bgcolor="#edfaff" align="right"><em class="mark">*</em>单位名称：</th>
				<td colspan="2">${qyFrom.dwmc}
				<input type="hidden" value="${qyFrom.dwmc}" id="id" name="dwmc">
				</td>
				<th width="120" bgcolor="#edfaff" align="right">单位代码：</th>
				<td colspan="2">${qyFrom.code}
				<input type="hidden" value="${qyFrom.code}" id="code" name="code">
				</td>
			</tr>
			<tr>
				<th width="120" bgcolor="#edfaff" align="right">法定代表人：</th>
				<td colspan="2">${qyFrom.fddbr}
				<input type="hidden" value="${qyFrom.fddbr}" id="fddbr" name="fddbr">
				</td>
				<th width="120" bgcolor="#edfaff" align="right">行政区划：</th>
				<td colspan="2">${qyFrom.xzqh}
				<input type="hidden" value="${qyFrom.xzqh}" id="xzqh" name="xzqh">
				</td>
			</tr>
			<tr>
				<th width="120" bgcolor="#edfaff" align="right">单位所在地：</th>
				<td colspan="2">${qyFrom.dwszd}
				<input type="hidden" value="${qyFrom.dwszd}" id="dwszd" name="dwszd">
				</td>
				<th width="120" bgcolor="#edfaff" align="right">行政区划代码：</th>
				<td colspan="2">${qyFrom.xzqhCode}
				<input type="hidden" value="${qyFrom.xzqhCode}" id="xzqhCode" name="xzqhCode">
				</td>
			</tr>
			<tr>
				<th rowspan="2" width="60" bgcolor="#edfaff" align="right">地理坐标：</th>
				<th width="40" bgcolor="#edfaff" align="right">中心经度：</th>
				<td width="60">${qyFrom.zxjd}
				<input type="hidden" value="${qyFrom.zxjd}" id="zxjd" name="zxjd">
				</td>
				<th rowspan="2" width="60" bgcolor="#edfaff" align="right">所属网格：</th>
				<th width="40" bgcolor="#edfaff" align="right">网格名称：</th>
				<td width="60">${qyFrom.sswgqy}
				
				</td>
			</tr>
			<tr>
				<th width="40" bgcolor="#edfaff" align="right">中心纬度：</th>
				<td width="60">${qyFrom.zxwd}
				<input type="hidden" value="${qyFrom.zxwd}" id="zxwd" name="zxwd">
				</td>
				<th width="40" bgcolor="#edfaff" align="right">网格描述：</th>
				<td width="60">${qyFrom.wgms}
				</td>
			</tr>
			<tr>
				<th width="120" bgcolor="#edfaff" align="right">所在工业园区名称：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="50"  type="text" id="yqmc"  name="yqmc" value="${qyFrom.yqmc}" /></td>
				<th width="120" bgcolor="#edfaff" align="right">区    号：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="4" type="text" id="qh"  name="qh" value="${qyFrom.qh}" /></td>
			</tr>
			<tr>
				<th width="120" bgcolor="#edfaff" align="right">电话号码：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="15"  type="text" id="dhhm"  name="dhhm" value="${qyFrom.dhhm}" /></td>
				<th width="120" bgcolor="#edfaff" align="right">值班电话：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="15" type="text" id="zbdh"  name="zbdh" value="${qyFrom.zbdh}" /></td>
			</tr>
			<tr>
				<th width="120" bgcolor="#edfaff" align="right">传真号码：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="15"  type="text" id="czhm"  name="czhm" value="${qyFrom.czhm}" /></td>
				<th width="120" bgcolor="#edfaff" align="right">邮政编码：</th>
				<td colspan="2"><input class="i-text easyui-validatebox" maxlength="10" type="text" id="yzbm"  name="yzbm" value="${qyFrom.yzbm}" /></td>
			</tr>
			<tr>
				<th width="120" bgcolor="#edfaff" align="right">企业环境负责人：</th>
				<td colspan="2">${qyFrom.qyhjglrName}
				<input type="hidden" value="${qyFrom.qyhjglrName}" id="qyhjglrName" name="qyhjglrName">
				</td>
				<th width="120" bgcolor="#edfaff" align="right">企业环境负责人联系电话：</th>
				<td colspan="2">${qyFrom.qyhjglrPhone}
				<input type="hidden" value="${qyFrom.qyhjglrPhone}" id="qyhjglrPhone" name="qyhjglrPhone">
				</td>
			</tr>
			<tr>
				<th width="120" bgcolor="#edfaff" align="right">年生产时间 （小时）：</th>
				<td colspan="2"><input class="i-text easyui-numberbox" maxlength="9" type="text" id="nscsj"  name="nscsj" value="${qyFrom.nscsj}" /></td>
				<th width="120" bgcolor="#edfaff" align="right">工业总产值（万元）：</th>
				<td colspan="2"><input class="i-text easyui-numberbox" maxlength="9" type="text" id="zcz"  name="zcz" value="${qyFrom.zcz}" /></td>
			</tr>
			<tr>
				<th width="120" bgcolor="#edfaff" align="right">厂区面积（m<sup>2</sup>）：</th>
				<td colspan="2"><input class="i-text easyui-numberbox" maxlength="9"  type="text" id="cqmj"  name="cqmj" value="${qyFrom.cqmj}" /></td>
				<th width="120" bgcolor="#edfaff" align="right">是否编制突发环境事件应急预案：</th>
				<td colspan="2"><span style="color:#666666;font-weight:bold;line-height:24px;background-color:#f9fcfe;border:0px dotted #95b8e7;font-size:12px;text-align:center;" >无/有：<span/>
				  <input  type="Checkbox" id="sfbzya" name="sfbzya" <c:if test="${qyFrom.sfbzya=='1'}"> checked="checked" </c:if> onclick="check(this)" value="${qyFrom.sfbzya}"/></td>
			</tr>
			<tr>
				<th width="120" bgcolor="#edfaff" align="right">环境风险评价专篇：</th>
				<td colspan="5"><span style="color:#666666;font-weight:bold;line-height:24px;background-color:#f9fcfe;border:0px dotted #95b8e7;font-size:12px;text-align:center;" >否/是：<span/>&nbsp;&nbsp;&nbsp;&nbsp;
				<input  type="Checkbox" id="sfpjwj" name="sfpjwj" <c:if test="${qyFrom.sfpjwj=='1'}"> checked="checked" </c:if> onclick="checkzp(this)" value="${qyFrom.sfpjwj}"/></td>
			</tr>
			<tr>
				<th width="120" bgcolor="#edfaff" align="right">发生突发环境事件情况</th>
				<td colspan="5">截至  <input class="easyui-datetimebox"   data-options="editable:false" style="width:183px;"  type="text" id="sftfDate" name="sftfDate" value="${qyFrom.sftfDate}"/>    
				<span style="color:#666666;font-weight:bold;line-height:24px;background-color:#f9fcfe;border:0px dotted #95b8e7;font-size:12px;text-align:center;" >无/有：<span/>
				  <input  type="Checkbox" id="sftf" name="sftf" <c:if test="${qyFrom.sftf=='1'}"> checked="checked" </c:if> onclick="checksf(this)" value="${qyFrom.sftf}"/>  共    <input class="i-text easyui-numberbox" maxlength="6" type="text" id="sftfcs"  name="sftfcs" value="${qyFrom.sftfcs}" />    次 </td>
			</tr>
			
		</table>
		<div class="t-c" style="margin-top:25px">
			<span class="y-btn"><input id="save" type="submit" value="保存" />
			</span> 
		</div>
	</form>
	<script type="text/javascript">

		$(document).ready(function() {
			//alert("pid==="+$('#pid').val());
			
			
			
			// alert("ceshi");
			//提交表单
			$("#queryForm").validate({
				
				errorClass : "error",
				submitHandler : function(form) {
					if ($("#queryForm").form("validate")){
						
					    // return;
						$(form).ajaxSubmit( {
							type : "post",
							url : "saveQyFrom.json",
							success : function(data) {
								alert(data.msg);
								//self.close();
							}
						});
					}
				}
			});

		});
		//期限时间
    	$("#sftfDate").datetimebox({
    		showSeconds:false,
	    	onSelect: function(date){
	    		var str=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	    		$('#sftfDate').datebox('setValue', str);
	    	},
	    	onChange: function(date){
	    	}
		});
    	//是否编制突发环境事件应急预案
		function check(che){
			 if(che.checked)
			 {
			 $('#sfbzya').val("1");
			 } 
		}
		//环境风险评价专篇
		function checkzp(che){
			 if(che.checked)
			 {
			 $('#sfpjwj').val("1");
			 } 
		}
		//发生突发环境事件情况
		function checksf(che){
			 if(che.checked)
			 {
			 $('#sftf').val("1");
			 } 
		}
	</script>
</body>
</html>
