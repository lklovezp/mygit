<%@ page contentType="text/html; charset=gb2312" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" dir="ltr">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Online View PDF</title>
<%--<link href=""--%>
<script type="text/javascript" src="${jquery}/jquery-1.8.3.min.js"></script>
<link href="${app}/common.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
</script>
</head>
<body>
<div style="width: 100%;height: auto;">
    <iframe id="pdfFile" src="${filePath}" style="width:100%;"></iframe>
</div>
<script type="text/javascript" src="${static}/common/fjView.js"></script>
<script>
    $(function(){
        $('#pdfFile').height(($(window).height()-150)+'px');
    })
</script>
<p>${last}</p>

<p>${next}</p>
</body>
</html>