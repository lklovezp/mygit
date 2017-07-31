package com.hnjz.app.work.enums;

/**
 * ��ת����
 * @author zn
 * @version $Id: WorkTransferDirection.java, v 0.1 2013-1-25 ����08:01:02 zn Exp $
 */
public enum WorkTransferDirectionEnum {
    /** �ɷ� */
    //ת��
    PF_ZP("pf_zp", "ת��", "zpPage.htm?1=1", WorkActionTypeEnum.NEW_WIN_D),
    //����
    PF_JS("pf_js", "����", "jsPage.htm?1=1", WorkActionTypeEnum.NEW_WIN_D),
    //���ɵ���
    PF_XPDS("pf_xpds", "����", "xpPage.htm?1=1", WorkActionTypeEnum.NEW_WIN_D),

    /** ��� */
    //�ϼ����
    SH_SJ("sh_sj", "�ϼ����", "", null),
    //ͨ��
    SH_TG("sh_sj", "���ͨ��", "", null),

    /** �˻� */
    //�˻���ˣ�ͬ�ϼ��������
    SH_THSH("sh_thsh", "�ϼ����", "", null),
    //�˻�ִ��
    SH_THZX("sh_thsh", "�˻�ִ��", "", null),

    /** �ϱ� */
    //����������
    REPORT_FXPRW("report_fxprw", "����������", "", null),
    //��������
    REPORT_XPRW("report_xprw", "��������", "", null),
    
    /** ר�������� */
    PF_ZX("pf_zx", "ִ��", "", null);
    

    /** ���� */
    private String             code;
    /** ���֣�������ͼ��transition��Ӧ������ת��Ӧ������������ */
    private String             text;
    /** � */
    private String             action;
    /** ����� */
    private WorkActionTypeEnum workActionTypeEnum;

    private WorkTransferDirectionEnum(String code, String text, String action,
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