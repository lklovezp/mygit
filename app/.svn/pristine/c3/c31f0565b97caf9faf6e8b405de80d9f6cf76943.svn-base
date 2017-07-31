<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>自由裁量信息</title>
<script type="text/javascript" src="${jquery}/jquery.js"></script>
<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css" />
<link rel="stylesheet" type="text/css"
	href="${easyui}/themes/default/easyui.css" />
<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
<!-- colorbox -->
<script type="text/javascript" src="${colorbox}/jquery.colorbox-min.js"></script>
<link rel="stylesheet" type="text/css" href="${colorbox}/colorbox.css">
</head>
<body>
	<div id="neirong" style="margin: 10px">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="infotable">
			<tr>
				<td colspan="2" align="right" >
				<c:if test="${po.code!=4 }">
				 <a onclick="add1()">增加${name}</a>
				</c:if>
				<a onclick="edit('${po.id}')">编辑</a>
				<a onclick="remove1('${po.id}')">删除</a>
				
				</td>
			</tr>
			<c:forEach items="${plist}" var="item" >
				 <tr>
					<th width="30%">${item.codenote}：</th>
					<td>${item.desc}</td>
				</tr>
			</c:forEach>
			<tr>
				<th>关键词：</th>
				<td>${po.gjc }</td>
			</tr>
			<tr>
				<th>违法类型：</th>
				<td>${po.illegalTypeName }</td>
			</tr>
		</table>
		<br>
			<table width="100%" class="table1">
				<tr>
					<td class="b1">序号</td>
					<td class="b2">${name}内容</td>
					<td class="b1">操作</td>
				</tr>
				<c:forEach items="${childList}" var="item" varStatus="status">
					<tr>
						<td class="td">${status.count }</td>
						<td class="td_b">${item.name }</td>
						<td class="td"> 
						 <a href="#" id="edit" onclick="edit('${item.id }')" >编辑</a>  
						<a href="#" id="remove" onclick="remove1('${item.id }')"> 删除</a>    
						 </td>
					</tr>
				</c:forEach>
			</table>
	</div> 
</body>
</html>
<script type="text/javascript">
    function ref(){
       parent.ref();
    }
	//添加节点
	function add1() {
		$.colorbox({
			iframe : true,
			width : "90%",
			height : "95%",
			href : "addDiscretionNode.htm?pid=${po.id}"
		});
	};
	function edit(id){
	 $.colorbox({
					iframe : true,
					width : "60%",
					height : "85%",
					href : "addDiscretionNode.htm?id="+id
				});
	}
	function remove1(id){
	  $.messager.confirm('操作确认', '确认删除吗？', function(r) {
			if (r) {
				$.post('removeDiscretion.json',{
					id:	id
				}, 
				function(res) {
					if (res.state) {
						$.messager.alert('提示', "删除成功！", 'warning');
						//location.reload();
						parent.ref();
					} else {
						$.messager.alert('提示', res.msg, 'warning');
					}
				}, 'json');	
			}
		});
	}
</script>