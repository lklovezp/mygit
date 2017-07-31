<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>询问笔录</title>
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
.formtable {
    border: 1px solid #d4d4d4;
}
.formtable th {
    color: #666666;
    background-color: #ffffff;
    border: 1px solid #d4d4d4;
}
.formtable td {
	color: #666666;
	border: 1px solid #d4d4d4;
}
.i-text {
    border-color: #d4d4d4 #d4d4d4 #d4d4d4 #d4d4d4;
    border-style: solid;
    border-width: 1px;
}
.i-text:hover {
    border-color: #d4d4d4 #d4d4d4 #d4d4d4 #d4d4d4;
    border-style: solid;
    border-width: 1px;
}
.combo {
    border-color: #d4d4d4;
    background-color: #ffffff;
}
</style>
</head>
<body style="width: 90%;margin: 0 auto;padding-bottom: 70px;">
<div class="headTitle">询问笔录</div>
<form id="myform" name="myform" method="post" action="saveXwbl.json">
<input type="hidden" id="applyId" name="applyId" value="${work.id}" />
<input type="hidden" id="wflx" name="wflx" value="${wflx}" />
<input type="hidden" id="id" name="id" value="${xwblForm.id}" />
	<div class="divContainer">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formtable">
		<tr>
			<th>
				询问时间：
			</th>
			<td colspan="7">
				<input class="easyui-datetimebox" type="text" id="starttime" name="starttime" value="${xwblForm.starttime}" /> 至 
				<input class="easyui-datetimebox" type="text" id="endtime" name="endtime" value="${xwblForm.endtime}" />
			</td>
		</tr>
		<tr>
			<th>
				询问地点：
			</th>
			<td colspan="7">
				<input style="width: 400px;" class="i-text" type="text" id="xwdd" name="xwdd" value="${xwblForm.xwdd}" />
			</td>
		</tr>
		<tr>
			<th>
				被询问单位名称：
			</th>
			<td colspan="7">
				<input style="width: 400px;" class="i-text" type="text" id="bxwdwmc" name="bxwdwmc" value="${xwblForm.bxwdwmc}" />
			</td>
		</tr>
		<tr>
			<th>
				${title}
			</th>
			<td>
				<input class="i-text" type="text" id="fddbr" name="fddbr" value="${xwblForm.fddbr}" />
			</td>
			<th>
				电话：
			</th>
			<td>
				<input class="i-text" type="text" id="fddbrdh" name="fddbrdh" value="${xwblForm.fddbrdh}" />
			</td>
			<th>
				身份证号：
			</th>
			<td colspan="3">
				<input style="width: 343px;" class="i-text" type="text" id="fddbrsfzh" name="fddbrsfzh" value="${xwblForm.fddbrsfzh}" />
			</td>
		</tr>
		<tr>
			<th>
				被询问人姓名：
			</th>
			<td>
				<input class="i-text" type="text" id="bxwrxm" name="bxwrxm" value="${xwblForm.bxwrxm}" />
			</td>
			<th>
				性别：
			</th>
			<td>
				<input class="i-text" type="text" id="bxwrxb" name="bxwrxb" value="${xwblForm.bxwrxb}" />
			</td>
			<th>
				年龄：
			</th>
			<td>
				<input class="i-text" type="text" id="bxwrnl" name="bxwrnl" value="${xwblForm.bxwrnl}" />
			</td>
			<th>
				职务：
			</th>
			<td>
				<input class="i-text" type="text" id="bxwrzw" name="bxwrzw" value="${xwblForm.bxwrzw}" />
			</td>
		</tr>
		<tr>
			<th>
				电话：
			</th>
			<td>
				<input class="i-text" type="text" id="bxwrdh" name="bxwrdh" value="${xwblForm.bxwrdh}" />
			</td>
			<th>
				身份证号：
			</th>
			<td colspan="5">
				<input style="width: 351px;" class="i-text" type="text" id="bxwrsfzh" name="bxwrsfzh" value="${xwblForm.bxwrsfzh}" />
			</td>
		</tr>
		<tr>
			<th>
				被询问自然人姓名：
			</th>
			<td>
				<input class="i-text" type="text" id="bxwzrrxm" name="bxwzrrxm" value="${xwblForm.bxwzrrxm}" />
			</td>
			<th>
				性别：
			</th>
			<td>
				<input class="i-text" type="text" id="bxwzrrxb" name="bxwzrrxb" value="${xwblForm.bxwzrrxb}" />
			</td>
			<th>
				年龄：
			</th>
			<td colspan="3">
				<input class="i-text" type="text" id="bxwzrrnl" name="bxwzrrnl" value="${xwblForm.bxwzrrnl}" />
			</td>
		</tr>
		<tr>
			<th>
				电话：
			</th>
			<td>
				<input class="i-text" type="text" id="bxwzrrdh" name="bxwzrrdh" value="${xwblForm.bxwzrrdh}" />
			</td>
			<th>
				身份证号：
			</th>
			<td colspan="5">
				<input style="width: 351px;" class="i-text" type="text" id="bxwzrrsfzh" name="bxwzrrsfzh" value="${xwblForm.bxwzrrsfzh}" />
			</td>
		</tr>
		<tr>
			<th>
				所在单位：
			</th>
			<td colspan="7">
				<input style="width: 400px;" class="i-text" type="text" id="bxwzrrszdw" name="bxwzrrszdw" value="${xwblForm.bxwzrrszdw}" />
			</td>
		</tr>
		<tr>
			<th>
				住址：
			</th>
			<td colspan="7">
				<input style="width: 400px;" class="i-text" type="text" id="bxwzrrzz" name="bxwzrrzz" value="${xwblForm.bxwzrrzz}" />
			</td>
		</tr>
		<tr>
			<th>
				与本案关系：
			</th>
			<td colspan="7">
				<input style="width: 400px;" class="i-text" type="text" id="bxwzrrybagx" name="bxwzrrybagx" value="${xwblForm.bxwzrrybagx}" />
			</td>
		</tr>
		<tr>
			<td colspan="8">
				我们是<input class="i-text" type="text" id="zfrydwmc" name="zfrydwmc" value="${xwblForm.zfrydwmc}" />的执法人员：<input class="i-text" type="text" id="zfrynames" name="zfrynames" value="${xwblForm.zfrynames}" />，这是我们的执法证件，号码是：<input class="i-text" type="text" id="zfzhs" name="zfzhs" value="${xwblForm.zfzhs}" />请确认（出示证件）。
			</td>
		</tr>
		
		<tr>
			<td colspan="8">
			    <div id="qa">
					<c:forEach var="array" items="${kcxwblwtlist}" varStatus="indexstuts">
						<ul id="ul_${array.id}">
							<li style="margin-top : 8px;">
								<input id='isadd_${array.id}'  name="isdel" type="hidden" value="${array.isdel}" />
								<input type="hidden" name="wttype" value="${array.wttype}"/>
								<input type="hidden" name="ids" value="${array.id}"/>
								<input type="hidden" name="content" id='content_${array.id}' value="${array.content}"/>
								问：<label id='qus_${array.id}'>${array.content}</label>
								<c:if test="${array.orderby!=1}">
									<a id='edt_${array.id}_' class='btslink' onclick='edit("${array.id}")' />修改</a>
								</c:if>
								<a id='add_${array.id}' class='btslink' onclick='add("${array.id}")' />追加</a>
								<c:if test="${array.orderby!=1}">
								<c:if test="${array.isdel eq 'Y'}" >
									<a id='del_${array.id}' class='btslink' onclick='del("${array.id}")' />删除</a>
								</c:if>
								</c:if>
							</li>
							<li>
								答：<textarea id='ans_${array.id}' onKeyUp="return textMaxLen(this);" onBlur="return textMaxLen(this);" maxlength="300" title="可填300字" name="danr" type='text' class='i-text ta-da' >${array.answer}</textarea>
							</li>
						</ul>
					</c:forEach>
				</div>
			    
			    <div>
			    <ul>
			       <li>
			            问：<label>以上这些是我们对你的询问笔录，请查看一下内容是否一致，如无异议，请签名并注明对笔录内容真实性的意见</label>
			       </li>
			       <li>
			            答：<textarea id="lastans" name="lastans" onKeyUp="return textMaxLen(this);" onBlur="return textMaxLen(this);" maxlength="300" title="可填300字" class='i-text ta-da'>${xwblForm.lastans}</textarea>
			       </li>
			    </ul>
			    </div>
				
			</td>
		</tr>

	</table>
		
	</div>
	<div class="rb_btn_fix">
		<input  class="queryBlue" id="but_save" type="button" value="保存" onclick="save()" />
	</div>
