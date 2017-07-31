<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
		<script type="text/javascript" src="${poshytip}/jquery.poshytip.min.js"></script>
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
	    <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
	    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
	    <link href="${app}/common.css" rel="stylesheet" type="text/css" />
	    <style>
	    .dataTable th{text-align: right;}
	    </style>
	</HEAD>

	<body>
	<div class="breadClumbs"> 系统管理&nbsp;&gt;&nbsp;${title}</div>
		<div id="searchMask" class="searchMask" style="top:30px;"><p>点击查看更多查询条件</p></div>
        <div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;top:30px;">
			<form id="queryForm" action="queryOperLogList.json" method="post">
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
				<table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
						<th>
							操作时间：
						</th>
						<td>
						  <input type="text" class="y-dateTime" id="czsjFrom" name="czsjFrom" value="${czsjFrom}" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'czsjTo\',{d:-1});}'})"/> 
                                                            至 <input type="text" class="y-dateTime"  id="czsjTo" name="czsjTo" value="${czsjTo}" onclick="WdatePicker({minDate:'#F{$dp.$D(\'czsjFrom\',{d:1});}'})"/>
						</td>
						<th>
							<a href="#" style="color:#3399CC;" id="selectCzrname">操作人：</a>
						</th>
						<td>
						    <input class="y-text" style="width:194px" type="text" id="czr" name="czr"/>
							<input class="y-text" style="width:194px" type="hidden" id="czrName" name="czrName"/>
						</td>
					</tr>
					<tr>
						<th>
							操作类型：
						</th>
						<td>
							<input class="y-text" style="width:206px" type="text" id="operateType" name="operateType"/>
						</td>
						<th>
							描述：
						</th>
						<td>
							<textarea class="y-text" style="width:206px" type="text" id="note" name="note"></textarea>
						</td>
					</tr>
						<td colspan="4" align="center">
							<input type="submit" id="query"  class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
                            <input type="button" id="J-form-reset" onClick="xs();" class="queryOrange" value="重　置"/>
						</td>
					</tr>
				</table>
			</form>
		<div class="closeBtn">收起</div>
		</div>
	  <div id="layer1" class="layer"></div>
            <h1 id="divTitle" class="h1_1 topMask" style="padding-top: 10px;">${title}</h1>
		 <div class="divContainer" id="rbblist" style=" width:100%; margin:10px auto 0px;">
			<table id="data" fit="true"></table>
		</div>
	</body>
</html>

<script language="JavaScript">
var searchMask=$("#searchMask");
var searchForm=$("#searchForm");
var layer1=$("#layer1");
layer1.hide();
searchForm.hide();
var hideSearchBtn=searchForm.find(".closeBtn");
function hideSearchForm(){
	searchForm.hide();
	layer1.hide();
}
function showSearchForm(){
	searchForm.show();
	layer1.show();
}
searchMask.bind("click",showSearchForm);
hideSearchBtn.bind("click",hideSearchForm);
////////////////////////////////////////////////////////////////////////////////////
function initH(){
	var hh=$(window).height() - $("#divTitle").outerHeight()-10;
	$("#rbblist").height(hh);
}
initH();
//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});
	var date;
	var pre;
	var from;
	var to;
	function setUserInfoCzr(id,name) {
		//$("#czrName").linkbox("setValue", {id:id,name:name});
		$("#czrName").val(id);
	    $("#czr").val(name);
		jQuery().colorbox.close();
	}
	
	$(document).ready(function() {

		$("#infectlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight() - 0.5);
		$("#infectlist").width($(window).width());
		$("#questionContainer").width($(window).width());

		$('#operateType').combobox({
			height:34,
			url : 'operateTypeList.json',
			editable:false,
			valueField:'id',
			textField:'name'
		});

		date = getCurTime().substring(0, 10);
		pre = getPreMonth(date).substring(0, 10);
		from = pre;
		to = date;
		$("#czsjTo").val( date);
		$("#czsjFrom").val(pre);
		
		$("#selectCzrname").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?all=true&notShowZj=false&methodname=setUserInfoCzr&multi=false"});
		
		$('#data').datagrid( {
			nowrap: false,
			striped: true,
			collapsible:true,
			singleSelect:true,
			remoteSort:false,
			pagination : true,
			rownumbers : true,
			fitColumns:true,
			url : 'queryOperLogList.json?czsjTo='+$("#czsjTo").val()+'&czsjFrom='+$("#czsjFrom").val(),
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
				field : 'czsj',
				title : '操作时间',
				align : 'center',
				halign : 'center',
				width : 20
			}, {
				field : 'operateType',
				title : '操作类型',
				align : 'center',
				halign : 'center',
				width : 15
			}, {
				field : 'czr',
				title : '操作人',
				align : 'center',
				halign : 'center',
				width : 20
			}, {
				field : 'note',
				title : '描述',
				align : 'left',
				halign : 'center',
				width : 100
			} ] ]
		});
		initPager();
	});

	function xs(){
		$("#queryForm").form("reset");
		//$("#czrName").linkbox("setValue", {id:'',name:''});
		$("#czsjTo").val(date);
		$("#czsjFrom").val(pre);
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