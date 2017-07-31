/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.common.dao;

import java.util.List;
import java.util.Map;

import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.SqlQueryCondition;

/**
 * ����sql��ѯ��һЩ��װ
 * 
 * @author wumi
 * @version $Id: SqlQuery.java, v 0.1 Dec 20, 2011 6:42:32 AM Administrator Exp $
 */
public interface SqlQuery {

    /**
     * ʹ��sql��ѯ�����ڲ����������̶��Ĳ�ѯ�����磺 String sql = "SELECT ID,NAME from USER where
     * NAME = :name"; QueryPCondition data = new QueryPCondition();
     * data.put("name", "����");
     * 
     * if(null != sex){ sql.applend(" and SEX = :sex"); data.put("sex", sex); }
     * dao.queryForList(sql,data); 
     * List��ΪMap���Ա���ѯΪ����
     * List��ΪMap�Ľṹ��
     *  key value 
     *  ID   1234566 
     *  NAME ����
     * 
     * @param data
     *            sql��Ҫ�Ĳ���
     * @return ����б�
     */
    List<Map<String, Object>> query(final SqlQueryCondition data);

    /**
     * ��ҳ��ѯ���������ʾ���μ���{@link Dao#find(String, Map)}
     * 
     * @param data
     *            sql��ѯ�����Ĳ���
     * @param curPage
     *            ��ǰҪҪ��ѯ��ҳ��
     * @return ����б�
     */
    FyResult queryPageList(final SqlQueryCondition data, int curPage);

}