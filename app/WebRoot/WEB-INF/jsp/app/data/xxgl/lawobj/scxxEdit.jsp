<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>新建/编辑三产信息</title>
		<script type="text/javascript" src="${app}/js.js"></script>
		<script type="text/javascript" src="${app}/data.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery }/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery }/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery }/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui }/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${easyui }/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox }/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui }/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui }/themes/default/easyui.css">
		<link href="${app }/hnjz.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${colorbox }/colorbox.css">
		<script type="text/javascript" src="${common}/All.js"></script>
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
		<link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
		<style>
        .basicinfoTable,.basicinfoTable td{ border: 1px solid #d4d4d4;}
      </style>
	</head>

	<body style="padding-bottom: 70px;">
		<div class="h1_1" id="divTitle"><c:if test="${lawobj.id == null }">新建</c:if><c:if test="${lawobj.id != null }">编辑</c:if>三产信息</div>
		<form id="queryForm" action="" method="post">
			<input type="hidden" id="lawobjId" name="id" value="${lawobj.id }"/>
			<input type="hidden" id="lawobjType" name="lawobjtype" value="06"/>
			<input type="hidden" id="jsxmid" name="jsxmid" value="${jsxmForm.id }"/>
			<div id="condition">
				${innerHtml }
			</div>
			<div id="fsaq" style="width:100%;height:20px;text-align:right;padding-top:10px;font-size:16px;">
			<a id="fsaqxx" class="btslink" onclick="add()">辐射安全信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
			<c:if test="${ismobile != 'Y' }">
				<div class="panel-header" style="margin-top:10px;">
					<div class="panel-title">
						附件信息
					</div>
				</div>
				<table width="100%" height="100%" border="0" cellpadding="0"
					cellspacing="0" class="formtable">
					<tr>
						<th width="150" bgcolor="#edfaff" align="right">
							附件类型：
						</th>
						<td width="200">
							<input id="fjlx" class="i-text" type="text" value=""/>
						</td>
						<td>
							<a id="fileupload" class="btslink">上传附件</a>
						</td>
					</tr>
				</table>
				<div class="divContainer" id="infectlist">
					<table id="data" fit="true"></table>
				</div>
			</c:if>
			<div class="rb_btn_fix" id="bottomBtn">
				<input type="submit" id="query" class="queryBlue" value="保   存"/>
				<input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
			</div>
		</form>
		<iframe name="download" id="download" src="" style="display: none"></iframe>
	</body>
</html>

<script language="JavaScript">

$(document).ready(function(){
	$.ajaxSetup({cache:false});
	${innerJs }

	$("#sc_wghzrr").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?all=true&notShowZj=false&methodname=setUserValue&multi=true"});
	$("#fjlx").combobox({
		url:'fjlxList.json?enumName=SC',
		valueField:'id',
		textField:'name'
	});
	
	//设置建设项目转过来的属性值
	<c:if test="${jsxmForm!=null }">
	$("#sc_dwmc").val('${jsxmForm.dwmc }');
	$("#sc_dz").val('${jsxmForm.address }');
	$("#sc_fddbr").val('${jsxmForm.fddbr }');
	$("#sc_fddbrlxdh").val('${jsxmForm.fddbrlxdh }');
	$("#sc_hbfzr").val('${jsxmForm.hbfzr }');
	$("#sc_hbfzrlxdh").val('${jsxmForm.hbfzrlxdh }');
	$("#sc_ssxzq").combotree('setValue','${jsxmForm.regionid }');
	</c:if>
	
	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
		   var $selectedvalue = $("input[id='sc_qysczt']:checked").val();
		   if ($selectedvalue == 'Y') {
				$("#season").hide();
				$("#sc_qysczt").val("Y");
		   }
		   else {
				$("#season").show();
		   }
	});
	var h = $(window).height() - $("#bottomBtn").outerHeight() - $("#condition").outerHeight() - $("#divTitle").outerHeight();
	$("#infectlist").height(h<150?150:h);
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	
	//表单校验
    $("#queryForm").validate({
		errorClass: "error",
		submitHandler:function(form){
			if($("#queryForm").form("validate")){
				$(form).ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"saveOrUpdateLawobj.json",
					success: function(data){
						if(data.state){
							/*$.messager.alert("保存三产信息:", data.msg, "info", function () {
							    //self.close();
							    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						        parent.layer.close(index);
								Android.close(data.id, data.name);
                                 });*/
							var index=layer.alert(data.msg,{
						     	title:'保存三产信息:',
						        icon:1,
						        shadeClose:true,
						     },
						     function(){
						        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						        parent.layer.close(index);
						        Android.close(data.id, data.name);
						      });
						}else{
							$.messager.alert('保存三产信息:',data.msg);
						}
					}
				});
			}
		}
	});
  	//附件列表
	$('#data').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		fitColumns:true,
		url:'queryFileList.json',
		queryParams:{pid:$("input[name='id']").val()},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:20},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:50},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:15},
			{field:'operate',title:'操作', align:'center', halign:'center',width:15}		
		]]
	});
  
    $("#fileupload").click(function(){
		var id = $("input[name='id']").val();
		var fjlx = $("#fjlx").combobox('getValue');
		if(fjlx==null || fjlx == ''){
			alert("请选择附件类型！");
			return;
		}
		if(id!=null && id!=''){
			$.colorbox({
				iframe:true, width:"610", height:"380",
				href:'uploadPage.htm?pid='+id+'&fileType='+fjlx+'&path=XXGL&tableId=data'
			});
		}else{
			if($("#queryForm").form("validate")){
				/*$.messager.confirm('操作确认', '上传附件需要保存当前三产信息，是否继续？', function(r){
					if(r){
						$("#queryForm").ajaxSubmit({
							type:"post",
							dataType:"json",
							url:"saveOrUpdateLawobj.json",
							success: function(data){
								if(data.state){
									$("input[name='id']").val(data.id);
									$.colorbox({
										iframe:true, width:"610", height:"380",
										href:'uploadPage.htm?pid='+data.id+'&fileType='+fjlx+'&path=XXGL&tableId=data'
									});
								}else{
									$.messager.alert('保存三产信息:',data.msg);
								}
							}
						});
					}
				});*/
				/*var index=layer.confirm('上传附件需要保存当前三产信息，是否继续？', {
			     	icon: 3, 
			        title:'操作确认'
			     }, function(index){
			    	 $("#queryForm").ajaxSubmit({
							type:"post",
							dataType:"json",
							url:"saveOrUpdateLawobj.json",
							success: function(data){
								if(data.state){
									$("input[name='id']").val(data.id);
									$.colorbox({
										iframe:true, width:"610", height:"380",
										href:'uploadPage.htm?pid='+data.id+'&fileType='+fjlx+'&path=XXGL&tableId=data'
									});
								}else{
									$.messager.alert('保存三产信息:',data.msg);
									
								}
							}
						});
			        layer.close(index);
			     },function(index){
			        //alert('取消按钮的回调函数');
			        layer.close(index);
			     });*/
				$("#queryForm").ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"saveOrUpdateLawobj.json",
					success: function(data){
						if(data.state){
							$("input[name='id']").val(data.id);
							$.colorbox({
								iframe:true, width:"610", height:"380",
								href:'uploadPage.htm?pid='+data.id+'&fileType='+fjlx+'&path=XXGL&tableId=data'
							});
						}else{
							$.messager.alert('保存三产信息:',data.msg);
							
						}
					}
				});
			}
		}
	});
});

