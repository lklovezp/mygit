<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
		<form id="queryForm" action="savejzgd.json" method="post">
			<input type="hidden" id="fid" name="fid" value="${fid}" />
			<input type="hidden" id="id" name="id" value="${jzgdForm.id}"/>
			<input type="hidden" id="lawobjtypeid" name="lawobjtypeid" value="${lawobjtypeid}"/>
			<input type="hidden" id="lawobjfid" name="lawobjfid" value="${jzgdForm.lawobjfid}"/>
			<input type="hidden" id="jsxmid" name="jsxmid" value="${jzgdForm.jsxmid}"/>
			<input type="hidden" id="qyscztj" name="qyscztj" value='${jzgdForm.qysczt}'/>
			<input type="hidden" id="lawobjId" name="lawobjId" value=''/>
			<div id="condition">
				<table class='dataTable basicinfoTable' width='100%' cellpadding='0' cellspacing='0'>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
临时排污许可证编码：</td>
<td colspan='3'>
<input class='i-text easyui-validatebox' type='text'  name='lspwxkzbm' id='lspwxkzbm' value='${jzgdForm.lspwxkzbm }'/></td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>施工项目名称：</td>
<td colspan='3'>
<textarea title='限制输入150字符'  style='width:350px;' maxlength='150' onKeyUp='return textMaxLen(this);' onBlur='return textMaxLen(this);' rows='2' class='i-text easyui-validatebox'  name='dwmc'  data-options='required:true'  id='dwmc' >${jzgdForm.dwmc}</textarea></td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>工程地点：</td>
<td colspan='3'>
<textarea title='限制输入150字符'  style='width:350px;' maxlength='150' onKeyUp='return textMaxLen(this);' onBlur='return textMaxLen(this);' rows='2' class='i-text easyui-validatebox'  name='dwdz'  data-options='required:true'  id='dwdz' >${jzgdForm.dwdz}</textarea></td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
经度：</td>
<td>
<input class='i-text easyui-numberbox' type='text' style='width:50px' maxLength='3' name='jddu' id='jddu' value="${jzgdForm.jddu}"/>度<input class='i-text easyui-numberbox' type='text' style='width:50px' maxLength='2' name='jdfen' id='jdfen' value="${jzgdForm.jdfen}"/>分<input class='i-text easyui-numberbox' type='text' style='width:50px' maxLength='2' name='jdmiao' id='jdmiao'value="${jzgdForm.jdmiao}"/>秒</td>
<td width='150' bgcolor='#edfaff' align='right'>
纬度：</td>
<td>
<input class='i-text easyui-numberbox' type='text' style='width:50px' maxLength='3' name='wddu' id='wddu' value="${jzgdForm.wddu}"/>度<input class='i-text easyui-numberbox' type='text' style='width:50px' maxLength='2' name='wdfen' id='wdfen' value="${jzgdForm.wdfen}"/>分<input class='i-text easyui-numberbox' type='text' style='width:50px' maxLength='2' name='wdmiao' id='wdmiao' value="${jzgdForm.wdmiao}"/>秒</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
施工单位名称：</td>
<td colspan='3'>
<textarea title='限制输入150字符'  style='width:350px;' maxlength='150' onKeyUp='return textMaxLen(this);' onBlur='return textMaxLen(this);' rows='2' class='i-text easyui-validatebox'  name='sgxmmc'  id='sgxmmc' >${jzgdForm.sgxmmc}</textarea> <a id='chosee'>选择</a> </td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
单位地址：</td>
<td colspan='3'>
<textarea title='限制输入150字符'  style='width:350px;' maxlength='150' onKeyUp='return textMaxLen(this);' onBlur='return textMaxLen(this);' rows='2' class='i-text easyui-validatebox'  name='gcdd'  id='gcdd' >${jzgdForm.gcdd}</textarea></td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>所属行政区：</td>
<td >
<input class='i-text' type='text'  name='ssxzq' id='ssxzq' value='${jzgdForm.ssxzq}' /></td>
<td width='150' bgcolor='#edfaff' align='right'>
建筑工地状态：</td>
<td >
<input class='i-text easyui-validatebox' type='text'  name='jzgdzt' id='jzgdzt' value='${jzgdForm.jzgdzt}'/></td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>法定代表人：</td>
<td>
<input class='i-text easyui-validatebox' type='text'  name='fddbr' id='fddbr' value='${jzgdForm.fddbr}' data-options='required:true' title='限填150字以内' maxlength='150' onKeyUp='return textMaxLen(this);' onBlur='return textMaxLen(this);' /></td>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>法定代表人联系电话：</td>
<td>
<input class='i-text easyui-validatebox' type='text'  name='fddbrdh' id='fddbrdh' value='${jzgdForm.fddbrdh}' data-options='required:true' title='限填150字以内' maxlength='150' onKeyUp='return textMaxLen(this);' onBlur='return textMaxLen(this);' /></td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
环保负责人：</td>
<td>
<input class='i-text easyui-validatebox' type='text'  name='hbfzr' id='hbfzr' value='${jzgdForm.hbfzr}'/></td>
<td width='150' bgcolor='#edfaff' align='right'>
环保负责人联系电话：</td>
<td>
<input class='i-text easyui-validatebox' type='text'  name='hbfzrdh' id='hbfzrdh' value='${jzgdForm.hbfzrdh}'/></td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
开工日期：</td>
<td>
<input class='easyui-datebox' type='text'  name='kgrq'  id='kgrq' data-options='editable:false' value='${jzgdForm.kgrq}' /></td>
<td width='150' bgcolor='#edfaff' align='right'>
预计竣工日期：</td>
<td>
<input class='easyui-datebox' type='text'  name='yjjgtrq'  id='yjjgtrq' data-options='editable:false' value='${jzgdForm.yjjgtrq}'  /></td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
噪声源个数：</td>
<td>
<input class='i-text easyui-numberbox' min='0' type='text'  name='zsygs' id='zsygs' value='${jzgdForm.zsygs}' /></td>
<td width='150' bgcolor='#edfaff' align='right'>
污染源防治设施数量：</td>
<td>
<input class='i-text easyui-numberbox' min='0' type='text'  name='wryfzsssl' id='wryfzsssl' value='${jzgdForm.wryfzsssl}'/></td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
所属网格区域：</td>
<td>
<input class='i-text ' type='text'  name='sswgqy' id='sswgqy' value="${jzgdForm.sswgqy}" /></td>
<td width='150' bgcolor='#edfaff' align='right'>
创建人：</td>
<td>
<c:if test="${jzgdForm.id==null}">
<input type="hidden" id="cjr" name="cjr" value="${cjr}"/>
<input class='i-text' type='text' style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '  name='cjrname' id='cjrname' value="${cjrname}" /></td>
</c:if>
<c:if test="${jzgdForm.id !=null&& jzgdForm.id!=''}">
<input class='i-text' type='text' style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '  name='cjr' id='cjr' value="${jzgdForm.cjr}" /></td>
</c:if>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
所属监管部门：</td>
<td>
<input class='i-text' type='text'  name='ssjgbm' id='ssjgbm' value="${jzgdForm.ssjgbm}" /></td>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>企业状态：</td>
<td>
<input class='i-text easyui-validatebox' type='text'  name='qyzt' id='qyzt' value="${jzgdForm.qyzt}"/></td>
</tr>

