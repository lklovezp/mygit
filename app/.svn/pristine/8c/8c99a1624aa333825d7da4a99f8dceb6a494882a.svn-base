<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
         <!--jQuery-->
        <script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${app}/js.js"></script>
		<script type="text/javascript" src="${app}/data.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<script type="text/javascript" src="${common}/All.js"></script>
		<!--时间插件 my97-->
        <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
	</HEAD>

	<body>
		<div id="searchMask" class="searchMask"><p>点击查看更多查询条件</p></div>
		<div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;">
			<form id="queryForm" method="post" action="drafterStatistics.json">
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" />
				 <table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style=" margin:0 auto">
					<tr>
						<th width="100px;">
							发送时间：
						</th>
						<td width="400px;">
							<input type="text" class="y-dateTime" id="startTime" name="startTime" value="${startTime }" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\',{d:-1});}'})"/> 
                                                                      至 <input type="text" class="y-dateTime"  id="endTime" name="endTime" value="${endTime }" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\',{d:1});}'})"/>
						</td>
						<th width="100px;">
							稿件状态：
						</th>
						<td width="200px;">
							<input class="y-text"  type="text" id="state" name="state" value="6"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="submit" class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
                           <input type="button" class="queryOrange" id="J-from-reset" value="重　置"/>
						</td>
					</tr>
				</table>
			</form>
			<div class="closeBtn">收起</div>
		</div>
		<div id="layer" class="layer"></div>
           <h1 id="divTitle" class="h1_1 topMask">${title }</h1>
          <div class="divContainer" id="rbblist" style=" width:100%; margin:0px auto 0px;">
         <table id="data" fit="true" ></table>
        </div>
		<p>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
var searchMask=$("#searchMask");
var searchForm=$("#searchForm");
var layer=$("#layer");
layer.hide();
searchForm.hide();
var hideSearchBtn=searchForm.find(".closeBtn");
function hideSearchForm(){
	searchForm.hide();
	layer.hide();
}
function showSearchForm(){
	searchForm.show();
	layer.show();
}
searchMask.bind("click",showSearchForm);
hideSearchBtn.bind("click",hideSearchForm);
///////////////////////////////////////////////
function initH(){
	var hh=$(window).height() - $("#divTitle").outerHeight();
	$("#rbblist").height(hh);
}
initH();
$(window).resize(function(){
	$('#data').datagrid("resize");
	initH();
	
});
	
	$('#queryForm').submit(function() {
		$("#queryForm").ajaxSubmit( {
			success : function(data) {
				$('#data').datagrid('loadData', data)
			}
		});
		return false;
	});

	$("#J-from-reset").click(function() {
		$("#queryForm").form("reset");
	});
	
	$("#qk").click(function() {
		$("#submitDate1").datebox("setValue","");
		$("#submitDate2").datebox("setValue","");
	});
	
	$(document).ready(function() {
		$.ajaxSetup({cache:false});
		$("#drafterlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
		$('#data').datagrid( {
			nowrap : false,
			striped : true,
			collapsible : true,
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			fitColumns:true,
			url : 'drafterStatistics.json',
			queryParams: {
		        submitDate1: $("#submitDate1").val(),
		        submitDate2: $("#submitDate2").val(),
		        state:$("#state").val(),
		    },
			onLoadSuccess : function(data) {
				$(this).datagrid('doCellTip', {
					'max-width' : '300px',
					'delay' : 500
				});
			},
			columns : [ [
				{field : 'orgname', align:'center',title : '部门', width : 40}, 
				{field : 'drafterName', align:'center',title : '拟稿人', width : 100}, 
				{field : 'number', align:'center',title : '稿件数', width : 60}, 
			] ]
		});
		initPager();
	});
$('#state').combobox({
	    height:34,
		url:'stateList.json',
		valueField:'id',
		textField:'name'
});
	

</SCRIPT>