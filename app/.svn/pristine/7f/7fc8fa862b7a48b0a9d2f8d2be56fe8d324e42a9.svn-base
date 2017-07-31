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
<!--时间插件 my97-->
<script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
<!--poshytip-->
<link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/resetCSS.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/easyUIReset.css" rel="stylesheet" type="text/css"/>
<link href="${app}/css/resetCSS.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="breadClumbs"> 查询统计&nbsp;&gt;&nbsp;${title}</div>
<div class="searchDiv" id="searchDiv" style="height: 65px;">
			<form id="queryForm" action="" method="post" onSubmit="return checkDate();">
			<table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style=" margin:0 auto">
				<tr>
				<th width="11.3%">
					姓名：
				</th>
				<td width="22%">
					<input class="y-text" type="text" name="username" id="username" value="${username }"/>
				</td>
				<th>
				要求完成时限：
				</th>
				<td colspan="3">
					 <input type="text" class="y-dateTime" id="starttime" name="starttime" value="${starttime }" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\',{d:-1});}'})"/> 
                                              至 <input type="text" class="y-dateTime"  id="endtime" name="endtime" value="${endtime }" onclick="WdatePicker({minDate:'#F{$dp.$D(\'starttime\',{d:1});}'})"/>
			       <input type="submit" id="query"  class="input_btnImgBlue" value="查　询" onclick="hideSearchForm()"/>
				<a class="moreSearch" id="moreSearch">更多查询条件</a>
				</td>
				</tr>
					<tr height="72">
						<th width="11.3%">区域：</th>
						<td width="22%"><input class="y-text" id="areaid" name="areaid"
							type="text" value="${areaid}" />
						</td>
						<th width="11.3%">
						<a href="#" style="color:#3399CC;" id="selectOrg">部门：</a>
						</th>
						<td width="22%" >
							<input class="y-text"  name="orgname" type="text" id="orgname" value="${orgname }"/>
		                    <input class="y-text" type="hidden" id="orgid" name="orgid" value="${orgid }"/>
						</td>
						<th width="11.3%">
							任务来源：
						</th>
						<td width="22%">
							<input class="y-text" type="text" name="rwly" id="rwly" value="${rwly }"/>
						</td>
						
					</tr>
					<tr>
					<th width="11.3%">
						紧急程度：
					</th>
					<td width="22%">
						<input class="y-text" type="text" id="jjcd" name="jjcd" value="${jjcd }"/>
					</td>
					<th >
					任务类型：
				</th>
				   <td >
				   <input class="y-text" id="tasktypeid" name="tasktypeid" type="text" value="${tasktypeid }"/>
				   </td>
					</tr>
				</table>
			</form>
	</div>
   <div style="width:95%; margin:-25px  auto -35px;" class="sttsTab clearfix">
   <span>
    	<a  onclick="showTab(this,'con1')">按部门统计</a>
        <a class="cur" onclick="showTab(this,'con2')">按人员统计</a>
    </span>
    <input id="J-form-export" class="input_btnColorlBlue dc" type="button" value="导出"/>
  <!--  <input type="button" id="J-form-export" class="bTn directory_save directory_comwidth"  value="导出" />
