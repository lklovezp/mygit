package com.hnjz.wf;

import com.hnjz.app.work.po.WorkType;

/**
 * 申请单公用接口
 * 
 * @author zn
 * @version $Id: IApplyCommonPo.java, v 0.1 2013-1-14 上午08:41:43 Administrator
 *          Exp $
 */
public interface IApplyCommonPo {
    /**
     * 得到编号
     * @return
     */
    String getId();

    /**
     * 得到名称
     * @return
     */
    String getName();

    /**
     * 得到流程编号
     * @return
     */
    String getProcessId();

    /**
     * 得到下一步活动
     * @return
     */
    String getNextActions();

    /**
     * 任务类型的标示，参见枚举WorkTypeCode
     * @return
     */
    String getCode();
    /**
     * 任务类型的标示，参见枚举WorkTypeCode
     * @return
     */
    void setCode(String code);

    /**
     * 设置流程编号
     * @param processId
     */
    void setProcessId(String processId);

    /**
     * 设置任务编号
     * @param taskId
     */
    void setTaskId(String taskId);

    /**
     * 设置流程是否结束
     * @param isOver
     */
    void setIsOver(Boolean isOver);

    /**
     * 设置下步活动
     * @param nextActions
     */
    void setNextActions(String nextActions);

    /**
     * 设置下步操作人
     * @param nextOper
     */
    void setNextOper(String nextOper);
}
