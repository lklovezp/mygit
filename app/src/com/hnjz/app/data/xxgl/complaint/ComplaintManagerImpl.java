package com.hnjz.app.data.xxgl.complaint;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.enums.LawobjOutColunmEnum;
import com.hnjz.app.data.po.TDataComplaint;
import com.hnjz.app.data.xxgl.lawobj.LawobjManager;
import com.hnjz.app.work.enums.ZfdxLx;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.util.DateUtil;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-17
 * 功能描述：
	执法对象字典manager实现层
 *
 */
@Service("complaintManager")
public class ComplaintManagerImpl extends ManagerImpl implements ComplaintManager{

	@Autowired
    private LawobjManager     lawobjManager;
	
	@Override
	public ComplaintForm getComplaintInfo(ComplaintForm complaintForm) {
		TDataComplaint tDataComplaint = (TDataComplaint) this.get(TDataComplaint.class,complaintForm.getId());
		return tDataComplaint.transToComplaintForm(complaintForm);
	}

	@Override
	public TDataComplaint saveOrUpdateComplaint(ComplaintForm complaintForm) {
		TDataComplaint tDataComplaint = null;
		if(StringUtils.isNotBlank(complaintForm.getId())){
			tDataComplaint = (TDataComplaint) this.get(TDataComplaint.class,complaintForm.getId());
		}
		tDataComplaint = complaintForm.transToTDataComplaint(tDataComplaint);
		this.save(tDataComplaint);
		return tDataComplaint;
	}

	@Override
	public FyWebResult queryComplaintList(String lawobjtypeid,String lawobjname, String lawobjaddress, String starttime, String endtime, String isActive,String lawobjid, String page, String pageSize) {
		FyWebResult fy = null;
		try {
			Map<String,Object> data = new HashMap<String,Object>();
			StringBuffer sql = new StringBuffer("select o.id_,o.CPDATE_,o.LAWOBJNAME_,o.REASON,o.CPUSERNAME_,o.CPMOBILE_,o.ISACTIVE_ from T_Data_Complaint o where 1=1 ");
			if(StringUtils.isNotBlank(lawobjtypeid)){
				sql.append(" and o.lawobjtypeid_ = :lawobjtypeid ");
				data.put("lawobjtypeid", lawobjtypeid);
			}
			if(StringUtils.isNotBlank(lawobjname)){
				sql.append(" and o.lawobjname_ like :lawobjname ");
				data.put("lawobjname", "%"+lawobjname+"%");
			}
			if(StringUtils.isNotBlank(lawobjaddress)){
				sql.append(" and o.lawobjaddr_ like :lawobjaddr ");
				data.put("lawobjaddr", "%"+lawobjaddress+"%");
			}
			if(StringUtils.isNotBlank(starttime)){
				sql.append(" and o.cpdate_ >= :starttime ");
				data.put("starttime", DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", starttime+" 00:00:00"));
			}
			if(StringUtils.isNotBlank(endtime)){
				sql.append(" and o.cpdate_ <= :endtime ");
				data.put("endtime", DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", endtime+" 23:59:59"));
			}
			if(StringUtils.isNotBlank(isActive)){
				sql.append(" and o.isActive_ = :isActive ");
				data.put("isActive", isActive);
			}
			if(StringUtils.isNotBlank(lawobjid)){
				sql.append(" and (o.lawobjid_ = :lawobjid ");
				data.put("lawobjid", lawobjid);
				//建设项目转污染源等执法对象时带出执法记录
				String tolawobjid_column = lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.jsxm_lawobjid.getCode());
				sql.append(" or o.lawobjid_ in (select j.id_ from T_DATA_LAWOBJ j where j."+tolawobjid_column+" =:tolawobjid and j.LAWOBJTYPE_= '"+ZfdxLx.JSXM.getCode()+"'))");
				data.put("tolawobjid", lawobjid);
			}
			sql.append(" order by o.cpdate_ desc ");
			FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), data);
			fy = new FyWebResult(pos);
			List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
			fy.setRows(rows);
			List<Object[]> list = pos.getRe();
			Map<String, String> dataObject = null;
			for (Object[] obj : list) {
				dataObject = new HashMap<String, String>();
				dataObject.put("id", String.valueOf(obj[0]));
				dataObject.put("tssj", String.valueOf(obj[1]));
				dataObject.put("dwmc", String.valueOf(obj[2]));
				dataObject.put("tsyy", String.valueOf(obj[3]));
				dataObject.put("tsr", String.valueOf(obj[4]));
				dataObject.put("tsrlxdh", String.valueOf(obj[5]));
				dataObject.put("isActive", "Y".equals(String.valueOf(obj[6]))?"有效":"无效");
				dataObject.put("operate", OperateUtil.getOperate(String.valueOf(obj[0])));
				rows.add(dataObject);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return fy;
	}
	
	@Override
	public Map<String,String> queryOneComplaint(String lawobjid) {
		Map<String, String> dataObject = null;
		try {
			QueryCondition data = new QueryCondition();
			StringBuffer hql = new StringBuffer(" from TDataComplaint o where o.isActive = 'Y' ");
			if(StringUtils.isNotBlank(lawobjid)){
				hql.append(" and o.lawobjid = :lawobjid ");
				data.put("lawobjid", lawobjid);
			}
			hql.append(" order by o.cpdate desc ");
			List<TDataComplaint> list = dao.find(hql.toString(), data);
			if(list.size()>0){
				TDataComplaint ele = list.get(0);
				dataObject = new HashMap<String, String>();
				dataObject.put("id", ele.getId());
				dataObject.put("tssj", DateUtil.getDate(ele.getCpdate()));
				dataObject.put("dwmc", ele.getLawobjname());
				dataObject.put("tsyy", ele.getReason());
				dataObject.put("tsr", ele.getCpusername());
				dataObject.put("tsrlxdh", ele.getCpmobile());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} 
		return dataObject;
	}

	@Override
	public void removeComplaint(String id) {
		TDataComplaint tDataComplaint = (com.hnjz.app.data.po.TDataComplaint) this.get(TDataComplaint.class, id);
		tDataComplaint.setIsActive("N");
		this.save(tDataComplaint);
	}


}
