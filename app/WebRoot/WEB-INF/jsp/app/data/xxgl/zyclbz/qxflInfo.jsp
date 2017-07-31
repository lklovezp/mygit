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
		<form id="queryForm" action="" method="post">
			<div class="divContainer" id="condition">
				<table class="dataTable basicinfoTable" width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<th width="250" align="right" bgcolor="#edfaff" align="right">
							制度分类：
						</th>
						<td>
							${tDataDiscrecaseclass.tDataDiscremerit.tDataDiscreacts.type.name}
						</td>
					</tr>
					<tr>
						<th align="right" bgcolor="#edfaff" align="right">
							违法行为：
						</th>
						<td>
							${tDataDiscrecaseclass.tDataDiscremerit.tDataDiscreacts.content}
						</td>
					</tr>
					<tr>
						<th align="right" bgcolor="#edfaff" align="right">
							法律依据：
						</th>
						<td>
							${tDataDiscrecaseclass.tDataDiscremerit.content}
						</td>
					</tr>
					<tr>
						<th align="right" bgcolor="#edfaff" align="right">
							情形分类：
						</th>
						<td >
							${tDataDiscrecaseclass.name}
						</td>
					</tr>
				</table>
			</div>
			<div style="width: 100%; height: 22px; background-color: rgb(224, 236, 255); padding-top: 5px; border: 1px solid #95b8e7; border-left: none; border-right: none; color: #0e2d5f; line-height: 16px; font-weight: bold; margin-top: 10px;">
				<label style="margin-left: 5px;">
					自由裁量权裁内容
				</label>
			</div>
			<div id="divContainer" class="divContainer" style="margin:10px;" id="infectlist">
				${tDataDiscrecaseclass.content }
			</div>
		</form>
	</body>
</html>

<script language="JavaScript">
	$(document).ready(function() {
		$("#infectlist").height($(window).height() - $("#condition").outerHeight() - 69);
		$("#infectlist").width($(window).width() - 20);
		
		$("#divContainer table").css("width","100%");
		$("table td").css("border","1px solid #d4d4d4");
	});
</script>