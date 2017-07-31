package com.hnjz.app.work.dao;

import org.springframework.stereotype.Repository;

import com.hnjz.app.work.po.Work;
import com.hnjz.wf.dao.MyDao;

/**
 * Work（任务）数据操作
 * @author zn
 * @version $Id: WorkDao.java, v 0.1 2013-1-22 上午03:51:00 zn Exp $
 */
@Repository("workDAO")
public class WorkDao extends MyDao<Work, String> {

}
