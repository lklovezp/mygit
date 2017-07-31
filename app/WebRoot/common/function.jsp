<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="divContainer">
  <c:forEach var="po" items="${funs}">
  <a href="#" onclick="${po.function}">${po.name}</a>
  </c:forEach>
</div>