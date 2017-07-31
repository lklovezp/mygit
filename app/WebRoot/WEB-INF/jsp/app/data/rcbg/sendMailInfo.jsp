<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>查看会商</title>
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${app}/js.js"></script>
		<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
		<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css"	type="text/css" />
		<link rel="stylesheet" href="${colorbox}/colorbox.css" type="text/css">
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<link href="${app}/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${common}/All.js"></script>
<style type="text/css">
body{ font-family:"Microsoft YaHei";}
*{padding:0; margin:0;}
a{}
a.dbtslink,a.addFile{color:#026da2; text-decoration:none; padding-left:5px; font-size:14px; cursor:pointer;}
a.dbtslink:hover, a.addFile:hover{ text-decoration:underline;}
a.addFile{ padding-left:16px; margin-left:5px; background:url(images/addFile.png) 0px 2px no-repeat;}
table.in,table.in tr, table.in tr td{ border-width:0px;}
a.del{ color:#0088E0; cursor:pointer; margin-left:10px;}
.formtable th{ font-size:14px;}
/**/
.allViews, .viewState{ width:90%; margin:0 auto; border:1px solid #dddddd; padding-bottom:10px; margin-bottom:20px;}
.allViews h5, .viewState h5{ height:16px; line-height:16px; padding:5px; margin-bottom:5px; border-bottom:1px solid #dddddd; color:#0e2d5f; font-weight:bold; font-size:14px;background-color: #cff1ff;
background: -webkit-linear-gradient(top,#EFF5FF 0,#E0ECFF 100%);
background: -moz-linear-gradient(top,#EFF5FF 0,#E0ECFF 100%);
background: -o-linear-gradient(top,#EFF5FF 0,#E0ECFF 100%);
background: linear-gradient(to bottom,#EFF5FF 0,#E0ECFF 100%);
background-repeat: repeat-x;}
.allViews ul li{ padding:3px 0px; margin:0px 20px; border-bottom:1px dashed #bad0ef;}
.allViews ul li div{ line-height:30px;}
.allViews ul li div font{ margin-right:5px;}
.allViews ul li p{ line-height:25px;}
.viewState div{ padding:5px 20px;}
.viewState fieldset{padding:10px; border:1px solid #bad0ef}
.viewState  legend{margin-left:10px;color:#0088cc; padding:0px 3px;}
.viewState p{ line-height:25px;}
.formtable, .formtable th, .formtable td{  border-style: solid; border-color:#dddddd;}
</style>
</head>

<body style="padding-bottom: 60px;">
<div id="divTitle" class="h1_1">查看会商</div>
<input type="hidden" id="mailId" value="${mailForm.id}"/>
<input type="hidden" id="recMailId" value="${recMailId}"/>
<input type="hidden" id="topic" value="${mailForm.topic}"/>
<table class="formtable" width="90%" border="1" cellpadding="0" cellspacing="0" style="margin:0 auto 20px;">
	<tr>
     <th width="90px" align="right" style="background-color:#cff1ff">发送人：</th>
     <td>${mailForm.userId}</td>
    </tr>
    <tr>
     <th align="right" style="background-color:#cff1ff">收件人：</th>
     <td><div style="width:100%;overflow:auto;word-wrap:break-word;word-break:break-all;">${mailForm.recList}</div></td>
    </tr>
    <tr>
     <th align="right" style="background-color:#cff1ff">抄　送：</th>
     <td><div style="width:100%;overflow:auto;word-wrap:break-word;word-break:break-all;">${mailForm.chaoSong}</div></td>
    </tr>
    <tr>
     <th align="right" style="background-color:#cff1ff">主　题：</th>
     <td><div style="width:100%;overflow:auto;word-wrap:break-word;word-break:break-all;">${mailForm.topic}</div></td>
    </tr>
    <tr>
     <th width="90px" align="right" style="font-size:14px;background-color:#cff1ff" valign="top">附　件：</th>
     <td>
        <div class="fileList">
        <iframe name="download" id="download" src="" style="display: none"></iframe>
         <table class="in" border="0">
         <c:forEach items ="${list}" var = "file">
          <tr>
           <td>${file.path}</td>
           <input type="hidden" id="fileId" name="fileId" value="${file.pid}"/>
           <td><a class="del" onclick="exportFile('${file.pid}')">下载</a></td>
          </tr>
          </c:forEach>
         </table>
        </div>
     </td>
    </tr>
    <tr>
     <th width="90px" align="right" style="font-size:14px;background-color:#cff1ff" valign="top">内　容：</th>
     <td ><div style="height:380px; width:100%;overflow:auto;word-wrap:break-word;word-break:break-all;">${mailForm.content}</div></td>
    </tr>
    
</table>
<div class="allViews">
	<h5>全部回复意见</h5>
    <ul>
    <c:forEach  items ="${yjList}" var = "yj">
      <li>
        <div><font color="#0088cc">[${yj.userId}]</font>${yj.readTime}</div>
        <p>${yj.yiJianContent}</p><span>${yj.fuJianId}</span>
      </li>
    </c:forEach>
    </ul>
</div>
<div class="viewState">
  <h5>阅读状态</h5>
  <div>
    <fieldset>
  	  <legend>已读</legend>
      <p>${isReadForm.isReadName}</p>
    </fieldset>
  </div>
  <div>
    <fieldset>
  	  <legend>未读</legend>
      <p>${isReadForm.noReadName}</p>
    </fieldset>
  </div>
</div>
<div style="height:40px; text-align:center" class="rb_btn_fix">
  <input class="queryBlue" type="button" value="返回" onclick="fanhui()" /> 
</div>
</body>
</html>
<Script type="text/javascript">
function toHuiFuYijian(){
	All.ShowModalWin('huiFuYiJian.htm?mailId='+$("#mailId").val()+'&recMailId='+$("#recMailId").val(), '回复意见',800,600);
}
function Mycheck(){
	location=location;
	//window.location.reload();  //刷新父窗口的网页
	//window.close();   
	//关闭父窗口
}
//单附件导出下载
function exportFile(fileId){
	$('#download').attr('src','downloadFile?id='+fileId);
}

//返回
function fanhui(){
	var topic=$('#topic').val();
	if(topic.length>4){
		topic=topic.substr(0, 4);
	}
	//parent.closeSelect(topic);
	window.history.back();
}

</Script>