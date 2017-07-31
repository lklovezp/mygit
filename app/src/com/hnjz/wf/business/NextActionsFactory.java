package com.hnjz.wf.business;

import com.hnjz.app.work.wf.WorkNextActions;
import com.hnjz.wf.enums.ProcessEnum;

/**
 * �²�����������<br/>
 * ������ʱ�����̣��õ����²�������
 * @author zn
 * @version $Id: NextActionsFactory.java, v 0.1 2013-1-28 ����09:09:00 zn Exp $
 */
public class NextActionsFactory {
    private NextActionsFactory() {
    }

    /**
     * ���ݴ��������KEYֵ���õ���Ӧ�Ĳ�����
     * @param processKey
     * @return
     */
    public static INextActions getInstance(String processKey) {
        ProcessEnum pe = ProcessEnum.getByKey(processKey);
        INextActions nextActions = null;
        switch (pe) {
            // ����
            case GENERAL_TASK:
            	nextActions = new WorkNextActions();
                break;
            default:
                break;
        }
        return nextActions;
    }
}