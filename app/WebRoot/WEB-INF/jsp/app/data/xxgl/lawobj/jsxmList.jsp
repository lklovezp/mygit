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
			<form id="queryForm" action="queryJsxmList.json" method="post">
				<input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" /> 
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
						<td width="200" align="left">
							<input class="y-text" type="text" id="hylx" name="industryId"/>
						</td>
						<th width="100" align="right">
							<a href="#" style="color:#3399CC;" id="selectDwei">单位名称：</a>
						</th>
						<td>
						    <input class="y-text" type="text" id="lawobjname" name="lawobjname" value="${lawobjname }"/>
							<input class="y-text" type="hidden" id="lawobjId" name="lawobjId"/>
						</td>
						<th align="right">
							状态：
						</th>
						<td align="left" >
							<input class="y-text" type="text" id="zt" value="Y" name="isActive"/>
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
		   $("#zt").combobox('setValue','Y');
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
		nowrap:false,
		url:'queryJsxmList.json?isActive=Y&orgId='+$('#ssjgbm').val(),
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'name',title:'建设项目名称', align:"left", halign:'center',width:100},
			{field:'regionName',title:'所属行政区', align:"center", halign:'center',width:40},
			{field:'orgName',title:'所属监管部门', align:"center", halign:'center',width:40},
			{field:'industryName',title:'行业类型', align:"center", halign:'center',width:30},
			{field:'dwmc',title:'单位名称', align:"left", halign:'center',width:100},
			{field:'jsjdjsczt',title:'建设进度及生产状态', align:"center", halign:'center',width:48},
			{field:'isActive',title:'状态', align:"center", halign:'center',width:20},
			{field:'operate',title:'操作', align:"center", halign:'center',width:50}
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

	 $('#queryForm').submit(function(){  
		    $("#queryForm").ajaxSubmit({
		   	  success: function(data){
		   	      $('#data').datagrid('loadData',data)
			      }
			 });
		   return false;  
		});

		$("#xinjian").click(function(){
			//All.ShowModalWin('jsxmEdit.htm', '新建工业污染源',1000,900);
			//refresh();
			parent.layer.open({
	            type: 2,
	            title: '新建建设项目',
	            maxmin:true,
	            area: ['1100px', ($(window).height()+120)+'px'],
	            content: 'jsxmEdit.htm', 
	            end: function () {
	                //location.reload();
	                refresh();
	            }

	        });
		});
});


function edit(obj){
	//All.ShowModalWin('jsxmEdit.htm?id='+$(obj).attr("id"), '编辑建设项目', 1000, 800);
	//refresh();
	//var width=screen.width * 0.8;
  	//var height=screen.height * 0.8-50;
  	//var title='查看建设项目信息';
  	//parent.layerIframe(2,'jsxmEdit.htm?id='+$(obj).attr("id"),title,width,height);
	parent.layer.open({
        type: 2,
        title: '编辑建设项目',
        maxmin:true,
        area: ['1100px', ($(window).height()+120)+'px'],
        content: 'jsxmEdit.htm?id='+$(obj).attr("id"), 
        end: function () {
            //location.reload();
            refresh();
        }

    });
}
/*
function del(obj){
	$.messager.confirm('建设项目管理', '确定要删除吗？', function(r){
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
	//All.ShowModalWin('importPage.htm?lawObjType=02', '工业污染源导入', 600, 350);
	//refresh();
	layer.open({
        type: 2,
        title: '建设项目导入',
        maxmin:true,
        area: ['600px', ($(window).height()-50)+'px'],
        content: 'importPage.htm?lawObjType=02', 
        end: function () {
            //location.reload();
           refresh();
        }

    });
});

function info(obj){
	//All.ShowModalWin('jsxmInfo.htm?id='+$(obj).attr("id"), '查看建设项目信息');
	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	var title='查看建设项目信息';
  	parent.layerIframe(2,'jsxmInfo.htm?id='+$(obj).attr("id"),title,width,height);
}

/**
 * 选择后回填数据
 */
function setValues(id,name){
	$("#lawobjname").val(name);
	$("#lawobjId").val(id);
	jQuery().colorbox.close();
	
	
}

