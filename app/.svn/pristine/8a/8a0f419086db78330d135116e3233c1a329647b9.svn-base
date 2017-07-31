<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>执法检查任务</title>
    <!--jQuery-->
	<script type="text/javascript" src="${jquery}/jquery.js"></script>
	<script type="text/javascript" src="${app}/js.js"></script>
	<script type="text/javascript" src="${app}/data.js"></script>
	<script type="text/javascript" src="${layer}/layer.js"></script>
	<script type="text/javascript" src="${common}/All.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <!--poshytip-->
    <link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
    <!--派发列表-->
    <script type="text/javascript" src="${poshytip}/jquery.poshytip.min.js"></script>
    <!--时间插件 my97-->
    <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
	<link href="${app}/resetCSS.css" rel="stylesheet" type="text/css" />
	<link href="${app}/css/taskcommon.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div  id="title" class="breadClumbs"> 任务&nbsp;&gt;&nbsp;${title}</div>
<div id ="searchdiv"class="searchWrap">
<form id="queryForm" method="post" action="getDbrwList.json">
  <div id="searchMask" class="searchMask">
    <p>
      	<input type="text" class="i-text" id="rwmc" name="rwmc"style="width:60%;"/>
      	<input type="submit" class="searchBtn" id="query" value="查　询"/>
      	<a  class="searchMore">更多查询条件</a>
     </p>
    <div id="searchForm" class="searchForm" style="padding-top:10px;">
	  <input type="hidden" id="fid" name="fid" value="${fid}" />
	  <input type="hidden" id="page" name="page" value="${page}" />
	  <input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
        <table class="fileTable"  border="0" cellpadding="0" cellspacing="0" >
          <tr>
			<th width="10%">任务来源</th>
			<td>
			   <input class="y-text" type="text" id="rwly" name="rwly" />
			</td>
			<th width="10%">任务状态</th>
			<td><input class="y-text" type="text" id="rwzt" name="rwzt" /></td>
         </tr>
         <tr>
		 <th width="10%">要求完成时间</th>
		    <td colspan="2">
			<input type="text" class="y-dateTime" id="gdsj_from" name="gdStarttime"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\'gdsj_to\',{d:-1});}'})"/> 
	                     至 <input type="text" class="y-dateTime"  id="gdsj_to" name="gdEndtime"  onclick="WdatePicker({minDate:'#F{$dp.$D(\'gdsj_from\',{d:1});}'})"/>
			</td>
         <th width="10%">派发时间</th>
			<td colspan="2">
	         <input type="text" class="y-dateTime" id="pfsj_from" name="pfStarttime"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\'pfsj_to\',{d:-1});}'})"/> 
	                   至 <input type="text" class="y-dateTime"  id="pfsj_to" name="pfEndtime"  onclick="WdatePicker({minDate:'#F{$dp.$D(\'pfsj_from\',{d:1});}'})"/>
            </td>
          </tr>
          <tr>
            <th>执行时间</th>
            <td colspan="3"><input type="text" style="width: 207px;" class="y-dateTime" id="startTime2" name="startTime2" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime2\',{d:-1});}'})">　 至 　
              <input type="text" style="width: 207px;" class="y-dateTime" id="endTime2" name="endTime2" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime2\',{d:1});}'})"></td>
          </tr>
        </table>
		</form>
    </div>
  </div>
</div>
<div id ="state" class="lawObjectWrap">
  <div class="state">
  	<div class="taskStateT">状态：</div>
 	<div class="taskStateC">
  	<p><a class="kaBtn_blue solid" onclick="getdpfrw()">待派发（10）</a> <span class="">待派发（20）</span><span>已修改完成时间（10）</span></p>
    <p><a class="kaBtn_green solid" onclick="getdbrw()">待办理（10）</a> <span class="">待办理（40）</span><span class=" ">逾期未办理（4）</span><span class=" ">待审核（2）</span></p>
    <p><a class="kaBtn_orange solid" onclick="getyblrw()">已办理（10）</a> <span class="">已办理（40）</span><span class=" ">已上报（4）</span><span class=" ">已归档（2）</span></p></div>
  </div>
</div>
<div class="divContainer" id="rbblist" style="margin:16px 80px 25px;">
	<div id="caozuo" class="caozuo clearfix">
    	<div class="shencha"><a class="kaBtn_orange big" onclick="shTask()">审核</a></div>
        <div class="banli">办理<a class="kaBtn_green big" id="banli" onclick="banliTask()">办理</a><a class="kaBtn_green big" onclick="Zp()">改派</a><a class="kaBtn_green big" onclick="Xp()">下派</a></div>
        <div class="paifa">派发<a class="kaBtn_blue big" onclick="createTask()">派发</a><a class="kaBtn_blue big" onclick="changeEndtimeTask()">修改完成时间</a><a class="kaBtn_blue big" onclick="Cp()">重派</a><a class="kaBtn_blue big" onclick="CxTask()">撤销</a></div>
   </div>
   <div class="dataDiv" id="dataDiv" style="padding:0px 25px 0px ;">
    <table id="data" fit="true" class="easyui-datagrid" style="width:100%;"></table>
    </div>
