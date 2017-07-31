<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${title }</title>
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
			<form id="queryForm" action="queryGywryList.json" method="post">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" /> 
				<table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
						<th width="120" align="right">
							单位名称：
						</th>
						<td width="200" >
							<input class="y-text" type="text" name="name" id=""/>
						</td>
						<th width="120" align="right">
							所属行政区：
						</th>
						<td width="200" >
							<input class="y-text" type="text" name="regionId" id="ssxzq"/>
						</td>
						<th width="120" align="right">
							所属监管部门：
						</th>
						<td>
							<input class="y-text"  type="text" id="ssjgbm" name="orgId" value="${orgId}"/>
						</td>
					</tr>
					<tr>
						<th width="120" align="right">
							控制类型：
						</th>
						<td width="200" >
							<input class="y-text" type="text" id="kzlx" name="kzlx"/>
						</td>
						<th width="120" align="right">
							企业状态：
						</th>
						<td width="200" >
							<input class="y-text" type="text" id="qyzt" value="0" name="qyzt"/>
						</td>
						<th width="120" align="right">
							状态：
						</th>
						<td>
							<input class="y-text" type="text" id="zt" name="isActive" value="Y"/>
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
			<div style="width:95%; margin:-7px  auto 0px;" class="t-r">
            <input type="button" class="bTn btnbgAdd directory_comwidth" id="xinjian" value="新建" />
            <input type="button" class="bTn btnbgImport" id="XGFJfileupload" value="导入企业附件类型" />
            <input type="button" class="bTn btnbgImg directory_comwidth"  id="daoru" value="导入" />
           </div>
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
	var hh=$(window).height() - $("#divTitle").outerHeight()-40;
	$("#rbblist").height(hh);
}
initH();
//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});
//相关附件上传
$("#XGFJfileupload").click(function(){
	    //任务名称校验
	        
			var fileType = 'RWGLPFFJ';//派发附件
			   $.colorbox({	iframe:true, width:"610", height:"380",
					href:'jieXiUploadPage.htm?fileType='+fileType
					     })
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
	
	$('#qyzt').combobox({
		height:34,
		url:'qyztList.json',
		valueField:'id',
		textField:'name'
	}).combobox("initClear");
	
	$('#kzlx').combobox({
		height:34,
		url:'kzlxList.json',
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
		   $("#zt").combobox('setValue','Y');
		    var p = $('#data').datagrid('getPager');
		   $('#page').val($(p).pagination('options').pageNumber);
		   $('#pageSize').val($(p).pagination('options').pageSize);
	});
	
	$("#infectlist").height($(window).height() - $("#questionContainer").outerHeight() - $("#divTitle").outerHeight());
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		nowrap:false,
		fitColumns : true,
		pagination:true,
		url:'queryGywryList.json?isActive=Y&qyzt=0&orgId='+$('#ssjgbm').val(),
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'name',title:'单位名称', align:"left", halign:'center',width:100},
			{field:'regionName',title:'所属行政区', align:"center", halign:'center',width:40},
			{field:'orgName',title:'所属监管部门', align:"center", halign:'center',width:40},
			{field:'qyzt',title:'企业状态', align:"center", halign:'center',width:20},
			{field:'kzlx',title:'控制类型', align:"center", halign:'center',width:30},
		    {field:'isActive',title:'状态', align:"center", halign:'center',width:20},
			{field:'operate',title:'操作', align:"center", halign:'center',width:55}
		]],
		onDblClickRow: function(rowIndex, rowData){
			//All.ShowModalWin('gywryInfo.htm?id='+rowData.id, '查看工业污染源');
			var width=screen.width * 0.8;
		  	var height=screen.height * 0.8-50;
		  	var title='查看工业污染源';
		  	parent.layerIframe(2,'gywryInfo.htm?id='+rowData.id,title,width,height);
		}
	});
	initPager();
	
	$('#queryForm').submit(function(){  
	    $("#queryForm").ajaxSubmit({
			success: function(data){
	   	      $('#data').datagrid('loadData',data)
		      }
		   	});
	   return false;  
	});

	$("#xinjian").click(function(){
		//All.ShowModalWin('gywryEdit.htm', '新建工业污染源');
		//refresh();
		parent.layer.open({
            type: 2,
            title: '新建工业污染源',
            area: ['1100px', ($(window).height()+120)+'px'],
            content: 'gywryEdit.htm', 
            end: function () {
                //location.reload();
            	refresh();
            }

        });
	});
});



function edit(obj){
	//All.ShowModalWin('gywryEdit.htm?id='+$(obj).attr("id"), '查看工业污染源');
	//refresh();
	parent.layer.open({
            type: 2,
            title: '编辑工业污染源信息',
            area: ['1100px', ($(window).height()+120)+'px'],
            content: 'gywryEdit.htm?id='+$(obj).attr("id"), 
            end: function () {
            	refresh();
            }

        });
	}
//危化品
function danger(obj){
	//All.ShowModalWin('weiHuaPing.htm?id='+$(obj).attr("id"), '企业环境风险及化学品检查');
	//refresh();
	parent.layer.open({
        type: 2,
        title: '企业环境风险及化学品检查',
        area: ['1100px', ($(window).height()+120)+'px'],
        content: 'weiHuaPing.htm?id='+$(obj).attr("id"), 
        end: function () {
            //location.reload();
        	refresh();
        }

    });
}
/*
function del(obj){
	$.messager.confirm('工业污染源管理', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
			  url: "delLawobj.json?id="+obj.id,
			  success:function(data){
				 alert(data.msg);
				 refresh();
			  }
			});
		}
	});
}*/
//删除
function del(obj) {
	var index=layer.confirm('确定删除吗？', {
     	icon: 3, 
        title:'删除任务'
     }, function(index){
    	 $.ajax( {
				url : "delLawobj.json?id="+obj.id,
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


$("#daoru").click(function(){
	//All.ShowModalWin('importPage.htm?lawObjType=01', '工业污染源导入', 600, 350);
	layer.open({
            type: 2,
            title: '工业污染源导入',
            area: ['600px', ($(window).height()-50)+'px'],
            content: 'importPage.htm?lawObjType=01', 
            end: function () {
                //location.reload();
            	refresh();
            }

        });
	
});

function info(obj){
	//All.ShowModalWin('gywryInfo.htm?id='+$(obj).attr("id"), '查看工业污染源');
	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	var title='查看工业污染源';
  	parent.layerIframe(2,' gywryInfo.htm?id='+$(obj).attr("id"),title,width,height);
}

//创建人转移
function transfer(obj){
	//All.ShowModalWin('lawobjUserPubQuery.htm?all=true&notShowZj=false&lawobjtypeid=01&multi=false&lawobjId='+$(obj).attr("id"), '创建人转移',300, 380);
	//var width=screen.width * 0.8;
  	//var height=screen.height * 0.8;
  	//var title='查看工业污染源';
  	//parent.layerIframe(2,'lawobjUserPubQuery.htm?all=true&notShowZj=false&lawobjtypeid=01&multi=false&lawobjId='+$(obj).attr("id"),title,width,height);
	parent.layer.open({
        type: 2,
        title: '创建人转移',
        area: ['300px', '420px'],
        content: 'lawobjUserPubQuery.htm?all=true&notShowZj=false&lawobjtypeid=01&multi=false&lawobjId='+$(obj).attr("id"),
        end: function () {
            //location.reload();
        	refresh();
        }

    });
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