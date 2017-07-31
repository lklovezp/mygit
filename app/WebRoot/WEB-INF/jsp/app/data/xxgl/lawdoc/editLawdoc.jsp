<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css">
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox2.css">
		<style>
        .basicinfoTable,.basicinfoTable td,.basicinfoTable th{ border: 1px solid #d4d4d4;}
        caption, th {
          text-align: right;
          }
        </style>
	</HEAD>

	<body>
		<div class="h1_1" id="divTitle">编辑执法文件</div>
		<div id="divContainer" style="">
			<form id="queryForm" method="post" action="updateLawdoc.json" enctype="multipart/form-data">
				<input type="hidden" value="${lawdoc.id}" id="id" name="id">
				<input type="hidden" value="${lawdoc.fileid}" id="fileid" name="fileid">
				<table class="dataTable basicinfoTable" width="100%"  cellpadding="0" cellspacing="0">
					<tr>
						<th width="40%" bgcolor="#edfaff" >
							<label class="requiredLabel">*</label>
							标题：
						</th>
						<td>
							<input class="y-text"  name="name" type="text" style="width: 250px" value="${lawdoc.name}" />
						</td>
					</tr>
					<tr>
						<th bgcolor="#edfaff">
							<label class="requiredLabel">*</label>目录：
						</th>
						<td>
							<input class="y-text" id="dirid" name="dirid" type="text" style="width: 260px" value="${lawdoc.dirid}"/>
						</td>
					</tr>
					<tr>
						<th bgcolor="#edfaff">
							关键字：
						</th>
						<td>
							<input class="y-text" name="keywords" type="text" style="width: 250px" value="${lawdoc.keywords}" />
						</td>
					</tr>
					<tr>
						<th bgcolor="#edfaff">
							文件：
						</th>
						<td>
							<input class="y-text" type="file" id="file" name="file" type="text" style="width: 250px" value="" />
							<c:if test="${lawdoc.filename != ''}">
									<br>
									${lawdoc.filename} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a class="btslink" id="downloadfile">下载</a>
							</c:if>
						</td>
					</tr>
					
					<tr>
						<td align="center" colspan="4">
							<input type="submit" id="query" class="queryBlue" value="保   存"/>
				            <input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<iframe name="download" id="download" src="" style="display: none"></iframe>
	</body>
</html>

<script language="JavaScript">
<c:if test="${not empty state}">

	<c:if test="${state}">
		alert("${msg}");
		self.close();
	</c:if>
	<c:if test="${!state}">
		alert("${msg}");
	</c:if>

</c:if>

$(document).ready(function(){
	$.ajaxSetup({cache:false});
	
	$("#dirid").combotree({
		height:34,
		required:true,
		url:'lawdocdirTree.json'
	});
	
	//表单校验
	$("#queryForm").validate({
		errorClass: "error",
		submitHandler:function(form){
			if($("#file").val()==""){
				$("#file").attr("disabled","disabled");
			}
			$("#queryForm").ajaxSubmit({
				type:"post",
				dataType:"json",
				url:"updateLawdoc.json",
				success: function(data){
					if(data.state){
						alert(data.msg);
						//self.close();
						parent.closeLayer();
					}else{
						$.messager.alert('保存执法文件:',data.msg);
						$("#file").removeAttr("disabled");
					}
				}
			});
		},
		rules:{
			"name": { required: true },
			"orderby":{ required: true,digits:true }
		}
	});
	
	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
	});
	
	$("#downloadfile").click(function(){
		$('#download').attr('src','downloadFile?id=${lawdoc.fileid}');
	});
});
</script>