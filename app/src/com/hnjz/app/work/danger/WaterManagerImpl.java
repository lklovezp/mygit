package com.hnjz.app.work.danger;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.po.TDataQyhxwzqkzycp;
import com.hnjz.app.data.po.TDataShjjbzk;
import com.hnjz.app.data.po.TDataShjmbfb;
import com.hnjz.app.work.enums.DbsEnum;
import com.hnjz.app.work.enums.HsEnum;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
@Service("waterManagerImpl")
public class WaterManagerImpl extends ManagerImpl implements WaterManager {
	
	@Override
	public List<Combobox> dbsList() {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult.add(new Combobox("1","Ⅰ类 "));
		listResult.add(new Combobox("2","Ⅱ类"));
		listResult.add(new Combobox("3","Ⅲ类"));
		listResult.add(new Combobox("4","Ⅳ类"));
		listResult.add(new Combobox("5","Ⅴ类"));
		return listResult;
	}

	@Override
	public List<Combobox> hsList() {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult.add(new Combobox("1","第一类"));
		listResult.add(new Combobox("2","第二类 "));
		listResult.add(new Combobox("3","第三类 "));
		listResult.add(new Combobox("4","第四类"));
		return listResult;
	}

	@Override
	public List<Combobox> qjhsList() {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult.add(new Combobox("1","第一类"));
		listResult.add(new Combobox("2","第二类 "));
		listResult.add(new Combobox("3","第三类 "));
		listResult.add(new Combobox("4","第四类"));
		return listResult;
	}

	@Override
	public List<Combobox> qjdbsList() {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult.add(new Combobox("1","Ⅰ类 "));
		listResult.add(new Combobox("2","Ⅱ类"));
		listResult.add(new Combobox("3","Ⅲ类"));
		listResult.add(new Combobox("4","Ⅳ类"));
		listResult.add(new Combobox("5","Ⅴ类"));
		return listResult;
	}

	@Override
	public void saveWaterForm(WaterForm waterForm) throws AppException {
		if(StringUtils.isNotBlank(waterForm.getId())){
			TDataShjjbzk ts=(TDataShjjbzk) this.get(TDataShjjbzk.class, waterForm.getId());
			ts.setAreaId(CtxUtil.getAreaId());
			ts.setDbs(waterForm.getDbs());
			ts.setHs(waterForm.getHs());
			ts.setCreater(CtxUtil.getCurUser());
			ts.setCreateTime(new Date());
			ts.setQjdbs(waterForm.getQjdbs());
			ts.setQjhs(waterForm.getQjhs());
			ts.setIsActive("Y");
			ts.setPid(waterForm.getPid());
			ts.setQjstdm(waterForm.getQjstdm());
			ts.setQjstmc(waterForm.getQjstmc());
			ts.setQxlxdm(waterForm.getQxlxdm());
			ts.setStdm(waterForm.getStdm());
			ts.setStmc(waterForm.getStmc());
			ts.setUpdateby(CtxUtil.getCurUser());
			ts.setUpdateTime(new Date());
			this.dao.save(ts);
		}else{
			TDataShjjbzk ts=new TDataShjjbzk();
			//tDataQyhxwzqkzycp.setId(qyhxwzqkzycpForm.getId());
			ts.setAreaId(CtxUtil.getAreaId());
			ts.setDbs(waterForm.getDbs());
			ts.setHs(waterForm.getHs());
			ts.setCreater(CtxUtil.getCurUser());
			ts.setCreateTime(new Date());
			ts.setQjdbs(waterForm.getQjdbs());
			ts.setQjhs(waterForm.getQjhs());
			ts.setIsActive("Y");
			ts.setPid(waterForm.getPid());
			ts.setQjstdm(waterForm.getQjstdm());
			ts.setQjstmc(waterForm.getQjstmc());
			ts.setQxlxdm(waterForm.getQxlxdm());
			ts.setStdm(waterForm.getStdm());
			ts.setStmc(waterForm.getStmc());
			ts.setUpdateby(CtxUtil.getCurUser());
			ts.setUpdateTime(new Date());
			this.dao.save(ts);
		}
	
		
	}

