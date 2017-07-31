<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>污染源自动监控设施采样单元重点检查表</title>
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
<div class="headTitle">污染源自动监控设施采样单元重点检查表</div>
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
				废水采样单元
			</td>
			<td colspan="5" align="left">
				1、采样点与分析仪器连接正常联通
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
				1、存在给水、排水管路外的其他旁路
				<c:if test="${zxzzForm.sf10000009 == '1'}">
					<input type="checkbox" name="sf10000009" id="sf10000009" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000009 != '1'}">
					<input type="checkbox" name="sf10000009" id="sf10000009" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
				2、反冲洗管路是否存在对采集水样的稀释现象
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
				2、反冲洗管路存在对采集水样的稀释现象
				<c:if test="${zxzzForm.sf10000010 == '1'}">
					<input type="checkbox" name="sf10000010" id="sf10000010" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000010 != '1'}">
					<input type="checkbox" name="sf10000010" id="sf10000010" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
				3、水样预处理装置与验收文件、登记备案或最近一次有效性审核一致，无过度处理现象
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
				3、水样预处理装置与验收文件、登记备案或最近一次有效性审核不一致，存在过度处理现象
				<c:if test="${zxzzForm.sf10000011 == '1'}">
					<input type="checkbox" name="sf10000011" id="sf10000011" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000011 != '1'}">
					<input type="checkbox" name="sf10000011" id="sf10000011" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="11" align="center">
				废气采样单元
			</td>
			<td colspan="5" align="left">
				1、加热采样探头内部及滤芯无玷污和堵塞现象，其过滤器加热温度符合仪器说明书要求。
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
				1、加热采样探头内部及滤芯玷污和堵塞
				<c:if test="${zxzzForm.sf10000012 == '1'}">
					<input type="checkbox" name="sf10000012" id="sf10000012" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000012 != '1'}">
					<input type="checkbox" name="sf10000012" id="sf10000012" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
				2、采样伴热管的长度通常在76m以内，且其走向向下倾斜度大于5°，管路无低凹或凸起，伴热管温度通常大于120℃（直接抽取法）
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
				2、采样探头过滤器加热温度不符合仪器说明书要求
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
				3、反吹系统正常工作，反吹气压缩机正常工作
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
				3、目测加热导管存在平直的管段或明显U型管段
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
				4、稀释比恒定,其数值与登记备案一致（稀释抽取法）
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
				4、管线存在纽结、缠绕或断裂的现象
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
				5、气水分离器工作正常
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
				5、伴热管温度过低
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
			</td>
			<td colspan="5" align="left">
				6、反吹周期、时间、空压机表头压力不符合仪器说明书要求
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
			</td>
			<td colspan="5" align="left">
				7、稀释气流量及样品气流量不稳定
				<c:if test="${zxzzForm.sf10000018 == '1'}">
					<input type="checkbox" name="sf10000018" id="sf10000018" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000018 != '1'}">
					<input type="checkbox" name="sf10000018" id="sf10000018" value="1"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="left">
			</td>
			<td colspan="5" align="left">
				8、稀释比、流量与登记备案不一致
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
			</td>
			<td colspan="5" align="left">
				9、气水分离器冷凝器温度与登记备案不一致
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
				10、干燥器滤芯变色
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
				11、冷凝器无冷凝水排出
				<c:if test="${zxzzForm.sf10000022 == '1'}">
					<input type="checkbox" name="sf10000022" id="sf10000022" value="1" checked/>
				</c:if>
				<c:if test="${zxzzForm.sf10000022 != '1'}">
					<input type="checkbox" name="sf10000022" id="sf10000022" value="1"/>
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