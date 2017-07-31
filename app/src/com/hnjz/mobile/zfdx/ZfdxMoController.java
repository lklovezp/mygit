package com.hnjz.mobile.zfdx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.hnjz.common.jsonUtils;



@Controller
public class ZfdxMoController {
    
	@Autowired
	ZfdxMoManager zfdxmoManager;
	
	@RequestMapping(value = "/dicData.mo")
	public void alldicData(String strUpdate, ModelMap model) {
		List<Map<String, String>> dicMap =zfdxmoManager.queryDic(strUpdate);
		 model.put("dicMap", dicMap);
	}
	
	@RequestMapping(value = "/lawobjtypeData.mo")
	public void alllawtypeData(String strUpdate, ModelMap model) {
		List<Map<String, String>> lawobjtypeMap =zfdxmoManager.queryLawObjtype(strUpdate);
		 model.put("lawobjtypeMap", lawobjtypeMap);
	}
	
	@RequestMapping(value = "/gywryData.mo")
	public void allgywryData(String strUpdate, ModelMap model) {
		List<Map<String, String>> gywryMap =zfdxmoManager.queryGywry(strUpdate);
		 model.put("gywryMap", gywryMap);
	}
	
	@RequestMapping(value = "/jsxmData.mo")
	public void alljsxmData(String strUpdate, ModelMap model) {
		List<Map<String, String>> jsxmMap =zfdxmoManager.queryJsxm(strUpdate);
		 model.put("jsxmMap", jsxmMap);
	}
	
	@RequestMapping(value = "/yyData.mo")
	public void allyyData(String strUpdate, ModelMap model) {
		List<Map<String, String>> yyMap =zfdxmoManager.queryYy(strUpdate);
		 model.put("yyMap", yyMap);
	}
	
	@RequestMapping(value = "/glData.mo")
	public void allglData(String strUpdate, ModelMap model) {
		List<Map<String, String>> glMap =zfdxmoManager.queryGl(strUpdate);
		 model.put("glMap", glMap);
	}
	
	@RequestMapping(value = "/jzgdData.mo")
	public void alljzgdData(String strUpdate, ModelMap model) {
		List<Map<String, String>> jzgdMap =zfdxmoManager.queryJzgd(strUpdate);
		 model.put("jzgdMap", jzgdMap);
	}
	
	@RequestMapping(value = "/xqyzData.mo")
	public void allxqyzData(String strUpdate, ModelMap model) {
		List<Map<String, String>> xqyzMap =zfdxmoManager.queryXqyz(strUpdate);
		 model.put("xqyzMap", xqyzMap);
	}
	
	@RequestMapping(value = "/scData.mo")
	public void allscData(String strUpdate, ModelMap model) {
		List<Map<String, String>> scMap =zfdxmoManager.querySc(strUpdate);
		 model.put("scMap", scMap);
	}
	
	@RequestMapping(value = "/fwyData.mo")
	public void allfwyData(String strUpdate, ModelMap model) {
		List<Map<String, String>> fwyMap =zfdxmoManager.queryFwy(strUpdate);
		 model.put("fwyMap", fwyMap);
	}
	
	@RequestMapping(value = "/zzyData.mo")
	public void allzzyData(String strUpdate, ModelMap model) {
		List<Map<String, String>> zzyMap =zfdxmoManager.queryZzy(strUpdate);
		 model.put("zzyMap", zzyMap);
	}
	
	@RequestMapping(value = "/ysyData.mo")
	public void allysyData(String strUpdate, ModelMap model) {
		List<Map<String, String>> ysyMap =zfdxmoManager.queryYsy(strUpdate);
		 model.put("ysyMap", ysyMap);
	}
	
	@RequestMapping(value = "/ylyData.mo")
	public void allylyData(String strUpdate, ModelMap model) {
		List<Map<String, String>> ylyMap =zfdxmoManager.queryYly(strUpdate);
		 model.put("ylyMap", ylyMap);
	}
	
	
	@RequestMapping(value = "/jcmbData.mo")
	public void alljcmbData(String strUpdate, ModelMap model) {
		List<Map<String, String>> jcmbMap =zfdxmoManager.queryJcmb(strUpdate);
		 model.put("jcmbMap", jcmbMap);
	}
	
	@RequestMapping(value = "/tbData.mo")
	public void alltbData(String strUpdate, ModelMap model ,String table) throws HibernateException, SQLException {
		List<Map<String, Object>> tbMap =zfdxmoManager.querytaskUser(strUpdate,table);
		for (Map<String, Object> m : tbMap)
	    {
	      for (String k : m.keySet())
	      {
	        if(m.get(k)==null){
	        	m.put(k, "");
	        }
	      }

	    }
		
		System.out.println("GEGGEAGGGEGGGEEFEEQWERFERW 徐"+table);
		model.put("tbMap", tbMap);
	}
	
