<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title }</title>
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<!--poshytip-->
<link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
<!--jQuery-->
<script type="text/javascript" src="${app}/js.js"></script>
<script type="text/javascript" src="${app}/data.js"></script>
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${layer}/layer.js"></script>
<script src="${jquery}/echarts.js"></script>
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<!--时间插件 my97-->
<script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
<script type="text/javascript" src="${common}/All.js"></script>
<style>
caption, th {text-align: center;}
</style>
</head>

<body>
<div class="breadClumbs"> 查询统计&nbsp;&gt;&nbsp;${title}</div>
<div id="searchForm">
   <form id="queryForm" action="lawobjtypeStatistics.htm" method="post">
   <input type="hidden" class="y-text" id="list" name="list" value="${list}" />
   <table class="queryTable" width="98%" border="0" cellpadding="0" cellspacing="0" style=" margin:0 auto">
     <tr>
       <th width="8%">区域:</th>
       <td width="12%"><input type="text" class="y-text" id="areaid" name="areaid" value="${areaid}" /></td>
       <th width="8%">年份:</th>
       <td width="12%">
		<input class="y-text" type="text" id="year" name="year" value="${year}"/>
		<input class="y-text" type="hidden" id="selectyear" name="selectyear" value="${year}"/>
       </td>
       <th width="8%">季度:</th>
       <td width="12%">
		<input class="y-text" type="text" id="quarter" name="quarter" value="${quarter}"/>
       </td>
       <td width="150px">
        <input type="submit" id="query"  class="queryBlue" value="查　询" onclick="hideSearchForm()"/>
        
       </td>
       <td width="150px">
       	<input type="reset" id="J-form-reset" class="queryOrange" value="重　置"/>
       </td>
     </tr>
   </table>
   </form>
  
</div>
<div class="dataDiv" style="width:99%; margin:26px auto 25px;">
   <div id="main" style="width:99%;height:400px;"></div>
</div>
</body>
</html>


<script language="JavaScript">
////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////
 var myChart = echarts.init(document.getElementById('main'));
$(document).ready(function(){
	//区域
	$('#areaid').combobox({
		height:34,
		url : 'queryAreaComboWithCur.json?fid='+Math.random(),
		valueField : 'id',
		textField : 'name',
		onSelect: function (record) {//做区域部门的联动选择
        }
	});
	//年度下拉
	var year=new Date().getFullYear(); 
	$('#year').combobox({
		height:34,
		data:[{'id':year-2,'name':year-2+'年'},{'id':year-1,'name':year-1+'年'},{'id':year,'name':year+'年'},{'id':year+1,'name':year+1+'年'},
			  {'id':year+2,'name':year+2+'年'},{'id':year+3,'name':year+3+'年'},
			  {'id':year+4,'name':year+4+'年'}],
		editable:false,
		valueField:'id',
		textField:'name',
		onSelect: function(param){
		}
	});
	//var selectYear=$('#selectyear').val();
	//alert(selectYear);
	//$('#year').combobox('setValue', selectYear);
	//季度下拉
	$('#quarter').combobox({
		height:34,
		data:[{'id':"0",'name':"全部"},{'id':"1",'name':"第一季度"},
		      {'id':"2",'name':"第二季度"},{'id':"3",'name':"第三季度"},
		      {'id':"4",'name':"第四季度"}
		      ],
		editable:false,
		valueField:'id',
		textField:'name',
		onSelect: function(param){
		}
	});
	$("#questionContainer").width($(window).width());
	//echars...
	  // 基于准备好的dom，初始化echarts实例
   
	initData();
	
});

function initData(){
	var data=$("#list").val();
	var arrs=new Array();
	arrs=data.split(";");
	var a;
	var array=new Array();
	var names=new Array();
	for(var i=0;i<arrs.length;i++){
		a=arrs[i].split(",");
		var laws ={};
		// 先来创建一个js对象  
		names[i]=a[1];
	    laws.name = a[1];   // 我们给这个对象名字，name 为饼图的name   
	    laws.value = a[0];   // 我们给这个对象值，value为饼图的value  
		array[i] = laws;  
		
	}
	 // 指定图表的配置项和数据
    var option = {
    	    title : {
    	        text: '企业类型',
    	        x:'center'
    	    },
    	    tooltip : {
    	        trigger: 'item',
    	        formatter: "{a} <br/>{b} : {c} ({d}%)"
    	    },
    	    legend: {
    	        orient: 'vertical',
    	        left: 'left',
    	        data: names,
    	        textStyle:{
    	        	fontSize : 16
    	        }
    	        
    	    },
    	    color:['#ff7f50','#87cefa','#da70d6','#32cd32','#6495ed',
    	            '#ff69b4','#ba55d3','#cd5c5c','#ffa500','#40e0d0',
    	            '#1e90ff','#ff6347','#7b68ee',
    	            '#6699FF','#ff6666','#3cb371','#b8860b','#30e0e0'],
    	    series : [
    	        {
    	            name: '企业类型',
    	            type: 'pie',
    	            radius : '75%',
    	            center: ['50%', '55%'],
    	            data:array,
    	            label : {
    	            	normal : {
    	            	
    	            	textStyle : {
    	            	fontWeight : 'normal',
    	            	fontSize : 16
    	            	}
    	            	}
    	            	}, 
    	            itemStyle: {
    	            	 normal: {
    	            		  label:{ 
    	                          show: true, 
    	                          formatter: '{b} : {c} ({d}%)' 
    	                        }, 
    	                      labelLine :{show:true}
    	                      
    	                   },
    	                emphasis: {
    	                    shadowBlur: 10,
    	                    shadowOffsetX: 0,
    	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
    	                }
    	            }
    	        }
    	    ]
    	};
    //option.series[0].data = array; 

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
	
}

	
$('#J-from-reset').click(function(){
	$("#queryForm").form("clear");
	var date = new Date();
	var year = parseInt(date.getFullYear());
	var month = parseInt(date.getMonth() + 1);
	//前一个季度
	var quarter = 4;
	if(month>=4 && month<=6){
		quarter = 1;
	}else if(month>=7 && month<=9){
		quarter = 2;
	}else if(month>=10 && month<=12){
		quarter = 3;
	}
	$('#year').combobox('setValue', year);
	$('#quarter').combobox('setValue', quarter);
});

$('#J-form-export').click(function(){
	var year = $('#year').combobox('getValue');
	var quarter = $('#quarter').combobox('getValue');
	var areaid = $('#areaid').combotree("getValue");
});

</script>
