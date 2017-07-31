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
		<div id="searchMask" class="searchMask" style="top:30px;"><p>点击查看更多查询条件</p></div>
		<div id="searchForm" class="searchForm" style="width: 100%; margin: 0 auto;top:30px;">
			<form id="queryForm" method="post" action="mobileQuery.json">
				<table class="queryTable" width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<th>
							名称：
						</th>
						<td>
							<input class="y-text"  type="text" id="name" name="name"/>
						</td>
						<th>
							是否可用：
						</th>
						<td>
							<input class="y-text"  type="text" id="isActive" name="isActive"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input id="query" type="submit" class="queryBlue" value="查询">
							<input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							
						</td>
					</tr>
				</table>
			</form>
			<div class="closeBtn">收起</div>
		</div>
		<div id="layer1" class="layer"></div>
		<h1 id="divTitle" class="h1_1 topMask" style="padding-top: 10px;">${title}</h1>
		<div style="width:95%; margin:-7px  auto 0px;" class="t-r">
			<span style="margin-right: 600px;">
				环形菜单时，排序的最后一个会显示到中间&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</span>
            <input type="button" class="bTn btnbgAdd directory_comwidth" id="functionAdd" value="新建" />
        </div>
		<div  class="divContainer" style="width: 100%;margin: 0 auto;margin-top: 10px;">
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
			{isNodeClick:true,dataIndex:'name',width:'40%'},
			{dataIndex:'description',width:'30%',canSort:true},
			{dataIndex:'orderby',width:'10%'},
			{dataIndex:'isActive',width:'10%'},
			{dataIndex:'operate',width:'10%',canSort:false}
			],
	headers:[{
		  	columns:[
			{dataIndex:'name'},
		    {dataIndex:'description'},
		    {dataIndex:'orderby'},
			{dataIndex:'isActive'},
			{dataIndex:'operate'}
			],
			dataObject:{name:'功能名称',description:'描述',orderby:'排序',isActive:'是否有效',operate:'操作'},
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
function edit(curObj){
	layer.open({
	       type:2,
	       title:'编辑功能菜单',
	       area:['700px','430px'],
	       shadeClose:false,
	       maxmin:true,
	       content:"editMobile.htm?id="+curObj.id
	     });
	//All.ShowModalWin("editMobile.htm?id="+curObj.id, "编辑功能菜单", 700, 500);
	ref();
}

function del(curObj){
	var index=layer.confirm('确定要删除当前手机功能吗？', {
     	icon: 3, 
        title:'终端功能管理'
     }, function(index){
    	 $.ajax( {
				url : "delMobile.json?id="+curObj.id,
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
     	      intiTips();
     	      var treedata = eval('('+data.re+')');
	          fifaGirdTree.rebuildTreeByNodes(treedata,true);
	      }
	 });
     return false;  
});  

$("#functionAdd").click(function(){
	layer.open({
	       type:2,
	       title:'添加功能菜单',
	       area:['700px','430px'],
	       shadeClose:false,
	       maxmin:true,
	       content:"editMobile.htm"
	     });
	//All.ShowModalWin("editMobile.htm", "添加功能菜单", 700, 500);
	ref();
});

$(document).ready(function(){
	fifaGirdTree.build(${re},true);

	$('#isActive').combobox({
		height:34,
		data:[{'id':'Y','name':'是'},{'id':'N','name':'否'}],
		value : 'Y',
		editable:false,
		valueField:'id',
		textField:'name'
	});

	$("#J-from-reset").click(function() {
		$("#queryForm").form("reset");
	});
});
function closeLayer(){
	layer.closeAll('iframe');
}
//数据表格自适应
$(window).resize(function () {
    $('.divContainer').datagrid("resize");
});
</SCRIPT>