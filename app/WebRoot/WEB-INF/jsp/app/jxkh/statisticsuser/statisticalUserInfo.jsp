<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<!--jQuery-->
<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<!--poshytip-->
<link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.queryTable th,.queryTable td{border:1px solid #dddddd; }
</style>
	</head>

	<body>
		<div class="h1_1" id="divTitle">${title }</div>
		<div class="divContainer" id="questionContainer" style="width:90%;">
		<form id="queryForm" action="" method="post">
				<input type="hidden" id="type" name="type" value="${type }"/>
				<input type="hidden" id="userid" name="userid" value="${userid }"/>
				<input type="hidden" id="username" name="username" value="${username }"/>
				<input type="hidden" id="areaid" name="areaid" value="${areaid }"/>
				<input type="hidden" id="areaname" name="areaname" value="${areaname }"/>
				<input type="hidden" id="orgname" name="orgname" value="${orgname }"/>
				<input type="hidden" id="tasktypeid" name="tasktypeid" value="${tasktypeid }"/>
				<input type="hidden" id="tasktypename" name="tasktypename" value="${tasktypename }"/>
				<input type="hidden" id="jjcd" name="jjcd" value="${jjcd }"/>
				<input type="hidden" id="jjcdname" name="jjcdname" value="${jjcdname }"/>
				<input type="hidden" id="rwly" name="rwly" value="${rwly }"/>
				<input type="hidden" id="rwlyname" name="rwlyname" value="${rwlyname }"/>
				<input type="hidden" id="starttime" name="starttime" value="${starttime }"/>
				<input type="hidden" id="endtime" name="endtime" value="${endtime }"/>
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" /> 
				
				<table class="queryTable" width="95%" border="0" cellpadding="0" cellspacing="0" style=" margin:0 auto">
					<tr>
						<th width="120">
							区域：
						</th>
						<td width="120">
							${areaname }
						</td>
						<th width="120">
							部门：
						</th>
						<td width="120">
							${orgname }
						</td>
						<th width="130">
							姓名：
						</th>
						<td width="120">
							${username }
						</td>
						<th width="120">
							任务类型：
						</th>
						<td>
							${tasktypename }
						</td>
					</tr>
					<tr>
						<th>
							任务来源：
						</th>
						<td>
							${rwlyname }
						</td>
						<th>
							紧急程度：
						</th>
						<td>
							${jjcdname }
						</td>
						<th>
							要求完成时限：
						</th>
						<td colspan="3">
							${dateTime }
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div style="width:95%; margin:5px  auto 5px;" class="t-r" id="dao">
              <input type="button" id="query" class="bTn directory_save directory_comwidth"  value="导出" />
             <input type="button" id="fanhui" class="bTn directory_save directory_comwidth"  value="返 回" onclick="fanhui()" />
         
           </div>
		<div class="divContainer" id="infectlist" style="width:80%;">
			<table id="data" fit="true"></table>
		</div>
		<iframe name="download" id="download" src="" style="display: none"></iframe>
	</body>
</html>

<script language="JavaScript">


$(document).ready(function(){
	$("#infectlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight()-$("#dao").outerHeight()-10);
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		nowrap:false,
		fitColumns : true,
		url:'queryStatisticalUserInfo.json?type=${type}&userid=${userid}&areaid=${areaid}&orgid=${orgid}&tasktypeid=${tasktypeid}&jjcd=${jjcd}&rwly=${rwly}&starttime=${starttime}&endtime=${endtime}',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id', hidden:true},
			{field:'workname', title:'任务名称', align:'left', halign:'center',width:100},
			{field:'tasktypename', title:'任务类型', align:'center', halign:'center',width:40},
			{field:'lawobjname', title:'执法对象', align:'left', halign:'center',width:100},
			{field:'regionname', title:'所属行政区', align:'center', halign:'center',width:35},
			{field:'pfr', title:'派发人', align:'center', halign:'center',width:30},
			{field:'yqwcsx', title:'要求完成时限', align:'center', halign:'center',width:35},
			{field:'zbry', title:'主办人员', align:'center', halign:'center',width:30},
			{field:'rwzt', title:'任务状态', align:'center', halign:'center',width:30},
			{field:'gdsj', title:'归档时间', align:'center', halign:'center',width:35},
			{field:'operate', title:'操作', align:'center', halign:'center',width:20,
				formatter:function(value,rowData,rowIndex){  
		    		return '<a onclick="info(\''+ rowData.id +'\')" class="b-link">查看</a> ';  
		       } 
			}
		]],
		onDblClickRow: function(rowIndex, rowData){
			info(rowData.id);
		}
	});
	$("#query").click(function(){
		$('#download').attr('src','exportStatisticalUserInfoList.json?type=${type}&userid=${userid}&username='+encodeURIComponent('${username}')+'&areaid=${areaid}&areaname='+encodeURIComponent('${areaname}')+'&orgid=${orgid}&orgname='+encodeURIComponent('${orgname}')+'&tasktypeid=${tasktypeid }&tasktypename='+encodeURIComponent('${tasktypename}')+'&rwly=${rwly }&rwlyname='+encodeURIComponent('${rwlyname }')+'&jjcd=${jjcd }&jjcdname='+encodeURIComponent('${jjcdname}')+'&starttime=${starttime}&endtime=${endtime}');
	});
	
});

function info(id){
	//All.ShowModalWin('rwzxgcfx.htm?id='+id, '任务执行过程分析');
	window.location.href='rwzxgcfx.htm?id='+id;
}
function fanhui(){
	window.location.href='statisticsUserList.htm?areaId='+$("#areaId").val()+'&tasktypeid='+$("#tasktypeid").val()+'&rwly='+$("#rwly").val()+'&jjcd='+$("#jjcd").val()+'&starttime='+$("#starttime").val()+'&endtime='+$("#endtime").val()+'&username='+$("#username").val();
}
</script>