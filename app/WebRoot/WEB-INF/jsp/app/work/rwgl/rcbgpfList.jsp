<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>任务派发—列表</title>
    <script type="text/javascript" src="${app}/js.js"></script>
	<script type="text/javascript" src="${app}/data.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
    <link href="${app}/common.css" rel="stylesheet" type="text/css" />
    
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <!--poshytip-->
    <link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
    <!--派发列表-->
    <link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${poshytip}/jquery.poshytip.min.js"></script>
    <script type="text/javascript" src="${common}/All.js"></script>
</head>
<body>
<div id="searchMask" class="searchMask"><p>点击查看更多查询条件</p></div>
<div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;">
   <form id="queryForm" method="post" action="getRcbgRwpfList.json">
   <input type="hidden" id="fid" name="fid" value="${fid}" />
   <input type="hidden" id="page" name="page" value="${page}" />
   <input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
   <table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
         <tr>
           <th width="34%">任务名称</th>
           <td width="12%"><input class="y-text" type="text" id="rwmc" name="rwmc" /></td>
           <th width="12%">任务来源</th>
           <td width="42%"><input class="y-text" type="text" id="rwly" name="rwly" /></td>
         </tr>
         <tr>
           <td align="center" colspan="6">
            <input  id="query" type="submit" class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
            <input  id="J-from-reset" type="reset" class="queryOrange" value="重　置"/>
           </td>
         </tr>
   </table>
   </form>
   <div class="closeBtn">收起</div>
</div>
<div id="layer1" class="layer"></div>
<h1 id="title" class="h1_1 topMask">任务派发</h1>
<div id="oper" style="width:95%; margin:-23px  auto 0px;" class="t-r">
    <input type="button" class="bTn directory_save directory_comwidth"  value="任务派发" onclick="hideDialog('pfPage.htm?rwlxType=24&lx=1')"/>
</div>
<div class="divContainer" id="rbblist" style=" width:100%; margin:16px auto 0px;">
    <table id="data" fit="true" ></table>
</div>

<script type="text/javascript">
var searchMask=$("#searchMask");
var searchForm=$("#searchForm");
var layer1=$("#layer1");
layer1.hide();
searchForm.hide();
var hideSearchBtn=searchForm.find(".closeBtn");
function hideSearchForm(){
	searchForm.hide();
	layer1.hide();
}
function showSearchForm(){
	searchForm.show();
	layer1.show();
}
searchMask.bind("click",showSearchForm);
hideSearchBtn.bind("click",hideSearchForm);

function initH(){
	var hh=$(window).height() - $("#title").outerHeight()-$("#oper").outerHeight()+7;
	$("#rbblist").height(hh);
}
initH();
//派发
function pf(curObj) {
	//window.location.href='pfPage.htm?applyId=' + curObj.id+'&lx=1';
	var href = 'pfPage.htm?applyId=' + curObj.id+'&lx=1'; 
  	var width=screen.width * 0.9;
  	var height=screen.height * 0.8-50;
  //	parent.layerIframe(2,href,"待办任务",width,height);
  	parent.layer.open({
	  	  type: 2,
	  	  title: "待办任务",
	  	  maxmin: true,
	  	  shadeClose: false, //点击遮罩关闭层
	  	  area : [width+'px' , height+'px'],
	  	  content: href,
	  	  end:function(){
	  		$('#data').datagrid('reload');
	  	  }
	  	  });
	//refresh();
}

//查看
function info(curObj) {
	var href = 'pfPageInfo.htm?applyId=' + curObj.id; 
  	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	parent.layerIframe(2,href,"任务派发查看",width,height);
	//All.ShowModalWin('pfPageInfo.htm?applyId=' + curObj.id, '任务派发查看');
	//refresh();
}
//数据表格初始化
$('#queryForm').submit(function() {
		$("#queryForm").ajaxSubmit( {
			success : function(data) {
				$('#data').datagrid('loadData', data)
			}
		});
		return false;
});
//重置
$("#J-from-reset").click(function() {
	$("#queryForm").reset();
	//任务来源下拉框
		$('#rwly').combobox('setValues', '');
});
$(document).ready(function() {
		//任务来源下拉框
		$('#rwly').combobox( {
			height:34,
			url : 'dicList.json?type=1',
			valueField : 'id',
			textField : 'name'
		});

		$('#data').datagrid( {
			nowrap : false,
			striped : true,
			collapsible : true,
			singleSelect : true,
			fitColumns : true,
			pagination : true,
			rownumbers : true,
			url : 'getRcbgRwpfList.json',
			onLoadSuccess : function(data) {
				$(this).datagrid('doCellTip', {
					'max-width' : '300px',
					'delay' : 500
				});
			},
			columns : [ [ {field : 'rwmc1',title : '任务名称',	width : 300}, 
			              {field : 'rwly',title : '任务来源',	align : 'center',width : 100},
			              {field : 'jjcd',title : '紧急程度',	align : 'center',width : 100},
			              {field : 'djr',title : '登记人',align : 'center',width : 100},
			              {field : 'scsj',title : '生成时间',align : 'center',	width : 100},
			              {field : 'operate',title : '操作',align : 'center',	width : 150} ] ]
			});
		initPager();
	});
//删除
function del(curObj) {
	var index=layer.confirm('确定删除吗？', {
     	icon: 3, 
        title:'删除任务'
     }, function(index){
    	 $.ajax( {
				url : "delpf.json?applyId=" + curObj.id,
				success : function(data) {
					refresh();
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}
//派发
function distribute() {
	window.location.href="pfPage.htm?lx="+"1";
	//All.ShowModalWin('pfPage.htm', '任务派发');
	//refresh();
}
//批量派发
function batchDistribute() {
	window.location.href="plpfPage.htm";
	//All.ShowModalWin('plpfPage.htm', '批量派发');
	//refresh();
}
    //var layer=$("#layer");
    var dailogMenu=$("#dailogMenu");
    dailogMenu.hide();
    layer1.hide();
    var dWidth=dailogMenu.width();
    function initPostition(){
        var winW=$(window).width();
        var left=(winW-dWidth)/2+"px";
        dailogMenu.css({"left":left});
    }
    initPostition();
    $(window).bind("resize",initPostition);
   
    function hideDialog(t){
        var href = t; 
	  	var width=screen.width * 0.9;
	  	var height=screen.height * 0.8-50;
	  //	parent.layerIframe(2,href,"待办任务",width,height);
	  	parent.layer.open({
		  	  type: 2,
		  	  title: "任务派发",
		  	  maxmin: true,
		  	  shadeClose: false, //点击遮罩关闭层
		  	  area : [width+'px' , height+'px'],
		  	  content: href,
		  	  end:function(){
		  		$('#data').datagrid('reload');
		  	  }
		  	  });

    }
    function showDialog(){
        dailogMenu.show();
        layer1.show();

    }
    //数据表格自使用宽度
    $(window).resize(function(){
        $('#data').datagrid("resize");
		initH();
    });
</script>
</body>
</html>
