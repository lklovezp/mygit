<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>任务管理——任务办理</title>
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
    <!--时间插件 my97-->
    <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
    <!-- 任务管理 css-->
    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${common}/All.js"></script>
    <style type="text/css">
    #legalselect .combo{border: none;}
    #legalselect .combo .combo-text{padding-left: 10px; padding-right: 0;}
    #legalselect .combo .combo-arrow{background-color: transparent;}
    .f_operate {
    background: #ffffff;
    border-bottom: 0px solid #d4d4d4;
    border-top: 0px solid #d4d4d4;
    }
    .window {
    padding: 0px;
    border-style: none;
    
	}
	.window .window-body{
	border-color: #d4d4d4;
	}
	.panel-header, .panel-body {
    border-color: #d4d4d4;
    }
    </style>
</head>
<body style="background-color: #ffffff;padding-bottom: 60px;">
<form id="blForm" name="blForm">
<div class="dataDiv" style="width:95%;min-width: 1020px; margin:16px auto 25px;">
        <input type="hidden" id="applyId" name="applyId" value="${work.id}" />
        <input type="hidden" id="zfdxInfo" name="zfdxInfo" value="${zfdxInfo}" />
		<input type="hidden" id="taskId" name="taskId" value="${taskId}" />
		<input type="hidden" id="rwlxIds" name="rwlxIds" value="${rwlxIds}" />
    <!--task_step end -->
    <c:if test='${rwlxIds != "24" && zfdxInfo == "Y"}'>
    <h3 class="task_h3 mt25"><span class="h_icon"></span>企业信息</h3>
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <input type="hidden"  name="blZfdxForm.id" value="${blMainForm.blZfdxForm.id}" />
		<input type="hidden" id="lawobjId" name="blZfdxForm.lawobjid" value="${blMainForm.blZfdxForm.lawobjid}" />
		<input type="hidden" id="lawobjType" name="blZfdxForm.lawobjtype" value="${blMainForm.blZfdxForm.lawobjtype}" />
        <tr bgcolor="#cff1ff" height="48">
            <th colspan="4" align="left">企业信息</th>
        </tr>
        <tr height="48">
            <td width="12%" align="center">执法对象</td>
            <td colspan="3">
              ${blMainForm.blZfdxForm.lawobjname}<c:if test="${blMainForm.blZfdxForm.qysczt != null}">（${blMainForm.blZfdxForm.qysczt}）</c:if>
            </td>
        </tr>
        <tr height="48">
            <td width="12%" align="center">地址</td>
            <td colspan="3">${blMainForm.blZfdxForm.address}</td>
        </tr>
        <tr height="48">
            <td width="20%" align="center" id="legalselect"><span class="red">*</span>
            <input type="text" class="y-text" style="width:100px; border: none"  id="zwtitle" name="blZfdxForm.zwtitle"/>
            </td>
            <td width="30%">${blMainForm.blZfdxForm.manager}</td>
            <td width="12%" align="center">联系电话</td>
            <td width="38%">${blMainForm.blZfdxForm.managermobile}</td>
        </tr>
    </table>
    <!-- 任务信息 end-->
    <table class="dataTable mt25"  width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
        <tr>
            <td width="12%" align="right"><span class="red">*</span> 环保负责人</td>
            <td width="22%">
            	<input type="text" class="y-text easyui-validatebox" data-options="required:true" style="width:200px;" name="blZfdxForm.bjcr" value="${blMainForm.blZfdxForm.bjcr}"/>
            </td>
            <td width="8%" align="right"><span class="red">*</span> 职务</td>
            <td width="20%">
            	<input type="text" class="y-text" data-options="required:true" style="width:200px;" id="zw" name="blZfdxForm.zw"/>
            </td>
            <td width="10%" align="right"><span class="red">*</span> 联系电话</td>
            <td width="20%">
            	<input type="text" class="y-text easyui-validatebox"  data-options="required:true" style="width:200px;" name="blZfdxForm.lxdh" value="${blMainForm.blZfdxForm.lxdh}" />
            </td>
        </tr>
    </table>
    
    <hr class="mt25" style="height: 2px;border: none; border-bottom: 2px dashed #d4d4d4" />
    </c:if>
    <!--现场监察 -->
    <c:if test="${blMainForm.blRcjcForm != null }">
    <div class="supervision">
        <h3 class="task_h3 mt25"><span class="h_icon"></span>现场监察</h3>
        <input type="hidden" id="rwlx" name="blRcjcForm.rwlx" value="${blMainForm.blRcjcForm.rwlx}" />
        <table class="dataTable mt25"  width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center"><span class="red">*</span> 检查人</td>
                <td>
                	<c:forEach items="${jcrList}" var="item">
						<c:choose>
							<c:when test="${fn:contains(blMainForm.blRcjcForm.jcr,item.id)}">
								<input checked name="blRcjcForm.jcr" id="RCJCJcr" type="checkbox" value="${item.id}" /> ${item.name}
							</c:when>
							<c:otherwise> 
								<input name="blRcjcForm.jcr" id="RCJCJcr" type="checkbox" value="${item.id}" /> ${item.name}
							</c:otherwise>
						</c:choose>
					</c:forEach>
	               
                </td>
                <td align="center"><span class="red">*</span> 检查时间</td>
                <td>
                <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_from0" name="blRcjcForm.jcsj1" value="${blMainForm.blRcjcForm.jcsj1}"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\'rcjc_to1\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"> 
                                       至 <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_to1" name="blRcjcForm.jcsj2" value="${blMainForm.blRcjcForm.jcsj2}"  onclick="WdatePicker({minDate:'#F{$dp.$D(\'rcjc_from0\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
            </tr>
            <tr>
                <td align="center"><span class="red">*</span> 记录人</td>
                <td><input type="text" class="y-text" style="width:200px;"  id="RCJCJlr" name="blRcjcForm.jlr" value="${blMainForm.blRcjcForm.jlr}"/></td>
                <td align="center">检查地点</td>
                <td><input type="text" class="y-text" maxlength="100"  onKeyUp="return textMaxLen(this);" onBlur="return textMaxLen(this);" maxlength="100" style="width:365px;" id="RCJCJcdd" name="blRcjcForm.jcdd" value="${blMainForm.blRcjcForm.jcdd}"/></td>
            </tr>
            <c:if test="${blMainForm.blRcjcForm.isexecchecklist == '0'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="3">
	                <input type="text" class="y-text" id="RCJCjcmbId" name="blRcjcForm.jcmbId" value="${blMainForm.blRcjcForm.jcmbId}"/>
	                <a class='o_btn btn_blue opm' onclick='commonDoCheck("10")'>进行检查</a>
	                <a class='o_btn btn_blue opm'   onclick='commonMoreCheck("10")' style="padding: 5px 16px; margin-left: 20px;">再次检查</a>
                </td>
            </tr>
            </c:if>
            <c:if test="${blMainForm.blRcjcForm.isexecchecklist == '1'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="1">
                <a class='o_btn btn_blue opm' onclick='singleDownload("${blMainForm.blRcjcForm.jcmbFileId}")'>下载监察模板</a>&nbsp;
                </td>
                <td colspan="2">
                <a class='o_btn btn_blue opm' onclick="singleUpload1('RCJCJCJL')">上传监察笔录</a>&nbsp;
                </td>
            </tr>
            </c:if>
            <tr>
                <td align="center"><span class="red">*</span> 监察笔录</td>
                <td colspan="3">
                   <label id="filename_RCJCJCJL">
					<c:forEach items="${blMainForm.blRcjcForm.jcjlFileMap}" var="item">
						${item.filename}
						<a class='o_btn btn_blue opm' onclick="review(this)" id="${item.id},${item.filesize1}" >预览</a>&nbsp;
						<a class='o_btn btn_blue opm' onclick="download2('${item.id}')">下载</a>&nbsp;
						<a class='o_btn btn_orange opm' onclick="deleteJcbl('${item.id}','${item.fileTypeEnumName}','${item.moreCheckFiletype}','10')">删除</a>&nbsp;
						<c:if test = "${item.type eq '1'}">
						<a class='o_btn btn_blue opm' onclick="editMoreCheck('10','${item.id}')">修改</a>&nbsp;
						</c:if>
						<br/><br/>
					</c:forEach>
			       </label>
                </td>
            </tr>
            <tr>
                <td align="center" valign="top"><span class="red">*</span> 监察结论</td>
                <td colspan="3">
                <textarea class="y-textarea"  name="blRcjcForm.jcjl" id="jcjl" onpropertychange="if(value.length>500) value=value.substr(0,500)" style="width: 80%; height:120px; float:left" >${blMainForm.blRcjcForm.jcjl}</textarea>
                <a href="javascript:void(0);" name="zfwj" style="width: 20px; display: block; float:left; margin-left: 20px; color: #00a2d9; line-height: 16px;">查看相关执法文件</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            <a class="b_link" id="RCJCfileupload" style="float:right;" >上传附件</a>
            相关附件
            <div class="annexselect">
                <input type="text" class="y-text" style="width:200px;" id="RCJCfiletype"/>
            </div>
        </div>
        <div class="annex_con" style="height: 248px;">
            <table id="RCJCdata" fit="true" ></table>
        </div>
    </div>
    </c:if>
    <!--年度核查 -->
    <c:if test="${blMainForm.blNdhcForm != null }">
    <div class="supervision">
        <h3 class="task_h3 mt25"><span class="h_icon"></span>年度核查</h3>
        <input type="hidden" id="rwlx" name="blNdhcForm.rwlx" value="${blMainForm.blNdhcForm.rwlx}" />
        <table class="dataTable mt25"  width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center"><span class="red">*</span> 检查人</td>
                <td>
                	<c:forEach items="${jcrList}" var="item">
									<c:choose>
										<c:when test="${fn:contains(blMainForm.blNdhcForm.jcr,item.id)}">
											<input checked name="blNdhcForm.jcr" id="NDHCJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:when>
										<c:otherwise> 
											<input name="blNdhcForm.jcr" id="NDHCJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:otherwise>
									</c:choose>
					</c:forEach>
	               
                </td>
                <td align="center"><span class="red">*</span> 检查时间</td>
                <td>
                <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_from2" name="blNdhcForm.jcsj1" value="${blMainForm.blNdhcForm.jcsj1}"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\'rcjc_to3\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"> 
                                       至 <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_to3" name="blNdhcForm.jcsj2" value="${blMainForm.blNdhcForm.jcsj2}"  onclick="WdatePicker({minDate:'#F{$dp.$D(\'rcjc_from2\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
            </tr>
            <tr>
                <td align="center"><span class="red">*</span> 记录人</td>
                <td><input type="text" class="y-text" style="width:200px;"  id="NDHCJlr" name="blNdhcForm.jlr" value="${blMainForm.blNdhcForm.jlr}"/></td>
                <td align="center">检查地点</td>
                <td><input type="text" class="y-text" maxlength="100" style="width:365px;" id="NDHCJcdd" name="blNdhcForm.jcdd" value="${blMainForm.blNdhcForm.jcdd}"/></td>
            </tr>
            <c:if test="${blMainForm.blNdhcForm.isexecchecklist == '0'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="3">
	                <input type="text" class="y-text" id="NDHCjcmbId" name="blNdhcForm.jcmbId" value="${blMainForm.blNdhcForm.jcmbId}"/>
	                <a class='o_btn btn_blue opm' onclick='commonDoCheck("11")'>进行检查</a>
	                <a class='o_btn btn_blue opm'   onclick='commonMoreCheck("11")' style="padding: 5px 16px; margin-left: 20px;">再次检查</a>
                </td>
            </tr>
            </c:if>
            <c:if test="${blMainForm.blNdhcForm.isexecchecklist == '1'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="1">
                <a class='o_btn btn_blue opm'  onclick='singleDownload("${blMainForm.blNdhcForm.jcmbFileId}")'>下载监察模板</a>&nbsp;
                </td>
                <td colspan="2">
                <a class='o_btn btn_blue opm' onclick="singleUpload1('NDHCJCJL')">上传监察笔录</a>&nbsp;
                </td>
            </tr>
            </c:if>
            <tr>
                <td align="center"><span class="red">*</span> 监察笔录</td>
                <td colspan="3">
                   <label id="filename_NDHCJCJL">
					<c:forEach items="${blMainForm.blNdhcForm.jcjlFileMap}" var="item">
						${item.filename}
						<a class='o_btn btn_blue opm' onclick="review(this)" id="${item.id},${item.filesize1}" >预览</a>&nbsp;
						<a class='o_btn btn_blue opm' onclick="download2('${item.id}')">下载</a>&nbsp;
						<a class='o_btn btn_orange opm' onclick="deleteJcbl('${item.id}','${item.fileTypeEnumName}','${item.moreCheckFiletype}','11')">删除</a>&nbsp;
						<c:if test = "${item.type eq '1'}">
						<a class='o_btn btn_blue opm' onclick="editMoreCheck('11','${item.id}')">修改</a>&nbsp;
						</c:if>
						<br/><br/>
					</c:forEach>
			       </label>
                </td>
            </tr>
            <tr>
                <td align="center" valign="top"><span class="red">*</span> 监察结论</td>
                <td colspan="3">
                <textarea class="y-textarea" name="blNdhcForm.jcjl" id="jcjl" onpropertychange="if(value.length>500) value=value.substr(0,500)" style="width: 80%; height:120px; float:left" >${blMainForm.blNdhcForm.jcjl}</textarea>
                <a href="javascript:void(0);" name="zfwj" style="width: 20px; display: block; float:left; margin-left: 20px; color: #00a2d9; line-height: 16px;">查看相关执法文件</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            <a class="b_link" id="NDHCfileupload" style="float:right;" >上传附件</a>
            相关附件
            <div class="annexselect">
                <input type="text" class="y-text" style="width:200px;" id="NDHCfiletype"/>
            </div>
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="NDHCdata" fit="true"></table>
        </div>
    </div>
    </c:if>
    <!--后督察 -->
    <c:if test="${blMainForm.blHdcForm != null }">
    <div class="supervision">
        <h3 class="task_h3 mt25"><span class="h_icon"></span>后督察</h3>
        <input type="hidden" id="rwlx" name="blHdcForm.rwlx" value="${blMainForm.blHdcForm.rwlx}" />
        <table class="dataTable mt25"  width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center"><span class="red">*</span> 检查人</td>
                <td>
                	<c:forEach items="${jcrList}" var="item">
									<c:choose>
										<c:when test="${fn:contains(blMainForm.blHdcForm.jcr,item.id)}">
											<input checked name="blHdcForm.jcr" id="HDCJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:when>
										<c:otherwise> 
											<input name="blHdcForm.jcr" id="HDCJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:otherwise>
									</c:choose>
					</c:forEach>
	               
                </td>
                <td align="center"><span class="red">*</span> 检查时间</td>
                <td>
                <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_from4" name="blHdcForm.jcsj1" value="${blMainForm.blHdcForm.jcsj1}"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\'rcjc_to5\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"> 
                                       至 <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_to5" name="blHdcForm.jcsj2" value="${blMainForm.blHdcForm.jcsj2}"  onclick="WdatePicker({minDate:'#F{$dp.$D(\'rcjc_from4\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
            </tr>
            <tr>
                <td align="center"><span class="red">*</span> 记录人</td>
                <td><input type="text" class="y-text" style="width:200px;" id="HDCJlr" name="blHdcForm.jlr" value="${blMainForm.blHdcForm.jlr}"/></td>
                <td align="center">检查地点</td>
                <td><input type="text" class="y-text" maxlength="100" style="width:365px;" id="HDCJcdd" name="blHdcForm.jcdd" value="${blMainForm.blHdcForm.jcdd}"/></td>
            </tr>
            <c:if test="${blMainForm.blHdcForm.isexecchecklist == '0'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="3">
	                <input type="text" class="y-text" id="HDCjcmbId" name="blHdcForm.jcmbId" value="${blMainForm.blHdcForm.jcmbId}"/>
	                <a class='o_btn btn_blue opm' onclick='commonDoCheck("12")'>进行检查</a>
	                <a class='o_btn btn_blue opm'   onclick='commonMoreCheck("12")' style="padding: 5px 16px; margin-left: 20px;">再次检查</a>
                </td>
            </tr>
            </c:if>
            <c:if test="${blMainForm.blHdcForm.isexecchecklist == '1'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="1">
                <a class='o_btn btn_blue opm' onclick='singleDownload("${blMainForm.blHdcForm.jcmbFileId}")'>下载监察模板</a>&nbsp;
                </td>
                <td colspan="2">
                <a class='o_btn btn_blue opm' onclick="singleUpload('HDCJCJL')">上传监察笔录</a>&nbsp;
                </td>
            </tr>
            </c:if>
            <tr>
                <td align="center"><span class="red">*</span> 监察笔录</td>
                <td colspan="3">
                   <label id="filename_HDCJCJL">
					<c:forEach items="${blMainForm.blHdcForm.jcjlFileMap}" var="item">
						${item.filename}
						<a class='o_btn btn_blue opm' onclick="review(this)" id="${item.id},${item.filesize1}" >预览</a>&nbsp;
						<a class='o_btn btn_blue opm' onclick="download2('${item.id}')">下载</a>&nbsp;
						<a class='o_btn btn_orange opm' onclick="deleteJcbl('${item.id}','${item.fileTypeEnumName}','${item.moreCheckFiletype}','12')">删除</a>&nbsp;
						<c:if test = "${item.type eq '1'}">
						<a class='o_btn btn_blue opm' onclick="editMoreCheck('12','${item.id}')">修改</a>&nbsp;
						</c:if>
						<br/><br/>
					</c:forEach>
			       </label>
                </td>
            </tr>
            <tr>
                <td align="center" valign="top"><span class="red">*</span> 监察结论</td>
                <td colspan="3">
                <textarea class="y-textarea" name="blHdcForm.jcjl" id="jcjl" onpropertychange="if(value.length>500) value=value.substr(0,500)" style="width: 80%; height:120px; float:left" >${blMainForm.blHdcForm.jcjl}</textarea>
                <a href="javascript:void(0);" name="zfwj" style="width: 20px; display: block; float:left; margin-left: 20px; color: #00a2d9; line-height: 16px;">查看相关执法文件</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            <a class="b_link" id="HDCfileupload" style="float:right;" >上传附件</a>
            相关附件
            <div class="annexselect">
                <input type="text" class="y-text" style="width:200px;" id="HDCfiletype"/>
            </div>
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="HDCdata" fit="true"></table>
        </div>
    </div>
    </c:if>
    <!--信访投诉 -->
    <c:if test="${blMainForm.blXftsForm != null}">
	<c:if test="${zfdxInfo == 'Y'}">
    <div class="supervision">
        <h3 class="task_h3 mt25"><span class="h_icon"></span>信访投诉</h3>
        <input type="hidden" id="rwlx" name="blXftsForm.rwlx" value="${blMainForm.blXftsForm.rwlx}" />
        <table class="dataTable mt25"  width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center"><span class="red">*</span> 检查人</td>
                <td>
                	<c:forEach items="${jcrList}" var="item">
						<c:choose>
							<c:when test="${fn:contains(blMainForm.blXftsForm.jcr,item.id)}">
								<input type="checkbox" checked="checked" name="blXftsForm.jcr" id="XFTSJcr" type="checkbox" value="${item.id}" /> ${item.name}
							</c:when>
							<c:otherwise> 
								<input name="blXftsForm.jcr" id="XFTSJcr" type="checkbox" value="${item.id}" /> ${item.name}
							</c:otherwise>
						</c:choose>
					</c:forEach>
	               
                </td>
                <td align="center"><span class="red">*</span> 检查时间</td>
                <td>
                <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_from6" name="blXftsForm.jcsj1" value="${blMainForm.blXftsForm.jcsj1}"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\'rcjc_to7\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"> 
                                       至 <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_to7" name="blXftsForm.jcsj2" value="${blMainForm.blXftsForm.jcsj2}"  onclick="WdatePicker({minDate:'#F{$dp.$D(\'rcjc_from6\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
            </tr>
            <tr>
                <td align="center"><span class="red">*</span> 记录人</td>
                <td><input type="text" class="y-text" style="width:200px;" id="XFTSJlr" name="blXftsForm.jlr" value="${blMainForm.blXftsForm.jlr}"/></td>
                <td align="center">检查地点</td>
                <td><input type="text" class="y-text" maxlength="100" style="width:365px;" id="XFTSJcdd" name="blXftsForm.jcdd" value="${blMainForm.blXftsForm.jcdd}"/></td>
            </tr>
            <tr> 
				<td> 
				 <span class="red">*</span>信访投诉来源
				</td> 
				<td colspan="3"> 
				<input type="text" class="y-text" id="xftsly" name="blXftsForm.xftsly" value="${blMainForm.blXftsForm.xftsly}"/>
				</td> 
			</tr> 
            <c:if test="${blMainForm.blXftsForm.isexecchecklist == '0'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="3">
	                <input type="text" class="y-text" id="XFTSjcmbId" name="blXftsForm.jcmbId" value="${blMainForm.blXftsForm.jcmbId}"/>
	                <a class='o_btn btn_blue opm' onclick='commonDoCheck("13")'>进行检查</a>
	                <a class='o_btn btn_blue opm'   onclick='commonMoreCheck("13")' style="padding: 5px 16px; margin-left: 20px;">再次检查</a>
                </td>
            </tr>
            </c:if>
           <c:if test="${blMainForm.blXftsForm.isexecchecklist == '1'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="1">
                <a class='o_btn btn_blue opm' onclick='singleDownload("${blMainForm.blXftsForm.jcmbFileId}")'>下载监察模板</a>&nbsp;
                </td>
                <td colspan="2">
                <a class='o_btn btn_blue opm' onclick="singleUpload('XFTSJCJL')">上传监察笔录</a>&nbsp;
                </td>
            </tr>
            </c:if>
            <tr>
                <td align="center"><span class="red">*</span> 监察笔录</td>
                <td colspan="3">
                   <label id="filename_XFTSJCJL">
					<c:forEach items="${blMainForm.blXftsForm.jcjlFileMap}" var="item">
						${item.filename}
						<a class='o_btn btn_blue opm' onclick="review(this)" id="${item.id},${item.filesize1}" >预览</a>&nbsp;
						<a class='o_btn btn_blue opm' onclick="download2('${item.id}')">下载</a>&nbsp;
						<a class='o_btn btn_orange opm' onclick="deleteJcbl('${item.id}','${item.fileTypeEnumName}','${item.moreCheckFiletype}','13')">删除</a>&nbsp;
						<c:if test = "${item.type eq '1'}">
						<a class='o_btn btn_blue opm' onclick="editMoreCheck('13','${item.id}')">修改</a>&nbsp;
						</c:if>
						<br/><br/>
					</c:forEach>
			       </label>
                </td>
            </tr>
            <tr>
                <td align="center" valign="top"><span class="red">*</span> 监察结论</td>
                <td colspan="3">
                <textarea class="y-textarea" name="blXftsForm.jcjl" id="jcjl" onpropertychange="if(value.length>500) value=value.substr(0,500)"  style="width: 80%; height:120px; float:left" >${blMainForm.blXftsForm.jcjl}</textarea>
                <a href="javascript:void(0);" name="zfwj" style="width: 20px; display: block; float:left; margin-left: 20px; color: #00a2d9; line-height: 16px;">查看相关执法文件</a>
                </td>
            </tr>
        </table>
    </div>
    </c:if>
    </c:if>
    <c:if test='${fn:contains(rwlxIds,"13")}'>
    <div class="supervision">
        <h3 class="task_h3 mt25"><span class="h_icon"></span>信访办理情况</h3>
        <input type="hidden" name="xfbjdForm.id" id="xftsId" value="${xfbjdForm.id}" />
		<!-- <input type="hidden" name="xfbjdForm.slsj" value="${xfbjdForm.slsj}" /> -->
        <table class="dataTable mt25"  width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
        <tr>
          <td align="center" valign="top">环境信访办理情况</td>
          <td colspan="3">
          <textarea class="y-textarea" name="xfbjdForm.hjxfblqk" id="hjxfblqk" onpropertychange="if(value.length>500) value=value.substr(0,500)" style="width: 80%; height:120px; float:left" >${xfbjdForm.hjxfblqk}</textarea>
          </td>
        </tr>
        <tr> 
			<td align="center" style="width:170px;"> 
			办结情况
			</td> 
			<td colspan="3">
			<c:if test="${xfbjdForm.bjqk == '1'}">
				<input id="bjqk" name="xfbjdForm.bjqk" type="radio" value="1" checked="checked">已办结</br>
     			<input id="bjqk" name="xfbjdForm.bjqk" type="radio" value="999">已办理&nbsp;&nbsp;
     			下次办结时限&nbsp;&nbsp;<input type="text" style="width: 120px;" class="y-dateTime" id="slsj" name="xfbjdForm.slsj" value="${xfbjdForm.slsj}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></br>
     			<input id="bjqk" name="xfbjdForm.bjqk" type="radio" value="0">信访终结
     		</c:if>
     		<c:if test="${xfbjdForm.bjqk == '0'}">
				<input id="bjqk" name="xfbjdForm.bjqk" type="radio" value="1">已办结</br>
     			<input id="bjqk" name="xfbjdForm.bjqk" type="radio" value="999">已办理&nbsp;&nbsp;
     			下次办结时限&nbsp;&nbsp;<input type="text" style="width: 120px;" class="y-dateTime" id="slsj" name="xfbjdForm.slsj" value="${xfbjdForm.slsj}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></br>
     			<input id="bjqk" name="xfbjdForm.bjqk" type="radio" value="0" checked="checked">信访终结
     		</c:if>
     		<c:if test="${xfbjdForm.bjqk == '999'}">
				<input id="bjqk" name="xfbjdForm.bjqk" type="radio" value="1">已办结</br>
     			<input id="bjqk" name="xfbjdForm.bjqk" type="radio" value="999" checked="checked">已办理&nbsp;&nbsp;
     			下次办结时限&nbsp;&nbsp;<input type="text" style="width: 120px;" class="y-dateTime" id="slsj" name="xfbjdForm.slsj" value="${xfbjdForm.slsj}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></br>
     			<input id="bjqk" name="xfbjdForm.bjqk" type="radio" value="0">信访终结
     		</c:if>
			<!-- <input type="hidden" id="bjqk" name="xfbjdForm.bjqk" value="${xfbjdForm.bjqk}"/>
			<span id="bjqkInfo">${xfbjdForm.bjqk}次办结</span>
			<span id="zcbl">
			<a class='o_btn btn_orange opm' onclick="zcbl()">再次办理</a>
			</span> -->
			</td>
		</tr> 
        </table>
    </div>
    <!-- <div class="supervision">
        <h3 class="task_h3 mt25"><span class="h_icon"></span>信访回复和报出情况</h3>
        <table class="dataTable mt25"  width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center">回访形式</td>
                <td colspan="3">
                	<input class="y-text" type="text" id="hfxs" name="xfbjdForm.hfxs" value="${xfbjdForm.hfxs}"/>
					<input class="y-text" type="text" id="hfxsxm" maxlength="10" name="xfbjdForm.hfxsxm" value="${xfbjdForm.hfxsxm}" tipMsg="姓名"/>
					<input class="y-text" type="text" id="hfxsdyrn" name="xfbjdForm.hfxsdyrn" value="${xfbjdForm.hfxsdyrn}" tipMsg="内容"/>
	               
                </td>
                <td align="center">回访人</td>
                <td>
                    <input type="hidden" id="hfr" name="xfbjdForm.hfr" value="${xfbjdForm.hfr}"/>
					<input class="y-text" type="text" id="hfrName" name="xfbjdForm.hfrName" value="${xfbjdForm.hfrName}" readOnly/>
					<a href="#" id="selectHfr">选择人员</a>
                </td>
            </tr>
            <tr>
                <td align="center">回访日期</td>
                <td>
                <input type="text" class="y-dateTime" id="hfrq" name="xfbjdForm.hfrq" value="${xfbjdForm.hfrq}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" style="width:156px;"/>
                </td>
                <td align="center">返回人</td>
                <td>
                <input class="y-text" type="text" id="fhr" name="xfbjdForm.fhr" value="${xfbjdForm.fhr}"/>
                </td>
                <td align="center">返回日期</td>
                <td>
                <input type="text" class="y-dateTime" id="fhrq" name="xfbjdForm.fhrq" value="${xfbjdForm.fhrq}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" style="width:156px;"/>
                </td>
            </tr>
            <tr> 
				<td align="center"> 
				 接收人
				</td> 
				<td colspan="1"> 
				<input type="hidden" id="jsr" name="xfbjdForm.jsr" value="${xfbjdForm.jsr}"/>${xfbjdForm.jsr}
				</td> 
				<td align="center"> 
				 接收日期
				</td> 
				<td colspan="3"> 
				<input type="text" class="y-dateTime" id="jssj" name="xfbjdForm.jssj" value="${xfbjdForm.jssj}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" style="width:156px;"/>
				
				</td> 
			</tr> 
			<tr>
			    <td align="center" valign="top">报出情况</td>
                <td colspan="5">
                   <textarea class="y-textarea"  name="xfbjdForm.bcqk" id="bcqk" style="width: 80%; height:120px; float:left" >${xfbjdForm.bcqk}</textarea>
                </td>
			</tr>
            <tr> 
				<td align="center"> 
				 报出人
				</td> 
				<td colspan="1"> 
				<input class="y-text" type="text" id="bcr" name="xfbjdForm.bcr" value="${xfbjdForm.bcr}"/>
				</td> 
				<td align="center"> 
				 报出日期
				</td> 
				<td colspan="3">
				<input type="text" class="y-dateTime" id="bcrq" name="xfbjdForm.bcrq" value="${xfbjdForm.bcrq}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" style="width:156px;"/>
				</td> 
			</tr> 
            
        </table>
    </div> -->
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            <a class="b_link" id="XFTSfileupload" style="float:right;" >上传附件</a>
            相关附件
            <div class="annexselect">
                <input type="text" class="y-text" style="width:200px;" id="XFTSfiletype"/>
            </div>
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="XFTSdata" fit="true"></table>
        </div>
    </div>
    </c:if>
     <!--排污许可证检查 -->
    <c:if test="${blMainForm.blPwxkzjcForm != null }">
    <div class="supervision">
        <h3 class="task_h3 mt25"><span class="h_icon"></span>排污许可证检查</h3>
        <input type="hidden" id="rwlx" name="blPwxkzjcForm.rwlx" value="${blMainForm.blPwxkzjcForm.rwlx}" />
        <table class="dataTable mt25"  width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center"><span class="red">*</span> 检查人</td>
                <td>
                	<c:forEach items="${jcrList}" var="item">
									<c:choose>
										<c:when test="${fn:contains(blMainForm.blPwxkzjcForm.jcr,item.id)}">
											<input checked name="blPwxkzjcForm.jcr" id="PWXKZJCJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:when>
										<c:otherwise> 
											<input name="blPwxkzjcForm.jcr" id="PWXKZJCJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:otherwise>
									</c:choose>
					</c:forEach>
	               
                </td>
                <td align="center"><span class="red">*</span> 检查时间</td>
                <td>
                <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_from8" name="blPwxkzjcForm.jcsj1" value="${blMainForm.blPwxkzjcForm.jcsj1}"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\'rcjc_to9\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"> 
                                       至 <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_to9" name="blPwxkzjcForm.jcsj2" value="${blMainForm.blPwxkzjcForm.jcsj2}"  onclick="WdatePicker({minDate:'#F{$dp.$D(\'rcjc_from8\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
            </tr>
            <tr>
                <td align="center"><span class="red">*</span> 记录人</td>
                <td><input type="text" class="y-text" style="width:200px;"  id="PWXKZJCJlr" name="blPwxkzjcForm.jlr" value="${blMainForm.blPwxkzjcForm.jlr}"/></td>
                <td align="center">检查地点</td>
                <td><input type="text" class="y-text" maxlength="100" style="width:365px;" id="PWXKZJCJcdd" name="blPwxkzjcForm.jcdd" value="${blMainForm.blPwxkzjcForm.jcdd}"/></td>
            </tr>
            <c:if test="${blMainForm.blPwxkzjcForm.isexecchecklist == '0'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="3">
	                <input type="text" class="y-text" id="PWXKZJCjcmbId" name="blPwxkzjcForm.jcmbId" value="${blMainForm.blPwxkzjcForm.jcmbId}">
	                <a class='o_btn btn_blue opm' onclick='commonDoCheck("14")'>进行检查</a>
	                <a class='o_btn btn_blue opm'   onclick='commonMoreCheck("14")' style="padding: 5px 16px; margin-left: 20px;">再次检查</a>
                </td>
            </tr>
            </c:if>
            <c:if test="${blMainForm.blPwxkzjcForm.isexecchecklist == '1'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="1">
                <a class='o_btn btn_blue opm' onclick='singleDownload("${blMainForm.blPwxkzjcForm.jcmbFileId}")'>下载监察模板</a>&nbsp;
                </td>
                <td colspan="2">
                <a class='o_btn btn_blue opm' onclick="singleUpload('PWXKZJCJCJL')">上传监察笔录</a>&nbsp;
                </td>
            </tr>
            </c:if>
            <tr>
                <td align="center"><span class="red">*</span> 监察笔录</td>
                <td colspan="3">
                   <label id="filename_PWXKZJCJCJL">
					<c:forEach items="${blMainForm.blPwxkzjcForm.jcjlFileMap}" var="item">
						${item.filename}
						<a class='o_btn btn_blue opm' onclick="review(this)" id="${item.id},${item.filesize1}" >预览</a>&nbsp;
						<a class='o_btn btn_blue opm' onclick="download2('${item.id}')">下载</a>&nbsp;
						<a class='o_btn btn_orange opm' onclick="deleteJcbl('${item.id}','${item.fileTypeEnumName}','${item.moreCheckFiletype}','14')">删除</a>&nbsp;
						<c:if test = "${item.type eq '1'}">
						<a class='o_btn btn_blue opm' onclick="editMoreCheck('14','${item.id}')">修改</a>&nbsp;
						</c:if>
						<br/><br/>
					</c:forEach>
			       </label>
                </td>
            </tr>
            <tr>
                <td align="center" valign="top"><span class="red">*</span> 监察结论</td>
                <td colspan="3">
                <textarea class="y-textarea" name="blPwxkzjcForm.jcjl" id="jcjl" onpropertychange="if(value.length>500) value=value.substr(0,500)" style="width: 80%; height:120px; float:left" >${blMainForm.blPwxkzjcForm.jcjl}</textarea>
                <a href="javascript:void(0);" name="zfwj" style="width: 20px; display: block; float:left; margin-left: 20px; color: #00a2d9; line-height: 16px;">查看相关执法文件</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            <a class="b_link" id="PWXKZJCfileupload" style="float:right;" >上传附件</a>
            相关附件
            <div class="annexselect">
                <input type="text" class="y-text" style="width:200px;" id="PWXKZJCfiletype"/>
            </div>
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="PWXKZJCdata" fit="true"></table>
        </div>
    </div>
    </c:if>
     <!--违法案件调查 -->
    <c:if test="${blMainForm.blWfajdcForm != null }">
    <div class="supervision">
        <h3 class="task_h3 mt25"><span class="h_icon"></span>违法案件调查</h3>
        <table class="dataTable mt25"  width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
            <tr>
                <td align="right" width="15%"><span class="red">*</span> 立案登记时间</td>
                <td width="15%">
	            <input type="text" class="y-dateTime" data-options="required:true,editable:false" name="blWfajdcForm.ladjsj" value="${blMainForm.blWfajdcForm.ladjsj}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:156px;"/>
                </td>
                <td align="right" width="10%">立案登记号</td>
                <td width="55%">
                <input class="y-text" type="text" name="blWfajdcForm.ladjh" value="${blMainForm.blWfajdcForm.ladjh}" />
                </td>
            </tr>
            <tr>
                <td align="right"><span class="red">*</span> 违法类型</td>
                <td colspan="3">
                   <input class="y-text" style="width:405px;" type="text" id="wflx" name="blWfajdcForm.wflxId" />
				   <a id="flyj" style="color: #0088CC;">查看相关法律依据</a>
                </td>
            </tr>
            <tr>
                <td align="right">违法案件名称</td>
                <td colspan="3">
                <input style="width: 89%;" class="y-text easyui-validatebox" data-options="required:true" type="text" id="blWfajdcForm_wfajmc" name="blWfajdcForm.wfajmc" value="${blMainForm.blWfajdcForm.wfajmc}" />
                </td>
            </tr>
            <tr>
                <td align="right"><span class="red">*</span> 记录人</td>
                <td>
                <input class="y-text" type="text" id="WFAJDCJlr" name="blWfajdcForm.jlr" value="${blMainForm.blWfajdcForm.jlr}" />
                </td>
                <td align="right"><span class="red">*</span>调查时间</td>
                <td>
                 <input type="text" style="width: 120px;" class="y-dateTime" data-options="required:true,editable:false" id="rcjc_from10" name="blWfajdcForm.dcsj1" value="${blMainForm.blWfajdcForm.dcsj1}"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\'rcjc_to11\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"> 
                                       至 <input type="text" style="width: 120px;" class="y-dateTime" data-options="required:true,editable:false" id="rcjc_to11" name="blWfajdcForm.dcsj2" value="${blMainForm.blWfajdcForm.dcsj2}" onclick="WdatePicker({minDate:'#F{$dp.$D(\'rcjc_from10\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                </td>
            </tr>
            <tr>
                <td align="right">参与执法人员</td>
                <td>
                ${blMainForm.blWfajdcForm.cyzfry}
                </td>
                <td align="right">案件整理人</td>
                <td>
                <input type="hidden" name="blWfajdcForm.jzzlr" value="${blMainForm.blWfajdcForm.jzzlr}" />
                ${blMainForm.blWfajdcForm.jzzlr}
                </td>
            </tr>
			<tr>
				<td align="right"><span class="red">*</span>询问笔录</td>
				<td align="left">
					<a style="color: #0088CC;" id="zzxwbl"  onclick="saveBeforeNext('xwbl')">制作笔录</a>&nbsp; 
					<a style="color: #0088CC;" onclick="singleUploadKcxwblCheck('xwbl')">上传附件</a>&nbsp;
				</td>
				<td  colspan="2">
					<label id="filename_xwbl"> 
					<c:if test="${blMainForm.blWfajdcForm.xwblFileMap!=null}">
						${blMainForm.blWfajdcForm.xwblFileMap.filename}
					</c:if> 
					<br/><br/>
					</label>&nbsp;&nbsp;&nbsp;&nbsp;
					<label id="oper_xwbl"> 
					<c:if test="${blMainForm.blWfajdcForm.xwblFileMap!=null}">
					<a class="o_btn btn_blue opm" onclick="review(this)" id="${blMainForm.blWfajdcForm.xwblFileMap.id},${blMainForm.blWfajdcForm.xwblFileMap.filesize1}">预览</a>&nbsp;
					<a class="o_btn btn_blue opm" onclick="download2('${blMainForm.blWfajdcForm.xwblFileMap.id}')">下载</a>&nbsp;
					<a class="o_btn btn_orange opm" onclick="singleDeleteKcxwbl('${blMainForm.blWfajdcForm.xwblFileMap.id}','xwbl')">删除</a>&nbsp;
					</c:if>
				</label>
				</td>
			</tr>
			<tr>
			    <td align="right">
					<span class="red">*</span>勘察笔录
				</td>
				<td align="left">
					<a style="color: #0088CC;" id="zzkcbl"  onclick="saveBeforeNext('kcbl')">制作笔录</a>&nbsp;
					<a style="color: #0088CC;" id=""  onclick="singleUploadKcxwblCheck('kcbl')">上传附件</a>&nbsp;
				</td>
							
				<td colspan="2">
					<label id="filename_kcbl">
					<c:if test="${blMainForm.blWfajdcForm.kcblFileMap!=null}">
						${blMainForm.blWfajdcForm.kcblFileMap.filename}
					</c:if><br/><br/>
					</label>&nbsp;&nbsp;&nbsp;&nbsp;
					<label id="oper_kcbl">
					<c:if test="${blMainForm.blWfajdcForm.kcblFileMap!=null}">
						<a class="o_btn btn_blue opm" onclick="review(this)" id="${blMainForm.blWfajdcForm.kcblFileMap.id},${blMainForm.blWfajdcForm.kcblFileMap.filesize1}" >预览</a>&nbsp;
						<a class="o_btn btn_blue opm" onclick="download2('${blMainForm.blWfajdcForm.kcblFileMap.id}')">下载</a>&nbsp;
						<a class="o_btn btn_orange opm" onclick="singleDeleteKcxwbl('${blMainForm.blWfajdcForm.kcblFileMap.id}','kcbl')">删除</a>&nbsp;
					</c:if>
					</label>
				</td>
			</tr>
        </table>
    </div>
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            <a class="b_link" id="WFAJDCfileupload" style="float:right;" >上传附件</a>
            相关附件
            <div class="annexselect">
                <input type="text" class="y-text" style="width:200px;" id="WFAJDCfiletype"/>
            </div>
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="WFAJDCdata" fit="true"></table>
        </div>
    </div>
    </c:if>
    <!--限期治理 -->
    <c:if test="${blMainForm.blXqzlForm != null }">
    <div class="supervision">
        <h3 class="task_h3 mt25"><span class="h_icon"></span>限期治理</h3>
        <input type="hidden" id="rwlx" name="blXqzlForm.rwlx" value="${blMainForm.blXqzlForm.rwlx}" />
        <table class="dataTable mt25"  width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center"><span class="red">*</span> 检查人</td>
                <td>
                	<c:forEach items="${jcrList}" var="item">
									<c:choose>
										<c:when test="${fn:contains(blMainForm.blXqzlForm.jcr,item.id)}">
											<input checked name="blXqzlForm.jcr" id="XQZLJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:when>
										<c:otherwise> 
											<input name="blXqzlForm.jcr" id="XQZLJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:otherwise>
									</c:choose>
					</c:forEach>
	               
                </td>
                <td align="center"><span class="red">*</span> 检查时间</td>
                <td>
                <input type="text" style="width: 120px;" class="y-dateTime" data-options="required:true,editable:false" id="rcjc_from12" name="blXqzlForm.jcsj1" value="${blMainForm.blXqzlForm.jcsj1}"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\'rcjc_to13\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"> 
                                       至 <input type="text" style="width: 120px;" class="y-dateTime" data-options="required:true,editable:false" id="rcjc_to13" name="blXqzlForm.jcsj2" value="${blMainForm.blXqzlForm.jcsj2}"  onclick="WdatePicker({minDate:'#F{$dp.$D(\'rcjc_from12\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
            </tr>
            <tr>
                <td align="center"><span class="red">*</span> 记录人</td>
                <td><input type="text" class="y-text" style="width:200px;" id="XQZLJlr" name="blXqzlForm.jlr" value="${blMainForm.blXqzlForm.jlr}"/></td>
                <td align="center">检查地点</td>
                <td><input type="text" class="y-text" maxlength="100" style="width:365px;" id="XQZLJcdd" name="blXqzlForm.jcdd" value="${blMainForm.blXqzlForm.jcdd}"/></td>
            </tr>
            <c:if test="${blMainForm.blXqzlForm.isexecchecklist == '0'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="3">
	                <input type="text" class="y-text" id="XQZLjcmbId" name="blXqzlForm.jcmbId" value="${blMainForm.blXqzlForm.jcmbId}"/>
	                <a class='o_btn btn_blue opm' onclick='commonDoCheck("17")'>进行检查</a>
	                <a class='o_btn btn_blue opm'   onclick='commonMoreCheck("17")' style="padding: 5px 16px; margin-left: 20px;">再次检查</a>
                </td>
            </tr>
            </c:if>
            <c:if test="${blMainForm.blXqzlForm.isexecchecklist == '1'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="1">
                <a class='o_btn btn_blue opm' onclick='singleDownload("${blMainForm.blXqzlForm.jcmbFileId}")'>下载监察模板</a>&nbsp;
                </td>
                <td colspan="2">
                <a class='o_btn btn_blue opm' onclick="singleUpload('XQZLJCJL')">上传监察笔录</a>&nbsp;
                </td>
            </tr>
            </c:if>
            <tr>
                <td align="center"><span class="red">*</span> 监察笔录</td>
                <td colspan="3">
                   <label id="filename_XQZLJCJL">
					<c:forEach items="${blMainForm.blXqzlForm.jcjlFileMap}" var="item">
						${item.filename}
						<a class='o_btn btn_blue opm' onclick="review(this)" id="${item.id},${item.filesize1}" >预览</a>&nbsp;
						<a class='o_btn btn_blue opm' onclick="download2('${item.id}')">下载</a>&nbsp;
						<a class='o_btn btn_orange opm' onclick="deleteJcbl('${item.id}','${item.fileTypeEnumName}','${item.moreCheckFiletype}','17')">删除</a>&nbsp;
						<c:if test = "${item.type eq '1'}">
						<a class='o_btn btn_blue opm' onclick="editMoreCheck('17','${item.id}')">修改</a>&nbsp;
						</c:if>
						<br/><br/>
					</c:forEach>
			       </label>
                </td>
            </tr>
            <tr>
                <td align="center" valign="top"><span class="red">*</span> 监察结论</td>
                <td colspan="3">
                <textarea class="y-textarea" name="blXqzlForm.jcjl" id="jcjl" onpropertychange="if(value.length>500) value=value.substr(0,500)" style="width: 80%; height:120px; float:left" >${blMainForm.blXqzlForm.jcjl}</textarea>
                <a href="javascript:void(0);" name="zfwj" style="width: 20px; display: block; float:left; margin-left: 20px; color: #00a2d9; line-height: 16px;">查看相关执法文件</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            <a class="b_link" id="XQZLfileupload" style="float:right;" >上传附件</a>
            相关附件
            <div class="annexselect">
                <input type="text" class="y-text" style="width:200px;" id="XQZLfiletype"/>
                
            </div>
            <div  style="float: right;">
            <a id="" onclick="gztzAdd()" class="btslink">环境违法行为限期改正通知</a>&nbsp;
			<a id="" onclick="jdssdhzAdd()" class="btslink">行政处罚决定书送达回执</a>&nbsp;
			<a id="" onclick="gzssdhzAdd()" class="btslink">（听证）告知书送达回证</a>&nbsp;
            </div>
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="XQZLdata" fit="true"></table>
        </div>
    </div>
    </c:if>
    <!--跟踪检查-->
    <c:if test="${blMainForm.blGzjcForm != null }">
    <div class="supervision">
        <h3 class="task_h3 mt25"><span class="h_icon"></span>跟踪检查</h3>
        <input type="hidden" id="rwlx" name="blGzjcForm.rwlx" value="${blMainForm.blGzjcForm.rwlx}" />
        <table class="dataTable mt25"  width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center"><span class="red">*</span> 检查人</td>
                <td>
                	<c:forEach items="${jcrList}" var="item">
									<c:choose>
										<c:when test="${fn:contains(blMainForm.blGzjcForm.jcr,item.id)}">
											<input checked name="blGzjcForm.jcr" id="GZJCJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:when>
										<c:otherwise> 
											<input name="blGzjcForm.jcr" id="GZJCJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:otherwise>
									</c:choose>
					</c:forEach>
	               
                </td>
                <td align="center"><span class="red">*</span> 检查时间</td>
                <td>
                <input type="text" style="width: 120px;" class="y-dateTime" data-options="required:true,editable:false" id="rcjc_from14" name="blGzjcForm.jcsj1" value="${blMainForm.blGzjcForm.jcsj1}"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\'rcjc_to15\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"> 
                                       至 <input type="text" style="width: 120px;" class="y-dateTime" data-options="required:true,editable:false" id="rcjc_to15" name="blGzjcForm.jcsj2" value="${blMainForm.blGzjcForm.jcsj2}" onclick="WdatePicker({minDate:'#F{$dp.$D(\'rcjc_from14\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
            </tr>
            <tr>
                <td align="center"><span class="red">*</span> 记录人</td>
                <td><input type="text" class="y-text" style="width:200px;" id="GZJCJlr" name="blGzjcForm.jlr" value="${blMainForm.blGzjcForm.jlr}"/></td>
                <td align="center">检查地点</td>
                <td><input type="text" class="y-text" maxlength="100" style="width:365px;" id="GZJCJcdd" name="blGzjcForm.jcdd" value="${blMainForm.blGzjcForm.jcdd}"/></td>
            </tr>
            <c:if test="${blMainForm.blGzjcForm.isexecchecklist == '0'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="3">
	                <input type="text" class="y-text" id="GZJCjcmbId" name="blGzjcForm.jcmbId" value="${blMainForm.blGzjcForm.jcmbId}"/>
	                <a class='o_btn btn_blue opm' onclick='commonDoCheck("18")'>进行检查</a>
	                <a class='o_btn btn_blue opm'   onclick='commonMoreCheck("18")' style="padding: 5px 16px; margin-left: 20px;">再次检查</a>
                </td>
            </tr>
            </c:if>
            <c:if test="${blMainForm.blGzjcForm.isexecchecklist == '1'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="1">
                <a class='o_btn btn_blue opm' onclick='singleDownload("${blMainForm.blGzjcForm.jcmbFileId}")'>下载监察模板</a>&nbsp;
                </td>
                <td colspan="2">
                <a class='o_btn btn_blue opm' onclick="singleUpload('GZJCJCJL')">上传监察笔录</a>&nbsp;
                </td>
            </tr>
            </c:if>
            <tr>
                <td align="center"><span class="red">*</span> 监察笔录</td>
                <td colspan="3">
                   <label id="filename_GZJCJCJL">
					<c:forEach items="${blMainForm.blGzjcForm.jcjlFileMap}" var="item">
						${item.filename}
						<a class='o_btn btn_blue opm' onclick="review(this)" id="${item.id},${item.filesize1}" >预览</a>&nbsp;
						<a class='o_btn btn_blue opm' onclick="download2('${item.id}')">下载</a>&nbsp;
						<a class='o_btn btn_orange opm' onclick="deleteJcbl('${item.id}','${item.fileTypeEnumName}','${item.moreCheckFiletype}','18')">删除</a>&nbsp;
						<c:if test = "${item.type eq '1'}">
						<a class='o_btn btn_blue opm' onclick="editMoreCheck('18','${item.id}')">修改</a>&nbsp;
						</c:if>
						<br/><br/>
					</c:forEach>
			       </label>
                </td>
            </tr>
            <tr>
                <td align="center" valign="top"><span class="red">*</span> 监察结论</td>
                <td colspan="3">
                <textarea class="y-textarea" name="blGzjcForm.jcjl" id="jcjl" onpropertychange="if(value.length>500) value=value.substr(0,500)" style="width: 80%; height:120px; float:left" >${blMainForm.blGzjcForm.jcjl}</textarea>
                <a href="javascript:void(0);" name="zfwj" style="width: 20px; display: block; float:left; margin-left: 20px; color: #00a2d9; line-height: 16px;">查看相关执法文件</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            <a class="b_link" id="GZJCfileupload" style="float:right;" >上传附件</a>
            相关附件
            <div class="annexselect">
                <input type="text" class="y-text" style="width:200px;" id="GZJCfiletype"/>
            </div>
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="GZJCdata" fit="true"></table>
        </div>
    </div>
    </c:if>
    <!--自动监控-->
    <c:if test="${blMainForm.blZdjkForm != null }">
    <div class="supervision">
        <h3 class="task_h3 mt25"><span class="h_icon"></span>自动监控</h3>
        <input type="hidden" id="rwlx" name="blZdjkForm.rwlx" value="${blMainForm.blZdjkForm.rwlx}" />
        <table class="dataTable mt25"  width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center"><span class="red">*</span> 检查人</td>
                <td>
                	<c:forEach items="${jcrList}" var="item">
									<c:choose>
										<c:when test="${fn:contains(blMainForm.blZdjkForm.jcr,item.id)}">
											<input checked name="blZdjkForm.jcr" id="ZDJKJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:when>
										<c:otherwise> 
											<input name="blZdjkForm.jcr" id="ZDJKJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:otherwise>
									</c:choose>
					</c:forEach>
	               
                </td>
                <td align="center"><span class="red">*</span> 检查时间</td>
                <td>
                <input type="text" style="width: 120px;" class="y-dateTime" data-options="required:true,editable:false" id="rcjc_from16" name="blZdjkForm.jcsj1" value="${blMainForm.blZdjkForm.jcsj1}"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\'rcjc_to17\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"> 
                                       至 <input type="text" style="width: 120px;" class="y-dateTime" data-options="required:true,editable:false" id="rcjc_to17" name="blZdjkForm.jcsj2" value="${blMainForm.blZdjkForm.jcsj2}" onclick="WdatePicker({minDate:'#F{$dp.$D(\'rcjc_from16\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
            </tr>
            <tr>
                <td align="center"><span class="red">*</span> 记录人</td>
                <td colspan="3"><input type="text" class="y-text" style="width:200px;" id="ZDJKJlr" name="blZdjkForm.jlr" value="${blMainForm.blZdjkForm.jlr}"/></td>
            </tr>
            <c:if test="${blMainForm.blZdjkForm.isexecchecklist == '0'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="3">
	                <input type="text" class="y-text" id="ZDJKjcmbId" name="blZdjkForm.jcmbId" value="${blMainForm.blZdjkForm.jcmbId}"/>
	                <a class='o_btn btn_blue opm' onclick='commonDoCheck("19")'>进行检查</a>
	                <a class='o_btn btn_blue opm'   onclick='commonMoreCheck("19")' style="padding: 5px 16px; margin-left: 20px;">再次检查</a>
                </td>
            </tr>
            </c:if>
            <c:if test="${blMainForm.blZdjkForm.isexecchecklist == '1'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="1">
                <a class='o_btn btn_blue opm' onclick='singleDownload("${blMainForm.blZdjkForm.jcmbFileId}")'>下载监察模板</a>&nbsp;
                </td>
                <td colspan="2">
                <a class='o_btn btn_blue opm' onclick="singleUpload('ZDJKJCJL')">上传监察笔录</a>&nbsp;
                </td>
            </tr>
            
            <tr>
                <td align="center"><span class="red">*</span> 监察笔录</td>
                <td colspan="3">
                   <label id="filename_ZDJKJCJL">
						<c:if test="${blMainForm.blZdjkForm.jcjlFileMap!=null}">
							${blMainForm.blZdjkForm.jcjlFileMap.filename}
						 </c:if>
				   </label>&nbsp;&nbsp;&nbsp;&nbsp;
                   <label id="oper_ZDJKJCJL">
						<c:if test="${blMainForm.blZdjkForm.jcjlFileMap!=null}">
							<a class='o_btn btn_blue opm' onclick="review(this)" id="${blMainForm.blZdjkForm.jcjlFileMap.id},${blMainForm.blZdjkForm.jcjlFileMap.filesize1}" >预览</a>&nbsp;
							<a class='o_btn btn_blue opm' onclick="download2('${blMainForm.blZdjkForm.jcjlFileMap.id}')">下载</a>&nbsp;
							<a class='o_btn btn_orange opm' onclick="singleDelete('${blMainForm.blZdjkForm.jcjlFileMap.id}','ZDJKJCJL')">删除</a>&nbsp;
						</c:if>
				   </label>
                </td>
            </tr>
            </c:if>
           <c:if test="${blMainForm.blZdjkForm.isexecchecklist == '2'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="3">
                   <input class="y-text" style="width:410px;" type="text" id="zzmblx" name="zzmblx" value="${blMainForm.blZdjkForm.zzmblx}"/>
					<!-- <a class="btslink" onclick='commonDoCheck("19")'>进行检查</a>&nbsp; -->
					<span>
					<a class='o_btn btn_blue opm' onclick='saveBeforeNext("ZDJKJCJL")'>在线制作</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a class='o_btn btn_blue opm' id="hbbt" name="hbbt"  onclick="saveBeforeNext('HBJCJL')">笔录制作</a>
                </td>
            </tr>
            <tr>
                <td align="center"><span class="red">*</span> 监察笔录</td>
                <td colspan="3" id="zdjkfile">
					<c:forEach items="${blMainForm.blZdjkForm.zxjcjlFileMap}" var="item">
					<label id="oper_ZDJKJCJL">
						${item.filename}
						<a class='o_btn btn_blue opm' onclick="review(this)" id="${item.id},${item.filesize1}" >预览</a>&nbsp;
						<a class='o_btn btn_blue opm' onclick="download2('${item.id}')">下载</a>&nbsp;
						<a class='o_btn btn_orange opm' onclick="singleDeleteKcxwbl('${item.id}','ZDJKJCJL')">删除</a>&nbsp;
					</label></br><br/>	
					</c:forEach>
                </td>
            </tr>
            </c:if>
            <tr>
                <td align="center" valign="top"><span class="red">*</span> 监察结论</td>
                <td colspan="3">
                <textarea class="y-textarea"  name="blZdjkForm.jcjl" id="jcjl" onpropertychange="if(value.length>500) value=value.substr(0,500)" style="width: 80%; height:120px; float:left" >${blMainForm.blZdjkForm.jcjl}</textarea>
                <a href="javascript:void(0);" name="zfwj" style="width: 20px; display: block; float:left; margin-left: 20px; color: #00a2d9; line-height: 16px;">查看相关执法文件</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            <a class="b_link" id="ZDJKfileupload" style="float:right;" >上传附件</a>
            相关附件
            <div class="annexselect">
                <input type="text" class="y-text" style="width:200px;" id="ZDJKfiletype"/>
            </div>
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="ZDJKdata" fit="true"></table>
        </div>
    </div>
    </c:if>
    <!--危险废物 -->
    <c:if test="${blMainForm.blWxfwForm != null }">
    <div class="supervision">
        <h3 class="task_h3 mt25"><span class="h_icon"></span>危险废物</h3>
        <input type="hidden" id="rwlx" name="blWxfwForm.rwlx" value="${blMainForm.blWxfwForm.rwlx}" />
        <table class="dataTable mt25"  width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center"><span class="red">*</span> 检查人</td>
                <td>
                	<c:forEach items="${jcrList}" var="item">
									<c:choose>
										<c:when test="${fn:contains(blMainForm.blWxfwForm.jcr,item.id)}">
											<input checked name="blWxfwForm.jcr" id="WXFWJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:when>
										<c:otherwise> 
											<input name="blWxfwForm.jcr" id="WXFWJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:otherwise>
									</c:choose>
					</c:forEach>
	               
                </td>
                <td align="center"><span class="red">*</span> 检查时间</td>
                <td>
                <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_from18" name="blWxfwForm.jcsj1" value="${blMainForm.blWxfwForm.jcsj1}"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\'rcjc_to19\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"> 
                                       至 <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_to19" name="blWxfwForm.jcsj2" value="${blMainForm.blWxfwForm.jcsj2}"  onclick="WdatePicker({minDate:'#F{$dp.$D(\'rcjc_from18\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
            </tr>
            <tr>
                <td align="center"><span class="red">*</span> 记录人</td>
                <td><input type="text" class="y-text" style="width:200px;"  id="WXFWJlr" name="blWxfwForm.jlr" value="${blMainForm.blWxfwForm.jlr}"/></td>
                <td align="center">检查地点</td>
                <td><input type="text" class="y-text" maxlength="100" style="width:365px;" id="WXFWJcdd" name="blWxfwForm.jcdd" value="${blMainForm.blWxfwForm.jcdd}"/></td>
            </tr>
            <c:if test="${blMainForm.blWxfwForm.isexecchecklist == '0'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="3">
	                <input type="text" class="y-text" id="WXFWjcmbId" name="blWxfwForm.jcmbId" value="${blMainForm.blWxfwForm.jcmbId}"/>
	                <a class='o_btn btn_blue opm' onclick='commonDoCheck("20")'>进行检查</a>
	                <a class='o_btn btn_blue opm'   onclick='commonMoreCheck("20")' style="padding: 5px 16px; margin-left: 20px;">再次检查</a>
                </td>
            </tr>
            </c:if>
            <c:if test="${blMainForm.blWxfwForm.isexecchecklist == '1'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="1">
                <a class='o_btn btn_blue opm' onclick='singleDownload("${blMainForm.blWxfwForm.jcmbFileId}")'>下载监察模板</a>&nbsp;
                </td>
                <td colspan="2">
                <a class='o_btn btn_blue opm' onclick="singleUpload('WXFWJCJL')">上传监察笔录</a>&nbsp;
                </td>
            </tr>
            </c:if>
            <c:if test="${blMainForm.blWxfwForm.isexecchecklist == '3'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="1">
                <a class='o_btn btn_blue opm' onclick='exportFile("${blMainForm.blWxfwForm.jcmbFileId}")'>导出监察模板</a>&nbsp;
                </td>
                <td colspan="2">
                <a class='o_btn btn_blue opm' onclick="singleUpload1('WXFWJCJL')">上传监察笔录</a>&nbsp;
                </td>
            </tr>
            </c:if>
            <tr>
                <td align="center"><span class="red">*</span> 监察笔录</td>
                <td colspan="3">
                   <label id="filename_WXFWJCJL">
					<c:forEach items="${blMainForm.blWxfwForm.jcjlFileMap}" var="item">
						${item.filename}
						<a class='o_btn btn_blue opm' onclick="review(this)" id="${item.id},${item.filesize1}" >预览</a>&nbsp;
						<a class='o_btn btn_blue opm' onclick="download2('${item.id}')">下载</a>&nbsp;
						<a class='o_btn btn_orange opm' onclick="deleteJcbl('${item.id}','${item.fileTypeEnumName}','${item.moreCheckFiletype}','20')">删除</a>&nbsp;
						<c:if test = "${item.type eq '1'}">
						<a class='o_btn btn_blue opm' onclick="editMoreCheck('20','${item.id}')">修改</a>&nbsp;
						</c:if>
						<br/><br/>
					</c:forEach>
			       </label>
                </td>
            </tr>
            <tr>
                <td align="center" valign="top"><span class="red">*</span> 监察结论</td>
                <td colspan="3">
                <textarea class="y-textarea"  name="blWxfwForm.jcjl" id="jcjl" onpropertychange="if(value.length>500) value=value.substr(0,500)" style="width: 80%; height:120px; float:left" >${blMainForm.blWxfwForm.jcjl}</textarea>
                <a href="javascript:void(0);" name="zfwj" style="width: 20px; display: block; float:left; margin-left: 20px; color: #00a2d9; line-height: 16px;">查看相关执法文件</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            <a class="b_link" id="WXFWfileupload" style="float:right;" >上传附件</a>
            相关附件
            <div class="annexselect">
                <input type="text" class="y-text" style="width:200px;" id="WXFWfiletype"/>
            </div>
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="WXFWdata" fit="true"></table>
        </div>
    </div>
    </c:if>
    <!--危险化学品 -->
    <c:if test="${blMainForm.blWxhxpForm != null }">
    <div class="supervision">
        <h3 class="task_h3 mt25"><span class="h_icon"></span>危险化学品</h3>
        <input type="hidden" id="rwlx" name="blWxhxpForm.rwlx" value="${blMainForm.blWxhxpForm.rwlx}" />
        <table class="dataTable mt25"  width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center"><span class="red">*</span> 检查人</td>
                <td>
                	<c:forEach items="${jcrList}" var="item">
									<c:choose>
										<c:when test="${fn:contains(blMainForm.blWxhxpForm.jcr,item.id)}">
											<input checked name="blWxhxpForm.jcr" id="WXHXPJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:when>
										<c:otherwise> 
											<input name="blWxhxpForm.jcr" id="WXHXPJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:otherwise>
									</c:choose>
					</c:forEach>
	               
                </td>
                <td align="center"><span class="red">*</span> 检查时间</td>
                <td>
                <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_from20" name="blWxhxpForm.jcsj1" value="${blMainForm.blWxhxpForm.jcsj1}"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\'rcjc_to21\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"> 
                                       至 <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_to21" name="blWxhxpForm.jcsj2" value="${blMainForm.blWxhxpForm.jcsj2}" onclick="WdatePicker({minDate:'#F{$dp.$D(\'rcjc_from20\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
            </tr>
            <tr>
                <td align="center"><span class="red">*</span> 记录人</td>
                <td colspan="3"><input type="text" class="y-text" style="width:200px;"  id="WXHXPJlr" name="blWxhxpForm.jlr" value="${blMainForm.blWxhxpForm.jlr}"/></td>
            </tr>
            <c:if test="${blMainForm.blWxhxpForm.isexecchecklist == '0'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="3">
	                <input type="text" class="y-text" id="WXHXPjcmbId" name="blWxhxpForm.jcmbId" value="${blMainForm.blWxhxpForm.jcmbId}"/>
	                <a class='o_btn btn_blue opm' onclick='commonDoCheck("21")'>进行检查</a>
                </td>
            </tr>
            </c:if>
            <c:if test="${blMainForm.blWxhxpForm.isexecchecklist == '1'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="1">
                <a class='o_btn btn_blue opm' onclick='singleDownload("${blMainForm.blWxhxpForm.jcmbFileId}")'>下载监察模板</a>&nbsp;
                </td>
                <td colspan="2">
                <a class='o_btn btn_blue opm' onclick="singleUpload('WXHXPJCJL')">上传监察笔录</a>&nbsp;
                </td>
            </tr>
            </c:if>
            <tr>
                <td align="center"><span class="red">*</span> 监察笔录</td>
                <td colspan="3">
                     <label id="filename_WXHXPJCJL">
							<c:if test="${blMainForm.blWxhxpForm.jcjlFileMap!=null}">
								${blMainForm.blWxhxpForm.jcjlFileMap.filename}
							</c:if>
					</label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <label id="oper_WXHXPJCJL">
					<c:if test="${blMainForm.blWxhxpForm.jcjlFileMap!=null}">
						<a class="o_btn btn_blue opm" onclick="review(this)" id="${blMainForm.blWxhxpForm.jcjlFileMap.id},${blMainForm.blWxhxpForm.jcjlFileMap.filesize1}" >预览</a>&nbsp;
						<a class="o_btn btn_blue opm" onclick="download2('${blMainForm.blWxhxpForm.jcjlFileMap.id}')">下载</a>&nbsp;
						<a class="o_btn btn_orange opm" onclick="singleDelete('${blMainForm.blWxhxpForm.jcjlFileMap.id}','WXHXPJCJL')">删除</a>&nbsp;
					</c:if>
			       </label>
                </td>
            </tr>
            <tr>
                <td align="center" valign="top"><span class="red">*</span> 监察结论</td>
                <td colspan="3">
                <textarea class="y-textarea"  name="blWxhxpForm.jcjl" id="jcjl" onpropertychange="if(value.length>500) value=value.substr(0,500)" style="width: 80%; height:120px; float:left" >${blMainForm.blWxhxpForm.jcjl}</textarea>
                <a href="javascript:void(0);" name="zfwj" style="width: 20px; display: block; float:left; margin-left: 20px; color: #00a2d9; line-height: 16px;">查看相关执法文件</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            <a class="b_link" id="WXHXPfileupload" style="float:right;" >上传附件</a>
            相关附件
            <div class="annexselect">
                <input type="text" class="y-text" style="width:200px;" id="WXHXPfiletype"/>
            </div>
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="WXHXPdata" fit="true"></table>
        </div>
    </div>
    </c:if>
    <!--辐射安全 -->
    <c:if test="${blMainForm.blFsaqForm != null }">
    <div class="supervision">
        <h3 class="task_h3 mt25"><span class="h_icon"></span>辐射安全</h3>
        <input type="hidden" id="rwlx" name="blFsaqForm.rwlx" value="${blMainForm.blFsaqForm.rwlx}" />
        <table class="dataTable mt25"  width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center"><span class="red">*</span> 检查人</td>
                <td>
                	<c:forEach items="${jcrList}" var="item">
									<c:choose>
										<c:when test="${fn:contains(blMainForm.blFsaqForm.jcr,item.id)}">
											<input checked name="blFsaqForm.jcr" id="FSAQJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:when>
										<c:otherwise> 
											<input name="blFsaqForm.jcr" id="FSAQJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:otherwise>
									</c:choose>
					</c:forEach>
	               
                </td>
                <td align="center"><span class="red">*</span> 检查时间</td>
                <td>
                <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_from22" name="blFsaqForm.jcsj1" value="${blMainForm.blFsaqForm.jcsj1}"  onclick="WdatePicker({maxDate:'#F{$dp.$D(\'rcjc_to23\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"> 
                                       至 <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_to23" name="blFsaqForm.jcsj2" value="${blMainForm.blFsaqForm.jcsj2}"  onclick="WdatePicker({minDate:'#F{$dp.$D(\'rcjc_from22\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
            </tr>
            <tr>
                <td align="center"><span class="red">*</span> 记录人</td>
                <td><input type="text" class="y-text" style="width:200px;" id="FSAQJlr" name="blFsaqForm.jlr" value="${blMainForm.blFsaqForm.jlr}"/></td>
                <td align="center">检查地点</td>
                <td><input type="text" class="y-text" maxlength="100" style="width:365px;" id="FSAQJcdd" name="blFsaqForm.jcdd" value="${blMainForm.blFsaqForm.jcdd}"/></td>
            </tr>
            <c:if test="${blMainForm.blFsaqForm.isexecchecklist == '0'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="3">
	                <input type="text" class="y-text" id="FSAQjcmbId" name="blFsaqForm.jcmbId" value="${blMainForm.blFsaqForm.jcmbId}"/>
	                <a class='o_btn btn_blue opm' onclick='commonDoCheck("22")'>进行检查</a>
	                <a class='o_btn btn_blue opm'   onclick='commonMoreCheck("22")' style="padding: 5px 16px; margin-left: 20px;">再次检查</a>
                </td>
            </tr>
            </c:if>
            <c:if test="${blMainForm.blFsaqForm.isexecchecklist == '1'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="1">
                <a class='o_btn btn_blue opm' onclick='singleDownload("${blMainForm.blFsaqForm.jcmbFileId}")'>下载监察模板</a>&nbsp;
                </td>
                <td colspan="2">
                <a class='o_btn btn_blue opm' onclick="singleUpload('FSAQJCJL')">上传监察笔录</a>&nbsp;
                </td>
            </tr>
            </c:if>
            <c:if test="${blMainForm.blFsaqForm.isexecchecklist == '3'}">
						<tr>
							<td align="center">
								监察模板
							</td>
							<td colspan="2">
								<a style="color: #00a2d9;" onclick="exportFsaq('1')">导出Ⅲ类源监督检查表</a>&nbsp;
								&nbsp;</br>
								<a style="color: #00a2d9;" onclick="exportFsaq('2')">导出固定式Ⅲ、Ⅳ和Ⅴ类源监督检查表</a>&nbsp;
								&nbsp;</br>
								<a style="color: #00a2d9;" onclick="exportFsaq('3')">导出移动式Ⅲ、Ⅳ和Ⅴ类源监督检查表</a>
							</td>
							<td colspan="1">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a style="color: #00a2d9;" onclick="singleUpload1('FSAQJCJL')">上传监督检查表</a>&nbsp;
							</td>
							
						</tr>
			</c:if>
            <tr>
                <td align="center"><span class="red">*</span> 监察笔录</td>
                <td colspan="3">
                   <label id="filename_FSAQJCJL">
					<c:forEach items="${blMainForm.blFsaqForm.jcjlFileMap}" var="item">
						${item.filename}
						<a class='o_btn btn_blue opm' onclick="review(this)" id="${item.id},${item.filesize1}" >预览</a>&nbsp;
						<a class='o_btn btn_blue opm' onclick="download2('${item.id}')">下载</a>&nbsp;
						<a class='o_btn btn_orange opm' onclick="deleteJcbl('${item.id}','${item.fileTypeEnumName}','${item.moreCheckFiletype}','22')">删除</a>&nbsp;
						<c:if test = "${item.type eq '1'}">
						<a class='o_btn btn_blue opm' onclick="editMoreCheck('22','${item.id}')">修改</a>&nbsp;
						</c:if>
						<br/><br/>
					</c:forEach>
			       </label>
                </td>
            </tr>
            <tr>
                <td align="center" valign="top"><span class="red">*</span> 监察结论</td>
                <td colspan="3">
                <textarea class="y-textarea"  name="blFsaqForm.jcjl" id="jcjl" onpropertychange="if(value.length>500) value=value.substr(0,500)" style="width: 80%; height:120px; float:left" >${blMainForm.blFsaqForm.jcjl}</textarea>
                <a href="javascript:void(0);" name="zfwj" style="width: 20px; display: block; float:left; margin-left: 20px; color: #00a2d9; line-height: 16px;">查看相关执法文件</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            <a class="b_link" id="FSAQfileupload" style="float:right;" >上传附件</a>
            相关附件
            <div class="annexselect">
                <input type="text" class="y-text" style="width:200px;" id="FSAQfiletype"/>
            </div>
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="FSAQdata" fit="true"></table>
        </div>
    </div>
    </c:if>
    <!--污染事故现场调查 -->
    <c:if test="${blMainForm.blWrsgxcdcForm != null }">
    <div class="supervision">
        <h3 class="task_h3 mt25"><span class="h_icon"></span>污染事故现场调查</h3>
        <input type="hidden" id="rwlx" name="blWrsgxcdcForm.rwlx" value="${blMainForm.blWrsgxcdcForm.rwlx}" />
        <table class="dataTable mt25"  width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center"><span class="red">*</span> 检查人</td>
                <td>
                	<c:forEach items="${jcrList}" var="item">
									<c:choose>
										<c:when test="${fn:contains(blMainForm.blWrsgxcdcForm.jcr,item.id)}">
											<input checked name="blWrsgxcdcForm.jcr" id="WRSGXCDCJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:when>
										<c:otherwise> 
											<input name="blWrsgxcdcForm.jcr" id="WRSGXCDCJcr" type="checkbox" value="${item.id}" /> ${item.name}
										</c:otherwise>
									</c:choose>
					</c:forEach>
	               
                </td>
                <td align="center"><span class="red">*</span> 检查时间</td>
                <td>
                <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_from24" name="blWrsgxcdcForm.jcsj1" value="${blMainForm.blWrsgxcdcForm.jcsj1}" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'rcjc_to25\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"> 
                                       至 <input type="text" style="width: 120px;" class="y-dateTime" id="rcjc_to25" name="blWrsgxcdcForm.jcsj2" value="${blMainForm.blWrsgxcdcForm.jcsj2}" onclick="WdatePicker({minDate:'#F{$dp.$D(\'rcjc_from24\',{d:0});}', dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
            </tr>
            <tr>
                <td align="center"><span class="red">*</span> 记录人</td>
                <td><input type="text" class="y-text" style="width:200px;"  id="WRSGXCDCJlr" name="blWrsgxcdcForm.jlr" value="${blMainForm.blWrsgxcdcForm.jlr}"/></td>
                <td align="center">检查地点</td>
                <td><input type="text" class="y-text" maxlength="100" style="width:365px;" id="WRSGXCDCJcdd" name="blWrsgxcdcForm.jcdd" value="${blMainForm.blWrsgxcdcForm.jcdd}"/></td>
            </tr>
            <c:if test="${blMainForm.blWrsgxcdcForm.isexecchecklist == '0'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="3">
	                <input type="text" class="y-text" id="WRSGXCDCjcmbId" name="blWrsgxcdcForm.jcmbId" value="${blMainForm.blWrsgxcdcForm.jcmbId}"/>
	                <a class='o_btn btn_blue opm' onclick='commonDoCheck("23")'>进行检查</a>
	                <a class='o_btn btn_blue opm'   onclick='commonMoreCheck("23")' style="padding: 5px 16px; margin-left: 20px;">再次检查</a>
                </td>
            </tr>
            </c:if>
            <c:if test="${blMainForm.blWrsgxcdcForm.isexecchecklist == '1'}">
            <tr>
                <td align="center"> 监察模板</td>
                <td colspan="1">
                <a class='o_btn btn_blue opm' onclick='singleDownload("${blMainForm.blWrsgxcdcForm.jcmbFileId}")'>下载监察模板</a>&nbsp;
                </td>
                <td colspan="2">
                <a class='o_btn btn_blue opm' onclick="singleUpload('WRSGXCDCJCJL')">上传监察笔录</a>&nbsp;
                </td>
            </tr>
            </c:if>
            <tr>
                <td align="center"><span class="red">*</span> 监察笔录</td>
                <td colspan="3">
                   <label id="filename_WRSGXCDCJCJL">
					<c:forEach items="${blMainForm.blWrsgxcdcForm.jcjlFileMap}" var="item">
						${item.filename}
						<a class='o_btn btn_blue opm' onclick="review(this)" id="${item.id},${item.filesize1}" >预览</a>&nbsp;
						<a class='o_btn btn_blue opm' onclick="download2('${item.id}')">下载</a>&nbsp;
						<a class='o_btn btn_orange opm' onclick="deleteJcbl('${item.id}','${item.fileTypeEnumName}','${item.moreCheckFiletype}','23')">删除</a>&nbsp;
						<c:if test = "${item.type eq '1'}">
						<a class='o_btn btn_blue opm' onclick="editMoreCheck('23','${item.id}')">修改</a>&nbsp;
						</c:if>
						<br/>
					</c:forEach>
			       </label>
                </td>
            </tr>
            <tr>
                <td align="center" valign="top"><span class="red">*</span> 监察结论</td>
                <td colspan="3">
                <textarea class="y-textarea" name="blWrsgxcdcForm.jcjl" id="jcjl" onpropertychange="if(value.length>500) value=value.substr(0,500)" maxlength="500" style="width: 80%; height:120px; float:left" >${blMainForm.blWrsgxcdcForm.jcjl}</textarea>
                <a href="javascript:void(0);" name="zfwj" style="width: 20px; display: block; float:left; margin-left: 20px; color: #00a2d9; line-height: 16px;">查看相关执法文件</a>
                </td>
            </tr>
        </table>
    </div>
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            <a class="b_link" id="WRSGXCDCfileupload" style="float:right;" >上传附件</a>
            相关附件
            <div class="annexselect">
                <input type="text" class="y-text" style="width:200px;" id="WRSGXCDCfiletype"/>
            </div>
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="WRSGXCDCdata" fit="true"></table>
        </div>
    </div>
    </c:if>
     <!--日常办公-->
   	<c:if test='${rwlxIds == "24"}'>
    <div class="supervision">
        <h3 class="task_h3 mt25"><span class="h_icon"></span>日常办公</h3>
        <input type="hidden" id="rwlx" name="blRcbgForm.rwlx" value="24" />
        <table class="dataTable mt25"  width="100%" border="0" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
            <tr>
                <td align="center">备注</td>
                <td>
                	<textarea class="y-textarea" name="blRcbgForm.desc" id="desc" maxlength="500" onKeyUp="return textMaxLen(this);" onBlur="return textMaxLen(this);" style="width: 80%; height:120px; float:left" >${blMainForm.blRcbgForm.desc}</textarea>
                </td>
            </tr>
        </table>
    </div>
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            <a class="b_link" id="RCBGfileupload" style="float:right;" >上传附件</a>
            相关附件
            <div class="annexselect">
                <input type="text" class="y-text" style="width:200px;" id="RCBGfiletype"/>
            </div>
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="RCBGdata" fit="true"></table>
        </div>
    </div>
    </c:if>
</div>
<div class="rb_btn_fix" style="text-align:center;">
    <input type="button" id="savebutton" class="queryBlue" value="保  存" onclick="baocun()" style="cursor: pointer">
    <c:if test='${rwlxIds == "13" }'>
		<span class="btn btn-ok"> <input type="button" class="queryBlue" id="print" value="打印办结单" style="cursor: pointer;" /> </span>
		<!-- <span class="btn btn-ok" id="bjsb"> <input type="button" class="queryBlue" value="${sfsb}" onclick="bj()" style="cursor: pointer;"/> </span>
		<span class="btn btn-ok" id="xfzj"> <input type="button" class="queryBlue" value="信访终结" onclick="xfzj()" style="cursor: pointer;"/> </span> -->
	</c:if>
	<c:if test="${sysVer != 0 }">
		<span class="btn btn-ok"> 
		<input id="lxdb" name="lxdb" type="hidden" value="办理完成，准备打包" onclick="db()"/>
		<input id="" type="button" class="queryOrange" value="下一步" onclick="bl()" style="cursor: pointer;"/> </span>
	</c:if>
	<c:if test="${sysVer == 0 && rwlxIds == 24}">
		<span class="btn btn-ok"> <input id="lxdb" name="lxdb" type="button" value="办理完成，准备打包" onclick="db()"/> </span>
	</c:if>
	<c:if test="${sysVer == 0 && rwlxIds != 24}">
		<span class="btn btn-ok"> 
		<input id="lxdb" name="lxdb" type="hidden" value="办理完成，准备打包" onclick="db()"/>
		<input id="" type="button" value="办理完成，准备打包" onclick="db()"/> </span>
	</c:if>
</div>
</form>
<c:if test='${rwlxIds != "24" && zfdxInfo == "Y"}'>
	<div id="floatDiv" onclick='javascript:fuzhu()'></div>
</c:if>
<iframe name="download" id="download" src="" style="display: none"></iframe>
<div align="center"> 
</div>
<a href="javascript:void(0);" class="getInfo">获取离线信息</a>
</body>
</html>
<script type="text/javascript">
var lxdbbtn = document.getElementById('lxdb');
lxdbbtn.disabled = "disabled";
$(function(){  
            inputTipText();  //初始化Input的灰色提示信息  
	}); 
function inputTipText(){   
    $("input[tipMsg]").each(function(){  
        if($(this).val() == ""){  
        var oldVal=$(this).attr("tipMsg");  
        if($(this).val()==""){$(this).attr("value",oldVal).css({"color":"#888"});}  
        $(this)
           .css({"color":"#888"})     //灰色  
           .focus(function(){  
            if($(this).val()!=oldVal){$(this).css({"color":"#000"})}else{$(this).val("").css({"color":"#888"})}  
           })  
           .blur(function(){  
            if($(this).val()==""){$(this).val(oldVal).css({"color":"#888"})}  
           })  
           .keydown(function(){$(this).css({"color":"#000"})});  
        }  
    });  
}   
String.prototype.trim = function()  
 {  
      return this.replace(/(^\s*)|(\s*$)/g, "");  
 }
var applyId = $('#applyId').val();
var zfdxInfo = $('#zfdxInfo').val();
var rwlxIds = $('#rwlxIds').val();

function xzjcbgmb(tasktype){
	var jcmbId="";
	var filenameTEXT = "";
    switch(tasktype){//根据code判断任务类型
        case '10'://现场监察
           jcmbId = $('#RCJCjcmbId').combobox("getValue");
           filenameTEXT = $('#filename_RCJCJCJL').text().trim();
           break;
        case '11'://年度核查
           jcmbId = $('#NDHCjcmbId').combobox("getValue");
           filenameTEXT = $('#filename_NDHCJCJL').text().trim();
           break;
        case '12'://后督察
           jcmbId = $('#HDCjcmbId').combobox("getValue");
           filenameTEXT = $('#filename_HDCJCJL').text().trim();
           break;
        case '13'://信访投诉
           jcmbId = $('#XFTSjcmbId').combobox("getValue");
           filenameTEXT = $('#filename_XFTSJCJL').text().trim();
           break;
        case '14'://排污许可证检查
           jcmbId = $('#PWXKZJCjcmbId').combobox("getValue");
           filenameTEXT = $('#filename_PWXKZJCJCJL').text().trim();
           break;
        case '15'://专项行动
           jcmbId = $('#ZXXDjcmbId').combobox("getValue");
           filenameTEXT = $('#filename_ZXXDZRWJCJL').text().trim();
           break;
        case '17'://限期治理
           jcmbId = $('#XQZLjcmbId').combobox("getValue");
           filenameTEXT = $('#filename_XQZLJCJL').text().trim();
           break;
        case '18'://跟踪检查
           jcmbId = $('#GZJCjcmbId').combobox("getValue");
           filenameTEXT = $('#filename_GZJCJCJL').text().trim();
           break;
        case '19'://自动监控
           jcmbId = $('#ZDJKjcmbId').combobox("getValue");
           filenameTEXT = $('#filename_ZDJKJCJL').text().trim();
           break;
        case '20'://危险废物
           jcmbId = $('#WXFWjcmbId').combobox("getValue");
           filenameTEXT = $('#filename_WXFWJCJL').text().trim();
           break;
        case '21'://危险化学品
           jcmbId = $('#WXHXPjcmbId').combobox("getValue");
           filenameTEXT = $('#filename_WXHXPJCJL').text().trim();
           break;
        case '22'://辐射安全
           jcmbId = $('#FSAQjcmbId').combobox("getValue");
           filenameTEXT = $('#filename_FSAQJCJL').text().trim();
           break;
        case '23'://污染事故现场调查
           jcmbId = $('#WRSGXCDCjcmbId').combobox("getValue");
           filenameTEXT = $('#filename_WRSGXCDCJCJL').text().trim();
           break;
        default:
        	jcmbId = "10";
    }

    if (filenameTEXT == ""){
        alert("请先生成检查笔录。");
        return;
	}
	//$('#download').attr('src',"saveDownJcbgmb?jcmbId="+jcmbId+"&applyId="+applyId+"&taskType=" + tasktype);
}

//定义一个对象（code：任务类型code,zwName：中文名称,pyjx：拼音简写；fileType：文件类型；）
function RwlxObj(code,zwName,pyjx,fileType){
    this.code = code;
    this.zwName = zwName;
    this.pyjx = pyjx;
    this.fileType=fileType;
}
var rwlxObj=new RwlxObj("","","","");
//通用展示"相关任务附件"
function showCOMMON(rwlxObj){
    //上传附件类型
    $('#'+rwlxObj.pyjx+'filetype').combobox({
    	height:34,
		url:'queryBlFileTypeCombo.json?rwlx='+rwlxObj.code+'&zfdxInfo='+zfdxInfo,
		valueField:'id',
		textField:'name'
	});
    var fileTypeNum=rwlxObj.fileType.split(',');
    var canDelStr="";
    for(var i=0;i<fileTypeNum.length;i++){
        if(i<fileTypeNum.length-1){
           canDelStr+="Y,";
        }else{
           canDelStr+="Y";
        }
    }
    //赋值
    $('#'+rwlxObj.pyjx+'data').datagrid({
        pagination:true,//分页控件
        height:'auto',
        //nowrap:false,
		rownumbers: true,
		singleSelect: true,
		fitColumns : true,
		url:'queryFileListMulfileType.json?canDel='+canDelStr,
		queryParams:{pid:$("#applyId").val(),fileType:rwlxObj.fileType,tableId:rwlxObj.pyjx+'data'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:150},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:350},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:100},
			{field:'operate',title:'操作', align:'center', halign:'center',width:200}
		]]
	});
	//上传
	$('#'+rwlxObj.pyjx+'fileupload').click(function(){
		var id = $("#applyId").val();
		var uploadFileType = $('#'+rwlxObj.pyjx+'filetype').combobox('getValue');
		if(uploadFileType!=null && uploadFileType!=''){
		    $(this).colorbox({
				iframe:true, width:"610", height:"380",
				href:'uploadPage.htm?pid='+id+'&fileType='+uploadFileType+'&path=WORK&tableId='+rwlxObj.pyjx+'data'
			});
		}else{
		    alert("请先选择上传附件类型！");
		}
	});
}

function textMaxLen(i){
 	 var conMaxLen=parseInt(i.getAttribute("maxlength"));                            
 	 if(i.value.length>=conMaxLen){
 		i.value=i.value.substring(0,conMaxLen)                                        
 	 }                                                                                
}    

//设置选择回访人
function setUserInfoHfr(id,name) {
	$("#hfr").val(id);
	$("#hfrName").val(name);
	jQuery().colorbox.close();
}
$(document).ready(function() {
    $.ajaxSetup({cache:false});
	
	if($("#bjqk").val()==""){
		$("#bjqkInfo").html("");
	}else if($("#bjqk").val()=="0"){
    	$("#bjqkInfo").html("信访终结");
    	$('#zcbl').hide();
    	$('#bjsb').hide();
    	$('#xfzj').hide();
    }else{
    	$('#zcbl').show();
    }
    $('#zwtitle').combobox({
    	width:110,
        height:34,
        required:true,
		url:'dicList.json?type=13',
		valueField:'id',
		editable : true,
		textField:'name',
		value : "${blMainForm.blZfdxForm.zwtitle}",
		onSelect : function (r){
			if (r.id == '自定义'){
				$('#zwtitle').combobox({
					height:34,
			        required:true,
					editable : true,
					valueField : 'name',
					hasDownArrow : false,
					value : r.id,
					onChange : function (n, o){
						$('#zwtitle').combobox("setValue", n);
					}
				});
			} else {
				$('#zwtitle').combobox({
					height:34,
			        required : true,
					editable : false,
					hasDownArrow : true,
					value : r.id
				});
			}
		}
	});

	$("#zw").combobox({
		height:34,
		required : true,
		url : 'dicList.json?type=14',
		valueField : 'id',
		editable : true,
		textField : 'name',
		value : "${blMainForm.blZfdxForm.zw}",
		onSelect : function (r){
			if (r.id == '自定义'){
				$('#zw').combobox({
					height:34,
					required:true,
					editable : true,
					valueField : 'name',
					hasDownArrow : false,
					value : r.id,
					onChange : function (n, o){
						$('#zw').combobox("setValue", n);
					}
				});
			} else {
				$('#zw').combobox({
					height:34,
					required : true,
					editable : false,
					hasDownArrow : true,
					value : r.id
				});
			}
		}
	});
	
	//$('#editWin').window({
	//	title : '填写现场监察情况',
	//	width:600,
	//	draggable : false,
	//	resizable : false,
	//	shadow : true,
	//	modal : true,
	//	minimizable : false,
	//	maximizable : false,
	//	height : 330,
	//	closed : true,
	//	modal : true
	//});
	//回访形式下拉框
	$('#hfxs').combobox({
		height:34,
		url:'dicList.json?type=26',
		valueField:'id',
		textField:'name'
	});
	 //选择回访人
    $("#selectHfr").colorbox({iframe:true,width:"300", height:"380",href:"userPubQuery.htm?all=true&notShowZj=false&notShowSys=true&methodname=setUserInfoHfr&multi=false"});
	$("#print").click(function(){
		//先保存再打印
		$('#blForm').attr('action','saveWorkzxBL.json');
		$('#blForm').ajaxSubmit(function(data){
			if(data.state){
	   			$('#download').attr('src','exportXfbjd.json?xfdjId='+data.xfdjbId);
	   			$('#xftsId').val(data.xftsId);
	   		}else{
				$.messager.alert('信访登记单保存:',data.msg);
			}
   		});
	});
	
    //现场监察
    //<c:if test="${blMainForm.blRcjcForm != null }">
    if(rwlxIds.indexOf("10")!=-1){
    //记录人
        $('#RCJCJlr').combobox({
        	height:34,
            required:true,
			url:'ryghCombo.json?applyId='+applyId,
			valueField:'id',
			textField:'name'
		});

		//检查模板
        $('#RCJCjcmbId').combobox({
        	height:34,
            required:true,
			url:'getJcmbByTaskType.json?tasktype=${blMainForm.blRcjcForm.rwlx}',
			panelHeight : 200,
			valueField:'id',
			textField:'name'
		});
		
		
		
		//相关任务附件
		var rwlxObj=new RwlxObj("10","现场监察","RCJC","RCJCJCJLSMJ,RCJCXZWS,RCJCQTZL,RCJCCLYJS,RCJCSPZL,RCJCLYZL,RCJCZP,RCJCHPPFWJ,RCJCYSPFWJ");
		showCOMMON(rwlxObj);
    }
    //</c:if>
    
    //年度核查
    //<c:if test="${blMainForm.blNdhcForm != null }">
    if(rwlxIds.indexOf("11")!=-1){
		//记录人
        $('#NDHCJlr').combobox({
        	height:34,
            required:true,
			url:'ryghCombo.json?applyId='+applyId,
			valueField:'id',
			textField:'name'
		});

		//检查模板
		$('#NDHCjcmbId').combobox({
			height:34,
			required:true,
			url:'getJcmbByTaskType.json?tasktype=${blMainForm.blNdhcForm.rwlx}',
			panelHeight : 200,
			valueField:'id',
			textField:'name'
		});
		
		
		//相关任务附件
		var rwlxObj=new RwlxObj("11","年度核查","NDHC","NDHCJCJLSMJ,NDHCXZWS,NDHCQTZL,NDHCCLYJS,NDHCSPZL,NDHCLYZL,NDHCZP,NDHCHPPFWJ,NDHCYSPFWJ");
		showCOMMON(rwlxObj);
		}
   // </c:if>
    
    //后督察
   // <c:if test="${blMainForm.blHdcForm != null }">
    if(rwlxIds.indexOf("12")!=-1){
    	//记录人
        $('#HDCJlr').combobox({
        	height:34,
            required:true,
			url:'ryghCombo.json?applyId='+applyId,
			valueField:'id',
			textField:'name'
		});
		//检查模板
		$('#HDCjcmbId').combobox({
			height:34,
			required:true,
			url:'getJcmbByTaskType.json?tasktype=${blMainForm.blHdcForm.rwlx}',
			panelHeight : 200,
			valueField:'id',
			textField:'name'
		});
		//相关任务附件
		rwlxObj=new RwlxObj("12","后督察","HDC","HDCJCJLSMJ,HDCXZWS,HDCQTZL,HDCCLYJS,HDCSPZL,HDCLYZL,HDCZP,HDCHPPFWJ,HDCYSPFWJ");
		showCOMMON(rwlxObj);
    }
    //</c:if>
   
    //信访投诉
    if(rwlxIds.indexOf("13")!=-1){
    	if(zfdxInfo == "Y"){
	    	//记录人
	        $('#XFTSJlr').combobox({
	        	height:34,
	            required:true,
				url:'ryghCombo.json?applyId='+applyId,
				valueField:'id',
				textField:'name'
			});
	
			//检查模板
			$('#XFTSjcmbId').combobox({
				height:34,
				required:true,
				url:'getJcmbByTaskType.json?tasktype=${blMainForm.blXftsForm.rwlx}',
				panelHeight : 200,
				valueField:'id',
				textField:'name'
			});
			
			//信访投诉来源
	        $('#xftsly').combobox({
	        	height:34,
	            required:true,
				url:'dicList.json?type=11',
				valueField:'id',
				textField:'name'
			});
    	}
		
		//相关任务附件
		if(zfdxInfo == "Y"){
			rwlxObj=new RwlxObj("13","信访投诉","XFTS","XFTSJCJLSMJ,XFTSBJDSMJ,XFTSXZWS,XFTSQTZL,XFTSCLYJS,XFTSSPZL,XFTSLYZL,XFTSZP,XFTSHPPFWJ,XFTSYSPFWJ");
		}else{
			rwlxObj=new RwlxObj("13","信访投诉","XFTS","XFTSBJDSMJ,XFTSXZWS,XFTSQTZL,XFTSCLYJS,XFTSSPZL,XFTSLYZL,XFTSZP,XFTSHPPFWJ,XFTSYSPFWJ");
		}
		
		showCOMMON(rwlxObj);
    }
    
    //排污许可证检查
    //<c:if test="${blMainForm.blPwxkzjcForm != null }">
    if(rwlxIds.indexOf("14")!=-1){
		//记录人
        $('#PWXKZJCJlr').combobox({
        	height:34,
            required:true,
			url:'ryghCombo.json?applyId='+applyId,
			valueField:'id',
			textField:'name'
		});
		//检查模板
		$('#PWXKZJCjcmbId').combobox({
			height:34,
			required:true,
			url:'getJcmbByTaskType.json?tasktype=${blMainForm.blPwxkzjcForm.rwlx}',
			panelHeight : 200,
			valueField:'id',
			textField:'name'
		});
		
		
		//相关任务附件
		rwlxObj=new RwlxObj("14","排污许可证检查","PWXKZJC","PWXKZJCJCJLSMJ,PWXKZJCXZWS,PWXKZJCQTZL,PWXKZJCCLYJS,PWXKZSPZL,PWXKZLYZL,PWXKZZP,PWXKZHPPFWJ,PWXKZYSPFWJ");
		showCOMMON(rwlxObj);
		}
    //</c:if>
    
    //违法案件调查
    //<c:if test="${blMainForm.blWfajdcForm != null }">
    if(rwlxIds.indexOf("16")!=-1){
        $('#wflx').combotree({
        	height:34,
            required:true,
			type:"post",
			multiple: true,
			url:'illegalTypeByTasktypeList.json?tasktype=16',
			cascadeCheck : false,
			valueField:'id',
		    textField:'name',
			value:'${blMainForm.blWfajdcForm.wflxId}'.split(','),
			onlyLeafCheck : true,
			onCheck : function (){
				var wf = $('#wflx').combotree("getText").split(",");
				var lawName = "${blMainForm.blZfdxForm.lawobjname}";
				var text = "";
				
				for(var i = 0; i < wf.length; i++){
					if(i > 0){
						text += ",";
					}
					if (wf[i] != ''){
						text += wf[i];
					}
				}
				text=lawName+text+"案件";
				var wflx = $('#wflx').combotree("getValues");
				$("#blWfajdcForm_wfajmc").val(text);
				$.ajax( {
					url : "delRecord.json?applyId="+applyId+"&wflx="+wflx,
					success : function(data) {
						//refresh();
					}
				});	
			}
		});
		//记录人
        $('#WFAJDCJlr').combobox({
        	height:34,
            required:true,
			url:'ryghCombo.json?applyId='+applyId,
			valueField:'id',
			textField:'name'
		});
		
		
		//相关任务附件
		rwlxObj=new RwlxObj("16","违法案件调查","WFAJDC","WFAJDCLADJB,WFAJDCLADJSMJ,WFAJDCKCBLSMJ,WFAJDCXWBLSMJ,WFAJDCSZDZJZL,WFAJDCSTZLTP,WFAJDCYPZL,WFAJDCSTZLLX,WFAJDCXZWS,WFAJDCQTZL,WFAJDCDCBG,WFAJDCHPPFWJ,WFAJDCYSPFWJ");
		showCOMMON(rwlxObj);
		}
    //</c:if>
    
    //限期治理
    //<c:if test="${blMainForm.blXqzlForm != null }">
    if(rwlxIds.indexOf("17")!=-1){
		//记录人
        $('#XQZLJlr').combobox({
        	height:34,
            required:true,
			url:'ryghCombo.json?applyId='+applyId,
			valueField:'id',
			textField:'name'
		});

		//检查模板
		$('#XQZLjcmbId').combobox({
			height:34,
			required:true,
			url:'getJcmbByTaskType.json?tasktype=${blMainForm.blXqzlForm.rwlx}',
			panelHeight : 200,
			valueField:'id',
			textField:'name'
		});

		
		
		//相关任务附件
		rwlxObj=new RwlxObj("17","限期治理","XQZL","XQZLJCJLSMJ,XQZLXZWS,XQZLQTZL,XQZLCLYJS,XQZLSPZL,XQZLLYZL,XQZLZP,XQZLHPPFWJ,XQZLYSPFWJ,XQZLHJWFXWXQGZTZ,XQZLXZCFJDHSDHZ,XQZLTZGZSSDHZ");
		showCOMMON(rwlxObj);
		}
    //</c:if>
    
    //跟踪检查
    //<c:if test="${blMainForm.blGzjcForm != null }">
    if(rwlxIds.indexOf("18")!=-1){
		//记录人
        $('#GZJCJlr').combobox({
        	height:34,
            required:true,
			url:'ryghCombo.json?applyId='+applyId,
			valueField:'id',
			textField:'name'
		});

		//检查模板
		$('#GZJCjcmbId').combobox({
			height:34,
			required:true,
			url:'getJcmbByTaskType.json?tasktype=${blMainForm.blGzjcForm.rwlx}',
			panelHeight : 200,
			valueField:'id',
			textField:'name'
		});
		
		
		//相关任务附件
		rwlxObj=new RwlxObj("18","跟踪检查","GZJC","GZJCJCJLSMJ,GZJCXZWS,GZJCQTZL,GZJCCLYJS,GZJCSPZL,GZJCLYZL,GZJCZP,GZJCHPPFWJ,GZJCYSPFWJ");
		showCOMMON(rwlxObj);
		}
    //</c:if>
    
    //自动监控
    //<c:if test="${blMainForm.blZdjkForm != null }">
    if(rwlxIds.indexOf("19")!=-1){
		//记录人
        $('#ZDJKJlr').combobox({
        	height:34,
            required:true,
			url:'ryghCombo.json?applyId='+applyId,
			valueField:'id',
			textField:'name'
		});

		//检查模板
		$('#ZDJKjcmbId').combobox({
			height:34,
			required:true,
			url:'getJcmbByTaskType.json?tasktype=${blMainForm.blZdjkForm.rwlx}',
			panelHeight : 200,
			valueField:'id',
			textField:'name'
		});

		
		//相关任务附件
		rwlxObj=new RwlxObj("19","自动监控","ZDJK","ZDJKJCJLSMJ,ZDJKXZWS,ZDJKQTZL,ZDJKCLYJS,ZDJKSPZL,ZDJKLYZL,ZDJKZP,ZDJKHPPFWJ,ZDJKYSPFWJ");
		showCOMMON(rwlxObj);
		
		//在线制作模板类型
		$('#zzmblx').combobox({
			height:34,
			type:"post",
			url:'dicList.json?type=15',
			valueField:'id',
			textField:'name'
		});
		}
    //</c:if>
    
    //危险废物
   // <c:if test="${blMainForm.blWxfwForm != null }">
    if(rwlxIds.indexOf("20")!=-1){
		//记录人
        $('#WXFWJlr').combobox({
        	height:34,
            required:true,
			url:'ryghCombo.json?applyId='+applyId,
			valueField:'id',
			textField:'name'
		});

		//检查模板
		$('#WXFWjcmbId').combobox({
			height:34,
			required:true,
			url:'getJcmbByTaskType.json?tasktype=${blMainForm.blWxfwForm.rwlx}',
			panelHeight : 200,
			valueField:'id',
			textField:'name'
		});
		
		//相关任务附件
		rwlxObj=new RwlxObj("20","危险废物","WXFW","WXFWJCJLSMJ,WXFWXZWS,WXFWQTZL,WXFWCLYJS,WXFWSPZL,WXFWLYZL,WXFWZP,WXFWHPPFWJ,WXFWYSPFWJ");
		showCOMMON(rwlxObj);
		}
    //</c:if>
    
    //危险化学品
   // <c:if test="${blMainForm.blWxhxpForm != null }">
    if(rwlxIds.indexOf("21")!=-1){
		//记录人
        $('#WXHXPJlr').combobox({
        	height:34,
            required:true,
			url:'ryghCombo.json?applyId='+applyId,
			valueField:'id',
			textField:'name'
		});

		//检查模板
		$('#WXHXPjcmbId').combobox({
			height:34,
			required:true,
			url:'getJcmbByTaskType.json?tasktype=${blMainForm.blWxhxpForm.rwlx}',
			panelHeight : 200,
			valueField:'id',
			textField:'name'
		});
		
		//相关任务附件
		rwlxObj=new RwlxObj("21","危险化学品","WXHXP","WXHXPJCJLSMJ,WXHXPXZWS,WXHXPQTZL,WXHXPCLYJS,WXHXPSPZL,WXHXPLYZL,WXHXPZP,WXHXPHPPFWJ,WXHXPYSPFWJ");
		showCOMMON(rwlxObj);
		}
    //</c:if>
    
    //辐射安全
    //<c:if test="${blMainForm.blFsaqForm != null }">
    if(rwlxIds.indexOf("22")!=-1){
		//记录人
        $('#FSAQJlr').combobox({
        	height:34,
            required:true,
			url:'ryghCombo.json?applyId='+applyId,
			valueField:'id',
			textField:'name'
		});

		//检查模板
		$('#FSAQjcmbId').combobox({
			height:34,
			required:true,
			url:'getJcmbByTaskType.json?tasktype=${blMainForm.blFsaqForm.rwlx}',
			panelHeight : 200,
			valueField:'id',
			textField:'name'
		});

		
		//相关任务附件
		rwlxObj=new RwlxObj("22","辐射安全","FSAQ","FSAQJCJLSMJ,FSAQXZWS,FSAQQTZL,FSAQCLYJS,FSAQSPZL,FSAQLYZL,FSAQZP,FSAQHPPFWJ,FSAQYSPFWJ");
		showCOMMON(rwlxObj);
		}
    //</c:if>
    
    //污染事故现场调查
    //<c:if test="${blMainForm.blWrsgxcdcForm != null }">
    if(rwlxIds.indexOf("23")!=-1){
		//记录人
        $('#WRSGXCDCJlr').combobox({
        	height:34,
            required:true,
			url:'ryghCombo.json?applyId='+applyId,
			valueField:'id',
			textField:'name'
		});

		//检查模板
		$('#WRSGXCDCjcmbId').combobox({
			height:34,
			required:true,
			url:'getJcmbByTaskType.json?tasktype=${blMainForm.blWrsgxcdcForm.rwlx}',
			panelHeight : 200,
			valueField:'id',
			textField:'name'
		});
		
		//相关任务附件
		rwlxObj=new RwlxObj("23","污染事故现场调查","WRSGXCDC","WRSGXCDCJCJLSMJ,WRSGXCDCXZWS,WRSGXCDCQTZL,WRSGXCDCCLYJS,WRSGXCDCSPZL,WRSGXCDCLYZL,WRSGXCDCZP,WRSGXCDCHPPFWJ,WRSGXCDCYSPFWJ");
		showCOMMON(rwlxObj);
	}
    //</c:if>
    
    //日常办公
    //<c:if test='${rwlxIds == "24"}'>
    if(rwlxIds == "24"){
		//相关任务附件
		rwlxObj=new RwlxObj("24","日常办公","RCBG","RCBGBLZL");
		
		showCOMMON(rwlxObj);
		}
    //</c:if>
    
    //相关法律依据
    $("#flyj").click(function(){
    	var href = 'wflx_qxflList.htm?wflxId='+$('#wflx').combotree('getValues'); 
      	var width=screen.width * 0.8;
      	var height=screen.height * 0.8-50;
      	parent.parent.layerIframe(2,href,"相关法律依据",width,height);
    	//All.ShowModalWin('wflx_qxflList.htm?wflxId='+$('#wflx').combotree('getValues'),"",1200,600);
    });
    //相关执法文件
    $("a[name='zfwj']").click(function(){
    	var href = 'xxcx_lawdocList.htm'; 
      	var width=screen.width * 0.8;
      	var height=screen.height * 0.8-50;
      	parent.parent.layerIframe(2,href,"相关执法文件",width,height);
    	//All.ShowModalWin('xxcx_lawdocList.htm',"",1200,600);
    });
});

//通用（开始检查）
function commonDoCheck(taskTypeCode){
    var fileTypeEnumName="";//附件类型枚举名称
    var moreCheckFiletype="";//多次检查附件枚举名称
    var pyjx="";//任务类型拼音简写
    //开始检查前先保存
    $('#blForm').attr('action','saveWorkzxBL.json');
	$('#blForm').ajaxSubmit(function(data){
   		if(data.state){
			var jcmbId="";
		    switch(taskTypeCode){//根据code判断任务类型
		        case '10'://现场监察
		           jcmbId = $('#RCJCjcmbId').combobox("getValue");
		           fileTypeEnumName='RCJCJCJL';
		           moreCheckFiletype='RCJCMOREJCBL';
		           pyjx='RCJC';
		           break;
		        case '11'://年度核查
		           jcmbId = $('#NDHCjcmbId').combobox("getValue");
		           fileTypeEnumName='NDHCJCJL';
		           pyjx='NDHC';
		           break;
		        case '12'://后督察
		           jcmbId = $('#HDCjcmbId').combobox("getValue");
		           fileTypeEnumName='HDCJCJL';
		           pyjx='HDC';
		           break;
		        case '13'://信访投诉
		           jcmbId = $('#XFTSjcmbId').combobox("getValue");
		           fileTypeEnumName='XFTSJCJL';
		           pyjx='XFTS';
		           break;
		        case '14'://排污许可证检查
		           jcmbId = $('#PWXKZJCjcmbId').combobox("getValue");
		           fileTypeEnumName='PWXKZJCJCJL';
		           pyjx='PWXKZJC';
		           break;
		        case '15'://专项行动
		           jcmbId = $('#ZXXDjcmbId').combobox("getValue");
		           fileTypeEnumName='ZXXDZRWJCJL';
		           pyjx='ZXXD';
		           break;
		        case '17'://限期治理
		           jcmbId = $('#XQZLjcmbId').combobox("getValue");
		           fileTypeEnumName='XQZLJCJL';
		           pyjx='XQZL';
		           break;
		        case '18'://跟踪检查
		           jcmbId = $('#GZJCjcmbId').combobox("getValue");
		           fileTypeEnumName='GZJCJCJL';
		           pyjx='GZJC';
		           break;
		        case '19'://自动监控
		           jcmbId = $('#ZDJKjcmbId').combobox("getValue");
		           fileTypeEnumName='ZDJKJCJL';
		           pyjx='ZDJK';
		           break;
		        case '20'://危险废物
		           jcmbId = $('#WXFWjcmbId').combobox("getValue");
		           fileTypeEnumName='WXFWJCJL';
		           pyjx='WXFW';
		           break;
		        case '21'://危险化学品
		           jcmbId = $('#WXHXPjcmbId').combobox("getValue");
		           fileTypeEnumName='WXHXPJCJL';
		           pyjx='WXHXP';
		           break;
		        case '22'://辐射安全
		           jcmbId = $('#FSAQjcmbId').combobox("getValue");
		           fileTypeEnumName='FSAQJCJL';
		           pyjx='FSAQ';
		           break;
		        case '23'://污染事故现场调查
		           jcmbId = $('#WRSGXCDCjcmbId').combobox("getValue");
		           fileTypeEnumName='WRSGXCDCJCJL';
		           pyjx='WRSGXCDC';
		           break;
		        case '24'://日常办公
		           pyjx='RCBG';
		           break;
		        default:
		           alert("还未定义code为"+n+"的任务类型");
		    }
		    if(jcmbId!=null && jcmbId!=''){
		        if(checkDoCheck(pyjx)){
		        	var href = "jcd_page.htm?jcmbId="+jcmbId+"&applyId="+applyId+"&taskType="+taskTypeCode; 
		        	var width=screen.width * 0.8-100;
		    	  	var height=screen.height * 0.8-100;
		          	//parent.layerIframe(2,href,"生成监察笔录",width,height);
		          	parent.layer.open({
		      	  	  type: 2,
		    	  	  title: "生成监察笔录",
		    	  	  maxmin: false,
		    	  	  shadeClose: false, //点击遮罩关闭层
		    	  	  area : [width+'px' , height+'px'],
		    	  	  content: href,
		    	  	  end:function(){
		    	  		if(taskTypeCode=='21'){
			            	commonShowFile(fileTypeEnumName);
			            }else{
			            	showJcbl(fileTypeEnumName,moreCheckFiletype,taskTypeCode);
			            } 
		    	  	  }
		    	  	  });
		           // All.ShowModalWin("jcd_page.htm?jcmbId="+jcmbId+"&applyId="+applyId+"&taskType="+taskTypeCode);
		            
		        }
			}else{
			    alert("请先选择监察模板。");
			}
			$('#xftsId').val(data.xftsId);
		}
   	});
}

//通用开始检查校验
function checkDoCheck(pyjx){
    if(!$("input[name='blZfdxForm.bjcr']").validatebox("isValid")){
    	alert("请输入环保负责人");
        return false;
    }
    if($("#zw").combobox("getText")==""){
    	alert("请选择环保负责人职务");
        return false;
    }
    if(!$("input[name='blZfdxForm.lxdh']").validatebox("isValid")){
    	alert("请输入环保负责人联系电话");
        return false;
    }
    var flag = 0;
	//检查人校验
	$("input[id='"+pyjx+"Jcr']:checkbox").each(function(){ 
		if ($(this).attr("checked")) { 
			flag += 1; 
		} 
	});
	if(flag==0){
		alert("请选择检查人");
        return false;
    }
    var jlr = $('#'+pyjx+'Jlr').combobox('getValue');
    if(jlr==""){
    	alert("请选择记录人");
        return false;
    }
    
    //信访投诉加“信访投诉来源”
    if(pyjx=='XFTS'){
        var xftsly = $('#xftsly').combobox('getValue');
	    if(xftsly==""){
	        return false;
	    }
    }
    return true;
}

//通用单附件回显
function commonShowFile(fileTypeEnumName){
    $.ajax({
	  url: "getCommonFile.json?applyId="+applyId+"&fileTypeEnumName="+fileTypeEnumName,
	  success:function(data){
	      if(data.commonFileMap!=null){
	          $('#filename_'+fileTypeEnumName).html(data.commonFileMap.filename);
	          $('#oper_'+fileTypeEnumName).html("<a class='o_btn btn_blue opm' onclick='review(this)' id='"+data.commonFileMap.id+","+data.commonFileMap.filesize1+"' >预览</a>&nbsp;"
	                               +"<a class='o_btn btn_blue opm' onclick=download2('"+data.commonFileMap.id+"')>下载</a>&nbsp;"
	                               +"<a class='o_btn btn_orange opm' onclick=singleDelete('"+data.commonFileMap.id+"','"+fileTypeEnumName+"')>删除</a>&nbsp;");
	      }else{
	          $('#filename_'+fileTypeEnumName).html("");
	          $('#oper_'+fileTypeEnumName).html("");
	      }
	   }
	});
}

//通用单附件回显1（导出模板上传对应的方法）
function commonShowFile1(fileTypeEnumName){
	var nameString="";
    $.ajax({
	  url: "getCommonFile.json?applyId="+applyId+"&fileTypeEnumName="+fileTypeEnumName,
	  success:function(data){
	      if(data.commonFileMap!=null){
	      		nameString += data.commonFileMap.filename +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"<a class='o_btn btn_blue opm' onclick='review(this)' id='"+data.commonFileMap.id+","+data.commonFileMap.filesize1+"' >预览</a>&nbsp;&nbsp;"
	                         	+"<a class='o_btn btn_blue opm' onclick=download2('"+data.commonFileMap.id+"')>下载</a>&nbsp;&nbsp;"
	                         	+"<a class='o_btn btn_orange opm' onclick=deleteJcbl('"+data.commonFileMap.id+"','"+fileTypeEnumName+"')>删除</a>&nbsp;";
				nameString	+= "<br/>";
	      	$('#filename_'+fileTypeEnumName).html(nameString);
	      }else{
	          $('#filename_'+fileTypeEnumName).html("");
	          $('#oper_'+fileTypeEnumName).html("");
	      }
	   }
	});
}

//监察笔录回显
function showJcbl(fileTypeEnumName,moreCheckFiletype,tasktypeCode){
    var nameString="";
    var array = new Array();
    $.ajax({
	  url: "getJcblFile.json?applyId="+applyId+"&fileTypeEnumName="+fileTypeEnumName+"&moreCheckFiletype="+moreCheckFiletype+"&tasktypeCode="+tasktypeCode,
	  success:function(data){
	      if(data.jcjlFileMap!=null){
	      	array = data.jcjlFileMap;
	      	for(var i = 0; i < array.length; i++){
	      		nameString += array[i].filename +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"<a class='o_btn btn_blue opm' onclick='review(this)' id='"+array[i].id+","+array[i].filesize1+"' >预览</a>&nbsp;&nbsp;"
	                         	+"<a class='o_btn btn_blue opm' onclick=download2('"+array[i].id+"')>下载</a>&nbsp;&nbsp;"
	                         	+"<a class='o_btn btn_orange opm' onclick=deleteJcbl('"+array[i].id+"','"+array[i].fileTypeEnumName+"','"+array[i].moreCheckFiletype+"','"+array[i].tasktypeCode+"')>删除</a>&nbsp;";
	            if(array[i].type == "1"){
	            	nameString += "<a class='o_btn btn_blue opm' onclick=editMoreCheck('"+array[i].tasktypeCode+"','"+array[i].id+"')>修改</a>&nbsp;";
	            }
				nameString	+= "<br/><br/>";
	      	}
	      	$('#filename_'+fileTypeEnumName).html(nameString);
	      }else{
	        $('#filename_'+fileTypeEnumName).html("");
	      }
	   	}
	});
}

//下一步操作前先保存。operType(操作类型):'xwbl','kcbl'...
function saveBeforeNext(operType){
    $('#blForm').attr('action','saveWorkzxBL.json');
	$('#blForm').ajaxSubmit(function(data){
   		if(data.state){
			//next操作
			switch(operType){
		        case 'xwbl'://询问笔录制作
		           zzxwbl();
		           break;
		        case 'kcbl'://勘察笔录制作
		           zzkcbl();
		           break;
		        case 'ZDJKJCJL'://在线制作
		           onlineCreate();
		           break;
		        case 'HBJCJL'://合并文档
		           comboWord();
		           break;
		        default:
		           alert("没有该操作");
		    }
		    $('#xftsId').val(data.xftsId);
		}else{
			$.messager.alert('任务办理保存:',data.msg);
		}
   	});
}

//勘察询问笔录删除。operType(操作类型):'xwbl','kcbl'...
function singleDeleteKcxwbl(id,operType){
	var index=layer.confirm('确定要删除吗？', {
     	icon: 3, 
        title:'操作'
     }, function(index){
    	 if(operType == 'ZDJKJCJL'){
				$.ajax({
					url: "removeZxzzJcbl.json?id="+id,
					success:function(data){
						alert(data.msg);
						showzdjkfj(operType);
					}
				});
			}else{
				$.ajax({
					url: "removeFile.json?id="+id,
					success:function(data){
						//刷新页面
						showkcxwbl(operType);
					}
				});
			}
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
   
}

function singleUploadKcxwblCheck(operType){
    //判断是否有文件
    var hasFileM=false;
    var filenameTEXT=$('#filename_'+operType).text().trim();
    if(filenameTEXT!=''){
        hasFileM=true;
    }
    if(hasFileM){
    	var tishi=layer.alert("该类型附件只有一份，重新生成或上传后覆盖之前的文件",{
	     	title:'单附件上传',
	        icon:1,
	        shadeClose:true,
	     },
	     function(){
	    	singleUploadKcxwbl(operType);
	        layer.close(tishi);
	     }
	     );
       
    }else{
        singleUploadKcxwbl(operType);
    }
}

//在线制作方法
function onlineCreate(){
	var zzmblx = $('#zzmblx').combobox('getValue');
	if(zzmblx!=null && zzmblx!=''){
		if(!$("input[name='blZfdxForm.bjcr']").validatebox("isValid")){
       		return false;
    	}
    	if(!$("input[name='blZfdxForm.lxdh']").validatebox("isValid")){
        	return false;
    	}
    	var flag = 0;
		//检查人校验
		$("input[id='ZDJKJcr']:checkbox").each(function(){
			if ($(this).attr("checked")) {
				flag += 1;
			}
		});
		if(flag==0){
			alert("请选择检查人！");
        	return false;
   		}
		var width=screen.width * 0.8-100;
	  	var height=screen.height * 0.8-100;
		parent.layer.open({
		       type:2,
		       title:'在线制作',
		       area:[width+'px' , height+'px'],
		       shadeClose:false,
		       maxmin:true,
		       content:"onlineCreate.htm?applyId="+applyId+"&zzmblx="+zzmblx,
		    	end:function(){
		    		showzdjkfj('ZDJKJCJL');	
		    	}
		     });
		//All.ShowModalWin("onlineCreate.htm?applyId="+applyId+"&zzmblx="+zzmblx,'在线制作',1200,1200);
		
	}else{
		alert("请选在线制作模板类型！");
	}
}

//在线下载合并文档的方法
function comboWord(){
	var hbbt = document.getElementById('hbbt');
	var bllx = "ZDJKJCJL";
	var nameString = "";
	var operString = "";
	var array = new Array();
	var fileid = "";
	hbbt.disabled = "disabled";
    $.ajax({
	  url: "getCopyFiles.json?applyId="+applyId+"&bllx="+bllx,
	  success:function(data){
	      if(data.id!=null){
	      	alert("合并文档成功!");
	      	//fileid = data.id;
	      	//$('#download').attr('src','downloadFile?id='+fileid);
	      	showzdjkfj('ZDJKJCJL');
	      }else{
	        alert("请确认有在线制作的文档！");
	      }
	      hbbt.removeAttribute('disabled');
	   }
	});
}

//勘察询问笔录上传。operType(操作类型):'xwbl','kcbl'...
function singleUploadKcxwbl(operType){
    $('#blForm').attr('action','saveWorkzxBL.json');
	$('#blForm').ajaxSubmit(function(data){
   		if(data.state){
			//next操作
			switch(operType){
		        case 'xwbl'://询问笔录制作
		           All.ShowModalWin("singleUploadPage.htm?pid="+applyId+"&fileType=WFAJDCXWBL&path=WORK","附件上传",630,200);
		           showkcxwbl(operType);
		           break;
		        case 'kcbl'://勘察笔录制作
		           All.ShowModalWin("singleUploadPage.htm?pid="+applyId+"&fileType=WFAJDCKCBL&path=WORK","附件上传",630,200);
		           showkcxwbl(operType);
		           break;
		        default:
		           alert("没有该操作");
		    }
		    $('#xftsId').val(data.xftsId);
		}else{
			$.messager.alert('任务办理保存:',data.msg);
		}
   	});
}

//询问笔录制作
function zzxwbl(){
    var checkedNodesIds="";
    var t = $('#wflx').combotree('tree'); // 得到树对象 
	var checkedNodes = t.tree('getChecked');
	for(var i=0;i<checkedNodes.length;i++){
	    if(i<checkedNodes.length-1){
	        checkedNodesIds+=checkedNodes[i].id+',';
	    }else{
	        checkedNodesIds+=checkedNodes[i].id;
	    }
	}
	if(checkedNodesIds!=null && checkedNodesIds!=''){
      	//parent.layerIframe(2,href,"笔录制作",width,height);
		//All.ShowModalWin("xwbl.htm?applyId="+applyId+"&wflx="+checkedNodesIds);
		var href = "xwbl.htm?applyId="+applyId+"&wflx="+checkedNodesIds;
		layer.open({
				      type: 2,
				      title: '笔录制作',
				      area: ['1000px', '460px'],
				      content:href
		});
	}else{
		alert("请选择违法类型！");
	}
}
//显示勘察询问笔录（'kcbl','xwbl'）
function showkcxwbl(bllx){
    $.ajax({
	  url: "getKcxwblFile.json?applyId="+applyId+"&bllx="+bllx,
	  success:function(data){
	      if(data.kcxwblFileMap!=null){
	          $('#filename_'+bllx).html(data.kcxwblFileMap.filename);
	          $('#oper_'+bllx).html("</br></br><a class='o_btn btn_blue opm' onclick='review(this)' id='"+data.kcxwblFileMap.id+","+data.kcxwblFileMap.filesize1+"' >预览</a>&nbsp;"
	                               +"<a class='o_btn btn_blue opm' onclick=download2('"+data.kcxwblFileMap.id+"')>下载</a>&nbsp;"
	                               +"<a class='o_btn btn_orange opm' onclick=singleDeleteKcxwbl('"+data.kcxwblFileMap.id+"','"+bllx+"')>删除</a>&nbsp;");
	      }else{
	          $('#filename_'+bllx).html("");
	          $('#oper_'+bllx).html("");
	      }
	      
	   }
	});
}


//多个附件的回显（'ZDJKJCJL'）
function showzdjkfj(bllx){
	var nameString = "";
	var operString = "";
	var array = new Array();
    $.ajax({
	  url: "getZxzzFiles.json?applyId="+applyId+"&bllx="+bllx,
	  success:function(data){
	      if(data.kcxwblFileMap!=null){
	      	array = data.kcxwblFileMap;
	      	for(var i = 0; i < array.length; i++){
	      		nameString += (array[i].filename +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"<a class='o_btn btn_blue opm' onclick='review(this)' id='"+array[i].id+","+array[i].filesize1+"' >预览</a>&nbsp;&nbsp;"
	                         	+"<a class='o_btn btn_blue opm' onclick=download2('"+array[i].id+"')>下载</a>&nbsp;&nbsp;"
	                         	+"<a class='o_btn btn_orange opm' onclick=singleDeleteKcxwbl('"+array[i].id+"','"+bllx+"')>删除</a>&nbsp;</br></br>")
	      	}
	      	document.getElementById('zdjkfile').innerHTML = nameString;
	      }else{
	          $('#filename_'+bllx).html("");
	          $('#oper_'+bllx).html("");
	      }
	   }
	});
}

//勘察笔录制作
function zzkcbl(){
    var checkedNodesIds="";
    var t = $('#wflx').combotree('tree'); // 得到树对象 
	var checkedNodes = t.tree('getChecked');
	for(var i=0;i<checkedNodes.length;i++){
	    if(i<checkedNodes.length-1){
	        checkedNodesIds+=checkedNodes[i].id+',';
	    }else{
	        checkedNodesIds+=checkedNodes[i].id;
	    }
	}
	if(checkedNodesIds!=null && checkedNodesIds!=''){
		var href = "kcbl.htm?applyId="+applyId+"&wflx="+checkedNodesIds; 
		layer.open({
				      type: 2,
				      title: '笔录制作',
				      area: ['1000px', '460px'],
				      content:href
		});
	}else{
		alert("请选择违法类型！");
	}
}
//信访投诉任务再次办理
function zcbl(){
	//办理信息清空
	$("#hjxfblqk").val("");
	$("#hfxs").combobox("setValue","");
	$("#hfxsxm").val("");
	$("#hfxsdyrn").val("");
	$("#hfr").val("");
	$("#hfrName").val("");
	$("#fhr").val("");
	$("#bcqk").val("");
	$("#bcr").val("");
	inputTipText();
}
//办结、办结上报
function bj(){
	//办理次数+1
	if($("#bjqk").val()==""){
		$("#bjqk").val(1);
	}else{
		var bjqk = parseInt($("#bjqk").val());
		$("#bjqk").val(bjqk+1);
	}
	$("#bjqkInfo").html($("#bjqk").val()+"次办结");
	//保存
    $('#blForm').attr('action','saveWorkzxBL.json');
    $('#blForm').ajaxSubmit(function(data){
   		if(data.state){
			 alert($("#bjqk").val()+"次办理办结成功");
			 $('#xftsId').val(data.xftsId);
		}else{
			$.messager.alert('任务办理保存:',data.msg);
		}
   	});
}
//信访终结
function xfzj(){
	var index=layer.confirm('确定进行信访终结吗？（信访任务被终结，将不可进行再次办理）', {
     	icon: 3, 
        title:'操作'
     }, function(index){
    	 if($("#bjqk").val()=="0"){
			 alert("该信访任务已经被终结");
			 return;
		}
		$("#bjqk").val("0");
		$("#bjqkInfo").html("信访终结");
		$('#zcbl').hide();
		$('#bjsb').hide();
		$('#xfzj').hide();
		//保存
	    $('#blForm').attr('action','saveWorkzxBL.json');
    	$('#blForm').ajaxSubmit(function(data){
	   		if(data.state){
				 alert("信访任务被终结，不可进行再次办理");
				 $('#xftsId').val(data.xftsId);
			}else{
				$.messager.alert('任务办理保存:',data.msg);
			}
   		});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}

//办理
function bl() {
    //前台校验
    if($("#blForm").form('validate')){
        //保存
        $('#blForm').attr('action','saveWorkzxBL.json');
		$('#blForm').ajaxSubmit(function(data){
	   		if(data.state){
				//后台校验
		        $.post('checkBlBL.json', {
					applyId : applyId,
					zfdxInfo : zfdxInfo
				}, function(json) {
					if (json.state) {
						//跳到报告
						parent.changeTitle("2");
					} else {
						//提示信息
						//alert(json.msg);
						var index=layer.alert(json.msg,{
        	        	   		     	title:'办理',
        	        	   		        icon:1,
        	        	   		        shadeClose:true,
        	        	 },
        	        	 function(){
        	        	   		        layer.close(index);
        	        	  }
        	        	  );
					}
				}, 'json');
				$('#xftsId').val(data.xftsId);
			}else{
				$.messager.alert('任务办理保存:',data.msg);
			}
	   	});
	}
}

//添加打包功能
function db() {
    //前台校验
    if($("#blForm").form('validate')){
        //保存
        $('#blForm').attr('action','CreateXML.json');
		$('#blForm').ajaxSubmit(function(data){
	   		if(data.state){
				//后台校验
		        $.post('checkBlBL.json', {
					applyId : applyId
				}, function(json) {
					if (json.state) {
						//跳到打包页面
						parent.$("#tt").tabs('select','打包');
					} else {
						//提示信息
						alert(json.msg);
					}
				}, 'json');
			}else{
				$.messager.alert('任务办理保存:',data.msg);
			}
	   	});
	}
}

function fuzhu(){
    var zfdxid='${blMainForm.blZfdxForm.lawobjid}'
	All.ShowModalWin('lawobjInfo.htm?id='+zfdxid, '辅助信息查看');
}

function download2(id){
	$('#download').attr('src','downloadFile?id='+id);
}
function deletefile2(id){
	var index=layer.confirm('确定要删除吗？', {
     	icon: 3, 
        title:'操作'
     }, function(index){
    	 $.ajax({
				url: "removeFile.json?id="+id,
				success:function(data){
					alert(data.msg);
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}

//保存草稿
function baocun(){
	//$('#blForm').attr('action','');
	$('#blForm').ajaxSubmit({
		   type: 'post', // 提交方式 get/post
            url: 'saveWorkzxBL.json', // 需要提交的 url
		success:function(data){
			if(data.state){
				alert(data.msg);
				lxdbbtn.removeAttribute('disabled');
				$('#xftsId').val(data.xftsId);
			}else{
				$.messager.alert('任务办理保存:',data.msg);
			}
		}
	});
	return false;
}

//单附件上传
function singleUpload(fileTypeEnumName){
    //判断是否有监察报告
    var hasFileM=false;
    var filenameTEXT=$('#filename_'+fileTypeEnumName).text().trim();
    if(filenameTEXT!=''){
        hasFileM=true;
    }
    if(hasFileM){
    	var tishi=layer.alert("该类型附件只有一份，重新生成或上传后覆盖之前的文件",{
	     	title:'单附件上传',
	        icon:1,
	        shadeClose:true,
	     },
	     function(){
	    	 All.ShowModalWin("singleUploadPage.htm?pid="+applyId+"&fileType="+fileTypeEnumName+'&path=WORK',"附件上传",630,200);
             commonShowFile(fileTypeEnumName);
	        layer.close(tishi);
	     }
	     );
    }else{
        All.ShowModalWin("singleUploadPage.htm?pid="+applyId+"&fileType="+fileTypeEnumName+'&path=WORK',"附件上传",630,200);
        commonShowFile(fileTypeEnumName);
    }
}

//单附件上传1（回显方法的不一样）
function singleUpload1(fileTypeEnumName){
    //判断是否有监察报告
    var hasFileM=false;
    var filenameTEXT=$('#filename_'+fileTypeEnumName).text().trim();
    if(filenameTEXT!=''){
        hasFileM=true;
    }
    if(hasFileM){
        var tishi=layer.alert("该类型附件只有一份，重新生成或上传后覆盖之前的文件",{
	     	title:'单附件上传',
	        icon:1,
	        shadeClose:true,
	     },
	     function(){
	    	 All.ShowModalWin("singleUploadPage.htm?pid="+applyId+"&fileType="+fileTypeEnumName+'&path=WORK',"附件上传",630,200);
             commonShowFile1(fileTypeEnumName);
	        layer.close(tishi);
	     }
	     );
    }else{
        All.ShowModalWin("singleUploadPage.htm?pid="+applyId+"&fileType="+fileTypeEnumName+'&path=WORK',"附件上传",630,200);
        commonShowFile1(fileTypeEnumName);
    }
}

//单附件下载
function singleDownload(fileId){
	$('#download').attr('src','downloadFile?id='+fileId);
}

//单附件导出下载
function exportFile(fileId){
	$('#download').attr('src','exportFile?id='+fileId+'&applyId='+applyId);
}
//单附件删除
function singleDelete(id,fileTypeEnumName){
	var index=layer.confirm('确定要删除吗？', {
     	icon: 3, 
        title:'操作'
     }, function(index){
    	 $.ajax({
				url: "removeFile.json?id="+id,
				success:function(data){
					alert(data.msg);
					commonShowFile(fileTypeEnumName);
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}
//删除监察笔录
function deleteJcbl(id,fileTypeEnumName,moreCheckFiletype,tasktypeCode){
	var index=layer.confirm('确定要删除吗？', {
     	icon: 3, 
        title:'操作'
     }, function(index){
    	 $.ajax({
				url: "removeJcbl.json?id="+id,
				success:function(data){
					alert(data.msg);
					showJcbl(fileTypeEnumName,moreCheckFiletype,tasktypeCode);
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
	
}

/////附件操作/////
/**
 * 公用的上传文件之后的刷新grid方法
 * @param tableId tablegrid的id
 */
function reload(tableId,pid,fileType){
	var id = "#" + tableId;
	$(id).datagrid("reload");
	jQuery.colorbox.close();
}
/**
 * 公用的删除文件方法 删除grid中的文件
 * @param obj grid的一行数据
 */
function deletefile1(obj){
	var index=layer.confirm('确定要删除吗？', {
     	icon: 3, 
        title:'操作'
     }, function(index){
    	 $.ajax({
				url: "removeFile.json?id="+obj.id,
				success:function(data){
					alert(data.msg);
					var reloadtable= $(obj).attr("reloadtable");//自定义属性采用此方式获得 
					$('#'+reloadtable).datagrid('reload');
				}
			});
    	 
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}
//通用（再次检查）
function commonMoreCheck(taskTypeCode){
	var hasjcjlFileM=false;
	switch(taskTypeCode){//根据code判断任务类型
		        case '10'://现场监察
		           var filenameTEXT=$('#filename_RCJCJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        case '11'://年度核查
		           var filenameTEXT=$('#filename_NDHCJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        case '12'://后督察
		           var filenameTEXT=$('#filename_HDCJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        case '13'://信访投诉
		           var filenameTEXT=$('#filename_XFTSJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;   
		        case '14'://排污许可证检查
		           var filenameTEXT=$('#filename_PWXKZJCJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        case '15'://专项行动
		           var filenameTEXT=$('#filename_ZXXDZRWJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        case '17'://限期治理
		           var filenameTEXT=$('#filename_XQZLJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        case '18'://跟踪检查
		           var filenameTEXT=$('#filename_GZJCJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        case '20'://危险废物
		           var filenameTEXT=$('#filename_WXFWJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        case '22'://辐射安全
		           var filenameTEXT=$('#filename_FSAQJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        case '23'://污染事故现场调查
		           var filenameTEXT=$('#filename_WRSGXCDCJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        default:
		           alert("还未定义code为"+n+"的任务类型");
		    }
	if(hasjcjlFileM){
	//开始填写前先保存
    $('#blForm').attr('action','saveWorkzxBL.json');
	$('#blForm').ajaxSubmit(function(data){
   		if(data.state){
			var jcmbId="";
		    switch(taskTypeCode){//根据code判断任务类型
		        case '10'://现场监察
		           jcmbId = $('#RCJCjcmbId').combobox("getValue");
		           fileTypeEnumName='RCJCJCJL';
		           moreCheckFiletype='RCJCMOREJCBL';
		           tasktypeCode='10';
		           var filenameTEXT=$('#filename_RCJCJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        case '11'://年度核查
		           jcmbId = $('#NDHCjcmbId').combobox("getValue");
		           fileTypeEnumName='NDHCJCJL';
		           moreCheckFiletype='NDHCMOREJCBL';
		           tasktypeCode='11';
		           var filenameTEXT=$('#filename_NDHCJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        case '12'://后督察
		           jcmbId = $('#HDCjcmbId').combobox("getValue");
		           fileTypeEnumName='HDCJCJL';
		           moreCheckFiletype='HDCMOREJCBL';
		           tasktypeCode='12';
		           var filenameTEXT=$('#filename_HDCJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		         case '13'://信訪投訴
		           jcmbId = $('#XFTSjcmbId').combobox("getValue");
		           fileTypeEnumName='XFTSJCJL';
		           moreCheckFiletype='XFTSMOREJCBL';
		           tasktypeCode='13';
		           var filenameTEXT=$('#filename_XFTSJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        case '14'://排污许可证检查
		           jcmbId = $('#PWXKZJCjcmbId').combobox("getValue");
		           fileTypeEnumName='PWXKZJCJCJL';
		           moreCheckFiletype='PWXKZMOREJCBL';
		           tasktypeCode='14';
		           var filenameTEXT=$('#filename_PWXKZJCJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        case '15'://专项行动
		           jcmbId = $('#ZXXDjcmbId').combobox("getValue");
		           fileTypeEnumName='ZXXDZRWJCJL';
		           var filenameTEXT=$('#filename_ZXXDZRWJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        case '17'://限期治理
		           jcmbId = $('#XQZLjcmbId').combobox("getValue");
		           fileTypeEnumName='XQZLJCJL';
		           moreCheckFiletype='XQZLMOREJCBL';
		           tasktypeCode='17';
		           var filenameTEXT=$('#filename_XQZLJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        case '18'://跟踪检查
		           jcmbId = $('#GZJCjcmbId').combobox("getValue");
		           fileTypeEnumName='GZJCJCJL';
		           moreCheckFiletype='GZJCMOREJCBL';
		           tasktypeCode='18';
		           var filenameTEXT=$('#filename_GZJCJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        case '20'://危险废物
		           jcmbId = $('#WXFWjcmbId').combobox("getValue");
		           fileTypeEnumName='WXFWJCJL';
		           moreCheckFiletype='WXFWMOREJCBL';
		           tasktypeCode='20';
		           var filenameTEXT=$('#filename_WXFWJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        case '22'://辐射安全
		           jcmbId = $('#FSAQjcmbId').combobox("getValue");
		           fileTypeEnumName='FSAQJCJL';
		           moreCheckFiletype='FSAQMOREJCBL';
		           tasktypeCode='22';
		           var filenameTEXT=$('#filename_FSAQJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        case '23'://污染事故现场调查
		           jcmbId = $('#WRSGXCDCjcmbId').combobox("getValue");
		           fileTypeEnumName='WRSGXCDCJCJL';
		           moreCheckFiletype='WRSGXCDCMOREJCBL';
		           tasktypeCode='23';
		           var filenameTEXT=$('#filename_WRSGXCDCJCJL').text().trim();
		           if(filenameTEXT!=''){
		               hasjcjlFileM=true;
		           }
		           break;
		        default:
		           alert("还未定义code为"+n+"的任务类型");
		    }
		    if(hasjcjlFileM){
			    var href = "zcjc_page.htm?applyId="+applyId+"&taskTypeCode="+taskTypeCode+"&jcmbId="+jcmbId+"&fileTypeEnumName="+fileTypeEnumName+"&moreCheckFiletype="+moreCheckFiletype; 
				layer.open({
				      type: 2,
				      title: '再次检查',
				      area: ['650px', '400px'],
				      content:href
				 });
		    }else{
		        alert("请先生成监察笔录");
		    }
		    $('#xftsId').val(data.xftsId);
		}
	});
	}
}
//修改多次检查的检查情况
function editMoreCheck(taskTypeCode,fileId){
	$.ajax({
				url: "getJcqk.json?fileId="+fileId,
				success:function(data){
					 var jcmbId="";
					 switch(taskTypeCode){//根据code判断任务类型
				        case '10'://现场监察
				           jcmbId = $('#RCJCjcmbId').combobox("getValue");
				           fileTypeEnumName='RCJCJCJL';
				           moreCheckFiletype='RCJCMOREJCBL';
				           break;
				        case '11'://年度核查
				           jcmbId = $('#NDHCjcmbId').combobox("getValue");
				           fileTypeEnumName='NDHCJCJL';
				           moreCheckFiletype='NDHCMOREJCBL';
				           break;
				        case '12'://后督察
				           jcmbId = $('#HDCjcmbId').combobox("getValue");
				           fileTypeEnumName='HDCJCJL';
				           moreCheckFiletype='HDCMOREJCBL';
				           break;
				         case '13'://信訪投訴
				           jcmbId = $('#XFTSjcmbId').combobox("getValue");
				           fileTypeEnumName='XFTSJCJL';
				           moreCheckFiletype='XFTSMOREJCBL';
				           break;
				        case '14'://排污许可证检查
				           jcmbId = $('#PWXKZJCjcmbId').combobox("getValue");
				           fileTypeEnumName='PWXKZJCJCJL';
				           moreCheckFiletype='PWXKZMOREJCBL';
				           break;
				        case '15'://专项行动
				           jcmbId = $('#ZXXDjcmbId').combobox("getValue");
				           fileTypeEnumName='ZXXDZRWJCJL';
				           var filenameTEXT=$('#filename_ZXXDZRWJCJL').text().trim();
				           break;
				        case '17'://限期治理
				           jcmbId = $('#XQZLjcmbId').combobox("getValue");
				           fileTypeEnumName='XQZLJCJL';
				           moreCheckFiletype='XQZLMOREJCBL';
				           break;
				        case '18'://跟踪检查
				           jcmbId = $('#GZJCjcmbId').combobox("getValue");
				           fileTypeEnumName='GZJCJCJL';
				           moreCheckFiletype='GZJCMOREJCBL';
				           break;
				        case '20'://危险废物
				           jcmbId = $('#WXFWjcmbId').combobox("getValue");
				           fileTypeEnumName='WXFWJCJL';
				           moreCheckFiletype='WXFWMOREJCBL';
				           break;
				        case '22'://辐射安全
				           jcmbId = $('#FSAQjcmbId').combobox("getValue");
				           fileTypeEnumName='FSAQJCJL';
				           moreCheckFiletype='FSAQMOREJCBL';
				           break;
				        case '23'://污染事故现场调查
				           jcmbId = $('#WRSGXCDCjcmbId').combobox("getValue");
				           fileTypeEnumName='WRSGXCDCJCJL';
				           moreCheckFiletype='WRSGXCDCMOREJCBL';
				           break;
				        default:
				           alert("还未定义code为"+n+"的任务类型");
				    }
					 var href = "zcjc_page.htm?applyId="+applyId+"&taskTypeCode="+taskTypeCode+"&jcmbId="+jcmbId+"&fileTypeEnumName="+fileTypeEnumName+"&moreCheckFiletype="+moreCheckFiletype+"&jcqk="+data.jcqk+"&fileId="+fileId; 
					 layer.open({
					      type: 2,
					      title: '再次检查',
					      area: ['650px', '400px'],
					      content:href
					 });
				}
			});
			
}
//跳转到添加环境违法行为限期改正通知页面
function gztzAdd(){
	var appleId=$('#applyId').val();
	var taskTypeId=$('#rwlx').val();
	var href = "gztzList.htm?taskId="+appleId+"&taskTypeId="+taskTypeId; 
  	var width=screen.width * 0.8-100;
  	var height=screen.height * 0.8-100;
  //	parent.layerIframe(2,href,"待办任务",width,height);
  	parent.layer.open({
	  	  type: 2,
	  	  title: "环境违法行为限期改正通知",
	  	  maxmin: true,
	  	  shadeClose: false, //点击遮罩关闭层
	  	  area : [width+'px' , height+'px'],
	  	  content: href,
	  	  end:function(){
	  		$('#XQZLdata').datagrid('reload');
	  	  }
	  	  });
}
function jdssdhzAdd(){
	var appleId=$('#applyId').val();
	var taskTypeId=$('#rwlx').val();
	var href = "jdssdhzAdd.htm?taskId="+appleId+"&taskTypeId="+taskTypeId; 
  	var width=screen.width * 0.8-100;
  	var height=screen.height * 0.8-100;
  //	parent.layerIframe(2,href,"待办任务",width,height);
  	parent.layer.open({
	  	  type: 2,
	  	  title: "行政处罚决定书送达回执",
	  	  maxmin: true,
	  	  shadeClose: false, //点击遮罩关闭层
	  	  area : [width+'px' , height+'px'],
	  	  content: href,
	  	  end:function(){
	  		$('#XQZLdata').datagrid('reload');
	  	  }
	  	  });
}
//告知书
function gzssdhzAdd(){
	var appleId=$('#applyId').val();
	var taskTypeId=$('#rwlx').val();
	var href = "tzgzssdhzAdd.htm?taskId="+appleId+"&taskTypeId="+taskTypeId; 
  	var width=screen.width * 0.8-100;
  	var height=screen.height * 0.8-100;
  //	parent.layerIframe(2,href,"待办任务",width,height);
  	parent.layer.open({
	  	  type: 2,
	  	  title: "（听证）告知书送达回证",
	  	  maxmin: true,
	  	  shadeClose: false, //点击遮罩关闭层
	  	  area : [width+'px' , height+'px'],
	  	  content: href,
	  	  end:function(){
	  		$('#XQZLdata').datagrid('reload');
	  	  }
	  	  });
}

//导出辐射安全模板
function exportFsaq(biaoshi){
	if(checkDoCheck("FSAQ")){
		$('#blForm').attr('action','saveWorkzxBL.json');
		$('#blForm').ajaxSubmit(function(data){});
	$('#download').attr('src',"buildFsaqWord.json?lawobjId="+$("#lawobjId").val()+"&lawobjType="+$("#lawobjType").val()+"&biaoshi="+biaoshi+"&appleId="+$("#applyId").val());
	}else{
		return false;
	}
}
	
//信访任务办理后选择期限重新派发任务
function cpTask(){
	var appleId=$('#applyId').val();
	var endtime=$('#slsj').val();
	$.ajax({
		type : "post",
		url : "rwzbl.json?applyId="+appleId+"&slsj="+endtime,
		async : false,
		success : function(data) {
			alert(data.msg);
		}
	});
}
</script>
