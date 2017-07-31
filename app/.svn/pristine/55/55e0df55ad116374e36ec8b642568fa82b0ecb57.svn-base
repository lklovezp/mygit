package com.hnjz.app.work.transfer;

import org.springframework.stereotype.Component;

import com.hnjz.app.work.wf.handler.target.WorkProcessTransfer;
import com.hnjz.wf.bean.ProcessArgsBean;

/**
 * 处理审核中转走向
 * @author zn
 * @version $Id: ShTransfer.java, v 0.1 2013-1-25 上午07:24:28 zn Exp $
 */
@Component
public class ShTransfer implements WorkProcessTransfer {

    @Override
    public String getTransfer(ProcessArgsBean bean) throws Exception {
        return bean.getDirection().getText();
    }
}
