<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>任务管理——任务准备</title>
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
    <!-- 任务管理 css-->
    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
    <!--时间插件 my97-->
    <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
    <script type="text/javascript" src="${common}/All.js"></script>
    <style type="text/css">
    .f_operate {
    background: #ffffff;
    border-bottom: 0px solid #d4d4d4;
    border-top: 0px solid #d4d4d4;
    }
    </style>
</head>
<body style="background-color: #ffffff;padding-bottom: 60px;">
<div class="dataDiv" style="width:95%;min-width: 1020px; margin:16px auto 25px;">
   <form id="zbForm" name="zbForm" method="post" action="">
		<input type="hidden" id="applyId" name="applyId" value="${work.id}" />
		<input type="hidden" id="zxrId" name="zxrId" value="${work.zxrId}" />
		<input type="hidden" id="taskId" name="taskId" value="${taskId}" />
		<input type="hidden" id="rwlxIds" name="rwlxIds" value="${rwlxIds}" />
    <!--task_step end -->
    <h3 class="task_h3 mt25"><span class="h_icon"></span> 任务信息</h3>
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="48">
            <th colspan="4" align="left">任务信息</th>
        </tr>
        <tr height="48">
            <td width="10%" align="center">任务名称</td>
            <td colspan="3" style="padding-right: 120px; position: relative">
				${work.name}             
				 <span style="position:absolute; right:0; top:50%; margin-top: -12px;">
				 <input type="button" class="bTn blue" value="任务详情" id="viewDetail" onclick="info()">
				 </span>
            </td>
        </tr>
        <tr height="48">
            <td width="10%" align="center">主要内容</td>
            <td colspan="3">${work.workNote}</td>
        </tr>
        <tr height="48">
            <td width="10%" align="center">任务登记人</td>
            <td width="40%">${work.djrName}</td>
            <td width="10%" align="center">要求完成时限</td>
            <td width="40%"><fmt:formatDate value="${work.endTime}" pattern="yyyy-MM-dd"/></td>
        </tr>
    </table>
    <!-- 任务信息 end-->
    <c:if test='${rwlxIds != "24"}'>
    <table class="dataTable mt25" width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
        <tr>
            <td width="12%" align="right"><span class="red">*</span>执法对象类型</td>
            <td width="20%"><input type="text" class="y-text" style="width:160px;" id="zfdxType" <c:if test='${rwlxIds != "13"}'>required="true"</c:if>name="zfdxType" value="${work.zfdxType}"/></td>
            <td width="12%" align="right">
            <c:if test='${rwlxIds != "13"}'> <span class="red">*</span></c:if>
                                   执法对象</td>
            <td width="16%"><input type="text" class="y-text" style="width:160px;color: #00a2d9; cursor: pointer;" value="选择执法对象" onclick="zfdxxz()" readonly/></td>
           <c:if test='${rwlxIds != "13"}'>
            <td width="10%" align="right"><span class="red">*</span>任务类型</td>
            <td width="18%"><input type="text" class="y-text"  style="width:160px;" id="rwlx" name="rwlx" size="50" readonly="readonly"/></td>
            <td width="8%" align="right"><input type="button" class="bTn blue" onclick="saverwlx()" value="保存任务类型" style="padding: 3px 10px;"></td>
       		</c:if>
        </tr>
    </table>
    
    <!--执法对象列表 -->
    <div class="mt25" style=" width: 100%; height: 150px;">
        <table id="zfdxTable" fit="true"></table>
    </div>
    </c:if>
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            相关附件
        </div>
        <div class="annex_con" style="width: 100%; height: 248px;">
            <table id="XGFJdata" fit="true"></table>
        </div>
    </div>
    <hr class="mt25" style="height: 2px;border: none; border-bottom: 2px dashed #d4d4d4" />
    <!--人员规划 -->
    <div class="personplan mt25">
        <h3 class="task_h3">
         <a class="b-link"  onclick="selectxbr()"  style="float:left;text-decoration:underline;" >选择协办人员</a>
        </h3>
        <div class="mt25" style=" width: 100%; height: 218px;">
            <table id="ryghTable" fit="true"></table>
        </div>
    </div>
    <hr class="mt25" style="height: 2px;border: none; border-bottom: 2px dashed #d4d4d4" />
    <!-- 准备工作-->
    <!--现场监察 -->
    <div id="DIV_10" class="preparation mt25" style="display:none">
        <div class="annex_header">
            <a class="b_link" id="RCJCfileupload" style="float:left;text-decoration:underline;" >上传附件</a>
      <!--现场监察 -->
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="RCJCdata" fit="true"></table>
        </div>
    </div>
    <!--年度核查 -->
    <div id="DIV_11" class="preparation mt25" style="display:none">
        <div class="annex_header">
            <a class="b_link" id="NDHCfileupload" style="float:left;text-decoration:underline;" >上传附件</a>
            <!--年度核查 -->
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="NDHCdata" fit="true"></table>
        </div>
    </div>
    <!--后督察 -->
    <div id="DIV_12" class="preparation mt25" style="display:none">
        <div class="annex_header">
            <a class="b_link" id="HDCfileupload" style="float:left;text-decoration:underline;" >上传附件</a>
           <!--后督察--> 
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="HDCdata" fit="true"></table>
        </div>
    </div>
    <!--信访投诉 -->
    <div id="DIV_13" class="preparation mt25" style="display:none">
        <div class="annex_header">
           <a class="b-link" id="XFTSfileupload" style="float:left;text-decoration:underline;" >上传附件</a>
             <!--信访投诉 -->
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="XFTSdata" fit="true"></table>
        </div>
    </div>
    <!--排污许可证检查-->
    <div id="DIV_14" class="preparation mt25" style="display:none">
        <div class="annex_header">
            <a class="b_link" id="PWXKZJCfileupload" style="float:left;text-decoration:underline;" >上传附件</a>
           <!--排污许可证检查 -->
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="PWXKZJCdata" fit="true"></table>
        </div>
    </div>
    <!--专项行动-->
    <div id="DIV_15" class="preparation mt25" style="display:none">
        <div class="annex_header">
            <a class="b_link" id="ZXXDfileupload" style="float:left;text-decoration:underline;" >上传附件</a>
           <!--专项行动-->
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="ZXXDdata" fit="true"></table>
        </div>
    </div>
    <!--违法案件调查-->
    <div id="DIV_16" class="preparation mt25" style="display:none">
        <div class="annex_header">
            <a class="b_link" id="WFAJDCfileupload" style="float:left;text-decoration:underline;" >上传附件</a>
          <!--违法案件调查 -->
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="WFAJDCdata" fit="true"></table>
        </div>
    </div>
    <!--限期治理-->
    <div id="DIV_17" class="preparation mt25" style="display:none">
        <div class="annex_header">
            <a class="b_link" id="XQZLfileupload" style="float:left;text-decoration:underline;" >上传附件</a>
          <!--限期治理-->
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="XQZLdata" fit="true"></table>
        </div>
    </div>
    <!--跟踪检查-->
    <div id="DIV_18" class="preparation mt25" style="display:none">
        <div class="annex_header">
            <a class="b_link" id="GZJCfileupload" style="float:left;text-decoration:underline;" >上传附件</a>
          <!-- 跟踪检查 -->
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="GZJCdata" fit="true"></table>
        </div>
    </div>
    <!--自动监控-->
    <div id="DIV_19" class="preparation mt25" style="display:none">
        <div class="annex_header">
           <a class="b_link" id="ZDJKfileupload" style="float:left;text-decoration:underline;" >上传附件</a>
       <!--自动监控 -->   
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="ZDJKdata" fit="true"></table>
        </div>
    </div>
    <!--危险废物-->
    <div id="DIV_20" class="preparation mt25" style="display:none">
        <div class="annex_header">
            <a class="b_link" id="WXFWfileupload" style="float:left;text-decoration:underline;" >上传附件</a>
            <a href="#" id="WXFWchooseLawdoc" class="b_link" style="float:right;">选择执法文件</a>&nbsp;&nbsp;&nbsp;&nbsp;
        <!--危险废物 -->
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="WXFWdata" fit="true"></table>
        </div>
    </div>
    <!--危险化学品-->
    <div id="DIV_21" class="preparation mt25" style="display:none">
        <div class="annex_header">
            <a class="b_link" id="WXHXPfileupload" style="float:left;text-decoration:underline;" >上传附件</a>
            <a href="#" id="WXHXPchooseLawdoc"  class="b_link" style="float:right;">选择执法文件</a>&nbsp;&nbsp;&nbsp;&nbsp;
      <!--危险化学品-->  
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="WXHXPdata" fit="true"></table>
        </div>
    </div>
    <!--辐射安全-->
    <div id="DIV_22" class="preparation mt25" style="display:none">
        <div class="annex_header">
            <a class="b_link" id="FSAQfileupload" style="float:left;text-decoration:underline;" >上传附件</a>
            <a href="#" id="FSAQchooseLawdoc" class="b_link" style="float:right;">选择执法文件</a>&nbsp;&nbsp;&nbsp;&nbsp;
     <!--辐射安全-->
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="FSAQdata" fit="true"></table>
        </div>
    </div>
    <!--污染事故现场调查-->
    <div id="DIV_23" class="preparation mt25" style="display:none">
        <div class="annex_header">
            <a class="b_link" id="WRSGXCDCfileupload"  style="float:left;text-decoration:underline;" >上传附件</a>
            <a href="#" id="WRSGXCDCchooseLawdoc" class="b_link" style="float:right;">选择执法文件</a>&nbsp;&nbsp;&nbsp;&nbsp;
            
          污染事故现场调查
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="WRSGXCDCdata" fit="true"></table>
        </div>
    </div>
    <!--日常办公-->
    <div id="DIV_24" class="preparation mt25" style="display:none">
        <div class="annex_header">
            <a class="b_link" id="RCBGfileupload" style="float:left;text-decoration:underline;" >上传附件</a>
     <!--日常办公-->
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="RCBGdata" fit="true"></table>
        </div>
    </div>
    </form>
