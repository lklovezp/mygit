<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${title }</title>
	<script type="text/javascript" src="${app}/js.js"></script>
	<script type="text/javascript" src="${app}/data.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${layer}/layer.js"></script>
	<!-- colorbox -->
	<script type="text/javascript"	src="${colorbox}/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css"/>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
    <!--poshytip-->
    <link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
    <!--派发列表-->
    <link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${poshytip}/jquery.poshytip.min.js"></script>
    <script type="text/javascript" src="${common}/All.js"></script>
    <!--时间插件 my97-->
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
    <link href="${app}/common.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div class="breadClumbs"> 系统管理&nbsp;&gt;&nbsp;${title}</div>
        <div id="searchForm">
			<form id="queryForm" method="post" action="specialLawobjlist.json">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" />
				<table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
						<th width="45" align="right">
							年份
						</th>
						<td >
							<input class="y-text" type="text" id="year" name="year"/>
						</td>
						
						<th width="125px">
							执法对象名称
						</th>
						<td>
							<input class="y-text"  type="text" id="lawobjname" name="lawobjname"/>
						</td>
						<th width="45" align="right">
							季度
						</th>
						<td  >
							<input class="y-text" type="text" id="quarter" name="quarter"/>
						</td>
						<th>
							执法对象类型
						</th>
						<td  >
							<input class="y-text" type="text" id="lawobjtype" name="lawobjtype"/>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center">
							<input type="submit" id="query"  class="queryBlue" style="margin: 0px 9px;" value="查　询" onclick="hideSearchForm()"/>
                             <input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
						</td>
						<td>
							<input id="toSpecialLawobj" class="bTn directory_save directory_comwidth" style="margin: 0px 9px;" type="button" title="获取特殊监管企业" value="获取特殊企业">
						</td>
						<td>
						    <input id="changeLawobj" class="bTn directory_save directory_comwidth" type="button" title="设置特殊监管企业" value="设置特殊企业">
						</td>
					</tr>
				</table>
			</form>
			</div>
			
		  <div class="divContainer" id="rbblist" style=" width:100%; margin:10px auto 0px;">
			<table id="data" fit="true"></table>
		</div>
	</body>
</html>

<script language="JavaScript">

////////////////////////////////////////////////////////////////////////////////////
function initH(){
	var hh=$(window).height() -154;
	$("#rbblist").height(hh);
}
initH();
//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});
function del(curObj) {
	var index=layer.confirm('确定要移出当前特殊企业吗？', {
     	icon: 3, 
        title:'特殊监管企业管理'
     }, function(index){
    	 $.ajax( {
				url : "delSpecialLawobj.json?id=" + curObj.id,
				success : function(data) {
					if(data.state){
						var tishi=layer.alert(data.msg,{
					     	title:'信息提示',
					        icon:1,
					        shadeClose:true,
					     },
					     function(){
					    	refresh();
					        layer.close(tishi);
					     }
					     );
					}else{
						$.messager.alert('提示:',data.msg);
					}
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}



$('#changeLawobj').click(function(){
	layer.open({
        type: 2,
        title: '设置特殊企业',
        area: ['400px', '300px'],
        content: "jumpSpecialLawobj.htm", 
        end: function () {
        	$('#data').datagrid('reload');
        }

    });
});

$('#queryForm').submit(function() {
	$("#queryForm").ajaxSubmit( {
		success : function(data) {
			$('#data').datagrid('loadData', data)
		}
	});
	return false;
});

$("#J-from-reset").click(function() {
	$("#queryForm").form("reset");
});



$(document).ready(function() {
	$.ajaxSetup({cache:false});
	$("#specialLawobjlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
	var year=new Date().getFullYear(); 
	//年度下拉
	$('#year').combobox({
		height:34,
		data:[{'id':year,'name':year+'年'},{'id':year+1,'name':year+1+'年'},
			  {'id':year+2,'name':year+2+'年'},{'id':year+3,'name':year+3+'年'},
			  {'id':year+4,'name':year+4+'年'}],
		editable:false,
		valueField:'id',
		textField:'name'
	});
	//季度下拉
	$('#quarter').combobox({
		height:34,
		url:'quarterList.json',
		editable:false,
		valueField:'id',
		textField:'name'
	});
	//执法对象类型下拉框
	$('#lawobjtype').combobox({
		height:34,
		url:'dicList.json?type=5',
		valueField:'id',
		textField:'name'
	});
	
	$('#data').datagrid( {
		nowrap : false,
		striped : true,
		collapsible : true,
		singleSelect : true,
		pagination : true,
		rownumbers : true,
		fitColumns:true,
		url : 'specialLawobjlist.json',
		onLoadSuccess : function(data) {
			$(this).datagrid('doCellTip', {
				'max-width' : '300px',
				'delay' : 500
			});
		},
		columns : [ [
			{field : 'year', align:'center',title : '年份', width : 50}, 
			{field : 'quarter', align:'center',title : '季度', width : 50}, 
			{field : 'lawobjname', align:'left',title : '执法对象', width : 230}, 
			{field : 'lawobjtype', align:'center',title : '执法对象类型', width : 50}, 
			{field : 'operate', align:'center',title : '操作', align : 'center', width : 50}
		] ]
	});
	initPager();
	//刷新当前页面
	
});

function refresh(){
	layer.closeAll('iframe');
	$('#data').datagrid('reload');
}
//将上年的违法案件企业添加到特殊企业
$('#toSpecialLawobj').click(function(){
	
	if($('#year').combobox('getValue')==""){
		alert("请选择年份！");
		return false;
	}
	$.ajax( {
		url : "queryForSpecialLawobj.json?year="+$('#year').combobox('getValue'),
		success : function(data) {
			if(data.tsize=="0"){
				$.ajax( {
					url : "toSpecialLawobj.json?year="+$('#year').combobox('getValue'),
					success : function(data) {
						if (data.state) {
							alert(data.msg);
							refresh();
						} else {
							$.messager.alert('设置成功:', data.msg);
						}
					}
				});
			}else{
				alert("本年度已经设置过了！");
			}
			
		}
	});
});
</script>