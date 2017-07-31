/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.po;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * �������͹���
 * 
 * @author wumi
 * @version $Id: WorkType.java, v 0.1 Jan 28, 2013 11:18:16 AM wumi Exp $
 */
public class WorkType extends BaseObj {

    /**  */
    private static final long serialVersionUID = -1922268438923380141L;

    private String            id;

    /** code */
    private String            code;
    /** ���� */
    private String            name;
    /** ��ע */
    private String            note;
    /** ִ���������� */
    private String            zfdxlx;
    /** ���ڵ� */
    private String            pid;
    /** ���� */
    private Integer           order;
    /** �Ƿ��ܴ����������� */
    private String            isjjrw;
    /** ��˼��� */
    private String            shjb;
    /** ִ�н����url */
    private String            url;
    private String            url2;//ִ��url2
    /***
     * �ֻ�urlactivity��
     */
    private String            sjurl;
    /***
     * ������
     */
    private String            gzliu;

    public Map<String, Object> toJcdtypeTreeMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", getId());
        map.put("pid", getPid() == null ? "1" : getPid());
        map.put("name", getName());
        map.put("isParent", true);
        map.put("type", "WorkType");
        return map;
    }

    /**
     * �ֻ���ʹ��
     * @return
     */
    public Map<String, Object> toMobileTreeMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", getId());
        if (!getId().equals("0")) {
            map.put("pid", getPid() == null ? "0" : getPid());
        }
        map.put("name", getName());
        map.put("type", getId().equals("0") ? "o" : "u");
        return map;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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