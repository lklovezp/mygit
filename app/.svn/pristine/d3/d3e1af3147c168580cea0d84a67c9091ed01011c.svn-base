<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${title }</title>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css" />
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
		<script type="text/javascript" src="${common}/All.js"></script>
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<link href="/app/static/app/css/taskDispatch.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
        .panel-header{background-color: #cff1ff;}
        .formtable, .formtable th, .formtable td{border-color:#dddddd; }
        </style>
	</head>
	<body>
		<div class="h1_1">任务执行过程分析
		

	
		</div>
		<div style="width:98%; margin:5px  auto 5px;" class="t-r" id="dao">
			<input type="button" id="fanhui"  class="bTn directory_save directory_comwidth"  value="返 回" onclick="fanhui()" />
	
            
           </div>
		<form id="queryForm" action="" method="post">
			
			
			<div class="divContainer" style="width:98%;margin:0px auto;" >
				<div class="easyui-panel" title="任务基本信息" style="margin-bottom:10px;">
					<table width="100%" border="1px" align="center" cellpadding="0"
						cellspacing="0" class="formtable">
						<tr>
							<th width="150">
								任务名称：
							</th>
							<td colspan="2">
								${rwzxgcfxForm.workname }
							</td>
							<td>
								<a id="rwxq" onclick="info()" class="btslink">任务详情</a>&nbsp;
							</td>
						</tr>
						<tr>
							<th>
								主要内容：
							</th>
							<td colspan="3">${rwzxgcfxForm.worknote }
							</td>
						</tr>
						<tr>
							<th>
								任务来源：
							</th>
							<td>
								${rwzxgcfxForm.rwly }
							</td>
							<th width="150">
								任务密级：
							</th>
							<td>
								${rwzxgcfxForm.rwmj }
							</td>
						</tr>
						<tr>
							<th>
								紧急程度：
							</th>
							<td>
								${rwzxgcfxForm.jjcd }
							</td>
							<th>
								登记人：
							</th>
							<td>
								${rwzxgcfxForm.djr }
							</td>
						</tr>
						<tr>
							<th>
								派发人：
							</th>
							<td>
								${rwzxgcfxForm.pfr }
							</td>
							<th>
								派发时间：
							</th>
							<td>
								${rwzxgcfxForm.pfsj }
							</td>
						</tr>
						<tr>
							<th>
								要求完成时限：
							</th>
							<td colspan="3">
								${rwzxgcfxForm.yqwcsx }
							</td>
						</tr>
					</table>
				</div>
				<div class="easyui-panel" title="当前任务状态" style="margin-bottom:10px;">
					<table width="100%" border="1px" align="center" cellpadding="0"
						cellspacing="0" class="formtable">
						<tr>
							<th width="150">
								任务状态：
							</th>
							<td>
								${rwzxgcfxForm.rwzt }
							</td>
							<th width="150">
								待操作人：
							</th>
							<td>
								${rwzxgcfxForm.dczr }
							</td>
						</tr>
						<tr>
							<th>
								主办部门：
							</th>
							<td>
								${rwzxgcfxForm.zbbm }
							</td>
							<th>
								主办人员：
							</th>
							<td>
								${rwzxgcfxForm.zbry }
							</td>
						</tr>
						<tr>
							<th>
								办理时间：
							</th>
							<td colspan="3">
								${rwzxgcfxForm.blsj }
							</td>
						</tr>
					</table>
				</div>
				<div class="easyui-panel" title="用时最长阶段" style="margin-bottom:10px;">
					<table width="100%" border="1px" align="center" cellpadding="0"
						cellspacing="0" class="formtable">
						<tr>
							<th width="150">
								阶段类型：
							</th>
							<td>
								${stageAnalysis.stageType }
							</td>
							<th width="150">
								操作人：
							</th>
							<td>
								${stageAnalysis.oprateUser }
							</td>
							<td>
								<a id="" onclick="javascript:timeAnalysis()" class="btslink">阶段用时分析</a>&nbsp;
							</td>
						</tr>
						<tr>
							<th>
								开始时间：
							</th>
							<td>
								${stageAnalysis.starttime }
							</td>
							<th>
								结束时间：
							</th>
							<td colspan="2">
								${stageAnalysis.endtime }
							</td>
						</tr>
						<tr>
							<th>
								用时：
							</th>
							<td colspan="4">
								${stageAnalysis.usetime }
							</td>
						</tr>
					</table>
				</div>
				<div class="easyui-panel" title="逾期点分析" style="margin-bottom:10px;">
					<table width="100%" border="1px" align="center" cellpadding="0"
						cellspacing="0" class="formtable">
						<tr>
							<th width="150">
								阶段类型：
							</th>
							<td>${yqjdForm.stageType }
							</td>
							<th width="150">
								操作人：
							</th>
							<td>${yqjdForm.oprateUser }
							</td>
						</tr>
						<tr>
							<th>
								开始时间：
							</th>
							<td>${yqjdForm.starttime }
							</td>
							<th>
								结束时间：
							</th>
							<td>${yqjdForm.endtime }
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</body>
</html>

<script language="JavaScript">

function timeAnalysis(){
	//All.ShowModalWin('jdysfxList.htm?id=${id}', '阶段用时分析', document.body.clientWidth, document.body.clientHeight-30)
	//window.location.href='jdysfxList.htm?id=${id}';
	var title='阶段用时分析';
	var width=screen.width*0.8;
	var height=screen.height*0.8-50;
	parent.layerIframe(2,'jdysfxList.htm?id=${id}',title,width,height);
}

//查看任务详情
function info(){
     //All.ShowModalWin('taskDetail.htm?applyId=${id}', '任务详情');
      //window.location.href='taskDetail.htm?applyId=${id}';
	  var width=screen.width * 0.8;
  	  var height=screen.height * 0.8-50;
      var title='任务详情';
      parent.layerIframe(2,'taskDetail.htm?applyId=${id}',title,width,height);
     
}
function fanhui(){
	/*var url = 'statisticalOrgInfo.htm?orgid=${orgid}';
	url += '&type=${type}';
	url += '&areaId=${areaId}';
	url += '&tasktypeid=${tasktypeid}';
	url += '&jjcd=${jjcd}';
	url += '&rwly=${rwly}';
	url += '&starttime='+$("#starttime").val();
	url += '&endtime='+$("#endtime").val();
	window.location.href=url;*/
	history.go(-1);
}
</script>