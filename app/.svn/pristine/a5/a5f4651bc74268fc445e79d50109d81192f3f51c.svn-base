<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>任务派发-接受任务</title>
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <!-- 任务管理 css-->
    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
    <!-- ztree -->
    <link rel="stylesheet" href="${ztree}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
    <script type="text/javascript" src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${ztree}/js/jquery.ztree.excheck-3.5.js"></script>
    <!-- zTree 修改的样式-->
    <link href="${app}/zTreeReset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${common}/All.js"></script>
    <style type="text/css">
    #checkup_footer {
    height: 40px;
	}
    </style>
</head>
<body style="padding-bottom: 60px;">
<form id="bgForm" name="bgForm" method="post" action="commworkzxBG.json">
        <input type="hidden" id="applyId" name="applyId" value="${work.id}" />
		<input type="hidden" id="taskId" name="taskId" value="${taskId}" />
		<input type="hidden" id="slsj" name="slsj" value="${slsj}" />
		<input type="hidden" id="bjqk" name="bjqk" value="${bjqk}" />
		<input type="hidden" id="fileIds" name="fileIds" value="" />
<!-- 现场监察 -->
<div id="DIV_10" class="checkup">
    <div class="mt25">
        <div class="annex_header">
        <c:if test="${sysVer != 0 }">
            <a class="b_link" onclick="downZipFile('RCJCdata')" style="float:right;" >打包下载</a>
        </c:if>
            现场监察
        </div>
        <div class="annex_con" style=" height: 210px;">
            <table id="RCJCdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="36">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="80">
            <td style="color: #666666;">
            <div style="height:140px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
            		${blMainForm.blRcjcForm.jcjl}
            </div>
            </td>
        </tr>
    </table>
</div>
<!-- 年度核查 -->
<div id="DIV_11" class="checkup">
    <div class="mt25">
        <div class="annex_header">
        <c:if test="${sysVer != 0 }">
            <a class="b_link" onclick="downZipFile('NDHCdata')" style="float:right;" >打包下载</a>
        </c:if>
            年度核查
        </div>
        <div class="annex_con" style=" height: 210px;">
            <table id="NDHCdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="36">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="80">
            <td style="color: #666666;">
            <div style="height:140px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
            	${blMainForm.blNdhcForm.jcjl}
            </div>
            </td>
        </tr>
    </table>
</div>
<!-- 后督察-->
<div id="DIV_12" class="checkup">
    <div class="mt25">
        <div class="annex_header">
        <c:if test="${sysVer != 0 }">
            <a class="b_link" onclick="downZipFile('HDCdata')" style="float:right;" >打包下载</a>
        </c:if>
            后督察
        </div>
        <div class="annex_con" style=" height: 210px;">
            <table id="HDCdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="36">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="80">
            <td style="color: #666666;">
            <div style="height:140px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
            	${blMainForm.blHdcForm.jcjl}
            </div>
            </td>
        </tr>
    </table>
</div>
<!-- 信访投诉-->
<div id="DIV_13" class="checkup">
    <div class="mt25">
        <div class="annex_header">
        <c:if test="${sysVer != 0 }">
            <a class="b_link" onclick="downZipFile('XFTSdata')" style="float:right;" >打包下载</a>
        </c:if>
            信访投诉
        </div>
        <div class="annex_con" style=" height: 210px;">
            <table id="XFTSdata" fit="true"></table>
        </div>
    </div>
    <c:if test="${blMainForm.blXftsForm != null && blMainForm.blXftsForm.jcjl != null}">
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="36">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="80">
            <td style="color: #666666;">
            <div style="height:140px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
            	${blMainForm.blXftsForm.jcjl}
            </div>
            </td>
        </tr>
    </table>
    </c:if>
