/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.po.WorkRy;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;

/**
 * 各部门任务统计用，记录转派人，记录人，接收人，执行人，陪同人
 * 
 * @author wumi
 * @version $Id: WorkTyManager.java, v 0.1 Apr 18, 2013 11:37:23 AM wumi Exp $
 */
@Service("workRyManager")
public class WorkRyManager extends ManagerImpl {

    @Autowired
    private UserManager userManager;
    @Autowired
    private OrgManager  orgManager;

    /**
     * 保存任务的执行人，陪同人，记录人
     * 
     * @param zx 执行人
     * @param jl 记录人
     * @param ptrIds 陪同人id集合
     */
//    public void saveZx(TSysUser zx, TSysUser jl, String ptrIds, String workId) {
//        String s = "本部门其他人员：";
//        StringBuilder str = new StringBuilder(s);
//        TSysOrg zxOrg = this.userManager.getOrgbyUser(zx.getId());
//        //记录人
//        if (null != jl) {
//        	TSysOrg jlOrg = this.userManager.getOrgbyUser(jl.getId());
//            if (StringUtils.equals(zxOrg.getId(), jlOrg.getId())) {
//                str.append("记录人：").append(jl.getName()).append(",");
//            } else {
//                this.saveRy(jl, WorkLogType.JS, jl.getName().concat("作为记录人"), workId);
//            }
//        }
//        List<String> names = new ArrayList<String>();
//        //陪同人
//        if (StringUtils.isNotBlank(ptrIds)) {
//        	TSysOrg p = null;
//            String[] objs = ptrIds.split(",");
//            for (String ele : objs) {
//                TSysUser u = this.userManager.getUser(ele);
//                p = this.userManager.getOrgbyUser(u.getId());
//                if (StringUtils.equals(zxOrg.getId(), p.getId())) {
//                    names.add(u.getName());
//                } else {
//                    this.saveRy(u, WorkLogType.PT, u.getName().concat("作为陪同人"), workId);
//                }
//            }
//        }
//        String note = zx.getName().concat("作为执行人");
//        if (str.length() != s.length() || !names.isEmpty()) {
//            if (!names.isEmpty()) {
//                str.append("陪同人：");
//            }
//            for (String ele : names) {
//                str.append(ele).append(",");
//            }
//            note = note.concat(str.toString());
//        }
//        this.saveRy(zx, WorkLogType.ZX, note, workId);
//    }

    /**
     * 保存人员信息
     * 
     * @param user 用户信息
     * @param type 人员类型
     */
    public void saveRy(TSysUser user, WorkLogType type, String workId) {
        this.saveRy(user, type, null, workId);

    }

    /**
     * 保存人员信息<br>
     * 记录转派人，记录人，接收人，执行人，陪同人<br>
     * 转派人只记录最后一条<br>
     * 记录接受人时，会删除转派人的记录<br>
     * 记录执行人时，会删除接收人的记录，<br>
     * 如果记录人或者陪同人和执行人有和执行人部门相同,则只记录执行人的记录，会在执行人的备注中加入记录人，陪同人的姓名
     * 
     * @param user 用户信息
     * @param type 人员类型
     * @param type 备注
     * @param workId 任务ID
     */
    public void saveRy(TSysUser user, WorkLogType type, String note, String workId) {
        //转派人只记录最后一条
        String hsql = "from WorkRy where type = ? and workId = ?";
        if (type == WorkLogType.ZF) {
            this.dao.removeFindObjs(hsql, type.getCode(), workId);
            //记录接受人时，会删除转派人的记录
        } else if (type == WorkLogType.JS) {
            this.dao.removeFindObjs(hsql, WorkLogType.ZF.getCode(), workId);
            //记录执行人时，会删除接收人,执行人（审核退回时重复）的记录
        } else if (type == WorkLogType.ZX) {
            this.dao.removeFindObjs(hsql, WorkLogType.JS.getCode(), workId);
            this.dao.removeFindObjs(hsql, WorkLogType.ZX.getCode(), workId);
        }
        WorkRy ry = new WorkRy();
        ry.setWorkId(workId);
        ry.setUserid(user.getId());
        ry.setUsername(user.getName());
        ry.setType(type.getCode());
        TSysOrg o = this.userManager.getOrgbyUser(user.getId());
        ry.setOrgid(o.getId());
        ry.setOrgname(o.getName());
        ry.setOrgorder(o.getOrderby());
        ry.setNote(note);
        if (null != o.getOrg()) {
        	TSysOrg p = this.orgManager.getOrg(o.getOrg().getId());
            ry.setPorgid(p.getId());
            ry.setPorgname(p.getName());
            ry.setPorgorder(p.getOrderby());
        }
        this.dao.save(ry);

    }
}
