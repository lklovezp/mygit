<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>任务流转记录</title>
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link rel="stylesheet" href="${app}/easyUIReset.css"  type="text/css" />
<link rel="stylesheet" href="${app}/attachFileList.css" type="text/css">
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<!--任务流转记录-->
<link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div style="width: 720px; margin:25px auto;">
<input type="hidden" id="applyId" name="applyId" value="${work.id}" />
<h1 style="text-align: center;height: 50px;line-height: 50px;color: #262626;font-size: 18px;">任务流转记录</h1>
  <div style="margin-top:20px; width: 720px;">
    <table style="width:100%;">
      <tbody>
      <c:forEach var="po" items="${rwlzjlMap.workLogList}" varStatus="indexstuts">
        <tr>
          <td><table align="center" width="100%"  align="center" width="100%" class="taskTable"  cellpadding="0" cellspacing="0">
      <tbody>
        <tr class="taskFlowbgcolor">
          <td width="30%"><font color="#00A2D9">【${po.operateTypeNote}】</font></td>
          <td width="30%" align="center">${po.czrName}</td>
          <td width="40%" align="right"> ${po.czsj} </td>
        </tr>
        <tr>
          <td colspan="3"><div class="taskFlow_content" style="overflow:auto;width:100%;height:100px;">${po.note}</div></td>
        </tr>
      </tbody>
    </table>
    </td>
    </tr>
    <c:if test="${indexstuts.last==false}">
    <tr>
      <td align="center"><div class="taskFlow_down"></div></td>
    </tr>
    </c:if>
    </c:forEach>
    </tbody>
    </table>
  </div>
</div>
</body>
</html>