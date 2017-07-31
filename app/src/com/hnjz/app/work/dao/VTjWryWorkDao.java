package com.hnjz.app.work.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.hnjz.app.work.po.VTjWryWork;
import com.hnjz.wf.dao.MyDao;

/**
 * 污染源任务统计
 * @author zn
 * @version $Id: VTjWryWorkDao.java, v 0.1 2013-4-10 上午08:00:24 zn Exp $
 */
@Repository(value = "vtjWryWorkDao")
public class VTjWryWorkDao extends MyDao<VTjWryWork, String> {

    /**
     * sql查询分页
     * @param pageNo
     * @param pageSize
     * @param sql
     * @param canshu
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Object> findBySQL(final Integer pageNo, final Integer pageSize, final String sql,
                                  final Object... canshu) {
        SQLQuery query = super.getSession().createSQLQuery(sql);
        if (canshu != null && canshu.length > 0) {
            for (int i = 0; i < canshu.length; i++) {
                query.setParameter(i, canshu[i]);
            }
        }
        if (pageNo != -1 && pageSize != -1) {// 添加分页
            Integer start = (pageNo - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);
        }
        return query.list();
    }
}
