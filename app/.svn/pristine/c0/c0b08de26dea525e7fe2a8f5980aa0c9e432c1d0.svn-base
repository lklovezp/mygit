package com.hnjz.app.work.beans;

import java.text.SimpleDateFormat;

import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.po.VTjWorkList;

/**
 * 所有任务统计列表对象（VTjWorkList多一个序号，导出时使用）
 * @author zn
 * @version $Id: VTjWorkListBean.java, v 0.1 2013-4-19 上午01:39:22 zn Exp $
 */
public class VTjWorkListBean extends VTjWorkList {
    /**  */
    private static final long serialVersionUID = 1L;
    private int               seq;
    private String            endTimeStr;
    private String            startTimeStr;

    public VTjWorkListBean() {
    }

    public VTjWorkListBean(int index, VTjWorkList t) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        setEndTimeStr(sdf.format(t.getEndTime()));
        setNote(t.getNote());
        setSeq(index);
        setStartTimeStr(sdf.format(t.getStartTime()));
        setState(t.getState() == null ? "" : WorkState.getNote(t.getState()));
        setWorkName(t.getWorkName());
        setWorkTypeName(t.getWorkTypeName());
        setWryName(t.getWryName());
        setWrySsds(t.getWrySsds());
        setZxr(t.getZxr());
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

}
