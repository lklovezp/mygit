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
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<script type="text/javascript" src="${common}/All.js"></script>
		<link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
        <!-- 任务管理 css-->
        <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
        <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
        <style>
         h3 {
            background: #00a2d9;
            height: 38px;
            color: #fff;
            line-height: 38px;
            text-align: center;
           }
           
        </style>
	</head>
	<body>  
		    <input type="hidden" id="page" name="page" value="${page}" />
			<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" /> 
		    <input type="hidden" id="orgId" name="orgId" value="${orgId}" />
		    <input type="hidden" id="code" name="code" value="${code}" />
		    <input type="hidden" id="name" name="name" value="${name}" />
		    <input type="hidden" id="orgName" name="orgName" value="${orgName}" />
		    <input type="hidden" id="shaiXuan" name="shaiXuan" value="${shaiXuan}" />
		    <h3>${name}</h3>
			<div id="divTable" style="width:100%;height:400px; border-bottom:0px solid #95b8e7;border-left:0px solid #95b8e7;border-top:none;">
			<table id="data" width="100%" fit="true"></table>
			</div>
			  
		   
	</body>
</html>

<script language="JavaScript">
function initH(){
	var hh=$(window).height()-38;
	$("#divTable").height(hh);
}
initH();

//数据表格自使用宽度
$(window).resize(function(){
	$('#data').datagrid("resize");
	initH();
});
//获取执法对象类型
$('#data').datagrid({
		rownumbers: true,
		singleSelect: false,
		pagination:true,
		fitColumns:true,
		nowrap: false,
		url:'queryAllCompanyByCode.json?code='+$('#code').val()+'&orgId='+$('#orgId').val()+'&shaiXuan='+$('#shaiXuan').val(),
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
			var rows = $('#choseedata').datagrid('getRows');
			for(var i=0;i<rows.length;i++){
				var datarows = data.rows;
				for(var j=0;j<datarows.length;j++){
					if(datarows[j].id==rows[i].lawobjid){
						selectRow(datarows[j].id);
					}
				}
			}
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'ck',checkbox:true},
		    {field:'dwmc',title:'名称', align:"left", halign:'center', width:100},
			]],
			onSelect:function(rowIndex, rowData){
				$(window.parent.$("#if2"))[0].contentWindow.addrow(rowData);
			},
			onUnselect:function(rowIndex, rowData){
				$(window.parent.$("#if2"))[0].contentWindow.delRightRow(rowData.id);
			},
			onCheckAll:function(rows){
				for(var i=0;i<rows.length;i++){
					$(window.parent.$("#if2"))[0].contentWindow.addrow(rows[i]);
				}
			},
			onUncheckAll:function(rows){
				for(var i=0;i<rows.length;i++){
				   $(window.parent.$("#if2"))[0].contentWindow.delRightRow(rows[i].id);
				}
			}
			
	});

function initPager(){
    var p = $('#data').datagrid('getPager');
	$(p).pagination({
				onSelectPage:function(pageNumber, pageSize){
				    $('#page').val(pageNumber);
				    $('#pageSize').val(pageSize);
					$(this).pagination('loading');
					
					$(this).pagination('loaded');
				}
	});
}
//确认返回数据
var orgId =$('#orgId').val();
	function save(){
	  if(orgId==""){
		  alert("请选择监管部门");
		  return;
	  }
	   var rows = $('#data').datagrid('getChecked');
	  	if(rows.length==0){
			alert("请至少选择一个执法对象！");
			return;
		}
		var rowString = '[';
		for(var i=0;i<rows.length;i++){
			rowString += "{'lawObjId':'"+rows[i].id+"'";
			
			rowString += ",'name':'"+rows[i].name+"'}";
			if(i!=rows.length-1){
				rowString += ",";
			}
		}
		rowString += ']';
		var tishi='确认要将所选择的执法对象调整到'+$('#orgName').val()+'下吗？';
		/*$.messager.confirm('调整执法对象', tishi, function(r){
			if(r){
		$.ajax({
			  type: "POST",
			  url: "saveChoseeCompany.json",
			  data: "rows="+rowString+"&orgId="+orgId+"&code="+$('#code').val(),
			  success:function(data){
				if(data.state){				
					alert(data.msg);
					parent.shuaXin(orgId,$('#code').val(),$('#orgName').val(),$('#name').val());
					$('#data').datagrid('reload');
					
				}else{
				 	alert("修改错误！");
				}
			  }
			}); 
			}
		});*/
		
		var index=layer.confirm(tishi, {
	     	icon: 3, 
	        title:'调整执法对象'
	     }, function(index){
	    	 $.ajax({
				  type: "POST",
				  url: "saveChoseeCompany.json",
				  data: "rows="+rowString+"&orgId="+orgId+"&code="+$('#code').val(),
				  success:function(data){
					if(data.state){				
						alert(data.msg);
						parent.shuaXin(orgId,$('#code').val(),$('#orgName').val(),$('#name').val());
						$('#data').datagrid('reload');
						
					}else{
					 	alert("修改错误！");
					}
				  }
				}); 
	        layer.close(index);
	     },function(index){
	        //alert('取消按钮的回调函数');
	        layer.close(index);
	     });
	};
	
	function pressed(){ 
		if($('#code').val()==""){
			alert("请选择执法对象类型！");
			return;
		} 
		window.location.href="queryAllCompany.htm?orgId="+orgId+"&code="+$('#code').val()+"&shaiXuan='shaiXuan'&name="+$('#name').val()+"&orgName="+$('#orgName').val();
	} 
	
	//从右边删除
	function delrow(id){
		delRightRow(id);
		unSelectRow(id);
	}
	 
	 
	//删除右边行数据
	function delRightRow(id){
		//删除行`
		var rows = $('#choseedata').datagrid('getRows');
		for(var i=0;i<rows.length;i++){
			if(rows[i].lawobjfid==id){
				$('#choseedata').datagrid("deleteRow",i);
				break;
			}
		}
	}
	 
	//取消左边选中状态
	function unSelectRow(id){
		var dataRows = $('#data').datagrid('getSelections');
		for(j=0;j<dataRows.length;j++){
			if(dataRows[j].id==id){
				var index = $('#data').datagrid('getRowIndex',dataRows[j]);
				if(index>=0){
					$('#data').datagrid('unselectRow',index);
				}
				return;
			}
		}
	}
	 
	//左边选中状态
	function selectRow(id){
		var dataRows = $('#data').datagrid('getRows');
		for(j=0;j<dataRows.length;j++){
			if(dataRows[j].id==id){
				var index = $('#data').datagrid('getRowIndex',dataRows[j]);
				if(index>=0){
					$('#data').datagrid('selectRow',index);
				}
				return;
			}
		}
	}
	
	//添加行
	function addrow(rowData){
		var rows = $('#choseedata').datagrid('getRows');
		var isCZ = false;//是否存在
		for(var i=0;i<rows.length;i++){
			if(rowData.id==rows[i].lawobjid){
				isCZ = true;
			}
		}
		if(!isCZ){
			$('#choseedata').datagrid("appendRow",{lawobjfid:rowData.id,name:rowData.dwmc,fddbr:rowData.fddbr,fddbrlxdh:rowData.fddbrdh,hbfzr:rowData.hbfzr,hbfzrlxdh:rowData.hbfzrdh,regionid:rowData.regionid,address:rowData.dwdz});
		}
	}

</script>