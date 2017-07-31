package com.hnjz.app.work.po;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Element;
import org.dom4j.Node;

import com.hnjz.sys.po.TSysUser;
import com.hnjz.wf.entity.CommonPo;

/**
 * �ճ������Ϣ�������۱���
 * @author zn
 * @version $Id: WorkJcinfo.java, v 0.1 2013-3-12 ����01:45:51 zn Exp $
 */
public class WorkJcinfo extends CommonPo {

    /**  */
    private static final long serialVersionUID = 1L;
    /** �ļ����� */
    private String            name;
    /** �ļ���С */
    private Long              size;
    /** �ļ�·�� */
    private String            url;
    /** �ļ����� */
    private String            desc;
    /** �ļ����� */
    private String            contentType;
    /** �ļ���׺ */
    private String            suffix;
    /** ����rwbh�� */
    private Work              work;
    /** �����ˣ�rwblr�� */
    private TSysUser              blrUser;
    private String            xczfbh;
    /** ��ȾԴ��ţ��������ɷ�ʱѡ��ļ��ִ�������ж�ȡ�� */
    private String            wrybh;
    /** ��ȾԴ���ƣ��������ɷ�ʱѡ��ļ��ִ�������ж�ȡ�� */
    private String            wrymc;
    /** ��ȾԴ��ַ���������ɷ�ʱѡ��ļ��ִ�������ж�ȡ�������޸ģ� */
    private String            wrydz;
    /** ���������� */
    private String            fddbr;
    /** ���������˵绰 */
    private String            fddbrdh;
    /** ���������� */
    private String            hbfzr;
    /** ���������˵绰 */
    private String            hbfzrdh;
    private String            xzqy;
    private String            sshy;
    private String            qylx;
    private String            zywrwyz;
    private String            zflxxl;
    /** ��ʼʱ�� */
    private Date              kssj;
    /** ����ʱ�� */
    private Date              jssj;
    private String            jcrbmbh;
    private String            jcrbm;
    private String            jcrbh;
    /** ����� */
    private String            jcr;
    private String            zfzh;
    private String            jlrbh;
    private String            jlr;
    private Integer           jcrys;
    private String            rwlb;
    private String            qyqk;
    private String            jcxj;
    private String            sfhg;
    /** ������������ */
    private String            sshbbm;
    private Integer           jdd;
    private Integer           jdf;
    private Integer           wdd;
    private Integer           wdm;
    private Integer           jdm;
    private Integer           wdf;
    private String            bz;
    /** ����ְ�� */
    private String            bmzw;
    /** ���ʱ�� */
    private Date              jcsj;

    public WorkJcinfo() {
    }

    public WorkJcinfo(String oldWorkId, Work work, Node node) throws ParseException {
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        setWrydz(node.selectSingleNode("wrydz") == null ? "" : node.selectSingleNode("wrydz")
            .getText());
        setFddbr(node.selectSingleNode("fddbr") == null ? "" : node.selectSingleNode("fddbr")
            .getText());
        setFddbrdh(node.selectSingleNode("fddbrdh") == null ? "" : node.selectSingleNode("fddbrdh")
            .getText());
        setHbfzr(node.selectSingleNode("hbfzr") == null ? "" : node.selectSingleNode("hbfzr")
            .getText());
        setHbfzrdh(node.selectSingleNode("hbfzrdh") == null ? "" : node.selectSingleNode("hbfzrdh")
            .getText());
        setBmzw(node.selectSingleNode("bmzw") == null ? "" : node.selectSingleNode("bmzw")
            .getText());
        setJcsj(node.selectSingleNode("jcsj") == null ? null : sdf.parse(node.selectSingleNode(
            "jcsj").getText()));
    }

    public Element addXml(Element ele) {
        ele.addElement("id").setText(getId());
        ele.addElement("name").setText(getName());
        ele.addElement("size").setText(getSize().toString());
        ele.addElement("url").setText(getUrl());
        ele.addElement("desc").setText(getDesc() == null ? "" : getDesc());
        ele.addElement("contenttype").setText(getContentType());
        ele.addElement("suffix").setText(getSuffix() == null ? "" : getSuffix());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ele.addElement("wrydz").addText(getWrydz());
        ele.addElement("fddbr").addText(getFddbr());
        ele.addElement("fddbrdh").addText(getFddbrdh());
        ele.addElement("hbfzr").addText(getHbfzr());
        ele.addElement("hbfzrdh").addText(getHbfzrdh());
        ele.addElement("bmzw").addText(getBmzw());
        ele.addElement("jcsj").addText(sdf.format(getJcsj()));
        return ele;
    }

    public String getXczfbh() {
        return xczfbh;
    }

    public void setXczfbh(String xczfbh) {
        this.xczfbh = xczfbh;
    }

    public String getWrybh() {
        return wrybh;
    }

    public void setWrybh(String wrybh) {
        this.wrybh = wrybh;
    }

    public String getWrymc() {
        return wrymc;
    }

