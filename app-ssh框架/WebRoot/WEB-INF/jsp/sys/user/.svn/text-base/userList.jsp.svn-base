<%@ include file="/common/taglibs.jsp" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户列表</title>

    <script type="text/javascript" src="${app}/js.js"></script>
    <script type="text/javascript" src="${app}/data.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.form.js"></script>
    <!-- colorbox -->
    <script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
    <link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css"/>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <!--poshytip-->
    <link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css"/>
    <!--派发列表-->
    <link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${poshytip}/jquery.poshytip.min.js"></script>
    <script type="text/javascript" src="${common}/All.js"></script>
    <!--时间插件 my97-->
    <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
    <!--css-->
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
   		<link href="${app}/common.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<!-- <div id="searchMask" class="searchMask"><p>点击查看更多查询条件</p></div> -->
<div style="padding-top:10px;">
    <form id="queryForm" method="post" action="userQuery.json">
        <input type="hidden" id="page" name="page" value="${page}"/>
        <input type="hidden" id="pageSize" name="pageSize" value="${pageSize}"/>
        <table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
            <tr>
                <th width="15%">用户名</th>
                <td width="15%"><input type="text" class="y-text" id="name" name="name" value="${name}" size="30"
                                       jzTips="请输入用户名/登录名"/></td>
                <th width="15%">是否可用</th>
                <td width="15%">
                    <input class="y-text" type="text" id="isActive" name="isActive"/>
                </td>
                <td width="40%">
                    <input type="submit" id="query" class="queryBlue" value="查　询" />
                    <input type="button" id="J-from-reset" class="queryOrange" value="重　置"/>
                </td>
            </tr>
        </table>
    </form>
   <!--  <div class="closeBtn">收起</div> -->
</div>
<!-- <div id="layer1" class="layer"></div> -->
<h1 id="divTitle" class="h1_1 topMask" style="padding-top:0px;">用户管理</h1>
<div id="addForm" style="width:95%; margin:-7px  auto 0px;" class="t-r">
    <input type="button" class="bTn blue bTn_distance" id="userAdd" value="添加"/>
</div>
<div class="divContainer" id="userlist" style=" width:100%; margin:10px auto 0px;">
    <table id="data" fit="true"></table>
</div>
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
    //var searchMask = $("#searchMask");
   // var searchForm = $("#searchForm");
   // var layer1 = $("#layer1");
   // layer1.hide();
   // searchForm.hide();
   //var hideSearchBtn = searchForm.find(".closeBtn");
   // function hideSearchForm() {
   //     searchForm.hide();
  //      layer1.hide();
  //  }
  //   function showSearchForm() {
  //      searchForm.show();
  //       layer1.show();
  //   }
   // searchMask.bind("click", showSearchForm);
  //  hideSearchBtn.bind("click", hideSearchForm);


    function initH() {
        var hh = $(window).height() - $("#divTitle").outerHeight() - $("#addForm").outerHeight() - 80;
        $("#userlist").height(hh);
    }
    initH();
    ////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////
    $(document).ready(function () {
        $("#userAdd").click(function () {
            layer.open({
                type: 2,
                title: '添加新用户',
                area: ['900px', '420px'],
                shadeClose: false,
                maxmin: true,
                content: "editUser.htm"
            });
        });
        if ('${orgid }' != '') {
            setOrgInfo('${orgid }', '${orgname }');
        }
        //部门
        $("#selectOrg").colorbox({
            iframe: true,
            width: "300",
            height: "400",
            href: "${basePath}/orgPubQuery.htm?multi=true"
        });
        //区域
        $('#areaid').combobox({
            height: 34,
            url: 'queryAreaCur.json',
            valueField: 'id',
            textField: 'name',
            onSelect: function (record) {//做区域部门的联动选择
                $("#selectOrg").colorbox({
                    iframe: true,
                    width: "300",
                    height: "400",
                    href: "${basePath}/orgAdminQuery.htm?multi=true&areaid=" + record.id
                });
            }
        });
        $('#isActive').combobox({
            height: 34,
            data: [{'id': 'Y', 'name': '是'}, {'id': 'N', 'name': '否'}],
            value: 'Y',
            editable: false,
            valueField: 'id',
            textField: 'name'
        });
        $('#data').datagrid({
            nowrap: false,
            striped: true,
            collapsible: true,
            singleSelect: true,
            remoteSort: false,
            pagination: true,
            rownumbers: true,
            fitColumns: true,
            url: 'userQuery.json',
            onLoadSuccess: function (data) {
                $(this).datagrid('doCellTip', {'max-width': '300px', 'delay': 500});
            },
            columns: [[
                {field: 'name', title: '用户名', sortable: true, width: 80},
                {field: 'userName', title: '登录名', width: 80},
                {field: 'orgname', title: '所在部门', width: 30, align: 'center'},
                {field: 'operate', title: '操作', align: 'center', width: 50}
            ]]
        });
        initPager();
    });
    function edit(curObj) {
        layer.open({
            type: 2,
            title: '编辑用户信息',
            area: ['900px', '420px'],
            shadeClose: false,
            maxmin: true,
            content: "editUser.htm?id=" + curObj.id
        });
    }
    function del(curObj) {
        var index = layer.confirm('确定要删除当前用户吗？', {
                    icon: 3,
                    title: '用户管理'
                }, function (index) {
                    $.ajax({
                        url: "delUser.json?id=" + curObj.id,
                        success: function (data) {
                            var tishi = layer.alert(data.msg, {
                                        title: '信息提示',
                                        icon: 1,
                                        shadeClose: true,
                                    },
                                    function () {
                                        $('#data').datagrid("reload");
                                        layer.close(tishi);
                                    }
                            );


                        }
                    });
                    layer.close(index);
                }, function (index) {
                    layer.close(index);
                }
        );
    }

    $('#queryForm').submit(function () {
        $("#queryForm").ajaxSubmit({
            success: function (data) {
                $('#data').datagrid('loadData', data)
            }
        });
        return false;
    });
    function closeLayer() {
        layer.closeAll('iframe');
        $('#data').datagrid("reload");
    }
    $("#J-from-reset").click(function () {

        $("#queryForm").form("clear");

    });
    //设置部门
    function setOrgInfo(id, name) {
        $("#orgname").val(name);
        $("#orgid").val(id);
        jQuery().colorbox.close();
    }
</SCRIPT>