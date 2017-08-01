<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>home页</title>
<!-- jquery -->
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${common}/All.js"></script>
<link href="${app}/css/home.css" rel="stylesheet" type="text/css" />
</head>

<body style="background:#ebebeb">
<div class="homePart toDo">
  <form action="dbQuery.json" id="dbForm" method="post">
    <div class="con">
       <h2 class="tit"><p>我的待办</p></h2>
       <div class="content" id = "db">
         
       </div>
    </div>
   </form>
</div>
<!--toDo-->
<div class="homePart consultation">
</div>
<!--consultation-->
<div class="homePart done">
    <div class="con">
       <h2 class="tit"><p>我的已办</p></h2>
       <div class="content">
          <ul>
            <li>
              <div class="left zfjc"><a>执法检查</a></div>
              <div class="right">
              </div>
            </li>
            <li>
              <div class="left xfts"><a>信访投诉</a></div>
              <div class="right">
              </div>
            </li>
            <li>
              <div class="left rcbg"><a>日常办公</a></div>
              <div class="right">
              </div>
            </li>
          </ul>
       </div>
    </div>
    
</div>
<!--done-->
<div class="homePart message">
    <div class="con">
       <h2 class="tit"><p>信息助手</p></h2>
       <div class="content">
         <ul>
          <li class="wdhs">
          </li>
          <li class="xdrw" onclick="info('1')">
          </li>
          <li class="bydl">
            <a href="#" onclick="loginDetail('true')">
              <span>本月登录次数</span>
              <em>${theMonthLogTimes}</em>
            </a>
          </li>
          <li class="zdl">
            <a href="#" onclick="loginDetail('flase')">
              <span>总登录次数</span>
              <em>${allLogTimes}</em>
            </a>
          </li>
         </ul>
       </div>
    </div>
</div>
<!--message-->
</body>
</html>
<script type="text/javascript" language="javascript">

//登录详情
function loginDetail(bythemonth){
	var href = 'logTimesDetails.htm?bythemonth='+bythemonth; 
  	var width=screen.width * 0.9;
  	var height=screen.height * 0.8-50;
  	parent.layerIframe(2,href,"登录详情",width,height);
		//All.ShowModalWin('logTimesDetails.htm?bythemonth='+bythemonth, '登录详情');
}

function closeLayerIframe1(){
	$('#dbForm').submit();
	$('#hsForm').submit();
	//parent.closeLayerIframe();
}

</script>