<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>任务详情</title>
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
    <script type="text/javascript" src="${layer}/layer.js"></script>
    <!-- 任务管理 css-->
    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
</head>
    <body>
	 <ul class="lookupinfo_a clearfix" style="padding-top: 0px;">
        <li class="cur"><a href="taskDetailJBXX.htm?applyId=${applyId}" target="lookupframe" hidefocus="true" >基本信息</a></li>
        <li><a href="taskDetailRWLZJL.htm?applyId=${applyId}" target="lookupframe" hidefocus="true" >任务流转记录</a></li>
        <li><a href="taskDetailBGXQ.htm?applyId=${applyId}" target="lookupframe" hidefocus="true">报告详情</a></li>
    </ul>
    <div class="frame mt25" style="width:99%;border-bottom: 0px solid #d4d4d4;margin: auto;">
        <iframe id="fame" frameborder="0" width="100%" height="100%" src="taskDetailJBXX.htm?applyId=${applyId}" name="lookupframe">

        </iframe>
    </div>
	</body>
</body>
</html>
<script type="text/javascript">
$(function(){
	//$("#fame").attr("src",taskDetailJBXX.htm?applyId=${applyId});
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