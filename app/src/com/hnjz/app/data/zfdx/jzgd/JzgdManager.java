package com.hnjz.app.data.zfdx.jzgd;
import java.text.ParseException;

import com.hnjz.app.data.po.TDataJzgd;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;



/**
 * Tdatajzgd管理的manager
 * 
 * @author xuyaxing
 * @version 
 */
public interface JzgdManager extends Manager{

	  
    /**
	 * 保存TDataJzgd
	 * 
	 * @param frm
     * @return 
	 * @throws AppException
	 */
	public TDataLawobjf saveJzgd(JzgdForm frm) throws AppException, ParseException;


/**
	 * 获取单个TDataJzgd
	 * 
	 * @param id
	 *            TDataJzgd的ID
	 */
	public void editJzgd(JzgdForm frm);
	
	/**
	 *通过某个字段获取建筑工地对象
	 */
   public TDataJzgd getJzgd(String lawobjid);
   
   /**
    * 删除建筑工地
    */
   public void delJzgd(String lawobjid);

}

