<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
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
		<div class="h1_1" id="divTitle">环评信息列表</div>
		<div class="divContainer" id="infectlist">
			<table id="data" fit="true"></table>
		</div>
	</body>
</html>

<script language="JavaScript">
$(document).ready(function(){
	
	$("#infectlist").height($(window).height() -  $("#divTitle").outerHeight());
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		fitColumns:true,
		nowrap: false,
		url:'hpxxList.json?pid=${pid}',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'name',title:'环评项目名称', align:'center', align:"left", halign:'center',width:100},
			{field:'hpspwh',title:'环评审批文号', align:"left", halign:'center',width:50},
			{field:'spsj',title:'审批时间', align:"center", halign:'center',width:50},
			{field:'operate',title:'操作', align:"center", halign:'center',width:30,
				formatter:function(value,rowData,rowIndex){  
		    		return '<a class="b-link" onclick="info(\''+ rowData.id +'\')">查看</a>';  
		        }  
			}
		]]
	});
});

function info(id){
	//All.ShowModalWin('hpxxInfo.htm?id='+id, '', 1000, 800);
	parent.parent.layer.open({
        type: 2,
        title: '查看环评 信息',
        area: ['1100px', ($(window).height()+100)+'px'],
        content: 'hpxxInfo.htm?id='+id, 
        end: function () {
            //location.reload();
        	refresh();
        }

    });
}
/*
function del(obj){
	$.messager.confirm('环评信息管理', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
			  url: "delHpxx.json?id="+obj.id,
			  success:function(data){
				 alert(data.msg);
				 $('#data').datagrid('reload');
			  }
			});
		}
	});
}*/
//删除
function del(curObj) {
	var index=layer.confirm('确定删除吗？', {
     	icon: 3, 
        title:'环评信息管理'
     }, function(index){
    	 $.ajax( {
				url : "delHpxx.json?id="+obj.id,
				success : function(data) {
					//refresh();
					 alert(data.msg);
					 $('#data').datagrid('reload');
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}

</script>