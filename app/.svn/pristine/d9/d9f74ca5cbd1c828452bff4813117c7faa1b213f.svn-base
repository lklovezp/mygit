<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>任务接受</title>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
    
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
<div class="checkup" style="padding-bottom: 70px;">
    <h1 id="checkup_header">接受任务</h1>
    <form id="myform" name="myform" method="post" action="js.json">
	<input type="hidden" id="applyId" name="applyId" value="${work.id}" />
	<input type="hidden" id="lx" name="lx" value="${lx}" />
	<input type="hidden" id="sy" name="sy" value="${sy}" />
	<input type="hidden" id="taskId" name="taskId" value="${taskId}" />
	<input type="hidden" id="xfdjId" name="xfdjId" value="${work.xfdjbId}" />
	<input type="hidden" id="fid" name="fid" value="${fid}" />
	<input type="hidden" id="page" name="page" value="${page}" />
	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize}" />
    <table class="dataTable" width="99%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr bgcolor="#cff1ff" height="48">
            <th colspan="6" align="left">&nbsp;&nbsp;&nbsp;&nbsp;任务信息</th>
        </tr>
        <tr  height="42">
            <td width="12%" align="center">任务名称</td>
            <td colspan="5">${work.name}</td>
        </tr>
        <tr  height="42">
            <td width="12%" align="center">主要内容</td>
            <td colspan="5">${work.workNote}</td>
        </tr>
        <tr  height="42">
            <td width="12%" align="center">任务来源</td>
            <td colspan="5">${source}</td>
        </tr>
        <tr  height="42">
            <td width="12%" align="center">任务密级</td>
            <td width="21%">${security}</td>
            <td width="12%" align="center">紧急程度</td>
            <td width="21%">${emergency}</td>
            <td width="12%" align="center">登记人</td>
            <td width="21%">${work.djrName}</td>
        </tr>
        <tr  height="42">
            <td width="12%" align="center">任务派发时间</td>
            <td width="21%"><label id=""><fmt:formatDate value="${work.startTime}" pattern="yyyy-MM-dd"/></label></td>
            <td width="12%" align="center">要求完成时间</td>
            <td width="21%"><label id=""><fmt:formatDate value="${work.endTime}" pattern="yyyy-MM-dd"/></label></td>
            <td width="12%" align="center">派发人</td>
            <td width="21%">${work.createUser.name}</td>
        </tr>
    </table>
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            <a class="b_link" style="float:right;" onclick="downZipFile()">打包下载</a>
            相关附件
        </div>
        <div class="annex_con" style="width:99.9%;height:248px;">
            <table id="XGFJdata" fit="true"></table>
        </div>
    </div>
    <!--办理人员-->
    <table class="dataTable mt25" width="100%" border="1px" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
       <c:forEach var="po" items="${lsps}">
        <tr bgcolor="#cff1ff" height="48">
            <th align="left"><span style="float: right;margin-right:15px;"><fmt:formatDate value="${po.czsj}" pattern="yyyy-MM-dd HH:mm"/></span>&nbsp;&nbsp;&nbsp;&nbsp;${po.czrName}</th>
        </tr>
        <tr height="42">
            <td style="color: #666666;"><div style="width: 100%;overflow: auto;">${po.note}</div></td>
        </tr>
	  </c:forEach>
    </table>
</div>
   <div class="rb_btn_fix">
        <input id="jsbutton" type="submit" class="queryBlue" value="接受" style=" font-size:14px;cursor: pointer;">
        <c:if test="${work.xfdjbId != '' && work.xfdjbId != null}">
        	<input id="pfbutton" type="button" class="queryOrange" value="信访登记表查看" style=" font-size:14px;" onclick="chakan()">
    	</c:if>
    </div>

</div>
</form>
<iframe name="download" id="download" src="" style="display: none"></iframe>
</body>
</html>
<script>
var xfdjId = $('#xfdjId').val();
$(document).ready(function() {
	//相关附件列表
	$('#XGFJdata').datagrid({
	    pagination:false,//分页控件
	    height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns : true,
		url:'queryFileListMulfileType.json?canDel=N,N&rows=10000',
		queryParams:{pid:$("#applyId").val(),fileType:'RWGLPFFJ,RWGLZPFJ'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:50},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:500},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:100},
			{field:'operate',title:'操作', align:'center', halign:'center',width:100}
		]]
	});
$('#myform').submit(function(){ 
	 $("#jsbutton").attr({"disabled":"disabled"});
     $('#myform').ajaxSubmit({  
          success : function(data) {
			  $("#jsbutton").removeAttr("disabled");//将按钮可用
			  if (data.state) {
				  var index=layer.alert(data.msg,{
				     	title:'接受',
				        icon:1,
				        shadeClose:true,
				     },
				     function(){
				    		var lx=$('#lx').val();
				    		var sy=$('#sy').val();
				    		if($('#sy').val()=='1'){
				    	  	  parent.closeLayerIframe(); 
				    	     }else{
				    	    	 if(lx=="1"){
				    	    		 parent.parent.closeLayer();	
				    	    		}else if(lx=="2"){
				    	    			parent.closeLayer();
				    	    		}else if(lx=="3"){
				    	    			parent.closeLayer();
				    	    		}else if(lx=="4"){
				    	    			 parent.closeLayer();
				    	    		}
				    	     }
				        //window.location.href="javascript:history.go(-1);";
				       // window.location.href="dbrwList.htm?title=待办任务";
				       // window.location.href="xftsdbRwList.htm?title=信访投诉";
				        layer.close(index);
				     }
				     );
			  }
		  }
	 });
    return false;  
});
});

//查看
function chakan() {
	var href = 'xfdjInfo.htm?id=' + xfdjId; 
  	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	parent.layerIframe(2,href,"信访登记表基本信息",width,height);
	//All.ShowModalWin("xfdjInfo.htm?id=" + curObj.id, "", 1100, 700);
	//refresh();
}

//打包下载
function downZipFile(){
var data = $('#XGFJdata').datagrid("getData");
var ids = "";
for(var i = 0; i < data.rows.length; i++){
	if (i > 0){
		ids += ",";
	}
	ids += data.rows[i].id;
}
if(ids!=null && ids!=''){
    $('#download').attr('src','downZipFile?id='+ids);
}else{
    alert("没有附件！");
}
}

</script>