//转污染源
function transGywry(obj){
	/*($.messager.confirm('操作确认', '确定要转污染源吗？', function(r){
		if (r){
			//All.ShowModalWin('gywryEdit.htm?jsxmid='+obj.id, '新建工业污染源');
			//refresh();
			parent.layer.open({
		        type: 2,
		        title: '新建工业污染源',
		        area: ['1100px', ($(window).height()+120)+'px'],
		        content: 'gywryEdit.htm?jsxmid='+obj.id, 
		        end: function () {
		            location.reload();
		        }

		    });
			
		}
	});*/
	var index=layer.confirm('确定要转污染源吗？', {
     	icon: 3, 
        title:'操作确认'
     }, function(index){
    	 parent.layer.open({
		        type: 2,
		        title: '新建工业污染源',
		        maxmin:true,
		        area: ['1100px', ($(window).height()+120)+'px'],
		        content: 'gywryEdit.htm?jsxmid='+obj.id, 
		        end: function () {
		            //location.reload();
		            refresh();
		        }

		    });
        layer.close(index);
     },function(index){
        layer.close(index);
     });
	
}
//转医院
function transYy(obj){
	/*$.messager.confirm('操作确认', '确定要转医院吗？', function(r){
		if (r){
			All.ShowModalWin('yyxxEdit.htm?jsxmid='+obj.id, '新建医院');
			refresh();
		}
	});*/
	var index=layer.confirm('确定要转医院吗？', {
     	icon: 3, 
        title:'操作确认'
     }, function(index){
    	 parent.layer.open({
		        type: 2,
		        title: '新建医院',
		        maxmin:true,
		        area: ['1100px', ($(window).height()+120)+'px'],
		        content: 'yyxxEdit.htm?jsxmid='+obj.id, 
		        end: function () {
		            //location.reload()
		        	refresh();
		        }

		    });
        layer.close(index);
     },function(index){
        layer.close(index);
     });
}
//转三产
function transSc(obj){
	/*$.messager.confirm('操作确认', '确定要转三产吗？', function(r){
		if (r){
			All.ShowModalWin('scxxEdit.htm?jsxmid='+obj.id, '新建三产');
			refresh();
		}
	});*/
	var index=layer.confirm('确定要转三产吗？', {
     	icon: 3, 
        title:'操作确认'
     }, function(index){
    	 parent.layer.open({
		        type: 2,
		        title: '新建三产',
		        maxmin:true,
		        area: ['1100px', ($(window).height()+120)+'px'],
		        content: 'scxxEdit.htm?jsxmid='+obj.id, 
		        end: function () {
		        	refresh();
		        }

		    });
        layer.close(index);
     },function(index){
        layer.close(index);
     });
}
//转畜禽养殖
function transXqyz(obj){
	/*$.messager.confirm('操作确认', '确定要转畜禽养殖吗？', function(r){
		if (r){
			All.ShowModalWin('xqyzEdit.htm?jsxmid='+obj.id, '新建畜禽养殖');
			refresh();
		}
	});*/
	var index=layer.confirm('确定要转畜禽养殖吗？', {
     	icon: 3, 
        title:'操作确认'
     }, function(index){
    	 parent.layer.open({
		        type: 2,
		        title: '新建畜禽养殖',
		        maxmin:true,
		        area: ['1100px', ($(window).height()+120)+'px'],
		        content: 'xqyzEdit.htm?jsxmid='+obj.id, 
		        end: function () {
		        	refresh();
		        }

		    });
        layer.close(index);
     },function(index){
        layer.close(index);
     });
}
//转服务业
function transFwy(obj){
	/*$.messager.confirm('操作确认', '确定要转服务业吗？', function(r){
		if (r){
			All.ShowModalWin('fwyEdit.htm?jsxmid='+obj.id, '新建服务业');
			$('#queryForm').submit();
		}
	});*/
	var index=layer.confirm('确定要转服务业吗？', {
     	icon: 3, 
        title:'操作确认'
     }, function(index){
    	 parent.layer.open({
		        type: 2,
		        title: '新建服务业',
		        maxmin:true,
		        area: ['1100px', ($(window).height()+120)+'px'],
		        content: 'fwyEdit.htm?jsxmid='+obj.id, 
		        end: function () {
		        	refresh();
		        }

		    });
        layer.close(index);
     },function(index){
        layer.close(index);
     });
	
}
//转饮食业
function transYsy(obj){
	/*$.messager.confirm('操作确认', '确定要转饮食业吗？', function(r){
		if (r){
			All.ShowModalWin('ysyEdit.htm?jsxmid='+obj.id, '新建饮食业');
			$('#queryForm').submit();
		}
	});*/
	var index=layer.confirm('确定要转饮食业吗？', {
     	icon: 3, 
        title:'操作确认'
     }, function(index){
    	 parent.layer.open({
		        type: 2,
		        title: '新建饮食业',
		        maxmin:true,
		        area: ['1100px', ($(window).height()+120)+'px'],
		        content: 'ysyEdit.htm?jsxmid='+obj.id, 
		        end: function () {
		        	refresh();
		        }

		    });
        layer.close(index);
     },function(index){
        layer.close(index);
     });
}
//转制造业
function transZzy(obj){
	/*$.messager.confirm('操作确认', '确定要转制造业吗？', function(r){
		if (r){
			All.ShowModalWin('zzyEdit.htm?jsxmid='+obj.id, '新建制造业');
			$('#queryForm').submit();
		}
	});*/
	var index=layer.confirm('确定要转制造业吗？', {
     	icon: 3, 
        title:'操作确认'
     }, function(index){
    	 parent.layer.open({
		        type: 2,
		        title: '新建制造业',
		        maxmin:true,
		        area: ['1100px', ($(window).height()+120)+'px'],
		        content: 'zzyEdit.htm?jsxmid='+obj.id, 
		        end: function () {
		        	refresh();
		        }

		    });
        layer.close(index);
     },function(index){
        layer.close(index);
     });
}
//转娱乐业
function transYly(obj){
	/*$.messager.confirm('操作确认', '确定要转娱乐业吗？', function(r){
		if (r){
			All.ShowModalWin('ylyEdit.htm?jsxmid='+obj.id, '新建娱乐业');
			$('#queryForm').submit();
		}
	});*/
	var index=layer.confirm('确定要转娱乐业吗？', {
     	icon: 3, 
        title:'操作确认'
     }, function(index){
    	 parent.layer.open({
		        type: 2,
		        title: '新建娱乐业',
		        maxmin:true,
		        area: ['1100px', ($(window).height()+120)+'px'],
		        content: 'ylyEdit.htm?jsxmid='+obj.id, 
		        end: function () {
		        	refresh();
		        }

		    });
        layer.close(index);
     },function(index){
        layer.close(index);
     });
}

//创建人转移
function transfer(obj){
	//All.ShowModalWin('lawobjUserPubQuery.htm?all=true&notShowZj=false&lawobjtypeid=02&multi=false&lawobjId='+$(obj).attr("id"), '创建人转移',300, 380);
	parent.layer.open({
        type: 2,
        title: '创建人转移',
        maxmin:true,
        area: ['300px', '420px'],
        content: 'lawobjUserPubQuery.htm?all=true&notShowZj=false&lawobjtypeid=02&multi=false&lawobjId='+$(obj).attr("id"),
        end: function () {
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