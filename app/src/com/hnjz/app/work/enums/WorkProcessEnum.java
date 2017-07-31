package com.hnjz.app.work.enums;

/**
 * 任务流程<br/>
 * 定义出整个流程步骤
 * @author zn
 * @version $Id: WorkProcessEnum.java, v 0.1 2013-1-25 上午03:13:11 zn Exp $
 */
public enum WorkProcessEnum {
    /** 派发 */
    PF("pf", "派发", "", null),
    /** 执行（办理） */
    ZX("zx", "办理", "zxPage.htm?1=1", WorkActionTypeEnum.NEW_WIN_D),
    /** 上报移交(接受后、退回到执行后的操作) */
    SBYJ("sbyj", "上报移交", "work/zx/zx.json?1=1", WorkActionTypeEnum.AJAX),
    /** 审核 */
    SH("sh", "审核", "shPage?1=1", WorkActionTypeEnum.NEW_WIN_D),
    /** 上报任务-归档 */
    REPORT_GD("report_gd", "归档", "work/report/shPage.htm?1=1", WorkActionTypeEnum.NEW_WIN_B),
    /** 上报任务-审核 */
    REPORT_SH("report_sh", "审核", "report_shPage.htm?1=1", WorkActionTypeEnum.NEW_WIN_D),
    
    /** 专项子任务-办理 */
    ZXZRW_ZX("zxzrw_zx", "办理", "zxzrw_zxPage.htm?1=1", WorkActionTypeEnum.NEW_WIN_D);

    /** 编码（与流程图中对应） */
    private String             code;
    /** 文字 */
    private String             text;
    /** 活动 */
    private String             action;
    /** 活动类型 */
    private WorkActionTypeEnum workActionTypeEnum;

    private WorkProcessEnum(String code, String text, String action,
                            WorkActionTypeEnum workActionTypeEnum) {
        this.code = code;
        this.text = text;
        this.action = action;
        this.workActionTypeEnum = workActionTypeEnum;
    }

    /**
     * 根据编码查询
     * @param code
     * @return
     */
    public static WorkProcessEnum getByCode(String code) {
        WorkProcessEnum workProcessEnum = null;
        for (WorkProcessEnum wpe : values()) {
            if (wpe.getCode().equals(code)) {
                workProcessEnum = wpe;
                break;
            }
        }
        return workProcessEnum;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public WorkActionTypeEnum getWorkActionTypeEnum() {
        return workActionTypeEnum;
    }

    public void setWorkActionTypeEnum(WorkActionTypeEnum workActionTypeEnum) {
        this.workActionTypeEnum = workActionTypeEnum;
    }
}
