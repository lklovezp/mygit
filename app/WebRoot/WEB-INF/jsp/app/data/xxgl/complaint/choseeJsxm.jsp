<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="${app}/js.js"></script>
	<script type="text/javascript" src="${app}/data.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${layer}/layer.js"></script>
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
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
    <link href="${app}/common.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<div id="searchMask" class="searchMask"><p>点击查看更多查询条件</p></div>
        <div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;">
			<form id="queryForm" action="queryJsxmList.json" method="post">
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" /> 
			<table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
						<th width="120" align="right">
							建设项目名称：
						</th>
						<td  align="left">
							<input class="y-text" type="text" name="name" id=""/>
						</td>
						<th width="120" align="right">
							所属行政区：
						</th>
						<td>
							<input class="y-text" type="text" id="ssxzq" name="regionId"/>
						</td>
					</tr>
					<tr>
						<th align="right" width="120">
							行业类型：
						</th>
						<td width="" align="left">
							<input class="y-text" type="text" id="hylx" name="industryId"/>
						</td>
						<th width="120" align="right">
						<font color="red"> * </font><a href="#" style="color:#3399CC;" id="selectDwei">单位名称：</a>
						</th>
						<td>
						<input class="y-text" type="text" id="lawobjname" name="lawobjname" value="${lawobjname }"/>
						<input class="y-text" type="hidden" id="lawobjId" name="lawobjId"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
						<input type="submit" id="query"  class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
                        <input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
						</td>
					</tr>
				</table>
			</form>
		 <div class="closeBtn">收起</div>
		  </div>
		<div id="layer1" class="layer"></div>
            <h1 id="divTitle" class="h1_1 topMask">选择建设项目</h1>
		 <div class="divContainer" id="rbblist" style=" width:98%; margin:10px auto 0px;">
			<table id="data" fit="true"></table>
		</div>
		</div>
	</body>
</html>

<script language="JavaScript">
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
////////////////////////////////////////////////////////////////////////////////////
function initH(){
	var hh=$(window).height() - $("#divTitle").outerHeight()-30;
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
		height:34,
		url:'regionTree.json',
		onBeforeSelect:function(node){
			if(node.state){        
				$('#ssxzq').tree('unselect');    
			} 
		}
	});

	/**
	 * 选择执法对象
	 */
	$("#selectDwei").click(function(){
		var hylx = $("#hylx").combobox('getValue');
		if(hylx == ''){
			alert("请先选择行业类型！");
		}else{
			$.colorbox({iframe:true,width:"550", height:"400",href:"choseeLawobj.htm?type=list&industryId="+hylx});
		}
	});
	
	$('#kzlx').combobox({
		height:34,
		url:'kzlxList.json',
		valueField:'id',
		textField:'name'
	});
	
	$('#zt').combobox({
		height:34,
		url:'ztList.json',
		valueField:'id',
		textField:'name'
	});
	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
		    var p = $('#data').datagrid('getPager');
		   $('#page').val($(p).pagination('options').pageNumber);
		   $('#pageSize').val($(p).pagination('options').pageSize);

		   $("#lawobjId-value").val("");
		   $("#lawobjId-text").val("");
		   $("#lawobjId").val("");
	});
	
	$('#hylx').combobox({
		height:34,
		url:'industryList.json?lawobjType=02',
		valueField:'id',
		panelHeight:200,
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
		nowrap: false,
		url:'queryJsxmList.json?isActive=Y&isChoose=Y',
		columns:[[
			{field:'id',hidden:true},
			{field:'address',hidden:true},
			{field:'fddbr',hidden:true},
			{field:'fddbrlxdh',hidden:true},
			{field:'hbfzr',hidden:true},
			{field:'hbfzrlxdh',hidden:true},
			{field:'regionid',hidden:true},
			{field:'name',title:'建设项目名称', align:"left", halign:'center', width:100},
			{field:'regionName',title:'所属行政区', align:"center", halign:'center', width:50},
			{field:'industryName',title:'行业类型', align:"center", halign:'center',width:30},
			{field:'operate',title:'操作', align:"center", halign:'center',width:40,
				formatter:function(value,rowData,rowIndex){  
		    		return '<a class="b-link" onclick="info(\''+ rowData.id +'\')">查看</a> '+ "  " +'<a onclick="value(\''+ rowData.id +', '+ rowData.name +', '+ rowData.address+'\');" class="b-link">选择</a>';;  
		        }  
			}
		]],
		onDblClickRow: function(rowIndex, rowData){
			parent.setValue(rowData.id,rowData.name,rowData.address);
		}
	});
	initPager();
});

function value(data){
	var arr = new Array();
	arr = data.split(",");
	parent.setValue(arr[0],arr[1],arr[2]);
}

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
	//All.ShowModalWin('jsxmInfo.htm?id='+id, '查看工业污染源');
	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	var title='查看工业污染源';
  	parent.parent.layerIframe(2,'jsxmInfo.htm?id='+id,title,width,height);
}

function setValues(id, name){
	//$("#lawobjId").linkbox("setValue", {"id":id, "name":name});
	$("#lawobjname").val(name);
	$("#lawobjId").val(id);
	jQuery.colorbox.close();
}
</script>