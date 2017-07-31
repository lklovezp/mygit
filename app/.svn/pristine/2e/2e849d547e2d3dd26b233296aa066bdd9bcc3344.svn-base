<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新建/编辑建设项目</title>
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
     .datagrid-header, .datagrid-toolbar, .datagrid-pager, .datagrid-footer-inner {
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
		<div class="h1_1" id="divTitle">${title}</div>
<form id="queryForm" action="savejsxm.json" method="post">
 <input type="hidden" id="fid" name="fid" value="${fid}" />
			<input type="hidden" id="id" name="id" value="${jsxmForm.id}"/>
			<input type="hidden" id="lawobjtypeid" name="lawobjtypeid" value="${lawobjtypeid}"/>
			<input type="hidden" id="lawobjfid" name="lawobjfid" value="${jsxmForm.lawobjfid}"/>
			<input type="hidden" id="jsxmid" name="jsxmid" value="${jsxmForm.jsxmid}"/>
			<input type="hidden" id="qyscztj" name="qyscztj" value='${jsxmForm.qysczt}'/>
			<input type="hidden" id="lawobjId" name="lawobjId" value=''/>
			<div id="condition">
				<table class='dataTable basicinfoTable' width='100%' cellpadding='0' cellspacing='0'>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>建设项目名称：</td>
<td colspan='3'>
${jsxmForm.dwmc}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>建设地点：</td>
<td colspan='3'>
${jsxmForm.dwdz}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
经度：</td>
<td>
${jsxmForm.jd1}</td>
<td width='150' bgcolor='#edfaff' align='right'>
纬度：</td>
<td>
${jsxmForm.wd1}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>所属行政区：</td>
<td > 
${jsxmForm.ssxzqmc}
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>行业类型：</td>
<td>
${jsxmForm.hylxmc}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
审批机关：</td>
<td>
${jsxmForm.spjgmc}</td>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>建设进度及生产状态：</td>
<td>
${jsxmForm.jsjdjscztmc}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
建设性质：</td>
<td>
${jsxmForm.jsxzmc}</td>


<td width='150' bgcolor='#edfaff' align='right'>
创建人：</td>
<td>
<c:if test="${jsxmForm.id==null}">
<input type="hidden" id="cjr" name="cjr" value="${cjr}"/>
<input class='i-text' type='text' style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '  name='cjrname' id='cjrname' value="${cjrname}" /></td>
</c:if>
<c:if test="${jsxmForm.id !=null&& jsxmForm.id!=''}">
<input class='i-text' type='text' style='border-left:0px;border-top:0px;border-right:0px;border-bottom:1px '  name='cjr' id='cjr' value="${jsxmForm.cjr}" /></td>
</c:if>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
建设内容：</td>
<td>
${jsxmForm.jsnr}</td>
<td width='150' bgcolor='#edfaff' align='right'>
建设规模：</td>
<td>
${jsxmForm.jsgm}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
产能：</td>
<td>
${jsxmForm.cn}</td>
<td width='150' bgcolor='#edfaff' align='right'>
项目开工时间：</td>
<td>
${jsxmForm.xmkgsj}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
建成时间：</td>
<td>
${jsxmForm.jcsj}</td>
<td width='150' bgcolor='#edfaff' align='right'>
投产时间：</td>
<td>
${jsxmForm.tcsj}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
单位名称：</td>
<td >${jsxmForm.jsxmmc}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
地址：</td>
<td>
${jsxmForm.gcdd}</td>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>法定代表人：</td>
<td>
${jsxmForm.fddbr}</td>
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>法定代表人联系电话：</td>
<td>
${jsxmForm.fddbrdh}</td>
<td width='150' bgcolor='#edfaff' align='right'>
环保负责人：</td>
<td>
${jsxmForm.hbfzr}</td> 
</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
环保负责人联系电话：</td>
<td>
${jsxmForm.hbfzrdh}</td>
<td width='150' bgcolor='#edfaff' align='right'>
监理单位：</td>
<td>
${jsxmForm.jldw}</td>
</tr>
<input class='i-text' type='hidden'  name='lawobjid' id='lawobjid'/>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
所属网格区域：</td>
<td>
${jsxmForm.sswgqymc}</td>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>所属监管部门：</td>
<td>
${jsxmForm.ssjgbmmc }</td>
</tr>
<tr>
<!-- 
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>状态：</td>
<td>
<input class='i-text easyui-validatebox' type='text'  name='isActive' id='jsxm_zt'/></td> -->

</tr>
<tr>
<td width='150' bgcolor='#edfaff' align='right'>
<label class='requiredLabel'>*</label>企业生产状态：</td>
<td colspan='3'>

${jsxmForm.qysczt1}

</td>
</tr>
</table>
 
</div>
			
				
		</form>
		<iframe name="download" id="download" src="" style="display: none"></iframe>
	</body>
</html>
 
<script language="JavaScript"> 
//企业生产状态     季节随选择全年性生产或季节性生产隐藏和显示
$(document).ready(function() {
	
	//企业生产状态     季节随选择全年性生产或季节性生产隐藏和显示
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
			var $selectedvalue=$("input[id='qysczt']:checked").val();
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
				refresh();
			}else{
				if($("#queryForm").form("validate")){
				
					$("#queryForm").ajaxSubmit({
						type:"post",
						dataType:"json",
						url:"savejsxm.json",
						success: function(data){
							if(data.state){
								$("#id").val(data.id);
								$.colorbox({
									iframe:true, width:"610", height:"380",
									href:'uploadPage.htm?pid='+data.id+'&fileType='+fjlx+'&path=XXGL&tableId=data'
								});
							}else{
								$.messager.alert('保存建设项目信息:',data.msg);
								
							}
						}
					});
				}
			}
		});
		
});


 
var h =$(window).height() - $("#bottomBtn").outerHeight() - $("#condition").outerHeight();
$("#infectlist").height(h<150?150:h);
$("#infectlist").width($(window).width());
$("#fileInfectlist").height(h<150?150:h);
$("#fileInfectlist").width($(window).width());
 
