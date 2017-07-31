package com.hnjz.app.work.wf.handler;

import org.apache.log4j.Logger;
import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.pvm.internal.env.EnvironmentImpl;

import com.hnjz.app.work.wf.handler.target.WorkProcessTransfer;
import com.hnjz.app.work.wf.handler.target.WorkProcessTransferFactory;
import com.hnjz.wf.bean.ProcessArgsBean;

/**
 * ��ת����
 * 
 * @author zn
 * @version $Id: TransferHandler.java, v 0.1 2013-1-22 ����12:47:55 Administrator
 *          Exp $
 */
public class TransferHandler implements DecisionHandler {
    private Logger            log              = Logger.getLogger(this.getClass());
    /**  */
    private static final long serialVersionUID = 1L;

    /** ��ǰ������ת��ʶ */
    private String            workTransfer;

    @Override
    public String decide(OpenExecution ex) {
        WorkProcessTransferFactory workProcessTransferFactory = EnvironmentImpl
            .getFromCurrent(WorkProcessTransferFactory.class);
        WorkProcessTransfer wpt = workProcessTransferFactory.getInstance(workTransfer);
        String result = "";
        try {
            ProcessArgsBean argsBean = (ProcessArgsBean) ex.getVariable("argsBean");
            result = wpt.getTransfer(argsBean);
        } catch (Exception e) {
            log.error("", e);
        }
        return result;
    }

}