<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>企业生产状态：</td>
<td colspan='3'>

<input type='radio' class='radioItem' name='qysczt' id='qysczt' checked  value='Y'/> 全年性生产
<input type='radio' class='radioItem' name='qysczt' id='qysczt'  value='N' /> 季节性生产
<span style='display:none;' id='season'>
<input type='checkbox' name='qysczt' id='qysczt'value='01' /> 1月
<input type='checkbox' name='qysczt' id='qysczt' value='02'/> 2月
<input type='checkbox' name='qysczt' id='qysczt' value='03' /> 3月
<input type='checkbox' name='qysczt' id='qysczt' value='04'/> 4月
<input type='checkbox' name='qysczt' id='qysczt' value='05'/> 5月
<input type='checkbox' name='qysczt' id='qysczt' value='06'/> 6月
<input type='checkbox' name='qysczt' id='qysczt' value='07'/> 7月
<input type='checkbox' name='qysczt' id='qysczt' value='08'/> 8月
<input type='checkbox' name='qysczt' id='qysczt' value='09'/> 9月
<input type='checkbox' name='qysczt' id='qysczt' value='10'/> 10月
<input type='checkbox' name='qysczt' id='qysczt' value='11'/> 11月
<input type='checkbox' name='qysczt' id='qysczt' value='12'/> 12月</span>

</td>
</tr>


</table></div>
			
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
						<td width="200" >
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
			
			<div class="rb_btn_fix" id="bottomBtn">
				<input type="submit" id="query" class="queryBlue" value="保   存"/>
				<input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
			</div>

			
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
	if($('input[name=isActive]').val()==''){$('input[name=isActive]').val('Y');}
$('#ssxzq').combotree({required:true, url:'regionTree.json'});
$('input[name=isActive]').combobox({required:true, url:'ztList.json',valueField:'id',textField:'name'});
$('#sswgqy').combobox({required:true,url:'wgTree.json',valueField:'id',textField:'name'});
$('#ssjgbm').combotree({required:true,url:'orgTree.json'});

