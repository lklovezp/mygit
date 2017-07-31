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

import com.hnjz.app.data.po.TDataQyhjyjczjjyzy;
import com.hnjz.app.data.po.TDataQyhxwzqkzycp;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;

@Service("qyhjyjczjjyzyManagerImpl")
public class QyhjyjczjjyzyManagerImpl extends ManagerImpl implements QyhjyjczjjyzyManager{
      private static final Log log=LogFactory.getLog(QyhjyjczjjyzyManagerImpl.class);

	@Override
	public List<Combobox> queryYjczTypeList() {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult.add(new Combobox("1","个人防护装备器材"));
		listResult.add(new Combobox("2","消防设施"));
		listResult.add(new Combobox("3","堵漏、收集器材/设备"));
		listResult.add(new Combobox("4","应急监测设备"));
		listResult.add(new Combobox("5","应急救援物资"));
		listResult.add(new Combobox("6","其他"));
		return listResult;
	}

	@Override
	public void saveQyhjyjczjjyzyForm(QyhjyjczjjyzyForm qyhjyjczjjyzyForm)
			throws AppException {
		if(StringUtils.isNotBlank(qyhjyjczjjyzyForm.getId())){
			TDataQyhjyjczjjyzy tq=(TDataQyhjyjczjjyzy) this.get(TDataQyhjyjczjjyzy.class, qyhjyjczjjyzyForm.getId());
			tq.setAreaId(CtxUtil.getAreaId());
			tq.setSxsl(qyhjyjczjjyzyForm.getSxsl());
			tq.setType(qyhjyjczjjyzyForm.getType());
			tq.setCreater(CtxUtil.getCurUser());
			tq.setCreateTime(new Date());
			tq.setWbdh(qyhjyjczjjyzyForm.getWbdh());
			tq.setWbxm(qyhjyjczjjyzyForm.getWbxm());
			tq.setIsActive("Y");
			tq.setPid(qyhjyjczjjyzyForm.getPid());
			tq.setWzmc(qyhjyjczjjyzyForm.getWzmc());
			tq.setXysl(qyhjyjczjjyzyForm.getXysl());
			tq.setWbName(qyhjyjczjjyzyForm.getWbName());
			tq.setUpdateby(CtxUtil.getCurUser());
			tq.setUpdateTime(new Date());
			
			this.dao.save(tq);
		}else{
			TDataQyhjyjczjjyzy tq=new TDataQyhjyjczjjyzy();
			//tDataQyhxwzqkzycp.setId(qyhxwzqkzycpForm.getId());
			tq.setAreaId(CtxUtil.getAreaId());
			tq.setSxsl(qyhjyjczjjyzyForm.getSxsl());
			tq.setType(qyhjyjczjjyzyForm.getType());
			tq.setCreater(CtxUtil.getCurUser());
			tq.setCreateTime(new Date());
			tq.setWbdh(qyhjyjczjjyzyForm.getWbdh());
			tq.setWbxm(qyhjyjczjjyzyForm.getWbxm());
			tq.setIsActive("Y");
			tq.setWbName(qyhjyjczjjyzyForm.getWbName());
			tq.setPid(qyhjyjczjjyzyForm.getPid());
			tq.setWzmc(qyhjyjczjjyzyForm.getWzmc());
			tq.setXysl(qyhjyjczjjyzyForm.getXysl());
			
			tq.setUpdateby(CtxUtil.getCurUser());
			tq.setUpdateTime(new Date());
			
			this.dao.save(tq);
		
	}
	}

