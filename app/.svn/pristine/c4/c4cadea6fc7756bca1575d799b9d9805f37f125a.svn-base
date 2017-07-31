package com.hnjz.quartz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hnjz.common.AppException;
import com.hnjz.quartz.po.Job;

/**
 * 使用定时器处理作业 
 * 与spring集成，由quartz定时调度
 * 
 * @author xuguanghui
 * @version $Id: JobProcessing.java, v 0.1 2013-4-3 下午03:19:31 admi Exp $
 */
@Service("jobProcessing")
public class JobProcessing {

    /**日志*/
    private static final Log      log   = LogFactory.getLog(JobProcessing.class);

    @Autowired
    private JobManager            jobManager;

    /**key为JobType对应的code,value为JobItem的实现**/
    private Map<Integer, JobItem> items = new HashMap<Integer, JobItem>();

    @Value("#{settings['WHETHER_THE_JOB_RUN']}")
    private String                whetherToRun;                                  //作业是否执行 0执行1不执行

    /**
     * 作业处理方法 由quartz定时调用
     */
    public void execute() {
        if (!StringUtils.equals("0", whetherToRun)) {
            return;//为简单考虑，此处直接返回，其实此时后台程序仍会定时调用此方法
        }
        List<Job> jobList = jobManager.getAllJob();
        if (log.isDebugEnabled()) {
            log.debug("系统中需要处理的定时数目：" + jobList.size());
        }
        for (Job job : jobList) {
            JobItem it = items.get(job.getType());
            if (null == it) {
                log.error("找不到对应的作业类型，作业ID：" + job.getId());
                continue;
            }
            try {
                it.execute(job);//此处根据作业类型 对应相应的实现类 做相应处理
                log.info("作业处理完成！作业ID：" + job.getId());
            } catch (AppException e) {
                //作业处理失败 修改作业失败次数和失败原因，保存作业信息，等待下次处理
                log.info("作业处理失败!");
                int failNum = job.getFailNum() + 1;
                job.setFailNum(failNum);
                if (StringUtils.isNotBlank(e.getMessage())) {
                    job.setFailNote(StringUtils.substring(e.getMessage(), 0, 100));
                }
                jobManager.saveJob(job);
            }
        }
    }

    public void setItems(Map<Integer, JobItem> items) {
        this.items = items;
    }

}
