<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>笔录管理</title>
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
	<div class="breadClumbs"> 知识库&nbsp;&gt;&nbsp;${title}&nbsp;&gt;&nbsp;<span id="name"></span></div>
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
            <form id="queryForm" action="queryRecordList.json" method="post">
                <input id="pid" name="pid" type="hidden"/>
		        <input id="pname" name="pname" type="hidden"/>
		        <input type="hidden" id="fid" name="fid" value="${fid}" />
				<input type="hidden" id="page" name="page" value="${page}" />
				<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />  
				<input type="hidden"  id="lawobjtypeid" name="lawobjtypeid" value="${lawobjtypeid}">
              <table width="100%" class="noBorder" border="0" cellpadding="0" cellspacing="0">
                 <tr height="72">
                      <td colspan="3" align="left">
                      	
                        <th width="85">问题项内容</th>
                        <td><input type="text" class="y-text" style="width:220px;" id="content" name="content" /></td>
                        
                      </td>
                      <th width="78">笔录类型</th>
                        <td><input type="text" class="y-text" style="width: 220px;" id="kcxwbj" name="kcxwbj"/></td>
                      
                      <td colspan="2" align="left">
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
<iframe name="download" id="download" src="" style="display: none"></iframe>
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
			enable: true
		}
	},
	callback: {
		onClick : onClick
	},view: {
        showLine: false
    }
};

function onClick(event, treeId, treeNode, clickFlag) {
		var level = treeNode.level;
		var href = treeNode.href;
		var id = treeNode.id;
		var name = treeNode.name;
		$("#pid").val(id);
		$("#pname").val(name);
		$("#name").html(name);
		/*$.ajax({
			 type: "GET",
	         url: "queryZfdxList.json",
	         data: {lawobjtypeid:id},
	         dataType: "json",
	         success: function(data){
	        	 $('#data').datagrid('loadData',data)
			 }
		});*/
		$('#data').datagrid('reload',{wflx:treeNode.id});
}

var menu_ =${menu};
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

	$('#kcxwbj').combobox({
		height:34,
		data:[{'id':'0','name':'勘察笔录'},{'id':'1','name':'询问笔录'}],
		editable:false,
		valueField:'id',
		textField:'name'
	});
	

