<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>待办任务-专项子任务办理页</title>
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
    <script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
    <!--时间插件 my97-->
    <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
    <!-- 任务管理 css-->
    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${common}/All.js"></script>
    <style type="text/css">
        #legalselect .combo{border: none;}
        #legalselect .combo .combo-text{padding-left: 10px; padding-right: 0;}
        #legalselect .combo .combo-arrow{background-color: transparent;}
        .window {
	    padding: 0px;
	    border-style: none;
	    
		}
		.window .window-body{
		border-color: #d4d4d4;
		}
		.panel-header, .panel-body {
	    border-color: #d4d4d4;
	    }
    </style>
</head>
<body style="background-color: #ffffff;padding-bottom: 60px;">
<h1 id="checkup_header">任务办理</h1>
<form id="blForm" name="blForm" method="post" action="">
    <input type="hidden" id="applyId" name="applyId" value="${work.id}" />
	<input type="hidden" id="taskId" name="taskId" value="${taskId}" />
	<input type="hidden" id="sy" name="sy" value="${sy}" />
<div class="dataDiv" style="width:95%;min-width: 1020px; margin:16px auto 25px;">
    <h3 class="task_h3 mt25"><span class="h_icon"></span>企业信息</h3>
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <input type="hidden" name="blZfdxForm.id" value="${blZxxdZrwMainForm.blZfdxForm.id}" />
        <tr bgcolor="#cff1ff" height="48">
            <th colspan="4" align="left">企业信息</th>
        </tr>
        <tr height="48">
            <td width="12%" align="center">执法对象</td>
            <td colspan="3">
              ${blZxxdZrwMainForm.blZfdxForm.lawobjname}
            </td>
        </tr>
        <tr height="48">
            <td width="12%" align="center">地址</td>
            <td colspan="3">${blZxxdZrwMainForm.blZfdxForm.address}</td>
        </tr>
        <tr height="48">
            <td width="12%" align="center" id="legalselect">
	            <span class="red">*</span>
	            <input type="text" class="y-text" style="width:100px; border: none" id="zwtitle" name="blZfdxForm.zwtitle"/>
            </td>
            <td width="38%">${blZxxdZrwMainForm.blZfdxForm.manager}</td>
            <td width="12%" align="center">联系电话</td>
            <td width="38%">${blZxxdZrwMainForm.blZfdxForm.managermobile}</td>
        </tr>
    </table>
    <!-- 任务信息 end-->
    <table class="dataTable mt25"  width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
        <tr>
            <td width="12%" align="right"><span class="red">*</span> 环保负责人</td>
            <td width="22%">
            <input type="text" class="y-text easyui-validatebox" data-options="required:true" style="width:200px;" name="blZfdxForm.bjcr" value="${blZxxdZrwMainForm.blZfdxForm.bjcr}"/>
            </td>
            <td width="8%" align="right"><span class="red">*</span> 职务</td>
            <td width="20%">
            <input type="text" class="y-text" id="zw" name="blZfdxForm.zw" style="width:200px;" id="post"/>
            </td>
            <td width="10%" align="right"><span class="red">*</span> 联系电话</td>
            <td width="20%">
            <input type="text" class="y-text easyui-validatebox" data-options="required:true" style="width:200px;" name="blZfdxForm.lxdh" value="${blZxxdZrwMainForm.blZfdxForm.lxdh}"/>
            </td>
        </tr>
    </table>
    <hr class="mt25" style="height: 2px;border: none; border-bottom: 2px dashed #d4d4d4" />
    <!--人员规划 -->
    <div class="personplan mt25">
        <h3 class="task_h3">
        <input type="button" class="bTn blue" id="selectxbr" value="选择协办人员" style="padding: 5px 10px; float:right;">
        <span class="h_icon"></span> 
        	人员规划
        </h3>
        <div class="mt25" style=" width: 100%; height: 218px;">
            <table id="ryghTable" fit="true"></table>
        </div>
    </div>
    <hr class="mt25" style="height: 2px;border: none; border-bottom: 2px dashed #d4d4d4" />
    <!--现场监察 -->
    <c:if test="${blZxxdZrwMainForm.blZxxdZrwForm != null }">
    <div class="supervision">
        <h3 class="task_h3 mt25"><span class="h_icon"></span>现场监察</h3>
        <table class="dataTable mt25"  width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center"><span class="red">*</span> 检查人</td>
                <td>
					<span class="jcr" id="jcr">
								<input type="hidden" id="jcrIds" value="${blZxxdZrwMainForm.blZxxdZrwForm.jcr}">
								<c:forEach items="${jcrList}" var="item"> 
									<c:choose> 
										<c:when test="${fn:contains(blZxxdZrwMainForm.blZxxdZrwForm.jcr,item.id)}"> 
											<input type="checkbox" checked="checked" name="blZxxdZrwForm.jcr" id="zxxdzrwJcr" value="${item.id}" /> ${item.name} 
										</c:when> 
										<c:otherwise>  
											<input type="checkbox" name="blZxxdZrwForm.jcr" id="zxxdzrwJcr" value="${item.id}" /> ${item.name} 
										</c:otherwise> 
									</c:choose> 
								</c:forEach> 
					</span>
				</td>
                <td align="center"><span class="red">*</span> 检查时间</td>
                <td>
                 <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_from" name="blZxxdZrwForm.jcsj1" value="${blZxxdZrwMainForm.blZxxdZrwForm.jcsj1}"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\'rcjc_to\',{d:-1});}'})"> 
                                       至 <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_to" name="blZxxdZrwForm.jcsj2" value="${blZxxdZrwMainForm.blZxxdZrwForm.jcsj2}"  onclick="WdatePicker({minDate:'#F{$dp.$D(\'rcjc_from\',{d:1});}'})">
                </td>
            </tr>
            <tr>
                <td align="center"><span class="red">*</span> 记录人</td>
                <td><input type="text" class="y-text" style="width:200px;" id="zxxdzrwJlr" name="blZxxdZrwForm.jlr" value="${blZxxdZrwMainForm.blZxxdZrwForm.jlr}"/></td>
                <td align="center">检查地点</td>
                <td><input type="text" class="y-text"  maxlength="100" onKeyUp="return textMaxLen(this);" onBlur="return textMaxLen(this);"  style="width:365px;" id="ZXXDJcdd" name="blZxxdZrwForm.jcdd" value="${blZxxdZrwMainForm.blZxxdZrwForm.jcdd}"/></td>
            </tr>
            <c:if test="${blZxxdZrwMainForm.blZxxdZrwForm.isexecchecklist == '0'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="3">
                <input type="text" class="y-text" id="jcmbId" name="blZxxdZrwForm.jcmbId" value="${blZxxdZrwMainForm.blZxxdZrwForm.jcmbId}"/>
                <a class='o_btn btn_orange opm' id="zxzrwDoCheck"  onclick='commonDoCheck()' style="">进行检查</a>
                <a class='o_btn btn_blue opm'  onclick='commonMoreCheck()' style="padding: 5px 16px; margin-left: 20px;">再次检查</a>
                </td>
            </tr>
            </c:if>
            <c:if test="${blZxxdZrwMainForm.blZxxdZrwForm.isexecchecklist == '1'}">
						<tr>
							<td align="center">
								监察模板：
							</td>
							<td colspan="1">
								<a class='o_btn btn_orange opm' onclick='singleDownload("${blZxxdZrwMainForm.blZxxdZrwForm.jcmbFileId}")'>下载监察模板</a>&nbsp;
							</td>
							<td colspan="1">
								<a class='o_btn btn_blue opm' onclick="singleUpload('ZXXDZRWJCJL')">上传监察记录</a>&nbsp;
							</td>
						</tr>
			</c:if>
            <tr>
                <td align="center"><span class="red">*</span> 监察笔录</td>
                <td colspan="3">
                <label id="filename_ZXXDZRWJCJL">
					<c:forEach items="${blZxxdZrwMainForm.blZxxdZrwForm.jcjlFileMap}" var="item">
						${item.filename}
						<a class='o_btn btn_grey opm' onclick="review(this)" id="${item.id},${item.filesize1}" >预览</a>&nbsp;
						<a class='o_btn btn_orange opm' onclick="download2('${item.id}')">下载</a>&nbsp;
						<a class='o_btn btn_blue opm' onclick="deleteJcbl('${item.id}','${item.fileTypeEnumName}','${item.moreCheckFiletype}','15')">删除</a>&nbsp;
						<c:if test = "${item.type eq '1'}">
							<a class='o_btn btn_grey opm' onclick="editMoreCheck('15','${item.id}')">修改</a>&nbsp;
						</c:if>
						<br/><br/>
					</c:forEach>
				</label>
                </td>
            </tr>
            <tr>
                <td align="center" valign="top"><span class="red">*</span> 监察结论</td>
                <td colspan="3">
                <textarea class="y-textarea" onKeyUp="return textMaxLen(this);" onBlur="return textMaxLen(this);" maxlength="500" name="blZxxdZrwForm.jcjl" id="jcjl" style="width: 80%; height:120px; float:left" >${blZxxdZrwMainForm.blZxxdZrwForm.jcjl}</textarea>
                <a href="javascript:void(0);" name="zfwj" style="width: 20px; display: block; float:left; margin-left: 20px; color: #00a2d9; line-height: 16px;">查看相关执法文件</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            <a class="b_link" id="ZXXDZRWfileupload" style="float:right;" >上传附件</a>
            相关附件
            <div class="annexselect">
                <input type="text" class="y-text" style="width:200px;" id="ZXXDZRWfiletype"/>
            </div>
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="ZXXDZRWdata" fit="true"></table>
        </div>
    </div>
    </c:if>
