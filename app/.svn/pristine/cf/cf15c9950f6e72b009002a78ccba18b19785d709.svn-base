<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>home页</title>
<!-- jquery -->
<link href="${app}/css/resetCSS.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/home.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${jquery}/setLayout.js"></script>
<!--layer-->
<script type="text/javascript" src="${layer}/layer.js"></script>
</head>
<body>
<div class="home_l">
  <div class="headThumb"> <span><img src="${app}/images/home/headThumb.png" /></span>
    <p><a href="#" class="cp" id="changePw">修改密码</a></p>
  </div>
  <ul class="info">
    <li>姓名：${name}</li>
    <li>部门：${orgname}</li>
    <li>职位：${postion}</li>
    <li>执法证：${zfzh}</li>
  </ul>
</div>
<div class="home_r">
  <ul>
    <li>
      <div class="left zfjc"><span><img src="${app}/images/home/home_icon1.png" /></span><br />
        <h>执法检查</h></div>
      <div class="right">  
        <div class="list">
          <p class="detial"><span><img src="${app}/images/home/home_icon11.png" /></span><br />
            <i>待办任务<a class="a1" href="#" onclick="dkdbrwList('ZFJCDB')"><font color="#ff0600">${rwCount}</font></a></i></p>
        </div>
        <div class="list">
          <p class="detial"><span><img src="${app}/images/home/home_icon12.png" /></span><br />
            <i>即将超期<a class="a1" href="#" onclick="dkdbrwList('ZFJC')"><font color="#ff0600">${rwCount}</font></a></i></p>
        </div>
        <div class="list">
          <p class="detial"><span><img src="${app}/images/home/home_icon13.png" /></span><br />
            <i>已超期<a class="a1" href="#" onclick="dkdbrwList('ZFJCYQ')"><font color="#ff0600">${yqCount}</font></a></i></p>
        </div>   
      </div>
    </li>
    <li>
      <div class="left xfts"><span><img src="${app}/images/home/home_icon2.png" /></span><br /><h>信访投诉</h></div>
      <div class="right">
        <div class="list">
          <p class="detial"><span><img src="${app}/images/home/home_icon11.png" /></span><br />
            <i>待办任务<a class="a1" href="#" onclick="dkdbrwList('XFTSDB')"><font color="#ff0600">${xftsdb}</font></a></i></p>
        </div>
        <div class="list">
          <p class="detial"><span><img src="${app}/images/home/home_icon12.png" /></span><br />
            <i>即将超期<a class="a1" href="#" onclick="dkdbrwList('XFTS')"><font color="#ff0600">${xftsdb}</font></a></i></p>
        </div>
        <div class="list">
          <p class="detial"><span><img src="${app}/images/home/home_icon13.png" /></span><br />
            <i>已超期<a class="a1" href="#" onclick="dkdbrwList('XFTSYQ')"><font color="#ff0600">${xftsdb}</font></a></i></p>
        </div>
      </div>
    </li>
    <li>
      <div class="left rcbg"><span><img src="${app}/images/home/home_icon3.png" /></span><br /><h>日常办公</h></div>
      <div class="right">
        <div class="list">
          <p class="detial"><span><img src="${app}/images/home/home_icon11.png" /></span><br />
            <i>待办任务<a class="a1" href="#"  onclick="dkdbrwList('RCBGDB')" ><font color="#ff0600">${rcbgdb}</font></a></i></p>
        </div>
        <div class="list">
          <p class="detial"><span><img src="${app}/images/home/home_icon12.png" /></span><br />
            <i>即将超期<a class="a1" href="#" onclick="dkdbrwList('RCBG')"><font color="#ff0600">${rcbgbj}</font></a></i></p>
        </div>
        <div class="list">
          <p class="detial"><span><img src="${app}/images/home/home_icon13.png" /></span><br />
            <i>已超期<a class="a1" href="#" onclick="dkdbrwList('RCBGYQ')"><font color="#ff0600">${rcbgdb}</font></a></i></p>
        </div>
        <div class="list">
          <p class="detial"><span><img src="${app}/images/home/home_icon14.png" /></span><br />
            <i>未读会商<a href="#"  onclick="dkdbrwList('WDHS')"><font color="#ff0600">${hsCount}</font></a></i>
          </p>
        </div>
      </div>
    </li>
  </ul>
</div>
<!--message-->
</body>
</html>
<script type="text/javascript" language="javascript">
$(document).ready(function(){
	$(".home_r li").each(function(){
		var listLth=$(this).find(".list").length;
		var listW=$(".list").width();
		var lW=$(".left").width();
		$(this).width(lW+listLth*listW+5);
    });
});

$("#changePw").click(function(){
	var index=layer.open({
       type:2,
       title: false,
       area:['600px','450px'],
       shade:[0.5, '#000000'],
       maxmin:false,
       content:'editPas.htm'
     });
});

//打开待办任务
function dkdbrwList(curobj){
	if("WDHS" == curobj){
		window.location.href="recConsultation.htm";
	}else if("ZFJCDB" == curobj){
		window.location.href="dbrwList.htm?lx="+"ZFJCDB";
	}else if("ZFJC" == curobj){
		window.location.href="dbrwList.htm?lx="+"ZFJC";
	}else if("ZFJCYQ" == curobj){
		window.location.href="dbrwList.htm?lx="+"ZFJCYQ";
	}else if("XFTSDB" == curobj){
		window.location.href="xftsdbRwList.htm?lx="+"XFTSDB";
	}else if("XFTS" == curobj){
		window.location.href="dbrwList.htm?lx="+"XFTS";
	}else if("XFTSYQ" == curobj){
		window.location.href="dbrwList.htm?lx="+"XFTSYQ";
	}else if("RCBGDB" == curobj){
		window.location.href="rcbgdbRwList.htm?lx="+"RCBGDB";
	}else if("RCBG" == curobj){
		window.location.href="dbrwList.htm?lx="+"RCBG";
	}else if("RCBGYQ" == curobj){
		window.location.href="dbrwList.htm?lx="+"RCBGYQ";
	}
}
</script>