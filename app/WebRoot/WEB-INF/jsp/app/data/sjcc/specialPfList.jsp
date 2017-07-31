<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${layer}/layer.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/icon.css">
		<link rel="stylesheet" type="text/css"
			href="${easyui}/themes/default/easyui.css">
		<script type="text/javascript"
			src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
		<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
		<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
        <link href="${app}/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${jquery}/json2.js"></script>
		<style type="text/css">
			.panel-header, .panel-body {
    			border-color: #acacac;
			}
			.panel-header {
			    padding: 0px;
			}
			.panel-title {
    			height: 48px;
				line-height: 48px;
				background: #cff1ff;
				border: 1px solid #D4D4D4;
				text-indent: 18px;
				border-right: 1px;
				border-bottom: 0px;
				font-size: 14px;
				color: #262626;
			}
			.datagrid-toolbar, .datagrid-pager {
   				 background: #FFFFFF;
			}
			.datagrid-htable, .datagrid-header-row {
   			 background-color: #ffffff;
			}
		</style>
	</HEAD>

	<body>
		<div class="breadClumbs"> 系统管理&nbsp;&gt;&nbsp;${title}</div>
	    <div id="title" style="width:98%;font-size:16px;margin:10px auto 10px;"align="center">${title}</div>
		<div class="dataDiv" style="border-right: 1px;width: 98%;padding: 0px 0px 0px 14px;">
			<form id="myform"  name="myform" method="post">
				<input type="hidden" value="${configPfForm.id}" id="id" name="id">
				<input type="hidden" value="" id="data" name="data">
				<input type="hidden" value="2" id="type" name="type">
				<input type="hidden"  id="tssz" name="tssz" value="${tssz}">
				<table class="formtable" width="100%">
					<tr>
					    <th><font color="red"> * </font>区域：</th>
						<td><input class="y-text" data-options="required:true" id="areaid" name="areaid"
							type="text" value="${areaid}" />
						</td>
						<th>
							<font color="red"> * </font>年份：
						</th>
						<td>
							<input class="y-text" type="text" data-options="required:true" id="year" name="year" vale=""/>
						</td>
						<th>
							<font color="red"> * </font>季度：
						</th>
						<td>
							<input class="y-text" type="text" data-options="required:true" style="width: 120px;" id="quarter" name="quarter" value="${configPfForm.quarter}"/>
						</td>
						<td>
							<div id="showMonth">
							<input class="y-text" type="text" style="width: 80px;" id="month" name="month" value="${configPfForm.month}"/>
						    </div>
						</td>
						
						<th>
							<font color="red"> * </font>办结时限：
						</th>
						<td>
							<input class="y-text easyui-numberbox" style="width: 50px;" data-options="required:true" maxlength="2" type="text" id="bjsx" name="bjsx"
								value="${configPfForm.bjsx}"/>天
						</td>
					</tr>
				</table>	
					<div class="divContainer">
						<table id="functionOp" width="100%" title="添加菜单对应的操作，例如：增加，删除" style="height: 280px" iconCls="icon-edit">
						</table>
					</div>
					<div class="footerBtn t-c" id="bottomBtn" style="margin-top: 10px;">
       					<input type="submit" class="queryBlue" value="提　交"/>
       					<!--  
       					<input type="reset"  class="queryOrange"  id="J-from-reset" value="重　置"/>
       					 -->
    				</div>
				
			</form>
		</div>
	</body>
</HTML>
<SCRIPT LANGUAGE="JavaScript">

