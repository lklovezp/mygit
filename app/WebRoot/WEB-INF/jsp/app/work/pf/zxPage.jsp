<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>任务管理——任务准备</title>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
	<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <!--时间插件 my97-->
    <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
	<script type="text/javascript" src="${common}/All.js"></script>
    <!--easyui-->
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
    <!-- 任务管理 css-->
    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
</head>
<body style="background-color: #ffffff;height:100%;">
<div class="dataDiv" style="width:100%;height:100%;min-width: 1020px; margin:6px auto 0px;">
        <input type="hidden" id="lx" name="lx" value="${lx}" />
        <input type="hidden" id="sy" name="sy" value="${sy}" />
    <div id="head" class="task_step">
        <ul class="curframe  clearfix">
            <li class="for_cur">
            	<a id="zb" title="准备">任务准备</a>
            </li>
            <li>
            	<a id="bl" title="办理">任务办理</a>
            </li>
            <li>
                <a id="bg" title="报告">整理报告</a>
            </li>
        </ul>
    </div>
    <div class="frame mt25" id="content" height="100%">
        <iframe id="fame" frameborder="0" width="100%" height="100%" src="commworkzxZB.htm?applyId=${applyId}&taskId=${taskId}" name="lookupframe">

        </iframe>
    </div>
</div>
</body>
</html>
<script>
$(document).ready(function() {
	var hh=$(window).height() - $("#head").outerHeight()-15;
	$("#content").height(hh);
});
function changeTitle(name){
	if(name=="1"){
		$("#bl").parent().addClass("for_cur").siblings().removeClass("for_cur"); // 给当前链接父级添加 类“cur”
		showBlPage("办理");
	}
	if(name=="2"){
		$("#bg").parent().addClass("for_cur").siblings().removeClass("for_cur"); // 给当前链接父级添加 类“cur”
		showBGPage("报告");
	}
	
}
$(function(){
    $(".curframe a").click(function(){
    	var curThis=$(this)
    	var title=$(this).attr("title");
    	if (title == "准备") {
			//createTab(, title);
			 $(this).parent().addClass("for_cur").siblings().removeClass("for_cur");  // 给当前链接父级添加 类“cur”
		     $("#fame").attr("src",'commworkzxZB.htm?applyId=${applyId}&taskId=${taskId}'); 
		}
		if (title == "办理") {
			 //先校验准备完毕
			 /*
			 $(this).parent().addClass("for_cur").siblings().removeClass("for_cur"); // 给当前链接父级添加 类“cur”
			 $("#fame").attr("src",'commworkzxBL.htm?applyId=${applyId}&taskId=${taskId}');
			 */
			 /**/
		    $.post('checkBlZB.json', {
				applyId : '${applyId}'
			}, function(json) {
				if (json.state) {
					//跳到办理
					//showBlPage("办理");
					 // 给当前链接父级添加 类“cur”
					 
					 curThis.parent().addClass("for_cur").siblings().removeClass("for_cur");
					showBlPage("办理");
				} else {
					
					//提示信息
					alert(json.msg);
					//跳到准备
					//curThis.parent().addClass("for_cur").siblings().removeClass("for_cur");  // 给当前链接父级添加 类“cur”
				     $("#fame").attr("src",'commworkzxZB.htm?applyId=${applyId}&taskId=${taskId}');
				}
			}, 'json');
			
		}
		if (title == "报告") {
		    //先校验准备完毕
		    $.post('checkBlZB.json', {
				applyId : '${applyId}'
			}, function(json) {
				if (json.state) {
					//再校验办理完毕
				    $.post('checkBlBL.json', {
						applyId : '${applyId}'
					}, function(json) {
						if (json.state) {
							//跳到报告
							curThis.parent().addClass("for_cur").siblings().removeClass("for_cur");  // 给当前链接父级添加 类“cur”
							showBGPage("报告");
						} else {
							//提示信息
							alert(json.msg);
							//跳到办理
							//changeSelect("办理");
							$('#bl').parent().addClass("for_cur").siblings().removeClass("for_cur");  // 给当前链接父级添加 类“cur”
							showBlPage("办理");
						}
					}, 'json');
				} else {
					//提示信息
					alert(json.msg);
					//跳到准备
					//curThis.parent().addClass("for_cur").siblings().removeClass("for_cur");  // 给当前链接父级添加 类“cur”
				     $("#fame").attr("src",'commworkzxZB.htm?applyId=${applyId}&taskId=${taskId}'); 
				}
			}, 'json');
		    
		}
       // $(this).parent().addClass("for_cur").siblings().removeClass("for_cur"); // 给当前链接父级添加 类“cur”
       // $("#fame").attr("src",href);  // 设定当前框架iframe 的地址为 该链接地址
    })
});
//验证办理页面跳转【1、专项；2、其他】
function showBlPage(title){
	$.post('showBlPage.json', {
		applyId : '${applyId}'
	}, function(json) {
		if (json.state) {
			//跳到专项
			//createTab('zxworkzxBL.htm?applyId=${applyId}&taskId=${taskId}', title);
			// $(this).parent().addClass("for_cur").siblings().removeClass("for_cur"); // 给当前链接父级添加 类“cur”
		    $(this).parent().addClass("for_cur").siblings().removeClass("for_cur");
			$("#fame").attr("src",'zxworkzxBL.htm?applyId=${applyId}&taskId=${taskId}'); 
		} else {
			//跳到一般
			//createTab('commworkzxBL.htm?applyId=${applyId}&taskId=${taskId}', title);
			$(this).parent().addClass("for_cur").siblings().removeClass("for_cur"); // 给当前链接父级添加 类“cur”
		    $("#fame").attr("src",'commworkzxBL.htm?applyId=${applyId}&taskId=${taskId}');
			
		}
	}, 'json');
}
//验证报告页面跳转【1、专项；2、其他】
function showBGPage(title){
	$.post('showBlPage.json', {
		applyId : '${applyId}'
	}, function(json) {
		if (json.state) {
			//跳到专项
			//createTab('zxworkzxBG.htm?applyId=${applyId}&taskId=${taskId}', title);
			$("#fame").attr("src",'zxworkzxBG.htm?applyId=${applyId}&taskId=${taskId}');
		} else {
			//跳到一般
			//createTab('commworkzxBG.htm?applyId=${applyId}&taskId=${taskId}', title);
			$("#fame").attr("src",'commworkzxBG.htm?applyId=${applyId}&taskId=${taskId}');
		}
	}, 'json');
}
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
function closeLayer(){
	//showBlPage("办理")
	layer.closeAll('iframe');
}
function fHeight(){
    var ulHeight=$("ul.lookupinfo_a").innerHeight();
    var frameHeight=$(window).innerHeight()-ulHeight-70;
    $(".frame").height(frameHeight);
}
function toDbList(){
	var lx=$('#lx').val();
	var sy=$('#sy').val();
	if($('#sy').val()=='1'){
  	  parent.closeLayerIframe(); 
     }else{
    	 if(lx=="1"){
    		 parent.closeLayer();	
    		}else if(lx=="2"){
    			parent.closeLayer();
    		}else if(lx=="3"){
    			parent.closeLayer();
    		}else if(lx=="4"){
    			parent.closeLayer();
    		}
     }
	
	
}
function closeLayerIframe(){
	layer.closeAll('iframe');
}
fHeight();
$(window).resize(function(){
    fHeight();
});
</script>
