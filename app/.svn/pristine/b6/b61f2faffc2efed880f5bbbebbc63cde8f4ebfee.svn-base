<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${title }</title>
		<script type="text/javascript" src="/app/static/app/js.js"></script>
		<script type="text/javascript" src="/app/static/app/data.js"></script>
		<script type="text/javascript" src="/app/static/jquery/jquery.js"></script>
		<script type="text/javascript" src="/app/static/jquery/jquery.validate.js"></script>
		<script type="text/javascript" src="/app/static/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="/app/static/jquery/messages_cn.js"></script>
		<script type="text/javascript" src="/app/static/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="/app/static/layer/layer.js"></script>
        <link href="/app/static/app/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="/app/static/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="/app/static/colorbox/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="/app/static/easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="/app/static/easyui/themes/default/easyui.css">
		<link href="/app/static/app/hnjz.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="/app/static/colorbox/colorbox.css">
		<script type="text/javascript" src="/app/static/common/All.js"></script>
		<link href="/app/static/app/CSSReset.css" rel="stylesheet" type="text/css"/>
		<link href="/app/static/app/css/task.css" rel="stylesheet" type="text/css"/>
		<style>
        .basicinfoTable,.basicinfoTable td{ border: 1px solid #d4d4d4;}
        .basicinfoTable a{color:#0088cc;}
        .panel-header{background-color: #cff1ff;}
        .formtable, .formtable th, .formtable td{border-color:#dddddd;}
         .datagrid-header{
         background: #cff1ff;
         }
        .tabs li a.tabs-inner {
               background: #cff1ff;
               color: #000000;
               filter: none;
            }
      </style>
	</head>

	<body style="padding-bottom: 70px;">
		<div class="h1_1" id="divTitle">${title }</div>
		<form id="queryForm" action="saveysy.json" method="post">
			<input type="hidden" id="fid" name="fid" value="${fid}" />
			<input type="hidden" id="id" name="id" value="${ysyForm.id}"/>
			<input type="hidden" id="lawobjtypeid" name="lawobjtypeid" value="${lawobjtypeid}"/>
			<input type="hidden" id="lawobjfid" name="lawobjfid" value="${ysyForm.lawobjfid}"/>
			<input type="hidden" id="jsxmid" name="jsxmid" value="${ysyForm.jsxmid}"/>
			<input type="hidden" id="qyscztj" name="qyscztj" value='${ysyForm.qysczt}'/>
			<input type="hidden" id="lawobjId" name="lawobjId" value=''/>
			<div id="condition">
				<table class='dataTable basicinfoTable' width='100%' cellpadding='0' cellspacing='0'>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>单位名称：</td>
<td>
${ysyForm.dwmc}</td>

<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>所属行政区：</td>
<td>
${ysyForm.ssxzqmc}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>法定代表人：</td>
<td>
${ysyForm.fddbr}</td>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>法定代表人联系电话：</td>
<td>
${ysyForm.fddbrdh}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
环保负责人：</td>
<td>
${ysyForm.hbfzr}</td>
<td width='150' bgcolor='#edfaff' align='right'>
环保负责人联系电话：</td>
<td>
${ysyForm.hbfzrdh}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
经度：</td>
<td>
${ysyForm.jd1}</td>
<td width='150' bgcolor='#edfaff' align='right'>
纬度：</td>
<td>
${ysyForm.wd1}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>地址：</td>
<td>
${ysyForm.dwdz}</td>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>营业执照注册号：</td>
<td>
${ysyForm.yyzzzch}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
发证时间：</td>
<td>
${ysyForm.fzsj}</td>
<td width='150' bgcolor='#edfaff' align='right'>
底商：</td>
<td>
${ysyForm.dsmc}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
位于几层：</td>
<td>
${ysyForm.wyjc}</td>
<td width='150' bgcolor='#edfaff' align='right'>
住宅：</td>
<td>
${ysyForm.zzmc}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
商服：</td>
<td>
${ysyForm.sf}</td>
<td width='150' bgcolor='#edfaff' align='right'>
周围环境：</td>
<td>
${ysyForm.zwhj}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
面积㎡：</td>
<td>
${ysyForm.mj}</td>

<td width='150' bgcolor='#edfaff' align='right'>
桌子：</td>
<td>
${ysyForm.zzi}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
椅子：</td>
<td>
${ysyForm.yz}</td>
<td width='150' bgcolor='#edfaff' align='right'>
人员：</td>
<td>
${ysyForm.ry}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
包厢：</td>
<td>
${ysyForm.bx}</td>
<td width='150' bgcolor='#edfaff' align='right'>
天然气：</td>
<td>
${ysyForm.trq}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
液化气：</td>
<td>
${ysyForm.yhq}</td>
<td width='150' bgcolor='#edfaff' align='right'>
煤：</td>
<td>
${ysyForm.mt}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
灶眼：</td>
<td>
${ysyForm.zy}</td>
<td width='150' bgcolor='#edfaff' align='right'>
制冰机：</td>
<td>
${ysyForm.zbj}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
冷凝机组：</td>
<td>
${ysyForm.lnjz}</td>
<td width='150' bgcolor='#edfaff' align='right'>
和面机：</td>
<td>
${ysyForm.hmj}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
豆浆机：</td>
<td>
${ysyForm.djj}</td>
<td width='150' bgcolor='#edfaff' align='right'>
排气风机（台）：</td>
<td>
${ysyForm.pqfj}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
油烟排放情况：</td>
<td>
${ysyForm.yypfqk}</td>
<td width='150' bgcolor='#edfaff' align='right'>
污染防治设施安装时间：</td>
<td>
${ysyForm.wrfzssazsj}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
是否正常运行：</td>
<td>
${ysyForm.sfzcyxmc}</td>
<td width='150' bgcolor='#edfaff' align='right'>
排油烟管道是否超出楼顶：</td>
<td>${ysyForm.pyygdsfccldmc}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
厂家：</td>
<td>
${ysyForm.cj}</td>
<td width='150' bgcolor='#edfaff' align='right'>
风量：</td>
<td>
${ysyForm.fl}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
清洗时间：</td>
<td>
${ysyForm.qxsj}</td>
<td width='150' bgcolor='#edfaff' align='right'>
维修时间：</td>
<td>
${ysyForm.wxsj}</td>
</tr>

<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>所属监管部门：</td>
<td>
${ysyForm.ssjgbmmc}</td>
<td width='150' bgcolor='#edfaff' align='right'>
企业状态：</td>
<td>
${ysyForm.qyztmc}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
所属网格：</td>
<td>
${ysyForm.sswgqymc}</td>

<td width='150' bgcolor='#edfaff' align='right'>
创建人：</td>
<td>
<c:if test="${ysyForm.id==null}">
<input type="hidden" id="cjr" name="cjr" value="${cjr}"/>
<input class='i-text' type='text' style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '  name='cjrname' id='cjrname' value="${cjrname}" /></td>
</c:if>
<c:if test="${ysyForm.id !=null&& ysyForm.id!=''}">
<input class='i-text' type='text' style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '  name='cjr' id='cjr' value="${ysyForm.cjr}" /></td>
</c:if>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
备注：</td>
<td  colspan='3'>
${ysyForm.bz}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>企业生产状态：</td>
<td colspan='3'>

${ysyForm.qysczt1}

</td>
</tr>
</table>

		
		</form>
		<iframe name="download" id="download" src="" style="display: none"></iframe>
	</body>
</html>


<script language="JavaScript">

$(document).ready(function(){
	$.ajaxSetup({cache:false}); 
	
	
	
	if($('input[name=isActive]').val()==''){$('input[name=isActive]').val('Y');}
$('#ssxzq').combotree({required:true, url:'regionTree.json'});
//$('input[name=column20]').combobox({required:true, url:'industryList.json?lawobjType=01',valueField:'id',textField:'name'});
//$('#kzlx').combobox({required:true, url:'kzlxList.json',valueField:'id',textField:'name'});
$('#sswgqy').combobox({required:true,url:'wgTree.json',valueField:'id',textField:'name'});
$('#ssjgbm').combotree({required:true,url:'orgTree.json'});

$('#qyzt').combobox({required:true, url:'qyztList.json',valueField:'id',textField:'name'});
//$('#zclx').combobox({url:'dicList.json?type=30',valueField:'id',textField:'name'});
//$('#gklx').combobox({multiple:true, url:'dicList.json?type=32',valueField:'id',textField:'name'});
//$('#lsgx').combobox({url:'dicList.json?type=31',valueField:'id',textField:'name'});
//$('#sfsfqy').combobox({url:'sfList.json',valueField:'id',textField:'name'});
//$('#hyqgx').combobox({url:'dicList.json?type=33',valueField:'id',textField:'name'});
$('#ds').combobox({url:'sfList.json',valueField:'id',textField:'name'});
$('#zz').combobox({url:'sfList.json',valueField:'id',textField:'name'});
$('#djgngl').combobox({url:'sfList.json',valueField:'id',textField:'name'});
	
	//设置建设项目转过来的属性值
	
     
	
	
	
	$("#fjlx").combobox({
		url:'fjlxList.json?enumName=GYWRY',
		valueField:'id',
		textField:'name'
	});
	$("#fileupload").click(function(){
		var id = $("#id").val();
		var fjlx = $("#fjlx").combobox('getValue');
		if(fjlx==null || fjlx == ''){
			alert("请选择附件类型！");
			return;
		}
		if(id!=null && id!=''){
			$("#fileupload").colorbox({
				iframe:true, width:"610", height:"380",
				href:'uploadPage.htm?pid='+id+'&fileType='+fjlx+'&path=XXGL&tableId=data'
			});
		}else{
			if($("#queryForm").form("validate")){
				/* $.messager.confirm('操作确认', '上传附件需要保存当前污染源，是否继续？', function(r){
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
									$.messager.alert('保存娱乐业:',data.msg);
									
								}
							}
						});
					}
				});*/
			    	 $("#queryForm").ajaxSubmit({
							type:"post",
							dataType:"json",
							url:"saveysy.json",
							success: function(data){
								if(data.state){
									$("#id").val(data.id);
									$.colorbox({
										iframe:true, width:"610", height:"300",
										href:'uploadPage.htm?pid='+data.id+'&fileType='+fjlx+'&path=XXGL&tableId=data'
									});
								}else{
									$.messager.alert('保存娱乐业:',data.msg);
									
								}
							}
						});
			   
			}
		}
	});
	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
		   var $selectedvalue = $("#qysczt:checked").val();
		   if ($selectedvalue == 'Y') {
				 $("#season").hide();
				 $("#qysczt").val("Y");
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
				$("#queryForm").ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"saveysy.json",
					success: function(data){
						if(data.state){
							 /*$.messager.alert("保存娱乐业:", data.msg, "info", function () {
								    //self.close();
								    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
									parent.layer.close(index);
									Android.close(data.id, data.name);
	                                 });*/
							var index=layer.alert(data.msg,{
						     	title:'保存娱乐业:',
						        icon:1,
						        shadeClose:true,
						     },
						     function(){
						        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						        parent.layer.close(index);
						        Android.close(data.id, data.name);
						      }
						     );
						}else{
							$.messager.alert('保存娱乐业:',data.msg);
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
		url:'queryFileList.json',
		queryParams:{pid:$("#id").val()},
		fitColumns:true,
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
//删除
function deletefile1(obj) {
	var index=layer.confirm('确定删除吗？', {
     	icon: 3, 
        title:'删除任务'
     }, function(index){
    	 $.ajax( {
				url : "removeFile.json?id="+obj.id,
				success : function(data) {
					//refresh();
					alert(data.msg);
					$('#data').datagrid('reload',{pid:$("#id").val()});
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
	//$("#gywry_wghzrr").linkbox("setValue", {id:id,name:name});
	$("#gywry_wghzrr").val(name);
	$("#gywry_wghzrrid").val(id);
	jQuery().colorbox.close();
}
 
/**
 * 填完经度后回填数据
 */
function setJdValue(jd){
	$("#gywry_jd").val(jd);
	jQuery().colorbox.close();
}
 
/**
 * 填完纬度后回填数据
 */
function setWdValue(wd){
	$("#gywry_wd").val(wd);
	jQuery().colorbox.close();
}
 
 
 
//企业生产状态     季节随选择全年性生产或季节性生产隐藏和显示
$(document).ready(function() {
	
	if($("#qyscztj").val()!=null&&$("#qyscztj").val()!=""){
		var value=$("#qyscztj").val();
		var arr = new Array();
		arr = value.split(",");
		if(arr[0]=='Y'){
		  $("#season").hide();
		  $("input[value='Y']").attr("checked",true);
		}else{
		  $("#season").show();
		  $("input[value='N']").attr("checked",true);
		  if(arr[1]=='01'){
			 $("#season input[value='01']").attr("checked",true);
		  }
		  if(arr[2]=='02'){
				 $("#season input[value='02']").attr("checked",true);
			}
		  if(arr[3]=='03'){
			 $("#season input[value='03']").attr("checked",true);
		  }
		  if(arr[4]=='04'){
			 $("#season input[value='04']").attr("checked",true);
		  }
		  if(arr[5]=='05'){
			 $("#season input[value='05']").attr("checked",true);
		  }
		  if(arr[6]=='06'){
				 $("#season input[value='06']").attr("checked",true);
			}
		  if(arr[7]=='07'){
			 $("#season input[value='07']").attr("checked",true);
		  }
		  if(arr[8]=='08'){
			 $("#season input[value='08']").attr("checked",true);
		  }
		  if(arr[9]=='09'){
			   $("#season input[value='09']").attr("checked",true);
		      }
		  if(arr[10]=='10'){
				 $("#season input[value='10']").attr("checked",true);
			}
		  if(arr[11]=='11'){
			 $("#season input[value='11']").attr("checked",true);
		  }
		  if(arr[12]=='12'){
			 $("#season input[value='12']").attr("checked",true);
		  }
		
	  }
	}
	$(".radioItem").change(
		function() {
			var $selectedvalue = $("input[id='qysczt']:checked").val();
			
			if ($selectedvalue == 'Y') {
				 $("#season").hide();
			}
			else {
				 $("#season").show();
			}
	});
	
	$("input[id='qysczt']").blur(function(){
		var yue="";
		$("#season input[id='qysczt']").each(function(){
			
			if($(this).prop("checked")){
				yue+=$(this).attr("value")+",";
			}
		});
		var $selectedvalue = $("input[id='qysczt']:checked").val();
		
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
function add() {
	if($("#queryForm").form("validate")){
		/*$.messager.confirm('操作确认', '添加(修改)辐射安全基本信息需要保存当前污染源，是否继续？', function(r){
			if(r){
				$("#queryForm").ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"saveOrUpdateLawobj.json",
					success: function(data){
						$("#lawobjId").val(data.id);
						$.fn.colorbox({
							iframe : true,
							width : "923",
							height : "599",
							href : "fsaqxxEdit.htm?lawobjId="+data.id+"&lawobjTypeId="+$("#lawobjType").val()
						});
					}
				});
			}
		});*/
		
	    	 $("#queryForm").ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"saveysy.json",
					success: function(data){
						$("#lawobjId").val(data.id);
						$("#id").val(data.id);
						$.fn.colorbox({
							iframe : true,
							width : "800",
							height : "450",
							href : "fsaqxxEdit.htm?lawobjId="+data.id+"&lawobjTypeId="+$("#lawobjtypeid").val()
						});
						/*var width=screen.width * 0.8+50;
					  	var height=screen.height * 0.8-50;
					  	var title='辐射信息';
					  	parent.layerIframe(2,"fsaqxxEdit.htm?lawobjId="+data.id+"&lawobjTypeId="+$("#lawobjType").val(),title,width,height);
					   */
						/*top.layer.open({
				            type: 2,
				            title: '新建制造业',
				            area:[($(window).width())+'px', ($(window).height()+40)+'px'],
				            content: "fsaqxxEdit.htm?lawobjId="+data.id+"&lawobjTypeId="+$("#lawobjType").val()
				        });*/
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