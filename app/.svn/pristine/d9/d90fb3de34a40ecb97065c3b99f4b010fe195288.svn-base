<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<script type="text/javascript" src="${app}/js.js"></script>
		<script type="text/javascript" src="${app}/data.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<script type="text/javascript" src="${common}/All.js"></script>
		<link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
        <!-- 任务管理 css-->
        <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
        <style>
         h3 {
            background: #00a2d9;
            height: 38px;
            color: #fff;
            line-height: 38px;
            text-align: center;
           }
           
        </style>
<body>		
		
	        <input type="hidden" id="orgId" name="orgId" value="${orgId}" />
		    <input type="hidden" id="code" name="code" value="${code}" />
		    <h3>${headTitle}</h3>
			<div id="divTable" style="width:100%;height:400px; border-bottom:0px solid #95b8e7;border-left:0px solid #95b8e7;border-top:none;">
			<table id="data" width="100%" fit="true"></table>
			</div>
			
 </body>
<script language="JavaScript">
function initH(){
	var hh=$(window).height() -38;
	$("#divTable").height(hh);
	
}
initH();

//数据表格自使用宽度
$(window).resize(function(){
	$('#data').datagrid("resize");
	initH();
});

/** */
$(document).ready(function(){
    
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		fitColumns:true,
		nowrap: false,
		url:'queryCompanyByCodeAndOrgId.json?isActive=Y&qyzt=0&orgId='+$('#orgId').val()+'&code='+$('#code').val(),
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'dwmc',title:'名称', align:"left", halign:'center',width:100},
			
			{field:'operate',title:'操作', align:"center", halign:'center',width:20,
				formatter:function(value,rowData,rowIndex){  
		    		return '<a onclick="del(\''+rowData.id+ '\')" class="b-link">删除</a>';  
		       } 	
			}
		]]
		
		
	});
	//initPager();
});

//添加行
function addrow(rowData){
	var rows = $('#data').datagrid('getRows');
	var isCZ = false;//是否存在
	for(var i=0;i<rows.length;i++){
		if(rowData.id==rows[i].id){
			isCZ = true;
		}
	}
	if(!isCZ){
		$('#data').datagrid("appendRow",{id:rowData.id,dwmc:rowData.dwmc});
	}
}
//删除右边行数据
function delRightRow(id){
	//删除行`
	var rows = $('#data').datagrid('getRows');
	for(var i=0;i<rows.length;i++){
		if(rows[i].id==id){
			$('#data').datagrid("deleteRow",i);
			break;
		}
	}
}
/**
function initPager(){
    var p = $('#data').datagrid('getPager');
	$(p).pagination({
				onSelectPage:function(pageNumber, pageSize){
				    $('#page').val(pageNumber);
				    $('#pageSize').val(pageSize);
					$(this).pagination('loading');
								
				}
	});
}
*/
/*
function del(obj){
	$.messager.confirm("数据", '确定要删除吗？', function(r){

		if (r){
			
			$.ajax({
			  async:false,
			  type:'POST',
			  url: "updateOrgToNull.json?companyId="+obj.id+"&code="+$('#code').val(),
			  success:function(data){
				 alert(data.msg);
				$('#data').datagrid('reload');
			  }
			});
		}
		
	});
}*/
//删除
function del(obj) {
	var arr=new Array();
	    arr=obj.split(",");
	var index=layer.confirm('确定删除吗？', {
     	icon: 3, 
        title:'删除任务'
     }, function(index){
    	 $.ajax( {
				url : "updateOrgToNull.json?companyId="+arr[0]+"&code="+$('#code').val(),
				success : function(data) {
					refresh();
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}


</script>