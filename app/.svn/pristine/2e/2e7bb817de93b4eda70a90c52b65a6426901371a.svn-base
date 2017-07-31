<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<style>
	 .formtable th{font-weight: normal;}
	</style>
	</head>

	<body style="padding-bottom: 70px;">
		<div id="title" style="width: 99%;margin:16px auto 16px;font-size: 18px;" align="center">选择饮食业信息</div>
		<div id="main" class="main" style="padding:0px;margin:0 auto; width:98%; height:380px; border:1px solid #95b8e7;">
				<div class="left" style="float:left; width:75%;height:380px; border:0px solid #95b8e7; ">
				<div style="margin:16px auto 0px;" id="questionContainer">
				<form id="queryForm" action="queryYsyList.json" method="post">
					<input type="hidden" id="lawobjtype" name="lawobjtype" value="${lawobjtype}" />
					<input type="hidden" id="rwid" name="rwid" value="${rwid}" />
					<input type="hidden" id="page" name="page" value="${page}" />
					<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" /> 
					<input type="hidden" name="isActive" value="Y" /> 
					<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="formtable">
					<tr>
						<th width="150" align="right">
							单位名称：
						</th>
						<td width="100" align="left">
							<input class="y-text" type="text" name="name" id="" style="width:127px;"/>
						</td>
						<th width="150" align="right">
							所属监管部门：
						</th>
						<td width="100">
							<input class="y-text"  style="width:150px;" type="text" id="ssjgbm" name="orgId" value="${orgId}"/>
						</td>
						<th width="110" align="right">
							企业状态：
						</th>
						<td align="left">
							<input class="y-text" type="text" id="qyzt" value="0" name="qyzt"/>
						</td>
					</tr>
					<tr style="height:60px;">
						<td colspan="6" align="center">
							<input id="query" type="submit" class="queryBlue" value="查询"> 
							<input  id="J-from-reset" type="reset" class="queryOrange" value="重　置"/>
							<input type="button" class="bTn btnbgAdd1 directory_comwidth1" id="xinjian" value="新建" />
						</td>
					</tr>
				</table>
				</form>
			</div>
			<div class="divContainer" id="infectlist">
				<table id="data" fit="true"></table>
			</div>
			</div>
			<div style="float:right;width:24.5%;height:380px; border-bottom:0px solid #95b8e7;border-left:1px solid #95b8e7;border-top:none;">
				<h1 style="height:30px;font-size: 16px;" align="center">您选择的饮食业信息</h1>
				<table id="choseedata" >
				</table>
			</div>
		</div>
		<div class="rb_btn_fix">
		<input id="save" type="submit" class="queryBlue" value="确定"> 
		</div>
	</body>
</html>

<script language="JavaScript">

