<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>任务派发-接受任务</title>
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${jquery}/messages_cn.js"></script>    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
    <link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
    <script type="text/javascript" src="${common}/All.js"></script>
    <!-- 任务管理 css-->
    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
    <!-- ztree -->
    <link rel="stylesheet" href="${ztree}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
    <!-- zTree 修改的样式-->
    <link href="${app}/zTreeReset.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="checkup">
<input type="hidden" id="applyId" name="applyId" value="${work.id}" />
    <h1 id="checkup_header">基本信息</h1>
    <table class="dataTable" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="48">
            <th colspan="4" align="left">&nbsp;&nbsp;&nbsp;&nbsp;任务信息</th>
        </tr>
        <tr  height="42">
            <td width="12%" align="center">任务名称</td>
            <td width="88%" colspan="3">${jbxxMap.name}</td>
        </tr>
        <tr  height="42">
            <td width="12%" align="center">主要内容</td>
            <td width="88%" colspan="3">${jbxxMap.workNote}</td>
        </tr>
        <tr  height="42">
            <td width="12%" align="center">任务类型</td>
            <td width="60%" colspan="1">${jbxxMap.rwlxNames}</td>
            <td width="12%" align="center">任务来源</td>
            <td width="16%" colspan="1">${jbxxMap.source}</td>
        </tr>
        <c:if test="${jbxxMap.rwlxIds != '24'}" >
        <tr  height="42">
            <td width="12%" align="center">执法对象</td>
            <td colspan="3">
            	<c:choose>
					<c:when test="${jbxxMap.source eq '随机抽查' && (jbxxMap.state eq '01' || jbxxMap.state eq '02' || jbxxMap.state eq '03')}">
									×××××企业
					</c:when>
					<c:otherwise>
					<label id="">${jbxxMap.zfdxNames}</label>
					</c:otherwise>
				</c:choose>
            </td>
        </tr>
        </c:if>
        <tr  height="42">
            <td width="12%" align="center">任务登记人</td>
            <td colspan="1">${jbxxMap.djrName}</td>
            <td width="12%" align="center">任务派发人</td>
            <td colspan="1">${jbxxMap.pfrName}</td>
        </tr>
        <tr  height="42">
            <td width="12%" align="center">紧急程度</td>
            <td colspan="1">${jbxxMap.emergency}</td>
            <td width="12%" align="center">任务密级</td>
            <td colspan="1">${jbxxMap.security}</td>
        </tr>
         <tr  height="42">
            <td width="12%" align="center">任务派发时间</td>
            <td colspan="1">${jbxxMap.createTime}</td>
            <td width="12%" align="center">要求完成时限</td>
            <td colspan="1">${jbxxMap.endTime}</td>
        </tr>
    </table>
    <!-- 历史批示 -->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
       <c:forEach var="po" items="${jbxxMap.lsps}">
        <tr bgcolor="#cff1ff" height="48">
            <th align="left"><span style="float: right;margin-right:15px;"><fmt:formatDate value="${po.czsj}" pattern="yyyy-MM-dd HH:mm"/></span>&nbsp;&nbsp;&nbsp;&nbsp;${po.czrName}</th>
        </tr>
        <tr height="42">
            <td style="color: #666666;">
            	<div class="taskFlow_content" style="overflow:auto;width:100%;">${po.note}</div>
            </td>
        </tr>
	   </c:forEach>
    </table>
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            相关附件
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="XGFJdata" fit="true"></table>
        </div>
    </div>
</div>
<iframe name="download" id="download" src="" style="display: none"></iframe>
</body>
</html>

<script type="text/javascript">
var applyId = $('#applyId').val();

$(document).ready(function() {
	//执法对象列表
	$('#zfdxTable').datagrid({
	    url:'zfdxTable.json', 
	    queryParams:{applyId:applyId},
	    width:'100%',
		height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns:true,
	    columns:[[  
	        {field:'lawobjname',title:'执法对象',width:100,align:'center'},   
	        {field:'address',title:'地址',width:100,align:'center'},
	        {field:'manager',title:'负责人',width:100,align:'center'},
	        {field:'managermobile',title:'联系方式',width:100,align:'center'}
	    ]]
	});
	
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

</script>