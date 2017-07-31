<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
        <!--jQuery-->
        <script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${app}/js.js"></script>
		<script type="text/javascript" src="${app}/data.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<script type="text/javascript" src="${common}/All.js"></script>
		<!--时间插件 my97-->
        <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
         <link href="${app}/common.css" rel="stylesheet" type="text/css" />
         <link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div class="breadClumbs"> 任务&nbsp;&gt;&nbsp;${title}</div>
<div id="searchMask" class="searchMask" style="top:30px"><p>点击查看更多查询条件</p></div>
		
			<div id="searchForm" class="searchForm" style="padding-top:10px;padding-bottom:21px;top:30px;">
			<form id="queryForm" action="queryRecConsultList.json" method="post">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" /> 
				<table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style=" margin:0 auto">
					<tr>
						<th width="120" align="right">
							主题：
						</th>
						<td>
							<input class="y-text" type="text" name="topic" id="topic"/>
						</td>
						<th width="120" align="right">
							发件人：
						</th>
						<td >
							<input type="hidden" id="pfrId" name="pfrId" value=""/>
							<input class="y-text" type="text" id="selectSend" name="selectSend" value=""/>
						</td>
						
					</tr>
					<tr>
						<th width="120" align="right">
							时间：
						</th>
						<td colspan="2">
							 <input type="text" class="y-dateTime" id="startTime" name="startTime" value="${startTime }" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\',{d:-1});}'})"/> 
                                                                          至 <input type="text" class="y-dateTime"  id="endTime" name="endTime" value="${endTime }" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\',{d:1});}'})"/>

						</td>
					</tr>
					<tr>
						<td colspan="6" align="center">
							<input type="submit" class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
                           <input type="reset" class="queryOrange" value="重　置"/>
						</td>
					</tr>
					
				</table>
			</form>
			<div class="closeBtn">收起</div>
		</div>
     
		<div id="layer1" class="layer"></div>
        <div id="divTitle" class="h1_1 topMask" style="padding-top: 10px;">${title}</div>
        <div style="width:95%; margin:-7px  auto 0px;" class="t-r">
          <input type="button" class="bTn btnbgAdd directory_comwidth" id="functionAdd" value="新建" />
         </div>
         <div class="divContainer" id="rbblist" style="width: 100%;margin: 0 auto;margin-top: 10px;">
             <table id="data" fit="true" ></table>
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
////////////////////////////////////////////
function initH(){
	var hh=$(window).height() - $("#divTitle").outerHeight();
	$("#rbblist").height(hh);
}
initH();
$(document).ready(function(){
	
	
	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
		   $("#pfrId").val("");
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
		//pageSize : 10,//默认选择的分页是每页10行数据
		//pageList: [5,10,15,20],//可以设置每页记录条数的列表  
		url:'queryRecConsultList.json',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'danWei',title:'单位名称', align:"left", halign:'center',width:50},
			{field:'buMen',title:'部门', align:"center", halign:'center',width:30},
			{field:'userName',title:'发件人', align:"center", halign:'center',width:30},
			{field:'topic',title:'主题', align:"left", halign:'center',width:100},
			{field:'faSongTime',title:'时间', align:"center", halign:'center',width:30},
		    {field:'isRead',title:'状态', align:"center", halign:'center',width:20},
			{field:'operate',title:'操作', align:"center", halign:'center',width:55}
		]],
		onDblClickRow: function(rowIndex, rowData){
			$.ajax({
				  url: "findMailByRecId.json?id="+rowData.id,
				  success:function(data){
					  var href = "recMailInfo.htm?id="+rowData.id; 
					  parent.dkSelect(rowData.id,data.topic,href);
				  }
				});
		}
	});
	//initPager();
	initPager();
	$('#queryForm').submit(function(){  
	    $("#queryForm").ajaxSubmit({
			success: function(data){
	   	      $('#data').datagrid('loadData',data)
		      }
		   	});
	   return false;  
	});

	
});

//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});



/*
function del(obj){
	$.messager.confirm('已收会商管理', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
			  url: "delRecMail.json?id="+obj.id,
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
				url : "delRecMail.json?id="+obj.id,
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


function info(obj){
	//All.ShowModalWin('recMailInfo.htm?id='+$(obj).attr("id"), '查看邮件');
	window.location.href='recMailInfo.htm?id='+$(obj).attr("id");
	
}
function setUserPfr(id,name) {
	$("#pfrId").val(id);
	$("#selectSend").val(name);
	jQuery().colorbox.close();
}
//选择发件人
$("#selectSend").colorbox({
	iframe:true,
	width:"300", 
	height:"400",
	href:"queryUserTree.htm?methodname=setUserPfr&multi=false"
});

//发送会商
$("#functionAdd").click(function(){
		parent.layer.open({
		       type:2,
		       title:'发送会商',
		       area:['900px','650px'],
		       shadeClose:false,
		       maxmin:true,
		       content:'sendConsultation.htm',
		       end:function(){
		    	   location.reload();
		       }
		     });
	});

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