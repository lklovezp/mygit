package com.hnjz.app.work.danger;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.MidiDevice.Info;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;


import com.hnjz.app.data.po.TDataQyhxwzqkfcp;
import com.hnjz.app.data.po.TDataQyhxwzqkyl;
import com.hnjz.app.data.po.TDataQyhxwzqkzycp;
import com.hnjz.app.work.enums.SbztEnum;
import com.hnjz.app.work.enums.ScfsEnum;
import com.hnjz.app.work.enums.WlztEnum;
import com.hnjz.app.work.enums.YsfsEnum;
import com.hnjz.app.work.po.TDataSgdw;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.sys.po.TSysArea;

@Service("dangerManagerImpl")
public class DangerManagerImpl extends ManagerImpl implements DangerManager{
	private static final Log log=LogFactory.getLog(DangerManagerImpl.class);

	@Override
	public List<Combobox> queryWlStateList(){
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult.add(new Combobox("1","气体"));
		listResult.add(new Combobox("2","液体"));
		listResult.add(new Combobox("3","固体"));
		return listResult;
		
	}

	@Override
	public List<Combobox> ysfs() {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult.add(new Combobox("1","公路"));
		listResult.add(new Combobox("2","铁路"));
		listResult.add(new Combobox("3","水路"));
		listResult.add(new Combobox("4","航空"));
		return listResult;
	}

	@Override
	public List<Combobox> sbState() {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult.add(new Combobox("1","密闭式"));
		listResult.add(new Combobox("2","半密闭式"));
		listResult.add(new Combobox("3","敞开式"));
		return listResult;
	}

	@Override
	public List<Combobox> scType() {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult.add(new Combobox("1","连续式"));
		listResult.add(new Combobox("2","间歇式"));
		return listResult;
	}

