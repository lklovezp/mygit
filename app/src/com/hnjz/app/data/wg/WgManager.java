package com.hnjz.app.data.wg;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.hnjz.app.data.po.TDataWg;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysRole;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserForm;

/**
 * 
 * @author xuyaxing
 * 功能描述：网格化管理
 */
public interface WgManager extends Manager{

	/**
	 * 保存网格
	 */
	public void saveWg(WgForm wg) throws AppException; 
	
	/**
	 * 删除网格,网格和用户的关联
	 */
	public void delWg(String id);
	
	/**
	 * 通过id获取某个网格
	 * @throws Exception 
	 */
	public void  getWg(WgForm wg) throws Exception;
	
	
	
	/**
	 * 通过部门分页查询网格
	 */
	public FyWebResult queryWg(String wgmc, String orgid,String page, String pageSize) throws Exception;
	
	/**
	 * 通过网格id获取人员信息
	 */
	public List<TSysUser> queryUserByWg(String id)throws Exception;
	
	/**
	 * 编辑网格
	 */
	
	public void editWg(WgForm frm)throws Exception;
	
	/**
	 * 查询所有网格
	 */
	public List<Map<String, String>> queryAllWg();
	
	/**
	 * 通过部门id查询网格
	 */
	public List<TDataWg> queryWgByOrg(String orgid);
	

}

