package com.hnjz.app.work.wf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hnjz.app.work.enums.WorkProcessEnum;
import com.hnjz.app.work.enums.WorkTransferDirectionEnum;
import com.hnjz.wf.bean.NextActionBean;
import com.hnjz.wf.business.INextActions;

/**
 * 任务下步操作
 * @author zn
 * @version $Id: WorkNextActions.java, v 0.1 2013-1-28 上午09:14:56 zn Exp $
 */
public class WorkNextActions implements INextActions {

    @Override
    public List<NextActionBean> getActions(String nextActions) throws Exception {
        List<NextActionBean> beanList = new ArrayList<NextActionBean>();
        if (StringUtils.isNotBlank(nextActions)) {
            String[] actionArr = nextActions.split(",");
            for (int i = 0; i < actionArr.length; i++) {
                NextActionBean bean = new NextActionBean();
                if (WorkProcessEnum.getByCode(actionArr[i]) != null) {// 流程任务节点
                    WorkProcessEnum wpe = WorkProcessEnum.getByCode(actionArr[i]);
                    bean.setCode(wpe.getCode());
                    bean.setText(wpe.getText());
                    bean.setAction(wpe.getAction());
                    bean.setActionType(wpe.getWorkActionTypeEnum().toString());
                } else if (WorkTransferDirectionEnum.getByCode(actionArr[i]) != null) {// 流程中转节点
                    WorkTransferDirectionEnum wtde = WorkTransferDirectionEnum
                        .getByCode(actionArr[i]);
                    bean.setCode(wtde.getCode());
                    bean.setText(wtde.getText());
                    bean.setAction(wtde.getAction());
                    bean.setActionType(wtde.getWorkActionTypeEnum().toString());
                }
                beanList.add(bean);
            }
        }
        return beanList;
    }

}
