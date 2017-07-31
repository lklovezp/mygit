<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>环评信息详情</title>
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
        .basicinfoTable a{color:#0088cc;}
        .panel-header{background-color: #cff1ff;}
        .formtable, .formtable th, .formtable td{border-color:#dddddd;}
        .tabs li a.tabs-inner {
               background: #cff1ff;
               color: #000000;
               filter: none;
            }
      </style>
	</head>

	<body>
		<div class="h1_1" id="divTitle">环评信息详情</div>
		<div id="hpxx">
		<form id="queryForm" action="saveOrUpdateHpxx.json" method="post">
			<input type="hidden" name="id" value="${hpxxForm.id }"/>
			<input type="hidden" name="pid" value="${hpxxForm.pid }"/>
			<table class="dataTable basicinfoTable" width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<th width="15%" bgcolor="#edfaff" align="right">
							<label class="requiredLabel">*</label>环评项目名称：
						</th>
						<td colspan="3">
						${hpxxForm.name }
						</td>
					</tr>
					<tr>
						<th width="15%" bgcolor="#edfaff" align="right">
							环评审批文号：
						</th>
						<td width="35%">${hpxxForm.docnum1 }
						</td>
						<th width="15%" bgcolor="#edfaff" align="right">
							审批时间：
						</th>
						<td width="35%">${hpxxForm.docnum1date }
						</td>
					</tr>
					<tr>
						<th  bgcolor="#edfaff" align="right">
							试生产审批文号：
						</th>
						<td>${hpxxForm.docnum2 }
						</td>
						<th align="right" bgcolor="#edfaff">
							审批时间：
						</th>
						<td>${hpxxForm.docnum2date }
						</td>
					</tr>
					<tr>
						<th align="right" bgcolor="#edfaff">
							延期试生产审批文号：
						</th>
						<td>${hpxxForm.docnum3 }
						</td>
						<th align="right" bgcolor="#edfaff">
							审批时间：
						</th>
						<td>${hpxxForm.docnum3date }
						</td>
					</tr>
					<tr>
						<th align="right" bgcolor="#edfaff">
							建设项目竣工<br>‘三同时’验收审批文号：
						</th>
						<td>${hpxxForm.docnum4 }
						</td>
						<th align="right" bgcolor="#edfaff">
							审批时间：
						</th>
						<td>${hpxxForm.docnum4date }
						</td>
					</tr>
					<tr>
						<th align="right" bgcolor="#edfaff">
							清洁生产审批文号：
						</th>
						<td>${hpxxForm.docnum5 }
						</td>
						<th align="right" bgcolor="#edfaff">
							审批时间：
						</th>
						<td>${hpxxForm.docnum5date }
						</td>
					</tr>
				</table>
			</div>
			<div class="panel-header" style="margin-top:10px;" id="fujian">
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

$(document).ready(function(){
	$('#fjlx').combobox({
		url:'fjlxList.json?enumName=hpxx',
		valueField:'id',
		textField:'name'
	});
	$("#fileupload").click(function(){
		var id = $("input[name='id']").val();
		var fjlx = $("#fjlx").combobox('getValue');
		if(fjlx==null || fjlx == ''){
			alert("请选择附件类型！");
			return;
		}
		if(id!=null && id!=''){
			All.ShowBrowserWin('uploadPage.htm?pid='+id+'&fileType='+fjlx+'&path=XXGL', '上传污染源附件', 596, 346);
		}else{
			if($("#queryForm").form("validate")){
				$.messager.confirm('操作确认', '上传附件需要保存当前环评信息，是否继续？', function(r){
					if (r){
						$("#queryForm").ajaxSubmit({
							type:"post",
							dataType:"json",
							url:"saveOrUpdateHpxx.json",
							success: function(data){
								if(data.state){
									$("input[name='id']").val(data.id);
									All.ShowBrowserWin('uploadPage.htm?pid='+data.id+'&fileType='+fjlx+'&path=XXGL', '上传污染源附件', 596, 346);
									
								}else{
									$.messager.alert('保存工業污染源:',data.msg);
								}
							}
						});
					}
				});
			}
		}
	});
	$("#infectlist").height($(window).height() -  $("#hpxx").outerHeight() - $("#divTitle").outerHeight()-$("#fujian").outerHeight()-10);
	$("#infectlist").width($(window).width());
	//附件列表
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		fitColumns:true,
		nowrap: false,
		url:'queryFileList.json?canDel=N',
		queryParams:{pid:$("input[name='id']").val()},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:100},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:100},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:30},
			{field:'operate',title:'操作', align:'center', halign:'center',width:30}
		]]
	});
	//表单校验
    $("#queryForm").validate({
		errorClass: "error",
		submitHandler:function(form){
			if($("#queryForm").form("validate")){
				$(form).ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"saveOrUpdateHpxx.json",
					success: function(data){
						if(data.state){
							alert(data.msg);
							self.close();
						}else{
							$.messager.alert('保存环评信息:',data.msg);
						}
					}
				});
			}
		}
	});
	
});

</script>