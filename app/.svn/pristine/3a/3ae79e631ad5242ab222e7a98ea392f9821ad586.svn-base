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
<table align="center" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>流程名称：</td>
		<td>${pe.processName }</td>
	</tr>	
</table>
<table align="center" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<iframe id="execFrame" frameborder="0" src="${ctx }/${pe.infoMethod }/${applyId}" width="100%" height="100%"></iframe>
		</td>
		<td>
<table align="center" border="0" cellpadding="2" cellspacing="0" style="margin: 5px">	
	<tr>
		<td>意见</td>
		<td>
			<select onchange="fillOpinion(this.options[selectedIndex].value)">
				<option value="请选择">请选择</option>
				<option value="同意！">同意！</option>
				<option value="不同意！">不同意！</option>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<textarea rows="3" cols="20" id="p_opinion"></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<button onclick="saveProcess()">提交</button>&nbsp;
			<button onclick="rollbackPrevious()">退回到上一步</button>
		</td>
	</tr>
</table>
		</td>
	</tr>	
</table>
<script type="text/javascript">
function saveProcess(){
	//下一步流程
	$.post("${ctx}/wf/process/exec/${taskId}", {},function(json){
		alert(json.msg);
		if(json.state){
			window.close();
		}
	},'json');
}
function rollbackPrevious(){
	$.post("${ctx}/wf/process/processRollback/${taskId}", {},function(json){
		
	},'json');
}
</script>

</body>
</html>