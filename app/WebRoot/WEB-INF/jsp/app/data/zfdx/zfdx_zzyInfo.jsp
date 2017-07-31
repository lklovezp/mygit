<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
</head>


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
		<form id="queryForm" action="savezzy.json" method="post">
			<input type="hidden" id="fid" name="fid" value="${fid}" />
			<input type="hidden" id="id" name="id" value="${zzyForm.id}"/>
			<input type="hidden" id="lawobjtypeid" name="lawobjtypeid" value="${lawobjtypeid}"/>
			<input type="hidden" id="lawobjfid" name="lawobjfid" value="${zzyForm.lawobjfid}"/>
			<input type="hidden" id="jsxmid" name="jsxmid" value="${zzyForm.jsxmid}"/>
			<input type="hidden" id="qyscztj" name="qyscztj" value='${zzyForm.qysczt}'/>
			<input type="hidden" id="lawobjId" name="lawobjId" value=''/>
			<div id="condition">
				<table class='dataTable basicinfoTable' width='100%' cellpadding='0' cellspacing='0'>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>单位名称：</td>
<td>
${zzyForm.dwmc}</td>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>所属行政区：</td>
<td>
${zzyForm.ssxzqmc }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>法定代表人：</td>
<td>
${zzyForm.fddbr }</td>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>法定代表人联系电话：</td>
<td>
${zzyForm.fddbrdh }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
环保负责人：</td>
<td>
${zzyForm.hbfzr }</td>
<td width='150' bgcolor='#edfaff' align='right'>
环保负责人联系电话：</td>
<td>
${zzyForm.hbfzrdh }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
经度：</td>
<td>
${zzyForm.jd1 }</td>
<td width='150' bgcolor='#edfaff' align='right'>
纬度：</td>
<td>
${zzyForm.wd1 }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>地址：</td>
<td>
${zzyForm.dwdz }</td>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>营业执照注册号：</td>
<td>
${zzyForm.yyzzzch}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
发证时间：</td>
<td>
${zzyForm.fzsj }</td>
<td width='150' bgcolor='#edfaff' align='right'>
底商：</td>
<td>
${zzyForm.dsmc }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
位于几层：</td>
<td>
${zzyForm.wyjc }</td>
<td width='150' bgcolor='#edfaff' align='right'>
住宅：</td>
<td>
${zzyForm.zzmc }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
商服：</td>
<td>
${zzyForm.sf }</td>
<td width='150' bgcolor='#edfaff' align='right'>
周围环境：</td>
<td>
${zzyForm.zwhj }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
面积㎡：</td>
<td>
${zzyForm.mj }</td>

<td width='150' bgcolor='#edfaff' align='right'>
切割机：</td>
<td>
${zzyForm.qgj }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
角磨机：</td>
<td>
${zzyForm.jmj }</td>
<td width='150' bgcolor='#edfaff' align='right'>
电钻：</td>
<td>
${zzyForm.dz }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
电锯：</td>
<td>
${zzyForm.dj }</td>
<td width='150' bgcolor='#edfaff' align='right'>
断剪钳：</td>
<td>
${zzyForm.djq }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
去籽机：</td>
<td>
${zzyForm.qzj }</td>
<td width='150' bgcolor='#edfaff' align='right'>
打包机：</td>
<td>
${zzyForm.dbj }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
平磨机：</td>
<td>
${zzyForm.pmj }</td>
<td width='150' bgcolor='#edfaff' align='right'>
雕刻机：</td>
<td>
${zzyForm.dkj }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
镯子机：</td>
<td>
${zzyForm.zzj }</td>
<td width='150' bgcolor='#edfaff' align='right'>
抛光机：</td>
<td>
${zzyForm.pgj }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
电刨：</td>
<td>
${zzyForm.dp }</td>
<td width='150' bgcolor='#edfaff' align='right'>
隔声降噪措施：</td>
<td>
${zzyForm.gsjzcs }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
制作地点：</td>
<td>
${zzyForm.zzdd }</td>
<td width='150' bgcolor='#edfaff' align='right'>
喷、刷油漆：</td>
<td>
${zzyForm.psyq }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>所属监管部门：</td>
<td>
${zzyForm.ssjgbmmc }</td>
<td width='150' bgcolor='#edfaff' align='right'>
企业状态：</td>
<td>
${zzyForm.qyztmc }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
所属网格区域：</td>
<td>
${yyForm.sswgqymc}</td>

<td width='150' bgcolor='#edfaff' align='right'>
创建人：</td>
<td>
<c:if test="${zzyForm.id==null}">
<input type="hidden" id="cjr" name="cjr" value="${cjr}"/>
<input class='i-text' type='text' style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '  name='cjrname' id='cjrname' value="${cjrname}" /></td>
</c:if>
<c:if test="${zzyForm.id !=null&& zzyForm.id!=''}">
<input class='i-text' type='text' style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '  name='cjr' id='cjr' value="${zzyForm.cjr}" /></td>
</c:if>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
备注：</td>
<td colspan='3'>
${zzyForm.bz }</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>企业生产状态：</td>
<td colspan='3'>

${zzyForm.qysczt1}
</td>
</tr>
</table>

			</div>
			
				
		</form>
		
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
	
	//设置建设项目转过来的属性值
	
     
	
	
	
	$("#fjlx").combobox({
		url:'fjlxList.json?enumName=GYWRY',
		valueField:'id',
		textField:'name'
	});
	

	var h = $(window).height() - $("#bottomBtn").outerHeight() - $("#condition").outerHeight() - $("#divTitle").outerHeight();
	$("#infectlist").height(h<150?150:h);
	$("#infectlist").width($(window).width());
	$("#questionContainer").width($(window).width());
	
	
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
					url:"savezzy.json",
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