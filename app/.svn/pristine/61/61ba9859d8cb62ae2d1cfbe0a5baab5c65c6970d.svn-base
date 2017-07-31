<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>信息管理-批量调整企业的所属监管部门</title>
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css"/>
<link href="${app}/common.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${app}/js.js"></script>
<script type="text/javascript" src="${app}/data.js"></script>
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${jquery }/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery }/jquery.form.js"></script>
<script type="text/javascript" src="${jquery }/messages_cn.js"></script>
<script type="text/javascript" src="${easyui }/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easyui }/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${colorbox }/jquery.colorbox-min.js"></script>
<!-- 任务管理 css-->
<link href="${app}/css/task.css" rel="stylesheet" type="text/css"/>
<link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
<link href="${app}/common.css" rel="stylesheet" type="text/css" />
<link href="${app}/resetCSS.css" rel="stylesheet" type="text/css" />
<link href="${app}/css/handleTaskStatistics.css" rel="stylesheet" type="text/css" />
<link href="${app}/attachFileList.css" rel="stylesheet" type="text/css"/>

<!--zTree-->
<link rel="stylesheet" href="${ztree}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<link rel="stylesheet" href="${app}/css/zTreeReset.css" type="text/css" />
<script type="text/javascript" src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/css/easyUIReset.css" rel="stylesheet" type="text/css"/>

    <style type="text/css" media="screen">
        input.colchiname {
            border: none;
        }

        input[type=button] {
            border: none;
            background: none;
            color: #00a2d9;
            cursor: pointer;
        }

        table.dataTable, table.dataTable td {
            border: 1px solid #d4d4d4
        }
     
    </style>
</head>
<body style="padding-bottom: 70px;">
<div style="width: 90%; margin: 0 auto; min-width: 900px;">
    <h1 id="checkup_header">批量调整企业的所属监管部门</h1>
    <!-- -->
    <div class="clearfix" id="dapartment">
        <!--left-->
        <div class="dapartment_l" >
            <!--select-->
            <div class="clearfix"><span class="red">*</span>执法对象类型  <input class="y-text" id="lawobjtypeid" name="lawobjtypeid" value="" /></div>
            <!--table-->
            <div class="dapartment_l_b mt25" >
                <div style=" width: 100%;height:400px; ">
                    <iframe id="if1" src="queryAllCompany.htm"  frameborder="no" border="0" width='100%' height='100%'></iframe>
                </div>
            </div>
        </div>
         <!-- 中间箭头 -->
      <div class="mt25" style="width:10%; float:left; text-align:center; height:200px; padding-top:200px;">
    	<img src="${app}/images/l_arrow.png" />
       </div>
         <!--right-->
        <div class="dapartment_r">
            <!--select-->
            <div class="clearfix"><span class="red">*</span>所属监管部门  <input class="y-text" id="supdepartmentid" name="supdepartmentid" value="" /></div>
            <!--table-->
            <div class="dapartment_r_b mt25">
                <div style=" width: 100%; height:400px;">
                     <iframe id="if2" src="queryAllCompanyByOrgId.htm" frameborder="no" border="0"  width='100%' height='100%'></iframe>
                </div>
            </div>
        </div>
    </div>
     <div class="rb_btn_fix" id="bottomBtn">
			<a class="o_btn btn_blue " id="shaiXuan" style="cursor: pointer; padding: 5px 12px;" >筛选无监管对象</a>
            <a class="o_btn btn_orange" id="save"   style="cursor: pointer; padding: 5px 12px;">确认转移</a>
     </div>
 </div>
  </body>
</html>
<script>
function initH(){
	var hh=$(window).height()-$("#checkup_header").outerHeight()-70;
	$("#dapartment").height(hh);
}
initH();

//数据表格自使用宽度
$(window).resize(function(){
	initH();
});

    $(document).ready(function () {
    	
        //执法对象类型
        $('#lawobjtypeid').combotree({
            height: 38,
            width:300,
            url: 'lawtypeTree.json',
            valueField: 'id',
            textField: 'name',
            onChange:function(){
            	var orgId=$("#supdepartmentid").combotree("getValue");
            	if(orgId != "" && orgId != null){
            	    var code=$("#lawobjtypeid").combotree("getValue");
            	    var codeName=$("#lawobjtypeid").combotree("getText");
            	    var orgName=$('#supdepartmentid').combotree('tree').tree('getSelected').text;
            	    hrefAllCompanyByOrgId = "queryAllCompanyByOrgId.htm?orgId="+orgId+"&code="+code+"&name="+orgName;
            		$('#if2').attr('src',hrefAllCompanyByOrgId);
            		var  href1= "queryAllCompany.htm?orgId="+orgId+"&code="+code+"&name="+codeName+"&orgName="+orgName;
                    $('#if1').attr('src',href1);
            	}else{
            		var code=$("#lawobjtypeid").combotree("getValue");
            	    var codeName=$("#lawobjtypeid").combotree("getText");
            	    var orgName="";
            	    hrefAllCompanyByOrgId = "queryAllCompanyByOrgId.htm?orgId="+orgId+"&code="+code+"&name="+orgName;
            		$('#if2').attr('src',hrefAllCompanyByOrgId);
            		var  href1= "queryAllCompany.htm?orgId="+orgId+"&code="+code+"&name="+codeName+"&orgName="+orgName;
                    $('#if1').attr('src',href1);
            	}
                   
              }
        });
        //所属监管部门
        $("#supdepartmentid").combotree({
            width: 300,
            height: 38,
            url: 'orgTree.json',
            valueField: 'id',
            textField: 'name',
            onChange:function(){
            			 var orgId=$("#supdepartmentid").combotree("getValue");
                	     var code=$("#lawobjtypeid").combotree("getValue");
                	     var codeName=$("#lawobjtypeid").combotree("getText");
                	     var orgName=$('#supdepartmentid').combotree('tree').tree('getSelected').text;
                	     var  href1= "queryAllCompany.htm?orgId="+orgId+"&code="+code+"&name="+codeName+"&orgName="+orgName;
            		     $('#if1').attr('src',href1);
            			 var href2 = "queryAllCompanyByOrgId.htm?orgId="+orgId+"&code="+code+"&name="+orgName;
            		     $('#if2').attr('src',href2);
                  
                 }
        }).combobox("initClear");
        
    });
    var id ="";
    var name="";
    
    //调整部门后刷新
   function shuaXin(orgId,code,name,orgName){
	    var orgName=$("#supdepartmentid").combotree('tree').tree('getSelected').text;
	    var code=$("#lawobjtypeid").combotree("getValue");
	    var codeName=$("#lawobjtypeid").combotree("getText");
	    var orgId=$("#supdepartmentid").combotree("getValue");
	href2 = "queryAllCompanyByOrgId.htm?orgId="+orgId+"&code="+code+"&name="+orgName;
	$('#if2').attr('src',href2);
   }
   
  $("#shaiXuan").click(function(){
	  document.getElementById("if1").contentWindow.pressed();
  })
  $("#save").click(function(){
	  document.getElementById("if1").contentWindow.save();
  })
</script>

