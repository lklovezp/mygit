<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>app</title>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" href="${ztree}/ztree/zTreeStyle.css"
			type="text/css">
		<script type="text/javascript" src="${ztree}/jquery.ztree.core.js"></script>
	</head>
	<body>
		<div>
			<ul id="treeDemo" class="ztree"></ul>
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
function onClick(event, treeId, treeNode, clickFlag){
	if ('${id}' != '' && '${id}' == treeNode.id){
		alert("不能选择自己作为父功能。");
		return;
	}
	parent.setMobileInfo(treeNode.id,treeNode.name);
} 
$(document).ready(function(){
    var mmm = ${menu};
	var treeObj = $.fn.zTree.init($("#treeDemo"), setting,mmm);
	var nodes = treeObj.getNodesByParam("selected", true, null);
    for(var obj in nodes){
        treeObj.selectNode(nodes[obj]);
    }
});
</script>