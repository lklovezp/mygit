/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.area.AreaManager;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysRole;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.po.TSysUserOrg;
import com.hnjz.sys.po.TSysUserRole;

/**
 * �û�����ѡ��İ�����
 * 
 * @author wumi
 * @version $Id: UserPubQueryManager.java, v 0.1 Jan 28, 2013 8:49:46 AM wumi
 *          Exp $
 */
@Service("userPubQueryManager")
public class UserPubQueryManager {

	/** ��־ */
	private static final Log log = LogFactory.getLog(UserPubQueryManager.class);

	@Autowired
	private Dao dao;

	@Autowired
	private UserManager userManager;

	@Autowired
	private OrgManager orgManager;
	
	@Autowired
	private AreaManager areaManager;

	/**
	 * ѡ����Ա
	 * 
	 * @param id
	 *            �û��Ѿ�ѡ�е���Աid����
	 * @param all
	 *            true��ʾ�����û�(�칫�ҿ���ѡ��������Ա)��Ϊ�ջ���������true���ַ��� ��ʾ�Լ����ż��¼����ŵ��û�
	 * @param notShowZj
	 *            true����ʾ�Լ���Ϊ�ջ���������true���ַ��� ���Լ���ʾ����
	 * @return
	 * @throws Exception
	 */
	public JSONArray queryUser(String[] id, String all, String notShowZj, String notShowSys)
			throws Exception {
		boolean isAll = StringUtils.equals(all, "true");
		boolean isNotShowZj = StringUtils.equals(notShowZj, "true");
		boolean isNotShowSys = StringUtils.equals(notShowSys, "true");
		JSONArray re = new JSONArray();
		// ��ǰ�û�
		TSysUser curUser = CtxUtil.getCurUser();
		// ��ǰ�û���������
		TSysOrg curOrg = this.userManager.getOrgbyUser(CtxUtil.getUserId());
		// ��ǰ�û���������
		String areaId = CtxUtil.getAreaId();
		
		String hsql = null;
		
		List<TSysOrg> pos = new ArrayList<TSysOrg>();
		// һ�����Ų�ѯ
		/**  1������adminʱ��ѯϵͳ�����в��� */
		if (curUser.getId().equals("a0000000000000000000000000000000")){
			hsql = "from TSysOrg where isActive=? order by orderby";
			pos = dao.find(hsql, YnEnum.Y.getCode());
		} else {
			/**  2�����Ƿ�admin����ͨ����Աʱ��ѯ�˹���Ա������������в��� */
			if (curUser.getIssys().equals("Y")){
				hsql = "from TSysOrg where isActive=? and area.id = ? order by orderby";
				pos = dao.find(hsql, YnEnum.Y.getCode(), areaId);
			} else {
				/**  3��������ͨ�û�ʱ  �Ȱ�������isAll�Ƿ�ȫ����ʾ��ѯ */
				if (isAll) {
					// ��ѯ���п��õĲ���
					hsql = "from TSysOrg where isActive=? and area.id = ? order by orderby";
					pos = dao.find(hsql, YnEnum.Y.getCode(), areaId);
				} 
				/** 4�������Ƿ��ǲ����쵼��ѯ */
				else {
					// ��ѯ�����쵼�ǵ�ǰ�û��Ĳ���
					List<TSysOrg> orgs = this.dao.find("from TSysOrg where isActive='Y' and leader1.id = ?", curUser.getId());
					/** 4.1�������쵼ʱ��ѯ�������ż��¼����� */
					if (orgs.size() >  0){
						// ��ѯ�ϼ���������Щ���ŵĲ���
						for (int i = 0; i < orgs.size(); i++) {
							List<TSysOrg> lowerOrgs = this.dao.find("from TSysOrg where isActive='Y' and org.id = ?", orgs.get(i).getId());
							pos.addAll(lowerOrgs);
						}
						pos.addAll(orgs);
					} 
					/** 4.2���������쵼ʱֻ��ѯ�¼����� */
					else {
						pos = orgManager.getChs(curOrg.getId());
					}
				}
			}
		}
		
		// �����û����Ź�������ѯ
		List<TSysUserOrg> userOrgs = new ArrayList<TSysUserOrg>();
		// ѭ�����Ų�ѯ�����û���ϵ
		for (int i = 0; i < pos.size(); i++) {
			List<TSysUserOrg> userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and org.id = ? order by user.orderby", pos.get(i).getId());
			userOrgs.addAll(userOrg);
		}

		JSONObject obj = null;
		for (TSysUserOrg ele : userOrgs) {
			// ���Բ���ʾ�Լ�
			if (isNotShowZj && StringUtils.equals(ele.getUser().getId(), CtxUtil.getUserId())) {
				continue;
			}
			// ����ʾ����Ա
			if (isNotShowSys && StringUtils.equals(ele.getUser().getIssys(), YnEnum.Y.getCode())){
				continue;
			}
			obj = new JSONObject();
			obj.put("id", ele.getUser().getId());
			obj.put("pid", ele.getOrg().getId());
			//obj.put("iconSkin", "iconuser");
			obj.put("type", "u");
			obj.put("nocheck", false);
			obj.put("selected", false);
			obj.put("checked", false);
			if (null != id) {
				for (String mx : id) {
					if (StringUtils.equals(mx, ele.getUser().getId())) {
						obj.put("selected", true);
						obj.put("checked", true);
						break;
					}
				}
			}
			obj.put("name", ele.getUser().getName());
			if (log.isDebugEnabled()) {
				log.debug("obj:" + obj);
			}
			re.put(obj);
		}

		for (TSysOrg ele : pos) {
			obj = new JSONObject();
			obj.put("id", ele.getId());

			if (null != ele.getOrg()) {
				obj.put("pid", ele.getOrg().getId());
			}
			if (!isAll && StringUtils.equals(curOrg.getId(), ele.getId())) {
				obj.put("pid", "");
			}
			obj.put("nocheck", true);
			//obj.put("iconSkin", "icondept");
			obj.put("name", ele.getName());
			obj.put("isorg", true);
			re.put(obj);
		}
		return re;
	}

