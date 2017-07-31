<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
    <link href="${app}/common.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${app}/js.js"></script>
	<script type="text/javascript" src="${app}/data.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link rel="stylesheet" href="${app}/easyUIReset.css"  type="text/css" />
    <link rel="stylesheet" href="${app}/attachFileList.css" type="text/css">
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
    <!--执法检查-->
    <link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
    <!--时间插件 my97-->
    <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
    <script type="text/javascript" src="${common}/All.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
	<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
	<link href="${app}/hnjz.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
</head>
<body style="padding-bottom: 70px;">
    <div class="h1_1">${title}</div>
    <div id="task_xsts" style="min-width: 1020px">
        <form id="myform" name="myform" method="post" action="xfdjSave.json">
           <input type="hidden" value="${xfdjForm.id}" id="id" name="id">
			<input type="hidden" value="${xfdjForm.xfdjId}" id="xfbjdId" name="xfbjdId">
			<input type="hidden" id="mcs" name="mcs" value="${mcs}"/>
			<input type="hidden" value="${xfdjForm.applyId}" id="applyId" name="applyId">
            <table class="queryTable"  width="95%" border="0" cellpadding="0" cellspacing="0" style=" margin:0 auto">
                <tr>
					<th>
						<span class="mark">*</span>信访来源：
					</th>
					<td colspan="3">
						<input class="form-text" type="text" data-options="required:true" id="xfly" name="xfly"
							value="${xfdjForm.xfly}" />
					</td>
					<th>
						信访编号：
					</th>
					<td>
						<input type="hidden" id="xfbh" name="xfbh" class="form-text" value="${xfdjForm.xfbh}"/>${xfdjForm.xfbh}
					</td>
				</tr>
				<tr>
				</tr>
				<tr id="xzdj">
					<th>
						<span class="mark">*</span>污染类型：
					</th>
					<td colspan="5">
						<a id="chooseWrlx" title="选择污染类型" class="btslink" onclick='chooseWrlx()'>选择污染类型</a>
					</td>
				</tr>
				<tr id="wrxz">
					<th>
						污染类型：
					</th>
					<td colspan="5">
						<!--<input class="i-text" style="width:248px;" type="text" id="wrlx" name="wrlx" value="${xfdjForm.wrlx}"/>
							<a id="chooseWrlx" title="选择污染类型" class="btslink" onclick='chooseWrlx()'>选择污染类型</a>
						-->
						<input class="form-text" style="width:248px;" type="hidden" id="wrlx" name="wrlx" value="${xfdjForm.wrlx}"/>
						<b>水污染</b>
						<c:forEach items="${swrList}" var="item">
									<input name="wrlx" type="checkbox"  value="${item.id}" 
										<c:if test="${fn:contains(xfdjForm.wrlx,item.id)}"> checked</c:if> <c:if test="${item.code==2104}"> onclick="checkboxOnclick1(this)"</c:if>/> ${item.name}
									<c:if test="${item.code==2104}"><input type="text" disabled="false" style="width:160px;" maxlength="49" class="form-text" id="swrqt" name="swrqt" value="${xfdjForm.swrqt}"/></c:if>
						</c:forEach><br/><br/>
						<b>大气污染</b>
							<c:forEach items="${dqwrList}" var="item">
										<input name="wrlx" type="checkbox" value="${item.id}" 
											<c:if test="${fn:contains(xfdjForm.wrlx,item.id)}"> checked</c:if> <c:if test="${item.code==2205}"> onclick="checkboxOnclick2(this)"</c:if>/> ${item.name}
										<c:if test="${item.code==2205}"><input type="text" disabled="false" style="width:160px;" maxlength="49" class="form-text" id="dqwrqt" name="dqwrqt" value="${xfdjForm.dqwrqt}"/></c:if>
							</c:forEach><br/><br/>
						<b>噪声污染</b>
							<c:forEach items="${zswrList}" var="item">
										<input name="wrlx" type="checkbox" value="${item.id}" 
											<c:if test="${fn:contains(xfdjForm.wrlx,item.id)}"> checked</c:if> <c:if test="${item.code==2304}"> onclick="checkboxOnclick3(this)"</c:if>/> ${item.name}
										<c:if test="${item.code==2304}"><input type="text" disabled="false" style="width:160px;" maxlength="49" class="form-text" id="zswrqt" name="zswrqt" value="${xfdjForm.zswrqt}"/></c:if>
							</c:forEach><br/><br/>
						<b>固废污染</b>
							<c:forEach items="${gfwrList}" var="item">
										<input name="wrlx" type="checkbox" value="${item.id}" 
											<c:if test="${fn:contains(xfdjForm.wrlx,item.id)}"> checked</c:if> <c:if test="${item.code==2406}"> onclick="checkboxOnclick4(this)"</c:if>/> ${item.name}
										<c:if test="${item.code==2406}"><input type="text" disabled="false" style="width:160px;" maxlength="49" class="form-text" id="gfwrqt" name="gfwrqt" value="${xfdjForm.gfwrqt}"/></c:if>
							</c:forEach><br/><br/>
						<b>辐射污染</b>
							<c:forEach items="${fswrList}" var="item">
										<input name="wrlx" type="checkbox" value="${item.id}" 
											<c:if test="${fn:contains(xfdjForm.wrlx,item.id)}"> checked</c:if> <c:if test="${item.code==2503}"> onclick="checkboxOnclick5(this)"</c:if>/> ${item.name}
										<c:if test="${item.code==2503}"><input type="text" disabled="false" style="width:160px;" maxlength="49" class="form-text" id="fswrqt" name="fswrqt" value="${xfdjForm.fswrqt}"/></c:if>
							</c:forEach><br/><br/>
								<b>生态环境污染</b>
							<c:forEach items="${sthjwrList}" var="item">
										<input name="wrlx" type="checkbox" value="${item.id}" 
											<c:if test="${fn:contains(xfdjForm.wrlx,item.id)}"> checked</c:if> <c:if test="${item.code==3402}"> onclick="checkboxOnclick6(this)"</c:if>/> ${item.name}
										<c:if test="${item.code==3402}"><input type="text" disabled="false" style="width:160px;" maxlength="49" class="form-text" id="sthjwrqt" name="sthjwrqt" value="${xfdjForm.sthjwrqt}"/></c:if>
							</c:forEach><br/><br/>
						<b>其他污染</b>
							<input type="text" style="width:280px;" maxlength="49" class="form-text" id="wrlxqt" name="wrlxqt" value="${xfdjForm.wrlxqt}"/><br/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="mark">*</span>信访时间：
					</th>
					<td>
						<input type="text" class="y-dateTime" data-options="required:true"  style="width:156px;" id="xfsj" name="xfsj" value="${xfdjForm.xfsj}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					</td>
					<th>
						信访人：
					</th>
					<td style="width:120px;">
						<input class="form-text" type="text" id="xfr" name="xfr" value="${xfdjForm.xfr}"/>
					</td>
					<th>
						联系电话：
					</th>
					<td>
						<input id="lxdh" class="form-text" name="lxdh" value="${xfdjForm.lxdh}"/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="mark">*</span>办结时限：
					</th>
					<td colspan="5">
						<input type="text" class="y-dateTime" data-options="required:true" style="width:156px;" id="bjsx" name="bjsx" value="${xfdjForm.bjsx}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					</td>
				</tr>
				<tr>
					<th>
						来文名称：
					</th>
					<td colspan="5" style="height:40px;">
						<input class="form-text" style="width:690px;" type="text" id="lwmc" name="lwmc" value="${xfdjForm.lwmc}"/>
					</td>
				</tr>
				<tr>
					<th>
						信访内容：
					</th>
					<td colspan="5">
						<textarea style="width:98%;height:120px;" class="y-text easyui-validatebox" id="xfnr" name="xfnr">${xfdjForm.xfnr}</textarea>
					</td>
				</tr>
				<tr>
					<th>
						记录人：
					</th>
					<td>
						<input class="form-text" type="text" id="jlr" name="jlr" value="${xfdjForm.jlr}"/>
					</td>
					<th>
						日期：
					</th>
					<td colspan="3">
						<input type="text" class="y-dateTime" id="jlsj" name="jlsj" style="width:156px;" value="${xfdjForm.jlsj}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					</td>
				</tr>
				<tr>
					<th></th>
					<td colspan="5" style="color:red;">请上传完纸质扫描件再填写支队长意见!</td>
				</tr>
				<tr>
					<th>
						支队领导意见：
					</th>
					<td colspan="5">
						<textarea style="width:98%;height:120px;" class="y-text easyui-validatebox" id="psyj" name="psyj">${xfdjForm.psyj}</textarea>
					</td>
				</tr>
				<tr>
				<th>企业类型：</th>
				<td colspan="2">
					<input class="form-text" type="text" id="zfdxType" name="zfdxType" readonly="readonly" value="${xfdjForm.zfdxType}" >
				</td>
				<th>企业名称：</th>
				<td colspan="3">
					<input class="form-text" type="text" id="zfdxmc" name="zfdxmc" value="${xfdjForm.zfdxmc}"/>
					<a href="#" id="zfdxxz" class="task_zflx" onclick="zfdxxz()" >选择执法对象</a>&nbsp;
				</td>
			</tr>
			<tr>
				<th>企业地址：</th>
				<td colspan="2">
					<input class="form-text" type="text" id="qydz" name="qydz" style="width:260px;" value="${xfdjForm.qydz}" />
				</td>
				<th align="right">
					<label class="requiredLabel">*</label>接受人：
				</th>
				<td colspan="3">
				    <input type="hidden" id="jsrId" name="jsrId" value="${xfdjForm.jsrId}"/>
					<input class="form-text" data-options="required:true" type="text" id="jsrName" readonly="readonly" value="${xfdjForm.jsrName}" />
					<c:if test="${isZd==0}">
							<c:if test="${isXfzy == 0}">
								<a href="#" class="task_zflx" onclick='javascript:zdselectrcrs()' id="zdselectrcrs" >选择接受人</a>
							</c:if>
							<c:if test="${isXfzy != 0}">
								<a href="#" class="task_zflx" onclick='javascript:dsselectjsr()' id="dsselectjsr">选择接受人</a>
							</c:if>
					</c:if>
					<c:if test="${isZd!=0}">
						<c:if test="${isXfzy == 0}">
							<a onclick='javascript:selectjsr()' id="selectjsr" >选择接受人</a>
						</c:if>
						<c:if test="${isXfzy != 0}">
							<a onclick='javascript:dsselectjsr()' id="dsselectjsr" >选择接受人</a>
						</c:if>
					</c:if>
				</td>
			</tr>
          </table>
    	</div>
	  	<!--相关附件-->
	    <div class="dataDiv" style="width:95%; margin:16px auto 25px;">
	        <div class="mt25" id="annex">
	            <div class="annex_header">
	                <a class="b_link" style="float:right;" onclick="downZipFile()">打包下载</a>
	                <a id="XGFJfileupload" class="b_link" style="float:right;" >上传附件</a>
	                	相关附件
	            </div>
	            <div class="annex_con" style=" height: 248px;">
	                <table id="XGFJdata" fit="true"></table>
	            </div>
	        </div>
	    </div>
	   <div  class="rb_btn_fix"  style="text-align: center; border-top:1px solid #d4d4d4">
	       <tr>
				<td align="center" colspan="6" style="height:50px;">
					<input type="submit" class="queryBlue" value="保存">&nbsp;&nbsp;&nbsp;
					<input id="pfbutton" type="button" class="queryOrange" value="派发"> &nbsp;&nbsp;&nbsp;
					<!--<c:if test="${isZd==0}">
						<input id="xpbutton" type="button" class="queryBlue" value="下派"> &nbsp;&nbsp;&nbsp;
						<input id="print" type="button" class="queryOrange" value="打印">&nbsp;&nbsp;&nbsp;
						<input type="button" class="queryBlue" id="J-from-reset" value="重置">
					</c:if>
					<c:if test="${isZd!=0}">
					</c:if>
					-->
					<input id="print" type="button" class="queryBlue" value="导出办结单">&nbsp;&nbsp;&nbsp;
					<input type="button" class="queryOrange" id="J-from-reset" value="重置">
				</td>
			</tr>
		</div>
   </form>
   <iframe name="download" id="download" src="" style="display: none"></iframe>
    <SCRIPT LANGUAGE="JavaScript">
