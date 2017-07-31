package com.hnjz.app.data.zfdx.gl;

import java.text.ParseException;

import com.hnjz.app.data.po.TDataGl;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.zfdx.gywry.GywryForm;
import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;
import com.hnjz.common.manager.ManagerA;


/**
 * Tdatagl管理的manager
 * 
 * @author xuyaxing
 * @version 
 */
public interface GlManager extends Manager{


	 
    /**
	 * 保存锅炉
	 * 
	 * @param frm
     * @return 
	 * @throws AppException
	 */
	public TDataLawobjf saveGl(GlForm frm) throws AppException, ParseException;


/**
	 * 获取单个锅炉
	 * 
	 * @param id
	 *            锅炉的ID
	 */
	public void editGl(GlForm frm);
	
	/**
	 *通过某个字段获取锅炉对象
	 */
   public TDataGl getGl(String lawobjid);
   
   /**
    * 删除锅炉
    */
   public void delGl(String lawobjid);
}

