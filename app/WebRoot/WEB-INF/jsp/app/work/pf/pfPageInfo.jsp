<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>任务派发查看</title>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
    
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
    <link rel="stylesheet" href="${ztree}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
    <script type="text/javascript" src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${ztree}/js/jquery.ztree.excheck-3.5.js"></script>
    <!-- zTree 修改的样式-->
    <link href="${app}/zTreeReset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${common}/All.js"></script>
</head>
<body>
<div class="checkup">
    <h1 id="checkup_header">任务派发查看</h1>
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
            <td width="12%" align="center">要求完成时间</td>
            <td width="21%"><label><fmt:formatDate value="${work.endTime}" pattern="yyyy-MM-dd"/></label></td>
            <td width="12%" align="center">接受人</td>
            <td width="21%">${work.jsrNames}</td>
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
    <iframe name="download" id="download" src="" style="display: none"></iframe>
</div>
<div id="checkup_footer" class="t-c">
    <div style="padding-top: 20px;">
        <input id="guanbi" type="button" class="queryBlue" value="关闭" style=" font-size:14px;cursor: pointer;" onclick="javascript:guanbi();">
    </div>
</div>
<script>
$(document).ready(function(){
    //相关附件列表
    $('#XGFJdata').datagrid({
		height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns : true,
		url:'queryFileList.json?canDel=N',
		queryParams:{pid:$("#applyId").val(),fileType:'RWGLPFFJ',tableId:'XGFJdata'},
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
function guanbi(){
	parent.closeLayer();
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
    //高度自适应
    $(window).resize(function(){
        $('#XGFJdata').datagrid("resize");
    });
</script>
</body>
</html>
