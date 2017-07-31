<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>新建卷宗</title>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css" />
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
		<script type="text/javascript" src="${common}/All.js"></script>
	</head>
	<body style='overflow-x:hidden;width: 98%'>
	        <c:if test="${isCp==0}">
	        <div class="headTitle" id="divTitle">新建卷宗</div>
		    </c:if>
		    <c:if test="${isCp==1}">
		    <div class="headTitle" id="divTitle">新建卷宗</div>
		    </c:if>
		    <form id="myform" name="myform" method="post" action="jz.json">
		    <input type="hidden" id="applyId" name="applyId" value="${work.id}" />
		    <input type="hidden" id="id" name="id" value="${work.id}" />
		    <input type="hidden" id="jsrId" name="jsrId" value="${work.jsr}"/>
			<div class="divContainer" style="padding:10px;">
			<!-- 任务信息 -->
			<div class="easyui-panel" title="卷宗信息" style="margin-bottom:10px;">
				<table width="100%" height="" border="0" align="left" cellpadding="0"
					cellspacing="0" class="formtable">
					<tr>
						<th width="100" align="right">
							<label class="requiredLabel">*</label>卷宗名称：
						</th>
						<td align="left" colspan="3">
							<input style="width:350px;" class="i-text easyui-validatebox" id="workName" name="workName" type="text" value="${work.name}" />
						</td>
					</tr>
					<tr>
						<th align="right">
							<label class="requiredLabel">*</label>卷宗时间：
						</th>
						<td>
							<input class="easyui-datebox" data-options="editable:false" type="text" id="endTime" name="endTime" value="<fmt:formatDate value="${work.endTime}" pattern="yyyy-MM-dd"/>" />
						</td>
					</tr>
				</table>
			</div>
		<table style="width : 100%;">
			<tr>
			    <c:if test="${isCp==0}">
			    <td align="center" height="50">
					<span class="btn btn-ok"> <input id="savebutton" type="button" onclick="sc()"
								value="保存"> </span>&nbsp;
					<span class="btn btn-ok"> <input id="pfbutton" type="submit"
							value="提交"> </span>&nbsp;
					<a href="#" id="J-from-reset" class="btslink">重置</a>&nbsp;
					
				</td>
			    </c:if>
			    <c:if test="${isCp==1}">
			    <td align="center" height="50">
				    <span class="btn btn-ok"> <input id="cpbutton" type="button" onclick="cp()"
									value="重派"> </span>&nbsp;
				</td>
			    </c:if>
				
			</tr>
		</table>
		</form>
		<iframe name="download" id="download" src="" style="display: none"></iframe>
	</body>
</html>
<script type="text/javascript">
function myformatter(date){  
    var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();  
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
}  
          
function myparser(s){  
    if (!s) return new Date();  
    var ss = (s.split('-'));  
    var y = parseInt(ss[0],10);  
    var m = parseInt(ss[1],10);  
    var d = parseInt(ss[2],10);  
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){  
        return new Date(y,m-1,d);  
    } else {  
        return new Date();  
    }  
} 

$(document).ready(function(){
    //要求完成时限选择校验
    $("#endTime").datebox({
	    onSelect: function(date){
	        var curTime=new Date();//当前时间
	        var st=curTime.getFullYear()+'-'+(curTime.getMonth()+1)+'-'+curTime.getDate()
	        var curDate=new Date(st.replace(/-/g,"/"));
		    if(date<curDate){
	            alert("要求完成时限不能早于当前时间！");
	            $(this).datebox("setValue","");
	            return false;
	        }
	        return true;
	    }
	});
	
    $(".inputWithImge").each(function(){
        $(this).add($(this).next()).wrapAll('<div class="imageInputWrapper"></div>');
    }); 
    
	$("#myform").validate( {
		errorClass : "error",
		submitHandler : function(form) {
			if ($("#myform").form("validate")){
			    $("#pfbutton").attr({"disabled":"disabled"});
				$(form).ajaxSubmit( {
					type : "post",
					url : "pf.json",
					success : function(data) {
					    $("#pfbutton").removeAttr("disabled");//将按钮可用
					    if (data.state) {
						    $.messager.alert("派发:", data.msg, "info", function () {
		                         window.close();
		                    });
						} else {
							alert(data.msg);
						}
					}
				});
			}
		}
	});

});

// 重置
$("#J-from-reset").click(function(){
	$("#myform").form("reset");
	$("#createTime").val('${work.createTime}');
	$("#endTime").val('${work.endTime}');
});

//任务生成
function sc(){
    //任务名称校验
    if($("#workName").validatebox("isValid")){
        $('#myform').attr('action','sc.json');
		$('#myform').ajaxSubmit(function(data){
	   		if(data.state){
			    $.messager.alert("生成:", data.msg, "info", function () {
	                 window.close();
	            });		
	   		} else {
		   		alert(data.msg);
	   		}
	   	});
    }else{
        $("#workName").focus();
    }
}

//任务重派
function cp(){
    //任务名称校验
    if($("#workName").validatebox("isValid")){
        $('#myform').attr('action','cp.json');
		$('#myform').ajaxSubmit(function(data){
	   		if(data.state){
			    $.messager.alert("重派:", data.msg, "info", function () {
	                 window.close();
	            });		
	   		} else {
				alert(data.msg);
	   		}
	   	});
    }else{
        $("#workName").focus();
    }
}

//打包下载
function downZipFile(){
    var data = $('#XGFJdata').datagrid("getData");
	var ids = "";
	for(var i = 0; i < data.rows.length; i++){
		if (i > 0){
			ids += ",";
		}
		ids += data.rows[i].id;
	}
	if(ids!=null && ids!=''){
	    $('#download').attr('src','downZipFile?id='+ids);
	}else{
	    alert("没有附件！");
	}
}

/////附件操作/////
/**
 * 公用的上传文件之后的刷新grid方法
 * @param tableId tablegrid的id
 */
function reload(tableId,pid,fileType){
    var id = "#" + tableId;
	$(id).datagrid("reload",{pid:pid,fileType:fileType,tableId:tableId});
	jQuery.colorbox.close();
}
/**
 * 公用的删除文件方法 删除grid中的文件
 * @param obj grid的一行数据
 */
function deletefile1(obj){
	$.messager.confirm('操作', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
				url: "removeFile.json?id="+obj.id,
				success:function(data){
					alert(data.msg);
					var reloadtable= $(obj).attr("reloadtable");//自定义属性采用此方式获得 
					$('#'+reloadtable).datagrid('reload');
				}
			});
		}
	});
}
</script>