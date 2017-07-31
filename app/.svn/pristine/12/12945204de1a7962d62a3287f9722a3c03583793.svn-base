<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="${app}/js.js"></script>
	<script type="text/javascript" src="${app}/data.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${layer}/layer.js"></script>
	<!-- colorbox -->
	<script type="text/javascript"	src="${colorbox}/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css"/>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
    <!--poshytip-->
    <link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
    <!--派发列表-->
    <link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${poshytip}/jquery.poshytip.min.js"></script>
    <script type="text/javascript" src="${common}/All.js"></script>
    <!--时间插件 my97-->
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
    <link href="${app}/common.css" rel="stylesheet" type="text/css" />
	</head>
<body>
		<div id="searchMask" class="searchMask"><p>点击查看更多查询条件</p></div>
        <div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;">
			<form id="queryForm" action="queryJsxmList.json?isActive=Y" method="post">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" /> 
				<table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
						<th width="120" align="right">
							建设项目名称：
						</th>
						<td width="200" align="left">
							<input class="y-text" type="text" name="name" id=""/>
						</td>
						<th width="100" align="right">
							所属行政区：
						</th>
						<td  width="200">
							<input class="y-text" type="text" id="ssxzq" name="regionId"/>
						</td>
						<th width="120"  align="right">
							所属监管部门：
						</th>
						<td>
							<input class="y-text"  type="text" id="ssjgbm" name="orgId" value="${orgId}"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							行业类型：
						</th>
						<td  align="left">
							<input class="y-text" type="text" id="hylx" name="industryId"/>
						</td>
						<th  align="right">
							<a href="#" style="color:#3399CC;" id="selectDwei">单位名称：</a>
						</th>
						<td colspan="3">
						    <input class="y-text" type="text" id="lawobjname" name="lawobjname" value="${lawobjname }"/>
							<input class="y-text" type="hidden" id="lawobjId" name="lawobjId"/>
							
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center">
							<input type="submit" id="query"  class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
                             <input type="button" id="J-from-reset" class="queryOrange" value="重　置"/>
						</td>
					</tr>
				</table>
			</form>
		<div class="closeBtn">收起</div>
			</div>
		    <div id="layer1" class="layer"></div>
		    <h1 id="divTitle" class="h1_1 topMask">${title}</h1>
		 <div class="divContainer" id="rbblist" style=" width:100%; margin:10px auto 0px;">
			<table id="data" fit="true"></table>
		</div>
	</body>
</html>

<script language="JavaScript">
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
////////////////////////////////////////////////////////////////////////////////////
function initH(){
	var hh=$(window).height() - $("#divTitle").outerHeight()-10;
	$("#rbblist").height(hh);
}
initH();
//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});


$(document).ready(function(){

	$('#ssxzq').combotree({
		height:34,
		url:'regionTree.json',
		onBeforeSelect:function(node){
			if(node.state){        
				$('#ssxzq').tree('unselect');    
			} 
		}
	});
	
	$('#ssjgbm').combotree({
		height:34,
		url:'orgTree.json',
		valueField:'id',
		textField:'name'
	}).combobox("initClear");
	
	$('#hylx').combobox({
		height:34,
		url:'industryList.json?lawobjType=02',
		valueField:'id',
		textField:'name'
	});
	
	$('#zt').combobox({
		height:34,
		url:'ztList.json',
		valueField:'id',
		textField:'name'
	});
	
	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
		   var p = $('#data').datagrid('getPager');
		   $('#page').val($(p).pagination('options').pageNumber);
		   $('#pageSize').val($(p).pagination('options').pageSize);
		   $("#lawobjId").linkbox("setValue", {id:'',name:''});
	});
	$("#infectlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		fitColumns:true,
		nowrap: false,
		url:'queryJsxmList.json?isActive=Y&orgId='+$('#ssjgbm').val(),
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'name',title:'建设项目名称', align:"left", halign:'center',width:100},
			{field:'regionName',title:'所属行政区', align:"center", halign:'center',width:40},
			{field:'orgName',title:'所属监管部门', align:"center", halign:'center',width:40},
			{field:'industryName',title:'行业类型', align:"center", halign:'center',width:35},
			{field:'dwmc',title:'单位名称', align:"left", halign:'center',width:100},
			{field:'jsjdjsczt',title:'建设进度及生产状态', align:"center", halign:'center',width:50},
			{field:'operate',title:'操作', align:"center", halign:'center',width:70}
		]],
		onDblClickRow: function(rowIndex, rowData){
			//All.ShowModalWin('jsxmInfo.htm?id='+rowData.id, '查看建设项目信息');
			var width=screen.width * 0.8;
		  	var height=screen.height * 0.8-50;
		  	var title='查看建设项目信息';
		  	parent.layerIframe(2,'jsxmInfo.htm?id='+rowData.id,title,width,height);
		}
	});
	initPager();
	
	/**
	 * 选择执法对象
	 */
	 $("#selectDwei").click(function(){
		var hylx = $("#hylx").combobox('getValue');
		if(hylx == ''){
			alert("请先选择行业类型！");
		}else{
			$.colorbox({iframe:true,width:"600", height:"600",href:"choseeLawobj.htm?type=list&industryId="+hylx});
		}
	 });
});
$('#queryForm').submit(function(){  
    $("#queryForm").ajaxSubmit({
   	  success: function(data){
   	      $('#data').datagrid('loadData',data)
	      }
	 });
   return false;  
});

