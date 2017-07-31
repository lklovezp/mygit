package com.hnjz.app.data.zfdx.yy;
import java.text.ParseException;

import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataYy;
import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;


/**
 * Tdatayy管理的manager
 * 
 * @author XUYAXING
 * @version 
 */
public interface YyManager extends Manager{


    /**
	 * 保存TDataYy
	 * 
	 * @param frm
     * @return 
	 * @throws AppException
	 */
	public TDataLawobjf saveyy(YyForm frm) throws AppException, ParseException;


/**
	 * 获取单个TDataYy
	 * 
	 * @param id
	 *            TDataYy的ID
	 */
	public void edityy(YyForm frm);
	
	/**
	 *通过某个字段获取医院对象
	 */
   public TDataYy getyy(String lawobjid);
   
   /**
    * 删除医院
    */
   public void delYy(String lawobjid);


}

