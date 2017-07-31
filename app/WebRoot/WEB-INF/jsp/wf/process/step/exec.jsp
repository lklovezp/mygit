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
			<button onclick="saveProcess()">提交</button>
		</td>
	</tr>
	<tr>
		<td>
			<span id="msg" style="color: red;"></span>
		</td>		
	</tr>
</table>
</form>
<script type="text/javascript">
function saveProcess(){
	//下一步流程
	$.post("${ctx}/wf/process/exec/${taskId}", {},function(json){
		alert(json.msg);
		if(json.success){
			window.close();
		}
	},'json');
}
</script>
<iframe id="execFrame" frameborder="0" src="${ctx }/${pe.infoMethod }/${applyId}" width="100%" height="100%"></iframe>
</body>
</html>