</div>
<!-- 排污许可证检查 -->
<div id="DIV_14" class="checkup">
    <div class="mt25">
        <div class="annex_header">
        <c:if test="${sysVer != 0 }">
            <a class="b_link" onclick="downZipFile('PWXKZJCdata')" style="float:right;" >打包下载</a>
        </c:if>
            排污许可证检查
        </div>
        <div class="annex_con" style=" height: 210px;">
            <table id="PWXKZJCdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="36">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="80">
            <td style="color: #666666;">
            <div style="height:140px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
            	${blMainForm.blPwxkzjcForm.jcjl}
            </div>
            </td>
        </tr>
    </table>
</div>
<!-- 专项行动 -->
<div id="DIV_15" class="checkup">
    <div class="mt25">
        <div class="annex_header">
        <c:if test="${sysVer != 0 }">
            <a class="b_link" onclick="downZipFile('ZXXDdata')" style="float:right;" >打包下载</a>
        </c:if>
            专项行动
        </div>
        <div class="annex_con" style=" height: 210px;">
            <table id="ZXXDdata" fit="true"></table>
        </div>
    </div>
</div>
<!-- 违法案件调查  -->
<div id="DIV_16" class="checkup">
    <div class="mt25">
        <div class="annex_header">
        <c:if test="${sysVer != 0 }">
            <a class="b_link" onclick="downZipFile('WFAJDCdata')" style="float:right;" >打包下载</a>
        </c:if>
           违法案件调查 
        </div>
        <div class="annex_con" style=" height: 210px;">
            <table id="WFAJDCdata" fit="true"></table>
        </div>
    </div>
</div>
<!-- 限期治理  -->
<div id="DIV_17" class="checkup">
    <div class="mt25">
        <div class="annex_header">
        <c:if test="${sysVer != 0 }">
            <a class="b_link" onclick="downZipFile('XQZLdata')" style="float:right;" >打包下载</a>
        </c:if>
           限期治理 
        </div>
        <div class="annex_con" style=" height: 210px;">
            <table id="XQZLdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="36">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="80">
            <td style="color: #666666;">
            <div style="height:140px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
            	${blMainForm.blXqzlForm.jcjl}
            </div>
            </td>
        </tr>
    </table>
</div>
<!-- 跟踪检查  -->
<div id="DIV_18" class="checkup">
    <div class="mt25">
        <div class="annex_header">
        <c:if test="${sysVer != 0 }">
            <a class="b_link" onclick="downZipFile('GZJCdata')" style="float:right;" >打包下载</a>
        </c:if>
           跟踪检查
        </div>
        <div class="annex_con" style=" height: 210px;">
            <table id="GZJCdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="36">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="80">
            <td style="color: #666666;">
            <div style="height:140px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
            	${blMainForm.blGzjcForm.jcjl}
            </div>
            </td>
        </tr>
    </table>
</div>
<!-- 自动监控 -->
<div id="DIV_19" class="checkup">
    <div class="mt25">
        <div class="annex_header">
        <c:if test="${sysVer != 0 }">
            <a class="b_link" onclick="downZipFile('ZDJKdata')" style="float:right;" >打包下载</a>
        </c:if>
           自动监控
        </div>
        <div class="annex_con" style=" height: 210px;">
            <table id="ZDJKdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="36">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="80">
            <td style="color: #666666;">
            <div style="height:140px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
            	${blMainForm.blZdjkForm.jcjl}
            </div>
            </td>
        </tr>
    </table>
</div>
<!-- 危险废物-->
<div id="DIV_20" class="checkup">
    <div class="mt25">
        <div class="annex_header">
        <c:if test="${sysVer != 0 }">
            <a class="b_link" onclick="downZipFile('WXFWdata')" style="float:right;" >打包下载</a>
        </c:if>
           危险废物
        </div>
        <div class="annex_con" style=" height: 210px;">
            <table id="WXFWdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="36">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="80">
            <td style="color: #666666;">
            <div style="height:140px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
            	${blMainForm.blWxfwForm.jcjl}
            </div>
            </td>
        </tr>
    </table>
