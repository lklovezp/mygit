package com.hnjz.wf.business;

import java.util.List;

import com.hnjz.wf.bean.NextActionBean;

/**
 * �²�����
 * @author zn
 * @version $Id: INextActions.java, v 0.1 2013-1-28 ����09:09:53 zn Exp $
 */
public interface INextActions {
    /**
     * ���²������ַ�������ΪNextActionBean����
     * @param nextActions
     * @return
     * @throws Exception
     */
    List<NextActionBean> getActions(String nextActions) throws Exception;
}