</div>
<div class="rb_btn_fix" style="text-align:center; cursor: pointer">
    <input id="savebutton" type="button"  onclick="baocun()" class="queryBlue" value="保  存" style="cursor: pointer">
    <input id="blbt" type="button" class="queryOrange" value="下一步" onclick="blwb()" style="cursor: pointer">
</div>
</form>
<iframe name="download" id="download" src="" style="display: none"></iframe>
<div id="editWin" style="margin:0 auto;text-align:center;width:auto;">
			<br/>
			<form id='jcqkform' method='post' action=''>
			<input type="hidden" id="taskTypeCode">
			<input type="hidden" id="fileId">
			<span style="height:200px;width:110px; display:block;vetical-align:top;text-align:right;float:left;">现场监察情况：</span>
			<textarea class="y-textarea" id="jcqk" name="jcqk" maxlength="2000" onKeyUp="return textMaxLen(this);" onBlur="return textMaxLen(this);" style="width:450px; height:300px;"></textarea>
			<div class="bottomBtn">
				<span class="btn btn-ok"><input class="queryBlue" onclick="buildJcbl()" type="button" value="生成监察笔录" /></span>&nbsp;
            </div>
</form>
</div><div align="center"> 
</div>
</body>
</html>
<script type="text/javascript">
var applyId = $('#applyId').val();
var jcrIds = $('#jcrIds').val();
String.prototype.trim = function()  
 {  
      return this.replace(/(^\s*)|(\s*$)/g, "");  
 }
