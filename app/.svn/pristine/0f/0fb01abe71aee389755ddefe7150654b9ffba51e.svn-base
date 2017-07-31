<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>任务派发-执法检查</title>
    <link href="${app}/CSSReset.css" rel="stylesheet" type="text/css" />
    <link href="${app}/common.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${app}/js.js"></script>
	<script type="text/javascript" src="${app}/data.js"></script>
    <!--jQuery-->
    <script type="text/javascript" src="${jquery}/jquery.js"></script>
    <script type="text/javascript" src="${layer}/layer.js"></script>
    <script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
	<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
    <!--easyui-->
    <link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
    <link rel="stylesheet" href="${app}/easyUIReset.css"  type="text/css" />
    <link rel="stylesheet" href="${app}/attachFileList.css" type="text/css">
    <script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css" />
    <!--执法检查-->
    <link href="${app}/css/taskDispatch.css" rel="stylesheet" type="text/css" />
    <!--时间插件 my97-->
    <script type="text/javascript" src="${My97DatePicker}/WdatePicker.js"></script>
    <script type="text/javascript" src="${common}/All.js"></script>
    <link rel="stylesheet" href="${ztree}/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<link rel="stylesheet" href="${app}/css/zTreeReset.css" type="text/css" />
<script type="text/javascript" src="${ztree}/js/jquery.ztree.core-3.5.js"></script>
<!--easyui-->
<link rel="stylesheet" href="${easyui}/themes/default/easyui.css" type="text/css">
<link rel="stylesheet" href="${easyui}/themes/icon.css" type="text/css">
<link href="${app}/css/easyUIReset.css" rel="stylesheet" type="text/css"/>
</head>
<body style="padding-bottom:60px;">
<div class="breadClumbs"> 执法检查&nbsp;&gt;&nbsp;
			<c:if test="${isCp==0}">
             	任务派发&nbsp;&gt;&nbsp;
		    </c:if>
		    <c:if test="${isCp==1}">
		     	任务重派&nbsp;&gt;&nbsp;
		    </c:if><span id="name"></span></div>
     <!-- <div style=" width:95%; margin:0 auto; ">
            
      <div class="h2_1" style="margin-top:-20px;"><span class="tit">任务信息</span></div> 
    </div>--> 
    <div id="task_xsts" style="min-width: 1020px">
        <form id="myform" name="myform" method="post" action="pf.json">
            <input type="hidden" id="lx" name="lx" value="${lx}"/>
            <input type="hidden" id="applyId" name="applyId" value="${work.id}" />
		    <input type="hidden" id="id" name="id" value="${work.id}" />
		    <input type="hidden" id="mcs" name="mcs" value="${mcs}"/>
		    <input type="hidden" id="rwlxType" name="rwlxType" value="${rwlxType}"/>
		     <input type="hidden" id="worksource" name="worksource" value="${work.source}"/>
		     <input type="hidden" name="rcbg" id="rcbg" <c:if test='${rwlxType == "24"}'> value='Y'</c:if> />
		     <input type="hidden" name="xfts" id="xfts" <c:if test='${rwlxType == "13"}'> value='Y'</c:if> />
            <table class="queryTable"  width="95%" border="0" cellpadding="0" cellspacing="0" style=" margin:0 auto">
                <tr>
                    <th>组合方式</th>
                    <td colspan="3">
                    <input type="text" class="form-text text-length1" id="rwmcgs" name="gs" value="${work.rwmcgs}"/>
                    </td>
                  </tr>
                  <tr <c:if test='${rwlxType == "24"}'>style="display:none;"</c:if>>
                    <span>
                        <th width="130px"><label class="requiredLabel">*</label>执法对象类型</th>
                        <td width="440px"><input type="text" class="form-text" id="zfdxType" name="zfdxType" readonly="readonly" value="${work.zfdxType}" ></td>
                    </span>
                    <span algin="right">
                        <th width="130"><a href="#" id="zfdxxz" class="task_zflx" onclick="zfdxxz()" >执法对象名称</a></th>
						<td>
							<input class="form-text" type="text" id="zfdxmc" name="zfdxmc" readonly="readonly" value="${work.zfdxmc}"/>
						</td>
                    </span>
                </tr>
                <tr <c:if test='${rwlxType == "24"}'>style="display:none;"</c:if>>
                    <th width="130px">任务类型</th>
                    <td width="440px"><input type="text" class="form-text" id="rwlx" name="rwlx" style="height: 34px;" readonly="readonly"/></td>
                    <th>日期</th>
                    <td width="">
                    <input type="text" class="y-dateTime" id="rwmcrq" name="rwmcrq" value="<fmt:formatDate value="${work.rwmcrq}" pattern="yyyy年MM月dd日"/>"  onclick="WdatePicker({onpicked:function(){rwmczh('');},dateFmt:'yyyy年MM月dd日'})" style="width:156px;"/>
                    </td>
                </tr>
                <tr>
                    <th>任务名称</th>
                    <td colspan="3">
                    <textarea title="限制字符200字" style="width:790px;height:40px;overflow:auto;" maxlength="200"  onKeyUp="return textMaxLen(this);" onBlur="return textMaxLen(this);" class="y-text easyui-validatebox" data-options="required:true" id="workName" name="workName"/>${work.name}</textarea>
                    </td>
                </tr>
                <tr>
                    <th><label class="requiredLabel">*</label>主要内容</th>
                    <td colspan="3" >
                    <textarea style="width:790px;height:100px;overflow:auto;" maxlength="1000"  onKeyUp="return textMaxLen(this);" onBlur="return textMaxLen(this);"  class="y-text easyui-validatebox" data-options="required:true" id="workNote" name="workNote"/>${work.workNote}</textarea>
                    </td>
                </tr>
                <tr>
                    <th width="130px"><label class="requiredLabel">*</label>任务来源</th>
                    <td width="440px"><input type="text" class="form-text" id="source" name="source" value="${work.source}" /></td>
                    <th width="130px"><label class="requiredLabel">*</label>任务密级</th>
                    <td><input type="text" class="form-text" id="security" name="security" value="${work.security}" ></td>
                   
                </tr>
                <tr>
                 	<th><label class="requiredLabel">*</label>紧急程度</th>
                    <td width="440px"><input type="text"  class="form-text" id="emergency" name="emergency" value="${work.emergency}" /></td>
                    <th><label class="requiredLabel">*</label>要求完成时限</th>
                    <td>
                    <input type="text" class="y-dateTime" id="endTime" name="endTime" value="<fmt:formatDate value="${work.endTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 156px;"/>
                    <input type="hidden" id="hideTime" value="<fmt:formatDate value="${work.endTime}" pattern="yyyy-MM-dd"/>" />
                    </td>
                   
                </tr>
                <tr>
                 	<th><label class="requiredLabel">*</label><a href="#" id="selectdjr" class="task_zflx">登记人</a></th>
                    <td>
                    <input type="hidden" id="djrId" name="djrId" value="${work.djrId}" />
					<input class="form-text" data-options="required:true" type="text" id="djr" readonly="readonly" value="${work.djrName}" />
                    </td>
                    <th><label class="requiredLabel">*</label>
						<a href="#" class="task_zflx" onclick='javascript:selectjsr()' id="selectjsr">选择接受人</a>
                    </th>
                    <td>
                    <input type="hidden" id="jsrIds" name="jsrIds" value="${work.jsrIds}"/>
					<input class="form-text easyui-validatebox" data-options="required:true" type="text" id="jsrNames" readonly="readonly" value="${work.jsrNames}" />
                    </td>
                </tr>
                <tr>
                    <th><label class="requiredLabel">*</label>批示意见</th>
                    <td colspan="3" >
                   		<textarea title="限制字符200字" style="width:790px;height:120px;overflow:auto;" maxlength="150"  onKeyUp="return textMaxLen(this);" onBlur="return textMaxLen(this);" class="y-text easyui-validatebox" data-options="required:true" id="psyj" name="psyj"/>${work.psyj}</textarea>
                    </td>
                </tr>
            </table>
        
    </div>
  <!--相关附件-->
    <div class="dataDiv" style="width:80%; margin:16px auto 25px;">
        <div class="mt25" id="annex">
            <div class="annex_header">
                <a class="b_link" style="float:right;" onclick="downZipFile()">打包下载</a>
                <a id="XGFJfileupload" class="b_link" style="float:right;" >上传附件</a>
                相关附件
            </div>
            <div class="annex_con" style=" height: 248px;">
                <table id="XGFJdata" fit="true"></table>
            </div>
        </div>
    </div>
   <div class="rb_btn_fix">
       <tr>
           <td align="center" colspan="4">
               <c:if test="${isCp==0}">
					 <input id="savebutton" type="button" class="queryBlue"  onclick="sc()"
								value="保存"> &nbsp;
					 <input id="pfbutton" type="submit" class="queryOrange" value="派发"> &nbsp;
			    </c:if>
			    <c:if test="${isCp==1}">
				     <input id="cpbutton" type="button" class="queryOrange" onclick="cp()"
									value="重派"> &nbsp;
			    </c:if>
           </td>
       </tr>
   </div>
   </form>
   <iframe name="download" id="download" src="" style="display: none"></iframe>
    <script type="text/javascript">
        var applyId = $('#applyId').val();
        var rwlxType = $('#rwlxType').val();
        var source = $('#worksource').val();
        function myformatter(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
        }  
                  
        function myparser(s){  
            if (!s) return new Date();  
            var ss = (s.split('-'));  
            var y = parseInt(ss[0],10);  
            var m = parseInt(ss[1],10);  
            var d = parseInt(ss[2],10);  
            if (!isNaN(y) && !isNaN(m) && !isNaN(d)){ 
                return new Date(y,m-1,d);
            } else {
                return new Date();  
            }  
        } 
        //设置选择登记人
        function setUserInfoDjr(id,name) {
        	$("#djrId").val(id);
        	$("#djr").val(name);
        	jQuery().colorbox.close();
        }
        //选择接受人
        function selectjsr(){
            var ids = $("#jsrIds").val();
            $("#selectjsr").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?all=false&id="+ids+"&notShowZj=false&methodname=setUserInfoJsr&multi=false&showExist=true&group=0"});
        }
        //设置选择接受人
        function setUserInfoJsr(id,name) {
        	$("#jsrIds").val(id);
        	$("#jsrNames").val(name);
        	jQuery().colorbox.close();
        }
        function huixian(zfdxmc,mcs){
			$("#zfdxmc").val(zfdxmc);
			$("#mcs").val(mcs);
        }
        $(document).ready(function(){
        	//任务名称组合方式
            $('#rwmcgs').combobox({
                height:34,
                valueField : 'value',
                textField : 'text',
                data: [
                       {text: '组合',value: '0'},
                	   {text: '自定义',value: '1'}],
                onChange:function(newValue,oldValue){
                	mcfs();
               	}
            });
            if($('#rwlxType').val()==24){
            	$('#rwmcgs').combobox('setValue','1');
            	$('#rwmcgs').combobox("disable");
            }
        	var mcgs = $('#rwmcgs').combobox('getValue');
        	if(source == '11'){
        		$(".zfdxInfo").hide();
        		$('#rwmcgs').attr("disabled",true);
        	}
        	//先默认为只读属性
        	if(mcgs == '1'){
        		$("#zfdxType").combotree({required: false, disabled: true});
        		$('#zfdxxz').attr("disabled",true);
        		$('#zfdxmc').attr("disabled",true);
        		//$("#rwmcrq.easyui-datebox").datebox({required: false, disabled: true});
        		$("#rwmcrq").attr("disabled",true);
        		$("#rwlx").combotree({panelHeight:300,multiple: true,required: false, disabled: true});
        		if($("#rwlxType").val()=="13"){
        			$('#xfbh').validatebox({ 
        				required:true
        			});
        		}
        	}else{
        		$("#zfdxType").combotree({required: true, disabled: false});
        		//$("#rwmcrq.easyui-datebox").datebox({required: true, disabled: false});
        		$("#rwmcrq").attr("disabled",false);
        		//$("#rwlx").combotree({panelHeight:300,multiple: true,required: true, disabled: false});
        		//任务类型
       			$('#rwlx').combotree({
       				multiple: true,
       				url:'taskTypeTreeComboByTaskIdWithoutXf.json?applyId='+applyId,
       				panelHeight:300,
       				valueField:'id',
       				textField:'name',
       				onChange : function (newValue,oldValue){
       					var rw="";
       					for (i=0;i<newValue.length;i++){
       					  	node=$('#rwlx').combotree('tree').tree('find',newValue[i]);
       					  	rw+=node.text+",";
       					}
       					if(rw!=""){
       						rw=rw.substring(0,rw.length-1); 
       					}
       					rwmczh(rw);
       				},
       				value:'${rwlxIds}'.split(',')
       			});
        	}
            
        	//执法对象类型下拉框
        	$('#zfdxType').combotree({
        		multiple: false,
       			url:'lawtypeTree.json',
       			panelHeight:300,
       		    onChange : function (newValue,oldValue){
      				rwlxjz();
       	        	rwmczh("");
       			}
       			//value:'${rwlxIds}'.split(',')
       		});
            //任务来源下拉框
            $('#source').combobox({
            	height:34,
                required:true,
        		url:'dicList.json?type=1',
        		valueField:'id',
        		textField:'name'
        	});
        	//任务密级下拉框
            $('#security').combobox({
            	height:34,
                required:true,
        		url:'dicList.json?type=2',
        		valueField:'id',
        		textField:'name'
        	});
        	//紧急程度下拉框
            $('#emergency').combobox({
            	height:34,
                required:true,
        		url:'dicList.json?type=3',
        		valueField:'id',
        		textField:'name',
        		onChange:function(newValue, oldValue){
        		    //改变要求完成时间
        		    var m_date = new Date();  
        		    switch(newValue){
        		        case '1'://一般
        		           m_date.setDate(m_date.getDate()+20);//当前日期+几天  
        		           $("#endTime").val(myformatter(m_date)); 
        		           $("#hideTime").val(myformatter(m_date)); 
        		           break;
        		        case '2'://紧急
        		           m_date.setDate(m_date.getDate()+7);//当前日期+几天  
        		           $("#endTime").val(myformatter(m_date)); 
        		           $("#hideTime").val(myformatter(m_date));
        		           break;
        		        case '3'://特急
        		           m_date.setDate(m_date.getDate()+3);//当前日期+几天  
        		           $("#endTime").val(myformatter(m_date)); 
        		           $("#hideTime").val(myformatter(m_date));
        		           break;
        		        default:
        		    }
        		}
        	});
        	
        	//选择登记人
        	$("#selectdjr").colorbox({iframe:true,width:"300", height:"380",href:"taskUserPubQuery.htm?all=false&notShowZj=false&methodname=setUserInfoDjr&multi=false"});
            
    		
            $(".inputWithImge").each(function(){
                $(this).add($(this).next()).wrapAll('<div class="imageInputWrapper"></div>');
            });
            
            //相关附件列表
            $('#XGFJdata').datagrid({
                pagination:true,//分页控件
        		height:'auto',
        		rownumbers: true,
        		singleSelect: true,
        		fitColumns : true,
        		url:'queryFileList.json?canDel=Y',
        		queryParams:{pid:$("#applyId").val(),fileType:'RWGLPFFJ',tableId:'XGFJdata'},
        		onLoadSuccess:function(data){
        			$(this).datagrid('doCellTip',{'max-width':'300px','delay':500});
        		},
        		columns:[[
        			{field:'id',hidden:true},
        			{field:'filetype',title:'附件类型', align:'center', halign:'center',width:90},
        			{field:'filename',title:'附件名称', align:'left', halign:'center',width:430},
        			{field:'filesize',title:'附件大小', align:'center', halign:'center',width:100},
        			{field:'operate',title:'操作', align:'center', halign:'center',width:130}
        		]]
        	});
        	//相关附件上传
        	$("#XGFJfileupload").click(function(){
        	    //任务名称校验
        	    if($("#workName").validatebox("isValid")){
        	    	//保存对应的执法对象类型和任务类型
        	    	var checkedNodesIds="";
            		var rows = $('#mcs').val();
        			var t = $('#rwlx').combotree('tree'); // 得到树对象 
        			var checkedNodes = t.tree('getChecked');
        			if($("#rwlxType").val() == "24"){
	            		checkedNodesIds = "24";
	            	}else if($("#rwlxType").val() == "13"){
	            		checkedNodesIds = "13";
	            	}else{
	        			var t = $('#rwlx').combotree('tree'); // 得到树对象 
	        			var checkedNodes = t.tree('getChecked');
	        			for(var i=0;i<checkedNodes.length;i++){
	        				if(i<checkedNodes.length-1){
	        					checkedNodesIds+=checkedNodes[i].id+',';
	        				}else{
	        					checkedNodesIds+=checkedNodes[i].id;
	        				}
	        			}
	            	}
        	        var id = $("#applyId").val();
        	        var rwlxIds;
        			var fileType = 'RWGLPFFJ';//派发附件
        			if(id!=null && id!=''){
        			    $.colorbox({
        					iframe:true, width:"610", height:"380",
        					href:'uploadPage.htm?pid='+id+'&fileType='+fileType+'&path=WORK&tableId=XGFJdata'
        				});
        			}else{
        			    $("#myform").ajaxSubmit({
        					type:"post",
        					dataType:"json",
        					url:"sc.json?checkedNodesIds="+checkedNodesIds+"&zfdxmcs="+encodeURIComponent(rows)+"&rwlxIds="+rwlxIds+'&rwmcgs='+$('#rwmcgs').combobox('getValue'),
        					success: function(data){
        						if(data.state){
        							$("#applyId").val(data.id);
        							$("#id").val(data.id);//这儿任务重复了，派发时用。
        							$.colorbox({
        								iframe:true, width:"610", height:"380",
        								href:'uploadPage.htm?pid='+$("#applyId").val()+'&fileType='+fileType+'&path=WORK&tableId=XGFJdata'
        							});
        						}else{
        							$.messager.alert('生成:',data.msg);
        						}
        					}
        				});
        			}
        	    }else{
        	        $("#workName").focus();
        	    }
        	});
        	
        	$("#myform").validate( {
        		errorClass : "error",
        		submitHandler : function(form) {
        			if ($("#myform").form("validate")){
        			    $("#pfbutton").attr({"disabled":"disabled"});
        			    
        			    //保存对应的执法对象类型和任务类型
        	    		var checkedNodesIds="";
            			var rows = $('#mcs').val();
        				var t = $('#rwlx').combotree('tree'); // 得到树对象 
        				var checkedNodes = t.tree('getChecked');
        				for(var i=0;i<checkedNodes.length;i++){
        					if(i<checkedNodes.length-1){
        						checkedNodesIds+=checkedNodes[i].id+',';
        					}else{
        						checkedNodesIds+=checkedNodes[i].id;
        					}
        				}
        			    var rwlxIds;
        			    if($("#rwlxType").val()=="13" || $("#rwlxType").val()=="24"){
        			    	rwlxIds=$("#rwlxType").val();
        			    }
        				$(form).ajaxSubmit( {
        					type : "post",
        					url : "pf.json?checkedNodesIds="+checkedNodesIds+"&zfdxmcs="+encodeURIComponent(rows)+"&rwlxIds="+rwlxIds+'&rwmcgs='+$('#rwmcgs').combobox('getValue'),
        					success : function(data) {
        					    $("#pfbutton").removeAttr("disabled");//将按钮可用
        					    if (data.state) {
        					      	alert(data.msg);
        					      	if("24" == rwlxType){
        					      		window.location.href="rcbgdbRwList.htm";
        					      	}else{
        					      		window.location.href="dbrwList.htm";
        					      	}
        					      	//var nodedataid = data.id;
        		                   	//window.location.href="preSubmitNodePubQuery.htm?applyId="+nodedataid;
        						} else {
        							alert(data.msg);
        						}
        					}
        				});
        			}
        		}
        	});

        });

        // 重置
        $("#J-from-reset").click(function(){
        	//location.reload();
        	$("#rcbg").attr("checked",false);
        	//$('#rcbg').val("N");
        	$("#zfdxType").combotree({required: false, disabled: false});
        	$('#rwmcgs').attr("disabled",false);
        	$('#zfdxxz').attr("disabled",false);
        	$('#zfdxmc').attr("disabled",false);
        	//$("#rwmcrq.easyui-datebox").datebox({required: false, disabled: false});
        	$("#rwmcrq").attr("disabled",false);
        	$("#rwlx").combotree({multiple: true,required: false, disabled: false});
            //document.location.reload();
        	$("#myform").form("reset");
        	$("#createTime").val('${work.createTime}');
        	$("#endTime").val('${work.endTime}');
        });

        //任务生成
        function sc(){
            //任务名称校验
            if($("#workName").validatebox("isValid")){
            	var checkedNodesIds="";
            	var mcs = $('#mcs').val();
        		if($("#rwlxType").val() == "24"){
            		checkedNodesIds = "24";
            	}else if($("#rwlxType").val() == "13"){
            		checkedNodesIds = "13";
            	}else{
        			var t = $('#rwlx').combotree('tree'); // 得到树对象 
        			var checkedNodes = t.tree('getChecked');
        			for(var i=0;i<checkedNodes.length;i++){
        				if(i<checkedNodes.length-1){
        					checkedNodesIds+=checkedNodes[i].id+',';
        				}else{
        					checkedNodesIds+=checkedNodes[i].id;
        				}
        			}
            	}
            	var rwlxIds;
            	if($("#rwlxType").val()=="13"){
        	        	rwlxIds = "13";
        	    }
                $('#myform').attr('action','sc.json?checkedNodesIds='+checkedNodesIds+'&zfdxmcs='+encodeURIComponent(mcs)+'&rwlxIds='+rwlxIds+'&rwmcgs='+$('#rwmcgs').combobox('getValue'));
        		$('#myform').ajaxSubmit(function(data){
        	   		if(data.state){
        	   			var index=layer.alert(data.msg,{
        	   		     	title:'生成',
        	   		        icon:1,
        	   		        shadeClose:true,
        	   		     },
        	   		     function(){
        	   		    	 //alert($('#lx').val()==1);
        	   		    	 //if($('#lx').val()==1){
        	   		    		parent.closeLayer();
        	   		    	// }else{
        	   		    	//	 window.location.reload();
        	   		    	// }
        	   		        layer.close(index);
        	   		     }
        	   		     );
        	   		     window.location.href="dbrwList.htm";
        	   		} else {
        		   		alert(data.msg);
        	   		}
        	   	});
            }else{
                $("#workName").focus();
            }
        }
        //任务重派
        function cp(){
            //任务名称校验
            if($("#myform").form("validate")){
            	var checkedNodesIds="";
            	var mcs = $('#mcs').val();
        		var t = $('#rwlx').combotree('tree'); // 得到树对象 
        		var checkedNodes = t.tree('getChecked');
        		for(var i=0;i<checkedNodes.length;i++){
        			if(i<checkedNodes.length-1){
        				checkedNodesIds+=checkedNodes[i].id+',';
        			}else{
        				checkedNodesIds+=checkedNodes[i].id;
        			}
        		}
        		var rwlxIds;
        		if($("#rwlxType").val()=="13"){
        			rwlxIds="13";
        		}
                $('#myform').attr('action','cp.json?checkedNodesIds='+checkedNodesIds+'&zfdxmcs='+encodeURIComponent(mcs)+'&rwlxIds='+rwlxIds+'&rwmcgs='+$('#rwmcgs').combobox('getValue'));
        		$('#myform').ajaxSubmit(function(data){
        	   		if(data.state){
        	   			var index=layer.alert(data.msg,{
        	   		     	title:'重派',
        	   		        icon:1,
        	   		        shadeClose:true,
        	   		     },
        	   		     function(){
        	   		    	parent.closeLayer();
        	   		        layer.close(index);
        	   		     }
        	   		     );
        	   		} else {
        				alert(data.msg);
        	   		}
        	   	});
            }else{
                $("#workName").focus();
            }
        }

        //打包下载
        function downZipFile(){
            var data = $('#XGFJdata').datagrid("getData");
        	var ids = "";
        	for(var i = 0; i < data.rows.length; i++){
        		if (i > 0){
        			ids += ",";
        		}
        		ids += data.rows[i].id;
        	}
        	if(ids!=null && ids!=''){
        	    $('#download').attr('src','downZipFile?id='+ids);
        	}else{
        	    alert("没有附件！");
        	}
        }

        /////附件操作/////
        /**
         * 公用的上传文件之后的刷新grid方法
         * @param tableId tablegrid的id
         */
        function reload(tableId,pid,fileType){
            var id = "#" + tableId;
        	$(id).datagrid("reload",{pid:pid,fileType:fileType,tableId:tableId});
        	jQuery.colorbox.close();
        }
        /**
         * 公用的删除文件方法 删除grid中的文件
         * @param obj grid的一行数据
         */
        function deletefile1(obj){
        	var index=layer.confirm('确定删除吗？', {
        	     	icon: 3, 
        	        title:'删除'
        	     }, function(index){
        	    	 $.ajax({
          				url: "removeFile.json?id="+obj.id,
          				success:function(data){
          					alert(data.msg);
          					var reloadtable= $(obj).attr("reloadtable");//自定义属性采用此方式获得 
          					$('#'+reloadtable).datagrid('reload');
          				}
          			});
        	        layer.close(index);
        	     },function(index){
        	        layer.close(index);
        	     }
        	    );

        }
        // 选择执法对象
        function zfdxxz(){
        	var zfdxType = $('#zfdxType').combotree('getValue');
        	var rwid = $("#applyId").val();
        	var bs = "1";
        	if(zfdxType!=null && zfdxType!=''){
        		var href="choseezfdx.htm?lawobjtypeid="+zfdxType+'&rwid='+rwid+'&fzbs='+bs;
        		var width=screen.width * 0.8;
        	  	var height=screen.height * 0.8-100;
    		  	var index=layer.open({
    			  	  type: 2,
    			  	  title: "选择执法对象",
    			  	  maxmin: false,
    			  	  shadeClose: false, //点击遮罩关闭层
    			  	  area : [width+'px' , height+'px'],
    			  	  content: href
    			  	  
    			  	  });
    		  	//layer.full(index);
        		//window.parent.xzzfdxlx(2,href,,width,height);
        		//All.ShowModalWin("selectLawobj.htm?lawobjtype="+zfdxType+'&rwid='+rwid+'&fzbs='+bs,'选择执法对象',1230,630);
        		rwmczh("");
        	}else{
        		alert("请先选择执法对象类型！");
        	}
        }
      
        function closeLayerIframe(){
        	layer.closeAll('iframe');
        }
        //任务名称的组合方法
        function rwmczh(r){
        	var zfdxmc = $('#zfdxmc').val();
        	//组合时间日期
        	var pfrq = $("#rwmcrq").val();
        	var rwlxmc = $('#rwlx').combotree('getText');
        	//组合任务类型
        	if(r!=""){
        		rwlxmc = r;
        	}
        	
        	var text = "";
        	text=zfdxmc+pfrq+rwlxmc;
        	$("#workName").val(text);
        }

        function mcfs(){
        	var mcgs = $('#rwmcgs').combobox('getValue');
        	if(mcgs == '1'){
        		$("#zfdxType").combotree({required: false, disabled: true});
        		$('#zfdxxz').attr("disabled",true);
        		$('#zfdxmc').attr("disabled",true);
        		//$("#rwmcrq.easyui-datebox").datebox({required: false, disabled: true});
        		$("#rwmcrq").attr("disabled",true); 
        		$("#rwlx").combotree({multiple: true,required: false, disabled: true});
        	}else{
        		$("#zfdxType").combotree({required: true, disabled: false});
        		$('#zfdxxz').attr("disabled",false);
        		$('#zfdxmc').attr("disabled",false);
        		//$("#rwmcrq.easyui-datebox").datebox({required: true, disabled: false});
        		$("#rwmcrq").attr("disabled",false);
        		if($('#rwlxType').val() == "13"){
        			$('#rwlx').combotree('loadData', [{id:'13',text:'信访投诉'}]); 
        			$("#rwlx").combotree('setValue', '13');
        		}else{
        			$('#rwlx').combotree({
        				required: true,
        				disabled: false,
        				panelHeight:300,
        				multiple: true,
        				url:'taskTypeTreeComboByTaskIdWithoutXf.json?applyId='+applyId,
        				valueField:'id',
        				textField:'name',
        				onChange : function (newValue,oldValue){
        					var rw="";
        					for (i=0;i<newValue.length;i++){
        					  	node=$('#rwlx').combotree('tree').tree('find',newValue[i]);
        					  	rw+=node.text+",";
        					}
        					if(rw!=""){
        						rw=rw.substring(0,rw.length-1); 
        					}
        					rwmczh(rw);
        				},
        				value:'${rwlxIds}'.split(',')
        			});
        		}
        	}
        }

        function rwlxjz(){
        	var zfdxType = $('#zfdxType').combotree('getValue');
        	var zfdxmc = $('#zfdxmc').val();
        	if(zfdxType!=null && zfdxType!=''){
        		if(zfdxmc!=null && zfdxmc!=''){
        			$("#zfdxmc").val("");//清空任务类型
        			if($("#rwlxType").val()!="13"){
        				$("#rwlx").combotree('setValues',"");//清空任务类型
        				$('#rwlx').combotree('reload','taskTypeTreeComboByTaskIdWithoutXf.json?applyId='+applyId+'&zfdxlx='+zfdxType);
        			}
        		}
        	}else{
        		alert("请先选择执法对象类型！");
        	}
        }

        //任务类型的重置
        function rwlxcz(){
        	var zfdxmc = $('#zfdxmc').val();
        	var myarray= new Array();
        	var myarray = zfdxmc.split(",");
        	var zfdxType = $('#zfdxType').combotree('getValue');
        	if(myarray.length>1){
        			$("#xfts").val("N");
        			$('#xfbh').validatebox({ 
        				required:false
        			});
        			$(".xfdjShow").hide();
        			$('#xfts').removeAttr('checked');
        			zfdxType = 'zxbs';
        			$('#rwlx').combotree('reload','taskTypeTreeComboByTaskIdWithoutXf.json?applyId='+applyId+'&zfdxlx='+zfdxType);
        			$("#rwlx").combotree({multiple: true,required: true, disabled: false});
        	}else if($("#rwlxType").val()!="13"){
        			$("#rwlx").combotree('setValues',"");//清空任务类型
        			$('#rwlx').combotree('reload','taskTypeTreeComboByTaskIdWithoutXf.json?applyId='+applyId+'&zfdxlx='+zfdxType);
        	}
        }
        
        function textMaxLen(i){
       	 var conMaxLen=parseInt(i.getAttribute("maxlength"));
       	 if(i.value.length>=conMaxLen){
       		i.value=i.value.substring(0,conMaxLen)
       	 }
      	}
      
        //数据表格宽度自适应
        $(window).resize(function(){
            $('#XGFJdata').datagrid("resize");
        });
    </script>
   
</body>
</html>
