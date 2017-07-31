<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>任务派发</title>
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
	</head>
	<body style='overflow-x:hidden;width: 98%'>
	        <c:if test="${isCp==0}">
	        <div class="headTitle" id="divTitle">任务派发</div>
		    </c:if>
		    <c:if test="${isCp==1}">
		    <div class="headTitle" id="divTitle">任务重派</div>
		    </c:if>
		    
		    <form id="myform" name="myform" method="post" action="pf.json">
		    <input type="hidden" id="applyId" name="applyId" value="${work.id}" />
		    <input type="hidden" id="id" name="id" value="${work.id}" />
		    <input type="hidden" id="mcs" name="mcs" value="${mcs}"/>
		    <input type="hidden" id="sysver" name="sysver" value="${sysVer}"/>
		     <input type="hidden" id="worksource" name="worksource" value="${work.source}"/>
			<div class="divContainer" style="padding:10px;">
			<!-- 任务信息 -->
			<div class="easyui-panel" title="任务信息" style="margin-bottom:10px;">
				<table width="100%" height="" border="0" align="left" cellpadding="0"
					cellspacing="0" class="formtable">
					<tr>
						<th width="100" align="right">
							<label class="requiredLabel">*</label>任务名称：
						</th>
						<td colspan="3">
							<select class="i-text" type="text" id="rwmcgs" name="gs" onChange="mcfs()">
								<option <c:if test='${work.rwmcgs == "0"}'>  selected='selected'</c:if> value="0">组合</option>
								<option <c:if test='${work.rwmcgs == "1"}'>  selected='selected'</c:if> value="1">自定义</option>
							</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="rcbg" id="rcbg" <c:if test='${rwlxIds == "24"}'>checked value='Y'</c:if> type="checkbox"/>
							<span>日常办公</span>
							<input name="xfts" id="xfts" <c:if test='${rwlxIds == "13"}'>checked value='Y'</c:if> type="checkbox"/>
							<span>信访投诉</span>
						</td>
					</tr>
					<tr class="zfdxInfo">
						<th>执法对象类型：</th>
						<td>
							<input class="i-text" type="text" id="zfdxType" name="zfdxType" readonly="readonly" value="${work.zfdxType}" />
						</td>
						<th width="150">执法对象名称：</th>
						<td>
							<input class="i-text" type="text" id="zfdxmc" name="zfdxmc" readonly="readonly" value="${work.zfdxmc}" style="width:400px"/>
							<a href="#" id="zfdxxz" class="btslink" onclick="zfdxxz()" >选择执法对象</a>&nbsp;
						</td>
					</tr>
					<tr class="zfdxInfo">
						<th>日期：</th>
						<td>
							<input class="easyui-datebox" data-options="editable:false" type="text" id="rwmcrq" name="rwmcrq" value="<fmt:formatDate value="${work.rwmcrq}" pattern="yyyy年MM月dd日"/>" />
						</td>
						<th>任务类型：</th>
						<td>
							<input class="i-text" type="text" id="rwlx" name="rwlx" style="width:405px" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th>
						</th>
						<td align="left" colspan="3">
							<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="workName" name="workName"/>${work.name}</textarea>
						</td>
					</tr>
					<tr>
						<th align="right">
							<label class="requiredLabel">*</label>主要内容：
						</th>
						<td align="left" colspan="3">
							<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="workNote" name="workNote"/>${work.workNote}</textarea>
						</td>
					</tr>
					<tr>
						<th align="right">
							<label class="requiredLabel">*</label>任务来源：
						</th>
						<td>
							<input class="i-text" id="source" name="source"  type="text" value="${work.source}" />
						</td>
						<th width="100" align="right">
							<label class="requiredLabel">*</label>任务密级：
						</th>
						<td>
							<input class="i-text" id="security" name="security" type="text" value="${work.security}" />
						</td>
					</tr>
					<tr>
						<th align="right">
							<label class="requiredLabel">*</label>紧急程度：
						</th>
						<td>
							<input class="i-text" id="emergency" name="emergency" type="text" value="${work.emergency}" />
						</td>
						<th align="right">
							<label class="requiredLabel">*</label>登记人：
						</th>
						<td>
							<input type="hidden" id="djrId" name="djrId" value="${work.djrId}" />
							<input class="i-text easyui-validatebox" data-options="required:true" type="text" id="djr" readonly="readonly" value="${work.djrName}" />
							<a href="#" id="selectdjr">选择人员</a>
						</td>
					</tr>
					<tr>
						<!--
							<th align="right">
								<label class="requiredLabel">*</label>生成时间：
							</th>
							<td>
							    <label/><fmt:formatDate value="${work.createTime}" pattern="yyyy-MM-dd"/></label>
							</td>
						-->
						<th align="right">
							<label class="requiredLabel">*</label>接受人：
						</th>
						<td>
						    <input type="hidden" id="jsrIds" name="jsrIds" value="${work.jsrIds}"/>
							<input class="i-text easyui-validatebox" data-options="required:true" type="text" id="jsrNames" readonly="readonly" value="${work.jsrNames}" />
							<c:if test="${sysVer != 0 }">
							<a onclick='javascript:selectjsr()' id="selectjsr" >选择人员</a>
							</c:if>
						</td>
						<th align="right">
							<label class="requiredLabel">*</label>要求完成时限：
						</th>
						<td>
							<input class="easyui-datebox" data-options="editable:false" type="text" id="endTime" name="endTime" value="<fmt:formatDate value="${work.endTime}" pattern="yyyy-MM-dd"/>" />
							<input type="hidden" id="hideTime" value="<fmt:formatDate value="${work.endTime}" pattern="yyyy-MM-dd"/>" />
						</td>
					</tr>
					<tr class="xfdjShow" <c:if test='${rwlxIds != "13"}'>style="display:none;"</c:if>>
						<th align="right">
							<label class="requiredLabel">*</label>信访登记表：
						</th>
						<td>
						    <input type="hidden" id="xfdjbId" name="xfdjbId" value="${work.xfdjbId}"/>
							<input class="i-text" type="text" id="xfbh" readonly="readonly" value="${xfbh}" />
							<a href="#" id="selectXfdjb">选择信访登记表</a>
						</td>
						<th align="right">
							信访报出接收人：
						</th>
						<td>
							<input type="hidden" id="xfbcjsrId" name="xfbcjsrId" value="${work.xfbcjsrId}"/>
							<input class="i-text" type="text" id="xfbcjsrName" name="xfbcjsrName" readonly="readonly" value="${work.xfbcjsrName}" />
							<a href="#" id="selectXfbcjsr">选择人员</a>
						</td>
					</tr>
					<tr class="xfdjShow" <c:if test='${rwlxIds != "13"}'>style="display:none;"</c:if>>
						<th align="right">
							是否代办：
						</th>
						<td>
						    <input class="i-text" type="text" id="sfdb" name="sfdb" value="${work.sfdb}"/>
						</td>
						<th align="right">
							办理人是否需要报出：
						</th>
						<td>
							<input class="i-text" type="text" id="blrsfbc" name="blrsfbc" value="${work.blrsfbc}"/>
						</td>
					</tr>
					<!--
						<tr>
							<th align="center">
								派发人：
							</th>
							<td>
								<label/>${curUser.name}</label>
							</td>
						</tr>
					-->
					<tr>
						<th align="right">
							<label class="requiredLabel">*</label>批示意见：
						</th>
						<td align="left" colspan="3">
							<textarea style="width:655px;height:40px;" class="i-text easyui-validatebox" data-options="required:true" id="psyj" name="psyj"/>${work.psyj}</textarea>
						</td>
					</tr>
				</table>
			</div>
			<!-- 相关附件 -->
			<div class="easyui-panel" title="相关附件" style="margin-bottom:10px;">
				<table style="width : 100%;" border="0" align="left" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="24" align="right">
							<a id="XGFJfileupload" class="btslink">上传附件</a>&nbsp;
							<a href="#" id="" class="btslink" onclick="downZipFile()">打包下载</a>&nbsp;
						</td>
					</tr>
					<tr>
						<td >
						    <div style="height: 200px">
							<table id="XGFJdata" fit="true"></table>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<table style="width : 100%;">
			<tr>
			    <c:if test="${isCp==0}">
			    <td align="center" height="50">
					<span class="btn btn-ok"> <input id="savebutton" type="button" onclick="sc()"
								value="保存"> </span>&nbsp;
					<span class="btn btn-ok"> <input id="pfbutton" type="submit"
							value="派发"> </span>&nbsp;
					<a href="#" id="J-from-reset" class="btslink">重置</a>&nbsp;
					
				</td>
			    </c:if>
			    <c:if test="${isCp==1}">
			    <td align="center" height="50">
				    <span class="btn btn-ok"> <input id="cpbutton" type="button" onclick="cp()"
									value="重派"> </span>&nbsp;
				</td>
			    </c:if>
			
		</table>
		</form>
		<iframe name="download" id="download" src="" style="display: none"></iframe>
	</body>
