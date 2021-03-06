<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<title>${title }</title>
<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<!--jQuery-->
<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${common}/All.js"></script>
<style type="text/css">
.panel-header{background-color: #cff1ff;}
.formtable, .formtable th, .formtable td{border-color:#dddddd;}
</style>
	</head>

	<body>
		<div class="h1_1" id="divTitle">娱乐业信息</div>
		<div class="divContainer" id="questionContainer">
			<form id="queryForm" action="queryYlyList.json?isActive=Y" method="post">
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" /> 
			</form>
		</div>
		<div class="divContainer" id="rbblist">
			<table id="data" fit="true"></table>
		</div>
		<div id="fanhui" style="margin-top:20px; text-align:center"> 
	    <input  type="button" class="queryBlue" value="返回" onclick="fanhui()">
	</div>
	</body>
</html>

<script language="JavaScript">
function fanhui(){
	window.location.href="tjpwdwslList.htm?title=统计排污单位数";
}
function initH(){
	var hh=$(window).height()-$("#fanhui").outerHeight()-$("#divTitle").outerHeight()-20;
	$("#rbblist").height(hh);
}
initH();

//数据表格自使用宽度
$(window).resize(function(){
    $("#data").datagrid("resize");
	initH();
});
$(document).ready(function(){

	$('#ssxzq').combotree({
		url:'regionTree.json',
		onBeforeSelect:function(node){
			if(node.state){        
				$('#ssxzq').tree('unselect');    
			} 
		}
	});
	$('#ssxzq').combotree('setValue','${regionid}');
	
	$('#zt').combobox({
		url:'ztList.json',
		valueField:'id',
		textField:'name'
	});
	
	$("#infectlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		fitColumns:true,
		nowrap:false,
		url:'queryYlyList.json?isActive=Y&regionid=${regionid}',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'name',title:'饮食业名称', align:"left", halign:'center',width:100},
			{field:'regionName',title:'所属行政区', align:"center", halign:'center',width:40},
			{field:'operate',title:'操作', align:"center", halign:'center',width:20,
		    	formatter:function(value,rowData,rowIndex){  
		    		return '<a class="b-link" onclick="info(\''+ rowData.id +'\')">查看</a>';  
		       } 
		    }
		]],
		onDblClickRow: function(rowIndex, rowData){
			//All.ShowModalWin('YsyInfo.htm?id='+rowData.id, '查看饮食业信息');
			var width=screen.width*0.8;
			var height=screen.height*0.8-50;
			var title='查看娱乐业信息';
			parent.layerIframe(2,'ylyInfo.htm?id='+rowData.id,title,width,height);
		}
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
$("#xinjian").click(function(){
	All.ShowModalWin('glxxEdit.htm', '新建锅炉')
	$('#queryForm').submit();
});

function edit(obj){
	All.ShowModalWin('glxxEdit.htm?id='+$(obj).attr("id"), '锅炉信息');
	$('#queryForm').submit();
}

function del(obj){
	$.messager.confirm('锅炉信息管理', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
			  url: "delLawobj.json?id="+obj.id,
			  success:function(data){
				 alert(data.msg);
				 $('#queryForm').submit();
			  }
			});
		}
	});
}

$("#daoru").click(function(){
	All.ShowModalWin('../../comman/import.html', '锅炉导入')
	
});

function info(id){
	//All.ShowModalWin('glxxInfo.htm?id='+id, '查看锅炉信息');
	var width=screen.width*0.8;
	var height=screen.height*0.8-50;
	var title='查看娱乐业信息';
	parent.layerIframe(2,'ylyInfo.htm?id='+id,title,width,height);
}
</script>