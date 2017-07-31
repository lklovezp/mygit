package com.hnjz.app.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hnjz.app.work.manager.WorkReportManager;
import com.hnjz.common.upload.FileUpDownUtil;

public class WorkReportServlet extends HttpServlet {
    private Logger            log              = Logger.getLogger(this.getClass());
    private WorkReportManager workReportManager;

    /**  */
    private static final long serialVersionUID = 1L;

    /** 上传文件的目录 */
    private String            uploadPath       = FileUpDownUtil.path.concat(File.separator).concat(
                                                   "temp");
    /** 临时文件目录 */
    private String            tempPath         = FileUpDownUtil.path.concat(File.separator)
                                                   .concat("temp").concat(File.separator)
                                                   .concat("buffer");
    /** 解压文件目录 */
    private String            unzipPath        = FileUpDownUtil.path.concat(File.separator)
                                                   .concat("temp").concat(File.separator)
                                                   .concat("unzip");

    public WorkReportServlet() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
                                                                               throws ServletException,
                                                                               IOException {

        doPost(request, response);
    }

    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest request, HttpServletResponse response)
                                                                                throws ServletException,
                                                                                IOException {
        try {
            //文件存放文件夹及临时文件夹创建
            File uploadFile = new File(uploadPath);
            if (!uploadFile.exists()) {
                uploadFile.mkdirs();
            }
            File tempPathFile = new File(tempPath);
            if (!tempPathFile.exists()) {
                tempPathFile.mkdirs();
            }
            // 创建一个基于磁盘的文件工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 设置工厂约束
            factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
            factory.setRepository(tempPathFile);// 设置缓冲区目录
            // 创建一个新文件上传处理程序
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 设置总体请求的大小约束
            List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
            Iterator<FileItem> i = items.iterator();
            // 得到传入的内容
            String filePath = "";
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.getFieldName().equals("file")) {
                    String fileName = fi.getName();
                    filePath = uploadPath.concat(File.separator).concat(fileName);
                    if (fileName != null) {
                        File fullFile = new File(fi.getName());
                        File savedFile = new File(uploadPath, fullFile.getName());
                        fi.write(savedFile);
                    }
                }
            }
            String workId = workReportManager.saveParseReportInfo(filePath, unzipPath);
            PrintWriter out = response.getWriter();
            out.print(workId);
        } catch (Exception e) {
            log.error("任务上报出错", e);
        }
    }

    public void init() throws ServletException {
        WebApplicationContext wac = WebApplicationContextUtils
            .getRequiredWebApplicationContext(this.getServletContext());
        workReportManager = (WorkReportManager) wac.getBean("workReportManager");
    }

}
