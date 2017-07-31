package com.hnjz.app.work.sgdw;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.work.po.TDataSgdw;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.sys.user.UserManager;
@Service("builderManager")
public class BuilderManagerImpl extends ManagerImpl implements BuilderManager {
private static final Log log=LogFactory.getLog(BuilderManagerImpl.class);
	
    @Autowired
    private UserManager userManager;
    @Override
	public FyWebResult queryBuilderList(String name, String isActive,String page, String pageSize) throws Exception {
    	QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.id_,a.name_,a.adress_,a.fddbr_,a.fddbrlxdh_,a.hbfzr_,a.hbfzrlxdh_,a.isActive_");
		sql.append(" from t_data_sgdw a ");
		sql.append(" where 1=1 ");
		String areaId = CtxUtil.getAreaId();
		//所属行政区
		if(StringUtils.isNotBlank(areaId)){
			sql.append(" and a.AREAID_ = :areaId ");
			data.put("areaId", areaId);
		}
		if (StringUtils.isNotBlank(name)) {
			sql.append(" and (a.name_ like :name or a.adress_ like :name)");
			data.putLike("name", name);
		}
		if (StringUtils.isNotBlank(isActive)) {
			sql.append(" and a.isactive_ = :isActive ");
			data.put("isActive", isActive);
		} else {
			sql.append(" and a.isactive_ = :isActive ");
			data.put("isActive", YnEnum.Y.getCode());
		}
		sql.append(" order by a.updated_ desc ");
		FyResult pos = dao.query(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<Object[]> builders = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] ele : builders) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(ele[0]));
			dataObject.put("name", String.valueOf(ele[1]));
			dataObject.put("adress", String.valueOf(ele[2]));
			dataObject.put("fddbr", String.valueOf(ele[3]));
			dataObject.put("fddbrlxdh", String.valueOf(ele[4]));
			dataObject.put("hbfzr", String.valueOf(ele[5]));
			dataObject.put("hbfzrlxdh", String.valueOf(ele[6]));
			dataObject.put("isActive", "Y".equals(String.valueOf(ele[7]))?"有效":"无效");
			dataObject.put("operate", OperateUtil.getOperate(String.valueOf(ele[0])));
			rows.add(dataObject);
		}
		fy.setRows(rows);
		return fy;
	}

	@Override
	public void saveBuilder(BuilderForm builderForm) throws AppException {
		// 保存施工单位信息
		
		if(builderForm.getId().equals("")){
			TDataSgdw tDataSgdw=new TDataSgdw();
			tDataSgdw.setAdress(builderForm.getAdress());
			tDataSgdw.setCreater(userManager.getUser(CtxUtil.getUserId()));
			tDataSgdw.setFddbr(builderForm.getFddbr());
			tDataSgdw.setFddbrlxdh(builderForm.getFddbrlxdh());
			tDataSgdw.setName(builderForm.getName());
			tDataSgdw.setIsActive(builderForm.getIsActive());
			tDataSgdw.setHbfzr(builderForm.getHbfzr());
			tDataSgdw.setHbfzrlxdh(builderForm.getHbfzrlxdh());
			tDataSgdw.setAreaId(CtxUtil.getAreaId());
			tDataSgdw.setCreateTime(new Date());
			tDataSgdw.setUpdateby(CtxUtil.getCurUser());
			tDataSgdw.setUpdateTime(new Date());
			tDataSgdw.setId(builderForm.getId());
			this.dao.save(tDataSgdw);
		}else{
			TDataSgdw tDataSgdw=(TDataSgdw) this.get(TDataSgdw.class, builderForm.getId());
			tDataSgdw.setAdress(builderForm.getAdress());
			tDataSgdw.setCreater(userManager.getUser(CtxUtil.getUserId()));
			tDataSgdw.setFddbr(builderForm.getFddbr());
			tDataSgdw.setFddbrlxdh(builderForm.getFddbrlxdh());
			tDataSgdw.setName(builderForm.getName());
			tDataSgdw.setIsActive(builderForm.getIsActive());
			tDataSgdw.setHbfzr(builderForm.getHbfzr());
			tDataSgdw.setHbfzrlxdh(builderForm.getHbfzrlxdh());
			tDataSgdw.setAreaId(CtxUtil.getAreaId());
			tDataSgdw.setCreateTime(new Date());
			tDataSgdw.setUpdateby(CtxUtil.getCurUser());
			tDataSgdw.setUpdateTime(new Date());
			tDataSgdw.setId(builderForm.getId());
			this.dao.save(tDataSgdw);
		}
		
	}

	@Override
	public void removeBuilder(String id) throws AppException {
		TDataSgdw tsDataSgdw=(TDataSgdw) this.get(TDataSgdw.class, id);
		tsDataSgdw.setIsActive("N");
		tsDataSgdw.setUpdateby(CtxUtil.getCurUser());
		tsDataSgdw.setUpdateTime(new Date());
		
	}

	@Override
	public void editBuilder(BuilderForm builderForm) throws AppException {
		TDataSgdw tDataSgdw=(TDataSgdw) this.get(TDataSgdw.class, builderForm.getId());
		builderForm.setAdress(tDataSgdw.getAdress());
		builderForm.setFddbr(tDataSgdw.getFddbr());
		builderForm.setFddbrlxdh(tDataSgdw.getFddbrlxdh());
		builderForm.setHbfzr(tDataSgdw.getHbfzr());
		builderForm.setHbfzrlxdh(tDataSgdw.getHbfzrlxdh());
		builderForm.setIsActive(tDataSgdw.getIsActive());
		builderForm.setName(tDataSgdw.getName());
		builderForm.setId(tDataSgdw.getId());
		
	}

}
