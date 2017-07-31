<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>流程监管</title>
	<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
    <link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
    <!-- 任务管理 css-->
    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
    <!--系统管理 css-->
    <link rel="stylesheet" href="${app}/css/management.css" type="text/css">
    <style>
     a {color:#0088cc;}
    </style>
	</head>
	<body>
         
         <!--已定义流程 -->
      <div class="definedTable">
          <h3 class="task_h3 defined_h3"><span class="h_icon"></span>已定义流程</h3>
          <div class="dataDiv" style="width:94%; margin:16px auto 5px;">
              <table id="mytable1" ></table>
          </div>
       </div> 
        

		  <!--已发布流程 -->
       <div class="definedTable" style="margin-bottom: 30px;">
          <h3 class="task_h3 defined_h3 mt25"><span class="h_icon"></span>已发布流程</h3>
          <div class="dataDiv" style="width:94%; margin:16px auto 5px;">
              <table id="mytable2" ></table>
          </div>
		</div>
		
	</body>
</html>
<script type="text/javascript">
		$(document).ready(function(){
			$('#mytable1').datagrid({   
			    url:'${ctx}/wf/process/defineProcedure.json', 
			    fitColumns:true,//自适应列宽
			    striped: true,//显示条纹
				height:'auto',
				rownumbers: true,
				singleSelect: true,
			    
			    columns:[[  
			        {field:'processName',title:'任务名称',width:400,align:'left'},   
			        {field:'processKey',title:'任务类型',width:100,align:'center'},
			        {field:'nextActions',title:'操作',width:100,align:'center'}
			    ]]
			});  
		$('#mytable2').datagrid({   
				    url:'${ctx}/wf/process/releaseProcedure.json', 
				    fitColumns:true,//自适应列宽
				    striped: true,//显示条纹
					rownumbers: true,
					singleSelect: true,
				      columns:[[  
				        {field:'name',title:'流程名称',width:400,align:'left'},   
				        {field:'key',title:'流程key',width:100,align:'center'},
				        {field:'id',title:'流程编号',width:100,align:'center'},
				        {field:'deploymentId',title:'流程发布编号',width:100,align:'center'},
				        {field:'version',title:'版本',width:100,align:'center'},
				        {field:'nextActions',title:'操作',width:100,align:'center'}
				      
				    ]]
				}); 
			
		
		});
	function deploySet(obj, deploymentId) {
		$(obj).colorbox( {
			iframe : true,
			width : "600",
			height : "500",
			href : '${ctx}/wf/process/deploySet/' + deploymentId
		});
	}
	function delProcess(deploymentId) {
		if (confirm('确定要删除吗？')) {
			$.post("${ctx}/wf/process/pdDel/" + deploymentId, {
				deploymentId : deploymentId
			}, function(json) {
				alert(json.msg);
				if (json.state) {
					location.reload();
				}
			}, 'json');
		}
	}
	function delProcessIns(piId) {
		if (confirm('确定要删除吗？')) {
			$.post("${ctx}/wf/process/piDel", {
				piId : piId
			}, function(json) {
				alert(json.msg);
				if (json.state) {
					location.reload();
				}
			}, 'json');
		}
	}
	function deployProcess(key) {
		$.post("${ctx}/wf/process/pdDeploy/" + key, {
			key : key
		}, function(json) {
			alert(json.msg);
			if (json.state) {
				location.reload();
			}
		}, 'json');
	}
</script>