//创建人转移
function transfer(obj){
	//All.ShowModalWin('lawobjUserPubQuery.htm?all=true&notShowZj=false&lawobjtypeid=02&multi=false&lawobjId='+$(obj).attr("id"), '创建人转移',300, 380);
	parent.layer.open({
        type: 2,
        title: '创建人转移',
        area: ['300px', '420px'],
        content: 'lawobjUserPubQuery.htm?all=true&notShowZj=false&lawobjtypeid=02&multi=false&lawobjId='+$(obj).attr("id"),
        end: function () {
        	refresh();
        }

    });
}
//编辑
function edit(obj){
	//All.ShowModalWin('jsxmEdit.htm?id='+$(obj).attr("id"), '编辑建设项目', 1000, 800);
	parent.layer.open({
            type: 2,
            title: '编辑建设项目',
            area: ['1100px', ($(window).height()+120)+'px'],
            content: 'jsxmEdit.htm?id='+$(obj).attr("id"), 
            end: function () {
            	refresh();
            }

        });
	$('#queryForm').submit();
}

function del(obj){
	$.messager.confirm('建设项目管理', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
			  url: "delLawobj.json?id="+obj.id,
			  success:function(data){
				 alert(data.msg);
				 $('#queryForm').submit();
			  }
			});
		}
	});
}



function info(obj){
	//All.ShowModalWin('jsxmInfo.htm?id='+$(obj).attr("id"), '查看建设项目信息');
	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	var title='查看建设项目信息';
  	parent.layerIframe(2,'jsxmInfo.htm?id='+$(obj).attr("id"),title,width,height);
}

	/**
	 * 选择执法对象
	 */
	 $("#lawobjId-link").click(function(){
		var hylx = $("#hylx").combobox('getValue');
		if(hylx == ''){
			alert("请先选择行业类型！");
		}else{
			$.colorbox({iframe:true,width:"600", height:"600",href:"choseeLawobj.htm?type=list&industryId="+hylx});
		}
	 });

	 /**
	  * 选择后回填数据
	  */
	 function setValues(id,name){
	 	//$("#lawobjId").linkbox("setValue", {id:id,name:name})
	 	$("#lawobjname").val(name);
	    $("#lawobjId").val(id);
	 	jQuery().colorbox.close();
	 }
	 
//添加清除combobox值的功能
(function($){  
      
    //初始化清除按钮  
    function initClear(target){  
        var jq = $(target);  
        var opts = jq.data('combo').options;  
        var combo = jq.data('combo').combo;  
        var arrow = combo.find('span.combo-arrow');  
          
        var clear = arrow.siblings("span.combo-clear");  
        if(clear.size()==0){  
            //创建清除按钮。  
            clear = $('<span class="combo-clear" style="height: 20px;"></span>');  
              
            //清除按钮添加悬停效果。  
            clear.unbind("mouseenter.combo mouseleave.combo").bind("mouseenter.combo mouseleave.combo",  
                function(event){  
                    var isEnter = event.type=="mouseenter";  
                    clear[isEnter ? 'addClass' : 'removeClass']("combo-clear-hover");  
                }  
            );  
            //清除按钮添加点击事件，清除当前选中值及隐藏选择面板。  
            clear.unbind("click.combo").bind("click.combo",function(){  
                jq.combo("setValue","").combo("setText","");  
                jq.combo('hidePanel');  
            });  
            arrow.before(clear);  
        };  
        var input = combo.find("input.combo-text");  
        input.outerWidth(input.outerWidth()-clear.outerWidth());  
          
        opts.initClear = true;//已进行清除按钮初始化。  
    }  
      
    //扩展easyui combo添加清除当前值。  
    var oldResize = $.fn.combo.methods.resize;  
    $.extend($.fn.combo.methods,{  
        initClear:function(jq){  
            return jq.each(function(){  
                 initClear(this);  
            });  
        },  
        resize:function(jq){  
            //调用默认combo resize方法。  
            var returnValue = oldResize.apply(this,arguments);  
            var opts = jq.data("combo").options;  
            if(opts.initClear){  
                jq.combo("initClear",jq);  
            }  
            return returnValue;  
        }  
    });  
}(jQuery));
</script>