	@Override
	public void saveQyhxwzqkzycpForm(QyhxwzqkzycpForm qyhxwzqkzycpForm)
			throws AppException {
		if(StringUtils.isNotBlank(qyhxwzqkzycpForm.getId())){
			TDataQyhxwzqkzycp tDataQyhxwzqkzycp=(TDataQyhxwzqkzycp) this.get(TDataQyhxwzqkzycp.class, qyhxwzqkzycpForm.getId());
			tDataQyhxwzqkzycp.setAreaId(CtxUtil.getAreaId());
			tDataQyhxwzqkzycp.setCas(qyhxwzqkzycpForm.getCas());
			tDataQyhxwzqkzycp.setCkxsl(qyhxwzqkzycpForm.getCkxsl());
			tDataQyhxwzqkzycp.setCreater(CtxUtil.getCurUser());
			tDataQyhxwzqkzycp.setCreateTime(new Date());
			tDataQyhxwzqkzycp.setGnxsl(qyhxwzqkzycpForm.getGnxsl());
			tDataQyhxwzqkzycp.setHxm(qyhxwzqkzycpForm.getHxm());
			tDataQyhxwzqkzycp.setIsActive("Y");
			tDataQyhxwzqkzycp.setPid(qyhxwzqkzycpForm.getPid());
			tDataQyhxwzqkzycp.setSbzt(qyhxwzqkzycpForm.getSbzt());
			tDataQyhxwzqkzycp.setScfs(qyhxwzqkzycpForm.getScfs());
			tDataQyhxwzqkzycp.setScqzcfs(qyhxwzqkzycpForm.getScqzcfs());
			tDataQyhxwzqkzycp.setScqzcl(qyhxwzqkzycpForm.getScqzcl());
			tDataQyhxwzqkzycp.setSjcl(qyhxwzqkzycpForm.getSjcl());
			tDataQyhxwzqkzycp.setSjscnl(qyhxwzqkzycpForm.getSjscnl());
			tDataQyhxwzqkzycp.setSpm(qyhxwzqkzycpForm.getSpm());
			tDataQyhxwzqkzycp.setUpdateby(CtxUtil.getCurUser());
			tDataQyhxwzqkzycp.setUpdateTime(new Date());
			tDataQyhxwzqkzycp.setWlzt(qyhxwzqkzycpForm.getWlzt());
			tDataQyhxwzqkzycp.setWzfl(qyhxwzqkzycpForm.getWzfl());
			tDataQyhxwzqkzycp.setYsfs(qyhxwzqkzycpForm.getYsfs());
			tDataQyhxwzqkzycp.setYt(qyhxwzqkzycpForm.getYt());
			tDataQyhxwzqkzycp.setZcqzcfs(qyhxwzqkzycpForm.getZcqzcfs());
			tDataQyhxwzqkzycp.setZcqzcl(qyhxwzqkzycpForm.getZcqzcl());
			this.dao.save(tDataQyhxwzqkzycp);
		}else{
			TDataQyhxwzqkzycp tDataQyhxwzqkzycp=new TDataQyhxwzqkzycp();
			//tDataQyhxwzqkzycp.setId(qyhxwzqkzycpForm.getId());
			tDataQyhxwzqkzycp.setAreaId(CtxUtil.getAreaId());
			tDataQyhxwzqkzycp.setCas(qyhxwzqkzycpForm.getCas());
			tDataQyhxwzqkzycp.setCkxsl(qyhxwzqkzycpForm.getCkxsl());
			tDataQyhxwzqkzycp.setCreater(CtxUtil.getCurUser());
			tDataQyhxwzqkzycp.setCreateTime(new Date());
			tDataQyhxwzqkzycp.setGnxsl(qyhxwzqkzycpForm.getGnxsl());
			tDataQyhxwzqkzycp.setHxm(qyhxwzqkzycpForm.getHxm());
			tDataQyhxwzqkzycp.setIsActive("Y");
			tDataQyhxwzqkzycp.setPid(qyhxwzqkzycpForm.getPid());
			tDataQyhxwzqkzycp.setSbzt(qyhxwzqkzycpForm.getSbzt());
			tDataQyhxwzqkzycp.setScfs(qyhxwzqkzycpForm.getScfs());
			tDataQyhxwzqkzycp.setScqzcfs(qyhxwzqkzycpForm.getScqzcfs());
			tDataQyhxwzqkzycp.setScqzcl(qyhxwzqkzycpForm.getScqzcl());
			tDataQyhxwzqkzycp.setSjcl(qyhxwzqkzycpForm.getSjcl());
			tDataQyhxwzqkzycp.setSjscnl(qyhxwzqkzycpForm.getSjscnl());
			tDataQyhxwzqkzycp.setSpm(qyhxwzqkzycpForm.getSpm());
			tDataQyhxwzqkzycp.setUpdateby(CtxUtil.getCurUser());
			tDataQyhxwzqkzycp.setUpdateTime(new Date());
			tDataQyhxwzqkzycp.setWlzt(qyhxwzqkzycpForm.getWlzt());
			tDataQyhxwzqkzycp.setWzfl(qyhxwzqkzycpForm.getWzfl());
			tDataQyhxwzqkzycp.setYsfs(qyhxwzqkzycpForm.getYsfs());
			tDataQyhxwzqkzycp.setYt(qyhxwzqkzycpForm.getYt());
			tDataQyhxwzqkzycp.setZcqzcfs(qyhxwzqkzycpForm.getZcqzcfs());
			tDataQyhxwzqkzycp.setZcqzcl(qyhxwzqkzycpForm.getZcqzcl());
			this.dao.save(tDataQyhxwzqkzycp);
		}
	
	}

