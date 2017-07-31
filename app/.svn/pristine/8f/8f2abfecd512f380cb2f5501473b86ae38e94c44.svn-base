<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>勘察笔录</title>
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
<div class="headTitle">勘察笔录</div>
<form id="myform" name="myform" method="post" action="saveKcbl.json">
<input type="hidden" id="applyId" name="applyId" value="${work.id}" />
<input type="hidden" id="wflx" name="wflx" value="${wflx}" />
<input type="hidden" id="id" name="id" value="${kcblForm.id}" />
	<div class="divContainer">

    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formtable">
        <tr>
			<th>
				当事人：
			</th>
			<td colspan="7">
				<input style="width: 400px;" class="i-text" type="text" id="dsr" name="dsr" value="${kcblForm.dsr}" />
			</td>
		</tr>
		<tr>
			<th>
				地址：
			</th>
			<td colspan="7">
				<input style="width: 400px;" class="i-text" type="text" id="dz" name="dz" value="${kcblForm.dz}" />
			</td>
		</tr>
		<tr>
			<th>
				检查（勘察）时间：
			</th>
			<td colspan="7">
				<input class="easyui-datetimebox" type="text" id="starttime" name="starttime" value="${kcblForm.starttime}" /> 至 
				<input class="easyui-datetimebox" type="text" id="endtime" name="endtime" value="${kcblForm.endtime}" />
			</td>
		</tr>
		<tr>
			<th>
				地 点：
			</th>
			<td colspan="7">
				<input style="width: 400px;" class="i-text" type="text" id="dd" name="dd" value="${kcblForm.dd}" />
			</td>
		</tr>
		<tr>
			<th>
				检查（勘察）人：
			</th>
			<td>
			    <input class="i-text" type="text" id="kcr" name="kcr" value="${kcblForm.kcr}"/>
			</td>
			<th>
				执法证号：
			</th>
			<td colspan="5">
				<input class="i-text" type="text" id="zfzh" name="zfzh" value="${kcblForm.zfzh}" />
			</td>
		</tr>
		<tr>
			<th>
				记录人：
			</th>
			<td>
				<input class="i-text" type="text" id="jlr" name="jlr" value="${kcblForm.jlr}" />
			</td>
			<th>
				执法证号：
			</th>
			<td colspan="5">
				<input class="i-text" type="text" id="jlrzfzh" name="jlrzfzh" value="${kcblForm.jlrzfzh}" />
			</td>
		</tr>
		<tr>
			
			<td colspan="8">
				我们是<input class="i-text" type="text" id="zfrydwmc" name="zfrydwmc" value="${kcblForm.zfrydwmc}" />的执法人员，这是我们的执法证件（向当事人出示证件），请过目确认。今天我们依法进行检查并了解有关情况，你应当配合调查，如实回答询问和提供材料，不得拒绝、阻碍、隐瞒或者提供虚假情况。如果你认为我们与本案有利害关系，可能影响公正办案，可以申请我们回避，并说明理由。（暗查等无法告知的情形除外）
			</td>
		</tr>
		
		<tr>
			<td colspan="8">
			    <div id="qa">
					<c:forEach var="array" items="${kcxwblwtlist}" varStatus="indexstuts">
						<ul id="ul_${array.id}">
							<li style="margin-top : 8px;">
								<input id='isadd_${array.id}' name="isdel" type="hidden" value="${array.isdel}" />
								<input type="hidden" name="wttype" value="${array.wttype}"/>
								<input type="hidden" name="ids" value="${array.id}"/>
								<input type="hidden" name="content" id='content_${array.id}' value="${array.content}"/>
									<label id='qus_${array.id}'>${array.content}</label>
								<a id='edt_${array.id}_' class='btslink' onclick='edit("${array.id}")' />修改</a>
								<a id='add_${array.id}' class='btslink' onclick='add("${array.id}")' />追加</a>
								<c:if test="${array.isdel eq 'Y'}" >
									<a id='del_${array.id}' class='btslink' onclick='del("${array.id}")' />删除</a>
								</c:if>
							</li>
							<li>
								<textarea id='ans_${array.id}' name="danr" type='text' class='i-text ta-da' >${array.answer}</textarea>
							</li>
						</ul>
					</c:forEach>
				</div>
			</td>
		</tr>

	</table>
		
	</div>
	<div class="rb_btn_fix">
	 <input  class="queryBlue" id="but_save" type="button" value="保存" onclick="save()" />
	</div>
	<div id="editWin" class="rb_btn_fix" style="margin:0 auto;text-align:center;width:auto;">
			<input type="hidden" id="qusEId"></input>
			<br>
			<textarea id="editValue" style="width : 300px;height : 60px;"></textarea>
			<div class="bottomBtn">
				<input type="button" class="queryBlue" id="yes"  value="确定" onclick="" />
			</div>
	</div>
</form>

</body>
</html>
<script type="text/javascript">
var applyId = $('#applyId').val();
var addCount = 0;

