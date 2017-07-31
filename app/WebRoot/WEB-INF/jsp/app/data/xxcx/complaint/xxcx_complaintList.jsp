<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${common}/All.js"></script>
<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<!--jQuery-->
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
.panel-header{background-color: #cff1ff;}
.formtable, .formtable th, .formtable td{border-color:#dddddd; }

</style>
	</head>
	<body>
		<div class="h1_1" id="divTitle">投诉信息</div>
		<div class="divContainer" id="questionContainer">
			<form id="queryForm" action="queryComplaintList.json" method="post">
				<input type="hidden" id="lawobjid" name="lawobjid" value="${lawobjid}" />
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" /> 
			</form>
		</div>
		<div class="divContainer" id="infectlist">
			<table id="data" fit="true"></table>
		</div>
	</body>
</html>

<script language="JavaScript">
$(document).ready(function(){

	$('#zt').combobox({
		url:'ztList.json',
		valueField:'id',
		textField:'name'
	});
	$("#J-from-reset").click(function(){
	   $("#queryForm").form("reset");
	   var p = $('#data').datagrid('getPager');
	   $('#page').val($(p).pagination('options').pageNumber);
	   $('#pageSize').val($(p).pagination('options').pageSize);
	});
	
	$("#infectlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		nowrap:false,
		fitColumns : true,
		pagination:true,
		url:'queryComplaintList.json?isActive=Y',
		queryParams:{lawobjid:'${lawobjid}'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'tssj',title:'投诉时间', align:"center", halign:'center',width:30},
			{field:'tsyy',title:'投诉原因', align:"left", halign:'center',width:100},
			{field:'tsr',title:'投诉人', align:"center", halign:'center',width:30},
			{field:'operate',title:'操作', align:"center", halign:'center',width:30,
				formatter:function(value,rowData,rowIndex){  
		    		return '<a class="b-link" onclick="info(\''+ rowData.id +'\')">查看</a> ';  
		       }  
			}
		]]
	});
	initPager();
	
});

function initPager(){
    var p = $('#data').datagrid('getPager');
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
  	      intiTips();
  	      $('#data').datagrid('loadData',data)
          initPager();
      }
 	});
   return false;  
});

function info(id){
	//All.ShowModalWin('complaintInfo.htm?id='+id, '查看投诉信息');
	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	var title='查看投诉信息';
  	parent.parent.parent.layerIframe(2,'complaintInfo.htm?id='+id,title,width,height);
	
}
</script>