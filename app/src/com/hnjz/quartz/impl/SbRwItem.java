/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.quartz.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.work.manager.WorkManagerImpl;
import com.hnjz.app.work.po.Work;
import com.hnjz.common.AppException;
import com.hnjz.quartz.po.Job;
import com.hnjz.quartz.service.JobItem;
import com.hnjz.quartz.service.JobManager;
import com.hnjz.sys.area.AreaManagerImpl;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;

/**
 * 
 * 任务上报的实现
 * @author wumi
 * @version $Id: SbRwItem.java, v 0.1 Apr 11, 2013 9:49:08 AM wumi Exp $
 */
@Service("sbRwItem")
public class SbRwItem implements JobItem {

    /**日志*/
    private static final Log log = LogFactory.getLog(SbRwItem.class);

    @Autowired
    private UserManager      userManager;

    @Autowired
    private WorkManagerImpl  workManager;
    
    @Autowired
    private AreaManagerImpl  areaManager;
    
    @Autowired
    private JobManager            jobManager;
    /** 
     * @see com.hnjz.quartz.service.JobItem#execute(com.hnjz.quartz.po.Job)
     */
    @Override
    public void execute(Job job) throws AppException {
        try {
            Work work = workManager.get(job.getBillNo());
            if (null != work.getSbsj()) {
                if (log.isDebugEnabled()) {
                    log.debug(work.getName() + "已经上报");
                }
                return;
//                throw new AppException("任务已经处理");
            }

            TSysUser u = this.userManager.getUser(job.getCreateBy().getId());
            
            //2015-3-18 修改 保存上报信息 文件的打包上传部分先去掉。只记录上报人。
            //修改方法，该方法中做两件事：1、保存work上报人。2、把work基本字段信息生成xml文档，打包上传到服务器。
            String path = workManager.saveCreateReportInfo(job.getBillNo(), u);
            if (log.isDebugEnabled()) {
                log.debug("生成文件：" + path);
            }
            File targetFile = new File(path);
            if (!targetFile.exists()) {
                throw new AppException("目标文件不存在！");
            }
            
            //上级服务器地址改为从数据库读取
            String targetUrl = areaManager.getPAreaServerByAreaId(u.getAreaId()).concat("servlet/WorkReportServlet");
            /*String targetUrl = Sys.SJURL.concat("/servlet/WorkReportServlet");*/
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(targetUrl);
            FileBody file = new FileBody(targetFile);
            //对请求的表单域进行填充  
            MultipartEntity reqEntity = new MultipartEntity();
            reqEntity.addPart("file", file);
            //设置请求  
            httpPost.setEntity(reqEntity);
            //执行  
            HttpResponse response = httpClient.execute(httpPost);

            if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
                throw new AppException("操作失败！" + response.getStatusLine().getStatusCode());
            }
            String reportWorkId = convertStreamToString(response.getEntity().getContent());
            if (StringUtils.isNotBlank(reportWorkId)) {
                workManager.saveSjid(job.getBillNo(), reportWorkId);
            } else {
                throw new AppException("上报失败！服务器问题");
            }
            if (log.isDebugEnabled()) {
                log.debug("上报成功：");
            }
            jobManager.removeJob(job.getId());//作业处理完成，删除作业
        } catch (Exception e) {
            log.error("", e);
            throw new AppException("操作失败！" + e.getMessage());
        }
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                log.error("", e);
            }
        }
        return sb.toString();
    }

}
