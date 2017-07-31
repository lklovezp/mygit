<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>${title }</title>
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${layer}/layer.js"></script>
	<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <!-- 任务管理 css-->
    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
    <!-- ztree -->
 <link rel="stylesheet" href="${ztree}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<link rel="stylesheet" href="${app}/css/zTreeReset.css" type="text/css" />
<script type="text/javascript" src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
    <!--执法目录管理-->
    <link rel="stylesheet" href="${app}/css/taskDispatch.css" type="text/css">
    <script type="text/javascript"src="${colorbox}/jquery.colorbox-min.js"></script>
    <link rel="stylesheet" type="text/css"href="${colorbox}/colorbox2.css">
    <script type="text/javascript" src="${common}/All.js"></script>
</head>
<body>
<div class="checkup" style="margin: 0 5px 5px !important;width:98%;" >
<div class="breadClumbs" > 知识库&nbsp;&gt;&nbsp;执法文件管理</div>
   <form id="queryForm" action="queryLawdocList.json" method="post">
		<input id="pid" name="pid" type="hidden"/>
		<input id="pname" name="pname" type="hidden"/>
		<input type="hidden" id="fid" name="fid" value="${fid}" />
		<input type="hidden" id="page" name="page" value="${page}" />
		<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
    <!--  <h1 id="checkup_header">执法文件管理</h1>-->
    <div class="clearfix">
        <div id="checkup_con_l">
            <div style="padding: 20px;">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
        </div>
        <div id="checkup_con_r" style="overflow-y: auto;width:69%;">
            <div style="width: 96%; margin: 0 auto; margin-top: 10px;" class="checkup_table">
                <table width="100%" class="queryTable" border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
                    <tr>
                        <th width="85">标题</th>
                        <td><input type="text" class="y-text" style="width:220px;" name="title" value=""/></td>
                        <th width="78">关键字</th>
                        <td><input type="text" class="y-text" style="width: 220px;" name="keywords" value=""/></td>
                    </tr>
                    <tr>
                        <td align="center" colspan="6">
                            <input type="submit" class="queryBlue" id="query" value="查　询" onclick="hideSearchForm()"/>
                            <input type="reset" class="queryOrange" id="J-from-reset" value="重　置"/>
                            <input type="button" value="新   建" class="bTn directory_add1 directory_comwidth1" id="add" style="font-size:16px; margin-top: 0px; margin-bottom:0px;">
                        </td>
                    </tr>
                </table>
            </div>
            <div style="border-top:1px dotted #acacac; text-align:right; margin: 0px 5px 0px; padding-top:10px; padding-bottom:0px;">
            </div>   
            <div class="enfor_document" style=" width:100%;" id="infectlist" >
                <table id="data" fit="true"></table>
            </div>
        </div>
    </div>
    </form>
    <iframe name="download" id="download" src="" style="display: none"></iframe>
</div>

<script>
    //状态下拉框
    $('#dic_state').combobox({
        height: 34,
        url: 'json/state.json',
        method: 'get',
        valueField: 'value',
        textField: 'text'
    });
    // zTree 的参数配置（setting 配置详解）
    var setting = {
        view: {
            showLine: false
        },
        callback: {
    		onClick : onClick
    	},
        data: {
            simpleData: {
                enable: true
            }
        }
    };
    // zTree 的数据属性（zTreeNode 节点数据详解）
    var menu_=${menu};
    function onClick(event, treeId, treeNode, clickFlag) {
    	$("#pid").val(treeNode.id);
    	$("#pname").val(treeNode.name);
    	$('#data').datagrid('reload',{pid:treeNode.id});
    }
    
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
            area: ['800px', ($(window).height()+120)+'px'],
            content: 'editLawdoc.htm?id='+obj.id, 
            end: function () {
            	$('#queryForm').submit();
                //location.reload();
            }

        });
    }
    /*
    function deletefile1(obj){
    	$.messager.confirm('执法文件管理', '确定要删除吗？', function(r){
    		if (r){
    			$.ajax({
    			  url: "deleteLawdoc.json?id="+obj.id,
    			  success:function(data){
    				 alert(data.msg);
    				 $('#queryForm').submit();
    			  }
    			});
    		}
    	});
    }*/
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
    
    $(document).ready(function () {
    	var treeObj = $.fn.zTree.init($("#treeDemo"), setting, menu_);
    	var nodes = treeObj.getNodes();
    	treeObj.expandNode(nodes[0], true, true, true);
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

    	$("#add").click(function(){
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
    		//All.ShowModalWin('addLawdoc.htm?uuid=${uuid}&id='+pid+'&name='+encodeURIComponent(pname), '新建执法文件');
    		
    		parent.layer.open({
                type: 2,
                title: '新建执法文件',
                area: ['800px', ($(window).height()+120)+'px'],
                content: 'addLawdoc.htm?uuid=${uuid}&id='+pid+'&name='+encodeURIComponent(pname), 
                end: function () {
                	$('#queryForm').submit();
                    //location.reload();
                }

            });
    		
    	});
    	
    	$('#data').datagrid({
    		rownumbers: true,
    		singleSelect: true,
    		nowrap:false,
    		fitColumns : true,
    		pagination:true,
    		url:'queryLawdocList.json',
    		onLoadSuccess:function(data){
    			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
    		},
    		columns:[[
    			{field:'id',hidden:true},
    			{field:'fileid',hidden:true},
    			{field:'name',title:'标题', align:"left", halign:'center',width:100},
    			{field:'keywords',title:'关键词', align:"left", halign:'center',width:50},
    			{field:'dirpath',title:'目录', align:"center", halign:'center',width:100},
    			{field:'operate',title:'操作', align:"center", halign:'center',width:55}
    		]]
    	});
    	initPager();
    	
    	$("#J-from-reset").click(function(){
    		$("#queryForm").form("reset");
    	});
   
    });

    ////////////////////////////////////
    function vheight() {
       // var topHeight = $("#checkup_header").height();//h1 80
        var topHeight=0;
        var enforHeight = $(".checkup_table").height();
        var xh = $(window).innerHeight() - topHeight - 25;
        var th = xh - enforHeight-20;
        $("#checkup_con_l").height(xh);
        $("#checkup_con_r").height(xh);
        $("#infectlist").height(th);
    }
    vheight();
    ////////////////////////////////////
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
   
   
    //高度自适应
    $(window).resize(function () {
        vheight();
    });

    //数据表格自适应宽度
    $(window).resize(function () {
        $('#data').datagrid("resize");
    });
</script>
</body>
</html>
