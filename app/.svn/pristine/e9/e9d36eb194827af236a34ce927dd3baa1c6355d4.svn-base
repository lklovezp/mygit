<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title }</title>
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<!--poshytip-->
<link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
<!--jQuery-->
<script type="text/javascript" src="${app}/js.js"></script>
<script type="text/javascript" src="${app}/data.js"></script>
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<!--时间插件 my97-->
<script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
<script type="text/javascript" src="${common}/All.js"></script>
<style>
caption, th {text-align: center;}
</style>
</head>

<body>
<div class="breadClumbs"> 查询统计&nbsp;&gt;&nbsp;${title}</div>
<div id="searchForm">
   <form id="queryForm" action="checkedCount.htm" method="post">
   <table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style=" margin:0 auto">
     <tr>
       <th width="13.3%">区域:</th>
       <td width="22%"><input type="text" class="y-text" id="areaid" name="areaid" value="${areaid}" /></td>
       <th width="13.3%">年份:</th>
       <td width="22%">
		<input class="y-text" type="text" id="year" name="year" vale=""/>
       </td>
       <th width="13.3%">季度:</th>
       <td width="22%">
		<input class="y-text" type="text" id="quarter" name="quarter" value="${quarter}"/>
       </td>
       <td>
        <input type="submit" id="query"  class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
        
       </td>
       <td>
       	<input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
       </td>
     </tr>
   </table>
   </form>
</div>
<div style="width:92%; margin:18px  auto 0px;" class="t-r">
  <input type="button" id="J-form-export"  class="bTn directory_save directory_comwidth"  value="导出" />
</div>
<div class="dataDiv" style="width:99%; margin:16px auto 25px;">
   <table class="dataTable" width="100%" border="1" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
     <tr bgcolor="#cff1ff" >
       <th width="60" rowspan="2" align="center">单位</th>
       <th  colspan="4">污染源家数</th>
       <th  colspan="1">环境监察人员信息库</th>
       <th  colspan="4">实施“双随机”监管污染源家次</th>
       <th rowspan="2">随机抽查信息公开数量</th>
       <th  rowspan="2">随机抽查发现并查处违法问题数量</th>
     </tr>
     <tr bgcolor="#cff1ff">
		<td align="center">
						总数
		</td>
		<td align="center">
						一般排污单位家数
		</td>
		<td align="center">
						重点排污单位家数
		</td>
		<td align="center">
						特殊监管对象数量
		</td>
		<td align="center">
						环境监察人员数量
		</td>
		<td align="center">
						总家次
		</td>
		<td align="center">
						一般排污单位家次
		</td>
		<td align="center">
						重点排污单位家次
		</td>
		<td align="center">
						特殊监管对象家次
		</td>
	</tr>
    <c:forEach items="${cfs}" var="checkedCountForm" varStatus="str" begin="0">
					<tr>
						<td align="center">
							${checkedCountForm.danwei }
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${checkedCountForm.wryzs }"></fmt:formatNumber>
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${checkedCountForm.ybwry }"></fmt:formatNumber>
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${checkedCountForm.zdwry }"></fmt:formatNumber>
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${checkedCountForm.tswry }"></fmt:formatNumber>
						</td>
						<td align="center">
							<fmt:formatNumber pattern="0" value="${checkedCountForm.jcrysl }"></fmt:formatNumber>
						</td>
						<td align="center">
						   <a style="color:#00a2d9;cursor: pointer;" onclick="toZjcList('${checkedCountForm.curAreaId }','${checkedCountForm.curYear }','${checkedCountForm.curQuarter }','0')"><fmt:formatNumber pattern="0" value="${checkedCountForm.zjc }"></fmt:formatNumber></a>
						</td>
						<td align="center">
							<a style="color:#00a2d9;cursor: pointer;" onclick="toZjcList('${checkedCountForm.curAreaId }','${checkedCountForm.curYear }','${checkedCountForm.curQuarter }','1')"><fmt:formatNumber pattern="0" value="${checkedCountForm.ybjc }"></fmt:formatNumber></a>
						</td>
						<td align="center">
							<a style="color:#00a2d9;cursor: pointer;" onclick="toZjcList('${checkedCountForm.curAreaId }','${checkedCountForm.curYear }','${checkedCountForm.curQuarter }','2')"><fmt:formatNumber pattern="0" value="${checkedCountForm.zdjc }"></fmt:formatNumber></a>
						</td>
						<td align="center">
							<a style="color:#00a2d9;cursor: pointer;" onclick="toZjcList('${checkedCountForm.curAreaId }','${checkedCountForm.curYear }','${checkedCountForm.curQuarter }','3')"><fmt:formatNumber pattern="0" value="${checkedCountForm.tsjc }"></fmt:formatNumber></a>
						</td>
						<td align="center">
							<a style="color:#00a2d9;cursor: pointer;"  onclick="toZjcList('${checkedCountForm.curAreaId }','${checkedCountForm.curYear }','${checkedCountForm.curQuarter }','0')"><fmt:formatNumber pattern="0" value="${checkedCountForm.gksl }"></fmt:formatNumber></a>
						</td>
						<td align="center">
							<a style="color:#00a2d9;cursor: pointer;" onclick="toWfLawobjList('${checkedCountForm.curAreaId }','${checkedCountForm.curYear }','${checkedCountForm.curQuarter }')"><fmt:formatNumber pattern="0" value="${checkedCountForm.wfsl }"></fmt:formatNumber></a>
						</td>
						
					</tr>
	</c:forEach>
   </table>
