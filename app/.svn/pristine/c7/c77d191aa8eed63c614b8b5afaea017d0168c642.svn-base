<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>app</title>
				<!-- jquery -->
		<script src="${jquery}/jquery.js"></script>
		<script src="${jquery}/jquery.form.js"></script>
		
		<link rel="stylesheet" href="${ztree}/ztree/zTreeStyle.css" type="text/css" />
		<script type="text/javascript" src="${ztree}/jquery.ztree.core.js"></script>
		<script type="text/javascript" src="${ztree}/jquery.ztree.excheck.js"></script>
		<!-- easyui -->
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/default/easyui.css">
		<!-- app -->
		<link rel="stylesheet" type="text/css" href="${app}/hnjz.css">
	</head>
	<body>
		
		<form id="queryForm" method="post" action="savewflx.json">
			<input type="hidden" id="disid" name="disid">
			<input type="hidden" id="wflxid" name="wflxid">
			<div id="search" class="divContainer">
				违法类型：<input  type="text" id="wflx" name="wflx"
					value="${wflx}"/>
				<span class="btn btn-ok"> <input type="submit" value="保存">
				</span>
			</div>
			<div class="divContainer">
			<div class="easyui-panel" title="法律依据信息" style="width:500px;">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			</div>
		</form>
	      
	</body>
</html>
<script LANGUAGE="JavaScript">
var setting = {
	check: {
		enable: true,
		chkStyle: "checkbox",
		chkboxType: {'Y': '', 'N': ''}
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
    var mmm = ${menu};
    //$('#wflx').val('40288ac440338d87014033aa4dba00e6');
	var treeObj = $.fn.zTree.init($("#treeDemo"), setting,mmm);
	treeObj.expandAll(true);
	
     $('#wflx').combobox({
		url:'queryWflx.json',
		valueField:'id',
		textField:'name',
		width:150, 
		onSelect:function(){
		  var val = $('#wflx').combobox('getValue');
		  $('#wflxid').val(val);
		  location.href="editWflx.htm?wflx="+val;
		}
	});
	$('#queryForm').submit(function(){
		    var nodes=treeObj.getCheckedNodes(true);
			var ids='';
			$.each(nodes,function(index,ele){
					ids+=ele.id+',';
			});
			$('#disid').val(ids);
			$("#queryForm").ajaxSubmit({
	     	     success: function(data){
		         $.messager.alert('保存结果:',data.msg);
		       }
	       });
           return false;  
    });  
});
</script>