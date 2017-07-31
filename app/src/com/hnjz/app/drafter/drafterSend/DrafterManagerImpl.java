/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.drafter.drafterSend;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnjz.app.drafter.drafterSet.DrafterSetManager;
import com.hnjz.app.drafter.enums.AuditStateEnum;
import com.hnjz.app.drafter.po.TBizDrafter;
import com.hnjz.app.drafter.po.TDataDrafterSet;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.util.DateUtil;
import com.hnjz.sys.area.AreaManager;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserManager;

/**
 * 稿件报送的Manager实现
 * @author shiqiuhan
 * @created 2015-12-24,上午08:59:36
 */
@Service("drafterManagerImpl")
public class DrafterManagerImpl extends ManagerImpl implements DrafterManager {
	
	@Autowired
    private UserManager userManager;
	
	@Autowired
    private OrgManager orgManager;
	
	@Autowired
    private AreaManager areaManager;
	
	@Autowired
    private DrafterSetManager drafterSetManager;
	
	/**
	 * 查询稿件列表
	 * 
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @return 稿件列表
	 * @throws Exception
	 */
	public FyWebResult queryDrafter(DrafterForm frm,String page, String pageSize)
			throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select d.id_,d.drafterName_,d.name_,TO_CHAR(d.submitDate_,'yyyy-mm-dd hh24:mi:ss'),d.state_,d.auditid_,d.DRAFTERID_ from T_Biz_Drafter d ");
		sql.append(" where d.isActive_ = 'Y' ");
		TSysUser cur = CtxUtil.getCurUser();
		