var biaoshiId = $('#id').val();
var applyId = $('#applyId').val();
var wrxzsj = $('#wrlx').val();
var swrqtsj = $('#swrqt').val();
var dqwrqtsj = $('#dqwrqt').val();
var zswrqtsj = $('#zswrqt').val();
var gfwrqtsj = $('#gfwrqt').val();
var fswrqtsj = $('#fswrqt').val();
var sthjwrqtsj = $('#sthjwrqtsj').val();
if(wrxzsj == "" || wrxzsj == null){
	$("#wrxz").hide();
	$("#xzdj").show();
}else{
	$("#wrxz").show();
	$("#xzdj").hide();
}
if(swrqtsj != "" && swrqtsj != null){
	$('#swrqt').removeAttr("disabled");
}
if(dqwrqtsj != "" && dqwrqtsj != null){
	$('#dqwrqt').removeAttr("disabled");
}
if(zswrqtsj != "" && zswrqtsj != null){
	$('#zswrqt').removeAttr("disabled");
}
if(gfwrqtsj != "" && gfwrqtsj != null){
	$('#gfwrqt').removeAttr("disabled");
}
if(fswrqtsj != "" && fswrqtsj != null){
	$('#fswrqt').removeAttr("disabled");
}
if(sthjwrqtsj != "" && sthjwrqtsj != null){
	$('#sthjwrqtsj').removeAttr("disabled");
}

