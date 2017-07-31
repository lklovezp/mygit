<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>我的任务</title>
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/icon.css">
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript"
			src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<!-- app -->
		<link rel="stylesheet" type="text/css" href="${app}/hnjz.css">
		
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>	
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
	</head>
	<body>
		<div id="tablelist" class="divContainer">
			<table id="myTable" fit="true"></table>
		</div>
	</body>
	<script type="text/javascript">
$(document).ready(function(){
    $("#tablelist").height($(window).height()-100);
	$('#myTable').datagrid({   
	    url:'${ctx}/wf/process/mineData.json', 
	    width:'100%',
		height:'auto',
		rownumbers: true,
		singleSelect: true,
	    onLoadSuccess:function(data){
      		    $(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
                                    },
	    columns:[[  
	        {field:'applyName',title:'任务名称',width:400},   
	        {field:'applyType',title:'任务类型',width:100,align:'center'},   
	        {field:'taskName',title:'任务状态',width:100,align:'center'},
	        {field:'nextActions',title:'操作',width:150,align:'center'}   
	    ]]
	});  
	
});
function flowChart(piId){
	$.colorbox({
		iframe:true,
		width:"800", 
		height:"500",
		href:"flowChart?piId="+piId,
		title:'流程图'
	});
}
function actionOper(code,action,actionType,applyId,taskId,text){
	if(code=='sbyj'){
		$.post('${ctx}/work/zx/shangbao.json', {workId:applyId}, function(json){
			if(json.state){//可以上报，进行上报移交操作
			$.messager.confirm('操作确认', '确定要'+text+'?', function(r){
		       if (r){
					$.post('${ctx}/'+action, {applyId:applyId,taskId:taskId}, function(json){
						$.messager.alert('提示',json.msg,'info',function(){
							if(json.state){
								$('#myTable').datagrid('reload');
							}
						});
					},'json');
				}
			 });
			}else{
				$.messager.alert('提示',json.msg,'info',function(){
					window.location.href='${ctx}/work/zx/zxPage?applyId='+applyId+'&taskId='+taskId;
				});
			}
		},'json');
		return;
	}
	$.messager.confirm('操作确认', '确定要'+text+'?', function(r){
		if (r){
			if(actionType=='AJAX'){
					$.post('${ctx}/'+action, {applyId:applyId,taskId:taskId}, function(json){
						$.messager.alert('提示',json.msg,'info',function(){
							if(json.state){
								$('#myTable').datagrid('reload');
							}
						});
					},'json');
			}else if('NEW_WIN'){
				window.location.href='${ctx}/'+action+'&applyId='+applyId+'&taskId='+taskId;
			}
		}
	});
}
</script>
</html>