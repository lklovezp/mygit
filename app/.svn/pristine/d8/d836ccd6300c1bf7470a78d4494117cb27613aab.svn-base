<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<!--时间插件 my97-->
<script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
<!--poshytip-->
<link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />

<link href="${app}/css/resetCSS.css" rel="stylesheet" type="text/css" />
    <link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />

</head>

	<body>
		<div class="breadClumbs"> 查询统计&nbsp;&gt;&nbsp;${title}</div>
			<form id="queryForm" action="" method="post" >
				<table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style=" margin:0 auto">
					<tr align="center">
						区域：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input class="y-text" id="areaId" name="areaId" type="text" value="${areaId}" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						年份：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						
						<input type="text"  id="starttime" name="starttime" value="${starttime}" onfocus="WdatePicker({dateFmt:'yyyy'})" class="y-text" readonly/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" id="query"  class="input_btnImgBlue" value="查　询" onclick="hideSearchForm()"/>
                        <!-- <input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/> -->
						
					</tr>
				</table>
		    </form>
         <div style="width:95%; margin:-20px  auto -35px;" class="sttsTab clearfix">
     <span>
        <a  onclick="showTab(this,'con1')">总分数统计</a>
    	<a class="cur" onclick="showTab(this,'con2')" style="border-right:1px solid #1895fb;border-left:1px solid #1895fb">按部门统计</a>
        <a  onclick="showTab(this,'con3')">按人员统计</a>
    </span>
    <input id="J-form-export" class="input_btnColorlBlue dc" type="button" value="导出"/>
 
</div>
         
         
        
        
        
        
        
        
        
        
        <div class="dataDiv" style="width:100%; margin:16px auto 25px;">
			<table class="dataTable" width="100%" border="1" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center" colspan="2" width="15%" bgcolor="#cff1ff">
						部门
					</td>
					<td align="center" bgcolor="#cff1ff">
						一月份
					</td>
					<td align="center" bgcolor="#cff1ff">
						二月份
					</td>
					<td align="center" bgcolor="#cff1ff">
						三月份
					</td>
					<td align="center" bgcolor="#cff1ff">
						四月份
					</td>
					<td align="center" bgcolor="#cff1ff"c>
						五月份
					</td>
					<td align="center" bgcolor="#cff1ff">
						六月份
					</td>
					<td align="center" bgcolor="#cff1ff">
						七月份
					</td>
					<td align="center" bgcolor="#cff1ff">
						八月份
					</td>
					<td align="center" bgcolor="#cff1ff">
						九月份
					</td>
					<td align="center" bgcolor="#cff1ff">
						十月份
					</td>
					<td align="center" bgcolor="#cff1ff">
						十一月份
					</td>
					<td align="center" bgcolor="#cff1ff">
						十二月份
					</td>
					<td align="center" bgcolor="#cff1ff">
						合计
					</td>
				</tr>
					<c:forEach items="${list }" var="statisticsForm">
						<tr>
							<c:if test="${statisticsForm.orgid != 'sum' && statisticsForm.orgid != 'real'}">
								<td align="center" colspan="2">
									${statisticsForm.orgname }
								</td>
							</c:if>
							<c:if test="${statisticsForm.orgid == 'sum'}">
								<td align="center" colspan="2">
									合计
								</td>
							</c:if>
							<td align="center" title="该部门的监察笔录份数">
								<c:if test="${statisticsForm.yyfs>0 }">
									<font color="blue">${statisticsForm.yyfs }</font>
								</c:if>
								<c:if test="${statisticsForm.yyfs<=0 }">
									${statisticsForm.yyfs }
								</c:if>
							</td>
							<td align="center">
								<c:if test="${statisticsForm.eyfs>0 }">
									<font color="blue">${statisticsForm.eyfs }</font>
								</c:if>
								<c:if test="${statisticsForm.eyfs<=0 }">
									${statisticsForm.eyfs }
								</c:if>
							</td>
							<td align="center">
								<c:if test="${statisticsForm.myfs>0 }">
									<font color="blue">${statisticsForm.myfs }</font>
								</c:if>
								<c:if test="${statisticsForm.myfs<=0 }">
									${statisticsForm.myfs }
								</c:if>
							</td>
							<td align="center">
								<c:if test="${statisticsForm.syfs>0 }">
									<font color="blue">${statisticsForm.syfs }</font>
								</c:if>
								<c:if test="${statisticsForm.syfs<=0 }">
									${statisticsForm.syfs }
								</c:if>
							</td>
							<td align="center">
								<c:if test="${statisticsForm.wyfs>0 }">
									<font color="blue">${statisticsForm.wyfs }</font>
								</c:if>
								<c:if test="${statisticsForm.wyfs<=0 }">
									${statisticsForm.wyfs }
								</c:if>
							</td>
							<td align="center">
								<c:if test="${statisticsForm.lyfs>0 }">
									<font color="blue">${statisticsForm.lyfs }</font>
								</c:if>
								<c:if test="${statisticsForm.lyfs<=0 }">
									${statisticsForm.lyfs }
								</c:if>
							</td>
							<td align="center">
								<c:if test="${statisticsForm.qyfs>0 }">
									<font color="blue">${statisticsForm.qyfs }</font>
								</c:if>
								<c:if test="${statisticsForm.qyfs<=0 }">
									${statisticsForm.qyfs }
								</c:if>
							</td>
							<td align="center">
								<c:if test="${statisticsForm.byfs>0 }">
									<font color="blue">${statisticsForm.byfs }</font>
								</c:if>
								<c:if test="${statisticsForm.byfs<=0 }">
									${statisticsForm.byfs }
								</c:if>
							</td>
							<td align="center">
								<c:if test="${statisticsForm.jyfs>0 }">
									<font color="blue">${statisticsForm.jyfs }</font>
								</c:if>
								<c:if test="${statisticsForm.jyfs<=0 }">
									${statisticsForm.jyfs }
								</c:if>
							</td>
							<td align="center">
								<c:if test="${statisticsForm.oyfs>0 }">
									<font color="blue">${statisticsForm.oyfs }</font>
								</c:if>
								<c:if test="${statisticsForm.oyfs<=0 }">
									${statisticsForm.oyfs }
								</c:if>
							</td>
							<td align="center">
								<c:if test="${statisticsForm.ayfs>0 }">
									<font color="blue">${statisticsForm.ayfs }</font>
								</c:if>
								<c:if test="${statisticsForm.ayfs<=0 }">
									${statisticsForm.ayfs }
								</c:if>
							</td>
							<td align="center">
								<c:if test="${statisticsForm.dyfs>0 }">
									<font color="blue">${statisticsForm.dyfs }</font>
								</c:if>
								<c:if test="${statisticsForm.dyfs<=0 }">
									${statisticsForm.dyfs }
								</c:if>
							</td>
							<td align="center">
								<c:if test="${statisticsForm.ynfs>0 }">
									<font color="blue">${statisticsForm.ynfs }</font>
								</c:if>
								<c:if test="${statisticsForm.ynfs<=0 }">
									${statisticsForm.ynfs }
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
////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////

