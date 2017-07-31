package com.hnjz.wf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hnjz.app.work.enums.WorkTransferDirectionEnum;
import com.hnjz.sys.po.TSysUser;

/**
 * 流程传递参数的对象
 * @author zn
 * @version $Id: ProcessArgsBean.java, v 0.1 2013-1-25 上午03:10:42 zn Exp $
 */
public class ProcessArgsBean implements Serializable {
    /**  */
    private static final long         serialVersionUID = 1L;
    /** 申请单编号 */
    private String                    applyId;
    /** 流程中转方向 */
    private WorkTransferDirectionEnum direction;
    /** 当前操作人 */
    private TSysUser                      currentOper;
    /** 下步操作人 */
    private List<TSysUser>                nextOpers        = new ArrayList<TSysUser>();
    /** 意见 */
    private String                    opinion;
    /** 结果 */
    private String                    result;

    public TSysUser getCurrentOper() {
        return currentOper;
    }

    public void setCurrentOper(TSysUser currentOper) {
        this.currentOper = currentOper;
    }

    public WorkTransferDirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(WorkTransferDirectionEnum direction) {
        this.direction = direction;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<TSysUser> getNextOpers() {
        return nextOpers;
    }

    public void setNextOpers(List<TSysUser> nextOpers) {
        this.nextOpers = nextOpers;
    }
}
