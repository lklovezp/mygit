/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.common;

import java.util.List;
import java.util.Map;

import com.hnjz.common.dao.domain.FyResult;

/**
 * 
 * @author wumi
 * @version $Id: FyWebResult.java, v 0.1 Jan 17, 2013 12:50:34 PM wumi Exp $
 */
public class FyWebResult {

    /** 每页显示数目* */
    private int                       perPageNum;
    /** 第几页* */
    private int                       pageNumber;
    /** 数据* */
    private List<Map<String, String>> rows;
    /**第几页*/
    private long                      total;
    
    

    public FyWebResult() {

    }

    public FyWebResult(FyResult pos) {
        this.perPageNum = pos.getPerPageNum();
        this.total = pos.getNum();
        this.pageNumber = pos.getCurrentPage();
    }

    public int getPerPageNum() {
        return perPageNum;
    }

    public void setPerPageNum(int perPageNum) {
        this.perPageNum = perPageNum;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<Map<String, String>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, String>> rows) {
        this.rows = rows;
    }

}
