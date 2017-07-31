package com.hnjz.sys.mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.hnjz.common.AppException;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.sys.po.TSysMobileFunc;
import com.hnjz.sys.po.TSysRole;
import com.hnjz.sys.po.TSysRoleMobileFunc;
import com.hnjz.sys.po.TSysUser;

/**
 * 
 * 手机功能管理
 * 
 * @author Administrator
 * @version $Id: MobileManager.java, v 0.1 Apr 22, 2013 2:46:05 PM Administrator
 *          Exp $
 */
@Service("mobileManagerImpl")
public class MobileManagerImpl extends ManagerImpl implements MobileManager {

	private static final Log log = LogFactory.getLog(MobileManagerImpl.class);

	/**
	 * 手机权限管理，获取所有手机功能，并将角色所有的功能勾上
	 * 
	 * @param roleId
	 *            角色Id
	 * @return
	 */
	public JSONArray queryQx(String roleId) {
		JSONArray re = new JSONArray();
		try {
			String hsql = "select m.id from TSysMobileFunc m, TSysRoleMobileFunc r where r.function.id = m.id and r.role.id = ? order by m.orderby";
			List<String> m = this.dao.find(hsql, roleId);
			hsql = "from TSysMobileFunc order by orderby";
			List<TSysMobileFunc> datas = this.dao.find(hsql);
			JSONObject d = null;
			for (TSysMobileFunc s : datas) {
				d = new JSONObject();
				d.put("id", s.getId());
				d.put("name", s.getName());
				d.put("checked", m.contains(s.getId()));
				if (log.isDebugEnabled()) {
					log.debug(s.getName() + ":" + m.contains(s.getId()));
				}
				if (null != s.getFunction()) {
					d.put("pid", s.getFunction().getId());
				} else {
					d.put("pid", "");
				}
				re.put(d);
			}

		} catch (Exception e) {
			log.error("", e);
		}

		return re;
	}

	/**
	 * 获取用户手机端具有的菜单
	 * 
	 * @return
	 */
	public List<Map<String, String>> queryMo() {
		List<Map<String, String>> re = new ArrayList<Map<String, String>>();
		String hsql = "select m from TSysMobileFunc m, TSysRoleMobileFunc  r where r.function.id = m.id and r.role.id = ? order by m.orderby";
		String roleId = CtxUtil.getCurUser().getRole();
		List<TSysMobileFunc> m = this.dao.find(hsql, roleId);
		Map<String, String> d = null;
		for (TSysMobileFunc s : m) {
			d = new HashMap<String, String>();
			d.put("id", s.getId());
			d.put("name", s.getName());
			d.put("note", s.getDescribe());
			/*
			 * d.put("tupian", s.getPicName()); d.put("tupian1",
			 * s.getPicName1()); d.put("tupian2", s.getPicName2());
			 * d.put("tupian3", s.getPicName3()); d.put("tupian4",
			 * s.getPicName4());
			 */
			d.put("type", s.getStyle());
			d.put("activity", s.getActivity());
			if (null != s.getFunction()) {
				d.put("pid", s.getFunction().getId());
			} else {
				d.put("pid", "");
			}
			if (log.isDebugEnabled()) {
				log.debug("手机端菜单：" + d);
			}
			re.add(d);
		}
		return re;
	}

	/**
	 * 保存手机端权限,保存前先删除角色具有的权限
	 * 
	 * @param mobileId
	 *            手机功能Id
	 * @param roleId
	 *            角色Id
	 */
	public void saveRight(String[] mobileId, String roleId) {
		String hsql = "from TSysRoleMobileFunc where role.id = ?";
		this.dao.removeFindObjs(hsql, roleId);
		for (String ele : mobileId) {
			if (StringUtils.isBlank(ele)) {
				continue;
			}
			TSysRoleMobileFunc m = new TSysRoleMobileFunc();
			TSysRole tSysRole = (TSysRole) this.dao.get(TSysRole.class, roleId);
			m.setRole(tSysRole);
			TSysMobileFunc func = (TSysMobileFunc) this.dao.get(
					TSysMobileFunc.class, ele);
			m.setFunction(func);
			this.dao.save(m);
		}
	}

