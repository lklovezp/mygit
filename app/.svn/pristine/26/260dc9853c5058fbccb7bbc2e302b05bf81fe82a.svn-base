<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css"	href="${easyui}/themes/default/easyui.css">
		<script type="text/javascript"	src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css"	href="${colorbox}/colorbox2.css">
		<!-- app -->
		<script type="text/javascript" src="${common}/All.js"></script>
		<!-- 任务管理 css-->
	    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
	    <!--系统管理 css-->
	    <link rel="stylesheet" href="${app}/css/management.css" type="text/css">
	    <link rel="stylesheet" href="${app}/css/taskDispatch.css" type="text/css">
		<!-- app -->
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
        <style type="text/css">
        .panel-header {
        	border: 1px solid #d4d4d4;
        	background-color: #cff1ff;
        }
        </style>
	</HEAD>

	<body>
		<div style="padding-left:10px">
			<div class="easyui-panel" title="${templateForm.title}" id="main">
				<form id="queryForm" method="post" action="saveSubTemplate.json">
					<input type="hidden" id="id" name="id" value="${templateForm.id}"/>
					<input type="hidden" id="pid" name="pid" value="${templateForm.pid}"/>
					<table class="queryTable" width="100%" border="1px" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
						<tr>
							<th>
								<label class="requiredLabel">*</label>监察模板名称：
							</th>
							<td>
								<input class="y-text easyui-validatebox"  data-options="required:true,tipPosition : 'bottom'" type="text" id="name" name="name" value="${templateForm.name}"/>
							</td>
							<th>
								描述：
							</th>
							<td>
								<textarea class="y-text" id="describe" name="describe"  value="${templateForm.describe}">${templateForm.describe}</textarea>
							</td>
						</tr>
						<tr>
							<th>
								模板名称是否显示：
							</th>
							<td>
								<input type="checkbox" id="namevisiable" name="namevisiable" <c:if test="${fn:trim(templateForm.namevisiable) eq 'Y'}" >checked</c:if>/>
							</td>
							<th>
								<label class="requiredLabel">*</label>排序：
							</th>
							<td>
								<input class="y-text easyui-numberbox" data-options="required:true" type="text" id="orderby" name="orderby" value="${templateForm.orderby}"/>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" id="save" class="bTn directory_save directory_comwidth" value="保  存"/>
				                <input type="reset" id="J-from-reset" class="bTn directory_reset directory_comwidth" value="重  置"/>
				                <input type="button" id="newCheckItmes" class="bTn directory_save directory_comwidth" value="添加检查项"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="divContainer" id="infectlist" style="">
				<table id="data" fit="true"></table>
			</div>
		</div>
	</body>
</html>

<script language="JavaScript">
$(document).ready(function() {
	$.ajaxSetup({cache:false});
	$("#infectlist").height($(window).height() - $("#main").outerHeight() - 28);

	$("#name").validatebox({
		
	});
	$('#data').datagrid( {
		collapsible:true,
		singleSelect:true,
		pagination : true,
		rownumbers : true,
		fitColumns : true,
		url : 'queryCheckListItem.json?templateid=${templateForm.id}',
		onLoadSuccess : function(data) {
			$(this).datagrid('doCellTip', {
				'max-width' : '300px',
				'delay' : 500
			});
		},
		columns : [ [ 
			{field : 'id', hidden : true},
			{field : 'contents', title : '检查项内容', align : 'left', halign : 'center', width : 100},
			{field : 'inputtype', title : '输入类型', align : 'center', halign : 'center', width : 100},
			{field : 'isanswernewline', title : '答案换行显示', align : 'center', halign : 'center', width : 100},
			{field : 'operate', title : '操作', align : 'center', halign : 'center', width : 100}
		] ]
	});
	init();

	$("#queryForm").validate({
		errorClass: "error",
		submitHandler:function(form){
			if ($("#queryForm").form("validate")){
				$(form).ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"saveSubTemplate.json",
					success: function(data){
						if (data.state){
							var tishi=layer.alert(data.msg,{
						     	title:'信息提示',
						        icon:1,
						        shadeClose:true,
						     },
						     function(){
						    	parent.closeLayer();
						        layer.close(tishi);
						     }
						     );
						} else {
							alert(data.msg);
						}
					}
				});
			}
		}
	});
});
// 添加检查项
$("#newCheckItmes").click(function() {
	var href='editCheckListItem.htm?templateid=${templateForm.id}&';
	parent.layerIframe(2,href,"新建检查项",600,420);
	//All.ShowModalWin('editCheckListItem.htm?templateid=${templateForm.id}&', '新建检查项', 650, 550);
	//refresh();
});

function init(){
	var p = $('#data').datagrid('getPager');
	$(p).pagination({
		onSelectPage:function(pageNumber, pageSize){
			$.ajax({
				url: "queryCheckListItem.json?templateid=${templateForm.id}&page="+pageNumber+"&pageSize="+pageSize,
				success:function(data){
					$('#data').datagrid("loadData", data);
				}
			});
		}
	});
}

function edit(curObj){
	var href='editCheckListItem.htm?id='+curObj.id;
	parent.layerIframe(2,href,"编辑检查项",600,420);
	//All.ShowModalWin('editCheckListItem.htm?id='+curObj.id, '编辑检查项', 650, 550);
	//refresh();
}

function del(curObj){
	var index=layer.confirm('确定要删除当前检查项吗？', {
     	icon: 3, 
        title:'监察模板管理'
     }, function(index){
    	 $.ajax( {
				url : "removeCheckListItme.json?id="+curObj.id,
				success : function(data) {
					if(data.state){
						$('#data').datagrid('reload');
					}else{
						var tishi=layer.alert(data.msg,{
					     	title:'信息提示',
					        icon:1,
					        shadeClose:true,
					     },
					     function(){
					        layer.close(tishi);
					     }
					     );
					}
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
	
}
</script>