</html>
<script type="text/javascript">
var applyId = $('#applyId').val();
var source = $('#worksource').val();
function myformatter(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}  
          
function myparser(s){  
    if (!s) return new Date();  
    var ss = (s.split('-'));  
    var y = parseInt(ss[0],10);  
    var m = parseInt(ss[1],10);  
    var d = parseInt(ss[2],10);  
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){  
        return new Date(y,m-1,d);  
    } else {  
        return new Date();  
    }  
} 
//设置选择登记人
function setUserInfoDjr(id,name) {
	$("#djrId").val(id);
	$("#djr").val(name);
	jQuery().colorbox.close();
}
//选择接受人
function selectjsr(){
    var ids = $("#jsrIds").val();	 
    $("#selectjsr").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?all=false&id="+ids+"&notShowZj=false&methodname=setUserInfoJsr&multi=true&showExist=true"});
}
//设置选择接受人
function setUserInfoJsr(id,name) {
	$("#jsrIds").val(id);
	$("#jsrNames").val(name);
	jQuery().colorbox.close();
}
//设置选择信访报出接收人
function setUserInfoXfbcjsr(id,name) {
	$("#xfbcjsrId").val(id);
	$("#xfbcjsrName").val(name);
	jQuery().colorbox.close();
}
$(document).ready(function(){
	var mcgs = $('#rwmcgs').val();
	if(source == '11'){
		$(".zfdxInfo").hide();
		$('#rwmcgs').attr("disabled",true);
	}
	if($('#rcbg').val() == 'Y'){
		$('#rwmcgs').attr("disabled",true);
	}
	
	//先默认为只读属性
	if(mcgs == '1'){
		$("#zfdxType").combobox({required: false, disabled: true});
		$('#zfdxxz').attr("disabled",true);
		$('#zfdxmc').attr("disabled",true);
		$("#rwmcrq.easyui-datebox").datebox({required: false, disabled: true});
		$("#rwlx").combotree({multiple: false,required: false, disabled: true});
		if($("#xfts").val()=="Y"){
			$('#xfbh').validatebox({ 
				required:true
			});
		}
	}else{
		$("#zfdxType").combobox({required: true, disabled: false});
		$("#rwmcrq.easyui-datebox").datebox({required: true, disabled: false});
		$("#rwlx").combotree({multiple: false,required: true, disabled: false});
		//任务类型
		if($("#xfts").val()=="Y"){
			$('#rwlx').combotree('loadData', [{id:'13',text:'信访投诉'}]); 
			$("#rwlx").combotree('setValue', '13');
			$('#xfbh').validatebox({ 
				required:true
			});
		}else{
			$('#rwlx').combotree({
				multiple: false,
				url:'taskTypeTreeComboByTaskId.json?applyId='+applyId,
				valueField:'id',
				textField:'name',
				onChange : function (newValue,oldValue){
					var rw="";
					node=$('#rwlx').combotree('tree').tree('find',newValue);
					if(node != "" && node != null){
						rw=node.text;
						rwmczh(rw);
					}
				},
				value:'${rwlxIds}'
			});
		}
	}
    //要求完成时限选择校验
    $("#endTime").datebox({
	    onSelect: function(date){
	        var curTime=new Date();//当前时间
	        var st=curTime.getFullYear()+'-'+(curTime.getMonth()+1)+'-'+curTime.getDate();
	        var curDate=new Date(st.replace(/-/g,"/"));
		    if(date<curDate){
	            alert("要求完成时限不能早于当前时间！");
	            $(this).datebox("setValue",$("#hideTime").val());
	            return false;
	        }
	        return true;
	    }
	});
	
	//执法对象类型下拉框
	$('#zfdxType').combobox({
		url:'dicList.json?type=5',
		valueField:'id',
		textField:'name',
		onChange:function(newValue,oldValue){
	        rwlxjz();
	        rwmczh("");
	    }
	});
	//是否代办
	$('#sfdb').combobox({
		url:'sfList.json',
		valueField:'id',
		textField:'name'
	});
	//是否报出
	$('#blrsfbc').combobox({
		url:'sfList.json',
		valueField:'id',
		textField:'name'
	});
	$('#rwmcrq').datebox({
         formatter: function (date) {
              var y = date.getFullYear();
              var m = date.getMonth() + 1;
              var d = date.getDate();
              return y + "年" + (m < 10 ? ("0" + m) : m) + "月" + (d < 10 ? ("0" + d) : d) + "日";
        }
    });
	
    //任务来源下拉框
    $('#source').combobox({
        required:true,
		url:'dicList.json?type=1',
		valueField:'id',
		textField:'name'
	});
	//任务密级下拉框
    $('#security').combobox({
        required:true,
		url:'dicList.json?type=2',
		valueField:'id',
		textField:'name'
	});
	//紧急程度下拉框
    $('#emergency').combobox({
        required:true,
		url:'dicList.json?type=3',
		valueField:'id',
		textField:'name',
		onChange:function(newValue, oldValue){
		    //改变要求完成时间
		    var m_date = new Date();  
		    switch(newValue){
		        case '1'://一般
		           m_date.setDate(m_date.getDate()+20);//当前日期+几天  
		           $("#endTime").datebox("setValue",myformatter(m_date)); 
		           $("#hideTime").val(myformatter(m_date)); 
		           break;
		        case '2'://紧急
		           m_date.setDate(m_date.getDate()+7);//当前日期+几天  
		           $("#endTime").datebox("setValue",myformatter(m_date)); 
		           $("#hideTime").val(myformatter(m_date));
		           break;
		        case '3'://特急
		           m_date.setDate(m_date.getDate()+3);//当前日期+几天  
		           $("#endTime").datebox("setValue",myformatter(m_date)); 
		           $("#hideTime").val(myformatter(m_date));
		           break;
		        default:
		    }
		}
	});
	
	//选择登记人
	$("#selectdjr").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?all=false&notShowZj=false&methodname=setUserInfoDjr&multi=false"});
    //选择信访报出接收人
    $("#selectXfbcjsr").colorbox({iframe:true,width:"300", height:"380",href:"userPubQuery.htm?all=true&notShowZj=false&notShowSys=true&methodname=setUserInfoXfbcjsr&multi=false"});
	//选择信访登记表
	$("#selectXfdjb").colorbox({iframe:true,width:"1000", height:"600", scrolling:false, href:"choseeXfdjb.htm?type=edit"});

    $(".inputWithImge").each(function(){
        $(this).add($(this).next()).wrapAll('<div class="imageInputWrapper"></div>');
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
	    //任务名称校验
	    if($("#workName").validatebox("isValid")){
	    	//保存对应的执法对象类型和任务类型
    		var rows = $('#mcs').val();
			var checkedNodesIds = $('#rwlx').combotree("getValues");
	        var id = $("#applyId").val();
	        var rwlxIds;
	        if($("#xfts").val()=="Y"){
	        	rwlxIds = "13";
	        }
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
					url:"sc.json?checkedNodesIds="+checkedNodesIds+"&zfdxmcs="+encodeURIComponent(rows)+"&rwlxIds="+rwlxIds+'&rwmcgs='+$("#rwmcgs").val(),
					success: function(data){
						if(data.state){
							$("#applyId").val(data.id);
							$("#id").val(data.id);//这儿任务重复了，派发时用。
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
	    }else{
	        $("#workName").focus();
	    }
	});
	
	$("#myform").validate( {
		errorClass : "error",
		submitHandler : function(form) {
			if ($("#myform").form("validate")){
			    $("#pfbutton").attr({"disabled":"disabled"});
			    
			    //保存对应的执法对象类型和任务类型
    			var rows = $('#mcs').val();
				var checkedNodesIds = $('#rwlx').combotree("getValues");
			    var rwlxIds;
			    if($("#xfts").val()=="Y"){
			    	rwlxIds="13";
			    }
				$(form).ajaxSubmit( {
					type : "post",
					url : "pf.json?checkedNodesIds="+checkedNodesIds+"&zfdxmcs="+encodeURIComponent(rows)+"&rwlxIds="+rwlxIds+'&rwmcgs='+$("#rwmcgs").val(),
					success : function(data) {
					    $("#pfbutton").removeAttr("disabled");//将按钮可用
					    if (data.state) {
		                    alert(data.msg);
		                    self.close();
						} else {
							alert(data.msg);
						}
					}
				});
			}
		}
	});

});

// 重置
$("#J-from-reset").click(function(){
	//location.reload();
	$("#rcbg").attr("checked",false);
	//$('#rcbg').val("N");
	$("#zfdxType").combobox({required: false, disabled: false});
	$('#rwmcgs').attr("disabled",false);
	$('#zfdxxz').attr("disabled",false);
	$('#zfdxmc').attr("disabled",false);
	$("#rwmcrq.easyui-datebox").datebox({required: false, disabled: false});
	$("#rwlx").combotree({multiple: false,required: false, disabled: false});
    //document.location.reload();
	$("#myform").form("reset");
	$("#createTime").val('${work.createTime}');
	$("#endTime").val('${work.endTime}');
});

/**
 * 选择信访登记表后回填信访编号
 */
function setValues(id,xfbh){
	if(id!=""){
		$("#xfdjbId").val(id);
		$("#xfbh").val(xfbh);
	}
	jQuery().colorbox.close();
}

//任务生成
function sc(){
    //任务名称校验
    if($("#workName").validatebox("isValid")){
    	var checkedNodesIds="";
    	var mcs = $('#mcs').val();
		if($("#rcbg").val() == 'Y'){
    		checkedNodesIds = "24";
    	}else if($("#xfts").val() == 'Y'){
    		checkedNodesIds = "13";
    	}else{
			var t = $('#rwlx').combotree('tree'); // 得到树对象 
			var checkedNodes = t.tree('getChecked');
			for(var i=0;i<checkedNodes.length;i++){
				if(i<checkedNodes.length-1){
					checkedNodesIds+=checkedNodes[i].id+',';
				}else{
					checkedNodesIds+=checkedNodes[i].id;
				}
			}
    	}
    	var rwlxIds;
    	if($("#xfts").val()=="Y"){
	        	rwlxIds = "13";
	    }
        $('#myform').attr('action','sc.json?checkedNodesIds='+checkedNodesIds+'&zfdxmcs='+encodeURIComponent(mcs)+'&rwlxIds='+rwlxIds+'&rwmcgs='+$("#rwmcgs").val());
		$('#myform').ajaxSubmit(function(data){
	   		if(data.state){
			    $.messager.alert("生成:", data.msg, "info", function () {
	                 window.close();
	            });
	   		} else {
		   		alert(data.msg);
	   		}
	   	});
    }else{
        $("#workName").focus();
    }
}

//任务重派
function cp(){
    //任务名称校验
    if($("#myform").form("validate")){
    	var checkedNodesIds="";
    	var mcs = $('#mcs').val();
		var t = $('#rwlx').combotree('tree'); // 得到树对象 
		var checkedNodes = t.tree('getChecked');
		for(var i=0;i<checkedNodes.length;i++){
			if(i<checkedNodes.length-1){
				checkedNodesIds+=checkedNodes[i].id+',';
			}else{
				checkedNodesIds+=checkedNodes[i].id;
			}
		}
		var rwlxIds;
		if($("#xfts").val()=="Y"){
			rwlxIds="13";
		}
        $('#myform').attr('action','cp.json?checkedNodesIds='+checkedNodesIds+'&zfdxmcs='+encodeURIComponent(mcs)+'&rwlxIds='+rwlxIds+'&rwmcgs='+$("#rwmcgs").val());
		$('#myform').ajaxSubmit(function(data){
	   		if(data.state){
			    $.messager.alert("重派:", data.msg, "info", function () {
	                 window.close();
	            });		
	   		} else {
				alert(data.msg);
	   		}
	   	});
    }else{
        $("#workName").focus();
    }
}

//打包下载
function downZipFile(){
    var data = $('#XGFJdata').datagrid("getData");
	var ids = "";
	for(var i = 0; i < data.rows.length; i++){
		if (i > 0){
			ids += ",";
		}
		ids += data.rows[i].id;
	}
	if(ids!=null && ids!=''){
	    $('#download').attr('src','downZipFile?id='+ids);
	}else{
	    alert("没有附件！");
	}
}

/////附件操作/////
/**
 * 公用的上传文件之后的刷新grid方法
 * @param tableId tablegrid的id
 */
function reload(tableId,pid,fileType){
    var id = "#" + tableId;
	$(id).datagrid("reload",{pid:pid,fileType:fileType,tableId:tableId});
	jQuery.colorbox.close();
}
/**
 * 公用的删除文件方法 删除grid中的文件
 * @param obj grid的一行数据
 */
function deletefile1(obj){
	$.messager.confirm('操作', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
				url: "removeFile.json?id="+obj.id,
				success:function(data){
					alert(data.msg);
					var reloadtable= $(obj).attr("reloadtable");//自定义属性采用此方式获得 
					$('#'+reloadtable).datagrid('reload');
				}
			});
		}
	});
}
// 选择执法对象
function zfdxxz(){
	var zfdxType = $('#zfdxType').combobox('getValue');
	var rwid = $("#applyId").val();
	var bs = "1";
	if(zfdxType!=null && zfdxType!=''){
		All.ShowModalWin("selectLawobj.htm?lawobjtype="+zfdxType+'&rwid='+rwid+'&fzbs='+bs,'选择执法对象',1230,630);
		rwmczh("");
	}else{
		alert("请先选择执法对象类型！");
	}
}

