<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title }</title>
<!--poshytip-->
<link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />
<!--jQuery-->
<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<!--时间插件 my97-->
<script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
<!--poshytip-->
<link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${app}/js.js"></script>
<script type="text/javascript" src="${common}/All.js"></script>
<link href="${app}/css/easyUIReset.css" rel="stylesheet" type="text/css"/>
<link href="${app}/css/resetCSS.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="breadClumbs"> 查询统计&nbsp;&gt;&nbsp;${title}</div>
<div class="searchDiv" id="searchDiv" style="height: 65px;">
   <form id="queryForm" action="" method="post">
   <table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style=" margin:0 auto">
    <tr>
       <th width="11.3%">任务类型</th>
       <td width="22%"><input type="text" class="y-text" id="tasktypeid" name="tasktypeid" value="${tasktypeid }"/></td>
       <th>要求完成时限</th>
       <td colspan="3">
         <input type="text" class="y-dateTime" id="starttime" name="starttime" value="${starttime }" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\',{d:-1});}'})"/> 
                   至 <input type="text" class="y-dateTime"  id="endtime" name="endtime" value="${endtime }" onclick="WdatePicker({minDate:'#F{$dp.$D(\'starttime\',{d:1});}'})"/>
        <input type="submit" id="query"  class="input_btnImgBlue" value="查　询" onclick="hideSearchForm()"/>
         <a class="moreSearch" id="moreSearch">更多查询条件</a>
         </td>
    </tr>
     <tr height="72">
       <th width="11.3%">区域</th>
       <td width="22%"><input type="text" class="y-text" id="areaId" name="areaId"  value="${areaId}"/></td>
       <th width="11.3%">任务来源</th>
       <td width="22%"><input type="text" class="y-text" id="rwly" name="rwly" value="${rwly }"/></td>
       <th>紧急程度</th>
       <td><input type="text" class="y-text" id="jjcd" name="jjcd" value="${jjcd }"/></td>
     </tr>
   </table>
   </form>
   </div>
<div style="width:95%; margin:-20px  auto -35px;" class="sttsTab clearfix">
   <span>
    	<a class="cur" onclick="showTab(this,'con1')">按部门统计</a>
        <a onclick="showTab(this,'con2')">按人员统计</a>
    </span>
    <input id="J-form-export" class="input_btnColorlBlue dc" type="button" value="导出"/>
  <!--  <input type="button" id="J-form-export" class="bTn directory_save directory_comwidth"  value="导出" />
--></div>
<div class="dataDiv" style="width:100%; margin:16px auto 25px;">
   <table class="dataTable" width="100%" border="1" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
     <tr bgcolor="#cff1ff">
       <th width="23%" rowspan="2" colspan="2">部门</th>
       <th colspan="5"><c:if test="${tasktypename != null }">${tasktypename }</c:if>
						<c:if test="${tasktypename == null }">所有任务类型</c:if>（次数）</th>
     </tr>
     <tr bgcolor="#cff1ff">
       <th width="15.3%">下达</th>
       <th width="15.3%">完成</th>
       <th width="15.3%">逾期完成</th>
       <th width="15.3%">正在办理</th>
       <th width="15.3%">逾期正在办理</th>
     </tr>
    <c:forEach items="${list }" var="statisticsForm">
						<tr>
							<c:if test="${statisticsForm.orgid != 'sum' && statisticsForm.orgid != 'real'}">
								<td align="center" colspan="2">
									${statisticsForm.orgname }
								</td>
							</c:if>
							<c:if test="${statisticsForm.orgid == 'sum' }">
								<td align="center" rowspan="2">
									合计
								</td>
								<td align="center">
									${statisticsForm.orgname }
								</td>
							</c:if>
							<c:if test="${statisticsForm.orgid == 'real' }">
								<td align="center">
									${statisticsForm.orgname }
								</td>
							</c:if>
							<td align="center" title="下达到该部门的任务">
								<c:if test="${statisticsForm.xd>0 }">
									<a onclick="javascript:info('${statisticsForm.orgid }','${statisticsForm.orgname }','1')" class="b-link">${statisticsForm.xd }</a>
								</c:if>
								<c:if test="${statisticsForm.xd<=0 }">
									${statisticsForm.xd }
								</c:if>
							</td>
							<td align="center">
								<c:if test="${statisticsForm.wc>0 }">
									<a onclick="javascript:info('${statisticsForm.orgid }','${statisticsForm.orgname }','2')" class="b-link">${statisticsForm.wc }</a>
								</c:if>
								<c:if test="${statisticsForm.wc<=0 }">
									${statisticsForm.wc }
								</c:if>
							</td>
							<td align="center">
								<c:if test="${statisticsForm.yqwc>0 }">
									<a onclick="javascript:info('${statisticsForm.orgid }','${statisticsForm.orgname }','3')" class="b-link"><font color="red">${statisticsForm.yqwc }</font></a>
								</c:if>
								<c:if test="${statisticsForm.yqwc<=0 }">
									${statisticsForm.yqwc }
								</c:if>
							</td>
							<td align="center">
								<c:if test="${statisticsForm.zzbl>0 }">
									<a onclick="javascript:info('${statisticsForm.orgid }','${statisticsForm.orgname }','4')" class="b-link">${statisticsForm.zzbl }</a>
								</c:if>
								<c:if test="${statisticsForm.zzbl<=0 }">
									${statisticsForm.zzbl }
								</c:if>
							</td>
							<td align="center">
								<c:if test="${statisticsForm.yqzzbl>0 }">
									<a onclick="javascript:info('${statisticsForm.orgid }','${statisticsForm.orgname }','5')" class="btslink"><font color="red">${statisticsForm.yqzzbl }</font></a>
								</c:if>
								<c:if test="${statisticsForm.yqzzbl<=0 }">
									${statisticsForm.yqzzbl }
								</c:if>
							</td>
						</tr>
					</c:forEach>
   </table>
