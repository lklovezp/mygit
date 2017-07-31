package com.hnjz.app.data.zfdx.fwy;
import java.text.ParseException;

import com.hnjz.app.data.po.TDataFwy;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;


/**
 * Tdatafwy管理的manager
 * 
 * @author XUYAXING
 * @version 
 */
public interface FwyManager extends Manager{


    /**
	 * 保存TDataFwy
	 * 
	 * @param frm
     * @return 
	 * @throws AppException
	 */
	public TDataLawobjf savefwy(FwyForm frm) throws AppException, ParseException;


/**
	 * 获取单个TDataFwy
	 * 
	 * @param id
	 *            TDataFwy的ID
	 */
	public void editfwy(FwyForm frm);
	
	/**
	 *通过某个字段获取服务业对象
	 */
   public TDataFwy getfwy(String lawobjid);
   
   /**
    * 删除服务业
    */
   public void delFwy(String id);

}

