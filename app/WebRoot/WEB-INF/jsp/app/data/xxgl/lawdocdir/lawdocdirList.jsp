<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>信息管理-执法文件目录管理</title>
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
<link href="${app}/common.css" rel="stylesheet" type="text/css"/>
<!--jQuery-->
<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css"/>
<link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<!-- 任务管理 css-->
<link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
<!-- ztree -->
<link rel="stylesheet" href="${ztree}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<link rel="stylesheet" href="${app}/css/zTreeReset.css" type="text/css" />
<script type="text/javascript" src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
<!--执法目录文件管理-->
<link rel="stylesheet" href="${app}/css/taskDispatch.css" type="text/css">
</head>
<body>
<div class="checkup" style="margin: 0 5px 5px !important;width:98%;">
<div class="breadClumbs" > 知识库&nbsp;&gt;&nbsp;执法文件目录管理</div>
 <!--   <h1 id="checkup_header">执法文件目录管理</h1>-->
 <div id="topadd">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="formtable">
					<tr>
						<td align="right">
				<input type="button" id="add" value="添加" class="bTn directory_add directory_comwidth" align="left"/>
					
                 		</td>
					</tr>
				</table>
			</div>
 
    <input type="hidden" name="selectId" id="selectId"/>
  <div class="clearfix">
    <div id="checkup_con_l">
      <div style="padding: 20px;">
        <ul id="treeDemo" class="ztree">
        </ul>
      </div>
    </div>
    <div id="checkup_con_r" style="overflow:none;width:69%;">
       <iframe id="ifram" name="myFrame" src="" width="100%"  frameborder="no" border="0" marginwidth="0" marginheight="0"  ></iframe>
      </div>
    </div>
  </div>
</div>
</body>
</html>
<script language="JavaScript">
	//树菜单配置
	var zTree_Menu = null, curMenu = null;
	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		},
		callback: {
			onClick : onClick
		},
		view: {
	            showLine: false
	        }
	};

	var menu_ = ${menu};
	// 点击菜单事件
	function onClick(event, treeId, treeNode, clickFlag) {
		$("#selectId").val(treeNode.id);
		var hrefsrc="editLawdocdir.htm?id="+treeNode.id+"&Rnd="+Math.random();
		$("#ifram").attr("src",hrefsrc);
	}
	var treeObj = $.fn.zTree.init($("#treeDemo"), setting, menu_);
	// 树 end
	$(document).ready(function() {
		var nodes = treeObj.getNodes();
		treeObj.expandNode(nodes[0], true, true, true);
	
		$("#add").click(function(){
			//All.ShowModalWin('editLawdocdir.htm?pid='+$("#selectId").val(), '工业污染源导入', 600, 350);
			//var width=screen.width * 0.8;
		  	///var height=screen.height * 0.8-50;
		  	//var title='添加执法文件目录';
		  	//parent.layerIframe(2,'editLawdocdir.htm?pid='+$("#selectId").val(),title,width,height);
		  	//window.location.reload();
		  	parent.layer.open({
	            type: 2,
	            title: '添加执法文件目录',
	            area: ['800px', ($(window).height()+120)+'px'],
	            content: 'editLawdocdir.htm?pid='+$("#selectId").val(), 
	            end: function () {
	                location.reload();
	                
	            }
	        });
		});
		$("#J-from-reset").click(function(){
			$("#myform").form("reset");
		});
	
	});
	
	//判断是否是叶子节点
	function isParent(id){
		var treenodes = treeObj.getNodesByParam("id",id);
		if(treenodes.length>0){
			return treenodes[0].isParent;
		}
		return false;
	}
	 function vheight() {
	        var topHeight = $("#checkup_header").height();
	        var botHeight = $("#checkup_footer").height();
	        var xh = $(window).innerHeight() - topHeight - botHeight - 68;
	        var th = xh-10;
	        $("#checkup_con_l").height(xh);
	        $("#checkup_con_r").height(xh);
	        $("#ifram").height(xh);
	    }
	    vheight();
	  //高度自适应
	    $(window).resize(function () {
	        vheight();
	    });
	    
</script>
