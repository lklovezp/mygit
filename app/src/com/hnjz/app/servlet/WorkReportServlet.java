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

    /** �ϴ��ļ���Ŀ¼ */
    private String            uploadPath       = FileUpDownUtil.path.concat(File.separator).concat(
                                                   "temp");
    /** ��ʱ�ļ�Ŀ¼ */
    private String            tempPath         = FileUpDownUtil.path.concat(File.separator)
                                                   .concat("temp").concat(File.separator)
                                                   .concat("buffer");
    /** ��ѹ�ļ�Ŀ¼ */
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
            //�ļ�����ļ��м���ʱ�ļ��д���
            File uploadFile = new File(uploadPath);
            if (!uploadFile.exists()) {
                uploadFile.mkdirs();
            }
            File tempPathFile = new File(tempPath);
            if (!tempPathFile.exists()) {
                tempPathFile.mkdirs();
            }
            // ����һ�����ڴ��̵��ļ�����
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // ���ù���Լ��
            factory.setSizeThreshold(4096); // ���û�������С��������4kb
            factory.setRepository(tempPathFile);// ���û�����Ŀ¼
            // ����һ�����ļ��ϴ���������
            ServletFileUpload upload = new ServletFileUpload(factory);
            // ������������Ĵ�СԼ��
            List<FileItem> items = upload.parseRequest(request);// �õ����е��ļ�
            Iterator<FileItem> i = items.iterator();
            // �õ����������
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
            log.error("�����ϱ�����", e);
        }
    }

    public void init() throws ServletException {
        WebApplicationContext wac = WebApplicationContextUtils
            .getRequiredWebApplicationContext(this.getServletContext());
        workReportManager = (WorkReportManager) wac.getBean("workReportManager");
    }

}