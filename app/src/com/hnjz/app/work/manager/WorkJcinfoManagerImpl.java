package com.hnjz.app.work.manager;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.work.dao.WorkJcinfoDao;
import com.hnjz.app.work.po.WorkJcinfo;

@Service("jcInfoManager")
public class WorkJcinfoManagerImpl extends AbsManagerImpl<WorkJcinfo, String> {
    @Autowired
    private WorkJcinfoDao jcInfoDao;

    @Override
    @PostConstruct
    public void initDao() {
        setDao(jcInfoDao);
    }
}
