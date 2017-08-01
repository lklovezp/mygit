/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.common.dao.domain;

import java.util.List;

import com.hnjz.Constants;

/**
 * 分页查询的结果
 * 
 * @author wumi
 * @version $Id: FyResult.java, v 0.1 Dec 20, 2011 6:51:09 AM Administrator Exp $
 */
@SuppressWarnings("unchecked")
public class FyResult {
	/** 结果集* */
	private List<Object> re;
	/** 总页数 */
	private int allPage = 1;
	/** 当前页 */
	private int currentPage = 1;
	/** 开始记录 */
	private int startItem = 1;
	/** 总数目* */
	private long num;
	/** 每页显示数目* */
	private int perPageNum = Constants.PER_PAGE;

	public void execute() {
		if (currentPage <= 0) {	
			currentPage = 1;
		}
		allPage = (int) Math.ceil((num + perPageNum - 1) / perPageNum);
		if (currentPage > 0) {
			startItem = (currentPage - 1) * perPageNum;
		} else {
			startItem = 0;
		}
	}

	public int getAllPage() {
		return allPage;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartItem() {
		return startItem;
	}

	public void setStartItem(int startItem) {
		this.startItem = startItem;
	}

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}


	public <T> List<T> getRe() {
		return (List<T>) re;
	}

	public void setRe(List<Object> re) {
		this.re = re;
	}

}
