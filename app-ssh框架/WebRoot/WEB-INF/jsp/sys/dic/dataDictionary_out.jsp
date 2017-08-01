<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>数据字典——发文</title>
	<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
    <script src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script> 
    <!--tableTree-->
    <link rel="stylesheet" href="${tabletree4j}/tabletree4j.css"/>
    <script type="text/javascript" src="${tabletree4j}/Core4j-debug.js"></script>
    <script type="text/javascript" src="${tabletree4j}/Core4j.js"></script>
    <script type="text/javascript" src="${tabletree4j}/TableTree4j.js"></script>
    <script type="text/javascript" src="${tabletree4j}/TableTree4j-debug.js"></script>
    <!--数据字典  css-->
    <link rel="stylesheet" href="${app}/css/dataDictionary.css" type="text/css"/>
    <link rel="stylesheet" href="${app}/css/management.css" type="text/css">
    <style type="text/css">
		.ejiaA1_bt{ background:#CFF1FF;}
    </style>
</head>

<body>
<div><h1 class="h1_1" style="margin-top:20px;">数据字典</h1></div>
<div class="y_twarp clearfix">
	<div class="l_p cur"><a href="javascript:void(0)">发文类型</a></div>
    <div class="r_p"><a href="dataDictionary_in.htm">收文类型</a></div>
</div>
<div class="btnDiv"><input class="bTn blue" value="添加" type="button" id="dicAdd"/></div>
<div class="tcontainer">
	<div id="worldcupgird"></div>
</div>
</body>
</html>
<script type="text/javascript">
//create and config tabletree object
var fifaGirdTree = new Core4j.toolbox.TableTree4j({
    columns: [
        {isNodeClick: true, dataIndex: 'title', width: '48%', align: 'center'},
        {dataIndex: 'ops', width: '52%', align: 'center'}
    ],
    headers: [{
        columns: [
            {dataIndex: 'name', align: 'center'},
            {dataIndex: 'ops', align: 'center'}
        ],
        dataObject: {name: '文件名称', ops: '操作'},
        trAttributeNames: ['classStyle', 'style'],
        trAttributeValueObject: {classStyle: 'ejiaA1_bt', style: ''}
    }
    ],
    treeMode: 'gird',
    renderTo: 'worldcupgird',
    useLine: true,
    useIcon: true,
    id: 'myworldcupgirdtree',
    useCookie: true,
    themeName: 'arrow',
    selectMode: 'single'
});
	
function edit(curObj){
	layer.open({
	       type:2,
	       title:'修改发文字典',
	       area:['900px','420px'],
	       shadeClose:false,
	       maxmin:true,
	       content:"editDic.htm?id="+curObj.id
	     });
}
function del(curObj){
	var index=layer.confirm('确定要删除当前字典数据吗？', {
     	icon: 3, 
        title:'字典发文管理'
     }, function(index){
    	 $.ajax( {
				url : "removeDic.json?id="+curObj.id,
				success : function(data) {
					if(data.state){
						ref();
					}else{
						var tishi=layer.alert(data.msg,{
					     	title:'信息提示',
					        icon:1,
					        shadeClose:true,
					     },
					     function(){
					     	ref();
					        layer.close(tishi);
					     }
					     );
					}
				}
			});
        layer.close(index);
     },function(index){
        layer.close(index);
     }
    );
}

$(document).ready(function(){
	fifaGirdTree.build(${re},true);
	$("#dicAdd").click(function(){
		layer.open({
	       type:2,
	       title:'添加发文字典',
	       area:['980px','520px'],
	       shadeClose:false,
	       maxmin:true,
	       content:"editDic.htm?type="+'0'
	   });
	});
});

function closeLayer(){
	layer.closeAll('iframe');
	location=location;
}

function ref(){
	location=location;
}
</script>