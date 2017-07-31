package com.hnjz.app.data.zfdx.yly;
import java.text.ParseException;

import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataYly;
import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;



/**
 * Tdatayly管理的manager
 * 
 * @author XUYAXING
 * @version 
 */
public interface YlyManager extends Manager{

	/**
	 * 保存Tdatayly
	 * 
	 * @param frm
     * @return 
	 * @throws AppException
	 */
	public TDataLawobjf saveyly(YlyForm frm) throws AppException, ParseException;


/**
	 * 获取单个Tdatayly
	 * 
	 * @param id
	 *            Tdatayly的ID
	 */
	public void edityly(YlyForm frm);
	
	/**
	 *通过某个字段获取娱乐业对象
	 */
   public TDataYly getyly(String lawobjid);
   
   /**
    * 删除娱乐业
    */
   public void delYly(String lawobjid);

}

