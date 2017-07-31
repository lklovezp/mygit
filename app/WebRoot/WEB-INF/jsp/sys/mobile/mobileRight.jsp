<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>app</title>
				<!-- jquery -->
		<script src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<script src="${jquery}/jquery.form.js"></script>
		
		<link rel="stylesheet" href="${ztree}/ztree/zTreeStyle.css" type="text/css" />
		<script type="text/javascript" src="${ztree}/jquery.ztree.core.js"></script>
		<script type="text/javascript" src="${ztree}/jquery.ztree.excheck.js"></script>
		<!-- easyui -->
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<!-- zTree 修改的样式-->
	    <link href="${app}/zTreeReset.css" rel="stylesheet" type="text/css"/>
	    <!-- 任务管理 css-->
	    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
	    <!--系统管理 css-->
	    <link rel="stylesheet" href="${app}/css/management.css" type="text/css">
	    <link rel="stylesheet" href="${app}/css/taskDispatch.css" type="text/css">
		<!-- app -->
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
    	<link href="${app}/common.css" rel="stylesheet" type="text/css"/>
	</head>
	<body>
	<div class="system_authority">
		<h1 id="checkup_header">${name}终端权限设置</h1>
		<div id="search" class="y-author" >
			<form id="queryForm" method="post" action="savemobileR.json">
				<input type="hidden" name="mobileId" id="mobileId">
				<input type="hidden"  id="role" name="role"	value="${role}"/>
       			<input type="submit" class="bTn directory_save directory_comwidth" value="保 存" style="margin-left: 20px;"/>
			</form>
		</div>
		<div class="autor_border">
			<div style="padding: 20px;">
            <ul id="treeDemo" class="ztree">
            </ul>
        	</div>
		</div>
	</div>
		
	</body>
</html>
<script LANGUAGE="JavaScript">
var setting = {
	check: {
		enable: true,
		chkStyle: "checkbox",
		chkboxType: {'Y': 'ps', 'N': 'ps'}
	},
	data: {
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "pid"
		}
	}
};
$(document).ready(function(){
	$.ajaxSetup({cache:false});
    var mmm = ${menu};
	var treeObj = $.fn.zTree.init($("#treeDemo"), setting,mmm);
	treeObj.expandAll(true);
     /*$('#role').combobox({
    	height:34,
		url:'queryAllRole.json',
		valueField:'id',
		textField:'name',
		onSelect:function(){
		  var val = $('#role').combobox('getValue');
		  location.href="mobileRight.htm?title=${title}&role="+val;
		}
	});*/
	$('#queryForm').submit(function(){
		    var nodes=treeObj.getCheckedNodes(true);
			var ids='';
			$.each(nodes,function(index,ele){
					ids+=ele.id+',';
			});
			$('#mobileId').val(ids);
			$("#queryForm").ajaxSubmit({
	     	     success: function(data){
	     	    	var tishi=layer.alert(data.msg,{
				     	title:'保存结果',
				        icon:1,
				        shadeClose:true,
				     },
				     function(){
				    	 parent.closeLayer();
					     layer.close(tishi);
				     }
				     );
		       }
	       });
           return false;  
    });  
});
</script>