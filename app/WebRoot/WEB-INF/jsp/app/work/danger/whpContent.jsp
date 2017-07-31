<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业化学物质情况</title>
<script type="text/javascript" src="${app}/js.js"></script>
<script type="text/javascript" src="${app}/data.js"></script>
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<!--juqery-->
<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<!--poshytip-->
<link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css"type="text/css" />
<script type="text/javascript" src="${poshytip}/jquery.poshytip.min.js"></script>
<!--colorbox-->
<link rel="stylesheet" href="${colorbox}/colorbox.css" type="text/css">
<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
<!--（app）-->
<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
<link href="${app}/main.css" rel="stylesheet" type="text/css" />
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${common}/All.js"></script>
<style type="text/css">
.block {
	border: 1px solid #95b8e7;
	margin-top: 10px;
	margin-bottom: 5px;
	margin-left: 20px;
	margin-right: 20px;
}


 

.subTit {
	height: 16px;
	color: #0e2d5f;
	font-weight: bold;
	height: 16px;
	padding: 5px;
	line-height: 16px;
	border-bottom: 1px solid #95b8e7;
	background: #cff1ff;
	background: rgba(0, 0, 0, 0)
		linear-gradient(to bottom, #eff5ff 0px, #e0ecff 100%) repeat-x scroll
		0 0;
		
}
.datagrid-header td{background-color: #cff1ff;}
.formtable, .formtable th, .formtable td{border-color:#dddddd; }
</style>
</head>

<body>
	<div class="block">
		<p class="subTit">一、主要产品</p>
		<div class="t-r" id="extraBtn" style="margin:5px 30px;">
			<a class="b-link" onclick="add()">添加</a>
		</div>
		<!--数据表格-->
		<div class="divContainer" id="zycpList" style="height:300px;">
			<table id="content1" fit="true"></table>
		</div>
	</div>
	<div class="block">
		<p class="subTit">二、副产品</p>
		<div class="t-r" id="extraBtn" style="margin:5px 30px;">
			<a class="b-link" onclick="addFcp()">添加</a>
		</div>
		<!--数据表格-->
		<div class="divContainer" id="infectlist" style="height:300px;">
			<table id="content2" fit="true"></table>
		</div>
	</div>
	<div class="block">
		<p class="subTit">三、原料</p>
		<div class="t-r" id="extraBtn" style="margin:5px 30px;">
			<a class="b-link" onclick="addYl()">添加</a>
		</div>
		<!--数据表格-->
		<div class="divContainer" id="infectlist" style="height:300px;">
			<table id="content3" fit="true"></table>
		</div>
	</div>
	<script type="text/javascript">
		//数据表格初始化
		var pid=parent.pid;
		function zycpData(){
		$('#content1').datagrid({
			nowrap : false,//自动截取
			striped : true,//显示条纹
			collapsible : true,//是否添加折叠按钮
			singleSelect : true,//单选模式，只允许选取一行
			fitColumns : true,//自适应列宽
			pageSize : 10,//默认选择的分页是每页10行数据
			pageList: [5,10,15,20],//可以设置每页记录条数的列表  
			remoteSort : false,//是否远程排序
			url : 'whpContentMainList.json?isActive=Y&pid='+parent.pid,//请求数据的超链接地址
			//可以设置每页记录条数的列表  
			onLoadSuccess : function(data) {
				//datagrid鼠标经过提示单元格内容(需要在easyui.js中写扩展的doCellTip方法)
				$(this).datagrid('doCellTip', {'max-width' : '300px','delay' : 500});
			},
			columns : [ [//设置表头
			{field : 'id',	hidden:true},
			{field : 'spm',	title : '商品名',sortable : true,align : 'center',width : 2},
			{field : 'hxm', title : '化学名',align : 'center',width : 2},
			{field : 'operate',title : '操作',align : 'center',width : 1} ] ],
			onDblClickRow : function(rowIndex, rowData) {//双击某一行，查看详情
				info();
			},
			pagination : true,//是否添加底部的分页
			rownumbers : true
		//是否显示左侧的行序号
		});
		}
		zycpData();
		//副产品
		function fcpData(){
		$('#content2').datagrid({
			nowrap : false,//自动截取
			striped : true,//显示条纹
			collapsible : true,//是否添加折叠按钮
			singleSelect : true,//单选模式，只允许选取一行
			fitColumns : true,//自适应列宽
			pageSize : 10,//默认选择的分页是每页10行数据
			pageList: [5,10,15,20],//可以设置每页记录条数的列表  
			remoteSort : false,//是否远程排序
			url : 'whpContentFcpList.json?isActive=Y&pid='+parent.pid,//请求数据的超链接地址
			//可以设置每页记录条数的列表  
			onLoadSuccess : function(data) {
				//datagrid鼠标经过提示单元格内容(需要在easyui.js中写扩展的doCellTip方法)
				$(this).datagrid('doCellTip', {'max-width' : '300px','delay' : 500});
			},
			columns : [ [//设置表头
			{field : 'id',	hidden:true},
			{field : 'spm',	title : '商品名',sortable : true,align : 'center',width : 2},
			{field : 'hxm', title : '化学名',align : 'center',width : 2},
			{field : 'operate',title : '操作',align : 'center',width : 1} ] ],
			onDblClickRow : function(rowIndex, rowData) {//双击某一行，查看详情
				info();
			},
			pagination : true,//是否添加底部的分页
			rownumbers : true
		//是否显示左侧的行序号
		});
		}
		fcpData();
		//原料
		function ylData(){
		$('#content3').datagrid({
			nowrap : false,//自动截取
			striped : true,//显示条纹
			collapsible : true,//是否添加折叠按钮
			singleSelect : true,//单选模式，只允许选取一行
			fitColumns : true,//自适应列宽
			pageSize : 10,//默认选择的分页是每页10行数据
			pageList: [5,10,15,20],//可以设置每页记录条数的列表  
			remoteSort : false,//是否远程排序
			url : 'whpContentYlList.json?isActive=Y&pid='+parent.pid,//请求数据的超链接地址
			//可以设置每页记录条数的列表  
			onLoadSuccess : function(data) {
				//datagrid鼠标经过提示单元格内容(需要在easyui.js中写扩展的doCellTip方法)
				$(this).datagrid('doCellTip', {'max-width' : '300px','delay' : 500});
			},
			columns : [ [//设置表头
			{field : 'id',	hidden:true},
			{field : 'zycp',	title : '主要产品',sortable : true,align : 'center',width : 2},
			{field : 'ylmc',	title : '原料名称',sortable : true,align : 'center',width : 2},
			{field : 'hxm', title : '化学名',align : 'center',width : 2},
			{field : 'operate',title : '操作',align : 'center',width : 1} ] ],
			onDblClickRow : function(rowIndex, rowData) {//双击某一行，查看详情
				info();
			},
			pagination : true,//是否添加底部的分页
			rownumbers : true
		//是否显示左侧的行序号
		});
		}
		ylData();
		//initPager();
		//查看详情
		function info(obj) {
			$.fn.colorbox({
				iframe : true,
				width : "823",
				height : "480",
				href : "infoYzcp.htm?id="+obj.id
			});
		}
		//查看副产品详情
		function infoFcp(obj) {
			$.fn.colorbox({
				iframe : true,
				width : "823",
				height : "480",
				href : "infoFcp.htm?id="+obj.id
			});
		}
		//查看原料详情
		function infoYl(obj) {
			$.fn.colorbox({
				iframe : true,
				width : "823",
				height : "480",
				href : "infoYl.htm?id="+obj.id
			});
		}
		//增加
		function add() {
			$.fn.colorbox({
				iframe : true,
				width : "823",
				height : "480",
				href : "whpAdd.htm?pid="+parent.pid
			});
		}
		//增加
		function addFcp() {
			$.fn.colorbox({
				iframe : true,
				width : "823",
				height : "480",
				href : "whpAddFcp.htm?pid="+parent.pid
			});
		}
		//增加
		function addYl() {
			$.fn.colorbox({
				iframe : true,
				width : "823",
				height : "480",
				href : "whpAddYl.htm?pid="+parent.pid
			});
		}
		//数据表格每行的“修改”
		function modify(obj) {
			$.fn.colorbox({
				iframe : true,
				width : "823",
				height : "480",
				href : "editMain.htm?id="+obj.id
			});
		}
		//数据表格每行的“修改”
		function modifyFcp(obj) {
			$.fn.colorbox({
				iframe : true,
				width : "823",
				height : "480",
				href : "editFcp.htm?id="+obj.id
			});
		}
		//数据表格每行的“修改”
		function modifyYl(obj) {
			$.fn.colorbox({
				iframe : true,
				width : "823",
				height : "480",
				href : "editYl.htm?id="+obj.id
			});
		}
		//关闭弹出框
		function refEdit() {
			jQuery().colorbox.close();
			$('#content1').datagrid('reload');;
		}
		
		//关闭弹出框
		function ref() {
			jQuery().colorbox.close();
			$('#content1').datagrid('reload');;
		}
		//关闭弹出框
		function refFcp() {
			jQuery().colorbox.close();
			$('#content2').datagrid('reload');;
		}
		//关闭弹出框
		function refYl() {
			jQuery().colorbox.close();
			$('#content3').datagrid('reload');;
		}
		
		//数据表格每行的“删除”
		function getRowIndex(target) {
			var tr = $(target).closest('tr.datagrid-row');
			return parseInt(tr.attr('datagrid-row-index'));
		}
		
		/*删除主产品
		function del(target) {
			
			$.messager.confirm('主要产品', '确定要删除吗?', function(r) {
				if (r){
					$.ajax({
						url: "delZycp.json?id="+target.id,
						success:function(data){
							alert(data.msg);
							var p = $('#content1').datagrid('getPager');
							$(p).pagination("select", 1);
						}
					});
				}
			});
		}*/
		function del(target) {
			var index=layer.confirm('确定删除吗？', {
		     	icon: 3, 
		        title:'删除任务'
		     }, function(index){
		    	 $.ajax( {
						url : "delZycp.json?id="+target.id,
						success : function(data) {
							alert(data.msg);
							var p = $('#content1').datagrid('getPager');
							$(p).pagination("select", 1);
						}
					});
		        layer.close(index);
		     },function(index){
		        layer.close(index);
		     }
		    );
		}
		
		/*删除副产品
        function delFcp(target) {
			    $.messager.confirm('副产品', '确定要删除吗?', function(r) {
				if (r){
					$.ajax({
						url: "delFcp.json?id="+target.id,
						success:function(data){
							alert(data.msg);
							var p = $('#content2').datagrid('getPager');
							$(p).pagination("select", 1);
						}
					});
				}
			});
		}*/
		function delFcp(target) {
			var index=layer.confirm('确定删除吗？', {
		     	icon: 3, 
		        title:'删除任务'
		     }, function(index){
		    	 $.ajax( {
						url : "delFcp.json?id="+target.id,
						success : function(data) {
							alert(data.msg);
							var p = $('#content2').datagrid('getPager');
							$(p).pagination("select", 1);
						}
					});
		        layer.close(index);
		     },function(index){
		        layer.close(index);
		     }
		    );
		}
		/*删除原料
        function delYl(target) {
			    $.messager.confirm('原料', '确定要删除吗?', function(r) {
				if (r){
					$.ajax({
						url: "delYl.json?id="+target.id,
						success:function(data){
							alert(data.msg);
							var p = $('#content3').datagrid('getPager');
							$(p).pagination("select", 1);
						}
					});
				}
			});
		}*/
		function delYl(target) {
			var index=layer.confirm('确定删除吗？', {
		     	icon: 3, 
		        title:'删除任务'
		     }, function(index){
		    	 $.ajax( {
						url : "delYl.json?id="+target.id,
						success : function(data) {
							alert(data.msg);
							var p = $('#content3').datagrid('getPager');
							$(p).pagination("select", 1);
						}
					});
		        layer.close(index);
		     },function(index){
		        layer.close(index);
		     }
		    );
		}
		
		
		
</script>
</body>
</html>