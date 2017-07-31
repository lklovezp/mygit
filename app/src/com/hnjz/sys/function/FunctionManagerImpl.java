/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.function;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.IndexManager;
import com.hnjz.common.AppException;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.TSysFunc;
import com.hnjz.sys.po.TSysFuncOper;
import com.hnjz.sys.po.TSysUser;

/**
 * ���ܲ˵�������
 * 
 * @author wumi
 * @version $Id: FunctionManager.java, v 0.1 Jan 8, 2013 10:15:57 AM wumi Exp $
 */
@Service("functionManagerImpl")
public class FunctionManagerImpl extends ManagerImpl implements FunctionManager {

	/** ��־ */
	private static final Log log = LogFactory.getLog(FunctionManagerImpl.class);

	@Autowired
	private IndexManager     indexManager;
	
	/**
	 * ѡ���ܲ˵�����ҳ��Ĳ�ѯ
	 * 
	 * @return ���ܲ˵��б�
	 * @throws Exception
	 */
	public JSONArray querySelectFunction(String id) throws Exception {
		JSONArray re = new JSONArray();
		String hsql = "from TSysFunc where isActive=? order by orderby";
		List<TSysFunc> pos = dao.find(hsql, YnEnum.Y.getCode());
		JSONObject jsonObj = null;
		for (TSysFunc ele : pos) {
			jsonObj = new JSONObject();
			jsonObj.put("id", ele.getId());
			if (null != ele.getFunction()) {
				jsonObj.put("pid", ele.getFunction().getId());
			}
			jsonObj.put("name", ele.getName());
			re.put(jsonObj);
		}
		return re;
	}

	/**
	 * ��ѯ���ܲ˵�
	 * 
	 * @return ���ܲ˵��б�
	 * @throws Exception
	 */
	public JSONArray queryFunction(String name, String isActive) throws Exception {
		JSONArray re = new JSONArray();
		//�������߰�ı�ʶ�жϣ����Բ�ѯ��Ӧ��sql��䣩
		String biaoshi = indexManager.sysVer;
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TSysFunc where 1=1 ");
		if (StringUtils.isNotBlank(name)) {
			sql.append(" and name like :funcName ");
			data.putLike("funcName", name);
		}
		
		if (StringUtils.isNotBlank(isActive)) {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", isActive);
		}
		// ������ǳ�������Ա��½ȥ������̨���ն˵Ĺ��ܹ����˵�����
		if (!CtxUtil.getUserId().equals("a0000000000000000000000000000000")){
			sql.append(" and id != '" + FuncEnum.WEBFUNC.getCode() + "' and id != '" + FuncEnum.MOFUNC.getCode() + "' ");
		}
		
		sql.append(" order by orderby");
		List<TSysFunc> pos = dao.find(sql.toString(), data);
		JSONObject jsonObj = null;
		for (TSysFunc ele : pos) {
			jsonObj = new JSONObject();
			jsonObj.put("id", ele.getId());
			jsonObj.put("order", ele.getOrderby());
			if("0".equals(biaoshi)){
				if(null != ele.getFunction()){
					if (StringUtils.isBlank(name) && StringUtils.isNotBlank(ele.getFunction().getId())) {
						jsonObj.put("pid", ele.getFunction().getId());
					}
				}
			}else{
				if (StringUtils.isBlank(name) && null != ele.getFunction()) {
					jsonObj.put("pid", ele.getFunction().getId());
				}
			}
			jsonObj.put("name", ele.getName());
			JSONObject dataObject = new JSONObject();
			dataObject.put("title", ele.getName());
			if("0".equals(biaoshi)){
				if(StringUtils.isNotBlank(ele.getDescribe())){
					dataObject.put("note", ele.getDescribe());
				}
			}else{
				dataObject.put("note", ele.getDescribe());
			}
			dataObject.put("link", ele.getLinkAddr());
			dataObject.put("order", ele.getOrderby());
			dataObject.put("isActive", ele.getIsActive().equals(YnEnum.Y.getCode()) ? "��" : "��");
			jsonObj.put("dataObject", dataObject);
			if (log.isDebugEnabled()) {
//				log.debug(ele.getName() + ":" + ele.getOrderby());
			}
			re.put(jsonObj);
		}
		return re;
	}