</div>
<iframe name="download" id="download" src="" style="display: none"></iframe>
</body>
</html>


<script language="JavaScript">

////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////
$(document).ready(function(){
	//区域
	$('#areaid').combobox({
		height:34,
		url : 'queryAreaComboWithCur.json?fid='+Math.random(),
		valueField : 'id',
		textField : 'name',
		onSelect: function (record) {//做区域部门的联动选择
        }
	});
	//年度下拉
	var year=new Date().getFullYear(); 
	$('#year').combobox({
		height:34,
		data:[{'id':year-2,'name':year-2+'年'},{'id':year-1,'name':year-1+'年'},{'id':year,'name':year+'年'},{'id':year+1,'name':year+1+'年'},
			  {'id':year+2,'name':year+2+'年'},{'id':year+3,'name':year+3+'年'},
			  {'id':year+4,'name':year+4+'年'}],
		editable:false,
		valueField:'id',
		textField:'name',
		onSelect: function(param){
		}
	});
	$('#year').combobox('setValue', year);
	//季度下拉
	$('#quarter').combobox({
		height:34,
		data:[{'id':"0",'name':"全部"},{'id':"1",'name':"第一季度"},
		      {'id':"2",'name':"第二季度"},{'id':"3",'name':"第三季度"},
		      {'id':"4",'name':"第四季度"}
		      ],
		editable:false,
		valueField:'id',
		textField:'name',
		onSelect: function(param){
		}
	});
	$("#questionContainer").width($(window).width());
	
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
	$('#year').combobox('setValue', year);
	$('#quarter').combobox('setValue', quarter);
});

$('#J-form-export').click(function(){
	var year = $('#year').combobox('getValue');
	var quarter = $('#quarter').combobox('getValue');
	var areaid = $('#areaid').combotree("getValue");
	$('#download').attr('src','exportCheckedCountForm.json?areaid='+areaid+'&quarter='+quarter+'&year='+year);
});

function toZjcList(curAreaId,curYear,curQuarter,leixing){
	//alert(curAreaId+","+curYear+","+curQuarter);
	var href = 'queryZjcList.htm?areaid='+curAreaId+'&year='+curYear+'&quarter='+curQuarter+'&type='+leixing; 
  	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	parent.layerIframe(2,href,"统计列表",width,height);
	//All.ShowModalWin(, '统计列表');

}
function toWfLawobjList(curAreaId,curYear,curQuarter){
	var href = 'queryWfLawobjList.htm?areaid='+curAreaId+'&year='+curYear+'&quarter='+curQuarter; 
  	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	parent.layerIframe(2,href,"统计列表",width,height);
	//All.ShowModalWin(, '统计列表');

}
</script>
