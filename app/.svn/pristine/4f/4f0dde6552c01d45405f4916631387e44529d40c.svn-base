<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title }</title>
<!--poshytip-->
<link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<!--jQuery-->
<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/easyUIReset.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<!--时间插件 my97-->
<script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
<!--poshytip-->
<link rel="stylesheet" href="${poshytip}/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${app}/js.js"></script>
<script type="text/javascript" src="${common}/All.js"></script>
<script src="${jquery}/echarts.js" charset="utf-8" type="text/javascript"></script>
<link href="${app}/css/resetCSS.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />
<!--zTree-->
<link rel="stylesheet" href="${ztree}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<link rel="stylesheet" href="${app}/css/zTreeReset.css" type="text/css" />
<script type="text/javascript" src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
<script src="${jquery}/echarts.js" charset="utf-8" type="text/javascript"></script>
<style type="text/css">
	.leftTree {
	    height: 780px !important; 
	}
	.rightCon {
	    height: 780px !important; 
	}
	.lrDiv {
	    position: relative;
	    margin: -70px 70px 40px;
	}
	.sttsTab {
	    width: 1110px;
	    text-align: center;
	    padding: 20px 0px 20px;
	    margin: 0px auto;
	    position: relative;
	}
</style>
</script>
</head>
	<body>
	<div id="title" class="breadClumbs"> 工作量统计&nbsp;&gt;&nbsp;${title}</div>
	<h1 class="h1"></h1>
	<div class="lrDiv clearfix">
		<div class="leftTree"><ul id="treeDemo" class="ztree" ></ul></div>
	    <div class="rightCon">
			<div class="sttsTab clearfix"> 
				<span>
					<a class="cur" onclick="showTab(this,'con1')">按月统计</a>
					<a onclick="showTab(this,'con2')">按季度统计</a>
				</span>
				<span>
					<th width="11.3%">年份</th>
       				<td width="22%"><input type="text" class="y-text" id="yearChoose" name="yearChoose"/></td>
				</span>
			</div>
			<div class="sttsTab_con" id="con1">
				<input type="hidden" id="fid" name="fid" value="${areaid}" />
				<input type="hidden" id="fid" name="fid" value="${userId}" />
				<div id="rw" style="width: 450px;height:250px;float:left;"></div>
				<div id="jc" style="width: 450px;height:250px;float:left;"></div>
				<div id="dl" style="width: 1000px;height:400px;float:left;"></div>
				<script type="text/javascript">
					var date = ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'];
					var myChartDl = echarts.init(document.getElementById('dl'));
					var myChartRw = echarts.init(document.getElementById('rw'));
					var myChartJc = echarts.init(document.getElementById('jc'));
					optionDl = {
							legend: {
							data:['登录次数']
						},
						tooltip: {
							trigger: 'axis'
						},
						grid: {
							left: '3%',
							right: '4%',
							bottom: '3%',
							containLabel: true
						},
						
						xAxis: {
							type: 'category',
							boundaryGap: false,
							data: date
						},
						yAxis: {
							type: 'value'
						},
						series: [
							{
								name:'登录次数',
								type:'line',
								stack: '总量',
								data:[]
							}
						   
						]
					};
	
	
					optionRw = {
						legend: {
							data:['办理任务数']
						},
						tooltip: {
							trigger: 'axis'
						},
						grid: {
							left: '3%',
							right: '4%',
							bottom: '3%',
							containLabel: true
						},
						
						xAxis: {
							type: 'category',
							boundaryGap: false,
							data: date
						},
						yAxis: {
							type: 'value'
						},
						series: [
							{
								name:'办理任务数',
								type:'line',
								stack: '总量',
								data:[]
							}
						   
						]
					};
	
					optionJc = {
						legend: {
							data:['监察笔记分数']
						},
						tooltip: {
							trigger: 'axis'
						},
						grid: {
							left: '3%',
							right: '4%',
							bottom: '3%',
							containLabel: true
						},
						
						xAxis: {
							type: 'category',
							boundaryGap: false,
							data: date
						},
						yAxis: {
							type: 'value'
						},
						series: [
							{
								name:'监察笔记分数',
								type:'line',
								stack: '总量',
								data:[]
							}
						   
						]
					};
	
					/* myChartDl.setOption(optionDl);
					myChartRw.setOption(optionRw);
					myChartJc.setOption(optionJc); */
				</script>
			</div>
			<div class="sttsTab_con" id="con2" style="display:none;">
				<div id="rw2" style="width: 500px;height:250px;float:left;"></div>
				<div id="jc2" style="width: 500px;height:250px;float:left;"></div>
				<div id="dl2" style="width: 1000px;height:400px;float:left;"></div>
				<script type="text/javascript">
					var date = ['第一季度','第二季度','第三季度','第四季度'];
					var myChartDl2 = echarts.init(document.getElementById('dl2'));
					var myChartRw2 = echarts.init(document.getElementById('rw2'));
					var myChartJc2 = echarts.init(document.getElementById('jc2'));
					optionDl2 = {
					    color: ['#3398DB'],
					    tooltip : {
					        trigger: 'axis',
					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					        }
					    },
					    grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    },
					    xAxis : [
					        {
					            type : 'category',
					            data : date,
					            axisTick: {
					                alignWithLabel: true
					            }
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value'
					        }
					    ],
					    series : [
					        {
					            name:'登录次数',
					            type:'bar',
					            barWidth: '60%',
					            data:[]
					        }
					    ]
					};

	
					optionRw2 = {
					    color: ['#3398DB'],
					    tooltip : {
					        trigger: 'axis',
					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					        }
					    },
					    grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    },
					    xAxis : [
					        {
					            type : 'category',
					            data : date,
					            axisTick: {
					                alignWithLabel: true
					            }
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value'
					        }
					    ],
					    series : [
					        {
					            name:'办理任务数',
					            type:'bar',
					            barWidth: '60%',
					            data:[]
					        }
					    ]
					};
	
					optionJc2 = {
							    color: ['#3398DB'],
					    tooltip : {
					        trigger: 'axis',
					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					        }
					    },
					    grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    },
					    xAxis : [
					        {
					            type : 'category',
					            data : date,
					            axisTick: {
					                alignWithLabel: true
					            }
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value'
					        }
					    ],
					    series : [
					        {
					            name:'监察笔记分数',
					            type:'bar',
					            barWidth: '60%',
					            data:[]
					        }
					    ]
					};
	
				</script>
			</div>
	    </div>
	</div>
	<script type="text/javascript">
	//高度设置
	function initHH(){
		var hh=$(window).height()-98-50;
		$(".leftTree, .rightCon").height(hh+"px");
		$("#dataDiv").height((hh-92-95-18-20)+"px");
	}
	initHH();
	
	/*==========查询条件的展开与折叠============*/
	$("#searchDiv").height("95px");
	$("#moreSearch").click(function(){
		if($(this).hasClass("show")){//展开变折叠
			$(this).removeClass("show").text("展开查询条件");
			$("#searchDiv").height("95px");
		}else{//折叠变展开
			$(this).addClass("show").text("折叠查询条件");
			$("#searchDiv").height("auto");
		}
		
	});
	
	
	
	//左侧树
	var setting = {
		view: {
			showLine: false
		},
		data: {
			simpleData: {
				idKey: "id", 
				pIdKey: "pid",  
				enable: true
			}
		},
		callback: {
			onClick : onClick
		}
	};

	var zNodes =${array};
	var tid = 'con1';
	var areaid = $("#fid").val();
	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		$("#yearChoose").combobox({   
			valueField:'year',    
			textField:'year',  
			panelHeight:'auto',
			onSelect:function(params){ 
				loadData(areaid,tid,params.year);
         	} 
		});
		var data = [];//创建年度数组
		var startYear;//起始年份
		var thisYear=new Date().getUTCFullYear();//今年
		var endYear=thisYear+1;//结束年份
		for(startYear=endYear-4;startYear<=thisYear;startYear++){
	        data.push({"year":startYear});
	    }
		$("#yearChoose").combobox("loadData", data);//下拉框加载数据
		$("#yearChoose").combobox("setValue",thisYear);//设置默认值为今年
		var time = $('#yearChoose').combobox('getValue');
		
		loadData(areaid,tid,time);
	});
	
	function showTab(t, id) {
			$(t).addClass("cur").siblings("a").removeClass("cur");
			$(".sttsTab_con").hide();
			$("#" + id).show();
			tid = id;
			var time = $('#yearChoose').combobox('getValue');
			loadData(areaid,id,time);
	}
	//树的点击事件加载
	function onClick(event, treeId, treeNode) {
		areaid = treeNode.id;
		$("#lawobjtypeid").val(areaid);
		var time = $('#yearChoose').combobox('getValue');
		loadData(areaid,tid,time);
	}
	
	function loadData(id,cid,time){
		$.ajax({
			 type: "GET",
	         url: "queryEachStatisticsList.json",
	         data: {
	         	areaid:id,
	         	date:time,
	         	cid:cid
	         	},
	         dataType: "json",
	         success: function(result){
	        	 if(tid == 'con1'){
					optionDl.series[0].data = result.emList.split(",");
	        	 	myChartDl.setOption(optionDl);
	        	 	optionRw.series[0].data = result.emrw.split(",");
					myChartRw.setOption(optionRw);
					optionJc.series[0].data = result.embl.split(",");
					myChartJc.setOption(optionJc);
	        	 }else{
	        	 	optionDl2.series[0].data = result.emList.split(",");
	        	 	myChartDl2.setOption(optionDl2);
	        	 	optionRw2.series[0].data = result.emrw.split(",");
					myChartRw2.setOption(optionRw2);
					optionJc2.series[0].data = result.embl.split(",");
					myChartJc2.setOption(optionJc2);
	        	 }
			 }
		}); 
	}
</script>
	
</body>
</html>
