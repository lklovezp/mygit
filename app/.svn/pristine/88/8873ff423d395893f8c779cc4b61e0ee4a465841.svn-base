package com.hnjz.app.work.staff;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hnjz.app.work.enums.WorkTransferDirectionEnum;
import com.hnjz.app.work.manager.WorkManagerImpl;
import com.hnjz.app.work.wf.handler.target.WorkProcesssStaff;
import com.hnjz.wf.bean.ProcessArgsBean;

/**
 * 审核节点人员（此处人员为任务逐层派发后逐层向上进行审核）
 * @author zn
 * @version $Id: SjshStaff.java, v 0.1 2013-1-25 上午07:28:27 zn Exp $
 */
@Component
public class ShStaff implements WorkProcesssStaff {

    @Autowired
    private WorkManagerImpl workManager;

    @Override
    public List<String> getUsers(ProcessArgsBean bean) throws Exception {
        List<String> list = new ArrayList<String>();
        if (bean.getDirection() != null
            && bean.getDirection().equals(WorkTransferDirectionEnum.SH_THSH)) {//退回审核
            list.add(workManager.getBackUser(bean.getApplyId()).getUsername());
            //退回后将人员重新整理
            workManager.saveWorkAlreadyBack(bean.getApplyId());
        } else {
            list.add(workManager.getAuditUser(bean.getApplyId()).getUsername());
        }
        return list;
    }
}
