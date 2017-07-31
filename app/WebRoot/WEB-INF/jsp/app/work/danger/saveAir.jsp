<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>水环境保护目标分布</title>
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
<script type="text/javascript" src="${common}/All.js"></script>
</head>
<body>
	<div class="headTitle" style="font-size:16px; padding-top:20px;">大气环境基本状况</div>
	<form id="queryForm" action="" method="post">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="formtable">
			<input type="hidden" value="${airForm.id}" id="id" name="id">
			<input type="hidden" value="${airForm.pid}" id="pid" name="pid">
			<tr>
				<th width="180" colspan="2"><em class="mark">*</em>企业所处区域大气环境质量功能类别 ：</th>
				<td>
				<input   style="width:30px;"  type="radio" id="type1" name="type" value="1"/>
				<span>一级</span>
				<input   style="width:30px;"  type="radio" id="type2" name="type" value="2"/>
				<span>二级</span>
				<input   style="width:30px;"  type="radio" id="type3" name="type" value="3"/>
				<span>三级</span>
				</td>
			</tr>
		</table>
		<div class="t-c" style="margin-top:25px">
			<span class="y-btn"><input id="save" type="submit" value="保存" />
			</span> <span class="y-btn"><input id="reset" type="reset" value="重置" />
			</span>
		</div>
	</form>
	<script type="text/javascript">
	function initData(){
		if($('#type1').val()=='${airForm.type}'){
			$('#type1').attr("checked",'checked');	
		}else if($('#type2').val()=='${airForm.type}'){
			$('#type2').attr("checked",'checked');
		}else if($('#type3').val()=='${airForm.type}'){
			$('#type3').attr("checked",'checked');
		}
	}
	$(document).ready(function() {
			if('${airForm.id}'!=""){
				initData();
			}
			//提交表单
			$("#queryForm").validate({
				
				errorClass : "error",
				submitHandler : function(form) {
					if ($("#queryForm").form("validate")){
						
					    // return;
						$(form).ajaxSubmit( {
							type : "post",
							url : "saveAirForm.json?",
							success : function(data) {
								alert(data.msg);
								//self.close();
								parent.refAir();
								
							}
						});
					}
				}
			});

		});
	</script>
</body>
</html>
