<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
         <!--jQuery-->
        <script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${app}/js.js"></script>
		<script type="text/javascript" src="${app}/data.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
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
		<!--时间插件 my97-->
        <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
	</head>
	<body>
		<div id="searchMask" class="searchMask"><p>点击查看更多查询条件</p></div>
		<div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;">
			<form id="queryForm" action="queryYiSendConsultList.json" method="post">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" /> 
				 <table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style=" margin:0 auto">
					<tr>
						<th width="120" align="right">
							主题：
						</th>
						<td>
							<input class="y-text" type="text" name="topic" id="topic"/>
						</td>
						<th width="120" align="right">
							收件人：
						</th>
						<td >
							<input type="hidden" id="recId" name="recId" value=""/>
							<input class="y-text" type="text" id="selectSend" name="selectSend" value=""/>
						</td>
						
					</tr>
					<tr>
						<th width="120" align="right">
							时间：
						</th>
						<td colspan="2">
							<input type="text" class="y-dateTime" id="startTime" name="startTime" value="${startTime }" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\',{d:-1});}'})"/> 
                                                                      至 <input type="text" class="y-dateTime"  id="endTime" name="endTime" value="${endTime }" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\',{d:1});}'})"/>

						</td>
					</tr>
					<tr>
						<td colspan="6" align="center">
						   <input type="submit" class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
                           <input type="reset" class="queryOrange" value="重　置"/>
						</td>
					</tr>
					
				</table>
			</form>
			<div class="closeBtn">收起</div>
		</div>
		<div id="layer1" class="layer"></div>
           <h1 id="divTitle" class="h1_1 topMask">${title }</h1>
          <div class="divContainer" id="rbblist" style=" width:100%; margin:0px auto 0px;">
         <table id="data" fit="true" ></table>
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
///////////////////////////////////////////////
function initH(){
	var hh=$(window).height() - $("#divTitle").outerHeight();
	$("#rbblist").height(hh);
}
initH();



$(document).ready(function(){
	
	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
		   $("#recId").val("");
		    var p = $('#data').datagrid('getPager');
		   $('#page').val($(p).pagination('options').pageNumber);
		   $('#pageSize').val($(p).pagination('options').pageSize);
	});
	
	$("#infectlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		nowrap:false,
		fitColumns : true,
		pagination:true,
		//pageSize : 10,//默认选择的分页是每页10行数据
		//pageList: [5,10,15,20],//可以设置每页记录条数的列表  
		url:'queryYiSendConsultList.json',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'recName',title:'收件人', align:"left", halign:'center',width:60},
			{field:'topic',title:'主题', align:"left", halign:'center',width:100},
			{field:'sendDate',title:'时间', align:"center", halign:'center',width:20},
			{field:'operate',title:'操作', align:"center", halign:'center',width:55}
		]],
		onDblClickRow: function(rowIndex, rowData){
			//All.ShowModalWin('gywryInfo.htm?id='+rowData.id, '查看邮件信息');
			$.ajax({
				  url: "findMailById.json?id="+rowData.id,
				  success:function(data){
					  var href = "sendMailInfo.htm?id="+rowData.id; 
					  parent.dkSelect(rowData.id,data.topic,href);
				  }
				});
		}
	});
	initPager();
	$('#queryForm').submit(function(){  
	    $("#queryForm").ajaxSubmit({
			success: function(data){
	   	      $('#data').datagrid('loadData',data)
		      }
		   	});
	   return false;  
	});
});





/*
function del(obj){
	$.messager.confirm('已发会商管理', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
			  url: "delYiSendMail.json?id="+obj.id,
			  success:function(data){
				 alert(data.msg);
				 refresh();
			  }
			});
		}
	});
}*/
//删除
function del(obj) {
	var index=layer.confirm('确定删除吗？', {
     	icon: 3, 
        title:'删除任务'
     }, function(index){
    	 $.ajax( {
				url : "delYiSendMail.json?id="+obj.id,
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


function info(obj){
	//All.ShowModalWin('recMailInfo.htm?id='+$(obj).attr("id"), '查看邮件');
	window.location.href='sendMailInfo.htm?id='+$(obj).attr("id");
	
}
function setUserPfr(id,name) {
	$("#recId").val(id);
	$("#selectSend").val(name);
	jQuery().colorbox.close();
}
//选择发件人
$("#selectSend").colorbox({
	iframe:true,
	width:"300", 
	height:"400",
	href:"queryUserTree.htm?methodname=setUserPfr&multi=false"
});

//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});

</script>