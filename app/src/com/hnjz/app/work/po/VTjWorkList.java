package com.hnjz.app.work.po;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.hnjz.app.work.enums.WorkState;

/**
 * 总任务列表视图
 * @author zn
 * @version $Id: VTjWorkList.java, v 0.1 2013-4-17 上午07:55:00 zn Exp $
 */
public class VTjWorkList implements java.io.Serializable {

    /**  */
    private static final long serialVersionUID = 1L;
    private String            ryId;
    private String            workId;
    private String            workName;
    private String            workTypeId;
    private String            workTypeName;
    private Date              startTime;
    private Date              endTime;
    private String            zxr;
    private String            state;
    private String            areaId;
    private String            wryName;
    private String            wrySsds;
    private String            orgId;
    private String            orgName;
    private String            FOrgName;
    private String            note;
    private Integer           xd;
    private Integer           wc;
    private Integer           yqwc;
    private Integer           zx;
    private Integer           yqwwc;

    public Map<String, Object> toMap() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("workId", workId);
        map.put("workName", workName);
        map.put("workTypeName", workTypeName);
        map.put("startTime", sdf.format(startTime));
        map.put("endTime", sdf.format(endTime));
        map.put("zxr", zxr);
        map.put("state", state == null ? "" : WorkState.getNote(state));
        map.put("wryName", wryName);
        map.put("wrySsds", wrySsds);
        map.put("note", note);
        return map;
    }

    /**
     * 统计类型
     * @author zn
     * @version $Id: VTjWorkList.java, v 0.1 2013-4-17 上午08:21:49 zn Exp $
     */
    public enum TjType {
        /**  */
        XD("xd", "下达任务"),
        /**  */
        WC("wc", "完成任务"),
        /**  */
        YQWC("yqwc", "逾期完成任务"),
        /**  */
        ZX("zx", "执行中任务"),
        /**  */
        YQWWC("yqwwc", "逾期未完成任务");

        /** 编码 */
        private String code;
        /** 文本 */
        private String text;

        private TjType(String code, String text) {
            this.code = code;
            this.text = text;
        }

        public static TjType getByCode(String code) {
            TjType t = null;
            for (TjType m : values()) {
                if (m.getCode().equals(code)) {
                    t = m;
                    break;
                }
            }
            return t;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public String getWorkId() {
        return this.workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getWorkName() {
        return this.workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWorkTypeId() {
        return this.workTypeId;
    }

    public void setWorkTypeId(String workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String getWorkTypeName() {
        return this.workTypeName;
    }

    public void setWorkTypeName(String workTypeName) {
        this.workTypeName = workTypeName;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getZxr() {
        return this.zxr;
    }

    public void setZxr(String zxr) {
        this.zxr = zxr;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWryName() {
        return this.wryName;
    }

    public void setWryName(String wryName) {
        this.wryName = wryName;
    }

    public String getWrySsds() {
        return this.wrySsds;
    }

    public void setWrySsds(String wrySsds) {
        this.wrySsds = wrySsds;
    }

    public String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getFOrgName() {
        return this.FOrgName;
    }

    public void setFOrgName(String FOrgName) {
        this.FOrgName = FOrgName;
    }

    public Integer getXd() {
        return xd;
    }

    public void setXd(Integer xd) {
        this.xd = xd;
    }

    public Integer getWc() {
        return wc;
    }

    public void setWc(Integer wc) {
        this.wc = wc;
    }

    public Integer getYqwc() {
        return yqwc;
    }

    public void setYqwc(Integer yqwc) {
        this.yqwc = yqwc;
    }

    public Integer getZx() {
        return zx;
    }

    public void setZx(Integer zx) {
        this.zx = zx;
    }

    public Integer getYqwwc() {
        return yqwwc;
    }

    public void setYqwwc(Integer yqwwc) {
        this.yqwwc = yqwwc;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getRyId() {
        return ryId;
    }

    public void setRyId(String ryId) {
        this.ryId = ryId;
    }

}