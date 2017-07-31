<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${app}/data.js"></script>
<!--jQuery-->
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<!-- colorbox -->
<script type="text/javascript"	src="${colorbox}/jquery.colorbox-min.js"></script>
<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css"/>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<!--poshytip-->
<link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<!--派发列表-->
<link rel="stylesheet" href="${ztree}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<link rel="stylesheet" href="${app}/css/zTreeReset.css" type="text/css" />
<script type="text/javascript" src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
<link href="${app}/css/easyUIReset.css" rel="stylesheet" type="text/css"/>
<link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${poshytip}/jquery.poshytip.min.js"></script>
<script type="text/javascript" src="${common}/All.js"></script>
<!--时间插件 my97-->
<script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/resetCSS.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />
</head>

	<body>
	<div id="title" class="breadClumbs"> 查询统计&nbsp;&gt;&nbsp;${title}</div>
        <div class="searchDiv" id="searchDiv" style="height: 65px;">
			<form id="queryForm" action="queryComplaintList.json" method="post">
			<input type="hidden" id="fid" name="fid" value="${fid}" />
			<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" /> 
				<table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
					  <th width="11.3%" align="right">
							单位名称：
						</th>
						<td >
							<input class="y-text" type="text" name="lawobjname" id="lawobjname"/>
						</td>
						<th   align="right">
							投诉时间：
						</th>
						<td  colspan="4">
							<input type="text" class="y-dateTime" id="starttime" name="starttime" value="${startTime }" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\',{d:-1});}'})"/> 
                                                                      至 <input type="text" class="y-dateTime"  id="endtime" name="endtime" value="${endTime }" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\',{d:1});}'})"/>
							<input type="submit" id="query"  class="input_btnImgBlue" value="查　询" onclick="hideSearchForm()"/>
                            <a class="moreSearch" id="moreSearch">更多查询条件</a>
						</td>
					</tr>
					
					<tr height="72">
						<th  align="right">
							执法对象类型：
						</th>
						<td  width="22%">
							<input class="y-text" type="text" id="lawobjtypeid" name="lawobjtypeid" />
						</td>
						
						<th  align="right">
							地址：
						</th>
						<td >
							<input class="y-text" type="text" name="lawobjaddress" />
						</td>
				
					    <th  align="right" >
							状态：
						</th>
						<td>
							<input class="y-text" type="text" id="zt" name="isActive" value="Y"/>
						</td>
						
					</tr>
					
                        
					
				</table>
			</form>
		</div>
			<div id="adddiv" style="width:95%; margin:-7px  auto 0px;" class="t-r">
            <input type="button" class="input_btnColorlBlue" id="xinjian" value="新建" />
           </div>
		 <div class="divContainer" id="rbblist" style=" width:95%; margin:10px auto 0px;">
			<table id="data" fit="true"></table>
		</div>
	</body>
</html>

<script language="JavaScript">
$("#searchDiv").height("65px");
$("#moreSearch").click(function(){
	if($(this).hasClass("show")){//展开变折叠
		$(this).removeClass("show").text("展开查询条件");
		$("#searchDiv").height("65px");
	}else{//折叠变展开
		$(this).addClass("show").text("折叠查询条件");
		$("#searchDiv").height("auto");
	}
	
});
////////////////////////////////////////////////////////////////////////////////////
function initH(){
	var hh=$(window).height() - $("#title").outerHeight()-$("#searchDiv").outerHeight()-$("#adddiv").outerHeight()-10;
	$("#rbblist").height(hh);
}
initH();
//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});

