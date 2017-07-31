<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修复 Internet Explorer 兼容性视图问题</title>
</head>
<style>
p{color:#333;font-size:12px;line-height:24px;}
a{color:#36F;font-size:12px;line-height:24px; text-decoration:none;}
h3{color:#333;font-size:14px;line-height:35px;}
#wrap{width:600px;margin:0 auto;}
.bottom{width:598px;height:70px;background:#F5F5F5;border:#DDDDDD 1px solid; }
.bottom p{margin-left:20px;margin-right:20px;}
</style>
<body>
<div id="wrap">
 <h3>修复 Internet Explorer 兼容性视图问题</h3>
 <p>Internet Explorer 拥有兼容性视图（或模式），该功能可影响部分界面的显示方式。如果您针对 <%out.println(request.getServerName()); %> 启用了兼容性视图，则部分界面可能无法正确显示。我们建议您从启用了兼容性视图的网站的列表中删除 <%out.println(request.getServerName()); %>。</p>
 <h3>说明</h3>
 <strong><p>IE10</p></strong>
 <p>1.在 Internet Explorer 中，按<strong> Alt</strong> 键以显示菜单栏，或在按住地址栏的同时选择<strong>菜单栏</strong>。</p>
 <p>2.点击<strong>工具</strong>，然后选择<strong>兼容性视图设置</strong>。</p>
 <p>3.在“已添加到兼容性视图中的网站”下，选择<strong> <%out.println(request.getServerName()); %></strong>。</p>
 <p>4.点击<strong>删除</strong>。</p>
 <strong><p>IE9</p></strong>
 <p>1.在 Internet Explorer 中，按<strong> Alt</strong> 键以显示菜单栏，或在按住地址栏的同时选择<strong>菜单栏</strong>。</p>
 <p>2.点击<strong>工具</strong>，然后选择<strong>兼容性视图设置</strong>。</p>
 <p>3.在“已添加到兼容性视图中的网站”下，选择<strong> <%out.println(request.getServerName()); %></strong>。</p>
 <p>4.点击<strong>删除</strong>。</p>
 <strong><p>IE8</p></strong>
 <p>1.在 Internet Explorer 中，打开顶部的<strong>工具</strong>菜单，然后选择<strong>兼容性视图设置</strong>。</p>
 <p>2.在“已添加到兼容性视图中的网站”下，选择<strong> <%out.println(request.getServerName()); %></strong>。</p>
 <p>3.点击<strong>删除</strong>。</p>
 <h3>更多问题排查步骤</h3>
 <p>如果您在列表上没有看到 <%out.println(request.getServerName()); %>，或者在执行了上述步骤之后仍然看到有关兼容性视图的警告，请确保您的计算机已安装最新的 Windows 更新。请参阅 Microsoft 关于<a href="http://www.update.microsoft.com/windowsupdate/v6/thanks.aspx?ln=zh-CN&amp;&amp;thankspage=5"" target="_blank">获取 Windows 更新</a>的说明。</p>
 <p>但是，如果您不想安装最新的 Windows 更新，则可以执行以下操作来处理兼容性视图警告：</p>
 <p>1.在 Internet Explorer 中，打开顶部的<strong>工具</strong>菜单，然后选择<strong>兼容性视图设置</strong>。您可能需要按<strong> Alt</strong> 键或按住地址栏，才能看到菜单栏。</p>
 <p>2.请确保 <%out.println(request.getServerName()); %> 不在“已添加到兼容性视图中的网站”列表中。如果 <%out.println(request.getServerName()); %> 在该列表中，则请点击<strong>删除</strong>。</p>
 <p>3.在该窗口底部，确保未选中“在兼容性视图中显示所有网站”和“从 Microsoft 下载更新的兼容性列表”或“包括来自 Microsoft 的更新的网站列表”旁的复选框。</p>
 <p>4.点击<strong>关闭</strong>。</p>
 <div class="bottom">
 <p><strong>请注意：</strong>如果您的 Windows 计算机由您的单位管理，那么您可能无法访问兼容性视图设置或从列表中删除 <%out.println(request.getServerName()); %>。请与您单位的管理员或支持人员联系以获取帮助。</p>
 </div>
</div>
</body>
</html>