$(document).ready(function(){
	
	
	$.ajaxSetup({cache:false});
	
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
	$('#hpxxData').datagrid({
		rownumbers: true,
		singleSelect: true,
		pagination:true,
		fitColumns:true,
		nowrap: false,
		url:'hpxxList.json',
		queryParams:{pid:$("#id").val()},
		onLoadSuccess:function(data){
			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
		},
		columns:[[
			{field:'id',hidden:true},
			{field:'name',title:'环评项目名称', align:'center', align:"left", halign:'center',width:100},
			{field:'hpspwh',title:'环评审批文号', align:"left", halign:'center',width:50},
			{field:'spsj',title:'审批时间', align:"center", halign:'center',width:30},
			{field:'operate',title:'操作', align:"center", halign:'center',width:30}
		]]
	});
	
	if($('input[name=isActive]').val()==''){$('input[name=isActive]').val('Y');}
$('#sswgqy').combobox({required:true,url:'wgTree.json',valueField:'id',textField:'name'});
$('#ssjgbm').combotree({required:true, url:'orgTree.json'});
$('#ssxzq').combotree({required:true, url:'regionTree.json'});
$('#spjg').combobox({url:'dicList.json?type=10',valueField:'id',textField:'name'});
$('#jsjdjsczt').combobox({required:true, url:'dicList.json?type=8',valueField:'id',textField:'name'});
$('#jsxz').combobox({multiple:true, url:'dicList.json?type=9',valueField:'id',textField:'name'});
$('#jsxz').combobox('setValues','1'.split(','));

$('#hylx').combobox({required:true, panelHeight:150,url:'typetree.json',valueField:'id',textField:'name'});
//$("#hylx").colorbox({iframe:true,width:"300", height:"380",href:"typeQuery.json"});

//$('input[name=isActive]').combobox({required:true, url:'ztList.json',valueField:'id',textField:'name'});
 
	
	$("#fjlx").combobox({
		url:'fjlxList.json?enumName=JSXM',
		valueField:'id',
		textField:'name'
	});
	
	$("#jsxm_wghzrr").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?all=true&notShowZj=false&methodname=setUserValue&multi=true"});
	
	//表单校验
    $("#queryForm").validate({
		errorClass: "error",
		submitHandler:function(form){
			if($("#queryForm").form("validate")){
				$(form).ajaxSubmit({
					type:"post",
					dataType:"json",
					url:"savejsxm.json",
					success: function(data){
						if(data.state){
							/*$.messager.alert("保存建设项目信息:", data.msg, "info", function () {
							   // self.close();
							    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						        parent.layer.close(index);
								Android.close(data.id, data.name);
                                 });*/
							var index=layer.alert(data.msg,{
						     	title:'保存建设项目信息:',
						        icon:1,
						        shadeClose:true,
						     },
						     function(){
						        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						        parent.layer.close(index);
						        Android.close(data.id, data.name);
						      });
						}else{
							$.messager.alert('保存建设项目信息:',data.msg);
						}
					}
				});
			}
		}
	});
	
	
	//重置功能
    var jsxz = $('#sxz').combobox('getValues');
	$("#J-from-reset").click(function(){
		   $("#queryForm").form("reset");
		   if($("#id").val()==''){
			   $('#jsxm_jsxz').combobox('setValues','1'.split(','));
		   }else if(jsxz!=''){
			   $('#jsxm_jsxz').combobox('setValues',jsxz);
		   }
		   var $selectedvalue = $("input[id='qysczt']:checked").val();
		   if ($selectedvalue == 'Y') {
			   $("#season").hide();
			   $("#qysczt").val("Y");
		   }
		   else {
			   $("#season").show();
		   }
	});
	
	
	
  
   
});
 
