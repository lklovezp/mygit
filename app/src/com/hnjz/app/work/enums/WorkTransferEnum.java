package com.hnjz.app.work.enums;

/**
 * ����������ת
 * @author zn
 * @version $Id: WorkTransferEnum.java, v 0.1 2013-1-25 ����06:33:07 zn Exp $
 */
public enum WorkTransferEnum {
    /** �ɷ���ת */
    PFZZ("pfzz", "�ɷ���ת"),
    /** �����ת */
    SHZZ("shzz", "�����ת"),
    /** �����ɷ�������ת */
    RWPFLXZZ("rwpflxzz", "�����ɷ�������ת");

    /** ���루������ͼ�ж�Ӧ�� */
    private String code;
    /** ���� */
    private String text;

    private WorkTransferEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    /**
     * ���ݱ����ѯ
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