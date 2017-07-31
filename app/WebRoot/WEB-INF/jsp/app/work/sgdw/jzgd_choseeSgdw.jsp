<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html>
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
    <script type="text/javascript" src="${layer}/layer.js"></script>
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
				<form id="queryForm" action="builderQuery.json" method="post">
					<input type="hidden" id="page" name="page" value="${page}" />
					<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" /> 
					<input type="hidden" name="isActive" value="Y" /> 
				  <table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
						<th width="120" align="right">
							施工单位名称：
						</th>
						<td align="left">
							<input class="y-text" style="width:280px;" type="text" name="name" id=""/>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						<input type="submit" id="query"  class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
                        <input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
						</td>
					</tr>
				</table>
				</form>
			<div class="closeBtn">收起</div>
		  </div>
		<div id="layer1" class="layer"></div>
            <h1 id="divTitle" class="h1_1 topMask">选择施工单位</h1>
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

	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
	});
	$("#infectlist").height($("#main").outerHeight() - $("#questionContainer").outerHeight() -2);
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: false,
		pagination:true,
		fitColumns:true,
		nowrap: false,
		url:'builderQuery.json?isActive=Y',
		columns:[[
			{field:'id',hidden:true},
			{field:'fddbr',hidden:true},
			{field:'fddbrlxdh',hidden:true},
			{field:'hbfzr',hidden:true},
			{field:'hbfzrlxdh',hidden:true},
			{field:'name',title:'施工单位名称', align:"left", halign:'center',width:100},
			{field:'adress',title:'施工单位地址', align:"left", halign:'center',width:100},
			{field:'operate',title:'操作', align:"center", halign:'center',width:30,
				formatter:function(value,rowData,rowIndex){  
		    		return '<a onclick="info(\''+ rowData.id +'\')" class="b-link">查看</a>' + "  " +'<a class="b-link" onclick="select(\''+ rowData.id +', '+ rowData.name +', '+ rowData.adress +', '+ rowData.fddbr +', '+ rowData.fddbrlxdh +', '+ rowData.hbfzr +', '+ rowData.hbfzrlxdh +'\')">选择</a>';  
		       }  
			}
		]],
		onDblClickRow: function(rowIndex, rowData){
			<c:if test="${type=='edit'}">
				/*$.messager.confirm('选择施工单位信息', "<table><tr><td>是否覆盖施工单位基础信息（</td></tr><tr><td>施工单位名称、</td></tr><tr><td>施工单位地址、</td></tr><tr><td>法定代表人、</td></tr><tr><td>法定代表人联系电话、<tr><td>环保负责人、</td></tr><tr><td>环保负责人联系电话）?</td></tr><div>", function(r){
					if (r){
						parent.setValues(rowData.id,rowData.name,rowData.adress,rowData.fddbr,rowData.fddbrlxdh,rowData.hbfzr,rowData.hbfzrlxdh);
					}else{
						parent.setValues(rowData.id,rowData.name);
					}
				});*/
				var index=layer.confirm('是否覆盖施工单位基础信息（<br>施工单位名称、<br>施工单位地址、<br>法定代表人、<br>法定代表人联系电话、<br>环保负责人、<br>环保负责人联系电话）?<br>', {
			     	icon: 3, 
			        title:'选择施工单位信息'
			     }, function(index){
			    	 parent.setValues(rowData.id,rowData.name,rowData.address,rowData.fddbr,rowData.fddbrlxdh,rowData.hbfzr,rowData.hbfzrlxdh);
			        layer.close(index);
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
   	      $('#data').datagrid('loadData',data);
	          initPager();
	      }
	 });
   return false;  
});

//查看
function info(id){
	$.colorbox({iframe:true,width:"600", height:"520", scrolling:false, href:"builderInfo.htm?id="+id});
}

function select(data){
			var arr = new Array();
			arr = data.split(",");
			<c:if test="${type=='edit'}">
				/*$.messager.confirm('选择施工单位信息', "<table><tr><td>是否覆盖建设项目基础信息（</td></tr><tr><td>单位名称、</td></tr><tr><td>单位地址、</td></tr><tr><td>法定代表人、</td></tr><tr><td>法定代表人联系电话、<tr><td>环保负责人、</td></tr><tr><td>环保负责人联系电话）?</td></tr><div>", function(r){
					if (r){
						parent.setValues(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6]);
					}else{
						parent.setValues(arr[0],arr[1]);
					}
				});*/
				var index=layer.confirm('是否覆盖施工单位基础信息（<br>施工单位名称、<br>施工单位地址、<br>法定代表人、<br>法定代表人联系电话、<br>环保负责人、<br>环保负责人联系电话）?<br>', {
			     	icon: 3, 
			        title:'选择施工单位信息'
			     }, function(index){
			    	 parent.setValues(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6]);
			        layer.close(index);
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