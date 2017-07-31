<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base
			href="${pageContext.request.scheme}://${pageContext.request.serverName }:${pageContext.request.serverPort }${ctx }/">
		<title>编辑节点</title>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css" />
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<link href="${app}/file_upload_style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${static}/StringUtil.js"></script>
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
	</head>
	<body>
		<div style="padding-top:10px"></div>
		<form id="myform" name="myform" method="post" action="">
			<input type="hidden" id="id" name="id" value="${po.id }" />
			<table align="center"  border="0" cellpadding="0" cellspacing="0" class="formtable">
						<tr>
							<th width="80" height="28" nowrap="nowrap">
								法律依据名称：
							</th>
							<td align="left" width="360">
								<input id="name" name="name" value="${po.name }" class="i-text required" type="text" />
							</td>
						</tr>
						<tr>
							<th width="80" height="28" nowrap="nowrap">
								关键词：
							</th>
							<td align="left" width="360">
								<textarea id ="gjc" name="gjc"  class="i-textarea" style="width: 170px" cols="20" rows="5">${po.gjc }</textarea>
							</td>
						</tr>
						<tr>
							<th width="80" height="28" nowrap="nowrap">
								法律依据内容：
							</th>
							<td align="left" width="360">
								<textarea id ="content" name="content" class="i-textarea" style="width: 170px" cols="20" rows="5">${po.content }</textarea>
								
							</td>
						</tr>
				<tr>
					<td height="50" colspan="2" align="center" nowrap="nowrap">
						<span class="btn1 btn1-ok"> 
							<input type="button" value="提交" id="tijiao" />
						</span>
					</td>
				</tr>
			</table>
		</form>
		
	</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	
});
$('#tijiao').click(function(){
	var id = $('#id').val();
	var nodeName = $('#name').val();
	var content = "";
	if($('#content')){
		content = $('#content').val();	
	}
	var gjc = "";
	if($('#gjc')){
		gjc = $('#gjc').val();	
	}
	$.post('saveDiscretion.json', 
		{
			id:id,
			name : nodeName,
			content:content,
			gjc:gjc,
			type:'${type}'
		}, 
		function(res) {
			if (res.state) {
				var zTree = parent.$.fn.zTree.getZTreeObj("treeDemo"),
				nodes = zTree.getSelectedNodes(),
				treeNode = nodes[0];
				if(treeNode){
					treeNode.name=nodeName;
					zTree.updateNode(treeNode);
				}
				parent.$('#contentFrame').attr('src','${ctx}/discretionInfo.htm?id='+treeNode.id);
				parent.$.colorbox.close();			
			} else {
				$.messager.alert('提示', res.msg, 'warning');
			}
		}, 'json');	
	});
</script>