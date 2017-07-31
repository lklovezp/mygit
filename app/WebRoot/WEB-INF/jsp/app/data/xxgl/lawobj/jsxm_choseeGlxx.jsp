<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${app}/js.js"></script>
		<script type="text/javascript" src="${app}/data.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<script type="text/javascript" src="${common}/All.js"></script>
	</head>

	<body>
		<div class="headTitle" id="divTitle">选择锅炉信息</div>
		<div class="divContainer" id="questionContainer">
			<form id="queryForm" action="queryGlxxList.json" method="post">
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" /> 
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="formtable">
					<tr>
						<th width="200" align="right">
							单位名称：
						</th>
						<td width="200" align="left">
							<input class="i-text" type="text"  name="name" id=""/>
						</td>
						<th width="140" align="right">
							所属行政区：
						</th>
						<td>
							<input class="i-text" type="text" name="regionid" id="ssxzq"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<span class="btn btn-ok"> <input id="query" type="submit"
									value="查询"> </span>
							&nbsp;
							<a id="J-from-reset" class="btslink">重置</a>&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="divContainer" id="infectlist">
			<table id="data" fit="true"></table>
		</div>
	</body>
</html>

<script language="JavaScript">

$(document).ready(function(){

	$('#ssxzq').combotree({
		url:'regionTree.json',
		onBeforeSelect:function(node){
			if(node.state){        
				$('#ssxzq').tree('unselect');    
			} 
		}
	});
	
	$('#zt').combobox({
		url:'ztList.json',
		valueField:'id',
		textField:'name'
	});
	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
		   $("#zt").combobox('setValue','Y');
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
		pagination:true,
		fitColumns:true,
		url:'queryGlxxList.json?isActive=Y',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'address',hidden:true},
			{field:'fddbr',hidden:true},
			{field:'fddbrlxdh',hidden:true},
			{field:'hbfzr',hidden:true},
			{field:'hbfzrlxdh',hidden:true},
			{field:'name',title:'单位名称', align:"left", halign:'center',width:100},
			{field:'regionName',title:'所属行政区', align:"center", halign:'center',width:50},
			{field:'operate',title:'操作', align:"center", halign:'center',width:30,
				formatter:function(value,rowData,rowIndex){  
		    		return '<a onclick="info(\''+ rowData.id +'\')" class="b-link">查看</a>' + "  " +'<a onclick="select(\''+ rowData.id +', '+ rowData.name +', '+ rowData.address +', '+ rowData.fddbr +', '+ rowData.fddbrlxdh +', '+ rowData.hbfzr +', '+ rowData.hbfzrlxdh +'\')" class="b-link">选择</a>';  
		       }  
			}
		]],
		onDblClickRow: function(rowIndex, rowData){
			<c:if test="${type=='edit'}">
				/*$.messager.confirm('选择锅炉信息', "<table><tr><td>是否覆盖建设项目基础信息（</td></tr><tr><td>单位名称、</td></tr><tr><td>单位地址、</td></tr><tr><td>法定代表人、</td></tr><tr><td>法定代表人联系电话、<tr><td>环保负责人、</td></tr><tr><td>环保负责人联系电话）?</td></tr><div>", function(r){
					if (r){
						parent.setValues(rowData.id,rowData.name,rowData.address,rowData.fddbr,rowData.fddbrlxdh,rowData.hbfzr,rowData.hbfzrlxdh);
					}else{
						parent.setValues(rowData.id,rowData.name);
					}
				});*/
				var index=layer.confirm('是否覆盖建设项目基础信息（<br>单位名称、<br>单位地址、<br>法定代表人、<br>法定代表人联系电话、<br>环保负责人、<br>环保负责人联系电话）?<br>', {
			     	icon: 3, 
			        title:'选择锅炉信息'
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
	$.colorbox({iframe:true,width:"800", height:"600", scrolling:false, href:"glxxInfo.htm?id="+id});
	//All.ShowModalWin('glxxInfo.htm?id='+id, '查看锅炉信息');
}

function select(data){
			var arr = new Array();
			arr = data.split(",");
			<c:if test="${type=='edit'}">
				/*$.messager.confirm('选择锅炉信息', "<table><tr><td>是否覆盖建设项目基础信息（</td></tr><tr><td>单位名称、</td></tr><tr><td>单位地址、</td></tr><tr><td>法定代表人、</td></tr><tr><td>法定代表人联系电话、<tr><td>环保负责人、</td></tr><tr><td>环保负责人联系电话）?</td></tr><div>", function(r){
					if (r){
						parent.setValues(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6]);
					}else{
						parent.setValues(arr[0],arr[1]);
					}
				});*/
				var index=layer.confirm('是否覆盖建设项目基础信息（<br>单位名称、<br>单位地址、<br>法定代表人、<br>法定代表人联系电话、<br>环保负责人、<br>环保负责人联系电话）?<br>', {
			     	icon: 3, 
			        title:'选择锅炉信息'
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
				parent.setValues(arr[0],arr[1]);
			</c:if>
		}
</script>