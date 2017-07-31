<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>现场检查</title>
        <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css" />
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<!-- app -->
		<link rel="stylesheet" type="text/css" href="${app}/home.css" />
		<!-- ztree -->
		<link rel="stylesheet" href="${ztree}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
		<script type="text/javascript" src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="${ztree}/js/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript" src="${ztree}/js/jquery.ztree.exedit-3.5.js"></script>

		<script type="text/javascript" src="${upload}/extjs/ext-all.js"></script>
		<link type="text/css" href="${upload}/extjs/resources/css/ext-all.css" rel="stylesheet" />
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
		<script type="text/javascript" src="${jquery}/json2.js"></script>
		<script type="text/javascript" src="${common}/All.js"></script>
		<link href="${app}/zTreeReset.css" rel="stylesheet" type="text/css"/>
		<style type="text/css">
		.panel-header{
			border-color: #d4d4d4;
		}
		.x-panel-body-default{
			border-style: none none solid none;
		}
		</style>
		
	</head>
	<body style="padding:10px;">
		<div id="main" style="border:1px solid #d4d4d4;margin-right:0px;width:100%;">
			<form id="jcdForm">
				<input type="hidden" id="applyId" name="applyId" value="${applyId}" />
				<input type="hidden" id="taskType" name="taskType" value="${taskType}" />
				<input type="hidden" id="jcmbid" name="jcmbid" value="${jcmbId}" />
				<div class="left" id="left" style="position:fixed;top:10px;left:10px;border:1px solid #d4d4d4;OVERFLOW:auto;">
					<ul id="treeDemo" class="ztree"></ul>
				</div>
				<div class="right" id="right" style="position:fixed;top:10px;left:311px;border:1px solid #d4d4d4;">
					<div id="contents" style="border:none;"></div>
					<div class="bottomBtn" style="margin-top:0px;">
						 <input id="save" class="queryBlue" type="button" value="保存" /> 
					</div>
				</div>
			</form>
		</div>
		<div style="text-align:center;border:none;margin-top:4px;">
			<c:if test="${sysver != 0 }">
				 <input type="button" class="queryBlue" id="selectTask" value="加载历史记录" /> 
			</c:if>
			 <input id="buildRecord" class="queryOrange" type="button" value="生成监察笔录" /> 
			<!-- <span class="btn btn-ok"> <input type="button" onclick="javascript:downloadFile('${applyId}')" style="width:40px;" id="down" value="下载"/> </span> -->
		</div>
		<iframe name="downloadF" id="downloadF" src="" style="display: none"></iframe>
		<div id="coverDiv"  style="position:fixed;top:11px;left:312px;height:27px;width:99%;z-index:9999;color:#0e2d5f;font-size:20px; background-color: #ffffff;" ></div>
	</body>
</html>

