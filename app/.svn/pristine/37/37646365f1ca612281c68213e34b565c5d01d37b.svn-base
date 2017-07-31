package com.hnjz.app.work.sgdw;


import java.io.IOException;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;
/**
 * 施工单位管理
 * 
 * @author wumi
 * @version $Id: UserManager.java, v 0.1 Jan 17, 2013 9:32:53 AM wumi Exp $
 */
public interface BuilderManager extends Manager{
	/**
	 * 查询施工单位列表
	 * 
	 * @param name
	 *            搜索条件，可以按名称搜索
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * 
	 * @return 功能菜单列表
	 * @throws Exception
	 */
	public FyWebResult queryBuilderList(String name, String isActive, String page, String pageSize)
			throws Exception;
	
    /**
     * 保存施工单位信息
     * @param TDataSgdw tDataSgdw 施工单位
     * @throws AppException
     * */	
	public void saveBuilder(BuilderForm builderForm)throws AppException;
	/**
	 * 删除施工单位
	 * 
	 * @param id
	 *            施工单位ID
	 * @throws AppException 
	 */
	public void removeBuilder(String id) throws AppException;
	/**
	 * 修改施工单位
	 * 
	 * @param builderForm
	 *            施工单位builderForm
	 * @throws AppException 
	 */
	public void editBuilder(BuilderForm builderForm)throws AppException;

}
