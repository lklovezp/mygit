<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>待办任务</title>
    <link href="${app}/resetCSS.css" rel="stylesheet" type="text/css" />
    <link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />
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
    <style type="text/css">


</style>
</head>
<body>
<div id="title" class="breadClumbs"> 基础数据&nbsp;&gt;&nbsp;${title}</div>
<div class="searchDiv" id="searchDiv" style="height: 65px;">
   <form id="queryForm" method="post" action="roleQuery.json" >
	<input type="hidden" id="fid" name="fid" value="${fid}" />
	<input type="hidden" id="page" name="page" value="${page}" />
	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
   <table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style="margin:22 auto;">
         <tr align="center">
                            角色名称&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <input type="text" class="y-text" id="name" name="name" value="${name}" jzTips="请输入角色名称" /></td>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                
                             是否可用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		   <input class="y-text" type="text" id="isActive" name="isActive"/>
		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <input type="submit" id="query" class="input_btnImgBlue" value="查　询" onclick="hideSearchForm()"/>
         </tr>
   </table>
   </form>
</div>
<div id="addForm" style="width:95%; margin:-7px  auto 0px;" class="t-r">
    <input type="button" class="input_btnColorlBlue mr15" id="Add" value="新建" />
</div>
<div class="divContainer" id="rbblist" style=" width:100%; margin:10px auto 0px;">
    <table id="data" fit="true" ></table>
</div>
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
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


function initH(){
	var hh=$(window).height() - $("#title").outerHeight()-$("#searchDiv").outerHeight()-$("#addForm").outerHeight()-10;
	$("#rbblist").height(hh);
}
initH();
////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
	function edit(curObj) {
		layer.open({
		       type:2,
		       title:'新建角色',
		       area:['538px','420px'],
		       shadeClose:false,
		       maxmin:true,
		       content:"roleAdd.htm?id=" + curObj.id
		     });
		//All.ShowModalWin("roleAdd.htm?id=" + curObj.id, "", 600, 400);
		//refresh();
	}
	function del(curObj) {
		var index=layer.confirm('确定要删除当前角色吗？', {
	     	icon: 3, 
	        title:'角色管理'
	     }, function(index){
	    	 $.ajax( {
					url : "delRole.json?id=" + curObj.id,
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
	function htcz(obj){
		var arr = new Array();
		arr = $(obj).attr("id").split(",");
		parent.layer.open({
	        type: 2,
	        title: '后台操作设置',
	        area: ['1100px', '700px'],
	        content: 'permissionList.htm?name='+arr[1]+'&role='+arr[0], 
	        end: function () {
	            //location.reload();
	        	refresh();
	        }

	    });
		
	};
	function zdcz(obj){
		var arr = new Array();
		arr = $(obj).attr("id").split(",");
		parent.layer.open({
	        type: 2,
	        title: '后台操作设置',
	        area: ['1100px', '700px'],
	        content: 'mobileRight.htm?name='+arr[1]+'&role='+arr[0], 
	        end: function () {
	            //location.reload();
	        	refresh();
	        }

	    });
	};
	$(document).ready(function() {
		$.ajaxSetup({cache:false});
		$("#Add").click(function(){
			layer.open({
			       type:2,
			       title:'新建角色',
			       area:['538px','420px'],
			       shadeClose:false,
			       maxmin:true,
			       content:"roleAdd.htm"
			     });
			//All.ShowModalWin("roleAdd.htm", "", 600, 400);
			//refresh();
		});
		$('#isActive').combobox({
			height:34,
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
			url : 'roleQuery.json',
			onLoadSuccess : function(data) {
				$(this).datagrid('doCellTip', {
					'max-width' : '300px',
					'delay' : 500
				});
			},
			columns : [ [
				{field : 'name', title : '角色名称', width : 50}, 
				{field : 'note', title : '角色描述', width : 100}, 
				{field : 'isActive', title : '是否有效', align : 'center', width : 30}, 
				{field : 'operate', title : '操作', align : 'center', width : 150}
			] ]
		});
		initPager();
	});
	function closeLayer(){
		layer.closeAll('iframe');
		$('#data').datagrid("reload");
	}
</SCRIPT>