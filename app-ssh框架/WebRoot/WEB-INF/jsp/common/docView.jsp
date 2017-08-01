<%@ page contentType="text/html; charset=gb2312" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>在线预览</title>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <script src="${static}/jquery/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${static}/common/fjView.js"></script>
    <script type="text/javascript" src="${static}/layer/layer.js"></script>
    <link href="${app}/common.css" rel="stylesheet" type="text/css"/>
    <script language=javascript>
        function StatusMsg(mString) {
            if (mString == undefined) {
                mString = "iWebOffice2003插件未安装！"
                StatusBar.innerText = mString;
                $('#install').show();
            } else {
                $('#install').hide();
                StatusBar.innerText = mString;
            }
        }

        //作用：载入iWebOffice
        function Load() {
            try {
                $('#WebOffice').height(($(window).height()-150)+'px');
                var wordFilePath = document.getElementById('filePath').value;
                var wordFileExtension = document.getElementById('fileExtension').value;
                if (!wordFilePath) {
                    alert("获取文件失败！");
                    return;
                }
                //以下属性必须设置，实始化iWebOffice
                webform.WebOffice.WebUrl = "<%=basePath%>/office/WebOfficeServlet.htm?wordFilePath=" + wordFilePath;
                webform.WebOffice.EditType = "0";
                webform.WebOffice.FileType = wordFileExtension;
                //以下属性可以不要
                webform.WebOffice.ShowToolBar = "0"; //ShowToolBar:是否显示工具栏:1显示,0不显示
                webform.WebOffice.ShowMenu = "0"; //ShowMenu:1 显示菜单  0 隐藏菜单

                webform.WebOffice.ShowType = 1; //文档显示方式  1:表示文字批注  2:表示手写批注  0:表示文档核稿
                webform.WebOffice.WebOpen(false);
                //修订模式
                webform.WebOffice.WebShow(false);
                StatusMsg(webform.WebOffice.Status);
                /*webform.WebOffice.WebMkDirectory("C:\\iWebOffice2003");
                 mResult = webform.WebOffice.WebDownLoadFile(wordFilePath, "c:\\iWebOffice2003\\" + wordFileName);
                 StatusMsg(webform.WebOffice.Status);
                 if (mResult) {
                 webform.WebOffice.WebOpenLocalFile("c:\\iWebOffice2003\\" + wordFileName);
                 } else {
                 StatusMsg("文件打开失败...");
                 }*/
            } catch (e) {
                StatusMsg(e.description);
            }
        }

        //作用：退出iWebOffice
        function UnLoad() {
            try {
                var woffice = webform.WebOffice;
                if (!woffice.WebClose()) {
                    StatusMsg(woffice.Status);
                } else {
                    StatusMsg("关闭文档...");
                }
            } catch (e) {
            }
        }

        /**
         * 打印受理通知书
         */
        /*function printPage() {
         webform.WebOffice.WebOpenPrint();
         }*/
    </script>

</head>
<span id="install" style="color: rgb(255, 0, 0); display: none;">　★ iWebOffice2003插件未安装，请您安装插件，或者在这里<a
        href="${static}/common/iWebOffice2006.cab">【下载】</a>安装程序。</span>

<body bgcolor="#ffffff" onload="Load()" onunload="UnLoad()">
<!--引导和退出iWebOffice-->

<form name="webform" method="post" action="">
    <!--保存iWebOffice后提交表单信息-->
    <input type="hidden" id="filePath" name="filePath" value="${filePath}">
    <input type="hidden" id="fileExtension" name="fileExtension" value="${fileExtension}">

    <div id=StatusBar style="color:black;">正在打开文档...</div>
    <!-- <div id="operateDiv" style="color:black;float:right;">
        <input type="button" value="打印" onclick="printPage();" />
    </div> -->
    <!--调用iWebOffice，注意版本号，可用于升级-->
    <script src="${common}/iWebOffice2003.js"></script>
    <p>${last}</p>

    <p>${next}</p>
</form>

</body>
</html>