package com.hnjz.app.data.zfdx.gywry;
import java.text.ParseException;
import java.util.List;

import org.json.JSONArray;

import com.hnjz.app.data.po.TDataGywry;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerA;



/**
 * Tdatagywry管理的manager
 * 
 * @author xuyaxing
 * @version 
 */
public interface GywryManager extends Manager{



    
    
    /**
	 * 保存Tdatagywry
	 * 
	 * @param frm
     * @return 
	 * @throws AppException
	 */
	public TDataLawobjf saveTDataGywry(GywryForm frm) throws AppException, ParseException;


/**
	 * 获取单个Tdatagywry
	 * 
	 * @param id
	 *            Tdatagywry的ID
	 */
	public void editGywry(GywryForm frm);
	
	/**
	 *通过某个字段获取工业污染源对象
	 */
   public TDataGywry getGywry(String lawobjid);
   
   /**
    * 删除工业污染源
    */
   public void delGywry(String lawobjid);
}