function xzjcbgmb(tasktype){
	var jcmbId="";
	var filenameTEXT = "";
    switch(tasktype){//根据code判断任务类型
        case '15'://专项行动
           jcmbId = $('#jcmbId').combobox("getValue");
           filenameTEXT = $('#filename_ZXXDZRWJCJL').text().trim();
           break;
        default:
        	jcmbId = "10";
    }
    
    if (filenameTEXT == ""){
        alert("请先生成检查笔录。");
        return;
	}
	
	$('#download').attr('src',"saveDownJcbgmb?jcmbId="+jcmbId+"&applyId="+applyId+"&taskType=" + tasktype);
}

function textMaxLen(i){
 	 var conMaxLen=parseInt(i.getAttribute("maxlength"));                            
 	 if(i.value.length>=conMaxLen){
 		i.value=i.value.substring(0,conMaxLen)                                        
 	 }                                                                                
}  


function saveJcjl(){
	var jcjl = $("#jcjl").val();
	if (jcjl == ""){
		alert("前先填写监察结论。");
		return;
	}
	$.ajax({
		url: "saveJcjl.json?taskid="+applyId + "&tasktype=" + $("#taskTypeCode").val() +"&jcjl=" + encodeURI(encodeURI(jcjl)),
		success:function(data){
			alert(data.msg);
		}
	});
}

//定义一个对象（code：任务类型code,zwName：中文名称,pyjx：拼音简写；fileType：文件类型；）
function RwlxObj(code,zwName,pyjx,fileType){
    this.code = code;
    this.zwName = zwName;
    this.pyjx = pyjx;
    this.fileType=fileType;
}
var rwlxObj=new RwlxObj("","","","");
//通用展示"相关任务附件"
function showCOMMON(rwlxObj){
    //上传附件类型
    $('#'+rwlxObj.pyjx+'filetype').combobox({
    	height:34,
		url:'queryBlFileTypeComboZXXDZRW.json',
		valueField:'id',
		textField:'name'
	});
    var fileTypeNum=rwlxObj.fileType.split(',');
    var canDelStr="";
    for(var i=0;i<fileTypeNum.length;i++){
        if(i<fileTypeNum.length-1){
           canDelStr+="Y,";
        }else{
           canDelStr+="Y";
        }
    }
    //赋值
    $('#'+rwlxObj.pyjx+'data').datagrid({
        pagination:true,//分页控件
        height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns:true,
		url:'queryFileListMulfileType.json?canDel='+canDelStr,
		queryParams:{pid:$("#applyId").val(),fileType:rwlxObj.fileType,tableId:rwlxObj.pyjx+'data'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:150},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:500},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:100},
			{field:'operate',title:'操作', align:'center', halign:'center',width:150}
		]]
	});
	//上传
	$('#'+rwlxObj.pyjx+'fileupload').click(function(){
		var id = $("#applyId").val();
		var uploadFileType = $('#'+rwlxObj.pyjx+'filetype').combobox('getValue');
		if(uploadFileType!=null && uploadFileType!=''){
		    $(this).colorbox({
				iframe:true, width:"610", height:"380",
				href:'uploadPage.htm?pid='+id+'&fileType='+uploadFileType+'&path=WORK&tableId='+rwlxObj.pyjx+'data'
			});
		}else{
		    alert("请先选择上传附件类型类型！");
		}
	});
}

