package com.hnjz.app.work.staff;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hnjz.app.work.enums.WorkTransferDirectionEnum;
import com.hnjz.app.work.manager.WorkManagerImpl;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.wf.handler.target.WorkProcesssStaff;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.timertask.AutoPf;
import com.hnjz.sys.user.UserManager;
import com.hnjz.wf.bean.ProcessArgsBean;

/**
 * ִ������ڵ���Ա
 * @author zn
 * @version $Id: JsStaff.java, v 0.1 2013-1-25 ����07:24:41 zn Exp $
 */
@Component
public class ZxStaff implements WorkProcesssStaff {
    @Autowired
    private WorkManagerImpl workManager;
    @Autowired
    private UserManager     userManager;

    @Override
    public List<String> getUsers(ProcessArgsBean bean) throws Exception {
        List<String> list = new ArrayList<String>();
        if (bean.getDirection().equals(WorkTransferDirectionEnum.SH_THZX)) {
            Work work = workManager.get(bean.getApplyId());
            list.add(((TSysUser) userManager.get(TSysUser.class, work.getZxrId())).getUsername());
            if (StringUtils.isNotBlank(work.getPtrIds())) {
                String[] ptrIds = work.getPtrIds().split(",");
                for (int i = 0; i < ptrIds.length; i++) {
                    list.add(((TSysUser) userManager.get(TSysUser.class, ptrIds[i])).getUsername());
                }
            }
        } else {
            //����һ���û�ʱ��������ָ�ָ����Ա���˴�����һ���հױ�ʾ����������һ����Ա
            list.add("");
            if (CtxUtil.getCurUser() == null){
            	list.add(AutoPf.jsr.getUsername());
            } else {
            	list.add(CtxUtil.getCurUser().getUsername());
            }
        }
        return list;
    }

}