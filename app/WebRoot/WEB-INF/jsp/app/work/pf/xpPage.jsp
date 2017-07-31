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
    <script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
    <script type="text/javascript" src="${common}/All.js"></script>
    <!--执法检查-->
    <link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="checkup">
    <h1 id="checkup_header">任务下派</h1>
    <form id="myform" name="myform" method="post" action="xp.json">
	<input type="hidden" id="applyId" name="applyId" value="${work.id}" />
	<input type="hidden" id="taskId" name="taskId" value="${taskId}" />
	<input type="hidden" id="lx" name="lx" value="${lx}" />
	<input type="hidden" id="sy" name="sy" value="${sy}" />
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
        <tr>
        	<c:if test="${isXfrw==0}">
			<td align="center">
				<span class="red">*</span>
				<c:if test="${isZd==0}">
					<a href="#" class="task_zflx" onclick='javascript:selectxfr()' id="selectxfr" >选择接受人</a>
				</c:if>
				<c:if test="${isZd!=0}">
					<a href="#" class="task_zflx" onclick='javascript:selectjsr()' id="selectjsr">选择接受人</a>
				</c:if>
			</td>
		    <td align="left" colspan="5">
				<input type="hidden" id="jsrIds" name="jsrIds" value="${work.jsrIds}"/>
				<input class="form-text easyui-validatebox" data-options="required:true" type="text" id="jsrNames" readonly="readonly" value="${work.jsrNames}" />
			</td>
			</c:if>
			<c:if test="${isRcrw==0}">
			<td align="center">
				<span class="red">*</span>
				<c:if test="${isZd==0}">
					<a href="#" class="task_zflx" onclick='javascript:selectrcr()' id="selectrcr" >选择接受人</a>
				</c:if>
				<c:if test="${isZd!=0}">
					<a href="#" class="task_zflx" onclick='javascript:selectjsr()' id="selectjsr">选择接受人</a>
				</c:if>
			</td>
		    <td align="left" colspan="5">
				<input type="hidden" id="jsrIds" name="jsrIds" value="${work.jsrIds}"/>
				<input class="form-text easyui-validatebox" data-options="required:true" type="text" id="jsrNames" readonly="readonly" value="${work.jsrNames}" />
			</td>
			</c:if>
			<c:if test="${isXfrw!=0 && isRcrw!=0}">
			<td align="center">
				<span class="red">*</span>下级单位
			</td>
		    <td align="left" colspan="5">
				<input class="y-text" id="areaId" name="areaId" type="text" />
			</td>
			</c:if>
		</tr>
		<tr>
			<td align="center">
				<span class="red">*</span>批示意见
			</td>
			<td align="left" colspan="5">
				<textarea style="width:655px;height:40px;" onKeyUp="return textMaxLen(this);" onBlur="return textMaxLen(this);" maxlength="100" title="可填100字" class="y-text easyui-validatebox" data-options="required:true" name="psyj" /></textarea>
			</td>
		</tr>
    </table>
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            <a class="b_link" style="float:right;" onclick="downZipFile()">打包下载</a>
        	<a href="#" id="XGFJfileupload" class="b_link" style="float:right;">上传附件</a>&nbsp;
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
            <th align="left">
            <span style="float: right;margin-right:15px;">
            	是否下派&nbsp;<input name="lspsCB" type="checkbox" value="${po.id}"/>
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <fmt:formatDate value="${po.czsj}" pattern="yyyy-MM-dd HH:mm"/>
            	
            </span>
            &nbsp;&nbsp;&nbsp;&nbsp;${po.czrName}
         	</th>
        </tr>
        <tr height="42">
            <td style="color: #666666;">${po.note}</td>
        </tr>
	  </c:forEach>
    </table>
</div>
<div id="checkup_footer" class="t-c">
    <div style="padding-top: 30px;">
    	<span style="color: red;">注：下派附件和历史批示可勾选进行下派。</span></br>
		<input id="xpbutton" type="submit" class="queryBlue" value="下派" >
    </div>
</div>
</form>
<iframe name="download" id="download" src="" style="display: none"></iframe>
</body>
</html>
<script type="text/javascript">
//下派附件ids
function getXPFJ_ids(){
    var checkedItems = $('#XGFJdata').datagrid('getChecked');
    var ids = [];
    $.each(checkedItems, function(index, item){
        ids.push(item.id);
    }); 
    return ids;
}
//下派历史批示ids
function getXPLSPS_ids(){
    var lspsCB="";
    $("input[name='lspsCB']:checkbox:checked").each(function(index,domEle){
       if(index>0){
		   lspsCB+=",";
	   }
	   lspsCB+=$(this).val();
    })
    return lspsCB;
}


function textMaxLen(i){
 	 var conMaxLen=parseInt(i.getAttribute("maxlength"));
 	 if(i.value.length>=conMaxLen){
 		i.value=i.value.substring(0,conMaxLen)
 	 }
}
//选择信访专员
function selectxfr(){
    var ids = $("#jsrIds").val();
    $("#selectxfr").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?all=false&id="+ids+"&notShowZj=true&methodname=setUserInfoJsr&multi=false&showExist=true&group=3"});
}

