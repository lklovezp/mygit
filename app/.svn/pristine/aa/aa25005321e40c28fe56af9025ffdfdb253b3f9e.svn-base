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
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css"/>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css"/>
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css"/>
		<!-- app -->
		<link rel="stylesheet" type="text/css" href="${app}/home.css"/>
		<script type="text/javascript" src="${common}/All.js"></script>
		<!-- ztree -->
		<link rel="stylesheet"href="${ztree}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
		<script type="text/javascript"src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript"src="${ztree}/js/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript"src="${ztree}/js/jquery.ztree.exedit-3.5.js"></script>
		<!-- zTree 修改的样式-->
        <link href="${app}/zTreeReset.css" rel="stylesheet" type="text/css"/>
        <!--执法目录管理-->
        <link rel="stylesheet" href="${app}/css/taskDispatch.css" type="text/css">
        <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
        <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
        <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css"/>
        <link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
        <style>
         .datagrid-htable{background-color: #ffffff;}
        </style>
	</head>
	<body style="padding-bottom: 580px;">
		<form id="queryForm" action="queryLawdocList.json?canDel=N" method="post">
		<input id="applyId" name="applyId" type="hidden" value="${applyId }"/>
		<input id="fileType" name="fileType" type="hidden" value="${fileType }"/>
		<div class="h1_1" id="divTitle">选择执法文件</div>
		<input id="pid" name="pid" type="hidden"/>
		<input id="pname" name="pname" type="hidden"/>
		<input type="hidden" id="page" name="page" value="${page}" />
		<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" /> 
		<div id="main" style="">
			<div class="left" id="left" style="width:20%;border:1px solid #dddddd; OVERFLOW-Y: auto; OVERFLOW-X:auto;" data-options="region:'west'">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div class="right" id="right" style="width : 79%;">
				<div id="righttop">
					<table width="100%" class="queryTable" border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
						<tr>
							<th width="100">
								标题：
							</th>
							<td>
								<input class="y-text" id="title" type="text" name="title" value=""/>
							</td>
							<th width="100">
								关键词：
							</th>
							<td>
								<input class="y-text" id="keywords" type="text" name="keywords" value=""/>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
							<input type="submit" class="queryBlue" id="query" value="查　询" onclick="hideSearchForm()"/>
                            <input type="reset" class="queryOrange" id="J-from-reset" value="重　置"/>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="3" height="376" width="500">
								<div class="divContainer" id="infectlist" style="height:100%;width:100%;overflow:auto;">
									<table id="data" fit="true" width="100%" ></table>
								</div>
							</td>
							<td align="center" height="376" width="250">
								<div style="height:100%;width:100%;overflow:auto;">
								<h1 style="height:30px;font-size: 16px; background-color: #cff1ff;" align="center">已选附件</h1>
									<table id="choseedata"  width="100%">
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		</form>
		<iframe name="download" id="download" src="" style="display: none"></iframe>
		<div class="rb_btn_fix">
		 <input id="save" type="button" class="queryBlue" value="确定"> </span>
		</div>
	</body>
</html>

<script language="JavaScript">
//树菜单配置
var zTree_Menu = null, curMenu = null;
var setting = {
	data: {
		simpleData: {
			enable: true
		}
	},
	view: {
        showLine: false
    },
	callback: {
		onClick : onClick
	}
};
var menu_=${menu};
//点击菜单事件
function onClick(event, treeId, treeNode, clickFlag) {
	$("#pid").val(treeNode.id);
	$("#pname").val(treeNode.name);
	$('#data').datagrid('reload',{pid:treeNode.id});
}


function download2(id){
	$('#download').attr('src','downloadFile?id='+id);
}

$(document).ready(function(){
	$("#left").height($(window).height() - $("#divTitle").outerHeight() -2);
	
	
	$('#queryForm').submit(function(){  
	    $("#queryForm").ajaxSubmit({
	   	  success: function(data){
	   	      $('#data').datagrid('loadData',data)
		  }
		 });
	   return false;  
	});

	$('#data').datagrid({
		rownumbers: true,
		singleSelect: false,
		pagination:true,
		fitColumns:true,
		nowrap: false,
		checkOnSelect:false,
		url:'queryLawdocList.json?canDel=N',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'fileid',hidden:true},
			{field:'ck',checkbox:true},
			{field:'name',title:'标题', align:"left", halign:'center',width:100},
			{field:'keywords',title:'关键字', align:"left", halign:'center',width:50},
			{field:'dirpath',title:'目录', align:"center", halign:'center',width:100},
			{field:'operate',title:'操作', align:"center", halign:'center',width:30}
		]],
		onCheck:function(rowIndex, rowData){
			addrow(rowData);
		},
		onUncheck:function(rowIndex, rowData){
			delRightRow(rowData.fileid);
		},
		onCheckAll:function(rows){
			for(var i=0;i<rows.length;i++){
				addrow(rows[i]);
			}
		},
		onUncheckAll:function(rows){
			for(var i=0;i<rows.length;i++){
				delRightRow(rows[i].fileid);
			}
		}
	});
	initPager();
	
	$('#choseedata').datagrid({
		rownumbers: true,
		fitColumns:true,
		nowrap: false,
		singleSelect: true,
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'fileid',hidden:true},
			{field:'name',title:'标题', align:"left",width:100},
		    {field:'operat',title:'操作', align:"center", width:40,
		    	formatter:function(value,rowData,rowIndex){  
		    		return '<a class="b-link" onclick="delrow(\''+ rowData.fileid +'\')">删除</a> ';  
		        }  
			}
		]]
	});
	
	$("#J-from-reset").click(function(){
		//$("#queryForm").form("reset");
		$("#keywords").val("");
		$("#title").val("");
	});
	
	
	//确认返回数据
	$("#save").click(function(){
		var rows = $('#choseedata').datagrid('getRows');
		if(rows.length==0){
			alert("至少选择一个执法文件！");
			return;
		}
		var fileid = '';
		for(var i=0;i<rows.length;i++){
			fileid += rows[i].fileid;
			if(i!=rows.length-1){
				fileid += ",";
			}
		}
		$.ajax({
			  type: "POST",
			  url: "saveChooseeLawdoc.json",
			  data: "fileid="+fileid+"&fileType="+$("#fileType").val()+"&applyId="+$("#applyId").val(),
			  success:function(data){
				 if(data.state){
					alert(data.msg);
					//self.close();
					var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
					parent.layer.close(index);
				 }else{
				 	alert(data.msg);
				 }
			  }
			});
	});
	
	var treeObj = $.fn.zTree.init($("#treeDemo"), setting, menu_);
	var nodes = treeObj.getNodes();
	treeObj.expandNode(nodes[0], true, true, true);
	var node = getLastChildrenNode(nodes);
	onClick(null,null,node);
});

//获得叶子节点
function getLastChildrenNode(nodes){
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].isFirstNode ){
			if(nodes[i].isParent){
				return getLastChildrenNode(nodes[i].children);
			}else{
				return nodes[i];
			}
		}
	}
}


//添加行
function addrow(rowData){
	var rows = $('#choseedata').datagrid('getRows');
	var isCZ = false;//是否存在
	for(var i=0;i<rows.length;i++){
		if(rowData.fileid==rows[i].fileid){
			isCZ = true;
		}
	}
	if(!isCZ){
		$('#choseedata').datagrid("appendRow",{fileid:rowData.fileid,name:rowData.name});
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
		if(rows[i].fileid==id){
			$('#choseedata').datagrid("deleteRow",i);
			break;
		}
	}
}

//取消左边选中状态
function unSelectRow(id){
	var dataRows = $('#data').datagrid('getSelections');
	for(j=0;j<dataRows.length;j++){
		if(dataRows[j].fileid==id){
			var index = $('#data').datagrid('getRowIndex',dataRows[j]);
			if(index>=0){
				$('#data').datagrid('uncheckRow',index);
			}
			return;
		}
	}
}

</script>