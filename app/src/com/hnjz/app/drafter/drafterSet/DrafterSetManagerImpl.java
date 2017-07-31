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
 * �����������õ�Managerʵ��
 * @author shiqiuhan
 * @created 2015-12-24,����08:59:36
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
	 * ���������������
	 * @param orgId
	 *            ����
	 * @param jb
	 *            ��˼���   
	 * @param shr
	 *            �����           
	 */
	public void saveDrafterSet(String orgId,String jb,String shr) throws AppException {
		TDataDrafterSet po = null;
		TSysUser user = CtxUtil.getCurUser();
		Date cur = new Date();
		
		//��ɾ������
		String sqlStr=" from TDataDrafterSet where org.id=? and type=? ";
		dao.removeFindObjs(sqlStr,orgId,jb);
		
		if(StringUtils.isNotBlank(shr)){
			String shrs[] = shr.split(",");
			for(int i=0;i<shrs.length;i++){
				StringBuilder sql = new StringBuilder();
				sql.append("from TDataDrafterSet where isActive='Y' and org.id=? and type=? and audit=?");
				List <TDataDrafterSet> audit = dao.find(sql.toString(),orgId,jb,shrs[i]);
				if(audit!=null && audit.size()>0){//������ݿ��д���ͬ���š�ͬ��˼���ͬһ������ˣ������ظ�������Ϣ
					continue;
				}else{
					po = new TDataDrafterSet();
					//����
					if(StringUtils.isNotBlank(CtxUtil.getAreaId())){
						TSysArea area = (TSysArea) areaManager.get(TSysArea.class, CtxUtil.getAreaId());
						po.setArea(area);
					}
					//����
					if(StringUtils.isNotBlank(orgId)){
						TSysOrg org = (TSysOrg) orgManager.get(TSysOrg.class,orgId);
						po.setOrg(org);
					}
					
					po.setAudit(shrs[i]);	//�����
					po.setType(jb);			//��˼���
					//���� ȡ�ֵ���ļ�������
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
	 * ���ݲ��ź���˼����ѯ��������������Ϣ
	 * @param orgId
	 *            ����
	 * @param jb
	 *            ��˼���   
	 */
	public List <TDataDrafterSet> queryShr(String orgId,String jb) throws AppException{
		StringBuilder sql = new StringBuilder();
		sql.append("from TDataDrafterSet where isActive='Y' and org.id=? and type=?");
		List <TDataDrafterSet> auditList = dao.find(sql.toString(),orgId,jb);
		return auditList;
	}
	
	/**
	 * ��������˲�ѯ���ź���˼���
	 * @param auditid
	 *            �����id
	 */
	public List <TDataDrafterSet> querySet(String auditid) throws AppException{
		StringBuilder sql = new StringBuilder();
		sql.append("from TDataDrafterSet where isActive='Y' and audit=? order by type");
		List <TDataDrafterSet> auditList = dao.find(sql.toString(),auditid);
		return auditList;
	}
}