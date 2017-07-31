<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>再次检查</title>
        <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css" />
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<!-- app -->
		<link rel="stylesheet" type="text/css" href="${app}/home.css" />
		<!-- ztree -->
		<link rel="stylesheet" href="${ztree}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
		<script type="text/javascript" src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="${ztree}/js/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript" src="${ztree}/js/jquery.ztree.exedit-3.5.js"></script>

		<script type="text/javascript" src="${upload}/extjs/ext-all.js"></script>
		<link type="text/css" href="${upload}/extjs/resources/css/ext-all.css" rel="stylesheet" />
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
		<script type="text/javascript" src="${jquery}/json2.js"></script>
		<script type="text/javascript" src="${common}/All.js"></script>
		<link href="${app}/zTreeReset.css" rel="stylesheet" type="text/css"/>
	</head>
	<body style="padding:10px;">
		<div id="main">
			<form id='jcqkform' method='post' action=''>
			<input type="hidden" id="taskTypeCode" value="${taskTypeCode}"/>
			<input type="hidden" id="applyId" value="${applyId}"/>
			<input type="hidden" id="fileId" value="${fileId}"/>
			<input type="hidden" id="jcmbId" value="${jcmbId}"/>
			<input type="hidden" id="fileTypeEnumName" value="${fileTypeEnumName}"/>
			<input type="hidden" id="moreCheckFiletype" value="${moreCheckFiletype}"/>
			<span style="height:100px;width:110px; display:block;vetical-align:top;text-align:right;float:left;font-size:14px;">现场监察情况：</span>
			<textarea class="y-textarea" id="jcqk" name="jcqk" maxlength="2000" onchange="this.value=this.value.substring(0, 2000)" onkeydown="this.value=this.value.substring(0, 2000)" onkeyup="this.value=this.value.substring(0, 2000)" style="width:450px; height:270px;">${jcqk}</textarea>
			<div class="bottomBtn">
				<input class="queryBlue" onclick="buildJcbl()" type="button" value="生成监察笔录" />
            </div>
			</form>
		</div>
	</body>
</html>

<script type="text/javascript">
//通用（生成多次检查监察笔录）
function buildJcbl(){
	var jcqk = $("#jcqk").val();
	if (jcqk == ""){
		alert("请先填写监察情况");
		return;
	}
	var taskTypeCode = $("#taskTypeCode").val();
	var applyId = $('#applyId').val();
	var jcmbId = $('#jcmbId').val();
    var fileTypeEnumName=$('#fileTypeEnumName').val();//附件类型枚举名称
    var moreCheckFiletype=$('#moreCheckFiletype').val();//多次检查附件类型枚举名称
    var fileId = $('#fileId').val();//监察笔录已存在，修改现场监察情况
	$('#jcqkform').attr('action','buildJcbl.json?jcmbId='+jcmbId+'&applyId='+applyId+'&taskType='+taskTypeCode + '&fileid=' + fileId);
	$('#jcqkform').ajaxSubmit(function(data){
	   		if(data.state){
				alert(data.msg);
		   		window.parent.showJcbl(fileTypeEnumName,moreCheckFiletype,taskTypeCode);
		   		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		   		parent.layer.close(index);
	   		} else {
		   		alert(data.msg);
	   		}
	 });
}
</script>
