<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>环境风险及化学品</title>
		<script type="text/javascript" src="${app}/js.js"></script>
		<script type="text/javascript" src="${app}/data.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery }/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery }/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery }/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui }/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui }/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox }/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui }/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui }/themes/default/easyui.css">
		<link href="${app }/hnjz.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${colorbox }/colorbox.css">
		<script type="text/javascript" src="${common}/All.js"></script>
		<link rel="stylesheet" href="${easyui }/themes/default/easyui.css" type="text/css">
        <link rel="stylesheet" href="${easyui }/themes/icon.css" type="text/css">
        <link href="${app }/easyUIReset.css" rel="stylesheet" type="text/css"/>
        <link href="${app }/attachFileList.css" rel="stylesheet" type="text/css"/>
        <link href="${app }/css/task.css" rel="stylesheet" type="text/css"/>
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
	    <div class="h1_1" id="divTitle">环境风险及化学品
        <span style="float:right;font-size:16px;">
        <input type="button" id="query" class="bTn blue" onClick="buildWhpListRecord()" value="导出" />
         
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
        </div>
        <input type="hidden" id="pid" name="pid" value="${pid}"/>
		<input type="hidden" id="pid" name="pid" value="${pid}"/>
		<ul class="lookupinfo_a clearfix">
        <li class="cur"><a href="whpQyjbqkFind.htm?id=${pid }" target="lookupframe" hidefocus="true" value="jb">企业基本情况</a></li>
        <li><a href="whpContentFind.htm?id=${pid }" target="lookupframe" hidefocus="true" value="zf">企业化学物质情况</a></li>
        <li><a href="whpHjfxffcsContentFind.htm?id=${pid }" target="lookupframe" hidefocus="true" value="hp">企业环境风险防范措施</a></li>
        <li><a href="whpYjczjjyzyContentFind.htm?id=${pid }"  target="lookupframe" hidefocus="true" value="ts">企业环境应急处置及救援资源</a></li>
        <li><a href="waterContentFind.htm?id=${pid }"  target="lookupframe" hidefocus="true" value="xz">企业周边水环境状况及环境保护目标</a></li>
        <li><a href="airContentFind.htm?id=${pid}"  target="lookupframe" hidefocus="true"  value="fj">企业周边大气环境状况及环境保护目标</a></li>
    </ul>
    <div class="frame mt25" style="width:100%;border-bottom: 0px solid #d4d4d4;margin: auto;">
        <iframe id="fame" frameborder="0" width="100%" height="100%" src="whpQyjbqkFind.htm?id=${pid }" name="lookupframe">

        </iframe>
    </div>
	</body>
</html>
<script type="text/javascript">
	$(document).ready(function() {
		 $(".lookupinfo_a a").click(function(){
	        	
	            $(this).parent().addClass("cur").siblings().removeClass("cur");// 给当前链接父级添加 类“cur”
	            $("#fame").attr("src",$(this).attr("href"));  // 设定当前框架iframe 的地址为 该链接地址
	        })
	        
	});
	var pid=$('#pid').val();
	
	
	//生成危化品记录文档
	function buildWhpListRecord(){
				$.ajax({
					url: "buildWhpListRecord.json",
					async:false,
					type:'POST',
					data : {
						pid : pid,
						
					},
					success:function(data){
						
						$('#fame').attr('src','downloadFile?id='+data.msg);
					}
				});
			
	}
	
	 function fHeight(){
	        var ulHeight=$("ul.lookupinfo_a").innerHeight();
	        var frameHeight=$(window).innerHeight()-ulHeight-39-$("#divTitle").outerHeight();
	        $(".frame").height(frameHeight);
	    }
	    fHeight();
	    $(window).resize(function(){
	        fHeight();
	    });
</script>