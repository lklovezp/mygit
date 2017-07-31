package com.hnjz.app.work.wf.handler.target;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import com.hnjz.app.work.enums.WorkTransferEnum;
import com.hnjz.app.work.transfer.PfTransfer;
import com.hnjz.app.work.transfer.RwpflxTransfer;
import com.hnjz.app.work.transfer.ShTransfer;

@Component
public class WorkProcessTransferFactory extends ApplicationObjectSupport {

    public WorkProcessTransfer getInstance(String workTransfer) {
        WorkProcessTransfer workProcessTransfer = null;
        WorkTransferEnum wte = WorkTransferEnum.getByCode(workTransfer);
        switch (wte) {
            case PFZZ:
                workProcessTransfer = getApplicationContext().getBean(PfTransfer.class);
                break;

            case SHZZ:
                workProcessTransfer = getApplicationContext().getBean(ShTransfer.class);
                break;

            case RWPFLXZZ:
                workProcessTransfer = getApplicationContext().getBean(RwpflxTransfer.class);
                break;

            default:
                break;
        }
        return workProcessTransfer;
    }
}
