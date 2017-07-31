package com.hnjz.app.data.zfdx.ysy;
import java.text.ParseException;

import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataYsy;
import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;
import com.hnjz.common.manager.ManagerA;



/**
 * Tdataysy管理的manager
 * 
 * @author XUYAXING
 * @version 
 */
public interface YsyManager extends Manager{

	/**
	 * 保存TDataYsy
	 * 
	 * @param frm
     * @return 
	 * @throws AppException
	 */
	public TDataLawobjf saveysy(YsyForm frm) throws AppException, ParseException;


/**
	 * 获取单个TDataYsy
	 * 
	 * @param id
	 *            TDataYsy的ID
	 */
	public void editysy(YsyForm frm);
	
	/**
	 *通过某个字段获取饮食业对象
	 */
   public TDataYsy getysy(String lawobjid);
   
   /**
    * 删除饮食业
    */
   public void delYsy(String lawobjid);

}

