/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.drafter.drafterSend;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hnjz.app.drafter.po.TBizDrafter;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;

/**
 * 稿件报送Manager
 * @author shiqiuhan
 * @created 2015-12-24,上午09:00:13
 */
public interface DrafterManager extends Manager {

	/**
	 * 查询稿件列表
	 * 
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @return 稿件列表
	 * @throws Exception
	 */
	public FyWebResult queryDrafter(DrafterForm frm,String page, String pageSize) throws Exception;
	
	/**
	 * 查询稿件审核列表
	 * 
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @return 稿件列表
	 * @throws Exception
	 */
	public FyWebResult queryAuditDrafter(DrafterForm frm,String page, String pageSize) throws Exception;
	
	/**
	 * 查询稿件统计列表
	 * 
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @return 稿件列表
	 * @throws Exception
	 */
	public FyWebResult queryDrafterStatistics(DrafterForm frm,String page, String pageSize) throws Exception;

	/**
	 * 保存稿件
	 * 
	 * @param drafterSendForm
	 *            {@link drafterSendForm}
	 */
	@Transactional(readOnly = false)
	public TBizDrafter saveDrafter(DrafterForm frm) throws AppException;

	/**
	 * 删除稿件
	 * 
	 * @param id
	 *            稿件信息的ID
	 */
	public void removeDrafter(String id) throws AppException;
	
	/**
	 * 稿件审核
	 * 
	 * @param drafterForm
	 *            {@link drafterForm}
	 */
	public TBizDrafter auditDrafter(String id,String result) throws AppException;
	
	/**
	 * 
	 * 函数介绍：审批状态下拉列表
	 */
	public List<Combobox> queryStateList();
	
}
