<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html>
	<head>
	<script type="text/javascript" src="${app}/js.js"></script>
	<script type="text/javascript" src="${app}/data.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<!-- colorbox -->
	<script type="text/javascript"	src="${colorbox}/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css"/>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <!--poshytip-->
    <link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
    <!--派发列表-->
    <link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${poshytip}/jquery.poshytip.min.js"></script>
    <script type="text/javascript" src="${common}/All.js"></script>
    <!--时间插件 my97-->
    <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
    <link href="${app}/common.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<div class="divContainer" id="questionContainer">
			<form id="queryForm" method="post" action="getJcmbRecordList.json">
				<input type="hidden" id="applyId" name="applyId" value="${applyId}" />
				<input type="hidden" id="jcmbId" name="jcmbId" value="${jcmbId}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" />
			</form>
		</div>
		<div id="dbrwlist" class="divContainer">
			<table id="data" fit="true"></table>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
var applyId = $('#applyId').val();
var jcmbId = $('#jcmbId').val();
$('#queryForm').submit(function(){
      $("#queryForm").ajaxSubmit({
     	  success: function(data){
     	      $('#data').datagrid('loadData',data);
     	      initPager();
	      }
	 });
     return false;  
});

$(document).ready(function(){
	$("#dbrwlist").height($(window).height() - $("#questionContainer").outerHeight());
	$('#data').datagrid({
				nowrap : false,
				striped : true,
				collapsible : true,
				singleSelect : true,
				remoteSort:false,
				fitColumns : true,
				pagination : true,
				rownumbers : true,
				url:'getJcmbRecordList.json?applyId='+applyId+'&jcmbId='+jcmbId,
	            columns:[[
	            	{field:'id',hidden:true},
					{field:'taskname',title:'任务名称',width:200},
					{field:'zxrName',title:'执行人',halign:'center',align:'center',width:80},
					{field:'jcsj',title:'检查时间',halign:'center',align:'center',width:220},
					{field:'gdsj',title:'归档时间',align:'center',width:100},
					{field:'operate',title:'操作', align:"center", halign:'center',width:80,
				    	formatter:function(value,rowData,rowIndex){  
				    		return '<a style="color: #0088CC;" onclick="info(\''+ rowData.id +'\')">查看</a> '+ "  " +'<a style="color: #0088CC;" onclick="select(\''+ rowData.id +', '+ rowData.taskname +'\')">选择</a>'; 
				    	}  
		    		}
				]],
				onDblClickRow: function(rowIndex, rowData){
					select(rowData.id+","+rowData.taskname);
				}
	});
    initPager();
});
//查看
function info(curObj){
	var href = 'taskDetail.htm?applyId='+curObj; 
	var width=screen.width * 0.9-100;
  	var height=screen.height * 0.8-100;
  	parent.parent.layerIframe(2,href,"任务详情",width,height);
    // All.ShowModalWin('taskDetail.htm?applyId='+curObj, '任务详情');
}
//选择
function select(data){
	var arr = new Array();
	arr = data.split(",");
	var index=layer.confirm('确定要选择任务“'+arr[1]+' ”的检查单记录吗（加载历史检查记录会覆盖当前数据）？', {
     	icon: 3, 
        title:'选择检查模板历史记录'
     }, function(index){
    	parent.getHistoryRecord(jcmbId,arr[0]);
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
	
}
</SCRIPT>