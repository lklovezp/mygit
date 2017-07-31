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
	<div class="breadClumbs"> 系统管理&nbsp;&gt;&nbsp;${title}</div>
		<div id="searchMask" class="searchMask" style="top:30px"><p>点击查看更多查询条件</p></div>
			<div id="searchForm" class="searchForm" style="width: 100%; margin: 0 auto;top:30px">
				<form id="queryForm" method="post" action="areaQuery.json">
				<table class="queryTable" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<th width="150">
							区域名称：
						</th>
						<td colspan="">
							<input class="y-text" type="text" id="name" name="name"
								value="${name}" jzTips="请输入区域名称" />
						</td>
						<th width="150">
							是否可用：
						</th>
						<td>
							<input class="y-text" type="text" id="isActive" name="isActive"/>
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
            <input type="button" class="bTn btnbgAdd directory_comwidth" id="areaAdd" value="新建" />
        	</div>
			<div class="divContainer" style="width: 100%;margin: 10px auto 0px ;">
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
var fifaGirdTree=new Core4j.toolbox.TableTree4j({
	columns:[
		{isNodeClick:true,dataIndex:'name',width:'20%',canSort:true},
		{dataIndex:'code',width:'20%'},
		{dataIndex:'ip',width:'30%',canSort:true},
		{dataIndex:'orderby',width:'10%',canSort:true},
		{dataIndex:'isActive',width:'10%',canSort:true},
		{dataIndex:'operate',width:'10%',canSort:false}
	],
	headers:[{
		columns:[
			{dataIndex:'name'},
			{dataIndex:'code'},
			{dataIndex:'ip'},
			{dataIndex:'orderby'},
			{dataIndex:'isActive'},
			{dataIndex:'operate'}
		],
		dataObject:{id:'id',name:'区域名称',code:'区域编号',ip:'服务地址',orderby:'排序',isActive:'是否可用',operate:'操作'},
		trAttributeNames:['classStyle','style'],
		trAttributeValueObject:{classStyle:'ejiaA1_bt',style:''}
	}],
	treeMode:'gird',
	renderTo:'worldcupgird',
	useLine:true,
	useIcon:true,
	id:'myworldcupgirdtree',
	useCookie:true,
	themeName:'arrow',
	selectMode:'single'
});
function edit(curObj){
	layer.open({
	       type:2,
	       title:'修改区域',
	       area:['900px','420px'],
	       shadeClose:false,
	       maxmin:true,
	       content:"editArea.htm?id="+curObj.id
	     });
	$("#queryForm").form("reset");
	ref();
}
function del(curObj){
	var index=layer.confirm('确定要删除当前区域吗？', {
     	icon: 3, 
        title:'区域管理'
     }, function(index){
    	 $.ajax( {
				url : "removeArea.json?id="+curObj.id,
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

function ref(){
	$('#queryForm').submit();
}
$('#queryForm').submit(function(){  
	$("#queryForm").ajaxSubmit({
		success: function(data){
			var treedata = eval('('+data.re+')');
			fifaGirdTree.rebuildTreeByNodes(treedata,true);
		}
	});
	return false;  
});

$("#J-from-reset").click(function() {
	$("#queryForm").form("reset");
});

$(document).ready(function(){
	fifaGirdTree.build(${re},true);
	$("#areaAdd").click(function(){
		layer.open({
		       type:2,
		       title:'新建区域',
		       area:['900px','420px'],
		       shadeClose:false,
		       maxmin:true,
		       content:"editArea.htm"
		     });
		//All.ShowModalWin("editArea.htm", "", 600, 450);
		$("#queryForm").form("reset");
		ref();
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
</SCRIPT>