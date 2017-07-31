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
 * ��˽ڵ���Ա���˴���ԱΪ��������ɷ���������Ͻ�����ˣ�
 * @author zn
 * @version $Id: SjshStaff.java, v 0.1 2013-1-25 ����07:28:27 zn Exp $
 */
@Component
public class ShStaff implements WorkProcesssStaff {

    @Autowired
    private WorkManagerImpl workManager;

    @Override
    public List<String> getUsers(ProcessArgsBean bean) throws Exception {
        List<String> list = new ArrayList<String>();
        if (bean.getDirection() != null
            && bean.getDirection().equals(WorkTransferDirectionEnum.SH_THSH)) {//�˻����
            list.add(workManager.getBackUser(bean.getApplyId()).getUsername());
            //�˻غ���Ա��������
            workManager.saveWorkAlreadyBack(bean.getApplyId());
        } else {
            list.add(workManager.getAuditUser(bean.getApplyId()).getUsername());
        }
        return list;
    }
}