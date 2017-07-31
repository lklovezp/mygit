package com.hnjz.wf.business;

import com.hnjz.app.work.wf.WorkNextActions;
import com.hnjz.wf.enums.ProcessEnum;

/**
 * 下步操作管理类<br/>
 * 根据碰时的流程，得到“下步操作”
 * @author zn
 * @version $Id: NextActionsFactory.java, v 0.1 2013-1-28 上午09:09:00 zn Exp $
 */
public class NextActionsFactory {
    private NextActionsFactory() {
    }

    /**
     * 根据传入的流程KEY值，得到对应的操作类
     * @param processKey
     * @return
     */
    public static INextActions getInstance(String processKey) {
        ProcessEnum pe = ProcessEnum.getByKey(processKey);
        INextActions nextActions = null;
        switch (pe) {
            // 任务
            case GENERAL_TASK:
            	nextActions = new WorkNextActions();
                break;
            default:
                break;
        }
        return nextActions;
    }
}
