package com.hnjz.app.data.zfdx.zzy;
import java.text.ParseException;

import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataZzy;
import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;



/**
 * Tdatazzy管理的manager
 * 
 * @author xuyaxing
 * @version 
 */
public interface ZzyManager extends Manager{

	 /**
	 * 保存TDataZzy
	 * 
	 * @param frm
     * @return 
	 * @throws AppException
	 */
	public TDataLawobjf savezzy(ZzyForm frm) throws AppException, ParseException;


/**
	 * 获取单个TDataZzy
	 * 
	 * @param id
	 *            TDataZzy的ID
	 */
	public void editzzy(ZzyForm frm);
	
	/**
	 *通过某个字段获取工业污染源对象
	 */
   public TDataZzy getzzy(String lawobjid);
   
   /**
    * 删除制造业
    */
   public void delZzy(String lawobjid);

}

