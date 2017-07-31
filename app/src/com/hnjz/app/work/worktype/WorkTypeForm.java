/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.worktype;

/**
 * 任务类型表单
 * 
 * @author wumi
 * @version $Id: WorkTypeForm.java, v 0.1 Jan 28, 2013 11:43:00 AM wumi Exp $
 */
public class WorkTypeForm {

    private String  id;

    /** 名称 */
    private String  code;
    /** 名称 */
    private String  name;
    /** 备注 */
    private String  note;
    /** 执法对象类型 */
    private String  zfdxlx;
    /** 父节点 */
    private String  pid;
    /** 父节点 */
    private String  pname;
    /** 排序 */
    private Integer order;
    /** 是否能创建紧急任务 */
    private String  isjjrw;
    /** 审核级别 */
    private String  shjb;
    /** 执行界面的url */
    private String  url;

    /** 执行界面的url2 */
    private String  url2;
    
    /***
     * 手机urlactivity名
     */
    private String            sjurl;
    /***
     * 工作流
     */
    private String            gzliu;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getZfdxlx() {
        return zfdxlx;
    }

    public void setZfdxlx(String zfdxlx) {
        this.zfdxlx = zfdxlx;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getIsjjrw() {
        return isjjrw;
    }

    public void setIsjjrw(String isjjrw) {
        this.isjjrw = isjjrw;
    }

    public String getShjb() {
        return shjb;
    }

    public void setShjb(String shjb) {
        this.shjb = shjb;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getSjurl() {
        return sjurl;
    }

    public void setSjurl(String sjurl) {
        this.sjurl = sjurl;
    }

    public String getGzliu() {
        return gzliu;
    }

    public void setGzliu(String gzliu) {
        this.gzliu = gzliu;
    }

}
