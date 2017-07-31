<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- jquery -->
		<script src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<script src="${jquery}/jquery.form.js"></script>
		<!-- tabletree4j -->
		<script src="${tabletree4j}/Core4j.js"></script>
		<script src="${tabletree4j}/TableTree4j.js"></script>
		<link rel="stylesheet" type="text/css"	href="${tabletree4j}/tabletree4j.css">
		<!-- colorbox -->
		<script type="text/javascript"	src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css"	href="${colorbox}/colorbox2.css">
		<!-- easyui -->
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<!-- app -->
		<script type="text/javascript" src="${common}/All.js"></script>
		<!-- app -->
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
   		<link href="${app}/common.css" rel="stylesheet" type="text/css"/>
	    <link href="${app}/css/random.css" rel="stylesheet" type="text/css"/>
	    <link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css"/>
	    <!--系统管理 css-->
	    <link rel="stylesheet" href="${app}/css/management.css" type="text/css">
	    <style type="text/css">
	    	.queryBlue {
			    width: 93px;
			    height: 36px;
			 }
	    </style>
	</HEAD>

	<body>
		<div   style="border: 1px; margin:10px auto 15px; " id="questionContainer">
			<form id="queryForm" action="dicQuery.json" method="post">
				<input type="hidden" id="type" name="type" value="${type}">
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" />
				<input class="y-text" type="text" id="name" name="name" />
			 	<input id="query" class="bTn btnbgAdd directory_comwidth" type="submit"	value="搜索">
				<input type="button" class="bTn btnbgAdd directory_comwidth" id="dicAdd" value="新建" />
				<a id="dicEdit" onclick="javascript:edit(this)" title="编辑"></a>
			</form>
		</div>
		<div id="diclist" class="divContainer">
			<table id="data" fit="true"></table>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
	$('#queryForm').submit(function() {
		$("#queryForm").ajaxSubmit( {
			success : function(data) {
				$('#data').datagrid('loadData', data)
			}
		});
		return false;
	});
	function edit(curObj){
		layer.open({
		       type:2,
		       title:'修改字典',
		       area:['500px','390px'],
		       shadeClose:false,
		       maxmin:true,
		       content:"editDic.htm?id=" + curObj.id
		     });
		//All.ShowModalWin("editDic.htm?id=" + curObj.id, "", 500, 300);
		//refresh();
	}
	$(document).ready(function() {
		$.ajaxSetup({cache:false});
		$("#diclist").height($(window).height() - $("#questionContainer").outerHeight()-25);
		$("#dicAdd").click(function(){
			layer.open({
			       type:2,
			       title:'新建字典',
			       area:['500px','360px'],
			       shadeClose:false,
			       maxmin:true,
			       content:"editDic.htm?type=${type}"
			     });
			//All.ShowModalWin("editDic.htm?type=${type}", "", 500, 300);
			//refresh();
		});
		
		$('#data').datagrid( {
			nowrap : false,
			striped : true,
			collapsible : true,
			fitColumns : true,
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			url : 'dicQuery.json?type=' + '${type}',
			columns : [ [ {
				field : 'code',
				title : '代码',
				align : 'center',
				width : 30
			}, {
				field : 'name',
				title : '名称',
				width : 100
			}, {
				field : 'note',
				title : '备注',
				width : 80
			},
			<c:if test="${type == '4'}">
				{
					field : 'mandatory',
					align : 'center',
					title : '是否必填',
					width : 20
				},
			</c:if>
			{
				field : 'orderby',
				align : 'center',
				title : '排序',
				width : 20
			},
			{
				field : 'operate',
				title : '操作',
				align : 'center',
				width : 30
			} ] ]
		});
		initPager()
	});

	function del(curObj) {
		var index=layer.confirm('确定要删除当前字典吗？', {
	     	icon: 3, 
	        title:'字典管理'
	     }, function(index){
	    	 $.ajax( {
					url : "removeDic.json?id=" + curObj.id,
					success : function(data) {
						if(data.state){
							$('#data').datagrid('reload');
						}else{
							$.messager.alert('提示:',data.msg);
						}
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
		$('#data').datagrid('reload');
	}
</SCRIPT>