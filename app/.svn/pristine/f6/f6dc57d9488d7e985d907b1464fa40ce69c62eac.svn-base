<!DOCTYPE html>
<html dir="ltr" mozdisallowselectionprint moznomarginboxes>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="google" content="notranslate">
    <title>PDF.js viewer</title>
    <link rel="stylesheet" href="${static}/viewer/viewer.min.css"/>
    <script src="${static}/jquery/jquery-1.8.3.min.js"></script>
    <script src="${static}/viewer/viewer-jquery.min.js"></script>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
</head>
<body style="height: 100%;">
<script>
    $(function () {
        $('#imgFile').viewer({
            navbar: false,
            toolbar:false,
            button:false
        });
    });
</script>
<%--<input type="hidden" id="fileId" value="${id}">--%>
<div style="text-align: center;" style="height:500px;">
    <img id="imgFile" data-original="${filePath}" src="${filePath}" style="height:500px;">
    <script>
        $(function(){
            $('#imgFile').height(($(window).height()-20)+'px');
            $('#imgFile').click();
        })
    </script>
</div>
</body>
</html>
