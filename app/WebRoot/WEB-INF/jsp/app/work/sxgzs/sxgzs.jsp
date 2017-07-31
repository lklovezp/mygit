<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>行政处罚事先告知书</title>
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
<div class="headTitle">行政处罚事先告知书</div>
<form id="myform" name="myform" method="post" action="saveSxgzs.json">
<input type="hidden" id="applyId" name="applyId" value="${work.id}" />
<input type="hidden" id="id" name="id" value="${sxgzsForm.id}" />
	<div class="divContainer">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formtable">
        <tr>
			<td colspan="7" align="right">
				<input style="width:80px;" class="i-text" type="text" id="bmmc" name="bmmc" value="${sxgzsForm.bmmc}" />
				环罚告字 [<input style="width:50px;" class="i-text" type="text" id="wsmc" name="wsmc" value="${sxgzsForm.wsmc}" />]
				<input style="width:50px;" class="i-text" type="text" id="bh" name="bh" value="${sxgzsForm.bh}" />
			</td>
		</tr>
        <tr>
			<td colspan="7">
				<input style="width:400px;" class="i-text" type="text" id="cw" name="cw" value="${sxgzsForm.cw}" />：
			</td>
		</tr>
		<tr>
			<th>
				我厅（局）于
			</th>
			<td colspan="7">
				<input class="easyui-datetimebox" style="width:160px;" type="text" id="jcsj" name="jcsj" value="${sxgzsForm.jcsj}" />
			</td>
		</tr>
		<tr>
			<th>
				对你（单位）进行了调查，发现你（单位）实施了以下环境违法行为
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="jlnr" name="jlnr"/>${sxgzsForm.jlnr}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				以上事实有
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="wfajzj" name="wfajzj"/>${sxgzsForm.wfajzj}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				等证据为凭。
				你（单位）的上述行为违反了
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="rules" name="rules"/>${sxgzsForm.rules}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				的规定。依据
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="measure" name="measure"/>${sxgzsForm.measure}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				的规定，
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="xzcfzd" name="xzcfzd"/>${sxgzsForm.xzcfzd}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				。我厅（局）拟对你（单位）作出如下行政处罚：
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="xzcf" name="xzcf"/>${sxgzsForm.xzcf}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				联系人：
			</th>
			<td align="left" colspan="4">
				<input class="i-text" type="text" id="lxr" name="lxr" value="${sxgzsForm.lxr}" />
			</td>
			<th>
				电 话：
			</th>
			<td align="left" colspan="3">
				<input class="i-text" type="text" id="lxrdh" name="lxrdh" value="${sxgzsForm.lxrdh}" />
			</td>
		</tr>
		<tr>
			<th>
				地 址：
			</th>
			<td align="left" colspan="4">
				<input class="i-text" style="width:350px;" type="text" id="lxrdz" name="lxrdz" value="${sxgzsForm.lxrdz}" />
			</td>
			<th style="width:80px;">
				邮政编码：
			</th>
			<td align="left" colspan="3">
				<input class="i-text" type="text" id="postCode" name="postCode" value="${sxgzsForm.postCode}" maxlength="6"/>
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
	$("#jcsj").datetimebox({
	    showSeconds:false,
	    formatter:formatDate,
	    onSelect:function(date){
	        var y = date.getFullYear();
		    var m = date.getMonth() + 1;
		    var d = date.getDate();
		    var time=$('#starttime').datetimebox('spinner').spinner('getValue');
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
			    $.messager.alert("行政处罚事先告知书制作:", data.msg, "info", function () {
                     window.close();
                });
			}
		}
   });
}
</script>