package com.hnjz.app.work.dao;

import org.springframework.stereotype.Repository;

import com.hnjz.app.work.po.WorkJcinfo;
import com.hnjz.wf.dao.MyDao;

@Repository("jcInfoDao")
public class WorkJcinfoDao extends MyDao<WorkJcinfo, String> {

}
