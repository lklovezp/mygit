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
			<input type="hidden" id="id" name="id" value="${gywryForm.id}"/>
			<input type="hidden" id="lawobjtypeid" name="lawobjtypeid" value="${lawobjtypeid}"/>
			<input type="hidden" id="lawobjfid" name="lawobjfid" value="${gywryForm.lawobjfid}"/>
			<input type="hidden" id="jsxmid" name="jsxmid" value="${gywryForm.jsxmid}"/>
			<input type="hidden" id="qyscztj" name="qyscztj" value='${gywryForm.qysczt}'/>
			<input type="hidden" id="lawobjId" name="lawobjId" value=''/>
			<div id="condition">
			<table class='dataTable basicinfoTable' width='100%' cellpadding='0' cellspacing='0'>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
统一社会信用代码：</td>
<td colspan='3'>
${gywryForm.tyshxydm}
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>组织机构代码：</td>
<td colspan='3'>
${gywryForm.zzjgdm}
</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>单位名称：</td>
<td colspan='3'>
${gywryForm.dwmc}
</tr>

<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>单位地址：</td>
<td colspan='3'>
${gywryForm.dwdz}
</td>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>所属行政区：</td>
<td >
${gywryForm.ssxzqmc}
</td>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>行业：</td>
<td >
${gywryForm.hylxmc}
</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
经度：</td>
<td>
${gywryForm.jd1 }</td>
<td width='150' bgcolor='#edfaff' align='right'>
纬度：</td>
<td>
${gywryForm.wd1 }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>法定代表人：</td>
<td>
${gywryForm.fddbr }
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>法定代表人联系电话：</td>
<td>
${gywryForm.fddbrdh }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
环保负责人：</td>
<td>
${gywryForm.hbfzr }</td>
<td width='150' bgcolor='#edfaff' align='right'>
环保负责人联系电话：</td>
<td>
${gywryForm.hbfzrdh }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>营业执照注册号：</td>
<td>
${gywryForm.yyzzzch }</td>
<td width='150' bgcolor='#edfaff' align='right'>
排污许可证：</td>
<td>
${gywryForm.pwxkz }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
废水排放口个数：</td>
<td>
${gywryForm.fspfks }</td>
<td width='150' bgcolor='#edfaff' align='right'>
废气排放口个数：</td>
<td>
${gywryForm.fqpfks }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
噪声源个数：</td>
<td>
${gywryForm.zsygs }</td>
<td width='150' bgcolor='#edfaff' align='right'>
固废堆放场个数：</td>
<td>
${gywryForm.gfdfcgs }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
污染防治设施数量：</td>
<td>
${gywryForm.wrfzsssl }</td>
<td width='150' bgcolor='#edfaff' align='right'>
危废储存场个数：</td>
<td>
${gywryForm.wfcccgs }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
风险源个数：</td>
<td>
${gywryForm.fxygs }</td>
<!-- 
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>行业：</td>
<td>
<input class='i-text easyui-validatebox' type='text'  name='column20' id='gywry_hy'/></td> -->
<td width='150' bgcolor='#edfaff' align='right'>
所属监管部门：</td>
<td>
${gywryForm.ssjgbmmc }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>控制类型：</td>
<td>
${gywryForm.kzlxmc}</td>
<td width='150' bgcolor='#edfaff' align='right'>
所属网格：</td>
<td>
${gywryForm.sswgqymc}</td>
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
${gywryForm.qyztmc}</td>
<td width='150' bgcolor='#edfaff' align='right'>
创建人：</td>
<td>
<c:if test="${gywryForm.id==null}">
<input type="hidden" id="cjr" name="cjr" value="${cjr}"/>
<input class='i-text' type='text' style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '  name='cjrname' id='cjrname' value="${cjrname}" /></td>
</c:if>
<c:if test="${gywryForm.id !=null&& gywryForm.id!=''}">
<input class='i-text' type='text' style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '  name='cjr' id='cjr' value="${gywryForm.cjr}" /></td>
</c:if>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>企业生产状态：</td>
<td colspan='3'>

${gywryForm.qysczt1}

</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
投产日期：</td>
<td>
${gywryForm.tcrq}</td>
<td width='150' bgcolor='#edfaff' align='right'>
邮编：</td>
<td>
${gywryForm.yb}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
企业规模：</td>
<td>
${gywryForm.qygm}</td>
<td width='150' bgcolor='#edfaff' align='right'>
名称简拼：</td>
<td>
${gywryForm.mcjp}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
别名：</td>
<td>
${gywryForm.bm}</td>
<td width='150' bgcolor='#edfaff' align='right'>
曾用名：</td>
<td>
${gywryForm.cym}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
企业编号：</td>
<td>
${gywryForm.qybh}</td>
<td width='150' bgcolor='#edfaff' align='right'>
申报代码：</td>
<td>
${gywryForm.sbdm}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
注册类型：</td>
<td>
${gywryForm.zclxmc}</td>
<td width='150' bgcolor='#edfaff' align='right'>
国控类型：</td>
<td>
${gywryForm.gklxmc}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
隶属关系：</td>
<td>
${gywryForm.lsgxmc}</td>
<td width='150' bgcolor='#edfaff' align='right'>
流域：</td>
<td>
${gywryForm.ly}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
总占地面积：</td>
<td>
${gywryForm.zzdmj}</td>
<td width='150' bgcolor='#edfaff' align='right'>
是否收费企业：</td>
<td>
${gywryForm.sfsfqymc}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
和央企关系：</td>
<td>
${gywryForm.hyqgxmc}</td>
<td width='150' bgcolor='#edfaff' align='right'>
专职环保人员数：</td>
<td>
${gywryForm.zzhbrys}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
企业环保部门：</td>
<td>
${gywryForm.qyhbbm}</td>
<td width='150' bgcolor='#edfaff' align='right'>
电子邮件：</td>
<td>
${gywryForm.dzyj}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
传真：</td>
<td>
${gywryForm.cz}</td>
<td width='150' bgcolor='#edfaff' align='right'>
开户银行：</td>
<td>
${gywryForm.khyh}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
银行账号：</td>
<td>
${gywryForm.yhzh}</td>
<td width='150' bgcolor='#edfaff' align='right'>
企业网址：</td>
<td>
${gywryForm.qywz}</td>
</tr>
</table>
 
</body>
</html>
 
