<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>稿件审批</title>
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
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
        .panel-header{background-color: #cff1ff;}
        .formtable, .formtable th, .formtable td{border-color:#dddddd; }
        </style>
	</head>
	<body>
		    <div class="h1_1" id="divTitle">稿件审批</div>
		    <input type="hidden" id="id" name="id" value="${drafter.id}" />
			<div class="divContainer" style="padding:10px;">
			<!-- 任务信息 -->
			<div class="easyui-panel" title="稿件信息" style="margin-bottom:10px;">
				<table width="100%" height="" border="0" align="left" cellpadding="0"
					cellspacing="0" class="formtable">
					<tr>
						<th width="100" align="right">
							拟稿人：
						</th>
						<td width="400">
						    <label>${drafter.drafterName}</label>
						</td>
						<th width="100" align="right">
							发送时间：
						</th>
						<td>
						    <label><fmt:formatDate value="${drafter.submitDate}" pattern="yyyy-MM-dd HH:mm:ss"/></label>
						</td>
					</tr>
					<tr>
						<th width="100" align="right">
							稿件状态：
						</th>
						<td align="left">
						    <label>${state}</label>
						</td>
						<th width="100" align="right">
							审批时间：
						</th>
						<td>
						    <label><fmt:formatDate value="${drafter.auditDate}" pattern="yyyy-MM-dd HH:mm:ss"/></label>
						</td>
					</tr>
					<tr>
						<th width="100" align="right">
							稿件主题：
						</th>
						<td align="left" colspan="3">
						    <label>${drafter.name}</label>
						</td>
					</tr>
					<tr>
						<th align="right">
							稿件内容：
						</th>
						<td align="left" colspan="3">
						    <label>${drafter.describe}</label>
						</td>
					</tr>
				</table>
			</div>
			<!-- 相关附件 -->
			<div class="easyui-panel" title="相关附件" style="margin-bottom:10px;">
				<table style="width : 100%;" border="0" align="left" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="24" align="right">
							<a href="#" id="" class="btslink" onclick="downZipFile()">打包下载</a>&nbsp;
						</td>
					</tr>
					<tr>
						<td >
							<div style="height: 200px">
							<table id="XGFJdata" fit="true"></table>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		<table style="width : 100%;">
			<tr>
			    <td align="center" height="50">
			    	
					<input id="yesbutton" type="button" class="queryBlue" onclick="audit('true')" value="通过"> &nbsp;&nbsp;
					<input id="nobutton" type="button"  class="queryBlue" value="不通过" onclick="audit('false')">
				</td>
			</tr>
		</table>
		
		<iframe name="download" id="download" src="" style="display: none"></iframe>
	</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
    //相关附件列表
    $('#XGFJdata').datagrid({
		height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns : true,
		url:'queryFileList.json?canDel=N',
		queryParams:{pid:$("#id").val(),fileType:'GJGLGJFJ',tableId:'XGFJdata'},
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

	//审核
	function audit(result) {
		   
			var id = $("#id").val();
			$.ajax( {
				url : "auditDrafter.json?result="+result+"&id=" + id,
				success : function(data) {
					if (data.state) {
						alert(data.msg);
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						parent.layer.close(index);
					} else {
						$.messager.alert('审批稿件:', data.msg);
					}
				}
			});
	}
	
</script>