//easyui数据表格
$('#data').datagrid({
	rownumbers: true,
	singleSelect: false,
	nowrap:false,
	fitColumns : true,
	pagination:true,
	url:'queryRecordList.json?wflx='+$("#pid").val(),//请求数据的超链接地址
	onLoadSuccess:function(data){
		    //datagrid鼠标经过提示单元格内容(需要在easyui.js中写扩展的doCellTip方法)
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
	
		columns : [ [
            {field:'ck',checkbox:true},
		    {field : 'id',hidden : true}, 
			{field : 'content',title : '问题项内容',align : 'left',halign : 'center',width : 100}, 
			{field : 'kcxwbj',title : '笔录类型',align : 'center',halign : 'center',width : 30},
			{field : 'isdel',title : '是否可删除',align : 'center',halign : 'center',	width : 30}
	]]
	
});
initPager();



$('#ssjgbm').combotree({
	height:34,
	url:'orgTree.json',
	valueField:'id',
	textField:'name'
}).combobox("initClear");


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
//删除执法对象类型
$("#functionDel").click(function(){
	
	if($("#pid").val()==''){
		alert("请选择要删除的执法文件目录！");
		return;
	}
	if(!isParent($("#pid").val())){
		var index=parent.layer.confirm('确定删除吗？', {
	     	icon: 3, 
	        title:'删除任务'
	     }, function(index){
	    	 $("#queryForm").ajaxSubmit( {
	    		    type:"post",
					url:"removeIllegalType.json?id="+$("#pid").val(),
					dataType:"json",
					success : function(data) {
						if(data.state){
						    location.reload();
						}else{
							//alert(data.msg);
							var index=parent.layer.alert(data.msg,{
						     	title:'操作信息:',
						        icon:1,
						        shadeClose:true,
						     },
						     function(){
						    	 parent.layer.close(index);
						      });
						}
					}
				});
	    	 parent.layer.close(index);
	     },function(index){
	    	 parent.layer.close(index);
	     }
	    );
	}else{
		//alert("该目录下有子节点，不能删除！");
		var index=layer.alert("该目录下有子节点，不能删除！",{
	     	title:'操作信息:',
	        icon:1,
	        shadeClose:true,
	     },
	     function(){
	        layer.close(index);
	      });
	}
});


//添加执法对象类型
$("#functionAdd").click(function(){
	parent.layer.open({
        type: 2,
        title: '添加违法类型',
        area: ['800px', '500px'],
        content: 'editIllegalType.htm?pid='+$("#pid").val(), 
        end: function () {
            location.reload();
            
        }
    });
	
});
	

//修改执法对象类型
$("#functionEdit").click(function(){
	if(null !=$("#pid").val() && ""!=$("#pid").val()){
	parent.layer.open({
		       type:2,
		       title:'修改违法类型',
		       area:['800px', '500px'],
		       shadeClose:false,
		       maxmin:true,
		       content:'editIllegalType.htm?id='+$("#pid").val(),
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
	var pid=$("#pid").val();
	if(null !=pid && ""!= pid){
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
    	        title:'修改笔录问题',
    	        area: ['800px','500px'],
    	        content:'editRecord.htm?id='+id+'&pid='+pid, 
    	        end: function () {
    	        	
    	        	$('#data').datagrid('reload',{wflx:pid});
    	        }
    	 });   				
    }
      }else{
    	  alert("请选择执法对象类型！！！");
      }
}

//批量删除
$("#del").click(function(){
	var pid = $("#pid").val();
	var rows = $('#data').datagrid('getSelections');
	 var  ids = [];
	  $.each(rows, function (index, item){
	      ids.push(item.id); 
	  });
	  if(null !=ids && ""!=ids){
		var index=layer.confirm('确定要删除执法对象吗？', {
	     	icon: 3, 
	        title:'删除问题笔录'
	     }, function(index){
	    	 $.ajax( {
					url : "removeRecord.json?ids="+ids,
					success : function(data) {
						  var tishi=layer.alert(data.msg,{
						     	title:'信息提示',
						        icon:1,
						        shadeClose:true
						     },
						     function(){
						        $('#data').datagrid('reload',{wflx:pid});
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
	var pid = $("#pid").val();
	var pname = $("#pname").val();
	if(pid==''){
		//alert("请先选择目录！");
		var index=layer.alert("请先选择目录！",{
	     	title:'操作信息:',
	        icon:1,
	        shadeClose:true,
	     },
	     function(){
	        layer.close(index);
	      });
		return;
	}
	
	parent.layer.open({
        type: 2,
        title: '新建问题笔录',
        area: ['800px','500px'],
        content: 'editRecord.htm?wflx='+pid, 
        end: function () {
            //location.reload();
        	$('#data').datagrid('reload',{wflx:pid});

        }

    });
	
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
	 parent.layer.open({
        type: 2,
        title: '选择执法对象',
        area: ['1100px', '600px'],
        content: 'choseezfdx.htm?lawobjtypeid='+lawobjtypeid, 
        end: function () {
            //location.reload();
        	refresh();
        }

    });
   

});
//文档编辑
function edit(obj){
    	//All.ShowModalWin('editLawdoc.htm?id='+obj.id, '查看');
    	//var width=screen.width * 0.8;
      	//var height=screen.height * 0.8-50;
      	//var title='编辑';
      	//parent.layerIframe(2,'editLawdoc.htm?id='+obj.id,title,width,height);
    	//$('#queryForm').submit();
    	parent.layer.open({
            type: 2,
            title: '新建执法文件',
            area: ['800px', '500px'],
            content: 'editLawdoc.htm?id='+obj.id, 
            end: function () {
            	$('#queryForm').submit();
                //location.reload();
            }

        });
    }
//文件删除
function deletefile1(obj){
	var index=layer.confirm('确定删除吗？', {
     	icon: 3, 
        title:'删除任务'
     }, function(index){
    	 $.ajax( {
				url : "deleteLawdoc.json?id="+obj.id,
				success : function(data) {
					//refresh();
					 alert(data.msg);
    				 $('#queryForm').submit();
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
	
}
</script>

</body>
</html>
