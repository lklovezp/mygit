<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${app}/js.js"></script>
<script type="text/javascript" src="${app}/data.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css"/>
<script type="text/javascript" src="${common}/All.js"></script>
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<link href="${app}/resetCSS.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />
<!--zTree-->
<link rel="stylesheet" type="text/css" href="${app}/home.css">
<link rel="stylesheet" href="${app}/css/management.css" type="text/css">
<link rel="stylesheet" href="${app}/css/taskDispatch.css" type="text/css">
<link rel="stylesheet" href="${ztree}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<link rel="stylesheet" href="${app}/css/zTreeReset.css" type="text/css" />
<script type="text/javascript" src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/css/easyUIReset.css" rel="stylesheet" type="text/css"/>
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
		<div id="main">
			<div id="topadd">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="formtable">
					<tr>
						<td align="right">
							<input type="button" id="add" value="添加" align="right" class="input_btnColorlBlue mr15" style="margin:0px 0 10px 20px"/>
						</td>
					</tr>
				</table>
			</div>
			<div class="left" id="left" style="width:30%;border:1px solid #d4d4d4; OVERFLOW-Y: auto; OVERFLOW-X:auto;" data-options="region:'west'">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div class="right" id="right" style="width : 69%;"></div>
		</div>
	</body>
</html>

<script language="JavaScript">
//树菜单配置
var zTree_Menu = null, curMenu = null;
var setting = {
	view: {
		showLine: false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback: {
		onClick : onClick
	}
};
var menu_= ${menu};
//点击菜单事件
function onClick(event, treeId, treeNode, clickFlag) {
	var level = treeNode.level;
	var href = treeNode.href;
	var id = treeNode.id;
	var name = treeNode.name;
	
	var iframe = "<iframe id='" + id + "' frameborder='0' src='" + href + "' style='width:100%;height:100%;'></iframe>";
	document.getElementById('right').innerHTML = iframe;
}
// 树 end

$(document).ready(function(){
	var treeObj = $.fn.zTree.init($("#treeDemo"), setting, menu_);
	var nodes = treeObj.getNodes();
	treeObj.expandNode(nodes[0], true, true, true);

	$.ajaxSetup({cache:false});
	
	$("#left").height($(window).height() - $("#divTitle").outerHeight() - $("#topadd").outerHeight() - 10);
	$("#right").height($(window).height() - $("#divTitle").outerHeight() - $("#topadd").outerHeight() - 10);
	$("#right").width($(window).width() - $("#left").outerWidth() - 20);
	$("#main").width($(window).width() - 20);
});
$("#add").click(function(){
	layerIframe(2,"editTaskType.htm?pid=","添加子类型",600,550);
	//All.ShowModalWin("editTaskType.htm?pid=", "", 600, 400);
	//location.reload();
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