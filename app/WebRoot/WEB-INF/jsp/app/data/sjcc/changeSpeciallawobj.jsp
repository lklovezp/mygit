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
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/css/easyUIReset.css" rel="stylesheet" type="text/css"/>
</head>

<body>

<div class="dataDiv" style="width:95%;min-width: 1020px; margin:16px auto 25px;">
    <h1 id="checkup_header">执法对象选择</h1>
    <form id="queryForm" action="queryZfdxList.json" method="post">
    <input type="hidden" id="year" name="year" value="${year}" />
	<input type="hidden" id="quarter" name="quarter" value="${quarter}" /> 
     <input type="hidden" id="fid" name="fid" value="${fid}" />
	<input type="hidden" id="page" name="page" value="${page}" />
	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
	<input type="hidden"  id="lawobjtypeid" name="lawobjtypeid" value="1">
	<input type="hidden" id="ymbiaoshi" name="ymbiaoshi" value="${ymbiaoshi}" />
    <table class="noborder mt25" width="100%" border="0px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">      
        <tr height="48">
        	<th width="8%" >执法对象类型</th>
			<td width="15%"><input class="y-text" type="text" id="lawobjtype" name="lawobjtype"  style="width:127px;" value="1" /></td>
            <td width="8%" align="right">监管部门：</td>
            <td width="15%"><input class="y-text" id="ssjgbm" name="orgId" style="width:127px;" /></td>
            <td width="8%" align="right">企业名称：</td>
            <td width="35%"><input class="y-text" id="dwmc" name="dwmc" style="width:127px;" />
            <input type="submit"  id="query"  class="o_btn btn_blue" style="padding: 5px 20px; margin-left: 20px;" value="搜索"/>
            <input type="button" id="J-from-reset"  class="o_btn btn_blue"  style="padding: 5px 20px; margin-left: 20px;"value="重置"/>
           <!--   <a class='o_btn btn_blue' id="query" style="padding: 5px 20px; margin-left: 20px;">搜索</a>
            <a class='o_btn btn_grey' id="J-from-reset" style="padding: 5px 20px; margin-left: 20px;">重置</a></td>
        -->
        </tr>
    </table>
    </form>
    <!-- 待选执法对象 -->
    <div class="mt25" id="annex" style="width:55%; height:300px; float:left">
        <div class="annex_header" style="font-size: 16px;">
           <!--  <a class="o_btn btn_blue" style="float:right;line-height: 30px; color:#fff; margin-top:7px;font-size: 14px;"  >确认选择</a> -->
            待选执法对象
        </div>
        <div class="annex_con" style="width:100%;height:248px;">
            <table id="data" style="width:100%;" fit="true"></table>
        </div>
    </div>
    <!-- 已选择执法对象 -->
    <div class="mt25" style="width:12%; float:left; text-align:center; height:180px; padding-top:120px;">
    	<img src="${app}/images/l_arrow.png" />
    </div>
    <div class="mt25" id="annex" style="width:33%; height:300px; float:left">
        <div class="annex_header" style="font-size: 16px;">
        <input type="button"  onclick="delRightRow();" id="del"  class="o_btn btn_blue" style="float:right; line-height: 30px; color:#fff;margin-top:7px;font-size: 14px;" value="删除"/>
         <!--  <a class="o_btn btn_blue" style="float:right; line-height: 30px; color:#fff;margin-top:7px;font-size: 14px;" >删除</a> -->  
            已选择执法对象
        </div>
        <div class="annex_con" style="width:100%;height:248px;">
            <table id="choseedata" style="width:100%;" fit="true"></table>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="mt25" style="text-align: center">
    <input id="save" type="submit" class="input_addBtnImgOrange ml20 mr20" value="保存">
    <!--  <input type="button" class="input_addBtnImgOrange ml20 mr20" value="保 存"/> --></div>
</div>
<script>
$(document).ready(function(){
	//执法对象类型下拉框
	$('#lawobjtype').combotree({
		    multiple: false,
			url:'lawtypeTree.json',
			height:34,
		    onChange : function (newValue,oldValue){
		    	$("#lawobjtypeid").val(newValue);
			}
			//value:'${rwlxIds}'.split(',')
		});
	//$('#cc').combotree('setValue', 6); 
});
	
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
           onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
			var rows = $('#choseedata').datagrid('getRows');
			for(var i=0;i<rows.length;i++){
				var datarows = data.rows;
				for(var j=0;j<datarows.length;j++){
					if(datarows[j].id==rows[i].lawobjid){
						selectRow(datarows[j].id);
					}
				}
			}
		},
        columns:[[//设置表头
            {field:'ck',checkbox:true},
            {field:'id',hidden:true},
            {field:'hbfzr',hidden:true},
			{field:'hbfzrdh',hidden:true},
			{field:'regionid',hidden:true},
            {field:'dwmc',title:'企业名称',align:'center',halign:'center',width:4},
            {field:'dwdz',title:'地址', align:"left", halign:'center',width:5},
			{field:'orgmc',title:'所属监管部门', align:"center", halign:'center',width:3},
			{field:'fddbr',title:'法人', align:"center", halign:'center',width:3},
			{field:'fddbrdh',title:'法人电话', align:"center", halign:'center',width:3}
        ]],
       
       pagination:true,//是否添加底部的分页
       rownumbers:true,// 是否显示左侧的行序号
       onSelect:function(rowIndex, rowData){
			addrow(rowData);
		},
		onUnselect:function(rowIndex, rowData){
			if(canDel(rowData.id)){
				delRightRow(rowData.id);
			}else{
				selectRow(rowData.id);
			}
		},
		onCheckAll:function(rows){
			for(var i=0;i<rows.length;i++){
				addrow(rows[i]);
			}
		},
		onUncheckAll:function(rows){
			var isok = true;
			for(var i=0;i<rows.length;i++){
				if(!canDel(rows[i].id)){
					isok = false;
					break;
				}
			}
			if(isok){
				for(var i=0;i<rows.length;i++){
					delRightRow(rows[i].id);
				}
			}else{
				$('#data').datagrid("selectAll");
			}
		}
    });
