<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<script type="text/javascript" src="${app}/js.js"></script>
		<script type="text/javascript" src="${app}/data.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery }/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery }/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery }/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui }/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${easyui }/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox }/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui }/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui }/themes/default/easyui.css">
		<link href="${app }/hnjz.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${colorbox }/colorbox.css">
		<script type="text/javascript" src="${common}/All.js"></script>
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
		<link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
		<style>
        .basicinfoTable,.basicinfoTable td{ border: 1px solid #d4d4d4;}
        .basicinfoTable a{color:#0088cc;}
        .panel-header{background-color: #cff1ff;}
        .formtable, .formtable th, .formtable td{border-color:#dddddd;}
         .datagrid-header{
         background: #cff1ff;
         }
        .tabs li a.tabs-inner {
               background: #cff1ff;
               color: #000000;
               filter: none;
            }
           input{ border-left:0px;
                  border-top:0px;
                  border-right:0px;
                  border-bottom:1px;} 
      </style>
	</head>
 
	<body style="padding-bottom: 70px;">
		<div class="h1_1" id="divTitle">${title}</div>
		<form id="queryForm" action="savegywry.json" method="post">
		 <input type="hidden" id="fid" name="fid" value="${fid}" />
			<input type="hidden" id="id" name="id" value="${lawobjfForm.id}"/>
			<input type="hidden" id="lawobjtypeid" name="lawobjtypeid" value="${lawobjtypeid}"/>
			
			<input type="hidden" id="jsxmid" name="jsxmid" value="${lawobjfForm.jsxmid}"/>
			<input type="hidden" id="qyscztj" name="qyscztj" value='${lawobjfForm.qysczt}'/>
			<input type="hidden" id="lawobjId" name="lawobjId" value=''/>
			<div id="condition">
			<table class='dataTable basicinfoTable' width='100%' cellpadding='0' cellspacing='0'>


<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>单位名称：</td>
<td colspan='3'>
${lawobjfForm.dwmc}
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>所属行政区：</td>
<td colspan='3'>
${lawobjfForm.ssxzqmc}
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>单位地址：</td>
<td colspan='3'>
${lawobjfForm.dwdz}
</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
经度：</td>
<td>
${lawobjfForm.jd1 }</td>
<td width='150' bgcolor='#edfaff' align='right'>
纬度：</td>
<td>
${lawobjfForm.wd1 }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>法定代表人：</td>
<td>
${lawobjfForm.fddbr }
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>法定代表人联系电话：</td>
<td>
${lawobjfForm.fddbrdh }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
环保负责人：</td>
<td>
${lawobjfForm.hbfzr }</td>
<td width='150' bgcolor='#edfaff' align='right'>
环保负责人联系电话：</td>
<td>
${lawobjfForm.hbfzrdh }</td>
</tr>

<td width='150' bgcolor='#edfaff' align='right'>
废水排放口个数：</td>
<td>
${lawobjfForm.fspfks }</td>
<td width='150' bgcolor='#edfaff' align='right'>
废气排放口个数：</td>
<td>
${lawobjfForm.fqpfks }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
噪声源个数：</td>
<td>
${lawobjfForm.zsygs }</td>
<td width='150' bgcolor='#edfaff' align='right'>
固废堆放场个数：</td>
<td>
${lawobjfForm.gfdfcgs }</td>
</tr>

<tr>

<!-- 
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>行业：</td>
<td>
<input class='i-text easyui-validatebox' type='text'  name='column20' id='gywry_hy'/></td> -->
<td width='150' bgcolor='#edfaff' align='right'>
所属监管部门：</td>
<td>
${lawobjfForm.ssjgbmmc }</td>
</tr>

<td width='150' bgcolor='#edfaff' align='right'>
所属网格：</td>
<td>
${lawobjfForm.sswgqymc}</td>
<!-- 
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>状态：</td>
 <td>
<input class='i-text easyui-validatebox' type='text'  name='isActive' id='zt'/></td>

</tr>
-->
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>企业状态：</td>
<td>
${lawobjfForm.qyztmc}</td>
<td width='150' bgcolor='#edfaff' align='right'>
创建人：</td>
<td>
<c:if test="${lawobjfForm.id==null}">
<input type="hidden" id="cjr" name="cjr" value="${cjr}"/>
<input class='i-text' type='text' style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '  name='cjrname' id='cjrname' value="${cjrname}" /></td>
</c:if>
<c:if test="${lawobjfForm.id !=null&& lawobjfForm.id!=''}">
<input class='i-text' type='text' style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '  name='cjr' id='cjr' value="${lawobjfForm.cjr}" /></td>
</c:if>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>企业生产状态：</td>
<td colspan='3'>

${lawobjfForm.qysczt1}

</td>
</tr>

</table>
 
</body>
</html>
 