</div>
<div class="rb_btn_fix">
    <input type="button" id="pfbutton" class="queryOrange" value="下一步" onclick="zb()" style="cursor: pointer">
</div>
<a id="getDjMessage" href="#" class="getInfo">获取离线信息</a>
<iframe name="download" id="download" src="" style="display: none"></iframe>
</body>
</html>
<script>
var applyId = $('#applyId').val();
var zxrId = $('#zxrId').val();
var rwlxIds = $('#rwlxIds').val();
//设置选择协办人人
function setUserInfoXbr(id,name) {
	//保存协办人
	$.ajax({
		url: "rySaveMul.json?applyId="+applyId+"&ryid="+id+"&type=06",
		success:function(data){
			//更新表格
			$('#ryghTable').datagrid('reload');
			jQuery().colorbox.close();
		}
	});
}
function selectxbr() {
	var data = $('#ryghTable').datagrid("getData");
	var ids = "";
	for(var i = 0; i < data.rows.length; i++){
		if(data.rows[i].userid != zxrId){
			if (i > 0){
				ids += ",";
			}
			ids += data.rows[i].userid;
		}
	}

	$.colorbox({iframe:true,width:"300", height:"410",href:"taskUserPubQuery.htm?all=true&displayAll=true&id="+ids+"&notShowZj=true&methodname=setUserInfoXbr&multi=true&condition=0"});
}

