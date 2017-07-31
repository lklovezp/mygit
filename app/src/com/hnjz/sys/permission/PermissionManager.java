/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.permission;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.IndexManager;
import com.hnjz.common.AppException;
import com.hnjz.common.YnEnum;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.SecurityData;
import com.hnjz.sys.po.TSysFunc;
import com.hnjz.sys.po.TSysFuncOper;
import com.hnjz.sys.po.TSysRole;
import com.hnjz.sys.po.TSysRoleFunc;
import com.hnjz.sys.po.TSysRoleFuncOper;
import com.hnjz.sys.po.TSysUser;

/**
 * Ȩ�޹�����
 * 
 * @author wumi
 * @version $Id: PermissionManager.java, v 0.1 Jan 18, 2013 2:37:45 PM wumi Exp
 *          $
 */
@Service("permissionManager")
public class PermissionManager extends ManagerImpl {

	/** ��־ */
	private static final Log log = LogFactory.getLog(PermissionManager.class);

	@Autowired
	private SecurityData securityData;
	
	@Autowired
	private IndexManager     indexManager;

	/**
	 * ��ѯ���ܲ˵�
	 * 
	 * @return ���ܲ˵��б�
	 * @throws Exception
	 */
	public JSONArray queryQuxian(String role) throws Exception {
		JSONArray re = new JSONArray();
		//�������߰�ı�ʶ�жϣ����Բ�ѯ��Ӧ��sql��䣩
		String biaoshi = indexManager.sysVer;
		String hsql = "from TSysFunc where isActive=? order by orderby";
		List<TSysFunc> pos = dao.find(hsql, YnEnum.Y.getCode());
		JSONObject jsonObj = null;
		
		List<String> myOper = this.dao.find("select id from TSysFuncOper where id in (select funcoper.id from TSysRoleFuncOper where role.id = ?)", role);
		
		for (TSysFunc ele : pos) {
			jsonObj = new JSONObject();
			jsonObj.put("id", ele.getId());
			if("0".equals(biaoshi)){
				if(null != ele.getFunction()){
					if (StringUtils.isNotBlank(ele.getFunction().getId())) {
						jsonObj.put("pid", ele.getFunction().getId());
					}
				}
			}else{
				if (null != ele.getFunction()) {
					jsonObj.put("pid", ele.getFunction().getId());
				}
			}
			jsonObj.put("order", ele.getOrderby());
			jsonObj.put("name", ele.getName());
			JSONObject dataObject = new JSONObject();
			dataObject.put("title", ele.getName());
			hsql = "from TSysFuncOper where function.id=? ";
			List<TSysFuncOper> ops = dao.find(hsql, ele.getId());
			String temp = null;
			StringBuilder str = new StringBuilder();
			
			for (TSysFuncOper mx : ops) {
				temp = "<input type='checkBox' id='aa$id' value='$id' name='$id' />$name  ";
				if (myOper.contains(mx.getId())){
					temp = "<input type='checkBox' id='aa$id' value='$id' checked name='$id' />$name  ";
				};
				temp = temp.replaceAll("\\$id", mx.getId());
				temp = temp.replaceAll("\\$name", mx.getOpername());

				str.append(temp);
			}
			dataObject.put("ops", str.toString());
			jsonObj.put("dataObject", dataObject);
			re.put(jsonObj);
		}
		if (log.isDebugEnabled()) {
			log.debug(re);
		}
		return re;
	}

	/**
	 * ����Ȩ��
	 * 
	 * @param roleId
	 *            ��ɫid
	 * @param checkboxValue
	 *            ��ɫ�����е�Ȩ��
	 * @throws AppException
	 */
	public void savePermission(String roleId, Enumeration<String> checkboxValue)
			throws AppException {
		TSysUser user = CtxUtil.getCurUser();
		TSysRole role = (TSysRole) this.dao.get(TSysRole.class, roleId);
		if (null == role) {
			throw new AppException("��ѡ���ɫ");
		}
		Date cur = new Date();
		// ɾ�����н�ɫ����
		String hsql = "from TSysRoleFunc where role.id = ?";
		this.dao.removeFindObjs(hsql, roleId);
		hsql = "from TSysRoleFuncOper where role.id = ?";
		this.dao.removeFindObjs(hsql, roleId);
		// ����Ȩ�������еĲ���
		Map<String, TSysFunc> funs = new HashMap<String, TSysFunc>();
		while (checkboxValue.hasMoreElements()) {
			String funcOperID = checkboxValue.nextElement();
			TSysRoleFuncOper roleFuncOper = new TSysRoleFuncOper();
			if (StringUtils.isNotBlank(funcOperID) && funcOperID.length() == 32) {
				// ����
				TSysFuncOper funcOper = (TSysFuncOper) dao.get(
						TSysFuncOper.class, funcOperID);
				if (log.isDebugEnabled()) {
					log.debug("id---------" + funcOperID + ":"
							+ funcOper.getLinkAddr());
				}
				roleFuncOper.setFuncoper(funcOper);
				roleFuncOper.setRole(role);
				roleFuncOper.setCreateby(user);
				roleFuncOper.setCreated(cur);
				roleFuncOper.setIsActive(YnEnum.Y.getCode());
				roleFuncOper.setDescribe("");
				roleFuncOper.setOrderby(0);
				this.dao.save(roleFuncOper);
				funs.put(funcOper.getFunction().getId(), funcOper
								.getFunction());
			}
		}
		// ����Ȩ�������еĲ˵�
		TSysRoleFunc roleFunc = null;
		for (Map.Entry<String, TSysFunc> ele : funs.entrySet()) {
			// ���ܲ˵����ݹ鱣��
			List<String> funcList = new ArrayList<String>();
			roleFunc = new TSysRoleFunc();
			roleFunc.setCreateby(user);
			roleFunc.setCreated(cur);
			roleFunc.setFunction(ele.getValue());
			roleFunc.setIsActive(YnEnum.Y.getCode());
			roleFunc.setOrderby(0);
			roleFunc.setRole(role);
			this.save(roleFunc);
			if (!funcList.contains(ele.getKey())) {
				this.saveParent(role, ele.getValue());
				this.save(roleFunc);
				funcList.add(ele.getKey());
			}
		}
		securityData.loadAllResource();

	}

	/**
	 * �ݹ鱣�游����
	 * 
	 * @param role
	 *            ��ɫ
	 * @param function
	 *            ����
	 */
	private void saveParent(TSysRole role, TSysFunc function) {
		TSysRoleFunc roleFunc = new TSysRoleFunc();
		if (function == null || role == null) {
			return;
		}
		if (function.getFunction() != null) {
			TSysFunc funcParent = function.getFunction();
			String hsql = "from TSysRoleFunc where role.id=? and function.id= ?";
			List<String> rfList = this.dao.find(hsql, role.getId(), funcParent
					.getId());
			if (rfList.size() == 0) {
				log.debug("���Ӹ����ܣ�" + funcParent.getName() + "��");
				roleFunc = new TSysRoleFunc();
				roleFunc.setRole(role);
				roleFunc.setFunction(funcParent);
				roleFunc.setOrderby(funcParent.getOrderby());
				roleFunc.setCreateby(funcParent.getCreateby());
				roleFunc.setCreated(new Date());
				roleFunc.setIsActive(YnEnum.Y.getCode());
				this.save(roleFunc);
			}
			if (funcParent.getFunction() != null) {
				saveParent(role, funcParent);
			}

		}
	}

	public void setSecurityData(SecurityData securityData) {
		this.securityData = securityData;
	}

	public SecurityData getSecurityData() {
		return securityData;
	}

}