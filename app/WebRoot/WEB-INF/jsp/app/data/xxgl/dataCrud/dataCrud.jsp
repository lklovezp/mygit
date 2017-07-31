<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>数据同步</title>
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<!--jQuery-->
<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<!--时间插件 my97-->
<script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
<style>
.bTn.blue {background: #00a2d9;}
</style>
</head>
    <body>
        <div class="h1_1">数据同步</div>
		<div class="divContainer">
			<table class="dataTable" width="100%" border="1" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
				<tr>
					<td width="50" align="center" style="font-size: 18px; font-weight:normal;background-color:#cff1ff;">
						序号
					</td>
					<td width="150" align="center" style="font-size: 18px; font-weight:normal;background-color:#cff1ff;">
						数据项
					</td>
					<td width="200" align="center" style="font-size: 18px; font-weight:normal;background-color:#cff1ff;">
						上次更新时间
					</td>
					<td width="50" align="center" style="font-size: 18px; font-weight:normal;background-color:#cff1ff;">
						操作
					</td>
					<td width="50" align="center" style="font-size: 18px; font-weight:normal;background-color:#cff1ff;">
						序号
					</td>
					<td width="150" align="center" style="font-size: 18px; font-weight:normal;background-color:#cff1ff;">
						数据项
					</td>
					<td width="200" align="center" style="font-size: 18px; font-weight:normal;background-color:#cff1ff;">
						上次更新时间
					</td>
					<td width="50" align="center" style="font-size: 18px; font-weight:normal;background-color:#cff1ff;">
						操作
					</td>
				</tr>
				<tr>
					<td width="50" align="center">
						1
					</td>
					<td width="150" align="center">
						服务器
					</td>
					<td id="fwqsj" width="200" align="center">
						${dataForm.fwqsj}
					</td>
					<td width="50" align="center" >
					<c:if test="${dataForm.fwqtb == 'Y'}">
							<input id="01tb" type="button" value="同步"  class="bTn blue" onclick="javascript:tb('01')">
					</c:if>
					</td>
					<td width="50" align="center">
						13
					</td>
					<td width="150" align="center">
						勘察询问笔录
					</td>
					<td id="kcxwsj" width="200" align="center">
						${dataForm.kcxwblsj}
					</td>
					<td width="50" align="center">
						<c:if test="${dataForm.kcxwbltb == 'Y'}">
							<input id="02tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('18')">
						</c:if>
					</td>
				</tr>
				<tr>
				<td width="50" align="center">
						2
					</td>
					<td width="150" align="center">
						区域
					</td>
					<td id="qysj" width="200" align="center">
						${dataForm.qysj}
					</td>
					<td width="50" align="center" >
						<c:if test="${dataForm.qytb == 'Y'}">
							<input id="03tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('02')">
						</c:if>
					</td>
					<td width="50" align="center">
						14
					</td>
					<td width="150" align="center">
						版本管理
					</td>
					<td id="bbglsj" width="200" align="center">
						${dataForm.bbglsj}
					</td>
					<td width="50" align="center">
						<c:if test="${dataForm.bbgltb == 'Y'}">
							<input id="04tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('19')">
						</c:if>
					</td>
				</tr>
				<tr>
				 <td width="50" align="center">
						3
					</td>
					<td width="150" align="center">
						部门
					</td>
					<td id="orgsj" width="200" align="center">
						${dataForm.orgsj}
					</td>
					<td width="50" align="center" >
						<c:if test="${dataForm.orgtb == 'Y'}">
							<input id="05tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('03')">
						</c:if>
					</td>
					<td width="50" align="center">
						15
					</td>
					<td width="150" align="center">
						设置检查模板
					</td>
					<td width="200" align="center">
						
					</td>
					<td width="50" align="center">
							<input id="06tb" type="button" value="同步"  class="bTn blue" onclick="javascript:tb('20')">
					</td>
				</tr>
				<tr>
				<td width="50" align="center">
						4
					</td>
					<td width="150" align="center">
						角色
					</td>
					<td id="rolesj" width="200" align="center">
						${dataForm.rolesj}
					</td>
					<td width="50" align="center" >
						<c:if test="${dataForm.roletb == 'Y'}">
							<input id="07tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('04')">
						</c:if>
					</td>
					<td width="50" align="center">
						16
					</td>
					<td width="150" align="center">
						所属行政区
					</td>
					<td id="ssxzqsj" width="200" align="center">
						${dataForm.ssxzqsj}
					</td>
					<td width="50" align="center">
						<c:if test="${dataForm.ssxzqtb == 'Y'}">
							<input id="08tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('21')">
						</c:if>
					</td>
				</tr>
				<tr>
				<td width="50" align="center">
						5
					</td>
					<td width="150" align="center">
						用户
					</td>
					<td id="usersj" width="200" align="center">
						${dataForm.usersj}
					</td>
					<td width="50" align="center" >
						<c:if test="${dataForm.usertb == 'Y'}">
							<input id="09tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('05')">
						</c:if>
					</td>
					<td width="50" align="center">
						17
					</td>
					<td width="150" align="center">
						施工单位
					</td>
					<td id="sgdwsj" width="200" align="center">
						${dataForm.sgdwsj}
					</td>
					<td width="50" align="center">
						<c:if test="${dataForm.sgdwtb == 'Y'}">
							<input id="10tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('22')">
						</c:if>
					</td>
				</tr>
				<tr>
				<td width="50" align="center">
						6
					</td>
					<td width="150" align="center">
						功能
					</td>
					<td id="funcsj" width="200" align="center">
						${dataForm.funcsj}
					</td>
					<td width="50" align="center" >
						<c:if test="${dataForm.functb == 'Y'}">
							<input id="11tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('07')">
						</c:if>
					</td>
					<td width="50" align="center">
						18
					</td>
					<td width="150" align="center">
						执法对象
					</td>
					<td id="zfdxsj" width="200" align="center">
						${dataForm.zfdxsj}
					</td>
					<td width="50" align="center">
						<c:if test="${dataForm.zfdxtb == 'Y'}">
							<input id="12tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('23')">
						</c:if>
					</td>
				</tr>
				<tr>
				<td width="50" align="center">
						7
					</td>
					<td width="150" align="center">
						字典
					</td>
					<td id="dicsj" width="200" align="center">
						${dataForm.dicsj}
					</td>
					<td width="50" align="center" >
						<c:if test="${dataForm.dictb == 'Y'}">
							<input id="13tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('10')">
						</c:if>
					</td>
					<td width="50" align="center">
						19
					</td>
					<td width="150" align="center">
						建设项目环评
					</td>
					<td id="jsxmhpsj" width="200" align="center">
						${dataForm.jsxmhpsj}
					</td>
					<td width="50" align="center">
						<c:if test="${dataForm.jsxmhptb == 'Y'}">
							<input id="14tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('24')">
						</c:if>
					</td>
				</tr>
				<tr>
				<td width="50" align="center">
						8
					</td>
					<td width="150" align="center">
						角色功能
					</td>
					<td width="200" align="center">
						
					</td>
					<td width="50" align="center" >
							<input id="15tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('11')">
					</td>
					<td width="50" align="center">
						20
					</td>
					<td width="150" align="center">
						企业危化信息
					</td>
					<td width="200" align="center">
						
					</td>
					<td width="50" align="center">
							<input id="18tb" type="button" value="同步"  class="bTn blue" onclick="javascript:tb('26')">
					</td>
				</tr>
				<tr>
				<td width="50" align="center">
						9
					</td>
					<td width="150" align="center">
						违法类型
					</td>
					<td id="wflxsj" width="200" align="center">
						${dataForm.wflxsj}
					</td>
					<td width="50" align="center">
						<c:if test="${dataForm.wflxtb == 'Y'}">
							<input id="17tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('14')">
						</c:if>
					</td>
					<td width="50" align="center">
						21
					</td>
					<td width="150" align="center">
						执法文件
					</td>
					<td id="zfwjsj" width="200" align="center">
						${dataForm.zfwjsj}
					</td>
					<td width="50" align="center">
						<c:if test="${dataForm.zfwjtb == 'Y'}">
							<input id="20tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('27')">
						</c:if>
					</td>
				</tr>
				<tr>
				<td width="50" align="center">
						10
					</td>
					<td width="150" align="center">
						任务类型
					</td>
					<td id="rwlxsj" width="200" align="center">
						${dataForm.rwlxsj}
					</td>
					<td width="50" align="center">
						<c:if test="${dataForm.rwlxtb == 'Y'}">
							<input id="19tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('15')">
						</c:if>
					</td>
					<td width="50" align="center">
						22
					</td>
					<td width="150" align="center">
						任务类型执法文件目录
					</td>
					<td id="zfwjmlsj" width="200" align="center">
						${dataForm.zfwjmlsj}
					</td>
					<td width="50" align="center">
						<c:if test="${dataForm.zfwjmltb == 'Y'}">
							<input id="22tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('28')">
						</c:if>
					</td>
				</tr>
				<tr>
					<td width="50" align="center">
						11
					</td>
					<td width="150" align="center">
						行业
					</td>
					<td id="hylxsj" width="200" align="center">
						${dataForm.hylxsj}
					</td>
					<td width="50" align="center">
						<c:if test="${dataForm.hylxtb == 'Y'}">
							<input id="21tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('16')">
						</c:if>
					</td>
					<td width="50" align="center">
						23
					</td>
					<td width="150" align="center">
						自由裁量
					</td>
					<td id="zyclsj" width="200" align="center">
						${dataForm.zyclsj}
					</td>
					<td width="50" align="center">
						<c:if test="${dataForm.zycltb == 'Y'}">
							<input id="24tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('29')">
						</c:if>
					</td>
				</tr>
				<tr>
					<td width="50" align="center">
							12
						</td>
						<td width="150" align="center">
							检查单模板
						</td>
						<td id="jcdmbsj" width="200" align="center">
							${dataForm.jcdmbsj}
						</td>
						<td width="50" align="center">
							<c:if test="${dataForm.jcdmbtb == 'Y'}">
								<input id="23tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('17')">
							</c:if>
						</td>
						<td width="50" align="center">
							24
						</td>
						<td width="150" align="center">
							信访登记表
						</td>
						<td id="xfdjbsj" width="200" align="center">
							${dataForm.xfdjbsj}
						</td>
						<td width="50" align="center">
							<c:if test="${dataForm.xfdjbtb == 'Y'}">
								<input id="25tb" type="button" value="同步" class="bTn blue" onclick="javascript:tb('30')">
							</c:if>
						</td>
				</tr>
			</table>
		</div>
	</body>
</body>
</html>
<script type="text/javascript">
var btn0 = document.getElementById('01tb');
var btn1 = document.getElementById('03tb');
var btn2 = document.getElementById('05tb');
var btn3 = document.getElementById('07tb');
var btn4 = document.getElementById('09tb');
var btn5 = document.getElementById('11tb');
var btn6 = document.getElementById('13tb');
var btn7 = document.getElementById('15tb');
var btn8 = document.getElementById('17tb');
var btn9 = document.getElementById('19tb');
var btn10 = document.getElementById('21tb');
var btn11 = document.getElementById('23tb');
var btn12 = document.getElementById('02tb');
var btn13 = document.getElementById('04tb');
var btn14 = document.getElementById('06tb');
var btn16 = document.getElementById('08tb');
var btn17 = document.getElementById('10tb');
var btn18 = document.getElementById('12tb');
var btn19 = document.getElementById('14tb');
var btn20 = document.getElementById('18tb');
var btn21 = document.getElementById('20tb');
var btn22 = document.getElementById('22tb');
var btn23 = document.getElementById('24tb');
var btn24 = document.getElementById('25tb');
function tb(type){
	if(type == '01'){
		btn0.disabled = "disabled";
	 }
	 else if(type == '02'){
	 	btn1.disabled = "disabled";
	 }
	 else if(type == '03'){
	 	btn2.disabled = "disabled";
	 }
	 else if(type == '04'){
	 	btn3.disabled = "disabled";
	 }
	 else if(type == '05'){
	 	btn4.disabled = "disabled";
	 }
	 else if(type == '07'){
	 	btn5.disabled = "disabled";
	 }
	 else if(type == '10'){
	 	btn6.disabled = "disabled";
	 }
	 else if(type == '11'){
	 	btn7.disabled = "disabled";
	 }
	 else if(type == '14'){
	 	btn8.disabled = "disabled";
	 }
	 else if(type == '15'){
	 	btn9.disabled = "disabled";
	 }
	 else if(type == '16'){
	 	btn10.disabled = "disabled";
	 }
	 else if(type == '17'){
	 	btn11.disabled = "disabled";
	 }
	 else if(type == '18'){
	 	btn12.disabled = "disabled";
	 }
	 else if(type == '19'){
	 	btn13.disabled = "disabled";
	 }
	 else if(type == '20'){
	 	btn14.disabled = "disabled";
	 }
	 else if(type == '21'){
		btn16.disabled = "disabled";
	 }
	 else if(type == '22'){
	 	btn17.disabled = "disabled";
	 }
	 else if(type == '23'){
	 	btn18.disabled = "disabled";
	 }
	 else if(type == '24'){
	 	btn19.disabled = "disabled";
	 }
	 else if(type == '26'){
	 	btn20.disabled = "disabled";
	 }
	 else if(type == '27'){
	 	btn21.disabled = "disabled";
	 }
	 else if(type == '28'){
	 	btn22.disabled = "disabled";
	 }
	 else if(type == '29'){
	 	btn23.disabled = "disabled";
	 }
	 else if(type == '30'){
	 	btn24.disabled = "disabled";
	 }
	
	$.ajax({
	  url: "queryDataList.json?type="+type,
	  success:function(data){
	  	if(data.state){
			$.ajax({
				url: "dataCrudtime.json?type="+type,
				success:function(data){
					if(data.date!=null){
					 if(type == '01'){
					 	document.getElementById('fwqsj').innerHTML = data.date;
					 	alert("服务器数据同步完成！");
					 	btn0.removeAttribute('disabled');
					 }
					 else if(type == '02'){
					 	document.getElementById('qysj').innerHTML = data.date;
					 	alert("区域数据同步完成！");
					 	btn1.removeAttribute('disabled');
					 }
					 else if(type == '03'){
					 	document.getElementById('orgsj').innerHTML = data.date;
					 	alert("部门数据同步完成！");
					 	btn2.removeAttribute('disabled');
					 }
					 else if(type == '04'){
					 	document.getElementById('rolesj').innerHTML = data.date;
					 	alert("角色数据同步完成！");
					 	btn3.removeAttribute('disabled');
					 }
					 else if(type == '05'){
					 	document.getElementById('usersj').innerHTML = data.date;
					 	alert("用户数据同步完成！");
					 	btn4.removeAttribute('disabled');
					 }
					 else if(type == '07'){
					 	document.getElementById('funcsj').innerHTML = data.date;
					 	alert("功能数据同步完成！");
					 	btn5.removeAttribute('disabled');
					 }
					 else if(type == '10'){
					 	document.getElementById('dicsj').innerHTML = data.date;
					 	alert("字典数据同步完成！");
					 	btn6.removeAttribute('disabled');
					 }
					 else if(type == '11'){
					 	//document.getElementById('wflxsj').innerHTML = data.date;
					 	alert("角色功能数据同步完成！");
					 	btn7.removeAttribute('disabled');
					 }
					 else if(type == '14'){
					 	document.getElementById('wflxsj').innerHTML = data.date;
					 	alert("违法类型数据同步完成！");
					 	btn8.removeAttribute('disabled');
					 }
					 else if(type == '15'){
					 	document.getElementById('rwlxsj').innerHTML = data.date;
					 	alert("任务类型数据同步完成！");
					 	btn9.removeAttribute('disabled');
					 }
					 else if(type == '16'){
					 	document.getElementById('hylxsj').innerHTML = data.date;
					 	alert("行业数据同步完成！");
					 	btn10.removeAttribute('disabled');
					 }
					 else if(type == '17'){
					 	document.getElementById('jcdmbsj').innerHTML = data.date;
					 	alert("检查单模板同步完成！");
					 	btn11.removeAttribute('disabled');
					 }
					 else if(type == '18'){
					 	document.getElementById('kcxwsj').innerHTML = data.date;
					 	alert("勘察询问笔录同步完成！");
					 	btn12.removeAttribute('disabled');
					 }
					 else if(type == '19'){
					 	document.getElementById('bbglsj').innerHTML = data.date;
					 	alert("版本管理同步完成！");
					 	btn13.removeAttribute('disabled');
					 }
					 else if(type == '20'){
					 	//document.getElementById('ssxzqsj').innerHTML = data.date;
					 	alert("设置检查模板同步完成！");
					 	btn14.removeAttribute('disabled');
					 }
					 else if(type == '21'){
					 	document.getElementById('ssxzqsj').innerHTML = data.date;
					 	alert("所属行政同步完成！");
						btn16.removeAttribute('disabled');
					 }
					 else if(type == '22'){
					 	document.getElementById('sgdwsj').innerHTML = data.date;
					 	alert("施工单位区同步完成！");
					 	btn17.removeAttribute('disabled');
					 }
					 else if(type == '23'){
					 	document.getElementById('zfdxsj').innerHTML = data.date;
					 	alert("执法对象同步完成！");
					 	btn18.removeAttribute('disabled');
					 }
					 else if(type == '24'){
					 	document.getElementById('jsxmhpsj').innerHTML = data.date;
					 	alert("建设项目环评同步完成！");
					 	btn19.removeAttribute('disabled');
					 }
					 else if(type == '26'){
					 	//document.getElementById('zfwjsj').innerHTML = data.date;
					 	alert("企业危化信息同步完成！");
					 	btn20.removeAttribute('disabled');
					 }
					 else if(type == '27'){
					 	document.getElementById('zfwjsj').innerHTML = data.date;
					 	alert("执法文件同步完成！");
					 	btn21.removeAttribute('disabled');
					 }
					 else if(type == '28'){
					 	document.getElementById('zfwjmlsj').innerHTML = data.date;
					 	alert("任务类型执法文件目录同步完成！");
					 	btn22.removeAttribute('disabled');
					 }
					 else if(type == '29'){
					 	document.getElementById('zyclsj').innerHTML = data.date;
					 	alert("自由裁量同步完成！");
					 	btn23.removeAttribute('disabled');
					 }
					 else if(type == '30'){
					 	document.getElementById('xfdjbsj').innerHTML = data.date;
					 	alert("信访登记表同步完成！");
					 	btn24.removeAttribute('disabled');
					 }
	      			}
				}
			});
		}
	   }
	});
}
</script>