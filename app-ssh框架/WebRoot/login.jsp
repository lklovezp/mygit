<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="overflow:hidden;">
	<head>
		<title>文件管理系统</title>
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/login.css" rel="stylesheet" type="text/css" />
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
<h1 class="logo">收发文档案管理系统</h1>
<div class="login">
  <p class="login_msg">${requestScope['SPRING_SECURITY_LAST_EXCEPTION'].message}</p>
  <form id="myForm" name="myForm" method="post" action="j_spring_security_check">
    <ul>
      <li class="li1 clearfix">
          <div class="con1 clearfix">
             <span></span>
             <input type="text" class="txt" name="j_username" id="j_username" style="ime-mode:disabled;" onpaste="return false"/>
          </div>
      </li>
      <li class="li2 clearfix">
          <div class="con1 clearfix">
             <span></span>
             <input type="password" class="txt" id="j_password" name="j_password" value=""/>
          </div>
      </li>
      <li class="li3">
        <input type="submit" class="loginS" id="login" name="login" value=""/>
      </li>
    </ul>
  </form>
</div>
<script type="text/javascript">
 $("#myForm").validate(
{
	  errorClass: "error",
	  submitHandler:function(form){	
	      form.submit();
	  
	  },
	  rules:
	  {  
		 "j_username": { required: true},
		 "j_password": { required: true}, 
	  },
	  messages:{
		 "j_username": { required:"用户名不能为空"},
		 "j_password": { required:"密码不能为空"} 
	  }
 });
</script>
</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
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