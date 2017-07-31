<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择特殊监管企业年限和季度</title>
<script type="text/javascript" src="${app}/js.js"></script>
<script type="text/javascript" src="${app}/data.js"></script>
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css">
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css"	type="text/css" />
<script type="text/javascript" src="${poshytip}/jquery.poshytip.min.js"></script>
<link rel="stylesheet" href="${colorbox}/colorbox.css" type="text/css">
<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
<script type="text/javascript" src="${common}/All.js"></script>
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div align="center" style="font-size:16px; padding-top:10px;">按年份和季度选择特殊监管企业</div>
		<table class="dataTable" width="100%"  bordercolor="#d4d4d4" cellpadding="0" cellspacing="0">
			<tr>
				       <th width="60px;">
							年份：
						</th>
						<td width="120px;">
							<input class="y-text" type="text" id="year" name="year"/>
						</td>
						
			</tr>
			<tr>
			            <th width="60px;">
							季度：
						</th>
						<td width="150px;">
							<input class="y-text" type="text" id="quarter" name="quarter"/>
						</td>
			</tr>
		</table>
		<div class="t-c" style="margin-top:10px">
			<input id="save" class="queryBlue" type="button" value="确定" />
			
		</div>
	<script type="text/javascript">
	
	$(document).ready(function() {
		
		//年度下拉
		var year=new Date().getFullYear(); 
		$('#year').combobox({
			height:34,
			data:[{'id':year,'name':year+'年'},{'id':year+1,'name':year+1+'年'},
				  {'id':year+2,'name':year+2+'年'},{'id':year+3,'name':year+3+'年'},
				  {'id':year+4,'name':year+4+'年'}],
			editable:false,
			valueField:'id',
			textField:'name'
		});
		//季度下拉
		$('#quarter').combobox({
			height:34,
			url:'quarterList.json',
			editable:false,
			valueField:'id',
			textField:'name'
		});

		
		
		});
	
$('#save').click(function(){
	var year = $('#year').combobox('getValue');
	var quarter = $('#quarter').combobox('getValue');
		if(year==""){
			alert("请选择年份！");
			return false;
		}
		if(quarter==""){
			alert("请选择季度！");
			return false;
		}
		var href='jumpSpecialLawobjAdd.htm?year='+year+'&quarter='+quarter;
		var title='添加特殊监管企业';
		var width=screen.width * 0.8;
	  	var height=screen.height * 0.8-50;
		//parent.parent.layerIframe(2,href,title,width,height);
		parent.parent.layer.open({
            type: 2,
            title: title,
            area: [width+'px', height+'px'],
            content: href, 
            end: function () {
                //location.reload();
            	refresh();
            }

        });
		//All.ShowModalWin('jumpSpecialLawobjAdd.htm?year='+year+'&quarter='+quarter, '添加特殊监管企业');
	});
function refresh(){
	parent.refresh();
}

	</script>
</body>
</html>
