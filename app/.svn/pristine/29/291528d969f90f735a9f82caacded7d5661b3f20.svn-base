<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
	</HEAD>

<body>

<div id="neirong" style="padding: 5px;">
		    
		<table width="95%" border="0" cellpadding="0" cellspacing="0" class="infotable">
		<c:forEach var="po" items="${re}">
		  <tr>
		    <th width="80" height="26" align="right">${po.czrName}</th>
		    <td width="250" align="left">于<fmt:formatDate value="${po.czsj}" type="both"/>   ${po.operateTypeNote}了任务</td>
		    <td align="left">[${po.workName}]</td>
	      </tr>
	      			</c:forEach>
</table>
	</div>

</body>
</HTML>