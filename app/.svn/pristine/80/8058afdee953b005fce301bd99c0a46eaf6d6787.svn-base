<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${common}/All.js"></script>
<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
<!--jQuery-->
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<!--easyui-->
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/attachFileList.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<!-- ztree -->
<link rel="stylesheet" href="${ztree}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ztree}/js/jquery.ztree.excheck-3.5.js"></script>
<link href="${app}/css/manuscriptReviewer.css" rel="stylesheet" type="text/css" />
</head>
<style>
#main .con .subTit {
    text-align: center;
    font-family: 'Microsoft YaHei';
    font-size: 16px;
    padding: 10px 10px 15px;
    font-weight: bold;
}
#main .con .ulDiv .jb {
    padding-top: 13px;
}
</style>
<body>
<h1 class="h1_1" style="padding-top: 0px;">稿件审核人设置</h1>
<center>
    <div id="main" class="clearfix">
        <div class="left"><div class="con">
          <p class="subTit">部门</p>
          <div class="ulDiv">
            <input type="hidden" id="orgId" />
            <ul id="treeDemo" class="ztree"></ul>
          </div>
        </div></div>
        <div class="center"><div class="con">
          <p class="subTit">审核级别</p>
          <div class="ulDiv" style="padding:0px;">
                <c:forEach items="${jbList}" var="item">
                <div class="jb" id="jb">
                   <p class="jb1" data_value="${item.id}" onclick="checkOn(this)"><a>${item.name}</a></p>
                </div>
               </c:forEach>
          </div>
        </div></div>
        <div class="right"><div class="con">
          <p class="subTit" style="margin-top:0px;">审核人</p>
          <div class="ulDiv">
           <ul id="shrTreeDemo" class="ztree"></ul>
          </div>
        </div>
       </div>
    </div>
    <div style="padding:0px 0px;">
        <input type="button" class="queryOrange" onClick="save();" value="保　存"/>
    </div>
</center>
<script type="text/javascript">

// 审核级别的值
function get_platIdVal(){
	return $('.jb').find(".on").attr("data_value");
}

function checkOn(t){//点击切换效果
	$('.jb').find("p").removeClass("on");
	$(t).addClass("on");
}

//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
//加载部门树
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
var menu_= ${orgList};

function onClick(event, treeId, treeNode, clickFlag) {
	
	var jb = get_platIdVal();//审核级别
	
}
function onClick(event, treeId, treeNode, clickFlag) {
	var level = treeNode.level;
	var href = treeNode.href;
	var id = treeNode.id;
	var name = treeNode.name;
	$("#orgId").val(id);
	//根据部门和审核级别获取审核人员，刷新人员树
	var jb = get_platIdVal();//审核级别
	$.ajax({
		 type: "GET",
         url: "queryShr.json",
         data: {orgId:id,jb:jb},
         dataType: "json",
         success: function(data){
            if(data.state){
				//重新加载人员树
				//var menu2_= ${data.shrList};
				var menu2_=JSON.parse(data.shrList);
				
				var shrTreeObj1 = $.fn.zTree.init($("#shrTreeDemo"), setting1, menu2_);
				shrTreeObj1.expandAll(true);//展开所有节点
			}else{
				$.messager.alert('刷新审核人员树:',data.msg);
			}
		 }
	});
	
}
// 部门树 end
//////////////////////////////////////////////////
/////////////////////////////////////////////////
//加载人员树
var psRelation = {
			"Y" : "p",
			"N" : "s"
	};
	var setting1 = {
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
			onClick : onClickMultiple
		}
	};
	setting1.check = {
				enable : true,
				chkStyle : "checkbox",
				chkboxType : psRelation
	};
	
	var menu1_= ${shrList};
	function onClickMultiple(event, treeId, treeNode, clickFlag) {
		if (treeNode.isParent || treeNode.isorg) {
			alert("不能选择部门。");
			return;
			//return false;
		}
	}
	
	$(document).ready(function(){
		$(".ulDiv").height($(window).height() - $("#divTitle").outerHeight() - 220);
		// 审核级别的值
		var platIdVal = get_platIdVal();
		// 如果platIdVal为空，说明没有勾选
		if(platIdVal==null || platIdVal == ''){
			// 默认选中审核级别的第一个
			$('.jb1').eq(0).addClass("on");
		}
		//加载部门树
		var treeObj = $.fn.zTree.init($("#treeDemo"), setting, menu_);
		treeObj.expandAll(true);
		//加载人员树
		var shrTreeObj = $.fn.zTree.init($("#shrTreeDemo"), setting1, menu1_);
		shrTreeObj.expandAll(true);
	});
	//审核级别多选框变单选
	
			$('.jb1').click(function(){ 
					var orgId = $("#orgId").val();
					if(orgId==null || orgId == ''){
						// 默认选中审核级别的第一个
						alert("请选择部门");
					}else{
					//根据部门和审核级别获取审核人员，刷新人员树
					var jb = get_platIdVal();//审核级别
					$.ajax({
						 type: "GET",
			             url: "queryShr.json",
			             data: {orgId:orgId,jb:jb},
			             dataType: "json",
			             success: function(data){
			                if(data.state){
								//重新加载人员树
								var menu2_= JSON.parse(data.shrList);
								var shrTreeObj1 = $.fn.zTree.init($("#shrTreeDemo"), setting1, menu2_);
								shrTreeObj1.expandAll(true);//展开所有节点
							}else{
								$.messager.alert('刷新审核人员树:',data.msg);
							}
						 }
			    	});	
					}
			}); 
	
	//保存
	function save(){
		var jb = get_platIdVal();//审核级别
		var orgId = $("#orgId").val();//部门
		var obj=$.fn.zTree.getZTreeObj("shrTreeDemo"),//审核人
	        	nodes=obj.getCheckedNodes(true),
	        	shr="";
	    for(var i=0;i<nodes.length;i++){
	            shr+=nodes[i].id + ",";	//获取选中节点的值
	    }
		if(orgId == null || orgId == ""){
			alert("请选择部门");
			return ;
		}
		if(jb == null || jb == ""){
			alert("请选择审核级别");
			return ;
		}
		if(shr == null || shr == ""){
			alert("请选择审核人");
			return ;
		}
		$.ajax({
				 type: "GET",
	             url: "drafterSetSave.json",
	             data: {orgId:orgId,jb:jb,shr:shr},
	             dataType: "json",
	             success: function(data){
	                if(data.state){
						alert(data.msg);
					}else{
						$.messager.alert('保存稿件审核人设置:',data.msg);
					}
				 }
	    });	
	}
</script>

</body>
</html>
