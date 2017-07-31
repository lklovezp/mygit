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
 * ��ȳ����������
 * @author shiqiuhan
 * @created 2016-3-10,����04:16:10
 */
@Service("yearLawobjManagerImpl")
public class YearLawobjManagerImpl extends ManagerImpl implements YearLawobjManager {
    
    @Autowired
    private LawobjManager lawobjManager;

	/**
	 * ��ѯ��ȳ�����
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
		//ִ����������
		if (StringUtils.isNotBlank(lawobjname)) {
			sql.append(" and lawobjname like :lawobjname ");
			data.putLike("lawobjname", lawobjname);
		}
		//���
		if (StringUtils.isNotBlank(year)) {
			sql.append(" and year = :year ");
			data.put("year", year);
		}
		//ִ����������
		if (StringUtils.isNotBlank(lawobjtype)) {
			sql.append(" and lawobjtype = :lawobjtype ");
			data.put("lawobjtype", lawobjtype);
		}
		//��ѡ����
		if (StringUtils.isNotBlank(type)) {
			sql.append(" and type = :type ");
			data.put("type", type);
		}
		//��������
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
			row.put("year", ele.getYear()+"��");
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
	 * ��������ѯ������ȳ�����
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
		//���
		if (StringUtils.isNotBlank(year)) {
			sql.append(" and year = :year ");
			data.put("year", year);
		}
		//��ѡ����
		if (StringUtils.isNotBlank(type)) {
			sql.append(" and type = :type ");
			data.put("type", type);
		}
		//��������
		if(StringUtils.isNotBlank(areaid)){
			sql.append(" and area.id = :areaid ");
			data.put("areaid", areaid);
		}
		sql.append(" order by type ");
		List<TBizYearLawobj> pos = this.dao.find(sql.toString(), data);
		//��ҵ����������,�Ƴ����ڱ�������������ҵ
		// ��iterator �ڲ���ʱ���ֹ����
		/*Iterator<TBizYearLawobj> iter = pos.iterator();
		if(StringUtils.isNotBlank(quarter)){
			while(iter.hasNext()){  
				TBizYearLawobj item = iter.next();
				String sczt = lawobjManager.getScztByLawobj(item.getLawobj());
				if(StringUtils.contains(sczt, "N")){//����������
					if(StringUtils.equals(quarter, QuarterEnum.first_quarter.getCode()) && !StringUtils.contains(sczt, "01") && !StringUtils.contains(sczt, "02") && !StringUtils.contains(sczt, "03")){//��һ���ȳ�ѡ���Ƴ���һ������δ��������ҵ
						iter.remove(); //�Ƴ�
					}else if(StringUtils.equals(quarter, QuarterEnum.second_quarter.getCode()) && !StringUtils.contains(sczt, "04") && !StringUtils.contains(sczt, "05") && !StringUtils.contains(sczt, "06")){//�ڶ����ȳ�ѡ���Ƴ��ڶ�������δ��������ҵ
						iter.remove(); //�Ƴ�
					}else if(StringUtils.equals(quarter, QuarterEnum.third_quarter.getCode()) && !StringUtils.contains(sczt, "07") && !StringUtils.contains(sczt, "08") && !StringUtils.contains(sczt, "09")){//�������ȳ�ѡ���Ƴ�����������δ��������ҵ
						iter.remove(); //�Ƴ�
					}else if(StringUtils.equals(quarter, QuarterEnum.fourth_quarter.getCode()) && !StringUtils.contains(sczt, "10") && !StringUtils.contains(sczt, "11") && !StringUtils.contains(sczt, "12")){//���ļ��ȳ�ѡ���Ƴ����ļ�����δ��������ҵ
						iter.remove(); //�Ƴ�
					}
				}
			}
		}*/
		return pos;
	}
	
	/**
	 * ����ȳ����ѡʱû�б�������ȾԴ
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
		//���
		if (StringUtils.isNotBlank(year)) {
			sql.append(" and y.year_ = :year ");
			condition.put("year", year);
		}
		//��ѡ����
		if (StringUtils.isNotBlank(type)) {
			sql.append(" and y.type_ = :type ");
			condition.put("type", type);
			//�ص���ҵ
			if(CxlxEnum.zdqy.getCode().equals(type)){
				sql.append(" and  y.LAWOBJID_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='0' and c.year_ = :year1)");
			}else if(CxlxEnum.ybqy.getCode().equals(type)){
				sql.append(" and  y.LAWOBJID_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='2' and c.year_ = :year1)");
			}else if(CxlxEnum.tsqy.getCode().equals(type)){
				sql.append(" and  y.LAWOBJID_ not in (select c.lawobjid_ from T_Biz_Checkedlawobj c where c.type_='3' and c.year_ = :year1)");
			}
			condition.put("year1", year);
		}
		//��������
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
	 * ����������ȳ�����
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
				lawobj = (TDataLawobj)this.get(TDataLawobj.class, idList[i]);//ִ������
				po.setArea((TSysArea)this.get(TSysArea.class, CtxUtil.getAreaId()));//����
				po.setLawobj(lawobj);//�����ִ������id
				po.setLawobjtype(lawobj.getLawobjtype());//ִ����������
				po.setLawobjname(nameList[i]);//ִ����������
				if(StringUtils.equals(lawobj.getLawobjtype(), ZfdxLx.GYWRY.getCode())){//��������Ϊ�ص���ҵ�����ó�ѡ����Ϊ�ص�
					if(StringUtils.isBlank(lawobjManager.getKzlxByLawobj(lawobj)) || StringUtils.equals(lawobjManager.getKzlxByLawobj(lawobj), "4")){
						po.setType(CxlxEnum.ybqy.getCode());//��ѡ���ͣ�һ����ҵ
					}else{
						po.setType(CxlxEnum.zdqy.getCode());//��ѡ���ͣ��ص���ҵ
					}
					
				}else{
					po.setType(CxlxEnum.ybqy.getCode());//��ѡ���ͣ�һ����ҵ
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
	 * ɾ����ȳ�����
	 * @param id
	 * @throws AppException
	 */
	public void removeYearLawobj(String id) throws AppException {
		TBizYearLawobj del = (TBizYearLawobj) this.dao.get(TBizYearLawobj.class, id);
		del.setIsActive(YnEnum.N.getCode());
		this.dao.save(del);
	}
	
	
	/**
	 * ������Ƿ�����ɳ�ѡ
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
	 * ������ȳ������б�
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
				lawobj = checkedList.get(i);//ִ������
				po.setArea((TSysArea)this.get(TSysArea.class, CtxUtil.getAreaId()));//����
				po.setLawobj(lawobj);//�����ִ������id
				po.setLawobjtype(lawobj.getLawobjtype());//ִ����������
				po.setLawobjname(lawobjManager.getNameByLawobj(lawobj));//ִ����������
				po.setType(cxlx);//��ѡ����
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
	 * �������ܣ���ѡ���������б�
	 */
	public List<Combobox> queryCxlxList(){
		return CxlxEnum.getCxlxEnumList();
	}
}