</div>
<!-- 危险化学品-->
<div id="DIV_21" class="checkup">
    <div class="mt25">
        <div class="annex_header">
        <c:if test="${sysVer != 0 }">
            <a class="b_link" onclick="downZipFile('WXHXPdata')" style="float:right;" >打包下载</a>
        </c:if>
           危险化学品
        </div>
        <div class="annex_con" style=" height: 210px;">
            <table id="WXHXPdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="36">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="80">
            <td style="color: #666666;">
            <div style="height:140px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
            	${blMainForm.blWxhxpForm.jcjl}
            </div>
            </td>
        </tr>
    </table>
</div>
<!-- 辐射安全-->
<div id="DIV_22" class="checkup">
    <div class="mt25">
        <div class="annex_header">
        <c:if test="${sysVer != 0 }">
            <a class="b_link" onclick="downZipFile('FSAQdata')" style="float:right;" >打包下载</a>
        </c:if>
           辐射安全
        </div>
        <div class="annex_con" style=" height: 210px;">
            <table id="FSAQdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="36">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="80">
            <td style="color: #666666;">
            <div style="height:140px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
            	${blMainForm.blFsaqForm.jcjl}
            </div>
            </td>
        </tr>
    </table>
</div>
<!-- 污染事故现场调查-->
<div id="DIV_23" class="checkup">
    <div class="mt25">
        <div class="annex_header">
        <c:if test="${sysVer != 0 }">
            <a class="b_link" onclick="downZipFile('WRSGXCDCdata')" style="float:right;" >打包下载</a>
        </c:if>
           污染事故现场调查
        </div>
        <div class="annex_con" style=" height: 210px;">
            <table id="WRSGXCDCdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="36">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="80">
            <td style="color: #666666;">
            <div style="height:140px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
            	${blMainForm.blWrsgxcdcForm.jcjl}
            </div>
            </td>
        </tr>
    </table>
</div>
<!-- 日常办公-->
<div id="DIV_24" class="checkup">
    <div class="mt25">
        <div class="annex_header">
        <c:if test="${sysVer != 0 }">
            <a class="b_link" onclick="downZipFile('RCBGdata')" style="float:right;" >打包下载</a>
        </c:if>
           日常办公
        </div>
        <div class="annex_con" style=" height: 210px;">
            <table id="RCBGdata" fit="true"></table>
        </div>
    </div>
    <!--备注-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="36">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;备注</th>
        </tr>
        <tr height="80">
            <td style="color: #666666;">
            <div style="height:140px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
            	${blMainForm.blRcbgForm.desc}
            </div>
            </td>
        </tr>
    </table>
</div>
<div id="checkup_footer" class="rb_btn_fix">
  <c:if test="${sysVer != 0 }">
    <div >
        <input class="queryBlue" id="bgbutton" type="button" value="报告" onclick="bg();" style=" font-size:14px;cursor: pointer;">
    </div>
  </c:if>
  <c:if test="${sysVer == 0 }">
    <div >
        <input class="queryBlue" id="bgbutton" type="button" value="打包" onclick="dabao();" style=" font-size:14px;cursor: pointer;">
    </div>
  </c:if>
</div>
</form>
<iframe name="download" id="download" src="" style="display: none"></iframe>
</body>
</html>
<script type="text/javascript">
var applyId = $('#applyId').val();
var endtime=$('#slsj').val();
var bjqk=$('#bjqk').val();

