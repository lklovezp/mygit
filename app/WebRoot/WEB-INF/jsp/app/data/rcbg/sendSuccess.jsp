<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>发送成功</title>
		<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
	</head>
<style type="text/css">
*{padding:0; margin:0;}
.sendSuccess{width:423px; height:360px; background:url(static/app/images/sendSuccess.png) no-repeat; position:absolute; z-index:10000;}
.sendSuccess .close{ display:inline-block; width:16px; height:16px; position:absolute; top:78px; right:28px;  cursor:pointer; text-indent:-9999px;}
.sendSuccess .confirm{ display:inline-block; width:397px; height:45px; position:absolute; left:12px; bottom:14px; cursor:pointer; text-indent:-9999px;}
</style>
  <body>
   <div class="sendSuccess" id="sendSuccess">
	<a href="sendConsultation.htm" id="close" class="close">关闭</a>
	<a href="sendConsultation.htm" id="confirm" class="confirm">确定</a>
</div>
<script type="text/javascript">
$(document).ready(function() {
    var sendSuccess=document.getElementById('sendSuccess');
	function setPosition(){
		var left=(document.documentElement.clientWidth-423)/2;
		var top=(document.documentElement.clientHeight-360)/2;
		sendSuccess.style.left=left+'px';
		sendSuccess.style.top=top+'px';
		
	}
	setPosition();
	$(window).bind('resize',setPosition);
		
});

</script>
  </body>
</html>
