<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>案件集体讨论笔录</title>
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
<style type="text/css">

.ta-da{
    overflow:scroll; 
    overflow-x:hidden; 
    height:80px;
    width:95%;
}
</style>
</head>
<body style="width: 90%;margin: 0 auto">
<div class="headTitle">案件集体讨论笔录</div>
<form id="myform" name="myform" method="post" action="saveAjtlbl.json">
<input type="hidden" id="applyId" name="applyId" value="${work.id}" />
<input type="hidden" id="id" name="id" value="${jttlblForm.id}" />
	<div class="divContainer">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formtable">
        <tr>
			<th>
				案件名称：
			</th>
			<td colspan="7">
				<input style="width: 400px;" class="i-text" type="text" id="ajmc" name="ajmc" value="${jttlblForm.ajmc}" />
			</td>
		</tr>
		<tr>
			<th>
				时 间：
			</th>
			<td colspan="7">
				<input class="easyui-datetimebox" type="text" id="tlstartsj" name="tlstartsj" value="${jttlblForm.tlstartsj}" /> 至 
				<input class="easyui-datetimebox" type="text" id="tlendsj" name="tlendsj" value="${jttlblForm.tlendsj}" />
			</td>
		</tr>
		<tr>
			<th>
				地 点：
			</th>
			<td colspan="7">
				<input style="width: 400px;" class="i-text" type="text" id="tldd" name="tldd" value="${jttlblForm.tldd}" />
			</td>
		</tr>
		<tr>
			<th>
				主持人：
			</th>
			<td>
			    <input class="i-text" type="text" id="zcr" name="zcr" value="${jttlblForm.zcr}"/>
			</td>
			<th>
				职务：
			</th>
			<td>
			    <input class="i-text" type="text" id="zcrzw" name="zcrzw" value="${jttlblForm.zcrzw}"/>
			</td>
			<th>
				记录人：
			</th>
			<td>
			    <input class="i-text" type="text" id="jlr" name="jlr" value="${jttlblForm.jlr}"/>
			</td>
			<th>
				职务：
			</th>
			<td>
			    <input class="i-text" type="text" id="jlrzw" name="jlrzw" value="${jttlblForm.jlrzw}"/>
			</td>
		</tr>
		<tr>
			<th>
				参加人员：
			</th>
			<td colspan="7">
				<input class="i-text" type="text" id="cjrys" name="cjrys" value="${jttlblForm.cjrys}" />
			</td>
		</tr>
		<tr>
			<th>
				人员：
			</th>
			<td colspan="7">
				<input class="i-text" type="text" id="ry" name="ry" value="${jttlblForm.ry}" />
			</td>
		</tr>
		<tr>
			<th>
				承办人汇报案件情况：
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="ajqk" name="ajqk"/>${jttlblForm.ajqk}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				陈述（听证）情况：
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="csqk" name="csqk"/>${jttlblForm.csqk}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				参加讨论人员意见：
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="tlyj" name="tlyj"/>${jttlblForm.tlyj}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				结论意见：
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="tljl" name="tljl"/>${jttlblForm.tljl}</textarea>
			</td>
		</tr>
	</table>	
	</div>
	<div class="bottomBtn">
		<span class="btn btn-ok"><input id="but_save" type="button" value="保存" onclick="save()"></span>&nbsp;
	</div>
</form>

</body>
</html>
<script type="text/javascript">
var applyId = $('#applyId').val();
function compareDate(d1, d2) {  // 时间比较的方法，如果d1时间比d2时间大，则返回true   
    return Date.parse(d1.replace(/-/g, "/")) > Date.parse(d2.replace(/-/g, "/"))   
}
$(document).ready(function() {
    //格式化时间
	$("#tlstartsj").datetimebox({
	    showSeconds:false,
	    formatter:formatDate,
	    onSelect: function(date){
	        var ed=$('#tlendsj').datetimebox('getValue');//结束时间
	        if(ed!=''){
	            var endTime=new Date(ed.replace(/-/g,"/"));
			    if(date>endTime){
		            alert("开始时间不能晚于结束时间！");
		            $(this).datetimebox("setValue","");
		            return false;
		        }
	        }
	        return true;
	    },
    	onChange: function(date){
    		var str=$('#tlstartsj').datetimebox('getValue');//开始时间
    		var ed= $('#tlendsj').datetimebox('getValue');//结束时间
        	if(ed!=''){
            	var endTime=new Date(ed.replace(/-/g,"/"));
            	var startTime=new Date(str.replace(/-/g,"/"));
		    	if(startTime>endTime){
	            	alert("开始时间不能晚于结束时间，请重新选择！");
	        	}
        	}
        	return false;
    	}
	});
	$("#tlendsj").datetimebox({
	    showSeconds:false,
	    formatter:formatDate,
	    onSelect: function(date){
	        var st=$('#tlstartsj').datetimebox('getValue');//开始时间
	        if(st!=''){
	            var startTime=new Date(st.replace(/-/g,"/"));
			    if(date<startTime){
		            alert("结束时间不能早于开始时间！");
		            $(this).datetimebox("setValue","");
		            return false;
		        }
	        }
	        return true;
		},
    	onChange: function(date){
    		var str=$('#tlstartsj').datetimebox('getValue');//开始时间
    		var ed= $('#tlendsj').datetimebox('getValue');//结束时间
        	if(ed!=''){
            	var endTime=new Date(ed.replace(/-/g,"/"));
            	var startTime=new Date(str.replace(/-/g,"/"));
		    	if(startTime>endTime){
	            	alert("结束时间不能早于开始时间，请重新选择！");
	        	}
        	}
        	return false;
    	}
	});
});

//日期格式化
function formatDate(date){
    var month = date.getMonth()+1;
    if( "" != date ){
        if( date.getMonth() +1 < 10 ){
            month = '0' + (date.getMonth() +1);
        }
        var day = date.getDate();
        if( date.getDate() < 10 ){
            day = '0' + date.getDate();
        }
        
        var hour = date.getHours();
        if( date.getHours() < 10 ){
            hour = '0' + date.getHours();
        }
        
        var minute = date.getMinutes();
        if( date.getMinutes() < 10 ){
            minute = '0' + date.getMinutes();
        }
 
       //返回格式化后的时间
        return date.getFullYear()+'-'+month+'-'+day+" "+hour+":"+minute;
    }else{
        return "";
    }
}

function save() {
   $("#but_save").attr({disabled:"true",value:"保存中"});//设置按钮属性
   $('#myform').ajaxSubmit({
	    success : function(data) {
			$("#but_save").removeAttr("disabled");//将按钮可用
			if (data.state) {
			    $.messager.alert("案件集体讨论笔录制作:", data.msg, "info", function () {
                     window.close();
                });
			}
		}
   });
}
</script>