<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${app}/js.js"></script>
		<script type="text/javascript" src="${app}/data.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<script type="text/javascript" src="/app/static/kindeditor/kindeditor-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<script type="text/javascript" src="${common}/All.js"></script>
		<!-- app -->
		<link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${app}/home.css"/>
		<style>
		.basicinfoTable,.basicinfoTable td{ border: 1px solid #d4d4d4;}
		.basicinfoTable th{background-color: #edfaff;}
        caption, th {text-align: right;}
		</style>
	</head>

	<body style="overflow-x:hidden;">
		<div class="h1_1" id="divTitle"><c:if test="${tDataDiscrecaseclass.id == null }">新建</c:if><c:if test="${tDataDiscrecaseclass.id != null }">编辑</c:if>情形分类</div>
		<div class="divContainer" id="divcontainer">
			<form id="queryForm" action="" method="post">
			<input type="hidden" name="id" value="${tDataDiscrecaseclass.id }"/>
			<input type="hidden" name="tDataDiscremerit.id" value="${tDataDiscrecaseclass.tDataDiscremerit.id }"/>
				<table class="dataTable basicinfoTable" width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<th width="150" align="right">
							制度分类：
						</th>
						<td>
							<label id="">${tDataDiscrecaseclass.tDataDiscremerit.tDataDiscreacts.type.name }</label>
						</td>
					</tr>
					<tr>
						<th width="150" align="right">
							违法行为：
						</th>
						<td>
							<label id="">${tDataDiscrecaseclass.tDataDiscremerit.tDataDiscreacts.content }</label>
						</td>
					</tr>
					<tr>
						<th width="150" align="right">
							法律依据：
						</th>
						<td>
							<label id="">${tDataDiscrecaseclass.tDataDiscremerit.content }</label>
						</td>
					</tr>
					<tr>
						<th width="150" align="right">
							<label class="requiredLabel">*</label>情形分类：
						</th>
						<td>
							<input class="i-text" type="text" name="name" value="${tDataDiscrecaseclass.name }" style="width: 550px;"/>
						</td>
					</tr>
					<tr>
						<th width="100" align="right">
							<label class="requiredLabel">*</label>排序：
						</th>
						<td >
							<input class="i-text" title="10位数字以内" onchange="this.value=this.value.substring(0, 9)" onkeydown="this.value=this.value.substring(0, 9)" onkeyup="this.value=this.value.substring(0, 9)" type="text" name="orderby" value="${tDataDiscrecaseclass.orderby }" style="width: 550px;"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div style="width:100%;height:22px;background-color:rgb(224, 236, 255);padding-top:5px;border:1px solid #95b8e7;border-top-color:#95b8e7;color:#0e2d5f;line-height:16px;font-weight:bold;">
								<label style="margin-left:5px;">自由裁量权裁内容</label>
							</div>
							<textarea id="content" name="content" style="width:100%;height:230px;">${tDataDiscrecaseclass.content }</textarea>
						</td>
					</tr>
				</table>
				<div class="bottomBtn" ><input type="submit" id="query" class="queryBlue" value="保   存"/></div>
			</form>
		</div>
	</body>
</html>

<script language="JavaScript">

	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[id="content"]', {
			resizeType : 1,
			newlineTag : 'br',
			pasteType : 1,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			items : ['source', '|', 
			'undo', 'redo', '|', 
			'preview', 'cut', 'copy', 'paste', 'plainpaste', 'wordpaste', '|', 
			'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 
			'/', 'formatblock', 'fontname', 'fontsize', '|', 
			'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 
			'image', 'table', 'hr', 'emoticons', 'pagebreak']
		});
	});
$(document).ready(function(){
	$("#divcontainer").height($(window).height() - $("#divTitle").outerHeight());
	//表单校验
    $("#queryForm").validate({
		errorClass: "error",
		submitHandler:function(form){
			editor.sync();
			$(form).ajaxSubmit({
				type:"post",
				url:"saveOrUpdateQxfl.htm",
				success: function(){
					alert("保存成功!");
					//self.close();
					var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
					parent.layer.close(index);
				}
			});
		},
		rules:{  
			"name": { required: true },
			"content": { required: true },
			"orderby": { required: true,digits:true }
		}
	});
});
</script>