var lastIndex=0;
var dataG;
var curmonth;
$(document).ready(function(){
	
	if($('#tssz').val()=='1'){
		document.getElementById("showMonth").style.display="none";
	}
	
	//区域
	$('#areaid').combobox({
		height:34,
		url : 'queryAreaComboWithCur.json?fid='+Math.random(),
		valueField : 'id',
		textField : 'name',
		onSelect: function (param) {//做区域部门的联动选择
			$('#functionOp').datagrid('endEdit', lastIndex);
			$.ajax({
				url: 'speciallConfigPfList.json?year='+$('#year').combobox('getValue')+'&quarter='+$('#quarter').combobox('getValue')+'&areaid='+param.id+'&type='+'2', 
				type: "post", 
				success: function(data){
					var s=0;
					if(data.length>0){
						s=data[0].bjsx;
					}
					//$('#bjsx').val();
					$('#bjsx').numberbox('setValue', s);
				$('#functionOp').datagrid('loadData', data);
		      }});
        }
	});
	
	//年度下拉
	var year=new Date().getFullYear(); 
	$('#year').combobox({
		height:34,
		data:[{'id':year,'name':year+'年'},{'id':year+1,'name':year+1+'年'},
			  {'id':year+2,'name':year+2+'年'},{'id':year+3,'name':year+3+'年'},
			  {'id':year+4,'name':year+4+'年'}],
		editable:false,
		valueField:'id',
		textField:'name',
		onSelect: function(param){
			$('#functionOp').datagrid('endEdit', lastIndex);
			$.ajax({
				url: 'speciallConfigPfList.json?year='+param.id+'&quarter='+$('#quarter').combobox('getValue')+'&areaid='+$('#areaid').combobox('getValue')+'&type='+'2', 
				type: "post", 
				success: function(data){
					var s=0;
					if(data.length>0){
						s=data[0].bjsx;
					}
					//$('#bjsx').val();
					$('#bjsx').numberbox('setValue', s);
				$('#functionOp').datagrid('loadData', data);
		      }});

			
		}
	});
	$('#year').combobox('setValue', year);
	//季度下拉
	$('#quarter').combobox({
		height:34,
		url:'quarterList.json',
		editable:false,
		valueField:'id',
		textField:'name',
		onSelect: function(param){
			$("#month").combobox('clear');
			$.ajax({
				url: 'configCheckTszz.json?year='+$('#year').combobox('getValue')+'&quarter='+$('#quarter').combobox('getValue')+'&areaid='+$('#areaid').combobox('getValue'), 
				type: "post", 
				success: function(data){
				if(data.tszz=="1"){
					document.getElementById("showMonth").style.display="none";
					$('#functionOp').datagrid('endEdit', lastIndex);
					//按月回显
					$.ajax({
						url: 'speciallConfigPfList.json?quarter='+param.id+'&year='+$('#year').combobox('getValue')+'&areaid='+$('#areaid').combobox('getValue')+'&type='+'2', 
						type: "post", 
						success: function(data){
							var s=0;
							if(data.length>0){
								s=data[0].bjsx;
							}
							//$('#bjsx').val();
							
							
							$('#bjsx').numberbox('setValue', s);
						$('#functionOp').datagrid('loadData', data);
				      }});
				}else{
					//按月设置
					document.getElementById("showMonth").style.display="block";
					var url = 'monthList.json?quarter='+param.id;
					//alert(url);
					$("#month").combobox('reload',url);
					curmonth=getQuarterSeasonStartMonth(param.id);
					$('#month').combobox('setValue',curmonth);
					//$('#month').combobox('setValue', year);
				
					$('#functionOp').datagrid('endEdit', lastIndex);
					//按月回显
					$.ajax({
						url: 'speciallConfigPfList.json?quarter='+param.id+'&year='+$('#year').combobox('getValue')+'&areaid='+$('#areaid').combobox('getValue')+'&month='+curmonth+'&type='+'2', 
						type: "post", 
						success: function(data){
							var s=0;
							if(data.length>0){
								s=data[0].bjsx;
							}
							//$('#bjsx').val();
							
							
							$('#bjsx').numberbox('setValue', s);
						$('#functionOp').datagrid('loadData', data);
				      }});
				}
		      }});
			
			
			//alert();
			//$('#month').combobox('setValue',$("#month").combobox('getData')[0].id);
			//$('#month').combobox('reload', url);
		}
	});
	$('#month').combobox({
		url:'monthList.json?quarter='+$('#quarter').combobox('getValue'),
		editable:false,
		valueField:'id',
		textField:'name',
		height:34,
		onSelect: function(param){
			$('#functionOp').datagrid('endEdit', lastIndex);
			$.ajax({
				url: 'configPfList.json?month='+param.id+'&year='+$('#year').combobox('getValue')+'&quarter='+$('#quarter').combobox('getValue')+'&areaid='+$('#areaid').combobox('getValue')+'&type='+'2', 
				type: "post", 
				success: function(data){
					var s=0;
					if(data.length>0){
						s=data[0].bjsx;
					}
					//$('#bjsx').val();
					$('#bjsx').numberbox('setValue', s);
				$('#functionOp').datagrid('loadData', data);
		      }});
		}
	});
	function getQuarterSeasonStartMonth(quarter) {
        var spring = "1"; //春  
        var summer = "4"; //夏  
        var fall = "7";   //秋  
        var winter = "10"; //冬  
        //月份从0-11  
        if (quarter == "1") {
            return spring;
        }

        if (quarter == "2") {
            return summer;
        }

        if (quarter == "3") {
            return fall;
        }
		if(quarter == "4"){
			return winter;
		}
        
    }


	//自定义datetimebox
	$.extend($.fn.datagrid.defaults.editors, {
	    datetimebox: {// datetimebox就是你要自定义editor的名称
	        init: function (container, options) {
	            var input = $('<input class="easyuidatetimebox">').appendTo(container);
	            return input.datetimebox({
	                formatter: function (date) {
	                    return new Date(date).format("yyyy-MM-dd hh:mm:ss");
	                }
	            });
	        },
	        getValue: function (target) {
	            return $(target).parent().find('input.combo-value').val();
	        },
	        setValue: function (target, value) {
	            $(target).datetimebox("setValue", value);
	        },
	        resize: function (target, width) {
	            var input = $(target);
	            if ($.boxModel == true) {
	                input.width(width - (input.outerWidth() - input.width()));
	            } else {
	                input.width(width);
	            }
	        }
	    }
	});
	// 时间格式化
	Date.prototype.format = function (format) {
	    if (!format) {
	        format = "yyyy-MM-dd hh:mm:ss";
	    }

	    var o = {
	        "M+": this.getMonth() + 1, // month
	        "d+": this.getDate(), // day
	        "h+": this.getHours(), // hour
	        "m+": this.getMinutes(), // minute
	        "s+": this.getSeconds(), // second
	        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
	        "S": this.getMilliseconds()
	        // millisecond
	    };

	    if (/(y+)/.test(format)) {
	        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    }

	    for (var k in o) {
	        if (new RegExp("(" + k + ")").test(format)) {
	            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
	        }
	    }
	    return format;
	};
	
	//自定义结束
	//加载列表页数据
	dataG = $('#functionOp').datagrid({
		url:'speciallConfigPfList.json?year='+$('#year').combobox('getValue')+'&quarter='+$('#quarter').combobox('getValue')+'&month='+$('#month').combobox('getValue')+'&type='+'2',
		toolbar:[{
			text:'添加',
			iconCls:'icon-add',
			handler:function(){
				
					$('#functionOp').datagrid('endEdit', lastIndex);
					$('#functionOp').datagrid('appendRow',{
						id:'',
						pfsj:'',
						pfbl:'',
						sfypf:''
						
					});
					lastIndex = $('#functionOp').datagrid('getRows').length-1;
					$('#functionOp').datagrid('selectRow', lastIndex);
					$('#functionOp').datagrid('beginEdit', lastIndex);
				
				
			}},{
			text:'删除',
			iconCls:'icon-remove',
				handler:function(){
				var row = $('#functionOp').datagrid('getSelected');
				if (row){
					var index = $('#functionOp').datagrid('getRowIndex', row);
					$('#functionOp').datagrid('deleteRow', index);
				}
				}},{
			text:'删除全部',
			iconCls:'icon-remove',
			handler:function(){
				var ll = $('#functionOp').datagrid('getRows').length - 1;
				for(i = ll; i >= 0;i--){
					$('#functionOp').datagrid('deleteRow', i);
				}
			},
		}],
		fitColumns : true,
		singleSelect : true,
		columns : [ [ 
						{field : 'pfsj', title : '派发时间',editor:{type : 'datetimebox'}, align : 'center', width : 100},
						{field : 'pfbl', title : '派发比例(%)',editor:{type : 'numberbox'}, align : 'center', width : 100},
						{field : 'sfypf', title : '是否已派发',editor:{type : 'string'}, align : 'center', width : 50}
					] ],
		onClickRow:function(rowIndex,rowData){
			if (rowData.sfypf=="是"){
				$('#functionOp').datagrid('endEdit', rowIndex);
				//$('#functionOp').datagrid('beginEdit', rowIndex);
			}else{
				$('#functionOp').datagrid('beginEdit', rowIndex);
			}
			
			//lastIndex = rowIndex;
		}
	});
	$("#J-from-reset").click(function(){
		$("#myform").reset();
	});
	
});

