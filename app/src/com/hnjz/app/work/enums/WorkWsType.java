package com.hnjz.app.work.enums;

/**
 * ��������
 * @author zn
 * @version $Id: WorkWsType.java, v 0.1 2013-3-25 ����01:07:15 zn Exp $
 */
public enum WorkWsType {
    /** ѯ�ʱ�¼ */
    XWBL,
    /** �����¼ */
    KCBL;

    public static WorkWsType getByCode(String code) {
        WorkWsType e = null;
        for (WorkWsType type : values()) {
            if (code.equals(type.toString())) {
                e = type;
                break;
            }
        }
        return e;
    }
}