$("#rwmcrq").datebox({
        onSelect: function(date){
			rwmczh("");
		}
    });

//任务名称的组合方法
function rwmczh(r){
	var zfdxmc = $('#zfdxmc').val();
	//组合时间日期
	var pfrq = $("#rwmcrq").datebox('getValue');
	var rwlxmc = $('#rwlx').combotree('getText');
	//组合任务类型
	if(r!=""){
		rwlxmc = r;
	}
	
	var text = "";
	text=zfdxmc+pfrq+rwlxmc;
	$("#workName").val(text);
}

function mcfs(){
	var mcgs = $('#rwmcgs').val();
	if(mcgs == '1'){
		$("#zfdxType").combobox({required: false, disabled: true});
		$('#zfdxxz').attr("disabled",true);
		$('#zfdxmc').attr("disabled",true);
		$("#rwmcrq.easyui-datebox").datebox({required: false, disabled: true});
		$("#rwlx").combotree({multiple: false,required: false, disabled: true});
	}else{
		$("#zfdxType").combobox({required: true, disabled: false});
		$('#zfdxxz').attr("disabled",false);
		$('#zfdxmc').attr("disabled",false);
		$("#rwmcrq.easyui-datebox").datebox({required: true, disabled: false});
		if($('#xfts').val() == 'Y'){
			$('#rwlx').combotree('loadData', [{id:'13',text:'信访投诉'}]); 
			$("#rwlx").combotree('setValue', '13');
		}else{
			$('#rwlx').combotree({
				required: true,
				disabled: false,
				multiple: false,
				url:'taskTypeTreeComboByTaskId.json?applyId='+applyId,
				valueField:'id',
				textField:'name',
				onChange : function (newValue,oldValue){
					var rw="";
					node=$('#rwlx').combotree('tree').tree('find',newValue);
					if(node != "" && node != null){
						rw=node.text;
						rwmczh(rw);
					}
				},
				value:'${rwlxIds}'
			});
		}
	}
}

