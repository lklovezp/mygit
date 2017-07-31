<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
        <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css"href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css"href="${easyui}/themes/default/easyui.css">
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css"href="${colorbox}/colorbox2.css">
		<link rel="stylesheet" href="${app}/css/taskDispatch.css" type="text/css">
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css"/>
        <link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
        <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
	</HEAD>

	<body>
	<div id="bj">
		<form id="queryForm" method="post">
	    <input type="hidden" value="${lawdocdirForm.id}" id="id" name="id">
	    <input type="hidden" value="${lawdocdirForm.pid}" id="pid" name="pid">
        <table class="queryTable mt25" width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
        <tr>
          <th width="25%">名称</th>
          <td width="75%"><input type="text" class="y-text y-directory" name="name" value="${lawdocdirForm.name}"></td>
        </tr>
       
        <tr>
          <th>任务类型</th>
          <td><input type="text" class="y-text y-directory"  id="tasktypeid" name="tasktypeid"></td>
        </tr>
        
        
      </table>
      <div style="text-align: center; padding:5px 0px 30px;">
        <input type="submit" class="bTn directory_save directory_comwidth" id="save" value="保  存"/>
        <input type="reset" class="bTn directory_save directory_comwidth"" id="J-from-reset" value="重  置"/>
        <c:if test="${lawdocdirForm.id!=null }">
        <input type="button" class="bTn directory_reset directory_comwidth" id="delete" value="删  除"/>
        </c:if>
        </form>
		</div>
	</body>
</html>

<script language="JavaScript">
function initH(){
	var hh=$(window).height()-30;
	$("#bj").height(hh);
	
}
initH();
$(window).resize(function(){
   
	initH();
});
$(document).ready(function(){
	$.ajaxSetup({cache:false});
	
	$('#zt').combobox({
		height:34,
		url:'ztList.json',
		valueField:'id',
		textField:'name'
	});
	
	$("#pid").combotree({
		height:34,
		url:'lawdocdirTree.json',
		onBeforeSelect:function(node){
			var id = $("input[name='id']").val();
			if(node.id==id){
				$("#pid").tree('unselect');
			}
		}
	});
	$("#tasktypeid").combotree({
		height:34,
		type:"post",
		multiple:true,
		url:'taskTypeTreeCombo.json'
	});
	
	var tasktypeid = '${lawdocdirForm.tasktypeid}';
	var arr = tasktypeid.split(",");
     var valueArr = new Array();
     for (var i = 0; i < arr.length; i++) {
       valueArr.push(arr[i]);
     }
	$("#tasktypeid").combotree("setValues",valueArr);

	$("#J-from-reset").click(function(){
		$("#queryForm").form("reset");
		$("#tasktypeid").combotree("setValues",valueArr);
	});
/*	
	$("#delete").click(function(){
		var id = $("#id").val();
		if(id==''){
			alert("请选择要删除的目录！");
			return;
		}
		if(!parent.isParent(id)){
			$.messager.confirm('操作确认', '你确定要删除该目录吗？', function(r){
				if(r){
					$("#queryForm").ajaxSubmit({
						type:"post",
						url:"deleteLawdocdir.json",
						dataType:"json",
						success: function(data){
							if(data.state){
								alert("删除成功！");
								parent.location.reload();
							}else{
								alert(data.msg);
							}
						}
					});
				}
			});
		}else{
			alert("该目录下有子节点，不能删除！");
		}
	});*/
//删除
$("#delete").click(function(){
	var id = $("#id").val();
	if(id==''){
		alert("请选择要删除的目录！");
		return;
	}
	if(!parent.isParent(id)){
		var index=parent.layer.confirm('确定删除吗？', {
	     	icon: 3, 
	        title:'删除任务'
	     }, function(index){
	    	 $("#queryForm").ajaxSubmit( {
	    		    type:"post",
					url:"deleteLawdocdir.json",
					dataType:"json",
					success : function(data) {
						if(data.state){
							alert("删除成功！");
							parent.location.reload();
						}else{
							//alert(data.msg);
							var index=parent.layer.alert(data.msg,{
						     	title:'操作信息:',
						        icon:1,
						        shadeClose:true,
						     },
						     function(){
						    	 parent.layer.close(index);
						      });
						}
					}
				});
	        layer.close(index);
	     },function(index){
	        layer.close(index);
	     }
	    );
	}else{
		//alert("该目录下有子节点，不能删除！");
		var index=layer.alert("该目录下有子节点，不能删除！",{
	     	title:'操作信息:',
	        icon:1,
	        shadeClose:true,
	     },
	     function(){
	        layer.close(index);
	      });
	}
	
});

	//表单校验
	$("#queryForm").validate({
		errorClass: "error",
		submitHandler:function(form){
			$(form).ajaxSubmit({
				type:"post",
				url:"saveOrUpdateLawdocdir.json",
				success: function(data){
					alert("保存成功！");
					
					var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
					parent.layer.close(index);
					
					if($("#id").val()==''){
						self.close();
					}else{
						
						parent.location.reload();
					}
				}
			});
		},
		rules:{
			"name": { required: true },
			"orderby":{ required: true,digits:true }
		}
	});
});
</script>