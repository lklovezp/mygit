package com.hnjz.mobile.data.xxcx;

import java.util.Map;

import org.json.JSONArray;

import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-3
 * 功能描述：
	执法对象Manager接口
 *
 */
public interface LawobjMobileManager extends Manager{

	/**
	 * 
	 * 函数介绍：工业污染源列表
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param regionCode:所属行政区编码
	 * @param kzlx:控制类型
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryGywryList(String name, String regionId, String orgId, String kzlx, String qyzt, String curPage, String pageSize) throws Exception;
	
	/**
	 * 
	 * 函数介绍：建设项目列表
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param regionCode:所属行政区编码
	 * @param hylx:行业类型
	 * @param isChoose:是否是选择执法对象（Y/N）
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryJsxmList(String name, String regionId, String orgId, String hylx, String isChoose, String lawobjId,String qyzt, String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * 函数介绍：医院信息列表
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param regionCode:所属行政区编码
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryYyxxList(String name, String regionId, String orgId, String qyzt, String curPage, String pageSize) throws Exception;
	
	/**
	 * 
	 * 函数介绍：建筑工地列表
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param regionCode:所属行政区编码
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryJzgdList(String name, String regionId, String orgId,String qyzt,String sgdwmc,String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * 函数介绍：锅炉列表
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param regionCode:所属行政区编码
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryGlxxList(String name, String regionId, String orgId,String qyzt,String curPage, String pageSize) throws Exception;
	
	/**
	 * 
	 * 函数介绍：三产信息列表
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param regionCode:所属行政区编码
	 * @param regionCode:所属行政区编码
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryScxxList(String name,String industryId, String regionId, String orgId, String qyzt,String curPage, String pageSize) throws Exception;
	
	/**
	 * 
	 * 函数介绍：畜禽养殖列表
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param regionCode:所属行政区编码
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryXqyzList(String name, String regionId, String orgId, String qyzt,String curPage, String pageSize) throws Exception;

	/**
	 * 
	 * 函数介绍：获得执法对象详情
	 * 输入参数：lawobjId:执法对象id
	 * 返回值：
	 */
	public JSONArray getLawobjInfo(TDataLawobj lawobj);

	/**
	 * 
	 * 函数介绍：获得单个最新执法对象附件
	 * 输入参数：
	 * 返回值：
	 */
	public Map<String, String> getOneFileListByPid(String pid);
	
	/**
	 * 
	 * 函数介绍：获得一个最新的环评附件
	 * 输入参数：
	 * 返回值：
	 */
	public Map<String,String> getOneHpxxFileByPid(String pid);

	/**
	 * 
	 * 函数介绍：获得单个执法历史记录详情
	 * 输入参数：执法对象id
	 * 返回值：
	 */
	public Map<String, String> getOneZfHistory(String lawobjId);

	/**
	 * 
	 * 函数介绍：查询执法历史记录
	 * 输入参数：
	 * 返回值：
	 */
	public FyWebResult queryZfHistoryList(String lawobjId, String curPage, String pageSize);

	/**
	 * 
	 * 函数介绍：查询更多环评信息
	 * 输入参数：
	 * 返回值：
	 */
	public FyWebResult queryHpxxListByPid(String pid, String page, String pageSize);
	
	/**
	 * 
	 * 函数介绍：保存选择的执法对象（终端专用）
	 * 输入参数：任务id、执法对象类型、执法对象id（用逗号隔开）
	 * 返回值：
	 */
	public void saveChoseeLawobj(String rwid,String lawobjtype,String lawobjid);

	/**
	 * 
	 * 函数介绍：服务业列表
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param regionCode:所属行政区编码
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryFwyList(String name, String regionId, String orgId,String qyzt,String page, String pageSize) throws Exception;

	/**
	 * 
	 * 函数介绍：饮食业列表
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param regionCode:所属行政区编码
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryYsyList(String name, String regionId,String orgId, String qyzt,String page, String pageSize) throws Exception;

	/**
	 * 
	 * 函数介绍：制造业列表
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param regionCode:所属行政区编码
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryZzyList(String name, String regionId,String orgId, String qyzt,String page, String pageSize) throws Exception;

	/**
	 * 
	 * 函数介绍：娱乐业列表
	 * 输入参数：
	 * @param name:企业名称，支持模糊查询
	 * @param regionCode:所属行政区编码
	 * @param curPage:当前页
	 * @param pageSize:每页显示条数
	 * 返回值：
	 * @throws Exception 
	 */
	public FyWebResult queryYlyList(String name, String regionId, String orgId, String qyzt,String page, String pageSize) throws Exception;

}
