<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>任务分工</title>
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <!--时间插件 my97-->
    <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
    <!-- 任务管理 css-->
    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${common}/All.js"></script>
</head>
    <body>
        <form id="blForm" name="blForm" method="post" action="saveZxrwfg.json">
        <input type="hidden" id="startTime" name="startTime" value="<fmt:formatDate value="${work.startTime}" pattern="yyyy-MM-dd"/>" />
        <input type="hidden" id="endTime" name="endTime" value="<fmt:formatDate value="${work.endTime}" pattern="yyyy-MM-dd"/>" />
        <input type="hidden" id="applyId" name="applyId" value="${work.id}" />
        <!--点击任务分工弹窗-->
			<div id="taskTable"  style="background: #fff; padding-bottom: 10px;">
			    <h1 id="checkup_header">任务分工</h1>
			    <table id="taskTable_con"  style="width: 80%; margin: 0 auto" class="dataTable"  border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
			        <tr>
			            <th width="40%">执法对象</th>
			            <th width="30%">主办人</th>
			            <th width="30%">要求完成时限</th>
			        </tr>
			        <c:forEach var="po" items="${listMap}">
			        <tr>
			        	<input type="hidden" name="zfdxid" value="${po.id}" />
			            <td>${po.lawobjname}</td>
			            <td align="center"><input type="text" class="y-text" name="zbry" id="taskSponsor"></td>
			            <td align="center">
			            <input class="easyui-datebox"  data-options="required:true,editable:false" type="text" name="yqwcsx" />
			            </td>
			        </tr>
			        </c:forEach>
			    </table>
			    <div class="t-c" style="margin-top: 30px;">
			    <input id="savebutton" type="button" class="queryBlue" value="分派任务" onclick="baocun()" style="cursor: pointer">
			    </div>
			</div>
		</form>
		<iframe name="download" id="download" src="" style="display: none"></iframe>
	</body>
</html>
<script type="text/javascript">
var applyId = $('#applyId').val();

$(document).ready(function() {
	//记录人
    $("input[name='zbry']").combobox({
    	height:34,
        required:true,
		url:'ryghCombo.json?applyId='+applyId,
		valueField:'id',
		textField:'name'
	});
	
	$('.easyui-datebox').datebox({
		height:34,
	    onSelect: function(date){
	        var st=$("#startTime").val();
	        var ed=$("#endTime").val();
	        var startTime=new Date(st.replace(/-/g,"/"));//主任务派发时间
		    var endTime=new Date(ed.replace(/-/g,"/"));//主任务要求完成时间
		    if(date<startTime){
	            alert("子任务的要求完成时间不能早于主任务的派发时间！");
	            $(this).datebox("setValue","");
	            return false;
	        }
	        if(date>endTime){
	            alert("子任务的要求完成时间不能晚于主任务的要求完成时间！");
	            $(this).datebox("setValue","");
	            return false;
	        }
	        return true;
	    }
	});
});

//分派任务
function baocun(){
    //前台校验
    if($("#blForm").form('validate')){
         $('#blForm').ajaxSubmit(function(data){
	   		if(data.state){
				alert(data.msg);
				parent.closeLayer();
			}else{
				$.messager.alert('分派任务保存:',data.msg);
			}
	   	 });
    }
}

</script>