<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	 <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
        <!--jQuery-->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${app}/js.js"></script>
		<script type="text/javascript" src="${app}/data.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<script type="text/javascript" src="${common}/All.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<!--时间插件 my97-->
        <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
	</HEAD>

	<body>
		<div id="searchMask" class="searchMask"><p>点击查看更多查询条件</p></div>
            <div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;">
			<form id="queryForm" action="queryTaskDelList.json" method="post">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" />
				<table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
						<th width="10%" align="right">区域：</th>
						<td align="left" width="15%"><input class="y-text" id="areaid" name="areaid" type="text" value="${areaid}" />
						</td>
						<th width="10%" align="right">
							任务名称：
						</th>
						<td align="left" width="15%">
							<input class="y-text" type="text" id="name" name="name"/>
						</td>
						 <th width="10%" align="right">
							任务状态：
						</th>
						<td align="left" width="40%">
							<input class="y-text" type="text" id="rwzt" name="rwzt" />
						</td>
						</tr>
						<tr>
						<th width="10%" align="right">
						<a href="#" id="selectHander" class="b-link">派发人：</a>
						</th>
						<td align="left">
						    <input class="y-text" type="text" id="handername" name="handername" value="${handername}"/>
							<input class="y-text" type="hidden" id="hander" name="hander" value="${hander}"/>
						</td>
						<th width="10%">
						<a href="#" id="selectzbr" class="b-link">主办人：</th>
				       <td><input type="hidden" id="zbrId" name="zbrId"/>
					     <input class="y-text" type="text" id="zbr" readonly="readonly" />
			          </td>
						<th width="15%" align="right">
							要求完成时限：
						</th>
						<td  align="left">
						<input type="text" class="y-dateTime" id="endTimeFrom" name="endTimeFrom" value="${endTimeFrom }" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTimeTo\',{d:-1});}'})"/> 
						至 <input type="text" class="y-dateTime"  id="endTimeTo" name="endTimeTo" value="${endTimeTo }" onclick="WdatePicker({minDate:'#F{$dp.$D(\'endTimeFrom\',{d:1});}'})"/>
					  </td>
					</tr>
					<tr>
						<td colspan="8" align="center">
						<input type="submit" id="query"  class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
                        <input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
						</td>
					</tr>
				</table>
			</form>
			<div class="closeBtn">收起</div>
		</div>
		<div id="layer1" class="layer"></div>
         <h1 id="divTitle" class="h1_1 topMask">${title}</h1>
		 <div class="divContainer" id="rbblist" style=" width:96%; margin:10px auto 0px;">
			<table id="data" fit="true"></table>
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
////////////////////////////////////////////////////////////////////////////////////
function initH(){
	var hh=$(window).height() - $("#divTitle").outerHeight()-15;
	$("#rbblist").height(hh);
}
initH();
//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});


function setUserInfoDPfr(id, name){
	$("#hander").linkbox('setValue', {'id':id,'name':name});
	jQuery.colorbox.close();
}

$(document).ready(function() {
	$.ajaxSetup({cache:false});
	
	$("#infectlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight() - 0.5);
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	//区域
	$('#areaid').combobox({
		height:34,
		url : 'queryAreaComboWithCur.json',
		valueField : 'id',
		textField : 'name',
		onSelect: function (record) {//做人员区域的联动选择
             $("#selectHander").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?showBj=true&all=false&notShowZj=false&methodname=setValues&multi=false&areaid="+record.id});
        }
	});
	$("#selectHander").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?showBj=true&all=false&notShowZj=false&methodname=setValues&multi=false"});
	//选择主办人
	$("#selectzbr").colorbox({iframe:true,width:"300", height:"400",href:"taskUserPubQuery.htm?all=true&notShowZj=false&methodname=setUserInfoZbr&multi=false&condition=0"});
	//任务状态下拉框
    $('#rwzt').combobox({
    	height:34,
		url:'queryRwztCombo.json',
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
		fitColumns:true,
		url : 'queryTaskDelList.json?areaid='+$('#areaid').combotree("getValue"),
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id', hidden:true},
			{field:'name',title:'任务名称', halign:'center', width:100},
			{field:'source',title:'任务来源', align:'center', width:30},
			{field:'emergency',title:'紧急程度', align:'center', width:20},
			{field:'endTime',title:'要求完成时限', align:'center', width:40},
			{field:'hander',title:'派发人', align:'center', width:20},
			{field:'zxrName',title:'主办人',align:'center',width:20},
			{field:'state',title:'任务状态', align:'center', width:20},
			{field:'operate',title:'操作', align:'center', width:25}
		]]
	});
	initPager();
	
	
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
/*
function del(curObj){
	$.messager.confirm('任务删除', '确认删除此任务吗？一旦删除，无法恢复！', function(r){
		if (r){
			$.ajax({
				url: "removeTask.json?id="+curObj.id,
				success:function(data){
					refresh();
				}
			});
		}
	});
}*/
//删除
function del(curObj) {
	var index=layer.confirm('确定删除吗？一旦删除，无法恢复！', {
     	icon: 3, 
        title:'删除任务'
     }, function(index){
    	 $.ajax( {
				url : "removeTask.json?id="+curObj.id,
				success : function(data) {
					refresh();
					//parent.location.reload();
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}



function info(curObj){
	//All.ShowModalWin('taskDetail.htm?applyId='+curObj.id, '任务详情');
	parent.layer.open({
        type: 2,
        title: '任务详情',
        area: ['1100px', ($(window).height()+120)+'px'],
        content: 'taskDetail.htm?applyId='+curObj.id, 
        end: function () {
            refresh();
        }

    });
	
}

/**
 * 选择后回填数据
 */
function setValues(id,name){
	//$("#hander").linkbox("setValue", {id:id,name:name});
	$("#hander").val(id);
	$("#handername").val(name);
	jQuery().colorbox.close();
}
//设置选择主办人
function setUserInfoZbr(id,name) {
	$("#zbrId").val(id);
	$("#zbr").val(name);
	jQuery().colorbox.close();
}
</script>