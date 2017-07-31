package com.hnjz.app.work.wf.handler.target;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import com.hnjz.app.work.enums.WorkProcessEnum;
import com.hnjz.app.work.staff.PfStaff;
import com.hnjz.app.work.staff.ReportGdStaff;
import com.hnjz.app.work.staff.ShStaff;
import com.hnjz.app.work.staff.ZrwZxStaff;
import com.hnjz.app.work.staff.ZxStaff;

@Component
public class WorkProcesssStaffFactory extends ApplicationObjectSupport {

    /**
     * 
     * @param workProcess   任务流程节点
     * @return
     */
    public WorkProcesssStaff getInstance(String workProcess) {
        WorkProcesssStaff workProcesssStaff = null;
        WorkProcessEnum wpe = WorkProcessEnum.getByCode(workProcess);
        switch (wpe) {
            case PF:
                workProcesssStaff = getApplicationContext().getBean(PfStaff.class);
                break;
            case ZX:
                workProcesssStaff = getApplicationContext().getBean(ZxStaff.class);
                break;
            case SH:
                workProcesssStaff = getApplicationContext().getBean(ShStaff.class);
                break;
            case REPORT_GD:
                workProcesssStaff = getApplicationContext().getBean(ReportGdStaff.class);
                break;
            case REPORT_SH:
                workProcesssStaff = getApplicationContext().getBean(ShStaff.class);
                break;
            case ZXZRW_ZX:
                workProcesssStaff = getApplicationContext().getBean(ZrwZxStaff.class);
                break;
            default:
                break;
        }
        return workProcesssStaff;
    }
}