//相关附件table
    $('#choseedata').datagrid({
        nowrap: false,//自动截取
        striped: true,//显示条纹
        collapsible:true,//是否添加折叠按钮
        singleSelect:true,//单选模式，只允许选取一行
        fitColumns:true,//自适应列宽
        remoteSort:false,//是否远程排序
        url:'querySelectLawobjList.json?rwid=${rwid}&lawobjtype=${lawobjtype}',//请求数据的超链接地址
       	//pageSize : 10,//默认选择的分页是每页10行数据
      	//pageList: [5,10,15,20],//可以设置每页记录条数的列表
        onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
			var rows = $('#data').datagrid('getRows');
			for(var i=0;i<rows.length;i++){
				var datarows = data.rows;
				for(var j=0;j<datarows.length;j++){
					if(datarows[j].lawobjid==rows[i].id){
						selectRow(datarows[j].id);
					}
				}
			}
		},
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
	//确认返回数据
	$("#save").click(function(){
		var bs = $('#ymbiaoshi').val();
		var rows = $('#choseedata').datagrid('getRows');
		var rwlxIds = $('#rwlxIds').val();
		if(rows.length==0){
			alert("请至少选择一个执法对象!");
			return;
		}
		var ids = "";
		var names = "";
		for(var i=0;i<rows.length;i++){
			ids += rows[i].lawobjfid;
			names += rows[i].name;
			if(i!=rows.length-1){
				ids += ",";
				names += ",";
			}
		}
		
		var year = $('#year').val();
		var quarter=$('#quarter').val();
		$.ajax({
			  type: "POST",
			  url: 'specialLawobjSave.json',
			  data: "ids="+ids+"&names="+names+"&year="+year+"&quarter="+quarter,
			  success:function(data){
				  if(data.state){
						alert(data.msg);
						parent.closeLayer();
					}else{
						$.messager.alert('保存特殊监管企业信息:',data.msg);
					}
			  }
			});
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



//添加行
function addrow(rowData){
	var rows = $('#choseedata').datagrid('getRows');
	var isCZ = false;//是否存在
	for(var i=0;i<rows.length;i++){
		if(rowData.id==rows[i].lawobjfid){
			isCZ = true;
		}
	}
	if(!isCZ){
	$('#choseedata').datagrid("appendRow",{lawobjfid:rowData.id,name:rowData.dwmc,fddbr:rowData.fddbr,fddbrlxdh:rowData.fddbrdh,hbfzr:rowData.hbfzr,hbfzrlxdh:rowData.hbfzrdh,regionid:rowData.regionid,address:rowData.dwdz});
	}
}

//从右边删除
function delrow(id){
	if(canDel(id)){
		delRightRow(id);
		unSelectRow(id);
	}
}


//删除右边行数据
function delRightRow(id){
	//删除行
	var rows = $('#choseedata').datagrid('getRows');
	for(var i=0;i<rows.length;i++){
		if(rows[i].lawobjid==id){
			$('#choseedata').datagrid("deleteRow",i);
			break;
		}
	}
}

//取消左边选中状态
function unSelectRow(id){
	var dataRows = $('#data').datagrid('getSelections');
	for(j=0;j<dataRows.length;j++){
		if(dataRows[j].id==id){
			var index = $('#data').datagrid('getRowIndex',dataRows[j]);
			if(index>=0){
				$('#data').datagrid('unselectRow',index);
			}
			return;
		}
	}
}

//左边选中状态
function selectRow(id){
	var dataRows = $('#data').datagrid('getRows');
	for(j=0;j<dataRows.length;j++){
		if(dataRows[j].id==id){
			var index = $('#data').datagrid('getRowIndex',dataRows[j]);
			if(index>=0){
				$('#data').datagrid('selectRow',index);
			}
			return;
		}
	}
}


//判断是否能删除
function canDel(id){
	var rows = $('#choseedata').datagrid('getRows');
	for(var i=0;i<rows.length;i++){
		if(rows[i].lawobjid==id){
			if(rows[i].newtaskid!='' && rows[i].newtaskid!=null && rows[i].newtaskid != 'null'){
				alert("专项子任务已分工，不能删除执法对象！");
				return false;
			}
		}
	}
	return true;
}
	
</script>
</body>
</html>