$(document).ready(function() {
    //附件展示
	initZBGZ();
});
//定义一个对象（code：任务类型code,zwName：中文名称,pyjx：拼音简写；fileType：文件类型；）
function RwlxObj(code,zwName,pyjx,fileType){
    this.code = code;
    this.zwName = zwName;
    this.pyjx = pyjx;
    this.fileType=fileType;
}
var rwlxObj=new RwlxObj("","","","");
//附件展示
function initZBGZ(){
    $("div[id^='DIV_']").hide();//先把所有的div隐藏
    //获取任务类型
	$.ajax({
	  url: "getTaskTypeMultiple.json?applyId="+applyId,
	  success:function(data){
	      if(data.rwlxIds!=null&&data.rwlxIds!=''){
	          var codesArr=data.rwlxIds.split(',');
	          $.each(codesArr, function(i, n){
				    switch(n){//根据code判断任务类型
		            case '10'://现场监察
		               rwlxObj=new RwlxObj("10","现场监察","RCJC","RCJCJCJL,RCJCMOREJCBL,RCJCJCJLSMJ,RCJCJCBG,RCJCXZWS,RCJCQTZL,RCJCCLYJS,RCJCZBZL,RCJCDBFJ,RCJCSPZL,RCJCLYZL,RCJCZP,RCJCHPPFWJ,RCJCYSPFWJ");
		               showCOMMON(rwlxObj);
		               document.getElementById("fileIds").value = "RCJCdata";
		               break;
		            case '11'://年度核查
		               rwlxObj=new RwlxObj("11","年度核查","NDHC","NDHCJCJL,NDHCMOREJCBL,NDHCJCJLSMJ,NDHCJCBG,NDHCXZWS,NDHCQTZL,NDHCCLYJS,NDHCZBZL,NDHCDBFJ,NDHCSPZL,NDHCLYZL,NDHCZP,NDHCHPPFWJ,NDHCYSPFWJ");
		               showCOMMON(rwlxObj);
		               document.getElementById("fileIds").value = "NDHCdata";
		               break;
		            case '12'://后督察
		               rwlxObj=new RwlxObj("12","后督察","HDC","HDCJCJL,HDCMOREJCBL,HDCJCJLSMJ,HDCJCBG,HDCXZWS,HDCQTZL,HDCCLYJS,HDCZBZL,HDCDBFJ,HDCSPZL,HDCLYZL,HDCZP,HDCHPPFWJ,HDCYSPFWJ");
		               showCOMMON(rwlxObj);
		               document.getElementById("fileIds").value = "HDCdata";
		               break;
		            case '13'://信访投诉
		               rwlxObj=new RwlxObj("13","信访投诉","XFTS","XFTSJCJL,XFTSBJDSMJ,XFTSMOREJCBL,XFTSJCJLSMJ,XFTSJCBG,XFTSXZWS,XFTSQTZL,XFTSCLYJS,XFTSZBZL,XFTSDBFJ,XFTSSPZL,XFTSLYZL,XFTSZP,XFTSHPPFWJ,XFTSYSPFWJ");
		               showCOMMON(rwlxObj);
		               document.getElementById("fileIds").value = "XFTSdata";
		               break;
		            case '14'://排污许可证检查
		               rwlxObj=new RwlxObj("14","排污许可证检查","PWXKZJC","PWXKZJCJCJL,PWXKZJCMOREJCBL,PWXKZJCJCJLSMJ,PWXKZJCJCBG,PWXKZJCXZWS,PWXKZJCQTZL,PWXKZJCCLYJS,PWXKZJCZBZL,PWXKZDBFJ,PWXKZSPZL,PWXKZLYZL,PWXKZZP,PWXKZHPPFWJ,PWXKZYSPFWJ");
		               showCOMMON(rwlxObj);
		               document.getElementById("fileIds").value = "PWXKZJCdata";
		               break;
		            case '15'://专项行动
		               rwlxObj=new RwlxObj("15","专项行动","ZXXD","ZXXDQTZL,ZXXDZRWYSB");
		               showCOMMON(rwlxObj);
		               document.getElementById("fileIds").value = "ZXXDdata";
		               break;
		            case '16'://违法案件调查
		               rwlxObj=new RwlxObj("16","违法案件调查","WFAJDC","WFAJDCLADJB,WFAJDCLADJSMJ,WFAJDCKCBL,WFAJDCKCBLSMJ,WFAJDCXWBL,WFAJDCXWBLSMJ,WFAJDCSZDZJZL,WFAJDCSTZLTP,WFAJDCYPZL,WFAJDCSTZLLX,WFAJDCXZWS,WFAJDCQTZL,WFAJDCDCBG,WFAJDCZBZL,WFAJDCDBFJ,WFAJDCHPPFWJ,WFAJDCYSPFWJ");
		               showCOMMON(rwlxObj);
		               document.getElementById("fileIds").value = "WFAJDCdata";
		               break;
		            case '17'://限期治理
		               rwlxObj=new RwlxObj("17","限期治理","XQZL","XQZLJCJL,XQZLMOREJCBL,XQZLJCJLSMJ,XQZLJCBG,XQZLXZWS,XQZLQTZL,XQZLCLYJS,XQZLZBZL,XQZLDBFJ,XQZLSPZL,XQZLLYZL,XQZLZP,XQZLHPPFWJ,XQZLYSPFWJ,XQZLHJWFXWXQGZTZ,XQZLXZCFJDHSDHZ,XQZLTZGZSSDHZ");
		               showCOMMON(rwlxObj);
		               document.getElementById("fileIds").value = "XQZLdata";
		               break;
		            case '18'://跟踪检查
		               rwlxObj=new RwlxObj("18","跟踪检查","GZJC","GZJCJCJL,GZJCMOREJCBL,GZJCJCJLSMJ,GZJCJCBG,GZJCXZWS,GZJCQTZL,GZJCCLYJS,GZJCZBZL,GZJCDBFJ,GZJCSPZL,GZJCLYZL,GZJCZP,GZJCHPPFWJ,GZJCYSPFWJ");
		               showCOMMON(rwlxObj);
		               document.getElementById("fileIds").value = "GZJCdata";
		               break;
		            case '19'://自动监控
		               rwlxObj=new RwlxObj("19","自动监控","ZDJK","ZDJKJCJL,ZDJKJCJLSMJ,ZDJKJCBG,ZDJKXZWS,ZDJKQTZL,ZDJKCLYJS,ZDJKZBZL,ZDJKDBFJ,ZDJKSPZL,ZDJKLYZL,ZDJKZP,ZDJKHPPFWJ,ZDJKYSPFWJ,ZDJKHBWD");
		               showCOMMON(rwlxObj);
		               document.getElementById("fileIds").value = "ZDJKdata";
		               break;
		            case '20'://危险废物
		               rwlxObj=new RwlxObj("20","危险废物","WXFW","WXFWJCJL,WXFWMOREJCBL,WXFWJCJLSMJ,WXFWJCBG,WXFWXZWS,WXFWQTZL,WXFWCLYJS,WXFWZBZL,WXFWDBFJ,WXFWSPZL,WXFWLYZL,WXFWZP,WXFWHPPFWJ,WXFWYSPFWJ");
		               showCOMMON(rwlxObj);
		               document.getElementById("fileIds").value = "WXFWdata";
		               break;
		            case '21'://危险化学品
		               rwlxObj=new RwlxObj("21","危险化学品","WXHXP","WXHXPJCJL,WXHXPJCJLSMJ,WXHXPJCBG,WXHXPXZWS,WXHXPQTZL,WXHXPCLYJS,WXHXPZBZL,WXHXPDBFJ,WXHXPSPZL,WXHXPLYZL,WXHXPZP,WXHXPHPPFWJ,WXHXPYSPFWJ");
		               showCOMMON(rwlxObj);
		               document.getElementById("fileIds").value = "WXHXPdata";
		               break;
		            case '22'://辐射安全
		               rwlxObj=new RwlxObj("22","辐射安全","FSAQ","FSAQJCJL,FSAQMOREJCBL,FSAQJCJLSMJ,FSAQJCBG,FSAQXZWS,FSAQQTZL,FSAQCLYJS,FSAQZBZL,FSAQDBFJ,FSAQSPZL,FSAQLYZL,FSAQZP,FSAQHPPFWJ,FSAQYSPFWJ");
		               showCOMMON(rwlxObj);
		               document.getElementById("fileIds").value = "FSAQdata";
		               break;
		            case '23'://污染事故现场调查
		               rwlxObj=new RwlxObj("23","污染事故现场调查","WRSGXCDC","WRSGXCDCJCJL,WRSGXCDCMOREJCBL,WRSGXCDCJCJLSMJ,WRSGXCDCJCBG,WRSGXCDCXZWS,WRSGXCDCQTZL,WRSGXCDCCLYJS,WRSGXCDCZBZL,WRSGXCDCDBFJ,WRSGXCDCSPZL,WRSGXCDCLYZL,WRSGXCDCZP,WRSGXCDCHPPFWJ,WRSGXCDCYSPFWJ");
		               showCOMMON(rwlxObj);
		               document.getElementById("fileIds").value = "WRSGXCDCdata";
		               break;
		            case '24'://日常办公
		               rwlxObj=new RwlxObj("24","日常办公","RCBG","RCBGZBZL,RCBGBLZL,RCBGDBFJ");
		               showCOMMON(rwlxObj);
		               document.getElementById("fileIds").value = "RCBGdata";
		               break;
		            default:
		               alert("还未定义code为"+n+"的任务类型");
		            }
			  });
	      }
	   }
	});
}
/////////////////////准备工作的展示方法start/////////////////////////
//通用展示
function showCOMMON(rwlxObj){
    var fileTypeNum=rwlxObj.fileType.split(',');
    var canDelStr="";
    for(var i=0;i<fileTypeNum.length;i++){
        if(i<fileTypeNum.length-1){
           canDelStr+="N,";
        }else{
           canDelStr+="N";
        }
    }
    //显示
    $('#DIV_'+rwlxObj.code).show();
    /**  */
    //赋值
    $('#'+rwlxObj.pyjx+'data').datagrid({
        pagination:true,//分页控件
        height:'auto',
        width : $(window).width(),
		rownumbers: true,
		singleSelect: true,
		fitColumns : true,
		
		url:'queryFileListMulfileType.json?canDel='+canDelStr,
		queryParams:{pid:$("#applyId").val(),fileType:rwlxObj.fileType},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:100},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:500},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:100},
			{field:'operate',title:'操作', align:'center', halign:'center',width:100}
		]]
	});
    
}
/////////////////////准备工作的展示方法end///////////////////////////