$(document).ready(function() {
	//执法对象类型下拉框
   	$('#zfdxType').combotree({
   		multiple: false,
		url:'lawtypeTree.json',
		panelHeight:300,
		valueField:'id',
		textField:'name',
		onChange : function (newValue,oldValue){
			zfdxTypeSave();
		},
		//value:'${rwlxIds}'.split(',')
	});
	
	//执法对象列表
	$('#zfdxTable').datagrid({
		url:'zfdxTable.json', 
		queryParams:{applyId:applyId},
		width:'100%',
		height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns:true,
		//onLoadSuccess:function(data){
		//	var rows = $(this).datagrid('getRows');
		//	if(rows.length>1){
		//		$("#rwlx").combotree('setValues','15'.split(","));
		//	}
		//},
		columns:[[  
			{field:'lawobjname',title:'执法对象',width:100,align:'center'},   
			{field:'address',title:'地址',width:100,align:'center'},
			{field:'manager',title:'负责人',width:100,align:'center'},
			{field:'managermobile',title:'联系方式',width:100,align:'center'}
		]]
	});
	
	//任务类型
	$("#rwlx").combotree({
		height:34,
		required:true,
		type:"post",
		multiple: true,
		url:'taskTypeTreeComboByTaskId.json?applyId='+applyId,
		valueField:'id',
		textField:'name',
		value:'${rwlxIds}'.split(','),
		onChange : function (newValue,oldValue){
			if(newValue!=null && newValue!=''){
				//保存任务类型
			$.ajax({
				  url: "taskTypeSaveMultiple.json?applyId="+applyId+"&checkedNodesIds="+newValue,
				  success:function(data){
					  //准备工作展示
					  initZBGZ();
				   }
				});
			}
		}
	});
	
	//人员规划列表
	$('#ryghTable').datagrid({
		url:'ryghTable.json', 
		queryParams:{applyId:applyId},
		width:'100%',
		height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns:true,
		columns:[[  
			{field:'name',title:'姓名',width:100,align:'center'},   
			{field:'type',title:'主办/协办',width:100,align:'center'},
			{field:'lawnumber',title:'执法证号',width:100,align:'center'},
			{field:'personmobile',title:'个人电话',width:100,align:'center'},
			{field:'workmobile',title:'工作电话',width:100,align:'center'},
			{field:'org',title:'部门',width:100,align:'center'},
			{field:'operate',title:'操作',width:100,align:'center'}
		]]
	});
	
//相关附件列表
	$('#XGFJdata').datagrid({
	    pagination:true,//分页控件
		height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns : true,
		url:'queryFileListMulfileType.json?canDel=N,N',
		queryParams:{pid:$("#applyId").val(),fileType:'RWGLPFFJ,RWGLZPFJ'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:50},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:500},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:100},
			{field:'operate',title:'操作', align:'center', halign:'center',width:100}
		]]
	});	
    //人员
	//准备工作展示
	initZBGZ();
	
	
	$("#getDjMessage").click(function(){
		All.ShowModalWin('getDjMessage.htm?applyId='+applyId, '任务详情', 600, 350);
		parent.changeSelect("准备");
	});

});
//定义一个对象（code：任务类型code,zwName：中文名称,pyjx：拼音简写；fileType：文件类型；）
function RwlxObj(code,zwName,pyjx,fileType){
	this.code = code;
	this.zwName = zwName;
	this.pyjx = pyjx;
	this.fileType=fileType;
}
var rwlxObj=new RwlxObj("","","","");
//准备工作展示
function initZBGZ(){
	$("div[id^='DIV_']").hide();//先把所有的div隐藏
	//获取任务类型
	$.ajax({
	  url: "getTaskTypeMultiple.json?applyId="+applyId,
	  success:function(data){
		  if(data.rwlxIds!=null&&data.rwlxIds!=''){
			  var codesArr=data.rwlxIds.split(',');
			  $.each(codesArr, function(i, n){
					switch(n){//根据code判断任务类型
					case '10'://现场监察
					   rwlxObj=new RwlxObj("10","现场监察","RCJC","RCJCZBZL");
					   showCOMMON(rwlxObj);
					   break;
					case '11'://年度核查
					   rwlxObj=new RwlxObj("11","年度核查","NDHC","NDHCZBZL");
					   showCOMMON(rwlxObj);
					   break;
					case '12'://后督察
					   rwlxObj=new RwlxObj("12","后督察","HDC","HDCZBZL");
					   showCOMMON(rwlxObj);
					   break;
					case '13'://信访投诉
					   rwlxObj=new RwlxObj("13","信访投诉","XFTS","XFTSZBZL");
					   showCOMMON(rwlxObj);
					   break;
					case '14'://排污许可证检查
					   rwlxObj=new RwlxObj("14","排污许可证检查","PWXKZJC","PWXKZJCZBZL");
					   showCOMMON(rwlxObj);
					   break;
					case '15'://专项行动
					   rwlxObj=new RwlxObj("15","专项行动","ZXXD","ZXXDZBZL");
					   showCOMMON(rwlxObj);
					   break;
					case '16'://违法案件调查
					   rwlxObj=new RwlxObj("16","违法案件调查","WFAJDC","WFAJDCZBZL");
					   showCOMMON(rwlxObj);
					   break;
					case '17'://限期治理
					   rwlxObj=new RwlxObj("17","限期治理","XQZL","XQZLZBZL");
					   showCOMMON(rwlxObj);
					   break;
					case '18'://跟踪检查
					   rwlxObj=new RwlxObj("18","跟踪检查","GZJC","GZJCZBZL");
					   showCOMMON(rwlxObj);
					   break;
					case '19'://自动监控
					   rwlxObj=new RwlxObj("19","自动监控","ZDJK","ZDJKZBZL");
					   showCOMMON(rwlxObj);
					   break;
					case '20'://危险废物
					   rwlxObj=new RwlxObj("20","危险废物","WXFW","WXFWZBZL");
					   showCOMMON(rwlxObj);
					   break;
					case '21'://危险化学品
					   rwlxObj=new RwlxObj("21","危险化学品","WXHXP","WXHXPZBZL");
					   showCOMMON(rwlxObj);
					   break;
					case '22'://辐射安全
					   rwlxObj=new RwlxObj("22","辐射安全","FSAQ","FSAQZBZL");
					   showCOMMON(rwlxObj);
					   break;
					case '23'://污染事故现场调查
					   rwlxObj=new RwlxObj("23","污染事故现场调查","WRSGXCDC","WRSGXCDCZBZL");
					   showCOMMON(rwlxObj);
					   break;
					case '24'://日常办公
					   rwlxObj=new RwlxObj("24","日常办公","RCBG","RCBGZBZL");
					   showCOMMON(rwlxObj);
					   break;
					default:
					   alert("还未定义code为"+n+"的任务类型");
					}
			  });
		  }
	   }
	});
}
/////////////////////准备工作的展示方法start/////////////////////////
//通用展示
function showCOMMON(rwlxObj){
	//显示
	$('#DIV_'+rwlxObj.code).show();
	//赋值
	$('#'+rwlxObj.pyjx+'data').datagrid({
	    pagination:true,//分页控件
		height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns : true,
		url:'queryFileList.json?canDel=Y',
		queryParams:{pid:$("#applyId").val(),fileType:rwlxObj.fileType,tableId:rwlxObj.pyjx+'data'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:50},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:500},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:100},
			{field:'operate',title:'操作', align:'center', halign:'center',width:100}
		]]
	});
	//选择执法文件
	$('#'+rwlxObj.pyjx+'chooseLawdoc').click(function(){
		var id = $("#applyId").val();
		var fileType = rwlxObj.fileType;
		if(id!=null && id!=''){
			//All.ShowModalWin('chooseLawdoc.htm?applyId='+id+'&fileType='+fileType+'&tasktypeCode='+rwlxObj.code, '选择执法文件',1250,800);
			top.layer.open({
            type: 2,
            title: '选择执法文件',
            area: ['1100px', ($(window).height()+60)+'px'],
            content: 'chooseLawdoc.htm?applyId='+id+'&fileType='+fileType+'&tasktypeCode='+rwlxObj.code+'&tableId='+rwlxObj.pyjx+'data',
			end: function () {
                $('#'+rwlxObj.pyjx+'data').datagrid('reload');
            }
			})
		}
	});
	
	//上传
	$('#'+rwlxObj.pyjx+'fileupload').click(function(){
		var id = $("#applyId").val();
		var fileType = rwlxObj.fileType;
		if(id!=null && id!=''){
			$('#'+rwlxObj.pyjx+'fileupload').colorbox({
				iframe:true, width:"610", height:"300",
				href:'uploadPage.htm?pid='+id+'&fileType='+fileType+'&path=WORK&tableId='+rwlxObj.pyjx+'data'
			});
		}
	});
}
/////////////////////准备工作的展示方法end///////////////////////////

