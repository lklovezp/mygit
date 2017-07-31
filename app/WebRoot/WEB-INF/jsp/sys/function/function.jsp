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
		<script type="text/javascript" src="${easyui}/plugins/jquery.pagination.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/pagination.css">
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
	<div class="breadClumbs"> 系统管理&nbsp;&gt;&nbsp;${title}</div>
		<div id="searchMask" class="searchMask" style="top:30px"><p>点击查看更多查询条件</p></div>
		<div id="searchForm" class="searchForm" style="width: 100%; margin: 0 auto;top:30px">
			<form id="queryForm" method="post" action="functionQuery.json">
				<table class="queryTable" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<th >
							名称：
						</th>
						<td>
							<input class="y-text" style="width:200px" type="text" id="name" name="name"/>
						</td>
						<th >
							是否可用：
						</th>
						<td>
							<input class="y-text" style="width:200px" type="text" id="isActive" name="isActive"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="submit" id="query"  class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
                             <input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
						</td>
					</tr>
				</table>
			</form>
			<div class="closeBtn">收起</div>
		</div>
		<div id="layer1" class="layer"></div>
		<h1 id="divTitle" class="h1_1 topMask" style="padding-top: 10px;">${title}</h1>
		<div style="width:95%; margin:-7px  auto 0px;" class="t-r">
            <input type="button" class="bTn btnbgAdd directory_comwidth" id="functionAdd" value="新建" />
        </div>
		<div class="divContainer" style="width: 100%;margin: 0 auto;margin-top: 10px;">
			<div id="worldcupgird"></div>
		</div>
	</body>
</HTML>
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
//create and config tabletree object
function initTab(){
		return new Core4j.toolbox.TableTree4j({
		columns:[
				{isNodeClick:true,dataIndex:'title',width:'20%'},
				{dataIndex:'link',width:'20%',canSort:true},
				{dataIndex:'note',width:'30%'},
				{dataIndex:'order',width:'10%'},
				{dataIndex:'isActive',width:'10%'},
				{width:'10%',canSort:false,renderFunction:operateRenderFunction}
				],
		headers:[{
				columns:[
				{dataIndex:'title'},
				{dataIndex:'link'},
				{dataIndex:'note'},
				{dataIndex:'order'},
				{dataIndex:'isActive'},
				{dataIndex:'operate'}
				],
				dataObject:{title:'功能名称',link:'链接',note:'备注',order:'排序',isActive:'是否可用',operate:'操作'},
				trAttributeNames: ['classStyle', 'style'],
	            trAttributeValueObject: {classStyle: 'ejiaA1_bt', style: ''}
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
//infoObj {dataValue:,node:,tabletreeObj:,rowObj:,rowIndex:,container:,columnIndex:}
function operateRenderFunction(infoObj){
	var re = "<a href='#' style='color: #0088CC;' onclick=edit(this) id="+infoObj.node.id+" >编辑</a>  ";
	re += "<a href='#' style='color: #0088CC;' onclick=del(this) id="+infoObj.node.id+" >删除</a>";
	return re;
}
function edit(curObj){
	layer.open({
	       type:2,
	       title:'编辑功能菜单',
	       area:['700px','580px'],
	       shadeClose:false,
	       maxmin:true,
	       content:"functionEdit.htm?id="+curObj.id
	     });
	//All.ShowModalWin(, "编辑功能菜单", 700, 520);
	//ref();
}
function del(curObj){
	var index=layer.confirm('确定要删除当前菜单吗？', {
     	icon: 3, 
        title:'后台功能管理'
     }, function(index){
    	 $.ajax( {
				url : "functionDel.json?id="+curObj.id,
				success : function(data) {
					if(data.state){
						$('#queryForm').submit();
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

$('#queryForm').submit(function(){  
      $("#queryForm").ajaxSubmit({
     	  success: function(data){
     	       intiTips();
     	      var treedata = eval('('+data.re+')');
	          fifaGirdTree.rebuildTreeByNodes(treedata,true);
	      }
	 });
     return false;  
});  
function ref(){
	$('#queryForm').submit();
}

$(document).ready(function(){
	fifaGirdTree.build(${re},true);
	$("#functionAdd").click(function(){
		layer.open({
		       type:2,
		       title:'编辑功能菜单',
		       area:['700px','580px'],
		       shadeClose:false,
		       maxmin:true,
		       content:"functionEdit.htm"
		     });
		//All.ShowModalWin("functionEdit.htm", "编辑功能菜单", 700, 520);
		//ref();
	});

	$("#J-from-reset").click(function() {
		$("#queryForm").form("reset");
	});
	
	$('#isActive').combobox({
		height:34,
		data:[{'id':'Y','name':'是'},{'id':'N','name':'否'}],
		value : 'Y',
		editable:false,
		valueField:'id',
		textField:'name'
	});
});
function closeLayer(){
	layer.closeAll('iframe');
	$('#queryForm').submit();
}
//数据表格自适应
$(window).resize(function () {
    $('.divContainer').datagrid("resize");
});
</SCRIPT>