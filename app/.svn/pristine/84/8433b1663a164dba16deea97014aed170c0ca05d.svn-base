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
		<div class="h1_1" id="divTitle">执法历史记录</div>
		<div class="divContainer" id="infectlist">
			<table id="data" fit="true"></table>
		</div>
	</body>
</html>	
		<script type="text/javascript">
		$(document).ready(function(){
				$("#infectlist").width($(window).width());
				$("#infectlist").height($(window).height() -  $("#divTitle").outerHeight());
				
				$('#data').datagrid({
					rownumbers: true,
					nowrap:false,
					fitColumns : true,
					pagination:true,
					url:'queryLawHistoryList.json?id=${id}',
					onLoadSuccess:function(data){
						$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
					},
					columns:[[
						{field:'id',hidden:true},
						{field:'workname',title:'任务名称', align:'left',halign:'center',width:100},
						{field:'taskname',title:'任务类型',align:'center', halign:'center',width:30},
						{field:'gddw',title:'归档单位', align:'center',halign:'center',width:40},
						{field:'gdry',title:'归档人员', align:'center',halign:'center',width:30},
						{field:'gdsj',title:'归档时间', align:'center',halign:'center',width:30},
						{field:'operate',title:'操作', align:'center',halign:'center',width:20,
							formatter:function(value,rowData,rowIndex){  
					    		return '<a class="b-link" onclick="info(\''+ rowData.id +'\')">查看</a>';  
					        }	
						}
					]]
				});
		});
		
		//查看
		function info(id){
		      //All.ShowModalWin('taskDetail.htm?applyId='+id, '任务详情');
			 //var width=screen.width * 0.8;
		  	 //var height=screen.height * 0.6-30;
		    //var title='执法历史记录详情查看';
		  	//parent.parent.layerIframe(2,'taskDetail.htm?applyId='+id,title,width,height);
			parent.parent.parent.layer.open({
	            type: 2,
	            title: '执法历史记录详情查看',
	            area: ['1100px', ($(window).height()+120)+'px'],
	            content: 'taskDetail.htm?applyId='+id 
	        });
		}
		</script>