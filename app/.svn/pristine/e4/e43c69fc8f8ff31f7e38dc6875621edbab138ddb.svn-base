package com.hnjz.app.work.po;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.dom4j.Node;

import com.hnjz.common.util.FileUtil;
import com.hnjz.common.util.FileUtil.FileSizeEnum;
import com.hnjz.wf.entity.CommonPo;

/**
 * 任务附件
 * @author zn
 * @version $Id: TCommFile.java, v 0.1 2013-2-5 上午07:07:38 zn Exp $
 */
public class WorkCommFile extends CommonPo {

    /**  */
    private static final long serialVersionUID = 1L;
    /** 任务 */
    private Work              work;
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

    private String            type;                 //附件类型
    private String            extInfo;              //附件扩展信息

    public WorkCommFile() {
    }

    public WorkCommFile(String oldWorkId, Work work, Node node) {
        if (node != null) {
            this.setWork(work);
            this.setName(node.selectSingleNode("name") == null ? "" : node.selectSingleNode("name")
                .getText());
            this.setSize(node.selectSingleNode("size") == null ? 0 : Long.parseLong(node
                .selectSingleNode("size").getText()));
            this.setUrl(node.selectSingleNode("url") == null ? "" : node.selectSingleNode("url")
                .getText().replace(oldWorkId, work.getId()));
            this.setDesc(node.selectSingleNode("desc") == null ? "" : node.selectSingleNode("desc")
                .getText());
            this.setContentType(node.selectSingleNode("contenttype") == null ? "" : node
                .selectSingleNode("contenttype").getText());
            this.setSuffix(node.selectSingleNode("suffix") == null ? "" : node.selectSingleNode(
                "suffix").getText());
            this.setType(node.selectSingleNode("type") == null ? "" : node.selectSingleNode("type")
                .getText());
            this.setExtInfo(node.selectSingleNode("extInfo") == null ? "" : node.selectSingleNode(
                "extInfo").getText());
        }
    }

    public WorkCommFile(Work work, String name, Long size, String url, String desc,
                        String contentType, String suffix) {
        this.work = work;
        this.name = name;
        this.size = size;
        this.url = url;
        this.desc = desc;
        this.contentType = contentType;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", getId());
        map.put("name", getFullName());
        map.put("url", getUrl());
        map.put("size", FileUtil.sizeFormat(getSize(), FileSizeEnum.B, FileSizeEnum.KB));
        map.put("desc", getDesc());
        return map;
    }

    protected String getFullName() {
        if (StringUtils.isNotBlank(getSuffix())) {
            return getName().concat(".").concat(getSuffix());
        }
        return getName();
    }

    public Element addXml(Element e) {
        Element ele = e.addElement("file");
        ele.addElement("id").setText(getId());
        ele.addElement("name").setText(getName());
        ele.addElement("size").setText(getSize().toString());
        ele.addElement("url").setText(getUrl());
        ele.addElement("desc").setText(getDesc() == null ? "" : getDesc());
        ele.addElement("contenttype").setText(getContentType() == null ? "" : getContentType());
        ele.addElement("suffix").setText(getSuffix() == null ? "" : getSuffix());
        ele.addElement("type").setText(getType() == null ? "" : getType());
        ele.addElement("extInfo").setText(getExtInfo() == null ? "" : getExtInfo());
        return ele;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

}