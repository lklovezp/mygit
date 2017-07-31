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
	</head>

	<body>
		<div class="right" id="right" style="margin:0 auto; width : 100%; border:0px solid #95b8e7;">
			<div id="righttop" style="">
				<table width="90%" align="center" height="" style="">
					<tr height="30">
						<td>
							制度分类：<span>${wfxw.type.name }</span>
						</td>
					</tr>
					<tr height="30">
						<td>
							违法行为：<span>${wfxw.content }</span>
						</td>
					</tr>
					<tr height="30">
						<td>
							违法类型：<span>${wfxw.illegaltype.name }</span>
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
		url:'queryFlyjList.json?pid=${wfxw.id}',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'name',title:'法律依据', align:"left", halign:'center',width:100},
			{field:'operate',title:'操作', align:"center", halign:'center',width:30,
				formatter:function(value,rowData,rowIndex){  
					var href = '<a onclick="info(\''+ rowData.id +'\')">查看</a> ';
		    		return href;  
		        } 	
			}
		]],
		onDblClickRow: function(rowIndex, rowData){
			//All.ShowModalWin('flyjInfo.htm?id='+rowData.id, '新建/编辑执法文件')
			var width=screen.width * 0.8;
		  	var height=screen.height * 0.8-50;
		  	var title='查看法律依据';
		  	parent.parent.layerIframe(2,'flyjInfo.htm?id='+rowData.id,title,width,height);
		}
	});
});


function info(id){
	//All.ShowModalWin('flyjInfo.htm?id='+id, '法律依据信息',800, 600);
	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	var title='查看法律依据';
  	parent.parent.layerIframe(2,'flyjInfo.htm?id='+id,title,width,height);
}

</script>