	@Override
	public FyWebResult qyhjyjczjjyzyList(String pid, String isActive,String page, String pageSize) throws AppException {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder(" from TDataQyhjyjczjjyzy t where t.isActive='Y' and ");
		if (StringUtils.isNotBlank(pid)) {
			sql.append(" t.pid=:pid");
			data.put("pid", pid);
		}
		sql.append(" order by t.updateTime desc ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		
		List<TDataQyhjyjczjjyzy> whyLists = pos.getRe();
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		Map<String, String> dataObject = null;
		for (TDataQyhjyjczjjyzy tq : whyLists) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(tq.getId()));
			dataObject.put("pid", String.valueOf(tq.getPid()));
			dataObject.put("sxsl", String.valueOf(tq.getSxsl()));
			String typeName="";
			if("1".equals(tq.getType())){
				typeName="个人防护装备器材";
			}else if("2".equals(tq.getType())){
				typeName="消防设施";
			}else if("3".equals(tq.getType())){
				typeName="堵漏、收集器材/设备";
			}else if("4".equals(tq.getType())){
				typeName="应急监测设备";
			}else if("5".equals(tq.getType())){
				typeName="应急救援物资";
			}else{
				typeName="其他";
			}
			dataObject.put("type", String.valueOf(typeName));
			dataObject.put("wbdh", String.valueOf(tq.getWbdh()));
			dataObject.put("wbName",tq.getWbName()==null?"":String.valueOf(tq.getWbName()));
			dataObject.put("wbxm", String.valueOf(tq.getWbxm()));
			dataObject.put("wzmc", tq.getWzmc()==null?"":String.valueOf(tq.getWzmc()));
			dataObject.put("xysl", String.valueOf(tq.getXysl()));
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
	public QyhjyjczjjyzyForm editQyhjyjczjjyzyForm(QyhjyjczjjyzyForm qyhjyjczjjyzyForm) throws AppException {
		TDataQyhjyjczjjyzy tq=(TDataQyhjyjczjjyzy) this.get(TDataQyhjyjczjjyzy.class, qyhjyjczjjyzyForm.getId());
		qyhjyjczjjyzyForm.setAreaId(tq.getAreaId());
		qyhjyjczjjyzyForm.setSxsl(tq.getSxsl());
		qyhjyjczjjyzyForm.setType(tq.getType());
		qyhjyjczjjyzyForm.setCreater(tq.getCreater());
		qyhjyjczjjyzyForm.setCreateTime(tq.getCreateTime());
		qyhjyjczjjyzyForm.setWbdh(tq.getWbdh());
		qyhjyjczjjyzyForm.setWbxm(tq.getWbxm());
		qyhjyjczjjyzyForm.setIsActive(tq.getIsActive());
		qyhjyjczjjyzyForm.setPid(tq.getPid());
		qyhjyjczjjyzyForm.setWzmc(tq.getWzmc());
		qyhjyjczjjyzyForm.setXysl(tq.getXysl());
		qyhjyjczjjyzyForm.setWbName(tq.getWbName());
		qyhjyjczjjyzyForm.setUpdateby(tq.getUpdateby());
		qyhjyjczjjyzyForm.setUpdateTime(tq.getUpdateTime());
		
		return qyhjyjczjjyzyForm;
	}

	@Override
	public void removeQyhjyjczjjyzy(String id) throws AppException {
		TDataQyhjyjczjjyzy tq=(TDataQyhjyjczjjyzy) this.get(TDataQyhjyjczjjyzy.class, id);
		tq.setIsActive("N");
		tq.setUpdateby(CtxUtil.getCurUser());
		tq.setUpdateTime(new Date());
		
	}

	@Override
	public QyhjyjczjjyzyForm infoQyhjyjczjjyzyForm(QyhjyjczjjyzyForm qyhjyjczjjyzyForm) throws AppException {
		TDataQyhjyjczjjyzy tq=(TDataQyhjyjczjjyzy) this.get(TDataQyhjyjczjjyzy.class, qyhjyjczjjyzyForm.getId());
		qyhjyjczjjyzyForm.setAreaId(tq.getAreaId());
		qyhjyjczjjyzyForm.setSxsl(tq.getSxsl());
		String typeName="";
		if("1".equals(tq.getType())){
			typeName="个人防护装备器材";
		}else if("2".equals(tq.getType())){
			typeName="消防设施";
		}else if("3".equals(tq.getType())){
			typeName="堵漏、收集器材/设备";
		}else if("4".equals(tq.getType())){
			typeName="应急监测设备";
		}else if("5".equals(tq.getType())){
			typeName="应急救援物资";
		}else{
			typeName="其他";
		}
		qyhjyjczjjyzyForm.setType(typeName);
		qyhjyjczjjyzyForm.setCreater(tq.getCreater());
		qyhjyjczjjyzyForm.setCreateTime(tq.getCreateTime());
		qyhjyjczjjyzyForm.setWbdh(tq.getWbdh());
		qyhjyjczjjyzyForm.setWbxm(tq.getWbxm());
		qyhjyjczjjyzyForm.setIsActive(tq.getIsActive());
		qyhjyjczjjyzyForm.setPid(tq.getPid());
		qyhjyjczjjyzyForm.setWzmc(tq.getWzmc());
		qyhjyjczjjyzyForm.setXysl(tq.getXysl());
		qyhjyjczjjyzyForm.setWbName(tq.getWbName());
		qyhjyjczjjyzyForm.setUpdateby(tq.getUpdateby());
		qyhjyjczjjyzyForm.setUpdateTime(tq.getUpdateTime());
		
		return qyhjyjczjjyzyForm;
	}

	@Override
	public FyWebResult qyhjyjczjjyzyListByTypeAndTime(String pid,
			String isActive, String page, String pageSize) throws AppException {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder(" from TDataQyhjyjczjjyzy t where t.isActive='Y' and ");
		if (StringUtils.isNotBlank(pid)) {
			sql.append(" t.pid=:pid");
			data.put("pid", pid);
		}
		sql.append(" order by t.type asc,t.updateTime desc ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		
		List<TDataQyhjyjczjjyzy> whyLists = pos.getRe();
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		Map<String, String> dataObject = null;
		for (TDataQyhjyjczjjyzy tq : whyLists) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(tq.getId()));
			dataObject.put("pid", String.valueOf(tq.getPid()));
			dataObject.put("sxsl", String.valueOf(tq.getSxsl()));
			String typeName="";
			if("1".equals(tq.getType())){
				typeName="个人防护装备器材";
			}else if("2".equals(tq.getType())){
				typeName="消防设施";
			}else if("3".equals(tq.getType())){
				typeName="堵漏、收集器材/设备";
			}else if("4".equals(tq.getType())){
				typeName="应急监测设备";
			}else if("5".equals(tq.getType())){
				typeName="应急救援物资";
			}else{
				typeName="其他";
			}
			dataObject.put("type", String.valueOf(typeName));
			dataObject.put("wbdh", String.valueOf(tq.getWbdh()));
			dataObject.put("wbName", String.valueOf(tq.getWbName()));
			dataObject.put("wbxm", String.valueOf(tq.getWbxm()));
			dataObject.put("wzmc", String.valueOf(tq.getWzmc()));
			dataObject.put("xysl", String.valueOf(tq.getXysl()));
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
}
