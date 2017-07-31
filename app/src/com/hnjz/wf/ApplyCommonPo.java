package com.hnjz.wf;

import java.util.Date;

import com.hnjz.wf.entity.CommonPo;

/**
 * 流程公用实体字段
 * 
 * @author zn
 * @version $Id: ApplyCommonPo.java, v 0.1 2013-1-10 上午07:35:47 Administrator
 *          Exp $
 */
public class ApplyCommonPo extends CommonPo implements IApplyCommonPo {
    /**  */
    private static final long serialVersionUID = 1L;

    /** 名称（对应各申请单的名称字段，如：Work中的WORK_NAME_;在配置时将此字段应映为name） */
    private String            name;

    /** 流程编号 */
    private String            processId;

    /** 任务编号 */
    private String            taskId;

    /** 是否结束 */
    private Boolean           isOver;
   
    /**结束日期*/
    private Date              endTime;

    /** 下步操作 */
    private String            nextActions;

    /** 下步操作人 */
    private String            nextOper;

    /** 任务类型的标示，参见枚举WorkTypeCode */
    private String            code;
    
    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Boolean getIsOver() {
        return isOver;
    }

    public void setIsOver(Boolean isOver) {
        this.isOver = isOver;
    }

    public String getNextActions() {
        return nextActions;
    }

    public void setNextActions(String nextActions) {
        this.nextActions = nextActions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNextOper() {
        return nextOper;
    }

    public void setNextOper(String nextOper) {
        this.nextOper = nextOper;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


}