</div>
<iframe name="download" id="download" src="" style="display: none"></iframe>
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
//////////////////////////////////////////////////////////////////////////////////

formatterDate = function(date) {
	 var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	 var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	 + (date.getMonth() + 1);
	 return date.getFullYear() + '-' + month + '-' + day;
};

$(document).ready(function(){
	
	$("#tasktypeid").combotree({
		height:34,
		type:"post",
		url:'taskTypeTreeCombo.json'
	});
	//任务来源下拉框
   $('#rwly').combobox({
	    height:34,
		url:'dicList.json?type=1',
		valueField:'id',
		textField:'name'
	});
	//紧急程度
	 $('#jjcd').combobox({
		height:34,
		url:'dicList.json?type=3',
		valueField:'id',
		textField:'name'
	});
	//区域
	$('#areaId').combobox({
		height:34,
		url : 'queryAreaComboWithCur.json',
		valueField : 'id',
		textField : 'name'
	});
	
});

$('#J-form-reset').click(function(){
	$("#queryForm").form("clear");
	 $('#endtime').datebox('setValue', formatterDate(new Date()));
});

$('#J-form-export').click(function(){
	//alert($("#starttime").val());
	//alert($dp.cal.getNewDateStr(""));
	//return false;
	var areaid = $("#areaId").combotree('getValue');
	var areaname = $("#areaId").combotree('getText');
	var tasktypeid = $("#tasktypeid").combotree('getValue');
	var tasktypename = $("#tasktypeid").combotree('getText');
	var rwly = $("#rwly").combotree('getValue');
	var rwlyname = $("#rwly").combotree('getText');
	var jjcd = $("#jjcd").combotree('getValue');
	var jjcdname = $("#jjcd").combotree('getText');
	var starttime = $("#starttime").val();
	var endtime = $("#endtime").val();
	$('#download').attr('src','exportStatisticalOrgList?areaId='+areaid+'&areaname='+encodeURIComponent(areaname)+'&tasktypeid='+tasktypeid+'&tasktypename='+encodeURIComponent(tasktypename)+'&rwly='+rwly+'&rwlyname='+encodeURIComponent(rwlyname)+'&jjcd='+jjcd+'&jjcdname='+encodeURIComponent(jjcdname)+'&starttime='+starttime+'&endtime='+endtime);
});

function info(orgid,orgname,type){
	var url = 'statisticalOrgInfo.htm?orgid='+orgid;
	url += '&type='+type;
	url += '&areaId=${areaId}';
	url += '&tasktypeid=${tasktypeid}';
	url += '&jjcd=${jjcd}';
	url += '&rwly=${rwly}';
	url += '&starttime='+$("#starttime").val();
	url += '&endtime='+$("#endtime").val();
	window.location.href=url;
	
}
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
function showTab(t,id){
	$(t).addClass("cur").siblings("a").removeClass("cur");
	var iframeObj= window.parent.document.getElementById("conIframe");
	if(id=="con1"){//按部门查询
	
	//alert(iframeObj.id);
	iframeObj.src="statisticsOrgList.htm?title=按部门统计";
	//location.reload();
	}else{//按人员查询
	//alert(iframeObj.src);
	iframeObj.src="statisticsUserList.htm?title=按人员统计";
	}
}

</script>
