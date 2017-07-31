package com.hnjz.app.work.danger;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.po.TDataQyhjfxffcs;
import com.hnjz.app.data.po.TDataQyhxwzqkyl;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;

@Service("hjfxffcsManagerImpl")
public class HjfxffcsManagerImpl extends ManagerImpl implements HjfxffcsManager{
	private static final Log log=LogFactory.getLog(HjfxffcsManagerImpl.class);

	@Override
	public void saveHjfxffcsForm(HjfxffcsForm hjfxffcsForm) throws AppException {
		if(StringUtils.isNotBlank(hjfxffcsForm.getId())){
			TDataQyhjfxffcs tq=(TDataQyhjfxffcs) this.get(TDataQyhjfxffcs.class, hjfxffcsForm.getId());
			tq.setAreaId(CtxUtil.getAreaId());
			tq.setBjxt("1".equals(hjfxffcsForm.getBjxt())?"1":"0");
			tq.setDmfs("1".equals(hjfxffcsForm.getDmfs())?"1":"0");
			tq.setDmfscl(hjfxffcsForm.getDmfscl());
			tq.setFxdymc(hjfxffcsForm.getFxdymc());
			tq.setGwgy(hjfxffcsForm.getGwgy());
			tq.setHcc(hjfxffcsForm.getHcc());
			tq.setHxwzyxl(hjfxffcsForm.getHxwzyxl());
			tq.setPfqx(hjfxffcsForm.getPfqx());
			tq.setQhfm(hjfxffcsForm.getQhfm());
			tq.setQt(hjfxffcsForm.getQt());
			tq.setSgyjcrj(hjfxffcsForm.getSgyjcrj());
			tq.setSgyjc("1".equals(hjfxffcsForm.getSgyjc())?"1":"0");
			tq.setWcjkw(hjfxffcsForm.getWcjkw());
			tq.setWcjkw("Y".equals(hjfxffcsForm.getWcjkw())?"Y":"N");
			tq.setWy("1".equals(hjfxffcsForm.getWy())?"1":"0");
			tq.setXcl(hjfxffcsForm.getXcl());
			tq.setXszz(hjfxffcsForm.getXszz());
			tq.setYryb(hjfxffcsForm.getYryb());
			tq.setYxrj(hjfxffcsForm.getYxrj());
			tq.setZdccl(hjfxffcsForm.getZdccl());
			tq.setZyhxwzmc(hjfxffcsForm.getZyhxwzmc());
			tq.setZypxgg("1".equals(hjfxffcsForm.getZypxgg())?"1":"0");
			tq.setCreater(CtxUtil.getCurUser());
			tq.setCreateTime(new Date());
			tq.setIsActive("Y");
			tq.setPid(hjfxffcsForm.getPid());
			tq.setUpdateby(CtxUtil.getCurUser());
			tq.setUpdateTime(new Date());
			
			this.dao.save(tq);
		}else{
			TDataQyhjfxffcs tq=new TDataQyhjfxffcs();
			tq.setAreaId(CtxUtil.getAreaId());
			tq.setBjxt("1".equals(hjfxffcsForm.getBjxt())?"1":"0");
			tq.setDmfs("1".equals(hjfxffcsForm.getDmfs())?"1":"0");
			tq.setDmfscl(hjfxffcsForm.getDmfscl());
			tq.setFxdymc(hjfxffcsForm.getFxdymc());
			tq.setGwgy(hjfxffcsForm.getGwgy());
			tq.setHcc(hjfxffcsForm.getHcc());
			tq.setHxwzyxl(hjfxffcsForm.getHxwzyxl());
			tq.setPfqx(hjfxffcsForm.getPfqx());
			tq.setQhfm(hjfxffcsForm.getQhfm());
			tq.setQt(hjfxffcsForm.getQt());
			tq.setSgyjc("1".equals(hjfxffcsForm.getSgyjc())?"1":"0");
			tq.setSgyjcrj(hjfxffcsForm.getSgyjcrj());
			tq.setWcjkw("Y".equals(hjfxffcsForm.getWcjkw())?"Y":"N");
			tq.setWy("1".equals(hjfxffcsForm.getWy())?"1":"0");
			tq.setXcl(hjfxffcsForm.getXcl());
			tq.setXszz(hjfxffcsForm.getXszz());
			tq.setYryb(hjfxffcsForm.getYryb());
			tq.setYxrj(hjfxffcsForm.getYxrj());
			tq.setZdccl(hjfxffcsForm.getZdccl());
			tq.setZyhxwzmc(hjfxffcsForm.getZyhxwzmc());
			tq.setZypxgg("1".equals(hjfxffcsForm.getZypxgg())?"1":"0");
			tq.setCreater(CtxUtil.getCurUser());
			tq.setCreateTime(new Date());
			tq.setIsActive("Y");
			tq.setPid(hjfxffcsForm.getPid());
			tq.setUpdateby(CtxUtil.getCurUser());
			tq.setUpdateTime(new Date());
			this.dao.save(tq);
		}
		
	}

