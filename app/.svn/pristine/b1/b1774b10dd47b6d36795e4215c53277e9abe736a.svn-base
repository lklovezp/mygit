<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>任务延期</title>
   	<!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${app}/js.js"></script>
	<script type="text/javascript" src="${app}/data.js"></script>
    <!--easyui-->
     <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link rel="stylesheet" href="${app}/easyUIReset.css"  type="text/css" />
    <link rel="stylesheet" href="${app}/attachFileList.css" type="text/css">
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <!-- 任务管理 css-->
    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
     <!--时间插件 my97-->
    <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
    <script type="text/javascript" src="${common}/All.js"></script>
</head>
<body>
<div class="checkup" style="padding-bottom: 80px;">
    <h1 id="checkup_header">任务延期</h1>
    <form id="myform" name="myform" method="post" action="rwyq.json">
	<input type="hidden" id="applyId" name="applyId" value="${work.id}" />
	<input type="hidden" id="id" name="id" value="${work.id}" />
    <table class="dataTable" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="48">
            <th colspan="6" align="left">&nbsp;&nbsp;&nbsp;&nbsp;任务信息</th>
        </tr>
        <tr  height="42">
            <td width="12%" align="center">任务名称</td>
            <td colspan="5">${work.name}</td>
        </tr>
        <tr  height="42">
            <td width="12%" align="center">主要内容</td>
            <td colspan="5">${work.workNote}</td>
        </tr>
        <tr  height="42">
            <td width="12%" align="center">任务来源</td>
            <td colspan="5">${source}</td>
        </tr>
        <tr  height="42">
            <td width="12%" align="center">任务密级</td>
            <td width="21%">${security}</td>
            <td width="12%" align="center">紧急程度</td>
            <td width="21%">${emergency}</td>
            <td width="12%" align="center">登记人</td>
            <td width="21%">${work.djrName}</td>
        </tr>
        <tr  height="42">
            <td width="12%" align="center">任务生成时间</td>
            <td width="21%"><label><fmt:formatDate value="${work.createTime}" pattern="yyyy-MM-dd"/></label></td>
            <td width="12%" align="center"><font color="red"><b>办结时限修改为</b></font></td>
            <td width="21%">
            	<input type="text" class="y-dateTime" id="endTime" name="endTime" value="<fmt:formatDate value="${work.endTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 156px;border:1px solid red;"/>
            </td>
            <td width="12%" align="center">主办人</td>
            <td width="21%">${work.zxrName}</td>
        </tr>
        <tr  height="42">
            <td width="12%" align="center">派发人</td>
            <td width="21%"><label>${curUser.name}</td>
            <td width="12%" align="center">批示意见</td>
            <td colspan="3">${work.psyj}</td>
        </tr>
    </table>
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            <a class="b_link" style="float:right;" onclick="downZipFile()">打包下载</a>
            相关附件
        </div>
        <div class="annex_con" style="height: 298px;">
            <table id="XGFJdata" fit="true"></table>
        </div>
    </div>
    </form>
    <iframe name="download" id="download" src="" style="display: none"></iframe>
</div>
    <div  class="rb_btn_fix">
        <input id="guanbi" type="button" class="queryBlue" value="保存" style=" font-size:14px;cursor: pointer;" onclick="javascript:baocun();">
    </div>
<script>
$(document).ready(function(){
    //相关附件列表
    $('#XGFJdata').datagrid({
        pagination:true,//分页控件
        height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns : true,
		url:'queryFileListMulfileType.json?canDel=N,N,N',
		queryParams:{pid:$("#applyId").val(),fileType:'RWGLPFFJ,RWGLZPFJ,RWGLXPFJ'},
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
});

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
    //高度自适应
    $(window).resize(function(){
        $('#XGFJdata').datagrid("resize");
    });
	//任务生成
    function baocun(){
         $('#myform').attr('action','rwyq.json');
         $('#myform').ajaxSubmit(function(data){
        	   	if(data.state){
        	   		 var index=layer.alert(data.msg,{
						     	title:'任务延期:',
						        icon:1,
						        shadeClose:true,
						     },
						     function(){
						        var i = parent.layer.getFrameIndex(window.name); //获取窗口索引
						        parent.layer.close(i);
						        Android.close(data.id, data.name);
						      }
						    );
        	   	} else {
        		   	alert(data.msg);
        	   	}
        });
    }
</script>
</body>
</html>
