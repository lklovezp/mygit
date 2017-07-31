<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>新建/编辑投诉信息</title>
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
      </style>
	</head>

	<body style="padding-bottom: 70px;">
		<div class="h1_1" id="divTitle"><c:if test="${complaintForm.id == null }">新建</c:if><c:if test="${complaintForm.id != null }">编辑</c:if>投诉信息</div>
		<form id="queryForm" action="" method="post">
			<input type="hidden" name="id" value="${complaintForm.id }"/>
			<div id="condition">
				<table class="dataTable basicinfoTable" width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<th width="150" bgcolor="#edfaff" align="right">
							<label class="requiredLabel">*</label>执法对象类型：
						</th>
						<td colspan="3">
							<input class="i-text" style="width:450px;" id="lawobjtypeid" name="lawobjtypeid" value="${complaintForm.lawobjtypeid }"/>
						</td>
					</tr>
					<tr>
						<th width="150" bgcolor="#edfaff" align="right">
							<label class="requiredLabel">*</label>单位名称：
						</th>
						<td colspan="3">
							<input class="i-text" type="hidden" id="lawobjid" name="lawobjid" value="${complaintForm.lawobjid }" />
							<textarea readonly="readonly" style="width:450px;height:30px;" class="i-text" id="lawobjname" name="lawobjname" >${complaintForm.lawobjname }</textarea>
							<a class="btslink" id="select">选择</a>
						</td>
					</tr>
					<tr>
						<th bgcolor="#edfaff" align="right">
							<label class="requiredLabel">*</label>地址：
						</th>
						<td colspan="3">
							<textarea style="width:450px;height:30px;" class="i-text" id="lawobjaddr" name="lawobjaddr" />${complaintForm.lawobjaddr }</textarea>
						</td>
					</tr>
					<tr>
						<th bgcolor="#edfaff" align="right">
							<label class="requiredLabel">*</label>投诉时间：
						</th>
						<td>
							<input class="i-text easyui-datebox" data-options="editable:false,required:true" name="cpdate"  value="${complaintForm.cpdate }"/>
						</td>
						<th bgcolor="#edfaff" align="right">
							<label class="requiredLabel">*</label>投诉原因：
						</th>
						<td>
							<textarea style="width:350px;height:30px;" class="i-text" name="reason" />${complaintForm.reason }</textarea>
						</td>
					</tr>
					<tr>
						<th bgcolor="#edfaff" align="right">
							<label class="requiredLabel">*</label>投诉人姓名：
						</th>
						<td>
							<input class="i-text" name="cpusername" value="${complaintForm.cpusername }"/>
						</td>
						<th bgcolor="#edfaff" align="right">
							<label class="requiredLabel">*</label>投诉人联系电话：
						</th>
						<td>
							<textarea style="width:350px;height:30px;" class="i-text" name="cpmobile" />${complaintForm.cpmobile }</textarea>
						</td>
					</tr>
					<tr>
						<th bgcolor="#edfaff" align="right">
							处理时间：
						</th>
						<td>
							<input class="i-text easyui-datebox" data-options="editable:false" name="handletime" value="${complaintForm.handletime }"/>
						</td>
						<th bgcolor="#edfaff" align="right">
							治理措施：
						</th>
						<td colspan="3">
							<textarea style="width:350px;height:30px;" class="i-text" name="control" />${complaintForm.control }</textarea>
						</td>
					</tr>
					<tr>
						<th bgcolor="#edfaff" align="right">
							<label class="requiredLabel">*</label>状态：
						</th>
						<td >
						<c:if test="${complaintForm.id == null }">
							<input class="i-text" type="text" id="zt" name="isActive" value="Y" />
						</c:if>
						<c:if test="${complaintForm.id != null }">
							<input class="i-text" type="text" id="zt" name="isActive" value="${complaintForm.isActive}" />
						</c:if>
						</td>
						<th bgcolor="#edfaff" align="right">
							备注：
						</th>
						<td >
							<textarea style="width:350px;height:30px;" class="i-text" name="desc" />${complaintForm.desc }</textarea>
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
					<td>
						<a id="fileupload" class="btslink">上传附件</a>
					</td>
				</tr>
			</table>
			<div class="divContainer" id="infectlist">
				<table id="data" fit="true"></table>
			</div>
			<div class="rb_btn_fix" id="bottomBtn">
				<input type="submit" id="query" class="queryBlue" value="保   存"/>
				<input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
			</div>
		</form>
		<iframe name="download" id="download" src="" style="display: none"></iframe>
	</body>
