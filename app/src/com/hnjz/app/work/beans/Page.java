package com.hnjz.app.work.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hnjz.Constants;

/**
 * 分页对象
 * @author zn
 * @version $Id: Page.java, v 0.1 2013-2-28 上午03:05:52 zn Exp $
 */
public class Page {
    private int                    pageNo;
    private int                    pageSize = Constants.PER_PAGE;
    private int                    total;
    private List<? extends Object> list     = new ArrayList<Object>();

    public Page() {
    }

    public Page(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    /**
     * 转为Map
     * @return
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", getTotal());
        map.put("pageSize", getPageSize());
        map.put("pageNo", getPageNo());
        map.put("rows", getList());
        return map;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<? extends Object> getList() {
        return list;
    }

    public void setList(List<? extends Object> list) {
        this.list = list;
    }
}
