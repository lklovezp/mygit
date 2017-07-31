package com.hnjz.app.data.zfdx.xqyz;
import java.text.ParseException;

import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataXqyz;
import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;



/**
 * Tdataxqyz管理的manager
 * 
 * @author xuyaxing
 * @version 
 */
public interface XqyzManager extends Manager{


	   
    /**
	 * 保存TDataXqyz
	 * 
	 * @param frm
     * @return 
	 * @throws AppException
	 */
	public TDataLawobjf saveXqyz(XqyzForm frm) throws AppException, ParseException;


/**
	 * 获取单个TDataXqyz
	 * 
	 * @param id
	 *            TDataXqyz的ID
	 */
	public void editXqyz(XqyzForm frm);
	
	/**
	 *通过某个字段获取畜禽养殖对象
	 */
   public TDataXqyz getXqyz(String lawobjid);

   /**
    * 删除畜禽养殖
    */
   public void delXqyz(String lawobjid);
}

