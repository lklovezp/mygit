/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.hnjz.common.dao;

import java.io.File;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.dialect.Dialect;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;

import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.Sql;
import com.hnjz.common.dao.domain.SqlQueryCondition;
import com.hnjz.common.util.LogUtil;

/**
 * 对于sql查询的一些封装
 * 
 * @author wumi
 * @version $Id: SqlQueryImpl.java, v 0.1 Jun 11, 2012 11:06:49 AM wumi Exp $
 */
public class SqlQueryImpl implements SqlQuery, InitializingBean {

    /**日志*/
    private static final Log           log  = LogFactory.getLog(SqlQueryImpl.class);

    private Map<String, Sql>           slqs = new ConcurrentHashMap<String, Sql>();

    private NamedParameterJdbcTemplate njt;

    private Dialect                    dialect;

    private String                     dialectClass;

    private List<String>               sqlFiles;

    
    public List<Map<String, Object>> query(SqlQueryCondition data) {
        Sql s = slqs.get(data.getName());
        Assert.notNull(s);
        StringBuilder ss = new StringBuilder();
        ss.append(s.getListSql());
        Map<String, Object> canshu = data.getCanshu();
        for (Map.Entry<String, Object> ele : canshu.entrySet()) {
            ss.append(s.getNotBlanks().get(ele.getKey()));
        }
        if (StringUtils.isNotBlank(s.getEndSql())) {
            ss.append(s.getEndSql());
        }
        if (log.isDebugEnabled()) {
            log.debug("sql:" + ss);
        }
        List<Map<String, Object>> re = njt.queryForList(ss.toString(), canshu);
        if (log.isDebugEnabled()) {
            log.debug("查询结果：" + LogUtil.m(re));
        }
        return re;
    }

    
    public FyResult queryPageList(SqlQueryCondition data, int curPage) {
        Sql s = slqs.get(data.getName());
        Assert.notNull(s);
        FyResult fy = new FyResult();
        StringBuilder ss = new StringBuilder();
        Map<String, Object> canshu = data.getCanshu();
        for (Map.Entry<String, Object> ele : canshu.entrySet()) {
            ss.append(s.getNotBlanks().get(ele.getKey()));
        }
        StringBuilder count = new StringBuilder();
        count.append(s.getCountSql());
        count.append(ss);
        if (log.isDebugEnabled()) {
            log.debug("count：" + count);
        }
        long c = njt.queryForLong(count.toString(), canshu);
        fy.setNum(c);
        fy.setCurrentPage(curPage);
        if (StringUtils.isNotBlank(data.getPageSize())) {
            fy.setPerPageNum(Integer.parseInt(data.getPageSize()));
        }
        fy.execute();
        if (fy.getAllPage() < fy.getCurrentPage()) {
            fy.setCurrentPage(1);
            fy.execute();
        }
        ss = new StringBuilder();
        ss.append(s.getListSql());
        ss.append(s.getEndSql());
        String ll = dialect.getLimitString(ss.toString(), fy.getStartItem(), fy.getPerPageNum());
        if (log.isDebugEnabled()) {
            log.debug("listSql:" + ll);
        }
        return fy;
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(dialectClass);
        this.loadSqls();
        dialect = (Dialect) Class.forName(dialectClass).newInstance();
        Assert.notNull(dialect);
    }

    /**
     * 加载所有的sql查询,将xml定义的sql查询转换到对象{@link Sql}中
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private void loadSqls() throws Exception {
        String dir = getClass().getResource("/").getFile().toString();
        dir = URLDecoder.decode(dir, "utf-8");
        if (log.isDebugEnabled()) {
            log.debug("dir:" + dir);
        }
        File f = null;
        for (String ele : sqlFiles) {
            f = new File(dir, ele);
            if (log.isDebugEnabled()) {
                log.debug("获取文件：" + f.getAbsolutePath());
            }
            Sql s = null;
            String name = null;
            SAXReader reader = new SAXReader();
            Document doc = reader.read(f);
            Element root = doc.getRootElement();
            for (Iterator<Element> i = root.elementIterator(); i.hasNext();) {
                Element el = (Element) i.next();
                s = new Sql();
                name = el.getName();
                for (Iterator<Element> j = el.elementIterator(); j.hasNext();) {
                    Element mx = (Element) j.next();
                    if (log.isDebugEnabled()) {
                        log.debug(mx.getName() + ":" + mx.getTextTrim());
                    }
                    if (StringUtils.equals(mx.getName(), "countSql")) {
                        s.setCountSql(mx.getTextTrim());
                    }
                    if (StringUtils.equals(mx.getName(), "listSql")) {
                        s.setListSql(mx.getTextTrim());
                    }
                    if (StringUtils.equals(mx.getName(), "endSql")) {
                        s.setEndSql(mx.getTextTrim());
                    }
                    if (StringUtils.equals(mx.getName(), "notBlank")) {
                        s.addBlank(mx.attribute("name").getValue(), mx.getTextTrim());
                    }
                }
                if (log.isDebugEnabled()) {
                    log.debug("s:" + s);
                }
                slqs.put(name, s);
            }
        }
    }

    public void setSqlFiles(List<String> sqlFiles) {
        this.sqlFiles = sqlFiles;
    }

    public void setDialectClass(String dialectClass) {
        this.dialectClass = dialectClass;
    }

    public void setNjt(NamedParameterJdbcTemplate njt) {
        this.njt = njt;
    }

}
