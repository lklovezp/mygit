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
		<form id="queryForm" action="queryStatisticalOrgInfo.json" method="post">
				<input type="hidden" id="type" name="type" value="${type }"/>
				<input type="hidden" id="orgid" name="orgid" value="${orgid }"/>
				<input type="hidden" id="orgname" name="orgname" value="${orgname }"/>
				<input type="hidden" id="tasktypeid" name="tasktypeid" value="${tasktypeid }"/>
				<input type="hidden" id="tasktypename" name="tasktypename" value="${tasktypename }"/>
				<input type="hidden" id="jjcd" name="jjcd" value="${jjcd }"/>
				<input type="hidden" id="jjcdname" name="jjcdname" value="${jjcdname }"/>
				<input type="hidden" id="rwly" name="rwly" value="${rwly }"/>
				<input type="hidden" id="rwlyname" name="rwlyname" value="${rwlyname }"/>
				<input type="hidden" id="starttime" name="starttime" value="${starttime }"/>
				<input type="hidden" id="endtime" name="endtime" value="${endtime }"/>
				<input type="hidden" id="areaId" name="areaId" value="${areaId }"/>
				<table class="queryTable" width="95%" border="0" cellpadding="0" cellspacing="0" style=" margin:0 auto">
					<tr>
						<th width="100">
							要求完成时限：
						</th>
						<td  width="220">
							${dateTime }
						</td>
						<th width="100">
							区域：
						</th>
						<td  width="200">
							${areaname }
						</td>
						<th width="100">
							部门：
						</th>
						<td>
							${orgname }
						</td>
					</tr>
					<tr>
						<th>
							任务类型：
						</th>
						<td>
							${tasktypename }
						</td>
						<th>
							任务来源：
						</th>
						<td>
							${rwlyname }
						</td>
						<th>
							紧急程度：
						</th>
						<td colspan="3">
							${jjcdname }
						</td>
					</tr>
				</table>
			</form>
		</div>
			<div style="width:95%; margin:5px  auto 5px;" class="t-r" id="dao">
			
             <input type="button" id="query" class="bTn directory_save directory_comwidth"  value="导 出" />
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
	$("#infectlist").height($(window).height()-$("#questionContainer").outerHeight()-$("#divTitle").outerHeight()-$("#dao").outerHeight()-10);
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		nowrap:false,
		fitColumns : true,
		url:'queryStatisticalOrgInfo.json?type=${type}&areaId=${areaId}&orgid=${orgid}&tasktypeid=${tasktypeid}&jjcd=${jjcd}&rwly=${rwly}&starttime=${starttime}&endtime=${endtime}',
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
			{field:'dqdclr', title:'当前待处理人', align:'center', halign:'center',width:35},
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
		$('#download').attr('src','exportStatisticalOrgInfoList.json?type=${type}&orgid=${orgid}&orgname='+encodeURIComponent('${orgname}')+'&areaId=${areaId}&areaname='+encodeURIComponent('${areaname}')+'&tasktypeid=${tasktypeid }&tasktypename='+encodeURIComponent('${tasktypename}')+'&rwly=${rwly }&rwlyname='+encodeURIComponent('${rwlyname }')+'&jjcd=${jjcd }&jjcdname='+encodeURIComponent('${jjcdname}')+'&starttime=${starttime}&endtime=${endtime}');
	});
});

function info(id){
	//All.ShowModalWin('rwzxgcfx.htm?id='+id, '任务执行过程分析');
	//alert('12'+'&areaId='+$("#areaId").val()+'&tasktypeid='+$("#tasktypeid").val()+'&rwly='+$("#rwly").val()+'&jjcd='+$("#jjcd").val()+'&starttime='+$("#starttime").val()+'&endtime='+$("#endtime").val());
	window.location.href='rwzxgcfx.htm?id='+id+'&areaId='+$("#areaId").val()+'&tasktypeid='+$("#tasktypeid").val()+'&rwly='+$("#rwly").val()+'&jjcd='+$("#jjcd").val()+'&starttime='+$("#starttime").val()+'&endtime='+$("#endtime").val()+'&type='+$("#type").val();
}
function fanhui(){
	window.location.href='statisticsOrgList.htm?areaId='+$("#areaId").val()+'&tasktypeid='+$("#tasktypeid").val()+'&rwly='+$("#rwly").val()+'&jjcd='+$("#jjcd").val()+'&starttime='+$("#starttime").val()+'&endtime='+$("#endtime").val();
}
</script>