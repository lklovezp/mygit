<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <title>${title}</title>

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
<!--poshytip-->
<link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/resetCSS.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />
<style type="text/css">
 div.panel-header{background-color: #cff1ff;}
 .formtable, .formtable th, .formtable td{border-color:#dddddd;}
</style>
</head>

	<body>
	<div class="breadClumbs"> 查询统计&nbsp;&gt;&nbsp;${title}</div>
	<div class="searchDiv" id="searchDiv" style="height: 65px;">
			<form id="queryForm" action="quarterStatisticsList.htm?title=${title }" method="post">
				 <table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style=" margin:0 auto">
					<tr>
					  <th align="right">
							<a href="#" style="color:#3399CC;" id="selectOrg">主办部门：</a>
						</th>
						<td align="left">
						    <input class="y-text" name="orgname" type="text" id="orgname" value="${orgname }"/>
		                    <input class="y-text" type="hidden" id="orgid" name="orgid" value="${orgid }"/>
						</td>
						<th align="right" >
							要求完成时限：
						</th>
						<td >
							<input type="text" class="y-dateTime" id="starttime" name="starttime" value="${starttime }" onClick="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\',{d:-1});}'})"/> 
                                                                     至 <input type="text" class="y-dateTime"  id="endtime" name="endtime" value="${endtime }" onClick="WdatePicker({minDate:'#F{$dp.$D(\'starttime\',{d:1});}'})"/>
					     <input type="submit" id="query"  class="input_btnImgBlue" value="查　询" onClick="hideSearchForm()"/>
						<a class="moreSearch" id="moreSearch">更多查询条件</a>
						</td>
					</tr>
					<tr height="72">
						<th width="100">区域：</th>
						<td width="150"><input class="y-text" id="areaid" name="areaid"
							type="text" value="${areaid}" />
						</td>
						<td  align="center" colspan="3">
						<select class="y-text"  name="year" onChange="innitDateBox();" style="overflow:hidden;">
							<option value="2015" >2015</option>
						</select>
						年
						<select class="y-text"  name="quarter"  onchange="innitDateBox();">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
						季度
						</td>
					</tr>
				</table>
			</form>
          </div>
             <div style="width:95%; margin:-23px  auto 0px;" class="t-r">
                <input type="button" id="J-form-export" class="bTn directory_save directory_comwidth"  value="导出" />
             </div>
			<div class="panel-header" style="margin-top: 10px;">
				<div class="panel-title">
				 	污染源监察情况
				</div>
			</div>
			  <table class="dataTable" width="100%" border="1" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center" rowspan="2" width="100">
						任务类型
					</td>
					<td align="center" rowspan="2">
						完成次数
					</td>
					<td align="center" colspan="3">
						废水
					</td>
					<td align="center" colspan="3">
						废气
					</td>
					<td align="center" colspan="3">
						噪声
					</td>
					<td align="center" colspan="3">
						固废
					</td>
					<td align="center" rowspan="2">
						污染源<br>总个数
					</td>
					<td align="center" rowspan="2">
						现场监察<br>总次数
					</td>
					<td align="center" rowspan="2">
						现场监察<br>总人次
					</td>
					<td align="center" width="80">
						其他检查数
					</td>
					<td align="center" width="30">
						<fmt:formatNumber pattern="0" value="${quarterList[0].qtjcs }"></fmt:formatNumber>
					</td>
				</tr>
				<tr>
					<td align="center">
						个数
					</td>
					<td align="center">
						次数
					</td>
					<td align="center">
						人次
					</td>
					<td align="center">
						个数
					</td>
					<td align="center">
						次数
					</td>
					<td align="center">
						人次
					</td>
					<td align="center">
						个数
					</td>
					<td align="center">
						次数
					</td>
					<td align="center">
						人次
					</td>
					<td align="center">
						个数
					</td>
					<td align="center">
						次数
					</td>
					<td align="center">
						人次
					</td>
					<td align="center" colspan="2">
						备注
					</td>
				</tr>
				<c:forEach items="${quarterList }" var="quarterForm" varStatus="str" begin="0">
					<tr>
						<td align="center">
							${quarterForm.taskname }
						</td>
						<td align="center">
							<c:if test="${quarterForm.tasksum > 0 }">
								<a class="b-link" onClick="queryWccs('${quarterForm.tasktypeCode }');">
									<fmt:formatNumber pattern="0" value="${quarterForm.tasksum }"></fmt:formatNumber>
								</a>
							</c:if>
							<c:if test="${quarterForm.tasksum <= 0 }">
								<fmt:formatNumber pattern="0" value="${quarterForm.tasksum }"></fmt:formatNumber>
							</c:if>
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${quarterForm.fsgs }"></fmt:formatNumber>
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${quarterForm.fscs }"></fmt:formatNumber>
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${quarterForm.fsrc }"></fmt:formatNumber>
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${quarterForm.fqgs }"></fmt:formatNumber>
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${quarterForm.fqcs }"></fmt:formatNumber>
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${quarterForm.fqrc }"></fmt:formatNumber>
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${quarterForm.zsgs }"></fmt:formatNumber>
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${quarterForm.zscs }"></fmt:formatNumber>
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${quarterForm.zsrc }"></fmt:formatNumber>
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${quarterForm.gfgs }"></fmt:formatNumber>
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${quarterForm.gfcs }"></fmt:formatNumber>
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${quarterForm.gfrc }"></fmt:formatNumber>
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${quarterForm.wrygs }"></fmt:formatNumber>
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${quarterForm.xckczcs }"></fmt:formatNumber>
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${quarterForm.xckczrc }"></fmt:formatNumber>
						</td>
						<c:if test="${str.index == 0 }">
							<td align="center" rowspan="5" colspan="2">
								${fn:replace(quarterForm.qtjcRemark, "、", "<br/>")}
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
			<div class="panel-header" style="margin-top:10px;">
				<div class="panel-title">
				 	环境监察现场监督执法情况
				</div>
			</div>
			 <table class="dataTable" width="100%" border="1" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center" rowspan="2">
						现场监察总次数
					</td>
					<td align="center" colspan="5">
						污染防治设施检查情况
					</td>
					<td align="center" colspan="5">
						建设项目现场监督检查情况
					</td>
					<td align="center" colspan="7">
						限期治理项目现场监察情况
					</td>
					<td align="center" colspan="4">
						许可证现场检查情况
					</td>
					<td align="center">
						其他 管理
					</td>
				</tr>
				<tr>
					<td align="center">
						设施总台数
					</td>
					<td align="center">
						现场检查总次数
					</td>
					<td align="center">
						正常运转台数
					</td>
					<td align="center">
						运转率
					</td>
					<td align="center">
						运转达标率（%）
					</td>
					<td align="center">
						现场检查总次数
					</td>
					<td align="center">
						按计划项目施工数
					</td>
					<td align="center">
						项目实际投产数
					</td>
					<td align="center">
						防治设施投产数
					</td>
					<td align="center">
						“三同时”执行率（%）
					</td>
					<td align="center">
						现场检查总次数
					</td>
					<td align="center">
						限期治理项目数
					</td>
					<td align="center">
						应按期完成项目数
					</td>
					<td align="center">
						按期完成项目数
					</td>
					<td align="center">
						逾期完成项目数
					</td>
					<td align="center">
						未完成项目数
					</td>
					<td align="center">
						项目按期完成率
					</td>
					<td align="center">
						现场检查总次数
					</td>
					<td align="center">
						许可证发放数
					</td>
					<td align="center">
						符合许可证的家数
					</td>
					<td align="center">
						超标排污的家数
					</td>
					<td align="center">
						监察次数
					</td>
				</tr>
				<tr>
					<td align="center">
						${quarterZfqkForm.xcjczcs }
					</td>
					<td align="center">
						${quarterZfqkForm.wrfzsszts }
					</td>
					<td align="center">
						${quarterZfqkForm.wrfzxcjczcs }
					</td>
					<td align="center">
						${quarterZfqkForm.zcyzts }
					</td>
					<td align="center">
						${quarterZfqkForm.yzl }
					</td>
					<td align="center">
						${quarterZfqkForm.yzdbl }
					</td>
					<td align="center">
						${quarterZfqkForm.jsxmxcjczcs }
					</td>
					<td align="center">
						${quarterZfqkForm.ajhxmsgs }
					</td>
					<td align="center">
						${quarterZfqkForm.xmsjtcs }
					</td>
					<td align="center">
						${quarterZfqkForm.fzsstcs }
					</td>
					<td align="center">
						${quarterZfqkForm.stszxl }
					</td>
					<td align="center">
						${quarterZfqkForm.xqzlxcjczcs }
					</td>
					<td align="center">
						${quarterZfqkForm.xqzlxms }
					</td>
					<td align="center">
						${quarterZfqkForm.yaqwcxms }
					</td>
					<td align="center">
						${quarterZfqkForm.ajwcxms }
					</td>
					<td align="center">
						${quarterZfqkForm.yqwcxms }
					</td>
					<td align="center">
						${quarterZfqkForm.wwcxms }
					</td>
					<td align="center">
						${quarterZfqkForm.xmaqwcl }
					</td>
					<td align="center">
						${quarterZfqkForm.xkzxcjczcs }
					</td>
					<td align="center">
						${quarterZfqkForm.xkzffs }
					</td>
					<td align="center">
						${quarterZfqkForm.fhxkzjs }
					</td>
					<td align="center">
						${quarterZfqkForm.cbpwjs }
					</td>
					<td align="center">
						${quarterZfqkForm.qtjccs }
						
					</td>
				</tr>
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

//初始化年份
function innitDate(){
	var date = new Date();
	var year = parseInt(date.getFullYear());//当前时间年份
	var month = parseInt(date.getMonth() + 1);//当前时间月份
	
	//前一个季度
	var quarter = 4;
	if(month>=4 && month<=6){
		quarter = 1;
	}else if(month>=7 && month<=9){
		quarter = 2;
	}else if(month>=10 && month<=12){
		quarter = 3;
	}
	$("select[name='year']").empty();
	for(var i=year-10;i<=year+10;i++){
		var option = "<option value='"+i+"'";
		if((quarter <=0 && i==year-1) || (quarter > 0 && i==year)){
			option += " selected='selected' ";
		}
		option += " >"+i+"</option> "
		$("select[name='year']").append(option);
	}
	$("select[name='quarter']").val(quarter);
}

//初始化日期选择框
function innitDateBox(){
	
	var year = $("select[name='year']   option:selected").val();
	var quarter = $("select[name='quarter']   option:selected").val();
	var startmonth = (parseInt(quarter)-1)*3+1;
	startmonth = startmonth > 9 ? startmonth : ("0" + startmonth);
	var endmonth = parseInt(startmonth) + 2;
	endmonth = endmonth > 9 ? endmonth : ("0" + endmonth);
	$('#starttime').val(year + "-" + startmonth + "-01");
	$('#endtime').val(year + "-" + endmonth + ((endmonth==6||endmonth==9)?"-30":"-31"));
}

$(document).ready(function(){
	innitDate();
	if('${year}'!=''){
		$("select[name='year']").val('${year}');
		$("select[name='quarter']").val('${quarter}');
	}
	$("#tasktypeid").combotree({
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
		url:'dicList.json?type=1',
		valueField:'id',
		textField:'name'
	});
	//紧急程度
	 $('#jjcd').combobox({
		url:'dicList.json?type=3',
		valueField:'id',
		textField:'name'
	});
	$("#questionContainer").width($(window).width());
	if('${starttime}'==''){
		innitDateBox();
	}
	
	$("#selectOrg").colorbox({iframe:true,width:"300", height:"400",href:"${basePath}/orgPubQuery.htm?multi=true"});
	if('${orgid }'!=''){
		setOrgInfo('${orgid }','${orgname }');
	}
});

	
$('#J-from-reset').click(function(){
	$("#queryForm").form("clear");
	var date = new Date();
	var year = parseInt(date.getFullYear());
	var month = parseInt(date.getMonth() + 1);
	//前一个季度
	var quarter = 4;
	if(month>=4 && month<=6){
		quarter = 1;
	}else if(month>=7 && month<=9){
		quarter = 2;
	}else if(month>=10 && month<=12){
		quarter = 3;
	}
	$("select[name='year']").val(year);
	$("select[name='quarter']").val(quarter);
	innitDateBox()
});

$('#J-form-export').click(function(){
	var year = $("select[name='year']   option:selected").val();
	var quarter = $("select[name='quarter']   option:selected").val();
	var areaid = $('#areaid').combotree("getValue");
	var areaname = $('#areaid').combotree("getText");
	var orgid = $('#orgid').val();
	var orgname = $("input[name='orgname']").val();
	var starttime = $("#starttime").val();
	var endtime	= $("#endtime").val();
	$('#download').attr('src','exportQuarterStatistics.json?areaid='+areaid+'&areaname='+encodeURIComponent(areaname)+'&orgid='+orgid+'&orgname='+encodeURIComponent(orgname)+'&quarter='+quarter+'&year='+year+'&starttime='+starttime+'&endtime='+endtime);
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


//设置部门
function setOrgInfo(id, name) {
	$("#orgname").val(name);
	$("#orgid").val(id);
	jQuery().colorbox.close();
}
//查询完成次数
function queryWccs(tasktypeCode){
	var orgid = $('#orgid').val();
	var areaid = $('#areaid').combotree("getValue");
	var starttime = $("#starttime").val();
	var endtime	= $("#endtime").val();
	var title='按季度统计';
	var width=screen.width*0.8;
	var height=screen.height*0.8-50;
	//All.ShowModalWin("queryWccs.htm?tasktype="+tasktypeCode+"&areaid="+areaid+"&zbOrgId="+orgid+"&yqwcStarttime="+starttime+"&yqwcEndtime="+endtime, '按季度统计',1200,800);
	parent.layerIframe(2,"queryWccs.htm?tasktype="+tasktypeCode+"&areaid="+areaid+"&zbOrgId="+orgid+"&yqwcStarttime="+starttime+"&yqwcEndtime="+endtime,title,width,height);
}

</script>
