<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>行政处罚</title>
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${common}/All.js"></script>
<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<!--jQuery-->
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
.panel-header{background-color: #cff1ff;}
.formtable, .formtable th, .formtable td{border-color:#dddddd; }

</style>
</head>
    <body>
        <div class="h1_1">行政处罚</div>
        <form id="blForm" name="blForm" method="post" action=" ">
        <input type="hidden" id="applyId" name="applyId" value="${work.id}" />
		<input type="hidden" id="taskId" name="taskId" value="${taskId}" />
		<div class="divContainer" style="padding:10px;">
			<!-- 行政处罚 -->
			<div style="width:100%;text-align:right;margin-bottom:10px;">
				<span style="padding-right:25px;"><a id="zfwj" class="btslink">查看相关执法文件</a></span>
			</div>
			<div class="easyui-panel" title="行政处罚" style="margin-bottom:10px;">
				<table width="98%" height="" border="0" align="left" cellpadding="0"
					cellspacing="0" class="formtable" style="margin:0 auto;">
						<tr>
							<th style="text-align:left;" colspan="6">
								违法案件调查相关附件：（包含立案登记表、立案登记扫描件、勘察笔录、勘察笔录扫描件、询问笔录、询问笔录扫描件、书证等证据资料、视听资料.图片、视听资料.音频资料、视听资料.录像、行政文书、其他资料、调查报告、准备资料、环评批复文件、验收批复文件的附件）
							</th>
						</tr>
						<tr>
							<td colspan="6">
							    <!-- 违法案件调查相关附件 -->
							    <div style="height: 200px">
								    <table id="WFAJDCdata" fit="true"></table>
								</div>
							</td>
						</tr>
						<tr>
							<th style="width:180px;text-align:left;">
								<label class="requiredLabel">*</label>行政处罚事先告知书：
							</th>
							<td>
								<a id="zzsxgzs" class="btslink" onclick="zzsxgzs()">在线制作</a>&nbsp;
								<a id="SXGZSfileupload" class="btslink">上传附件</a>&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="6">
							    <!-- 行政处罚事先告知书附件 -->
							    <div style="height: 200px">
								    <table id="SXGZSdata" fit="true"></table>
								</div>
							</td>
						</tr>
						<tr>
							<th style="width:180px;text-align:left;">
								<label class="requiredLabel">*</label>案件集体讨论笔录：
							</th>
							<td>
								<a id="zzjttlbl" class="btslink" onclick="zzjttlbl()" >在线制作</a>&nbsp;&nbsp;&nbsp;
								<a id="JTTLBLfileupload" class="btslink">上传附件</a>&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="6">
							    <!-- 案件集体讨论笔录附件 -->
							    <div style="height: 200px">
								    <table id="JTTLBLdata" fit="true"></table>
								</div>
							</td>
						</tr>
						<tr>
							<th style="width:180px;text-align:left;">
								<label class="requiredLabel">*</label>行政处罚决定书：
							</th>
							<td>
								<a id="zzxzcfjds" class="btslink" onclick="zzxzcfjds()" >在线制作</a>&nbsp;&nbsp;&nbsp;
								<a id="XZCFJDSfileupload" class="btslink">上传附件</a>&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="6">
							    <!--行政处罚决定书的附件 -->
							    <div style="height: 200px">
								    <table id="XZCFJDSdata" fit="true"></table>
								</div>
							</td>
						</tr>
						<tr>
							<th style="width:180px;text-align:left;">
								<label class="requiredLabel">*</label>送达回证：
							</th>
							<td>
								<a id="zzsdhz" class="btslink" onclick="zzsdhz()" >在线制作</a>&nbsp;&nbsp;&nbsp;
								<a id="SDHZfileupload" class="btslink">上传附件</a>&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="6">
							    <!-- 送达回证附件 -->
							    <div style="height: 200px">
								    <table id="SDHZdata" fit="true"></table>
								</div>
							</td>
						</tr>
						<tr>
							<th style="width:180px;text-align:left;">
								<label class="requiredLabel">*</label>行政处罚案件结案审批表：
							</th>
							<td>
								<a id="zzjaspb" class="btslink" onclick="zzjaspb()" >在线制作</a>&nbsp;&nbsp;&nbsp;
								<a id="JASPBfileupload" class="btslink">上传附件</a>&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="6">
							    <!-- 行政处罚案件结案审批表的附件 -->
							    <div style="height: 200px">
								    <table id="JASPBdata" fit="true"></table>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
			
		</form>
		<iframe name="download" id="download" src="" style="display: none"></iframe>
	</body>
</html>
<script type="text/javascript">
var applyId = $('#applyId').val();

function RwlxObj(code,zwName,pyjx,fileType){
    this.code = code;
    this.zwName = zwName;
    this.pyjx = pyjx;
    this.fileType=fileType;
}
var rwlxObj=new RwlxObj("","","","");

$(document).ready(function() {
    //行政处罚事先告知书附件列表
    $('#SXGZSdata').datagrid({
        pagination:true,//分页控件
		height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns : true,
		url:'queryFileList.json?canDel=Y',
		queryParams:{pid:$("#applyId").val(),fileType:'WFAJDCSXGZS',tableId:'SXGZSdata'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:90},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:500},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:45},
			{field:'operate',title:'操作', align:'center', halign:'center',width:65}
		]]
	});
	
	//案件集体讨论笔录附件列表
    $('#JTTLBLdata').datagrid({
        pagination:true,//分页控件
		height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns : true,
		url:'queryFileList.json?canDel=Y',
		queryParams:{pid:$("#applyId").val(),fileType:'WFAJDCJTTLBL',tableId:'JTTLBLdata'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:90},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:500},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:45},
			{field:'operate',title:'操作', align:'center', halign:'center',width:65}
		]]
	});
	
	//行政处罚决定书附件列表
    $('#XZCFJDSdata').datagrid({
        pagination:true,//分页控件
		height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns : true,
		url:'queryFileList.json?canDel=Y',
		queryParams:{pid:$("#applyId").val(),fileType:'WFAJDCXZCFJDS',tableId:'XZCFJDSdata'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:90},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:500},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:45},
			{field:'operate',title:'操作', align:'center', halign:'center',width:65}
		]]
	});
	
	//送达回证附件列表
    $('#SDHZdata').datagrid({
        pagination:true,//分页控件
		height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns : true,
		url:'queryFileList.json?canDel=Y',
		queryParams:{pid:$("#applyId").val(),fileType:'WFAJDCSDHZ',tableId:'SDHZdata'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:90},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:500},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:45},
			{field:'operate',title:'操作', align:'center', halign:'center',width:65}
		]]
	});
	
	//行政处罚案件结案审批表附件列表
    $('#JASPBdata').datagrid({
        pagination:true,//分页控件
		height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns : true,
		url:'queryFileList.json?canDel=Y',
		queryParams:{pid:$("#applyId").val(),fileType:'WFAJDCJASPB',tableId:'JASPBdata'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:100},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:500},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:45},
			{field:'operate',title:'操作', align:'center', halign:'center',width:65}
		]]
	});
	
	//违法案件调查相关附件
    if(1 == 1){
    	rwlxObj=new RwlxObj("16","违法案件调查","WFAJDC","WFAJDCLADJB,WFAJDCLADJSMJ,WFAJDCKCBL,WFAJDCKCBLSMJ,WFAJDCXWBL,WFAJDCXWBLSMJ,WFAJDCSZDZJZL,WFAJDCSTZLTP,WFAJDCYPZL,WFAJDCSTZLLX,WFAJDCXZWS,WFAJDCQTZL,WFAJDCDCBG,WFAJDCZBZL,WFAJDCHPPFWJ,WFAJDCYSPFWJ");
		showCOMMON(rwlxObj);
    }
    //相关执法文件
    $("#zfwj").click(function(){
    	//All.ShowModalWin('xxcx_lawdocList.htm',"",1200,600);
    	parent.layer.open({
            type: 2,
            title: '稿件查看',
            maxmin:true,
            area: ['1100px', ($(window).height()+60)+'px'],
            content: 'xxcx_lawdocList.htm'
        });
    });
});

