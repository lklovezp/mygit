<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>回复意见</title>
<script type="text/javascript" src="${app}/js.js"></script>
<script type="text/javascript" src="${app}/data.js"></script>
<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css"	type="text/css" />
<script type="text/javascript" src="${poshytip}/jquery.poshytip.min.js"></script>
<link rel="stylesheet" href="${colorbox}/colorbox.css" type="text/css">
<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
<link href="${app}/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${layer}/layer.js"></script>
<script type="text/javascript" src="${common}/All.js"></script>
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body{ font-family:"Microsoft YaHei";}
*{padding:0; margin:0;}
a{}
a.dbtslink,a.addFile{color:#026da2; text-decoration:none; padding-left:5px; font-size:14px; cursor:pointer;}
a.dbtslink:hover, a.addFile:hover{ text-decoration:underline;}
a.addFile{ padding-left:16px; margin-left:5px; background:url(static/app/images/addFile.png) 0px -3px no-repeat;}
a.del{ color:#0088E0; cursor:pointer;}

.formtable, .formtable th, .formtable td{ border-style: solid; border-color:#dddddd;}
</style>
</head>
<body>
	<div class="headTitle" style="font-size:16px; padding-top:20px;"></div>
	<form id="queryForm" action="" method="post">
		<table class="formtable" width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" >
			<input type="hidden" value="${mailId}" id="mailId" name="mailId">
			<input type="hidden" value="${recMailId}" id="recMailId" name="recMailId">
			<input type="hidden" value="" id="fuJianId" name="fuJianId">
			<input type="hidden" value="" id="yjId" name="${yjId}">
			<tr>
				<th width="60" style="font-size:14px;background-color:#edfaff;" ></th>
				<td>
				   <select name="select" id="select" onclick="selectOpt()">   
					  <option style="width:200px">已阅</option>   
					  <option style="width:200px">已收到</option>  
					  <option style="width:200px">已阅无意见</option> 
				   </select>
				</td>
			</tr>
			<tr>
			<th width="60px"  style="font-size:14px;background-color:#edfaff; "  >附    件：</th>
			     <td colspan="">
			     	<a id="fileupload" class="addFile" >&nbsp;&nbsp;&nbsp;添加附件</a>
                    <div id="fuJianList" class="fileList">
         
                    </div>
			     </td>
			</tr>
			<tr>
				<th width="60" style="font-size:14px;background-color:#edfaff;" >意    见：</th>
				<td>
				<textarea type="text" class="i-textarea easyui-validatebox"  style="width:460px;height:80px;border-color:#dddddd;" id="yj" name="yiJianContent" ></textarea>
				</td>
			</tr>
			
			
			
		</table>
		<div class="t-c" style="margin-top:25px">
			<input id="save" type="submit" class="queryBlue" value="确定" />
			<input id="cancl" type="button" class="queryOrange" value="取消" />
		</div>
	</form>
	<script type="text/javascript">

		$(document).ready(function() {
			document.getElementById("yj").value=$('#select').val();
			//提交表单
			$("#queryForm").validate({
				
				errorClass : "error",
				submitHandler : function(form) {
					if ($("#queryForm").form("validate")){
						
					    // return;
						$(form).ajaxSubmit( {
							type : "post",
							url : "saveYiJian.json?yjId="+$("#yjId").val(),
							success : function(data) {
								alert(data.msg);
								//parent.Mycheck();
								//window.dialogArguments.Mycheck();
								//self.close();
								var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						        parent.layer.close(index);
								//parent.refEdit();
								
								
							}
						});
					}
				}
			});

		});
		//单选中意见时，自动回显到意见内容里面
		function selectOpt(){
			document.getElementById("yj").value=$('#select').val();
		}
		$('#cancl').click(function(){
			//self.close();
			//window.close();
			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
			parent.layer.close(index);
		});
		 //添加附件
		$("#fileupload").click(function(){
			$.ajax({
				  url: "saveYiJian.json?mailId="+$("#mailId").val()+"&yjId="+$("#yjId").val(),
				  success:function(data){
					  $("#yjId").val(data.yjId);
					  $.fn.colorbox({
							iframe:true, width:"610", height:"380",
							href:'uploadYJFuJian.htm?yjId='+$("#yjId").val()
						});
				   }
				});
			
			
		});	
		 //刷新附件列表
	    function refFuJian(str){
	    	var array = new Array();
	    	//alert(str);
	    	var fjIds="";
	    	$.ajax({
	    	  url: "getFileListByUserId.json?yjId="+str,
	    	  success:function(data){
	    		 
	    		  
	    		  if(data.listMap!=null){
	    			  array = data.listMap;
	    			  
	    			  var nameString="";
	    			  for(var i = 0; i < array.length; i++){
	    				  nameString += array[i].name +"&nbsp;&nbsp;"+"<a class='btslink' onclick=deleteYJFuJian('"+array[i].id+"')>删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"; 
	    				  fjIds += array[i].id+",";
	    			  }
	    			  $('#fuJianList').html(nameString);
	    			  $('#fuJianId').val(fjIds);
	    		  }else{
	    			  $('#fuJianList').html("");
	    	      }
	    		  
	    	   	}
	    	});
	    	
	    }
	  /*删除会商附件
	    function deleteYJFuJian(id){
	    	$.messager.confirm('操作', '确定要删除吗？', function(r){
	    		if (r){
	    			$.ajax({
	    				url: "removeMailFuJian.json?id="+id,
	    				success:function(data){
	    					alert(data.msg);
	    					 refFuJian($("#yjId").val());
	    				}
	    			});
	    		}
	    	});
	    }*/
	  //附件删除
	    function deleteYJFuJian(id) {
	    	var index=layer.confirm('确定删除吗？', {
	         	icon: 3, 
	            title:'删除任务'
	         }, function(index){
	        	 $.ajax( {
	    				url : "removeMailFuJian.json?id="+id,
	    				success : function(data) {
	    					//refresh();
	    					//$('#data').datagrid('reload',{pid:$("input[name='id']").val()});
	    					alert(data.msg);
	    					refFuJian($("#yjId").val());
	    				}
	    			});
	            layer.close(index);
	         },function(index){
	            layer.close(index);
	         }
	        );
	    }
</script>
</body>
</html>
