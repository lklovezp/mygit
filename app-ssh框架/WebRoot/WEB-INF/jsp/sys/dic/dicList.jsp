<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>数据字典——收文</title>
	<link href="../static/app/CSSReset.css" rel="stylesheet" type="text/css"/>
    <link href="../static/app/common.css" rel="stylesheet" type="text/css"/>
    <!--jQuery-->
    <script type="text/javascript" src="../static/jquery/jquery-1.8.3.min.js"></script>
    <!--tableTree-->
    <link rel="stylesheet" href="../static/tabletree4j/tabletree4j.css"/>
    <script type="text/javascript" src="../static/tabletree4j/Core4j-debug.js"></script>
    <script type="text/javascript" src="../static/tabletree4j/Core4j.js"></script>
    <script type="text/javascript" src="../static/tabletree4j/TableTree4j.js"></script>
    <script type="text/javascript" src="../static/tabletree4j/TableTree4j-debug.js"></script>
    <!--数据字典 css-->
    <link rel="stylesheet" href="../static/app/css/dataDictionary.css" type="text/css"/>
    <link rel="stylesheet" href="../static/app/css/management.css" type="text/css">
    <style type="text/css">
		.ejiaA1_bt{ background:#CFF1FF;}
    </style>
</head>

<body>
<div><h1 class="h1_1"  style="margin-top:20px;">数据字典</h1></div>
<div class="y_twarp clearfix">
	<div class="l_p"><a href="dataDictionary_out.html">发文类型</a></div>
    <div class="r_p cur"><a href="javascript:void(0)">收文类型</a></div>
</div>
<div class="btnDiv"><input class="bTn blue" value="添加" type="button"/></div>
<div class="tcontainer">
	<div id="worldcupgird"></div>
</div>



<script type="text/javascript">
	var data=[
	    {
			"id":"001",
			"order":1,
			"name":"收文收文",
			"dataObject": {
				"title": "收文收文", 
				"ops": "<input class='bTn orange' value='修改' type='button'>   <input class='bTn blue' value='删除' type='button'>"
			}
		},{
			"id": "001001",
			"order": 1,
			"name": "收文收文",
			"pid": "001",
			"dataObject": {
				"title": "收文收文",
				"ops": "<input class='bTn orange' value='修改' type='button'>   <input class='bTn blue' value='删除' type='button'>"
          }
	   },{
			"id":"002",
			"order":2,
			"name":"收文收文1",
			"dataObject": {
				"title": "收文收文1", 
				"ops": "<input class='bTn orange' value='修改' type='button'>   <input class='bTn blue' value='删除' type='button'>"
			}
		},{
			"id": "002001",
			"order": 2,
			"name": "收文收文1",
			"pid": "002",
			"dataObject": {
				"title": "收文收文1",
				"ops": "<input class='bTn orange' value='修改' type='button'>   <input class='bTn blue' value='删除' type='button'>"
          }
	   }
    ];
	
	
	
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
            dataObject: {name: '　部门名称', ops: '　操作'},
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
	
	fifaGirdTree.build(data,true);
</script>
</body>
</html>