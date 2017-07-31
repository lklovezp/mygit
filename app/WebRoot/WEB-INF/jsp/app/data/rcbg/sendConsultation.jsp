<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<!--jQuery-->
<script type="text/javascript" src="${app}/js.js"></script>
<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css"	type="text/css" />
<link rel="stylesheet" href="${colorbox}/colorbox.css" type="text/css">
<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
<link href="${app}/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${common}/All.js"></script>
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/attachFileList.css" rel="stylesheet" type="text/css" />
<!-- ueditor -->
<script type="text/javascript" charset="utf-8" src="${ueditor}/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ueditor}/ueditor.all.min.js"> </script>
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/attachFileList.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.addFile{ display:inline-block; height:24px; line-height:24px; padding-left:32px; background:url(${app}/images/addFile.png) left top no-repeat; color:#043e7d;}
.addFile:hover{color:#043e7d;}
.fileList{ padding-top:5px; }
.fileList ul li{ height:30px; line-height:30px;}
.fileList ul li span{ margin-right:8px;}
.a_btn_blue{ display:inline-block; width:88px; height:30px; line-height:30px; text-align:center; background:#00a2d9; color:#ffffff; position:absolute; right:110px; top:45px; cursor:pointer; border-radius:5px;}
.a_btn_blue:hover{ color:#ffffff; text-decoration:none;}
</style>
</head>

<body style="padding-bottom: 75px">
<div class="h1_1">发送会商</div>
<form id="myform" method="post" action="">
<input type="hidden" id="mailId" name="id" value=""/>
  <table width="90%" border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
     <tr height="58">
     	<td align="right" width="58">
			<font color="red"> * </font>收件人:&nbsp;&nbsp;
		</td>
		<td align="left">
			<input type="hidden" id="pfrId" name="recList" value=""/>
			<input type="text" class="y-text easyui-validatebox" style="width:85%;" readonly="readonly" data-options="required:true" id="pfr" name=""/>
			<c:if test="${isZd == 0}">
				<a href="#" style="top:79px;right:296px;" class="a_btn_blue" id="selectpfg" onClick="javascript:selectpfg()">按分组选择人员</a>
			</c:if>
			<a href="#" style="top:79px;right:198px;" class="a_btn_blue" id="selectpfr" onClick="javascript:selectpfr()">按单位选择人员</a>
     	</td>
     </tr>
     <tr height="58">
       <td align="right">抄送:&nbsp;&nbsp;</td>
       <td align="left">
	       <input type="hidden" id="csrId" name="chaoSong" value=""/>
	       <input type="text" class="y-text" style="width:85%;" readonly="readonly" id="csr" name=""/>
			<c:if test="${isZd == 0}">
				<a href="#" style="top:137px;right:296px;" class="a_btn_blue" id="selectcsrg" onClick="javascript:selectcsrg()">按分组选择人员</a>
			</c:if>
      		<a href="#" style="top:137px;right:198px;" class="a_btn_blue" id="selectcsrp" onClick="javascript:selectcsrp()">按单位选择人员</a>
      </td>
     </tr>
     <tr height="58">
       <td align="right"><font color="red"> * </font>主题:&nbsp;&nbsp;</td>
       <td align="left"><input id="topic" name="topic" type="text" class="y-text easyui-validatebox" data-options="required:true" style="width:85%;" /></td>
     </tr>
     <tr>
       <td align="right">&nbsp;</td>
       <td align="left" style="padding-bottom:12px;">
          <div><a class="addFile" href="javascript:void(0)" id="fileupload">添加附件</a></div>
          <div class="fileList" id="fuJianList">
          </div>
       </td>
     </tr>
     <tr>
       <td align="right" valign="top">内容&nbsp;&nbsp;</td>
       <td align="left">
          <div style="width:87%; height:370px; border:0px solid #acacac;" >
            <script id="editor" type="text/plain" style="height:300px; width:100%;"></script>
          </div>
       </td>
     </tr>
  </table>
  <div class="rb_btn_fix">
  <input type="submit" class="queryBlue" value="发　送"/>
</div>
</form>

</body>
</html>
<Script type="text/javascript">

$(document).ready(function(){
	$("#J-from-reset").click(function(){
		   $("#myform").form("reset");
	});	
//实例化编辑器
//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
  var ue = UE.getEditor('editor');
//提交时验证
  $("#myform").validate({
  		errorClass:"error",
  		submitHandler : function(form) {
  			var note=UE.getEditor('editor').getContent();
  			//if(checkSubmitFlg ==true){return false}
  			if ($("#myform").form("validate")){
  				$('#save').attr("disabled","true");
  	  			//var checkSubmitFlg = false;
  	  			$('#save').attr("value","发送中...");
  				$(form).ajaxSubmit({
  					type : "post",
  					url : "saveSendConsultation.json",
  					data: {
  		                "note": note
        			},
  					success : function(data) {
  						window.location.href="sendSuccess.htm";
  					}
  				});
  			}
  			
  		}
  });
	    
});
/** */
//设置选择派发人
function setUserInfoPfr(id,name) {
	$("#pfrId").val(id);
	//$("#pfrId").attr("value",id);
	$("#pfr").val(name);
	jQuery().colorbox.close();
}
function setUserInfoCsr(id,name) {
	$("#csrId").val(id);
	$("#csr").val(name);
	jQuery().colorbox.close();
}
    //选择收件人按部门
function selectpfr(){
	var org = 0;
	$("#selectpfr").colorbox({
		iframe:true,
		width:"300", 
		height:"390",
		href:"queryUserTree.htm?methodname=setUserInfoPfr&multi=true&ids="+$("#pfrId").val()+"&org="+org
	});
}

    //选择收件人按分组
function selectpfg(){
	var org = 1;
	$("#selectpfg").colorbox({
		iframe:true,
		width:"300", 
		height:"390",
		href:"queryUserTree.htm?methodname=setUserInfoPfr&multi=true&ids="+$("#pfrId").val()+"&org="+org
	});
}

 //选择收件人按部门
function selectcsrp(){
	var org = 0;
	$("#selectcsrp").colorbox({
		iframe:true,
		width:"300", 
		height:"390",
		href:"queryUserTree.htm?methodname=setUserInfoCsr&multi=true&ids="+$("#csrId").val()+"&org="+org
	});
}

    //选择收件人按分组
function selectcsrg(){
	var org = 1;
	$("#selectcsrg").colorbox({
		iframe:true,
		width:"300", 
		height:"390",
		href:"queryUserTree.htm?methodname=setUserInfoCsr&multi=true&ids="+$("#csrId").val()+"&org="+org
	});
}

    //添加附件
	$("#fileupload").click(function(){
		$.ajax({
			  url: "saveMailForm.json?id="+$("#mailId").val(),
			  success:function(data){
				  $("#mailId").val(data.mailId);
				  $.fn.colorbox({
						iframe:true, width:"610", height:"380",
						href:'uploadSendmailFuJian.htm?mailId='+data.mailId
					});
			   }
			});
		 
		
	});
    //刷新附件列表
    function refFuJian(str){
    	var array = new Array();
        $.ajax({
    	  url: "getFileList.json?mailId="+str,
    	  success:function(data){
    		  if(data.listMap!=null){
    			  array = data.listMap;
    			  
    			  var nameString="";
    			  for(var i = 0; i < array.length; i++){
    				  nameString += array[i].name +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"<a class='b-link' onclick=deleteMailFuJian('"+array[i].id+"')>删除</a>&nbsp;<br/>"; 
    			  }
    			  $('#fuJianList').html(nameString);
    		  }else{
    			  $('#fuJianList').html("");
    	      }
    	   	}
    	});
    }
  /*删除会商附件
    function deleteMailFuJian(id){
    	$.messager.confirm('操作', '确定要删除吗？', function(r){
    		if (r){
    			alert(id);
    			$.ajax({
    				url: "removeMailFuJian.json?id="+id,
    				success:function(data){
    					alert(data.msg);
    					 refFuJian($("#mailId").val());
    				}
    			});
    		}
    	});
    }*/
    function deleteMailFuJian(id){
    	var index=layer.confirm('确定删除吗？', {
         	icon: 3, 
            title:'删除任务'
         }, function(index){
        	 $.ajax( {
    				url : "removeMailFuJian.json?id="+id,
    				success : function(data) {
    					alert(data.msg);
    					//var reloadtable= $(obj).attr("reloadtable");//自定义属性采用此方式获得 
    					//$('#'+reloadtable).datagrid('reload');
    					refFuJian($("#mailId").val());
    					refresh();
    				}
    			});
            layer.close(index);
         },function(index){
            layer.close(index);
         }
        );
    }
   
</Script>