--></div>
            
            <div class="dataDiv" style="width:100%; margin:16px auto 25px;">
           <table class="dataTable" width="100%" border="1" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
				<tr bgcolor="#cff1ff">
					<td align="center" rowspan="2" colspan="2" width="30%">
						部门
					</td>
					<td align="center" colspan="5">
						<c:if test="${tasktypename != null }">${tasktypename }</c:if>
						<c:if test="${tasktypename == null }">所有任务类型</c:if>（次数）
					</td>
				</tr>
				<tr bgcolor="#cff1ff">
					<td align="center">
						下达
					</td>
					<td align="center">
						完成
					</td>
					<td align="center">
						逾期完成
					</td>
					<td align="center">
						正在办理
					</td>
					<td align="center">
						逾期正在办理
					</td>
				</tr>
					<c:forEach items="${list }" var="statisticsForm" varStatus="status">
						<tr>
						<c:if test="${statisticsForm.orgid != 'sum' && statisticsForm.orgid != 'real'}">
							<c:if test="${list[status.index-1].orgname != statisticsForm.orgname }">
								<% int i= 0; %>
								<c:forEach items="${list }" var="statisticsForm2" >
									<c:if test="${statisticsForm2.orgname == statisticsForm.orgname }">
										<% i++; %>
									</c:if>
								</c:forEach>
								<td align="center" rowspan="<%=i%>">
									${statisticsForm.orgname }
								</td>
							</c:if>
						</c:if>
						<c:if test="${statisticsForm.orgid == 'sum' }">
							<td align="center" rowspan="2">
								${statisticsForm.orgname }
							</td>
						</c:if>
						<td align="center">
							${statisticsForm.username }
						</td>
						<td align="center" title="统计任务流转过程中待处理（除审核之外）的任务数，其中：主办、协办人员均统计">
							<c:if test="${statisticsForm.xd>0 }">
								<a onClick="javascript:info('${statisticsForm.userid}','1')" class="b-link">
							</c:if>
									${statisticsForm.xd }
							<c:if test="${statisticsForm.xd>0 }">
								</a>
							</c:if>
						</td>
						<td align="center">
							<c:if test="${statisticsForm.wc>0 }">
								<a onClick="javascript:info('${statisticsForm.userid}','2')" class="b-link">
									${statisticsForm.wc }
								</a>
							</c:if>
							<c:if test="${statisticsForm.wc<=0 }">
									${statisticsForm.wc }
							</c:if>
						</td>
						<td align="center">
							<c:if test="${statisticsForm.yqwc>0 }">
								<a onClick="javascript:info('${statisticsForm.userid}','3')" class="b-link"><font color="red">${statisticsForm.yqwc }</font></a>
							</c:if>
							<c:if test="${statisticsForm.yqwc<=0 }">
								${statisticsForm.yqwc }
							</c:if>
						</td>
						<td align="center">
							<c:if test="${statisticsForm.zzbl>0 }">
								<a onClick="javascript:info('${statisticsForm.userid}','4')" class="b-link">${statisticsForm.zzbl }</a>
							</c:if>
							<c:if test="${statisticsForm.zzbl<=0 }">
								${statisticsForm.zzbl }
							</c:if>
						</td>
						<td align="center">
							<c:if test="${statisticsForm.yqzzbl>0 }">
								<a onClick="javascript:info('${statisticsForm.userid}','5')" class="btslink"><font color="red">${statisticsForm.yqzzbl }</font></a>
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
formatterDate = function(date) {
	 var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	 var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	 + (date.getMonth() + 1);
	 return date.getFullYear() + '-' + month + '-' + day;
};
$(document).ready(function(){
	if('${orgid }'!=''){
		setOrgInfo('${orgid }','${orgname }');
	}
	//部门
	$("#selectOrg").colorbox({iframe:true,width:"300", height:"400",href:"${basePath}/orgPubQuery.htm?multi=true"});
	
	//任务类型
	$("#tasktypeid").combotree({
		height:34,
		type:"post",
		url:'taskTypeTreeCombo.json'
	});
	//区域
	$('#areaid').combobox({
		height:34,
		url : 'queryAreaComboWithCur.json',
		valueField : 'id',
		textField : 'name',
		onSelect: function (record) {//做区域部门的联动选择
             $("#selectOrg").colorbox({iframe:true,width:"300", height:"400",href:"${basePath}/orgPubQuery.htm?multi=true&areaid="+record.id});
        }
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
	$("#questionContainer").width($(window).width());

	
	$('#J-form-reset').click(function(){
		$("#queryForm").form("clear");
		 $('#endtime').datebox('setValue', formatterDate(new Date()));
	});
	
	$('#J-form-export').click(function(){
		var tasktypeid = $("#tasktypeid").combotree('getValue');
		var tasktypename = $("#tasktypeid").combotree('getText');
		var rwly = $("#rwly").combotree('getValue');
		var rwlyname = $("#rwly").combotree('getText');
		var jjcd = $("#jjcd").combotree('getValue');
		var jjcdname = $("#jjcd").combotree('getText');
		var starttime = $("#starttime").val();
		var endtime = $("#endtime").val();
		var username = $("#username").val();
		var orgid = $("#orgid").val();
		var orgname = $("#orgname").val();
		var areaid = $("#areaid").combotree("getValue");
		var areaname = $("#areaid").combotree("getText");
		$('#download').attr('src','exportStatisticsUserList?tasktypeid='+tasktypeid+'&tasktypename='+encodeURIComponent(tasktypename)+'&rwly='+rwly+'&rwlyname='+encodeURIComponent(rwlyname)+'&jjcd='+jjcd+'&jjcdname='+encodeURIComponent(jjcdname)+'&starttime='+starttime+'&endtime='+endtime+'&username='+encodeURIComponent(username)+'&orgid='+orgid+'&orgname='+encodeURIComponent(orgname)+'&areaid='+areaid+'&areaname='+encodeURIComponent(areaname));
	});
	
	
	
	
});

function info(userid,type){
	
	var orgid = $("#orgid").val();
	var orgname = $("#orgname").val();
	var areaid = $("#areaid").combotree("getValue");
	var areaname = $("#areaid").combotree("getText");
	var username = $("#username").val();
	var url = 'statisticalUserInfo.htm?userid='+userid;
	url += '&areaid='+areaid;
	url += '&areaname='+encodeURIComponent(areaname);
	url += '&orgid='+orgid;
	url += '&orgname='+encodeURIComponent(orgname);
	url += '&type='+type;
	url += '&tasktypeid=${tasktypeid}';
	url += '&jjcd=${jjcd}';
	url += '&rwly=${rwly}';
	url += '&username='+encodeURIComponent(username);
	url += '&starttime='+$("#starttime").val();
	url += '&endtime='+$("#endtime").val();
	//All.ShowModalWin(url, '按人员统计',1200,800);
	window.location.href=url;
}

//设置部门
function setOrgInfo(id, name) {
	$("#orgname").val(name);
	$("#orgid").val(id);
	jQuery().colorbox.close();
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
	
	iframeObj.src="statisticsOrgList.htm?title=按部门统计";
	
	}else{//按人员查询
     iframeObj.src="statisticsUserList.htm?title=按人员统计";
	//location.reload();
	}
}
</script>
