<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登录详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- jquery -->
<script src="${jquery}/jquery.js"></script>
<script src="${jquery}/jquery.form.js"></script>
<!-- easyui -->
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"	href="${easyui}/themes/default/easyui.css">
<script type="text/javascript" src="${common}/All.js"></script>
<link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
    <link href="${app}/common.css" rel="stylesheet" type="text/css" />
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
    	.h1_1.topMask {
    		padding-top: 5px;
		}
    </style>
</head>

<body>
	<h1 id="title" class="h1_1 topMask">登录详情</h1>
	<div class="divContainer" id="rbblist" style=" width:100%; margin:0px auto 0px;">
	<form id="queryForm" action="queryLogTimesDetails.json" method="post">
			<input type="hidden" id="page" name="page" value="${page}" /> 
			<input type="hidden" id="bythemonth" name="bythemonth" value="${bythemonth}" /> 
			<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
		</form>
		<table id="data" fit="true"></table>
	</div>
</body>
</html>
<script language="JavaScript">
	$(document)
			.ready(
					function() {
						$("#rbblist").height($(window).height() - $("#title").outerHeight());
						 
						$('#data')
								.datagrid(
										{
											nowrap : false,
											striped : true,
											collapsible : true,
											singleSelect : true,
											remoteSort : false,
											pagination : true,
											rownumbers : true,
											fitColumns : true,
											url : 'queryLogTimesDetails.json?bythemonth=${bythemonth}',
											onLoadSuccess : function(data) {
												$(this).datagrid('doCellTip', {
													'max-width' : '300px',
													'delay' : 500
												});
											},
											columns : [ [ {
												field : 'id',
												hidden : true
											},{
												field : 'name',
												title : '登录人姓名',
												align : 'left',
												halign : 'center',
												width : 100
											}, {
												field : 'opertime',
												title : '登录日期',
												align : 'center',
												halign : 'center',
												width : 100
											} ] ]
										});
						initPager();
					});

	$("#J-from-reset").click(function() {
		$("#queryForm").form("reset");
	});

	function initPager() {
		var p = $('#data').datagrid('getPager');
		$(p).pagination({
			onSelectPage : function(pageNumber, pageSize) {
				$('#page').val(pageNumber);
				$('#pageSize').val(pageSize);
				$(this).pagination('loading');
				$('#queryForm').submit();
				$(this).pagination('loaded');
			}
		});
	}

	$('#queryForm').submit(function() {
		$("#queryForm").ajaxSubmit({
			success : function(data) {
				$('#data').datagrid('loadData', data)
			}
		});
		return false;
	});
	 
</script>