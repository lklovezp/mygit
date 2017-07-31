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
		<div class="h1_1" id="divTitle">建设项目</div>
		<div class="divContainer" id="questionContainer">
			<form id="queryForm" action="queryJsxmList.json?isActive=Y" method="post">
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" />
			</form>
		</div>
		<div class="divContainer" id="rbblist">
			<table id="data" fit="true"></table>
		</div>
		<div style="margin-top:20px; text-align:center" id="fanhui"> 
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
    $('#data').datagrid("resize");
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
	$('#hylx').combobox({
		url:'jsxmHylxList.json',
		valueField:'id',
		textField:'name'
	});
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
		pagination:true,
		fitColumns:true,
		nowrap:false,
		url:'queryJsxmList.json?isActive=Y&regionId=${regionid}',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'name',title:'建设项目名称', align:"left", halign:'center',width:100},
			{field:'regionName',title:'所属行政区', align:"center", halign:'center',width:40},
			{field:'industryName',title:'行业类型', align:"center", halign:'center',width:30},
			{field:'operate',title:'操作', align:"center", halign:'center',width:20,
				formatter:function(value,rowData,rowIndex){  
		    		return '<a class="b-link" onclick="info(\''+ rowData.id +'\')">查看</a>';  
		        }	
			}
		]],
		onDblClickRow: function(rowIndex, rowData){
			All.ShowModalWin('jsxmInfo.htm?id='+rowData.id, '查看建设项目信息');
			
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
	All.ShowModalWin('jsxmEdit.htm', '新建工业污染源');
	$('#queryForm').submit();
});

function edit(obj){
	All.ShowModalWin('jsxmEdit.htm?id='+$(obj).attr("id"), '编辑建设项目', 1000, 800);
	$('#queryForm').submit();
}

function del(obj){
	$.messager.confirm('建设项目管理', '确定要删除吗？', function(r){
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
	All.ShowModalWin('../../comman/import.html', '工业污染源导入');
	$('#queryForm').submit();
});

function info(id){
	//All.ShowModalWin('jsxmInfo.htm?id='+id, '查看建设项目信息');
	var width=screen.width*0.8;
	var height=screen.height*0.8-50;
	var title='查看建设项目信息';
	parent.layerIframe(2,'jsxmInfo.htm?id='+id,title,width,height);
}

/**
 * 选择执法对象
 */
 $("#chosee").click(function(){
	var hylx = $("#hylx").combobox('getValue');
	if(hylx == ''){
		alert("请先选择行业类型！");
	}else{
		$("#chosee").colorbox({iframe:true,width:"600", height:"600",href:"choseeLawobj.htm?type=list&lawobjtype="+hylx});
	}
 });

/**
 * 选择后回填数据
 */
function setValues(id,dwmc){
	$("#lawobjId").val(id);
	$("#lawobjName").val(dwmc);
	jQuery().colorbox.close();
}

//转污染源
function transGywry(obj){
	$.messager.confirm('操作确认', '确定要转污染源吗？', function(r){
		if (r){
			All.ShowModalWin('gywryEdit.htm?jsxmid='+obj.id, '新建工业污染源');
			$('#queryForm').submit();
		}
	});
}
//转医院
function transYy(obj){
	$.messager.confirm('操作确认', '确定要转医院吗？', function(r){
		if (r){
			All.ShowModalWin('yyxxEdit.htm?jsxmid='+obj.id, '新建医院');
			$('#queryForm').submit();
		}
	});
}
//转三产
function transSc(obj){
	$.messager.confirm('操作确认', '确定要转三产吗？', function(r){
		if (r){
			All.ShowModalWin('scxxEdit.htm?jsxmid='+obj.id, '新建三产');
			$('#queryForm').submit();
		}
	});
}
//转畜禽养殖
function transXqyz(obj){
	$.messager.confirm('操作确认', '确定要转畜禽养殖吗？', function(r){
		if (r){
			All.ShowModalWin('xqyzEdit.htm?jsxmid='+obj.id, '新建畜禽养殖');
			$('#queryForm').submit();
		}
	});
}
//转服务业
function transXqyz(obj){
	$.messager.confirm('操作确认', '确定要转服务业吗？', function(r){
		if (r){
			All.ShowModalWin('fwyEdit.htm?jsxmid='+obj.id, '新建服务业');
			$('#queryForm').submit();
		}
	});
}
//转饮食业
function transXqyz(obj){
	$.messager.confirm('操作确认', '确定要转饮食业吗？', function(r){
		if (r){
			All.ShowModalWin('ysyEdit.htm?jsxmid='+obj.id, '新建饮食业');
			$('#queryForm').submit();
		}
	});
}
//转制造业
function transXqyz(obj){
	$.messager.confirm('操作确认', '确定要转制造业吗？', function(r){
		if (r){
			All.ShowModalWin('zzyEdit.htm?jsxmid='+obj.id, '新建制造业');
			$('#queryForm').submit();
		}
	});
}
//转娱乐业
function transXqyz(obj){
	$.messager.confirm('操作确认', '确定要转娱乐业吗？', function(r){
		if (r){
			All.ShowModalWin('ylyEdit.htm?jsxmid='+obj.id, '新建娱乐业');
			$('#queryForm').submit();
		}
	});
}
</script>