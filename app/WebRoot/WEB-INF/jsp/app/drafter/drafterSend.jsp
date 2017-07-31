<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${common}/All.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<link rel="stylesheet" type="text/css"href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css"href="${easyui}/themes/default/easyui.css">
		<script type="text/javascript"src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<link href="${app }/CSSReset.css" rel="stylesheet" type="text/css" />
		<link href="${app }/common.css" rel="stylesheet" type="text/css" />
		<link href="${app }/easyUIReset.css" rel="stylesheet" type="text/css" />
		<link href="${app }/attachFileList.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
        .a_btn_blue{ display:inline-block; width:81px; height:30px; line-height:30px; text-align:center; background:#00a2d9; color:#ffffff; position:absolute; right:12px; top:14px; cursor:pointer; border-radius:5px;}
        .a_btn_blue:hover{ color:#ffffff; text-decoration:none;}
        </style>
	</HEAD>
	<body>
		<div class="h1_1">${title}</div>
		<form id="myform" name="myform" method="post" action="drafterSave.json">
		<input type="hidden" id="id" name="id" value="${drafterForm.id}" />
		<input type="hidden" id="state" name="state" value="${drafterForm.state}" />
		<div class="divContainer" style="padding-left:10px;width:85%;" >
			<!-- 稿件信息 -->
				<table width="95%" border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
						<tr height="58">
							<td align="right" width="70">
								<font color="red"> * </font>拟稿人：
							</td>
							<td align="right" style="position:relative;">
								<input type="hidden" id="drafterId" name="drafterId" value="${drafterForm.drafterId}" />
								<input class="y-text easyui-validatebox" data-options="required:true" style="width:98%;" type="text" id="drafterName" name="drafterName" value="${drafterForm.drafterName}" readonly/>
								<a href="#" class="a_btn_blue" id="selectDrafter">选择人员</a>
							</td>
						</tr>
						<tr height="58">
							<td align="right">
								<font color="red"> * </font>主题：
							</td>
							<td align="right">
								<input class="y-text easyui-validatebox" data-options="required:true" style="width:98%;" type="text" id="name" name="name" value="${drafterForm.name}" />
							</td>
						</tr>
						<tr height="58">
							<td align="right" >
								内容：
							</td>
							<td align="right" >
								<textarea style="width:98%; height:100px;" class="y-text" id="describe" name="describe" />${drafterForm.describe}</textarea>
							</td>
						</tr>
				</table>
				
				<!-- 稿件附件 -->
				<div class="dataDiv" style="width:95%; margin:16px auto 25px;">
				<div class="mt25" id="annex">
                 <div class="annex_header">
					 <a class="b_link" style="float:right;" onClick="downZipFile()">打包下载</a>
              <a class="b_link" style="float:right;" id="XGFJfileupload" >上传附件</a>
                                稿件
          </div>
          <div class="annex_con" style=" height: 248px;">
              <table id="XGFJdata" fit="true"></table>
          </div>
           </div>
         </div>
         </div>
            <div class="t-c" style="padding-bottom:35px;">
			 <input type="button" class="queryOrange" onclick = "save()" value="保存草稿">
			 <input type="button" class="queryBlue"  onclick = "send()" value="发送">
			</div>
			</form>
			<iframe name="download" id="download" src="" style="display: none"></iframe>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
String.prototype.trim = function()  
 {  
      return this.replace(/(^\s*)|(\s*$)/g, "");  
 }
//设置选择拟稿人
function setUserInfoNgr(id,name) {
	$("#drafterId").val(id);
	$("#drafterName").val(name);
	jQuery().colorbox.close();
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
} */
//删除
function deletefile1(obj){
	var index=layer.confirm('确定删除吗？', {
     	icon: 3, 
        title:'删除任务'
     }, function(index){
    	 $.ajax( {
				url : "removeFile.json?id="+obj.id,
				success : function(data) {
					alert(data.msg);
					var reloadtable= $(obj).attr("reloadtable");//自定义属性采用此方式获得 
					$('#'+reloadtable).datagrid('reload');
					refresh();
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
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
//保存草稿
function save(){	
    var content = $("#describe").val().trim();
    var name = $("#name").val().trim();
    if(name.length > 100)
	{
		alert("稿件主题不能超过100字!");
		return false;
	}else if(content.length > 2000)
	{
		alert("稿件内容不能超过2000字!");
		return false;
	}else{
		$("#state").val(0);
		$("#myform").ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"drafterSave.json",
					success: function(data){
						if(data.state){
							$("#id").val(data.id);
							alert(data.msg);
						}else{
							$.messager.alert('发送稿件:',data.msg);
						}
					}
		});
	}
}
//发送稿件
function send(){	
    var content = $("#describe").val().trim();
    var name = $("#name").val().trim();
    if(name.length > 100)
	{
		alert("稿件主题不能超过100字!");
		return false;
	}else if(content.length > 2000)
	{
		alert("稿件内容不能超过2000字!");
		return false;
	}else{
		$("#state").val(1);
		$("#myform").submit();
	}
}
$(document).ready(function(){
	//选择拟稿人
	$("#selectDrafter").colorbox({iframe:true,width:"300", height:"380",href:"userPubQuery.htm?all=true&notShowZj=false&methodname=setUserInfoNgr&id="+$('#drafterId').val()+"&notShowSys=true"});
	
	$.ajaxSetup({cache:false});
	$("#J-from-reset").click(function(){
	   $("#myform").reset();
	});
   	//表单校验
    $("#myform").validate({
		errorClass: "error",
		submitHandler:function(form){
			if ($("#myform").form("validate")){
				$(form).ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"drafterSave.json",
					success: function(data){
						if(data.state){
							alert(data.msg);
							history.go(0);
							var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
							parent.layer.close(index);
							//self.close();
						}else{
							$.messager.alert('发送稿件:',data.msg);
						}
					}
				});
			}
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
		queryParams:{pid:$("#id").val(),fileType:'GJGLGJFJ',tableId:'XGFJdata'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:50},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:100},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:100},
			{field:'operate',title:'操作', align:'center', halign:'center',width:100}
		]]
	});
	//相关附件上传
	$("#XGFJfileupload").click(function(){
		var fileType = 'GJGLGJFJ';//稿件附件
		var content = $("#describe").val().trim();
	    var name = $("#name").val().trim();
	    if(name.length > 100)
		{
			alert("稿件主题不能超过100字!");
			return false;
		}else if(content.length > 2000)
		{
			alert("稿件内容不能超过2000字!");
			return false;
		}
		$("#state").val(0);
		
		if($("#myform").form("validate")){
				$("#myform").ajaxSubmit({
						type:"post",
						dataType:"json",
						url:"drafterSave.json",
						success: function(data){
							$("#id").val(data.id);
							if(data.state){
								$.colorbox({
									iframe:true, width:"610", height:"380",
									href:'uploadPage.htm?pid='+$("#id").val()+'&fileType='+fileType+'&path=GJFJ&tableId=XGFJdata'
								});
							}else{
								$.messager.alert('发送稿件:',data.msg);
							}
						}
				});
		}else{
			if($("#drafterName").val()==null){
				$.messager.alert('发送稿件:','请填写拟稿人');
				return;
			}else if($("#name").val()==null){
				$.messager.alert('发送稿件:','请填写主题');
				return;
			}
		}
	});
});
</SCRIPT>