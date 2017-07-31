<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html>
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
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css"/>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css"/>
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css"/>
		<!-- app -->
		<link rel="stylesheet" type="text/css" href="${app}/home.css"/>
		<script type="text/javascript" src="${common}/All.js"></script>
	</head>

	<body>
		<div class="headTitle" id="divTitle">选择工业污染源</div>
		<div id="main" class="main" style="padding:0px;margin:0 auto; width:98%; height:518px; border:1px solid #95b8e7;">
				<div class="left" style="width:70%;height:518px; border:0px solid #95b8e7; OVERFLOW-Y: auto; OVERFLOW-X:auto;">
				<div class="divContainer" id="questionContainer">
				<form id="queryForm" action="queryGywryList.json" method="post">
					<input type="hidden" id="lawobjtype" name="lawobjtype" value="${lawobjtype}" />
					<input type="hidden" id="rwid" name="rwid" value="${rwid}" />
					<input type="hidden" id="rwlxIds" name="rwlxIds" value="${rwlxIds}" />
					<input type="hidden" id="ymbiaoshi" name="ymbiaoshi" value="${ymbiaoshi}" />
					<input type="hidden" id="page" name="page" value="${page}" />
					<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" /> 
					<input type="hidden" name="isActive" value="Y" /> 
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="formtable">
						<tr>
							<th width="150" align="right">
								工业污染源名称：
							</th>
							<td width="200">
								<input class="i-text" type="text" name="name" id=""/>
							</td>
							<th width="150" align="right">
								所属监管部门：
							</th>
							<td width="200">
								<input class="i-text" style="width:150px;" type="text" id="ssjgbm" name="orgId" value="${orgId}"/>
							</td>
							<th width="120" align="right">
								控制类型：
							</th>
							<td>
								<input class="i-text" type="text" id="kzlx" name="kzlx"/>
							</td>
						</tr>
						<tr>
							<td colspan="5" align="center">
								<span class="btn btn-ok"> <input id="query" type="submit"
										value="查询"> </span>
								&nbsp;
								<a id="J-from-reset" class="btslink">重置</a>&nbsp;
							</td>
							<td align="right" >
								<a id="xinjian" class="btslink">新建</a>&nbsp;&nbsp;
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="divContainer" id="infectlist">
				<table id="data" fit="true"></table>
			</div>
			</div>
			<div class="right" style="width:29%;height:518px; border-bottom:0px solid #95b8e7;border-left:1px solid #95b8e7;border-top:none;">
				<table id="choseedata" title="您选择的污染源">
				</table>
			</div>
		</div>
		<table style="width : 100%;">
			<tr>
				<td align="center" height="50">
					<span class="btn btn-ok"> <input id="save" type="submit"
						value="确定"> </span>
					&nbsp;
				</td>
			</tr>
		</table>
	</body>
</html>

<script language="JavaScript">

$(document).ready(function(){

	$('#ssjgbm').combotree({
		url:'orgTree.json',
		valueField:'id',
		textField:'name'
	});
	
	$('#kzlx').combobox({
		url:'kzlxList.json',
		valueField:'id',
		textField:'name'
	});
	
	$('#zt').combobox({
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
	$("#infectlist").height($("#main").outerHeight() - $("#questionContainer").outerHeight() -2);
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		fitColumns:true,
		nowrap: false,
		url:'queryGywryList.json?isActive=Y&orgId='+$('#ssjgbm').val(),
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
			{field:'name',title:'工业污染源名称', align:"left", halign:'center', width:100},
			{field:'orgName',title:'所属监管部门', align:"center", halign:'center', width:50},
			{field:'kzlx',title:'控制类型', align:"center", halign:'center', width:30},
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
			{field:'newtaskid',hidden:true},
			{field:'lawobjname',title:'工业污染源名称', align:"left", halign:'center',width:100},
		    {field:'operate',title:'操作', align:"center", halign:'center',width:30,
		    	formatter:function(value,rowData,rowIndex){  
		    		return '<a onclick="delrow(\''+ rowData.lawobjid +'\')">删除</a> ';  
		       }  
			}
		]]
	});
	
	//确认返回数据
	$("#save").click(function(){
	    var bs = $('#ymbiaoshi').val();
		var rows = $('#choseedata').datagrid('getRows');
		var rwlxIds = $('#rwlxIds').val();
		if(rows.length==0){
			alert("请至少选择一个工业污染源！");
			return;
		}
		if(rows.length>1 && rwlxIds=='13'){//信访投诉
			alert("信访投诉任务只能选择一个工业污染源！");
			return;
		}
		var rowString = '[';
		for(var i=0;i<rows.length;i++){
			rowString += "{'lawobjid':'"+rows[i].lawobjid+"'";
			rowString += ",'lawobjname':'"+rows[i].lawobjname+"'";
			rowString += ",'fddbr':'"+rows[i].fddbr+"'";
			rowString += ",'fddbrlxdh':'"+rows[i].fddbrlxdh+"'";
			rowString += ",'hbfzr':'"+rows[i].hbfzr+"'";
			rowString += ",'hbfzrlxdh':'"+rows[i].hbfzrlxdh+"'";
			rowString += ",'regionid':'"+rows[i].regionid+"'";
			rowString += ",'address':'"+rows[i].address+"'}";
			if(i!=rows.length-1){
				rowString += ",";
			}
		}
		rowString += ']';
		var nameString = '';
		var parentWin=window.dialogArguments; 
		for(var j=0;j<rows.length;j++){
			if(j<rows.length-1){
				nameString += rows[j].lawobjname+',';
			}else{
				nameString += rows[j].lawobjname;
			}
		}
		$.ajax({
			  type: "POST",
			  url: "saveChoseeLawobj.json",
			  data: "rows="+encodeURIComponent(rowString)+"&lawobjtype="+$("#lawobjtype").val()+"&rwid="+$("#rwid").val(),
			  success:function(data){
				  if(data.state){
						if(bs == '1'){
							parentWin.document.all.zfdxmc.value = nameString;
							parentWin.document.all.mcs.value = rowString;
							parentWin.rwlxcz();
							parentWin.rwmczh();
							//window.opener.document.all.zfdxmc.value = nameString;
							//window.opener.document.all.mcs.value = rowString;
							//window.opener.rwmczh();
						}else{
							alert(data.msg);
						}
						self.close();
				 }else{
				 	alert(data.msg);
				 }
			  }
			});
	});
	
	initPager();
	
	$("#xinjian").click(function(){
		All.ShowModalWin('gywryEdit.htm', '新建工业污染源');
		$('#queryForm').submit();
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
	//删除行`
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