</div>
<script type="text/javascript">
var rowid;
var rwState;

//点击查询下拉效果
$(".searchMore").click(function(){
	$(this).toggleClass("up");
	$("#searchForm").toggleClass("show");
});
//任务阶段点击状态
$(".taskPhase .btn").click(function(){
	$(this).addClass("solid").siblings().removeClass("solid");
});

function initH(){
	var hh=$(window).height() - $("#state").outerHeight()-$("#searchdiv").outerHeight()-$("#title").outerHeight()-45;
	$("#rbblist").height(hh);
	var h1=hh-$("#caozuo").outerHeight();
	$("#dataDiv").height(h1);
}
initH();
//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});
//设置选择派发人
function setUserInfoPfr(id,name) {
	$("#pfrId").val(id);
	$("#pfr").val(name);
	jQuery().colorbox.close();
}
$(document).ready(function(){
	//任务来源下拉选择
	$('#rwly').combobox({
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
    $('#tasktype').combotree({
    	height:34,
		multiple:true,
		panelHeight:280,
		url:'taskTypeTreeCombo.json?markId='+'zfjc'
	});
   	//数据表格初始化
    $('#data').datagrid({
		nowrap : true,
		striped : true,
		collapsible : true,
		singleSelect : true,
		remoteSort:false,
		fitColumns : true,
		pagination : true,
		multiSort:true,
		rownumbers : true,
		url:'getDbrwList.json?tasktype='+'13',
        onLoadSuccess:function(data){
   		   $(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
        },
           	rowStyler: function(index,row){
	        if (row.isYQ==1){
	            return 'color:red;';
	        }
    	 },
           columns:[[
           	{field:'ids',hidden:true},
           	{field:'stateCode',hidden:true},
			{field:'dbworkName1',title:'任务名称',width:240,sortable:true},
			{field:'workNote1',title:'任务内容',halign:'center',align:'left',width:100,sortable:true},
			{field:'rwly',title:'任务来源',align:'center',width:100},
			{field:'jjcd',title:'紧急程度',align:'center',width:160},
			{field:'yqwcsx',title:'要求完成时限',align:'center',width:100},
			{field:'createby',title:'派发人',align:'center',width:80},
			{field:'state',title:'任务状态',align:'center',width:80}
		]],
		onDblClickRow:function(rowIndex,rowData){//双击某一行，查看详情
			var href = 'taskDetail.htm?applyId='+rowData.ids; 
  			var width=screen.width * 0.8;
  			var height=screen.height * 0.8-50;
  			parent.layerIframe(2,href,"任务详情",width,height);
		},
		onSelect:function(rowIndex,rowData){  
           rowid = rowData.ids;
           rwState = rowData.stateCode;
        },
		pagination:true,//是否添加底部的分页
		rownumbers:false//是否显示左侧的行序号
	});
	initPager();
	$('#queryForm').submit(function(){
      $("#queryForm").ajaxSubmit({
     	  success: function(data){
     	      $('#data').datagrid('loadData',data);
  	      }
  	 });
     return false;  
  });
});
//重置
$("#J-from-reset").click(function(){
	$("#queryForm").form("reset");
	//任务来源下拉框
	$('#rwly').combobox('setValues', '');
	//任务状态下拉框
	$('#rwzt').combobox('setValues', '');
	$("#pfrId").val("");
});

function flowChart(piId){
	var href = "${ctx}/wf/process/flowChart?piId="+piId; 
  	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	parent.layerIframe(2,href,"流程图",900,height);
}
//任务详情
function info(curObj){
	var href = 'taskDetail.htm?applyId='+curObj.id; 
  	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	parent.layerIframe(2,href,"任务详情",width,height);
}

//派发
function createTask() {
    if(rwState == "" || rwState == null){
    	if(rowid == null || rowid == ""){
    		parent.layer.open({
	            type: 2,
	            title: '信访任务派发',
	            area: ['1100px', ($(window).height()+50)+'px'],
	            content: 'xfdjAdd.htm?lx='+'xfts', 
	            end: function () {
	                refresh();
	            }
	        }); 
			//window.location.href="xfdjAdd.htm?lx="+"xfts";
		}else{
			parent.layer.open({
	            type: 2,
	            title: '任务派发',
	            area: ['1100px', ($(window).height()+50)+'px'],
	            content: 'pfPage.htm?lx='+'xfts'+'&applyId='+rowid,
	            end: function () {
	            	refresh();
	            }
	        });
			//window.location.href="pfPage.htm?applyId="+rowid+"&lx="+"xfts";
		}
    }else{
    	parent.layer.open({
            type: 2,
            title: '任务派发',
            area: ['1100px', ($(window).height()+50)+'px'],
            content: 'xfdjAdd.htm?lx='+'xfts',
            end: function () {
            	refresh();
            }
        });
    	//window.location.href="xfdjAdd.htm?lx="+"xfts";
    }
}

//执行办理页面
function banliTask() {
	if(rwState == "01" || rwState == "02" || rwState == "03" || rwState == "05" || rwState == "08"){
		if(rowid != null && rowid != ""){
		var index=layer.confirm('确定要办理当前任务吗？', {
     	icon: 3, 
        title:'任务办理'
     }, function(index){
    	 $.ajax({
				url : "js.json?applyId="+rowid,
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
						window.location.href="zxPage.htm?applyId="+rowid;
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
     });	
	//All.ShowModalWin('pfPage.htm', '任务派发');
	//refresh();
	}
	}
}

//任务延期
function changeEndtimeTask() {
	if(rwState == "01" || rwState == "02" || rwState == "03" || rwState == "04" || rwState == "05"){
		if(rowid != null && rowid != ""){
			var width=screen.width * 0.8;
	    	var height=screen.height * 0.8-50;
			parent.layer.open({
	            type: 2,
	            title: '任务延期',
	            maxmin: false,
				shadeClose: false, //点击遮罩关闭层
	            area: [width+'px' , height+'px'],
	            content: 'rwyq.htm?applyId='+rowid, 
	            end: function () {
	            	refresh();
	            }
	        });
		}
	}
}

//任务撤销
function CxTask() {
	if(rwState == "" || rwState == null){
		if(rowid != null && rowid != ""){
			var index=layer.confirm('确定要撤销当前任务吗？', {
	     	icon: 3, 
	        title:'任务删除'
	     }, function(index){
	    	 $.ajax({
					url : "delpf.json?applyId="+rowid,
					success : function(data) {
						if(data.state){
							var tishi=layer.alert(data.msg,{
						     	title:'信息提示',
						        icon:1,
						        shadeClose:true,
						     },
						     function(){
						        layer.close(tishi);
						        refresh();
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
	     });
		}
	}
}

//任务转派
function Zp() {
	if(rwState == "01" || rwState == "02" || rwState == "03"){
		if(rowid != null && rowid != ""){
			var width=screen.width * 0.8;
	    	var height=screen.height * 0.8-50;
			parent.layer.open({
	            type: 2,
	            title: '任务转派',
	            maxmin: false,
				shadeClose: false, //点击遮罩关闭层
	            area: [width+'px' , height+'px'],
	            content: 'zpPage.htm?applyId='+rowid, 
	            end: function () {
	            	refresh();
	            }
	        });
			//window.location.href="zpPage.htm?applyId="+rowid;
		}
	}
}

//任务重派
function Cp() {
	if(rwState == "01" || rwState == "02" || rwState == "04" || rwState == "05"){
		if(rowid != null && rowid != ""){
			var index=layer.confirm('确定要重派当前任务吗？', {
	     	icon: 3, 
	        title:'任务重派'
	     }, function(index){
	    	 var href='pfPage.htm?applyId='+rowid+'&isCp=1';
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
	}
}

//任务下派
function Xp() {
	if(rwState == "01" || rwState == "02" || rwState == "03"){
		if(rowid != null && rowid != ""){
			var width=screen.width * 0.8;
	    	var height=screen.height * 0.8-50;
			parent.layer.open({
	            type: 2,
	            title: '任务下派',
	            maxmin: false,
				shadeClose: false, //点击遮罩关闭层
	            area: [width+'px' , height+'px'],
	            content: 'xpPage.htm?applyId='+rowid, 
	            end: function () {
	            	refresh();
	            }
	        });
			//window.location.href="xpPage.htm?applyId="+rowid;
		}
	}
}

//任务审核
function shTask() {
	if(rwState == "06" || rwState == "07" || rwState == "08"){
		if(rowid != null && rowid != ""){
			var width=screen.width * 0.8;
	    	var height=screen.height * 0.8-50;
			parent.layer.open({
	            type: 2,
	            title: '任务审核',
	            maxmin: false,
				shadeClose: false, //点击遮罩关闭层
	            area: [width+'px' , height+'px'],
	            content: 'shPage?applyId='+rowid, 
	            end: function () {
	            	refresh();
	            }
	        });
			//window.location.href="shPage?applyId="+rowid;
		}
	}
}
</script>
</body>
</html>