//设置选择协办人人
function setUserInfoXbr(id,name) {
    //保存协办人
    $.ajax({
	    url: "rySaveMul.json?applyId="+applyId+"&ryid="+id+"&type=06",
	    success:function(data){
	        //更新表格
	        $('#ryghTable').datagrid('reload');
	        jQuery().colorbox.close();
	        
	        //更新检查人、记录人下拉框
	        //$('#zxxdzrwJcr').combobox('reload','ryghCombo.json?applyId='+applyId);
	        $('#zxxdzrwJlr').combobox('reload','ryghCombo.json?applyId='+applyId);
	        if(data.state){
	        	$("#jcr").html("");
				var tempList = data.jcrList;
				for(var i=0;i<tempList.length;i++){
					if(jcrIds!="" && jcrIds.indexOf(tempList[i].id)!=-1){
						var checkbox="<input type='checkbox' id='zxxdzrwJcr' name='blZxxdZrwForm.jcr' value='"+tempList[i].id+"' checked/>&nbsp;"
									+tempList[i].name+"&nbsp;&nbsp;"; 
		    			$("#jcr").append(checkbox);
					}else{
						var checkbox="<input type='checkbox' id='zxxdzrwJcr' name='blZxxdZrwForm.jcr' value='"+tempList[i].id+"'/>&nbsp;"
									+tempList[i].name+"&nbsp;&nbsp;"; 
		    			$("#jcr").append(checkbox);
					}
			  	}
	        }
	    }
	});
}
//删除"人员规划协办人"
function del(curObj){
	$.messager.confirm('人员删除', '确定要删除当前记录吗？', function(r){
		if (r){
			$.ajax({
			  url: "delrygh.json?id="+curObj.id,
			  success:function(data){
				 $('#ryghTable').datagrid("reload",{applyId:applyId});
				 //更新检查人、记录人下拉框
		         //$('#zxxdzrwJcr').combobox('reload','ryghCombo.json?applyId='+applyId);
		         $('#zxxdzrwJlr').combobox('reload','ryghCombo.json?applyId='+applyId);
		         //更新检查人多选框
		         if(data.state){
		        	$("#jcr").html("");
					var tempList = data.jcrList;
					for(var i=0;i<tempList.length;i++){
						if(jcrIds!="" && jcrIds.indexOf(tempList[i].id)!=-1){
							var checkbox="<input type='checkbox' id='zxxdzrwJcr' name='blZxxdZrwForm.jcr' value='"+tempList[i].id+"' checked/>&nbsp;"
										+tempList[i].name+"&nbsp;&nbsp;"; 
			    			$("#jcr").append(checkbox);
						}else{
							var checkbox="<input type='checkbox' id='zxxdzrwJcr' name='blZxxdZrwForm.jcr' value='"+tempList[i].id+"'/>&nbsp;"
										+tempList[i].name+"&nbsp;&nbsp;"; 
			    			$("#jcr").append(checkbox);
						}
				  	}
	        	}
		      }   
			});
		}
	});
}
$("#selectxbr").click(function(){
	var data = $('#ryghTable').datagrid("getData");
	var ids = "";
	for(var i = 0; i < data.rows.length; i++){
		if (i > 0){
			ids += ",";
		}
		ids += data.rows[i].userid;
	}

	$.colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?all=true&displayAll=true&id="+ids+"&notShowZj=false&methodname=setUserInfoXbr&multi=true&condition=0"});
	
});

