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
	</head>

	<body>
		<div class="breadClumbs"> 系统管理&nbsp;&gt;&nbsp;${title}</div>
        <div id="searchForm">
			<form id="queryForm" action="querySjccLawobjList.json" method="post">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" /> 
				<table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
					<tr>
						<th width="90" align="right">
							单位名称
						</th>
						<td width="170" >
							<input class="y-text" type="text" name="name" id="name"/>
						</td>
						<th width="138" align="right">
							执法对象类型
						</th>
						<td width="170" >
							<input class="y-text" type="text" name="lawobjType" id="lawobjType"/>
						</td>
						<th width="120" align="right">
							所属行政区
						</th>
						<td width="170" >
							<input class="y-text" type="text" name="regionId" id="ssxzq"/>
						</td>
						<th width="120" align="right">
							监管部门
						</th>
						<td>
							<input class="y-text"  type="text" id="ssjgbm" name="orgId" value="${orgId}"/>
						</td>
					</tr>
					<tr>
						<td colspan="7" align="center">
							<input type="submit" id="query"  class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
                            <input type="button" id="J-form-reset" class="queryOrange" value="重　置"/>
						</td>
						<td>
							<input type="button" id="J-form-export" class="bTn directory_save directory_comwidth"  value="导出" />
						</td>
					</tr>
				</table>
			</form>
			</div>
			 <div class="divContainer" id="rbblist" style=" width:100%; margin:10px auto 0px;">
				<table id="data" fit="true"></table>
			</div>
			<iframe name="download" id="download" src="" style="display: none"></iframe>
	</body>
</html>

<script language="JavaScript">
////////////////////////////////////////////////////////////////////////////////////
function initH(){
	var hh=$(window).height() -154;
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
	
	//执法对象类型下拉框
	$('#lawobjType').combobox({
		height:34,
		url:'dicList.json?type=5',
		valueField:'id',
		textField:'name'
	});
	
	$("#J-form-reset").click(function(){
		   $("#queryForm").form("reset");
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
		pagination:true,
		fitColumns:true,
		nowrap: false,
		url:'querySjccLawobjList.json?orgId='+$('#ssjgbm').val(),
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'lawobjTypeid',hidden:true},
			{field:'lawobjType',title:'执法对象类型', align:"center", halign:'center',width:50},
			{field:'name',title:'单位名称', align:"left", halign:'center',width:60},
			{field:'address',title:'地址', align:"left", halign:'center',width:60},
			{field:'regionName',title:'所属行政区', align:"center", halign:'center',width:30},
			{field:'orgName',title:'所属监管部门', align:"center", halign:'center',width:30},
			{field:'fddbr',title:'法定代表人', align:"center", halign:'center',width:30},
			{field:'fddbrlxdh',title:'法定代表人联系电话', align:"center", halign:'center',width:40},
			{field:'qyzt',title:'企业生产状态', align:"center", halign:'center',width:40},
			{field:'operate',title:'操作', align:"center", halign:'center',width:30,
				formatter:function(value,rowData,rowIndex){  
		    		return '<a class="b-link" onclick="info(\''+ rowData.id +','+rowData.lawobjType+','+rowData.lawobjTypeid+'\')">查看</a>';  
		       }
				}
		]],
		onDblClickRow: function(rowIndex, rowData){
			var width=screen.width * 0.8;
		  	var height=screen.height * 0.8-50;
		  	var title='查看执法对象信息';
		  	
		  	parent.layer.open({
			       type:2,
			       title:title,
			       area:[width+"px",height+"px"],
			       shadeClose:false,
			       maxmin:true,
			       content:'lawobjfInfo.htm?id='+rowData.id+'&lawobjtypeid='+rowData.lawobjTypeid,
			       end:function(){
			       }
			     });
		  	
		}
	});
	initPager();
});

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


function info(obj){
	var arr = new Array();
	arr = obj.split(",");
	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	var title='查看'+arr[1]+'信息';
  	parent.layer.open({
	       type:2,
	       title:title,
	       area:[width+"px",height+"px"],
	       shadeClose:false,
	       maxmin:true,
	       content:'lawobjfInfo.htm?id='+arr[0]+'&lawobjtypeid='+arr[2],
	       end:function(){
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

//导出
$('#J-form-export').click(function(){
	var name = $("#name").val();
	var lawobjType = $("#lawobjType").combobox('getValue');
	var regionId = $("#ssxzq").combotree('getValue');
	var regionName = $("#ssxzq").combotree('getText');
	var orgId = $("#ssjgbm").combotree('getValue');
	var orgName = $("#ssjgbm").combotree('getText');
	$('#download').attr('src','exportYbLawobjList.json?name='+encodeURIComponent(name)+'&lawobjType='+lawobjType+'&regionId='+regionId+'&regionName='+encodeURIComponent(regionName)+'&orgId='+orgId+'&orgName='+encodeURIComponent(orgName));
});
</script>