	/**
	 * 查询手机功能
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public JSONArray queryMobile(String name, String isActive) throws Exception {
		JSONArray ja = new JSONArray();
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TSysMobileFunc where 1=1 ");

		if (StringUtils.isNotBlank(name)) {
			sql.append(" and name like :name ");
			data.putLike("name", name);
		}
		
		if (StringUtils.isNotBlank(isActive)) {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", isActive);
		}
		sql.append(" order by orderby desc");
		List<TSysMobileFunc> pos = dao.find(sql.toString(), data);
		JSONObject jsonObj = null;
		for (TSysMobileFunc m : pos) {
			jsonObj = new JSONObject();
			jsonObj.put("id", m.getId());
			if (StringUtils.isBlank(name) && null != m.getFunction()) {
				jsonObj.put("pid", m.getFunction().getId());
			}
			jsonObj.put("name", m.getName());
			JSONObject dataObject = new JSONObject();
			dataObject.put("name", m.getName());
			dataObject.put("description", m.getDescribe());
			dataObject.put("displayType", DisplayType.getNote(m.getStyle()));
			dataObject.put("orderby", m.getOrderby());
			dataObject.put("isActive", YnEnum.getNote(m.getIsActive()));
			dataObject.put("operate", OperateUtil.getOperate(m.getId()));
			jsonObj.put("dataObject", dataObject);
			ja.put(jsonObj);
		}
		return ja;
	}

	/**
	 * 
	 * 保存手机功能
	 * 
	 * @param form
	 * @throws AppException
	 */
	public void saveMobile(MobileForm form) throws AppException {
		TSysMobileFunc po = null;
		TSysUser user = CtxUtil.getCurUser();
		Date cur = new Date();
		if (StringUtils.isNotBlank(form.getId())) {
			po = (TSysMobileFunc) this.get(TSysMobileFunc.class, form.getId());
		} else {
			po = new TSysMobileFunc();
			po.setCreateby(user);
			po.setCreated(cur);
		}
		po.setName(form.getName());
		po.setDescribe(form.getDescribe());
		if (StringUtils.isNotBlank(form.getPid())) {
			TSysMobileFunc parent = (TSysMobileFunc) this.get(
					TSysMobileFunc.class, form.getPid());
			po.setFunction(parent);
		}
		po.setOrderby(form.getOrderby());
		po.setStyle(form.getStyle());
		po.setActivity(form.getActivity());
		if (StringUtils.isNotBlank(form.getIsActive())) {
			po.setIsActive(YnEnum.Y.getCode());
		} else {
			po.setIsActive(YnEnum.N.getCode());
		}
		po.setUpdateby(user);
		po.setUpdated(cur);
		this.dao.save(po);
	}

	/**
	 * @return 列表
	 * @throws Exception
	 */
	public JSONArray querySelectMobile(String id) throws Exception {
		JSONArray re = new JSONArray();
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TSysMobileFunc where 1=1 order by orderby ");
		List<TSysMobileFunc> pos = dao.find(sql.toString(), data);
		JSONObject jsonObj = null;
		for (TSysMobileFunc ele : pos) {
			jsonObj = new JSONObject();
			jsonObj.put("id", ele.getId());
			if (null != ele.getFunction()) {
				jsonObj.put("pid", ele.getFunction().getId());
			}
			jsonObj.put("selected", StringUtils.equals(id, ele.getId()));
			jsonObj.put("name", ele.getName());
			re.put(jsonObj);
		}
		return re;
	}

	/**
	 * 删除手机功能信息
	 * 
	 * @param id
	 */
	public void removeMobile(String id) throws AppException {
		String hsql = "from TSysMobileFunc where function.id = ?";
		List<TSysMobileFunc> re = this.dao.find(hsql, id);
		if (!re.isEmpty()) {
			throw new AppException("当前功能有子节点，不允许删除。");
		}
		hsql = "from TSysRoleMobileFunc  where function.id = ?";
		this.dao.removeFindObjs(hsql, id);
		this.dao.remove(TSysMobileFunc.class, id);
	}

	@Override
	public JSONArray queryMobileMenu() {
		JSONArray arr = new JSONArray();
        try {
            String role = CtxUtil.getCurUser().getId();
            String hsql = "select function.id,function.name,function.function.id, function.activity from TSysRoleMobileFunc where role.id in(select role.id from TSysUserRole where user.id = ?) order by function.orderby";
            List<Object[]> re = dao.find(hsql, role);
	        //角色的权限有重复的时候过滤掉重复的菜单项
            for(int i=0; i < re.size();i++){
            	for(int j=re.size()-1;j > i;j--){
					if(String.valueOf(re.get(i)[0]).equals(String.valueOf(re.get(j)[0]))){
	                    re.remove(j);
	                 }
            	}
            }
            for (Object[] ele : re) {
                JSONObject obj = new JSONObject();
                obj.put("id", String.valueOf(ele[0]));
                obj.put("name", String.valueOf(ele[1]));
                if (null != ele[2]) {
                    obj.put("pId", String.valueOf(ele[2]));
                }else{
                	obj.put("pId", "");
                }
                obj.put("activity", String.valueOf(ele[3] == null ? "" : ele[3]));
                arr.put(obj);
            }
            for(int i=0;i<arr.length();i++){
            	JSONObject obj = arr.getJSONObject(i);
            	String pid = obj.getString("pId");
            	if(StringUtils.isNotBlank(pid)){
            		Integer count = 2;
            		obj.put("level", this.jsonHelp(arr, pid, count));
            	}else{
            		obj.put("level", 1);
            	}
            }
        } catch (JSONException e) {
            log.error("用户登录查询菜单错误：", e);
        }
        return arr;
	}
	
	private Integer jsonHelp(JSONArray array,String pid,Integer count){
		try {
			for(int i=0;i<array.length();i++){
				JSONObject obj = array.getJSONObject(i);
				if(obj.getString("id").equals(pid) && StringUtils.isNotBlank(obj.getString("pId"))){
					count++;
					count = this.jsonHelp(array, obj.getString("pId"), count);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return count;
	}
}
