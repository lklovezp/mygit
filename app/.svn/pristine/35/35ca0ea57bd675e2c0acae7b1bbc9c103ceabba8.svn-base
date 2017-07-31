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
		<div class="h1_1" id="divTitle">违法行为详情</div>
		<div class="divContainer">
			<form id="queryForm" action="" method="post">
				<table class="dataTable basicinfoTable" width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<th width="100" align="right" bgcolor="#edfaff" align="right">
							制度分类：
						</th>
						<td>
							<label id="">${tDataDiscreacts.type.name }</label>
						</td>
					</tr>
					<tr>
						<th width="100" align="right" bgcolor="#edfaff" align="right">
							违法类型：
						</th>
						<td >${tDataDiscreacts.illegaltype.name }
						</td>
					</tr>
					<tr>
						<th width="100" align="right" bgcolor="#edfaff" align="right">
							违法行为：
						</th>
						<td >${tDataDiscreacts.content }
						</td>
					</tr>
					<tr>
						<th width="100" align="right" bgcolor="#edfaff" align="right">
							排序：
						</th>
						<td >${tDataDiscreacts.orderby }
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>

<script language="JavaScript">

$(document).ready(function(){
	$('#wflx').combotree({
		required: true,
		type:"post",
		url:'illegalTypeList.json'
	});
	
	//表单校验
    $("#queryForm").validate({
		errorClass: "error",
		submitHandler:function(form){
			$(form).ajaxSubmit({
				type:"post",
				url:"saveOrUpdateWfxw.htm",
				success: function(){
					alert("保存成功!");
					//self.close();
					var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
					parent.parent.layer.close(index);
				}
			});
		},
		rules:{  
			"content": { required: true },
			"orderby": { required: true,digits:true }
		}
	});
	
});
</script>