$(document).ready(function(){

	$('#ssjgbm').combotree({
		height:34,
		url:'orgTree.json',
		valueField:'id',
		textField:'name'
	});
	$('#qyzt').combobox({
		height:34,
		url:'qyztList.json',
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
	});
	$("#infectlist").height($("#main").outerHeight() - $("#questionContainer").outerHeight() -20);
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: false,
		pagination:true,
		fitColumns:true,
		nowrap: false,
		url:'queryYsyList.json?isActive=Y&qyzt=0&orgId='+$('#ssjgbm').val(),
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
			{field:'address',hidden:true},
			{field:'fddbr',hidden:true},
			{field:'fddbrlxdh',hidden:true},
			{field:'hbfzr',hidden:true},
			{field:'hbfzrlxdh',hidden:true},
			{field:'regionid',hidden:true},
			{field:'name',title:'单位名称', align:"left", halign:'center', width:100},
			{field:'orgName',title:'所属监管部门', align:"center", halign:'center', width:50},
			{field:'qyzt',title:'企业状态', align:"center", halign:'center',width:20},
		]],
		onSelect:function(rowIndex, rowData){
			addrow(rowData);
		},
		onUnselect:function(rowIndex, rowData){
			if(canDel(rowData.id)){
				delRightRow(rowData.id);
			}else{
				selectRow(rowData.id);
			}
		},
		onCheckAll:function(rows){
			for(var i=0;i<rows.length;i++){
				addrow(rows[i]);
			}
		},
		onUncheckAll:function(rows){
			var isok = true;
			for(var i=0;i<rows.length;i++){
				if(!canDel(rows[i].id)){
					isok = false;
					break;
				}
			}
			if(isok){
				for(var i=0;i<rows.length;i++){
					delRightRow(rows[i].id);
				}
			}else{
				$('#data').datagrid("selectAll");
			}
		}
	});
	$('#choseedata').datagrid({
		rownumbers: true,
		singleSelect: true,
		fitColumns:true,
		nowrap: false,
		url:'querySelectLawobjList.json?rwid=${rwid}&lawobjtype=${lawobjtype}',
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
			{field:'lawobjid',hidden:true},
			{field:'address',hidden:true},
			{field:'fddbr',hidden:true},
			{field:'fddbrlxdh',hidden:true},
			{field:'hbfzr',hidden:true},
			{field:'hbfzrlxdh',hidden:true},
			{field:'regionid',hidden:true},
			{field:'lawobjname',title:'单位名称', align:"left", halign:'center',width:100},
		    {field:'operate',title:'操作', align:"center", halign:'center',width:30,
		    	formatter:function(value,rowData,rowIndex){  
		    		return '<a class="b-link" onclick="delrow(\''+ rowData.lawobjid +'\')">删除</a> ';  
		       }  
			}
		]]
	});
	
	//确认返回数据
	$("#save").click(function(){
		var rows = $('#choseedata').datagrid('getRows');
		if(rows.length==0){
			alert("请至少选择一条饮食业信息！");
			return;
		}
			var ids = "";
		var names = "";
		for(var i=0;i<rows.length;i++){
			ids += rows[i].lawobjid;
			names += rows[i].lawobjname;
			if(i!=rows.length-1){
				ids += ",";
				names += ",";
			}
		}
		
		// 调用父窗口方法
		//var parentObj = window.dialogArguments;
		//parentObj.setloawObj(ids, names);
		//self.close();
		   parent.setloawObj(ids, names);
		   var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		   parent.layer.close(index);
	});
	initPager();

	$("#xinjian").click(function(){
		//All.ShowModalWin('ysyEdit.htm', '新建饮食业信息',1000, 800);
		//$('#queryForm').submit();
		parent.layer.open({
            type: 2,
            title: '新建饮食业信息',
            area: ['1100px', ($(window).height()+40)+'px'],
            content: 'ysyEdit.htm', 
            end: function () {
                //location.reload();
            	$('#queryForm').submit();
            }

        });
	});
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
		$('#choseedata').datagrid("appendRow",{lawobjid:rowData.id,lawobjname:rowData.name,fddbr:rowData.fddbr,fddbrlxdh:rowData.fddbrlxdh,hbfzr:rowData.hbfzr,hbfzrlxdh:rowData.hbfzrlxdh,regionid:rowData.regionid,address:rowData.address});
	}
}

//从右边删除
function delrow(id){
	if(canDel(id)){
		delRightRow(id);
		unSelectRow(id);
	}
}


//删除右边行数据
function delRightRow(id){
	//删除行
	var rows = $('#choseedata').datagrid('getRows');
	for(var i=0;i<rows.length;i++){
		if(rows[i].lawobjid==id){
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


//判断是否能删除
function canDel(id){
	var rows = $('#choseedata').datagrid('getRows');
	for(var i=0;i<rows.length;i++){
		if(rows[i].lawobjid==id){
			if(rows[i].newtaskid!='' && rows[i].newtaskid!=null && rows[i].newtaskid != 'null'){
				alert("专项子任务已分工，不能删除执法对象！");
				return false;
			}
		}
	}
	return true;
}

</script>