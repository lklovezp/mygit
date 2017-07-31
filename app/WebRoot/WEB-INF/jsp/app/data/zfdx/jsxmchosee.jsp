<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>任务-执法对象选择</title>
<link href="${app}/css/resetCSS.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/taskcommon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${app}/js.js"></script>
<script type="text/javascript" src="${app}/data.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css"/>
<script type="text/javascript" src="${common}/All.js"></script>
<link href="${app }/hnjz.css" rel="stylesheet" type="text/css" />
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/css/easyUIReset.css" rel="stylesheet" type="text/css"/>
</head>
<style>
  #checkup_header{  text-align: center;
    height: 20px;
    line-height: 20px;
    color: #262626;
    font-size: 18px;
    font-weight: normal;}
}

</style>
<body>

<div class="dataDiv" style="width:95%; margin:16px auto;">
    <h1 id="checkup_header">执法对象选择</h1>
    <form id="queryForm" action="queryZfdxList.json" method="post">
     <input type="hidden" id="fid" name="fid" value="${fid}" />
	<input type="hidden" id="page" name="page" value="${page}" />
	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
	<input type="hidden"  id="lawobjtypeid" name="lawobjtypeid" value="${lawobjtypeid}">
    <table class="noborder mt25" width="100%" border="0px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">      
        <tr height="48">
            <td width="8%" align="right">监管部门：</td>
            <td width="15%"><input class="y-text" id="ssjgbm" name="orgId" style="width:186px;" /></td>
            <td width="8%" align="right">企业名称：</td>
            <td width="35%"><input class="y-text" id="dwmc" name="dwmc" style="width:186px;" />
            <input type="submit"  id="query"  class="o_btn btn_blue" style="padding: 5px 20px; margin-left: 20px;" value="搜索"/>
            <input type="button" id="J-from-reset"  class="o_btn btn_blue"  style="padding: 5px 20px; margin-left: 20px;"value="重置"/>
           <!--   <a class='o_btn btn_blue' id="query" style="padding: 5px 20px; margin-left: 20px;">搜索</a>
            <a class='o_btn btn_grey' id="J-from-reset" style="padding: 5px 20px; margin-left: 20px;">重置</a></td>
        -->
        </tr>
    </table>
    </form>
    <!-- 待选执法对象 -->
   
        <div class="annex_header" style="width:94%;font-size: 16px;">
           <!--  <a class="o_btn btn_blue" style="float:right;line-height: 30px; color:#fff; margin-top:7px;font-size: 14px;"  >确认选择</a> -->
            待选执法对象
        </div>
        <div class="annex_con" style="width:100%;height:248px;">
            <table id="data" style="width:100%;" fit="true"></table>
        </div>
   
    
</div>
<script>

 //相关附件table
    $('#data').datagrid({
        nowrap: false,//自动截取
        striped: true,//显示条纹
        collapsible:true,//是否添加折叠按钮
        
        fitColumns:true,//自适应列宽
        remoteSort:false,//是否远程排序
		checked:true,
        url:'queryZfdxList.json?lawobjtypeid='+$("#lawobjtypeid").val(),//请求数据的超链接地址
        //pageSize : 10,//默认选择的分页是每页10行数据
        //pageList: [5,10,15,20],//可以设置每页记录条数的列表
        columns:[[//设置表头
            {field:'ck',checkbox:true},
            {field:'id',hidden:true},
            {field:'hbfzr',hidden:true},
			{field:'hbfzrdh',hidden:true},
            {field:'dwmc',title:'企业名称',align:'center',halign:'center',width:4},
            {field:'dwdz',title:'地址', align:"left", halign:'center',width:5},
			{field:'orgmc',title:'所属监管部门', align:"center", halign:'center',width:3},
			{field:'fddbr',title:'法人', align:"center", halign:'center',width:3},
			{field:'fddbrdh',title:'法人电话', align:"center", halign:'center',width:3},
			{field:'operate',title:'操作', align:"center", halign:'center',width:3,
				formatter:function(value,rowData,rowIndex){  
		    		return '<a onclick="value(\''+ rowData.id +', '+ rowData.dwmc +', '+ rowData.dwdz+', '+ rowData.fddbr+', '+ rowData.fddbrdh+', '+ rowData.hbfzr+', '+ rowData.hbfzrdh+'\')" class="b-link">选择</a>';  
		       }  
			}
        ]],
       
       pagination:true,//是否添加底部的分页
       rownumbers:true// 是否显示左侧的行序号
       
    });
 
    function value(data){
    	var arr = new Array();
    	arr = data.split(",");
    	parent.setValues(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6]);
    	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		parent.layer.close(index);
    }

//相关附件table
    $('#choseedata').datagrid({
        nowrap: false,//自动截取
        striped: true,//显示条纹
        collapsible:true,//是否添加折叠按钮
        singleSelect:true,//单选模式，只允许选取一行
        fitColumns:true,//自适应列宽
        remoteSort:false,//是否远程排序
        url:'querySelectLawobjList.json?rwid=${rwid}&lawobjtype=${lawobjtype}',//请求数据的超链接地址
       // pageSize : 10,//默认选择的分页是每页10行数据
      //  pageList: [5,10,15,20],//可以设置每页记录条数的列表			
        columns:[[
            {field:'ck',checkbox:true},
            {field:'lawobjfid',hidden:true},
            {field:'address',hidden:true},
			{field:'fddbr',hidden:true},
			{field:'fddbrlxdh',hidden:true},
			{field:'hbfzr',hidden:true},
			{field:'hbfzrlxdh',hidden:true},
			{field:'regionid',hidden:true},
            {field:'name',title:'名称', align:"center", width:3}
        ]],
       pagination:false,//是否添加底部的分页
       rownumbers:false// 是否显示左侧的行序号
       
    });	
$(function() {

$('#queryForm').submit(function(){  
    $("#queryForm").ajaxSubmit({
   	  success: function(data){
   	      intiTips();
   	      $('#data').datagrid('loadData',data)
	          initPager();
	      }
	 });
   return false;  
});

$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
		    var p = $('#data').datagrid('getPager');
		   $('#page').val($(p).pagination('options').pageNumber);
		   $('#pageSize').val($(p).pagination('options').pageSize);
	});
	$('#ssjgbm').combotree({
		height:34,
		url:'orgTree.json',
		valueField:'id',
		textField:'name'
	}).combobox("initClear");
	$(".noborder,.noborder tr,.noborder td").css('border','none');
    $('.datagrid-htable, .datagrid-header-row,.datagrid-row-over,.datagrid-header td.datagrid-header-over').css('background','none');
	$('.datagrid-htable tr').css('height','48px');
});

	
initPager();
	
function initPager(){
    var p = $('#data').datagrid('getPager');
	$(p).pagination({
				onSelectPage:function(pageNumber, pageSize){
				    $('#page').val(pageNumber);
				    $('#pageSize').val(pageSize);
					$(this).pagination('loading');
					$('#queryForm').submit();
					$(this).pagination('loaded');
				}



	});
	
}

	
</script>
</body>
</html>