function checkboxOnclick1(checkbox){
	if (checkbox.checked == true){
		$('#swrqt').removeAttr("disabled");
	}else{
		$('#swrqt').attr("disabled",true);
	}
}
function checkboxOnclick2(checkbox){
	if (checkbox.checked == true){
		$('#dqwrqt').removeAttr("disabled");
	}else{
		$('#dqwrqt').attr("disabled",true);
	}
}
function checkboxOnclick3(checkbox){
	if (checkbox.checked == true){
		$('#zswrqt').removeAttr("disabled");
	}else{
		$('#zswrqt').attr("disabled",true);
	}
}
function checkboxOnclick4(checkbox){
	if (checkbox.checked == true){
		$('#gfwrqt').removeAttr("disabled");
	}else{
		$('#gfwrqt').attr("disabled",true);
	}
}
function checkboxOnclick5(checkbox){
	if (checkbox.checked == true){
		$('#fswrqt').removeAttr("disabled");
	}else{
		$('#fswrqt').attr("disabled",true);
	}
}
function checkboxOnclick6(checkbox){
	if (checkbox.checked == true){
		$('#sthjwrqt').removeAttr("disabled");
	}else{
		$('#sthjwrqt').attr("disabled",true);
	}
}

//一般专员选择接受人
function selectjsr(){
    var ids = $("#jsrId").val();	 
    $("#selectjsr").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?all=true&id="+ids+"&notShowZj=false&methodname=setUserInfoJsr&multi=false&showExist=true&group=0"});
}