<script type="text/javascript">

	
	// 一个监察模板下的检查项ids
	var jcxids = new Array();
	// 所有监察模板下的检查项ids
	var alljcxids = new Array();
	// 保存时传到后台的数据
	var subdata;
	// 监察模板id
	var jcmbId;
	var superJcmbId = $('#jcmbid').val();
	var unOpenTab = new Array();
	// 树配置
	var setting = {
		data: {
			simpleData: {
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
		var orderby = treeNode.orderby;
		jcmbId = id;
		var name = treeNode.name;
		if (!treeNode.isParent){
			$("#coverDiv").html(name);
			href += "?jcmbId=" + id + "&applyId=" + $("#applyId").val() + "&taskType=" + $("#taskType").val();
		} else {
			href = "";
		}

		if (href != ""){
			if (Ext.getCmp(id) == undefined){
				$.ajax({
					url: href,
					async:false,
					success:function(data){
						var jcxdata = jQuery.parseJSON(data.data);

						// 创建一个检查项页
						createTab(id, orderby, jcxdata);
						alljcxids[jcmbId] = jcxids;
						// 加载检查记录
						getCheckRecord();
					}
				});
			} else {
				jcxids = new Array();
				jcxids = alljcxids[jcmbId];
				Ext.getCmp("mainTab").setActiveTab(Ext.getCmp(id));
			}
		}
	}
	var menu_ = ${menu};
	// 树配置 end
	
	$("#save").click(function(){
		if (jcmbId == undefined){
			alert('请先打开检查单。');
			return;
		}
		var form = Ext.getCmp(jcmbId).getForm();
		if (form.isValid()){
			var data = new Array();
			data.push(getData(jcxids));
			save(jcmbId, data);

			// 打开下一个监察模板
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = new Array();
			nodes = treeObj.getNodesByParam("isParent", false, treeObj.getNodes()[0]);
			for (var i = 0; i < nodes.length; i++){
				if (nodes[i].id == jcmbId && nodes.length - i > 1) {
					onClick(null, null, nodes[i + 1], null);
					treeObj.selectNode(nodes[i + 1], false);
					break;
				}
			}
		}
	});
	
	function getData(jcxs){
		var str = new Array();
		subdata = new Array();
		for(var j = 0; j < jcxs.length; j++){
			var id = jcxs[j];
			var compoment = Ext.getCmp(id);
			var type = '';
			var checked;
			var value = '';
			var beizhu = '';
			var orderby = Ext.getCmp(id + "-orderby").getValue();
			if (compoment.getXType() == 'checkboxgroup'){
				beizhu = Ext.getCmp(id + "-beizhu").getValue();
				type = "checkboxgroup";
				checked = compoment.getChecked();
				if(checked!=null){
					for(var i = 0; i < checked.length; i++){
						if (i > 0){
							value += ",";
						}
						value += checked[i].id;
					}
				}
			} else if (compoment.getXType() == 'textareafield'){
				type = "textareafield";
				beizhu = Ext.getCmp(id).getValue();
			} else if (compoment.getXType() == 'radiogroup'){
				beizhu = Ext.getCmp(id + "-beizhu").getValue();
				type = "radiogroup";
				checked = compoment.getChecked();
				if(checked!=null){
					for(var i = 0; i < checked.length; i++){
						if (i > 0){
							value += ",";
						}
						value += checked[i].id;
					}
				}
			}
			str = {
				"id" : id,
				"type" : type,
				"value" : value,
				"beizhu" : beizhu,
				"orderby" : orderby,
				"applyId" : '${applyId}'
			}
			subdata.push(str);
		}
		return subdata;
	}
	function save(jcmbId, subdata){
		$.ajax({
			url: "saveCheckList.json",
			type:"post",
			async:false,
			data : {
				applyId : '${applyId}',
				jcmbId : jcmbId,
				subdata : JSON.stringify(subdata)
			},
			success:function(data){
				alert(data.msg);
			}
		});
	}

	// 生成检查记录文档
	function buildCheckListRecord(jcmbId, subdata){
		/**
		$.ajax({
			url: "saveCheckList.json",
			async:false,
			type:'POST',
			data : {
				applyId : '${applyId}',
				jcmbId : jcmbId,
				subdata : JSON.stringify(subdata)
			},
			success:function(data){
				$.ajax({
					url: "buildCheckListRecord.json",
					async:false,
					type:'POST',
					data : {
						applyId : '${applyId}',
						taskType : '${taskType}',
						jcmbId : $.fn.zTree.getZTreeObj("treeDemo").getNodes()[0].id
					},
					success:function(data){
						alert(data.msg);
						self.close();
					}
				});
			}
		});
		
		*/
		$.ajax({
			url: "buildCheckListRecord.json",
			async:false,
			type:'POST',
			data : {
				applyId : '${applyId}',
				taskType : '${taskType}',
				jcmbId : $.fn.zTree.getZTreeObj("treeDemo").getNodes()[0].id
			},
			success:function(data){
				alert(data.msg);
				parent.closeLayer();
			}
		});
		
		
	}
	
	// 遍历tree获取所有子节点
	function getAllNodes(tree, nodes, pid){
		var pNode = tree.getNodesByParam('pId', pid);
		for (var i = 0; i < pNode.length; i++){
			if (pNode[i].isParent){
				getAllNodes(tree, nodes, pNode[i].id);
			} else {
				nodes.push(pNode[i]);
			}
		}
	}

	function downloadFile(taskId){
		$.ajax({
			url: "checkJcdExists.json",
			async:false,
			type:'POST',
			data : {
				applyId : '${applyId}',
				taskType : '${taskType}'
			},
			success:function(data){
				if (data.state){
					$('#downloadF').attr('src','downloadCheckListRecord?applyId='+taskId + '&taskType=${taskType}');
				} else {
					alert("检查记录未生成，请先生成。");
				}
			}
		});
	}
	
	$("#buildRecord").click(function(){
		var mainTab = Ext.getCmp("mainTab");
		var child = mainTab.child().items;
		var tab;
		var idorder = new Array();
		var idorders = new Array();

		var tree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = new Array();
		getAllNodes(tree, nodes, tree.getNodes()[0].id);

		var data = new Array();
		var jcmbids = "";
		for (var i = 0; i < nodes.length; i++){
			if (i > 0){
				jcmbids += ",";
			}
			jcmbids += nodes[i].id;
			//data.push(getData(alljcxids[nodes[i].id]));
		}
		buildCheckListRecord(jcmbids, data);
		
	});

	var myStore =new Ext.data.JsonStore({
		autoSync : false,
		proxy: {
			type : 'ajax',
			url : 'dicList.json?type=12',
			params : {},
			reader : {
				type : 'json',
				totalProperty : '',
				root : ''
			} 
		},
		fields : ['id','name','value'],
		autoLoad : true
	})
	
	Ext.onReady(function(){
		$.ajaxSetup({cache:false});
		$("#main").height($(window).height() - 70);
		$("#main").width($(window).width() - 33);
		$("#left").height($(window).height() - 70);
		$("#left").width(300);
		$("#right").height($(window).height() - 70);
		$("#right").width($(window).width() - 322);
		$("#coverDiv").width($("#right").width());
		var tabHeight = $(window).height() - 115;
		var mainTab = Ext.create('Ext.tab.Panel',{
			width: "100%",
			height: tabHeight,
			layout : 'absolute',
			id : 'mainTab',
			renderTo: contents
		});

		var waitingbox = Ext.create('Ext.form.Label', {
			style: 'color : #95b8e7;margin-bottom:10px;font-size:18px;',
			width : 100,
			height : 100,
			text : '加载问题中，请稍后。。。',
			x : 100,
			y : 100,
			id : 'waitingbox'
		});

		mainTab.add(waitingbox);
		var treeObj = $.fn.zTree.init($("#treeDemo"), setting, menu_);
		treeObj.expandAll(true);

		var nodes = new Array();
		//getAllNodes(treeObj, nodes, treeObj.getNodes()[0].id);

		setTimeout("opentab()", 200);
	});

	function opentab(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");

		var nodes = new Array();
		getAllNodes(treeObj, nodes, treeObj.getNodes()[0].id);
			
		for (var i = 0; i<nodes.length  ; i++){
			if (!nodes[i].isParent) {
				onClick(null, null, nodes[i], null);
				treeObj.selectNode(nodes[i], false);
				break;
			}
		}
		
		
		
		/**
		for (var i = nodes.length - 1; i >= 0 ; i--){
			if (!nodes[i].isParent) {
				onClick(null, null, nodes[i], null);
				treeObj.selectNode(nodes[i], false);
			}
		}
		*/
	}

	//加载检查模板历史记录
	function getHistoryRecord(superJcmbId,workId){
		$.ajax({
			url: "getHistoryRecord.json",
			data : {
				superJcmbId : superJcmbId,
				workId : workId
			},
			success:function(data){
				var anss = jQuery.parseJSON(data.ans);
				var jcxid = "";
				var answer = "";
				var describe = "";
				for (var i = 0; i < anss.length; i++){
					jcxid = anss[i].itemid;
					answer = anss[i].answer;
					describe = anss[i].describe;
					var beizhu = Ext.getCmp(jcxid+"-beizhu");
					if (beizhu != null){
							beizhu.setValue(describe);
							beizhu.validate();
					}
					if (answer != undefined){
						var ans = Ext.getCmp(answer);
						if (ans != null){
							ans.setValue(true);
							ans.validate();
						}
					} else {
						var ans = Ext.getCmp(jcxid);
						if (ans != null){
							ans.setValue(describe);
							ans.validate();
						}
					}
				}
			}
		});
		layer.closeAll('iframe');
	}
	// 加载检查数据并给检查项赋值
	function getCheckRecord(){
		var applyId = '${applyId}';
		$.ajax({
			url: "getCheckRecord.json",
			data : {
				applyId : applyId,
				jcmbId : jcmbId,
				superJcmbId : superJcmbId,
				taskType : '${taskType}'
			},
			success:function(data){
				var anss = jQuery.parseJSON(data.ans);
				var jcxid = "";
				var answer = "";
				var describe = "";
				for (var i = 0; i < anss.length; i++){
					jcxid = anss[i].itemid;
					answer = anss[i].answer;
					describe = anss[i].describe;
					var beizhu = Ext.getCmp(jcxid+"-beizhu");
					if (beizhu != null){
							beizhu.setValue(describe);
							beizhu.validate();
					}
					if (answer != undefined){
						var ans = Ext.getCmp(answer);
						if (ans != null){
							ans.setValue(true);
							ans.validate();
						}
					} else {
						var ans = Ext.getCmp(jcxid);
						if (ans != null){
							ans.setValue(describe);
							ans.validate();
						}
					}
				}
			}
		});
	}

	// 创建一个检查项页面
	//  tempId：监察模板id
	//  orderby：监察模板排序
	// data: 根据此监察模板查询出的检查项及检查项对应的答案的json数据
	//  1，创建form作为tab页，此formid为监察模板id，form为纵向布局，所有检查项竖向排列；
	//  2，创建监察模板排序的隐藏域
	//  3，循环检查项创建问题项：{
	//   	1），创建检查项排序的隐藏域
	//  	2），根据检查项类型创建文本域||单选按钮组||多选按钮组
	 //  }
	//   其中创建问题项时有答案是否换行显示的设定
	//   所以创建了Hpanel1和Hpanel2两个form，都是横向布局
	 ///
	function createTab(tempId, orderby, data){
		var data = data;
		var tab = Ext.getCmp("mainTab");
		var panel = Ext.create('Ext.form.Panel', {
			height : 450,
			id : tempId,
			title : false,
			autoScroll : true,
			padding : 10,
			headerCfg:{style:'display:none'},
			border : false,
			layout: 'vbox'
		});
		var jcxid = "";
		var jcxcontents = "";
		var jcxinputtype = "";
		var jcxcontentsunit = "";
		var jcxgetlawobjvalue = "";
		var jcxisActive = "";
		var jcxisanswernewline = "";
		var jcxisrequired = "";
		var jcxorderby = "";
		
		var ansid = "";
		var ansitemid = "";
		var ansanswer = "";
		var ansanswerdesc = "";
		var ansisnormal = "";
		var ansorderby = "";
		var ansisActive = "";

		var ans;

		// 横向布局panel 1
		var Hpanel1;
		// 横向布局panel 2
		var Hpanel2;
		// 横向布局panel 3
		var Hpanel13;
		// 横向布局panel 4
		var Hpanel4;
		// 暂存按钮
		var btn;
		// 符号按钮
		var symbol;
		// 隐藏按钮
		var hideBtn;
		// 修改问题按钮
		var editBtn;
		// 暂存等待
		var waitMsg;
		// 问题项标题
		var label;
		// 备注
		var beizhu;
		// 单选按钮组
		var radioGroup;
		// 多选按钮组
		var checkboxGroup;
		// 检查项序号
		var hiddenOrderBy;
		
		var newId = "aaa"; 
		
		jcxids = new Array();
		var jcmbOrder = Ext.create('Ext.form.field.Hidden', {
			id : tempId + "-orderby",
			value : orderby
		});
		panel.add(jcmbOrder);
		for (var i = 0; i < data.length; i++){
			jcxid = data[i].id;
			jcxids[i] = jcxid;
			jcxcontents = data[i].contents;
			jcxinputtype = data[i].inputtype;
			jcxcontentsunit = data[i].contentsunit;
			jcxgetlawobjvalue = data[i].getlawobjvalue;
			jcxisActive = data[i].isActive;
			jcxisanswernewline = data[i].isanswernewline;
			jcxorderby = data[i].orderby;
			jcxisrequired = data[i].isrequired == 'Y' ? false : true;
			
			label = Ext.create('Ext.form.Label', {
              	width:500,
				id : jcxid + '-label',
				style: 'color : #95b8e7;margin-bottom:10px;font-size:20px;',
				text : i + 1 + '、' + jcxcontents
			});

			symbol = Ext.create('Ext.button.Button', {
				text : '符号',
				width : 45,
				style : 'margin-left:10px;',
				height : 25,
				id : jcxid + '-symbol',
				handler : function(e) {
					new Ext.Window({
						title: '单位符号',
						width: 400,
						height: 300,
						modal: true,
						id : e.id.substring(0, 32) + 'sywin',
						closable: true,
						items : [{
							xtype : 'grid',
					        height : 270,
					        border : false,
					        autoScroll : true,
					        listeners: {
					        	'itemdblclick' : function(o, record, item, index, e, eOpts){
									if (Ext.getCmp(this.up().id.substring(0, 32) + "-beizhu") == undefined){
										var text = Ext.getCmp(this.up().id.substring(0, 32));
										text.setValue(text.getValue() + record.getData().id);
									} else {
										var text = Ext.getCmp(this.up().id.substring(0, 32) + "-beizhu");
										text.setValue(text.getValue() + record.getData().id);
									}
									this.up().close();
								}
							},
							columns : [{
								xtype : 'rownumberer',
								resizable: true,
								width : 30
							}, {
								xtype : 'gridcolumn',
								text : '单位',
								align : 'center', 
								width : 160,
								dataIndex : 'id'
							}, {
								xtype : 'gridcolumn',
								text : '名称',
								align : 'center',
								width : 180,
								dataIndex : 'name'
							}],
							store : myStore
						}]
					}).show();
				}
			});
			
			btn = Ext.create('Ext.button.Button', {
				text : '暂存',
				width : 45,
				style : 'margin-left:10px;',
				height : 25,
				id : jcxid + '-btn',
				handler : function(e) {
					var id = e.id.substring(0, e.id.length - 4)
					var compoment = Ext.getCmp(id);
					var type = '';
					var checked;
					var value = '';
					var beizhu = '';
					var orderby = Ext.getCmp(id + "-orderby").getValue();
					if (compoment.getXType() == 'checkboxgroup'){
						beizhu = Ext.getCmp(id + "-beizhu").getValue();
						type = "checkboxgroup";
						checked = compoment.getChecked();
						for(var i = 0; i < checked.length; i++){
							if (i > 0){
								value += ",";
							}
							value += checked[i].id;
						}
					} else if (compoment.getXType() == 'textareafield'){
						type = "textareafield";
						beizhu = Ext.getCmp(id).getValue();
					} else if (compoment.getXType() == 'radiogroup'){
						beizhu = Ext.getCmp(id + "-beizhu").getValue();
						type = "radiogroup";
						checked = compoment.getChecked();
						for(var i = 0; i < checked.length; i++){
							if (i > 0){
								value += ",";
							}
							value += checked[i].id;
						}
					}
					Ext.getCmp(id + '-wait').show(),
					// 暂存
					$.ajax({
						url: "saveTemporary.json",
						data : {
							applyId : '${applyId}',
							jcmbId : jcmbId,
							itemId : id,
							type : type,
							answer : value,
							beizhu : beizhu,
							orderby : orderby
						},
						success:function(data){
							Ext.getCmp(id + '-wait').hide();
						}
					});
				}
			});
			
			hideBtn = Ext.create('Ext.button.Button', {
				text : '隐藏',
				width : 45,
				style : 'margin-left:10px;',
				height : 25,
				id : jcxid + '-hide',
				handler : function(e) {
					var id = e.id.substring(0, e.id.length - 5)
					var type = '';
					var checked;
					var value = '';
					var beizhu = '';
					var orderby = Ext.getCmp(id + "-orderby").getValue();
					Ext.MessageBox.buttonText.yes = "确定"; 
					Ext.MessageBox.buttonText.no = "取消"; 
					Ext.MessageBox.confirm('隐藏问题项', '确认隐藏？', function(btn) {
						if (btn=='yes') {
							// 隐藏
							$.ajax({
								url: "hideJcx.json",
								data : {
									applyId : '${applyId}',
									jcmbId : jcmbId,
									itemId : id,
									type : type,
									answer : value,
									beizhu : beizhu,
									orderby : orderby
								},
								success:function(data){
									Ext.getCmp(id + '-label').hide();
									Ext.getCmp(id + '-btn').hide();
									Ext.getCmp(id + '-symbol').hide();
									Ext.getCmp(id).hide();
									Ext.getCmp(id + '-hide').hide();
									Ext.getCmp(id + '-edit').hide();
									Ext.getCmp(id + '-beizhu').hide();
								}
							});
						}
					})
				}
			});
			
			editBtn = Ext.create('Ext.button.Button', {
				text : '修改',
				width : 45,
				style : 'margin-left:10px;',
				height : 25,
				id : jcxid + '-edit',
				handler : function(e) {
					var id = e.id.substring(0, e.id.length - 5);
					var orderby = Ext.getCmp(id + "-orderby").getValue();
					var jcxcontents = All.ShowModalWin('jcxEdit.htm?itemId='+id+'&jcmbId='+jcmbId+'&orderby='+orderby+'&applyId=${applyId}', '修改检查项',400,200);
					if(jcxcontents != undefined){
						var num = new Array();
						var val = Ext.getCmp(id + '-label').text;
						num = val.split("、");
						var text = num[0] + '、' + jcxcontents;
						Ext.getCmp(id + '-label').setText(text);
					}
				}
			});
			
			waitMsg = Ext.create('Ext.Img', {
				src: '${app}/images/loading01.gif',
				id : jcxid + '-wait',
				margin : '4 0 0 3',
				hidden : true
			});
			
			hiddenOrderBy = Ext.create('Ext.form.field.Hidden', {
				id : jcxid + "-orderby",
				value : jcxorderby
			});
			panel.add(hiddenOrderBy);
			Hpanel1 = Ext.create('Ext.form.Panel', {
				border : false,
				width : '100%',
				layout: 'hbox'
			});
			Hpanel2 = Ext.create('Ext.form.Panel', {
					border : false,
					layout: 'hbox'
			});
			if (jcxinputtype == '0'){
				var text = Ext.create('Ext.form.field.TextArea', {
					id : jcxid,
					name: jcxid,
					width : 500,
					msgTarget : 'side',
					blankText : '此项目为必填项',
					allowBlank: jcxisrequired,
					validator:function (){
						var val = Ext.getCmp(this.id).getValue();
						if(this.allowBlank==false && Ext.util.Format.trim(val)=="")       
			                return false;        
			            else        
			               return true;   
	                },
					resizable : true,
					resizeHandles : "s e",
					maxLengthText : "该输入项的最大长度是 1000",
					maxLength:1000,
					style: 'margin-bottom:10px;'
				});
				if (jcxisanswernewline == 'Y'){
					Hpanel1.add(label);
					Hpanel1.add(editBtn);
					if(jcxisrequired){
						Hpanel1.add(hideBtn);
					}
					Hpanel2.add(text);
					Hpanel2.add(symbol);
					Hpanel2.add(btn);
					Hpanel2.add(waitMsg);
					
				} else {
					Hpanel1.add(label);
					Hpanel1.add(editBtn);
					if(jcxisrequired){
						Hpanel1.add(hideBtn);
					}
					Hpanel1.add(text);
					Hpanel1.add(symbol);
					Hpanel1.add(btn);
					Hpanel1.add(waitMsg);
				}
				panel.add(Hpanel1);
				panel.add(Hpanel2);
			} else if (jcxinputtype == '1'){
				ans = data[i].ans;
				radioGroup = Ext.create('Ext.form.RadioGroup', {
					id : jcxid,
					allowBlank : jcxisrequired,
					msgTarget : 'side',
					blankText : '请至少选择一项',
					style: 'marginBottom:10px;'
				});
				for(var j = 0; j < ans.length; j++){
					ansid = ans[j].id;
					ansanswer = ans[j].answer;
					ansanswerdesc = ans[j].answerdesc;
					ansisnormal = ans[j].isnormal;
					ansorderby = ans[j].orderby;
					ansisActive = ans[j].isActive;
					// 将单选按钮添加到单选组中 
					radioGroup.add(Ext.create('Ext.form.field.Radio', {
						id : ansid,
						width : ansanswer.length * 12 + 18,
						boxLabel : ansanswer,
						xtype : 'radiofield',
						name : jcxid,
						style: 'margin-bottom:10px;margin-left:10px;'
					}));
				}
				
				beizhu = Ext.create('Ext.form.field.TextArea', {
					id : jcxid + "-beizhu",
					width : 500,
					msgTarget : 'side',
					blankText : '此项目为必填项',
					allowBlank: jcxisrequired,
					validator:function (){
						var val = Ext.getCmp(this.id).getValue();
						if(this.allowBlank==false && Ext.util.Format.trim(val)=="")       
			                return false;        
			            else        
			               return true;   
	                },
					resizable : true,
					resizeHandles : "s e",
					maxLengthText : "该输入项的最大长度是 1000",
					maxLength:1000,
					style: 'margin-bottom:10px;'
				});
				if (jcxisanswernewline == 'Y'){
					Hpanel1.add(label);
					Hpanel1.add(editBtn);
					if(jcxisrequired){
						Hpanel1.add(hideBtn);
					}
					panel.add(Hpanel1);
					panel.add(radioGroup);
					// 备注和暂存按钮放到一行
					Hpanel2.add(beizhu);
					Hpanel2.add(symbol);
					Hpanel2.add(btn);
					Hpanel2.add(waitMsg);
					
					panel.add(Hpanel2);
				} else {
					Hpanel1.add(label);
					Hpanel1.add(editBtn);
					if(jcxisrequired){
						Hpanel1.add(hideBtn);
					}
					Hpanel1.add(radioGroup);
					Hpanel2.add(beizhu);
					Hpanel2.add(symbol);
					Hpanel2.add(btn);
					Hpanel2.add(waitMsg);
					
					panel.add(Hpanel1);
					panel.add(Hpanel2);
				}
			} else if (jcxinputtype == '2'){
				ans = data[i].ans;
				checkboxGroup = Ext.create('Ext.form.CheckboxGroup', {
					id : jcxid,
					allowBlank : jcxisrequired,
					//columns: 3,
					blankText : '请至少选择一项',
					msgTarget : 'side',
					style: 'marginBottom:10px;'
				});
				for(var j = 0; j < ans.length; j++){
					ansid = ans[j].id;
					ansanswer = ans[j].answer;
					ansanswerdesc = ans[j].answerdesc;
					ansisnormal = ans[j].isnormal;
					ansorderby = ans[j].orderby;
					ansisActive = ans[j].isActive;
					checkboxGroup.add(Ext.create('Ext.form.field.Checkbox', {
						id : ansid,
						width : ansanswer.length * 12 + 18,
						boxLabel : ansanswer,
						xtype : 'checkboxgroup',
						name : jcxid,
						style: 'margin-bottom:10px;margin-left:10px;',
						inputValue: ansanswer
					}));
				}
				beizhu = Ext.create('Ext.form.field.TextArea', {
					id : jcxid + "-beizhu",
					width : 500,
					msgTarget : 'side',
					//autoSize : true,
					allowBlank: jcxisrequired,
					validator:function (){
						var val = Ext.getCmp(this.id).getValue();
						if(this.allowBlank==false && Ext.util.Format.trim(val)=="")       
			                return false;        
			            else        
			               return true;   
	                },
					blankText : '此项目为必填项',
					resizable : true,
					resizeHandles : "s e",
					maxLengthText : "该输入项的最大长度是 1000",
					maxLength:1000,
					style: 'margin-bottom:10px;'
				});
				if (jcxisanswernewline == 'Y'){
					Hpanel1.add(label);
					Hpanel1.add(editBtn);
					if(jcxisrequired){
						Hpanel1.add(hideBtn);
					}
					panel.add(Hpanel1);
					panel.add(checkboxGroup);
					// 备注和暂存按钮放到一行
					Hpanel2.add(beizhu);
					Hpanel2.add(symbol);
					Hpanel2.add(btn);
					Hpanel2.add(waitMsg);
					
					panel.add(Hpanel2);
				} else {
					Hpanel1.add(label);
					Hpanel1.add(editBtn);
					if(jcxisrequired){
						Hpanel1.add(hideBtn);
					}
					Hpanel1.add(checkboxGroup);
					Hpanel2.add(beizhu);
					Hpanel2.add(symbol);
					Hpanel2.add(btn);
					Hpanel2.add(waitMsg);
					
					panel.add(Hpanel1);
					panel.add(Hpanel2);
				}
			}
		}
		tab.add(panel);
		tab.setActiveTab(panel);
	}
function onBack() { 
	window.location.reload(); 
} 	
$(document).ready(function(){
	var jcmbid = $("#jcmbid").val();
	var applyId = $("#applyId").val();
	//加载执法对象的历史记录
	
  	$("#selectTask").click(function(){
  		var href = "jcd_historyPage.htm?applyId="+applyId+"&jcmbId="+jcmbid; 
  	  	var width=900;
  	  	var height=450;
  	  layer.open({
	  	  type: 2,
	  	  title: "加载历史记录",
	  	  maxmin: true,
	  	  shadeClose: false, //点击遮罩关闭层
	  	  area : [width+'px' , height+'px'],
	  	  content: href
	  	  });
  	});
	//$("#selectTask").colorbox({iframe:true,width:"1000", height:"600", scrolling:false, href:"jcd_historyPage.htm?applyId="+applyId+"&jcmbId="+jcmbid});
});
</script>
