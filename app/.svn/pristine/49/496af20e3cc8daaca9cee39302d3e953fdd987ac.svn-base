/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.data.xxgl.yearlawobj;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.enums.CxlxEnum;
import com.hnjz.app.data.po.TBizYearLawobj;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.xxgl.lawobj.LawobjManager;
import com.hnjz.app.work.enums.ZfdxLx;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysUser;

/**
 * 年度抽查对象管理类
 * @author shiqiuhan
 * @created 2016-3-10,下午04:16:10
 */
@Service("yearLawobjManagerImpl")
public class YearLawobjManagerImpl extends ManagerImpl implements YearLawobjManager {
    
    @Autowired
    private LawobjManager lawobjManager;

	/**
	 * 查询年度抽查对象
	 * @param lawobjname
	 * @param lawobjtype
	 * @param type
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public FyWebResult queryYearLawobj(String year,String lawobjname, String lawobjtype, String type, String page, String pageSize)
			throws Exception {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append("from TBizYearLawobj where 1=1 and isActive = 'Y'");
		//执法对象名称
		if (StringUtils.isNotBlank(lawobjname)) {
			sql.append(" and lawobjname like :lawobjname ");
			data.putLike("lawobjname", lawobjname);
		}
		//年份
		if (StringUtils.isNotBlank(year)) {
			sql.append(" and year = :year ");
			data.put("year", year);
		}
		//执法对象类型
		if (StringUtils.isNotBlank(lawobjtype)) {
			sql.append(" and lawobjtype = :lawobjtype ");
			data.put("lawobjtype", lawobjtype);
		}
		//抽选类型
		if (StringUtils.isNotBlank(type)) {
			sql.append(" and type = :type ");
			data.put("type", type);
		}
		//所属区域
		String areaid = CtxUtil.getAreaId();
		if(StringUtils.isNotBlank(areaid)){
			sql.append(" and area.id = :areaid ");
			data.put("areaid", areaid);
		}
		sql.append(" order by year,type,lawobjtype,lawobjname ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<TBizYearLawobj> yearLawobjs = pos.getRe();
		Map<String, String> row = null;
		for (TBizYearLawobj ele : yearLawobjs) {
			row = new HashMap<String, String>();
			row.put("id", ele.getId());
			row.put("year", ele.getYear()+"年");
			row.put("lawobjname", ele.getLawobjname());
			row.put("lawobjtype", ZfdxLx.getByCode(ele.getLawobjtype()).getText());
			row.put("type", CxlxEnum.getByCode(ele.getType()).getName());
			row.put("isActive", YnEnum.getNote(ele.getIsActive()));
			row.put("operate", OperateUtil.getOperate(ele.getId()));
			rows.add(row);
		}
		fy.setRows(rows);
		return fy;
	}

	/**
	 * 根据类别查询所有年度抽查对象
	 * @param year
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<TBizYearLawobj> queryAllYearLawobj(String year,String quarter,String type,String areaid)
			throws Exception {
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TBizYearLawobj where isActive = 'Y' ");
		//年份
		if (StringUtils.isNotBlank(year)) {
			sql.append(" and year = :year ");
			data.put("year", year);
		}
		//抽选类型
		if (StringUtils.isNotBlank(type)) {
			sql.append(" and type = :type ");
			data.put("type", type);
		}
		//所属区域
		if(StringUtils.isNotBlank(areaid)){
			sql.append(" and area.id = :areaid ");
			data.put("areaid", areaid);
		}
		sql.append(" order by type ");
		List<TBizYearLawobj> pos = this.dao.find(sql.toString(), data);
		//企业季节性生产,移除不在本季节生产的企业
		// 用iterator 在并发时候防止错误
		/*Iterator<TBizYearLawobj> iter = pos.iterator();
		if(StringUtils.isNotBlank(quarter)){
			while(iter.hasNext()){  
				TBizYearLawobj item = iter.next();
				String sczt = lawobjManager.getScztByLawobj(item.getLawobj());
				if(StringUtils.contains(sczt, "N")){//季节性生产
					if(StringUtils.equals(quarter, QuarterEnum.first_quarter.getCode()) && !StringUtils.contains(sczt, "01") && !StringUtils.contains(sczt, "02") && !StringUtils.contains(sczt, "03")){//第一季度抽选，移除第一季度尚未生产的企业
						iter.remove(); //移除
					}else if(StringUtils.equals(quarter, QuarterEnum.second_quarter.getCode()) && !StringUtils.contains(sczt, "04") && !StringUtils.contains(sczt, "05") && !StringUtils.contains(sczt, "06")){//第二季度抽选，移除第二季度尚未生产的企业
						iter.remove(); //移除
					}else if(StringUtils.equals(quarter, QuarterEnum.third_quarter.getCode()) && !StringUtils.contains(sczt, "07") && !StringUtils.contains(sczt, "08") && !StringUtils.contains(sczt, "09")){//第三季度抽选，移除第三季度尚未生产的企业
						iter.remove(); //移除
					}else if(StringUtils.equals(quarter, QuarterEnum.fourth_quarter.getCode()) && !StringUtils.contains(sczt, "10") && !StringUtils.contains(sczt, "11") && !StringUtils.contains(sczt, "12")){//第四季度抽选，移除第四季度尚未生产的企业
						iter.remove(); //移除
					}
				}
			}
		}*/
		return pos;
	}
	
	/**
	 * 本年度常规抽选时没有被抽查的污染源
	 * @param year
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<TBizYearLawobj> queryNoCheckedLawobj(String year,String quarter,String type,String areaid)
			throws Exception{
		Map<String, Object> condition = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select y.id_ from T_Biz_YearLawobj y where y.isActive_ = 'Y' ");
		//年份
		if (StringUtils.isNotBlank(year)) {
			sql.append(" and y.year_ = :year ");
			condition.put("year", year);
		}
		//抽选类型
		if (StringUtils.isNotBlank(type)) {
			sql.append(" and y.type_ = :type ");
			condition.put("type", type);
			//重点企业
			if(CxlxEnum.zdqy.getCode().equals(type)){
				sql.append(" and  y.LAWOBJID_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='0' and c.year_ = :year1)");
			}else if(CxlxEnum.ybqy.getCode().equals(type)){
				sql.append(" and  y.LAWOBJID_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
			}else if(CxlxEnum.tsqy.getCode().equals(type)){
				sql.append(" and  y.LAWOBJID_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='3' and c.year_ = :year1)");
			}
			condition.put("year1", year);
		}
		//所属区域
		if(StringUtils.isNotBlank(areaid)){
			sql.append(" and y.areaid_ = :areaid ");
			condition.put("areaid", areaid);
		}
		sql.append(" order by y.type_ ");
		List<Object[]> list = this.dao.findBySql(sql.toString(),condition);
		List<TBizYearLawobj> pos = new ArrayList<TBizYearLawobj>();
		for(int i =0; i<list.size(); i++){
			TBizYearLawobj lawobj = new TBizYearLawobj();
			if(StringUtils.isNotBlank(String.valueOf(list.get(i)))){
				lawobj = (TBizYearLawobj) this.get(TBizYearLawobj.class, String.valueOf(list.get(i)));
			}
			pos.add(lawobj);
		}
		return pos;
	}
	
	/**
	 * 批量保存年度抽查对象
	 * @throws Exception 
	 */
	public void saveYearLawobj(String ids,String names,String year) throws Exception {
		TSysUser user = CtxUtil.getCurUser();
		if(StringUtils.isNotBlank(ids)){
			
			String [] idList = ids.split(",");
			String [] nameList = names.split(",");
			
			TBizYearLawobj po = new TBizYearLawobj();
			TDataLawobj lawobj = new TDataLawobj();
			Date cur = new Date();
			
			for(int i=0;i<idList.length;i++){
				po = new TBizYearLawobj();
				lawobj = (TDataLawobj)this.get(TDataLawobj.class, idList[i]);//执法对象
				po.setArea((TSysArea)this.get(TSysArea.class, CtxUtil.getAreaId()));//区域
				po.setLawobj(lawobj);//被抽查执法对象id
				po.setLawobjtype(lawobj.getLawobjtype());//执法对象类型
				po.setLawobjname(nameList[i]);//执法对象名称
				if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.GYWRY.getCode())){//控制类型为重点企业的设置抽选类型为重点
					if(StringUtils.isBlank(lawobjManager.getKzlxByLawobj(lawobj)) || StringUtils.equals(lawobjManager.getKzlxByLawobj(lawobj), "4")){
						po.setType(CxlxEnum.ybqy.getCode());//抽选类型：一般企业
					}else{
						po.setType(CxlxEnum.zdqy.getCode());//抽选类型：重点企业
					}
					
				}else{
					po.setType(CxlxEnum.ybqy.getCode());//抽选类型：一般企业
				}
				po.setYear(year);
				po.setCreateby(user);
				po.setCreated(cur);
				po.setIsActive(YnEnum.Y.getCode());
				po.setUpdateby(user);
				po.setUpdated(cur);
				this.dao.save(po);
			}
		}
	}

	/**
	 * 删除年度抽查对象
	 * @param id
	 * @throws AppException
	 */
	public void removeYearLawobj(String id) throws AppException {
		TBizYearLawobj del = (TBizYearLawobj) this.dao.get(TBizYearLawobj.class, id);
		del.setIsActive(YnEnum.N.getCode());
		this.dao.save(del);
	}
	
	
	/**
	 * 该年度是否已完成抽选
	 * @param year
	 * @return
	 * @throws AppException
	 */
	public boolean isChecked(String year) throws AppException{
		String areaid = CtxUtil.getAreaId();
		List<TBizYearLawobj> pos = dao.find("from TBizYearLawobj where isActive = 'Y' and year = ? and area.id = ?",year,areaid);
		if(pos!=null&& pos.size()>0){
			return true;
		}else
			return false;
	}
	
	/**
	 * 保存年度抽查对象列表
	 * @param checkedList
	 * @param year
	 * @throws Exception 
	 */
	public void saveYearLawobjList(List <TDataLawobj> checkedList, String year ,String cxlx) throws Exception{
		TSysUser user = CtxUtil.getCurUser();
		if(checkedList!=null && checkedList.size()>0){
			
			TBizYearLawobj po = new TBizYearLawobj();
			TDataLawobj lawobj = new TDataLawobj();
			Date cur = new Date();
			
			for(int i=0;i<checkedList.size();i++){
				po = new TBizYearLawobj();
				lawobj = checkedList.get(i);//执法对象
				po.setArea((TSysArea)this.get(TSysArea.class, CtxUtil.getAreaId()));//区域
				po.setLawobj(lawobj);//被抽查执法对象id
				po.setLawobjtype(lawobj.getLawobjtype());//执法对象类型
				po.setLawobjname(lawobjManager.getNameByLawobj(lawobj));//执法对象名称
				po.setType(cxlx);//抽选类型
				po.setYear(year);
				po.setCreateby(user);
				po.setCreated(cur);
				po.setIsActive(YnEnum.Y.getCode());
				po.setUpdateby(user);
				po.setUpdated(cur);
				this.dao.save(po);
			}
		}
	}
	
	/**
	 * 
	 * 函数介绍：抽选类型下拉列表
	 */
	public List<Combobox> queryCxlxList(){
		return CxlxEnum.getCxlxEnumList();
	}
}
