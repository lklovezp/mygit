<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>行政处罚决定书</title>
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
<div class="headTitle">行政处罚决定书</div>
<form id="myform" name="myform" method="post" action="saveCfjds.json">
<input type="hidden" id="applyId" name="applyId" value="${work.id}" />
<input type="hidden" id="wflx" name="wflx" value="${wflx}" />
<input type="hidden" id="id" name="id" value="${cfjdsForm.id}" />
	<div class="divContainer">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formtable">
        <tr>
			<td colspan="7" align="right">
				<input style="width:80px;" class="i-text" type="text" id="bmmc" name="bmmc" value="${cfjdsForm.bmmc}" />
				环罚告字 [<input style="width:50px;" class="i-text" type="text" id="wsmc" name="wsmc" value="${cfjdsForm.wsmc}" />]
				<input style="width:50px;" class="i-text" type="text" id="bh" name="bh" value="${cfjdsForm.bh}" />
			</td>
		</tr>
        <tr>
			<td colspan="7">
				<input style="width:400px;" class="i-text" type="text" id="dsr" name="dsr" value="${cfjdsForm.dsr}" />：
			</td>
		</tr>
		<tr>
			<th>
				营业执照注册号（公民身份证号码）：
			</th>
			<td colspan="3">
				<input class="i-text" style="width:160px;" type="text" id="zcbh" name="zcbh" value="${cfjdsForm.zcbh}" />
			</td>
			<th>
				组织机构代码证：
			</th>
			<td colspan="4">
				<input class="i-text" style="width:160px;" type="text" id="zcjgbm" name="zcjgbm" value="${cfjdsForm.zcjgbm}" />
			</td>
		</tr>
		<tr>
			<th>
				地址：
			</th>
			<td colspan="3">
				<input class="i-text" style="width:160px;" type="text" id="zcdz" name="zcdz" value="${cfjdsForm.zcdz}" />
			</td>
			<th>
				法定代表人（负责人）：
			</th>
			<td colspan="4">
				<input class="i-text" style="width:160px;" type="text" id="fddbr" name="fddbr" value="${cfjdsForm.fddbr}" />
			</td>
		</tr>
		</tr>
		<tr>
			<th>
				我厅（局）于
			</th>
			<td colspan="7">
				<input class="easyui-datetimebox" style="width:160px;" type="text" id="jcsj" name="jcsj" value="${cfjdsForm.jcsj}" />
			</td>
		</tr>
		<tr>
			<th>
				对你（单位）进行了调查，发现你（单位）实施了以下环境违法行为
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="wfxw" name="wfxw"/>${cfjdsForm.wfxw}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				以上事实有
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="wfzj" name="wfzj"/>${cfjdsForm.wfzj}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				等证据为凭。
				你（单位）的上述行为违反了
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="rules" name="rules"/>${cfjdsForm.rules}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				我厅（局）于
			</th>
			<td colspan="7">
				<input class="easyui-datetimebox" style="width:160px;" type="text" id="gzsj" name="gzsj" value="${cfjdsForm.gzsj}" />
			</td>
		</tr>
		<tr>
			<th>
				以
			</th>
			<td colspan="7">
				<input class="i-text" style="width:160px;" type="text" id="gzsmc" name="gzsmc" value="${cfjdsForm.gzsmc}" />
			</td>
		</tr>
		<tr>
			<th>
				告知你（单位）陈述申辩权（听证申请权）。
			</th>
			<td colspan="7">
				<input class="easyui-datetimebox" style="width:160px;" type="text" id="dsrsbsj" name="dsrsbsj" value="${cfjdsForm.dsrsbsj}" />
			</td>
		</tr>
		<tr>
			<th>
				，
			</th>
			<td colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="dsrsbnr" name="dsrsbnr"/>${cfjdsForm.dsrsbnr}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				依据
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="measure" name="measure"/>${cfjdsForm.measure}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				的规定，
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="xzcfzd" name="xzcfzd"/>${cfjdsForm.xzcfzd}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				。我厅（局）拟对你（单位）作出如下行政处罚：1.
			</th>
			<td align="left" colspan="7">
				<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="xzcf" name="xzcf"/>${cfjdsForm.xzcf}</textarea>
			</td>
		</tr>
		<tr>
			<th>
				2.罚款（大写）
			</th>
			<td align="left" colspan="7">
				<input class="i-text" type="text" id="fksm" name="fksm" value="${cfjdsForm.fksm}" />元
			</td>
		</tr>
		<tr>
			<td align="left" colspan="7">
				限于接到本处罚决定之日起十五日内缴至指定银行和账号。逾期不缴纳罚款的，我厅（局）可以根据《中华人民共和国行政处罚法》第五十一条第一项规定
				每日按罚款数额的3%加处罚款。
			</td>
		</tr>
		<tr>
			<th>
				收款银行：
			</th>
			<td align="left" colspan="4">
				<input class="i-text" style="width:350px;" type="text" id="skyh" name="skyh" value="${cfjdsForm.skyh}" />
			</td>
			<th>
				户 名：
			</th>
			<td align="left" colspan="3">
				<input class="i-text" type="text" id="yhhm" name="yhhm" value="${cfjdsForm.yhhm}" />
			</td>
		</tr>
		<tr>
			<th>
				账 号：
			</th>
			<td align="left" colspan="7">
				<input class="i-text" style="width:450px;" type="text" id="yhzh" name="yhzh" value="${cfjdsForm.yhzh}" />
			</td>
		</tr>
		<tr>
			<td align="left" colspan="7">
				你（单位）如不服本处罚决定，可在收到本处罚决定书之日起60日内向
				<input class="i-text" type="text" id="zfmc" name="zfmc" value="${cfjdsForm.zfmc}" />人民政府或者
				<input class="i-text" type="text" id="hbtmc" name="hbtmc" value="${cfjdsForm.hbtmc}" />环境保护厅（局）申请
				行政复议，也可以在6个月内向
				<input class="i-text" type="text" id="fymc" name="fymc" value="${cfjdsForm.fymc}" />人民法院提起行政诉讼。申请行政复议或者提起行政诉讼，不停止行政处罚决定的
				执行。
				逾期不申请行政复议，不提起行政诉讼，又不履行本处罚决定的，我厅（局）奖依法申请人民法院强制执行。
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
	//格式化时间
	$("#gzsj").datetimebox({
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
	
	//格式化时间
	$("#dsrsbsj").datetimebox({
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
	//格式化时间
	$("#dsrsbsj").datetimebox({
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
			    $.messager.alert("行政处罚决定书制作:", data.msg, "info", function () {
                     window.close();
                });
			}
		}
   });
}
</script>