    public void setWrymc(String wrymc) {
        this.wrymc = wrymc;
    }

    public String getWrydz() {
        return wrydz;
    }

    public void setWrydz(String wrydz) {
        this.wrydz = wrydz;
    }

    public String getFddbr() {
        return fddbr;
    }

    public void setFddbr(String fddbr) {
        this.fddbr = fddbr;
    }

    public String getFddbrdh() {
        return fddbrdh;
    }

    public void setFddbrdh(String fddbrdh) {
        this.fddbrdh = fddbrdh;
    }

    public String getHbfzr() {
        return hbfzr;
    }

    public void setHbfzr(String hbfzr) {
        this.hbfzr = hbfzr;
    }

    public String getHbfzrdh() {
        return hbfzrdh;
    }

    public void setHbfzrdh(String hbfzrdh) {
        this.hbfzrdh = hbfzrdh;
    }

    public String getXzqy() {
        return xzqy;
    }

    public void setXzqy(String xzqy) {
        this.xzqy = xzqy;
    }

    public String getSshy() {
        return sshy;
    }

    public void setSshy(String sshy) {
        this.sshy = sshy;
    }

    public String getQylx() {
        return qylx;
    }

    public void setQylx(String qylx) {
        this.qylx = qylx;
    }

    public String getZywrwyz() {
        return zywrwyz;
    }

    public void setZywrwyz(String zywrwyz) {
        this.zywrwyz = zywrwyz;
    }

    public String getZflxxl() {
        return zflxxl;
    }

    public void setZflxxl(String zflxxl) {
        this.zflxxl = zflxxl;
    }

    public Date getKssj() {
        return kssj;
    }

    public void setKssj(Date kssj) {
        this.kssj = kssj;
    }

    public Date getJssj() {
        return jssj;
    }

    public void setJssj(Date jssj) {
        this.jssj = jssj;
    }

    public String getJcrbmbh() {
        return jcrbmbh;
    }

    public void setJcrbmbh(String jcrbmbh) {
        this.jcrbmbh = jcrbmbh;
    }

    public String getJcrbm() {
        return jcrbm;
    }

    public void setJcrbm(String jcrbm) {
        this.jcrbm = jcrbm;
    }

    public String getJcrbh() {
        return jcrbh;
    }

    public void setJcrbh(String jcrbh) {
        this.jcrbh = jcrbh;
    }

    public String getJcr() {
        return jcr;
    }

    public void setJcr(String jcr) {
        this.jcr = jcr;
    }

    public String getZfzh() {
        return zfzh;
    }

    public void setZfzh(String zfzh) {
        this.zfzh = zfzh;
    }

    public String getJlrbh() {
        return jlrbh;
    }

    public void setJlrbh(String jlrbh) {
        this.jlrbh = jlrbh;
    }

    public String getJlr() {
        return jlr;
    }

    public void setJlr(String jlr) {
        this.jlr = jlr;
    }

    public Integer getJcrys() {
        return jcrys;
    }

    public void setJcrys(Integer jcrys) {
        this.jcrys = jcrys;
    }

    public String getRwlb() {
        return rwlb;
    }

    public void setRwlb(String rwlb) {
        this.rwlb = rwlb;
    }

    public String getQyqk() {
        return qyqk;
    }

    public void setQyqk(String qyqk) {
        this.qyqk = qyqk;
    }

    public String getJcxj() {
        return jcxj;
    }

    public void setJcxj(String jcxj) {
        this.jcxj = jcxj;
    }

    public String getSfhg() {
        return sfhg;
    }

    public void setSfhg(String sfhg) {
        this.sfhg = sfhg;
    }

    public String getSshbbm() {
        return sshbbm;
    }

    public void setSshbbm(String sshbbm) {
        this.sshbbm = sshbbm;
    }

    public Integer getJdd() {
        return jdd;
    }

    public void setJdd(Integer jdd) {
        this.jdd = jdd;
    }

    public Integer getJdf() {
        return jdf;
    }

    public void setJdf(Integer jdf) {
        this.jdf = jdf;
    }

    public Integer getWdd() {
        return wdd;
    }

    public void setWdd(Integer wdd) {
        this.wdd = wdd;
    }

    public Integer getWdm() {
        return wdm;
    }

    public void setWdm(Integer wdm) {
        this.wdm = wdm;
    }

    public Integer getJdm() {
        return jdm;
    }

    public void setJdm(Integer jdm) {
        this.jdm = jdm;
    }

    public Integer getWdf() {
        return wdf;
    }

    public void setWdf(Integer wdf) {
        this.wdf = wdf;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public TSysUser getBlrUser() {
        return blrUser;
    }

    public void setBlrUser(TSysUser blrUser) {
        this.blrUser = blrUser;
    }

    public String getBmzw() {
        return bmzw;
    }

    public void setBmzw(String bmzw) {
        this.bmzw = bmzw;
    }

    public Date getJcsj() {
        return jcsj;
    }

    public void setJcsj(Date jcsj) {
        this.jcsj = jcsj;
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