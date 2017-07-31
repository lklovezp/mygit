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
 * �������
 * 
 * @author zn
 * @version $Id: AcceptAssignmentHandler.java, v 0.1 2013-1-21 ����09:56:39
 *          Administrator Exp $
 */
@Component(value = "acceptAssignmentHandler")
public class AcceptAssignmentHandler implements AssignmentHandler {
    private Logger            log              = Logger.getLogger(this.getClass());
    /**  */
    private static final long serialVersionUID = 1L;
    /** ��ǰ���̱�ʶ */
    private String            workProcess;

    @Override
    public void assign(Assignable assignable, OpenExecution ex) throws Exception {
        //���ݴ˽ڵ�����̱���õ���Ա����ʵ�ַ���
        WorkProcesssStaffFactory workProcesssStaffFactory = EnvironmentImpl
            .getFromCurrent(WorkProcesssStaffFactory.class);
        WorkProcesssStaff wps = workProcesssStaffFactory.getInstance(workProcess);
        //������Ա
        ProcessArgsBean argsBean = (ProcessArgsBean) ex.getVariable("argsBean");
        List<String> list = wps.getUsers(argsBean);
        if (list.size() > 0) {//���ҳ��²�����ִ����
            //����ĳ����Ա�����������񣨸������������񣩣��ڴ����б������������⡣�޸�Ϊ������������Ϊ������
            for (int i = 0; i < list.size(); i++) {
                if (StringUtils.isNotBlank(list.get(i))) {
                    assignable.addCandidateUser(list.get(i));
                }
            }
        } else {//���²�ִ����
            log.error("��ִ����");
        }
    }

}