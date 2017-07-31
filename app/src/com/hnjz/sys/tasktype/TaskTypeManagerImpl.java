/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.tasktype;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.po.TDataLawobjtypetasktype;
import com.hnjz.app.data.po.TDataTasktype;
import com.hnjz.app.data.po.TDataTasktypeandillegaltype;
import com.hnjz.common.AppException;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.po.TSysUser;

/**
 * 任务类型管理的manager
 * 
 * @author wumi
 * @version $Id: TaskTypeManager.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
@Service("taskTypeManagerImpl")
public class TaskTypeManagerImpl extends ManagerImpl implements
		TaskTypeManager {

	/** 日志 */
	private static final Log log = LogFactory
			.getLog(TaskTypeManagerImpl.class);

	@Override
	public JSONArray queryTaskType() throws Exception {
		JSONArray arr = new JSONArray();
		try {
			String hsql = "select id, name, code, pid from TDataTasktype where isActive = 'Y' order by orderby";
			List<Object[]> type = dao.find(hsql);
			// 任务类型
			for (Object[] ele : type) {
				JSONObject obj = new JSONObject();
				obj.put("id", String.valueOf(ele[0]));
				if (StringUtil.isNotBlank(String.valueOf(ele[3]))){
					obj.put("pId", String.valueOf(ele[3]));
				}
				obj.put("name", String.valueOf(ele[1]));
				obj.put("code", String.valueOf(ele[2]));
				obj.put("href", "editTaskType.htm?id=" + String.valueOf(ele[0]));
				if (log.isDebugEnabled()) {
					log.debug("obj:" + obj);
				}
				arr.put(obj);
			}
		} catch (JSONException e) {
			log.error("用户登录查询菜单错误：", e);
		}
		return arr;
	}

	@Override
	public List<ComboboxTree> queryTaskTypeIdTree(String id) {
		List<TDataTasktype> list = null;
		if (StringUtil.isNotBlank(id)){
			list = this.find(" from TDataTasktype where isActive = 'Y' and id != ? order by orderby", id);	
		} else {
			list = this.find(" from TDataTasktype where isActive = 'Y' order by orderby");
		}
		
		return this.taskTypeTreeIdHelp(list, null);
	}
	
	private List<ComboboxTree> taskTypeTreeIdHelp(List<TDataTasktype> list,String pid){
		List<ComboboxTree> listTree = new ArrayList<ComboboxTree>();
		for(TDataTasktype ele : list){
			if((null == pid && null==ele.getPid()) || (null != pid && null != ele.getPid() && pid.equals(ele.getPid())) ){
				ComboboxTree comboboxTree = new ComboboxTree(ele.getId(), ele.getName());
				comboboxTree.setChildren(this.taskTypeTreeIdHelp(list, ele.getId()));
				listTree.add(comboboxTree);
			}
		}
		return listTree;
	}
	
	@Override
	public List<ComboboxTree> queryTaskTypeCodeTree(String id) {
		List<Object[]> list = null;
		list = this.find(" select b.id, b.name, b.code, b.pid from TDataLawobjtypetasktype a, TDataTasktype b where b.isActive = 'Y' and a.lawobjtype = ? and a.tasktypeid = b.code order by b.orderby", id);
		return this.taskTypeTreeCodeHelp(list, null);
	}
	
	private List<ComboboxTree> taskTypeTreeCodeHelp(List<Object[]> list,String pid){
		List<ComboboxTree> listTree = new ArrayList<ComboboxTree>();
		for(Object[] ele : list){
			if((null == pid && null==ele[3]) || (null != pid && null != ele[3] && pid.equals(ele[3])) ){
				ComboboxTree comboboxTree = new ComboboxTree(String.valueOf(ele[2]), String.valueOf(ele[1]));
				comboboxTree.setChildren(this.taskTypeTreeCodeHelp(list, String.valueOf(ele[0])));
				listTree.add(comboboxTree);
			}
		}
		return listTree;
	}
	
	@Override
	public void saveTaskType(TaskTypeForm frm) {
		try {
			TDataTasktype taskTypePo = null;
			TDataTasktypeandillegaltype illegalTypePo = null;
			TDataLawobjtypetasktype lawobjtypePo = null;
			List<TDataLawobjtypetasktype> lawobjtypePos = new ArrayList<TDataLawobjtypetasktype>();
			// 任务类型不能重复
			StringBuilder hsq = new StringBuilder();
			hsq.append("select count(id) from TDataTasktype where isActive = 'Y' and (name = :name or code = :code) ");
			QueryCondition data = new QueryCondition();
			data.put("name", frm.getName());
			data.put("code", frm.getCode());
			long count = (Long) this.dao.find(hsq.toString(), data).get(0);
			if (StringUtils.isNotBlank(frm.getId())){
				taskTypePo = (TDataTasktype)this.get(TDataTasktype.class, frm.getId());
				List<TDataTasktypeandillegaltype> illegalTypePos = this.find("from TDataTasktypeandillegaltype where tasktypeid = ?", frm.getId());
				for (int i = 0; i < illegalTypePos.size(); i++) {
					illegalTypePo = illegalTypePos.get(i);
					this.dao.remove(illegalTypePo);
				}
				lawobjtypePos = this.find("from TDataLawobjtypetasktype where tasktypeid = ?", frm.getCode());
			} else {
				if (count > 0) {
					new AppException("任务类型名称或编号不能重复。");
					return;
				} else {
					taskTypePo = new TDataTasktype();
					illegalTypePo = new TDataTasktypeandillegaltype();
					lawobjtypePo = new TDataLawobjtypetasktype();
				}
			}
			
			TSysUser curUser = CtxUtil.getCurUser();
			Date cur = new Date();
			
			taskTypePo.setName(frm.getName());
			taskTypePo.setCode(frm.getCode());
			taskTypePo.setPid(frm.getPid());
			taskTypePo.setCreated(cur);
			taskTypePo.setCreateby(curUser);
			taskTypePo.setIsActive(YnEnum.Y.getCode());
			taskTypePo.setUpdateby(curUser);
			taskTypePo.setUpdated(cur);
			taskTypePo.setDescribe(frm.getDescribe());
			taskTypePo.setOrderby(frm.getOrderby());
			
			taskTypePo = (TDataTasktype) this.dao.save(taskTypePo);
			List<TDataLawobjtypetasktype> deletePos = new ArrayList<TDataLawobjtypetasktype>();
			//日常办公任务类型无执法对象类型
			if(StringUtils.isNotBlank(frm.getLawobjtype())){
				for (int i = 0; i < frm.getLawobjtype().split(",").length; i++) {
					lawobjtypePo = new TDataLawobjtypetasktype();
					String lawobjtype = frm.getLawobjtype().split(",")[i];
					for (int j = 0; j < lawobjtypePos.size(); j++) {
						// 如果数据库中存在的记录中的执法对象类型、任务类型和前台传过来的一致的话，不需新增记录。
						if (lawobjtypePos.get(j).getLawobjtype().equals(lawobjtype) && lawobjtypePos.get(j).getTasktypeid().equals(frm.getCode())){
							lawobjtypePo = lawobjtypePos.get(j);
							lawobjtypePo.setId(lawobjtypePo.getId());
							break;
						}
					}
					lawobjtypePo.setLawobjtype(frm.getLawobjtype().split(",")[i]);
					lawobjtypePo.setTasktypeid(taskTypePo.getCode());
					if(StringUtils.isBlank(lawobjtypePo.getIsexecchecklist())){
						lawobjtypePo.setIsexecchecklist("0");
					}
					this.dao.save(lawobjtypePo);
				}
			}
			//如果数据库中存在记录，但前台传过来的没有此执法对象类型，先做记录，以供删除。
			for (int j = 0; j < lawobjtypePos.size(); j++) {
				if(StringUtils.isBlank(frm.getLawobjtype()) || StringUtils.isNotBlank(frm.getLawobjtype()) && !frm.getLawobjtype().contains(lawobjtypePos.get(j).getLawobjtype())){
					deletePos.add(lawobjtypePos.get(j));
				}
			}
			// 删除被删除的执法对象
			for (int i = 0; i < deletePos.size(); i++) {
				List<TDataFile> filePo = this.find("from TDataFile where pid = ?", deletePos.get(i).getId());
				for (int j = 0; j < filePo.size(); j++) {
					// 删除文件
					FileUpDownUtil.delFile(FileUpDownUtil.path + UploadFileType.JCD.getPath() + File.separator + filePo.get(i).getOsname());
					this.dao.remove(TDataFile.class, filePo.get(i).getId());
				}
				this.dao.remove(deletePos.get(i));
			}
			if (StringUtil.isNotBlank(frm.getIllegaltype())){
				for (int i = 0; i < frm.getIllegaltype().split(",").length; i++) {
					illegalTypePo = new TDataTasktypeandillegaltype();
					illegalTypePo.setTasktypeid(taskTypePo.getId());
					illegalTypePo.setIllegaltypeid(frm.getIllegaltype().split(",")[i]);
					this.dao.save(illegalTypePo);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public JSONArray taskTypeInfo(TaskTypeForm frm) {
		JSONArray arr = new JSONArray();
		try {
			TDataTasktype po = new TDataTasktype();
			List<TDataTasktypeandillegaltype> illegaltypePo = new ArrayList<TDataTasktypeandillegaltype>();
			List<TDataLawobjtypetasktype> lawobjtypePo = new ArrayList<TDataLawobjtypetasktype>();
			po = (TDataTasktype) this.dao.get(TDataTasktype.class, frm.getId());
			illegaltypePo = this.dao.find("from TDataTasktypeandillegaltype where tasktypeid = ?", po.getId());
			lawobjtypePo = this.dao.find("from TDataLawobjtypetasktype where tasktypeid = ?", po.getCode());
			
			frm.setName(po.getName());
			frm.setPid(po.getPid());
			frm.setCode(po.getCode());
			
			String lawobjs = "";
			String illegaltypes = "";
			for (int i = 0; i < lawobjtypePo.size(); i++) {
				if (i > 0){
					lawobjs += ",";
				}
				lawobjs += lawobjtypePo.get(i).getLawobjtype();
			}
			for (int i = 0; i < illegaltypePo.size(); i++) {
				if (i > 0){
					illegaltypes += ",";
				}
				illegaltypes += illegaltypePo.get(i).getIllegaltypeid();
			}
			frm.setIllegaltype(illegaltypes);
			frm.setLawobjtype(lawobjs);
			frm.setDescribe(po.getDescribe());
			frm.setOrderby(po.getOrderby());
			
			JSONObject obj = new JSONObject();
			obj.put("id", frm.getId());
			obj.put("name", frm.getName());
			obj.put("code", frm.getCode());
			obj.put("pid", frm.getPid());
			obj.put("lawobjtype", lawobjs);
			obj.put("illegaltype", frm.getIllegaltype());
			obj.put("describe", frm.getDescribe());
			obj.put("orderby", frm.getOrderby());
			arr.put(obj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return arr;
	}

//	// 递归查找下级任务类型
//	private void getChs(String id, List<TDataTasktype> pos){
//		List<TDataTasktype> re = this.dao.find("from TDataTasktype where isActive = 'Y' and pid = ?", id);
//		pos.addAll(re);
//		for (TDataTasktype ele : re) {
//			this.getChs(ele.getId(), pos);
//		}
//	}
	
	@Override
	public void removeTaskType(String id) {
//		List<TDataTasktype> pos = new ArrayList<TDataTasktype>();
//		this.getChs(id, pos);
//		// 删除子任务类型
//		for (int i = 0; i < pos.size(); i++) {
//			this.dao.removeFindObjs("from TDataTasktypeandillegaltype where tasktypeid = ?", pos.get(i).getId());
//			this.dao.removeFindObjs("from TDataLawobjtypetasktype where tasktypeid = ?", pos.get(i).getId());
//			
//			this.dao.remove(pos.get(i));
//		}
//		this.dao.removeFindObjs("from TDataTasktypeandillegaltype where tasktypeid = ?", id);
//		this.dao.removeFindObjs("from TDataLawobjtypetasktype where tasktypeid = ?", id);
		
		TDataTasktype del = (TDataTasktype) this.dao.get(TDataTasktype.class, id);
		del.setIsActive(YnEnum.N.getCode());
		this.dao.save(del);
	}
	
	public TDataTasktype getTaskTypeByCode(String code){
		String hsql = "from TDataTasktype where  isActive='Y' and code = ? ";
		List<TDataTasktype> list = dao.find(hsql,code);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
