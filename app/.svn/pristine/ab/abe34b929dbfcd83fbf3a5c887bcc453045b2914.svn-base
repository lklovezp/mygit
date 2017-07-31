/*
 * 	rent		//渲染控件id		
 *  name        //标题
 *  data		//数据源，格式["名称",数量]
 * 	seriesName  //光标放在饼图模块上提示数据的标题
 *  url  		//点击饼图模块跳转的url，参数hName：搜索条件的值；hValue：饼图模块的值；
 * 	colName 	//搜索条件的值
*/
function highChartPie(rent,name,data,seriesName,url,colName){
	var chartvalue = {
		chart: {
				renderTo: rent, 
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: name
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.y}</b>',
            	percentageDecimals: 1
            },
              credits:{
                enabled: false //是否显示官网水印
        	},
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    size:'75%',
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        formatter: function() {
                            return '<b>'+ this.point.name +'</b>: '+ this.point.y;
                        }
                    }
                }
            },
            series: [{
                type: 'pie',
                name: seriesName,
                data: data,
                cursor: 'pointer', 
	            events: { 
	                click: function(e) { 
	                    parent.$.colorbox({iframe:true,width:"80%", height:"98%",href:url+"?hName="+colName+"&hValue="+e.point.name});
	                } 
	            } 
            }]
	};
	return chartvalue;
};

	