<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>行政处罚查看</title>
	    <script type="text/javascript" src="${layer}/layer.js"></script>
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
	    <script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css" />
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
		<script type="text/javascript" src="${common}/All.js"></script>
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
        <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
		<style type="text/css">
         .panel-header{background-color: #cff1ff;}
         .formtable, .formtable th, .formtable td{border-color:#dddddd; }
         
        </style>
</head>
    <body>
        <div class="h1_1">行政处罚查看</div>
        <form id="blForm" name="blForm" method="post" action=" ">
        <input type="hidden" id="applyId" name="applyId" value="${work.id}" />
		<input type="hidden" id="taskId" name="taskId" value="${taskId}" />
		<div class="divContainer" style="padding:10px;">
			<!-- 行政处罚 -->
			<div class="easyui-panel" title="行政处罚查看" style="margin-bottom:10px;">
				<table width="98%" height="" border="0" align="left" cellpadding="0"
					cellspacing="0" class="formtable" style="margin:0 auto;">
						<tr>
							<th style="text-align:left;" colspan="6" >
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
								行政处罚事先告知书：
							</th>
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
								案件集体讨论笔录：
							</th>
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
								行政处罚决定书：
							</th>
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
								送达回证：
							</th>
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
								行政处罚案件结案审批表：
							</th>
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
		url:'queryFileList.json?canDel=N',
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
		url:'queryFileList.json?canDel=N',
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
		url:'queryFileList.json?canDel=N',
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
		url:'queryFileList.json?canDel=N',
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
		url:'queryFileList.json?canDel=N',
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
});

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