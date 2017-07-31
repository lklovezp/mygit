package com.hnjz.wf;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hnjz.app.work.manager.WorkManagerImpl;
import com.hnjz.common.manager.Manager;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;

/**
 * 工作流操作抽象类提供公用方法
 * 
 * @author zn
 * @version $Id: AbsJbpmController.java, v 0.1 2013-1-9 上午03:40:37 Administrator
 *          Exp $
 */
public abstract class AbsJbpmController {
    protected Logger          log = Logger.getLogger(this.getClass());
    
    @Autowired
    protected Manager manager;
    
    @Autowired
    protected WorkManagerImpl workManager;
    
    @Autowired
    protected UserManager userManager;

    protected TSysUser findLgUser() {
        return CtxUtil.getCurUser();

    }

}