$(document).ready(function() {
	$.ajaxSetup({cache:false});
	
	$('#editWin').window({
		title : '填写现场监察情况',
		width:600,
		draggable : false,
		resizable : false,
		shadow : true,
		modal : true,
		minimizable : false,
		maximizable : false,
		height : 400,
		closed : true,
		modal : true
	});

	$('#zwtitle').combobox({
        required:true,
		url:'dicList.json?type=13',
		valueField:'id',
		editable : true,
		textField:'name',
		value : "${blZxxdZrwMainForm.blZfdxForm.zwtitle}",
		onSelect : function (r){
			if (r.id == '自定义'){
				$('#zwtitle').combobox({
					height:34,
			        required:true,
					editable : true,
					valueField : 'name',
					hasDownArrow : false,
					value : r.id,
					onChange : function (n, o){
						$('#zwtitle').combobox("setValue", n);
					}
				});
			} else {
				$('#zwtitle').combobox({
					height:34,
			        required : true,
					editable : false,
					hasDownArrow : true,
					value : r.id
				});
			}
		}
	});
	//相关执法文件
    $("a[name='zfwj']").click(function(){
    	var href = 'xxcx_lawdocList.htm'; 
      	var width=screen.width * 0.8;
      	var height=screen.height * 0.8-50;
      	parent.parent.layerIframe(2,href,"相关执法文件",width,height);
    	//All.ShowModalWin('xxcx_lawdocList.htm',"",1200,600);
    });
	$("#zw").combobox({
		height:34,
		required : true,
		url : 'dicList.json?type=14',
		valueField : 'id',
		editable : true,
		textField : 'name',
		value : "${blZxxdZrwMainForm.blZfdxForm.zw}",
		onSelect : function (r){
			if (r.id == '自定义'){
				$('#zw').combobox({
					height:34,
					required:true,
					editable : true,
					valueField : 'name',
					hasDownArrow : false,
					value : r.id,
					onChange : function (n, o){
						$('#zw').combobox("setValue", n);
					}
				});
			} else {
				$('#zw').combobox({
					height:34,
					required : true,
					editable : false,
					hasDownArrow : true,
					value : r.id
				});
			}
		}
	});
	
    //专项子任务
    <c:if test="${blZxxdZrwMainForm.blZxxdZrwForm != null }">
        //检查人
        //$('#zxxdzrwJcr').combobox({
        //    required:true,
		//	url:'ryghCombo.json?applyId='+applyId,
		//	multiple: true,
		//	valueField:'id',
		//	textField:'name'
		//});
		//记录人
        $('#zxxdzrwJlr').combobox({
        	height:34,
            required:true,
			url:'ryghCombo.json?applyId='+applyId,
			valueField:'id',
			textField:'name'
		});
		
		//人员规划列表
		$('#ryghTable').datagrid({
			height:34,
		    url:'ryghTable.json', 
		    queryParams:{applyId:applyId},
		    width:'100%',
			height:'auto',
			rownumbers: true,
			singleSelect: true,
			fitColumns:true,
		    columns:[[  
		        {field:'name',title:'姓名',width:100,align:'center'},   
		        {field:'type',title:'主办/协办',width:100,align:'center'},
		        {field:'lawnumber',title:'执法证号',width:100,align:'center'},
		        {field:'workmobile',title:'电话',width:100,align:'center'},
		        {field:'org',title:'部门',width:100,align:'center'},
		        {field:'operate',title:'操作',width:100,align:'center'}
		    ]]
		});

		//检查模板
        $('#jcmbId').combobox({
        	height:34,
            required:true,
			url:'getJcmbByTaskType.json?tasktype=15',
			panelHeight : 200,
			valueField:'id',
			textField:'name'
		});
		
		
		//相关任务附件
		rwlxObj=new RwlxObj("","专项行动子任务","ZXXDZRW","ZXXDZRWJCJLSMJ,ZXXDZRWXZWS,ZXXDZRWQTZL,ZXXDZRWCLYJS,ZXXDZRWSPZL,ZXXDZRWLYZL,ZXXDZRWZP,ZXXDZRWHPPFWJ,ZXXDZRWYSPFWJ");
		showCOMMON(rwlxObj);
    </c:if>
    
    //导入单机信息
    $("#getDjMessage").click(function(){
		All.ShowModalWin('getDjMessage.htm?applyId='+applyId, '任务详情', 600, 350);
		//alert(window.location.href);
		window.name = "__self"; 
		window.open(window.location.href, "__self"); 
		//location.reload(true);
	});
});

//专项子任务（开始检查）
//通用（开始检查）
function commonDoCheck(){
    var taskTypeCode="15";//专项任务
    var fileTypeEnumName="";//附件类型枚举名称
    //开始检查前先保存
    $('#blForm').attr('action','saveZxzrw_zxPage.json');
	$('#blForm').ajaxSubmit(function(data){
   		if(data.state){
			var jcmbId="";
			jcmbId = $('#jcmbId').combobox("getValue");
	        fileTypeEnumName='ZXXDZRWJCJL';
	        var moreCheckFiletype='ZXXDZRWMOREJCBL';
		    if(jcmbId!=null && jcmbId!=''){
		        if(checkDoCheck()){
		        	var href = "jcd_page.htm?jcmbId="+jcmbId+"&applyId="+applyId+"&taskType="+taskTypeCode; 
		          	var width=1000;
		          	var height=530;
		          	layer.open({
		      	  	  type: 2,
		      	  	  title: "生成监察笔录",
		      	  	  maxmin: true,
		      	  	  shadeClose: false, //点击遮罩关闭层
		      	  	  area : [width+'px' , height+'px'],
		      	  	  content: href
		      	  	  });
		            showJcbl(fileTypeEnumName,moreCheckFiletype,taskTypeCode);
		        }
			}else{
			    alert("监察模板配置不正确，请与管理员联系！");
			}     
		    
		}
   	});
}
function closeLayer(){
	showJcbl("ZXXDZRWJCJL","ZXXDZRWMOREJCBL","15");
	layer.closeAll('iframe');
}
//通用开始检查校验
function checkDoCheck(){
    if(!$("input[name='blZfdxForm.bjcr']").validatebox("isValid")){
    	alert("请输入环保负责人");
        return false;
    }
    if($("#zw").combobox("getText")==""){
    	alert("请选择环保负责人职务");
        return false;
    }
    if(!$("input[name='blZfdxForm.lxdh']").validatebox("isValid")){
    	alert("请输入环保负责人联系电话");
        return false;
    }
    var flag = 0;
	//检查人校验
	$("input[id='zxxdzrwJcr']:checkbox").each(function(){ 
		if ($(this).attr("checked")) { 
			flag += 1; 
		} 
	});
	if(flag==0){
		alert("请选择检查人");
        return false;
    }
    var jlr = $('#zxxdzrwJlr').combobox('getValue');
    if(jlr==""){
    	alert("请选择记录人");
        return false;
    }
    
    
    var data = $('#ryghTable').datagrid("getData");
	if(data.rows.length<2){
	    alert("请选择协办人员！");
	    return false;
	}
    
    return true;
}

