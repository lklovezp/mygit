<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
   <link href="/app/static/app/CSSReset.css" rel="stylesheet" type="text/css" />
    <link href="/app/static/app/common.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/app/static/app/js.js"></script>
	<script type="text/javascript" src="/app/static/app/data.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="/app/static/jquery/jquery.js"></script>
    <script type="text/javascript" src="/app/static/layer/layer.js"></script>
    <script type="text/javascript" src="/app/static/jquery/jquery.validate.js"></script>
	<script type="text/javascript" src="/app/static/jquery/jquery.form.js"></script>
    <!--easyui-->
    <link rel="stylesheet" href="/app/static/easyui/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="/app/static/easyui/themes/icon.css" type="text/css">
    <link rel="stylesheet" href="/app/static/app/easyUIReset.css"  type="text/css" />
    <link rel="stylesheet" href="/app/static/app/attachFileList.css" type="text/css">
    <script type="text/javascript" src="/app/static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/app/static/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/app/static/colorbox/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="/app/static/colorbox/colorbox.css" />
    <!--执法检查-->
    <link href="/app/static/app/css/taskDispatch.css" rel="stylesheet" type="text/css" />
    <!--时间插件 my97-->
    <script type="text/javascript" src="/app/static/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/app/static/common/All.js"></script>
    <link rel="stylesheet" href="/app/static/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<link rel="stylesheet" href="/app/static/app/css/zTreeReset.css" type="text/css" />
<script type="text/javascript" src="/app/static/ztree/js/jquery.ztree.core-3.5.js"></script>
<!--easyui-->
<link rel="stylesheet" href="/app/static/easyui/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="/app/static/easyui/themes/icon.css" type="text/css">
<link href="/app/static/app/css/easyUIReset.css" rel="stylesheet" type="text/css"/>

