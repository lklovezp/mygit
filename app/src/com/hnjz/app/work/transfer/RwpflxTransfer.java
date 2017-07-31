package com.hnjz.app.work.transfer;

import org.springframework.stereotype.Component;

import com.hnjz.app.work.wf.handler.target.WorkProcessTransfer;
import com.hnjz.wf.bean.ProcessArgsBean;

/**
 * ���������ת����
 * @author zn
 * @version $Id: ShTransfer.java, v 0.1 2013-1-25 ����07:24:28 zn Exp $
 */
@Component
public class RwpflxTransfer implements WorkProcessTransfer {

    @Override
    public String getTransfer(ProcessArgsBean bean) throws Exception {
        return bean.getDirection().getText();
    }
}