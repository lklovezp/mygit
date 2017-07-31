<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${title }</title>
   <script type="text/javascript" src="/app/static/app/js.js"></script>
	<script type="text/javascript" src="/app/static/app/data.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="/app/static/jquery/jquery.js"></script>
    <script type="text/javascript" src="/app/static/layer/layer.js"></script>
    <script type="text/javascript" src="/app/static/jquery/jquery.validate.js"></script>
	<script type="text/javascript" src="/app/static/jquery/jquery.form.js"></script>
	<!-- colorbox -->
	<script type="text/javascript"	src="/app/static/colorbox/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" type="text/css" href="/app/static/colorbox/colorbox.css"/>
    <!--easyui-->
    <link rel="stylesheet" href="/app/static/easyui/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="/app/static/easyui/themes/icon.css" type="text/css">
    <link href="/app/static/app/easyUIReset.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/app/static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/app/static/easyui/locale/easyui-lang-zh_CN.js"></script>
    <!--poshytip-->
    <link rel="stylesheet" href="/app/static/poshytip/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
    <!--派发列表-->
    <link href="/app/static/app/css/taskDispatch.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/app/static/poshytip/jquery.poshytip.min.js"></script>
    <script type="text/javascript" src="/app/static/common/All.js"></script>
    <!--时间插件 my97-->
    <script type="text/javascript" src="/app/static/My97DatePicker/WdatePicker.js"></script>
    <link href="/app/static/app/CSSReset.css" rel="stylesheet" type="text/css" />
    <link href="/app/static/app/common.css" rel="stylesheet" type="text/css" />
    <link href="/app/static/app/css/easyUIReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
<div id="title" class="breadClumbs"> 查询统计&nbsp;&gt;&nbsp;${title}</div>
   <div id="search">
		<form id="queryForm" action="queryPwdwslList.json" method="post">
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" /> 
				 <table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style=" margin:1px auto">
					<tr align="center">
						 <td>  
							所属行政区：
						    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input class="y-text" type="text" name="regionId" id="ssxzq"/>
						    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							执法对象类型：
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input class="y-text" type="text" id="lawobjtype" name="lawobjtype" />
					        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="submit" id="query"  class="input_btnImgBlue" value="查　询" onclick="hideSearchForm()"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" id="J-form-export" class="input_btnImgBlue"  value="导 出" />
							
					</td>
					</tr>
				</table>
			</form>
		</div>
          <div id="daochu" style="width:95%; margin:-23px  auto 0px; height:25px;" class="t-r">
        <!--  <input type="button" id="J-form-export" class="input_btnColorlBlue"  value="导出" />
        --></div>
        <div class="divContainer" id="rbblist" style=" width:98%; margin:1px auto 0px;">
        <table id="data" fit="true" ></table>
      </div>
		<iframe name="download" id="download" src="" style="display: none"></iframe>
	</body>
</html>

<script language="JavaScript">

////////////////////////////////////////////////////////////////////////////////////
function initH(){
	var hh=$(window).height() - $("#title").outerHeight()-$("#search").outerHeight()-$("#daochu").outerHeight();
	$("#rbblist").height(hh);
}
initH();

//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});

$(document).ready(function(){

	$('#ssxzq').combotree({
		height:34,
		url:'regionTree.json',
		onBeforeSelect:function(node){
			if(node.state){        
				$('#ssxzq').tree('unselect');    
			} 
		}
	});
	

	$('#lawobjtype').combotree({
			height:34,
			type:"post",
			url:'lawtypeTree.json'
		});
	
	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
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
		url:'queryPwdwslList.json',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'regionid',hidden:true},
			{field:'lawobjtypecode',hidden:true},
			{field:'regionname',title:'所属行政区', align:"center", halign:'center',width:100},
			{field:'lawobjtypename',title:'执法对象类型', align:"center", halign:'center',width:40},
			{field:'pwdwsl',title:'排污单位数量', align:"center", halign:'center',width:30,
				formatter:function(value,rowData,rowIndex){  
					if(value=='' || value=='0'){
						return value;
					}else{
						return '<a class=\"b-link\" onclick=\"info(\''+rowData.regionid+'\',\''+rowData.lawobjtypecode+'\',\''+rowData.lawobjtypename+'\')\" >'+value+'</a>';  
		    		}
		        }
			},
		    {field:'wrwlxsl',title:'污染源类型数量', align:"center", halign:'center',width:30},
			{field:'pfksl',title:'排放口数量', align:"center", halign:'center',width:30},
			{field:'fzsssl',title:'污染源防治设施数量', align:"center", halign:'center',width:30},
		]]
	});
	initPager();
});

$('#queryForm').submit(function(){  
    $("#queryForm").ajaxSubmit({
   	  success: function(data){
   	      $('#data').datagrid('loadData',data)
	      }
	 });
   return false;  
});

function info(regionid,lawobjtype,lawobjname){
	//All.ShowModalWin('queryLawobjList.htm?regionid='+regionid+'&lawobjtype='+lawobjtype, '执法对象列表');
	//alert(lawobjtype);
	//alert(regionid);
	//alert(lawobjname);
	
	window.location.href='zfdxtjbbList.htm?regionid='+regionid+'&lawobjtypeid='+lawobjtype+'&title='+lawobjname;
}

$('#J-form-export').click(function(){
alert($('#ssxzq').combotree('getValue'));
alert($('#lawobjtype').combobox('getValue'));
	$('#download').attr('src','exportStatisticalList?regionId='+$('#ssxzq').combotree('getValue')+'&lawobjtype='+$('#lawobjtype').combobox('getValue'));
});
</script>