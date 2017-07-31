<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>执法对象详情</title>
 <script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${common}/All.js"></script>
<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<!--jQuery-->
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<!--easyui-->
<link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
<!-- 任务管理 css-->
<link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
<style>
 .basicinfoTable,.basicinfoTable td{ border: 1px solid #d4d4d4;}
</style>
	</head>

	<body>
		<div class="h1_1" id="divTitle">
		<c:if test="${lawobj.lawobjtype == '01' }">
		工业污染源基本信息
		</c:if>
		<c:if test="${lawobj.lawobjtype == '02' }">
		建设项目基本信息
		</c:if>
		<c:if test="${lawobj.lawobjtype == '03' }">
		医院基本信息
		</c:if>
		<c:if test="${lawobj.lawobjtype == '04' }">
		锅炉基本信息
		</c:if>
		<c:if test="${lawobj.lawobjtype == '05' }">
		建筑工地基本信息
		</c:if>
		<c:if test="${lawobj.lawobjtype == '06' }">
		三产基本信息
		</c:if>
		<c:if test="${lawobj.lawobjtype == '07' }">
		畜禽养殖基本信息
		</c:if>
		<c:if test="${lawobj.lawobjtype == '08' }">
		服务业基本信息
		</c:if>
		<c:if test="${lawobj.lawobjtype == '09' }">
		饮食业基本信息
		</c:if>
		<c:if test="${lawobj.lawobjtype == '10' }">
		三产制造业基本信息
		</c:if>
		<c:if test="${lawobj.lawobjtype == '11' }">
		娱乐业基本信息
		</c:if>
		</div>
		<form id="queryForm" action="" method="post">
			<div id="condition">
			${innerHtml}
			</div>
			<c:if test="${lawobj.lawobjtype != '01' }">
			
			
			     <div class="panel-header" style="margin-top:10px;">
					<div class="panel-title">
						附件信息
					  <!--  	<a onclick="info();">查看全部附件</a> -->
					</div>
				</div>
				<div class="divContainer" id="infectlist" style="height:300px;">
					<table id="data" fit="true"></table>
				</div>
			</c:if>
		</form>
		<iframe name="download" id="download" src="" style="display: none"></iframe>
	</body>
</html>

<script language="JavaScript">

$(document).ready(function(){
	$.ajaxSetup({cache:false}); ${innerJs}

	$("#questionContainer").width($(window).width());
		//$("#infectlist").height($(window).height() -  $("#condition").outerHeight() - $("#divTitle").outerHeight() - 40);
		$("#infectlist").width($(window).width());
		//附件列表
		
		$('#data').datagrid({
			rownumbers: true,
			singleSelect: true,
			pagination:true,
			fitColumns:true,
			nowrap: false,
			url:'queryFileList.json?pid=${lawobj.id}&canDel=N',
			onLoadSuccess:function(data){
				$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
			},
			columns:[[
				{field:'id',hidden:true},
				{field:'filetype',title:'附件类型', align:'center', halign:'center',width:100},
				{field:'filename',title:'附件名称', align:'left', halign:'center',width:100},
				{field:'filesize',title:'附件大小', align:'center', halign:'center',width:30},
				{field:'operate',title:'操作', align:'center', halign:'center',width:30}
			]]
		});
});
//查看附件
function info(){
	//All.ShowModalWin('infoFj.htm?id='+'${lawobj.id}', '查看工业污染源');
	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	var title='查看工业污染源';
  	parent.layerIframe(2,'infoFj.htm?id='+'${lawobj.id}',title,width,height);
}
</script>