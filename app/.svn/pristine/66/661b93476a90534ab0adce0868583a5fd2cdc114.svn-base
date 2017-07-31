package com.hnjz.app.data.zfdx.lawobjf;
import java.util.List;

import org.json.JSONArray;

import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerA;


/**
 * Tdatalawobjf管理的manager
 * 
 * @author 高志阳
 * @version 
 */
public interface LawobjfManager extends Manager{



	
    
      /**
	 * 分页查询功能
	 * @param strWhere  例如 and name=:name
	 * @param data   data.putLike("name", "张三")
     * @page 
     * @pageSize 
	 * @return 
	 * @throws Exception
	 */
	public FyWebResult queryTdatalawobjf(String name,String dwmc,String lawobjTypeId,String regionId,String orgId,String wgid, String page,String pageSize);

/**
	 * 删除Tdatalawobjf
	 * 
	 * @param id
	 *            Tdatalawobjf的ID
	 */
	public void removeTdatalawobjf(String id) throws AppException;
    
    
    
    /**
	 * 保存Tdatalawobjf
	 * 
	 * @param frm
	 * @throws AppException
	 */
	public TDataLawobjf saveTdatalawobjf(LawobjfForm frm) throws AppException;


/**
	 * 获取单个Tdatalawobjf
	 * 
	 * @param id
	 *            Tdatalawobjf的ID
	 */
	public void editLawobjf(LawobjfForm frm);
	
	/**
	 * 
	 * 函数介绍：获取工业污染源详情
	
	 * 输入参数：jsxmid:如果是建设项目转过来的，更新建设项目对应的执法对象id
	
	 * 返回值：
	 */
	public TDataLawobjf getLawobjfInfo(TDataLawobjf lawobjf);
   /**
    * 获取父表信息
    */
	public TDataLawobjf getLawobjfById(String id);
	
	 /**
		 * 分页查询功能
		 * @param strWhere  例如 and name=:name
		 * @param data   data.putLike("name", "张三")
	     * @page 
	     * @pageSize 
		 * @return 
		 * @throws Exception
		 */
		public FyWebResult queryssjgbmnull(String page,String pageSize);

}

