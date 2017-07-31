<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>行政处罚案件结案审批表</title>
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
<div class="headTitle">行政处罚案件结案审批表</div>
<form id="myform" name="myform" method="post" action="saveJaspb.json">
<input type="hidden" id="applyId" name="applyId" value="${work.id}" />
<input type="hidden" id="wflx" name="wflx" value="${wflx}" />
<input type="hidden" id="id" name="id" value="${jaspbForm.id}" />
	<div class="divContainer">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formtable">
		<tr>
			<th>
				案由
			</th>
			<td colspan="3">
				<input class="i-text" style="width:160px;" type="text" id="ajnr" name="ajnr" value="${jaspbForm.ajnr}" />
			</td>
			<th>
				案件来源
			</th>
			<td colspan="4">
				<input class="i-text" style="width:160px;" type="text" id="ajly" name="ajly" value="${jaspbForm.ajly}" />
			</td>
		</tr>
		<tr>
			<th>
				当事人名称/姓名
			</th>
			<td colspan="3">
				<input class="i-text" style="width:160px;" type="text" id="dsrmc" name="dsrmc" value="${jaspbForm.dsrmc}" />
			</td>
			<th>
				法定代表人（负责人）：
			</th>
			<td colspan="4">
				<input class="i-text" style="width:160px;" type="text" id="fddbr" name="fddbr" value="${jaspbForm.fddbr}" />
			</td>
		</tr>
		<tr>
			<th>
				工作单位
			</th>
			<td colspan="3">
				<input class="i-text" style="width:160px;" type="text" id="company" name="company" value="${jaspbForm.company}" />
			</td>
			<th>
				职务或职业
			</th>
			<td colspan="4">
				<input class="i-text" style="width:160px;" type="text" id="dsrzw" name="dsrzw" value="${jaspbForm.dsrzw}" />
			</td>
		</tr>
		<tr>
			<th>
				地址或住址
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="dsrdz" name="dsrdz"/>${jaspbForm.dsrdz}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				立案时间
			</th>
			<td colspan="3">
				<input class="easyui-datetimebox" style="width:160px;" type="text" id="lasj" name="lasj" value="${jaspbForm.lasj}" />
			</td>
			<th>
				案件审查人及执法证编号
			</th>
			<td colspan="4">
				<input class="i-text" style="width:160px;" type="text" id="ajscr" name="ajscr" value="${jaspbForm.ajscr}" />
			</td>
		</tr>
		<tr>
			<th>
				行政处罚决定书文号
			</th>
			<td colspan="7">
				<input class="i-text" style="width:160px;" type="text" id="xzcfjdswh" name="xzcfjdswh" value="${jaspbForm.xzcfjdswh}" />
			</td>
		</tr>
		<tr>
			<th>
				简要案情及查处经过
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="jyaq" name="jyaq"/>${jaspbForm.jyaq}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				处理依据及结果
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="clyj" name="clyj"/>${jaspbForm.clyj}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				行政复议行政诉讼情况
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="xzssqk" name="xzssqk"/>${jaspbForm.xzssqk}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				处罚执行情况及罚没财务的处置情况
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="cfzxqk" name="cfzxqk"/>${jaspbForm.cfzxqk}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				备注
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="remark" name="remark"/>${jaspbForm.remark}</textarea>
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
$(document).ready(function() {
    //格式化时间
	$("#lasj").datetimebox({
	    showSeconds:false,
	    formatter:formatDate,
	    onSelect:function(date){
	        var y = date.getFullYear();
		    var m = date.getMonth() + 1;
		    var d = date.getDate();
		    var starttime = y + "-" + (m < 10 ? ("0" + m) : m) + "-" + (d < 10 ? ("0" + d) : d);
			return starttime;
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
       	//返回格式化后的时间
        return date.getFullYear()+'-'+month+'-'+day;
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
			    $.messager.alert("行政处罚案件结案审批表制作:", data.msg, "info", function () {
                     window.close();
                });
			}
		}
   });
}
</script>