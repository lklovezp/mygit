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
 * ʹ�ö�ʱ��������ҵ 
 * ��spring���ɣ���quartz��ʱ����
 * 
 * @author xuguanghui
 * @version $Id: JobProcessing.java, v 0.1 2013-4-3 ����03:19:31 admi Exp $
 */
@Service("jobProcessing")
public class JobProcessing {

    /**��־*/
    private static final Log      log   = LogFactory.getLog(JobProcessing.class);

    @Autowired
    private JobManager            jobManager;

    /**keyΪJobType��Ӧ��code,valueΪJobItem��ʵ��**/
    private Map<Integer, JobItem> items = new HashMap<Integer, JobItem>();

    @Value("#{settings['WHETHER_THE_JOB_RUN']}")
    private String                whetherToRun;                                  //��ҵ�Ƿ�ִ�� 0ִ��1��ִ��

    /**
     * ��ҵ�������� ��quartz��ʱ����
     */
    public void execute() {
        if (!StringUtils.equals("0", whetherToRun)) {
            return;//Ϊ�򵥿��ǣ��˴�ֱ�ӷ��أ���ʵ��ʱ��̨�����Իᶨʱ���ô˷���
        }
        List<Job> jobList = jobManager.getAllJob();
        if (log.isDebugEnabled()) {
            log.debug("ϵͳ����Ҫ�����Ķ�ʱ��Ŀ��" + jobList.size());
        }
        for (Job job : jobList) {
            JobItem it = items.get(job.getType());
            if (null == it) {
                log.error("�Ҳ�����Ӧ����ҵ���ͣ���ҵID��" + job.getId());
                continue;
            }
            try {
                it.execute(job);//�˴�������ҵ���� ��Ӧ��Ӧ��ʵ���� ����Ӧ����
                log.info("��ҵ������ɣ���ҵID��" + job.getId());
            } catch (AppException e) {
                //��ҵ����ʧ�� �޸���ҵʧ�ܴ�����ʧ��ԭ�򣬱�����ҵ��Ϣ���ȴ��´δ���
                log.info("��ҵ����ʧ��!");
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