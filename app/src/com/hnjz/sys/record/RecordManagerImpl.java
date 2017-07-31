/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.record;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.sys.po.TSysUser;

import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TDataIllegaltype;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.data.po.TDataRecord;
import com.hnjz.app.data.po.TDataRecordlawobjtype;
import com.hnjz.app.data.po.TDataTasktype;

/**
 * 笔录问题管理的manager
 * 
 * @author wumi
 * @version $Id: RecordManagerImpl.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
@Service("recordManagerImpl")
public class RecordManagerImpl extends ManagerImpl implements
		RecordManager {

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(RecordManagerImpl.class);

	@Override
	public FyWebResult queryRecord(String wflx, String content, String iscurver, String kcxwbj, String isActive, String page, String pageSize) {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append("from TDataRecord where 1=1 ");
		if (StringUtils.isNotBlank(content)) {
			sql.append(" and content like :content");
			data.putLike("content", content);
		}
		if (StringUtils.isNotBlank(iscurver)) {
			sql.append(" and iscurver = :iscurver");
			data.put("iscurver", iscurver);
		}
		if (StringUtils.isNotBlank(kcxwbj)) {
			sql.append(" and kcxwbj = :kcxwbj");
			data.put("kcxwbj", kcxwbj);
		}
		if (StringUtils.isNotBlank(wflx)) {
			String wflxs = "";
			for (int i = 0; i < wflx.split(",").length; i++) {
				if (i > 0) {
					wflxs += ",";
				}
				wflxs += "'" + wflx.split(",")[i] + "'";
			}
			sql.append(" and wflx.id in (" + wflxs + ")");
		}
		if (StringUtils.isNotBlank(isActive)) {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", isActive);
		} else {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", YnEnum.Y.getCode());
		}
		sql.append(" order by orderby ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<TDataRecord> records = pos.getRe();
		Map<String, String> dataObject = null;
		for (TDataRecord ele : records) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele.getId());
			dataObject.put("content", ele.getContent());
			dataObject.put("kcxwbj", ele.getKcxwbj().equals("0") ? "勘察笔录" : "询问笔录");
			dataObject.put("isdel", ele.getIsdel().equals(YnEnum.Y.getCode()) ? "是" : "否");
			dataObject.put("orderby", String.valueOf(ele.getOrderby()));
			dataObject.put("operate", OperateUtil.getOperate(ele.getId()));
			rows.add(dataObject);
		}
		fy.setRows(rows);
		log.debug(fy);
		return fy;
	}

	@Override
	public void recordInfo(RecordForm frm) {
		TDataRecord po = (TDataRecord) this.dao.get(TDataRecord.class, frm.getId());
		List<TDataRecordlawobjtype> typePo = this.dao.find("from TDataRecordlawobjtype where recordid = ?", frm.getId());
		String lawobjtype = "";
		for (int i = 0; i < typePo.size(); i++) {
			if (i > 0){
				lawobjtype += ",";
			}
			lawobjtype += typePo.get(i).getLawobjtype();
		}
		
		frm.setId(po.getId());
		frm.setContent(po.getContent());
		frm.setDescribe(po.getDescribe());
		frm.setTsnr(po.getTsnr());
		frm.setWflx(po.getWflx().getId());
		frm.setLawobjtype(lawobjtype);
		frm.setKcxwbj(po.getKcxwbj());
		frm.setTasktype(TaskTypeCode.WFAJ.getCode());
		frm.setIsdel(po.getIsdel());
		frm.setOrderby(po.getOrderby());
		frm.setVernum(po.getVernum());
		frm.setIscurver(po.getIscurver());
		frm.setIszdjz(po.getIszdjz());
		frm.setIsActive(po.getIsActive());
	}

	@Override
	public void saveRecord(RecordForm frm) {
		TDataRecord po = null;
		// 名称不能重复
		StringBuilder hsq = new StringBuilder();
		hsq.append("select count(id) from TDataRecord where isActive = 'Y' and content = :content ");
		QueryCondition data = new QueryCondition();
		data.put("content", frm.getContent());
		long count = (Long) this.dao.find(hsq.toString(), data).get(0);
		if (StringUtils.isNotBlank(frm.getId())){
			po = (TDataRecord)this.get(TDataRecord.class, frm.getId());
			this.dao.remove(this.dao.find("from TDataRecordlawobjtype where recordid = ?", po.getId()));
		} else {
/*			if (count > 0) {
				new AppException("笔录内容不能重复。");
				return;
			} else {*/
				po = new TDataRecord();
			/*}*/
		}
		
		TSysUser curUser = CtxUtil.getCurUser();
		Date cur = new Date();
		
		TDataIllegaltype ill = (TDataIllegaltype)(this.find(" from TDataIllegaltype where isActive = 'Y' and id = ? ", frm.getWflx()).get(0));
		String sql = " from TDataTasktype where isActive = 'Y' and code = '" + frm.getTasktype() + "'";
		List<TDataTasktype> task = this.find(sql);
		
		po.setContent(frm.getContent());
		po.setDescribe(frm.getDescribe());
		po.setTsnr(frm.getTsnr());
		po.setWflx(ill);
		po.setKcxwbj(frm.getKcxwbj());
		po.setTasktype(task.get(0));
		if (StringUtils.isNotBlank(frm.getIsdel())) {
			po.setIsdel(YnEnum.Y.getCode());
		} else {
			po.setIsdel(YnEnum.N.getCode());
		}
		if (StringUtils.isNotBlank(frm.getIscurver())) {
			po.setIscurver(YnEnum.Y.getCode());
		} else {
			po.setIscurver(YnEnum.N.getCode());
		}
		if (StringUtils.isNotBlank(frm.getIszdjz())) {
			po.setIszdjz(YnEnum.Y.getCode());
		} else {
			po.setIszdjz(YnEnum.N.getCode());
		}
		
		po.setIsActive(YnEnum.Y.getCode());
		
		
		po.setOrderby(frm.getOrderby());
		po.setVernum(frm.getVernum()==null?1:frm.getVernum());
		
		po.setCreated(cur);
		po.setCreateby(curUser);
		po.setUpdateby(curUser);
		po.setUpdated(cur);
		
		this.dao.save(po);
		
		TDataRecordlawobjtype lawobjtypePo = new TDataRecordlawobjtype();
		for (int i = 0; i < frm.getLawobjtype().split(",").length; i++) {
			lawobjtypePo = new TDataRecordlawobjtype();
			lawobjtypePo.setLawobjtype(frm.getLawobjtype().split(",")[i]);
			lawobjtypePo.setRecordid(po.getId());
			this.dao.save(lawobjtypePo);
		}
	}

	@Override
	public void removeRecord(String id) throws AppException {
		TDataRecord del = (TDataRecord) this.dao.get(TDataRecord.class, id);
		if (del.getIsdel().equals(YnEnum.N.getCode())){
			throw new AppException("此笔录问题不允许删除。");
		}
		del.setIsActive(YnEnum.N.getCode());
		this.dao.save(del);
	}
}
