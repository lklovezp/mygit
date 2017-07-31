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
	<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
	<script type="text/javascript" src="${layer}/layer.js"></script>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
    <!-- 任务管理 css-->
    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${common}/All.js"></script>
</head>
<body>
<div class="checkup">
    <input type="hidden" id="applyId" name="applyId" value="${work.id}" />
    <h1 id="checkup_header">报告详情</h1>
    <table class="dataTable" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="48">
            <th colspan="8" align="left">&nbsp;&nbsp;&nbsp;&nbsp;任务信息</th>
        </tr>
        <tr>
	        <td colspan="8">
	           <a href="#" id="viewDetail" style="float: left;" class="b_link" onclick="info()">任务详情</a>&nbsp;
	        </td>
        </tr>
        <tr  height="42">
            <td width="12%" align="center">主办人员</td>
            <td colspan="1">${bgxqMap.zxrName}</td>
            <td width="12%" align="center">执法证号</td>
            <td colspan="1">${bgxqMap.lawnumber}</td>
            <td width="12%" align="center">电话</td>
            <td colspan="1">${bgxqMap.workmobile}</td>
            <td width="12%" align="center">部门</td>
            <td colspan="1">${bgxqMap.org}</td>
        </tr>
        <c:if test="${isXP==false}">
		<c:if test='${bgxqMap.xbr == "Y"}'>
        <tr  height="42">
            <td width="12%" align="center">协办人员</td>
            <td colspan="7">
               <div class="annex_con" style=" height: 200px;">
                  <table id="xbryTable" fit="true"></table>
               </div>
            </td>
        </tr>
        </c:if>
		<c:if test='${bgxqMap.rwlxIds != "24"}'>
        <tr  height="42">
            <td width="12%" align="center">执法对象</td>
            <td colspan="7">
				<div class="annex_con" style=" height: 200px;">
                  <table id="zfdxTable" fit="true"></table>
                </div>
            </td>
        </tr>
        </c:if>
		</c:if>
        <tr  height="42">
            <td width="12%" align="center">办理时间</td>
            <td colspan="7">${bgxqMap.zxStartTime} 至 ${bgxqMap.zxtime}</td>
        </tr>
    </table>
</div>
   <!-- 现场监察 -->