$('#addhpxx').click(function(){
	var id = $("#id").val();
	if(id!=null && id!=''){
		//All.ShowModalWin('hpxxEdit.htm?pid='+id, '新建环评信息');
		parent.layer.open({
            type: 2,
            title: '新建环评信息',
            area: ['1100px', ($(window).height()+60)+'px'],
            content: 'hpxxEdit.htm?pid='+id, 
            end: function () {
                location.reload();
            }
        });
		$('#hpxxData').datagrid('reload',{pid:id});
	}else{
		if($("#queryForm").form("validate")){
			/*$.messager.confirm('操作确认', '新建环评信息需要保存当前建设项目，是否继续？', function(r){
				if(r){
					$("#queryForm").ajaxSubmit({
						type:"post",
						dataType:"json",
						url:"saveOrUpdateLawobj.json",
						success: function(data){
							if(data.state){
								$("input[name='id']").val(data.id);
								All.ShowModalWin('hpxxEdit.htm?pid='+data.id, '新建环评信息');
								$('#hpxxData').datagrid('reload',{pid:data.id});
							}else{
								$.messager.alert('保存建设项目信息:',data.msg);
							}
						}
					});
				}
			});*/
			var index=layer.confirm('新建环评信息需要保存当前建设项目，是否继续？', {
		     	icon: 3, 
		        title:'操作确认'
		     }, function(index){
		    	 $("#queryForm").ajaxSubmit({
						type:"post",
						dataType:"json",
						url:"savejsxm.json",
						success: function(data){
							if(data.state){
								$("#id").val(data.id);
								//All.ShowModalWin('hpxxEdit.htm?pid='+data.id, '新建环评信息');
								parent.layer.open({
                                   type: 2,
                                   title: '编辑环评信息',
                                   area: ['900px', ($(window).height())+'px'],
                                   content: 'hpxxEdit.htm?pid='+data.id,
                                   end: function () {
                                       //location.reload();
                                	   $('#hpxxData').datagrid('reload',{pid:data.id});
                                       }
                                    });
								
							}else{
								$.messager.alert('保存建设项目信息:',data.msg);
								
							}
						}
					});
		        layer.close(index);
		     },function(index){
		        //alert('取消按钮的回调函数');
		        layer.close(index);
		     });
			
		}
	}
});
 
