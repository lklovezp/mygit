<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>待办任务-专项行动任务办理</title>
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.form.js"></script>
	<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
    <!--时间插件 my97-->
    <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
    <!-- 任务管理 css-->
    <link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${common}/All.js"></script>
</head>
<body style="background-color: #ffffff;padding-bottom: 60px;">
<form id="blForm" name="blForm" method="post" action="">
        <input type="hidden" id="applyId" name="applyId" value="${work.id}" />
		<input type="hidden" id="taskId" name="taskId" value="${taskId}" />
<div class="dataDiv" style="width:95%; min-width: 1020px; margin:16px auto 25px;">
    <!--task_step end -->
    <h3 class="task_h3 mt25"><span class="h_icon"></span>专项行动</h3>
    <table class="dataTable mt25" width="100%" border="0" bordercolor="#d4d4d4"  cellpadding="0" cellspacing="0">
        <tr>
            <td style="width: 120px;" align="center"><span class="red">*</span> 检查时间</td>
            <td >
            <input type="text" style="width: 120px;" class="y-dateTime" id="jcsj1" name="jcsj1" value="${blZxxdForm.jcsj1}" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'jcsj2\',{d:-1});}',dateFmt:'yyyy-MM-dd HH:mm'})"> 
                            至 <input type="text" style="width: 120px;" class="y-dateTime" id="jcsj2" name="jcsj2" value="${blZxxdForm.jcsj2}" onclick="WdatePicker({minDate:'#F{$dp.$D(\'jcsj1\',{d:1});}',dateFmt:'yyyy-MM-dd HH:mm'})"></td>
        </tr>
        <tr>
            <td style="width: 120px;" align="center"><span class="red">*</span> 检查要求</td>
            <td><textarea name="jcyq" id="require"  class="y-textarea easyui-validatebox" data-options="required:true" style="width:80%;height: 80px;">${blZxxdForm.jcyq}</textarea></td>
        </tr>
        <tr>
            <td style="width: 120px;" align="center"><span class="red">*</span> 检查目的</td>
            <td>
	            <textarea name="jcmd" id="aims"  class="y-textarea easyui-validatebox" data-options="required:true" style="width:80%;min-height: 80px;">${blZxxdForm.jcmd}</textarea>
            </td>
        </tr>
    </table>
    <hr class="mt25" style="height: 2px;border: none; border-bottom: 2px dashed #d4d4d4" />
    <!--实施现场检查 -->
    <div class="inspection mt25">
        <h3 class="task_h3">
        <input type="button" class="bTn blue" id="taskDivision" value="任务分工" style="padding: 5px 10px; float:right;" onclick="rwfg()">
        <span class="h_icon"></span>
        	 实施现场检查
        </h3>
        <div class="mt25" style=" width: 100%; height: 218px;margin-top: 20px;">
            <table id="zxZfdxTable" fit="true"></table>
        </div>
    </div>
    <hr class="mt25" style="height: 2px;border: none; border-bottom: 2px dashed #d4d4d4" />
    <!-- 相关附件 -->
    <div class="mt25" id="annex">
        <div class="annex_header">
            <a class="b_link" id="ZXXDfileupload" style="float:right;" >上传附件</a>
            相关附件
            <div class="annexselect">
                <input type="text" class="y-text" style="width:200px;" id="ZXXDfiletype"/>
            </div>
        </div>
        <div class="annex_con" style=" height: 248px;">
            <table id="ZXXDdata" fit="true"></table>
        </div>
    </div>
</div>
<div class="rb_btn_fix" style="text-align:center; cursor: pointer">
    <input id="savebutton" type="button" class="queryBlue" onclick="baocun()" value="保  存" style="cursor: pointer">
    <input type="button" class="queryOrange" onclick="bl()" value="下一步" style="cursor: pointer">
</div>
</form>
<iframe name="download" id="download" src="" style="display: none"></iframe>
</body>
</html>

<script type="text/javascript">
var applyId = $('#applyId').val();

