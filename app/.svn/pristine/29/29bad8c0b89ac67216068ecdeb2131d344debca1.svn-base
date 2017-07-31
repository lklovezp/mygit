package com.hnjz.quartz.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.common.AppException;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.quartz.JobType;
import com.hnjz.quartz.po.Job;

/**
 * 后台作业处理
 * 
 * @author xuguanghui
 * @version $Id: JobManager.java, v 0.1 2013-4-3 下午02:42:59 admi Exp $
 */
@Service("jobManager")
public class JobManager extends ManagerImpl {
    /**日志*/
    private static final Log log = LogFactory.getLog(JobManager.class);
    
    @Autowired
    private JobDao jobDao;

    /**
     * 
     * 根据id查作业
     * @param id
     * @return
     */
    public Job getJobById(String id) {
        return (Job) this.dao.get(Job.class, id);
    }

    /**
     * 创建需处理的作业信息 单据号不能重复?
     * 
     * @param jobType 作业类型
     * @param billNo 单据号
     * @throws AppException
     */
    public void saveJob(JobType jobType, String billNo) throws AppException {
        this.saveJob(jobType, billNo, null, null);
    }

    /**
     * 创建需处理的作业信息 单据号不能重复?
     * 
     * @param jobType 作业类型
     * @param billNo 单据号
     * @param dataStr 定时器执行所需的字符串类型的数据
     * @param data  定时器执行所需的数据 大字段
     * @throws AppException
     */
    public void saveJob(JobType jobType, String billNo, String dataStr, Object data)
                                                                                    throws AppException {
        if (!StringUtils.isNotBlank(billNo) || StringUtils.isNotBlank(dataStr)) {
            throw new AppException("请传人单据号和字符串类型的数据！");
        }
        Job job = new Job();
        job.setType(jobType.getCode());
        job.setBillNo(billNo);
        job.setDataStr(dataStr);
        //job.setData(data);
        job.setCreateBy(CtxUtil.getCurUser());
        job.setCreated(new Date());
        job.setFailNum(0);
        this.dao.save(job);
    }

    /**
     * 修改作业
     * 
     * @param job
     */
    public void saveJob(Job job) {
        String id = job.getId();
        Job newJob = this.getJobById(id);
        int failNum = job.getFailNum();
        String failNote = job.getFailNote();
        newJob.setFailNum(failNum);
        newJob.setFailNote(failNote);
        this.dao.save(newJob);
        log.info("作业处理失败！作业ID：" + id);
    }

    /**
     * 删除作业
     * 
     * @param id
     */
    public void removeJob(String id) {
        this.dao.remove(Job.class, id);
    }

    /**
     * 查询所有作业
     * 
     * @return
     */
    public List<Job> getAllJob() {
        return jobDao.getAllJob();

    }
}