<div id="DIV_10" class="checkup">
    <div class="mt25">
        <div class="annex_header">
        <c:if test="${sysVer != 0 }">
            <a class="b_link" onclick="downZipFile('RCJCdata')" style="float:right;" >打包下载</a>
        </c:if>
            现场监察
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="RCJCdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="48">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="42">
            <td style="color: #666666;">
            <div  id="JCJL_10" style="height:100px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
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
        <div class="annex_con" style=" height: 248px;">
            <table id="NDHCdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="48">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="42">
            <td style="color: #666666;">
            <div id="JCJL_11" style="height:100px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
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
        <div class="annex_con" style=" height: 248px;">
            <table id="HDCdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="48">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="42">
            <td style="color: #666666;">
            <div id="JCJL_12" style="height:100px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
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
        <div class="annex_con" style=" height: 248px;">
            <table id="XFTSdata" fit="true"></table>
        </div>
    </div>
    <c:if test="${blMainForm.blXftsForm != null && blMainForm.blXftsForm.jcjl != null}">
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="48">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="42">
            <td style="color: #666666;">
            <div id="JCJL_13" style="height:100px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
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
        <div class="annex_con" style=" height: 248px;">
            <table id="PWXKZJCdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="48">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="42">
            <td style="color: #666666;">
            <div id="JCJL_14" style="height:100px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
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
        <div class="annex_con" style=" height: 248px;">
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
        <div class="annex_con" style=" height: 248px;">
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
        <div class="annex_con" style=" height: 248px;">
            <table id="XQZLdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="48">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="42">
            <td style="color: #666666;">
            <div id="JCJL_17" style="height:100px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
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
        <div class="annex_con" style=" height: 248px;">
            <table id="GZJCdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="48">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="42">
            <td style="color: #666666;">
            <div id="JCJL_18" style="height:100px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
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
        <div class="annex_con" style=" height: 248px;">
            <table id="ZDJKdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="48">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="42">
            <td style="color: #666666;">
            <div id="JCJL_19" style="height:100px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
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
        <div class="annex_con" style=" height: 248px;">
            <table id="WXFWdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="48">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="42">
            <td style="color: #666666;">
            <div id="JCJL_20" style="height:100px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
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
        <div class="annex_con" style=" height: 248px;">
            <table id="WXHXPdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="48">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="42">
            <td style="color: #666666;">
            <div id="JCJL_21" style="height:100px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
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
        <div class="annex_con" style=" height: 248px;">
            <table id="FSAQdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="48">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="42">
            <td style="color: #666666;">
            <div id="JCJL_22" style="height:100px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
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
        <div class="annex_con" style=" height: 248px;">
            <table id="WRSGXCDCdata" fit="true"></table>
        </div>
    </div>
    <!--监察结论-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="48">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;监察结论</th>
        </tr>
        <tr height="42">
            <td style="color: #666666;">
            <div id="JCJL_23" style="height:100px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
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
        <div class="annex_con" style=" height: 248px;">
            <table id="RCBGdata" fit="true"></table>
        </div>
    </div>
    <!--备注-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="48">
            <th align="left">&nbsp;&nbsp;&nbsp;&nbsp;备注</th>
        </tr>
        <tr height="42">
            <td style="color: #666666;">
            <div id="JCJL_24" style="height:100px;width:100%;overflow:auto;word-wrap:break-word;word-break:break-all; ">
            </div>
            </td>
        </tr>
    </table>
</div>
 <iframe name="download" id="download" src="" style="display: none"></iframe>
</body>
</html>
<script type="text/javascript">
var applyId = $('#applyId').val();