	@Override
	public FyWebResult waterList(String pid, String isActive, String page,String pageSize) throws AppException {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder(" from TDataShjjbzk t where t.isActive='Y' and ");
		if (StringUtils.isNotBlank(pid)) {
			sql.append(" t.pid=:pid");
			data.put("pid", pid);
		}
		sql.append(" order by t.updateTime desc ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		
		List<TDataShjjbzk> whyLists = pos.getRe();
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		Map<String, String> dataObject = null;
		for (TDataShjjbzk tq : whyLists) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(tq.getId()));
			dataObject.put("pid", String.valueOf(tq.getPid()));
			dataObject.put("qxlxdm", String.valueOf(tq.getQxlxdm()));
			dataObject.put("stmc", tq.getStmc()==null?"":String.valueOf(tq.getStmc()));
			dataObject.put("stdm", String.valueOf(tq.getStdm()));
			dataObject.put("dbs", String.valueOf(tq.getDbs()));
			dataObject.put("hs", String.valueOf(tq.getHs()));
			dataObject.put("qjstmc", tq.getQjstmc()==null?"":String.valueOf(tq.getQjstmc()));
			dataObject.put("qjstdm", String.valueOf(tq.getQjstdm()));
			dataObject.put("qjdbs", String.valueOf(tq.getQjdbs()));
			dataObject.put("qjhs", String.valueOf(tq.getQjhs()));
			dataObject.put("isActive", "Y".equals(String.valueOf(tq.getIsActive()))?"有效":"无效");
			//dataObject.put("operate", OperateUtil.getOperate(String.valueOf(tq.getId())));
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate","<a id='"+tq.getId()+"' class='b-link' onclick='infoWater(this)'>查看</a> <a id='"+tq.getId()+"' class='b-link' onclick='modifyWater(this)'>修改</a> <a id='"+tq.getId()+"' class='b-link' onclick='delWater(this)'>删除</a> ");
			
			
			rows.add(dataObject);
		}
		
		fy.setRows(rows);
		
