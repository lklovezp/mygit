package com.hnjz.app.work.enums;

/**
 * 中转方向
 * @author zn
 * @version $Id: WorkTransferDirection.java, v 0.1 2013-1-25 上午08:01:02 zn Exp $
 */
public enum WorkTransferDirectionEnum {
    /** 派发 */
    //转派
    PF_ZP("pf_zp", "转派", "zpPage.htm?1=1", WorkActionTypeEnum.NEW_WIN_D),
    //接受
    PF_JS("pf_js", "接受", "jsPage.htm?1=1", WorkActionTypeEnum.NEW_WIN_D),
    //下派地市
    PF_XPDS("pf_xpds", "下派", "xpPage.htm?1=1", WorkActionTypeEnum.NEW_WIN_D),

    /** 审核 */
    //上级审核
    SH_SJ("sh_sj", "上级审核", "", null),
    //通过
    SH_TG("sh_sj", "审核通过", "", null),

    /** 退回 */
    //退回审核（同上级审核走向）
    SH_THSH("sh_thsh", "上级审核", "", null),
    //退回执行
    SH_THZX("sh_thsh", "退回执行", "", null),

    /** 上报 */
    //非下派任务
    REPORT_FXPRW("report_fxprw", "非下派任务", "", null),
    //下派任务
    REPORT_XPRW("report_xprw", "下派任务", "", null),
    
    /** 专项子任务 */
    PF_ZX("pf_zx", "执行", "", null);
    

    /** 编码 */
    private String             code;
    /** 文字（与流程图中transition对应，在中转中应用于流程走向） */
    private String             text;
    /** 活动 */
    private String             action;
    /** 活动类型 */
    private WorkActionTypeEnum workActionTypeEnum;

    private WorkTransferDirectionEnum(String code, String text, String action,
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
    public static WorkTransferDirectionEnum getByCode(String code) {
        WorkTransferDirectionEnum workTransferDirectionEnum = null;
        for (WorkTransferDirectionEnum wtde : values()) {
            if (wtde.getCode().equals(code)) {
                workTransferDirectionEnum = wtde;
                break;
            }
        }
        return workTransferDirectionEnum;
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
