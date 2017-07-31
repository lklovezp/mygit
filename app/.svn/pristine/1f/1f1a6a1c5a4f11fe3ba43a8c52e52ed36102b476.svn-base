<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>待办任务</title>
    
    <script type="text/javascript" src="${app}/js.js"></script>
	<script type="text/javascript" src="${app}/data.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
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
<div class="breadClumbs"> 系统管理&nbsp;&gt;&nbsp;${title}</div>
<div id="searchMask" class="searchMask" style="top:30px"><p>点击查看更多查询条件</p></div>
<div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;top:30px">
   <form id="queryForm" method="post" action="serverQuery.json">
      <input type="hidden" id="fid" name="fid" value="${fid}" />
		<input type="hidden" id="page" name="page" value="${page}" />
		<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
   <table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
         <tr>
           <th width="10%">服务器</th>
           <td width="15%"><input type="text" class="y-text" id="name" name="name" value="${name}" jzTips="请输入服务器名称或地址" /></td>
           <th width="10%">是否可用</th>
		   <td width="15%">
			 <input class="y-text" type="text" id="isActive" name="isActive"/>
		   </td>
         </tr>
         <tr>
           <td align="center" colspan="4">
            <input type="submit" id="query" class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
            <input type="reset" id="J-from-reset" class="queryOrange" value="重　置"/>
           </td>
         </tr>
   </table>
   </form>
   <div class="closeBtn">收起</div>
</div>
<div id="layer1" class="layer"></div>
<h1 id="title" class="h1_1 topMask" style="padding-top: 10px;">${title}</h1>
<div id="addForm" style="width:95%; margin:-7px  auto 0px;" class="t-r">
    <input type="button" class="bTn btnbgAdd directory_comwidth" id="Add" value="新建" />
</div>
<div class="divContainer" id="rbblist" style=" width:100%; margin:10px auto 0px;">
    <table id="test" fit="true" ></table>
</div>
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
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



function initH(){
	var hh=$(window).height() - $("#title").outerHeight()-$("#addForm").outerHeight()-3;
	$("#rbblist").height(hh);
}
initH();
////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
	function edit(curObj) {
		layer.open({
		       type:2,
		       title:'新建服务器',
		       area:['538px','350px'],
		       shadeClose:false,
		       maxmin:true,
		       content:"serverAdd.htm?id=" + curObj.id
		});
		//All.ShowModalWin("serverAdd.htm?id=" + curObj.id, "", 600, 400);
		$("#queryForm").form("reset");
	}
	function del(curObj) {
		var index=layer.confirm('确定删除吗？', {
	     	icon: 3, 
	        title:'服务器管理'
	     }, function(index){
	    	 $.ajax( {
					url : "removeServer.json?id=" + curObj.id,
					success : function(data) {
						  var tishi=layer.alert(data.msg,{
						     	title:'信息提示',
						        icon:1,
						        shadeClose:true,
						     },
						     function(){
						    	$('#queryForm').submit();
						        layer.close(tishi);
						     }
						     );

						
					}
				});
	        layer.close(index);
	     },function(index){
	        layer.close(index);
	     }
	     );

	}
	$('#query').click(function() {
		$('#page').val("1");
	});
	$('#queryForm').submit(function() {
		$("#queryForm").ajaxSubmit( {
			success : function(data) {
				$('#test').datagrid('loadData', data)
				initPager();
			}
		});
		return false;
	});

	$("#J-from-reset").click(function() {
		$("#queryForm").form("reset");
	});
	
	function initPager() {
		var p = $('#test').datagrid('getPager');
		$(p).pagination( {
			onSelectPage : function(pageNumber, pageSize) {
				$('#page').val(pageNumber);
				$('#pageSize').val(pageSize);
				$(this).pagination('loading');
				$('#queryForm').submit();
				$(this).pagination('loaded');
			}
		});
	}
	$(document).ready(function() {
		$('#isActive').combobox({
			height:34,
			data:[{'id':'Y','name':'是'},{'id':'N','name':'否'}],
			value : 'Y',
			editable:false,
			valueField:'id',
			textField:'name'
		});
		$("#Add").click(function() {
			 layer.open({
			       type:2,
			       title:'新建服务器',
			       area:['538px','350px'],
			       shadeClose:false,
			       maxmin:true,
			       content:"serverAdd.htm"
			     });
			//All.ShowModalWin("serverAdd.htm", "", 600, 400);
			$("#queryForm").form("reset");
		});
		$('#test').datagrid( {
			nowrap : false,
			striped : true,
			collapsible : true,
			singleSelect : true,
			fitColumns : true,
			pagination : true,
			rownumbers : true,
			url : 'serverQuery.json',
			onLoadSuccess : function(data) {
				$(this).datagrid('doCellTip', {
					'max-width' : '300px',
					'delay' : 500
				});
			},
			columns : [ [ {
				field : 'name',
				title : '服务器名称',
				width : 100
			}, {
				field : 'ip',
				title : '服务地址',
				width : 100
			}, {
				field : 'operate',
				title : '操作',
				align : 'center',
				width : 100
			} ] ]
		});
		initPager()
	});
	function closeLayer(){
		layer.closeAll('iframe');
		$('#test').datagrid("reload");
	}
</SCRIPT>