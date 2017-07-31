package com.hnjz.app.clent.po;

import java.util.HashMap;
import java.util.Map;

import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.util.FileUtil;
import com.hnjz.common.util.FileUtil.FileSizeEnum;
import com.hnjz.wf.entity.CommonPo;

/**
 * 客户端
 * @author zn
 * @version $Id: TClient.java, v 0.1 2013-5-13 上午01:45:58 zn Exp $
 */
public class TClient extends CommonPo {

    /**  */
    private static final long serialVersionUID = 1L;
    /** 文件名称 */
    private String            name;
    /** 文件大小 */
    private Long              size;
    /** 文件路径 */
    private String            url;
    /** 文件描述 */
    private String            desc;
    /** 文件类型 */
    private String            contentType;
    /** 文件后缀 */
    private String            suffix;
    /** 配置版本 */
    private String            pzbb;
    /** 版本号 */
    private Integer           bbh;
    /** 是否强制更新 */
    private Boolean           isforce;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", getId());
        map.put("name", getName());
        map.put("size", FileUtil.sizeFormat(getSize(), FileSizeEnum.B, FileSizeEnum.KB) + "KB");
        map.put("desc", getDesc());
        map.put("version", getPzbb().concat(".").concat(getBbh().toString()));
        map.put("isforce", getIsforce());
        map.put("oper", OperateUtil.getOperate(getId()));
        return map;
    }

    public Map<String, Object> toMobileMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("version", getBbh());
        map.put("isforce", getIsforce());
        map.put("id", getId());
        map.put("desc", getDesc());
        return map;
    }

    public String getPzbb() {
        return pzbb;
    }

    public void setPzbb(String pzbb) {
        this.pzbb = pzbb;
    }

    public Integer getBbh() {
        return bbh;
    }

    public void setBbh(Integer bbh) {
        this.bbh = bbh;
    }

    public Boolean getIsforce() {
        return isforce;
    }

    public void setIsforce(Boolean isforce) {
        this.isforce = isforce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    
}