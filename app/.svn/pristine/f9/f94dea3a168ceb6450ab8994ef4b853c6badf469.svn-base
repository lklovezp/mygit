package com.hnjz.app.work.po;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 污染源任务视图
 * @author zn
 * @version $Id: VTjWryWork.java, v 0.1 2013-4-10 上午09:35:19 zn Exp $
 */
public class VTjWryWork implements java.io.Serializable {

    /**  */
    private static final long serialVersionUID = 1L;
    // Fields    

    private String            workid;
    private String            wrymc;
    private String            hydm;
    private String            hymc;
    private String            ssds;
    private String            hblxr;
    private String            worktype;
    private Date              starttime;
    private Date              endtime;
    private String            zxr;
    private String            ptrs;
    private String            jlr;
    private Date              archivestime;
    private Date              createtime;
    private String            wflxid;
    private String            wflxname;
    private String            orgid;
    private String            orgname;
    private String            forgname;
    private String            areaId;

    public Map<String, Object> toCommMap() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("workid", getWorkid());
        map.put("wrymc", getWrymc());
        map.put("ssds", getSsds());
        map.put("starttime", getStarttime() == null ? "" : sdf.format(getStarttime()));
        map.put("endtime", getEndtime() == null ? "" : sdf.format(getEndtime()));
        map.put("zxr", getZxr());
        map.put("ptrs", getPtrs());
        map.put("jlr", getJlr());
        map.put("archivestime", getArchivestime() == null ? "" : sdf.format(getArchivestime()));
        return map;
    }

    public String getWorkid() {
        return this.workid;
    }

    public void setWorkid(String workid) {
        this.workid = workid;
    }

    public String getWrymc() {
        return this.wrymc;
    }

    public void setWrymc(String wrymc) {
        this.wrymc = wrymc;
    }

    public String getHydm() {
        return this.hydm;
    }

    public void setHydm(String hydm) {
        this.hydm = hydm;
    }

    public String getHymc() {
        return this.hymc;
    }

    public void setHymc(String hymc) {
        this.hymc = hymc;
    }

    public String getSsds() {
        return this.ssds;
    }

    public void setSsds(String ssds) {
        this.ssds = ssds;
    }

    public String getHblxr() {
        return this.hblxr;
    }

    public void setHblxr(String hblxr) {
        this.hblxr = hblxr;
    }

    public String getWorktype() {
        return this.worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    public Date getStarttime() {
        return this.starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return this.endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getZxr() {
        return this.zxr;
    }

    public void setZxr(String zxr) {
        this.zxr = zxr;
    }

    public String getJlr() {
        return this.jlr;
    }

    public void setJlr(String jlr) {
        this.jlr = jlr;
    }

    public Date getArchivestime() {
        return archivestime;
    }

    public void setArchivestime(Date archivestime) {
        this.archivestime = archivestime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getWflxid() {
        return wflxid;
    }

    public void setWflxid(String wflxid) {
        this.wflxid = wflxid;
    }

    public String getWflxname() {
        return wflxname;
    }

    public void setWflxname(String wflxname) {
        this.wflxname = wflxname;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getForgname() {
        return forgname;
    }

    public void setForgname(String forgname) {
        this.forgname = forgname;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getPtrs() {
        return ptrs;
    }

    public void setPtrs(String ptrs) {
        this.ptrs = ptrs;
    }

}