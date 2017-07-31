<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- jquery -->
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<link rel="stylesheet" href="${ztree}/ztree/zTreeStyle.css" type="text/css" />
		<script type="text/javascript" src="${ztree}/jquery.ztree.core.js"></script>
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css" />
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css"/>
</HEAD>
<BODY class="easyui-layout">
     <div data-options="region:'west',split:true,title:'自由裁量权标准'"
		style="width: 220px;">
			<div data-options="region:'north',border:false"
		style="height: 20px; padding: 0px 3px; overflow: hidden;">
			<a href="#" id="addParent">增加${name}</a>
	</div>
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	<div id="tt" class="easyui-tabs" data-options="region:'center'">
        	<iframe id="contentFrame" name="contentFrame" style="width:100%;height:100%;" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="auto" allowtransparency="yes">
	        </iframe>
	</div>
</BODY>
</HTML>
	
<SCRIPT type="text/javascript">
	<!--
	var zid_ = null;
	var pid_ = null;
	var setting = {
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick:onClick
		}
	};

	function onClick(event, treeId, treeNode, clickFlag){
	     zid_ = treeNode.id;
	     pid_ = treeNode.pid;
		 $('#contentFrame').attr('src','${ctx}/discretionInfo.htm?id='+treeNode.id);
	}
	
	function ref(){
	   location ='${ctx}/discretionPage.htm?zid='+zid_;
	}
	
	$(document).ready(function(){
		var zNodes=${arr};
		var treeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		var nodes = treeObj.getNodesByParam("selected", true, null);
		for(var i in nodes){	
	        treeObj.selectNode(nodes[i], true, true);
	    }
		$('#contentFrame').attr('src','${ctx}/discretionInfo.htm?id=${zid}');
	    $("#addParent").click(function(){
				$.colorbox({iframe : true,
					width : "80%",
					height : "85%",
					href : "addDiscretionNode.htm"
				});
	     });
		
	});
	//-->
</SCRIPT>