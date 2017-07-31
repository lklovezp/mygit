package com.hnjz.app.work.staff;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hnjz.app.work.wf.handler.target.WorkProcesssStaff;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;
import com.hnjz.wf.bean.ProcessArgsBean;

/**
 * 上报归档任务节点人员
 * @author zn
 * @version $Id: ReportGdStaff.java, v 0.1 2013-4-2 上午02:31:34 zn Exp $
 */
@Component
public class ReportGdStaff implements WorkProcesssStaff {
    @Autowired
    private UserManager userManager;

    @Override
    public List<String> getUsers(ProcessArgsBean bean) throws Exception {
        List<String> list = new ArrayList<String>();
        List<TSysUser> userList = userManager.getSbUsers();
        for (int i = 0; i < userList.size(); i++) {
            list.add(userList.get(i).getUsername());
        }
        return list;
    }

}