//定义一个对象（code：任务类型code,zwName：中文名称,pyjx：拼音简写；fileType：文件类型；）
function RwlxObj(code,zwName,pyjx,fileType){
  this.code = code;
  this.zwName = zwName;
  this.pyjx = pyjx;
  this.fileType=fileType;
}
var rwlxObj=new RwlxObj("","","","");
//通用展示"相关任务附件"
function showCOMMON(rwlxObj){
  //上传附件类型
  $('#'+rwlxObj.pyjx+'filetype').combobox({
	    height:34,
		url:'queryBlFileTypeCombo.json?rwlx='+rwlxObj.code,
		valueField:'id',
		textField:'name'
	});
  var fileTypeNum=rwlxObj.fileType.split(',');
  var canDelStr="";
  for(var i=0;i<fileTypeNum.length;i++){
      if(i<fileTypeNum.length-1){
         canDelStr+="Y,";
      }else{
         canDelStr+="Y";
      }
  }
  //赋值
  $('#'+rwlxObj.pyjx+'data').datagrid({
      pagination:true,//分页控件
      height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns : true,
		url:'queryFileListMulfileType.json?canDel='+canDelStr,
		queryParams:{pid:$("#applyId").val(),fileType:rwlxObj.fileType,tableId:rwlxObj.pyjx+'data'},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:150},
			{field:'filename',title:'附件名称', align:'left', halign:'center',width:500},
			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:100},
			{field:'operate',title:'操作', align:'center', halign:'center',width:150}
		]]
	});
	//上传
	$('#'+rwlxObj.pyjx+'fileupload').click(function(){
		var id = $("#applyId").val();
		var uploadFileType = $('#'+rwlxObj.pyjx+'filetype').combobox('getValue');
		if(uploadFileType!=null && uploadFileType!=''){
		    $(this).colorbox({
				iframe:true, width:"610", height:"380",
				href:'uploadPage.htm?pid='+id+'&fileType='+uploadFileType+'&path=WORK&tableId='+rwlxObj.pyjx+'data'
			});
		}else{
		    alert("请先选择上传附件类型类型！");
		}
	});
}

$(document).ready(function() {
  //执法对象列表
	$('#zxZfdxTable').datagrid({
	    url:'zxZfdxTable.json', 
	    queryParams:{applyId:applyId},
	    width:'100%',
		height:'auto',
		rownumbers: true,
		singleSelect: true,
		fitColumns:true,
	    columns:[[
	        {field:'lawobjname',title:'执法对象',width:150,align:'center'},   
	        {field:'rwzt',title:'任务状态',width:50,align:'center'},
	        {field:'zbry',title:'主办人员',width:50,align:'center'},
	        {field:'yqwcsx',title:'要求完成时限',width:100,align:'center'},
	        {field:'jcsj',title:'检查时间',width:100,align:'center'},
	        {field:'operate',title:'操作',width:50,align:'center'}
	    ]]
	});
	
	//相关任务附件
	rwlxObj=new RwlxObj("15","专项行动","ZXXD","ZXXDJCBG,ZXXDQTZL,ZXXDZRWSPZL,ZXXDZRWLYZL,ZXXDZRWZP,ZXXDZRWHPPFWJ,ZXXDZRWYSPFWJ");
	showCOMMON(rwlxObj);
	
});

function bl() {
  //前台校验
  if($("#blForm").form('validate')){
      //保存
      $('#blForm').attr('action','saveZxWorkzxBL.json');
		$('#blForm').ajaxSubmit(function(data){
	   		if(data.state){
				//后台校验
		        $.post('checkBlBL.json', {
					applyId : applyId
				}, function(json) {
					if (json.state) {
						//跳到报告
						parent.changeTitle("2");
					} else {
						//提示信息
						alert(json.msg);
					}
				}, 'json');
			}else{
				$.messager.alert('任务办理保存:',data.msg);
			}
	   	});
	}
}

//保存草稿
function baocun(){
  $('#blForm').attr('action','saveZxWorkzxBL.json');
	$('#blForm').ajaxSubmit(function(data){
 		if(data.state){
			alert(data.msg);
		}else{
			$.messager.alert('任务办理保存:',data.msg);
		}
 	});
}

//任务分工
function rwfg(){
	layer.open({
	  	  type: 2,
	  	  title: "任务分工",
	  	  maxmin: true,
	  	  shadeClose: false, //点击遮罩关闭层
	  	  area : [1000+'px' , 460+'px'],
	  	  content: 'zxrwfg.htm?applyId='+applyId,
	  		end:function(){
	  			$('#zxZfdxTable').datagrid('reload');
	  		}
	  	  });
  //更新表格
	
}
function closeLayer(){
	layer.closeAll('iframe');
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
	$.messager.confirm('操作', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
				url: "removeFile.json?id="+obj.id,
				success:function(data){
					alert(data.msg);
					var reloadtable= $(obj).attr("reloadtable");//自定义属性采用此方式获得 
					$('#'+reloadtable).datagrid('reload');
				}
			});
		}
	});
}
    //数据表格宽度自适应
    $(window).resize(function(){
        $('#annex_con ,#inspection').datagrid("resize");
    });
</script>
