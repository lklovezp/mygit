<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>任务派发-接受任务</title>
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
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
    <script type="text/javascript" src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${ztree}/js/jquery.ztree.excheck-3.5.js"></script>
    <!-- zTree 修改的样式-->
    <link href="${app}/zTreeReset.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${common}/All.js"></script>
</head>
<body>
<form id="bgForm" name="bgForm" method="post" action="commworkzxBG.json">
        <input type="hidden" id="applyId" name="applyId" value="${work.id}" />
		<input type="hidden" id="taskId" name="taskId" value="${taskId}" />
		<input type="hidden" id="fileIds" name="fileIds" value="" />
<!-- 专项行动 -->
<div id="DIV_15" class="checkup">
    <div class="mt25">
        <div class="annex_header">
        <c:if test="${sysVer != 0 }">
            <a class="b_link" onclick="downZipFile('ZXXDdata')" style="float:right;" >打包下载</a>
        </c:if>
            专项行动
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="ZXXDdata" fit="true"></table>
        </div>
    </div>
</div>
<div id="checkup_footer" class="t-c">
    <div style="padding-top: 30px;">
        <input class="queryBlue" id="bgbutton" type="button" value="报告" onclick="bg()" style=" font-size:14px;cursor: pointer;">
    </div>
</div>
</form>
<iframe name="download" id="download" src="" style="display: none"></iframe>
</body>
</html>
<script type="text/javascript">
var applyId = $('#applyId').val();

$(document).ready(function() {
    var canDelStr="N,N,N,N";
    //赋值
    $('#ZXXDdata').datagrid({
        pagination:true,//分页控件
        height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns:true,
		url:'queryFileListMulfileType.json?canDel='+canDelStr,
		queryParams:{pid:$("#applyId").val(),fileType:'ZXXDJCBG,ZXXDQTZL,ZXXDZRWYSB,ZXXDZBZL'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:100},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:500},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:100},
			{field:'operate',title:'操作', align:'center', halign:'center',width:100}
		]]
	});
});

function bg() {
	var index=layer.confirm('确定报告吗？', {
     	icon: 3, 
        title:'报告'
     }, function(index){
    	 $("#bgbutton").attr({"disabled":"disabled"});
			$.post('bg.json', 
				{
					applyId : $('#applyId').val(),
					taskId : $('#taskId').val()
				}, 
				function(data) {
				    $("#bgbutton").removeAttr("disabled");//将按钮可用
				    if (data.state) {
				    	var index=layer.alert('报告成功！',{
				         	title:'报告',
				            icon:1,
				            shadeClose:true,
				         },
				         function(){
				        	 parent.toDbList();
				            layer.close(index);
				         }
				         );

					}
			}, 'json');	
        layer.close(index);
     },function(index){
        layer.close(index);
     }
     );
}

function download2(id){
	$('#download').attr('src','downloadFile?id='+id);
}
function deletefile2(id){
	$.messager.confirm('操作', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
				url: "removeFile.json?id="+id,
				success:function(data){
					alert(data.msg);
				}
			});
		}
	});
}

//打包下载
function downZipFile(tableId){
    var data = $('#'+tableId).datagrid("getData");
	var ids = "";
	for(var i = 0; i < data.rows.length; i++){
		if (i > 0){
			ids += ",";
		}
		ids += data.rows[i].id;
	}
	$('#download').attr('src','downZipFile?id='+ids);
}

</script>