/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.hnjz.app.drafter.drafterSet;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.drafter.po.TDataDrafterSet;
import com.hnjz.common.AppException;
import com.hnjz.common.YnEnum;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.area.AreaManager;
import com.hnjz.sys.dic.DicManager;
import com.hnjz.sys.dic.DicTypeEnum;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysDic;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;

/**
 * 稿件审核人设置的Manager实现
 * @author shiqiuhan
 * @created 2015-12-24,上午08:59:36
 */
@Service("drafterSetManagerImpl")
public class DrafterSetManagerImpl extends ManagerImpl implements DrafterSetManager {
	
	@Autowired
    private AreaManager areaManager;
	
	@Autowired
    private OrgManager orgManager;
	
	
	@Autowired
	private DicManager dicManager;

	/**
	 * 保存稿件审核人设置
	 * @param orgId
	 *            部门
	 * @param jb
	 *            审核级别   
	 * @param shr
	 *            审核人           
	 */
	public void saveDrafterSet(String orgId,String jb,String shr) throws AppException {
		TDataDrafterSet po = null;
		TSysUser user = CtxUtil.getCurUser();
		Date cur = new Date();
		
		//先删除数据
		String sqlStr=" from TDataDrafterSet where org.id=? and type=? ";
		dao.removeFindObjs(sqlStr,orgId,jb);
		
		if(StringUtils.isNotBlank(shr)){
			String shrs[] = shr.split(",");
			for(int i=0;i<shrs.length;i++){
				StringBuilder sql = new StringBuilder();
				sql.append("from TDataDrafterSet where isActive='Y' and org.id=? and type=? and audit=?");
				List <TDataDrafterSet> audit = dao.find(sql.toString(),orgId,jb,shrs[i]);
				if(audit!=null && audit.size()>0){//如果数据库中存在同部门、同审核级别，同一个审核人，则不再重复保存信息
					continue;
				}else{
					po = new TDataDrafterSet();
					//区域
					if(StringUtils.isNotBlank(CtxUtil.getAreaId())){
						TSysArea area = (TSysArea) areaManager.get(TSysArea.class, CtxUtil.getAreaId());
						po.setArea(area);
					}
					//部门
					if(StringUtils.isNotBlank(orgId)){
						TSysOrg org = (TSysOrg) orgManager.get(TSysOrg.class,orgId);
						po.setOrg(org);
					}
					
					po.setAudit(shrs[i]);	//审核人
					po.setType(jb);			//审核级别
					//排序 取字典里的级别排序
					if(StringUtils.isNotBlank(jb)){
						TSysDic dic = dicManager.queryDicByCode(DicTypeEnum.GJSHJB.getCode(), jb);
						if(dic!=null){
							po.setOrderby(dic.getOrderby());
						}else{
							po.setOrderby(0);
						}
					}else{
						po.setOrderby(0);
					}
					po.setCreateby(user);
					po.setCreated(cur);
					po.setIsActive(YnEnum.Y.getCode());
					po.setUpdateby(user);
					po.setUpdated(cur);
					this.dao.save(po);
				}
			}
		}
	}
	/**
	 * 根据部门和审核级别查询稿件审核人设置信息
	 * @param orgId
	 *            部门
	 * @param jb
	 *            审核级别   
	 */
	public List <TDataDrafterSet> queryShr(String orgId,String jb) throws AppException{
		StringBuilder sql = new StringBuilder();
		sql.append("from TDataDrafterSet where isActive='Y' and org.id=? and type=?");
		List <TDataDrafterSet> auditList = dao.find(sql.toString(),orgId,jb);
		return auditList;
	}
	
	/**
	 * 根据审核人查询部门和审核级别
	 * @param auditid
	 *            审核人id
	 */
	public List <TDataDrafterSet> querySet(String auditid) throws AppException{
		StringBuilder sql = new StringBuilder();
		sql.append("from TDataDrafterSet where isActive='Y' and audit=? order by type");
		List <TDataDrafterSet> auditList = dao.find(sql.toString(),auditid);
		return auditList;
	}
}
