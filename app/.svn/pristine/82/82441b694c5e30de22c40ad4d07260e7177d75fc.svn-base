<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${app}/js.js"></script>
		<script type="text/javascript" src="${app}/data.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery }/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery }/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery }/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui }/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${easyui }/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox }/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui }/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui }/themes/default/easyui.css">
		<link href="${app }/hnjz.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${colorbox }/colorbox.css">
		<script type="text/javascript" src="${common}/All.js"></script>
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
		<link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
		<style>
        .basicinfoTable,.basicinfoTable td{ border: 1px solid #d4d4d4;}
        caption, th {text-align: right;}
      </style>
	</head>

	<body>
		<div class="h1_1" id="divTitle"><c:if test="${tDataDiscremerit.id == null }">新建</c:if><c:if test="${tDataDiscremerit.id != null }">编辑</c:if>法律依据</div>
		<div class="divContainer">
			<form id="queryForm" action="saveOrUpdateFlyj.htm" method="post">
			<input type="hidden" name="id" value="${tDataDiscremerit.id }"/>
			<input type="hidden" name="tDataDiscreacts.id" value="${tDataDiscremerit.tDataDiscreacts.id }"/>
				<table class="dataTable basicinfoTable" width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<th width="150" align="right" bgcolor="#edfaff" align="right">
							制度分类：
						</th>
						<td >
							<label id="">${tDataDiscremerit.tDataDiscreacts.type.name }</label>
						</td>
					</tr>
					<tr>
						<th width="150" align="right" bgcolor="#edfaff" align="right">
							违法行为：
						</th>
						<td >
							<label id="">${tDataDiscremerit.tDataDiscreacts.content }</label>
						</td>
					</tr>
					<tr>
						<th width="150" align="right" bgcolor="#edfaff" align="right">
							<label class="requiredLabel">*</label>法律依据别名：
						</th>
						<td >
							<input class="i-text" type="text" name="alias" value="${tDataDiscremerit.alias }" style="width: 550px;"/>
						</td>
					</tr>
					<tr>
						<th width="150" align="right" bgcolor="#edfaff" align="right">
							<label class="requiredLabel">*</label>法律依据：
						</th>
						<td >
							<textarea class="i-text" style="width:550px;height:50px;" name="content">${tDataDiscremerit.content }</textarea>
						</td>
					</tr>
					<tr>
						<th width="150" align="right" bgcolor="#edfaff" align="right">
							<label class="requiredLabel">*</label>排序：
						</th>
						<td >
							<input class="i-text" title="10位数字以内" onchange="this.value=this.value.substring(0, 9)" onkeydown="this.value=this.value.substring(0, 9)" onkeyup="this.value=this.value.substring(0, 9)"  type="text" name="orderby" value="${tDataDiscremerit.orderby }" style="width: 550px;"/>
						</td>
					</tr>
				</table>
				<div class="bottomBtn" ><input type="submit" id="query" class="queryBlue" value="保   存"/></div>
			</form>
		</div>
	</body>
</html>

<script language="JavaScript">

$(document).ready(function(){
	//表单校验
    $("#queryForm").validate({
		errorClass: "error",
		submitHandler:function(form){
			$(form).ajaxSubmit({
				type:"post",
				url:"saveOrUpdateFlyj.htm",
				success: function(){
					alert("保存成功!");
					//self.close();
					var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
					parent.layer.close(index);
				}
			});
		},
		rules:{  
			"alias": { required: true },
			"content": { required: true },
			"orderby": { required: true,digits:true }
		}
	});
});
</script>