<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>投诉信息详情</title>
		<script type="text/javascript" src="${app}/js.js"></script>
		<script type="text/javascript" src="${app}/data.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery }/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery }/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery }/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui }/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui }/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox }/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui }/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui }/themes/default/easyui.css">
		<link href="${app }/hnjz.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${colorbox }/colorbox.css">
		<script type="text/javascript" src="${common}/All.js"></script>
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
        <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
         <style>
        .basicinfoTable,.basicinfoTable td{ border: 1px solid #d4d4d4;}
        </style>
	</head>

	<body>
		<div class="h1_1" id="divTitle">投诉信息详情</div>
		<div id="condition">
		<form id="queryForm" action="" method="post">
		<input type="hidden" name="id" value="${complaintForm.id }"/>
			
				<table class="dataTable basicinfoTable" width="100%" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<th width="15%" bgcolor="#edfaff" align="right">
							执法对象类型：
						</th>
						<td colspan="3">
						${typename }
						
						</td>
					</tr>
					<tr>
						<th width="15%" bgcolor="#edfaff" align="right">
							单位名称：
						</th>
						<td colspan="3" >${complaintForm.lawobjname }
						</td>
					</tr>
					<tr>
						<th bgcolor="#edfaff" align="right">
							地址：
						</th>
						<td colspan="3">${complaintForm.lawobjaddr }
						</td>
					</tr>
					<tr>
						<th width="15%" bgcolor="#edfaff" align="right">
							投诉时间：
						</th>
						<td width="35%">${complaintForm.cpdate }
						</td>
						<th width="15%" bgcolor="#edfaff" align="right">
							投诉原因：
						</th>
						<td width="35%">${complaintForm.reason }
						</td>
					</tr>
					<tr>
						<th bgcolor="#edfaff" align="right">
							投诉人姓名：
						</th>
						<td>${complaintForm.cpusername }
						</td>
						<th bgcolor="#edfaff" align="right">
							投诉人联系电话：
						</th>
						<td>${complaintForm.cpmobile }
						</td>
					</tr>
					<tr>
						<th bgcolor="#edfaff" align="right">
							处理时间：
						</th>
						<td>${complaintForm.handletime }
						</td>
						<th bgcolor="#edfaff" align="right">
							治理措施：
						</th>
						<td colspan="3">${complaintForm.control }
						</td>
					</tr>
					<tr>
						<th bgcolor="#edfaff" align="right">
							状态：
						</th>
						<td colspan="3">
						<c:if test="${complaintForm.isActive == 'Y' }">有效
						</c:if>
						<c:if test="${complaintForm.isActive == 'N' }">无效
						</c:if>
						</td>
					</tr>
					<tr>
						<th bgcolor="#edfaff" align="right">
							备注：
						</th>
						<td colspan="3">${complaintForm.desc }
						</td>
					</tr>
				</table>
			</div>
			<div class="panel-header" style="margin-top:10px;margin: auto;">
				<div class="panel-title">
					附件信息
				</div>
			</div>
			<div class="divContainer" id="infectlist">
				<table id="data" fit="true"></table>
			</div>
		</form>
		<iframe name="download" id="download" src="" style="display: none"></iframe>
	</body>
</html>

<script language="JavaScript">
//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	
});

$(document).ready(function(){

	$("#infectlist").height($(window).height()-$("#condition").outerHeight()-$("#divTitle").outerHeight()-30);
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	
	//附件列表
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		nowrap:false,
		fitColumns : true,
		url:'queryFileList.json?canDel=N',
		queryParams:{pid:$("input[name='id']").val()},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:100},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:30},
			{field:'operate',title:'操作', align:'center', halign:'center',width:30}
		]]
	});
});

</script>