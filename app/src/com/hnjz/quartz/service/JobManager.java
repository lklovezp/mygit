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
 * ��̨��ҵ����
 * 
 * @author xuguanghui
 * @version $Id: JobManager.java, v 0.1 2013-4-3 ����02:42:59 admi Exp $
 */
@Service("jobManager")
public class JobManager extends ManagerImpl {
    /**��־*/
    private static final Log log = LogFactory.getLog(JobManager.class);
    
    @Autowired
    private JobDao jobDao;

    /**
     * 
     * ����id����ҵ
     * @param id
     * @return
     */
    public Job getJobById(String id) {
        return (Job) this.dao.get(Job.class, id);
    }

    /**
     * �����账������ҵ��Ϣ ���ݺŲ����ظ�?
     * 
     * @param jobType ��ҵ����
     * @param billNo ���ݺ�
     * @throws AppException
     */
    public void saveJob(JobType jobType, String billNo) throws AppException {
        this.saveJob(jobType, billNo, null, null);
    }

    /**
     * �����账������ҵ��Ϣ ���ݺŲ����ظ�?
     * 
     * @param jobType ��ҵ����
     * @param billNo ���ݺ�
     * @param dataStr ��ʱ��ִ��������ַ������͵�����
     * @param data  ��ʱ��ִ����������� ���ֶ�
     * @throws AppException
     */
    public void saveJob(JobType jobType, String billNo, String dataStr, Object data)
                                                                                    throws AppException {
        if (!StringUtils.isNotBlank(billNo) || StringUtils.isNotBlank(dataStr)) {
            throw new AppException("�봫�˵��ݺź��ַ������͵����ݣ�");
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
     * �޸���ҵ
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
        log.info("��ҵ����ʧ�ܣ���ҵID��" + id);
    }

    /**
     * ɾ����ҵ
     * 
     * @param id
     */
    public void removeJob(String id) {
        this.dao.remove(Job.class, id);
    }

    /**
     * ��ѯ������ҵ
     * 
     * @return
     */
    public List<Job> getAllJob() {
        return jobDao.getAllJob();

    }
}