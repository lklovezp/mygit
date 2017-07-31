<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
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
<script type="text/javascript" src="${layer}/layer.js"></script>
	</HEAD>

	<body>
		<div id="searchMask" class="searchMask"><p>点击查看更多查询条件</p></div>
		<div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;">
			<form id="queryForm" method="post" action="auditDrafterQuery.json">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" />
				<table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style=" margin:0 auto">
					<tr>
						<th width="100px;">
							拟稿人：
						</th>
						<td width="200px;">
							<input class="y-text"  type="text" id="drafterName" name="drafterName"/>
						</td>
						<th width="100px;">
							主题：
						</th>
						<td width="280px;">
							<input class="y-text" style="width:200px;" type="text" id="name" name="name"/>
						</td>
						<th width="100px;">
							稿件状态：
						</th>
						<td width="200px;">
							<input class="y-text"  type="text" id="state" name="state"/>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center">
							 <input type="submit" class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
                           <input type="reset" class="queryOrange" value="重　置"/>
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

	//查看
	function info(curObj) {
		//All.ShowModalWin('drafterInfo.htm?id=' + curObj.id, '稿件查看','1100px','600px');
		var height=screen.height*0.8-50;
		var width=screen.width*0.8;
		title='稿件查看';
		parent.layerIframe(2,'drafterInfo.htm?id=' + curObj.id,title,width,height);
		parent.href.localtion='auditDrafterList.htm';
	}
	//审批
	function audit(curObj) {
		//All.ShowModalWin('auditDrafter.htm?id=' + curObj.id, '稿件审批','1100px','600px');
		//var height=screen.height*0.8-50;
        //var width=screen.width*0.8;
		//title='稿件审批';
		//parent.layerIframe(2,'auditDrafter.htm?id=' + curObj.id,title,width,height);
	
	        //iframe层
	        parent.layer.open({
	            type: 2,
	            title: '稿件审批',
	            area: ['1100px', ($(window).height()+120)+'px'],
	            content: 'auditDrafter.htm?id=' + curObj.id, 
	            end: function () {
	                location.reload();
	            }

	        });
		
	}
	
	$('#queryForm').submit(function() {
		$("#queryForm").ajaxSubmit( {
			success : function(data) {
				$('#data').datagrid('loadData', data)
			}
		});
		return false;
	});
	
	$('#state').combobox({
		height:34,
		url:'stateList.json',
		valueField:'id',
		textField:'name'
	});

	$("#J-from-reset").click(function() {
		$("#queryForm").form("reset");
	});
	
	$(document).ready(function() {
		$.ajaxSetup({cache:false});
		$("#drafterlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
		//是否可用
		$('#isActive').combobox({
			data:[{'id':'Y','name':'是'},{'id':'N','name':'否'}],
			value : 'Y',
			editable:false,
			valueField:'id',
			textField:'name'
		});
		$('#data').datagrid( {
			nowrap : false,
			striped : true,
			collapsible : true,
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			fitColumns:true,
			url : 'auditDrafterQuery.json',
			onLoadSuccess : function(data) {
				$(this).datagrid('doCellTip', {
					'max-width' : '300px',
					'delay' : 500
				});
			},
			columns : [ [
				{field : 'drafterName', align:'center',title : '拟稿人', width : 40}, 
				{field : 'name', align:'left',title : '主题', width : 150}, 
				{field : 'submitDate', align:'center',title : '发送时间', width : 100}, 
				{field : 'state', align:'center',title : '稿件状态', width : 60}, 
				{field : 'operate', align:'center',title : '操作', align : 'center', width : 70}
			] ]
		});
		initPager();
	});
	
	//数据表格自使用宽度
	$(window).resize(function(){
	    $('#data').datagrid("resize");
		initH();
	});
</SCRIPT>