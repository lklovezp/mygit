<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>服务业信息详情</title>
  <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <!-- 任务管理 css-->
    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
     <style>
     ul.lookupinfo_a {
    padding-top: 0px;
    border-bottom: 1px solid #00a2d9;
    }
    </style>
	</head>

	<body>
		<ul class="lookupinfo_a clearfix">
        <li class="cur"><a href="lawobjDetail.htm?id=${lawobj.id}" target="lookupframe" hidefocus="true" value="jb">基本信息</a></li>
        <li><a href="lawHistory.htm?id=${lawobj.id}" target="lookupframe" hidefocus="true" value="zf">执法历史记录</a></li>
        <li><a href="xxcx_hpxxList.htm?pid=${lawobj.id}" target="lookupframe" hidefocus="true" value="hp">环评信息</a></li>
        <li><a href="xxcx_complaintList.htm?lawobjid=${lawobj.id}"  target="lookupframe" hidefocus="true" value="ts">投诉信息</a></li>
    </ul>
    <div class="frame mt25" style="width:100%;border-bottom: 0px solid #d4d4d4;margin: auto;">
        <iframe id="fame" frameborder="0" width="100%" height="100%" src="lawobjDetail.htm?id=${lawobj.id}" name="lookupframe">

        </iframe>
    </div>
	</body>
</html>
<script type="text/javascript">
$(function(){
    $(".lookupinfo_a a").click(function(){
    	
        $(this).parent().addClass("cur").siblings().removeClass("cur");// 给当前链接父级添加 类“cur”
        $("#fame").attr("src",$(this).attr("href"));  // 设定当前框架iframe 的地址为 该链接地址
    });
    
});
function fHeight(){
    var ulHeight=$("ul.lookupinfo_a").innerHeight();
    var frameHeight=$(window).innerHeight()-ulHeight-25;
    $(".frame").height(frameHeight);
}
fHeight();
$(window).resize(function(){
    fHeight();
});
</script>