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
</head>
<body>
<h1 id="checkup_header" style="height:60px;">文件录入</h1>

<form id="myform" method="post" action="sfwSave.json">
    <table id="taskTable_con" style="margin: 0 auto" class="dataTable" width="95%" cellpadding="0" cellspacing="0">
        <input type="hidden" value="${sfwForm.id}" id="id" name="id">
        <tr>
            <th width="60" nowrap="nowrap" align="right"><font color="red"> * </font>收文/发文：</th>
            <td><input type="text" class="y-text" id="type" name="type" value="${sfwForm.type}"/></td>
            <th width="60" nowrap="nowrap" align="right">文档分类：</th>
            <td width="60"><input type="text" class="y-text" id="sourcepid" name="sourcepid"
                                  value="${sfwForm.sourcepid}"/></td>
            <th width="60" nowrap="nowrap" align="right">文档子类型：</th>
            <td width="60"><input type="text" class="y-text" id="sourceid" name="sourceid" value="${sfwForm.sourceid}"/>
            </td>
        </tr>
        <tr>
            <th align="right">文件标题：</th>
            <td colspan="5"><input type="text" class="y-text" style="width:80%" id="title" name="title"
                                   value="${sfwForm.title}"/></td>
        </tr>
        <tr>
            <th align="right">年度：</th>
            <td><input type="text" class="y-text" id="year" name="year"/></td>
            <th align="right">编号：</th>
            <td><input type="text" class="y-text" id="num" name="num"/></td>
            <th align="right">文号：</th>
            <td><input type="text" class="y-text" id="number" name="number" value="${sfwForm.number}"/></td>
        </tr>
        <tr>
            <th align="right">日期：</th>
            <td><input type="text" id="sfwdate" name="sfwdate" value="${sfwForm.sfwdate}" style="width: 120px;"
                       class="y-dateTime" onclick="WdatePicker()"></td>
            <th align="right">签名：</th>
            <td><input type="text" id="autograph" name="autograph" value="${sfwForm.autograph}" class="y-text"></td>
            <th align="right" width="160">存放位置：</th>
            <td><input type="text" id="position" name="position" value="${sfwForm.position}" class="y-text"></td>
        </tr>
        <tr>
            <td></td>
            <td colspan="5"><input type="button" id="XGFJfileupload" class="bTn blue bTn_distance" value="上传附件"
                                   style="margin:0;cursor: pointer"></td>
        </tr>
        <tr>
            <td></td>
            <td colspan="5">
                <div class="divContainer" style="height:200px; width:100%;">
                    <table id="XGFJdata" fit="true"></table>
                </div>
            </td>
        </tr>
        <iframe name="download" id="download" src="" style="display: none"></iframe>
    </table>
    <div class="t-c" style="margin-top: 15px;">
        <input id="savebutton" type="submit" class="queryBlue" value="保存">
        <input type="reset" class="queryOrange" id="J-from-reset" value="重　置"/>
    </div>
    <script type="text/javascript">
        //收文/发文
        $('#type').combobox({
            height: '34',
            url: 'sfwlxList.json',
            valueField: 'id',
            textField: 'name',
            onChange: function (newValue, oldValue) {
                $("#sourcepid").combobox('setValue', '');
                $("#sourceid").combobox('setValue', '');
                $('#sourcepid').combobox('reload', 'wdflQuery.json?type=' + newValue);
            }
        });
        //文档分类
        $('#sourcepid').combobox({
            height: '34',
            url: 'wdflQuery.json?type=' + $('#type').combobox('getValue'),
            valueField: 'id',
            textField: 'name',
            onChange: function (newValue, oldValue) {
                $("#sourceid").combobox('setValue', '');
                $('#sourceid').combobox('reload', 'wdzlxQuery.json?pid=' + newValue);
                if ($('#sourceid').combobox('getValue') == null || $('#sourceid').combobox('getValue') == "") {
                    inintNumber(newValue);
                }
            }
        });
        //文档子类型
        $('#sourceid').combobox({
            height: '34',
            url: 'wdzlxQuery.json?pid=' + $('#sourcepid').combobox('getValue'),
            valueField: 'id',
            textField: 'name',
            onChange: function (newValue, oldValue) {
                inintNumber(newValue);
            }
        });
        //按下按键修改文号
        $('#year').keyup(function () {
            inintNumber("");
        });
        //按下按键修改文号
        $('#num').keyup(function () {
            inintNumber("");
        });
        //初始化文号
        function inintNumber(newValue) {
            var wdlx = "";
            //获取年度
            var year = $('#year').val();
            //获取编号
            var num = $('#num').val();
            if (newValue == "" || newValue == null) {
                var strs = new Array(); //定义一数组
                if ($('#number').val() != null && $('#number').val() != "") {
                    strs = $('#number').val().split("〔");
                    if (strs.length > 1) {
                        wdlx = strs[0];
                    }
                }
                $('#number').val(wdlx + "〔" + year + "〕" + num + "号");
            } else {
                //根据id获取文档类型名称
                $.ajax({
                    url: "getWdlxById.json?id=" + newValue,
                    success: function (data) {
                        $('#number').val(data.name + "〔" + year + "〕" + num + "号");
                    }
                });
            }
        }
        //数据表格初始化
        //相关附件列表
        $('#XGFJdata').datagrid({
//                pagination:true,//分页控件
            height: 'auto',
            rownumbers: true,
            singleSelect: true,
            fitColumns: true,
            url: 'queryFileList.json?canDel=Y',
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
        //相关附件上传
        $("#XGFJfileupload").click(function () {
            var fileType = 'WJGLPFFJ';//派发附件
            var id = $("#id").val();
            if (id != null && id != '') {
                $.colorbox({
                    iframe: true, width: "610", height: "380",
                    href: 'uploadPage.htm?pid=' + id + '&fileType=' + fileType + '&path=WORK&tableId=XGFJdata'
                });
            } else {
                $("#myform").ajaxSubmit({
                    type: "post",
                    dataType: "json",
                    url: "sfwSave.json",
                    success: function (data) {
                        if (data.state) {
                            $("#id").val(data.id);
                            $.colorbox({
                                iframe: true, width: "610", height: "380",
                                href: 'uploadPage.htm?pid=' + $("#id").val() + '&fileType=' + fileType + '&path=WORK&tableId=XGFJdata'
                            });
                        } else {
                            $.messager.alert('生成:', data.msg);
                        }
                    }
                });
            }
        });
        $("#J-from-reset").click(function () {
            $("#myform").form('reset');
        });
        //表单校验
        $("#myform").validate({
            errorClass: "error",
            submitHandler: function (form) {
                if ($("#myform").form("validate")) {
                    $(form).ajaxSubmit({
                        type: "post",
                        dataType: "json",
                        url: "sfwSave.json",
                        success: function (data) {
                            if (data.state) {
                                var index = layer.alert(data.msg, {
                                            title: '保存文件信息:',
                                            icon: 1,
                                            shadeClose: true,
                                        },
                                        function () {
                                            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                                            parent.parent.layer.close(index);
                                            refresh();
                                        });
                            } else {
                                $.messager.alert('保存文件信息:', data.msg);
                            }
                        }
                    });
                }
            }
        });
        /**
         * 公用的删除文件方法 删除grid中的文件
         * @param obj grid的一行数据
         */
        function deletefile1(obj) {
            var index = layer.confirm('确定删除吗？', {
                        icon: 3,
                        title: '删除'
                    }, function (index) {
                        $.ajax({
                            url: "removeFile.json?id=" + obj.id,
                            success: function (data) {
                                alert(data.msg);
                                $('#XGFJdata').datagrid('reload');
                            }
                        });
                        layer.close(index);
                    }, function (index) {
                        layer.close(index);
                    }
            );
        }
    </script>
</form>
</body>
</html>