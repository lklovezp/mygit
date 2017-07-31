package com.hnjz.wf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hnjz.app.work.enums.WorkTransferDirectionEnum;
import com.hnjz.sys.po.TSysUser;

/**
 * ���̴��ݲ����Ķ���
 * @author zn
 * @version $Id: ProcessArgsBean.java, v 0.1 2013-1-25 ����03:10:42 zn Exp $
 */
public class ProcessArgsBean implements Serializable {
    /**  */
    private static final long         serialVersionUID = 1L;
    /** ���뵥��� */
    private String                    applyId;
    /** ������ת���� */
    private WorkTransferDirectionEnum direction;
    /** ��ǰ������ */
    private TSysUser                      currentOper;
    /** �²������� */
    private List<TSysUser>                nextOpers        = new ArrayList<TSysUser>();
    /** ��� */
    private String                    opinion;
    /** ��� */
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