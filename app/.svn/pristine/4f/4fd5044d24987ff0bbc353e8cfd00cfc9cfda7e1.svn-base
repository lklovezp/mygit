<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
	<title>流程步骤</title>
	<script type="text/javascript" src="${jquery}/jquery.js"></script>
</head>
<body>
<form id="processForm" action="">
<table align="center" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>流程名称：</td>
		<td>${pe.processName }</td>
	</tr>	
</table>
<table align="center" border="0" cellpadding="0" cellspacing="0">	
	<tr>
		<td>
			<button onclick="saveBusiness()">保存</button>
		</td>
		<td>
			<button onclick="saveProcess()">提交</button>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<span id="msg" style="color: red;"></span>
		</td>		
	</tr>
</table>
</form>
<script type="text/javascript">
var success;
var applyId;
var timeId;
var overTimeIndex=15;
var index=0;
function saveCallback(json){
	success=json.success
	applyId=json.applyId;
	if(success){
		showMsg('数据保存成功！');	
	}else{
		showMsg('数据保存失败！');
	}
}
function saveBusiness(){
	showMsg('正在保存数据...');
	draftFrame.save();
}
function saveProcess(){
	//保存表单
	draftFrame.save();
	//启动流程
	//定时检查applyId是否有值
	timeId=setInterval('getApplyId()',1000);
}
function getApplyId(){
	showMsg(index);
	if(success!=null){
		clearInterval(timeId);
		if(success){
			showMsg('正在启动流程...');
			$.post("${ctx}/wf/process/start/${pe.processKey}/"+applyId, {},function(json){
				showMsg(json.msg);
			},'json');
		}
	}
	if(index==overTimeIndex){
		showMsg('数据保存超时！');
		clearInterval(timeId);
	}
	index++;
}
function showMsg(msg){
	$('#msg').html(msg);
}
</script>
<iframe id="draftFrame" frameborder="0" src="${ctx }/${pe.newMethod }" width="100%" height="100%"></iframe>
</body>
</html>