//通用单附件回显
function commonShowFile(fileTypeEnumName){
    $.ajax({
	  url: "getCommonFile.json?applyId="+applyId+"&fileTypeEnumName="+fileTypeEnumName,
	  success:function(data){
	      if(data.commonFileMap!=null){
	          $('#filename_'+fileTypeEnumName).html(data.commonFileMap.filename);
	          $('#oper_'+fileTypeEnumName).html("<a href='#' class='btslink' onclick='review(this)' id='"+data.commonFileMap.id+","+data.commonFileMap.filesize1+"' >预览</a>&nbsp;"
	                               +"<a href='#' class='btslink' onclick=download2('"+data.commonFileMap.id+"')>下载</a>&nbsp;"
	                               +"<a href='#' class='btslink' onclick=singleDelete('"+data.commonFileMap.id+"','"+fileTypeEnumName+"')>删除</a>&nbsp;");
	      }else{
	          $('#filename_'+fileTypeEnumName).html("");
	          $('#oper_'+fileTypeEnumName).html("");
	      }
	   }
	});
}

//通用（生成监察报告）
function buildJcbg(taskTypeCode){
	var jcjl = $("#jcjl").html();
	if (jcjl == ""){
		alert("请先填写监察结论。");
		return;
	}
    var hasjcjlFileM=false;//是否有监察笔录
    var fileTypeEnumName="";//附件类型枚举名称
    //开始检查前先保存
    $('#blForm').attr('action','saveZxzrw_zxPage.json');
	$('#blForm').ajaxSubmit(function(data){
   		if(data.state){
			var jcmbId="";
			jcmbId = $('#jcmbId').combobox("getValue");
		    fileTypeEnumName='ZXXDZRWJCBG';
		    var filenameTEXT=$('#filename_ZXXDZRWJCJL').text().trim();
            if(filenameTEXT!=''){
                hasjcjlFileM=true;
            }
            if(hasjcjlFileM){
                //判断是否有监察报告
		        var hasjcbgFileM=false;
	            var jcbgFilenameTEXT=$('#filename_'+fileTypeEnumName).text().trim();
	            if(jcbgFilenameTEXT!=''){
	                hasjcbgFileM=true;
	            }
	            if(hasjcbgFileM){
	                $.messager.confirm('监察报告', '监察报告只有一份，重新生成或上传后覆盖之前的文件', function(r) {
						if (r) {
							$.ajax({
							  url: "buildJcbg.json?jcmbId="+jcmbId+"&applyId="+applyId+"&taskType="+taskTypeCode + "&jcjl=" + encodeURI(encodeURI(jcjl)),
							  success:function(data){
							      if(data.state){
							    	  $('#editWin').window('close');
							          $.ajax({
										  url: "getCommonFile.json?applyId="+applyId+"&fileTypeEnumName="+fileTypeEnumName,
										  success:function(data){
										      if(data.commonFileMap!=null){
										          $('#filename_'+fileTypeEnumName).html(data.commonFileMap.filename);
										          $('#oper_'+fileTypeEnumName).html("<a href='#' class='btslink' onclick='review(this)' id='"+data.commonFileMap.id+","+data.commonFileMap.filesize1+"' >预览</a>&nbsp;"
										                               +"<a href='#' class='btslink' onclick=download2('"+data.commonFileMap.id+"')>下载</a>&nbsp;"
										                               +"<a href='#' class='btslink' onclick=singleDelete('"+data.commonFileMap.id+"','"+fileTypeEnumName+"')>删除</a>&nbsp;");
										                               
										          $('#download').attr('src','downloadFile?id='+data.commonFileMap.id);
										      }
										   }
									  });
							      }
							   }
							});
						}
					});
	            }else{
	                $.ajax({
					  url: "buildJcbg.json?jcmbId="+jcmbId+"&applyId="+applyId+"&taskType="+taskTypeCode + "&jcjl=" + encodeURI(encodeURI(jcjl)),
					  success:function(data){
					      if(data.state){
					    	  $('#editWin').window('close');
					          $.ajax({
								  url: "getCommonFile.json?applyId="+applyId+"&fileTypeEnumName="+fileTypeEnumName,
								  success:function(data){
								      if(data.commonFileMap!=null){
								          $('#filename_'+fileTypeEnumName).html(data.commonFileMap.filename);
								          $('#oper_'+fileTypeEnumName).html("<a href='#' class='btslink' onclick='review(this)' id='"+data.commonFileMap.id+","+data.commonFileMap.filesize1+"' >预览</a>&nbsp;"
								                               +"<a href='#' class='btslink' onclick=download2('"+data.commonFileMap.id+"')>下载</a>&nbsp;"
								                               +"<a href='#' class='btslink' onclick=singleDelete('"+data.commonFileMap.id+"','"+fileTypeEnumName+"')>删除</a>&nbsp;");
								                               
								          $('#download').attr('src','downloadFile?id='+data.commonFileMap.id);
								      }
								   }
							  });
					      }
					   }
					});
	            }
            }else{
		        alert("请先生成监察笔录");
		    }
		}
   	});
   	
}

