package com.hnjz.app.data.xxgl.lawobjdic;

import java.util.List;

import com.hnjz.app.data.po.TDataLawobjdic;
import com.hnjz.common.domain.Combobox;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-17
 * 功能描述：
	执法对象字典manager接口
 *
 */
public interface LawobjDicManager {
	
	/**
	 * 
	 * 函数介绍：获得执法对象表的所有字段列
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public List<Combobox> queryLawobjColumnList();
	
	/**
	 * 
	 * 函数介绍：保存更新执法对象字典
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveOrUpdateLawobjDic(String lawobjtypeid,String[] id,String[] orderby,String[] colengname,String[] enumname,String[] colchiname,String[] inputtype,String[] datasource,String[] mandatory,String[] istwoitem);

	/**
	 * 
	 * 函数介绍：获取执法对象字段配置项列表
	
	 * 输入参数：执法对象类型
	
	 * 返回值：
	 */
	public List<TDataLawobjdic> queryLawobjDicList(String lawobjtypeid);

}