/**
 * 公用的上传文件之后的刷新grid方法
 * @param tableId tablegrid的id
 */
function reload(tableId,pid){
	var id = "#" + tableId;
	$(id).datagrid("reload",{pid:pid});
	jQuery.colorbox.close();
}

//限制单位地址长度
function textMaxLen(i){
 	 var conMaxLen=parseInt(i.getAttribute("maxlength"));
 	 if(i.value.length>=conMaxLen){
 		i.value=i.value.substring(0,conMaxLen)
 	 }
}

/**
 * 公用的删除文件方法 删除grid中的文件
 * @param obj grid的一行数据
 
function deletefile1(obj){
	$.messager.confirm('操作', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
				url: "removeFile.json?id="+obj.id,
				success:function(data){
					alert(data.msg);
					$('#data').datagrid('reload',{pid:$("input[name='id']").val()});
				}
			});
		}
	});
}*/
//附件删除
function deletefile1(obj) {
	var index=layer.confirm('确定删除吗？', {
     	icon: 3, 
        title:'删除任务'
     }, function(index){
    	 $.ajax( {
				url : "removeFile.json?id="+obj.id,
				success : function(data) {
					//refresh();
					$('#data').datagrid('reload',{pid:$("input[name='id']").val()});
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}
/**
 * 选择人员后回填数据
 */
function setUserValue(id,name){
	//$("#sc_wghzrr").linkbox("setValue", {id:id,name:name});
	$("#sc_wghzrr").val(name);
	$("#sc_wghzrrid").val(id);
	jQuery().colorbox.close();
}

/**
 * 填完经度后回填数据
 */
function setJdValue(jd){
	$("#sc_jd").val(jd);
	jQuery().colorbox.close();
}

/**
 * 填完纬度后回填数据
 */
function setWdValue(wd){
	$("#sc_wd").val(wd);
	jQuery().colorbox.close();
}

//企业生产状态     季节随选择全年性生产或季节性生产隐藏和显示
$(document).ready(function() {
	$(".radioItem").change(
		function() {
			var $selectedvalue = $("input[id='sc_qysczt']:checked").val();
			if ($selectedvalue == 'Y') {
				 $("#season").hide();
				 $("#sc_qysczt").val("Y");
			}
			else {
				 $("#season").show();
			}
	});
	$("input[id='sc_qysczt']").blur(function(){
		var yue="";
		$("#season input[id='sc_qysczt']").each(function(){
			if($(this).prop("checked")){
				yue+=$(this).attr("value")+",";
			}
		});
		var $selectedvalue = $("input[id='sc_qysczt']:checked").val();
		var scyf=$selectedvalue+' '+yue;
		if($.trim(scyf)=='N'){
			var index=layer.alert('请选择允许生产的月份！！！',{
		     	title:'信息提示',
		        icon:1,
		        shadeClose:true,
		     },
		     function(){
		        layer.close(index);
		     });
		}
	});
});
//添加辐射安全基本信息
function add() {
	if($("#queryForm").form("validate")){
		
				$("#queryForm").ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"saveOrUpdateLawobj.json",
					success: function(data){
						$("#lawobjId").val(data.id);
						$.fn.colorbox({
							iframe : true,
							width : "800",
							height : "450",
							href : "fsaqxxEdit.htm?lawobjId="+data.id+"&lawobjTypeId="+$("#lawobjType").val()
						});
					}
				});
		
	}
}
//关闭弹出框
function ref() {
	jQuery().colorbox.close();
	//$('#content1').datagrid('reload');;
}
</script>