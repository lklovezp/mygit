<%@ include file="/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base
			href="${pageContext.request.scheme}://${pageContext.request.serverName }:${pageContext.request.serverPort }${ctx }/">
		<title>查看自由裁量</title>
		<script type="text/javascript" src="${jquery}/jquery.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.validate.js"></script>
		<script type="text/javascript" src="${jquery}/jquery.form.js"></script>
		<script type="text/javascript" src="${jquery}/messages_cn.js"></script>
		<script type="text/javascript" src="${easyui}/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${easyui}/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/icon.css" />
		<link rel="stylesheet" type="text/css" href="${easyui}/themes/default/easyui.css" />
		<link href="${app}/hnjz.css" rel="stylesheet" type="text/css" />
		 <style  type="text/css">
	        .Mover
	        {
	            background: #fff;
	        }
	        
	        .Mon
	        {
	            background: #f4f4f4;
	        }
	        .Mclick
	        {
	            background: #ccc;
	        }
	    </style>
	</head>
	<body>
		<div id="neirong" style="margin: 10px">
			<table width="100%"  class="table1">
				<tr>
					<td>内容</td>
					<td>
						<textarea rows="8" id="content" cols="80"  name="content" style="width: 98%" readOnly="readOnly">
						</textarea>					
					</td>
				</tr>
			</table>
				<table width="100%"  class="table1">
					<tr >
						<td class="b1">序号</td>
						<td class="b2">法律依据</td>
					</tr>
					<c:forEach items="${list}" var="item" varStatus="status">
						<tr onclick = "setContent('${item.content }')">
							<td class="td">${status.count }</td>
							<td class="td">${item.name }</td>
						</tr>
					</c:forEach>
				</table>
			
		</div><div id="tempcon" style="display:none;"></div>	
	</body>
</html>
<script type="text/javascript">
	function setContent(text){
		$('#tempcon').html(text);
		var ttext = $('#tempcon').text();
		$('#content').val(ttext);
	}
    window.onload = function () {
        var trs = document.getElementsByTagName('tr');
        for (var i = 0; i < trs.length; i++) {
                trs[i].onmouseover = function () {
                    if (this.className == 'Mclick') { }
                    else this.className = 'Mon';
                }
                trs[i].onmouseout = function () {
                    if (this.className == 'Mclick') { }
                    else this.className = "Mover";
                }
        }
     }

</script>