/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.dic;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.hnjz.common.AppException;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysDic;

/**
 * 部门管理Manager
 * 
 * @author wumi
 * @version $Id: DicManager.java, v 0.1 Jan 15, 2013 5:05:39 PM wumi Exp $
 */
public interface DicManager extends Manager {

	/**
	 * 查询不可编辑分页列表
	 * 
	 * @return 列表
	 * @throws Exception
	 */
	public JSONArray queryDic(String type) throws Exception;

	/**
	 * 删除功能
	 * 
	 * @param id
	 * @throws AppException 
	 */
	public void removeDic(String id) throws AppException;

	/**
	 * 清空表
	 * 
	 * @param id
	 */
	public void removeDicData();

	/**
	 * 保存
	 * 
	 * @param {@link DicForm}
	 */
	public void saveDicData(DicForm frm) throws AppException;

	/**
	 * 保存
	 * 
	 * @param {@link DicForm}
	 */
	public int saveBatchDicData(List<DicForm> dicFormList) throws AppException;

	/**
	 * 查询
	 * 
	 * @throws Exception
	 */
	public JSONArray dicTypeQuery() throws Exception;

	/**
	 * 无分页查询,根据type
	 * 
	 * @throws Exception
	 */
	public JSONArray dicDateQuery(String type) throws Exception;

	/**
	 * 查询某个字典类型下的所有数据(code,name)
	 * 
	 * @param dicType
	 *            字典类型{@link DicTypeEnum}
	 * @return 某个类型下定义的所有字典集合
	 */
	public List<Combobox> queryDicData(DicTypeEnum dicType);

	/**
	 * 查询某个字典类型下的所有数据(code,name)
	 * 
	 * @param dicType
	 *            字典类型{@link DicTypeEnum}
	 * @return 某个类型下定义的所有字典集合
	 */
	public List<Combobox> queryDicDataByCode(String diccode);

	/**
	 * 查询某个字典类型下的所有数据(code,name)
	 * 
	 * @param dicType
	 *            字典类型{@link DicTypeEnum}
	 * @return 某个类型下定义的所有字典集合
	 */
	public List<Combobox> queryDicDataId(DicTypeEnum dicType);
	
	/**
	 * 通过code查询某个字典数据(code)
	 * 
	 * @param code
	 *            字典编号
	 * @return 某个字典数据
	 */
	public TSysDic queryDicByCode(String type, String code);

	/**
	 * 数据字典的公共选择界面
	 * 
	 * @param id
	 *            部门Id
	 * @param type 
	 * @return 部门功能的初始界面
	 * @throws JSONException 
	 */
	public JSONArray querySelectDic(String id, String type) throws JSONException;
	
	/**
	 * 查询所有字典根节点（文档总分类）
	 * 
	 * @return 列表
	 * @throws Exception
	 */
	public List<Combobox> queryWdfl(String type);
	
	/**
	 * 根据文档分类查询所有子类型
	 * 
	 * @return 列表
	 * @throws Exception
	 */
	public List<Combobox> queryWdzlx(String type);

}
