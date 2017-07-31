var All = {};
var popWin = null;
var winCount = 0;
var winName = "popWin";
var click_count = 0;

// 取浏览器名称
function getOs(){
	var sObject = "";
	if(navigator.userAgent.indexOf("MSIE")>0) {
		return "MSIE";
	} else if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){
		return "Firefox";
	} else if(isSafari=navigator.userAgent.indexOf("Chrome")>0) {
		return "Chrome";
	} else if(isSafari=navigator.userAgent.indexOf("Safari")>0) {
		return "Safari";
	} else if(isCamino=navigator.userAgent.indexOf("Camino")>0){
		return "Camino";
	} else if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){
		return "Gecko";
	}
}

 

/**
 * 打开窗口
 * 
 */
 /**
	 * 不使用ext All.showWindow = function(src, title, width, height, modal) { if
	 * (width == null){ width = document.body.scrollWidth - 200; } if (height ==
	 * null){ height = document.body.scrollHeight - 50; } if (modal == null){
	 * modal = true; } var win = new Ext.create('Ext.window.Window', { title :
	 * title, width : width, height : height, layout : 'fit', modal : modal,
	 * draggable : true, html:"<iframe scrolling='auto' frameborder='0'
	 * width='100%' height='100%' src='" + src + "'></iframe>" }); win.show(); };
	 */
// 打开浏览器对话框
All.ShowModalWin = function(winURL, winName, winWidth, winHeight) {
	
	if (winWidth == null){
		winWidth = screen.width * 0.8;
	}
	if (winHeight == null){
		winHeight = screen.height * 0.8;
	}
	x = (screen.width - winWidth) / 2;
	y = (screen.height - winHeight) / 4;
	// 解决ie8打开窗口不刷新的问题，添加随机数参数
	if (winURL.indexOf("?") == -1){
		winURL += "?Rnd="+Math.random();
	} else {
		winURL += "&Rnd="+Math.random();
	}
	var os = getOs();
	if (os == "Chrome"){
		window.open(winURL, "弹出窗口", "width=" + winWidth + ", height=" + winHeight + ",top=" + y + ",left=" + x + ",toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=no, status=no, alwaysRaised=yes, depended=yes");
	} else {
		try {
			return window.showModalDialog(winURL,window,"dialogTop:" + y + ";dialogLeft:" + x + ";dialogWidth:"+winWidth+"px;dialogHeight:"+winHeight+"px;help:no;center:yes;resizable:no;status:no;");	
		} catch (e) {
			alert('您可能打开了"弹出窗口阻止程序",请按照以下步骤关闭：\r\n打开ie菜单栏，点击"工具"选项，点击"弹出窗口组织程序"，在弹出的子菜单中点击"关闭弹出窗口阻止程序"。');
		}
	}
};

// 打开一个浏览器窗口
All.ShowBrowserWin = function(winURL, winName, winWidth, winHeight) {
	if (winWidth == null){
		winWidth = screen.width * 0.8;
	}
	if (winHeight == null){
		winHeight = screen.height * 0.8;
	}
	x = (screen.width - winWidth) / 2;
	y = (screen.height - winHeight) / 4;
	popWin = window.open(winURL,winName, "top=" + y + ",left=" + x + ",width=" + winWidth + ",height=" + winHeight + ",resizable=no,scrollbars=yes");
};

/**
 * 調用方式如：if(notNull('enterpriseEdit')==false)return;
 * 1.在需要加非空驗證的input標簽上直接添加例如notNull=標簽名;
 * 2.checkbox類型的input只需要在首個input中加notNull=標簽名; 3.select標簽中notNull=標簽名;
 * 
 * @param id
 *			組件的父級組件id，例如table、form的id
 * @returns {Boolean}
 */
function notNull(id){
	var data = jQuery("#"+id+" input[notNull]");
	data.each(function(){
		if($(this).val()==""){
			$(this).focus();
			return false;
		}
		if ($(this).attr("type")=="checkbox"){
			var name = $(this).attr("name");
			if ($("input:checked[:checkbox][name='"+name+"']").length==0){
				return false;
			}
		}
	});
	var selectData = jQuery("#"+id+" select[notNull]");
	selectData.each(function(){
		if($(this).val()==""){
			$(this).focus();
			return false;
		}
	});
	return true;
}

/**
 * 在需要加數字驗證的input標簽上直接添加例如isDigit=true，調用方式如：isDigit('enterpriseEdit');
 * 
 * @param id
 *			組件的父級組件id，例如table、form的id
 */
