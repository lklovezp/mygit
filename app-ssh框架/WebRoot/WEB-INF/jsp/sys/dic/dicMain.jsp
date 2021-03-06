<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>数据字典</title>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/default/menu.css">
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<link rel="stylesheet" href="${ztree}/ztree/zTreeStyle.css"	type="text/css">
		<script type="text/javascript" src="${ztree}/jquery.ztree.core.js"></script>
		<!-- zTree 修改的样式-->
   		 <link href="${app}/zTreeReset.css" rel="stylesheet" type="text/css"/>
   		 <!--css-->
	    <link rel="stylesheet" href="${app}/css/taskDispatch.css" type="text/css">
	    <!--系统管理 css-->
	    <link rel="stylesheet" href="${app}/css/management.css" type="text/css">
	    <!-- app -->
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
		.layout-split-west {
    		border-right: 0px solid #E6EEF8;
		}
		.tabs li a.tabs-inner,.panel-header, .panel-body, .tabs, .tabs-panels {
  			border-color: #d4d4d4;
		}
		
		.tabs li a.tabs-inner{background: #F5F5F5;filter:none;}
		
		.tabs li.tabs-selected a.tabs-inner{background:#CFF1FF;filter:none;}
		.panel-header {
			background:#ffffff;
    		border-color: #cff1ff;
    		border: 0px;
    		padding: 1px;
		}
		.tabs-header{background-color:#ffffff;}
		.panel-title {
		    font-weight: bold;
		    color: #262626;
		    height: 26px;
		    line-height: 26px;
		}
		.panel {
		    font-size: 15px;
		    text-align: left;
		}
    	#checkup_con_l{ overflow:auto;}
    	.ztree li button {
		    width: 24px;
		    height: 20px;
		    background-position: 5px -2px;
		    background-image: url(${app}/images/tree_leaf.png);
		}
		.ztree li button.ico_docu {
		    
		    background-position: 0px 0px;
		    
		}
		</style>
	</head>
	<body class="easyui-layout">
		
		<div id="checkup_con_l" class="clearfix" region="west" split="true" title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;字典分类"
			style="width: 200px; padding: 0px;">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		
		<div id="tt" class="easyui-tabs" tools="#tab-tools" region="center">
		</div>
	</body>
</html>
<script LANGUAGE="JavaScript">
var setting = {
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		onClick: onClick
	}
};

function onClick(event, treeId, treeNode, clickFlag) 
{
	var tt = $('#tt');
	//如果tab已经存在,则选中该tab  
	if (tt.tabs('exists', treeNode.name)){
		tt.tabs('select', treeNode.name);
		var currTab = tt.tabs('getTab', treeNode.name);
        var iframe = $(currTab.panel('options').content);
        var src = iframe.attr('src');
        tt.tabs('update', { tab: currTab, options: {content:createFrame(src)} });
		return;
	}
	var href = treeNode.href;

	if(null == href){
		return;
	}
	tt.tabs('add',{
		title:treeNode.name,
		content:createFrame(href),
		closable:true
	});
}
function createFrame(href){
	return "<iframe scrolling='yes' frameborder='0'  src='"+href+"' style='width:100%;height:100%;'></iframe>";
}
$(document).ready(function(){
	$("#tt").tabs('add',{
		title:"字典首页",
		content:"<iframe frameborder='0'  src='dicHome.htm' style='width:100%;height:100%;'></iframe>",
		closable:false
	});
	$('body').layout('panel','west').panel();
	$.fn.zTree.init($("#treeDemo"), setting,${menu});
});
</script>