$(document).ready(function() {
    //协办人员列表
	$('#xbryTable').datagrid({
	    url:'xbryTable.json', 
	    queryParams:{applyId:applyId},
	    width:'100%',
		height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns:true,
	    columns:[[
	        {field:'name',title:'姓名',width:100,align:'center'},
	        {field:'workmobile',title:'电话',width:100,align:'center'},
	        {field:'lawnumber',title:'执法证号',width:100,align:'center'},
	        {field:'org',title:'部门',width:100,align:'center'}
	    ]]
	});
	
	//执法对象列表
	$('#zfdxTable').datagrid({
	    url:'zfdxTable.json', 
	    queryParams:{applyId:applyId},
	    width:'100%',
		height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns:true,
	    columns:[[  
	        {field:'lawobjname',title:'执法对象',width:100,align:'center'},   
	        {field:'regionid',title:'所属行政区',width:50,align:'center'}, 
	        {field:'address',title:'地址',width:120,align:'center'},
	        {field:'manager',title:'负责人',width:50,align:'center'},
	        {field:'managermobile',title:'联系方式',width:80,align:'center'},
	        {field:'operate',title:'操作',width:50,align:'center'}
	    ]]
	});
	
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
		               rwlxObj=new RwlxObj("10","现场监察","RCJC","RCJCJCJL,RCJCJCJLSMJ,RCJCMOREJCBL,RCJCJCBG,RCJCXZWS,RCJCQTZL,RCJCCLYJS,RCJCZBZL,RCJCSPZL,RCJCLYZL,RCJCZP,RCJCHPPFWJ,RCJCYSPFWJ");
		               //根据任务类型获取监察笔录
					   $.ajax({
						  url: "jcjl.json?tasktype=10&taskid="+applyId,
						  success:function(data){
						  	$('#JCJL_'+10).html(data.jcjl);
						  }
					   });
		               showCOMMON(rwlxObj);
		               break;
		            case '11'://年度核查
		               rwlxObj=new RwlxObj("11","年度核查","NDHC","NDHCJCJL,NDHCJCJLSMJ,NDHCMOREJCBL,NDHCJCBG,NDHCXZWS,NDHCQTZL,NDHCCLYJS,NDHCZBZL,NDHCSPZL,NDHCLYZL,NDHCZP,NDHCHPPFWJ,NDHCYSPFWJ");
		               //根据任务类型获取监察笔录
					   $.ajax({
						  url: "jcjl.json?tasktype=11&taskid="+applyId,
						  success:function(data){
						  	$('#JCJL_'+11).html(data.jcjl);
						  }
					   });
		               showCOMMON(rwlxObj);
		               break;
		            case '12'://后督察
		               rwlxObj=new RwlxObj("12","后督察","HDC","HDCJCJL,HDCJCJLSMJ,HDCMOREJCBL,HDCJCBG,HDCXZWS,HDCQTZL,HDCCLYJS,HDCZBZL,HDCSPZL,HDCLYZL,HDCZP,HDCHPPFWJ,HDCYSPFWJ");
		               //根据任务类型获取监察笔录
					   $.ajax({
						  url: "jcjl.json?tasktype=12&taskid="+applyId,
						  success:function(data){
						  	$('#JCJL_'+12).html(data.jcjl);
						  }
					   });
		               showCOMMON(rwlxObj);
		               break;
		            case '13'://信访投诉
		               rwlxObj=new RwlxObj("13","信访投诉","XFTS","XFTSJCJL,XFTSJCJLSMJ,XFTSBJDSMJ,XFTSMOREJCBL,XFTSJCBG,XFTSXZWS,XFTSQTZL,XFTSCLYJS,XFTSZBZL,XFTSSPZL,XFTSLYZL,XFTSZP,XFTSHPPFWJ,XFTSYSPFWJ");
		                //根据任务类型获取监察笔录
					   $.ajax({
						  url: "jcjl.json?tasktype=13&taskid="+applyId,
						  success:function(data){
						  	$('#JCJL_'+13).html(data.jcjl);
						  }
					   });
		               showCOMMON(rwlxObj);
		               break;
		            case '14'://排污许可证检查
		               rwlxObj=new RwlxObj("14","排污许可证检查","PWXKZJC","PWXKZJCJCJL,PWXKZJCJCJLSMJ,PWXKZJCMOREJCBL,PWXKZJCJCBG,PWXKZJCXZWS,PWXKZJCQTZL,PWXKZJCCLYJS,PWXKZJCZBZL,PWXKZSPZL,PWXKZLYZL,PWXKZZP,PWXKZHPPFWJ,PWXKZYSPFWJ");
		                //根据任务类型获取监察笔录
					   $.ajax({
						  url: "jcjl.json?tasktype=14&taskid="+applyId,
						  success:function(data){
						  	$('#JCJL_'+14).html(data.jcjl);
						  }
					   });
		               showCOMMON(rwlxObj);
		               break;
		            case '15'://专项行动
		               rwlxObj=new RwlxObj("15","专项行动","ZXXD","ZXXDJCBG,ZXXDQTZL,ZXXDZRWJCJL,ZXXDZRWMOREJCBL,ZXXDZRWJCJLSMJ,ZXXDZRWJCBG,ZXXDZRWXZWS,ZXXDZRWQTZL,ZXXDZRWYSB,ZXXDZRWCLYJS,ZXXDZBZL,ZXXDZRWSPZL,ZXXDZRWLYZL,ZXXDZRWZP,ZXXDZRWHPPFWJ,ZXXDZRWYSPFWJ");
		               showCOMMON(rwlxObj);
		               break;
		            case '16'://违法案件调查
		               rwlxObj=new RwlxObj("16","违法案件调查","WFAJDC","WFAJDCLADJB,WFAJDCLADJSMJ,WFAJDCKCBL,WFAJDCKCBLSMJ,WFAJDCXWBL,WFAJDCXWBLSMJ,WFAJDCSZDZJZL,WFAJDCSTZLTP,WFAJDCYPZL,WFAJDCSTZLLX,WFAJDCXZWS,WFAJDCQTZL,WFAJDCDCBG,WFAJDCZBZL,WFAJDCHPPFWJ,WFAJDCYSPFWJ");
		               showCOMMON(rwlxObj);
		               break;
		            case '17'://限期治理
		               rwlxObj=new RwlxObj("17","限期治理","XQZL","XQZLJCJL,XQZLJCJLSMJ,XQZLMOREJCBL,XQZLJCBG,XQZLXZWS,XQZLQTZL,XQZLCLYJS,XQZLZBZL,XQZLDBFJ,XQZLSPZL,XQZLLYZL,XQZLZP,XQZLHPPFWJ,XQZLYSPFWJ,XQZLHJWFXWXQGZTZ,XQZLXZCFJDHSDHZ,XQZLTZGZSSDHZ");
		               //根据任务类型获取监察笔录
					   $.ajax({
						  url: "jcjl.json?tasktype=17&taskid="+applyId,
						  success:function(data){
						  	$('#JCJL_'+17).html(data.jcjl);
						  }
					   });
		               showCOMMON(rwlxObj);
		               break;
		            case '18'://跟踪检查
		               rwlxObj=new RwlxObj("18","跟踪检查","GZJC","GZJCJCJL,GZJCJCJLSMJ,GZJCMOREJCBL,GZJCJCBG,GZJCXZWS,GZJCQTZL,GZJCCLYJS,GZJCZBZL,GZJCDBFJ,GZJCSPZL,GZJCLYZL,GZJCZP,GZJCHPPFWJ,GZJCYSPFWJ");
		               //根据任务类型获取监察笔录
					   $.ajax({
						  url: "jcjl.json?tasktype=18&taskid="+applyId,
						  success:function(data){
						  	$('#JCJL_'+18).html(data.jcjl);
						  }
					   });
		               showCOMMON(rwlxObj);
		               break;
		            case '19'://自动监控
		               rwlxObj=new RwlxObj("19","自动监控","ZDJK","ZDJKJCJL,ZDJKJCJLSMJ,ZDJKJCBG,ZDJKXZWS,ZDJKQTZL,ZDJKCLYJS,ZDJKZBZL,ZDJKSPZL,ZDJKLYZL,ZDJKZP,ZDJKHPPFWJ,ZDJKYSPFWJ,ZDJKHBWD");
		               //根据任务类型获取监察笔录
					   $.ajax({
						  url: "jcjl.json?tasktype=19&taskid="+applyId,
						  success:function(data){
						  	$('#JCJL_'+19).html(data.jcjl);
						  }
					   });
		               showCOMMON(rwlxObj);
		               break;
		            case '20'://危险废物
		               rwlxObj=new RwlxObj("20","危险废物","WXFW","WXFWJCJL,WXFWJCJLSMJ,WXFWMOREJCBL,WXFWJCBG,WXFWXZWS,WXFWQTZL,WXFWCLYJS,WXFWZBZL,WXFWSPZL,WXFWLYZL,WXFWZP,WXFWHPPFWJ,WXFWYSPFWJ");
		               //根据任务类型获取监察笔录
					   $.ajax({
						  url: "jcjl.json?tasktype=20&taskid="+applyId,
						  success:function(data){
						  	$('#JCJL_'+20).html(data.jcjl);
						  }
					   });
		               showCOMMON(rwlxObj);
		               break;
		            case '21'://危险化学品
		               rwlxObj=new RwlxObj("21","危险化学品","WXHXP","WXHXPJCJL,WXHXPJCJLSMJ,WXHXPJCBG,WXHXPXZWS,WXHXPQTZL,WXHXPCLYJS,WXHXPZBZL,WXHXPSPZL,WXHXPLYZL,WXHXPZP,WXHXPHPPFWJ,WXHXPYSPFWJ");
		               //根据任务类型获取监察笔录
					   $.ajax({
						  url: "jcjl.json?tasktype=21&taskid="+applyId,
						  success:function(data){
						  	$('#JCJL_'+21).html(data.jcjl);
						  }
					   });
		               showCOMMON(rwlxObj);
		               break;
		            case '22'://辐射安全
		               rwlxObj=new RwlxObj("22","辐射安全","FSAQ","FSAQJCJL,FSAQJCJLSMJ,FSAQMOREJCBL,FSAQJCBG,FSAQXZWS,FSAQQTZL,FSAQCLYJS,FSAQZBZL,FSAQSPZL,FSAQLYZL,FSAQZP,FSAQHPPFWJ,FSAQYSPFWJ");
		               //根据任务类型获取监察笔录
					   $.ajax({
						  url: "jcjl.json?tasktype=22&taskid="+applyId,
						  success:function(data){
						  	$('#JCJL_'+22).html(data.jcjl);
						  }
					   });
		               showCOMMON(rwlxObj);
		               break;
		            case '23'://污染事故现场调查
		               rwlxObj=new RwlxObj("23","污染事故现场调查","WRSGXCDC","WRSGXCDCJCJL,WRSGXCDCMOREJCBL,WRSGXCDCJCJLSMJ,WRSGXCDCJCBG,WRSGXCDCXZWS,WRSGXCDCQTZL,WRSGXCDCCLYJS,WRSGXCDCZBZL,WRSGXCDCSPZL,WRSGXCDCLYZL,WRSGXCDCZP,WRSGXCDCHPPFWJ,WRSGXCDCYSPFWJ");
		               //根据任务类型获取监察笔录
					   $.ajax({
						  url: "jcjl.json?tasktype=23&taskid="+applyId,
						  success:function(data){
						  	$('#JCJL_'+23).html(data.jcjl);
						  }
					   });
		               showCOMMON(rwlxObj);
		               break;
		             case '24'://日常办公
		               rwlxObj=new RwlxObj("24","日常办公","RCBG","RCBGZBZL,RCBGBLZL");
		               $.ajax({
							  url: "rcbgDesc.json?tasktype=24&taskid="+applyId,
							  success:function(data){
							  	$('#JCJL_'+24).html(data.desc);
							  }
						   });
		               showCOMMON(rwlxObj);
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
    //显示
    $('#DIV_'+rwlxObj.code).show();
    
    var fileTypeNum=rwlxObj.fileType.split(',');
    var canDelStr="";
    for(var i=0;i<fileTypeNum.length;i++){
        if(i<fileTypeNum.length-1){
           canDelStr+="N,";
        }else{
           canDelStr+="N";
        }
    }
    //赋值
    $('#'+rwlxObj.pyjx+'data').datagrid({
        pagination:true,//分页控件
        height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns:true,
		url:'queryFileListMulfileType.json?canDel='+canDelStr,
		queryParams:{pid:$("#applyId").val(),fileType:rwlxObj.fileType,tableId:rwlxObj.pyjx+'data'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:50},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:500},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:100},
			{field:'operate',title:'操作', align:'center', halign:'center',width:100}
		]]
	});
}
/////////////////////准备工作的展示方法end///////////////////////////
//查看执法对象信息
function zfdxInfo(obj){
    //All.ShowModalWin('lawobjInfo.htm?id='+obj.id, );
    //window.open('lawobjInfo.htm?id='+obj.id,'执法对象信息查看',screen.width * 0.8,screen.height * 0.8-50);
	
  	parent.parent.layer.open({
  		type:2,
  		content:'lawobjInfo.htm?id='+obj.id,
  	    title:'执法对象信息查看',
  	    area:['1100px', ($(window).height()+120) +'px']
  	   });
   
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

//返回
function fanhui(){
	window.history.back();
	
}
</script>