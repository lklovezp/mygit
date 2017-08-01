<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
	文件管理系统
</title>
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>

</head>

<body>
<div class="top">
  <div class="menu">
    <div class="m1">
        <a href="#" id="help" class="help">帮助</a>丨<a  href="j_spring_security_logout" class="logOff" id="logout">退出</a>
    </div>
    <div class="m2">
        <span class="rightB"></span>
        <span class="txt"><font color="#ffffff">${name}，</font>欢迎您登录</span>
        <span class="leftB"></span>
    </div>
    <iframe name="download" id="download" src="" style="display: none"></iframe>
  </div>
</div>
<!--.top-->
<div class="main" id="main">
  <div class="l_part" id="l_part">
       <p onclick="loadURL(this)" ref="sfwList.htm"  class="cur"><a>档案管理</a></p>
  </div>
  <div class="content" id="content">
      <iframe src="sfwList.htm" frameborder="0" width="100%" height="100%" id="conIframe" allowtransparency="true"></iframe>
  </div>
</div>
<!--setMenu.js引入写在body体的最后-->
<script type="text/javascript">


/*===初始化布局===*/
 function initH(){
	 var winH=$(window).height();
	 var h=winH-100;
	 $("#main").css("height",h+"px");
	 $("#l_part").css("height",h+"px");
	 $("#content").css("height",h+"px");
 }
 initH();
 $(window).resize(function(){initH()});


/*===左侧菜单切换对应页面链接地址===*/
function loadURL(t){
	var lpart=$("#l_part");
	lpart.find("p").removeClass("cur");
	$(t).addClass("cur");
	//跳转地址
	var newUrl=$(t).attr("ref");
	var iframeObj=$("#conIframe");
	iframeObj.attr("src",newUrl);
}
</script>
</body>
</html>