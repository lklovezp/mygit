<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>app</title>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>

		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/icon.css" />
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/default/easyui.css" />
		<script type="text/javascript"
			src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
	<form id="myform"  name="myform" method="post">
		<div>
		<input type="hidden" id="str" name="str" value="${str}" />
		<input type="hidden" id="workId" name="workId" value="${workId}" />
		<input type="hidden" id="applyId" name="applyId" value="${applyId}" />
			<tr>
               <th>选择下一个节点</th>
               <td colspan="3">
               		<select id="node" name="node" onclick="onSelectNodeChange()"> <option value="">请选择...</option></select> 
               </td>
             </tr>
             <tr>
               <th>选择下一节点的办理人员</th>
               <td colspan="3">
               	<select id="people" name="people"> <option value="">请选择...</option></select> 
               </td>
             </tr>
		</div>
		</form>
	</body>
</html>
<script LANGUAGE="JavaScript">
var applyId = document.getElementById("applyId").value;
var str1 = document.getElementById("str").value;
$(document).ready(function(){
	$.ajax({
		type:"post",
		dataType:"json",
		url:"node.json?applyId="+applyId,
		success: function(result){
			if(result.str.success=="true"){
	        	if(result.str.type=='nodes'){
	        		var flowPersonVoList = result.str.data;
	        			if(flowPersonVoList.length>0){
	        				var options = "<option value=''>请选择节点...</option>";
	        				for(var i=0;i<flowPersonVoList.length;i++){
	        					var name = flowPersonVoList[i].name;
	        					options += "<option value=\""+flowPersonVoList[i].id+"\">"+ name +"</option>";
	        				}
	        			}
	        			$('#node').html(options);
	        	}
			}
		}
	});
});

//选择节点
function onSelectNodeChange(){
	var nextNodeId = $(this).val();
	$('#people').html('');
	if(nextNodeId==null || nextNodeId==''){
		return;
	}
	//todo 如果选择的是end节点的情况 《需要测试。》
	if(nextNodeId.indexOf("End") >= 0) {
		//$('#endNode').val(true);
		$('#people').attr('disabled', 'disabled');
		return;
	}	

	$('#people').removeAttr('disabled');
	$.ajax({
        type:"post",
		dataType:"json",
		url:"node.json?applyId="+applyId,
        success: function (result) {
        	if(result.str.success=="true"){
        		if(result.str.type=='submited'){
        			alert(result.str.msg);
        		}
        		else if(result.str.type=="users"){
        			$('#people').html('');
        			var flowPersonVoList = result.str.data;
        			if(flowPersonVoList.length>0){
        				var options = "<option value=''>请选择办理人...</option>";
        				for(var i=0;i<flowPersonVoList.length;i++){
        					var name = flowPersonVoList[i].realnameZh;
        					options += "<option value=\""+flowPersonVoList[i].id+"\">"+ name +"</option>";
        				}
        			}
        			$('#flowPerson').html(options);
		        	$('#workId').val(result.workId);
		        	//$('#endNode').val(result.endNode);
        		}
        		else if(result.type=='nodes'){
        			alert('选择节点[异常情况，需排查]');
        		}
        	}else{
        		alert(result.msg);
        	}
        }
	});
}



function loadUserList(nextNodeId){
	var workid = $("workId").val();
	$.ajax({
		type:"post",
		dataType:"json",
		url:"node.json?applyId="+applyId,
		success: function(result){
			if(result.str.success=="true"){
	        	if(result.str.type=='user'){
	        		var flowPersonVoList = result.str.data;
	        			if(flowPersonVoList.length>0){
	        				var options = "<option value=''>请选择办理人...</option>";
	        				for(var i=0;i<flowPersonVoList.length;i++){
	        					var name = flowPersonVoList[i].realnameZh + "[" + flowPersonVoList[i].employeeCode + "]";
	        					options += "<option value=\""+flowPersonVoList[i].id+"\">"+ name +"</option>";
	        				}
	        			}
	        			$('#people').html(options);
	        	}
			}
		}
	});
}
</script>