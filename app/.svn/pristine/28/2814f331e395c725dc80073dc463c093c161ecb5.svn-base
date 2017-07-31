package com.hnjz.app.work.enums;

/**
 * 文书类型
 * @author zn
 * @version $Id: WorkWsType.java, v 0.1 2013-3-25 上午01:07:15 zn Exp $
 */
public enum WorkWsType {
    /** 询问笔录 */
    XWBL,
    /** 勘查笔录 */
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