// 执法对象类型保存
function zfdxTypeSave(){
	var zfdxType = $('#zfdxType').combotree('getValue');
	$.ajax({
	  url: "zfdxTypeSave.json?applyId="+applyId+"&zfdxType="+zfdxType+"&rwlxIds="+rwlxIds,
	  success:function(data){
	      //准备工作展示
		  initZBGZ();
		  //删除执法对象列表
		  $('#zfdxTable').datagrid('reload');
		  //更新任务类型选择
		  if(rwlxIds!='13'){
		  	 $('#rwlx').combotree('reload','taskTypeTreeComboByTaskId.json?applyId='+applyId);
		  	 $("#rwlx").combotree("setValues","");//清空任务类型
		  }
	  }
	});
}

// 选择执法对象
function zfdxxz(){
	var zfdxType = $('#zfdxType').combobox('getValue');
	var rwid = $("#applyId").val();
	if(zfdxType!=null && zfdxType!=''){
		var href = 'selectLawobj.htm?lawobjtype='+zfdxType+'&rwid='+rwid; 
		var width=screen.width * 0.8;
	  	var height=screen.height * 0.8-100;
		//parent.layerIframe(2,href,"选择执法对象",width,height);
		parent.layer.open({
		  	  type: 2,
		  	  title: "选择执法对象",
		  	  maxmin: true,
		  	  shadeClose: false, //点击遮罩关闭层
		  	  area : [width+'px' , height+'px'],
		  	  content: href,
		  	  end:function(){
		  		$('#zfdxTable').datagrid('reload'); 
		  		//更新任务类型选择
				if(rwlxIds!='13'){
					$('#rwlx').combotree('reload','taskTypeTreeComboByTaskId.json?applyId='+applyId);
					$("#rwlx").combotree("setValues","");//清空任务类型
				}
		  	  }
		  	  });
		//layer.full(index);
		//All.ShowModalWin('selectLawobj.htm?lawobjtype='+zfdxType+'&rwid='+rwid, '选择执法对象');
		//准备工作展示
		initZBGZ();
		
		
	}else{
		alert("请先选择执法对象类型！");
	}
}
function closeLayerIframe(){
	$('#zfdxTable').datagrid('reload');
	layer.closeAll('iframe');
}
//保存任务类型
function saverwlx(){
	var zfdxType = $('#zfdxType').combobox('getValue');
	var zfdxTable = $('#zfdxTable').datagrid("getData");
	if(zfdxType==null || zfdxType==''){
		alert("请选择执法对象类型和执法对象！");
		return;
	}
	if(zfdxTable.rows.length<1){
		alert("请选择执法对象！");
		return;
	}
	
	var checkedNodesIds="";
	var t = $('#rwlx').combotree('tree'); // 得到树对象 
	var checkedNodes = t.tree('getChecked');
	for(var i=0;i<checkedNodes.length;i++){
		if(i<checkedNodes.length-1){
			checkedNodesIds+=checkedNodes[i].id+',';
		}else{
			checkedNodesIds+=checkedNodes[i].id;
		}
	}
	if(checkedNodesIds!=null && checkedNodesIds!=''){
		//保存任务类型
		$.ajax({
		  url: "taskTypeSaveMultiple.json?applyId="+applyId+"&checkedNodesIds="+checkedNodesIds,
		  success:function(data){
			  //准备工作展示
			  initZBGZ();
			  alert("保存任务类型成功！");
		   }
		});
	}else{
		alert("请选择任务类型！");
	}
}