function download2(id){
	$('#download').attr('src','downloadFile?id='+id);
}
function deletefile2(id){
	var index=layer.confirm('确定要删除吗？', {
     	icon: 3, 
        title:'操作'
     }, function(index){
    	 $.ajax({
				url: "removeFile.json?id="+id,
				success:function(data){
					alert(data.msg);
				}
			}); 
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}

//保存草稿
function baocun(){
    $('#blForm').attr('action','saveZxzrw_zxPage.json');
	$('#blForm').ajaxSubmit(function(data){
   		if(data.state){
			alert(data.msg);
		}else{
			$.messager.alert('任务办理保存:',data.msg);
		}
   	});
}
//办理完毕
function blwb() {
    //前台校验
    if($("#blForm").form('validate')){
        //保存
        $('#blForm').attr('action','saveZxzrw_zxPage.json');
		$('#blForm').ajaxSubmit(function(data){
	   		if(data.state){
	   		    //后台校验
		        $.post('checkZxZrwBlBL.json', {
					applyId : applyId
				}, function(json) {
					if (json.state) {
						$('#blbt').attr("disabled","true");
						//办理完毕
						$('#blForm').attr('action','saveZxzrw_zxPageBlwb.json');
						$('#blForm').ajaxSubmit(function(data){
						    if(data.state){
						       alert(data.msg);
						       if($('#sy').val()=='1'){
						    	  parent.closeLayerIframe(); 
						       }else{
						    	   parent.closeLayer();
						       }
			                }else{
			                   $.messager.alert('任务办理完毕:',data.msg);
			                }
			            });
					} else {
						//提示信息
						alert(json.msg);
					}
				}, 'json');
			}else{
			    $.messager.alert('任务办理保存:',data.msg);
			}
	   	});
	}
}

//单附件上传
function singleUpload(fileTypeEnumName){
    //判断是否有监察报告
    var hasFileM=false;
    var filenameTEXT=$('#filename_'+fileTypeEnumName).text().trim();
    if(filenameTEXT!=''){
        hasFileM=true;
    }
    if(hasFileM){
    	 var tishi=layer.alert("该类型附件只有一份，重新生成或上传后覆盖之前的文件",{
 	     	title:'单附件上传',
 	        icon:1,
 	        shadeClose:true,
 	     },
 	     function(){
 	    	All.ShowModalWin("singleUploadPage.htm?pid="+applyId+"&fileType="+fileTypeEnumName+'&path=WORK',"附件上传",430,150);
            commonShowFile(fileTypeEnumName);
 	        layer.close(tishi);
 	     }
 	     );
    }else{
        All.ShowModalWin("singleUploadPage.htm?pid="+applyId+"&fileType="+fileTypeEnumName+'&path=WORK',"附件上传",430,150);
        commonShowFile(fileTypeEnumName);
    }
}
//单附件下载
function singleDownload(fileId){
	$('#download').attr('src','downloadFile?id='+fileId);
}
//单附件删除
function singleDelete(id,fileTypeEnumName){
	var index=layer.confirm('确定要删除吗？', {
     	icon: 3, 
        title:'操作'
     }, function(index){
    	 $.ajax({
				url: "removeFile.json?id="+id,
				success:function(data){
					alert(data.msg);
					commonShowFile(fileTypeEnumName);
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}

/////附件操作/////
/**
 * 公用的上传文件之后的刷新grid方法
 * @param tableId tablegrid的id
 */
function reload(tableId,pid,fileType){
	var id = "#" + tableId;
	$(id).datagrid("reload");
	jQuery.colorbox.close();
}
/**
 * 公用的删除文件方法 删除grid中的文件
 * @param obj grid的一行数据
 */
function deletefile1(obj){
	var index=layer.confirm('确定要删除吗？', {
     	icon: 3, 
        title:'操作'
     }, function(index){
    	 $.ajax({
				url: "removeFile.json?id="+obj.id,
				success:function(data){
					alert(data.msg);
					var reloadtable= $(obj).attr("reloadtable");//自定义属性采用此方式获得 
					$('#'+reloadtable).datagrid('reload');
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
	
}
//通用（再次检查）
function commonMoreCheck(){
	var taskTypeCode="15";//专项任务
	var hasjcjlFileM=false;
	var filenameTEXT=$('#filename_ZXXDZRWJCJL').text().trim();
	if(filenameTEXT!=''){
		  hasjcjlFileM=true;
	}
	if(hasjcjlFileM){
		$("#taskTypeCode").val(taskTypeCode);
		$("#jcqk").val("");
		$("#fileId").val("");
		$('#editWin').window('open');
		$('#editWin').window('center');
	}else{
		 alert("请先生成监察笔录");
	}    
}
//删除监察笔录
function deleteJcbl(id,fileTypeEnumName,moreCheckFiletype,tasktypeCode){
	var index=layer.confirm('确定要删除吗？', {
     	icon: 3, 
        title:'操作'
     }, function(index){
    	 $.ajax({
				url: "removeJcbl.json?id="+id,
				success:function(data){
					alert(data.msg);
					showJcbl(fileTypeEnumName,moreCheckFiletype,tasktypeCode);
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}
//监察笔录回显
function showJcbl(fileTypeEnumName,moreCheckFiletype,tasktypeCode){
    $.ajax({
	  url: "getJcblFile.json?applyId="+applyId+"&fileTypeEnumName="+fileTypeEnumName+"&moreCheckFiletype="+moreCheckFiletype+"&tasktypeCode="+tasktypeCode,
	  success:function(data){
	      if(data.jcjlFileMap!=null){
	      	array = data.jcjlFileMap;
	      	var nameString="";
	      	for(var i = 0; i < array.length; i++){
	      		nameString += array[i].filename +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"<a class='o_btn btn_grey opm' onclick='review(this)' id='"+array[i].id+","+array[i].filesize1+"' >预览</a>&nbsp;&nbsp;"
	                         	+"<a class='o_btn btn_orange opm' onclick=download2('"+array[i].id+"')>下载</a>&nbsp;&nbsp;"
	                         	+"<a class='o_btn btn_blue opm' onclick=deleteJcbl('"+array[i].id+"','"+array[i].fileTypeEnumName+"','"+array[i].moreCheckFiletype+"','"+array[i].tasktypeCode+"')>删除</a>&nbsp;";
	            if(array[i].type == "1"){
	            	nameString += "<a class='o_btn btn_grey opm' onclick=editMoreCheck('"+array[i].tasktypeCode+"','"+array[i].id+"')>修改</a>&nbsp;";
	            }
				nameString	+= "<br/><br/>";
	      	}
	      	$('#filename_'+fileTypeEnumName).html(nameString);
	      }else{
	          $('#filename_'+fileTypeEnumName).html("");
	      }
	   	}
	});
}
//修改多次检查的检查情况
function editMoreCheck(taskTypeCode,fileId){
	$("#taskTypeCode").val(taskTypeCode);
	$("#fileId").val(fileId);
	$.ajax({
				url: "getJcqk.json?fileId="+fileId,
				success:function(data){
					$("#jcqk").val(data.jcqk);
				}
			});
	$('#editWin').window('open');
	$('#editWin').window('center');
}
//通用（生成多次检查监察笔录）
function buildJcbl(){
	var jcqk = $("#jcqk").html();
	if (jcqk == ""){
		alert("请先填写监察情况");
		return;
	}
	var taskTypeCode = "";
    var hasjcjlFileM=false;//是否有监察笔录
    var fileTypeEnumName="";//附件类型枚举名称
    var moreCheckFiletype="";//多次检查附件类型枚举名称
    //开始填写前先保存
    $('#blForm').attr('action','saveZxzrw_zxPage.json');
	$('#blForm').ajaxSubmit(function(data){
   		if(data.state){
				var jcmbId=$('#jcmbId').combobox("getValue");
		        fileTypeEnumName='ZXXDZRWJCJL';
		        taskTypeCode='15';
		    	var filenameTEXT=$('#filename_ZXXDZRWJCJL').text().trim();
		        moreCheckFiletype='ZXXDZRWMOREJCBL';
		        if(filenameTEXT!=''){
		             hasjcjlFileM=true;
		        }
		    }
		    if(hasjcjlFileM){
		    		var fileId = $("#fileId").val();//监察笔录已存在，修改现场监察情况
		    		$.ajax({
						url: "buildJcbl.json?jcmbId="+jcmbId+"&applyId="+applyId+"&taskType="+taskTypeCode + "&jcqk=" + encodeURIComponent(jcqk) + "&fileid=" + fileId,
						success:function(data){
						    if(data.state){
						    	$('#editWin').window('close');
						        showJcbl(fileTypeEnumName,moreCheckFiletype,taskTypeCode);
							}
						}
					});
		    }else{
		        alert("请先生成监察笔录");
		    }
		});
}
</script>