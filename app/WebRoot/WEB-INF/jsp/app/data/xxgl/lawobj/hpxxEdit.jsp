<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>新建/编辑环评信息</title>
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
            caption, th {text-align: right;}
      </style>
	</head>

	<body style="padding-bottom: 70px;">
		<div class="h1_1" id="divTitle">${title }</div>
		<form id="queryForm" action="saveOrUpdateHpxx.json" method="post">
			<input type="hidden" name="id" value="${hpxxForm.id }"/>
			<input type="hidden" name="pid" value="${hpxxForm.pid }"/>
			<div class="divContainer" id="questionContainer">
				<table class="dataTable basicinfoTable" width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<th width="140" align="right" bgcolor="#edfaff" align="right">
							<label class="requiredLabel">*</label>环评项目名称：
						</th>
						<td colspan="3">
							<input class="i-text easyui-validatebox" data-options="required:true" type="text" name="name" value="${hpxxForm.name }"/>
						</td>
					</tr>
					<tr>
						<th align="right" bgcolor="#edfaff" align="right">
							环评审批文号：
						</th>
						<td>
							<input class="i-text" type="text" name="docnum1" value="${hpxxForm.docnum1 }"/>
						</td>
						<th align="right" bgcolor="#edfaff" align="right">
							审批时间：
						</th>
						<td>
							<input class="easyui-datebox" data-options="editable:false" type="text" name="docnum1date" value="${hpxxForm.docnum1date }"/>
						</td>
					</tr>
					<tr>
						<th align="right" bgcolor="#edfaff" align="right">
							试生产审批文号：
						</th>
						<td>
							<input class="i-text" type="text" name="docnum2" value="${hpxxForm.docnum2 }"/>
						</td>
						<th align="right" bgcolor="#edfaff" align="right" >
							审批时间：
						</th>
						<td>
							<input class="easyui-datebox" data-options="editable:false" type="text" name="docnum2date" value="${hpxxForm.docnum2date }"/>
						</td>
					</tr>
					<tr>
						<th align="right" bgcolor="#edfaff" align="right">
							延期试生产审批文号：
						</th>
						<td>
							<input class="i-text" type="text" name="docnum3" value="${hpxxForm.docnum3 }"/>
						</td>
						<th align="right" bgcolor="#edfaff" align="right">
							审批时间：
						</th>
						<td>
							<input class="easyui-datebox" data-options="editable:false" type="text" name="docnum3date" value="${hpxxForm.docnum3date }"/>
						</td>
					</tr>
					<tr>
						<th align="right" bgcolor="#edfaff" align="right">
							建设项目竣工<br>‘三同时’验收审批文号：
						</th>
						<td>
							<input class="i-text" type="text" name="docnum4" value="${hpxxForm.docnum4 }"/>
						</td>
						<th align="right" bgcolor="#edfaff" align="right">
							审批时间：
						</th>
						<td>
							<input class="easyui-datebox" data-options="editable:false" type="text" name="docnum4date" value="${hpxxForm.docnum4date }"/>
						</td>
					</tr>
					<tr>
						<th align="right" bgcolor="#edfaff" align="right">
							清洁生产审批文号：
						</th>
						<td>
							<input class="i-text" type="text" name="docnum5" value="${hpxxForm.docnum5 }"/>
						</td>
						<th align="right" bgcolor="#edfaff" align="right">
							审批时间：
						</th>
						<td>
							<input class="easyui-datebox" data-options="editable:false" type="text" name="docnum5date" value="${hpxxForm.docnum5date }"/>
						</td>
					</tr>
				</table>
			</div>
			<div class="panel-header" style="margin-top:10px;">
				<div class="panel-title" >
					附件信息
				</div>
			</div>
			<table width="100%" height="100%" border="0" cellpadding="0"
				cellspacing="0" class="formtable">
				<tr>
					<th width="150" bgcolor="#edfaff" align="right">
						附件类型：
					</th>
					<td width="200">
						<input id="fjlx" class="i-text" type="text" value=""/>
					</td>
					<td>
						<a id="fileupload" class="btslink">上传附件</a>
					</td>
				</tr>
			</table>
			<div class="divContainer" id="infectlist">
				<table id="data" fit="true"></table>
			</div>
			<div class="rb_btn_fix" style="padding-top:10px;" id="footBtn">
				<input type="submit" id="query" class="queryBlue" value="保   存"/>
				<input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
			</div>
		</form>
		<iframe name="download" id="download" src="" style="display: none"></iframe>
	</body>
