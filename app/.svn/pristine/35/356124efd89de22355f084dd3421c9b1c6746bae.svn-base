<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName }:${pageContext.request.serverPort }${ctx }/"/>
    <title>字典选择</title>
    
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${jquery}/messages_cn.js"></script>

	<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css"/>
	<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
  
  <body>
    <div style="margin: 20px;">
    	<table id="myTable"></table>
    </div>
    <div id="toolbar">  
        <a href="javascript:void(0)" 
	        class="easyui-linkbutton" 
	        onclick="ok()">确定</a>  
    </div>  
<script type="text/javascript">
<!--
$(document).ready(function(){
  $('#myTable').datagrid({
        width:'100%',
		height:'500',
		rownumbers: true,
		<c:if test="${oper eq 's'}">
		singleSelect:true,
		</c:if>
		toolbar: '#toolbar',
		url:'dic_sel_list.json',
		columns:[[
		<c:choose> 
		 <c:when test="${oper eq 's'}">
			{field:'id',hidden:true},
		 </c:when> 
		 <c:otherwise> 
			{field:'id',checkbox:true},
		 </c:otherwise> 
		</c:choose> 
	        {field:'name',title:'内容',width:600,halign:'center',align:'left'}
	    ]],
	    queryParams: {
			dicType: '1'
	    }
	});
});
//选择
function ok(){
	var ids='';
	var names='';
	var selections=$('#myTable').datagrid('getSelections');
	if(selections==''){
	}else{
		$.each(selections,function(index,rowData){
			ids+=rowData.id+',';
			names+=rowData.name+',';
		});
	}
	parent.setDic(ids,names);
	parent.$.colorbox.close();
}
</script>    
  </body>
</html>
