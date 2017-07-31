package com.hnjz.app.data.xxgl.zyclbz;

import org.json.JSONArray;

import com.hnjz.app.data.po.TDataDiscreacts;
import com.hnjz.app.data.po.TDataDiscrecaseclass;
import com.hnjz.app.data.po.TDataDiscremerit;
import com.hnjz.common.FyWebResult;
import com.hnjz.sys.po.TSysDic;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-9
 * 功能描述：
		自有裁量Manager层
 *
 */
public interface DiscreManager {

	/**
	 * 
	 * 函数介绍：获得自由裁量标准树
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public JSONArray getZyclbzTree();

	/**
	 * 
	 * 函数介绍：制度分类详情
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public TSysDic zdflInfo(String id);

	/**
	 * 
	 * 函数介绍：违法行为详情
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public TDataDiscreacts getTDataDiscreactsInfo(String id);

	/**
	 * 
	 * 函数介绍：法律依据详情
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public TDataDiscremerit getTDataDiscremeritInfo(String id);

	/**
	 * 
	 * 函数介绍：情形分类详情
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public TDataDiscrecaseclass getTDataDiscrecaseclassInfo(String id);
	
	/**
	 * 
	 * 函数介绍：保存违法行为
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveOrUpdateWfxw(TDataDiscreacts tDataDiscreacts);
	
	/**
	 * 
	 * 函数介绍：违法行为置为无效
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void removeWfxw(String id);
	
	
	/**
	 * 
	 * 函数介绍：违法类型分页列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public FyWebResult queryWflxList(String pid,String page,String pageSize);
	
	/**
	 * 
	 * 函数介绍：保存法律依据
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveOrUpdateFlyj(TDataDiscremerit tDataDiscremerit);
	
	/**
	 * 
	 * 函数介绍：法律依据置为无效
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void removeFlyj(String id);
	
	
	/**
	 * 
	 * 函数介绍：法律依据分页列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public FyWebResult queryFlyjList(String pid,String page,String pageSize);
	
	/**
	 * 
	 * 函数介绍：保存情形分类
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void saveOrUpdateQxfl(TDataDiscrecaseclass tDataDiscrecaseclass);
	
	
	/**
	 * 
	 * 函数介绍：情形分类置为无效
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void removeQxfl(String id);
	
	/**
	 * 
	 * 函数介绍：情形分类分页列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public FyWebResult queryQxflList(String pid,String page,String pageSize);

	/**
	 * 
	 * 函数介绍：根据违法类型查询情形分类列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public FyWebResult queryQxflListByWflx(String wflxId, String page, String rows);

	/**
	 * 
	 * 函数介绍：自由裁量树（根据违法类型查询，违法任务查看相关法律依据使用）
	
	 * 输入参数：wflxId-违法行为id，多个用逗号隔开
	
	 * 返回值：
	 */
	public JSONArray getZyclbzTreeByWflxId(String wflxId);
}