$(document).ready(function(){
	var startDate = $("#starttime").val();
	//区域
	$('#areaId').combobox({
		height:34,
		url : 'queryAreaComboWithCur.json',
		valueField : 'id',
		textField : 'name'
	});
	$("#questionContainer").width($(window).width());
});

$('#J-form-reset').click(function(){
	$("#queryForm").form("clear");
	 //$('#starttime').datebox('setValue', formatterDate(new Date()));
});

$('#J-form-export').click(function(){
	var areaid = $("#areaId").combotree('getValue');
	var areaname = $("#areaId").combotree('getText');
	var starttime = $("#starttime").val();
	$('#download').attr('src','exportStatisticalDocInfoList?areaId='+areaid+'&areaname='+encodeURIComponent(areaname)+'&starttime='+starttime);
});

function info(orgid,orgname,type){
	var url = 'statisticalOrgInfo.htm?orgid='+orgid;
	url += '&areaId=${areaId}';
	url += '&starttime='+$("#starttime").datebox('getValue');
	All.ShowModalWin(url, '按部门统计监察笔录份数',1200,800);
}

function showTab(t,id){
	$(t).addClass("cur").siblings("a").removeClass("cur");

	var iframeObj= window.parent.document.getElementById("conIframe");
	if(id=="con1"){//按部门查询
	
	iframeObj.src="statisticsDocByAreaList.htm?title=按总份数统计";
	
	}else if(id=="con2"){//按人员查询
     iframeObj.src="statisticsDocByOrgList.htm?title=按部门统计";
	//location.reload();
	}else{
	iframeObj.src="statisticsDocByUserList.htm?title=按人员统计";
	}
}

</script>
