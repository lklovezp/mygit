<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
        <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="${app}/js.js"></script>
		<script type="text/javascript" src="${app}/data.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		 <script type="text/javascript" src="${layer}/layer.js"></script>
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
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css"/>
        <link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
        <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="${app}/css/taskDispatch.css" type="text/css">
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
        <style>
          .datagrid-header,.datagrid-header td ,.datagrid-td-rownumber{background-color: #cff1ff;}
        </style>
	</head>

	<body>
		<div class="right" id="right" style="margin:0 auto; width : 100%; border:0px solid #95b8e7;">
			<div id="righttop" style="">
				<table width="90%" align="center" height="50" style="">
					<tr>
						<td>
							制度分类：<span>${zdfl.name }</span>
							<input type="hidden" name="zdfl.id" id="zdflId" value="${zdfl.id }"/>
							<input type="hidden" name="zdfl.name" id="zdflName" value="${zdfl.name }"/>
						</td>
						<td align="right">
							<div><input id="new" type="submit" class="bTn blue" value="添加违法行为"></div>
						</td>
					</tr>
				</table>
			</div>
			<div class="" id="infectlist">
				<table id="data" fit="true"></table>
			</div>
		</div>
	</body>
</html>

<script language="JavaScript">
$(document).ready(function(){
	$("#infectlist").height($(window).height() - $("#righttop").outerHeight());

	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		nowrap:false,
		fitColumns : true,
		url:'queryWfxwList.json?pid=${zdfl.id }',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'content',title:'违法行为', align:"center", halign:'center',width:100},
			{field:'operate',title:'操作', align:"center", halign:'center',width:30,
				formatter:function(value,rowData,rowIndex){  
					var href = '<a onclick="info(\''+ rowData.id +'\')" class="b-link">查看</a> ';
					href += '<a onclick="edit(\''+ rowData.id +'\')" class="b-link">修改</a> ';
					href += '<a onclick="del(\''+ rowData.id +'\')" class="b-link">删除</a> ';
		    		return href;  
		        } 	
			}
		]],
		onDblClickRow: function(rowIndex, rowData){
			//All.ShowModalWin('wfxwInfo.htm?id='+rowData.id, '新建/编辑执法文件');
			var width=800;
		  	var height=600;
		  	var title='查看违法行为信息';
		  	parent.parent.layerIframe(2,'wfxwInfo.htm?id='+id,title,width,height);
		}
	});
});

function edit(id){
	//All.ShowModalWin('wfxwEdit.htm?id='+id, '违法行为信息', 800, 600);
	//parent.location.reload();
	parent.parent.layer.open({
        type: 2,
        title: '违法行为信息',
        area: ['800px', '600px'],
        content: 'wfxwEdit.htm?id='+id, 
        end: function () {
        	refresh();
        }

    });
	
}

function info(id){
	//All.ShowModalWin('wfxwInfo.htm?id='+id, '违法行为信息', 800, 600);
	    var width=800;
	  	var height=600;
	  	var title='查看违法行为信息';
	  	parent.parent.layerIframe(2,'wfxwInfo.htm?id='+id,title,width,height);
}
/*
function del(id){
	$.messager.confirm('违法行为管理', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
			  url: "deleteWfxw.json?id="+id,
			  success:function(data){
				 alert(data.msg);
				 parent.location.reload();
			  }
			});
		}
	});
}*/
//删除
function del(id) {
	var index=layer.confirm('确定删除吗？', {
     	icon: 3, 
        title:'删除任务'
     }, function(index){
    	 $.ajax( {
				url : "deleteWfxw.json?id="+id,
				success : function(data) {
					refresh();
					//parent.location.reload();
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}

$("#new").click(function(){
	var typeid = $("#zdflId").val();
	var typeName = $("#zdflName").val();
	if(typeid==null || typeid == ''){
		alert("请先选择一个制度分类！");
	}else{
		//All.ShowModalWin('wfxwEdit.htm?type.id=${zdfl.id}&type.name='+encodeURIComponent(typeName), '添加违法行为', 800, 600);
		//parent.location.reload();
		parent.parent.layer.open({
            type: 2,
            title: '添加违法行为',
            area: ['800px', '600px'],
            content: 'wfxwEdit.htm?type.id=${zdfl.id}&type.name='+encodeURIComponent(typeName), 
            end: function () {
                //location.reload();
            	refresh();
            }

        });
	}
});
</script>