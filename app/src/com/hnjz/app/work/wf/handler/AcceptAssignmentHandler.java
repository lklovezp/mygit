package com.hnjz.app.work.wf.handler;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.springframework.stereotype.Component;

import com.hnjz.app.work.wf.handler.target.WorkProcesssStaff;
import com.hnjz.app.work.wf.handler.target.WorkProcesssStaffFactory;
import com.hnjz.wf.bean.ProcessArgsBean;

/**
 * 任务分配
 * 
 * @author zn
 * @version $Id: AcceptAssignmentHandler.java, v 0.1 2013-1-21 上午09:56:39
 *          Administrator Exp $
 */
@Component(value = "acceptAssignmentHandler")
public class AcceptAssignmentHandler implements AssignmentHandler {
    private Logger            log              = Logger.getLogger(this.getClass());
    /**  */
    private static final long serialVersionUID = 1L;
    /** 当前流程标识 */
    private String            workProcess;

    @Override
    public void assign(Assignable assignable, OpenExecution ex) throws Exception {
        //根据此节点的流程编码得到人员分配实现方法
        WorkProcesssStaffFactory workProcesssStaffFactory = EnvironmentImpl
            .getFromCurrent(WorkProcesssStaffFactory.class);
        WorkProcesssStaff wps = workProcesssStaffFactory.getInstance(workProcess);
        //设置人员
        ProcessArgsBean argsBean = (ProcessArgsBean) ex.getVariable("argsBean");
        List<String> list = wps.getUsers(argsBean);
        if (list.size() > 0) {//查找出下步任务执行人
            //对于某个人员存在两种任务（个人任务、组任务），在待办列表中排序有问题。修改为所有任务都设置为组任务
            for (int i = 0; i < list.size(); i++) {
                if (StringUtils.isNotBlank(list.get(i))) {
                    assignable.addCandidateUser(list.get(i));
                }
            }
        } else {//无下步执行人
            log.error("无执行人");
        }
    }

}
