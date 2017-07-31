<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>选择畜禽养殖</title>
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
</head>

	<body>
		<div id="searchMask" class="searchMask"><p>点击查看更多查询条件</p></div>
<div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;">
                    <form id="queryForm" action="queryXqyzList.json" method="post">
					<input type="hidden" id="lawobjtype" name="lawobjtype" value="${lawobjtype}" />
					<input type="hidden" id="rwid" name="rwid" value="${rwid}" />
					<input type="hidden" id="page" name="page" value="${page}" />
					<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" /> 
					<input type="hidden" name="isActive" value="Y" /> 
					<table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr style="height:70px;">
						<th width="200">
							畜禽养殖名称：
						</th>
						<td>
							<input class="y-text" type="text"  name="name" id="" style="width: 100px;"/>
						</td>
						<th width="140">
							所属行政区：
						</th>
						<td>
							<input class="y-text" type="text" name="regionid" id="ssxzq" style="width: 127px;"/>
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
<div id="layer1" class="layer"></div>
		<div class="h1_1 topMask" id="divTitle">选择畜禽养殖</div>
		<div class="divContainer" id="infectlist" style="width:100%; margin:0px auto 0px;">
			<table id="data" fit="true"></table>
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
	
	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
		    var p = $('#data').datagrid('getPager');
		   $('#page').val($(p).pagination('options').pageNumber);
		   $('#pageSize').val($(p).pagination('options').pageSize);
	});
	$("#infectlist").height($(window).height()-$("#divTitle").outerHeight());
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		fitColumns:true,
		nowrap: false,
		url:'queryXqyzList.json?isActive=Y',
		columns:[[
			{field:'id',hidden:true},
			{field:'address',hidden:true},
			{field:'fddbr',hidden:true},
			{field:'fddbrlxdh',hidden:true},
			{field:'hbfzr',hidden:true},
			{field:'hbfzrlxdh',hidden:true},
			{field:'name',title:'畜禽养殖名称', align:"left", halign:'center',width:100},
			{field:'regionName',title:'所属行政区', align:"center", halign:'center',width:30},
			{field:'operate',title:'操作', align:"center", halign:'center',width:30,
				formatter:function(value,rowData,rowIndex){  
		    		return '<a onclick="info(\''+ rowData.id +'\')" class="b-link">查看</a>' + "  " +'<a onclick="select(\''+ rowData.id +', '+ rowData.name +', '+ rowData.address +', '+ rowData.fddbr +', '+ rowData.fddbrlxdh +', '+ rowData.hbfzr +', '+ rowData.hbfzrlxdh +'\')" class="b-link">选择</a>';  
		       }  
			}
		]],
		onDblClickRow: function(rowIndex, rowData){
			<c:if test="${type=='edit'}">
				/*$.messager.confirm('选择污染源', "<table><tr><td>是否覆盖建设项目基础信息（</td></tr><tr><td>单位名称、</td></tr><tr><td>单位地址、</td></tr><tr><td>法定代表人、</td></tr><tr><td>法定代表人联系电话、<tr><td>环保负责人、</td></tr><tr><td>环保负责人联系电话）?</td></tr><div>", function(r){
					if (r){
						parent.setValues(rowData.id,rowData.name,rowData.address,rowData.fddbr,rowData.fddbrlxdh,rowData.hbfzr,rowData.hbfzrlxdh);
					}else{
						parent.setValues(rowData.id,rowData.name);
					}
				});*/
				var index=layer.confirm('是否覆盖建设项目基础信息（<br>单位名称、<br>单位地址、<br>法定代表人、<br>法定代表人联系电话、<br>环保负责人、<br>环保负责人联系电话）?<br>', {
			     	icon: 3, 
			        title:'选择畜禽养殖'
			     }, function(index){
			    	 parent.setValues(rowData.id,rowData.name,rowData.address,rowData.fddbr,rowData.fddbrlxdh,rowData.hbfzr,rowData.hbfzrlxdh);
			        layer.close(index);
			        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
					parent.layer.close(index);
			     },function(index){
			    	 parent.setValues(rowData.id,rowData.name);
			        layer.close(index);
			     });
			</c:if>
			<c:if test="${type=='list'}">
				parent.setValues(rowData.id,rowData.name);
			</c:if>
		}
	});
	initPager();
});


$('#queryForm').submit(function(){  
    $("#queryForm").ajaxSubmit({
   	  success: function(data){
   	      intiTips();
   	      $('#data').datagrid('loadData',data)
	          initPager();
   	   hideSearchForm();
	      }
	 });
   return false;  
});

function info(id){
	var href="xqyzInfo.htm?id="+id;
	parent.parent.parent.layerIframe(2,href,"查看畜禽养殖信息","1000","575");
	//$.colorbox({iframe:true,width:"800", height:"550", scrolling:true, href:"xqyzInfo.htm?id="+id});
	//All.ShowModalWin('xqyzInfo.htm?id='+id, '查看畜禽养殖信息');
}

function select(data){
			var arr = new Array();
			arr = data.split(",");
			<c:if test="${type=='edit'}">
				/*$.messager.confirm('选择畜禽养殖', "<table><tr><td>是否覆盖建设项目基础信息（</td></tr><tr><td>单位名称、</td></tr><tr><td>单位地址、</td></tr><tr><td>法定代表人、</td></tr><tr><td>法定代表人联系电话、<tr><td>环保负责人、</td></tr><tr><td>环保负责人联系电话）?</td></tr><div>", function(r){
					if (r){
						parent.setValues(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6]);
					}else{
						parent.setValues(arr[0],arr[1]);
					}
				})*/
				var index=layer.confirm('是否覆盖建设项目基础信息（<br>单位名称、<br>单位地址、<br>法定代表人、<br>法定代表人联系电话、<br>环保负责人、<br>环保负责人联系电话）?<br>', {
			     	icon: 3, 
			        title:'选择畜禽养殖'
			     }, function(index){
			    	 parent.setValues(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6]);
			        layer.close(index);
			        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
					parent.layer.close(index);
			     },function(index){
			    	 parent.setValues(arr[0],arr[1]);
			        layer.close(index);
			     });
			</c:if>
			<c:if test="${type=='list'}">
				parent.setValues(arr[0],arr[1]);
			</c:if>
		}
</script>