function compareDate(d1, d2) {  // 时间比较的方法，如果d1时间比d2时间大，则返回true   
    return Date.parse(d1.replace(/-/g, "/")) > Date.parse(d2.replace(/-/g, "/"))   
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
	
	//检查（勘察）人、记录人
	//检查（勘察）人
    $('#kcr').combobox({
		url:'ryghCombo.json?applyId='+applyId,
		multiple: true,
		valueField:'id',
		textField:'name',
		onChange:function(newValue,oldValue){
		    //获取执法证号
	        $.ajax({
	            url: "getLwnumber.json?userIds="+newValue,
	            success:function(data){
	                if(data.lwnumbers!=null&&data.lwnumbers!=''){
	                    $('#zfzh').val(data.lwnumbers);
	                }
	            }
	        });
        }
	});
	//记录人
    $('#jlr').combobox({
		url:'ryghCombo.json?applyId='+applyId,
		valueField:'id',
		textField:'name',
		onChange:function(newValue,oldValue){
		    //获取执法证号
	        $.ajax({
	            url: "getLwnumber.json?userIds="+newValue,
	            success:function(data){
	                if(data.lwnumbers!=null&&data.lwnumbers!=''){
	                    $('#jlrzfzh').val(data.lwnumbers);
	                }
	            }
	        });
        }
	});
	if ('${kcblForm.kcr}' != ''){
		$("#kcr").combobox("setValues", '${kcblForm.kcr}'.split(","));
		var opts = $("#kcr").combo("options");
		opts.originalValue = '${kcblForm.kcr}'.split(",");
	}
	
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
   for(var i=0;i<$uls.length;i++){
      var $ul = $uls.eq(i);
      var input1 = $ul.find("input[name='content']").val();
      //var input2 = $ul.find("textarea[name='moreda']").val();
      //alert(input1+":"+input2);
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
			     	title:'勘察笔录制作',
			        icon:1,
			        shadeClose:true,
			     },
			     function(){
			    	//parent.closeLayer();
			        //layer.close(tishi);
			        window.parent.showkcxwbl('kcbl');
				 	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				 	parent.layer.close(index);
			     }
			     );
			}
		}
   });
   
}

//添加追问
function morewtda(){
    var _len = $("#tab ul").length;
    $("#tab").append("<ul id='"+(_len+1)+"'>"
                       +"<li>"
			           +"<input name='morewt' id='morewt"+(_len+1)+"' type='text' class='i-text' size='75' />"
			           +"<a class='btslink' onclick=\'delwtda("+(_len+1)+")\'>删除该问答</a>"
			           +"</li>"
			           +"<li>"
			           +"<textarea name='moreda' id='moreda"+(_len+1)+"' class='i-text ta-da'></textarea>"
			           +"</li>"
			        +"</ul>");
}

//删除追问
var delwtda =function(index){
   var _len = $("#tab ul").length;
   $("ul[id='"+index+"']").remove();//删除当前追问
   for(var i=index+1,j=_len;i<j;i++){
       var nextTxtVal1 = $("#morewt"+i).val();
       var nextTxtVal2 = $("#moreda"+i).val();
       $("ul[id=\'"+i+"\']").replaceWith("<ul id='"+(i-1)+"'>"
                       +"<li>"
			           +"<input name='morewt' id='morewt"+(_len+1)+"' type='text' class='i-text' size='75' value='"+nextTxtVal1+"' />"
			           +"<a class='btslink' onclick=\'delwtda("+(i-1)+")\'>删除该问答</a>"
			           +"</li>"
			           +"<li>"
			           +"<textarea name='moreda' id='moreda"+(i-1)+"' class='i-text ta-da'>"+nextTxtVal2+"</textarea>"
			           +"</li>"
			        +"</ul>");
   }    
        
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
//删除问题项
function del(id){
	$.messager.confirm('删除问题项', '确认删除？', function(r) {
		if (r) {
			$("#ul_" + id).remove();
		}
	})
}
//追加问题项
function add(id){
	addCount++;
	var count = "addquestion" + addCount;
	$("#ul_" + id).after(
			$("<ul id='ul_" + count + "'>"
					+"<li style='height : 30px;margin-top : 8px;'>"
						+ "<input id='req_" + count + "' type='hidden' value='N' />"
						+ "<input id='isadd_" + count + "' name='isdel' type='hidden' value='Y' />"
						+ "<input type='hidden' name='ids' value='' />"
						+ "<input type='hidden' name='wttype' value='1'/>"
						+ " <label id='" + count + "'></label>"
						+ "<input name='content' id='qus_" + count + "' style='width : 600px; height : 22px;' class='i-text' type='text' />"
						+ "  <a id='edt_" + count + "' class='btslink' onclick=\'edit(\"" + count + "\")\' >修改</a>"
						+ "  <a id='add_" + count + "' class='btslink' onclick=\'add(\"" + count + "\")\' >追加</a>"
						+ "  <a id='del_" + count + "' class='btslink' onclick=\'del(\"" + count + "\")\' >删除</a>"
					+"</li>"
					+"<li>"
						+"<textarea id='ans_" + count + "' name='danr' type='text' class='i-text ta-da'></textarea>"
					+"</li>"
				+"</ul>"));

	$("#qus_" + count).validatebox({
		required : true
	});
}
</script>