		//拟稿人名称
		if(StringUtils.isNotBlank(frm.getDrafterName())){
			sql.append(" and d.drafterName_ like :drafterName ");
			data.put("drafterName", "%"+frm.getDrafterName()+"%");
		}
		//拟稿人id
		if(cur!=null && StringUtils.isNotBlank(cur.getId())){
			sql.append(" and (d.drafterId_ = :drafterId ");
			data.put("drafterId", cur.getId());
			sql.append("or  d.id_  in (select t.id_ from T_Biz_Drafter t ,T_Data_DrafterSet s "
						+" where t.isActive_ = 'Y' and s.isActive_ = 'Y' "  
						+" and t.drafterOrgid_ = s.orgid_ "
						+" and s.auditid_=:auditId "
						+" and ((s.type_='1'and t.state_>'1') or (s.type_='2'and t.state_>'3') or (s.type_='3'and t.state_>'5'))))");
			data.put("auditId", cur.getId());
		}
		//主题
		if(StringUtils.isNotBlank(frm.getName())){
			sql.append(" and d.name_ like :name ");
			data.put("name", "%"+frm.getName()+"%");
		}
		//状态
		if(frm.getState()!= null){
			sql.append(" and d.state_ = :state ");
			data.put("state", frm.getState());
		}
		//区域
		if(StringUtils.isNotBlank(CtxUtil.getAreaId())){
			sql.append(" and d.areaid_ = :areaid ");
			data.put("areaid", CtxUtil.getAreaId());
		}
		sql.append(" order by d.submitDate_ desc");
		FyResult pos = this.dao.find(sql.toString(),Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), data);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<Object[]> drafters = pos.getRe();
		Map<String, String> row = null;
		for (Object[] obj : drafters) {
			row = new HashMap<String, String>();
			row.put("id", String.valueOf(obj[0]));
			row.put("drafterName", String.valueOf(obj[1]));
			if((obj[2])!=null){
			row.put("name",String.valueOf(obj[2]));
			}else{
				row.put("name","");
			}
			if((obj[3])!=null){
				row.put("submitDate", String.valueOf(obj[3]));
			}else{
				row.put("submitDate", "");
			}
			String state = String.valueOf(AuditStateEnum.getNameByCode(String.valueOf(obj[4])));
			row.put("state", state);//稿件状态
			TSysOrg org = orgManager.getOrgByUserid(String.valueOf(obj[6]));//拟稿人用户所在部门
			String audit = "";//待审核人
			String auditid = "";
			List <TDataDrafterSet> drafterSet = new ArrayList<TDataDrafterSet>();
			if(org!=null && StringUtils.isNotBlank(org.getId())){
				if(StringUtils.equals(String.valueOf(obj[4]), AuditStateEnum.YTJ.getCode())){//稿件已提交，查找初级审核人
		    		drafterSet = drafterSetManager.queryShr(org.getId(), "1");//初审
		    	}else if(StringUtils.equals(String.valueOf(obj[4]), AuditStateEnum.CSTG.getCode())){//初审通过，查找批准审核人
		    	    drafterSet = drafterSetManager.queryShr(org.getId(), "2");//批准
		    	}else if(StringUtils.equals(String.valueOf(obj[4]), AuditStateEnum.YPZ.getCode())){//已批准，查找报出审批人
		    		drafterSet = drafterSetManager.queryShr(org.getId(), "3");//报出
		    	}
				if(drafterSet!=null && drafterSet.size()>0){
	    			for(int i=0; i<drafterSet.size();i++){
	    				auditid = drafterSet.get(i).getAudit();
	    				if(StringUtils.isNotBlank(auditid)){
	    					TSysUser user = userManager.getUser(auditid);
	    					if(user!=null){
	    						audit =audit + user.getName()+"，";
	    					}
	    				}
	    			}
	    			audit = audit.substring(0,audit.length()-1);//移除最后一个逗号
	    		}	
			}
			row.put("audit", audit);//待审核人
    		StringBuilder operate = new StringBuilder();
    		if(StringUtils.equals(String.valueOf(obj[4]), AuditStateEnum.WTJ.getCode())){//稿件状态为未提交，加编辑操作
				operate.append("<a class='b-link'  onclick='edit(this)' id='"+String.valueOf(obj[0])+"' >编辑</a> ");
			}
			row.put("operate", operate.toString()+OperateUtil.getOperate(String.valueOf(obj[0])));
			rows.add(row);
		}
		fy.setRows(rows);
		return fy;
	}
	/**
	 * 查询稿件审核列表
	 * 
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @return 稿件列表
	 * @throws Exception
	 */
	public FyWebResult queryAuditDrafter(DrafterForm frm,String page, String pageSize) 
		throws Exception{
		TSysUser cur = CtxUtil.getCurUser();
		FyWebResult fy = new FyWebResult();
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		StringBuilder sql = new StringBuilder();
		Map<String, Object> condition = new HashMap<String, Object>();
		sql.append("SELECT d.id_ ,d.drafterName_,d.name_,TO_CHAR(d.submitDate_,'yyyy-mm-dd hh24:mi:ss'),d.state_,d.DRAFTERORGID_"
							+" FROM T_Biz_Drafter d where d.isActive_ ='Y' and exists " +
							" (select 1 from T_DATA_DRAFTERSET s where  s.isActive_ ='Y'" +
							" and ((d.state_='1' and s.type_='1') or (d.state_='2' and s.type_='2') or (d.state_='4' and s.type_='3'))" +
							" and s.AUDITID_= '"+cur.getId()+
							"' and d.DRAFTERORGID_ = s.ORGID_)");
				
				//拟稿人名称
				if(StringUtils.isNotBlank(frm.getDrafterName())){
					sql.append(" and d.drafterName_ like :drafterName ");
					condition.put("drafterName", "%"+frm.getDrafterName()+"%");
				}
				//主题
				if(StringUtils.isNotBlank(frm.getName())){
					sql.append(" and d.name_ like :name ");
					condition.put("name", "%"+frm.getName()+"%");
				}
				//状态
				if(frm.getState()!= null){
					sql.append(" and d.state_ = :state ");
					condition.put("state", frm.getState());
				}
				//区域
				if(StringUtils.isNotBlank(CtxUtil.getAreaId())){
					sql.append(" and d.AREAID_ = :areaid ");
					condition.put("areaid", CtxUtil.getAreaId());
				}
				sql.append(" order by d.submitDate_ desc");
				FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
				fy = new FyWebResult(pos);
				Map<String, String> dataObject = null;
				List<Object[]> drafters = pos.getRe();
				for (Object[] obj : drafters) {
					dataObject = new HashMap<String, String>();
					String id = obj[0]==null?"":String.valueOf(obj[0]);
					dataObject.put("id", id);
					dataObject.put("drafterName", obj[1]==null?"":String.valueOf(obj[1]));
					dataObject.put("name",  obj[2]==null?"":String.valueOf(obj[2]));
					dataObject.put("submitDate", obj[3]==null?"":String.valueOf(obj[3]));
					dataObject.put("state", obj[4]==null?"":AuditStateEnum.getNameByCode(String.valueOf(obj[4])));
					String state = obj[4]==null?"":String.valueOf(obj[4]);
					StringBuilder operate = new StringBuilder();
					if(StringUtils.equals(state, AuditStateEnum.YTJ.getCode())){//当前登录人为初审人员,稿件状态为已提交，加审批操作
						operate.append("<a class='b-link' onclick='audit(this)' id='"+String.valueOf(obj[0])+"' >审批</a> ");
					}else if(StringUtils.equals(state, AuditStateEnum.CSTG.getCode())){//当前登录人为批准人员,稿件状态为初审通过，加审批操作
						operate.append("<a class='b-link'  onclick='audit(this)' id='"+String.valueOf(obj[0])+"' >审批</a> ");
					}else if(StringUtils.equals(state, AuditStateEnum.YPZ.getCode())){//当前登录人为报出人员,稿件状态为已批准，加审批操作
						operate.append("<a class='b-link' onclick='audit(this)' id='"+String.valueOf(obj[0])+"' >审批</a> ");
					}
					dataObject.put("operate", operate.toString()+OperateUtil.getOperate(String.valueOf(obj[0])));
					
					rows.add(dataObject);
				}
				fy.setRows(rows);
				return fy;
	}
	
	/**
	 * 查询稿件统计列表
	 * 
	 * @param page
	 *            第几页
	 * @param pageSize
	 *            每页显示多少条
	 * @return 稿件列表
	 * @throws Exception
	 */
	public FyWebResult queryDrafterStatistics(DrafterForm frm,String page, String pageSize)
			throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select drafterOrgname_, drafterName_, count(1) ");
		sql.append("from T_Biz_Drafter where isActive_='Y' and state_ = :state");
		if(frm.getState()!=null){
			data.put("state", frm.getState());
		}else{
			data.put("state", '6');//默认统计已报出稿件
		}
		//区域
		if(StringUtils.isNotBlank(CtxUtil.getAreaId())){
			sql.append(" and AREAID_ = :areaid ");
			data.put("areaid", CtxUtil.getAreaId());
		}
		//发送时间
		if (StringUtils.isNotBlank(frm.getSubmitDate1())){
			Date date1 = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",frm.getSubmitDate1()+" 00:00:00");
			sql.append(" and submitDate_ >= :submitDate1 ");
			data.put("submitDate1", date1);
		}
		//发送时间
		if (StringUtils.isNotBlank(frm.getSubmitDate2())){
			Date date2 = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",frm.getSubmitDate2()+" 24:00:00");
			sql.append(" and submitDate_ <= :submitDate2 ");
			data.put("submitDate2", date2);
		}
		sql.append(" group by drafterOrgname_,drafterName_");
		
		FyResult pos = (FyResult) dao.find(sql.toString(), StringUtils.isNotBlank(page)?Integer.parseInt(page):1,StringUtils.isNotBlank(pageSize)?Integer.parseInt(pageSize):20, data);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<Object[]> listDrafter = pos.getRe();
		Map<String, String> row = null;
		for (Object[] obj : listDrafter) {
			row = new HashMap<String, String>();
			row.put("orgname", String.valueOf(obj[0]));
			row.put("drafterName", String.valueOf(obj[1]));
			row.put("number",String.valueOf(obj[2]));
			rows.add(row);
		}
		fy.setRows(rows);
		return fy;
	}

	/**
	 * 保存稿件
	 * 
	 * @param drafterForm
	 *            {@link drafterForm}
	 */
	@Transactional(readOnly = false)
	public TBizDrafter saveDrafter(DrafterForm frm) throws AppException {
		TBizDrafter po = null;
		TSysUser user = CtxUtil.getCurUser();
		Date cur = new Date();
		if (StringUtils.isNotBlank(frm.getId())) {
			po = (TBizDrafter) this.get(TBizDrafter.class, frm.getId());
		} else {
			po = new TBizDrafter();
			po.setCreateby(user);
			po.setCreated(cur);
		}
		if(StringUtils.isNotBlank(frm.getName())){
			po.setName(frm.getName().trim());//稿件名称（主题）
		}
		po.setDrafterId(frm.getDrafterId());//拟稿人
		po.setDrafterName(frm.getDrafterName());//拟稿人姓名
		TSysOrg	tSysOrg = userManager.getOrgbyUser(frm.getDrafterId());//获取拟稿人所在部门
		if(tSysOrg!=null){
			po.setDrafterOrgid(tSysOrg.getId());
			po.setDrafterOrgname(tSysOrg.getName());
		}else{
			po.setDrafterOrgname("其他");
		}
		if(StringUtils.isNotBlank(frm.getDescribe())){
			po.setDescribe(frm.getDescribe().trim());//稿件内容
		}
		
		po.setState(frm.getState());//稿件状态
		if(frm.getState()==1){
			po.setSubmitDate(cur);//提交日期
		}
		if(StringUtils.isNotBlank(CtxUtil.getAreaId())){
			TSysArea area = (TSysArea) areaManager.get(TSysArea.class, CtxUtil.getAreaId());
			po.setArea(area);
		}
		po.setIsActive(YnEnum.Y.getCode());
		po.setUpdateby(user);
		po.setUpdated(cur);
		this.dao.save(po);
		return po;
	}
	
	/**
	 * 稿件审核
	 * 
	 * @param drafterForm
	 *            {@link drafterForm}
	 */
	@Transactional(readOnly = false)
	public TBizDrafter auditDrafter(String id,String result) throws AppException {
		TBizDrafter po = null;
		TSysUser user = CtxUtil.getCurUser();
		Date cur = new Date();
		if (StringUtils.isNotBlank(id)) {
			po = (TBizDrafter) this.get(TBizDrafter.class, id);
			//更改稿件状态
			if(StringUtils.equals(String.valueOf(po.getState()),AuditStateEnum.YTJ.getCode())){//已提交
				if(StringUtils.equals(result, "true")){//审核通过
					po.setState(Integer.parseInt(AuditStateEnum.CSTG.getCode()));
				}else if(StringUtils.equals(result, "false")){
					po.setState(Integer.parseInt(AuditStateEnum.CSWTG.getCode()));
				}
			}else if(StringUtils.equals(String.valueOf(po.getState()),AuditStateEnum.CSTG.getCode())){//初审通过
				if(StringUtils.equals(result, "true")){//审核通过
					po.setState(Integer.parseInt(AuditStateEnum.YPZ.getCode()));
				}else if(StringUtils.equals(result, "false")){
					po.setState(Integer.parseInt(AuditStateEnum.WPZ.getCode()));
				}
			}else if(StringUtils.equals(String.valueOf(po.getState()),AuditStateEnum.YPZ.getCode())){//已批准
				if(StringUtils.equals(result, "true")){//审核通过
					po.setState(Integer.parseInt(AuditStateEnum.YBC.getCode()));
				}else if(StringUtils.equals(result, "false")){
					po.setState(Integer.parseInt(AuditStateEnum.WBC.getCode()));
				}
			}
			po.setAuditDate(cur);//审核日期
			po.setUpdateby(user);
			po.setUpdated(cur);
			this.dao.save(po);
			return po;
		}
		return null;
	}

	/**
	 * 删除稿件
	 * 
	 * @param id
	 *            稿件信息的ID
	 */
	public void removeDrafter(String id) throws AppException {
		TBizDrafter delObj = (TBizDrafter) this.dao.get(TBizDrafter.class, id);
		this.dao.remove(delObj);
	}
	
	/**
	 * 
	 * 函数介绍：审批状态下拉列表
	 */
	public List<Combobox> queryStateList(){
		return AuditStateEnum.getStateEnumList();
	}
}
