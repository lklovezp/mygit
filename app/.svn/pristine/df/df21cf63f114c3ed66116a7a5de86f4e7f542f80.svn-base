<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="${app}/js.js"></script>
	<script type="text/javascript" src="${app}/data.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
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
    <style>
    .dataTable th{text-align: right;}
    </style>
	</HEAD>

	<body>
	<div class="breadClumbs"> 系统管理&nbsp;&gt;&nbsp;${title}</div>
		<div id="searchMask" class="searchMask" style="top:30px"><p>点击查看更多查询条件</p></div>
        <div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;top:30px">
			<form id="queryForm" action="queryRecordList.json" method="post">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
				<table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
						<th width="100">
							违法类型：
						</th>
						<td width="250">
							<input class="y-text"  type="text" id="wflx" name="wflx"/>
						</td>
						<th width="100" colspan="">
							问题项内容：
						</th>
						<td colspan="3">
							<input class="y-text"  type="text" id="content" name="content"/>
						</td>
					</tr>
					<tr>
						<th>
							当前版本：
						</th>
						<td>
							<input class="y-text" type="text" id="iscurver" name="iscurver"/>
						</td>
						<th>
							笔录类型：
						</th>
						<td>
							<input class="y-text"  type="text" id="kcxwbj" name="kcxwbj"/>
						</td>
						<th width="100">
							是否可用：
						</th>
						<td>
							<input class="y-text" type="text" id="isActive" name="isActive"/>
						</td>
						<tr>
					</tr>
						<td colspan="6" align="center">
							<input type="submit" id="query"  class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
                            <input type="button" id="J-from-reset" class="queryOrange" value="重　置"/>
						</td>
					</tr>
				</table>
			</form>
			<div class="closeBtn">收起</div>
		</div>
	  <div id="layer1" class="layer"></div>
            <h1 id="divTitle" class="h1_1 topMask" style="padding-top: 10px;">${title}</h1>
			<div style="width:95%; margin:-7px  auto 0px;" class="t-r">
            <input type="button" class="bTn btnbgAdd directory_comwidth" id="new" value="新建" />
           </div>
		 <div class="divContainer" id="rbblist" style=" width:98%; margin:10px auto 0px;">
			<table id="data" fit="true"></table>
		</div>
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
function initH(){
	var hh=$(window).height() - $("#divTitle").outerHeight()-40;
	$("#rbblist").height(hh);
}
initH();
//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});

$(document).ready(function() {
		$('#wflx').combotree({
			height:34,
			type:"post",
			multiple: true,
			url:'illegalTypeList.json'
		});
		
		$('#kcxwbj').combobox({
			height:34,
			data:[{'id':'0','name':'勘察笔录'},{'id':'1','name':'询问笔录'}],
			editable:false,
			valueField:'id',
			textField:'name'
		});

		$('#iscurver').combobox({
			height:34,
			data:[{'id':'Y','name':'是'},{'id':'N','name':'否'}],
			editable:false,
			valueField:'id',
			textField:'name'
		});
		
		$('#isActive').combobox({
			height:34,
			data:[{'id':'Y','name':'是'},{'id':'N','name':'否'}],
			value : 'Y',
			editable:false,
			valueField:'id',
			textField:'name'
		});
		$("#infectlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
		$("#infectlist").width($(window).width());
		$("#questionContainer").width($(window).width());

		$('#data').datagrid( {
			nowrap: false,
			striped: true,
			collapsible:true,
			singleSelect:true,
			remoteSort:false,
			pagination : true,
			rownumbers : true,
			fitColumns:true,
			url : 'queryRecordList.json',
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
				field : 'content',
				title : '问题项内容',
				align : 'left',
				halign : 'center',
				width : 100
			}, {
				field : 'kcxwbj',
				title : '笔录类型',
				align : 'center',
				halign : 'center',
				width : 30
			}, {
				field : 'isdel',
				title : '是否可删除',
				align : 'center',
				halign : 'center',
				width : 30
			}, {
				field : 'orderby',
				title : '序号',
				align : 'center',
				halign : 'center',
				width : 30
			}, {
				field : 'operate',
				title : '操作',
				align : 'center',
				halign : 'center',
				width : 30
			} ] ]
		});
		initPager();
	});
	// 新建
	$("#new").click(function() {
		//All.ShowModalWin('editRecord.htm', '新建笔录问题', 600, 500);
		//refresh();
		
		parent.layer.open({
            type: 2,
            title: '新建笔录问题',
            area: ['800px', '630px'],
            content: 'editRecord.htm', 
            end: function () {
                refresh();
            }

        });
	});

	$("#J-from-reset").click(function() {
		$("#queryForm").form("reset");
	});
	
	$('#queryForm').submit(function(){
		$("#queryForm").ajaxSubmit({
			success: function(data){
				$('#data').datagrid('loadData',data);
			}
		});
		return false;
	});

	function edit(curObj){
		//All.ShowModalWin('editRecord.htm?id='+curObj.id, '编辑笔录问题', 600, 500);
		//refresh();
		parent.layer.open({
            type: 2,
            title: '编辑笔录问题',
            area: ['800px', '630px'],
            content: 'editRecord.htm?id='+curObj.id, 
            end: function () {
                //location.reload();
            	refresh();
            }
        });
	}

	function del(curObj){
		/*$.messager.confirm('笔录问题管理', '确定要删除当前笔录问题吗？', function(r){
			if (r){
				$.ajax({
					url: "removeRecord.json?id="+curObj.id,
					success:function(data){
						alert(data.msg);
						refresh();
					}
				});
			}
		});*/
		var index=layer.confirm('确定要删除当前笔录问题吗？', {
	     	icon: 3, 
	        title:'笔录问题管理'
	     }, function(index){
	    	 $.ajax({
					url: "removeRecord.json?id="+curObj.id,
					success:function(data){
						alert(data.msg);
						refresh();
					}
				});
	        layer.close(index);
	     },function(index){
	        layer.close(index);
	     });
	}
</script>