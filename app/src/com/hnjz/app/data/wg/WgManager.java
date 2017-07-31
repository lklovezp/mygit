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
 * �������������񻯹���
 */
public interface WgManager extends Manager{

	/**
	 * ��������
	 */
	public void saveWg(WgForm wg) throws AppException; 
	
	/**
	 * ɾ������,������û��Ĺ���
	 */
	public void delWg(String id);
	
	/**
	 * ͨ��id��ȡĳ������
	 * @throws Exception 
	 */
	public void  getWg(WgForm wg) throws Exception;
	
	
	
	/**
	 * ͨ�����ŷ�ҳ��ѯ����
	 */
	public FyWebResult queryWg(String wgmc, String orgid,String page, String pageSize) throws Exception;
	
	/**
	 * ͨ������id��ȡ��Ա��Ϣ
	 */
	public List<TSysUser> queryUserByWg(String id)throws Exception;
	
	/**
	 * �༭����
	 */
	
	public void editWg(WgForm frm)throws Exception;
	
	/**
	 * ��ѯ��������
	 */
	public List<Map<String, String>> queryAllWg();
	
	/**
	 * ͨ������id��ѯ����
	 */
	public List<TDataWg> queryWgByOrg(String orgid);
	

}
