<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>任务派发—列表</title>
    <script type="text/javascript" src="${app}/js.js"></script>
	<script type="text/javascript" src="${app}/data.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
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
<body style="padding-bottom: 75px;">
<div id="title" style="width: 99%;margin:16px auto 16px;font-size: 18px;" align="center">批量派发</div>
	<div id="main" style="padding:0px;margin:0 auto; width:99%;height:518px; border:1px solid #95b8e7; ">
		<div  style="float:left; width:70%;height:518px; border:0px solid #95b8e7; ">
			<div  id="questionContainer" style="margin:16px auto 0px;">
				<form id="queryForm" action="getRwpfList.json" method="post">
					<input type="hidden" id="page" name="page" value="${page}" />
					<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" /> 
					<input type="hidden" name="isActive" value="Y" /> 
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="formtable">
						<tr>
							<th width="150" >
								任务名称
							</th>
							<td width="100">
							    <input class="y-text" type="text" id="rwmc" name="rwmc" style="width:127px;"/>
							</td>
							<th width="150" >
								任务来源
							</th>
							<td width="100">
                                <input class="y-text" type="text" id="rwly" name="rwly" />
							</td>
							
						</tr>
						<tr style="height:70px; ">
							<td colspan="4" align="center">
								<input id="query" type="submit" class="queryBlue" value="查询"> 
								<input  id="J-from-reset" type="reset" class="queryOrange" value="重　置"/>
							</td>
						</tr>
			
					</table>
				</form>
			</div>
			<div class="divContainer" id="infectlist" style="float:left;width:100%; margin:0px auto 0px;">
				<table id="data" fit="true"></table>
			</div>
		</div>
		<div class="divContainer"  style="float:right;width:29.5%;height:518px; border-bottom:0px solid #cff1ff;border-left:1px solid #cff1ff;border-top:none;">
			<h1 style="height:30px;font-size: 16px;" align="center">您选择的任务</h1>
			<table id="choseedata">
			</table>
		</div>
		
	</div>
	<div class="rb_btn_fix">
	<table style="width:100%;">
			<tr>
			<td align="center" height="50">
				<label class="requiredLabel">*</label><b>接受人：</b>
				<input type="hidden" id="jsrId" name="jsrId" value="${work.jsr}"/>
							<input class="y-text easyui-validatebox" data-options="required:true" type="text" id="jsr" readonly="readonly" value="${work.jsrName}" />
							<a href="#" id="selectjsr" style="color: #00a2d9">选择人员</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="pfbutton" class="queryBlue" type="submit" value="派发"> &nbsp;
					<input id="guanbi" class="queryOrange" type="button" value="关闭" onclick="javascript:guanbi()"> &nbsp;
				</td>
			</tr>
	</table>
	</div>
</body>
</html>
<script type="text/javascript">
//设置选择接受人
function setUserInfoJsr(id,name) {
	$("#jsrId").val(id);
	$("#jsr").val(name);
	jQuery().colorbox.close();
}
$(document).ready(function(){

	//任务来源下拉框
	$('#rwly').combobox( {
		height:34,
		url : 'dicList.json?type=1',
		valueField : 'id',
		textField : 'name'
	});
	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
		    var p = $('#data').datagrid('getPager');
		   $('#page').val($(p).pagination('options').pageNumber);
		   $('#pageSize').val($(p).pagination('options').pageSize);
	});
	//选择接受人
    $("#selectjsr").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?all=false&notShowZj=false&methodname=setUserInfoJsr&multi=false"});
    $("#infectlist").height($("#main").outerHeight() - $("#questionContainer").outerHeight() -20);
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: false,
		pagination:true,
		fitColumns:true,
		nowrap: false,
		url:'getRwpfList.json',
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
		columns:[[{field : 'id',title : 'id',hidden:true}, 
				{field : 'rwmc',title : '任务名称',width : 300}, 
				{field : 'rwly',title : '任务来源',align : 'center',width : 100}, 
				{field : 'jjcd',title : '紧急程度',align : 'center',width : 100}, 
				{field : 'djr',title : '登记人',align : 'center',width : 100}, 
				{field : 'scsj',title : '生成时间',align : 'center',width : 100}
		]],
		onSelect:function(rowIndex, rowData){
			addrow(rowData);
		},
		onUnselect:function(rowIndex, rowData){
			delRightRow(rowData.id);
		},
		onCheckAll:function(rows){
			for(var i=0;i<rows.length;i++){
				addrow(rows[i]);
			}
		},
		onUncheckAll:function(rows){
			for(var i=0;i<rows.length;i++){
				delRightRow(rows[i].id);
			}
		}
	});
	$('#choseedata').datagrid({
		rownumbers: true,
		singleSelect: true,
		fitColumns:true,
		nowrap: false,
		url:'querySelectLawobjList.json',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
			var rows = $('#data').datagrid('getRows');
			for(var i=0;i<rows.length;i++){
				var datarows = data.rows;
				for(var j=0;j<datarows.length;j++){
					if(datarows[j].lawobjid==rows[i].id){
						selectRow(datarows[j].id);
					}
				}
			}
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'rwly',hidden:true},
			{field:'djr',hidden:true},
			{field:'scsj',hidden:true},
			{field:'rwmc',title:'任务名称', align:"left", halign:'center',width:100},
		    {field:'operate',title:'操作', align:"center", halign:'center',width:30,
		    	formatter:function(value,rowData,rowIndex){  
		    		return '<a onclick="delrow(\''+ rowData.id +'\')">删除</a> ';  
		       }  
			}
		]]
	});
	
	//确认返回数据
	$("#pfbutton").click(function(){
		var rows = $('#choseedata').datagrid('getRows');
		if(rows.length==0){
			alert("请至少选择一条任务！");
			return;
		}
		if($("#jsrId").val()==null ||$("#jsrId").val()==""){
			alert("请选择接受人！");
			return;
		}
		var rowString = '[';
		for(var i=0;i<rows.length;i++){
			rowString += "{'id':'"+rows[i].id+"'";
			rowString += ",'rwmc':'"+rows[i].rwmc+"'";
			rowString += ",'rwly':'"+rows[i].rwly+"'";
			rowString += ",'djr':'"+rows[i].djr+"'";
			rowString += ",'scsj':'"+rows[i].scsj+"'}";
			if(i!=rows.length-1){
				rowString += ",";
			}
		}
		rowString += ']';
		$.ajax({
			  type: "POST",
			  url: "plpf.json",
			  data: "rows="+rowString+"&jsrId="+$("#jsrId").val(),
			  success:function(data){
				  if(data.state){
				  		$("#pfbutton").removeAttr("disabled");//将按钮置为不可用，防止重复派发
						alert(data.msg);
						window.location.href="rwpfList.htm?title=任务派发";
				 }else{
				 	alert(data.msg);
				 }
			  }
			});
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
function guanbi(){
	window.location.href="rwpfList.htm?title=任务派发";
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
		$('#choseedata').datagrid("appendRow",{id:rowData.id,rwmc:rowData.rwmc,rwly:rowData.rwly,djr:rowData.djr,scsj:rowData.scsj});
	}
}

//从右边删除
function delrow(id){
	delRightRow(id);
	unSelectRow(id);
}


//删除右边行数据
function delRightRow(id){
	//删除行
	var rows = $('#choseedata').datagrid('getRows');
	for(var i=0;i<rows.length;i++){
		if(rows[i].id==id){
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
//高度自适应
$(window).resize(function(){
    $('#data').datagrid("resize");
    $('#choseedata').datagrid("resize");
});
</script>