</form>
<div id="editWin" class="rb_btn_fix" style="margin:0 auto;text-align:center;width:auto;">
			<input type="hidden" id="qusEId"></input>
			<br>
			<textarea id="editValue" style="width : 300px;height : 60px;" onKeyUp="return textMaxLen(this);" onBlur="return textMaxLen(this);" maxlength="300" title="可填300字"></textarea>
			<div class="bottomBtn">
				<input type="button" class="queryBlue" id="yes"  value="确定" onclick="" />
			</div>
		</div>
</body>
</html>
<script type="text/javascript">
var applyId = $('#applyId').val();
var addCount = 0;
function compareDate(d1, d2) {  // 时间比较的方法，如果d1时间比d2时间大，则返回true   
    return Date.parse(d1.replace(/-/g, "/")) > Date.parse(d2.replace(/-/g, "/"))   
}

function textMaxLen(i){
 	 var conMaxLen=parseInt(i.getAttribute("maxlength"));
 	 if(i.value.length>=conMaxLen){
 		i.value=i.value.substring(0,conMaxLen)
 	 }
}
    
$(document).ready(function() {
	$('#editWin').window({
		title : '修改问题项',
		width:400,
		draggable : false,
		collapsible:false,
		resizable : false,
		closable : true,
		shadow : true,
		minimizable : false,
		maximizable : false,
		height : 160,
		closed : true,
		modal : true
	});
	$('#xwsj').datetimebox({
		editable : false,
		required:true
	});

	$("#yes").click(function(){
		var id = $("#qusEId").val();
		var isadd = ($("#isadd_" + id).val() == "Y");
		if (isadd){
			$("#qus_" + id).val($("#editValue").html());
		} else {
			$("#qus_" + id).html($("#editValue").html());
		}

		$("#qus_" + id).val($("#editValue").html());
		if($("#req_"+id).val()!="N"){
		$("#qus_" + id).html($("#editValue").html());
		}
		$("#content_" + id).val($("#editValue").html());
		$('#editWin').window('close');
	});
	//格式化时间
	$("#starttime").datetimebox({
	    showSeconds:false,
	    formatter:formatDate,
	    onSelect:function(date){
	        var y = date.getFullYear();
		    var m = date.getMonth() + 1;
		    var d = date.getDate();
		    var time=$('#starttime').datetimebox('spinner').spinner('getValue');
		    var starttime = y + "-" + (m < 10 ? ("0" + m) : m) + "-" + (d < 10 ? ("0" + d) : d) +' '+time+':00';
			var endtime = $("#endtime").val()+':00';
			if(starttime != ''&&endtime != '') {
				if(compareDate(starttime,endtime)) {
				    alert("开始时间不能大于截止时间！");
					//$("#starttime").datetimebox('clear');
					$('#starttime').datetimebox('hidePanel');
					return false;
				}
			}
			return true;
		}
	});
	$("#endtime").datetimebox({
	    showSeconds:false,
	    formatter:formatDate,
	    onSelect:function(date){
			var y = date.getFullYear();
		    var m = date.getMonth() + 1;
		    var d = date.getDate();
		    var time=$('#endtime').datetimebox('spinner').spinner('getValue');
		    var endtime = y + "-" + (m < 10 ? ("0" + m) : m) + "-" + (d < 10 ? ("0" + d) : d) +' '+time+':00';
			var starttime = $("#starttime").val()+':00';
			if(starttime != ''&&endtime != '') {
				if(compareDate(starttime,endtime)) {
				    alert("开始时间不能大于截止时间！");
					//$("#endtime").datetimebox('clear');
					$('#endtime').datetimebox('hidePanel');
					return false;
				}
			}
			return true;
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
   //校验追问的项信息录入完整性
   var $uls = $("#qa ul");
   //alert("123"+$uls.length);
   for(var i=0;i<$uls.length;i++){
      var $ul = $uls.eq(i);
      //alert($ul);
     // return false;
      var input1 = $ul.find("input[name='content']").val();
    //  var input2 = $ul.find("textarea[name='moreda']").val();
   //   alert(input1);
      if(input1!=''){
          
      }else{
          alert("请确认信息输入完整\n如果有多余的行请删除");
          $("#but_save").removeAttr("disabled");
          $("#but_save").attr({value:"保存"});//设置按钮属性
          return;
      }
   }
   //document.forms[0].submit();
   
   $('#myform').ajaxSubmit({
	    success : function(data) {
			$("#but_save").removeAttr("disabled");//将按钮可用
			if (data.state) {
				var tishi=layer.alert(data.msg,{
			     	title:'询问笔录制作',
			        icon:1,
			        shadeClose:true,
			     },
			     function(){
			    	//parent.closeLayer();
			        //layer.close(tishi);
			        window.parent.showkcxwbl('xwbl');
				 	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				 	parent.layer.close(index);
			     }
			     );
			}
		}
   });
}

function edit(id){
	var isadd = ($("#isadd_" + id).val() == "Y");
	var qus = "";
	if (isadd){
		qus = $("#qus_" + id).val();
	} else {
		qus = $("#qus_" + id).html();
	}

	if (qus == ""){
		qus = $("#qus_" + id).val();
	}

	if (qus == ""){
		qus = $("#qus_" + id).html();
	}
	$('#editWin').window('close');
	$('#editWin').window('open');
	$('#editWin').window('center');

	$("#qusEId").val(id);
	$("#editValue").html(qus);
}

function add(id){
	addCount++;
	var count = "addquestion" + addCount;
	$("#ul_" + id).after(
			$("<ul id='ul_" + count + "'>"
					+"<li style='height : 30px;margin-top : 8px;'>"
						+ "<input id='req_" + count + "' type='hidden' value='N' />"
						+ "<input id='isadd_" + count + "' name='isdel' type='hidden' value='Y' />"
						+ "<input type='hidden' name='wttype' value='1'/>"
						+ "<input type='hidden' name='ids' value='' />"
						+ " 问：<label id='" + count + "'></label>"
						+ "<input name='content' id='qus_" + count + "'  style='width : 600px; height : 22px;' class='i-text' type='text' />"
						+ "  <a id='edt_" + count + "' class='btslink' onclick=\'edit(\"" + count + "\")\' >修改</a>"
						+ "  <a id='add_" + count + "' class='btslink' onclick=\'add(\"" + count + "\")\' >追加</a>"
						+ "  <a id='del_" + count + "' class='btslink' onclick=\'del(\"" + count + "\")\' >删除</a>"
					+"</li>"
					+"<li>"
						+"答：<textarea id='ans_" + count + "' name='danr' type='text' class='i-text ta-da'></textarea>"
					+"</li>"
				+"</ul>"));

	$("#qus_" + count).validatebox({
		required : true
	});
}

function del(id){
	$.messager.confirm('删除问题项', '确认删除？', function(r) {
		if (r) {
			$("#ul_" + id).remove();
		}
	})
}
</script>