	/**
	 * ����һ�����ܲ˵�
	 * 
	 * @param funForm
	 *            {@link FunForm}
	 */
	public void saveFunction(FunForm frm) throws AppException {
		try {
			TSysFunc po = null;
			TSysUser user = CtxUtil.getCurUser();
			Date cur = new Date();
			// ������޸�
			if (StringUtils.isNotBlank(frm.getId())) {
				po = (TSysFunc) this.get(TSysFunc.class, frm.getId());
			} else {
				po = new TSysFunc();
				po.setCreateby(user);
				po.setCreated(cur);
			}
			TSysFunc parent = (TSysFunc) this.get(TSysFunc.class, frm.getParent());
			po.setFunction(parent);
			po.setName(frm.getFuncName());
			po.setDescribe(frm.getFuncDesc());
			po.setLinkAddr(frm.getLinkaddr());
			po.setOrderby(Integer.parseInt(frm.getOrderby()));
			po.setIsActive(YnEnum.Y.getCode());
			po.setUpdateby(user);
			po.setUpdated(cur);
			po.setType("0");
			po.setStyle("0");
			this.dao.save(po);
			
			String hsql = "select id from TSysFuncOper where function.id = ?  ";
			List<String> ids = this.dao.find(hsql, frm.getId());
			List<String> webids = new ArrayList<String>();
			
			JSONObject obj = new JSONObject(frm.getData());
			JSONArray arr = obj.getJSONArray("rows");
			for (int i = 0; i < arr.length(); i++) {
				obj = arr.getJSONObject(i);
				String opid = obj.getString("opid");
				
				TSysFuncOper funcOperPo = new TSysFuncOper();
				// ���oid��Ϊ����Ϊ�༭�˲���
				if (StringUtils.isNotBlank(opid)) {
					funcOperPo = (TSysFuncOper) this.get(TSysFuncOper.class, opid);
				} else {
					funcOperPo.setCreateby(user);
					funcOperPo.setCreated(cur);
					funcOperPo.setIsActive(YnEnum.Y.getCode());
				}
				funcOperPo.setUpdateby(user);
				funcOperPo.setUpdated(cur);
				funcOperPo.setFunction(po);
				funcOperPo.setLinkAddr(obj.getString("linkAddr"));
				funcOperPo.setOpername(obj.getString("name"));
				funcOperPo.setOnclickEvent(obj.getString("eventName"));
				funcOperPo.setOrderby(0);
				
				funcOperPo = (TSysFuncOper) this.dao.save(funcOperPo);
				webids.add(funcOperPo.getId());
			}
			
//			this.dao.exec("delete from T_SYS_ROLEFUNCOPER t where t.FUNCOPERID_ not in (select ID_ from T_SYS_FUNCOPER)");
			for (String ele : ids) {
				// ����������еļ�¼û����֮ǰ����ļ�¼�� ˵���˲����ѱ�ɾ��
				if (!webids.contains(ele)) {
					// ɾ����ɫ�������в����ڵĲ�����¼
					this.dao.removeFindObjs("from TSysRoleFuncOper where funcoper.id = ?", ele);
					// ɾ��ҳ���б�ɾ���Ĳ���
					this.dao.remove(TSysFuncOper.class, ele);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ɾ�����ܲ˵���Ϣ
	 * 
	 * @param id
	 *            ���ܲ˵���Ϣ��ID
	 */
	public void removeFunction(String id) throws AppException {
		String hsql = "from TSysFunc where function.id = ?";
		List<TSysFunc> re = this.dao.find(hsql, id);
		if (!re.isEmpty()) {
			throw new AppException("��ǰ�������ӽڵ㣬������ɾ����");
		}
		// ɾ���˵���ص�Ȩ������
		hsql = "from TSysRoleFunc where function.id = ?  ";
		this.dao.removeFindObjs(hsql, id);
		hsql = "from TSysRoleFuncOper where funcoper.id in (select id from TSysFuncOper where function.id = ?)";
		this.dao.removeFindObjs(hsql, id);
		hsql = "from TSysFuncOper where function.id = ?  ";
		this.dao.removeFindObjs(hsql, id);
		this.dao.remove(TSysFunc.class, id);
	}

	/**
	 * ��ѯ��ǰ�˵�����Щ����
	 * 
	 * @return ��ǰ�˵�����Щ����
	 * @throws Exception
	 */
	public List<FunOp> queryOptByFunction(String id) throws Exception {
		List<FunOp> re = new ArrayList<FunOp>();
		FunOp obj = null;
		if (StringUtils.isBlank(id)) {
			String[] names = new String[] { "����", "�޸�", "ɾ��" };
			String[] eventNames = new String[] { "add()", "edit(this)",
					"del(this)", };
			String[] isTableShow = new String[] { YnEnum.N.getCode(), YnEnum.Y.getCode(), YnEnum.Y.getCode(), };
			for (int i = 0; i < names.length; i++) {
				obj = new FunOp();
				obj.setOpid("");
				obj.setEventName(eventNames[i]);
				obj.setName(names[i]);
				obj.setLinkAddr("");
				obj.setIsTableShow(isTableShow[i]);
				re.add(obj);
			}

			return re;
		}
		String hsql = "from TSysFuncOper where function.id = ?";
		List<TSysFuncOper> pos = dao.find(hsql, id);

		for (TSysFuncOper ele : pos) {
			obj = new FunOp();
			obj.setOpid(ele.getId());
			obj.setEventName(StringUtils.defaultIfBlank(ele.getOnclickEvent(),
					""));
			obj.setName(ele.getOpername());
			obj.setLinkAddr(ele.getLinkAddr());
			// obj.setIsTableShow(ele.getFashion());
			if (log.isDebugEnabled()) {
				log.debug("�˵��Ĳ���:" + ToStringBuilder.reflectionToString(obj));
			}
			re.add(obj);
		}
		return re;
	}

	/**
	 * ��ѯ��ǰ�˵�����Щ����
	 * 
	 * @return ��ǰ�˵�����Щ����
	 * @throws Exception
	 */
	public TSysFunc queryFunByRepid(String reportid) throws Exception {
		TSysFunc fun = null;
		String hsql = "from TSysFunc where reportid = ?";
		List<TSysFunc> pos = dao.find(hsql, reportid);
		if (pos.size() > 0) {
			fun = pos.get(0);
		}
		return fun;
	}
}