	@Override
	public FyWebResult whpContentMainList(String pid,String isActive,
			String page, String pageSize) throws AppException {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder(" from TDataQyhxwzqkzycp t where t.isActive='Y' and ");
		if (StringUtils.isNotBlank(pid)) {
			sql.append(" t.pid=:pid");
			data.put("pid", pid);
		}
		sql.append(" order by t.updateTime desc ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		
		List<TDataQyhxwzqkzycp> whyLists = pos.getRe();
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		Map<String, String> dataObject = null;
		for (TDataQyhxwzqkzycp tq : whyLists) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(tq.getId()));
			dataObject.put("pid", String.valueOf(tq.getPid()));
			dataObject.put("spm", String.valueOf(tq.getSpm()));
			dataObject.put("hxm", tq.getHxm()==null?"":String.valueOf(tq.getHxm()));
			dataObject.put("cas", String.valueOf(tq.getCas()));
			dataObject.put("wlzt", String.valueOf(tq.getWlzt()));
			dataObject.put("sjscnl", String.valueOf(tq.getSjscnl()));
			dataObject.put("sjcl", String.valueOf(tq.getSjcl()));
			dataObject.put("yt", String.valueOf(tq.getYt()));
			dataObject.put("gnxsl", String.valueOf(tq.getGnxsl()));
			dataObject.put("ckxsl", String.valueOf(tq.getCkxsl()));
			dataObject.put("ysfs", String.valueOf(tq.getYsfs()));
			dataObject.put("scqzcl", String.valueOf(tq.getScqzcl()));
			dataObject.put("zcqzcl", String.valueOf(tq.getZcqzcl()));
			dataObject.put("scqzcfs", String.valueOf(tq.getScqzcfs()));
			dataObject.put("zcqzcfs", String.valueOf(tq.getZcqzcfs()));
			dataObject.put("sbzt", String.valueOf(tq.getSbzt()));
			dataObject.put("scfs", String.valueOf(tq.getScfs()));
			dataObject.put("isActive", "Y".equals(String.valueOf(tq.getIsActive()))?"有效":"无效");
			//dataObject.put("operate", OperateUtil.getOperate(String.valueOf(tq.getId())));
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate","<a id='"+tq.getId()+"' class='b-link' onclick='info(this)'>查看</a> <a id='"+tq.getId()+"' class='b-link' onclick='modify(this)'>修改</a> <a id='"+tq.getId()+"' class='b-link' onclick='del(this)'>删除</a> ");
			
			
			rows.add(dataObject);
		}
		
		fy.setRows(rows);
		
