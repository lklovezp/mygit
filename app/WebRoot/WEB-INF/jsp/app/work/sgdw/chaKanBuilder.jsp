<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>查看施工单位</title>
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
      </style>
	</HEAD>

	<body>
		<div class="h1_1" id="divTitle">${title}</div>
			<div class="divContainer" id="questionContainer">
				<form id="queryForm" action="" method="post" >
				<table class="dataTable basicinfoTable" width="100%" cellpadding="0" cellspacing="0">
						<input type="hidden" value="${builderForm.id}" id="id" name="id">
						<input type="hidden"  value="${builderForm.isActive}" id="yczt">
						<tr>
							<th width="150" bgcolor="#edfaff" align="right">
								施工单位名称：
							</th>
							<td colspan="2">
								
									${builderForm.name}
							</td>
						</tr>
						<tr>
							<th bgcolor="#edfaff" align="right">
								施工单位地址：
							</th>
							<td colspan="2">
								${builderForm.adress}
							</td>
						</tr>
						<tr>
							<th bgcolor="#edfaff" align="right">
								法定代表人：
							</th>
							<td colspan="2" >
								${builderForm.fddbr}
							</td>
						</tr>
						<tr>
							
							<th bgcolor="#edfaff" align="right">
								法定代表人联系电话：
							</th>
							<td colspan="2">
								
									${builderForm.fddbrlxdh}
							</td>
						
						</tr>
						
						<tr>
							<th bgcolor="#edfaff" align="right">
								环保负责人：
							</th>
							<td colspan="2">
                                ${builderForm.hbfzr}
									
							</td>
						</tr>
						<tr>
							<th bgcolor="#edfaff" align="right">
								环保负责人联系电话：
							</th>
							<td colspan="2">
								
									${builderForm.hbfzrlxdh}
							</td>
						</tr>
						
						<tr>
							<th bgcolor="#edfaff" align="right">
								是否有效：
							</th>
							<td colspan="2">
								
							<c:if test="${builderForm.isActive=='Y'}">有效</c:if>
						     <c:if test="${builderForm.isActive=='N'}">无效</c:if>
							</td>
						</tr>
						
					</table>
				</form>
			</div>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">
function guanBi(){
	//self.close();	
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	parent.layer.close(index);
};
	$(document).ready(function() {
		$.ajaxSetup({cache:false});
		if($('#id').val()!=""){
        	$('#zt').attr('value',$('#yczt').val());
           }
		
		
		$('#zt').combobox({
		url:'ztList.json',
		valueField:'id',
		textField:'name'
	    });
		
		
		
		$("#J-from-reset").click(function() {
			$("#queryForm").form('reset');
			
		});
	
		
	
	});
</SCRIPT>