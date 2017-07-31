package com.hnjz.app.work.enums;

/**
 * ��������<br/>
 * ������������̲���
 * @author zn
 * @version $Id: WorkProcessEnum.java, v 0.1 2013-1-25 ����03:13:11 zn Exp $
 */
public enum WorkProcessEnum {
    /** �ɷ� */
    PF("pf", "�ɷ�", "", null),
    /** ִ�У������� */
    ZX("zx", "����", "zxPage.htm?1=1", WorkActionTypeEnum.NEW_WIN_D),
    /** �ϱ��ƽ�(���ܺ��˻ص�ִ�к�Ĳ���) */
    SBYJ("sbyj", "�ϱ��ƽ�", "work/zx/zx.json?1=1", WorkActionTypeEnum.AJAX),
    /** ��� */
    SH("sh", "���", "shPage?1=1", WorkActionTypeEnum.NEW_WIN_D),
    /** �ϱ�����-�鵵 */
    REPORT_GD("report_gd", "�鵵", "work/report/shPage.htm?1=1", WorkActionTypeEnum.NEW_WIN_B),
    /** �ϱ�����-��� */
    REPORT_SH("report_sh", "���", "report_shPage.htm?1=1", WorkActionTypeEnum.NEW_WIN_D),
    
    /** ר��������-���� */
    ZXZRW_ZX("zxzrw_zx", "����", "zxzrw_zxPage.htm?1=1", WorkActionTypeEnum.NEW_WIN_D);

    /** ���루������ͼ�ж�Ӧ�� */
    private String             code;
    /** ���� */
    private String             text;
    /** � */
    private String             action;
    /** ����� */
    private WorkActionTypeEnum workActionTypeEnum;

    private WorkProcessEnum(String code, String text, String action,
                            WorkActionTypeEnum workActionTypeEnum) {
        this.code = code;
        this.text = text;
        this.action = action;
        this.workActionTypeEnum = workActionTypeEnum;
    }

    /**
     * ���ݱ����ѯ
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