function bg() {
	var index=parent.layer.confirm('确定报告？', {
     	icon: 3, 
        title:'报告'
     }, function(index){
    	 $("#bgbutton").attr({"disabled":"disabled"});
			$.post('bg.json', {
					applyId : $('#applyId').val(),
					taskId : $('#taskId').val()
				}, 
				function(data) {
					$("#bgbutton").removeAttr("disabled");//将按钮可用
					if (data.state) {
						if(bjqk != null && bjqk != "" && bjqk == "999"){
							$.ajax({
								type : "post",
								url : "rwzbl.json?applyId="+applyId+"&slsj="+endtime,
								async : false,
								success : function(data) {
									alert(data.msg);
								}
							});
						}
					  	parent.toDbList();
					} else {
						alert(data.msg);
					}
			}, 'json');	
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
	
}

//卷宗打包功能
function dabao(){
	var fileIds = $('#fileIds').val();
	var data = $('#'+fileIds).datagrid("getData");
	var ids = "";
	for(var i = 0; i < data.rows.length; i++){
		if (i > 0){
			ids += ",";
		}
		ids += data.rows[i].id;
	}
	$('#download').attr('src','downZipFile?id='+ids);
}

//打包下载
function downZipFile(tableId){
    var data = $('#'+tableId).datagrid("getData");
	var ids = "";
	for(var i = 0; i < data.rows.length; i++){
		if (i > 0){
			ids += ",";
		}
		ids += data.rows[i].id;
	}
	$('#download').attr('src','downZipFile?id='+ids);
}
</script>