<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="overflow:hidden;">
	<head>
		<title>兵团环境监察移动执法系统</title>
		<link href="${app}/css/resetCSS.css" rel="stylesheet" type="text/css" />
		<link href="${app}/css/login.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
		<!--表单验证validate.js-->
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<!--[if IE]>
        <style>
        .login_ver {top:10px;filter: progid:DXImageTransform.Microsoft.Matrix(sizingMethod='auto expand', M11=0.707107,M12=-0.707107,, M21=0.707107, M22=0.707107)";}
        </style>
        <![endif]-->
        
	</head>

	<body class="loginBody">
	<h1 class="logo"><img src="${app}/images/login_t.png" /></h1>
	<div class="login">
		<form id="myform" name="myform" method="post" action="j_spring_security_check">
			<div class="wrap">
				<div class="login_con">
					<!--  <div class="login_ver"><span id="verSpan"></span></div>-->
					<ul>
				      <li class="li1 clearfix">
				          <div class="con1 clearfix">
				             <span></span>
				           		 <input type="text" name="j_username" id="j_username" style="ime-mode:disabled;" onpaste="return false"
											class="txt" />
				          </div>
				      </li>
				      <li class="li2 clearfix">
				          <div class="con1 clearfix">
				             <span></span>
				              	<input input type="password" name="j_password" id="j_password" 
										class="txt" />
										
				          </div>
				          <span style="font-size: 15px; color: red; "  >
										${requestScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
				            </span>             
				      </li>
				      <li class="li3">
				        <input type="submit" id="login" class="loginS" value=""/>
				      </li>
				     
				      
				    </ul>
				   <!--  <tr>
						<td height="20" colspan="2" align="center"
							style="font-size: 15px; color: #FFFF00;    ">
							${requestScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
				     
						</td>
					</tr>--> 
				</div>
			</div>
			<div id="QR" name="QR" class="QRdiv">
				<img style="width:100px;height:100px;" src="${app}/images/QR.png"></img>
			</div>
		</form>
	</div>
	</body>
</html>
<script type="text/javascript">
function getVersion(){
	$.ajax({
		url: "getVersion.json",
		success:function(data){
			$("#verSpan").text(data.version);
		}
	});
}

$(document).ready(function(){
	//getVersion();
	$("#myform").validate({
		messages:{
			j_username:'请输入用户名',
			j_password:'请输入密码'
		},
		errorClass: "error",
		submitHandler: function(form){
			if ($("#myform").form("validate")){
				form.submit();
			}
		}
	});
});
</script>