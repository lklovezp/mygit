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
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/default/easyui.css">
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css">

		<script type="text/javascript"
			src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox2.css">
	</HEAD>

	<body>
		<div style="padding: 20px;">
			<form id="myform" name="myform" method="post"
				action="saveWorkType.json">
				<table width = "80%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="formtable">
				<input type="hidden" value="${workTypeForm.id}" id="id" name="id">
				<div id="neirong" style="padding: 5px;">
					<tr>
						<th>
							<span class="mark">*</span>名称：
						</th>
						<td>
							<input class="i-text" type="text" id="name" name="name"
							value="${workTypeForm.name}" />
						</td>
					</tr>
					
					<tr>
						<th>
							Code：
						</th>
						<td>
							<input class="i-text" type="text" value="${workTypeForm.code}"
							id="code" name="code" />
						</td>
					</tr>
					<tr>
						<th>
							父节点：
						</th>
						<td>
							<input type="hidden" value="${workTypeForm.pid}" id="pid"
							name="pid">
							<input class="i-text" type="text" readonly="true"
								value="${workTypeForm.pname}" id="pname" name="pname" />
							<a href="#" id="selectParent">选择父节点</a>
						</td>
					</tr>
					<tr>
						<th>
							执行界面url：
						</th>
						<td>
							<input class="i-text" type="text" value="${workTypeForm.url}"
							id="url" name="url" />
						</td>
					</tr>
					<tr>
						<th>
							执行界面url2：
						</th>
						<td>
							<input class="i-text" type="text" value="${workTypeForm.url2}"
							id="url2" name="url2" />
						</td>
					</tr>
					<tr>
						<th>
							手机功能activity
						</th>
						<td>
							<input class="i-text" type="text" value="${workTypeForm.sjurl}"
							id="sjurl" name="sjurl" />
						</td>
					</tr>
					<tr>
						<th>
							工作流：
						</th>
						<td>
							<input class="i-text" type="text" value="${workTypeForm.gzliu}"
							id="gzliu" name="gzliu" />
						</td>
					</tr>
					<tr>
						<th>
							<span class="mark">*</span>	排序：
						</th>
						<td>
							<input class="i-text" type="text" value="${workTypeForm.order}"
							id="order" name="order" />
						</td>
					</tr>
					<tr>
						<th>
							审核级别：
						</th>
						<td>
							<input class="i-text" type="text" value="${workTypeForm.shjb}"
							id="shjb" name="shjb" />
						</td>
					</tr>
					<tr>
						<th>
							说明：
						</th>
						<td>
							<input class="i-text" type="text" value="${workTypeForm.note}"
							id="note" name="note" />
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<span class="btn btn-ok"> <input id="workTypeBtn" type="submit" value="提交">
							</span>&nbsp;
							<a href="#" id="J-from-reset">重置</a>
						</td>
					</tr>
				</div>
				</table>
			</form>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
function setWorkInfo(id,name) {
    $('#pid').val(id)
    $('#pname').val(name)
    $('#pname').focus();
    jQuery().colorbox.close();
}
$(document).ready(function(){
    $("#selectParent").colorbox({iframe:true,width:"240", height:"400",href:"workTypePubQuery.htm?id=${workTypeForm.pid}"});
	$("#J-from-reset").click(function(){
	   $("#myform").reset();
	});
	$('#code').combobox({
		url:'queryAllWkCode.json',
		valueField:'id',
		textField:'name'
	});
	$('#shjb').combobox({
		url:'userPosition.json',
		valueField:'id',
		textField:'name'
	});
	$('#gzliu').combobox({
		url:'queryGzliu.json',
		valueField:'id',
		textField:'name'
	});
   	//表单校验
    $("#myform").validate(
    {
          errorClass: "error",
          submitHandler:function(form){
    		$("#workTypeBtn").attr({"disabled":"disabled"});
            $(form).ajaxSubmit({
		        type:"post",
		        success: function(data){
		          $.messager.alert('保存任务类型:',data.msg);
		          	if(data.state){
			          parent.ref();
			        }
		        }
		      });
		  },
          rules:
          {  
			 "name": { required: true },
			 "order": { required: true,digits:true }
          }
     });
});
</SCRIPT>