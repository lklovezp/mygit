<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>任务派发—列表</title>
    <script type="text/javascript" src="${app}/js.js"></script>
	<script type="text/javascript" src="${app}/data.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
    <link href="${app}/common.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
  
    <script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
    <link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css"/>
    <!--poshytip-->
    <link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
    <!--派发列表-->
    <link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${poshytip}/jquery.poshytip.min.js"></script>
    <script type="text/javascript" src="${common}/All.js"></script>
    <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
</head>

	<body>
		<div id="searchMask" class="searchMask"><p>点击查看更多查询条件</p></div>
<div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;">
   <form id="queryForm" action="xfdjQuery.json" method="post">
				<input type="hidden" id="page" name="page" value="${page}" />
					<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" /> 
					<input type="hidden" name="isActive" value="Y" /> 
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="formtable">
					<tr style="height:70px;">
						<th width="120">
							信访来源
						</th>
						<td>
						<input class="y-text" type="text" id="xfly" name="xfly"	value="${xfly}" style="width: 150px;"/>
						</td>
						<th width="120">
							信访时间
						</th>
						<td colspan="3">
						 <input type="text" class="y-dateTime" id="starttime" name="xfsj1" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\',{d:-1});}'})"/> 
                                                                 至 <input type="text" class="y-dateTime"  id="endtime" name="xfsj2" onclick="WdatePicker({minDate:'#F{$dp.$D(\'starttime\',{d:1});}'})"/>
						</td>
						
					</tr>
					<tr>
						<td colspan="6" align="center">
							<input id="query"  type="submit" class="queryBlue" value="查询"> 
							<input  id="J-from-reset" type="reset" class="queryOrange" value="重　置"/>
						</td>
					</tr>
				</table>
			</form>
   <div class="closeBtn">收起</div>
</div>
<div id="layer" class="layer"></div>
		<div class="h1_1 topMask" id="divTitle">选择信访登记表</div>
		<div class="divContainer" id="infectlist" style="width:100%; margin:0px auto 0px;">
			<table id="data" fit="true"></table>
		</div>
	</body>
</html>

<script language="JavaScript">
var searchMask=$("#searchMask");
var searchForm=$("#searchForm");
var layer=$("#layer");
layer.hide();
searchForm.hide();
var hideSearchBtn=searchForm.find(".closeBtn");
function hideSearchForm(){
	searchForm.hide();
	layer.hide();
}
function showSearchForm(){
	searchForm.show();
	layer.show();
}
searchMask.bind("click",showSearchForm);
hideSearchBtn.bind("click",hideSearchForm);
$(document).ready(function(){

	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
	});
	//任务来源下拉框
	$('#xfly').combobox({
		height:34,
		url:'dicList.json?type=20',
		valueField:'id',
		textField:'name'
	});
	$("#infectlist").height($(window).height() -$("#divTitle").outerHeight());
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: false,
		pagination:true,
		fitColumns:true,
		nowrap: true,
		url:'xfdjQuery.json?isActive=Y',
		
		columns:[[
			{field:'id',hidden:true},
			{field : 'xfbh', title : '信访编号', align : 'center', width : 50},
			{field : 'xfly', title : '信访来源', align : 'center', width : 50},
			{field : 'xfnr', title : '信访内容', width : 100}, 
			{field : 'wrlx', title : '污染类型', width : 100}, 
			{field : 'xfsj', title : '信访时间', align : 'center', width : 50},
			{field : 'zt', title : '状态', align : 'center', width : 30},
			{field:'operate',title:'操作', align:"center", halign:'center',width:30,
				formatter:function(value,rowData,rowIndex){  
		    		return '<a style="color: #0088CC;" onclick="info(\''+ rowData.id +'\')">查看</a>' + "  " +'<a style="color: #0088CC;" onclick="select(\''+ rowData.id +', '+ rowData.xfbh +'\')">选择</a>';  
		       }  
			}
		]],
		onDblClickRow: function(rowIndex, rowData){
			<c:if test="${type=='edit'}">
				parent.setValues(rowData.id,rowData.xfbh);
			</c:if>
			<c:if test="${type=='list'}">
				parent.setValues(rowData.id,rowData.xfbh);
			</c:if>
		}
		
	});
	initPager();
});


$('#queryForm').submit(function(){  
    $("#queryForm").ajaxSubmit({
   	  success: function(data){
   	      intiTips();
   	      $('#data').datagrid('loadData',data);
	          initPager();
	          hideSearchForm();
	      }
	 });
   return false;  
});

//查看
function info(id){
	var href="xfdjInfo.htm?id="+id;
	parent.parent.layerIframe(2,href,"查看信访登记信息","1000","500");
	//$.colorbox({iframe:true,width:"780", height:"620", scrolling:false, href:"xfdjInfo.htm?id="+id});
}

function select(data){
			var arr = new Array();
			arr = data.split(",");
			<c:if test="${type=='edit'}">
				parent.setValues(arr[0],arr[1]);
			</c:if>
			<c:if test="${type=='list'}">
				parent.setValues(arr[0],arr[1]);
			</c:if>
}
//高度自适应
$(window).resize(function(){
    $('#data').datagrid("resize");
});
</script>