function rwlxjz(){
	var zfdxType = $('#zfdxType').combobox('getValue');
	var zfdxmc = $('#zfdxmc').val();
	if(zfdxType!=null && zfdxType!=''){
		if(zfdxmc!=null && zfdxmc!=''){
			$("#zfdxmc").val("");//清空任务类型
			if($("#xfts").val()!="Y"){
				$("#rwlx").combotree('setValues',"");//清空任务类型
				$('#rwlx').combotree('reload','taskTypeTreeComboByTaskId.json?applyId='+applyId+'&zfdxlx='+zfdxType);
			}
		}
	}else{
		alert("请先选择执法对象类型！");
	}
}

//任务类型的重置
function rwlxcz(){
	var zfdxmc = $('#zfdxmc').val();
	var myarray= new Array();
	var myarray = zfdxmc.split(",");
	var zfdxType = $('#zfdxType').combobox('getValue');
	if(myarray.length>1){
			$("#xfts").val("N");
			$('#xfbh').validatebox({ 
				required:false
			});
			$(".xfdjShow").hide();
			$('#xfts').removeAttr('checked');
			zfdxType = 'zxbs';
			$('#rwlx').combotree('reload','taskTypeTreeComboByTaskId.json?applyId='+applyId+'&zfdxlx='+zfdxType);
			$("#rwlx").combotree({multiple: false,required: true, disabled: false});
	}else if($("#xfts").val()!="Y"){
			$("#rwlx").combotree('setValues',"");//清空任务类型
			$('#rwlx').combotree('reload','taskTypeTreeComboByTaskId.json?applyId='+applyId+'&zfdxlx='+zfdxType);
	}
}
$("#rcbg").click(function(){ 
	if(source=='11'){
		$(this).removeAttr('checked');
		alert("随机任务不能选择日常办公");
		return;
	}else{
		if($(this).attr('checked')){ 
			$("#rwmcgs").find("option[value='1']").attr("selected",true);
			$(this).val("Y");	
			$('#rwmcgs').attr("disabled",true);
			$('#zfdxmc').val("");
			$("#xfts").val("N");
			$('#xfbh').validatebox({ 
				required:false
			});
			$('#xfts').removeAttr('checked');
			$(".xfdjShow").hide();
		}else{
			$("#rwmcgs").find("option[value='0']").attr("selected",true);
			$(this).val("N");
			$('#rwmcgs').attr("disabled",false);
		}
		mcfs();
	}
});
$("#xfts").click(function(){
	var zfdxType = $('#zfdxType').combobox('getValue');
	var zfdxmc = $('#zfdxmc').val();
	var myarray = zfdxmc.split(",");
	if(source=='11'){
		$(this).removeAttr('checked');
		alert("随机任务不能选择信访投诉");
		return;
	}else if(myarray.length>1){
		$(this).removeAttr('checked');
		alert("多个执法对象时不能选择信访投诉");
		return;
	}else{
		if($(this).attr('checked')){ 
			$(this).val("Y");
			$('#rcbg').removeAttr('checked');
			$('#xfbh').validatebox({ 
				required:true
			});
			$(".xfdjShow").show();
		}else{
			$(this).val("N");
			$('#xfbh').validatebox({ 
				required:false
			});
			$(".xfdjShow").hide();
		}
		$('#rwmcgs').attr("disabled",false);	
		mcfs();
	}
});
</script>