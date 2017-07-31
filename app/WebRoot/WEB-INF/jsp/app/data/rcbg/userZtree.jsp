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
		<div id="treeDiv" style="padding: 10px;overflow: auto;">
			<ul  id="treeDemo" class="ztree"></ul>
		</div>
		<center><a id="btnConfirm" href="#" class="easyui-linkbutton">确定</a></center>
	</body>
</html>
<script LANGUAGE="JavaScript">
	//s-单选，m-多选
	var multi = '${multi}';
	var methodInfo = '';
	var url = location.href;
	var panelH = 350;
	var isSelectLeafOnly;
	var psRelation = {
		"Y" : "ps",
		"N" : "ps"
	};
	

	var setting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pid"
			}
		}
	};
	//单选
	function onClick(event, treeId, treeNode, clickFlag) {
		if (treeNode.isParent || treeNode.isorg) {
			alert("不能选择部门。");
			return;
		}
		eval("parent." + methodInfo + "('" + treeNode.id + "','"
				+ treeNode.name + "')");
	}
	//多选
	function onClickMultiple(event, treeId, treeNode, clickFlag) {
		if (treeNode.isParent || treeNode.isorg) {
			alert("不能选择部门。");
			return;
		}
		eval("parent." + methodInfo + "('" + treeNode.id + "','"
				+ treeNode.name + "')");
	}
	$(document).ready( function() {
		var paramStr = url.substring(url.indexOf('?') + 1, url.length)
				.split('&');
		for ( var i = 0; i < paramStr.length; i++) {
			var strInfo = paramStr[i].split('=');
			if (strInfo[0] == 'methodname') {
				methodInfo = strInfo[1];
			} else if (strInfo[0] == 'isSelectLeafOnly') {
				isSelectLeafOnly = strInfo[1];
			} else if (strInfo[0] == 'psRelation') {
				psRelation = strInfo[1];
			}
		}
		if (multi == 'true') {
			setting.check = {
				enable : true,
				chkStyle : "checkbox",
				chkboxType : psRelation
			};
			$('#btnConfirm').click(function() {
				var tree = $.fn.zTree.getZTreeObj("treeDemo");
				var checked = tree.getCheckedNodes(true);
				var ids = '';
				var names = '';
				for(var i = 0; i < checked.length; i++){
					if(!checked[i].isorg){
						/**
						if(i > 0){
							ids += ",";
							names += ",";
						}
					    */
						ids += checked[i].id+",";
						names += checked[i].name+",";
					}
				}
				if (ids == '') {
					alert("请选择人员。");
					return;
				}
				eval("parent." + methodInfo + "('" + ids
						+ "','" + names + "')");
			});
			panelH = 326;
		} else {
			setting.callback = {
				onClick : onClick
			};
			$('#btnConfirm').hide();
		}
		//赋值，并初始化Ztree
		var mmm = ${menu};
		var treeObj = $.fn.zTree.init($("#treeDemo"), setting, mmm);
		treeObj.expandAll(true);
		var nodes = treeObj.getNodesByParam("selected", true, null);
		if (multi == 'true') {
			for ( var i in nodes) {
				treeObj.checkNode(nodes[i], true, true);
			}
		} else {
			for ( var i in nodes) {
				treeObj.selectNode(nodes[i], true, true);
			}
		}
		$('#treeDiv').panel( {
			width : 290,
			height : 330
		});
	});
</script>