</html>

<script language="JavaScript">
$(document).ready(function(){
	$('#fjlx').combobox({
		url:'fjlxList.json?enumName=HPXX',
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
			$("#fileupload").colorbox({
				iframe:true, width:"610", height:"380",
				href:'uploadPage.htm?pid='+id+'&fileType='+fjlx+'&path=XXGL&tableId=data'
			});
		}else{
			if($("#queryForm").form("validate")){
				/*$.messager.confirm('操作确认', '上传附件需要保存当前环评信息，是否继续？', function(r){
					if (r){
						$("#queryForm").ajaxSubmit({
							type:"post",
							dataType:"json",
							url:"saveOrUpdateHpxx.json",
							success: function(data){
								if(data.state){
									$("input[name='id']").val(data.id);
									$.colorbox({
										iframe:true, width:"610", height:"380",
										href:'uploadPage.htm?pid='+data.id+'&fileType='+fjlx+'&path=XXGL&tableId=data'
									});
								}else{
									$.messager.alert('环评信息:',data.msg);
								}
							}
						});
					}
				});*/
				
				var index=layer.confirm('新建环评信息需要保存当前建设项目，是否继续？', {
			     	icon: 3, 
			        title:'操作确认'
			     }, function(index){
			    	 $("#queryForm").ajaxSubmit({
							type:"post",
							dataType:"json",
							url:"saveOrUpdateHpxx.json",
							success: function(data){
								if(data.state){
									$("input[name='id']").val(data.id);
									//All.ShowModalWin('hpxxEdit.htm?pid='+data.id, '新建环评信息');
									$.colorbox({
										iframe:true, width:"610", height:"380",
										href:'uploadPage.htm?pid='+data.id+'&fileType='+fjlx+'&path=XXGL&tableId=data'
									});
								}else{
									$.messager.alert('保存建设项目信息:',data.msg);
									
								}
							}
						});
			        layer.close(index);
			     },function(index){
			        //alert('取消按钮的回调函数');
			        layer.close(index);
			     });
			}
		}
	});
	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
	});
	$("#infectlist").height($(window).height()-$("#footBtn").outerHeight()-$("#questionContainer").outerHeight()-$("#divTitle").outerHeight());
	$("#infectlist").width($(window).width());
	//附件列表
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		fitColumns:true,
		nowrap: false,
		url:'queryFileList.json',
		queryParams:{pid:$("input[name='id']").val()},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:100},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:100},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:50},
			{field:'operate',title:'操作', align:'center', halign:'center',width:50}
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
							/*alert(data.msg);
							//self.close();
							var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						    parent.layer.close(index);
							//opener.location="javascript:reload()";*/
							var index=layer.alert(data.msg,{
						     	title:'保存建设工地信息:',
						        icon:1,
						        shadeClose:true,
						     },
						     function(){
						        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						        parent.layer.close(index);
						        Android.close(data.id, data.name);
						      });
						}else{
							$.messager.alert('保存环评信息:',data.msg);
						}
					}
				});
			}
		}
	});
	
});


/**
 * 公用的上传文件之后的刷新grid方法
 * @param tableId tablegrid的id
 */
function reload(tableId,pid){
	var id = "#" + tableId;
	$(id).datagrid("reload",{pid:pid});
	jQuery.colorbox.close();
}
/**
 * 公用的删除文件方法 删除grid中的文件
 * @param obj grid的一行数据
 
function deletefile1(obj){
	$.messager.confirm('操作', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
				url: "removeFile.json?id="+obj.id,
				success:function(data){
					alert(data.msg);
					$('#data').datagrid('reload',{pid:$("input[name='id']").val()});
				}
			});
		}
	});
}*/
//删除
function deletefile1(obj) {
	var index=layer.confirm('确定删除吗？', {
     	icon: 3, 
        title:'删除任务'
     }, function(index){
    	 $.ajax( {
				url : "removeFile.json?id="+obj.id,
				success : function(data) {
					$('#data').datagrid('reload',{pid:$("input[name='id']").val()});
					//refresh();
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}
</script>