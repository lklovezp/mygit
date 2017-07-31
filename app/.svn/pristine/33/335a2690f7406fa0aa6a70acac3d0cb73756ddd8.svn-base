<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>信访反馈</title>
    <script type="text/javascript" src="${app}/js.js"></script>
	<script type="text/javascript" src="${app}/data.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<!-- colorbox -->
	<script type="text/javascript"	src="${colorbox}/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css"/>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
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
</head>
<body>
<div id="searchMask" class="searchMask"><p>点击查看更多查询条件</p></div>
<div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;">
   <form id="queryForm" method="post" action="xfdjQuery.json">
      <input type="hidden" id="fid" name="fid" value="${fid}" />
	  <input type="hidden" id="page" name="page" value="${page}" />
	  <input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
   <table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
         <tr>
			<th width="120">
				信访来源：
			</th>
			<td width="140">
				<input class="i-text" type="text" id="xfly" name="xfly"
					value="${xfly}" />
			</td>
			<th width="120" align="right">
				信访时间：
			</th>
			<td width="320" align="left">
				<input class="easyui-datebox" type="text" name="xfsj1" />
				至
				<input class="easyui-datebox" type="text" name="xfsj2" />
			</td>
			<!-- <th width="120">
				状态：
			</th>
			<td>
				<input class="i-text" type="text" id="isActive" name="isActive"/>
			</td> -->
		</tr>
		<tr>
           <td align="center" colspan="6">
            <input  id="query" type="submit" class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
            <input  id="J-from-reset" type="reset" class="queryOrange" value="重　置"/>
           </td>
         </tr>
   </table>
   </form>
   <div class="closeBtn">收起</div>
</div>
<div id="layer" class="layer"></div>
<h1 id="title" class="h1_1 topMask">信访反馈</h1>
<!-- <div id="oper" style="width:95%; margin:-23px  auto 0px;" class="t-r">
    <input type="button" class="bTn btnbgAdd directory_comwidth" id="Add" value="新建" />
</div> -->
<div class="divContainer" id="xfdjlist">
	<table id="data" fit="true"></table>
</div>
<p>
<iframe name="download" id="download" src="" style="display: none"></iframe>

<script type="text/javascript">
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

function initH(){
	var hh=$(window).height() - $("#title").outerHeight();
	$("#xfdjlist").height(hh);
}
initH();
	
$(document).ready(function() {
		$.ajaxSetup({cache:false});
		$("#xfdjlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
		$("#Add").click(function(){
			All.ShowModalWin("xfdjAdd.htm", "", 1100, 780);
			refresh();
		});
		//任务来源下拉框
		$('#xfly').combobox({
			url:'dicList.json?type=20',
			valueField:'id',
			textField:'name'
		});
		$('#data').datagrid( {
			nowrap : true,
			striped : true,
			collapsible : true,
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			fitColumns:true,
			url : 'xfdjQuery.json',
			onLoadSuccess : function(data) {
				$(this).datagrid('doCellTip', {
					'max-width' : '300px',
					'delay' : 500
				});
			},
			columns : [ [
				{field : 'xfbh', title : '信访编号', align : 'center', width : 50},
				{field : 'xfly', title : '信访来源', align : 'center', width : 40},
				{field : 'xfnr', align:'left', title : '信访内容', width : 100}, 
				{field : 'wrlx', title : '污染类型', width : 80}, 
				{field : 'xfsj', title : '信访时间', align : 'center', width : 40},
				{field : 'zt', title : '状态', align : 'center', width : 30},
				{field : 'operate', title : '操作', align : 'center', width : 130}
			] ]
		});
		initPager();
		$('#queryForm').submit(function(){
	        $("#queryForm").ajaxSubmit({
	       	  success: function(data){
	       	      $('#data').datagrid('loadData',data);
	    	      }
	    	 });
	       return false;  
    	});
	});	
	
$("#J-from-reset").click(function() {
		$("#queryForm").form("reset");
	});
	
	//编辑
	function edit(curObj) {
		window.location.href="xfdjAdd.htm?id=" + curObj.id;
		//All.ShowModalWin("xfdjAdd.htm?id=" + curObj.id, "", 1100, 780);
		//refresh();
	}
	//回访
	function hf(curObj) {
		$.ajax({
			url : "isShowHf.json?xfdjId=" + curObj.id,
			success : function(data) {
				if (data.isShowHf) {
					//All.ShowModalWin("xfhf.htm?xfdjId=" + curObj.id, "", 1000, 550);
					//refresh();
					parent.layer.open({
			            type: 2,
			            title: '回访',
			            area: ['1100px', ($(window).height()+120)+'px'],
			            content: "xfhf.htm?xfdjId=" + curObj.id, 
			            end: function () {
			                //location.reload();
			            	refresh();
			            }
			        });
				} else {
					$.messager.alert('操作提示:', '该信访任务还未派发，无法进行回访');
				}
			}
		});
	}
	//查看
	function info(curObj) {
		var href = 'xfdjInfo.htm?id=' + curObj.id;
	  	var width=screen.width * 0.8;
	  	var height=screen.height * 0.8-50;
	  	parent.layerIframe(2,href,"信访登记表基本信息",width,height);
		//window.location.href="xfdjInfo.htm?id=" + curObj.id;
		//refresh();
	}
	//查看办结单关联任务
	function chakan(curObj) {
		var href = 'xfdjrwList.htm?xfdjId=' + curObj.id;
	  	var width=screen.width * 0.8;
	  	var height=screen.height * 0.8-50;
	  	parent.layerIframe(2,href,"信访任务办理情况",width,height);
		//refresh();
	}
	//查看办结单
	function bjdInfo(curObj) {
		$('#download').attr('src','exportXfbjd.json?xfdjId='+curObj.id);
	}
	function del(curObj) {
		$.messager.confirm('信访登记管理', '确定要删除当前信访登记吗？', function(r) {
			if (r) {
				$.ajax( {
					url : "delXfdj.json?id=" + curObj.id,
					success : function(data) {
						if (data.state) {
							alert(data.msg);
							refresh();
						} else {
							$.messager.alert('删除信访登记:', data.msg);
						}
					}
				});
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
	
    //数据表格自使用宽度
    $(window).resize(function(){
        $('#data').datagrid("resize");
		initH();
    });
</script>
</body>
</html>