function isDigit(id){
	var data = jQuery("#"+id+" input[isDigit]");
	data.each(function(){
		$(this).bind('keypress', function (evt) {
			// 取得鍵入的實際字符。
			// 這裏用 keyCode 替換 charCode，同样可以獲得想要的值(参見前文)
			var char = String.fromCharCode(evt.keyCode);
			// 如果不是數字，就不允許輸入
			if (!/^\d*\.{0,1}\d{0,2}$/.test(char)) {
				evt.preventDefault();
			}
		});
		$(this).bind('afterpaste', function () {
			$(this).val($(this).val().replace(/[^\d\.]/g,''));
		});
		$(this).bind('keyup', function () {
			$(this).val($(this).val().replace(/[^\d\.]/g,''));
		});
	})
}

/**
 * 公用的上传文件之后的刷新grid方法
 * 
 * @param tableId
 *			tablegrid的id
 */
function reload(tableId,pid,fileType){
	var id = "#" + tableId;
	$(id).datagrid("reload",{pid:pid,fileType:fileType});
	jQuery.colorbox.close();
}

/**
 * 公用的删除文件方法 删除grid中的文件
 * 
 * @param obj
 *			grid的一行数据
 */
function deletefile1(obj){
	$.messager.confirm('操作', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
				url: "removeFile.json?id="+obj.id,
				success:function(data){
					alert(data.msg);
					$('#'+obj.tableId).datagrid('reload',{pid:obj.pid});
				}
			});
		}
	});
}

/**
 * 公用的下载文件方法 下载grid中的文件
 * 
 * @param obj
 *			grid的一行数据
 */
function download1(obj){
	$('#download').attr('src','downloadFile?id='+obj.id);
}

function imgview(obj) {
    var value = obj.id;
    var arr = new Array();
    arr = value.split(",");
    parent.parent.layer.open({
        type: 2,
//        title: '附件查看',
        area: [($(parent.parent.window).width()-100)+'px', ($(parent.parent.window).height()-35)+'px'],
        content: 'imgView.htm?id=' + arr[0]
    });
}

function review(obj){
	var value=obj.id;
	var arr = new Array();
	arr = value.split(",");
	if(arr[1]<=4194304){
	All.ShowModalWin('preview.htm?id='+arr[0], '预览', 900, 600);
	}else{
		alert("文件太大，请下载预览。");
	}
}



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

function refresh(){
	var p = $('#data').datagrid('getPager');
	$(p).pagination("select", 1);
}

function getCurTime(){
	var now = new Date();
	var year = now.getFullYear(); // 年
	var month = now.getMonth() + 1; // 月
	var day = now.getDate(); // 日

	var hh = now.getHours(); // 时
	var mm = now.getMinutes(); // 分
	var ss = now.getSeconds(); // 秒

	var clock = year + "-";

	if(month < 10) {
		clock += "0"
	}

	clock += month + "-";

	if(day < 10) {
		clock += "0"
	}

	clock += day + " ";

	if(hh < 10) {
		clock += "0"
	}

	clock += hh + ":";
	
	if (mm < 10) {
		clock += '0'
	}
	
	clock += mm;
	
	if (ss < 10) {
		clock += '0'
	}
	
	clock += ":" + ss;
	return clock; 
}

function getPreMonth(date) {
	var arr = date.split('-');
	var year = arr[0]; //获取当前日期的年份
	var month = arr[1]; //获取当前日期的月份
	var day = arr[2]; //获取当前日期的日
	var days = new Date(year, month, 0);
	days = days.getDate(); //获取当前日期中月的天数
	var year2 = year;
	var month2 = parseInt(month) - 1;
	if (month2 == 0) {
		year2 = parseInt(year2) - 1;
		month2 = 12;
	}
	var day2 = day;
	var days2 = new Date(year2, month2, 0);
	days2 = days2.getDate();
	if (day2 > days2) {
		day2 = days2;
	}
	if (month2 < 10) {
		month2 = '0' + month2;
	}
	var t2 = year2 + '-' + month2 + '-' + day2;
	return t2;
}

function dateCompare(startdate, enddate){
	var arr=startdate.split("-");
	var starttime=new Date(arr[0],arr[1],arr[2]);
	var starttimes=starttime.getTime();

	var arrs = enddate.split("-");
	var lktime = new Date(arrs[0],arrs[1],arrs[2]);
	var lktimes = lktime.getTime();

	if(starttimes >= lktimes){
		return false;
	}
	else {
		return true;
	}
}


$(document).ready(function() {
	$('#query').click(function(){
		try{
			$('#data').datagrid('getPager')
		} catch (e){
			return;
		}
		var p = $('#data').datagrid('getPager');
		$(p).pagination({
			pageSize : 20,
			pageNumber : 1
		});
		$('#page').val("1");
		$('#pageSize').val("20");
	});
	
	
	
})
function deljsxm(){
	 $.ajax({
			url : "dellawobjf.json?ids="+ids,
			success : function(data) {
				alert("删除建设项目成功");
				refresh();
			}	  
		});
}

 window.history.forward();
 window.onbeforeunload=function(){}