//地市人员选择接受人
function dsselectjsr(){
    var ids = $("#jsrId").val();	 
    $("#dsselectjsr").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?all=false&id="+ids+"&notShowZj=false&methodname=setUserInfoJsr&multi=false&showExist=true&group=0"});
}

//总队专员选择人员
function zdselectrcrs(){
    var ids = $("#jsrIds").val();
    $("#zdselectrcrs").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?all=true&id="+ids+"&notShowZj=false&methodname=setUserInfoJsr&multi=false&showExist=true&group=5"});
}

//设置选择接受人
function setUserInfoJsr(id,name) {
	$("#jsrId").val(id);
	$("#jsrName").val(name);
	jQuery().colorbox.close();
}
var rows = "";
function huixian(zfdxmc,mcs){
	$("#zfdxmc").val(zfdxmc);
	$("#mcs").val(mcs);
	rows = $('#mcs').val();
	layer.closeAll('iframe');
}

$(document).ready(function(){
	if(rows == "" || rows == null){
		rows = $('#mcs').val();	
	}
	$("#J-from-reset").click(function(){
	   $("#myform").form("reset");
	});
	
	//信访来源下拉框
	$('#xfly').combobox({
		height:"30",
		url:'dicList.json?type=20',
		valueField:'id',
		textField:'name',
		displayField:'value',
		onSelect : function(o){
			days = parseInt(o.value);
			var m_date = new Date();
        	m_date.setDate(m_date.getDate()+days);//当前日期+几天  
        	$("#bjsx").val(myformatter(m_date)); 
		}
	});
	//执法对象类型下拉框
	$('#zfdxType').combobox({
		height:"30",
		url:'dicList.json?type=5',
		valueField:'id',
		textField:'name',
		onChange:function(newValue,oldValue){
	        return true;
	    }
	});
	
	//相关附件列表
    $('#XGFJdata').datagrid({
        pagination:true,//分页控件
		height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns : true,
		url:'queryFileList.json?canDel=Y',
		queryParams:{pid:$("#applyId").val(),fileType:'RWGLPFFJ',tableId:'XGFJdata'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:50},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:500},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:100},
			{field:'operate',title:'操作', align:'center', halign:'center',width:100}
		]]
	});
	//相关附件上传
	$("#XGFJfileupload").click(function(){
	    	//保存对应的执法对象类型和任务类型
	        var id = $("#applyId").val();
	        var rwlxIds = "13";
			var fileType = 'RWGLPFFJ';//派发附件
			if(id!=null && id!=''){
			    $.colorbox({
					iframe:true, width:"610", height:"380",
					href:'uploadPage.htm?pid='+id+'&fileType='+fileType+'&path=WORK&tableId=XGFJdata'
				});
			}else{
				$("#myform").ajaxSubmit({
     				type:"post",
					dataType:"json",
					url:"xfdjSave.json?zfdxmcs="+encodeURIComponent(rows),
					success: function(data){
						if(data.state){
							$("#applyId").val(data.applyId);
							$("#id").val(data.id);//这儿信访登记重复了，派发时用。
							$.colorbox({
								iframe:true, width:"610", height:"380",
								href:'uploadPage.htm?pid='+$("#applyId").val()+'&fileType='+fileType+'&path=WORK&tableId=XGFJdata'
							});
						}else{
							$.messager.alert('生成:',data.msg);
						}
					}
      			});
			}
	});
	
   	//表单校验
    $("#myform").validate({
		errorClass: "error",
		submitHandler:function(form){
			if ($("#myform").form("validate")){
				$(form).ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"xfdjSave.json?zfdxmcs="+encodeURIComponent(rows),
					success: function(data){
						if(data.state){
							alert(data.msg);
							var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
							parent.layer.close(index);
						}else{
							$.messager.alert('保存:',data.msg);
						}
					}
				});
			}
		}
	});
	$("#print").click(function(){
		//先保存再打印
		if(biaoshiId!=null && biaoshiId!=''){
			$('#download').attr('src','exportXfdjb.json?xfdjId='+biaoshiId);
		}else{
			$('#myform').attr('action','xfdjSave.json?zfdxmcs='+encodeURIComponent(rows));
			$('#myform').ajaxSubmit(function(data){
	   			if(data.state){
	   				$('#download').attr('src','exportXfdjb.json?xfdjId='+data.id);
	   				$("#id").val(data.id);
	   			}else{
					$.messager.alert('信访登记单保存:',data.msg);
				}
   			});
		}
	});
	//先保存再派发
	$("#pfbutton").click(function(){
		//表单校验
		var jsr = $("#jsrId").val();
		if ($("#myform").form("validate")){
			if(jsr == null || jsr == ""){
				alert("接受人不能空！");
			}else{
				//先保存再打印
				var pfbt = document.getElementById('pfbutton');
				$('#myform').attr('action','xfdjpf.json?xfdjId='+biaoshiId+'&zfdxmcs='+encodeURIComponent(rows));
				pfbt.disabled = "disabled";
				$('#myform').ajaxSubmit(function(data){
		 			if(data.state){
		 				alert("信访任务派发成功！");
		 				//window.location.href="xfdjList.htm?title=信访登记";
		 				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						parent.layer.close(index);
		 			}else{
		 				pfbt.removeAttribute('disabled');
						$.messager.alert('信访登记任务派发:',data.msg);
					}
				});
			}
		}
	});
});

	function zfdxxz(){
		var zfdxType = $('#zfdxType').combobox('getValue');
		var rwid = $("#applyId").val();
		var bs = "2";
		if(zfdxType!=null && zfdxType!=''){
			var href="selectLawobj.htm?lawobjtype="+zfdxType+'&rwid='+rwid+'&fzbs='+bs+'&rwlxIds=13';
			var width=1000;
	  	    var height=screen.height * 0.8-130; 
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
	};
	
	// 选择污染类型
	function chooseWrlx(){
		$("#wrxz").show();
		$("#xzdj").hide();
		//var zfdxType = "";
		//All.ShowModalWin("selectWrlx.htm?lawobjtype="+zfdxType,'选择污染类型',830,430);
	}
	
	function myformatter(date){
        var y = date.getFullYear();
        var m = date.getMonth()+1;
        var d = date.getDate();
        return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
    }
    
</SCRIPT>
</body>
</html>