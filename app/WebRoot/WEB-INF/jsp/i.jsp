<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>兵团环境监察移动执法系统</title>
<!-- jquery -->
<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${jquery}/setLayout.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="${app}/images/favicon.ico"><!-- 链接前的图标 -->
<link href="${app}/css/resetCSS.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/layout.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="new_top">
	<div id="logo">
    </div>
    <div class="nav">
      <div class="nav_c" id="nav_c">
         <div class="nav_con clearfix" id="nav_con">
            <ul> 
            </ul>
         </div>
         <!--.nav_con-->
      </div>
      <!--.nav_c-->
      <div class="quit">
      	<a  href="j_spring_security_logout" class="img" id="logout"></a><a  href="j_spring_security_logout" class="txt" id="logout">退出</a>
      </div>
    </div>
    <!--.nav-->
</div>
<!--.top-->

<div class="main" id="main">
  <div class="l_part" id="l_part"></div>
  <div class="content" id="content">
  <div class="breadClumbs"></div>
    <iframe src="home.htm" frameborder="0" width="100%" height=""  id="conIframe" allowtransparency="true"></iframe>
  </div>
</div>
<script type="text/javascript" src="${app}/setMenu.js"></script>
<script type="text/javascript" language="javascript">
//初始化菜单
//初始化nav及左侧菜单
//menuJSON对象说明：默认初始时呈现首页。如果需改变初始页面，需要在相应的最低级（也就是叶子节点，例如如果二级有三级页面，需要在三级菜单对应的数据）上面添加属性"cur":true
var menuJSON=${map};
//初始化菜单
initLev1(menuJSON);
setPositon();
initLev2(menuJSON);
$(window).bind("resize",function(){
	setPositon();
});
$(document).ready(function(){
	//点击帮助下载系统说明
	$("#help").click(function(){
		$.ajax({
			url: "isExistsHelpDoc.json",
			success:function(data){
				if (data.state){
					$('#download').attr('src','help.htm');
				} else {
					alert(data.msg);
				}
			}
		});
	});
});
function layerIframe(types,href,title,width,height){
	  layer.open({
	  	  type: types,
	  	  title: title,
	  	  maxmin: false,
	  	  shadeClose: false, //点击遮罩关闭层
	  	  area : [width+'px' , height+'px'],
	  	  content: href
	  	  });
	  
}
function closeLayerIframe(){
	homeChild.window.closeLayerIframe1();
	layer.closeAll('iframe');
}
function closeLayer(){
	layer.closeAll('iframe');
}
function logout(){
	document.getElementById("logout").click();
}
</script>
</body>
</html>