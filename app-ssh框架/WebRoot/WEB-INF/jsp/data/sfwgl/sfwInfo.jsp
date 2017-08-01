<%@ include file="/common/taglibs.jsp" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>档案管理</title>
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/layout.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>

    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.form.js"></script>

    <script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.media.js"></script>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
    <link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css"/>
    <!--poshytip-->
    <link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css"/>
    <script type="text/javascript" src="${poshytip}/jquery.poshytip.min.js"></script>
    <!--时间插件 my97-->
    <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
    <!--档案管理-->
    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${common}/All.js"></script>
    <style>
        .dataTable td {
            border: 1px solid #d4d4d4;
            font-size: 16px;
        }
    </style>
</head>
<body>
<h1 id="checkup_header" style="height:60px;">文件信息查看</h1>
<input type="hidden" value="${sfwForm.id}" id="id" name="id">
<table id="taskTable_con" style="margin: 0 auto" class="dataTable" width="95%" cellpadding="0" cellspacing="0">
    <tr>
        <td width="10%" bgcolor='#edfaff' align="right">收文/发文：</td>
        <td width="10%">${sfwForm.type}</td>
        <td width="10%" align="right" bgcolor='#edfaff'>文档分类：</td>
        <td width="10%">${sfwForm.sourcepname}</td>
        <td width="10%" bgcolor='#edfaff' align="right">文档子类型：</td>
        <td width="10%">${sfwForm.sourcename}</td>
    </tr>
    <tr>
        <td align="right" bgcolor='#edfaff'>文件标题：</td>
        <td colspan="3">${sfwForm.title}</td>
        <td align="right" bgcolor='#edfaff'>文号：</td>
        <td>${sfwForm.number}</td>
    </tr>
    <tr>
    	<td align="right" bgcolor='#edfaff'>日期：</td>
        <td>${sfwForm.sfwdate}</td>
        <td align="right" bgcolor='#edfaff'>签名：</td>
        <td>${sfwForm.autograph}</td>
        <td align="right" bgcolor='#edfaff'>存放位置：</td>
        <td>${sfwForm.position}</td>
    </tr>
    <tr>
        <td colspan="6">
            <div class="divContainer" style="height:350px; width:100%;">
                <table id="XGFJdata" fit="true"></table>
            </div>
        </td>
    </tr>
</table>
<iframe name="download" id="download" src="" style="display: none"></iframe>
<script type="text/javascript">
    //相关附件列表
    $('#XGFJdata').datagrid({
//        pagination: true,//分页控件
        height: 'auto',
        rownumbers: true,
        singleSelect: true,
        fitColumns: true,
        url: 'queryFileList.json?canDel=N',
        queryParams: {pid: $("#id").val(), tableId: 'XGFJdata'},
        onLoadSuccess: function (data) {
            $(this).datagrid('doCellTip', {'max-width': '300px', 'delay': 500});
        },
        columns: [[
            {field: 'id', hidden: true},
            {field: 'filename', title: '附件名称', align: 'left', halign: 'center', width: 430},
            {field: 'filesize', title: '附件大小', align: 'center', halign: 'center', width: 100},
            {field: 'operate', title: '操作', align: 'center', halign: 'center', width: 130}
        ]]
    });
    function back() {
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
    }

</script>
</body>
</html>