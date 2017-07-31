package com.hnjz.app.work.enums;

/**
 * 任务流程中转
 * @author zn
 * @version $Id: WorkTransferEnum.java, v 0.1 2013-1-25 上午06:33:07 zn Exp $
 */
public enum WorkTransferEnum {
    /** 派发中转 */
    PFZZ("pfzz", "派发中转"),
    /** 审核中转 */
    SHZZ("shzz", "审核中转"),
    /** 任务派发类型中转 */
    RWPFLXZZ("rwpflxzz", "任务派发类型中转");

    /** 编码（与流程图中对应） */
    private String code;
    /** 文字 */
    private String text;

    private WorkTransferEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    /**
     * 根据编码查询
     * @param code
     * @return
     */
    public static WorkTransferEnum getByCode(String code) {
        WorkTransferEnum workTransferEnum = null;
        for (WorkTransferEnum wte : values()) {
            if (wte.getCode().equals(code)) {
                workTransferEnum = wte;
                break;
            }
        }
        return workTransferEnum;
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
