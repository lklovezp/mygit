<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>统计详细信息</title>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/icon.css" />
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/default/easyui.css" />
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
	</head>
	
	<body >
		<div id="neirong" class="divContainer">
		<form id="queryForm" method="post" action="homeInfoQuery.json">
			<table width="80%" border="0" cellpadding="0" cellspacing="0"
				class="infotable">
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" />
				<input type="hidden" id="hName" name="hName" value="${hName}" />
				<input type="hidden" id="hValue" name="hValue" value="${hValue}" />
			</table>
			</form>
		</div>
		
		<div class="divContainer" id="homeList">
			<table id="test" fit="true"></table>
		</div>
	</body>

</html>
<SCRIPT LANGUAGE="JavaScript">
$("#homeList").height($(window).height()-30);
$(document).ready(function(){
		$('#test').datagrid({
			    nowrap: true,
				striped: true,				
				singleSelect:true,
				remoteSort:true,	
				url:'homeInfoQuery.json',
				onLoadSuccess:function(data){
      		    $(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
                                    },
	            columns:[[
					{field:'workType',title:'任务类型',sortable:true,width:70,align:'center'},
					{field:'workName',title:'任务名称',width:200},
					{field:'zfdxName',title:'执法对象',width:200},
					{field:'created',title:'创建时间',width:70,align:'center'},
					{field:'start',title:'要求开始时间',width:85,align:'center'},
					{field:'end',title:'要求结束时间',width:85,align:'center'},
					{field:'createby',title:'创建人',width:70},
					{field:'zxrName',title:'执行人',width:70},
					{field:'zxtime',title:'执行时间',width:70,align:'center'},
					{field:'nextOper',title:'待处理人',width:70},
					{field:'state',title:'任务状态',width:70,align:'center'}
				]],
				queryParams: {
					hName: '${hName}',
					hValue: '${hValue}'
			    },
				pagination:true,
				rownumbers:true
	});
    initPager()
});
function initPager(){
    var p = $('#test').datagrid('getPager');
	$(p).pagination({
				onSelectPage:function(pageNumber, pageSize){
				    $('#page').val(pageNumber);
				    $('#pageSize').val(pageSize);
					$(this).pagination('loading');
					$('#queryForm').submit();
					$(this).pagination('loaded');
				}
	});
}
$('#queryForm').submit(function(){
      $("#queryForm").ajaxSubmit({
     	  success: function(data){
     	      $('#test').datagrid('loadData',data)
	          initPager();
	      }
	 });
     return false;  
});
</script>