$(document).ready(function(){

	$('#zt').combobox({
		height:34,
		url:'ztList.json',
		valueField:'id',
		textField:'name'
	});
	
	$('#lawobjtypeid').combotree({height:34, url:'lawtypeTree.json'});
	/*执法对象类型
	$('#lawobjtypeid').combobox({
		height:34,
		url:'',
		valueField:'id',
		textField:'name'
	});*/
	
	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
		   $("#zt").combobox('setValue','Y');
		    var p = $('#data').datagrid('getPager');
		   $('#page').val($(p).pagination('options').pageNumber);
		   $('#pageSize').val($(p).pagination('options').pageSize);
	});
	
	$("#infectlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight()-8);
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		nowrap:false,
		fitColumns : true,
		pagination:true,
		url:'queryComplaintList.json?isActive=Y',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'tssj',title:'投诉时间', align:"center", halign:'center',width:50},
			{field:'dwmc',title:'单位名称', align:"left", halign:'center',width:100},
			{field:'tsyy',title:'投诉原因', align:"left", halign:'center',width:100},
			{field:'tsr',title:'投诉人', align:"center", halign:'center',width:30},
			{field:'tsrlxdh',title:'投诉人联系电话', align:"center", halign:'center',width:50},
		    {field:'isActive',title:'状态', align:"center", halign:'center',width:20},
			{field:'operate',title:'操作', align:"center", halign:'center',width:50}
		]]
	});
	initPager();

});

function initPager(){
    var p = $('#data').datagrid('getPager');
	$(p).pagination({
				onSelectPage:function(pageNumber, pageSize){
				    $('#page').val(pageNumber);
				    $('#pageSize').val(pageSize);
					$(this).pagination('loading');
					$('#queryForm').submit();
					$(this).pagination('loaded');
				}
	});
}
$('#queryForm').submit(function(){  
	if(checkDate()){
	   	 $("#queryForm").ajaxSubmit({
	   	  success: function(data){
	   	      intiTips();
	   	      $('#data').datagrid('loadData',data)
		          initPager();
		      }
		 });
	}
   return false;  
});

//校验查询条件开始时间不能大于结束时间
function checkDate(){
	var starttime = $("input[name='starttime']").val();
	var endtime = $("input[name='endtime']").val();
	if(starttime != ''&&endtime != '') {
		var start = parseInt(starttime.replace(new RegExp('-', 'gm'), ''));
		var end = parseInt(endtime.replace(new RegExp('-', 'gm'), ''));
		if(start > end) {
			alert("开始时间不能大于截止时间！");
			return false;
		}
	}
	return true;
}
$("#xinjian").click(function(){
	//All.ShowModalWin('complaintEdit.htm', '新建投诉信息');
	//$('#queryForm').submit();
	parent.layer.open({
        type: 2,
        title: '新建投诉信息',
        area: ['1100px', '600px'],
        content: 'complaintEdit.htm', 
        end: function () {
            //location.reload();
            refresh();
        }

    });
});

function edit(obj){
	//All.ShowModalWin('complaintEdit.htm?id='+$(obj).attr("id"), '查看工业污染源');
	//$('#queryForm').submit();
	//var width=screen.width * 0.8;
  	//var height=screen.height * 0.8-50;
  	//var title='编辑投诉信息';
  	//parent.layerIframe(2,'complaintEdit.htm?id='+$(obj).attr("id"),title,width,height);
	parent.layer.open({
        type: 2,
        title: '编辑投诉信息',
        area: ['1100px', '600px'],
        content: 'complaintEdit.htm?id='+$(obj).attr("id"), 
        end: function () {
            //location.reload();
            refresh();
        }

    });
}
/*
function del(obj){
	$.messager.confirm('投诉信息管理', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
			  url: "deleteComplaint.json?id="+obj.id,
			  success:function(data){
				 alert(data.msg);
				 $('#queryForm').submit();
			  }
			});
		}
	});
}*/
function del(obj) {
	var index=layer.confirm('确定删除吗？', {
     	icon: 3, 
        title:'删除任务'
     }, function(index){
    	 $.ajax( {
				url : "deleteComplaint.json?id="+obj.id,
				success : function(data) {
					refresh();
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}

$("#daoru").click(function(){
	All.ShowModalWin('../../comman/import.html', '工业污染源导入');
	$('#queryForm').submit();
});

function info(obj){
	//All.ShowModalWin('complaintInfo.htm?id='+$(obj).attr("id"), '查看投诉信息');
	//$('#queryForm').submit();
	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	var title='查看投诉信息';
  	parent.layerIframe(2,'complaintInfo.htm?id='+$(obj).attr("id"),title,width,height);
}
</script>