$('#qyzt').combobox({required:true, url:'qyztList.json',valueField:'id',textField:'name'});
$('#jzgdzt').combobox({url:'dicList.json?type=27',valueField:'id',textField:'name'});

	
	$("#jzgd_wghzrr").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?all=true&notShowZj=false&methodname=setUserValue&multi=true"});

	$("#fjlx").combobox({
		url:'fjlxList.json?enumName=JZGD',
		valueField:'id',
		textField:'name'
	});
	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
		   var $selectedvalue = $("input[id='qysczt']:checked").val();
		   if ($selectedvalue == 'Y') {
				$("#season").hide();
				$("input[id='qysczt']").val("Y");
		   }
		   else {
				$("#season").show();
		   }
	});
	var h =$(window).height() - $("#bottomBtn").outerHeight() - $("#condition").outerHeight() - $("#divTitle").outerHeight();
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
					url:"savejzgd.json",
					success: function(data){
						if(data.state){
							/*$.messager.alert("保存建筑工地信息:", data.msg, "info", function () {
							    //self.close();
							     var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						        parent.layer.close(index);
								Android.close(data.id, data.name);
                                 });*/
							var index=layer.alert(data.msg,{
						     	title:'保存建设工地信息:',
						        icon:1,
						        shadeClose:true,
						     },
						     function(){
						        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						        parent.layer.close(index);
						        Android.close(data.id, data.name);
						      });
						}else{
							$.messager.alert('保存建筑工地信息:',data.msg);
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
		queryParams:{pid:$("#id").val()},
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
				/*$.messager.confirm('操作确认', '上传附件需要保存当前建筑工地信息，是否继续？', function(r){
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
									$.messager.alert('保存建筑工地:',data.msg);
								}
							}
						});
					}
				});*/
				/*var index=layer.confirm('上传附件需要保存当前建筑工地信息，是否继续？', {
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
									$.messager.alert('保存建筑工地信息:',data.msg);
									
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
					url:"savejzgd.json",
					success: function(data){
						if(data.state){
							$("#id").val(data.id);
							$.colorbox({
								iframe:true, width:"610", height:"380",
								href:'uploadPage.htm?pid='+data.id+'&fileType='+fjlx+'&path=XXGL&tableId=data'
							});
						}else{
							$.messager.alert('保存建筑工地信息:',data.msg);
							
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
	//$("#jzgd_wghzrr").linkbox("setValue", {id:id,name:name});
	$("#jzgd_wghzrr").val(name);
	$("#jzgd_wghzrrid").val(id);
	jQuery().colorbox.close();
}

/**
 * 填完经度后回填数据
 */
function setJdValue(jd){
	$("#jzgd_jd").val(jd);
	jQuery().colorbox.close();
}

/**
 * 填完纬度后回填数据
 */
function setWdValue(wd){
	$("#jzgd_wd").val(wd);
	jQuery().colorbox.close();
}

//企业生产状态     季节随选择全年性生产或季节性生产隐藏和显示
$(document).ready(function() {
	if($("#qyscztj").val()!=null&&$("#qyscztj").val()!=""){
		var value=$("#qyscztj").val();
		var arr = new Array();
		arr = value.split(",");
		
		var checkBoxAll = $("#season input[id='qysczt']");
		
		if(arr[0]=='Y'){
		  $("#season").hide();
		  $("input[value='Y']").attr("checked",true);
		}else{
		  $("#season").show();
		  $("input[value='N']").attr("checked",true);
		  for(var i=0;i<arr.length;i++){
			    //获取所有复选框对象的value属性，然后，用checkArray[i]和他们匹配，如果有，则说明他应被选中
			    $.each(checkBoxAll,function(j,checkbox){
			    //获取复选框的value属性
			    var checkValue=$(checkbox).val();
			    if(arr[i]==checkValue){
			    $(checkbox).attr("checked",true);
			    }
			    })
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

/**
 * 选择执法对象
 */
 $("#chosee").click(function(){
	$("#chosee").colorbox({iframe:true,width:"800", height:"600", scrolling:false, href:"choseeSgdw.htm?type=edit"});
 });

/**
 * 选择后回填数据
 */
function setValues(id,dwmc,dwdz,fddbr,fddbrlxdh,hbfzr,hbfzrlxdh){
	$("#gcdd").val(dwmc);
	if(dwdz!=""){
		$("#sgxmmc").val(dwdz);
	}
	if(fddbr!=""){
		$("#fddbr").val(fddbr);
	}
	if(fddbrlxdh!=null){
		$("#fddbrdh").val(fddbrlxdh);
	}
	if(hbfzr!=""){
		$("#hbfzr").val(hbfzr);
	}
	if(hbfzrlxdh!=""){
		$("#hbfzrdh").val(hbfzrlxdh);
	}
	jQuery().colorbox.close();
}
</script>