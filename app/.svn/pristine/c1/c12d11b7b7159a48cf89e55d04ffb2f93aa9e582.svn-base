<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>附件信息列表</title>
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
<style type="text/css">
.panel-header{background-color: #cff1ff;}
.formtable, .formtable th, .formtable td{border-color:#dddddd; }

</style>
	</head>

	<body>
		<div class="h1_1" id="divTitle">
		执法对象附件信息
		</div>
		<form id="queryForm" action="" method="post">
			<div id="condition">
			${innerHtml}
			</div>
				<div class="panel-header" style="margin-top:10px;">
					<div class="panel-title">
						附件信息
						
					</div>
				</div>
				<div  id="infectlist">
					<table id="data" fit="true"></table>
				</div>
		</form>
		<iframe name="download" id="download" src="" style="display: none"></iframe>
	</body>
</html>

<script language="JavaScript">

$(document).ready(function(){
	$.ajaxSetup({cache:false}); ${innerJs}

	$("#questionContainer").width($(window).width());
		$("#infectlist").height($(window).height() -  $("#condition").outerHeight() - $("#divTitle").outerHeight() - 40);
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
				{field:'filetype',title:'附件类型', align:'left', halign:'center',width:80},
				{field:'filename',title:'附件名称', align:'left', halign:'center',width:120},
				{field:'filesize',title:'附件大小', align:'center', halign:'center',width:30},
				{field:'operate',title:'操作', align:'center', halign:'center',width:30}
			]]
		});
});

</script>