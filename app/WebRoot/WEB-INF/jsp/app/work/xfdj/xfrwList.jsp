<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>信访任务办理情况</title>
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
<div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;">
   <form id="queryForm" method="post" action="getDbrwList.json">
      <input type="hidden" id="fid" name="fid" value="${fid}" />
      <input type="hidden" id="xfdjId" name="xfdjId" value="${xfdjId}" />
	  <input type="hidden" id="page" name="page" value="${page}" />
	  <input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
   <table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
   </table>
   </form>
   <div class="closeBtn">收起</div>
</div>
<div id="layer1" class="layer"></div>
<h1 id="title" class="h1_1 topMask">信访任务办理情况</h1>
<div class="divContainer" id="rbblist" style=" width:100%; margin:0px auto 0px;">
    <table id="data" fit="true" ></table>
</div>


<script type="text/javascript">
var xfdjId = $("#xfdjId").val();
var searchMask=$("#searchMask");
var searchForm=$("#searchForm");
var layer1=$("#layer1");
layer1.hide();
searchForm.hide();
var hideSearchBtn=searchForm.find(".closeBtn");
function hideSearchForm(){
	searchForm.hide();
	layer1.hide();
}
function showSearchForm(){
	searchForm.show();
	layer1.show();
}
searchMask.bind("click",showSearchForm);
hideSearchBtn.bind("click",hideSearchForm);



function initH(){
	var hh=$(window).height() - $("#title").outerHeight();
	$("#rbblist").height(hh);
}
initH();

$(document).ready(function(){
    if(xfdjId == "" || xfdjId == null){
    	//数据表格初始化
	    $('#data').datagrid({
			nowrap : true,
			striped : true,
			collapsible : true,
			singleSelect : true,
			remoteSort:false,
			fitColumns : true,
			pagination : true,
			multiSort:true,
			rownumbers : true,
			url:'getDbrwList.json',
			onLoadSuccess:function(data){
     		        $(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
               },
               rowStyler: function(index,row){
		        if (row.isYQ==1){
		            return 'color:red;';
		        }
		    },
            columns:[[
				{field:'dbworkName1',title:'任务名称',width:240,sortable:true},
				{field:'rwly',title:'任务来源',align:'center',width:100},
				{field:'jjcd',title:'紧急程度',align:'center',width:160},
				{field:'yqwcsx',title:'要求完成时限',align:'center',width:100},
				{field:'createby',title:'派发人',align:'center',width:80},
				{field:'zxrName',title:'主办人',align:'center',width:80},
				{field:'state',title:'任务状态',align:'center',width:80},
				{field:'operate',title:'操作',align:'center',width:260}
			]]
		});
    }else{
    	//数据表格初始化
	    $('#data').datagrid({
			nowrap : true,
			striped : true,
			collapsible : true,
			singleSelect : true,
			remoteSort:false,
			fitColumns : true,
			pagination : true,
			multiSort:true,
			rownumbers : true,
			url:'getDbrwList.json?xfdjId='+xfdjId,
			onLoadSuccess:function(data){
     		        $(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
               },
               rowStyler: function(index,row){
		        if (row.isYQ==1){
		            return 'color:red;';
		        }
		    },
            columns:[[
				{field:'dbworkName1',title:'任务名称',width:240,sortable:true},
				{field:'rwly',title:'任务来源',align:'center',width:100},
				{field:'jjcd',title:'紧急程度',align:'center',width:160},
				{field:'yqwcsx',title:'要求完成时限',align:'center',width:100},
				{field:'createby',title:'派发人',align:'center',width:80},
				{field:'zxrName',title:'主办人',align:'center',width:80},
				{field:'state',title:'任务状态',align:'center',width:80},
				{field:'operate',title:'操作',align:'center',width:260}
			]]
		});
    }
    initPager();
});

function flowChart(piId){
	var href = "${ctx}/wf/process/flowChart?piId="+piId; 
  	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	parent.layerIframe(2,href,"流程图",900,height);

	//window.location.href="${ctx}/wf/process/flowChart?piId="+piId;
   // All.ShowModalWin("${ctx}/wf/process/flowChart?piId="+piId, '流程图',900,500);
}
//任务详情
function info(curObj){
	var href = 'taskDetail.htm?applyId='+curObj.id; 
  	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	parent.layerIframe(2,href,"任务详情",width,height);
    // All.ShowModalWin('taskDetail.htm?applyId='+curObj.id, '任务详情');
    // window.location.href='taskDetail.htm?applyId='+curObj.id;
     //refresh();
}

function actionOper(code,action,actionType,applyId,taskId,text){
	if(actionType=='AJAX'){
			$.post('${ctx}/'+action, {applyId:applyId,taskId:taskId}, function(json){
				$.messager.alert('提示',json.msg,'info',function(){
					if(json.state){
						$('#data').datagrid('reload');
					}
				});
			},'json');
	}else if(actionType == 'NEW_WIN_S'){
			$.colorbox({
				iframe:true,
				width:"50%", 
				height:"90%",
				href:'${ctx}/'+action+'&applyId='+applyId+'&taskId='+taskId
			});
	}else if(actionType == 'NEW_WIN_B'){
		   parent.$.colorbox({
				iframe:true,
				width:"80%", 
				height:"100%",
				href:'${ctx}/'+action+'&applyId='+applyId+'&taskId='+taskId,
				onClosed:function(){
				   $('#queryForm').submit();
				}
			});
	}else if(actionType == 'NEW_WIN_D'){
			//改为弹出对话框窗口
			 window.location.href='${ctx}/'+action+'&applyId='+applyId+'&taskId='+taskId+'&lx=1';
			//All.ShowModalWin('${ctx}/'+action+'&applyId='+applyId+'&taskId='+taskId, text);
			//$('#queryForm').submit();
	}
}

    //数据表格自使用宽度
    $(window).resize(function(){
        $('#data').datagrid("resize");
		initH();
    });
</script>
</body>
</html>
