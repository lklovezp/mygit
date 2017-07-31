<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>流程历史</title>
</head>
<body>
<h2 align="center">流程历史</h2>
<table align="center" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<th>执行人</th>
		<th>任务名称</th>
		<th>意见</th>
		<th>结果</th>
		<th>备注</th>
	</tr>
	<c:forEach items="${applyStepList }" var="applyStep">
		<tr>
			<td>${applyStep.execUser }</td>
			<td>${applyStep.taskName }</td>
			<td>${applyStep.opinion==null?'&nbsp;':applyStep.opinion }</td>
			<td>${applyStep.result }</td>
			<td>${applyStep.note==null?'&nbsp;':applyStep.note }</td>
		</tr>
	</c:forEach>	
</table>
</body>
</html>