function edit(obj){
	//All.ShowModalWin('hpxxEdit.htm?id='+$(obj).attr("id"), '', 1000, 800);
	parent.layer.open({
        type: 2,
        title: '编辑环评信息',
        area: ['900px', ($(window).height())+'px'],
        content: 'hpxxEdit.htm?id='+$(obj).attr("id"),
        end: function () {
            location.reload();
        }
 
    });
	$('#hpxxData').datagrid('reload');
}
 
function info(obj){
	//All.ShowModalWin('hpxxInfo.htm?id='+$(obj).attr("id"), '', 1000, 800);
	var width=screen.width * 0.8;
  	var height=screen.height * 0.8-50;
  	var title='查看环评信息';
  	parent.layerIframe(2,'hpxxInfo.htm?id='+$(obj).attr("id"),title,width,height);
}
/*
function del(obj){
	$.messager.confirm('环评信息管理', '确定要删除吗？', function(r){
		if (r){
			$.ajax({
			  url: "delHpxx.json?id="+obj.id,
			  success:function(data){
				 alert(data.msg);
				 $('#').datagrid('reload',{pid:$("input[name='id']").val()});
			  }
			});
		}
	});
}*/
//删除
function del(obj) {
	var index=layer.confirm('确定删除吗？', {
     	icon: 3, 
        title:'删除环评信息'
     }, function(index){
    	 $.ajax( {
				url : "delHpxx.json?id="+obj.id,
				success : function(data) {
					$('#hpxxData').datagrid('reload',{pid:$("#id").val()});
					//refresh();
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}
 
//限制单位地址长度
function textMaxLen(i){
 	 var conMaxLen=parseInt(i.getAttribute("maxlength"));
 	 if(i.value.length>=conMaxLen){
 		i.value=i.value.substring(0,conMaxLen)
 	 }
}
 
//创建人转移
function transfer(obj){
	//All.ShowModalWin('lawobjUserPubQuery.htm?all=true&notShowZj=false&lawobjtypeid=02&multi=false&lawobjId='+$(obj).attr("id"), '创建人转移',300, 380);
	parent.layer.open({
        type: 2,
        title: '创建人转移',
        area: ['300px', '450px'],
        content: 'lawobjUserPubQuery.htm?all=true&notShowZj=false&lawobjtypeid=02&multi=false&lawobjId='+$(obj).attr("id"),
        end: function () {
            location.reload();
        }
 
    });
}
/**
 * 选择执法对象
 */
 $("#chosee").click(function(){
	 var hylx = $("#hylx").combobox('getValue');
		if(hylx == ''){
			alert("请先选择行业类型！");
		}else{

	
		//$("#chosee").colorbox({iframe:true,width:"900", height:"650", scrolling:false, href:"choseeLawobj.htm?type=edit&industryId="+hylx});
		layer.open({
	        type: 2,
	        
	        title: '选择执法对象',
	        area: ['900px', '550px'],
	        content: "jsxmzfdx.htm?lawobjtypeid="+hylx
	    });
		}
 });
 
/**
 * 选择后回填数据
 */
function setValues(id,dwmc,dwdz,fddbr,fddbrlxdh,hbfzr,hbfzrlxdh){
	$("#lawobjid").val(id);
	$("#jsxmmc").val(dwmc);
	if(dwdz!=null){
		if(dwdz!=""){
			$("#dwdz").val(dwdz);
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
	}
	
	
}
 
/**
 * 选择人员后回填数据
 */
function setUserValue(id,name){
	//$("#jsxm_wghzrr").linkbox("setValue", {id:id,name:name});
	$("#wghzrr").val(name);
	$("#wghzrrid").val(id);
	jQuery().colorbox.close();
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
					//alert(data.msg);
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
 * 公用的上传文件之后的刷新grid方法
 * @param tableId tablegrid的id
 */
function reload(tableId,pid){
	var id = "#" + tableId;
	$(id).datagrid("reload",{pid:pid});
	jQuery.colorbox.close();
}
 
/**
 * 填完经度后回填数据
 */
function setJdValue(jd){
	$("#jsxm_jd").val(jd);
	jQuery().colorbox.close();
}
 
/**
 * 填完纬度后回填数据
 */
function setWdValue(wd){
	$("#jsxm_wd").val(wd);
	jQuery().colorbox.close();
}

 
</script>