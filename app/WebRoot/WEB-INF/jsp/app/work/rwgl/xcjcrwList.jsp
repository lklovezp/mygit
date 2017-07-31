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
   <form id="queryForm" method="post" action="getXcjcrwList.json">
      <input type="hidden" id="fid" name="fid" value="${fid}" />
	  <input type="hidden" id="page" name="page" value="${page}" />
	  <input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
   <table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
         <tr>
           <th width="10%">任务名称</th>
           <td width="15%"><input type="text" class="y-text" id="rwmc" name="rwmc" /></td>
           <th width="10%">任务类型</th>
			<td width="15%"> <input class="y-text" type="text" id="tasktype" name="tasktype" /></td>
           <th width="10%">执法对象类型</th>
			<td width="40%">
				<input class="y-text" type="text" id="zfdxType" name="zfdxType" value="${zfdxType}" />
			</td>
			
         </tr>
         <tr>
	         <th width="10%">派发人	</th>
				<td><input type="hidden" id="pfrId" name="pfrId"/>
					<input class="y-text" type="text" id="pfr" readonly="readonly" />
					<a href="#" style="color:#3399CC;" id="selectpfr">选择人员</a>
			</td>
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
         <th width="10%">任务状态</th>
			<td><input class="y-text" type="text" id="rwzt" name="rwzt" /></td>
		 <th width="10%">要求完成时间</th>
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
<h1 id="title" class="h1_1 topMask">待办任务（<font color="red">${total}</font>条）</h1>
<div class="divContainer" id="rbblist" style=" width:100%; margin:0px auto 0px;">
    <table id="data" fit="true" ></table>
</div>


<script type="text/javascript">
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
//设置选择派发人
function setUserInfoPfr(id,name) {
	$("#pfrId").val(id);
	$("#pfr").val(name);
	jQuery().colorbox.close();
}
$(document).ready(function(){
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
		url:'taskTypeTreeCombo.json?markId=1'
	});
  //选择派发人
	$("#selectpfr").colorbox({iframe:true,width:"300", height:"400",href:"taskUserPubQuery.htm?all=true&notShowZj=false&methodname=setUserInfoPfr&multi=false&condition=0"});
    //数据表格初始化
    $('#data').datagrid({
				nowrap : true,
				striped : true,
				collapsible : true,
				singleSelect : true,
				remoteSort:false,
				fitColumns : true,
				pagination : true,
				rownumbers : true,
				url:'getXcjcrwList.json',
				onLoadSuccess:function(data){
      		        $(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
                },
                rowStyler: function(index,row){
			        if (row.isYQ==1){
			            return 'color:red;';
			        }
			    },
	            columns:[[
					{field:'dbworkName1',title:'任务名称',width:300},
					{field:'workNote1',title:'任务内容',halign:'center',align:'left',width:300},
					{field:'rwly',title:'任务来源',align:'center',width:100},
					{field:'jjcd',title:'紧急程度',align:'center',width:160},
					{field:'yqwcsx',title:'要求完成时限',align:'center',width:120},
					{field:'createby',title:'派发人',align:'center',width:80},
					{field:'zxrName',title:'主办人',align:'center',width:80},
					{field:'state',title:'任务状态',align:'center',width:80},
					{field:'operate',title:'操作',align:'center',width:260}
				]]
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

	//window.location.href="${ctx}/wf/process/flowChart?piId="+piId;
   // All.ShowModalWin("${ctx}/wf/process/flowChart?piId="+piId, '流程图',900,500);
}
//任务详情
function info(curObj){
	var href = 'taskDetail.htm?applyId='+curObj.id; 
  	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	parent.layerIframe(2,href,"任务详情",width,height);
    // All.ShowModalWin('taskDetail.htm?applyId='+curObj.id, '任务详情');
    // window.location.href='taskDetail.htm?applyId='+curObj.id;
     //refresh();
}

function actionOper(code,action,actionType,applyId,taskId,text){
	if(actionType=='AJAX'){
			$.post('${ctx}/'+action, {applyId:applyId,taskId:taskId}, function(json){
				$.messager.alert('提示',json.msg,'info',function(){
					if(json.state){
						$('#data').datagrid('reload');
					}
				});
			},'json');
	}else if(actionType == 'NEW_WIN_S'){
			$.colorbox({
				iframe:true,
				width:"50%", 
				height:"90%",
				href:'${ctx}/'+action+'&applyId='+applyId+'&taskId='+taskId
			});
	}else if(actionType == 'NEW_WIN_B'){
		   parent.$.colorbox({
				iframe:true,
				width:"80%", 
				height:"100%",
				href:'${ctx}/'+action+'&applyId='+applyId+'&taskId='+taskId,
				onClosed:function(){
				   $('#queryForm').submit();
				}
			});
	}else if(actionType == 'NEW_WIN_D'){
			//改为弹出对话框窗口
			var href = '${ctx}/'+action+'&applyId='+applyId+'&taskId='+taskId+'&lx=3'; 
		  	var width=screen.width * 0.9;
		  	var height=screen.height * 0.8-50;
		  //	parent.layerIframe(2,href,"待办任务",width,height);
		  	parent.layer.open({
			  	  type: 2,
			  	  title: "待办任务",
			  	  maxmin: true,
			  	  shadeClose: false, //点击遮罩关闭层
			  	  area : [width+'px' , height+'px'],
			  	  content: href,
			  	  end:function(){
			  		$('#data').datagrid('reload');
			  	  }
			  	  });
			 //window.location.href='${ctx}/'+action+'&applyId='+applyId+'&taskId='+taskId+'&lx=3';
			//All.ShowModalWin('${ctx}/'+action+'&applyId='+applyId+'&taskId='+taskId, text);
			//$('#queryForm').submit();
	}
}

    


  
    //数据表格自使用宽度
    $(window).resize(function(){
        $('#data').datagrid("resize");
		initH();
    });
</script>
</body>
</html>
