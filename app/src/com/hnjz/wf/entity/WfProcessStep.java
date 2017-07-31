package com.hnjz.wf.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ���̲���
 * 
 * @author zn
 * @version $Id: WfProcessStep.java, v 0.1 2013-1-14 ����06:34:39 Administrator
 *          Exp $
 */
@Entity
@Table(name = "WF_PROCESS_STEP")
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WfProcessStep extends CommonPo {
    /**  */
    private static final long serialVersionUID = 1L;
    /** ���̷������ */
    private WfProcessBusiness wfProcessBusiness;
    /** �������� */
    private String stepName;
    /** ҳ������ */
    private String pageName;

    public WfProcessStep() {
    }

    public WfProcessStep(WfProcessBusiness wfProcessBusiness, String stepName,
	    String pageName) {
	this.wfProcessBusiness = wfProcessBusiness;
	this.stepName = stepName;
	this.pageName = pageName;
    }
    
    public JSONObject toJson() throws JSONException{
	JSONObject jsonObj = new JSONObject();
	jsonObj.put("name", this.getStepName());
	jsonObj.put("page", this.getPageName());
	return jsonObj;
    }

    public String getStepName() {
	return stepName;
    }

    public void setStepName(String stepName) {
	this.stepName = stepName;
    }

    public WfProcessBusiness getWfProcessBusiness() {
	return wfProcessBusiness;
    }

    public void setWfProcessBusiness(WfProcessBusiness wfProcessBusiness) {
	this.wfProcessBusiness = wfProcessBusiness;
    }

    public String getPageName() {
	return pageName;
    }

    public void setPageName(String pageName) {
	this.pageName = pageName;
    }

}