//获取选中季度的第一天和最后一天
var firstdate;
var lastdate;
function getFirstAndLastQuarterDay(year,quarter) {
 if(quarter=="1"){
  firstdate = year + '/' + 01 + '/01';
  var day = new Date(year,3,0);
  lastdate = year + '/' + 03 + '/' + day.getDate();//获取第一季度最后一天日期
 }else if(quarter=="2") {
  firstdate = year + '/' + 04 + '/01';
  var day = new Date(year,6,0);
  lastdate = year + '/' + 06 + '/' + day.getDate();//获取第二季度最后一天日期     
 } else if(quarter=="3") {
  firstdate = year + '/' + 07 + '/01';
  var day = new Date(year,9,0);
  lastdate = year + '/' + 09 + '/' + day.getDate();//获取第三季度最后一天日期
 } else if(quarter=="4") {
  firstdate = year + '/' + 10+ '/01';
  var day = new Date(year,12,0);
  lastdate = year + '/' + 12 + '/' + day.getDate();//获取第四季度最后一天日期
 }
} 
//js 将(yyyy/mm/dd)格式的字符日期减去dadd天
function addDate(dd,dadd){
	var a = new Date(dd)
	a = a.valueOf()
	a = a - dadd * 24 * 60 * 60 * 1000
	a = new Date(a)
	return a;
}
//js 强日期转换成(yyyy-mm-dd)
function DtoS (date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? '0' + m : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    return y + '-' + m + '-' + d;
};

