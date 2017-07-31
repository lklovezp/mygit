/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.mobile.sjtb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hnjz.common.manager.ManagerImpl;

/**
 * 数据同步的manager
 * 
 * @author zhangqingfeng
 * @version $Id: SjtbManagerImpl.java, v 0.1 2017-6-13 下午03:28:05
 *          Exp $
 */
@Service("sjtbMoManagerImpl")
public class sjtbMoManagerImpl extends ManagerImpl implements
		sjtbMoManager {

	/** 日志 */
	private static final Log log = LogFactory.getLog(sjtbMoManagerImpl.class);
	
	@Override
	public List<Map<String, String>> getTaskType(String strUpdate) {
		// TODO Auto-generated method stub
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select t.id_ , t.code_, t.name_, t.pid_, t.dotemplateid_, t.orderby_, t.desc_, t.isactive_, t.version_, t.updated_, t.updateby_, t.created_, t.createby_ from t_data_tasktype t where 1=1 and  t.isactive_ = 'Y' ");
		if(StringUtils.isNotBlank(strUpdate)){
			sql.append(" and t.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
			map.put("updated", strUpdate);
		}
		List<Object[]> list = dao.findBySql(sql.toString(), map);
		Map<String, String> dataObject = null;
		if(list.size() > 0){
			for (Object[] ele : list) {
				dataObject = new HashMap<String, String>();
				dataObject.put("ids", String.valueOf(ele[0]));
				dataObject.put("code", String.valueOf(ele[1]));
				dataObject.put("name", String.valueOf(ele[2]));
				dataObject.put("pid", String.valueOf(ele[3]));
				dataObject.put("dotemplateid", String.valueOf(ele[4]));
				dataObject.put("orderby", String.valueOf(ele[5]));
				dataObject.put("desc", String.valueOf(ele[6]));
				dataObject.put("isactive", String.valueOf(ele[7]));
				dataObject.put("version", String.valueOf(ele[8]));
				dataObject.put("updated", String.valueOf(ele[9])+" 23:59:59");
				dataObject.put("updateby", String.valueOf(ele[10]));
				dataObject.put("created", String.valueOf(ele[11]));
				dataObject.put("createby", String.valueOf(ele[12]));
				rows.add(dataObject);
			}
		}
		return rows;
	}
	
	@Override
	public List<Map<String, String>> queryAllLawobj(String strUpdate) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder sql=new StringBuilder();
		sql.append("select l.id_,l.jxsmid_,l.isold_,l.lawobjtypeid_,l.dwmc_,l.dwdz_,l.fddbr_,l.fddbrdh_,l.hbfzr_,l.hbfzrdh_,l.cjr_,l.ssjgbm_,l.ssxzq_,l.sswgqy_,l.jd_,l.wd_,l.qysczt_,l.qyzt_,l.isactive_,l.cjsj_ from t_Data_Lawobjf l where 1=1 ");
		if(StringUtils.isNotBlank(strUpdate)){
			sql.append(" and l.updated > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
			map.put("updated", strUpdate);
		}
		List<Object[]> list=dao.findBySql(sql.toString(), map);
		Map<String,String> dataObject=null;
		if(list.size() > 0){
			for(Object[] ele : list){
				dataObject= new HashMap<String,String>();
				dataObject.put("ids", String.valueOf(ele[0]));
				dataObject.put("jsxmid", String.valueOf(ele[1]));
				dataObject.put("isold", String.valueOf(ele[2]));
				dataObject.put("lawobjtype", String.valueOf(ele[3]));
				dataObject.put("dwmc", String.valueOf(ele[4]));
				dataObject.put("dwdz", String.valueOf(ele[5]));
				dataObject.put("fddbr", String.valueOf(ele[6]));
				dataObject.put("fddbrdh", String.valueOf(ele[7]));
				dataObject.put("hbfzr", String.valueOf(ele[8]));
				dataObject.put("hbfzrdh", String.valueOf(ele[9]));
				dataObject.put("cjr", String.valueOf(ele[10]));
				dataObject.put("ssjgbm", String.valueOf(ele[11]));
				dataObject.put("ssxzq", String.valueOf(ele[12]));
				dataObject.put("sswgqy", String.valueOf(ele[13]));
				dataObject.put("jd", String.valueOf(ele[14]));
				dataObject.put("wd", String.valueOf(ele[15]));
				dataObject.put("qysczt", String.valueOf(ele[16]));
				dataObject.put("qyzt", String.valueOf(ele[17]));
				dataObject.put("isactive", String.valueOf(ele[18]));
				dataObject.put("cjsj", String.valueOf(ele[19])+" 23:59:59");
				rows.add(dataObject);
			}
		}
		return rows;
	}

	@Override
	public List<Map<String, String>> queryLawobjtype(String strUpdate) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder sql= new StringBuilder();
		sql.append("select t.id_,t.name_,t.code_,t.updated_,t.type_ from t_sys_dic t where t.type_='5' or t.type_='4' or t.type_='12'");
		if(StringUtils.isNotBlank(strUpdate)){
			sql.append(" and t.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
			map.put("updated", strUpdate);
		}
		List<Object[]> list=dao.findBySql(sql.toString(), map);
		Map<String,String> dataObject=null;
		if(list.size() > 0){
			for(Object[] ele: list){
				dataObject= new HashMap<String,String>();
				dataObject.put("ids", String.valueOf(ele[0]));
				dataObject.put("name", String.valueOf(ele[1]));
				dataObject.put("code", String.valueOf(ele[2]));
				dataObject.put("updated", String.valueOf(ele[3])+" 23:59:59");
				dataObject.put("type", String.valueOf(ele[4]));
				rows.add(dataObject);
			}
		}
		return rows;
	}
	
	@Override
	public List<Map<String, String>> queryAllUser(String strUpdate) {
		// TODO Auto-generated method stub
		List<Map<String, String>>  rows= new ArrayList<Map<String, String>>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder sql=new StringBuilder();
		sql.append("select u.name_,u.id_,u.username_,u.password_,u.workmobile_,u.personmobile_,u.lawnumber_,u.issys_,u.biztype_,u.areaid_,u.updated_,u.isactive_ from t_sys_user u where 1=1 ");
		if(StringUtils.isNotBlank(strUpdate)){
			sql.append(" and u.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
			map.put("updated", strUpdate);
		}
		List<Object[]> list = dao.findBySql(sql.toString());
		Map<String,String> dataObject=null;
		if(list.size() > 0){
			for(Object[] ele : list){
				dataObject=new HashMap<String,String>();
				dataObject.put("name", String.valueOf(ele[0]));
				dataObject.put("ids", String.valueOf(ele[1]));
				dataObject.put("username", String.valueOf(ele[2]));
				dataObject.put("password", String.valueOf(ele[3]));
				dataObject.put("workmobile", String.valueOf(ele[4]));
				dataObject.put("personmobile", String.valueOf(ele[5]));
				dataObject.put("lawnumber", String.valueOf(ele[6]));
				dataObject.put("issys", String.valueOf(ele[7]));
				dataObject.put("biztype", String.valueOf(ele[8]));
				dataObject.put("areaid", String.valueOf(ele[9]));
				dataObject.put("updated", String.valueOf(ele[10])+" 23:59:59");
				dataObject.put("isactive", String.valueOf(ele[11]));
				rows.add(dataObject);
			}
		}
		return rows;
	}
	
	@Override
	public ArrayList<String> queryAllUserList(String strUpdate) {
		// TODO Auto-generated method stub
		ArrayList<String>  rows= new ArrayList<String>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder sql=new StringBuilder();
		sql.append("select u.name_,u.id_,u.username_,u.password_,u.workmobile_,u.personmobile_,u.lawnumber_,u.issys_,u.biztype_,u.areaid_,u.updated_,u.isactive_ from t_sys_user u where 1=1 ");
		if(StringUtils.isNotBlank(strUpdate)){
			sql.append(" and u.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
			map.put("updated", strUpdate);
		}
		List<Object[]> list = dao.findBySql(sql.toString());
		Map<String,String> dataObject=null;
		if(list.size() > 0){
			for(Object[] ele : list){
				dataObject=new HashMap<String,String>();
				dataObject.put("name", String.valueOf(ele[0]));
				dataObject.put("ids", String.valueOf(ele[1]));
				dataObject.put("username", String.valueOf(ele[2]));
				dataObject.put("password", String.valueOf(ele[3]));
				dataObject.put("workmobile", String.valueOf(ele[4]));
				dataObject.put("personmobile", String.valueOf(ele[5]));
				dataObject.put("lawnumber", String.valueOf(ele[6]));
				dataObject.put("issys", String.valueOf(ele[7]));
				dataObject.put("biztype", String.valueOf(ele[8]));
				dataObject.put("areaid", String.valueOf(ele[9]));
				dataObject.put("updated", String.valueOf(ele[10])+" 23:59:59");
				dataObject.put("isactive", String.valueOf(ele[11]));
				rows.add(dataObject.toString());
			}
		}
		return rows;
	}
	
	@Override
	public List<Map<String, String>> queryallDataFile(String strUpdate) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder sql=new StringBuilder();
		sql.append("select t.id_,t.pid_,t.osname_,t.name_,t.type_,t.isactive_,t.updated_ from t_Data_file t where 1=1 ");
		if(StringUtils.isNotBlank(strUpdate)){
			sql.append(" and t.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
			map.put("updated", strUpdate);
		}
		List<Object[]> list=dao.findBySql(sql.toString(), map);
		Map<String,String> dataObject=null;
		if(list.size() > 0){
			for(Object[] ele : list){
				dataObject= new HashMap<String,String>();
				dataObject.put("ids", String.valueOf(ele[0]));
				dataObject.put("pid", String.valueOf(ele[1]));
				dataObject.put("osname", String.valueOf(ele[2]));
				dataObject.put("name", String.valueOf(ele[3]));
				dataObject.put("type", String.valueOf(ele[4]));
				dataObject.put("isactive", String.valueOf(ele[5]));
				dataObject.put("updated", String.valueOf(ele[6])+" 23:59:59");
				rows.add(dataObject);
			}
		}
		return rows;
	}
	
	@Override
	public List<Map<String, String>> queryallDataArea(String strUpdate) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder sql=new StringBuilder();
		sql.append("select t.id_,t.pid_,t.name_,t.type_,t.unitname_,t.code_,t.isactive_,t.updated_ from t_sys_area t where 1=1 ");
		if(StringUtils.isNotBlank(strUpdate)){
			sql.append(" and t.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
			map.put("updated", strUpdate);
		}
		List<Object[]> list=dao.findBySql(sql.toString(), map);
		Map<String,String> dataObject=null;
		if(list.size() > 0){
			for(Object[] ele : list){
				dataObject= new HashMap<String,String>();
				dataObject.put("ids", String.valueOf(ele[0]));
				dataObject.put("pid", String.valueOf(ele[1]));
				dataObject.put("name", String.valueOf(ele[2]));
				dataObject.put("type", String.valueOf(ele[3]));
				dataObject.put("unitname", String.valueOf(ele[4]));
				dataObject.put("code", String.valueOf(ele[5]));
				dataObject.put("isactive", String.valueOf(ele[6]));
				dataObject.put("updated", String.valueOf(ele[7])+" 23:59:59");
				rows.add(dataObject);
			}
		}
		return rows;
	}
	
	@Override
	public List<Map<String, String>> queryallDataRegion(String strUpdate) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder sql=new StringBuilder();
		sql.append("select t.id_,t.pid_,t.name_,t.type_,t.isactive_,t.updated_ from t_data_region t where 1=1 ");
		if(StringUtils.isNotBlank(strUpdate)){
			sql.append(" and t.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
			map.put("updated", strUpdate);
		}
		List<Object[]> list=dao.findBySql(sql.toString(), map);
		Map<String,String> dataObject=null;
		if(list.size() > 0){
			for(Object[] ele : list){
				dataObject= new HashMap<String,String>();
				dataObject.put("ids", String.valueOf(ele[0]));
				dataObject.put("pid", String.valueOf(ele[1]));
				dataObject.put("name", String.valueOf(ele[2]));
				dataObject.put("type", String.valueOf(ele[3]));
				dataObject.put("isactive", String.valueOf(ele[4]));
				dataObject.put("updated", String.valueOf(ele[5])+" 23:59:59");
				rows.add(dataObject);
			}
		}
		return rows;
	}
	
	@Override
	public List<Map<String, String>> queryallLawdocData(String strUpdate) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder sql=new StringBuilder();
		sql.append("select t.id_,t.name_,t.dirid_,t.fileid_,t.keywords_,t.isactive_,t.updated_ from t_data_lawdoc t where 1=1 ");
		if(StringUtils.isNotBlank(strUpdate)){
			sql.append(" and t.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
			map.put("updated", strUpdate);
		}
		List<Object[]> list=dao.findBySql(sql.toString(), map);
		Map<String,String> dataObject=null;
		if(list.size() > 0){
			for(Object[] ele : list){
				dataObject= new HashMap<String,String>();
				dataObject.put("ids", String.valueOf(ele[0]));
				dataObject.put("name", String.valueOf(ele[1]));
				dataObject.put("dirid", String.valueOf(ele[2]));
				dataObject.put("fileid", String.valueOf(ele[3]));
				dataObject.put("keywords", String.valueOf(ele[4]));
				dataObject.put("isactive", String.valueOf(ele[5]));
				dataObject.put("updated", String.valueOf(ele[6])+" 23:59:59");
				rows.add(dataObject);
			}
		}
		return rows;
	}
	
	@Override
	public List<Map<String, String>> queryallRoleData(String strUpdate) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder sql=new StringBuilder();
		sql.append("select t.id_,t.name_,t.issys_,t.isactive_,t.updated_,t.desc_ from t_sys_role t where 1=1 ");
		if(StringUtils.isNotBlank(strUpdate)){
			sql.append(" and t.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
			map.put("updated", strUpdate);
		}
		List<Object[]> list=dao.findBySql(sql.toString(), map);
		Map<String,String> dataObject=null;
		if(list.size() > 0){
			for(Object[] ele : list){
				dataObject= new HashMap<String,String>();
				dataObject.put("ids", String.valueOf(ele[0]));
				dataObject.put("name", String.valueOf(ele[1]));
				dataObject.put("issys", String.valueOf(ele[2]));
				dataObject.put("isactive", String.valueOf(ele[3]));
				dataObject.put("describe", String.valueOf(ele[5]));
				dataObject.put("updated", String.valueOf(ele[4])+" 23:59:59");
				rows.add(dataObject);
			}
		}
		return rows;
	}
	
	@Override
	public List<Map<String, String>> queryallOrgData(String strUpdate) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder sql=new StringBuilder();
		sql.append("select t.id_,t.name_,t.pid_,t.unitname_,t.type_,t.leader1_,t.leader2_,t.areaid_,t.biztype_,t.isactive_,t.updated_ from t_sys_org t where 1=1 ");
		if(StringUtils.isNotBlank(strUpdate)){
			sql.append(" and t.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
			map.put("updated", strUpdate);
		}
		List<Object[]> list=dao.findBySql(sql.toString(), map);
		Map<String,String> dataObject=null;
		if(list.size() > 0){
			for(Object[] ele : list){
				dataObject= new HashMap<String,String>();
				dataObject.put("ids", String.valueOf(ele[0]));
				dataObject.put("name", String.valueOf(ele[1]));
				dataObject.put("pid", String.valueOf(ele[2]));
				dataObject.put("unitname", String.valueOf(ele[3]));
				dataObject.put("type", String.valueOf(ele[4]));
				dataObject.put("leader1", String.valueOf(ele[5]));
				dataObject.put("leader2", String.valueOf(ele[6]));
				dataObject.put("areaid", String.valueOf(ele[7]));
				dataObject.put("biztype", String.valueOf(ele[8]));
				dataObject.put("isactive", String.valueOf(ele[9]));
				dataObject.put("updated", String.valueOf(ele[10])+" 23:59:59");
				rows.add(dataObject);
			}
		}
		return rows;
	}
	
	@Override
	public List<Map<String, String>> queryallIndustryData(String strUpdate) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder sql=new StringBuilder();
		sql.append("select t.id_,t.name_,t.pid_,t.lawobjtype_,t.tolawobjtype_,t.isactive_,t.code_,t.updated_ from t_data_industry t where 1=1 ");
		if(StringUtils.isNotBlank(strUpdate)){
			sql.append(" and t.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
			map.put("updated", strUpdate);
		}
		List<Object[]> list=dao.findBySql(sql.toString(), map);
		Map<String,String> dataObject=null;
		if(list.size() > 0){
			for(Object[] ele : list){
				dataObject= new HashMap<String,String>();
				dataObject.put("ids", String.valueOf(ele[0]));
				dataObject.put("name", String.valueOf(ele[1]));
				dataObject.put("pid", String.valueOf(ele[2]));
				dataObject.put("lawobjtype", String.valueOf(ele[3]));
				dataObject.put("tolawobjtype", String.valueOf(ele[4]));
				dataObject.put("isactive", String.valueOf(ele[5]));
				dataObject.put("code", String.valueOf(ele[6]));
				dataObject.put("updated", String.valueOf(ele[7])+" 23:59:59");
				rows.add(dataObject);
			}
		}
		return rows;
	}
	
	@Override
	public List<Map<String, String>> queryallIllegaltypeData(String strUpdate) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder sql=new StringBuilder();
		sql.append("select t.id_,t.name_,t.pid_,t.type_,t.desc_,t.isactive_,t.updated_ from t_data_illegaltype t where 1=1 ");
		if(StringUtils.isNotBlank(strUpdate)){
			sql.append(" and t.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
			map.put("updated", strUpdate);
		}
		List<Object[]> list=dao.findBySql(sql.toString(), map);
		Map<String,String> dataObject=null;
		if(list.size() > 0){
			for(Object[] ele : list){
				dataObject= new HashMap<String,String>();
				dataObject.put("ids", String.valueOf(ele[0]));
				dataObject.put("name", String.valueOf(ele[1]));
				dataObject.put("pid", String.valueOf(ele[2]));
				dataObject.put("type", String.valueOf(ele[3]));
				dataObject.put("desc", String.valueOf(ele[4]));
				dataObject.put("isactive", String.valueOf(ele[5]));
				dataObject.put("updated", String.valueOf(ele[6])+" 23:59:59");
				rows.add(dataObject);
			}
		}
		return rows;
	}
	
	@Override
	public List<Map<String, String>> queryAllUserOrg(String strUpdate) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		StringBuilder sql=new StringBuilder();
		sql.append("select t.id_,t.userid_,t.orgid_ from t_sys_userorg t where 1=1 ");
		List<Object[]> list=dao.findBySql(sql.toString());
		Map<String,String> dataObject=null;
		for(Object[] ele : list){
			dataObject= new HashMap<String,String>();
			dataObject.put("ids", String.valueOf(ele[0]));
			dataObject.put("userid", String.valueOf(ele[1]));
			dataObject.put("orgid", String.valueOf(ele[2]));
			rows.add(dataObject);
		}
		return rows;
	}
	
	@Override
	public List<Map<String, String>> queryAllUserRole(String strUpdate) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		StringBuilder sql=new StringBuilder();
		sql.append("select t.id_,t.userid_,t.roleid_ from t_sys_userrole t where 1=1 ");
		List<Object[]> list=dao.findBySql(sql.toString());
		Map<String,String> dataObject=null;
		if(list.size() > 0){
			for(Object[] ele : list){
				dataObject= new HashMap<String,String>();
				dataObject.put("ids", String.valueOf(ele[0]));
				dataObject.put("userid", String.valueOf(ele[1]));
				dataObject.put("roleid", String.valueOf(ele[2]));
				rows.add(dataObject);
			}
		}
		return rows;
	}
	
	@Override
	public List<Map<String, String>> queryallDbrwData(String strUpdate) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder sql=new StringBuilder();
		sql.append("select t.id_,t.name_,t.pid_,t.type_,t.desc_,t.isactive_,t.updated_ from V_DBWORK t where 1=1 ");
		if(StringUtils.isNotBlank(strUpdate)){
			sql.append(" and t.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
			map.put("updated", strUpdate);
		}
		List<Object[]> list=dao.findBySql(sql.toString(), map);
		Map<String,String> dataObject=null;
		if(list.size() > 0){
			for(Object[] ele : list){
				dataObject= new HashMap<String,String>();
				dataObject.put("ids", String.valueOf(ele[0]));
				dataObject.put("name", String.valueOf(ele[1]));
				dataObject.put("pid", String.valueOf(ele[2]));
				dataObject.put("type", String.valueOf(ele[3]));
				dataObject.put("desc", String.valueOf(ele[4]));
				dataObject.put("isactive", String.valueOf(ele[5]));
				dataObject.put("updated", String.valueOf(ele[6])+" 23:59:59");
				rows.add(dataObject);
			}
		}
		return rows;
	}
	
	@Override
	public List<Map<String, String>> queryallDataRecordData(String strUpdate) {
		// TODO Auto-generated method stub
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder sql=new StringBuilder();
		sql.append("select t.id_,t.content_,t.pid_,t.tsnr_,t.desc_,t.tasktypeid_,t.wflxid_,t.kcxwbj_,t.isdel_,t.iscurver_,t.iszdjz_,t.isactive_,t.updated_ from t_data_record t where 1=1 ");
		if(StringUtils.isNotBlank(strUpdate)){
			sql.append(" and t.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
			map.put("updated", strUpdate);
		}
		List<Object[]> list=dao.findBySql(sql.toString(), map);
		Map<String,String> dataObject=null;
		if(list.size() > 0){
			for(Object[] ele : list){
				dataObject= new HashMap<String,String>();
				dataObject.put("ids", String.valueOf(ele[0]));
				dataObject.put("content", String.valueOf(ele[1]));
				dataObject.put("pid", String.valueOf(ele[2]));
				dataObject.put("tsnr", String.valueOf(ele[3]));
				dataObject.put("desc", String.valueOf(ele[4]));
				dataObject.put("tasktypeid", String.valueOf(ele[5]));
				dataObject.put("wflxid", String.valueOf(ele[6]));
				dataObject.put("kcxwbj", String.valueOf(ele[7]));
				dataObject.put("isdel", String.valueOf(ele[8]));
				dataObject.put("iscurver", String.valueOf(ele[9]));
				dataObject.put("iszdjz", String.valueOf(ele[10]));
				dataObject.put("isactive", String.valueOf(ele[11]));
				dataObject.put("updated", String.valueOf(ele[12])+" 23:59:59");
				rows.add(dataObject);
			}
		}
		return rows;
	}
	
	/**
	 * 
	 * 函数介绍：判断是否同步公共方法
	 * 输入参数：
	 * 返回值：
	 */
	public List<Map<String, String>> TableDataIsSynch(String strUpdated, String tablename) {
		List<Map<String,String>> rows=new ArrayList<Map<String,String>>();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String,String> dataObject=new HashMap<String, String>();
		try {
			StringBuffer sql = new StringBuffer("select id_ from ").append(tablename).append(" d where 1=1 ");
			if (StringUtils.isNotBlank(strUpdated)) {
				sql.append(" and d.updated_ > TO_DATE( :updated , 'yyyy-MM-dd hh24:mi:ss')");
				map.put("updated", strUpdated);
			}
			List<Object[]> list=dao.findBySql(sql.toString(), map);
			if (list.size() > 0) {
				dataObject.put("result", "Y");
				dataObject.put("table", tablename);
			} else {
				dataObject.put("result", "N");
				dataObject.put("table", tablename);
			}
			rows.add(dataObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	
}