	public JSONArray taskUserPubQuery(String all,String areaid,String displayAll, String showBj, List<String> ids, String notShowZj,String showExist, String condition) {
		JSONArray re = new JSONArray();
		try{
			// ����ʾ�Լ�
			boolean isNotShowZj = StringUtils.equals(notShowZj, "true");
			// �鿴����
			boolean isAll = StringUtils.equals(all, "true");
			boolean isDisplayAll = StringUtils.equals(displayAll, "true");
			boolean isShowExist = StringUtils.equals(showExist, "true");
			//�Ƿ���ʾͬ�����ŵ���
			boolean isShowBj = StringUtils.equals(showBj, "true");
			//�Ƿ���רԱ
			String Zy = "";
			boolean isZy = StringUtils.equals(Zy, "true");
			// ��ǰ�û�
			TSysUser curUser = CtxUtil.getCurUser();
			String area = curUser.getAreaId();
			// ��ǰ�û���������
			TSysOrg curOrg = this.userManager.getOrgbyUser(CtxUtil.getUserId());
			
			List<TSysOrg> pos = new ArrayList<TSysOrg>();
			// �Ƿ��ǲ����쵼
			boolean isLeader = false;
			// ��ѯ��ǰ�û��Ƿ��ǵ�ǰ��ʦ���쵼
			List<TSysOrg> orgs = this.dao.find("from TSysOrg where isActive='Y' and id = ? and leader1.id = ? and type = '0'", curOrg.getId(), curUser.getId());
			if (orgs.size() > 0){
				isLeader = true;
			}
			List<TSysUserRole> xfzydata = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'XFZY')", curUser.getId());
			List<TSysUserRole> rczydata = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'RCZY')", curUser.getId());
			if(xfzydata.size() != 0 || rczydata.size() != 0){
				isZy = true;
			}
			if (isAll){
				if(isDisplayAll){
					/**��ѯ�����������¼����� */
					List<TSysArea> areas = areaManager.getChAreas(area);
					List<TSysOrg> temp = new ArrayList<TSysOrg>();
					for(int i = 0; i < areas.size(); i++){
						temp = this.dao.find("from TSysOrg where isActive='Y' and area.id =? order by orderby",areas.get(i).getId());
						pos.addAll(temp);
					}
				}else{
					if(isLeader){
						isLeader = false;
						pos = this.dao.find("from TSysOrg where isActive='Y' and area.id =? order by orderby",area);
					}else{
						if("0".equals(condition)){
							pos = this.dao.find("from TSysOrg where isActive='Y' and area.id =? order by orderby",area);
						}else{
							if(isZy){
								pos = this.dao.find("from TSysOrg where isActive='Y' and area.id =? and type != '0' order by orderby",area);
							}else{
								pos = this.dao.find("from TSysOrg where isActive='Y' and area.id =? order by orderby",area);
							}
						}
					}
				}
			} else {
				if(StringUtils.isNotBlank(areaid) && !StringUtils.equals(areaid, CtxUtil.getAreaId())){//��ѡ�����Ǳ�����
					/** ���Ӹ����������в��� */
					pos = this.dao.find("from TSysOrg where isActive='Y' and area.id =? order by orderby",areaid);
				}else{
					// һ�����Ų�ѯ
					// ��ѯ��ǰ�û��Ƿ��ǵ�ǰ��ʦ���쵼
					List<TSysOrg> orgsl = this.dao.find("from TSysOrg where isActive='Y' and id = ? and leader1.id = ? ", curOrg.getId(), curUser.getId());
					
					/** 1�������쵼ʱ��ѯ�������ż��¼����� */
					if (orgsl.size() > 0){
						isLeader = true;
						// �����¼�����
						for (int i = 0; i < orgsl.size(); i++) {
							pos = orgManager.getChOrgs(curOrg.getId());
						}
					}
					/** 2�����ǹ���Աʱ��ѯ�������ż��¼�����*/
					else if(isShowBj){
						// �����¼�����
						List<TSysOrg> org = this.dao.find("from TSysOrg where isActive='Y' and id = ? ", curOrg.getId());
						isLeader = true;
						for (int i = 0; org!=null && i < org.size(); i++) {
							pos = orgManager.getChOrgs(curOrg.getId());
						}
					} 
					/** 3���������쵼ʱֻ��ѯ�¼����� */
					else {
						orgManager.getChs(curOrg.getId(), pos);
						TSysOrg o = (TSysOrg) this.dao.get(TSysOrg.class, curOrg.getId());
						pos.add(o);
					}
				}
				
			}
			
