<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>档案管理</title>
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
	<link href="${app}/layout.css" rel="stylesheet" type="text/css" />
    <link href="${app}/common.css" rel="stylesheet" type="text/css" />
     <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
    <script type="text/javascript" src="${easyui}/datagrid-groupview.js"></script>
	<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
    <!--poshytip-->
    <link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
    <script type="text/javascript" src="${poshytip}/jquery.poshytip.min.js"></script>
    <!--时间插件 my97-->
    <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
    <!--档案管理-->  
    <link href="${app}/css/task.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${common}/All.js"></script>
     <style type="text/css">
	  .bTn_distance {padding: 5px 12px;}
	  .fileTable{border-collapse:collapse;}
	  .fileTable th,.fileTable td{ padding:9px 5px; font-size:14px;}
	  .fileTable th{ text-align:right; font-weight:normal;}
	  .searchTable{ width:100%;  background:#f5f5f5; }
	  .searchForm{ width:100%; min-height:60px;_height:60px; left:0; top:0; z-index:3; box-shadow:0px 2px 2px #a0a9ae;}
    </style>
</head>
<body>
<!-- <div id="searchMask" class="searchMask"><p>点击查看更多查询条件</p></div> -->
<!-- <div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;"> -->
   <form id="queryForm" method="post" action="sfwQuery.json">
   	<input type="hidden" id="page" name="page" value="${page}" />
	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
	<div id="searchTable" class="searchTable" style="padding-top:10px;padding-bottom:21px;">
		<table class="fileTable" width="840" border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
	         <tr>
	            <td colspan="2"><input type="text" class="y-text" style="width:500px;" id="title" name="title" jzTips="请输入文件标题/文号"/></td>
	            <td align="center"><input type="submit" class="queryBlue" value="查　询"/>
	            </td>
	            <td align="center" id="searchMask" style="font-size:14px;color:#3393ff;" nowrap>更多查询条件&nbsp;<img src="${app}/images/down.png"/>
	            </td>
	         </tr>
	         <tr id="searchForm">
			    <td colspan="4">
			    	<table width="100%" border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
				       <tr>
				           <th width="11.3%" nowrap>收文/发文</th>
				           <td width="22%"><input type="text" class="y-text" id="type" name="type"/></td>
				           <th width="11.3%" nowrap>文档分类</th>
				           <td width="22%"><input type="text" class="y-text" id="sourcepid" name="sourcepid"/></td>
				           <th width="11.3%" nowrap>文档子类型</th>
				           <td width="22%"><input type="text" class="y-text" id="sourceid" name="sourceid"/></td>
				       </tr>
				       <tr>
				           <!-- <th width="11.3%">文件标题</th>
				           <td width="22%"><input type="text" class="y-text" id="title" name="title"/></td> -->
				           <th nowrap>收发文日期</th>
				           <td colspan="3" nowrap><input type="text" style="width: 162px;" class="y-dateTime" id="sfwdate" name="starttime"  onclick="WdatePicker()"> ~
				           <input type="text" style="width: 162px;" class="y-dateTime" id="sfwdate" name="endtime"  onclick="WdatePicker()"></td>
				           <th nowrap>状态</th>
						   <td><input class="y-text" type="text" id="isActive" name="isActive" value="Y"/></td>
				       </tr>
					  <!--  <tr>
							<th>文号</th>
							<td><input type="text" class="y-text" id="number" name="number"/></td>
							
						</tr> -->
						<!-- <tr>
							<td align="center" colspan="6">
								<input type="submit" id="query"  class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
								<input type="reset" class="queryOrange" value="重　置"/>
							</td>
						</tr> -->
		    	 	</table>
		    	 </td>
		      </tr>
	    </table>
     <!-- <div class="closeBtn" id="closeBtn">收起</div> -->
      <!-- <div id="layer1" class="layer"></div> -->
     </div> 
   </div>
   </form>
<!-- </div> -->
<h1 id="title" class="h1_1 topMask" style="padding-top:0px;">档案管理</h1>
<div id="addForm" style="width:95%; margin:-7px  auto 0px;" class="t-r">
    <input type="button" class="bTn orange bTn_distance"  id="taskDivision" value="录入" onclick="showTable()" value="录入" />
    <input type="button" class="bTn blue bTn_distance"  id="daoru" value="批量导入" />
</div>
<div class="divContainer" id="rbblist" style=" width:100%; margin:10px auto 0px;">
    <table id="data" fit="true" ></table>
</div>
<div onclick="hideTable()"></div>

<script type="text/javascript">
var searchMask=$("#searchMask");
var searchForm=$("#searchForm");
searchForm.hide();
//var layer1=$("#layer1");
//layer1.hide();
/* var hideSearchBtn=searchForm.find(".closeBtn"); */
//function hideSearchForm(){
//	searchForm.hide();
	//layer1.hide();
//}
function showSearchForm(){
	if(searchForm.is(':hidden')){
		searchForm.show();
	}else{
		searchForm.hide();
	}
	//layer1.show();
}
searchMask.bind("click",showSearchForm);
//hideSearchBtn.bind("click",hideSearchForm);

/////////////////////////////
function initH(){
	var hh=$(window).height() - $("#title").outerHeight()-$("#addForm").outerHeight()-90;
	$("#rbblist").height(hh);
}
initH();
//数据表格初始化
$('#queryForm').submit(function() {
		$("#queryForm").ajaxSubmit( {
			success : function(data) {
				$('#data').datagrid('loadData', data)
			}
		});
		return false;
});
/////////////////////////////

    //收文/发文
	$('#type').combobox({
			height:'34',
			url:'sfwlxList.json',
			valueField:'id',
			textField:'name',
			onChange:function(newValue,oldValue){
					$("#sourcepid").combobox('setValue', '');
					$("#sourceid").combobox('setValue', '');
        	        $('#sourcepid').combobox('reload', 'wdflQuery.json?type='+newValue); 
        	    }
	});
	//文档分类
	$('#sourcepid').combobox({
			height:'34',
			url:'wdflQuery.json?type='+$('#type').combobox('getValue'),
			valueField:'id',
			textField:'name',
			onChange:function(newValue,oldValue){
					$("#sourceid").combobox('setValue', '');
        	        $('#sourceid').combobox('reload', 'wdzlxQuery.json?pid='+newValue);
        	}
	});
	//文档子类型
	$('#sourceid').combobox({
			height:'34',
			url:'wdzlxQuery.json?pid='+$('#sourcepid').combobox('getValue'),
			valueField:'id',
			textField:'name'
	});

    $(document).ready(function(){
    	//数据表格初始化
	    $('#data').datagrid({
	        nowrap: false,//自动截取
	        striped: true,//显示条纹
	        collapsible:true,//是否添加折叠按钮
	        singleSelect:true,//单选模式，只允许选取一行
	        fitColumns:true,//自适应列宽
	        remoteSort:false,//是否远程排序
	        url:'sfwQuery.json',//请求数据的超链接地址
	        pageSize : 10,//默认选择的分页是每页10行数据
	        pageList: [5,10,15,20],//可以设置每页记录条数的列表
	        onLoadSuccess:function(data){
	            //datagrid鼠标经过提示单元格内容(需要在easyui.js中写扩展的doCellTip方法)
	            $(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
	        },
	        columns:[[//设置表头
				{field:'number',title:'文号',width:2,hidden:true},
	            {field:'title',title:'文件标题',width:5,
                    formatter:function(value,row,index){
                        return "<span style='font-size: 16px;font-weight:200;'><a onclick='info(this)' style='text-decoration:underline;' id='"+row.id+"'>"+value+"</a></span>";
                    }
                },
	            {field:'type',title:'收文/发文',align:'center',width:2,hidden:true},
	            {field:'sourcepname',title:'文档分类',align:'center',width:2},
	            {field:'sourcename',title:'文档子类型',align:'center',width:2},
	            {field:'sfwdate',title:'日期',align:'center',width:2,hidden:true},
	            {field:'operate',title:'操作',align:'center',width:2},
	        ]],
	        onDblClickRow:function(rowIndex,rowData){//双击某一行，查看详情
	
	        },
	        pagination:true,//是否添加底部的分页
	        rownumbers:true,//是否显示左侧的行序号
            groupField:'number',
            view: groupview,
            groupFormatter:function(value, rows){
                return "<table><tr><td style='width:120px;' nowrap>"+rows[0].sfwdate+"</td><td style='width:200px;font-weight:200;' nowrap>文号："+rows[0].number+"</td><td style='width:145px;font-weight:200;' nowrap>类型："+rows[0].type+"</td><tr></table>";
//                value + ' - ' + rows.length + ' Item(s)';
            }
	    });
		initPager();
	});
	 //数据表格每行的“下载”
    function download(){}
    //数据表格每行的“预览”
    function look(){}
    //数据表格每行的“编辑”
    function edit(){}
    //数据表格每行的“危化品”
    function danger(){}
    //数据表格每行的“删除”
    function getRowIndex(target){
        var tr = $(target).closest('tr.datagrid-row');
        return parseInt(tr.attr('datagrid-row-index'));
    }
    //数据表格自适应宽度
    var taskTable=$("#taskTable");
    var dWidth=taskTable.width();
    function initPostition(){
        var winW=$(window).width();
        var left=(winW-dWidth)/2+"px";
        taskTable.css({"left":left});
    }
    initPostition();
    var taskBtn=$("#taskDivision");
    var taskTable=$("#taskTable");
    var layer1=$("#layer1");
    layer1.hide();
    taskTable.hide();
    var closeBtn=taskTable.find(".r_closeBtn");
    function hideTable(){
        taskTable.hide();
        layer1.hide();
    }
    //数据表格宽度自适应
    $(window).resize(function(){
	    $('#data').datagrid("resize");
		initPostition();
		initH();
    });
//添加   
function showTable(){
	parent.layer.open({
        type: 2,
        title: '文件录入',
        area: ['1200px', ($(window).height()+80)+'px'],
        content:'sfwAdd.htm', 
        end: function () {
            refresh();
        }
    });
}  
//修改
function edit(obj){
	parent.layer.open({
        type: 2,
        title: '编辑文件信息',
        area: ['1200px', ($(window).height()+80)+'px'],
        content: 'sfwAdd.htm?id='+$(obj).attr("id"), 
        end: function () {
            refresh();
        }
    });
}
//查看
function info(obj){
	parent.layer.open({
        type: 2,
        title: '查看文件信息',
        area: ['1200px', ($(window).height()+80)+'px'],
        content:'sfwInfo.htm?id='+$(obj).attr("id"),
        end: function () {
            refresh();
        }
    });
}  
//删除
function del(obj) {
	var index=layer.confirm('确定删除此文件信息吗？', {
     	icon: 3, 
        title:'删除文件'
     }, function(index){
    	 $.ajax( {
				url : "delSfw.json?id="+obj.id,
				success : function(data) {
					refresh();
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}

$("#daoru").click(function(){
	layer.open({
            type: 2,
            title: '收发文导入',
            area: ['600px', ($(window).height()-50)+'px'],
            content: 'importsfFilePage.htm', 
            end: function () {
            	refresh();
            }
        });
});
$('#isActive').combobox({
		height:34,
		url:'ztList.json',
		valueField:'id',
		textField:'name'
});

function refresh(){
	$('#data').datagrid("reload");
}
</script>
</body>
</html>