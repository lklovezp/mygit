<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=8"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>

		<script type="text/javascript" src="${upload}/swfupload/swfupload.js"></script>
		<script type="text/javascript" src="${upload}/extjs/ext-all.js"></script>
		<script type="text/javascript" src="${upload}/swfupload/FileModel.js"></script>
		<script type="text/javascript" src="${upload}/swfupload/plugins/swfupload.speed.js"></script>
		<script type="text/javascript" src="${upload}/swfupload/plugins/swfupload.queue.js"></script>
		
		<link href="${upload}/extjs/resources/css/ext-all.css" rel="stylesheet" type="text/css" />
	</HEAD>

	<body>
		<input type="hidden" id="yjId" name="yjId" value="${fileForm.pid}"/>
		<div id="uploadPanel"></div>
		
	</body>
</html>
<script language="JavaScript">
function wait(){
	alert("上传完成。");
	Ext.getCmp("upload-panel-ID").close();
	parent.reload('${fileForm.tableId}','${fileForm.pid}','${fileForm.fileType}');
}
Ext.onReady(function(){
	parent.$('#cboxClose').unbind("click"); //移除click事件,
	parent.$('#cboxClose').bind("click"); //添加click事件,
	parent.$("#cboxClose").click(function(){
		Ext.getCmp("upload-panel-ID").close();
		parent.jQuery.colorbox.close();
	})
	
	Ext.define("UploadPanel", {
		extend : "Ext.panel.Panel",
		alias : "widget.fileuploadPanel",
		layout : "fit",
		id: 'upload-panel-ID',
		initComponent : function() {
			this.width = 510;
			this.height = 300;
			this.continuous = true; // 是否连续上传，true为连续上传队列后其他文件,false只上传当前队列开始的文件
			this.setting = {
				upload_url : this.uploadUrl,
				flash_url : this.flashUrl,
				file_size_limit : "2048 MB", // 上传文件体积上限，单位MB
				file_post_name : "upload_file",
				file_types : this.fileTypes, // 允许上传的文件类型
				file_types_description : "Files", // 文件类型描述
				file_upload_limit : this.uploadLimit, // 限定用户一次性最多上传多少个文件，在上传过程中，该数字会累加，如果设置为“0”，则表示没有限制
				file_queue_limit : "10", // 上传队列数量限制，该项通常不需设置，会根据file_upload_limit自动赋值
				debug : false,
				post_params : {},
				button_cursor : SWFUpload.CURSOR.HAND,
				button_window_mode : SWFUpload.WINDOW_MODE.TRANSPARENT,
				custom_settings : { // 自定义参数
					scope_handler : this
				},
				swfupload_loaded_handler : function() {}, // 当Flash控件成功加载后触发的事件处理函数
				file_dialog_start_handler : function() {
				}, // 当文件选取对话框弹出前出发的事件处理函数
				file_dialog_complete_handler : function() {
					var me = this.customSettings.scope_handler;
					me.linkBtnEvent();
				}, // 当文件选取对话框关闭后触发的事件处理
				upload_start_handler : function(file) {
				/**
					//判断数据库是否有企业
					$.ajax({
						url: "findCompany.json",
						async:false,
						type:'POST',
						data : {
							fileName : file.name,
							
						},
						success:function(data){
							if(data.msg=="false"){
								alert("上传的企业不存在！");
								this.swfupload.stopUpload();
							}
						}
					});
					
					*/
					
				}, // 开始上传文件前触发的事件处理函数
				upload_success_handler : this.uploadSuccess, // 文件上传成功后触发的事件处理函数
				upload_progress_handler : this.uploadProgress,
				upload_complete_handler : this.uploadComplete,
				upload_error_handler : this.onFileError,
				file_queue_error_handler : this.onFileError,
				file_queued_handler : this.onQueued
			};
			this.items = [{
				xtype : "grid",
				border : false,
				store : Ext.create("Ext.data.Store", {
					model : "FileModel",
					storeId : "fileItems"
				}),
				columns : [ new Ext.grid.RowNumberer(), {
					header : '文件名',
					width : 200,
					sortable : true,
					dataIndex : 'name',
					menuDisabled : true
				}, {
					header : '类型',
					width : 80,
					sortable : true,
					dataIndex : 'type',
					menuDisabled : true
				}, {
					header : '大小',
					width : 90,
					sortable : true,
					dataIndex : 'size',
					menuDisabled : true,
					renderer : this.formatFileSize
				}, {
					header : '进度',
					width : 80,
					sortable : true,
					dataIndex : 'percent',
					menuDisabled : true,
					renderer : this.formatProgressBar,
					scope : this
				}, {
					header : '状态',
					width : 80,
					sortable : true,
					dataIndex : 'filestatus',
					menuDisabled : true,
					renderer : this.formatFileState,
					scope : this
				}, {
					header : '&nbsp;',
					width : 45,
					dataIndex : 'id',
					menuDisabled : true,
					align : 'center',
					renderer : this.formatDelBtn
				}]
			}];
			this.tbar = [ {
				text : '添加文件',
				id : "btnAdd",
				icon : "${upload}/swfupload/images/icon16/add.png"
			}, '-', {
				text : '开始导入',
				handler : function() {
					var swf = this.swfupload;
					if (swf) {
						swf.displayDebugInfo();
						if (swf.getStats().files_queued > 0) {
							swf.setPostParams({
								"pid": "${fileForm.pid}",
								"fileType": "${fileForm.fileType}",
								"path": "${fileForm.path}"
							});
							swf.startUpload();
						}
					}
				},
				scope : this,
				icon : "${upload}/swfupload/images/icon16/upload.png"
			}, '-' , {
				text	: '清空列表',
				handler : function() {
					this.swfupload.cancelQueue();
					var store = Ext.data.StoreManager.lookup("fileItems");
					store.removeAll();
					var stats = this.swfupload.getStats();
					var label = Ext.getCmp("queue_id");
					label.setText(label.text = "队列中文件个数"+":" + stats.files_queued);
				},
				icon : "${upload}/swfupload/images/icon16/remove.png",
				scope : this
			}, '-', {
				xtype : 'label',
				id : "queue_id",
				text : '队列中文件个数'+':0',
				margins : '0 0 0 10'
			}];
			this.bbar = [ {
				xtype : "progressbar",
				id : "progressBar",
				text : "0%",
				width : 200
			}, {
				xtype : "label",
				text : "平均速度"+("：0kb/s"),
				id : "currentSpeed",
				width : 200
			}, '-', {
				xtype : "label",
				text : "剩余时间"+"：0s",
				id : "timeRemaining",
				width : 200
			}];
			this.listeners = {
				'afterrender' : function() {
					var em = Ext.get(Ext.query("#btnAdd>em")[0]);
					if (!em) {
						// 此处为IE9以下版本的兼容问题的临时解决办法，目前还是不支持IE6
						em = Ext.get("btnAdd-btnWrap");
					}
					var placeHolderId = Ext.id();
					em.setStyle({
						position : 'relative',
						display  : 'block'
					});
					em.createChild({
						tag : 'div',
						id  : placeHolderId
					});
					this.swfupload = new SWFUpload(Ext.apply(this.setting, {
						button_width		  : em.getWidth(),
						button_height		 : em.getHeight(),
						button_placeholder_id : placeHolderId
					}));
					this.swfupload.uploadStopped = false;
					Ext.get(this.swfupload.movieName).setStyle({
						position : 'absolute',
						top : 0,
						left : 0
					});
				}
			};

			this.callParent();
			scope : this;
			delay : 100;
		},
		onQueued : function(file) {
			var stats = this.getStats();
			var label = Ext.getCmp("queue_id");
			label.setText(label.text = "队列中文件个数"+":" + stats.files_queued);
			var f = Ext.create("FileModel", {
				id : file.id,
				name : file.name,
				type : file.type,
				size : file.size,
				filestatus : file.filestatus,
				percent : 0
			});
			Ext.data.StoreManager.lookup("fileItems").add(f);
		},
		formatFileSize  : function(_v, celmeta, record) {
			return Ext.util.Format.fileSize(_v);
		},
		formatFileState : function(n) {// 文件状态
			//alert("测试数据：n：==="+n);
			switch (n) {
				case SWFUpload.FILE_STATUS.QUEUED :
					return '已加入队列';
					break;
				case SWFUpload.FILE_STATUS.IN_PROGRESS :
					return '正在上传';
					break;
				case SWFUpload.FILE_STATUS.ERROR :
					return '<div style="color:red;">'+'上传失败'+'</div>';
					break;
				case SWFUpload.FILE_STATUS.COMPLETE :
					return '上传成功';
					break;
				case SWFUpload.FILE_STATUS.CANCELLED :
					return '取消上传';
					break;
				default :
					return n;
			}
		},
		formatDelBtn : function(v) {
			return "<a href='javascript:void(0);' id='" + v
					+ "' class='swf-delete' ext:qtip='移除该文件'>移除</a>";
		},
		linkBtnEvent : function() {
			$(".swf-delete").on('click', {
				swf : this.swfupload
			}, function(e) {
				var ds = Ext.data.StoreManager.lookup("fileItems");
				for (var i = 0; i < ds.getCount(); i++) {
					var rec = ds.getAt(i);
					if (rec.get('id') == e.target.id) {
						ds.remove(rec);
						e.data.swf.cancelUpload(e.target.id, false);
					}
				}
			});
		},
		onFileError	 : function(file, errorCode, msg) {
			switch (errorCode) {
				case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED :
					msg = '待上传文件列表数量超限，不能选择！';
					break;
				case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT :
					msg = '文件太大，不能选择！文件大小不能超过' + this.settings.file_size_limit;
					break;
				case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE :
					msg = '该文件大小为0，不能选择！';
					break;
				case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE :
					msg = '该文件类型不可以上传！';
					break;
				case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED :
					msg = "上传已经停止";
					break;
				case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED :
					msg = "所有文件都已清空！";
					break;
				default :
					msg = "未知错误！";
					break;
			}
			Ext.Msg.show({
				title   : '提示',
				msg	 : msg,
				width   : 280,
				icon	: Ext.Msg.WARNING,
				buttons : Ext.Msg.OK
			});
		},
		uploadProgress  : function(file, bytesComplete, totalBytes) { // 处理进度条
			var ds = Ext.data.StoreManager.lookup("fileItems");
			for (var i = 0; i < ds.getCount(); i++) {
				var record = ds.getAt(i);
				if (record.get('id') == file.id) {
					record.set('percent', file.percentUploaded);
					record.set('filestatus', file.filestatus);
					record.commit();
				}
			}
			// 更新进度条
			var pb = Ext.getCmp("progressBar");
			pb.updateProgress(file.percentUploaded / 100, SWFUpload.speed.formatPercent(file.percentUploaded), true);
			// 更新当前速度
			var speed = Ext.getCmp("currentSpeed");
			var speedNum = Math.ceil(file.averageSpeed / 8 / 1024);
			var unit = speedNum / 1024 < 0 ? "KB/s" : "MB/s";
			var speedValue = speedNum / 1024 < 0 ? speedNum : speedNum / 1024;
			speedValue = Math.ceil(speedValue);
			speed.setText("平均速度"+":" + speedValue + unit);
			// 更新剩余时间
			var timeRemaining = Ext.getCmp("timeRemaining");
			timeRemaining.setText("估计剩余时间"+":" + SWFUpload.speed.formatTime(file.timeRemaining));
		},
		uploadComplete : function(file) {
			var me = this;
			var store = Ext.data.StoreManager.lookup("fileItems");
			model = store.getById(file.id);
			model.set("filestatus", file.filestatus);
			model.commit();
			var stats = this.getStats();
			if(stats != undefined && stats != null && stats != '' ){
				var label = Ext.getCmp("queue_id");
				label.setText(label.text = "队列中文件个数"+":" + stats.files_queued);
				if (this.customSettings.scope_handler.alldone && this.getStats().files_queued === 0) {
					var uploadfiles = store.data.items[0].data.name;
					var i = 1;
					for (; i < store.data.items.length; i++) {
						uploadfiles = uploadfiles + ';' + store.data.items[i].data.name;
					}
				}
				this.customSettings.scope_handler.continuous;

				if (stats.files_queued == '0'){
					setTimeout("wait()", 500);
				}
				
				return this.customSettings.scope_handler.continuous;
			}
		},
		alldoneFun : function(jsonObj) {}
	});
	
	new Ext.form.Panel({
		width : 600,
		height : 350,
		layout : 'fit',
		modal : true,
		renderTo : 'uploadPanel',
		items : [ {
			xtype : 'fileuploadPanel',
			border : false,
			fileSize : 1024*4000,//限制文件大小单位是字节
			uploadUrl : "${ctx}/saveYJFuJian;jsessionid=${JSESSIONID}",//提交的action路径
			flashUrl : '${upload}/swfupload/swfupload.swf',//swf文件路径
			filePostName : 'uploads', //后台接收参数
			fileTypes : '*.*',//可上传文件类型
			alldone : true,
			uploadSuccess : function(file, server_data) { // json string 
				jsonObj = Ext.decode(server_data); // json object
				var result = jsonObj.resultcode;
				var msg = jsonObj.msg;
				parent.refFuJian($("#yjId").val());
			}
		}]
	});
})
</script>