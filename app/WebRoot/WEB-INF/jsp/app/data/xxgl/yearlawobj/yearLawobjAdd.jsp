<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加年度抽查对象</title>
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
		<div class="headTitle" id="divTitle">${title} </div>
		<div id="main" class="main" style="padding:0px;margin:0 auto; width:98%; height:518px; border:1px solid #95b8e7;">
				<div class="left" style="width:70%;height:518px; border:0px solid #95b8e7; OVERFLOW-Y: auto; OVERFLOW-X:auto;">
				<div class="divContainer" id="questionContainer">
				<form id="queryForm" action="selectLawobj.json" method="post">
					<input type="hidden" id="year" name="year" value="${year}" />
					<input type="hidden" id="page" name="page" value="${page}" />
					<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" /> 
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="formtable">
						<tr>
						<th width="120px;">
							执法对象类型：
						</th>
						<td width="150px;">
							<input class="i-text" type="text" id="lawobjtype" name="lawobjtype" value="01"/>
						</td>
						<th width="120" align="right">
							执法对象名称：
						</th>
						<td>
							<input class="i-text" type="text" name="name" id=""/>
						</td>
						<th width="120" align="right">
							所属监管部门：
						</th>
						<td>
							<input class="i-text" style="width:150px;" type="text" id="ssjgbm" name="orgId" value="${orgId}"/>
						</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
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
			</div>
			<div class="right" style="width:29%;height:518px; border-bottom:0px solid #95b8e7;border-left:1px solid #95b8e7;border-top:none;">
				<table id="choseedata" title="您选择的执法对象">
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
	//执法对象类型下拉框
	$('#lawobjtype').combobox({
		url:'dicList.json?type=5',
		valueField:'id',
		textField:'name'
	});	
	//所属监管部门
	$('#ssjgbm').combotree({
		url:'orgTree.json',
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
		singleSelect: false,
		pagination:true,
		fitColumns:true,
		nowrap: false,
		url:'selectLawobj.json?lawobjtype='+$('#lawobjtype').val()+"&year="+$('#year').val(),
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
			{field:'name',title:'执法对象名称', align:"left", halign:'center', width:100},
			{field:'orgName',title:'所属监管部门', align:"center", halign:'center', width:50},
			{field:'kzlx',title:'控制类型', align:"center", halign:'center', width:30},
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
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'lawobjid',hidden:true},
			{field:'lawobjname',title:'执法对象名称', align:"left", halign:'center',width:100},
		    {field:'operate',title:'操作', align:"center", halign:'center',width:30,
		    	formatter:function(value,rowData,rowIndex){  
		    		return '<a onclick="delrow(\''+ rowData.lawobjid +'\')">删除</a> ';  
		       }  
			}
		]]
	});
	
	
	
	//确认返回数据
	$("#save").click(function(){
		var rows = $('#choseedata').datagrid('getRows');
		if(rows.length==0){
			alert("请至少选择一个执法对象！");
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
		var year = $('#year').val();
		//保存执法对象 
		$.post('yearLawobjSave.json',
				{ ids: ids, names: names, year:year},
				function(data, status){
					if(data.state){
						alert(data.msg);
						self.close();
						Android.close(data.id, data.name);
					}else{
						$.messager.alert('保存年度抽查对象信息:',data.msg);
					}
				}
		);
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
		$('#choseedata').datagrid("appendRow",{lawobjid:rowData.id,lawobjname:rowData.name});
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

</script>