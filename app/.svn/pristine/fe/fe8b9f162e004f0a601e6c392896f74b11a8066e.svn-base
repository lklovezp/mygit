<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网格管理信息</title>
<link href="${app}/resetCSS.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${app}/data.js"></script>
<script type="text/javascript" src="${app}/js.js"></script>
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
<script type="text/javascript" src="${common}/All.js"></script>
<!--zTree-->
<link rel="stylesheet" href="${ztree}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<link rel="stylesheet" href="${app}/css/zTreeReset.css" type="text/css" />
<script type="text/javascript" src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
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
    	<input class="input_btnColorlBluex" id="functionAdd" type="button" value="新建"/>
        <input class="input_btnColorlBluex" id="functionEdit"   type="button" value="修改"/>
        <input class="input_btnColorlBluex" id="functionDel" type="button" value="删除"/>
    </div>
	<ul id="treeDemo" class="ztree" ></ul>
	
	</div>
    <div class="rightCon">
    	<div class="searchWrap" style=" padding:1px 25px 0px;">
            <div class="searchDiv" id="searchDiv">
             <!--   <h4>${title}&nbsp;&gt;&nbsp;<span id="name"></span></h4>-->
            <form id="queryForm" action="queryWgList.json" method="post">
               <input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize"
					value="${pageSize}" />  
				<input type="hidden"  id="orgid" name="orgid" value="${orgid}">
              <table width="100%" class="noBorder" border="0" cellpadding="0" cellspacing="0">
                 <tr height="72">
                      <td colspan="4" align="left">
                      	<input type="text" class="i-text" id="wgmc" name="wgmc"style="width:95%"/>
                      </td>
                      <td  align="left">
                      	<input type="submit" class="input_btnImgBlue" id="query" value="查　询"/>
                      </td>
                  </tr>
              </table>
              
            </div>
        </div>
        <div style="padding:30px 25px;" id="czdiv">
        	<input class="input_btnColorlBlue mr15" id="xinjian" type="button" value="新建"/>
            <input class="input_btnColorlBlue mr15" id="edit"  onclick="update()" type="button" value="修改"/>
            <input class="input_btnColorlBlue mr15" id="del" type="button" value="删除"/>
        </div>
        <div class="dataDiv" id="dataDiv" style="padding:0px 25px 30px ; height:216px;">
           <table id="data" fit="true"></table>
        </div>
      </form>
    </div>
</div>
<script type="text/javascript">

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
		$("#orgid").val(id);
		$("#name").html(name);
		$.ajax({
			 type: "GET",
	         url: "queryWgList.json",
	         data: {orgid:id},
	         dataType: "json",
	         success: function(data){
	        	 $('#data').datagrid('loadData',data)
			 }
		});
}

var menu_ =${orgList};
var treeObj = $.fn.zTree.init($("#treeDemo"), setting, menu_);

function initH(){
	var hh=$(window).height()-98-50;
	$(".leftTree, .rightCon").height(hh+95+"px");
	$("#dataDiv").height((hh-92-18)+"px");
}
initH();

$(document).ready(function(){
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
	url:'queryWgList.json',//请求数据的超链接地址
	onLoadSuccess:function(data){
		    //datagrid鼠标经过提示单元格内容(需要在easyui.js中写扩展的doCellTip方法)
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
	
	columns:[[//设置表头
	    {field:'ck',checkbox:true},
	    {field:'id',hidden:true},
	    {field:'wgmc',title:'网格名称',align:'center',width:2,formatter : function(value, row,index) {
								return "<span style='font-size: 16px;font-weight:200;'><u><a onclick='info(this)' style='cursor:pointer;' id='"
										+ row.id
										+ "'>"
										+ value
										+ "</a></u></span>";
							}
	    },
		{field:'name',title:'网格负责人',align:'center',width:6},
		{field:'workmobile',title:'工作电话',align:'center',width:3},
		{field:'personmobile',title:'个人电话',align:'center',width:3}
	]]
	
});
initPager();
});

$(window).resize(function(){
	$("#data").datagrid('resize');
	initH();
});
//判断是否是叶子节点
function isParent(id){
	var treenodes = treeObj.getNodesByParam("id",id);
	if(treenodes.length>0){
		return treenodes[0].isParent;
	}
	return false;
}
//删除部门
$("#functionDel").click(function(){
	var orgid=$("#orgid").val();
	var index=layer.confirm('确定要删除当前部门吗？', {
     	icon: 3, 
        title:'部门管理'
     }, function(index){
    	 $.ajax( {
				url : "removeOrg.json?id="+orgid,
				success : function(data) {
					 $.ajax( {
							url : "orgQuery.json?isActive='Y'",
							success : function(data) {
								location.reload();
							}
						});
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
});
//添加部门

$("#functionAdd").click(function(){
	var orgid=$("#orgid").val();
		parent.layer.open({
		       type:2,
		       title:'新建部门',
		       area:['1100px','600px'],
		       shadeClose:false,
		       maxmin:true,
		       content:'editOrg.htm',
		       end:function(){
		    	   location.reload();
		       }
		     });
		
	});

//修改部门
$("#functionEdit").click(function(){
	var orgid=$("#orgid").val();
	parent.layer.open({
		       type:2,
		       title:'编辑部门',
		       area:['1100px','600px'],
		       shadeClose:false,
		       maxmin:true,
		       content:'editOrg.htm?id='+orgid,
		       end:function(){
		    	   location.reload();
		       }
		     });
		
	});
//信息查看
function info(obj){
	parent.layer.open({
        type: 2,
        title:' 查看网格',
        area: ['538px','600px'],
        content:'WgInfo.htm?id='+$(obj).attr("id"), 
        end: function () {
            //location.reload();
            refresh();
        }
      });
}

//信息修改
function update(){
	var rows = $('#data').datagrid('getSelections');
    if (rows.length > 1) {
    	alert('一次只能修改一条信息！');
    	return ;
    }else if (rows.length<1){
    	alert('请选择您要修改的信息！');
    	return ;
    }else {
    	var id = rows[0].id;
    	var orgid=$("#orgid").val();
    	 parent.layer.open({
    	        type: 2,
    	        title:'修改网格',
    	        area: ['538px','440px'],
    	        content:'editWg.htm?orgid='+orgid+'&id='+id, 
    	        end: function () {
    	            //location.reload();
    	            refresh();
    	        }
    	 });   				
    } 
}

//批量删除
$("#del").click(function(){
	var rows = $('#data').datagrid('getSelections');
	 var  ids = [];
	  $.each(rows, function (index, item){
	      ids.push(item.id); 
	  });
	  if(null !=ids && ""!=ids){
		var index=layer.confirm('确定要删除网格吗？', {
	     	icon: 3, 
	        title:'删除网格'
	     }, function(index){
	    	 $.ajax( {
					url : "delWg.json?ids="+ids,
					success : function(data) {
						  var tishi=layer.alert(data.msg,{
						     	title:'信息提示',
						        icon:1,
						        shadeClose:true,
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
		  alert("请选择要删除的网格！！！");
	  }
});


$("#xinjian").click(function(){
	var orgid=$("#orgid").val();
	if(!isParent(orgid)){
	if(null != orgid && "" != orgid){
	 parent.layer.open({
        type: 2,
        title:'新建网格',
        area: ['538px','440px'],
        content:'editWg.htm?orgid='+orgid, 
        end: function () {
            //location.reload();
            refresh();
        }
      });
	}else{
		alert("请选择所属部门");
	}
 }else{
	 alert("不能选择父节点创建网格！！！");
 }	
	
});




</script>

</body>
</html>