//js日期比较(yyyy-mm-dd)

function duibi(a, b) {
   var arr = a.split("-");
   var starttime = new Date(arr[0], arr[1], arr[2]);
   var starttimes = starttime.getTime();

   var arrs = b.split("-");
   var lktime = new Date(arrs[0], arrs[1], arrs[2]);
   var lktimes = lktime.getTime();
   if (starttimes > lktimes) {
       return true;
   }else{
	   return false;
   }
       

}




//表单校验
$("#myform").validate({
	errorClass: "error",
	submitHandler:function(form){
		dataG.datagrid('acceptChanges');
		if($("#myform").form("validate")){
			  //调用Juqery Ajax 获取最低派发比例
			  var zs;
			  var validData = dataG.datagrid('getData');
				// 删除空行
				for(i = validData.rows.length - 1; i >= 0; i--){
					if (validData.rows[i].pfsj == "" || validData.rows[i].pfbl == ""){
						dataG.datagrid('deleteRow', i);
					}
				}
			   curmonth=$('#tssz').val();
			   if(curmonth=='1'){
				   if($('#quarter').combobox('getValue')=="" || $('#quarter').combobox('getValue')==null){
					   alert("请选择月份！");
					   return false;
				   }
			   }
	           $.ajax({
	           type: "POST",
	           url: "queryLowPfbl.json",
	           error: function(){alert('error');},
	           data: "year="+$('#year').combobox('getValue')+"&quarter="+$('#quarter').combobox('getValue')+"&areaid="+$('#areaid').combobox('getValue'),
	           success: function(msg){
	        	   getFirstAndLastQuarterDay($('#year').combobox('getValue'),$('#quarter').combobox('getValue'));
	        	   var pfFirstDate=addDate(firstdate,$('#bjsx').val());
	        	   var pfLastDate=addDate(lastdate,$('#bjsx').val());
	        	   var fistD=DtoS (pfFirstDate);
	        	   var listD=DtoS (pfLastDate);
	        	   var tsqybl=msg.tsqybl;
			        	zs=parseInt(msg.zs);
			        	 //判断设置派发比例是否为100%
			   			var chengedData = dataG.datagrid('getData');
			   			var s=0;
			   			for(var i = 0;i < chengedData.rows.length; i++){
			   				var pfsj=chengedData.rows[i].pfsj.substr(0,10);
			   				var t1=duibi(listD,pfsj);
			   				var t2=duibi(pfsj,fistD);
			   				if( t1 && t2){
			   					
			   				}else{
			   					alert("办结时间不在所选季度内！");
			   					return false;
			   				}
			   				
			   				if(tsqybl=="2"){
				   				if(parseInt(chengedData.rows[i].pfbl)<zs){
				   					alert("派发比例设置不得低于:"+zs);
				   					return false;
				   				}
			   				}
			   				s+=parseInt(chengedData.rows[i].pfbl);
			   			}
			   			if(s>100 || s<100){
			   				alert("派发比例设置不是100%");
			   				return false;
			   			}
			   			var gridData = dataG.datagrid('getData');
						var data = JSON.stringify(gridData);
						$('#data').val(data);
						$(form).ajaxSubmit({
							type:"post",
							dataType:"json",
							url:"specliallConfigPfSave.json",
							success: function(data){
								var tishi=layer.alert(data.msg,{
							     	title:'信息提示',
							        icon:1,
							        shadeClose:true,
							     },
							     function(){
							        layer.close(tishi);
							     }
							     );
								//$('#functionOp').datagrid('reload');
							}
						});   
	        	   
	        	   
	               }
	           });
			
	          

			
			
			/**
			
			for(i = chengedData.rows.length - 1; i >= 0; i--){
				if (validData.rows[i].linkAddr == ""){
					alert("请为操作添加对应链接。");
					$('#functionOp').datagrid('beginEdit', i);
					return;
				}
			}
			*/
			
		}	
	}
});
</SCRIPT>