//事先告知书制作
function zzsxgzs(){
	All.ShowModalWin("zzsxgzs.htm?applyId="+applyId,'',1100,550);
	this.reload("SXGZSdata", applyId, "WFAJDCSXGZS");
}

//案件集体讨论笔录制作
function zzjttlbl(){
	All.ShowModalWin("zzjttlbl.htm?applyId="+applyId,'',1100,500);
	this.reload("JTTLBLdata", applyId, "WFAJDCJTTLBL");
}

//行政处罚决定书制作
function zzxzcfjds(){
	All.ShowModalWin("zzcfjds.htm?applyId="+applyId);
	this.reload("XZCFJDSdata", applyId, "WFAJDCXZCFJDS");
}

//送达回证制作
function zzsdhz(){
	All.ShowModalWin("zzsdhz.htm?applyId="+applyId,'',880,360);
	this.reload("SDHZdata", applyId, "WFAJDCSDHZ");
}

//行政处罚案件结案审批表制作
function zzjaspb(){
	All.ShowModalWin("zzjaspb.htm?applyId="+applyId,'',1100,550);
	this.reload("JASPBdata", applyId, "WFAJDCJASPB");
}

function showCOMMON(rwlxObj){
    var fileTypeNum=rwlxObj.fileType.split(',');
    var canDelStr="";
    for(var i=0;i<fileTypeNum.length;i++){
        if(i<fileTypeNum.length-1){
           canDelStr+="N,";
        }else{
           canDelStr+="N";
        }
    }
    //赋值
    $('#'+rwlxObj.pyjx+'data').datagrid({
        pagination:true,//分页控件
        height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns:true,
		url:'queryFileListMulfileType.json?canDel='+canDelStr,
		queryParams:{pid:$("#applyId").val(),fileType:rwlxObj.fileType,tableId:rwlxObj.pyjx+'data'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:100},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:500},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:100},
			{field:'operate',title:'操作', align:'center', halign:'center',width:100}
		]]
	});
}

	//行政处罚事先告知书附件上传
	$("#SXGZSfileupload").click(function(){
	    var id = $("#applyId").val();
		var fileType = 'WFAJDCSXGZS';//行政处罚事先告知书
		if(id!=null && id!=''){
			$.colorbox({
				iframe:true, width:"610", height:"380",
				href:'uploadPage.htm?pid='+id+'&fileType='+fileType+'&path=WORK&tableId=SXGZSdata'
		 	});
		}else{
			alert("数据错误！");
		}
	});

	//集体讨论笔录附件上传
	$("#JTTLBLfileupload").click(function(){
	    var id = $("#applyId").val();
		var fileType = 'WFAJDCJTTLBL';//违法案件集体讨论笔录
		if(id!=null && id!=''){
			$.colorbox({
				iframe:true, width:"610", height:"380",
				href:'uploadPage.htm?pid='+id+'&fileType='+fileType+'&path=WORK&tableId=JTTLBLdata'
		 	});
		}else{
			alert("数据错误！");
		}
	});
	
	//行政处罚决定书附件上传
	$("#XZCFJDSfileupload").click(function(){
	    var id = $("#applyId").val();
		var fileType = 'WFAJDCXZCFJDS';//行政处罚决定书
		if(id!=null && id!=''){
			$.colorbox({
				iframe:true, width:"610", height:"380",
				href:'uploadPage.htm?pid='+id+'&fileType='+fileType+'&path=WORK&tableId=XZCFJDSdata'
		 	});
		}else{
			alert("数据错误！");
		}
	});
	
	//送达回证附件上传
	$("#SDHZfileupload").click(function(){
	    var id = $("#applyId").val();
		var fileType = 'WFAJDCSDHZ';//结案审批表
		if(id!=null && id!=''){
			$.colorbox({
				iframe:true, width:"610", height:"380",
				href:'uploadPage.htm?pid='+id+'&fileType='+fileType+'&path=WORK&tableId=SDHZdata'
		 	});
		}else{
			alert("数据错误！");
		}
	});
	
	//结案审批表附件上传
	$("#JASPBfileupload").click(function(){
	    var id = $("#applyId").val();
		var fileType = 'WFAJDCJASPB';//行政处罚决定书
		if(id!=null && id!=''){
			$.colorbox({
				iframe:true, width:"610", height:"380",
				href:'uploadPage.htm?pid='+id+'&fileType='+fileType+'&path=WORK&tableId=JASPBdata'
		 	});
		}else{
			alert("数据错误！");
		}
	});

function bl() {
    window.close();
}

//打包下载
function downZipFile(){
    var data = $('#XGFJdata').datagrid("getData");
	var ids = "";
	for(var i = 0; i < data.rows.length; i++){
		if (i > 0){
			ids += ",";
		}
		ids += data.rows[i].id;
	}
	if(ids!=null && ids!=''){
	    $('#download').attr('src','downZipFile?id='+ids);
	}else{
	    alert("没有附件！");
	}
}

/////附件操作/////
/**
 * 公用的上传文件之后的刷新grid方法
 * @param tableId tablegrid的id
 */
function reload(tableId,pid,fileType){
    var id = "#" + tableId;
	$(id).datagrid("reload",{pid:pid,fileType:fileType,tableId:tableId});
	jQuery.colorbox.close();
}
/**
 * 公用的删除文件方法 删除grid中的文件
 * @param obj grid的一行数据
 */
function deletefile1(obj){
	$.messager.confirm('操作', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
				url: "removeFile.json?id="+obj.id,
				success:function(data){
					alert(data.msg);
					var reloadtable= $(obj).attr("reloadtable");//自定义属性采用此方式获得 
					$('#'+reloadtable).datagrid('reload');
				}
			});
		}
	});
}

</script>