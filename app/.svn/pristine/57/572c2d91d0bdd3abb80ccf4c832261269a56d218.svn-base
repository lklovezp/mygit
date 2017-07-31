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
<script type="text/javascript" src="${layer}/layer.js"></script>
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
<link href="${app}/resetCSS.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/easyUIReset.css" rel="stylesheet" type="text/css"/>
       

</head>
<body>
<div id="title" class="breadClumbs"> 查询统计&nbsp;&gt;&nbsp;${title}</div>
	    
        <div class="searchDiv" id="searchDiv" style="height: 65px;">
		<form id="queryForm" action="queryLogTimesList.json" method="post">
			<input type="hidden" id="page" name="page" value="${page}" /> <input
				type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
             
             <table width="100%" class="noBorder" border="0" cellpadding="0" cellspacing="0">
                 <tr height="72" align="center">
                      <td colspan="4" align="center">
                      	<input type="text" class="i-text" id="name" name="name"style="width:50%;"/>
                      
                      	<input type="submit" class="input_btnImgBlue" id="query" value="查　询"/>
                      	<a class="moreSearch" id="moreSearch">更多查询条件</a>
                      </td>
                  </tr>
                  <tr height="72">
                      <th>区域：</th>
					<td><input class="y-text" id="areaId" name="areaId"
						type="text" value="${areaId}" />
					</td>
					<th>登录时间：</th>
					<td>
					 <input type="text" class="y-dateTime" id="starttime" name="starttime" value="${starttime }" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\',{d:-1});}'})"/> 
                                                   至 <input type="text" class="y-dateTime"  id="endtime" name="endtime" value="${endtime }" onclick="WdatePicker({minDate:'#F{$dp.$D(\'starttime\',{d:1});}'})"/>
					</td>
                  </tr>
                 
              </table>
		</form>
		 </div>
	</div>
   <div id="daochu"style="width:95%; margin:-23px  auto 0px;" class="t-r">
  <input type="button" id="J-form-export" class="input_btnColorlBlue"  value="导出" />
   </div>
         <div class="divContainer" id="rbblist" style=" width:95%; margin:16px auto 0px;">
             <table id="data" fit="true" ></table>
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
function initH(){
	var hh=$(window).height() - $("#searchDiv").outerHeight()-$("#title").outerHeight()- $("#daochu").outerHeight();
	$("#rbblist").height(hh);
}
initH();
	$(document)
			.ready(
					function() {
						$("#infectlist").height(
								$(window).height()
										- $("#questionContainer").outerHeight()
										- $("#divTitle").outerHeight());
						$("#infectlist").width($(window).width());
						$("#questionContainer").width($(window).width());
						$('#areaId').combobox({
							height:34,
							required : true,
							url : 'queryAreaComboWithCur.json',
							valueField : 'id',
							textField : 'name'
						});
						$('#data')
								.datagrid(
										{
											nowrap : false,
											striped : true,
											collapsible : true,
											singleSelect : true,
											remoteSort : false,
											pagination : true,
											rownumbers : true,
											fitColumns : true,
											url : 'queryLogTimesList.json?areaId=${areaId}&starttime=${starttime}&endtime=${endtime}',
											onLoadSuccess : function(data) {
												$(this).datagrid('doCellTip', {
													'max-width' : '300px',
													'delay' : 500
												});
											},
											columns : [ [ {
												field : 'id',
												hidden : true
											}, {
												field : 'orgname',
												title : '部门名称',
												align : 'left',
												halign : 'center',
												width : 100
											}, {
												field : 'name',
												title : '登录人姓名',
												align : 'center',
												halign : 'center',
												width : 100
											}, {
												field : 'count',
												title : '登录次数',
												align : 'center',
												halign : 'center',
												width : 100
											}, {
												field : 'operate',
												title : '操作',
												align : 'center',
												halign : 'center',
												width : 100
												 } ] ]
										});
						initPager();
					});

	$("#J-from-reset").click(function() {
		$("#queryForm").form("reset");
	});

	function initPager() {
		var p = $('#data').datagrid('getPager');
		$(p).pagination({
			onSelectPage : function(pageNumber, pageSize) {
				$('#page').val(pageNumber);
				$('#pageSize').val(pageSize);
				$(this).pagination('loading');
				$('#queryForm').submit();
				$(this).pagination('loaded');
			}
		});
	}

	$('#queryForm').submit(function() {
		$("#queryForm").ajaxSubmit({
			success : function(data) {
				$('#data').datagrid('loadData', data)
			}
		});
		return false;
	});
	function info(t){
		var id = t.id;
		var starttime = $('#starttime').val();
		var endtime = $('#endtime').val();
		var width=screen.width * 0.8;
	  	var height=screen.height * 0.8-50;
	  	var title='登录详情';
		//All.ShowModalWin('logTimesListDetails.htm?id='+id+'&starttime='+starttime+'&endtime='+endtime, '登录详情');
		//window.location.href='logTimesListDetails.htm?id='+id+'&starttime='+starttime+'&endtime='+endtime;
		parent.layerIframe(2,'logTimesListDetails.htm?id='+id+'&starttime='+starttime+'&endtime='+endtime,title,width,height);
	}
	
	$('#J-form-export').click(
			function() {
				var areaId=$('#areaId').combobox('getValue');
				var name =$('#name').val();
				var starttime = $('#starttime').val();
				var endtime = $('#endtime').val();
				$('#download').attr(
						'src',
						'exportLogTimesList.json?areaId=' + areaId
								+ '&name=' + encodeURIComponent(name)
								+ '&starttime=' + starttime + '&endtime=' + endtime);
			});
	//数据表格自使用宽度
	$(window).resize(function(){
	    $('#data').datagrid("resize");
		initH();
	});
</script>