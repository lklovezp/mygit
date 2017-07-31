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
		<link rel="stylesheet" type="text/css" href="${tabletree4j}/tabletree4j.css">
		<!-- colorbox -->
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<!-- easyui -->
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<!-- app -->
		<!--css-->
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
   		<link href="${app}/common.css" rel="stylesheet" type="text/css"/>
	    <link href="${app}/css/random.css" rel="stylesheet" type="text/css"/>
	    <link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css"/>
	    <!--系统管理 css-->
	    <link rel="stylesheet" href="${app}/css/management.css" type="text/css">
		<script type="text/javascript" src="${common}/All.js"></script>
		<style type="text/css">
        input[type="checkbox"]{
            margin-left: 35px;
            margin-right: 5px;
        }
		.ejiaA1_bt{ background:#CFF1FF;}
    	</style>
	</HEAD>

	<body>
	<div class="breadClumbs" id="divTitle"> 系统管理&nbsp;&gt;&nbsp;${name}后台权限设置</div>
		<!--  <div id="divTitle"><h1 class="h1_1">${name}后台权限设置</h1></div>-->
		
		<div id="search" class="divContainer" style="width: 100%; margin: 0 auto">
			<form id="queryForm" method="post" action="saveQuxanxian.json">
				<input type="hidden" id="role" name="role" value="${role}"/>
				<table class="queryTable" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<input class="bTn directory_reset directory_comwidth" type="submit" value="保存"/>
							<input type="button" class="bTn system_authorbg directory_comwidth" id="ref" value="刷新权限内存数据"/>
						</td>
						<td align="right">	
							<input type="button" class="bTn directory_reset directory_comwidth" id="selectAll" value="全选"/>
							<input type="button" class="bTn directory_save directory_comwidth" id="cancelAll" value="取消全选"/>
						</td>
					</tr>
				</table>
				<div class="divContainer">
					<div id="worldcupgird"></div>
				</div>
			</form>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
var data = ${re};
//create and config tabletree object
var fifaGirdTree=new Core4j.toolbox.TableTree4j({
	columns:[
			{isNodeClick:true,dataIndex:'title',width:'20%'},
			{dataIndex:'ops',width:'65%'},
			{dataIndex:'operate',width:'15%',canSort:false}
			],
	headers:[{
		  	columns:[
			{dataIndex:'name'},
		    {dataIndex:'ops'},
			{dataIndex:'operate'}
			],
			dataObject:{name:'功能名称',ops:'操作名称',operate:'操作'},
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

$('#queryForm').submit(function(){  
      $("#queryForm").ajaxSubmit({
     	  success: function(data){
	         var tishi=layer.alert(data.msg,{
			     	title:'信息提示',
			        icon:1,
			        shadeClose:true,
			     },
			     function(){
			    	parent.closeLayer();
			        layer.close(tishi);
			     }
			     );
	      }
	 });
     return false;  
});  

$(document).ready(function(){
	$.ajaxSetup({cache:false});
	fifaGirdTree.build(${re},true);

	$("#ref").click(function(){
		$.ajax({
			url: "refQuxanxian.json",
			success:function(data){
				var tishi=layer.alert(data.msg,{
			     	title:'信息提示',
			        icon:1,
			        shadeClose:true,
			     },
			     function(){
			        layer.close(tishi);
			     }
			     );
			}
		});
	});
	
	/*$('#role').combobox({
		height:34,
		url:'queryAllRole.json',
		valueField:'id',
		textField:'name',
		async:false,
		onSelect:function(){
			var val = $('#role').combobox('getValue');
			location.href="permissionList.htm?title=${title}&role="+val;
		}
	});*/

	$("#selectAll").click(function(){
		var checkbox = $("input[type='checkbox']");
		checkbox.attr("checked", true);
	})

	$("#cancelAll").click(function(){
		var checkbox = $("input[type='checkbox']");
		checkbox.attr("checked", false);
	})
});
</SCRIPT>