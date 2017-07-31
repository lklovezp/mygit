<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"	href="${easyui}/themes/icon.css">
<link rel="stylesheet" type="text/css"	href="${easyui}/themes/default/easyui.css">
<script type="text/javascript"	src="${colorbox}/jquery.colorbox-min.js"></script>
<link rel="stylesheet" type="text/css"	href="${colorbox}/colorbox2.css">
<!-- ztree -->
<link rel="stylesheet"	href="${ztree}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<script type="text/javascript"	src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"	src="${ztree}/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript"	src="${ztree}/js/jquery.ztree.exedit-3.5.js"></script>
<!-- app -->
<link rel="stylesheet" type="text/css" href="${app}/home.css">
<!-- zTree 修改的样式-->
<link href="${app}/zTreeReset.css" rel="stylesheet" type="text/css"/>
<!-- 任务管理 css-->
<link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
<!--系统管理 css-->
<link rel="stylesheet" href="${app}/css/management.css" type="text/css">
<link rel="stylesheet" href="${app}/css/taskDispatch.css" type="text/css">
<!-- app -->
<link rel="stylesheet" href="${app}/css/zTreeReset.css" type="text/css" />
   
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${common}/All.js"></script>
<style type="text/css">
.headTitle{
	text-align: center;
	height: 50px;
	line-height: 50px;
	color: #262626;
	font-size: 18px;
}
</style>
	</HEAD>

	<body>
	<div class="breadClumbs" id="divTitle"> 系统管理&nbsp;&gt;&nbsp;${title}</div>
		<!--  <div class="headTitle" id="divTitle">${title}</div>-->
		<div id="main" style="">
			<div class="left" id="left" style="width:20%;border:1px solid #d4d4d4; OVERFLOW-Y: auto; OVERFLOW-X:auto;" data-options="region:'west'">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div class="right" id="right" style="width : 79%;">
				<div id="righttop">
				</div>
				<div id="rightbottom">
				</div>
			</div>
		</div>
	</body>
</html>

<script language="JavaScript">
//树菜单配置
var setting = {
	view: {
		showLine: false
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		onClick : onClick
	}
};

var menu_= ${menu};

function onClick(event, treeId, treeNode, clickFlag) {
	var level = treeNode.level;
	var href = treeNode.href;
	var id = treeNode.id;
	var name = treeNode.name;

	if (level == 0) {
		href = "";
	} else if (level == 1){
		href = "treeClickJumpPage.htm?operate=1&tasktype=" + id;
	} else if (level == 2){
		var industry = id.split("_");
		href = "treeClickJumpPage.htm?operate=2&industry=" + industry[0] + "&tasktype=" + industry[1];
	} else if (level == 3){
		href = "treeClickJumpPage.htm?operate=3&id=" + id;
	} else if (level > 3){
		if (treeNode.isParent) {
			href = "treeClickJumpPage.htm?operate=4&id=" + id;
		} else {
			href = "treeClickJumpPage.htm?operate=5&id=" + id;
		}
	}
	var iframe = "<iframe id='" + id + "' frameborder='0' src='" + href + "' style='width:100%;height:100%;'></iframe>";
	document.getElementById('rightbottom').innerHTML = iframe;
}
// 树 end

$(document).ready(function(){
	$("#left").height($(window).height() - $("#divTitle").outerHeight() - 10);
	$("#right").height($(window).height() - $("#divTitle").outerHeight() - 10);
	$("#right").width($(window).width() - $("#left").outerWidth() - 20);
	$("#rightbottom").height($(window).height() - $("#divTitle").outerHeight() - $("#righttop").outerHeight() - 10);
	$("#main").width($(window).width() - 20);
	
	var treeObj = $.fn.zTree.init($("#treeDemo"), setting, menu_);
	var nodes = treeObj.getNodes();
	treeObj.expandNode(nodes[0], true, false, false);
});
function closeLayer(){
	layer.closeAll('iframe');
	location.reload();
	}
	function layerIframe(types,href,title,width,height){
		  layer.open({
		  	  type: types,
		  	  title: title,
		  	  maxmin: true,
		  	  shadeClose: false, //点击遮罩关闭层
		  	  area : [width+'px' , height+'px'],
		  	  content: href
		  	  });
		  
}
</script>