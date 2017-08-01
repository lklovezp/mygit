<%@ page contentType="application/vnd.ms-excel;charset=GBK"%>
<%
response.setHeader("Content-disposition","inline; filename=test.xls");
out.clear();
%>
<%=AddExcelHead()%>
		<spring:message code="userRight"/><br>
		<spring:message code="sysName"/><br>
		<spring:message code="version"/><br>
下达任务列表 
<table width="100%" border="1" cellspacing="0" cellpadding="0">
    <tr>
    <td>序号</td>
    <td>标题1</td>
    <td>标题2</td>
    <td>标题3</td>
    <td>标题4</td>
    <td>标题5</td>
    <td>标题6</td>
    <td>标题7</td>
    </tr>
<%
   for(int i=0; i<10; i++){
    %>
    <tr>
    <td><%=i%>.</td>
    <td><%=i+100%></td>
    <td><%=i+101%></td>
    <td><%=i+102%></td>
    <td><%=i+103%></td>
    <td><%=i+104%></td>
    <td> <%=i+105%></td>
    <td><%=i+106%></td>
   </tr>
   <%}//end For
   %>
</table>
下达任务列表
<%=AddExcelbottom()%>
<%!
    private static String AddExcelHead(){ 
        StringBuffer sb = new StringBuffer();
        sb.append("<html xmlns:x=\"urn:schemas-microsoft-com:office:excel\">");
        sb.append(" <head>");
        sb.append(" <!--[if gte mso 9]><xml>");
        sb.append("<x:ExcelWorkbook>");
        sb.append("<x:ExcelWorksheets>");
        sb.append("<x:ExcelWorksheet>");
        sb.append("<x:Name></x:Name>"); 
        sb.append("<x:WorksheetOptions>");
        sb.append("<x:Print>");            
        sb.append("<x:ValidPrinterInfo />");
        sb.append(" </x:Print>"); 
        sb.append("</x:WorksheetOptions>");
        sb.append("</x:ExcelWorksheet>");
        sb.append("</x:ExcelWorksheets>");
        sb.append("</x:ExcelWorkbook>");
        sb.append("</xml>");
        sb.append("<![endif]-->"); 
        sb.append(" </head>"); 
        sb.append("<body>");
        return sb.toString(); 
    }
    private static String AddExcelbottom(){
        StringBuffer sb = new StringBuffer();
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
%>