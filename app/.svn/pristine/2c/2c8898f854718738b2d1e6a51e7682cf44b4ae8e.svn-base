<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css">
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox2.css">
		<script type="text/javascript" src="${common}/All.js"></script>
		<script type="text/javascript" src="${jquery}/json2.js"></script>
	</HEAD>

	<body>
	<div class="h1_1" id="divTitle">新建执法文件</div>
		<form id="queryForm" action="" method="post">
		<input type="hidden" name="data">
		<input type="hidden" name="pid" value="${lawdocdir.id }">
			<table width="100%" border="0" align="center" style="padding-left:10px;padding-right:20px;" cellpadding="0"
				cellspacing="0">
				<tr>
					<td>
						<h1>文件目录：${lawdocdir.name }</h1>
					</td>
					<td align="right">
					    <input id="fileupload" type="button" value="增加"  class="bTn blue" />
					</td>
				</tr>
			</table><br>
			<%--<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formtable">
				<tr>
					<td align="center"  width="10%">
						序号
					</td>
					<td align="center" width="40%">
						标题
					</td>
					<td align="center" width="40%">
						关键词
					</td>
					<td align="center" width="5%">
						排序
					</td>
					<td align="center" width="10%">
						操作
					</td>
				</tr>
				<c:forEach items="${listDoc }" var="lawdoc" varStatus="sta">
				<tr name="trone">
					<td align="center">
						${sta.count }
					</td>
					<td align="center">
						<input type="hidden" name="id" value="${lawdoc.id }"/>
						${lawdoc.name }
					</td>
					<td align="center">
						<input class="i-text" style="width:100%;" type="text" name="keywords" value="${lawdoc.keywords }"/>
					</td>
					<td align="center">
						<input class="i-text easyui-numberbox" style="width:100%;" precision="0" min="0" type="text" required="true" name="orderby" value="${maxorder+sta.index }"/>
					</td>
					<td align="center">
						<a id="xinjian" class="btslink" onclick="deltr(this)">删除</a>&nbsp;
					</td>
				</tr>
				</c:forEach>
					<tr>
						<td colspan="6" align="center">
							<span class="btn btn-ok"> <input id="query" type="submit"
									value="保存"> </span>
							&nbsp;
							<a id="J-from-reset" class="btslink">重置</a>&nbsp;
							
						</td>
					</tr>
			</table>
			--%><div class="divContainer" id="infectlist">
				<table id="data" fit="true"></table>
			</div>
			<div class="bottomBtn" id="bottomBtn">
				<input type="submit" id="query" class="queryBlue" value="保   存"/> 
			</div>
		</form>
	</body>
</html>

<script language="JavaScript">
var dataG;
$(document).ready(function(){
	$.ajaxSetup({cache:false});
	$("#fileupload").colorbox({
		iframe:true, width:"610", height:"380",
		href:'uploadPage.htm?pid=${uuid}&fileType=ZFWJGLZFWJ&path=ZFWJ&tableId=data'
	});
	
	//关闭窗口删除已上传文件
	$(window).bind('beforeunload',function(){
		$.ajax({
			url: "deleteLawdoc.json?pid=${uuid}",
			success:function(data){
			}
		});
	});
	
	$("#infectlist").height($(window).height() - $("#bottomBtn").outerHeight()  - $("#divTitle").outerHeight() -65);
	$("#infectlist").width($(window).width());
	
	dataG = $('#data').datagrid({
		rownumbers: true,
		width:600,
		height:600,
		nowrap:false,
		fitColumns : true,
		singleSelect: true,
		url:'addLawdoc.json?uuid=${uuid}',
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
			var length = $('#data').datagrid('getRows').length;
			for(i=0;i<length;i++){
				$('#data').datagrid('beginEdit', i);
			}
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'name',title:'标题',editor:{type:'text'}, align:"left", halign:'center',width:100},
			{field:'keywords',title:'关键词',editor:{type:'text'}, align:"left", halign:'center',width:100},
			{field:'orderby',title:'排序', editor:{type:'text'}, align:"center", halign:'center',width:50},
			{field:'operate',title:'操作', align:"center", halign:'center',width:30,
				formatter:function(value,rowData,rowIndex){
					return '<a onclick="delrow(\''+ rowData.id +'\')">删除</a> ';
				}
			}
		]],
		onClickRow:function(rowIndex){
			var length = $('#data').datagrid('getRows').length;
			for(i=0;i<length;i++){
				$('#data').datagrid('beginEdit', i);
			}
		}
	});
});

//表单校验
$("#queryForm").validate({
	errorClass: "error",
	submitHandler:function(form){
		if($("#queryForm").form("validate")){
			dataG.datagrid('acceptChanges');
			var gridData = dataG.datagrid('getData');
			var data = JSON.stringify(gridData);
			$("input[name='data']").val(data);
			$("#queryForm").ajaxSubmit({
				type:"post",
				dataType:"json",
				url:"saveLawdoc.json",
				success: function(data){
					if(data.state){
						alert(data.msg);
						//self.close();
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						parent.layer.close(index);
					}else{
						$.messager.alert('保存执法文件:',data.msg);
					}
				}
			});
		}
	}
});


//删除行
function delrow(id){
	var rows = $('#data').datagrid('getRows');
	for(var i=0;i<rows.length;i++){
		if(rows[i].id==id){
			$.ajax({
				url: "deleteLawdoc.json?id="+id,
				success:function(data){
					if(data.state){
						$('#data').datagrid("deleteRow",i);
						//alert("删除成功!");
						var index=layer.alert(data.msg,{
					     	title:'操作信息:',
					        icon:1,
					        shadeClose:true,
					     },
					     function(){
					        layer.close(index);
					      });
					}else{
						alert("删除文件出错！");
						
					}
				}
			});
			break;
		}
	}
}

//删除一行
function deltr(obj){
	$(obj).parent().parent().remove();
	//$.ajax({
	//	  url: "deleteLawdoc.json?id="+id,
	//	  success:function(data){
	//		  if(data.state){
	//		  }else{
	//			  alert("删除文件出错！");
	//		  }
	//	  }
	//});
}
</script>