</html>

<script language="JavaScript">

//选择三产信息后，set界面属性值
function setValues(id,dwmc,dwdz,fddbr,fddbrlxdh,hbfzr,hbfzrlxdh){
	$("#lawobjid").val(id);
	$("#lawobjname").val(dwmc);
	$("#lawobjaddr").val(dwdz);
	//jQuery().colorbox.close();
}

$(document).ready(function(){
	//执法对象类型
	$('#lawobjtypeid').combotree({
		required:true,
		url:'lawtypeTree.json'
	});
	$('#zt').combobox({
		url:'ztList.json',
		valueField:'id',
		textField:'name'
	});
	$("#select").click(function(){
		var lawobjtypeid = $('#lawobjtypeid').combobox('getValue');
		if(lawobjtypeid == ''){
			alert("请先选择执法对象类型！");
			return;
		}
		//$.colorbox({iframe:true,width:"600", scrolling:false, height:"600",href:"complaintSelectLawobj.htm?lawobjtypeid="+lawobjtypeid});
		layer.open({
	        type: 2,
	        
	        title: '选择执法对象',
	        area: ['900px', '550px'],
	        content: "jsxmzfdx.htm?lawobjtypeid="+lawobjtypeid
	    });
	});
	$("#infectlist").height($(window).height() - $("#bottomBtn").outerHeight() - $("#condition").outerHeight() - $("#divTitle").outerHeight()+120);
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	
	//附件列表
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		nowrap:false,
		fitColumns : true,
		url:'queryFileList.json',
		queryParams:{pid:$("input[name='id']").val()},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:100},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:30},
			{field:'operate',title:'操作', align:'center', halign:'center',width:20}
		]]
	});
	
	
	//表单校验
    var validate = $("#queryForm").validate({
		errorClass: "error",
		submitHandler:function(form){
			if($("#queryForm").form("validate")){
				$(form).ajaxSubmit({
						type:"post",
						dataType:"json",
						url:"saveOrUpdateComplaint.json",
						success: function(data){
							if(data.state){
								//alert(data.msg);
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
								
								//self.close();
							}else{
								$.messager.alert('保存投诉信息:',data.msg);
							}
						}
				});
			}
		},
		rules:{  
			"lawobjname": { required: true ,maxlength:2000},
			"lawobjaddr": { required: true ,maxlength:2000},
			"reason": { required: true },
			"cpusername": { required: true },
			"cpmobile": { required: true }
		}
	});
	
	
    $("#fileupload").click(function(){
		var id = $("input[name='id']").val();
		if(id!=null && id!=''){
			$("#fileupload").colorbox({
				iframe:true, width:"610", height:"380",
				href:'uploadPage.htm?pid='+id+'&fileType=3900&path=XXGL&tableId=data'
			});
		}else{
			if(validate.form() && $("#queryForm").form("validate")){
				//$.messager.confirm('操作确认', '上传附件需要保存当前投诉信息，是否继续？', function(r){
				//	if(r){
						$("#queryForm").ajaxSubmit({
							type:"post",
							dataType:"json",
							url:"saveOrUpdateComplaint.json",
							success: function(data){
								if(data.state){
									$("input[name='id']").val(data.id);
									$.colorbox({
										iframe:true, width:"610", height:"380",
										href:'uploadPage.htm?pid='+data.id+'&fileType=3900&path=XXGL&tableId=data'
									});
								}else{
									$.messager.alert('保存投诉信息:',data.msg);
								}
							}
						});
					//}
				//});
			}
		}
	});
    
    $("#J-from-reset").click(function(){
    	$("#queryForm").form("reset");
    })
	
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

//附件删除
function deletefile1(obj) {
	var index=layer.confirm('确定删除吗？', {
     	icon: 3, 
        title:'删除任务'
     }, function(index){
    	 $.ajax( {
				url : "removeFile.json?id="+obj.id,
				success : function(data) {
					//refresh();
					$('#data').datagrid('reload',{pid:$("input[name='id']").val()});
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}


</script>