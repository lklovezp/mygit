<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

</head>

	<body>
		<div class="breadClumbs"> 查询统计&nbsp;&gt;&nbsp;${title}</div>
         <form id="queryForm" action="" method="post">
				<table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style=" margin:0 auto">
					<tr align="center">
						区域：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input class="y-text" id="areaid" name="areaid" type="text" value="${areaid}" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#" style="color:#3399CC;" id="selectOrg">部门：</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input class="y-text" name="orgname" type="text" id="orgname" value="${orgname }"/>&nbsp;&nbsp;
		                <input class="y-text" type="hidden" id="orgid" name="orgid" value="${orgid }"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span class="mark">*</span>年份：
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text"  id="starttime" name="starttime" value="${starttime}" onfocus="WdatePicker({dateFmt:'yyyy'})" class="y-text" readonly/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" id="query"  class="input_btnImgBlue" value="查　询" onclick="hideSearchForm()"/>
                           <!--   <input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>-->
					</tr>
				</table>
			 </form>
        <div style="width:95%; margin:-20px  auto -35px;" class="sttsTab clearfix">
   <span>
        <a  onclick="showTab(this,'con1')">总分数统计</a>
    	<a  onclick="showTab(this,'con2')" style="border-right:1px solid #1895fb;border-left:1px solid #1895fb">按部门统计</a>
        <a class="cur" onclick="showTab(this,'con3')">按人员统计</a>
    </span>
    <input id="J-form-export" class="input_btnColorlBlue dc" type="button" value="导出"/>
 
</div>
       
       
        <div class="dataDiv" style="width:100%; margin:16px auto 25px;">
			<table class="dataTable" width="100%" border="1" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center" colspan="2" width="30%" bgcolor="#cff1ff">
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
					<td align="center" bgcolor="#cff1ff">
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
					<c:forEach items="${list }" var="statisticsForm" varStatus="status">
						<tr>
						<c:if test="${statisticsForm.orgid != 'sum'}">
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
						<td align="center" title="监察笔录份数统计">
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
	if('${orgid }'!=''){
		setOrgInfo('${orgid }','${orgname }');
	}
	//部门
	$("#selectOrg").colorbox({iframe:true,width:"300", height:"400",href:"${basePath}/orgPubQuery.htm?multi=true"});
	
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
	$("#questionContainer").width($(window).width());
	$('#J-form-reset').click(function(){
		$("#queryForm").form("clear");
		 $('#starttime').datebox('setValue', formatterDate(new Date()));
	});
	
	$('#J-form-export').click(function(){
		var orgid = $("#orgid").val();
		var orgname = $("#orgid").val();
		var areaid = $("#areaid").combotree('getValue');
		var areaname = $("#areaid").combotree('getText');
		var starttime = $("#starttime").val();
		$('#download').attr('src','exportStatisticsDocByUserList?areaid='+areaid+'&starttime='+starttime+'&orgid='+orgid+'&orgname='+encodeURIComponent(orgname)+'&areaname='+encodeURIComponent(areaname));
	});
});


function info(userid,type){
	var orgid = $("#orgid").val();
	var orgname = $("#orgid").val();
	var areaid = $("#areaid").combotree("getValue");
	var areaname = $("#areaid").combotree("getText");
	var url = 'statisticalOrgInfo.htm?orgid='+orgid;
	url += '&areaid='+areaid;
	url += '&areaname='+encodeURIComponent(areaname);
	url += '&orgname='+encodeURIComponent(orgname);
	url += '&type='+type;
	url += '&starttime='+$("#starttime").datebox('getValue');
	All.ShowModalWin(url, '按人员统计',1200,800);
}

//设置部门
function setOrgInfo(id, name) {
	//$('#orgid').linkbox("setValue", {id:id,name:name});
	//$("input[name='orgname']").val(name);
	$("#orgname").val(name);
	$("#orgid").val(id);
	jQuery().colorbox.close();
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
