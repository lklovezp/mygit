package com.hnjz.app.data.zfdx.sc;
import java.text.ParseException;
import java.util.List;

import org.json.JSONArray;

import com.hnjz.app.data.po.TDataSc;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.zfdx.gywry.GywryForm;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.AppException;
import com.hnjz.common.manager.Manager;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerA;



/**
 * Tdatasc管理的manager
 * 
 * @author XUYAXING
 * @version 
 */
public interface ScManager extends Manager{


	 /**
		 * 保存TDataSc
		 * 
		 * @param frm
	     * @return 
		 * @throws AppException
		 */
		public TDataLawobjf savesc(ScForm frm) throws AppException, ParseException;


	/**
		 * 获取单个TDataSc
		 * 
		 * @param id
		 *            TDataSc的ID
		 */
		public void editsc(ScForm frm);
		
		/**
		 *通过某个字段获取三产对象
		 */
	   public TDataSc getsc(String lawobjid);
	   
	   /**
	    * 删除三产
	    */
	   public void delSc(String lawobjid);

}

