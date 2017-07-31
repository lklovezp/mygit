<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}"/>
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
	<link rel="stylesheet" type="text/css"
		href="${easyui}/themes/icon.css">
	<link rel="stylesheet" type="text/css"
		href="${easyui}/themes/default/easyui.css">
	<link href="${app}/hnjz.css" rel="stylesheet" type="text/css">

	<script type="text/javascript"
		src="${colorbox}/jquery.colorbox-min.js"></script>
	<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
<script type="text/javascript">
//步骤对象
var StepObj=function(obj){
	this.name=obj==null?'':obj.name;
	this.page=obj==null?'':obj.page;
	this.toJson=function(){
		var json='{';
		json+='\"name\":\"'+this.name+'\",';
		json+='\"page\":\"'+this.page+'\"';
		json+='}';
		return json;
	}
}
//添加步骤
function addStep(stepObj){
	var cStep=$('#cStep').clone(true);
	cStep.css('display','');//显示
	cStep.attr('id','');//去除ID
	cStep.insertBefore($('#cStep'));
	if(stepObj!=null){
		cStep.children($('td').first()).children($('input').first()).val(stepObj.name);
		cStep.children($('td').first()).next().find('select').val(stepObj.page);
	}
}
</script>	
</head>
<body>
<div style="padding: 20px;">
	<form id="myform" name="myform" method="post" action="saveUser.json">
		<input type="hidden" value="${userForm.id}" id="id" name="id">
		<div id="neirong" style="padding: 5px;">
			<div class="fm-item">
				<label class="fm-label" for="name">
					流程名称：
				</label>
				<input class="i-text" type="text" value="${pd.name}" readonly="readonly"/>
			</div>
			<div class="fm-item">
				<label class="fm-label" for="username">
					业务类型：
				</label>
				<select id="business" name="business" style="width:150px;">  
				    <option value="">请选择</option>
					<c:forEach items="${processes}" var="process">
						<option value="${process.processKey}">${process.processName}</option>
					</c:forEach>
				</select> 
			</div>
			<div class="fm-item">
				<label class="fm-label" for="jiece">
					<a id="addStep" title="添加新步骤">添加步骤：</a>
				</label>
				<table border="0" cellpadding="0" cellspacing="0" id="t">
					<tr id="cStep" style="display: none">
						<td><input id="name" class="i-text"/></td>
						<td>&nbsp;
							<select>
								<c:forEach items="${stepNameList }" var="stepName">
									<option value="${stepName }">${stepName }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
			</div>
			<div class="fm-item">
				<span class="btn btn-ok"> 
					<input type="submit" value="提交">
				</span>
			</div>
		</div>
	</form>
</div>

<c:if test="${wfProcessBusiness!=null}">
	<c:forEach items="${wfProcessBusiness.wfProcessSteps}" var="step">
	<script type="text/javascript">
	var stepObj=new StepObj();
	stepObj.name='${step.stepName}';
	stepObj.page='${step.pageName}';
	addStep(stepObj);
	</script>
	</c:forEach>
</c:if>
<script type="text/javascript">
$(document).ready(function(){
	if('${wfProcessBusiness}'!=''){
		$('#business').val('${wfProcessBusiness.businessKey}');
	}else{
		addStep();		
	}
	$('#addStep').click(function(){
		addStep();
	});
});
var stepArr;

function save(){
	//验证数据
	if($('#business').val()==''){
		alert('请选择业务类型！');
		return;
	}
	//整理数据
	clearUp();
	//整理步骤数据
	var stepJson='[';
	for(var i=0;i<stepArr.length;i++){
		stepJson+=stepArr[i].toJson();
		if(i<stepArr.length-1){
			stepJson+=',';	
		}
	}
	stepJson+=']';
	//整理全部数据
	var dataJson='{';
	dataJson+='\"deploymentId\":${pd.deploymentId},"business\":\"'+$('#business').val()+'\",';
	dataJson+='\"steps\":'+stepJson;
	dataJson+='}';
	//提交数据
	$.post("${ctx}/wf/process/pdSetSave", {dataJson:dataJson},function(json){
		alert(json.msg);
		if(json.success){
			
		}
	},'json');
}
//整理数据
function clearUp(){
	stepArr=new Array();
	var stepObj,widgetObj;
	$('#t').children($('tr').first()).children($('tr').first()).each(function (i){
		if($(this).children($('td').first()).children($('input').first()).val()!=''){//步骤名称不为空时记录数据
			stepObj=new StepObj();
			stepObj.name=$(this).children($('td').first()).children($('input').first()).val();//填入的步骤名称
			stepObj.page=$(this).children($('td').first()).next().find('select').find('option:selected').val();//对应页面名称
			stepArr.push(stepObj);
		}
	});
}
</script>
</body>
</html>