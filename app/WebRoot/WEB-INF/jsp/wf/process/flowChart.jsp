<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>流程图</title>
  </head>
<body>
<img src="${ctx}/wf/process/getFlowChart?piId=${piId}" alt="流程图" style="position:absolute;left:0px;top:0px;"/>
<c:if test="${position!=null }">
	<div style="position:absolute;border: 1px solid red; left:${position.x}px; top:${position.y}px;width:${position.w}px;height:${position.h}px;">
		
	</div>
	<div style="position:absolute;left:${position.x}px; top:${position.y+position.h+5}px;">
		<p>
		<c:choose>
			<c:when test="${assignee!=''}">正在由${assignee }处理</c:when>
			<c:otherwise>处理中...</c:otherwise>
		</c:choose>
		</p>	
	</div>
</c:if>
</body>
</html>