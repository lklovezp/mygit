package com.hnjz.app.work.staff;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hnjz.app.work.manager.WorkManagerImpl;
import com.hnjz.app.work.wf.handler.target.WorkProcesssStaff;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;
import com.hnjz.wf.bean.ProcessArgsBean;

/**
 * 执行任务节点人员
 * @author zn
 * @version $Id: JsStaff.java, v 0.1 2013-1-25 上午07:24:41 zn Exp $
 */
@Component
public class ZrwZxStaff implements WorkProcesssStaff {
    @Autowired
    private WorkManagerImpl workManager;
    @Autowired
    private UserManager     userManager;

    @Override
    public List<String> getUsers(ProcessArgsBean bean) throws Exception {
    	List<String> list = new ArrayList<String>();
        List<TSysUser> userList = bean.getNextOpers();
        for (int i = 0; i < userList.size(); i++) {
            list.add(userList.get(i).getUsername());
        }
        return list;
    }

}
