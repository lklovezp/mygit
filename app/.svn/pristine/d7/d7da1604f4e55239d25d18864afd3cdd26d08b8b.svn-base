<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>app</title>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>

		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/icon.css" />
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/default/easyui.css" />
		<script type="text/javascript"
			src="${easyui}/locale/easyui-lang-zh_CN.js"></script>

		<link rel="stylesheet" href="${ztree}/ztree/zTreeStyle.css"
			type="text/css" />
		<script type="text/javascript" src="${ztree}/jquery.ztree.core.js"></script>
		<script type="text/javascript" src="${ztree}/jquery.ztree.excheck.js"></script>

		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<link href="${app}/treeicon.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div>
		<div id="treeDiv" style="padding: 10px;height:300px;">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<div style="margin:0 auto;">
			<center>
			<a id="btnConfirm" href="#" class="easyui-linkbutton">确定</a>
			</center>
		</div>
		</div>
	</body>
</html>
<script LANGUAGE="JavaScript">

function onClick(event, treeId, treeNode, clickFlag){
	if ('${id}' != '' && '${id}' == treeNode.id){
		alert("不能选择自己作为上级部门。");
		return;
	}
	parent.setOrgInfo(treeNode.id,treeNode.name);
} 

$(document).ready(function(){
	$('#btnConfirm').click(function(){
		var tree = $.fn.zTree.getZTreeObj("treeDemo");
		var checked = tree.getCheckedNodes(true);
		var id = "";
		var name = "";
		for(var i = 0; i < checked.length; i++){
			if(i > 0){
				id += ",";
				name += ",";
			}
			id += checked[i].id;
			name += checked[i].name;
		}
		parent.setOrgInfo(id, name);
	});
	
	var setting = {
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	if ('${multi}' != '' && '${multi}' == 'true'){
		setting.check = {
			enable: true,
			chkboxType: { "Y" : "", "N" : "" }
		};
	} else {
		$('#btnConfirm').hide();
		setting.callback = {
			onClick: onClick
		};
	}

	var mmm = ${menu};
	var treeObj = $.fn.zTree.init($("#treeDemo"), setting, mmm);
	var nodes = treeObj.getNodesByParam("checked", true, null);
	for(var obj in nodes){
		treeObj.selectNode(nodes[obj]);
	}
});
</script>