//选择日常专员
function selectrcr(){
    var ids = $("#jsrIds").val();
    $("#selectrcr").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?all=false&id="+ids+"&notShowZj=true&methodname=setUserInfoJsr&multi=false&showExist=true&group=4"});
}

//选择接受人
function selectjsr(){
    var ids = $("#jsrIds").val();
    $("#selectjsr").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?all=true&id="+ids+"&notShowZj=true&methodname=setUserInfoJsr&multi=false&showExist=true&group=0"});
}

 //设置选择接受人
function setUserInfoJsr(id,name) {
 	$("#jsrIds").val(id);
 	$("#jsrNames").val(name);
 	jQuery().colorbox.close();
 }
$(document).ready(function() {
	$('#areaId').combobox({
		height:34,
	    required:true,
		url:'queryAreaCombo.json',
		valueField:'id',
		textField:'name'
	});
	
	//相关附件列表
    $('#XGFJdata').datagrid({
        pagination:false,//分页控件
        height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns : true,
		url:'queryFileListMulfileType.json?canDel=N,N,Y&rows=10000',
		queryParams:{pid:$("#applyId").val(),fileType:'RWGLPFFJ,RWGLZPFJ,RWGLXPFJ',tableId:'XGFJdata'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:50},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:500},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:100},
			{field:'operate',title:'操作', align:'center', halign:'center',width:100},
			{field:'ck',title:'是否下派',checkbox:true}
		]],
		singleSelect: false,
		selectOnCheck: true,
        checkOnSelect: false,
        onLoadSuccess:function(data){
        	//$('#XGFJdata').datagrid('uncheckAll');
	        if(data){
	            $.each(data.rows, function(index, item){
	                if(item.checked){
	                    $('#XGFJdata').datagrid('checkRow', index);
	                }
	            });
	        }
	    }

	});
	//相关附件上传
	$("#XGFJfileupload").click(function(){
		var id = $("#applyId").val();
		var fileType = 'RWGLXPFJ';//下派附件上传
		if(id!=null && id!=''){
		    $("#XGFJfileupload").colorbox({
				iframe:true, width:"610", height:"380",
				href:'uploadPage.htm?pid='+id+'&fileType='+fileType+'&path=WORK&tableId=XGFJdata'
			});
		}
	});
	
	$("#myform").validate( {
		errorClass : "error",
		submitHandler : function(form) {
			if ($("#myform").form("validate")){
			    $("#xpbutton").attr({"disabled":"disabled"});
			    var xpfjIds = getXPFJ_ids();
			    var xplspsIds = getXPLSPS_ids();
			    var ids = $("#jsrIds").val();
				$(form).ajaxSubmit( {
					type : "post",
					url : "xp.json?1=1&xpfjIds="+xpfjIds+"&xplspsIds="+xplspsIds+"&jsrId="+ids,
					success : function(data) {
					    $("#xpbutton").removeAttr("disabled");//将按钮可用
					    if (data.state) {
					    	var tishi=layer.alert(data.msg,{
						     	title:'信息提示',
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
						    	    		 parent.closeLayer();	
						    	    	}else if(lx=="2"){
						    	    		parent.closeLayer();
						    	    	}else if(lx=="3"){
						    	    		parent.closeLayer();
						    	    	}else if(lx=="4"){
						    	    		parent.closeLayer();
						    	    	}
						    	     }
						        layer.close(tishi);
						     }
						     );
						}
					}
				});
			}
		}
	});
	
});


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

/////附件操作/////
/**
 * 公用的上传文件之后的刷新grid方法
 * @param tableId tablegrid的id
 */
function reload(tableId,pid,fileType){
	var id = "#" + tableId;
	$(id).datagrid("reload");
	jQuery.colorbox.close();
}
/**
 * 公用的删除文件方法 删除grid中的文件
 * @param obj grid的一行数据
 */
function deletefile1(obj){
	var index=layer.confirm('确定要删除吗？', {
     	icon: 3, 
        title:'删除'
     }, function(index){
    	 $.ajax( {
				url : "removeFile.json?id="+obj.id,
				success : function(data) {
					if(data.state){
						var tishi=layer.alert(data.msg,{
					     	title:'信息提示',
					        icon:1,
					        shadeClose:true,
					     },
					     function(){
					    	 var reloadtable= $(obj).attr("reloadtable");//自定义属性采用此方式获得 
							$('#'+reloadtable).datagrid('reload');
					        layer.close(tishi);
					     }
					     );
						
					}else{
						var tishi=layer.alert(data.msg,{
					     	title:'信息提示',
					        icon:1,
					        shadeClose:true,
					     },
					     function(){
					        layer.close(tishi);
					     }
					     );
					}
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}

</script>