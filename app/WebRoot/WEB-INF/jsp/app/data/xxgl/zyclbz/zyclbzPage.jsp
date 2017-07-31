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
		<!-- app -->
		<link rel="stylesheet" type="text/css" href="${app}/home.css"/>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<script type="text/javascript" src="${common}/All.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<!-- ztree -->
		<link rel="stylesheet" href="${ztree }/css/zTreeStyle/zTreeStyle.css" type="text/css" />
		<script type="text/javascript" src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="${ztree}/js/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript" src="${ztree}/js/jquery.ztree.exedit-3.5.js"></script>
		<!-- zTree 修改的样式-->
         <link href="${app}/css/zTreeReset.css" rel="stylesheet" type="text/css"/>
		 <!--自由裁量权裁标准管理的样式-->
        <link rel="stylesheet" href="${app}/css/taskDispatch.css" type="text/css">
	</head>

	<body>
	<div class="breadClumbs" id='h1'> 知识库&nbsp;&gt;&nbsp;${title }</div>
		<!--  <div class="h1_1" id="divTitle">${title }</div>-->
		<div id="main" style="">
			<div class="left" id="left" style="width:30%;border:1px solid #95b8e7; OVERFLOW-Y: auto; OVERFLOW-X:auto;" data-options="region:'west'">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div class="right" id="right" style="margin:0 auto;width : 69%; border:1px solid #95b8e7;">
				<iframe id="contentIframe" name="contentIframe"  frameborder="no" border="0" src="" style="border:none;"></iframe>
			</div>
		</div>
	</body>
</html>

<script language="JavaScript">
//树菜单配置
var zTree_Menu = null, curMenu = null;
var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "pId"
		}
	},
	 view: {
         showLine: false
     },
	callback: {
		onClick: this.onClick
	}
};

function onClick(event, treeId, treeNode, clickFlag) {
	var href = treeNode.href;

	window.frames["contentIframe"].location.href=href;
}

var menu_=${array};

function beforeClick(treeId, node) {
	if (node.isParent) {
		if (node.level === 0) {
			var pNode = curMenu;
			while (pNode && pNode.level !==0) {
				pNode = pNode.getParentNode();
			}
			if (pNode !== node) {
				var a = $("#" + pNode.tId + "_a");
				a.removeClass("cur");
				zTree_Menu.expandNode(pNode, false);
			}
			a = $("#" + node.tId + "_a");

			var isOpen = false;
			for (var i=0,l=node.children.length; i<l; i++) {
				if(node.children[i].open) {
					isOpen = true;
					break;
				}
			}
			if (isOpen) {
				zTree_Menu.expandNode(node, true);
				curMenu = node;
			} else {
				zTree_Menu.expandNode(node.children[0].isParent?node.children[0]:node, true);
				curMenu = node.children[0];
			}
		} else {
			zTree_Menu.expandNode(node);
		}
	}
	return !node.isParent;
}
// 树 end

$(document).ready(function(){
	var treeObj = $.fn.zTree.init($("#treeDemo"), setting, menu_);
	var nodes = treeObj.getNodes();
	//var hi=$("#divTitle").outerHeight();
	var hi=0;
	treeObj.expandNode(nodes[0], true, true, true);
	onClick(null,null,nodes[0]);
	$("#left").height($(window).height() - hi - 50);
	$("#right").height($(window).height() - hi - 50);
	$("#right").width($(window).width() - $("#left").outerWidth() - 20);

	$("#main").width($(window).width() - 19);

	$("#contentIframe").height($(window).height() - hi - 65);
	$("#contentIframe").width($(window).width() - $("#left").outerWidth() - 42);
	
});
</script>