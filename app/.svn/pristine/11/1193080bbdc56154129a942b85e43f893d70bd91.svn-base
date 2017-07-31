<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- jquery -->
<script type="text/javascript" src="${app}/js.js"></script>
<script type="text/javascript" src="${app}/data.js"></script>
<!--jQuery-->
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<!-- colorbox -->
<script type="text/javascript"	src="${colorbox}/jquery.colorbox-min.js"></script>
<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css"/>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<!--poshytip-->
<link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<!--派发列表-->
<link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${poshytip}/jquery.poshytip.min.js"></script>
<script type="text/javascript" src="${common}/All.js"></script>
<!--时间插件 my97-->
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<link href="${app}/resetCSS.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/easyUIReset.css" rel="stylesheet" type="text/css"/>
	</HEAD>
	</HEAD>

	<body>
	<div id="title" class="breadClumbs"> 查询统计&nbsp;&gt;&nbsp;${title}</div>
		<div class="searchDiv" id="searchDiv" style="height: 65px;">
			<form id="queryForm" action="queryLoginLogList.json" method="post">
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
                 <table width="100%" class="noBorder" border="0" cellpadding="0" cellspacing="0">	
                 <tr height="72">
                      <td colspan="3" align="center">
                      	<input type="text" class="i-text" id="title" name="title"style="width:60%;" jzTips="请输入登录人或登录名或登录IP"/>
                      </td>
                      <td align="left">
                      	<input type="submit" class="input_btnImgBlue" id="query" value="查　询"/>
                      	<a class="moreSearch" id="moreSearch">更多查询条件</a>
                      </td>
                  </tr>
					<tr height="72">
						<th align="right">
							登录人姓名：
						</th>
						<td >
							<input class="i-text"  type="text" id="name" name="name" style="width:142px;"/>
						</td>
						<th align="right">
							登录名：
						</th>
						<td >
							<input class="i-text"  type="text" id="username" name="username" style="width:142px;"/>
						</td>
					</tr>
					<tr height="72">
						<th align="right">
							客户机IP：
						</th>
						<td >
							<input class="i-text"  type="text" id="loginip" name="loginip" style="width:142px;"/>
						</td>
						<th  align="right">
							登录类型：
						</th>
						<td >
							<input class="i-text"  type="text" id="logintype" name="logintype" style="width:154px;"/>
						</td>
				</table>
			</form>
		
		</div>
		 <div class="dataDiv" id="dataDiv" style="padding:20px 25px 5px ; height:216px;">
			<table id="data" fit="true"></table>
		</div>
	</body>
</html>

<script language="JavaScript">
$("#searchDiv").height("65px");
$("#moreSearch").click(function(){
	if($(this).hasClass("show")){//展开变折叠
		$(this).removeClass("show").text("展开查询条件");
		$("#searchDiv").height("65px");
	}else{//折叠变展开
		$(this).addClass("show").text("折叠查询条件");
		$("#searchDiv").height("auto");
	}
	
});
////////////////////////////////////////////////////////////////////////////////////
function initH(){
	var hh=$(window).height()-$("#title").outerHeight()-$("#searchDiv").outerHeight()-30;	
	$("#dataDiv").height((hh)+"px");
}
initH();
//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});

	$(document).ready(function() {
		
		$('#logintype').combobox({
			height:40,
			data:[{'id':'0','name':'后台'},{'id':'1','name':'终端'}],
			editable:false,
			valueField:'id',
			textField:'name'
		});

		$("#infectlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
		$("#infectlist").width($(window).width());
		$("#questionContainer").width($(window).width());

		$('#data').datagrid( {
			nowrap: false,
			striped: true,
			collapsible:true,
			singleSelect:true,
			remoteSort:false,
			pagination : true,
			rownumbers : true,
			fitColumns:true,
			url : 'queryLoginLogList.json',
			onLoadSuccess : function(data) {
				$(this).datagrid('doCellTip', {
					'max-width' : '300px',
					'delay' : 500
				});
			},
			columns : [ [ {
				field : 'id',
				hidden : true
			}, {
				field : 'name',
				title : '登录人姓名',
				align : 'left',
				halign : 'center',
				width : 100
			}, {
				field : 'username',
				title : '登录名',
				align : 'center',
				halign : 'center',
				width : 40
			}, {
				field : 'loginip',
				title : '客户机IP',
				align : 'center',
				halign : 'center',
				width : 40
			}, {
				field : 'logintype',
				title : '登录类型',
				align : 'center',
				halign : 'center',
				width : 30
			}, {
				field : 'opertime',
				title : '操作时间',
				align : 'center',
				halign : 'center',
				width : 50
			}, {
				field : 'result',
				title : '是否成功',
				align : 'center',
				halign : 'center',
				width : 30
			} ] ]
		});
		initPager();
	});

	$("#J-from-reset").click(function() {
		$("#queryForm").form("reset");
	});
	
	function initPager(){
		var p = $('#data').datagrid('getPager');
		$(p).pagination({
			onSelectPage:function(pageNumber, pageSize){
				$('#page').val(pageNumber);
				$('#pageSize').val(pageSize);
				$(this).pagination('loading');
				$('#queryForm').submit();
				$(this).pagination('loaded');
			}
		});
	}
	
	$('#queryForm').submit(function(){
		$("#queryForm").ajaxSubmit({
			success: function(data){
				$('#data').datagrid('loadData',data)
			}
		});
		return false;
	});
</script>