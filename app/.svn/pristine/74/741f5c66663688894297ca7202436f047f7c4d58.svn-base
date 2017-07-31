/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.data.enums;

/**
 * 附件类型
 * @author xuguanghui
 * @version $Id: AccessoryType.java, v 0.1 Jun 17, 2013 10:53:19 AM Administrator Exp $
 */
public enum DataFileType {

    JIANCHAJILUSAOMIAOJIAN("001","检查记录扫描件"),//检查记录扫描件
    XIANCHANGKANCHATU("002","现场勘察示意图"),//现场勘察示意图
    DIANZIFUJIAN("003","电子附件"),//电子附件
    
    XUNWENBILU("010","询问笔录"),//询问笔录
    KANCHABILU("011","勘察笔录"),//勘察笔录
    ZHENGJUXINXI("012","证据信息"),//证据信息
    TUPIAN("013","图片资料"),//图片资料
    YINPIN("014","音频资料"),//音频资料
    SHIPIN("015","视频资料"),//视频资料
    QITA("016","其他资料"),//其他资料
    
    TONGYONGRENWUFUJIAN("020","电子附件");//通用任务附件
    private DataFileType(String code, String name) {
        this.code = code;
        this.name = name;
    }
    /**
     * 根据编号得到附件类型
     * 
     * @param code
     * @return
     */
    public static DataFileType getType(String code){
        DataFileType type = null;
        for(DataFileType a :values()){
            if(a.code.equals(code)){
                type = a;
                break;
            }
        }
        return type;
    }
    
    /** 编码*/
    private String code;
    /** 名称 */
    private String name;
    
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
    
    
}
