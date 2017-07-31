<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
	<script type="text/javascript" src="${layer}/layer.js"></script>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <!-- 任务管理 css-->
    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
    <!-- ztree -->
    <link rel="stylesheet" href="${ztree}/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
    <script type="text/javascript" src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${ztree}/js/jquery.ztree.excheck-3.5.js"></script>
    <!-- zTree 修改的样式-->
    <link href="${app}/zTreeReset.css" rel="stylesheet" type="text/css"/>
    <!--执法目录管理-->
    <link rel="stylesheet" href="${app}/css/taskDispatch.css" type="text/css">
    <script type="text/javascript" src="${common}/All.js"></script>
	</HEAD>

	<body>
	<div class="checkup">
		<form id="queryForm" action="queryLawdocList.json?canDel=N" method="post">
		<input id="pid" name="pid" type="hidden"/>
		<input id="pname" name="pname" type="hidden"/>
		<input type="hidden" id="fid" name="fid" value="${fid}" />
		<input type="hidden" id="page" name="page" value="${page}" />
		<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" /> 
		 <h1 id="checkup_header">${title }</h1>
         <div class="clearfix">
        <div id="checkup_con_l">
            <div style="padding: 20px;">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
		  </div>	
			<div id="checkup_con_r" style="overflow-y: auto">
            <div style="width: 96%; margin: 0 auto; margin-top: 10px;" class="checkup_table">
					<table width="100%" class="queryTable" border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
					<tr>
                        <th width="85">标题</th>
                        <td><input type="text" class="y-text" style="width:220px;" name="title" value=""/></td>
                        <th width="78">关键字</th>
                        <td><input type="text" class="y-text" style="width: 220px;" name="keywords" value=""/></td>
                    </tr>
					  <tr>
                        <td align="center" colspan="6">
                            <input type="submit" class="queryBlue" id="query" value="查　询" onclick="hideSearchForm()"/>
                            <input type="reset" class="queryOrange" id="J-from-reset" value="重　置"/>
                        </td>
                      </tr>
					</table>
				</div>
				 <div style="border-top:1px dotted #acacac; text-align:right; margin: 10px 5px 0px; padding-top:10px; padding-bottom:15px;">
				 </div>
				<div class="enfor_document" style=" width:100%;" id="infectlist" >
                <table id="data" fit="true"></table>
               </div>
			</div>
		</div>
		</form>
		<iframe name="download" id="download" src="" style="display: none"></iframe>
		</div>
	</body>
</html>

<script language="JavaScript">
//树菜单配置
var zTree_Menu = null, curMenu = null;
var setting = {
	data: {
		simpleData: {
			enable: true
		}
	},
	 view: {
         showLine: false
     },
	callback: {
		onClick : onClick
	}
};
var menu_=${menu};
//点击菜单事件
function onClick(event, treeId, treeNode, clickFlag) {
	$("#pid").val(treeNode.id);
	$("#pname").val(treeNode.name);
	$('#data').datagrid('reload',{pid:treeNode.id});
}


function download2(id){
	$('#download').attr('src','downloadFile?id='+id);
}

function edit(id){
	All.ShowModalWin('lawdocEdit.htm?id='+id, '查看');
	$('#queryForm').submit();
}

function del(id){
	$.messager.confirm('执法文件管理', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
			  url: "deleteLawdoc.json?id="+id,
			  success:function(data){
				 alert(data.msg);
				 $('#queryForm').submit();
			  }
			});
		}
	});
}


$(document).ready(function(){
	var treeObj = $.fn.zTree.init($("#treeDemo"), setting, menu_);
	var nodes = treeObj.getNodes();
	treeObj.expandNode(nodes[0], true, true, true);
	
	$("#left").height($(window).height() - $("#divTitle").outerHeight() - 10);
	$("#right").height($(window).height() - $("#divTitle").outerHeight() - 10);
	$("#right").width($(window).width() - $("#left").outerWidth() - 20);
	$("#main").width($(window).width() - 20);
	$("#infectlist").height($(window).height() - $("#divTitle").outerHeight() - $("#righttop").outerHeight()  - 8);

	$('#queryForm').submit(function(){  
	    $("#queryForm").ajaxSubmit({
	   	  success: function(data){
	   	      intiTips();
	   	      $('#data').datagrid('loadData',data)
		          initPager();
		      }
		 });
	   return false;  
	});

	$("#add").click(function(){
		var pid = $("#pid").val();
		var pname = $("#pname").val();
		if(pid==''){
			alert("请先选择目录！");
			return;
		}
		All.ShowModalWin('addLawdoc.htm?id='+pid+'&name='+pname, '新建执法文件');
		$('#queryForm').submit();
	});
	
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		fitColumns:true,
		nowrap: false,
		url:'queryLawdocList.json?canDel=N',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'fileid',hidden:true},
			{field:'name',title:'标题', align:"left", halign:'center',width:100},
			{field:'keywords',title:'关键字', align:"left", halign:'center',width:60},
			{field:'dirpath',title:'目录', align:"center", halign:'center',width:90},
			{field:'operate',title:'操作', align:"center", halign:'center',width:40}
		]]
	});
	initPager();
	
	$("#J-from-reset").click(function(){
		$("#queryForm").form("reset");
	});
});
function vheight() {
    var topHeight = $("#checkup_header").height();//h1 80
    var enforHeight = $(".checkup_table").height();
    var xh = $(window).innerHeight() - topHeight - 25;
    var th = xh - enforHeight-46;
    $("#checkup_con_l").height(xh);
    $("#checkup_con_r").height(xh);
    $("#infectlist").height(th);
}
vheight();
///////////////////////////////////////
 //高度自适应
    $(window).resize(function () {
        vheight();
    });

    //数据表格自适应宽度
    $(window).resize(function () {
        $('#data').datagrid("resize");
    });
/////////////////////////////////////////////
function initPager(){
    var p = $('#data').datagrid('getPager');
	$(p).pagination({
				onSelectPage:function(pageNumber, pageSize){
				    $('#page').val(pageNumber);
				    $('#pageSize').val(pageSize);
					$(this).pagination('loading');
					$('#queryForm').submit();
					$(this).pagination('loaded');
				}
	});
}

</script>