package com.hnjz.quartz.po;

import java.sql.Clob;
import java.util.Date;

import com.hnjz.sys.po.TSysUser;

/**
 * ��̨������ҵ
 * 
 * @author xuguanghui
 * @version $Id: Job.java, v 0.1 2013-4-3 ����11:06:11 admi Exp $
 */
public class Job {

    private String            id;
    private int               type;                 //��ʱ������
    private Clob              data;                 //��ʱ��ִ���������� ���ֶ�
    private String            dataStr;              //��ʱ��ִ���������� �ַ���
    private String            billNo;               //���ݺ�
    private Date              created;              //����ʱ��
    private TSysUser              createBy;             //������
    private int               failNum;              //ʧ�ܴ���
    private String            failNote;             //ʧ��ԭ��

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