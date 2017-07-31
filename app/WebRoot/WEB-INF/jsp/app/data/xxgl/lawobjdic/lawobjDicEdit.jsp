<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>信息管理-执法对象字典管理</title>
        <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
        <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
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
		<!-- <link href="${app }/hnjz.css" rel="stylesheet" type="text/css" /> -->
		<link rel="stylesheet" type="text/css" href="${colorbox }/colorbox.css">
        <link href="${app }/easyUIReset.css" rel="stylesheet" type="text/css"/>
        <link href="${app }/attachFileList.css" rel="stylesheet" type="text/css"/>
        <link href="${app }/css/task.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="${common}/All.js"></script>
    <style type="text/css" media="screen">
        input.colchiname{ border:none;}
        input[type=button]{border:none; background: none; color: #00a2d9; cursor: pointer;}
        table.dataTable,table.dataTable td { border: 1px solid #d4d4d4}
    </style>
</head>
<body style="padding-bottom: 70px;">
<div class="checkup">
    <table style="display: none;width:100%" class="dataTable mt25" border="1px" bordercolor="#d4d4d4" cellpadding="0"
           cellspacing="0">
        <tr id="innit" style="">
            <td align="center">
                <input class="" style="width: 20px; border:none" name="orderby" value="1" readonly="readonly"/>
                <input type="hidden" name="id" value=""/>
            </td>
             <td align="center">
                <input class=" colengname "style="width: 100%;" data-options="required:true" name="colengname" value="${lawobjdic.colengname }"/>
             </td>
            <td align="center">
                <input class=" enumname" style="width: 100%;" name="enumname"/>
            </td>
            
            <td align="center">
                <input class=" colchiname easyui-validatebox" style="width: 100%;" name="colchiname" data-options="required:true" />
            </td>
            <td align="center">
                <input class=" inputtype " data-options="required:true" style="width: 100%;" name="inputtype"/>
            </td>
            <td align="center">
                <input class=" datasource" style="width: 100%;" name="datasource"/>
            </td>
            <td align="center">
                <input class=" mandatory" style="width: 100%;" name="mandatory"/>
            </td>
            <td align="center">
                <input class=" istwoitem" style="width: 100%;" name="istwoitem"/>
            </td>
            <td align="center">
                <input type="button" name="up" value="置顶" onclick="javascript:upstartvar(this);"/>
                <input type="button" name="up" value="上移" onclick="javascript:upvar(this);"/>
                <input type="button" name="down" value="下移" onclick="javascript:downvar(this);"/>
                <input type="button" name="add" value="添加行" onclick="javascript:addvar();"/>
                <input type="button" name="del" value="删除本行" onclick="javascript:delvar(this);"/>
            </td>
        </tr>

    </table>
    <form id="queryForm" action="saveOrUpdateLawobjDic.json" method="post">
        <h1 id="checkup_header" style="font-weight: normal;">${title}</h1>
        <table style="width:100%" border="0" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td width="10%"><span class="red">*</span>执法对象类型</td>
                <td><input class="y-text" id="lawobjtypeid" name="lawobjtypeid" value="${lawobjtypeid}"/>
                </td>
            </tr>
        </table>
        <!-- -->

        <table class="dataTable mt25" style="width:100%" border="1px" bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
            <tr bgcolor="#cff1ff" height="48">
                <td align="center">
                    排序
                </td>
                
                <td align="center">
                    列名
                </td>
                <td align="center">
                    对外名称
                </td>
                <td align="center">
                    列中文名称
                </td>
                <td align="center">
                    输入方式
                </td>
                <td align="center">
                    数据来源
                </td>
                <td align="center">
                    必填
                </td>
                <td align="center">
                    两列显示
                </td>
                <td align="center">
                    操作
                </td>
            </tr>
            <c:forEach items="${list }" var="lawobjdic">
            <tr name="trOne">
                <td align="center">
                    <input class="" style="width:20px; border:none;" name="orderby"  value="${lawobjdic.orderby}" readonly="readonly"/>
                    <input type="hidden" name="id" value="${lawobjdic.id}"/>
                </td>
                
                <td align="center">
                    <input class="colengname " data-options="required:true" style="width: 100%;" name="colengname" value="${lawobjdic.colengname }"/>
                </td>
                <td align="center">
                    <input class="enumname" style="width: 100%;"  name="enumname" value="${lawobjdic.enumname }"/>
                </td>
                <td align="center">
                    <input class=" colchiname easyui-validatebox" data-options="required:true" style="width: 100%;" name="colchiname" value="${lawobjdic.colchiname}"/>
                </td>
                <td align="center">
                    <input class=" inputtype " data-options="required:true" style="width: 100%;"name="inputtype" value="${lawobjdic.inputtype}"/>
                </td>
                <td align="center">
                    <input class=" datasource" style="width: 100%;" name="datasource" value="${lawobjdic.datasource}"/>
                </td>
                <td align="center">
                <c:if test="${lawobjdic.mandatory!=null && lawobjdic.mandatory!='' }">
                    <input class=" mandatory" style="width: 100%;" name="mandatory" value="${lawobjdic.mandatory }"/>
                </c:if>
                <c:if test="${lawobjdic.id==null || lawobjdic.id=='' }">
                <input class=" mandatory" style="width: 100%;" name="mandatory" value="Y"/>
				</c:if>			    
                </td>
                <td align="center">
                <c:if test="${lawobjdic.mandatory!=null && lawobjdic.mandatory!='' }">
                    <input class=" istwoitem" style="width: 100%;" name="istwoitem" value="${lawobjdic.istwoitem}"/>
                </c:if>
                <c:if test="${lawobjdic.id==null || lawobjdic.id=='' }">
                  <input class=" istwoitem" style="width: 100%;" name="istwoitem" value="N"/>
                </c:if>    
                </td>
                <td align="center">
                    <input type="button" name="up" value="置顶" onclick="javascript:upstartvar(this);"/>
                    <input type="button" name="up" value="上移" onclick="javascript:upvar(this);"/>
                    <input type="button" name="down" value="下移" onclick="javascript:downvar(this);"/>
                    <input type="button" name="add" value="添加行" onclick="javascript:addvar();"/>
                    <input type="button" name="del" value="删除本行" onclick="javascript:delvar(this);"/>
                </td>
            </tr>
             </c:forEach>
             <c:if test="${list==null || fn:length(list)<=0 }">
            <tr name="trOne">
                <td align="center">
                    <input class="" style="width:20px; border:none;" name="orderby"  value="1" readonly="readonly"/>
                    <input type="hidden" name="id" value=""/>
                </td>
                
                <td align="center">
                   <input class=" colengname " style="width: 100%;" name="colengname" data-options="required:true" value="${lawobjdic.colengname}"/>
                </td>
                <td align="center">
                     <input class=" enumname" style="width: 100%;" name="enumname" data-options="required:true" />
                </td>
                <td align="center">
                    <input class=" colchiname easyui-validatebox" data-options="required:true" style="width: 100%;" name="colchiname" />
                </td>
                <td align="center">
                    <input class=" inputtype " data-options="required:true" style="width: 100%;"name="inputtype" />
                </td>
                <td align="center">
                    <input class=" datasource" style="width: 100%;" name="datasource" />
                </td>
                <td align="center">
                <input class=" mandatory" style="width: 100%;" name="mandatory" value="Y"/>
                </td>
                <td align="center">
                  <input class=" istwoitem" style="width: 100%;" name="istwoitem" value="N"/>
                </td>
                <td align="center">
                    <input type="button" name="up" value="置顶" onclick="javascript:upstartvar(this);"/>
                    <input type="button" name="up" value="上移" onclick="javascript:upvar(this);"/>
                    <input type="button" name="down" value="下移" onclick="javascript:downvar(this);"/>
                    <input type="button" name="add" value="添加行" onclick="javascript:addvar();"/>
                    <input type="button" name="del" value="删除本行" onclick="javascript:delvar(this);"/>
                </td>
            </tr>
            </c:if>
        </table>


        <div class="rb_btn_fix" >
            <input type="submit" class="queryBlue" value="保  存" style="cursor: pointer;">
        </div>
    </form>
</div>
<script>
    //复制第一行
    var trOne = $("#innit").first().clone();
    trOne.attr("name", "trOne");

    //添加行
    function addvar() {
        var trLength = $("tr[name='trOne']").length;
        var trNew = trOne.clone();
        var length = $("tr[name='trOne']").last().children().children().first().val();
        var i = parseInt(length);
        if (!isNaN(i)) {
            trNew.children().children().first().val(i + 1);
            $("tr[name='trOne']").last().after(trNew);
            trNew.children().eq(1).children("input[name='colengname']").combobox({
                type: 'GET',
                width:120,
                height:30,
                panelHeight:300,
                url: 'lawobjColumnList.json',
                valueField: 'id',
                textField: 'name'
            });
            var lawobjType = $('#lawobjtypeid').combobox('getValue');
            if (lawobjType != null && lawobjType!='') {
                trNew.children().eq(2).children("input[name='enumname']").combobox({
                    type: 'GET',
                    width:100,
                    height:30,
                    panelHeight:300,
                    url: 'lawobjOutColunmList.json?type='+lawobjType,
                    valueField: 'id',
                    textField: 'name',
                    onHidePanel:function(){
    					trNew.children().eq(3).children("input[name='colchiname']").val($(this).combobox('getText'));
    				}
                });
            }
            trNew.children().eq(4).children("input[name='inputtype']").combobox({
                type: 'GET',
                width:100,
                height:30,
                url: 'inputTypeList.json',
                valueField: 'id',
                textField: 'name',
                onChange:function(newValue, oldValue){
    				$(this).parent().next().first().children(".datasource").combobox({
    					url:'dataSourceList.json?inputType='+newValue,
    					valueField:'id',
    					textField:'name'
    				});
    			}
            });
            /*trNew.children().eq(5).children("input[name='datasource']").combobox({
                type: 'GET',
                width:150,
                height:30,
                url: 'json/dataSourceList.json',
                valueField: 'id',
                textField: 'name',

            });*/
            trNew.children().eq(6).children("input[name='mandatory']").combobox({
                type: 'GET',
                width:100,
                height:30,
                url: 'sfList.json',
                value: 'Y',
                valueField: 'id',
                textField: 'name'
            });

            trNew.children().eq(7).children("input[name='istwoitem']").combobox({
                type: 'GET',
                width:100,
                height:30,
                url: 'sfList.json',
                value: 'N',
                valueField: 'id',
                textField: 'name'
            });
            $.parser.parse(trNew);
        }
    }
    //删除当前行
    function delvar(e) {
        var trLength = $("tr[name='trOne']").length;
        if (trLength > 1) {
            e.parentNode.parentNode.parentNode.removeChild(e.parentNode.parentNode);
        }
        $.each($("tr[name='trOne']"), function (i, n) {
            $(n).children().children().first().val(i + 1);
        });
    }
    //置顶
    function upstartvar(e) {
        $(e).parent().parent().insertBefore($("tr[name='trOne']").first());
        $.each($("tr[name='trOne']"), function (i, n) {
            $(n).children().children().first().val(i + 1);
        });
    }
    //上移当前行
    function upvar(e) {
        $(e).parent().parent().insertBefore($(e).parent().parent().prev("tr[name='trOne']"));
        $.each($("tr[name='trOne']"), function (i, n) {
            $(n).children().children().first().val(i + 1);
        });
    }

    //下移当前行
    function downvar(e) {
        $(e).parent().parent().insertAfter($(e).parent().parent().next("tr[name='trOne']").first());
        $.each($("tr[name='trOne']"), function (i, n) {
            $(n).children().children().first().val(i + 1);
        });
    }

    $(document).ready(function () {
        $.ajaxSetup({cache: false});
        //执法对象类型
    $('#lawobjtypeid').combobox({
        width:300,
        height:38,
        url:'dicList.json?type=5',
        valueField:'id',
        textField:'name',
        panelHeight:300,
        onChange:function(newValue, oldValue){
			location.reload('lawobjDicEdit.htm?title=${title}&lawobjtypeid='+newValue);
		}
        
    });
    //对外名称
    $(".enumname").combobox({
        width:100,
        height:30,
        panelHeight:300,
        url:'lawobjOutColunmList.json?type='+$('#lawobjtypeid').combobox('getValue'),
        valueField:'id',
        textField:'name',
        onHidePanel:function(){
			$(this).parent().parent().children().eq(3).children("input[name='colchiname']").val($(this).combobox('getText'));
		}
        
    });
    //列名
    $("input[name='colengname']").combobox({
        type:'GET',
        width:120,
        height:30,
        panelHeight:300,
        url:'lawobjColumnList.json',
        valueField:'id',
        textField:'name'
    });
    //输入类型
    $(".inputtype").combobox({
        type:'GET',
        width:100,
        height:30,
        url:'inputTypeList.json',
        valueField:'id',
        textField:'name',
        panelHeight:300,
        onChange:function(newValue, oldValue){
			$(this).parent().next().first().children(".datasource").combobox({
				url:'dataSourceList.json?inputType='+newValue,
				valueField:'id',
				textField:'name'
			});
		},
		onLoadSuccess:function(){
			$(this).parent().next().first().children(".datasource").combobox({
				url:'dataSourceList.json?inputType='+$(this).combobox('getValue'),
				valueField:'id',
				textField:'name'
			});
		}
    });
    /*数据来源
    $(".datasource").combobox({
        ttype: 'GET',
        width:150,
        height:30,
        url: 'json/dataSourceList.json',
        valueField: 'id',
        textField: 'name',

    });*/
    //是否必填
    $("input[name='mandatory']").combobox({
        type:'GET',
        width:100,
        height:30,
        url:'sfList.json',
        valueField:'id',
        textField:'name'
    });
    //是否两列显示
    $("input[name='istwoitem']").combobox({
        type:'GET',
        width:100,
        height:30,
        url:'sfList.json',
        valueField:'id',
        textField:'name'
    });
     
  //表单校验 提交
    $("#queryForm").validate({
		errorClass: "error",
		submitHandler:function(form){
			if($("#queryForm").form("validate")){
				$(form).ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"saveOrUpdateLawobjDic.json",
					success: function(data){
						if(data.state){
							//alert(data.msg);
							//location.reload('lawobjDicEdit.htm?title=${title}&lawobjtypeid='+$("#lawobjtypeid").combobox('getValue'));
							var index=layer.alert(data.msg,{
						     	title:'操作信息:',
						        icon:1,
						        shadeClose:true,
						     },
						     function(){
						    	location.reload('lawobjDicEdit.htm?title=${title}&lawobjtypeid='+$("#lawobjtypeid").combobox('getValue'));
						        layer.close(index);
						        Android.close(data.id, data.name);
						      });
						}else{
							$.messager.alert('保存执法对象字典:',data.msg);
						}
					}
				});
			}
		}
	});

});

    //高度自适应
    $(window).resize(function () {
        vheight();
    });
</script>
</body>
</html>
