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
			src="${easyui}/locale/easyui-lang-zh_CN.js">
	
</script>

		<link rel="stylesheet" href="${ztree}/ztree/zTreeStyle.css"
			type="text/css" />
		<script type="text/javascript" src="${ztree}/jquery.ztree.core.js"></script>
		<script type="text/javascript" src="${ztree}/jquery.ztree.excheck.js"></script>

		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<link href="${app}/treeicon.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div id="treeDiv" style="padding: 10px;">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<center><a id="btnConfirm" href="#" class="easyui-linkbutton">确定</a></center>
	</body>
</html>
<script LANGUAGE="JavaScript">
//左侧树
var setting = {
	view: {
		showLine: false
	},
	data: {
		simpleData: {
			idKey: "id", 
			pIdKey: "pid",  
			enable: true
		}
	},
	callback: {
		onClick : onClick
	}
};

var menu_ =[{id:'1',isactive:'Y',name:'工业污染源'}, {id:'2',isactive:'Y',name:'建设项目'}, {fid:'7',id:'11',isactive:'Y',name:'娱乐业',pid:'7'}, {fid:'7',id:'10',isactive:'Y',name:'制造业',pid:'7'}, {fid:'7',id:'9',isactive:'Y',name:'饮食业',pid:'7'}, {fid:'7',id:'8',isactive:'Y',name:'服务业',pid:'7'}, {id:'7',isactive:'Y',name:'三产'}, {id:'3',isactive:'Y',name:'医院'}, {id:'4',isactive:'Y',name:'锅炉'}, {id:'5',isactive:'Y',name:'建筑工地'},{id:'6',isactive:'Y',name:'畜禽养殖'},{id:'40288ad35c85dfb0015c867432490002',isactive:'Y',name:'EFW'},{id:'40288ad35c85dfb0015c868e2e570007',isactive:'Y',name:'fsa'},{id:'40288ad35c86b1b2015c86bdef6c0004',isactive:'Y',name:'好尴尬'},{id:'40288ad35c86ca84015c86eed19a0003',isactive:'Y',name:'噶'},{id:'40288ad35c86ca84015c86f06f300005',isactive:'Y',name:'gas'},{fid:'40288ad35c86ca84015c86f06f300005',id:'40288ad35c86ca84015c86f0890f0006',isactive:'Y',name:'tet',pid:'40288ad35c86ca84015c86f06f300005'}];
var treeObj = $.fn.zTree.init($("#treeDemo"), setting, menu_);

	$(document).ready( function() {
		var nodes = treeObj.getNodes();
		treeObj.expandNode(nodes[0], true, true, true);
	});
</script>