			// �����û����Ź�������ѯ
			List<TSysUserOrg> userOrgs = new ArrayList<TSysUserOrg>();
			// ѭ�����Ų�ѯ�����û���ϵ
			List<TSysUserOrg> userOrg = null;
			for (int i = 0; i < pos.size(); i++) {
				if (isAll){
					// ��ѯҵ������Ϊִ������Ա
					userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
					userOrgs.addAll(userOrg);
				} else {
					if (isLeader){
						// ��ѯҵ������Ϊִ������Ա
						userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
						userOrgs.addAll(userOrg);
					} else {
						// ��ѯҵ������Ϊִ������Ա
						if (pos.get(i).getId().equals(curOrg.getId())){
							userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and user.id = ? and org.id = ? order by user.orderby", curUser.getId(), pos.get(i).getId());
							userOrgs.addAll(userOrg);
						} else {
							userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
							userOrgs.addAll(userOrg);
						}
					}
				}
			}
	
			JSONObject obj = null;
			for (TSysUserOrg ele : userOrgs) {
				// ���Բ���ʾ�Լ�
				if (isNotShowZj && StringUtils.equals(ele.getUser().getId(), CtxUtil.getUserId())) {
					continue;
				}
				if(!isShowExist){
					if (ids.contains(ele.getUser().getId())) {
						continue;
					}
				}
				obj = new JSONObject();
				obj.put("id", ele.getUser().getId());
				obj.put("pid", ele.getOrg().getId());
				obj.put("iconSkin", "iconuser");
				obj.put("type", "u");
				obj.put("nocheck", false);
				obj.put("isorg", false);
				if (null != ids) {
					for (String mx : ids) {
						if (StringUtils.equals(mx, ele.getUser().getId())) {
							obj.put("selected", true);
							obj.put("checked", true);
							break;
						}
					}
				}
				obj.put("name", ele.getUser().getName());
				if (log.isDebugEnabled()) {
					log.debug("obj:" + obj);
				}
				re.put(obj);
			}
	
