package com.hnjz.app.work.wf.handler.target;

import java.util.List;

import com.hnjz.wf.bean.ProcessArgsBean;

/**
 * ����������ִ����Ա��ȡ
 * @author zn
 * @version $Id: WorkProcesssStaff.java, v 0.1 2013-1-25 ����03:56:17 zn Exp $
 */
public interface WorkProcesssStaff {
    List<String> getUsers(ProcessArgsBean bean) throws Exception;
}