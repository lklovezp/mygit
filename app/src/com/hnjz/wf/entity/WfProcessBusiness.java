package com.hnjz.wf.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 流程业务
 * @author zn
 * @version $Id: WfProcessBusiness.java, v 0.1 2013-4-8 上午12:41:39 zn Exp $
 */
@Entity
@Table(name = "WF_PROCESS_BUSINESS")
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WfProcessBusiness extends CommonPo {
    /**  */
    private static final long  serialVersionUID = 1L;
    /** 流程KEY值 */
    private String             processKey;
    /** 业务KEY值 */
    private String             businessKey;
    /** 步骤集合 */
    private Set<WfProcessStep> wfProcessSteps   = new HashSet<WfProcessStep>();

    public WfProcessBusiness() {
    }

    public WfProcessBusiness(String processKey, String businessKey) {
        this.processKey = processKey;
        this.businessKey = businessKey;
    }

    @Column(name = "BUSINESS_KEY_", length = 10)
    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Set<WfProcessStep> getWfProcessSteps() {
        return wfProcessSteps;
    }

    public void setWfProcessSteps(Set<WfProcessStep> wfProcessSteps) {
        this.wfProcessSteps = wfProcessSteps;
    }

    public String getProcessKey() {
        return processKey;
    }

    public void setProcessKey(String processKey) {
        this.processKey = processKey;
    }
}
