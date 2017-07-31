package com.hnjz.app.data.zfdx.lawobjtype;
import java.util.List;

import org.json.JSONArray;

import com.hnjz.app.data.po.TDataGywry;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.manager.ManagerA;


/**
 * Tdatalawobjtype管理的manager
 * 
 * @author xuyaxing
 * @version 
 */
public interface ZfdxManager extends Manager{


/**
	 * 查询功能
	 * @param isActive 
	 * 
	 * @return 
	 * @throws Exception
	 */
	public JSONArray queryTdatalawobjtype( String isActive) throws Exception;
    
    
/**
	 * 删除Tdatalawobjtype
	 * 
	 * @param id
	 *            Tdatalawobjtype的ID
	 */
	public void removeTdatalawobjtype(String id) throws AppException;
    
    
    
    /**
	 * 保存Tdatalawobjtype
	 * 
	 * @param frm
	 * @throws AppException
	 */
	public void saveTdatalawobjtype(ZfdxForm frm) throws AppException;


/**
	 * 获取单个Tdatalawobjtype
	 * 
	 * @param id
	 *            Tdatalawobjtype的ID
	 */
	public ZfdxForm tdatalawobjtypeInfo(String  id);
	
	/**
	 * 编辑执法对象类型
	 */
	public void editLawObjType(ZfdxForm frm);
	
	/**
	 * 获取单个Tdatalawobjtype
	 * 
	 * @param id
	 *            Tdatalawobjtype的ID
	 */
	public TDataLawobjtype querylawobjtypeById(String  id);
	/**
	 * 获取单个Tdatalawobjtype
	 * 
	 * @param id
	 *            Tdatalawobjtype的ID
	 */
	public List<TDataLawobjtype> querylawobjtypeByFId(String  fid);


	public List<TDataLawobjtype> querylawobjtype();
	
    public TDataLawobjtype gettype(String id);
    /**
     * 
     * @param pid
     * @return 执法对象类型树
     */
    public JSONArray getLawdocTypeListByPid(String pid);
    
    /**
	 * 
	 * 函数介绍：获得执法对象类型 （下拉树）
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<ComboboxTree> queryLawobjtypeComboTree();
	
	/**
	 *通过某个字段获取执法对象类型
	 */
   public TDataLawobjtype getType(String lawobjid);
}

