package com.hnjz.app.data.xxgl.complaint;

import java.util.Map;

import com.hnjz.app.data.po.TDataComplaint;
import com.hnjz.common.FyWebResult;



/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-17
 * 功能描述：
	执法对象字典manager接口
 *
 */
public interface ComplaintManager {
	
	/**
	 * 
	 * 函数介绍：获得投诉信息详情
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public ComplaintForm getComplaintInfo(ComplaintForm complaintForm);
	
	/**
	 * 
	 * 函数介绍：保存或更新投诉信息
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public TDataComplaint saveOrUpdateComplaint(ComplaintForm complaintForm);

	/**
	 * 
	 * 函数介绍：查询投诉信息列表数据
	
	 * 输入参数：
	 * @param lawobjname  单位名称
	 * @param lawobjaddress 单位地址
	 * @param starttime 投诉时间 开始时间
	 * @param endtime 投诉时间 结束时间
	 * @param isActive 是否有效
	 * @param lawobjid 三产信息id
	 * @param page 当前页
	 * @param pageSize 每页显示条数
	
	 * 返回值：
	 */
	public FyWebResult queryComplaintList(String lawobjtypeid,String lawobjname,String lawobjaddress,String starttime,String endtime,String isActive,String lawobjid,String page,String pageSize);

	/**
	 * 
	 * 函数介绍：投诉信息置为无效
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public void removeComplaint(String id);

	/**
	 * 
	 * 函数介绍：获取一条投诉信息
	
	 * 输入参数：
	
	 * 返回值：
	 */
	public Map<String, String> queryOneComplaint(String lawobjid);
}
