/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.work.xfdj;

import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import com.hnjz.app.work.po.TBizXfdj;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.zx.XfbjdForm;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 信访登记类
 * @author shiqiuhan
 * @created 2016-3-31,上午08:52:34
 */
public interface XfdjManager extends Manager {

	/**
	 * 查询信访登记
	 * @param xfly
	 * @param xfsj1
	 * @param xfsj2
	 * @param zt
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public FyWebResult queryXfdj(String xfly, String xfsj1, String xfsj2, String zt, String page, String pageSize)
			throws Exception;

	/**
	 * 保存信访登记
	 * @param frm
	 * @throws AppException
	 */
	@Transactional(readOnly = false)
	public TBizXfdj saveXfdj(XfdjForm frm) throws AppException;

	/**
	 * 删除信访登记
	 * @param id
	 * @throws AppException
	 */
	public void removeXfdj(String id) throws AppException;
	
	/**
	 * 查询本年份信访登记表数目
	 * @return
	 */
	public int queryNumByYear();
	
	/**
	 * 查询本月份信访登记表数目
	 * @return
	 */
	public int queryNumByMonth();
	
	/**
	 * 打印登记表
	 * @param xfly
	 * @param xfbh
	 * @param wrlx
	 * @param wrlxqt
	 * @param xfsj
	 * @param xfr
	 * @param lxdh
	 * @param lwmc
	 * @param bjsx
	 * @param xfnr
	 * @param jlr
	 * @param jlsj
	 * @param res
	 */
	public void exportXfdjb(HttpServletResponse response,String xfdjId);
	
	/**
	 * 打印办结单
	 * @param response
	 * @param xfdjId
	 */
	public void exportXfbjd(HttpServletResponse response,String xfdjId, String rwid);
	
	/**
	 * 查询办结单
	 * @param response
	 * @param xfdjId
	 */
	public XfbjdForm queryXfbjd(String xfdjId);
	
	/**
	 * 根据信访登记表id获取信访办结单
	 * @param xfdjId
	 * @return
	 */
	public XfbjdForm getXfbjdformByxfdjId(String xfdjId);
	
	/**
	 * 保存信访办结单
	 */
	public void saveHfInfo(XfbjdForm xfbjdForm,String xfdjId);
	
	/**
	 * 是否显示回访信息
	 * @param xfdjId
	 * @return
	 */
	public boolean isShowHf(String xfdjId);
	
	/**
	 * 根据信访登记表获取work的信息
	 * @param xfdjId
	 * @return
	 */
	public Work getWorkById(String xfdjId, String biaoshi);
	
	/**
	 * 根据work的信息获取下派的taskId
	 * @param xfdjId
	 * @return
	 */
	public String gettaskId(String processId);
	
	/**
	 * 是否可以进行派发或者修改操作
	 * @param xfdjId
	 * @return
	 */
	public boolean isShowXf(String xfdjId);
}
