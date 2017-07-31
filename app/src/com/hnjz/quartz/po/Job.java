package com.hnjz.quartz.po;

import java.sql.Clob;
import java.util.Date;

import com.hnjz.sys.po.TSysUser;

/**
 * 后台处理作业
 * 
 * @author xuguanghui
 * @version $Id: Job.java, v 0.1 2013-4-3 上午11:06:11 admi Exp $
 */
public class Job {

    private String            id;
    private int               type;                 //定时器类型
    private Clob              data;                 //定时器执行所需数据 大字段
    private String            dataStr;              //定时器执行所需数据 字符串
    private String            billNo;               //单据号
    private Date              created;              //创建时间
    private TSysUser              createBy;             //创建人
    private int               failNum;              //失败次数
    private String            failNote;             //失败原因

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Clob getData() {
        return data;
    }

    public void setData(Clob data) {
        this.data = data;
    }

    public String getDataStr() {
        return dataStr;
    }

    public void setDataStr(String dataStr) {
        this.dataStr = dataStr;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public TSysUser getCreateBy() {
        return createBy;
    }

    public void setCreateBy(TSysUser createBy) {
        this.createBy = createBy;
    }

    public int getFailNum() {
        return failNum;
    }

    public void setFailNum(int failNum) {
        this.failNum = failNum;
    }

    public String getFailNote() {
        return failNote;
    }

    public void setFailNote(String failNote) {
        this.failNote = failNote;
    }

}