			for (TSysOrg ele : pos) {
				obj = new JSONObject();
				obj.put("id", ele.getId());
				if (null != ele.getOrg()) {
					obj.put("pid", ele.getOrg().getId());
				} else {
					obj.put("pid", "");
				}
				
				obj.put("nocheck", true);
				obj.put("iconSkin", "icondept");
				obj.put("name", ele.getName());
				obj.put("isorg", true);
				re.put(obj);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	private Integer jsonHelp(JSONArray array,String pid,Integer count){
		try {
			for(int i=0;i<array.length();i++){
				JSONObject obj = array.getJSONObject(i);
				if(obj.getString("id").equals(pid) && StringUtils.isNotBlank(obj.getString("pid"))){
					count++;
					count = this.jsonHelp(array, obj.getString("pid"), count);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public JSONArray taskUserPubQueryByRole(String all,String areaid,String displayAll, String showBj, List<String> ids, String notShowZj,String showExist,String group) {
		JSONArray re = new JSONArray();
		try{
			// ����ʾ�Լ�
			boolean isNotShowZj = StringUtils.equals(notShowZj, "true");
			// �鿴����
			boolean isAll = StringUtils.equals(all, "true");
			boolean isDisplayAll = StringUtils.equals(displayAll, "true");
			boolean isShowExist = StringUtils.equals(showExist, "true");
			//�Ƿ���ʾͬ�����ŵ���
			boolean isShowBj = StringUtils.equals(showBj, "true");
			// ��ǰ�û�
			TSysUser curUser = CtxUtil.getCurUser();
			String area = curUser.getAreaId();
			// ��ǰ�û���������
			TSysOrg curOrg = this.userManager.getOrgbyUser(CtxUtil.getUserId());
			
			List<TSysOrg> pos = new ArrayList<TSysOrg>();
			// �Ƿ��ǲ����쵼
			boolean isLeader = false;
			// ��ѯ��ǰ�û��Ƿ��ǵ�ǰ��ʦ���쵼
			List<TSysOrg> orgs = this.dao.find("from TSysOrg where isActive='Y' and id = ? and leader1.id = ? and type = '0'", curOrg.getId(), curUser.getId());
			if (orgs.size() > 0){
				isLeader = true;
			}
			//�ܶӿ���ѡ�����еĲ���
			if (isAll){
				if(isDisplayAll){
					/**��ѯ�����������¼����� */
					List<TSysArea> areas = areaManager.getChAreas(area);
					List<TSysOrg> temp = new ArrayList<TSysOrg>();
					for(int i = 0; i < areas.size(); i++){
						temp = this.dao.find("from TSysOrg where isActive='Y' order by orderby");
						pos.addAll(temp);
					}
				}else{
					pos = this.dao.find("from TSysOrg where isActive='Y' order by orderby");
				}
			} else {
				if(StringUtils.isNotBlank(areaid) && !StringUtils.equals(areaid, CtxUtil.getAreaId())){//��ѡ�����Ǳ�����
					/** ���Ӹ����������в��� */
					pos = this.dao.find("from TSysOrg where isActive='Y' order by orderby");
				}else{
					// һ�����Ų�ѯ
					List<TSysOrg> orgsl = this.dao.find("from TSysOrg where isActive='Y' and id = ? and leader1.id = ?", curOrg.getId(), curUser.getId());
					/** 1�������쵼ʱ��ѯ�������ż��¼����� */
					if (orgsl.size() > 0){
						isLeader = true;
						// �����¼�����
						for (int i = 0; i < orgsl.size(); i++) {
							pos = orgManager.getChOrgs(curOrg.getId());
						}
					}
					/** 2�����ǹ���Աʱ��ѯ�������ż��¼�����*/
					else if(isShowBj){
						// �����¼�����
						List<TSysOrg> org = this.dao.find("from TSysOrg where isActive='Y' and id = ? ", curOrg.getId());
						isLeader = true;
						for (int i = 0; org!=null && i < org.size(); i++) {
							pos = orgManager.getChOrgs(curOrg.getId());
						}
					} 
					/** 3���������쵼ʱֻ��ѯ�¼����� */
					else {
						orgManager.getChs(curOrg.getId(), pos);
						TSysOrg o = (TSysOrg) this.dao.get(TSysOrg.class, curOrg.getId());
						pos.add(o);
					}
				}
			}
			
			// �����û����Ź�������ѯ
			List<TSysUserOrg> userOrgs = new ArrayList<TSysUserOrg>();
			// ѭ�����Ų�ѯ�����û���ϵ
			List<TSysUserOrg> userOrg = null;
			for (int i = 0; i < pos.size(); i++) {
				if (isAll){
					// ��ѯҵ������Ϊִ������Ա
					userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
					userOrgs.addAll(userOrg);
				} else {
					if (isLeader){
						// ��ѯҵ������Ϊִ������Ա
						userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
						userOrgs.addAll(userOrg);
					} else {
						// ��ѯҵ������Ϊִ������Ա
						if (pos.get(i).getId().equals(curOrg.getId())){
							userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and user.id = ? and org.id = ? order by user.orderby", curUser.getId(), pos.get(i).getId());
							userOrgs.addAll(userOrg);
						} else {
							userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
							userOrgs.addAll(userOrg);
						}
					}
				}
			}
			
			//���ӳ��ܶ��ⲿ�ŵ�λ�Լ���Ӧ���ŷ�רԱ
			List<TSysOrg> orglow = null;
			//�ѱ�ע��Ϊ��ʶ���ж�  XFZY �� RCZY�����ѯ�����û�����
			List<TSysRole> roledata = null;
			if("1".equals(group)){
				orglow = this.dao.find("from TSysOrg where isActive='Y' and id in (select org.id from TSysUserOrg u where u.user.id in (select t.user.id from TSysUserRole t where t.role.id in (select id from TSysRole r where r.describe = 'XFZY')))");
				roledata = this.dao.find("from TSysRole where isActive='Y' and describe = 'XFZY'");
			}else if("2".equals(group)){
				orglow = this.dao.find("from TSysOrg where isActive='Y' and id in (select org.id from TSysUserOrg u where u.user.id in (select t.user.id from TSysUserRole t where t.role.id in (select id from TSysRole r where r.describe = 'RCZY')))");
				roledata = this.dao.find("from TSysRole where isActive='Y' and describe = 'RCZY'");
			}
			pos.addAll(orglow);
			for (int i = 0; i < orglow.size(); i++) {
				String xfzyid = roledata.get(0).getId();
				// ��ѯҵ������Ϊִ������Ա
				if (pos.get(i).getId().equals(curOrg.getId())){
					userOrg = this.dao.find("from TSysUserOrg where user.id in(select user.id from TSysUserRole where role.id = ?) and org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and user.id = ? and org.id = ? order by user.orderby", xfzyid, curUser.getId(), pos.get(i).getId());
					userOrgs.addAll(userOrg);
				} else {
					userOrg = this.dao.find("from TSysUserOrg where user.id in(select user.id from TSysUserRole where role.id = ?) and org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", xfzyid, pos.get(i).getId());
					userOrgs.addAll(userOrg);
				}
			}
	
			JSONObject obj = null;
			for (TSysUserOrg ele : userOrgs) {
				// ���Բ���ʾ�Լ�
				if (isNotShowZj && StringUtils.equals(ele.getUser().getId(), CtxUtil.getUserId())) {
					continue;
				}
				if(!isShowExist){
					if (ids.contains(ele.getUser().getId())) {
						continue;
					}
				}
				obj = new JSONObject();
				obj.put("id", ele.getUser().getId());
				obj.put("pid", ele.getOrg().getId());
				obj.put("iconSkin", "iconuser");
				obj.put("type", "u");
				obj.put("nocheck", false);
				obj.put("isorg", false);
				if (null != ids) {
					for (String mx : ids) {
						if (StringUtils.equals(mx, ele.getUser().getId())) {
							obj.put("selected", true);
							obj.put("checked", true);
							break;
						}
					}
				}
				obj.put("name", ele.getUser().getName());
				if (log.isDebugEnabled()) {
					log.debug("obj:" + obj);
				}
				re.put(obj);
			}
			for (TSysOrg ele : pos) {
				obj = new JSONObject();
				obj.put("id", ele.getId());
				if (null != ele.getOrg()) {
					obj.put("pid", ele.getOrg().getId());
				} else {
					obj.put("pid", "");
				}
				obj.put("nocheck", true);
				obj.put("iconSkin", "icondept");
				obj.put("name", ele.getName());
				obj.put("isorg", true);
				re.put(obj);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	public JSONArray taskUserPubQueryByRoleWithout(String all,String areaid,String displayAll, String showBj, List<String> ids, String notShowZj,String showExist,String group) {
		JSONArray re = new JSONArray();
		try{
			// ����ʾ�Լ�
			boolean isNotShowZj = StringUtils.equals(notShowZj, "true");
			// �鿴����
			boolean isAll = StringUtils.equals(all, "true");
			boolean isShowExist = StringUtils.equals(showExist, "true");
			//�Ƿ���ʾͬ�����ŵ���
			// ��ǰ�û�
			TSysUser curUser = CtxUtil.getCurUser();
			// ��ǰ�û���������
			TSysOrg curOrg = this.userManager.getOrgbyUser(CtxUtil.getUserId());
			
			List<TSysOrg> pos = new ArrayList<TSysOrg>();
			// �Ƿ��ǲ����쵼
			boolean isLeader = false;
			
			// �����û����Ź�������ѯ
			List<TSysUserOrg> userOrgs = new ArrayList<TSysUserOrg>();
			// ѭ�����Ų�ѯ�����û���ϵ
			List<TSysUserOrg> userOrg = null;
			for (int i = 0; i < pos.size(); i++) {
				if (isAll){
					// ��ѯҵ������Ϊִ������Ա
					userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
					userOrgs.addAll(userOrg);
				} else {
					if (isLeader){
						// ��ѯҵ������Ϊִ������Ա
						userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
						userOrgs.addAll(userOrg);
					} else {
						// ��ѯҵ������Ϊִ������Ա
						if (pos.get(i).getId().equals(curOrg.getId())){
							userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and user.id = ? and org.id = ? order by user.orderby", curUser.getId(), pos.get(i).getId());
							userOrgs.addAll(userOrg);
						} else {
							userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
							userOrgs.addAll(userOrg);
						}
					}
				}
			}
			
			//���ӳ��ܶ��ⲿ�ŵ�λ�Լ���Ӧ���ŷ�רԱ
			List<TSysOrg> orglow = null;
			TSysOrg orgup = null;
			//�ѱ�ע��Ϊ��ʶ���ж�  XFZY �� RCZY�����ѯ�����û�����
			List<TSysRole> roledata = null;
			if("3".equals(group)){
				orglow = this.dao.find("from TSysOrg where isActive='Y' and id in (select org.id from TSysUserOrg u where u.user.id in (select t.user.id from TSysUserRole t where t.role.id in (select id from TSysRole r where r.describe = 'XFZY')))");
				for (int i = 0; i < orglow.size(); i++) {
					if(orglow.get(i).getOrg() != null){
						orgup = orgManager.getOrg(orglow.get(i).getOrg().getId());
						orglow.add(orgup);
					}
				}
				roledata = this.dao.find("from TSysRole where isActive='Y' and describe = 'XFZY'");
			}else if("4".equals(group)){
				orglow = this.dao.find("from TSysOrg where isActive='Y' and id in (select org.id from TSysUserOrg u where u.user.id in (select t.user.id from TSysUserRole t where t.role.id in (select id from TSysRole r where r.describe = 'RCZY')))");
				for (int i = 0; i < orglow.size(); i++) {
					if(orglow.get(i).getOrg() != null){
						orgup = orgManager.getOrg(orglow.get(i).getOrg().getId());
						orglow.add(orgup);
					}
				}
				roledata = this.dao.find("from TSysRole where isActive='Y' and describe = 'RCZY'");
			}
			//�����ӵ��ظ����ϼ�������ȥ��
            for(int i=0; i < orglow.size();i++){
            	for(int j=orglow.size()-1;j > i;j--){
					if(String.valueOf(orglow.get(i).getId()).equals(String.valueOf(orglow.get(j).getId()))){
						orglow.remove(j);
	                 }
            	}
            }
			pos.addAll(orglow);
			for (int i = 0; i < orglow.size(); i++) {
				String xfzyid = roledata.get(0).getId();
				// ��ѯҵ������Ϊִ������Ա
				if (pos.get(i).getId().equals(curOrg.getId())){
					userOrg = this.dao.find("from TSysUserOrg where user.id in(select user.id from TSysUserRole where role.id = ?) and org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and user.id = ? and org.id = ? order by user.orderby", xfzyid, curUser.getId(), pos.get(i).getId());
					userOrgs.addAll(userOrg);
				} else {
					userOrg = this.dao.find("from TSysUserOrg where user.id in(select user.id from TSysUserRole where role.id = ?) and org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", xfzyid, pos.get(i).getId());
					userOrgs.addAll(userOrg);
				}
			}
			
			JSONObject obj = null;
			for (TSysUserOrg ele : userOrgs) {
				if(!"a0000000000000000000000000000000".equals(ele.getOrg().getId())){
					// ���Բ���ʾ�Լ�
					if (isNotShowZj && StringUtils.equals(ele.getUser().getId(), CtxUtil.getUserId())) {
						continue;
					}
					if(!isShowExist){
						if (ids.contains(ele.getUser().getId())) {
							continue;
						}
					}
					obj = new JSONObject();
					obj.put("id", ele.getUser().getId());
					obj.put("pid", ele.getOrg().getId());
					obj.put("iconSkin", "iconuser");
					obj.put("type", "u");
					obj.put("nocheck", false);
					obj.put("isorg", false);
					if (null != ids) {
						for (String mx : ids) {
							if (StringUtils.equals(mx, ele.getUser().getId())) {
								obj.put("selected", true);
								obj.put("checked", true);
								break;
							}
						}
					}
					obj.put("name", ele.getUser().getName());
					if (log.isDebugEnabled()) {
						log.debug("obj:" + obj);
					}
					re.put(obj);
				}
			}
			for (TSysOrg ele : pos) {
				obj = new JSONObject();
				if(!"a0000000000000000000000000000000".equals(ele.getId())){
					obj.put("id", ele.getId());
					if (null != ele.getOrg()) {
						obj.put("pid", ele.getOrg().getId());
					} else {
						obj.put("pid", "");
					}
					obj.put("nocheck", true);
					obj.put("iconSkin", "icondept");
					obj.put("name", ele.getName());
					obj.put("isorg", true);
					re.put(obj);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
	public JSONArray ZdtaskUserPubQueryByRoleWithout(String all,String areaid,String displayAll, String showBj, List<String> ids, String notShowZj,String showExist,String group) {
		JSONArray re = new JSONArray();
		try{
			// ����ʾ�Լ�
			boolean isNotShowZj = StringUtils.equals(notShowZj, "true");
			// �鿴����
			boolean isAll = StringUtils.equals(all, "true");
			boolean isShowExist = StringUtils.equals(showExist, "true");
			List<TSysOrg> pos = new ArrayList<TSysOrg>();
			//�ܶӿ���ѡ�����еĲ���
			if (isAll){
				List<TSysOrg> temp = new ArrayList<TSysOrg>();
				temp = this.dao.find("from TSysOrg where id = 'a0000000000000000000000000000000' and isActive='Y'");
				pos.addAll(temp);
			}
			
			// �����û����Ź�������ѯ
			List<TSysUserOrg> userOrgs = new ArrayList<TSysUserOrg>();
			// ѭ�����Ų�ѯ�����û���ϵ
			List<TSysUserOrg> userOrg = null;
			for (int i = 0; i < pos.size(); i++) {
				if (isAll){
					// ��ѯҵ������Ϊִ������Ա
					userOrg = this.dao.find("from TSysUserOrg where org.isActive='Y' and user.isActive='Y' and user.bizType = '0' and user.issys = 'N' and org.id = ? order by user.orderby", pos.get(i).getId());
					for(int k=0;k<userOrg.size();k++){
						UserPosition pzd = orgManager.getPosition(userOrg.get(k).getUser().getId());
						if(pzd == UserPosition.ZD){
							userOrg.remove(k);
						}
					}
					userOrgs.addAll(userOrg);
				}
			}
	
			JSONObject obj = null;
			for (TSysUserOrg ele : userOrgs) {
				// ���Բ���ʾ�Լ�
				if (isNotShowZj && StringUtils.equals(ele.getUser().getId(), CtxUtil.getUserId())) {
					continue;
				}
				if(!isShowExist){
					if (ids.contains(ele.getUser().getId())) {
						continue;
					}
				}
				obj = new JSONObject();
				obj.put("id", ele.getUser().getId());
				obj.put("pid", ele.getOrg().getId());
				obj.put("iconSkin", "iconuser");
				obj.put("type", "u");
				obj.put("nocheck", false);
				obj.put("isorg", false);
				if (null != ids) {
					for (String mx : ids) {
						if (StringUtils.equals(mx, ele.getUser().getId())) {
							obj.put("selected", true);
							obj.put("checked", true);
							break;
						}
					}
				}
				obj.put("name", ele.getUser().getName());
				if (log.isDebugEnabled()) {
					log.debug("obj:" + obj);
				}
				re.put(obj);
			}
			for (TSysOrg ele : pos) {
				obj = new JSONObject();
				obj.put("id", ele.getId());
				if (null != ele.getOrg()) {
					obj.put("pid", ele.getOrg().getId());
				} else {
					obj.put("pid", "");
				}
				obj.put("nocheck", true);
				obj.put("iconSkin", "icondept");
				obj.put("name", ele.getName());
				obj.put("isorg", true);
				re.put(obj);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	
}