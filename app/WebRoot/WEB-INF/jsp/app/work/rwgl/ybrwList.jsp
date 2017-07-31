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
<div id="searchMask" class="searchMask"><p>点击查看更多查询条件</p></div>
<div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;">
   <form id="queryForm" method="post" action="getYbrwList.json">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
   <table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
         <tr>
           <th width="10%">任务名称</th>
           <td width="15%"><input type="text" class="y-text" id="rwmc" name="rwmc" value="${rwmc}"/></td>
           <th width="10%"><a href="#" style="color:#3399CC;" id="selectpfr">派发人</a></th>
				<td><input type="hidden" id="pfrId" name="pfrId"/>
					<input class="y-text" type="text" id="pfr" readonly="readonly" />
			</td>
           <th width="12%">执法对象类型</th>
			<td width="40%">
				<input class="y-text" type="text" id="zfdxType" name="zfdxType" value="${zfdxType}" />
			</td>
			
         </tr>
         <tr>
	        <th width="10%">任务类型</th>
			<td width="15%"> <input class="y-text" type="text" id="tasktype" name="tasktype" /></td>
			<th width="10%">任务来源	</th>
			<td>
			   <input class="y-text" type="text" id="rwly" name="rwly" />
			</td>
			<th width="10%">派发时间</th>
			<td>
	         <input type="text" class="y-dateTime" id="pfsj_from" name="pfStarttime"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\'pfsj_to\',{d:-1});}'})"/> 
	                   至 <input type="text" class="y-dateTime"  id="pfsj_to" name="pfEndtime"  onclick="WdatePicker({minDate:'#F{$dp.$D(\'pfsj_from\',{d:1});}'})"/>
            </td>
         </tr>
         <tr>
         <th width="10%"><a href="#" style="color:#3399CC;" id="selectzbr">办理人</a>	</th>
				<td><input type="hidden" id="zbrId" name="zbrId"/>
					<input class="y-text" type="text" id="zbr" readonly="readonly" />
			</td>
		 <th width="10%">归档时间</th>
		    <td colspan="3">
			<input type="text" class="y-dateTime" id="gdsj_from" name="gdStarttime"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\'gdsj_to\',{d:-1});}'})"/> 
	                     至 <input type="text" class="y-dateTime"  id="gdsj_to" name="gdEndtime"  onclick="WdatePicker({minDate:'#F{$dp.$D(\'gdsj_from\',{d:1});}'})"/>
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
<h1 id="title" class="h1_1 topMask">已办任务</h1>
<div class="divContainer" id="rbblist" style=" width:100%; margin:0px auto 0px;">
    <table id="data" fit="true" ></table>
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
	var hh=$(window).height() - $("#title").outerHeight();
	$("#rbblist").height(hh);
}
initH();
//撤销
function cx(curObj){
	var index=layer.confirm('确定要撤销当前任务吗？', {
     	icon: 3, 
        title:'任务删除'
     }, function(index){
    	 $.ajax( {
				url : "delpf.json?applyId="+curObj.id,
				success : function(data) {
					if(data.state){
						var tishi=layer.alert(data.msg,{
					     	title:'信息提示',
					        icon:1,
					        shadeClose:true,
					     },
					     function(){
					    	 refresh();
					        layer.close(tishi);
					     }
					     );
						
					}else{
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
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}
//重派
function cp(curObj,taskId){
	var index=layer.confirm('确定要重派当前任务吗？', {
     	icon: 3, 
        title:'任务重派'
     }, function(index){
    	 var href='pfPage.htm?applyId='+curObj.id+'&taskId='+taskId+'&isCp=1';
    	 var width=screen.width * 0.8;
    	 var height=screen.height * 0.8-50;
		 parent.layer.open({
			  	  type: 2,
			  	  title: "任务重派",
			  	  maxmin: false,
			  	  shadeClose: false, //点击遮罩关闭层
			  	  area : [width+'px' , height+'px'],
			  	  content: href,
			  		end: function () {
	            	refresh();
	           	 }
			  	  
		});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}

//任务延期
function rwyq(curObj){
		parent.layer.open({
            type: 2,
            title: '任务延期',
            area: ['1100px', ($(window).height()+120)+'px'],
            content: 'rwyq.htm?applyId='+curObj.id, 
            end: function () {
            	refresh();
            }
        });
}

function flowChart(piId){
	var href = "${ctx}/wf/process/flowChart?piId="+piId; 
  	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	parent.layerIframe(2,href,"流程图",900,height);
   // All.ShowModalWin("${ctx}/wf/process/flowChart?piId="+piId, '流程图',900,470);
}

//查看
function info(curObj){
	var href = 'taskDetail.htm?applyId='+curObj.id; 
  	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	parent.layerIframe(2,href,"任务详情",width,height);
    // refresh();
}

//设置选择派发人
function setUserInfoPfr(id,name) {
	$("#pfrId").val(id);
	$("#pfr").val(name);
	jQuery().colorbox.close();
}

//设置选择主办人
function setUserInfoZbr(id,name) {
	$("#zbrId").val(id);
	$("#zbr").val(name);
	jQuery().colorbox.close();
}

$('#queryForm').submit(function(){
      $("#queryForm").ajaxSubmit({
     	  success: function(data){
     	      $('#data').datagrid('loadData',data)
	      }
	 });
     return false;  
});
$(document).ready(function(){
	//选择派发人
	$("#selectpfr").colorbox({iframe:true,width:"300", height:"400",href:"taskUserPubQuery.htm?all=true&notShowZj=false&methodname=setUserInfoPfr&multi=false&condition=0"});
	//选择主办人
	$("#selectzbr").colorbox({iframe:true,width:"300", height:"400",href:"taskUserPubQuery.htm?all=true&notShowZj=false&methodname=setUserInfoZbr&multi=false&condition=0"});
	$("#ybrwlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
	
	//任务来源下拉选择
	$('#rwly').combobox( {
		height:34,
		url:'dicList.json?type=1',
		method: 'get',
		valueField : 'id',
		textField : 'name'
	});
	//任务状态下拉框
    $('#rwzt').combobox({
    	height:34,
		url:'queryRwztCombo.json',
		valueField:'id',
		textField:'name'
	});
	//执法对象类型下拉框
	$('#zfdxType').combobox({
		height:34,
		url:'dicList.json?type=5',
		valueField:'id',
		textField:'name'
	});
	//任务类型
    $('#tasktype').combotree({
    	height:34,
		multiple:true,
		panelHeight:280,
		url:'taskTypeTreeCombo.json'
	});
	
	$('#data').datagrid({
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		remoteSort:false,
		fitColumns : true,
		pagination : true,
		rownumbers : true,
		url:'getYbrwList.json',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'dbworkName1',title:'任务名称',width:130,sortable:true},
			{field:'rwlx1',title:'任务类型',align:'center',width:50,sortable:true},
			{field:'zfdxlx',title:'执法对象类型',align:'center',width:53},
			{field:'rwly',title:'任务来源',align:'center',width:40},
			{field:'jjcd',title:'紧急程度',align:'center',width:40},
			{field:'pfsj',title:'派发时间',align:'center',width:50},
			{field:'yqwcsx',title:'要求完成时限',align:'center',width:53},
			{field:'createby',title:'派发人',align:'center',width:30},
			{field:'zxrName',title:'主办人',align:'center',width:30},
			{field:'state',title:'任务状态',align:'center',width:40},
			{field:'operate',title:'操作',align:'center',width:80}
		]]
	});
    initPager()
});

// 重置
$("#J-from-reset").click(function(){
	$("#queryForm").form("reset");
	//任务来源下拉框
	$('#rwly').combobox('setValues', '');
	//任务状态下拉框
	$('#rwzt').combobox('setValues', '');
	$("#pfrId").val("");
});

</SCRIPT>