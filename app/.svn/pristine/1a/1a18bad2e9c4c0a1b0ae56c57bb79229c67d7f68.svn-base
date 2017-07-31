/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.dic;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
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
	public FyWebResult queryDic(String type, String name, String page,
			String pageSize) throws Exception;

	/**
	 * 删除功能
	 * 
	 * @param id
	 */
	public void removeDic(String id);

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
	 * @throws FileNotFoundException
	 */
	public void dicDataBackUp() throws AppException, FileNotFoundException;

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
	 * 按类型查询全部（用于选择页面id,name）
	 * 
	 * @param type
	 *            字典类型，定义见枚举DicTypeEnum
	 * @return 某个类型下定义的所有字典集合
	 */
	public List<Object> findByType(String type);

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
	 * 查询违法类型
	 * 
	 * @return
	 */
	public List<Map<String, Object>> queryIllegalType();

	/**
	 * 查询某个字典类型下的所有数据(code,name)
	 * 
	 * @param dicType
	 *            字典类型{@link DicTypeEnum}
	 * @return 某个类型下定义的所有字典集合
	 */
	public List<Combobox> queryDicDataId(DicTypeEnum dicType);
	
	/**
	 * 
	 * 函数介绍：通过type和code获得名称
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public String getNameByTypeAndCode(String type,String code);
	
	/**
	 * 通过code查询某个字典数据(code)
	 * 
	 * @param code
	 *            字典编号
	 * @return 某个字典数据
	 */
	public TSysDic queryDicByCode(String type, String code);
	
	/**
	 * 查询字典的全部根目录
	 * @return
	 */
	public List<String[]> queryRootCode();
	/**
	 * 根据字典序号查看是否重复
	 * @param xh
	 * @return
	 */
	public void findRootxh(DicForm df) throws Exception;
	/**
	 * 根据id删除根目录
	 * @param id
	 */
	public void deleteData(String id);
	
	/**
	 * 添加根目录信息
	 * @param name
	 * @param id
	 * @param userId
	 * @return
	 */
	public void addData(String name,String id,String userId,String dateString);
	
	public void findRootxhcc(String id) throws AppException;
}
