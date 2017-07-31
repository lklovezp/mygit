package com.hnjz.app.work.wf.handler.target;

import java.util.List;

import com.hnjz.wf.bean.ProcessArgsBean;

/**
 * 任务流程中执行人员获取
 * @author zn
 * @version $Id: WorkProcesssStaff.java, v 0.1 2013-1-25 上午03:56:17 zn Exp $
 */
public interface WorkProcesssStaff {
    List<String> getUsers(ProcessArgsBean bean) throws Exception;
}
