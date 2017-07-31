<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- jquery -->
		<script src="${jquery}/jquery.js"></script>
		<script src="${jquery}/jquery.form.js"></script>
		<!-- tabletree4j -->
		<script src="${tabletree4j}/Core4j.js"></script>
		<script src="${tabletree4j}/TableTree4j.js"></script>
		<link rel="stylesheet" type="text/css"
			href="${tabletree4j}/tabletree4j.css">
		<!-- colorbox -->
		<script type="text/javascript"
			src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<!-- easyui -->
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/plugins/jquery.pagination.js"></script>
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/default/pagination.css">
		<!-- app -->
	    <link rel="stylesheet" type="text/css" href="${app}/hnjz.css">
	</HEAD>

	<body>
		
			<form id="quryForm" method="post" action="queryWorkType.json">
				<div id="search" class="divContainer">
					<input class="i-text" type="text" id="name" name="name"
						value="${name}" />
					<span class="btn btn-ok"> <input type="submit" value="搜索">
					</span>
					<a id="workAdd" href="editWorkType.htm" class="btslink">新建</a>
				</div>
				<div class="divContainer">
					<div id="worldcupgird"></div>
				</div>
			</form>
		
		<p>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
//create and config tabletree object
function initTab(){
		return new Core4j.toolbox.TableTree4j({
		columns:[
				{isNodeClick:true,dataIndex:'name',width:'10%'},
				{dataIndex:'shjb',width:'10%',canSort:true},
				{dataIndex:'url',width:'20%',canSort:true},
				{dataIndex:'url2',width:'20%',canSort:true},
				{dataIndex:'zfdxlx',width:'15%',canSort:true},
				{dataIndex:'code',width:'10%',renderFunction:null},
				{dataIndex:'operate',width:'15%',canSort:false}
				],
		headers:[{
			  	columns:[
				{dataIndex:'name'},
				{dataIndex:'shjb'},
			    {dataIndex:'url'},
			    {dataIndex:'url2'},
			    {dataIndex:'zfdxlx'},
				{dataIndex:'code'},
				{dataIndex:'operate'}
				],
				dataObject:{name:'名称',shjb:'审核级别',url:'执行界面url',url2:'执行界面url2',zfdxlx:'执法对象类型',code:'Code',operate:'操作'},
				trAttributeNames:['classStyle','style'],
				trAttributeValueObject:{classStyle:'ejiaA1_bt',style:''}
			  }
			],
		treeMode:'gird',
		renderTo:'worldcupgird',
		useLine:true,
		useIcon:true,
		id:'myworldcupgirdtree',
		useCookie:true,
		themeName:'arrow',
		selectMode:'single'
	});
}
var fifaGirdTree = initTab();
function edit(curObj){
  	  $(curObj).colorbox({iframe:true, width:"500", height:"600",href:"editWorkType.htm?id="+curObj.id,title:"编辑功能菜单"});
}
function del(curObj){
$.messager.confirm('任务类型管理', '确定要删除当前任务类型吗？', function(r){
	if (r){
			$.ajax({
			  url: "delWorkType.json?id="+curObj.id,
			  success:function(data){
				$('#quryForm').submit();
			  }
			});
		}
	});
}

$('#quryForm').submit(function(){  
      $("#quryForm").ajaxSubmit({
     	  success: function(data){
     	      var treedata = eval('('+data.re+')');
	          fifaGirdTree.rebuildTreeByNodes(treedata,true);
	      }
	 });
     return false;  
});  
function ref(){
	jQuery().colorbox.close();
	$('#quryForm').submit();
}
$(document).ready(function(){
    fifaGirdTree.build(${re},true);
    $("#workAdd").colorbox({iframe:true, width:"500", height:"500"});
});
</SCRIPT>