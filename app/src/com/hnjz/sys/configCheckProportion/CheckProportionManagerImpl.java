/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.sys.configCheckProportion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.sys.po.TDataCheckProportion;
import com.hnjz.sys.po.TSysUser;

/**
 * �������趨Managerʵ��
 * @author shiqiuhan
 * @created 2015-12-16,����02:20:03
 */
@Service("checkProportionImpl")
public class CheckProportionManagerImpl extends ManagerImpl implements CheckProportionManager {

	/**
	 * ��ѯ���ȳ�����
	 * 
	 * @param frm
	 *            �������������԰���ݡ����ȡ�����������
	 * @param page
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ��ʾ������
	 * @return ���ȳ������б�
	 * @throws Exception
	 */
	public FyWebResult queryCheckProportion(CheckProportionForm frm, String page, String pageSize)
			throws Exception {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append("from TDataCheckProportion where 1=1 ");
		//���
		if (StringUtils.isNotBlank(frm.getYear())) {
			sql.append(" and year = :year ");
			data.put("year", frm.getYear());
		}
		//����
		if (StringUtils.isNotBlank(frm.getQuarter())) {
			sql.append(" and quarter = :quarter ");
			data.put("quarter", frm.getQuarter());
		}
		//����
		if (StringUtils.isNotBlank(frm.getProportion1())) {
			int proportion1 = Integer.parseInt(frm.getProportion1());
			sql.append(" and proportion >= :proportion1 ");
			data.put("proportion1", proportion1);
		}
		if (StringUtils.isNotBlank(frm.getProportion2())) {
			int proportion2 = Integer.parseInt(frm.getProportion2());
			sql.append(" and proportion <= :proportion2 ");
			data.put("proportion2", proportion2);
		}
		//�Ƿ���Ч
		if (StringUtils.isNotBlank(frm.getIsActive())) {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", frm.getIsActive());
		} else {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", YnEnum.Y.getCode());
		}
		sql.append(" order by year,quarter,orderby ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<TDataCheckProportion> checkProportions = pos.getRe();
		Map<String, String> row = null;
		for (TDataCheckProportion ele : checkProportions) {
			row = new HashMap<String, String>();
			row.put("id", ele.getId());
			row.put("year", ele.getYear()+"��");
			row.put("quarter", QuarterEnum.getNameByCode(ele.getQuarter()));
			row.put("proportion", String.valueOf(ele.getProportion())+"%");
			row.put("isActive", YnEnum.getNote(ele.getIsActive()));
			row.put("operate", OperateUtil.getOperate(ele.getId()));
			rows.add(row);
		}
		fy.setRows(rows);
		return fy;
	}

	/**
	 * ���漾�ȳ���������
	 * 
	 * @param checkProportionForm
	 *            {@link checkProportionForm}
	 */
	@Transactional(readOnly = false)
	public void saveCheckProportion(CheckProportionForm frm) throws AppException {
		TDataCheckProportion po = null;
		TSysUser user = CtxUtil.getCurUser();
		Date cur = new Date();
		if (StringUtils.isNotBlank(frm.getId())) {
			po = (TDataCheckProportion) this.get(TDataCheckProportion.class, frm.getId());
		} else {
			po = new TDataCheckProportion();
			po.setCreateby(user);
			po.setCreated(cur);
		}
		po.setYear(frm.getYear());
		po.setQuarter(frm.getQuarter());
		po.setProportion(Integer.parseInt(frm.getProportion()));
		if (StringUtils.isNotBlank(frm.getIsActive())) {
			po.setIsActive(YnEnum.Y.getCode());
		} else {
			po.setIsActive(YnEnum.N.getCode());
		}
		po.setUpdateby(user);
		po.setUpdated(cur);
		po.setOrderby(0);
		this.dao.save(po);
	}

	/**
	 * ɾ�����ȳ�����������Ϣ
	 * 
	 * @param id
	 *            ���ȳ�����������Ϣ��ID
	 */
	public void removeCheckProportion(String id) throws AppException {
		TDataCheckProportion delObj = (TDataCheckProportion) this.dao.get(TDataCheckProportion.class, id);
		this.dao.remove(delObj);
	}
	
	/**
	 * 
	 * �������ܣ����������б�
	 */
	public List<Combobox> queryQuarterList(){
		return QuarterEnum.getQuarterEnumList();
	}
	
	/**
	 * ������ݺͼ��Ȳ��ҳ�����
	 * 
	 * @param year
	 *            ���
	 * @param quarter
	 *            ����
	 *            
	 */
	public int queryProportion(String year, String quarter){
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TDataCheckProportion where 1=1 and isActive='Y' ");
		//���
		if (StringUtils.isNotBlank(year)) {
			sql.append(" and year = :year ");
			data.put("year", year);
		}
		//����
		if (StringUtils.isNotBlank(quarter)) {
			sql.append(" and quarter = :quarter ");
			data.put("quarter", quarter);
		}
		List<TDataCheckProportion> proportionList = dao.find(sql.toString(), data);
		if(proportionList!=null && proportionList.size()>0){
			return proportionList.get(0).getProportion();
		}else{
			return 0;
		}
	}
	
}