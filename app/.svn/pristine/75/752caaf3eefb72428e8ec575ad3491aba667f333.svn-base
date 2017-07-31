package com.hnjz.app.data.zfdx.jsxm;
import java.text.ParseException;

import org.json.JSONArray;

import com.hnjz.app.data.po.TDataGywry;
import com.hnjz.app.data.po.TDataJsxm;
import com.hnjz.app.data.po.TDataLawobjf;


import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.Manager;



/**
 * Tdatajsxm管理的manager
 * 
 * @author xuyaxing
 * @version 
 */
public interface JsxmManager extends Manager{


	/**
	 * 保存Tdatagywry
	 * 
	 * @param frm
     * @return 
	 * @throws AppException
	 */
	public TDataLawobjf saveTDataJsxm(JsxmForm frm) throws AppException, ParseException;


/**
	 *编辑单个Tdatagywry
	 * 
	 * @param id
	 *            Tdatagywry的ID
	 */
	public void editJsxm(JsxmForm frm);
	
	/**
	 *通过某个字段获取建设项目对象
	 */
   public TDataJsxm getJsxm(String lawobjfid);
   
   /**
    * 删除建设项目
    */
   public void delJsxm(String lawobjid);
}