	@Override
	public FyWebResult hjfxffcsList(String pid, String isActive, String page,
			String pageSize) throws AppException {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder(" from TDataQyhjfxffcs t where t.isActive='Y' and ");
		if (StringUtils.isNotBlank(pid)) {
			sql.append(" t.pid=:pid");
			data.put("pid", pid);
		}
		sql.append(" order by t.updateTime desc ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		
		List<TDataQyhjfxffcs> whyLists = pos.getRe();
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		Map<String, String> dataObject = null;
		for (TDataQyhjfxffcs tq : whyLists) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(tq.getId()));
			dataObject.put("pid", String.valueOf(tq.getPid()));
			dataObject.put("fxdymc", String.valueOf(tq.getFxdymc()));
			dataObject.put("zyhxwzmc", String.valueOf(tq.getZyhxwzmc()==null?"":tq.getZyhxwzmc()));
			dataObject.put("xcl", String.valueOf(tq.getXcl()));
			dataObject.put("zdccl", String.valueOf(tq.getZdccl()));
			dataObject.put("gwgy", String.valueOf(tq.getGwgy()));
			dataObject.put("yryb", String.valueOf(tq.getYryb()));
			dataObject.put("hxwzyxl", String.valueOf(tq.getHxwzyxl()));
			dataObject.put("qt", String.valueOf(tq.getQt()));
			dataObject.put("wy", String.valueOf(tq.getWy()));
			dataObject.put("yxrj", String.valueOf(tq.getYxrj()));
			dataObject.put("zypxgg", String.valueOf(tq.getZypxgg()));
			dataObject.put("dmfs", String.valueOf(tq.getDmfs()));
			dataObject.put("dmfscl", String.valueOf(tq.getDmfscl()));
			dataObject.put("bjxt", String.valueOf(tq.getBjxt()));
			dataObject.put("wcjkw", String.valueOf(tq.getWcjkw()));
			dataObject.put("pfqx", String.valueOf(tq.getPfqx()));
			dataObject.put("sgyjc", String.valueOf(tq.getSgyjc()));
			dataObject.put("sgyjcrj", String.valueOf(tq.getSgyjcrj()));
			dataObject.put("qhfm", String.valueOf(tq.getQhfm()));
			dataObject.put("hcc", String.valueOf(tq.getHcc()));
			dataObject.put("xszz", String.valueOf(tq.getXszz()));
			dataObject.put("areaId", String.valueOf(tq.getAreaId()));
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
	public HjfxffcsForm infoHjfxffcsForm(HjfxffcsForm hjfxffcsForm)
			throws AppException {
		TDataQyhjfxffcs tq=(TDataQyhjfxffcs) this.get(TDataQyhjfxffcs.class, hjfxffcsForm.getId());
		hjfxffcsForm.setAreaId(tq.getAreaId());
		hjfxffcsForm.setBjxt("1".equals(tq.getBjxt())?"有":"无");
		hjfxffcsForm.setDmfs("1".equals(tq.getDmfs())?"有":"无");
		hjfxffcsForm.setDmfscl(tq.getDmfscl());
		hjfxffcsForm.setFxdymc(tq.getFxdymc());
		hjfxffcsForm.setGwgy(tq.getGwgy());
		hjfxffcsForm.setHcc(tq.getHcc());
		hjfxffcsForm.setHxwzyxl(tq.getHxwzyxl());
		hjfxffcsForm.setPfqx(tq.getPfqx());
		hjfxffcsForm.setQhfm(tq.getQhfm());
		hjfxffcsForm.setQt(tq.getQt());
		hjfxffcsForm.setSgyjc("1".equals(tq.getSgyjc())?"有":"无");
		hjfxffcsForm.setSgyjcrj(tq.getSgyjcrj());
		hjfxffcsForm.setWcjkw("Y".equals(tq.getWcjkw())?"是":"否");
		hjfxffcsForm.setWy("1".equals(tq.getWy())?"有":"无");
		hjfxffcsForm.setXcl(tq.getXcl());
		hjfxffcsForm.setXszz(tq.getXszz());
		hjfxffcsForm.setYryb(tq.getYryb());
		hjfxffcsForm.setYxrj(tq.getYxrj());
		hjfxffcsForm.setZdccl(tq.getZdccl());
		hjfxffcsForm.setZyhxwzmc(tq.getZyhxwzmc());
		hjfxffcsForm.setZypxgg("1".equals(tq.getZypxgg())?"有":"无");
		hjfxffcsForm.setIsActive(tq.getIsActive());
		hjfxffcsForm.setPid(tq.getPid());
	
		
		return hjfxffcsForm;
	}

	@Override
	public void removeHjfxffcs(String id) throws AppException {
		TDataQyhjfxffcs tq=(TDataQyhjfxffcs) this.get(TDataQyhjfxffcs.class, id);
		tq.setIsActive("N");
		tq.setUpdateby(CtxUtil.getCurUser());
		tq.setUpdateTime(new Date());
		
	}

	@Override
	public HjfxffcsForm editHjfxffcsForm(HjfxffcsForm hjfxffcsForm)
			throws AppException {
		TDataQyhjfxffcs tq=(TDataQyhjfxffcs) this.get(TDataQyhjfxffcs.class, hjfxffcsForm.getId());
		hjfxffcsForm.setAreaId(tq.getAreaId());
		hjfxffcsForm.setBjxt(tq.getBjxt());
		hjfxffcsForm.setDmfs(tq.getDmfs());
		hjfxffcsForm.setDmfscl(tq.getDmfscl());
		hjfxffcsForm.setFxdymc(tq.getFxdymc());
		hjfxffcsForm.setGwgy(tq.getGwgy());
		hjfxffcsForm.setHcc(tq.getHcc());
		hjfxffcsForm.setHxwzyxl(tq.getHxwzyxl());
		hjfxffcsForm.setPfqx(tq.getPfqx());
		hjfxffcsForm.setQhfm(tq.getQhfm());
		hjfxffcsForm.setQt(tq.getQt());
		hjfxffcsForm.setSgyjc(tq.getSgyjc());
		hjfxffcsForm.setSgyjcrj(tq.getSgyjcrj());
		hjfxffcsForm.setWcjkw(tq.getWcjkw());
		hjfxffcsForm.setWy(tq.getWy());
		hjfxffcsForm.setXcl(tq.getXcl());
		hjfxffcsForm.setXszz(tq.getXszz());
		hjfxffcsForm.setYryb(tq.getYryb());
		hjfxffcsForm.setYxrj(tq.getYxrj());
		hjfxffcsForm.setZdccl(tq.getZdccl());
		hjfxffcsForm.setZyhxwzmc(tq.getZyhxwzmc());
		hjfxffcsForm.setZypxgg(tq.getZypxgg());
		hjfxffcsForm.setIsActive(tq.getIsActive());
		hjfxffcsForm.setPid(tq.getPid());
	
		
		return hjfxffcsForm;
	}
}