		return fy;
	}

	@Override
	public WaterForm editWaterForm(WaterForm waterForm) throws AppException {
		TDataShjjbzk ts=(TDataShjjbzk) this.get(TDataShjjbzk.class, waterForm.getId());
         waterForm.setAreaId(ts.getAreaId());
         waterForm.setCreater(ts.getCreater());
         waterForm.setCreateTime(ts.getCreateTime());
         waterForm.setDbs(ts.getDbs());
         waterForm.setHs(ts.getHs());
         waterForm.setIsActive(ts.getIsActive());
         waterForm.setPid(ts.getPid());
         waterForm.setQjdbs(ts.getQjdbs());
         waterForm.setQjhs(ts.getQjhs());
         waterForm.setQjstdm(ts.getQjstdm());
         waterForm.setQjstmc(ts.getQjstmc());
         waterForm.setQjstdm(ts.getQjstdm());
         waterForm.setQxlxdm(ts.getQxlxdm());
         waterForm.setStdm(ts.getStdm());
         waterForm.setStmc(ts.getStmc());
         waterForm.setUpdateby(ts.getUpdateby());
         waterForm.setUpdateTime(ts.getUpdateTime());
         
		return waterForm;
	}

	@Override
	public WaterForm infoWaterForm(WaterForm waterForm) throws AppException {
		TDataShjjbzk ts=(TDataShjjbzk) this.get(TDataShjjbzk.class, waterForm.getId());
        waterForm.setAreaId(ts.getAreaId());
        waterForm.setCreater(ts.getCreater());
        waterForm.setCreateTime(ts.getCreateTime());
        waterForm.setDbs(DbsEnum.getNote(ts.getDbs()));
        waterForm.setHs(HsEnum.getNote(ts.getHs()));
        waterForm.setIsActive(ts.getIsActive());
        waterForm.setPid(ts.getPid());
        waterForm.setQjdbs(DbsEnum.getNote(ts.getQjdbs()));
        waterForm.setQjhs(HsEnum.getNote(ts.getQjhs()));
        waterForm.setQjstdm(ts.getQjstdm());
        waterForm.setQjstmc(ts.getQjstmc());
        waterForm.setQjstdm(ts.getQjstdm());
        waterForm.setQxlxdm(ts.getQxlxdm());
        waterForm.setStdm(ts.getStdm());
        waterForm.setStmc(ts.getStmc());
        waterForm.setUpdateby(ts.getUpdateby());
        waterForm.setUpdateTime(ts.getUpdateTime());
        
		return waterForm;
	}

	@Override
	public void removeWater(String id) throws AppException {
		TDataShjjbzk ts=(TDataShjjbzk) this.get(TDataShjjbzk.class,id);
		ts.setIsActive("N");
		ts.setUpdateby(CtxUtil.getCurUser());
		ts.setUpdateTime(new Date());
		
	}

	@Override
	public void saveWaterProjectForm(WaterProjectForm waterProjectForm)
			throws AppException {
		if(StringUtils.isNotBlank(waterProjectForm.getId())){
			TDataShjmbfb ts=(TDataShjmbfb) this.get(TDataShjmbfb.class, waterProjectForm.getId());
			ts.setAreaId(CtxUtil.getAreaId());
			ts.setBhmbmc(waterProjectForm.getBhmbmc());
			ts.setJd(waterProjectForm.getJd());
			ts.setJl(waterProjectForm.getJl());
			ts.setLx(waterProjectForm.getLx());
			ts.setSl(waterProjectForm.getSl());
			ts.setSxhjgn(waterProjectForm.getSxhjgn());
			ts.setWd(waterProjectForm.getWd());
			ts.setCreater(CtxUtil.getCurUser());
			ts.setCreateTime(new Date());
			ts.setIsActive("Y");
			ts.setPid(waterProjectForm.getPid());
			ts.setUpdateby(CtxUtil.getCurUser());
			ts.setUpdateTime(new Date());
			this.dao.save(ts);
		}else{
			TDataShjmbfb ts=new TDataShjmbfb();
			//tDataQyhxwzqkzycp.setId(qyhxwzqkzycpForm.getId());
			ts.setAreaId(CtxUtil.getAreaId());
			ts.setBhmbmc(waterProjectForm.getBhmbmc());
			ts.setJd(waterProjectForm.getJd());
			ts.setJl(waterProjectForm.getJl());
			ts.setLx(waterProjectForm.getLx());
			ts.setSl(waterProjectForm.getSl());
			ts.setSxhjgn(waterProjectForm.getSxhjgn());
			ts.setWd(waterProjectForm.getWd());
			ts.setCreater(CtxUtil.getCurUser());
			ts.setCreateTime(new Date());
			ts.setIsActive("Y");
			ts.setPid(waterProjectForm.getPid());
			ts.setUpdateby(CtxUtil.getCurUser());
			ts.setUpdateTime(new Date());
			this.dao.save(ts);
		}
	
		
	}

	@Override
	public FyWebResult waterProjectList(String pid, String isActive,
			String page, String pageSize) throws AppException {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder(" from TDataShjmbfb t where t.isActive='Y' and ");
		if (StringUtils.isNotBlank(pid)) {
			sql.append(" t.pid=:pid");
			data.put("pid", pid);
		}
		sql.append(" order by t.updateTime desc ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		
		List<TDataShjmbfb> whyLists = pos.getRe();
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		Map<String, String> dataObject = null;
		for (TDataShjmbfb tq : whyLists) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(tq.getId()));
			dataObject.put("pid", String.valueOf(tq.getPid()));
			dataObject.put("bhmbmc", String.valueOf(tq.getBhmbmc()));
			dataObject.put("lx",tq.getLx()==null?"":String.valueOf(tq.getLx()));
			dataObject.put("sl",tq.getSl()==null?"":String.valueOf(tq.getSl()));
			dataObject.put("jd", String.valueOf(tq.getJd()));
			dataObject.put("wd", String.valueOf(tq.getWd()));
			dataObject.put("jl", String.valueOf(tq.getJl()));
			dataObject.put("sxhjgn", String.valueOf(tq.getSxhjgn()));
			dataObject.put("isActive", "Y".equals(String.valueOf(tq.getIsActive()))?"有效":"无效");
			//dataObject.put("operate", OperateUtil.getOperate(String.valueOf(tq.getId())));
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate","<a id='"+tq.getId()+"' class='b-link' onclick='infoWaterProject(this)'>查看</a> <a id='"+tq.getId()+"' class='b-link' onclick='modifyWaterProject(this)'>修改</a> <a id='"+tq.getId()+"' class='b-link' onclick='delWaterProject(this)'>删除</a> ");
			
			
			rows.add(dataObject);
		}
		
		fy.setRows(rows);
		
		return fy;
	}

	@Override
	public WaterProjectForm editWaterProjectForm(WaterProjectForm waterProjectForm)
			throws AppException {
		TDataShjmbfb ts=(TDataShjmbfb) this.get(TDataShjmbfb.class, waterProjectForm.getId());
        waterProjectForm.setAreaId(ts.getAreaId());
        waterProjectForm.setBhmbmc(ts.getBhmbmc());
        waterProjectForm.setCreater(ts.getCreater());
        waterProjectForm.setCreateTime(ts.getCreateTime());
        waterProjectForm.setIsActive(ts.getIsActive());
        waterProjectForm.setJd(ts.getJd());
        waterProjectForm.setJl(ts.getJl());
        waterProjectForm.setLx(ts.getLx());
        waterProjectForm.setPid(ts.getPid());
        waterProjectForm.setSl(ts.getSl());
        waterProjectForm.setSxhjgn(ts.getSxhjgn());
        waterProjectForm.setUpdateby(ts.getUpdateby());
        waterProjectForm.setUpdateTime(ts.getUpdateTime());
        waterProjectForm.setVersion(ts.getVersion());
        waterProjectForm.setWd(ts.getWd());
		return waterProjectForm;
	}

	@Override
	public WaterProjectForm infoWaterProjectForm(WaterProjectForm waterProjectForm) throws AppException {
		TDataShjmbfb ts=(TDataShjmbfb) this.get(TDataShjmbfb.class, waterProjectForm.getId());
        waterProjectForm.setAreaId(ts.getAreaId());
        waterProjectForm.setBhmbmc(ts.getBhmbmc());
        waterProjectForm.setCreater(ts.getCreater());
        waterProjectForm.setCreateTime(ts.getCreateTime());
        waterProjectForm.setIsActive(ts.getIsActive());
        waterProjectForm.setJd(ts.getJd());
        waterProjectForm.setJl(ts.getJl());
        waterProjectForm.setLx(ts.getLx());
        waterProjectForm.setPid(ts.getPid());
        waterProjectForm.setSl(ts.getSl());
        waterProjectForm.setSxhjgn(ts.getSxhjgn());
        waterProjectForm.setUpdateby(ts.getUpdateby());
        waterProjectForm.setUpdateTime(ts.getUpdateTime());
        waterProjectForm.setVersion(ts.getVersion());
        waterProjectForm.setWd(ts.getWd());
		return waterProjectForm;
	}

	@Override
	public void removeWaterProject(String id) throws AppException {
		TDataShjmbfb ts=(TDataShjmbfb) this.get(TDataShjmbfb.class,id);
		ts.setIsActive("N");
		ts.setUpdateby(CtxUtil.getCurUser());
		ts.setUpdateTime(new Date());
		
	}
	

}
