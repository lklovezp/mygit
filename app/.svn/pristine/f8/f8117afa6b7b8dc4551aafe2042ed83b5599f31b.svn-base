<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
        <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="${app}/js.js"></script>
		<script type="text/javascript" src="${app}/data.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<!-- app -->
		<link rel="stylesheet" type="text/css" href="${app}/home.css"/>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<script type="text/javascript" src="${common}/All.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<!-- ztree -->
		<link rel="stylesheet" href="${ztree }/css/zTreeStyle/zTreeStyle.css" type="text/css" />
		<script type="text/javascript" src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="${ztree}/js/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript" src="${ztree}/js/jquery.ztree.exedit-3.5.js"></script>
		<!-- zTree 修改的样式-->
         <link href="${app}/css/zTreeReset.css" rel="stylesheet" type="text/css"/>
		 <!--自由裁量权裁标准管理的样式-->
        <link rel="stylesheet" href="${app}/css/taskDispatch.css" type="text/css">
<style type="text/css">
.headTitle{
	text-align: center;
	height: 50px;
	line-height: 50px;
	color: #262626;
	font-size: 18px;
}
</style>
	</HEAD>
	<body>
	<div class="breadClumbs" id="divTitle"> 知识库&nbsp;&gt;&nbsp;${title}</div>
		 <!--   <div class="headTitle" id="divTitle">${title}</div>-->
		<div id="main" style="">
			<div id="topadd">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="formtable">
					<tr>
						<td align="right">
							<input type="button" id="add" value="添加" align="right" class="bTn directory_add directory_comwidth" style="margin:0px 0 10px 20px"/>
						</td>
					</tr>
				</table>
			</div>
			<div class="left" id="left" style="width:30%;border:1px solid #d4d4d4; OVERFLOW-Y: auto; OVERFLOW-X:auto;" data-options="region:'west'">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div class="right" id="right" style="width : 69%;">
				<div id="rightbottom">
				</div>
			</div>
		</div>
		
		<div id="main">
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
		},view: {
            showLine: false
        }
	};

	var menu_ = ${menu};
	// 点击菜单事件
	function onClick(event, treeId, treeNode, clickFlag) {
		var level = treeNode.level;
		var href = treeNode.href;
		var id = treeNode.id;
		var name = treeNode.name;
		
		if (treeNode.id != "" && treeNode.id != null && treeNode.id != "topid") {
			var iframe = "<iframe id='" + id + "' frameborder='0' src='" + href + "' style='width:100%;height:100%;'></iframe>";
			document.getElementById('rightbottom').innerHTML = iframe;

		} else {
			$("#id").val('');
			$("#name").val('');
			$('#pid').combotree('setValue', '');
			$("#describe").val('');
			$("#orderby").val('');
		}
	}
	// 树 end
	$(document).ready(function() {
		$.ajaxSetup({cache:false});
		$("#pid").combotree({
			type:"post",
			url:'illegalTypeList.json?ids=' + $("#id").val(),
			valueField:'id',
			textField:'name'
		});
		
		var treeObj = $.fn.zTree.init($("#treeDemo"), setting, menu_);
		var nodes = treeObj.getNodes();
		treeObj.expandNode(nodes[0], true, true, true);

		$("#left").height($(window).height() - $("#divTitle").outerHeight() - $("#topadd").outerHeight() - 10);
		$("#right").height($(window).height() - $("#divTitle").outerHeight() - $("#topadd").outerHeight() - 10);
		$("#right").width($(window).width() - $("#left").outerWidth() - 20);
		$("#rightbottom").height($(window).height() - $("#divTitle").outerHeight() - $("#topadd").outerHeight() - 10);
		$("#main").width($(window).width() - 20);

		//表单校验
		$("#myform").validate({
			errorClass: "error",
			submitHandler:function(form){
				$(form).ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"saveIllegalType.json",
					success: function(data){
						alert(data.msg);
						location.reload();
					}
				});
			},
			rules:{
				"name": { required: true },
				"orderby":{ required: true }
			}
		});
	});
	$("#add").click(function(){
		layerIframe(2,"editIllegalType.htm?pid=","添加子类型",600,420);
		
		//All.ShowModalWin("editIllegalType.htm?pid=", "", 600, 400);
		//location.reload();
	})
	
	$("#J-from-reset").click(function(){
		$("#myform").form("reset");
	})
	function closeLayer(){
	layer.closeAll('iframe');
	location.reload();
	}
	function layerIframe(types,href,title,width,height){
		  layer.open({
		  	  type: types,
		  	  title: title,
		  	  maxmin: true,
		  	  shadeClose: false, //点击遮罩关闭层
		  	  area : [width+'px' , height+'px'],
		  	  content: href
		  	  });
		  
	}
	
</script>