package com.hnjz.mobile.zfdx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

public interface ZfdxMoManager {
	/**
	 * 执法对象父表数据
	 */
	public List<Map<String,String>> queryLawobjf(String update);
	
	/**
	 * 执法对象工业污染源
	 */
	public List<Map<String,String>> queryGywry(String update);
	/**
	 * 执法对象建设项目
	 */
	public List<Map<String,String>> queryJsxm(String update);
	/**
	 * 执法对象医院
	 */
	public List<Map<String,String>> queryYy(String update);
	/**
	 * 执法对象锅炉
	 */
	public List<Map<String,String>> queryGl(String update);
	/**
	 * 执法对象建筑工地
	 */
	public List<Map<String,String>> queryJzgd(String update);
	/**
	 * 执法对象畜禽养殖
	 */
	public List<Map<String,String>> queryXqyz(String update);
	/**
	 * 执法对象三产
	 */
	public List<Map<String,String>> querySc(String update);
	/**
	 * 执法对象工业污染源
	 */
	public List<Map<String,String>> queryFwy(String update);
	
	/**
	 * 执法对象工业污染源
	 */
	public List<Map<String,String>> queryYsy(String update);
	/**
	 * 执法对象工业污染源
	 */
	public List<Map<String,String>> queryZzy(String update);
	/**
	 * 执法对象工业污染源
	 */
	public List<Map<String,String>> queryYly(String update);
	
    /**
     * 字典表
     */
	public List<Map<String,String>> queryDic(String update);
	
	/**
	 * 执法对象类型
	 */
	public List<Map<String,String>> queryLawObjtype(String update);
	
	/**
	 * 检查模板表
	 */
	public List<Map<String,String>> queryJcmb(String update);   
	
	/**
	 * 查询任务人员表
	 * @param update
	 * @return
	 */
	public List querytaskUser(String update,String table)throws HibernateException, SQLException;
	
	/**
	 * 获取手机端数据同步到数据库中
	 */
   public void getMoTbdatas(String table,String datas)throws Exception;
   
   /**
    * 根据接收人信息同步某个人的待办任务列表
    */
   public List 	querytaskByUserid(String update,String userid)throws HibernateException, SQLException;
}
