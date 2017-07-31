package com.hnjz.app.data.wg;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.hnjz.app.data.po.TDataOrgWg;
import com.hnjz.app.data.po.TDataUserWg;
import com.hnjz.app.data.po.TDataWg;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;

import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;


import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.po.TSysUserOrg;

import com.hnjz.sys.user.UserManager;
import com.hnjz.sys.user.UserManagerImpl;

@Service("WgManagerImpl")
public class WgManagerImpl extends ManagerImpl implements WgManager{
	@Autowired
	private UserManager userManager;
	
	/** 日志 */
	private static final Log log = LogFactory.getLog(UserManagerImpl.class);
	/** 用户对应部门的缓存，key为orgid,value为用户对应的部门 */
	private Map<String, TDataWg> wgs = new ConcurrentHashMap<String, TDataWg>();
	
	/**
	 * 保存网格
	 */
	@Override
	public void saveWg(WgForm frm) throws AppException{
		// TODO Auto-generated method stub
		// 网格名不能重复
		StringBuilder hsq = new StringBuilder();
		hsq.append("select count(id) from TDataWg where isActive = 'Y' and wgmc = :wgmc ");
		QueryCondition data = new QueryCondition();
		data.put("wgmc", frm.getWgmc());
		if (StringUtils.isNotBlank(frm.getId())) {
			hsq.append("and id <> :id");
			data.put("id", frm.getId());
		}
		long count = (Long) this.dao.find(hsq.toString(), data).get(0);
		if (count > 0) {
			throw new AppException("网格名称不能重复！");
		}
		
	    TDataWg po = null;
	    TSysUser user = CtxUtil.getCurUser();
	    Date cur = new Date();
		
		if (StringUtils.isNotBlank(frm.getId())) {
			po = (TDataWg) this.get(TDataWg.class, frm.getId());
		}else{
			po = new TDataWg();
			po.setCreateby(user);
			po.setCreated(cur);
		}
		po.setUpdateby(user);
		po.setUpdated(cur);
		po.setWgmc(frm.getWgmc());
		po.setMs(frm.getMs());
		po.setIsActive(YnEnum.Y.getCode());
		String areaid= CtxUtil.getAreaId();
		po.setAreaid(areaid);
		if (StringUtils.isNotBlank(po.getId())) {
			wgs.remove(po.getId());
		}
		this.dao.save(po);
		// 删除网格的用户
		String hsql = "from TDataUserWg where wg.id = ?";
		if (StringUtils.isNotBlank(po.getId())){
			this.dao.removeFindObjs(hsql, po.getId());
		}
		if (StringUtils.isNotBlank(frm.getUserid())) {
			String[] users = frm.getUserid().split(",");
			//判断选择的网格用户是几个就进行几次网格用户表的保存
			for(int i=0;i<users.length;i++){
				TDataUserWg r = new TDataUserWg();
				TSysUser tSysUser = (TSysUser) this.dao.get(TSysUser.class, users[i]);
				r.setUser(tSysUser);
				TDataWg wg1 = (TDataWg) this.dao.get(TDataWg.class, po.getId());
				r.setWg(wg1);
				this.dao.save(r);
			}
		}
		
		// 删除网格的部门
		 hsql = "from TDataOrgWg where wg.id = ?";
		if (StringUtils.isNotBlank(po.getId())){
			this.dao.removeFindObjs(hsql, po.getId());
		}
		if (StringUtils.isNotBlank(frm.getOrgid())) {
			TDataOrgWg r = new TDataOrgWg();
			TSysOrg org = (TSysOrg) this.dao.get(TSysOrg.class, frm.getOrgid());
			r.setOrg(org);
			TDataWg wg1 = (TDataWg) this.dao.get(TDataWg.class, po.getId());
			r.setWg(wg1);
			this.dao.save(r);
	
		}
	}
    /**
     * 删除网格
     */
	@Override
	public void delWg(String id) {
		// TODO Auto-generated method stub
		TDataWg del = (TDataWg) this.dao.get(TDataWg.class, id);
		del.setIsActive(YnEnum.N.getCode());
		this.dao.save(del);
		
	}
    /**
     * 通过部门分页查询网格
     */
	@Override
	public FyWebResult queryWg(String wgmc, String orgid,String page, String pageSize) throws Exception {
		// TODO Auto-generated method stub
		String SQL ="";
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		Map<String, Object> condition = new HashMap<String, Object>();
		sql.append(" select  a.id_,a.wgmc_,wm_concat(a.name_) as name_,wm_concat(a.personmobile_),wm_concat(a.workmobile_) as personmobile_ from ");
		sql.append("(SELECT ts.name_ ,ts.personmobile_,ts.workmobile_, tw.wgmc_,tw.id_,TOw.orgid_ from t_data_userwg uw LEFT JOIN t_sys_user ts on uw.user_=ts.id_  " );
		sql.append("left join t_data_wg tw on tw.id_=uw.wgid_ LEFT JOIN t_data_orgwg tow on tow.wgid_=tw.id_ ");
		sql.append(" where 1=1 ");
		String areaid = CtxUtil.getAreaId();
		sql.append(" and tw.areaid_=:areaid ");
		condition.put("areaid", areaid);
		if(StringUtils.isNotBlank(orgid)){
			sql.append(" and tow.orgid_ =:orgid ");
			condition.put("orgid", orgid);
		}
		if(StringUtils.isNotBlank(wgmc)){
			sql.append(" and (ts.name_ like :name  or ts.username_ like :name or ts.workmobile_ like :name or ts.personmobile_ like :name or tw.wgmc_ like :name) ");
			condition.put("name", "%"+wgmc+"%");
		}
		sql.append("  and tw.isactive_='Y' ) a   group by a.wgmc_,a.id_");
		FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<Object[]> wgs = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] ele : wgs) {
				dataObject = new HashMap<String, String>();
				dataObject.put("id", String.valueOf(ele[0]));
				dataObject.put("name", String.valueOf(ele[2]));
				dataObject.put("wgmc", String.valueOf(ele[1]));
				dataObject.put("workmobile", String.valueOf(ele[4]));
				dataObject.put("personmobile",ele[3]==null?"":String.valueOf(ele[3]));
				rows.add(dataObject);
		}
		fy.setRows(rows);
		return fy;
	}

    /**
     * t
     * @throws Exception 
     */
	@Override
	public void getWg(WgForm wg) throws Exception {
		// TODO Auto-generated method stub
		TDataWg po = (TDataWg) this.get(TDataWg.class, wg
				.getId());
		wg.setId(po.getId());
		wg.setIsActive(po.getIsActive());
		wg.setMs(po.getMs());
		wg.setWgmc(po.getWgmc());
		//网格对多用户的取值
				List<TSysUser> u = this.queryUserByWg(po.getId());
				String userId = "";
				String username="";
				for(int i=0; i<u.size();i++){
					if (null != u) {
						if(i<u.size()-1){
							userId += u.get(i).getId()+",";
							username +=u.get(i).getName()+",";
			        	}else{
			        		userId += u.get(i).getId();
			        		username +=u.get(i).getName();
			        	}
					}
				}
		wg.setUserid(userId);
		wg.setUsername(username);
	}
    
	/**
	 * 通过网格id查询人员
	 */
	@Override
	public List<TSysUser> queryUserByWg(String id) throws Exception {
		// TODO Auto-generated method stub
		String hsql = "select u from TDataUserWg uw,TDataWg w,TSysUser u where u.isActive = 'Y' and w.isActive = 'Y' and u.id=uw.user and w.id=uw.wg and w.id = ?";
		List<TSysUser> re = this.dao.find(hsql, id);
		log.debug("查询到角色信息：" + re.size());
		if (re.isEmpty()) {
			return null;
		}
		return re;
	}

	@Override
	public void editWg(WgForm frm) throws Exception {
		// TODO Auto-generated method stub
		TDataWg po = (TDataWg) this.get(TDataWg.class, frm
				.getId());
		frm.setId(po.getId());
		frm.setMs(po.getMs());
		frm.setWgmc(po.getWgmc());
		frm.setAreaid(po.getAreaid());
		//网格对多用户的取值
		List<TSysUser> u = this.queryUserByWg(frm.getId());
		String userId = "";
		String username="";
		for(int i=0; i<u.size();i++){
			if (null != u) {
				if(i<u.size()-1){
					userId += u.get(i).getId()+",";
					username +=u.get(i).getName()+",";
	        	}else{
	        		userId += u.get(i).getId();
	        		username +=u.get(i).getName();
	        	}
			}
		}
		
		frm.setUserid(userId);
		frm.setUsername(username);
		
	}

	@Override
	public List<Map<String, String>> queryAllWg() {
		// TODO Auto-generated method stub
		List<Map<String,String>>  rows= new ArrayList<Map<String, String>>();
		StringBuilder sql=new StringBuilder();
		sql.append("select c.id_,a.name_,a.workmobile_,a.personmobile_,c.wgmc_ from t_sys_user a ,t_data_userwg b, t_data_wg c,t_sys_org d,t_data_orgwg e where a.id_=b.user_ and c.id_=b.wgid_ and  e.orgid_=d.id_ and c.id_=e.wgid_ and c.isactive_='Y'");
		List<Object[]> list = dao.findBySql(sql.toString());
		Map<String,String> dataObject=null;
		for(Object[] ele : list){
			dataObject=new HashMap<String,String>();
			dataObject.put("id", String.valueOf(ele[0]));
			dataObject.put("name", String.valueOf(ele[1]));
			dataObject.put("workmobile", String.valueOf(ele[2]));
			dataObject.put("personmobile", String.valueOf(ele[3]));
			dataObject.put("wgmc", String.valueOf(ele[4]));
			rows.add(dataObject);
		}
		return rows;
		
	}
	@Override
	public List<TDataWg> queryWgByOrg(String orgid) {
		// TODO Auto-generated method stub
		return null;
	}


	


}