<style>
.basicinfoTable,.basicinfoTable td,.basicinfoTable th{ border: 1px solid #d4d4d4;}
.basicinfoTable th{background-color: #edfaff;}
.combo {border-color:  #578bd0 #95b8e7 #95b8e7 #578bd0;}
caption, th { text-align:right;}
.h1_1 {
    height: 40px;
    line-height: 40px;
    font-size: 18px;
    text-align:center;
}
</style>
	</HEAD>

	<body>
		<div class="h1_1">${title}</div>
		<div class="divContainer" id="questionContainer">
			<form id="queryForm" action="saveTimerTask.json" method="post">
				<input type="hidden" id="ids" value="${ids }">
				<input type="hidden" id="names" value="${names }">
				<input type="hidden" class="easyui-validatebox" data-options="required:false" id="id" name="id" value="${timerTaskForm.id}"/>
				<table class="dataTable basicinfoTable" width="99%" align="center" cellpadding="0" cellspacing="0" id="timerTaskTable">
					<tr>
						<th>
							<span class="mark">*</span>任务名称：
						</th>
						<td colspan="3">
							<textarea class="i-text easyui-validatebox" style="width:482px;height:45px;" data-options="required:true" id="name" name="name" >${timerTaskForm.name}</textarea>
						</td>
					</tr>
					<tr>
						<th>
							<span class="mark">*</span>主要内容：
						</th>
						<td colspan="3">
							<textarea class="i-text easyui-validatebox" style="width:482px;height:45px;" data-options="required:true" id="content" name="content" >${timerTaskForm.content}</textarea>
						</td>
					</tr>
					<tr>
						<th>
							<span class="mark">*</span>任务来源：
						</th>
						<td>
							<input class="i-text easyui-validatebox" type="text" id="source" name="source" value="${timerTaskForm.source}"/>
						</td>
						<th>
							<span class="mark">*</span>任务密级：
						</th>
						<td>
							<input class="i-text easyui-validatebox" type="text" id="security" name="security" value="${timerTaskForm.security}"/>
						</td>
					</tr>
					<tr>
						<th>
							<span class="mark">*</span>紧急程度：
						</th>
						<td>
							<input class="i-text easyui-validatebox" type="text" id="emergency" name="emergency" value="${timerTaskForm.emergency}"/>
						</td>
						<th>
							<span class="mark">*</span><a href="#" style="color:#3399CC;" id="selectRegister">登记人：</a>
						</th>
						<td>
						    <input type="text" class="i-text" style="width:119px;" id="registerName" name="registerName" value="${timerTaskForm.registerName}">
							<input type="hidden" style="width:119px;" class="i-text" id="register" name="register" value="${timerTaskForm.register}">
						</td>
					</tr>
					<tr>
						<th>
							<span class="mark">*</span><a href="#" style="color:#3399CC;" id="selectAccepter">接受人：</a>
						</th>
						<td>
						    <input type="text" class="i-text" style="width:119px;" id="accepterName" name="accepterName" value="${timerTaskForm.accepterName}">
							<input type="hidden" style="width:119px;" class="i-text" id="accepter" name="accepter" value="${timerTaskForm.accepter}">
						</td>
						<th>
							<span class="mark">*</span><a href="#" style="color:#3399CC;" id="selectHander">派发人：</a>
						</th>
						<td>
						    <input type="text" class="i-text" style="width:119px;" id="handerName" name="handerName" value="${timerTaskForm.handerName}">
							<input type="hidden" style="width:119px;" class="i-text" id="hander" name="hander" value="${timerTaskForm.hander}">
						</td>
					</tr>
					<tr>
						<th>
							<span class="mark">*</span>任务类型：
						</th>
						<td colspan="3">
							<input class="i-text easyui-validatebox" type="text" id="tasktype" name="tasktype" value="${timerTaskForm.tasktype}"/>
						</td>
					</tr>
					<tr>
						<th>
							<span class="mark">*</span>自动派发类型：
						</th>
						<td>
							<input class="i-text easyui-validatebox" type="text" id="type" name="type" value="${timerTaskForm.type}"/>
						</td>
						<th class="pfpc">
							<span class="mark">*</span>派发频次：
						</th>
						<td class="pfpc">
							<input class="i-text easyui-numberbox" type="text" id="times" name="times" value="${timerTaskForm.times}"/>
						</td>
					</tr>
					<tr>
						<th>
							<span class="mark">*</span>派发时间：
						</th>
						<td>
							<input class="easyui-datebox" style="width:133px;" id="taskstarted" name="taskstarted" value="${timerTaskForm.taskstarted}"/>
						</td>
						<th>
							<span class="mark">*</span>要求完成时限：
						</th>
						<td>
							<input class="easyui-datebox" style="width:133px;" id="taskended" name="taskended" value="${timerTaskForm.taskended}"/>
						</td>
					</tr>
					<tr>
						<th>
							<span class="mark">*</span>执法对象类型：
						</th>
						<td>
							<input class="i-text easyui-validatebox" type="text" id="lawobjtype" name="lawobjtype" value="${timerTaskForm.lawobjtype}"/>
						</td>
						<th>
							<span class="mark">*</span><a href="#" id="zfdxxz" class="btslink" onclick="zfdxxz()">选择执法对象</a>
						</th>
						<td>
							<input class="i-text" type="text" readOnly="true" id="lawobjName" name="lawobjName" value="${timerTaskForm.lawobjName}"/>
							<input class="i-text" type="hidden" id="lawobj" name="lawobj" value="${timerTaskForm.lawobj}"/>
						</td>
					</tr>
					<tr>
						<th>
							<span class="mark">*</span>批示意见：
						</th>
						<td colspan="3">
							<textarea class="i-text easyui-validatebox" data-options="required:true" style="width:483px;height:50px;" id="opinion" name="opinion">${timerTaskForm.opinion}</textarea>
						</td>
					</tr>
				</table>
				<div class="bottomBtn">
				<input type="submit" id="query"  class="queryBlue" value="保　存"/>
                <input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
				</div>
			</form>
		</div>
	</body>
</html>

<script language="JavaScript">
// 紧急程度对应的天数
var days = 20;

function setRegisterInfo(id,name) {
	//$("#register").linkbox("setValue", {id:id,name:name});
	$("#registerName").val(name);
	$("#register").val(id);
	jQuery().colorbox.close();
}
function setAccepterInfo(id,name) {
	//$("#accepter").linkbox("setValue", {id:id,name:name});
	$("#accepterName").val(name);
	$("#accepter").val(id);
	jQuery().colorbox.close();
}
function setHanderInfo(id,name) {
	//$("#hander").linkbox("setValue", {id:id,name:name});
	$("#handerName").val(name);
	$("#hander").val(id);
	jQuery().colorbox.close();
}

//选择执法对象
function zfdxxzAA(){
	var zfdxType = $('#lawobjtype').combobox('getValue');
	if(zfdxType!=null && zfdxType!=''){
		//All.ShowModalWin('selectLawobjPage.htm?lawobjtype='+$("#lawobjtype").combobox("getValue"), '选择执法对象');
		//var width=screen.width * 0.8+50;
	  	//var height=screen.height * 0.8-50;
	  	//var title='选择执法对象';
	  	//parent.layerIframe(2,'selectLawobjPage.htm?lawobjtype='+$("#lawobjtype").combobox("getValue"),title,width,height);
	  	var width=screen.width * 0.8-50;
  	    var height=screen.height * 0.8-120; 
	  	layer.open({
	        type: 2,
	        title: '编辑自动派发任务',
	        area: [width+'px', height+'px'],
	        content:'selectLawobjPage.htm?lawobjtype='+$("#lawobjtype").combobox("getValue")+'&id'+$("#id").val()
	    });
	}else{ 
		alert("请先选择执法对象类型！");
	}
}

function setloawObj(ids, names){
	var id = ids.split(",");
	var name = names.split(",");
	var data = new Array(id.length);
	var aidname = new Array();
	for (var i = 0; i < id.length; i++){
		aidname = new Array();
		aidname['id'] = id[i];
		aidname['name'] = name[i];
		data[i] = aidname;
	}
	$("#lawobj").val(id);
	$("#lawobjName").val(names);
}

function addDate(date){
	var d = new Date(date);
	var day = "";
	d.setDate(d.getDate() + (days));
	var m = d.getMonth() + 1;
	var month;
	if (m < 10){
		month = "0" + m;
	} else {
		month = m;
	}
	if (d.getDate() < 10){
		day = "0" + d.getDate();
	} else {
		day = d.getDate();
	}
	 
	return d.getFullYear() + '-' + month + '-' + day; 
}

$(document).ready(function(){
	$.ajaxSetup({cache:false});
	
	$("#selectRegister").colorbox({iframe:true,width:"300", height:"400",href:"taskUserPubQuery.htm?all=false&id=&notShowZj=false&methodname=setRegisterInfo&multi=false"});
	$("#selectAccepter").colorbox({iframe:true,width:"300", height:"400",href:"taskUserPubQuery.htm?all=false&id=&notShowZj=false&methodname=setAccepterInfo&&multi=false"});
	$("#selectHander").colorbox({iframe:true,width:"300", height:"400",href:"taskUserPubQuery.htm?all=false&id=&notShowZj=false&methodname=setHanderInfo&&multi=false"});
	
	$("#handerName").validatebox({required : true});
	$("#accepterName").validatebox({required : true});
	$("#registerName").validatebox({required : true});
	
	
	
	$('#lawobjtype').combotree({
	        required:true,
			//height:34,
			type:"post",
			url:'lawtypeTree.json'
		});
	
	
	$('#tasktype').combotree({
		required:true,
		editable:false,
		async:false,
		type:"post",
		url:'taskTypeExceptZX.json',
		onChange : function(a, b){
			if (a != b){
				$('#lawobjtype').combobox("setValue", "");
				$("#lawobj").val("");
				$("#lawobjName").val("");
				
				$.ajax({
					url: "getLawObjTypeByTaskType.json?tasktype="+a,
					success:function(data){
						$('#lawobjtype').combobox("loadData", data);
					}
				});
			}
		}
	});
	$('#source').combobox({
		type:"post",
		url:'dicList.json?type=1',
		required:true,
		editable:false,
		async:false,
		valueField:'id',
		textField:'name'
	});
	$('#emergency').combobox({
		url:'dicList.json?type=3',
		async:false,
		required:true,
		editable:false,
		valueField:'id',
		textField:'name',
		onSelect : function(o){
			days = parseInt(o.value);
		}
	});
	$('#security').combobox({
		url:'dicList.json?type=2',
		required:true,
		async:false,
		editable:false,
		valueField:'id',
		textField:'name'
	});
	$('#type').combobox({
		data:[{'id':'1','name':'定时（1次）'},{'id':'2','name':'按月'},{'id':'3','name':'按年'}],
		required:true,
		editable:false,
		valueField:'id',
		textField:'name',
		onSelect:function(){
			var val = $('#type').combobox('getValue');
			if (val == 2) {
				$(".pfpc").show();
				document.getElementById('timerTaskTable').rows[7].style.display = "none";
				$("#taskstarted").datebox({required : false});
				$("#taskended").datebox({required : false});
				$("#times").numberbox({required : true});
			} else {
				$(".pfpc").hide();
				document.getElementById('timerTaskTable').rows[7].style.display = "";
				$("#times").numberbox({required : false});
				$("#taskstarted").datebox({required : true});
				$("#taskended").datebox({required : true});
			}
		}
	});

	$('#taskstarted').datebox({
		onSelect : function(){
			var date = $('#taskstarted').datebox("getValue");
			var taskended = addDate(date);
			$("#taskended").datebox("setValue", taskended);
		}
	});
	
	if ('${timerTaskForm.id}' == ''){
		$('#type').combobox("setValue", '2');
		$('#emergency').combobox("select", '1');
		$('#security').combobox("setValue", '1');
		$(".pfpc").show();
		$('#source').combobox("setValue", '8');
		$("#times").numberbox({required : true});
		document.getElementById('timerTaskTable').rows[7].style.display = "none";
	} else {
		//$("#hander").linkbox("setValue", {id:"${timerTaskForm.hander}",name:"${timerTaskForm.handerName}"});
		//$("#accepter").linkbox("setValue", {id:"${timerTaskForm.accepter}",name:"${timerTaskForm.accepterName}"});
		//$("#register").linkbox("setValue", {id:"${timerTaskForm.register}",name:"${timerTaskForm.registerName}"});
         $("#registername").val("${timerTaskForm.registerName}");
	     $("#register").val("${timerTaskForm.register}");
	     $("#acceptername").val("${timerTaskForm.accepterName}");
	   	 $("#accepter").val("${timerTaskForm.accepter}");
	   	 $("#handername").val("${timerTaskForm.handerName}");
		 $("#hander").val("${timerTaskForm.hander}");
		if("${timerTaskForm.type}" == "2"){
			$(".pfpc").show();
			document.getElementById('timerTaskTable').rows[7].style.display = "none";
			$("#taskstarted").datebox({required : false});
			$("#taskended").datebox({required : false});
		} else {
			$(".pfpc").hide();
			$("#taskstarted").datebox({required : true});
			$("#taskended").datebox({required : true});
		}
		$.ajax({
			url: "getLawObjTypeByTaskType.json?tasktype=${timerTaskForm.tasktype}",
			success:function(data){
				$('#lawobjtype').combobox("loadData", data);
			}
		});
	}

	if ("${timerTaskForm.lawobj}" != ""){
		setloawObj("${timerTaskForm.lawobj}", "${timerTaskForm.lawobjName}");
	}

	$("#queryForm").validate( {
		errorClass : "error",
		submitHandler : function(form) {
			if($("#queryForm").form("validate")){
				$(form).ajaxSubmit( {
					type : "post",
					success : function(data) {
						if (data.state){
							alert(data.msg);
							//self.close();
							var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
							parent.layer.close(index);
						} else {
							alert(data.msg);
						}
					}
				});
			}
		}
	});
});


$("#J-from-reset").click(function(){
	if ('${timerTaskForm.id}' == ''){
		$("#queryForm").form("reset");
		$('#type').combobox("setValue", '2');
		$('#emergency').combobox("select", '1');
		$('#security').combobox("setValue", '1');
		$(".pfpc").show();
		$('#source').combobox("setValue", '8');
		$("#times").numberbox({required : true});
		document.getElementById('timerTaskTable').rows[7].style.display = "none";
	} else {
		$("#queryForm").form("reset");
		//$("#hander").linkbox("setValue", {id:"${timerTaskForm.hander}",name:"${timerTaskForm.handerName}"});
		//$("#accepter").linkbox("setValue", {id:"${timerTaskForm.accepter}",name:"${timerTaskForm.accepterName}"});
		//$("#register").linkbox("setValue", {id:"${timerTaskForm.register}",name:"${timerTaskForm.registerName}"});
		$("#registername").val("${timerTaskForm.registerName}");
	    $("#register").val("${timerTaskForm.register}");
	    $("#acceptername").val("${timerTaskForm.accepterName}");
	   	$("#accepter").val("${timerTaskForm.accepter}");
	   	$("#handername").val("${timerTaskForm.handerName}");
		$("#hander").val("${timerTaskForm.hander}");
		$("#handerName").removeClass("validatebox-invalid");
		$("#accepterName").removeClass("validatebox-invalid");
		$("#registerName").removeClass("validatebox-invalid");

		$("#lawobj").val("${timerTaskForm.lawobj}");
		$("#lawobjName").val("${timerTaskForm.lawobjName}");
	}
})

 // 选择执法对象
        function zfdxxz(){
      
        	var zfdxType = $('#lawobjtype').combotree('getValue');
        	var rwid = $("#tasktype").val();
        	var bs = "1";
        	if(zfdxType!=null && zfdxType!=''){
        		var href="choseezfdx.htm?lawobjtypeid="+zfdxType+'&rwid='+rwid+'&fzbs='+bs;
        		var width=screen.width * 0.8;
        	  	var height=screen.height * 0.8-100;
    		  	var index=layer.open({
    			  	  type: 2,
    			  	  title: "选择执法对象",
    			  	  maxmin: false,
    			  	  shadeClose: false, //点击遮罩关闭层
    			  	  area : [width+'px' , height+'px'],
    			  	  content: href
    			  	  
    			  	  });
    		  
        	}else{
        		alert("请先选择执法对象类型！");
        	}
        }

  function huixian(zfdxmc,mcs,idstr){
  //alert(idstr);
 // var obj=JSON.parse(mcs);
  //alert(zfdxmc);
  
			$("#lawobjName").val(zfdxmc);
			
			$("#lawobj").val(idstr);
	       //$("#lawobjName").val(names);
        }
        
        function rwlxcz(){
        }
        
         function rwmczh(id){
        }
        
        function closeLayerIframe(){
        	layer.closeAll('iframe');
        }
</script>