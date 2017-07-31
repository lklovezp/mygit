<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>任务审核</title>
	<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${jquery}/jquery.js"></script>
	<script type="text/javascript" src="${layer}/layer.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
	<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css" />
	<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
    <!-- 任务管理 css-->
    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
     <!-- ztree -->
    <link rel="stylesheet" href="${ztree}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
    <script type="text/javascript" src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${ztree}/js/jquery.ztree.excheck-3.5.js"></script>
    <!-- zTree 修改的样式-->
    <link href="${app}/zTreeReset.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${common}/All.js"></script>
</head>
       <body style='overflow-x:hidden;width: 98%'>
		    <h1 id="checkup_header">任务审核</h1>
		    <iframe name="shPageInfoIframe" scrolling='yes' frameborder='0' width="100%" height="380" src='shPageInfo.htm?applyId=${work.id}' ></iframe>
		
		<div id="bottomDiv">
		<form id="myform" name="myform" method="post" action="sh.json">
		<input type="hidden" id="applyId" name="applyId" value="${work.id}" />
		<input type="hidden" id="taskId" name="taskId" value="${taskId}" />
		 <input type="hidden" id="lx" name="lx" value="${lx}" />
		 <input type="hidden" id="sy" name="sy" value="${sy}" />
		<input type="hidden" id="passed" name="passed" />
				<table class="dataTable" width="100%"  border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
					<tr style="font-size: 14px;">
						<td height="24" align="right">
							审核人：
						</td>
						<td>
							<label id="">${curUser.name}</label>
						</td>
					</tr>
					<tr style="font-size: 14px;">
						<td height="24" align="right">
							审核时间：
						</td>
						<td>
							<label id=""><fmt:formatDate value="${curDate}" pattern="yyyy-MM-dd"/></label>
						</td>
					</tr>
					<tr style="font-size: 14px;">
						<td align="right">
							<span class="red">*</span> 审核意见：
						</td>
						<td>
							<textarea title="限制字数200字" onchange="this.value=this.value.substring(0, 200)" onkeydown="this.value=this.value.substring(0, 200)" onkeyup="this.value=this.value.substring(0, 200)" class="y-textarea easyui-validatebox" data-options="required:true" maxlength="200" style="width:350px; height:60px;" id="opinion" name="opinion"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<div class="">
								<c:forEach var="out" items="${acs}">
									<!-- 上级审核 参考 WorkSh.java-->
									<c:if test="${out.code==0}">
										<input type="button" class="queryBlue" value="${out.text}" onclick="sh(false)" />
									</c:if>
									<!-- 上报上级单位 参考 WorkSh.java-->
									<c:if test="${out.code==3}">
										<input type="button" id="sbsjdwButton" class="queryOrange" value="${out.text}" onclick="createReport()" />
									</c:if>
									<!-- 归档 参考 WorkSh.java-->
									<c:if test="${out.code==1}">
										<input type="button" class="queryBlue" value="${out.text}" onclick="sh(true)" />
									</c:if>
									<!-- 退回 参考 WorkSh.java-->
									<c:if test="${out.code==2}">
										<input type="button" class="queryOrange" value="${out.text}" onclick="th()" /> 
									</c:if>
								</c:forEach>
							</div>
						</td>
					</tr>
					
					
				</table>
		</form>
		</div>
	</body>
</body>
</html>
<script type="text/javascript">
var applyId = $('#applyId').val();

$(document).ready(function() {
    //人员规划列表
	$('#ryghTable').datagrid({
	    url:'ryghTable.json', 
	    queryParams:{applyId:applyId},
	    width:'100%',
		height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns:true,
	    columns:[[  
	        {field:'name',title:'姓名',width:100,align:'center'},   
	        {field:'type',title:'主办/协办',width:100,align:'center'},
	        {field:'lawnumber',title:'执法证号',width:100,align:'center'},
	        {field:'workmobile',title:'电话',width:100,align:'center'},
	        {field:'org',title:'部门',width:100,align:'center'}
	    ]]
	});
	
});


/**上报使用**/
function createReport(){
    //前台校验
    if($("#myform").form('validate')){
        //取子iframe附件选择信息
        var ids = shPageInfoIframe.window.getDatagrid();
        $('#myform').attr('action','create_report.json?ids='+ids);
        $("#sbsjdwButton").attr({"disabled":"disabled"});
		$('#myform').ajaxSubmit(function(data){
	   		if(data.state){
	   		    $("#sbsjdwButton").removeAttr("disabled");//将按钮可用
	   			//alert(data.msg);
	   		     var tishi=layer.alert(data.msg,{
			     	title:'上报',
			        icon:1,
			        shadeClose:true,
			     },  function(){
			    	 layer.close(tishi);
			     });
	   			var lx=$('#lx').val();
		    	var sy=$('#sy').val();
		    	 if($('#sy').val()=='1'){
		    	  	  parent.closeLayerIframe(); 
		    	     }else{
		    	    	 if(lx=="1"){
		    	    		 parent.closeLayer();	
		    	    		}else if(lx=="2"){
		    	    			parent.closeLayer();
		    	    		}else if(lx=="3"){
		    	    			parent.closeLayer();
		    	    		}else if(lx=="4"){
		    	    			parent.closeLayer();
		    	    		}
		    	     }
	   		}
	   	});
    }
}

function sh(passed){
    //前台校验
    if($("#myform").form('validate')){
        $('#passed').val(passed);
		$('#myform').ajaxSubmit(function(data){
	   		if(data.state){
	   			var tishi=layer.alert(data.msg,{
			     	title:'审核',
			        icon:1,
			        shadeClose:true,
			     },
			     function(){
			    	 var lx=$('#lx').val();
			    	var sy=$('#sy').val();
			    	 if($('#sy').val()=='1'){
			    	  	  parent.closeLayerIframe(); 
			    	     }else{
			    	    	 if(lx=="1"){
			    	    		 parent.closeLayer();	
			    	    		}else if(lx=="2"){
			    	    			parent.closeLayer();
			    	    		}else if(lx=="3"){
			    	    			parent.closeLayer();
			    	    		}else if(lx=="4"){
			    	    			parent.closeLayer();
			    	    		}
			    	     }
			        layer.close(tishi);
			     }
			     );
			   // $.messager.alert("审核:", data.msg, "info", function () {
			    	
			    	//window.location.href="dbrwList.htm?title=待办任务";
	          //  });		
	   		}
	   	});
    }
}

function th(){
    //前台校验
    if($("#myform").form('validate')){
        $('#myform').attr('action','th.json');
		$('#myform').ajaxSubmit(function(data){
	   		if(data.state){
	   			var tishi=layer.alert(data.msg,{
			     	title:'退回',
			        icon:1,
			        shadeClose:true,
			     },
			     function(){
			    	 var lx=$('#lx').val();
				    	var sy=$('#sy').val();
				    	 if($('#sy').val()=='1'){
				    	  	  parent.closeLayerIframe(); 
				    	     }else{
				    	    	 if(lx=="1"){
				    	    		 parent.closeLayer();	
				    	    		}else if(lx=="2"){
				    	    			parent.closeLayer();
				    	    		}else if(lx=="3"){
				    	    			parent.closeLayer();
				    	    		}else if(lx=="4"){
				    	    			parent.closeLayer();
				    	    		}
				    	     }
			    	 //window.location.href="javascript:history.go(-1);";
			        layer.close(tishi);
			     }
			     );
			    //$.messager.alert("退回:", data.msg, "info", function () {
			    //	window.location.href="dbrwList.htm?title=待办任务";
	           // });		
	   		}
	   	});
    }
}

</script>