function zb() {
	//前台校验
	if($("#zbForm").form('validate')){
		//后台校验
		$.post('checkBlZB.json', {
			applyId : applyId
		}, function(json) {
			if (json.state) {
				//跳到办理
				parent.changeTitle("1");
			} else {
				//提示信息
				alert(json.msg);
			}
		}, 'json');
	}
}

//删除"人员规划协办人"
function del(curObj){
	$.messager.confirm('人员删除', '确定要删除当前记录吗？', function(r){
		if (r){
			$.ajax({
			  url: "delrygh.json?id="+curObj.id+"&applyId="+applyId,
			  success:function(data){
				 $('#ryghTable').datagrid("reload",{applyId:applyId});
			  }
			});
		}
	});
}

//任务详情
function info(){
	var href = 'taskDetail.htm?applyId='+applyId; 
  	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	parent.parent.layerIframe(2,href,"任务详情",width,height);
	// All.ShowModalWin(, '任务详情');
	// ref();
}

/////附件操作/////
/**
 * 公用的上传文件之后的刷新grid方法
 * @param tableId tablegrid的id
 */
function reload(tableId,pid,fileType){
	var id = "#" + tableId;
	$(id).datagrid("reload");
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
    $(window).resize(function(){
       // $('#annex_con,#zfdxTable,#personplan,#preparation').datagrid("resize");
    });
</script>
