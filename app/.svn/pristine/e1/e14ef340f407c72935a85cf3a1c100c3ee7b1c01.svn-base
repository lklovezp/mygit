<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
    <link href="${app}/css/easyUIReset.css" rel="stylesheet" type="text/css"/>
    
    
</head>
<body>
<div class="breadClumbs"> 系统管理&nbsp;&gt;&nbsp;${title}</div>
<div id="searchMask" class="searchMask" style="top:30px;"><p>点击查看更多查询条件</p></div>
<div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;top:30px;">
  <form id="queryForm" action="queryIndustryList.json" method="post">
	<input type="hidden" id="fid" name="fid" value="${fid}" />
	<input type="hidden" id="page" name="page" value="${page}" />
	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
   <table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
         <tr>
           <th width="13%">行业名称</th>
           <td width="30.3%">
           <input class="y-text" type="text" value="${name}" id="name" name="name"/>
           </td>
           <th width="13%">执法对象类型</th>
		   <td width="30.3%">
			 <input class="y-text" type="text" id="lawobjtype" name="lawobjtype" value="${lawobjtype}"/>
		   </td>
           <th width="13%">是否可用</th>
		   <td width="30.3%">
			 <input class="y-text" type="text" id="isActive" name="isActive"/>
		   </td>
         </tr>
         <tr>
           <td align="center" colspan="6">
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
    <input type="button" class="bTn btnbgAdd directory_comwidth" id="new" value="新建" />
</div>
<div class="divContainer" id="userlist" style=" width:100%; margin:10px auto 0px;">
    <table id="data" fit="true" ></table>
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



function initH(){
	var hh=$(window).height() - $("#title").outerHeight()-$("#addForm").outerHeight()-3;
	$("#userlist").height(hh);
}
initH();
////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
	$(document).ready(function() {
		$('#lawobjtype').combotree({
			height:34,
			type:"post",
			url:'lawtypeTree.json'
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
			nowrap: false,
			striped: true,
			collapsible:true,
			singleSelect:true,
			remoteSort:false,
			pagination : true,
			rownumbers : true,
			fitColumns : true,
			url : 'queryIndustryList.json',
			onLoadSuccess : function(data) {
				$(this).datagrid('doCellTip', {
					'max-width' : '300px',
					'delay' : 500
				});
			},
			columns : [ [ 
				{field : 'id', hidden : true},
				{field : 'name', title : '行业名称', align : 'left', halign : 'center', width : 100},
				{field : 'lawobjtype', title : '执法对象类型', align : 'center', halign : 'center', width : 50},
				{field : 'isActive', title : '是否可用', align : 'center', halign : 'center', width : 30},
				{field : 'orderby', title : '排序', align : 'center', halign : 'center', width : 30},
				{field : 'operate', title : '操作', align : 'center', halign : 'center', width : 30}
			] ]
		});
		initPager();
	});
	// 新建
	$("#new").click(function() {
		layer.open({
		       type:2,
		       title:'新建行业',
		       area:['648px','480px'],
		       shadeClose:false,
		       maxmin:true,
		       content:"edtiIndustry.htm"
		     });
		//All.ShowModalWin('edtiIndustry.htm', '新建行业', 600, 400);
		//refresh();
	});

	$("#J-from-reset").click(function() {
		$("#queryForm").form("reset");
	});
	
	$('#queryForm').submit(function(){
		$("#queryForm").ajaxSubmit({
			success: function(data){
				$('#data').datagrid('loadData',data);
			}
		});
		return false;
	});

	function edit(curObj){
		layer.open({
		       type:2,
		       title:'新建行业',
		       area:['638px','480px'],
		       shadeClose:false,
		       maxmin:true,
		       content:'edtiIndustry.htm?id='+curObj.id
		     });
		//All.ShowModalWin('edtiIndustry.htm?id='+curObj.id, '编辑行业', 600, 400);
		//refresh();
	}

	function del(curObj){
		var index=layer.confirm('确定要删除当前行业吗？', {
	     	icon: 3, 
	        title:'行业管理'
	     }, function(index){
	    	 $.ajax( {
					url : "removeIndustry.json?id="+curObj.id,
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
	function closeLayer(){
		layer.closeAll('iframe');
		$('#data').datagrid("reload");
	}
</script>