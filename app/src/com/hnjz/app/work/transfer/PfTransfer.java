package com.hnjz.app.work.transfer;

import org.springframework.stereotype.Component;

import com.hnjz.app.work.wf.handler.target.WorkProcessTransfer;
import com.hnjz.wf.bean.ProcessArgsBean;

/**
 * 处理派发中转走向
 * @author zn
 * @version $Id: PfTransfer.java, v 0.1 2013-1-25 上午07:20:58 zn Exp $
 */
@Component
public class PfTransfer implements WorkProcessTransfer {

    @Override
    public String getTransfer(ProcessArgsBean bean) throws Exception {
        return bean.getDirection().getText();
    }
}
