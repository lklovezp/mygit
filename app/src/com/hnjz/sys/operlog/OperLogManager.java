/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.operlog;

import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 行业管理的manager
 * 
 * @author wumi
 * @version $Id: LogManager.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
public interface OperLogManager extends Manager {

	/**
	 * 查询列表数据
	 * @param pageSize2 
	 * @param page2 
	 * 
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public FyWebResult queryOperLogList(String czsjFrom, String czsjTo, String czrName, String operateType, String note, String page, String pageSize) throws Exception;
}