		return fy;
	}

	@Override
	public QyhxwzqkzycpForm editQyhxwzqkzycpForm(QyhxwzqkzycpForm qyhxwzqkzycpForm) throws AppException {
		TDataQyhxwzqkzycp tq=(TDataQyhxwzqkzycp)this.get(TDataQyhxwzqkzycp.class, qyhxwzqkzycpForm.getId());
		qyhxwzqkzycpForm.setId(tq.getId());
		qyhxwzqkzycpForm.setAreaId(tq.getAreaId());
		qyhxwzqkzycpForm.setCas(tq.getCas());
		qyhxwzqkzycpForm.setCkxsl(tq.getCkxsl());
		qyhxwzqkzycpForm.setGnxsl(tq.getGnxsl());
		qyhxwzqkzycpForm.setHxm(tq.getHxm());
		qyhxwzqkzycpForm.setIsActive(tq.getIsActive());
		qyhxwzqkzycpForm.setPid(tq.getPid());
		qyhxwzqkzycpForm.setSbzt(tq.getSbzt());
		qyhxwzqkzycpForm.setScfs(tq.getScfs());
		qyhxwzqkzycpForm.setScqzcfs(tq.getScqzcfs());
		qyhxwzqkzycpForm.setScqzcl(tq.getScqzcl());
		qyhxwzqkzycpForm.setSjcl(tq.getSjcl());
		qyhxwzqkzycpForm.setSjscnl(tq.getSjscnl());
		qyhxwzqkzycpForm.setSpm(tq.getSpm());
		qyhxwzqkzycpForm.setWlzt(tq.getWlzt());
		qyhxwzqkzycpForm.setWzfl(tq.getWzfl());
		qyhxwzqkzycpForm.setYsfs(tq.getYsfs());
		qyhxwzqkzycpForm.setYt(tq.getYt());
		qyhxwzqkzycpForm.setZcqzcfs(tq.getZcqzcfs());
		qyhxwzqkzycpForm.setZcqzcl(tq.getZcqzcl());
		return qyhxwzqkzycpForm;
	}

	@Override
	public QyhxwzqkzycpForm infoQyhxwzqkzycpForm(QyhxwzqkzycpForm qyhxwzqkzycpForm) throws AppException {
		TDataQyhxwzqkzycp tq=(TDataQyhxwzqkzycp)this.get(TDataQyhxwzqkzycp.class, qyhxwzqkzycpForm.getId());
		qyhxwzqkzycpForm.setId(tq.getId());
		qyhxwzqkzycpForm.setAreaId(tq.getAreaId());
		qyhxwzqkzycpForm.setCas(tq.getCas());
		qyhxwzqkzycpForm.setCkxsl(tq.getCkxsl());
		qyhxwzqkzycpForm.setGnxsl(tq.getGnxsl());
		qyhxwzqkzycpForm.setHxm(tq.getHxm());
		qyhxwzqkzycpForm.setIsActive(tq.getIsActive());
		qyhxwzqkzycpForm.setPid(tq.getPid());
		qyhxwzqkzycpForm.setSbzt(SbztEnum.getNote(tq.getSbzt()));
		qyhxwzqkzycpForm.setScfs(ScfsEnum.getNote(tq.getScfs()));
		qyhxwzqkzycpForm.setScqzcfs(tq.getScqzcfs());
		qyhxwzqkzycpForm.setScqzcl(tq.getScqzcl());
		qyhxwzqkzycpForm.setSjcl(tq.getSjcl());
		qyhxwzqkzycpForm.setSjscnl(tq.getSjscnl());
		qyhxwzqkzycpForm.setSpm(tq.getSpm());
		qyhxwzqkzycpForm.setWlzt(WlztEnum.getNote(tq.getWlzt()));
		qyhxwzqkzycpForm.setWzfl(tq.getWzfl());
		qyhxwzqkzycpForm.setYsfs(YsfsEnum.getNote(tq.getYsfs()));
		qyhxwzqkzycpForm.setYt(tq.getYt());
		qyhxwzqkzycpForm.setZcqzcfs(tq.getZcqzcfs());
		qyhxwzqkzycpForm.setZcqzcl(tq.getZcqzcl());
		return qyhxwzqkzycpForm;
	}
	@Override
	public void removeZycp(String id) throws AppException {
		TDataQyhxwzqkzycp tq=(TDataQyhxwzqkzycp) this.get(TDataQyhxwzqkzycp.class, id);
		tq.setIsActive("N");
		tq.setUpdateby(CtxUtil.getCurUser());
		tq.setUpdateTime(new Date());
		
	}

	@Override
	public void saveQyhxwzqkfcpForm(QyhxwzqkfcpForm qyhxwzqkfcpForm)
			throws AppException {
		if(StringUtils.isNotBlank(qyhxwzqkfcpForm.getId())){
			TDataQyhxwzqkfcp tfcp=(TDataQyhxwzqkfcp) this.get(TDataQyhxwzqkfcp.class,qyhxwzqkfcpForm.getId());
			tfcp.setAreaId(CtxUtil.getAreaId());
			tfcp.setCas(qyhxwzqkfcpForm.getCas());
			tfcp.setCreater(CtxUtil.getCurUser());
			tfcp.setCreateTime(new Date());
			tfcp.setHxm(qyhxwzqkfcpForm.getHxm());
			tfcp.setIsActive("Y");
			tfcp.setPid(qyhxwzqkfcpForm.getPid());
			tfcp.setSpm(qyhxwzqkfcpForm.getSpm());
			tfcp.setUpdateby(CtxUtil.getCurUser());
			tfcp.setUpdateTime(new Date());
			tfcp.setWlzt(qyhxwzqkfcpForm.getWlzt());
			tfcp.setWzfl(qyhxwzqkfcpForm.getWzfl());
			tfcp.setYt(qyhxwzqkfcpForm.getYt());
			this.dao.save(tfcp);
		}else{
			TDataQyhxwzqkfcp tfcp=new TDataQyhxwzqkfcp();
			tfcp.setAreaId(CtxUtil.getAreaId());
			tfcp.setCas(qyhxwzqkfcpForm.getCas());
			tfcp.setCreater(CtxUtil.getCurUser());
			tfcp.setCreateTime(new Date());
			tfcp.setHxm(qyhxwzqkfcpForm.getHxm());
			tfcp.setIsActive("Y");
			tfcp.setPid(qyhxwzqkfcpForm.getPid());
			tfcp.setSpm(qyhxwzqkfcpForm.getSpm());
			tfcp.setUpdateby(CtxUtil.getCurUser());
			tfcp.setUpdateTime(new Date());
			tfcp.setWlzt(qyhxwzqkfcpForm.getWlzt());
			tfcp.setWzfl(qyhxwzqkfcpForm.getWzfl());
			tfcp.setYt(qyhxwzqkfcpForm.getYt());
			this.dao.save(tfcp);
		}
	
		
	}

	@Override
	public FyWebResult whpContentFcpList(String pid, String isActive,
			String page, String pageSize) throws AppException {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder(" from TDataQyhxwzqkfcp t where t.isActive='Y' and ");
		if (StringUtils.isNotBlank(pid)) {
			sql.append(" t.pid=:pid");
			data.put("pid", pid);
		}
		sql.append(" order by t.updateTime desc ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		
		List<TDataQyhxwzqkfcp> whyLists = pos.getRe();
		Map<String, String> dataObject = null;
		for (TDataQyhxwzqkfcp tq : whyLists) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(tq.getId()));
			dataObject.put("pid", String.valueOf(tq.getPid()));
			dataObject.put("spm", String.valueOf(tq.getSpm()));
			dataObject.put("hxm", tq.getHxm()==null?"":String.valueOf(tq.getHxm()));
			dataObject.put("cas", String.valueOf(tq.getCas()));
			dataObject.put("wlzt", String.valueOf(tq.getWlzt()));
			dataObject.put("yt", String.valueOf(tq.getYt()));
			dataObject.put("wzfl", String.valueOf(tq.getWzfl()));
			dataObject.put("isActive", "Y".equals(String.valueOf(tq.getIsActive()))?"有效":"无效");
			//dataObject.put("operate", OperateUtil.getOperate(String.valueOf(tq.getId())));
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate","<a id='"+tq.getId()+"' class='b-link' onclick='infoFcp(this)'>查看</a> <a id='"+tq.getId()+"' class='b-link' onclick='modifyFcp(this)'>修改</a> <a id='"+tq.getId()+"' class='b-link' onclick='delFcp(this)'>删除</a> ");
			
			
			rows.add(dataObject);
		}
		
		fy.setRows(rows);
		
		return fy;
	}

	@Override
	public void removeFcp(String id) throws AppException {
		TDataQyhxwzqkfcp tq=(TDataQyhxwzqkfcp) this.get(TDataQyhxwzqkfcp.class, id);
		tq.setIsActive("N");
		tq.setUpdateby(CtxUtil.getCurUser());
		tq.setUpdateTime(new Date());		
	}

	@Override
	public QyhxwzqkfcpForm editQyhxwzqkfcpForm(QyhxwzqkfcpForm qyhxwzqkfcpForm) throws AppException {
		TDataQyhxwzqkfcp tq=(TDataQyhxwzqkfcp)this.get(TDataQyhxwzqkfcp.class, qyhxwzqkfcpForm.getId());
		qyhxwzqkfcpForm.setId(tq.getId());
		qyhxwzqkfcpForm.setAreaId(tq.getAreaId());
		qyhxwzqkfcpForm.setCas(tq.getCas());
		qyhxwzqkfcpForm.setHxm(tq.getHxm());
		qyhxwzqkfcpForm.setIsActive(tq.getIsActive());
		qyhxwzqkfcpForm.setPid(tq.getPid());
		qyhxwzqkfcpForm.setSpm(tq.getSpm());
		qyhxwzqkfcpForm.setWlzt(tq.getWlzt());
		qyhxwzqkfcpForm.setWzfl(tq.getWzfl());
		qyhxwzqkfcpForm.setYt(tq.getYt());
		
		return qyhxwzqkfcpForm;
	}

	@Override
	public QyhxwzqkfcpForm infoQyhxwzqkfcpForm(QyhxwzqkfcpForm qyhxwzqkfcpForm)	throws AppException {
		TDataQyhxwzqkfcp tq=(TDataQyhxwzqkfcp)this.get(TDataQyhxwzqkfcp.class, qyhxwzqkfcpForm.getId());
		qyhxwzqkfcpForm.setId(tq.getId());
		qyhxwzqkfcpForm.setAreaId(tq.getAreaId());
		qyhxwzqkfcpForm.setCas(tq.getCas());
		qyhxwzqkfcpForm.setHxm(tq.getHxm());
		qyhxwzqkfcpForm.setIsActive(tq.getIsActive());
		qyhxwzqkfcpForm.setPid(tq.getPid());
		qyhxwzqkfcpForm.setSpm(tq.getSpm());
		qyhxwzqkfcpForm.setWlzt(WlztEnum.getNote(tq.getWlzt()));
		qyhxwzqkfcpForm.setWzfl(tq.getWzfl());
		qyhxwzqkfcpForm.setYt(tq.getYt());
		
		return qyhxwzqkfcpForm;
	}

	@Override
	public FyWebResult whpContentYlList(String pid, String isActive,String page, String pageSize) throws AppException {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder(" from TDataQyhxwzqkyl t where t.isActive='Y' and ");
		if (StringUtils.isNotBlank(pid)) {
			sql.append(" t.pid=:pid");
			data.put("pid", pid);
		}
		sql.append(" order by t.updateTime desc ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		
		List<TDataQyhxwzqkyl> whyLists = pos.getRe();
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		Map<String, String> dataObject = null;
		for (TDataQyhxwzqkyl tq : whyLists) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(tq.getId()));
			dataObject.put("pid", String.valueOf(tq.getPid()));
			dataObject.put("zycp", String.valueOf(tq.getZycp()));
			dataObject.put("hxm", tq.getHxm()==null?"":String.valueOf(tq.getHxm()));
			dataObject.put("cas", String.valueOf(tq.getCas()));
			dataObject.put("ylmc", tq.getYlmc()==null?"":String.valueOf(tq.getYlmc()));
			dataObject.put("wlzt", String.valueOf(tq.getWlzt()));
			dataObject.put("syl", String.valueOf(tq.getSyl()));
			dataObject.put("cpdh", String.valueOf(tq.getCpdh()));
			dataObject.put("scqzcl", String.valueOf(tq.getScqzcl()));
			dataObject.put("zcqzcl", String.valueOf(tq.getZcqzcl()));
			dataObject.put("scqzcfs", String.valueOf(tq.getScqzcfs()));
			dataObject.put("zcqzcfs", String.valueOf(tq.getZcqzcfs()));
			dataObject.put("isActive", "Y".equals(String.valueOf(tq.getIsActive()))?"有效":"无效");
			//dataObject.put("operate", OperateUtil.getOperate(String.valueOf(tq.getId())));
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate","<a id='"+tq.getId()+"' class='b-link' onclick='infoYl(this)'>查看</a> <a id='"+tq.getId()+"' class='b-link' onclick='modifyYl(this)'>修改</a> <a id='"+tq.getId()+"' class='b-link' onclick='delYl(this)'>删除</a> ");
			
			
			rows.add(dataObject);
		}
		
		fy.setRows(rows);
		
		return fy;
	}

	@Override
	public void saveQyhxwzqkylForm(QyhxwzqkylForm qyhxwzqkylForm) throws AppException {
		if(StringUtils.isNotBlank(qyhxwzqkylForm.getId())){
			TDataQyhxwzqkyl tq=(TDataQyhxwzqkyl) this.get(TDataQyhxwzqkyl.class, qyhxwzqkylForm.getId());
			tq.setAreaId(qyhxwzqkylForm.getAreaId());
			tq.setCas(qyhxwzqkylForm.getCas());
			tq.setZycp(qyhxwzqkylForm.getZycp());
			tq.setYlmc(qyhxwzqkylForm.getYlmc());
			tq.setSyl(qyhxwzqkylForm.getSyl());
			tq.setCpdh(qyhxwzqkylForm.getCpdh());
			//tq.setCreater(qyhxwzqkylForm.getCreater());
			//tq.setCreateTime(qyhxwzqkylForm.getCreateTime());
			tq.setHxm(qyhxwzqkylForm.getHxm());
			tq.setIsActive("Y");
			tq.setPid(qyhxwzqkylForm.getPid());
			tq.setScqzcfs(qyhxwzqkylForm.getScqzcfs());
			tq.setScqzcl(qyhxwzqkylForm.getScqzcl());
			tq.setUpdateby(CtxUtil.getCurUser());
			tq.setUpdateTime(new Date());
			tq.setWlzt(qyhxwzqkylForm.getWlzt());
			tq.setZcqzcfs(qyhxwzqkylForm.getZcqzcfs());
			tq.setZcqzcl(qyhxwzqkylForm.getZcqzcl());
			this.dao.save(tq);
		}else{
			TDataQyhxwzqkyl tq=new TDataQyhxwzqkyl();
			tq.setAreaId(CtxUtil.getAreaId());
			tq.setCas(qyhxwzqkylForm.getCas());
			tq.setZycp(qyhxwzqkylForm.getZycp());
			tq.setYlmc(qyhxwzqkylForm.getYlmc());
			tq.setSyl(qyhxwzqkylForm.getSyl());
			tq.setCpdh(qyhxwzqkylForm.getCpdh());
			tq.setCreater(CtxUtil.getCurUser());
			tq.setCreateTime(new Date());
			tq.setHxm(qyhxwzqkylForm.getHxm());
			tq.setIsActive("Y");
			tq.setPid(qyhxwzqkylForm.getPid());
			tq.setScqzcfs(qyhxwzqkylForm.getScqzcfs());
			tq.setScqzcl(qyhxwzqkylForm.getScqzcl());
			tq.setUpdateby(CtxUtil.getCurUser());
			tq.setUpdateTime(new Date());
			tq.setWlzt(qyhxwzqkylForm.getWlzt());
			tq.setZcqzcfs(qyhxwzqkylForm.getZcqzcfs());
			tq.setZcqzcl(qyhxwzqkylForm.getZcqzcl());
			this.dao.save(tq);
		}
		
	}

	@Override
	public QyhxwzqkylForm editQyhxwzqkylForm(QyhxwzqkylForm qyhxwzqkylForm)throws AppException {
		TDataQyhxwzqkyl tq=(TDataQyhxwzqkyl) this.get(TDataQyhxwzqkyl.class, qyhxwzqkylForm.getId());
		qyhxwzqkylForm.setAreaId(tq.getAreaId());
		qyhxwzqkylForm.setCas(tq.getCas());
		qyhxwzqkylForm.setZycp(tq.getZycp());
		qyhxwzqkylForm.setYlmc(tq.getYlmc());
		qyhxwzqkylForm.setSyl(tq.getSyl());
		qyhxwzqkylForm.setCpdh(tq.getCpdh());
		qyhxwzqkylForm.setCreateTime(tq.getCreateTime());
		qyhxwzqkylForm.setHxm(tq.getHxm());
		qyhxwzqkylForm.setIsActive(tq.getIsActive());
		qyhxwzqkylForm.setPid(tq.getPid());
		qyhxwzqkylForm.setScqzcfs(tq.getScqzcfs());
		qyhxwzqkylForm.setScqzcl(tq.getScqzcl());
		qyhxwzqkylForm.setUpdateby(tq.getUpdateby());
		qyhxwzqkylForm.setUpdateTime(tq.getUpdateTime());
		qyhxwzqkylForm.setWlzt(tq.getWlzt());
		qyhxwzqkylForm.setZcqzcfs(tq.getZcqzcfs());
		qyhxwzqkylForm.setZcqzcl(tq.getZcqzcl());
		this.dao.save(tq);
		return qyhxwzqkylForm;
	}

	@Override
	public QyhxwzqkylForm infoQyhxwzqkylForm(QyhxwzqkylForm qyhxwzqkylForm) throws AppException {
		TDataQyhxwzqkyl tq=(TDataQyhxwzqkyl) this.get(TDataQyhxwzqkyl.class, qyhxwzqkylForm.getId());
		qyhxwzqkylForm.setAreaId(tq.getAreaId());
		qyhxwzqkylForm.setCas(tq.getCas());
		qyhxwzqkylForm.setZycp(tq.getZycp());
		qyhxwzqkylForm.setYlmc(tq.getYlmc());
		qyhxwzqkylForm.setSyl(tq.getSyl());
		qyhxwzqkylForm.setCpdh(tq.getCpdh());
		qyhxwzqkylForm.setCreateTime(tq.getCreateTime());
		qyhxwzqkylForm.setHxm(tq.getHxm());
		qyhxwzqkylForm.setIsActive(tq.getIsActive());
		qyhxwzqkylForm.setPid(tq.getPid());
		qyhxwzqkylForm.setScqzcfs(tq.getScqzcfs());
		qyhxwzqkylForm.setScqzcl(tq.getScqzcl());
		qyhxwzqkylForm.setUpdateby(tq.getUpdateby());
		qyhxwzqkylForm.setUpdateTime(tq.getUpdateTime());
		qyhxwzqkylForm.setWlzt(WlztEnum.getNote(tq.getWlzt()));
		qyhxwzqkylForm.setZcqzcfs(tq.getZcqzcfs());
		qyhxwzqkylForm.setZcqzcl(tq.getZcqzcl());
		return qyhxwzqkylForm;
	}

	@Override
	public void removeYl(String id) throws AppException {
		TDataQyhxwzqkyl tq=(TDataQyhxwzqkyl) this.get(TDataQyhxwzqkyl.class, id);
		tq.setIsActive("N");
		tq.setUpdateby(CtxUtil.getCurUser());
		tq.setUpdateTime(new Date());	
		
	}
    
	
}
