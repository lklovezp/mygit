<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网格管理信息</title>
<!--jQuery-->
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
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<link href="${app}/resetCSS.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />
<!--zTree-->
<link rel="stylesheet" href="${ztree}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<link rel="stylesheet" href="${app}/css/zTreeReset.css" type="text/css" />
<script type="text/javascript" src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/css/easyUIReset.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.input_btnColorlBluex{ width:auto; height:32px; padding:0px 10px; color:white; font-size:14px; border:none; outline:none; border-radius:3px;  background:#1895fb;}

.breadClumbs {
    background: #ffffff none repeat scroll 0 0;
    color: #666666;
    font-size: 12px;
    line-height: 34px;
    overflow: hidden;
    padding-left: 10px;
}
</style>
</head>

<body>
	<div class="breadClumbs"> 基础数据&nbsp;&gt;&nbsp;${title}&nbsp;&gt;&nbsp;<span id="name"></span></div>
<h1 class="h1" style="height:10px !important">${title}</h1>
<div class="lrDiv clearfix" style="margin: 0 5px 5px !important">
	<div class="leftTree">
	<div style="padding:25px;">
    	<input class="input_btnColorlBluex " id="functionAdd" type="button" value="新建"/>
        <input class="input_btnColorlBluex " id="functionEdit"   type="button" value="修改"/>
        <input class="input_btnColorlBluex" id="functionDel" type="button" value="删除"/>
    </div>
	<ul id="treeDemo" class="ztree" ></ul>
	
	</div>
    <div class="rightCon">
    	<div class="searchWrap" style=" padding:1px 25px 0px;">
            <div class="searchDiv" id="searchDiv" style="height: 65px;">
             <!--   <h4>${title}&nbsp;&gt;&nbsp;<span id="name"></span></h4>-->
            <form id="queryForm" action="queryZfdxList.json" method="post">
               <input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />  
				<input type="hidden"  id="lawobjtypeid" name="lawobjtypeid" value="${lawobjtypeid}">
              <table width="100%" class="noBorder" border="0" cellpadding="0" cellspacing="0">
                 <tr height="72">
                      <td colspan="3" align="center">
                      	<input type="text" class="i-text" id="name" name="name"style="width:95%;"/>
                      </td>
                      <td  align="left">
                      	<input type="submit" class="input_btnImgBlue" id="query" value="查　询"/>
                      	<a class="moreSearch" id="moreSearch">更多查询条件</a>
                      </td>
                  </tr>
                  <tr height="72">
                      <th align="right">单位名称：</th>
                      <td><input type="text" class="i-text" id="dwmc" name="dwmc" style="width:142px;height:25px;"/></td>
                      <th align="right">所属行政区：</th>
                      <td><input type="text" class="i-text" id="ssxzq" name="regionId" style="width:154px;"/></td>
                      
                  </tr>
                  <tr height="72">
                    <th align="right">所属监管部门：</th>
                    <td><input type="text" class="i-text" id="ssjgbm" name="orgId" style="width:154px;"/></td>
                    <th align="right">所属网格：</th>
                    <td><input type="text" class="i-text" id="sswg"   name="wgid"style="width:154px;"/></td>
                  </tr>
              </table>
              
            </div>
        </div>
        <div style="padding:30px 25px;" id="czdiv">
        	<input class="input_btnColorlBlue mr15" id="xinjian" type="button" value="新建"/>
            <input class="input_btnColorlBlue mr15" id="edit"  onclick="update()" type="button" value="修改"/>
            <input class="input_btnColorlBlue mr15" id="del" type="button" value="删除"/>
            <span id="weidiv"><input class="input_btnColorlBlue mr15" id="whp" type="button" value="危化品"/></span>
            <!--<input class="input_btnColorlBlue mr15" id="choseezfdx" type="button" value="选择执法对象"/>
              <input class="input_btnColorlBlue mr15" id="daoru" type="button" value="导入"/>-->
        </div>
        <div class="dataDiv" id="dataDiv" style="padding:0px 25px 30px ; height:216px;">
           <table id="data" fit="true"></table>
        </div>
      </form>
    </div>
</div>
<script type="text/javascript">

/*==========查询条件的展开与折叠============*/
$("#searchDiv").height("65px");
$("#moreSearch").click(function(){
	if($(this).hasClass("show")){//展开变折叠
		$(this).removeClass("show").text("展开查询条件");
		$("#searchDiv").height("65px");
	}else{//折叠变展开
		$(this).addClass("show").text("折叠查询条件");
		$("#searchDiv").height("auto");
	}
	
});

//左侧树
var setting = {
	view: {
		showLine: false
	},
	data: {
		simpleData: {
			idKey: "id", 
			pIdKey: "pid",  
			enable: true
		}
	},
	callback: {
		onClick : onClick
	}
};

function onClick(event, treeId, treeNode, clickFlag) {
		var level = treeNode.level;
		var href = treeNode.href;
		var id = treeNode.id;
		var name = treeNode.name;
		$("#lawobjtypeid").val(id);
		$("#name").html(name);
		if(id=='1'||id=='3'||id=='8'){
			$("#weidiv").show();
		}else{
			$("#weidiv").hide();
		}
		$.ajax({
			 type: "GET",
	         url: "queryZfdxList.json",
	         data: {lawobjtypeid:id},
	         dataType: "json",
	         success: function(data){
	        	 $('#data').datagrid('loadData',data)
			 }
		});
}

var menu_ =${re};
var treeObj = $.fn.zTree.init($("#treeDemo"), setting, menu_);

function initH(){
	var hh=$(window).height()-98-50;
	$(".leftTree, .rightCon").height(hh+95+"px");
	$("#dataDiv").height((hh-92-10)+"px");
}
initH();
//数据表格自使用宽度
$(window).resize(function(){
    $('#data').datagrid("resize");
	initH();
});

$(document).ready(function(){
	$('#sswg').combobox({
		height:35,
		url:'wgTree.json',
		valueField:'id',
		textField:'name'
	});
	
	$('#ssxzq').combotree({
		height:35,
		url:'regionTree.json',
		onBeforeSelect:function(node){
			if(node.state){        
				$('#ssxzq').tree('unselect');    
			} 
		}
	});

	var nodes = treeObj.getNodes();
	treeObj.expandNode(nodes[0], true, true, true);
	$('#queryForm').submit(function(){  
	    $("#queryForm").ajaxSubmit({
	   	  success: function(data){
	   	      $('#data').datagrid('loadData',data)
		      }
		 });
	   return false;  
	});

	
	

//easyui数据表格
$('#data').datagrid({
	rownumbers: true,
	singleSelect: false,
	nowrap:false,
	fitColumns : true,
	pagination:true,
	url:'queryZfdxList.json',//请求数据的超链接地址
	onLoadSuccess:function(data){
		    //datagrid鼠标经过提示单元格内容(需要在easyui.js中写扩展的doCellTip方法)
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
	
	columns:[[//设置表头
	    {field:'ck',checkbox:true},
	    {field:'id',hidden:true},
	    {field:'jsxmid',hidden:true},
	    {field:'dwmc',title:'单位名称',align:'center',width:4,formatter : function(value, row,index) {
								return "<span style='font-size: 16px;font-weight:200;'><a onclick='info(this)' class='b-link'  id='"
										+ row.id
										+ "'>"
										+ value
										+ "</a></span>";
							}
	    },
	    {field:'dwdz',title:'地址', align:"left", halign:'center',width:5},
		{field:'regionmc',title:'所属行政区', align:"center", halign:'center',width:3},
		{field:'orgmc',title:'所属监管部门', align:"center", halign:'center',width:3},
		{field:'wgmc',title:'所属网格', align:"center", halign:'center',width:3},
		{field:'fddbr',title:'法人', align:"center", halign:'center',width:3},
		{field:'fddbrdh',title:'法人电话', align:"center", halign:'center',width:3}

	]]
	
});
initPager();



$('#ssjgbm').combotree({
	height:35,
	url:'orgTree.json',
	valueField:'id',
	textField:'name'
}).combobox("initClear");


});


//判断是否是叶子节点
function isParent(id){
	var treenodes = treeObj.getNodesByParam("id",id);
	if(treenodes.length>0){
		return treenodes[0].isParent;
	}
	return false;
}
//删除执法对象类型
$("#functionDel").click(function(){
	var lawobjtypeid=$("#lawobjtypeid").val();
	if(!isParent(lawobjtypeid)){
	var index=layer.confirm('确定要删除当前执法对象类型吗？', {
     	icon: 3, 
        title:'部门管理'
     }, function(index){
    	 $.ajax( {
				url : "delzfdx.json?id="+lawobjtypeid,
				success : function(data) {
					location.reload();
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
	}else{
		alert("该节点下有子节点，不能删除！");
	}
});


//添加执法对象类型
$("#functionAdd").click(function(){
	var lawobjtypeid=$("#lawobjtypeid").val();
		parent.layer.open({
		       type:2,
		       title:'新建执法对象类型',
		       area:['538px','440px'],
		       shadeClose:false,
		       maxmin:true,
		       content:'editZfdx.htm?lawobjtypeid='+lawobjtypeid,
		       end:function(){
		    	   location.reload();
		       }
		     });
	});
	

//修改执法对象类型
$("#functionEdit").click(function(){
	var lawobjtypeid=$("#lawobjtypeid").val();
	if(null !=lawobjtypeid && ""!=lawobjtypeid){
	parent.layer.open({
		       type:2,
		       title:'修改执法对象类型',
		       area:['538px','440px'],
		       shadeClose:false,
		       maxmin:true,
		       content:'editZfdx.htm?id='+lawobjtypeid,
		       end:function(){
		    	   location.reload();
		       }
		     });
	}else{
		alert("请选择要修改的执法对象类型！！！");
	}
	});
	
	
////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////
//信息查看
function info(obj){
	var lawobjtypeid=$("#lawobjtypeid").val();
	parent.layer.open({
        type: 2,
        title:' 查看执法对象',
        area: ['1100px','600px'],
        content:'lawobjfInfo.htm?lawobjtypeid='+lawobjtypeid+'&id='+$(obj).attr("id"), 
        end: function () {
            //location.reload();
            refresh();
        }
      });
}

//信息修改
function update(){
	var rows = $('#data').datagrid('getSelections');
	var lawobjtypeid=$("#lawobjtypeid").val();
	if(null !=lawobjtypeid && ""!= lawobjtypeid){
    if (rows.length > 1) {
    	alert('一次只能修改一条信息！');
    	return ;
    }else if (rows.length<1){
    	alert('请选择您要修改的信息！');
    	return ;
    }else {
    	var id = rows[0].id;
    	 parent.layer.open({
    	        type: 2,
    	        title:'修改执法对象',
    	        area: ['1100px','600px'],
    	        content:'editlawobjf.htm?lawobjtypeid='+lawobjtypeid+'&id='+id, 
    	        end: function () {
    	            //location.reload();
    	            refresh();
    	        }
    	 });   				
    }
      }else{
    	  alert("请选择执法对象类型！！！");
      }
}

//批量删除
$("#del").click(function(){
	var rows = $('#data').datagrid('getSelections');
	var lawobjtypeid=$("#lawobjtypeid").val();
	 var  ids = [];
	  $.each(rows, function (index, item){
	      ids.push(item.id); 
	  });
	  if(null !=ids && ""!=ids){
		var index=layer.confirm('确定要删除执法对象吗？', {
	     	icon: 3, 
	        title:'删除执法对象'
	     }, function(index){
	    	 $.ajax( {
					url : "dellawobjf.json?ids="+ids+"&lawobjtype="+lawobjtypeid,
					success : function(data) {
						  var tishi=layer.alert(data.msg,{
						     	title:'信息提示',
						        icon:1,
						        shadeClose:true
						     },
						     function(){
						    	 $('#data').datagrid("reload");
						        layer.close(tishi);
						     }
						     );

						
					}
				});
	        layer.close(index);
	     },function(index){
	        layer.close(index);
	     }
	     );
	  }else{
		  alert("请选择要删除的执法对象！！！");
	  }
});


$("#xinjian").click(function(){
	var lawobjtypeid=$("#lawobjtypeid").val();
	
	if(null != lawobjtypeid && "" != lawobjtypeid){
	 parent.layer.open({
        type: 2,
        title:'新建执法对象',
        area: ['1100px','600px'],
        content:'editlawobjf.htm?lawobjtypeid='+lawobjtypeid, 
        end: function () {
            //location.reload();
            refresh();
        }
      });
	}else{
		alert("请选择执法对象类型");
	}
 
	
});

//危化品
$("#whp").click(function (){
	var rows = $('#data').datagrid('getSelections');
    if (rows.length > 1) {
    	alert('一次只选择一个执法对象！');
    	return ;
    }else if (rows.length<1){
    	alert('请选择您要添加危化品信息的执法对象！');
    	return ;
    }else {
     var id = rows[0].id;
	 parent.layer.open({
        type: 2,
        title: '企业环境风险及化学品检查',
        area: ['1100px', '600px'],
        content: 'weiHuaPing.htm?id='+id, 
        end: function () {
            //location.reload();
        	refresh();
        }

    });
    }

});


//危化品
$("#choseezfdx").click(function (){
	var lawobjtypeid=$("#lawobjtypeid").val();
	var json=[{"1":"4"},{"3":"4"},{"14":"4"},{"14":"14"}];
	 parent.layer.open({
        type: 2,
        title: '选择执法对象',
        area: ['1100px', '600px'],
        content: 'choseezfdx.htm?lawobjtypeid='+lawobjtypeid, 
        //content:'gettbData.json?table=work_&datas='+json,
        end: function () {
            //location.reload();
        	refresh();
        }

    });
   

});


</script>

</body>
</html>
