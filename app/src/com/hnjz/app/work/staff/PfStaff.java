package com.hnjz.app.work.staff;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hnjz.app.work.wf.handler.target.WorkProcesssStaff;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.wf.bean.ProcessArgsBean;

/**
 * �ɷ��ڵ���Ա��ת��-�˴���ԱΪ��һ�ڵ������ѡ�����Ա��
 * @author zn
 * @version $Id: PfStaff.java, v 0.1 2013-1-25 ����07:27:47 zn Exp $
 */
@Component
public class PfStaff implements WorkProcesssStaff {

    @Override
    public List<String> getUsers(ProcessArgsBean bean) throws Exception {
        List<String> list = new ArrayList<String>();
        List<TSysUser> userList = bean.getNextOpers();
        for (int i = 0; i < userList.size(); i++) {
            list.add(userList.get(i).getUsername());
        }
        return list;
    }
}