	@RequestMapping(value = "/gettbData.mo" )
	public void allgettbData(String strUpdate, ModelMap model ,String table, String datas) throws Exception {
		//datas="[ {\"ID_\": \"40288ac45d4e3f9d015d4f2eae9e0010\",\"ISACTIVE_\": \"Y\",\"WORK_NAME_\": \"皇父摄政王多尔衮123\", \"ZXR_NAME_\": \"东华帝君王母娘娘123\" },{\"ID_\": \"12345897\",\"ISACTIVE_\": \"Y\",\"WORK_NAME_\": \"诚敬义皇帝\", \"ZXR_NAME_\": \"八部天龙\" }]";
		//datas="[ {\"ID_\": \"40288ac45d4e3f9d015d4f2eae9e0010\"}]";
		//datas="[{\"ARCHIVES_TIME_\":\"2017-7-17 14:14:05\",\"ARCHIVES_USER_\":\"4028e4484d65d677014d6b492e5f0297\",\"AREAID_\":\"4028e4484d656b9e014d6579282e0008\",\"BLRSFXYBC_\":\"\",\"CODE_\":\"\",\"CREATEBY_\":\"4028e4484d65d677014d6b492e5f0297\",\"CREATED_\":\"2017-7-17 14:14:05\",\"DJRID_\":\"4028e4484d65d677014d6b492e5f0297\",\"EMERGENCY_\":\"1\",\"END_TIME_\":\"2017-8-6 23:59:59\",\"EXTINFO_\":\"\",\"FLOWID_\":\"generalTask1\",\"ID_\":\"40288ac45d4e3f9d015d4f2eae9e0010\",\"ISACTIVE_\":\"Y\",\"ISXP_\":\"0\",\"IS_OVER_\":\"1\",\"JLR_ID_\":\"\",\"JSRIDS_\":\"\",\"JSR_\":\"4028e4484d65d677014d6b492e5f0297\",\"NEXT_ACTIONS_\":\"sh,\",\"NEXT_OPER_\":\"\",\"PID_\":\"\",\"PROCESS_ID_\":\"\",\"PSYJ_\":\"12312312\",\"PTR_IDS_\":\"\",\"PTR_NAMES_\":\"\",\"RWMCRQ_\":\"2017-7-17 14:14:05\",\"SBR_\":\"\",\"SBSJ_\":\"\",\"SECURITY_\":\"1\",\"SFDB_\":\"\",\"SHILIID_\":\"HZ288ac45d4dfd71015d4f2eb1bc0213\",\"SHRIDS_\":\"\",\"SJID_\":\"\",\"SOURCE_\":\"8\",\"START_TIME_\":\"2017-7-17 14:14:05\",\"STATE_\":\"09\",\"TASK_ID_\":\"\",\"THRIDS_\":\"4028e4484d65d677014d6b492e5f0297,\",\"TRACKID_\":\"HZ288ac45d4dfd71015d4f2eb1f20214\",\"UPDATEBY_\":\"4028e4484d65d677014d6b492e5f0297\",\"UPDATED_\":\"2017-7-17 14:14:05\",\"ZXTIME_\":\"2017-7-17 14:14:05\",\"WORKNAMESTYLE_\":\"0\",\"WORK_NAME_\":\"皇父摄政王多尔衮1234\",\"WORK_NOTE_\":\"工业污染源12017年07月17日现场监察\",\"XFBCJSR_\":\"\",\"XFDJBID_\":\"\",\"XPFILEIDS_\":\"\",\"XPPSYJIDS_\":\"\",\"XP_CITY\":\"\",\"ZFDX_TYPE_\":\"1 \",\"ZXR_ID_\":\"4028e4484d65d677014d6b492e5f0297\",\"ZXR_NAME_\":\"东华帝君王母娘娘1234\",\"ZXSTARTTIME_\":\"2017-7-17 14:14:05\",\"VERSION_\":7,\"baseObjId\":50}]";
		System.out.println("GEGGEAGGGEGGGEEFEEQWERFERW 睿123"+table);
		System.out.println("GEGGEAGGGEGGGEEFEEQWERFERW 睿456"+datas);
		zfdxmoManager.getMoTbdatas(table, datas);
	}
	
	@RequestMapping(value = "/tbfileData.mo")
	public void alltbfileData(String strUpdate, ModelMap model ,String userid) throws HibernateException, SQLException {
		System.out.println("GEGGEAGGGEGGGEEFEEQWERFERW 睿456"+userid);
		List<Map<String, Object>> tbfileMap =zfdxmoManager.querytaskByUserid(strUpdate,userid);
		for (Map<String, Object> m : tbfileMap)
	    {
	      for (String k : m.keySet())
	      {
	    	System.out.println("GEGGEAGGGEGGGEEFEEQWERFERW 庶"+k); 
	        if(m.get(k)==null){
	        	m.put(k, "");
	        }
	      }

	    }
		
		System.out.println("GEGGEAGGGEGGGEEFEEQWERFERW 徐"+userid);
		model.put("tbfileMap", tbfileMap);
	}

}
