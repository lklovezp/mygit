<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加节点</title>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${tinymce}/tinymce.min.js"></script>
		<script type="text/javascript" src="${tinymce}/langs/zh_CN.js"></script>
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
		<form id="myform" name="myform" method="post" action="saveDiscretion.json">
			<input type="hidden" id="id" name="id" value="${po.id }" />
			<input type="hidden" id="pid" name="pid" value="${pid }"/>
			<input type="hidden" id="suffix" name="suffix" />
			<input type="hidden" id="contentnote" name="contentnote" />
			<table align="center"  border="0" cellpadding="0" cellspacing="0" class="formtable">
						<tr>
						<th>
							节点类型：
						</th>
						<td>
						 <c:if test="${not empty po.id}">
						         ${note}
						        <input type="hidden" name="code" value="${po.code}"/>
						   </c:if>
						<c:if test="${empty po.id}">
							<input class="i-text" class="i-text easyui-validatebox" required="true" type="text" value="${po.code}" id="code" name="code" />
						</c:if>
						</td>
					   </tr>
						<tr>
							<th  id="addName" width="80" height="28" nowrap="nowrap">
							法律依据名称：
							</th>
							<td align="left" width="360">
								<input class="i-text easyui-validatebox" maxlength="20" required="true" id="name" name="name" value="${po.name }"  class="i-text required" type="text" />
							</td>
						</tr>
						<tr>
							<th width="80" height="28" nowrap="nowrap">
								关键词：
							</th>
							<td align="left" width="360">
								<input id ="gjc" name="gjc" value="${po.gjc }" maxlength="15" class="i-textarea" type="text" />
							</td>
						</tr>
						<tr>
							<th id="discontent" width="80" height="28" nowrap="nowrap">
								法律依据内容：
							</th>
							<td align="left" width="360">
								<textarea id ="content" class="i-textarea" maxlength="150" name="content">
								${po.content }
								</textarea>
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
<c:if test="${empty po.id}">
	$('#code').combobox({
		url:'queryDisCode.json?code=${code}',
		valueField:'id',
		textField:'name',
		onSelect:function(r){
			 $.ajax({
			  url:'queryDisNote.json?code='+r.id,
			  success:function(data){
					  	$('#addName').html(data.name);
					  	$('#discontent').html(data.note);
					  	
				 }
			 });
	    }
	});
	</c:if>
	setname(${po.code});
	tinymce.init({selector:'textarea'});
});
function setname(code){
 $.ajax({
			  url:'queryDisNote.json?code='+code,
			  success:function(data){
					  	$('#addName').html(data.name);
					  	$('#discontent').html(data.note);
				 }
			 });
}
$('#tijiao').click(function(){
	var content =tinymce.get('content').getContent();
	$('#contentnote').val(content);
	$('#myform').submit();
});
$('#myform').submit(function(){
    if(!$(this).form('validate'))
  		 {
  		 	return false;
  		 }
      $("#myform").ajaxSubmit({
     	  success: function(data){
     	  	parent.ref();
			parent.$.colorbox.close();	
	      }
	 });
     return false;  
});
</script>