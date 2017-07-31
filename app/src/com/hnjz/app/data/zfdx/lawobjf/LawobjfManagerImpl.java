package com.hnjz.app.data.zfdx.lawobjf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.po.TDataIndustry;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.data.po.TDataRegion;
import com.hnjz.app.data.po.TDataWg;
import com.hnjz.app.data.po.TDataXgjl;
import com.hnjz.app.data.zfdx.FzUtils;
import com.hnjz.common.FyWebResult;
import com.hnjz.IndexManager;
import com.hnjz.common.AppException;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sal.WorkClientFactoty;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;


/**
 * Tdatalawobjf管理的manager
 * 
 * @author xuyaxing
 * @version 
 */
 @Service("tdatalawobjfManagerImpl")
public class LawobjfManagerImpl extends ManagerImpl implements LawobjfManager{


/** 日志 */
	private static final Log log = LogFactory.getLog(LawobjfManagerImpl.class);

	
	@Autowired
    private IndexManager     indexManager;




	@Override
	public FyWebResult queryssjgbmnull(String page,String pageSize) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
    	Map<String, Object> condition = new HashMap<String, Object>();
    	sql.append("select t.dwmc_,t.dwdz_ from t_data_lawobjf t where t.isactive_='Y'and t.ssjgbm_ is null");
    	FyResult pos = this.dao.find(sql.toString(),page==null?0:Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<Object[] > zfdxs = pos.getRe();
        Map<String, String> dataObject = null;
        for( Object[] els :zfdxs){
       	 dataObject = new HashMap<String, String>();
            dataObject.put("dwmc", els[0]==null?"":String.valueOf(els[0]));
            dataObject.put("dwdz", els[1]==null?"":String.valueOf(els[1]));
            rows.add(dataObject);
        }
        fy.setRows(rows);
		log.debug(fy);
		return fy;
	}
	
    
    /**
	 * 分页查询功能
	 * @param strWhere  例如 and name=:name
	 * @param data   data.putLike("name", "张三")
     * @page 
     * @pageSize 
	 * @return 
	 * @throws Exception
	 */
    @Override
	public FyWebResult queryTdatalawobjf(String name,String dwmc,String lawobjTypeId,String regionId,String orgId,String wgid, String page,String pageSize) {
    	String biaoshi = indexManager.sysVer;
    	QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
    	StringBuilder sql = new StringBuilder();
    	Map<String, Object> condition = new HashMap<String, Object>();
		
        sql.append("select w.wgmc_ wgmc,o.name_ orgmc ,r.name_ regionmc,a.name_ typemc,f.id_,f.jxsmid_,f.dwmc_,f.dwdz_,f.fddbr_,f.fddbrdh_,f.hbfzr_,f.hbfzrdh_ ,r.id_ regionid from t_data_lawobjf f  ");
        sql.append("left join t_data_lawobjtype a on f.lawobjtypeid_=a.id_  left join T_SYS_ORG o on o.id_=f.ssjgbm_ ");
        sql.append("left join t_data_region  r on r.id_=f.ssxzq_ left join T_DATA_WG w on w.id_=f.sswgqy_  where 1=1 and f.isactive_='Y' ");
        String areaid = CtxUtil.getAreaId();
		if(StringUtils.isBlank(areaid)){
			sql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
			condition.put("regionId", "");
		}else{
			TSysArea area = (TSysArea) this.get(TSysArea.class, areaid);
			if(area.getType().equals("1")){//师级
				sql.append(" and r.pid_ = (select a.pid_ from t_data_region a where a.id_ = :regionId) ");
				condition.put("regionId", area.getCode());
			}else if(area.getType().equals("2")){//团级
				sql.append(" and r.id_ = :regionId ");
				condition.put("regionId", area.getCode());
			}
		}
        if(StringUtils.isNotBlank(name)){
        	sql.append(" and (w.wgmc_ like :name  or o.name_ like :name or r.name_ like :name or a.name_ like :name or f.dwmc_ like :name)");
        	condition.put("name", "%"+name+"%");
		} 	
        if(StringUtils.isNotBlank(dwmc)){
        	sql.append(" and f.dwmc_ like :dwmc ");
        	condition.put("dwmc", "%"+dwmc+"%");
        }
        if(StringUtils.isNotBlank(lawobjTypeId)){
		sql.append(" and f.lawobjtypeid_ =:lawobjtypeid");	
		condition.put("lawobjtypeid", lawobjTypeId);
        }
		if(StringUtils.isNotBlank(regionId)){
			sql.append(" and r.id_ =:regionId");	
			condition.put("regionId", regionId);
		}
		 
		//所属监管部门
		if(StringUtils.isNotBlank(orgId)){
		sql.append(" and  o.id_ =:orgid");
		condition.put("orgid", orgId);
		}
		
		if(StringUtils.isNotBlank(wgid)){
			sql.append(" and w.id_ =:wgid");	
			condition.put("wgid", wgid);
		}
		
        FyResult pos = this.dao.find(sql.toString(),page==null?0:Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<Object[] > zfdxs = pos.getRe();
        Map<String, String> dataObject = null;
        
         for( Object[] els :zfdxs){
        	 dataObject = new HashMap<String, String>();
             dataObject.put("wgmc", els[0]==null?"":String.valueOf(els[0]));
             dataObject.put("orgmc", els[1]==null?"":String.valueOf(els[1]));
             dataObject.put("regionmc", els[2]==null?"":String.valueOf(els[2]));
             dataObject.put("lawobjtypemc", els[3]==null?"":String.valueOf(els[3]));
             dataObject.put("id", String.valueOf(els[4]));
             dataObject.put("jsxmid", els[5]==null?"":String.valueOf(els[5]));
             dataObject.put("dwmc", els[6]==null?"":String.valueOf(els[6]));
             dataObject.put("dwdz", els[7]==null?"":String.valueOf(els[7]));
             dataObject.put("fddbr", els[8]==null?"":String.valueOf(els[8]));
             dataObject.put("fddbrdh", els[9]==null?"":String.valueOf(els[9]));
             dataObject.put("hbfzr", els[10]==null?"":String.valueOf(els[10]));
             dataObject.put("hbfzrdh", els[11]==null?"":String.valueOf(els[11]));
             dataObject.put("regionid", els[12]==null?"":String.valueOf(els[12]));
            rows.add(dataObject);
         }
         
        fy.setRows(rows);
		log.debug(fy);
		return fy;
        
     }
    

/**
	 * 删除Tdatalawobjf
	 * 
	 * @param id
	 *            Tdatalawobjf的ID
	 */
	public void removeTdatalawobjf(String id) throws AppException{
		TDataLawobjf del = (TDataLawobjf) this.dao.get(TDataLawobjf.class, id);
		del.setIsactive(YnEnum.N.getCode());
		this.dao.save(del);
    }
    
    
    
    /**
	 * 保存Tdatalawobjf
	 * 
	 * @param frm
	 * @throws AppException
	 */
	public TDataLawobjf saveTdatalawobjf(LawobjfForm frm) throws AppException{
		StringBuilder hsq = new StringBuilder();
		hsq.append("select count(id) from TDataLawobjf where isactive = 'Y' and dwmc = :name ");
		QueryCondition data = new QueryCondition();
		data.put("name", frm.getDwmc());
		if (StringUtils.isNotBlank(frm.getId())) {
			hsq.append("and id <> :id");
			data.put("id", frm.getId());
		}
		long count = (Long) this.dao.find(hsq.toString(), data).get(0);
		if (count > 0) {
			throw new AppException("执法对象名称不能重复！");
		}
		
		TDataLawobjf po = null;
		TDataLawobjf poold =  new TDataLawobjf();
	    TSysUser user = CtxUtil.getCurUser();
	    Date cur = new Date();
	    if (StringUtils.isNotBlank(frm.getId())) {
			po = (TDataLawobjf) this.get(TDataLawobjf.class, frm.getId());
			FzUtils.converJavaBean(poold, po);
			//poold=(TDataLawobjf) this.get(TDataLawobjf.class, frm.getId());
		}else{
			po = new TDataLawobjf();
			po.setCjsj(cur);
			po.setCjr(user.getId());
		} 
		
	   TDataLawobjf lawobjf = null; 
	   TDataLawobjtype lawobjtype =null;
	   if(null!=frm.getJsxmid()){
		   lawobjf = (TDataLawobjf) this.dao.get(TDataLawobjf.class, frm.getJsxmid());
	   }
       po.setJsxm(lawobjf);
       po.setIsold(frm.getIsold());
       if(null !=frm.getLawobjtypeid()){
    	 lawobjtype=(TDataLawobjtype) this.dao.get(TDataLawobjtype.class, frm.getLawobjtypeid());  
       }
       po.setLawobjtype(lawobjtype);
       po.setUpdated(cur);
       po.setDwmc(frm.getDwmc());
       po.setDwdz(frm.getDwdz());
       po.setFddbr(frm.getFddbr());
       po.setFddbrdh(frm.getFddbrdh());
       po.setHbfzr(frm.getHbfzr());
       po.setHbfzrdh(frm.getHbfzrdh());
      
       po.setSsjgbm(frm.getSsjgbm());
       po.setSsxzq(frm.getSsxzq());
       po.setSswgqy(frm.getSswgqy());
       String jd="";
       String wd="";
       if(frm.getJddu()!=null){
    	  jd+=frm.getJddu()+","; 
       }
       if(frm.getJdfen()!=null){
     	  jd+=frm.getJdfen()+","; 
        }
       if(frm.getJdmiao()!=null){
     	  jd+=frm.getJdmiao(); 
        }
       if(frm.getWddu()!=null){
     	  wd+=frm.getWddu()+","; 
        }
        if(frm.getWdfen()!=null){
      	  wd+=frm.getWdfen()+","; 
         }
        if(frm.getWdmiao()!=null){
      	  wd+=frm.getWdmiao(); 
         }
       po.setJd(jd);
       po.setWd(wd);
       po.setQysczt(frm.getQysczt());
       po.setQyzt(frm.getQyzt());
       po.setZt(frm.getZt());
       po.setFqpfks(frm.getFqpfks());
       po.setFspfks(frm.getFspfks());
       po.setZsygs(frm.getZsygs());
       po.setGfdfcgs(frm.getGfdfcgs());
       po.setJsxmmc(frm.getJsxmmc());
       po.setYyzzzch(frm.getYyzzzch());
       po.setSgxmmc(frm.getSgxmmc());
       po.setGcdd(frm.getGcdd());
       po.setXqyzcmc(frm.getXqyzcmc());
       po.setIsactive(YnEnum.Y.getCode());
       po.setHylx(frm.getHylx());

       String fxzd=FzUtils.contrastObj(poold, po);
       this.dao.save(po);
       TDataXgjl xgjl=new TDataXgjl();
       
       
       xgjl.setZfdxid(po.getId());
       xgjl.setCjr(po.getCjr());
       xgjl.setCjsj(po.getCjsj());
       xgjl.setXgnr(fxzd);
       xgjl.setXgr(user.getId());
       xgjl.setXgsj(cur);
       this.dao.save(xgjl);
   	   return po;
    }


   /**
	 * 编辑Tdatalawobjf
	 * 
	 * @param id
	 *            Tdatalawobjf的ID
	 */
    @Override
	public void editLawobjf(LawobjfForm frm){
       TDataLawobjf po=(TDataLawobjf)this.dao.get(TDataLawobjf.class,frm.getId());
       frm.setId(po.getId());
       if(null !=po.getJsxm()){
    	   TDataLawobjf jsxm=(TDataLawobjf)this.dao.get(TDataLawobjf.class,po.getJsxm().getId());
    	   frm.setJsxmid(jsxm.getId());
    	   frm.setJxsmname(jsxm.getDwmc());
       }
       frm.setIsold(po.getIsold());
       if(null !=po.getLawobjtype()){
    	  TDataLawobjtype lawobjtype = (TDataLawobjtype) this.dao.get(TDataLawobjtype.class, po.getLawobjtype().getId());
    	  frm.setLawobjtypeid(lawobjtype.getId());
    	  frm.setLawobjtypename(lawobjtype.getName());
       }
       frm.setDwmc(po.getDwmc());
       frm.setDwdz(po.getDwdz());
       frm.setFddbr(po.getFddbr());
       frm.setFddbrdh(po.getFddbrdh());
       frm.setHbfzr(po.getHbfzr());
       frm.setHbfzrdh(po.getHbfzrdh());
       
       if(null !=po.getCjr()){
    	   TSysUser user=(TSysUser) this.dao.get(TSysUser.class, po.getCjr());
    	   frm.setCjr(user.getName());
       }else{
    	   TSysUser use=CtxUtil.getCurUser();
    	   frm.setCjr(use.getName());
       }
            
       
       frm.setSsjgbm(po.getSsjgbm());
       if(po.getSsjgbm()!=null){
    	   TSysOrg orgs=(TSysOrg) this.dao.get(TSysOrg.class, po.getSsjgbm());
    	   frm.setSsjgbmmc(orgs.getName());
       }
       
      
       frm.setSsxzq(po.getSsxzq());
       TDataRegion regions=(TDataRegion) this.dao.get(TDataRegion.class, po.getSsxzq());
       frm.setSsxzqmc(regions.getName());
       frm.setSswgqy(po.getSswgqy());
       TDataWg wgs=(TDataWg) this.dao.get(TDataWg.class, po.getSswgqy());
       frm.setSswgqymc(wgs.getWgmc());
       frm.setQysczt(po.getQysczt());
       frm.setJd(po.getJd());
       frm.setWd(po.getWd());
     //把经纬度改成度分秒
		 String jd="";
		 String wd="";
		 String jddu="";
		 String jdfen="";
		 String jdmiao="";
		 String wddu="";
		 String wdfen="";
		 String wdmiao="";
		if(StringUtil.isNotBlank(po.getJd())&& !po.getJd().equals(",,")){
			String[] arr=po.getJd().split(",");
			if(arr.length == 1){
				if("null" != arr[0]){
					jd = arr[0]+"度";
					jddu=arr[0];
				}else{
					jd = "";
				}
			}else if(arr.length == 2){
				jd = arr[0]+"度"+arr[1]+"分";
				jddu=arr[0];
				jdfen=arr[1];
			}else if(arr.length == 3){
				jd = arr[0]+"度"+arr[1]+"分"+arr[2]+"秒";
				jddu=arr[0];
				jdfen=arr[1];
				jdmiao=arr[2];
			}
			
		}else{
			jd="";
			jddu="";
			jdfen="";
			jdmiao="";
		}
		if(StringUtil.isNotBlank(po.getWd())&& !po.getWd().equals(",,")){
			String[] arr=po.getWd().split(",");
			if(arr.length == 1){
				if("null" != arr[0]){
					wd = arr[0]+"度";
					wddu=arr[0];
				}else{
					wd = "";
				}
			}else if(arr.length == 2){
				wd = arr[0]+"度"+arr[1]+"分";
				wddu=arr[0];
				wdfen=arr[1];
			}else if(arr.length == 3){
				wd = arr[0]+"度"+arr[1]+"分"+arr[2]+"秒";
				wddu=arr[0];
				wdfen=arr[1];
				wdmiao=arr[2];
			}
			
		}else{
			wd="";
			wddu="";
			wdfen="";
			wdmiao="";
		}
		frm.setJd1(jd);
		frm.setJddu(jddu);
		frm.setJdmiao(jdmiao);
		frm.setJdfen(jdfen);
	
      frm.setWd1(wd);
      frm.setWddu(wddu);
      frm.setWdfen(wdfen);
      frm.setWdmiao(wdmiao);
    //转季节性生产
   if(po.getQysczt().equals("Y")){
	   frm.setQysczt1("全年性生产");
   }else{
	   String ss="季节性生产 : ";
	   String[] strs=po.getQysczt().split(",");
	   for(int i=1;i<strs.length;i++){
		   ss +=strs[i]+"月  ";
	   }
	   frm.setQysczt1(ss);
  }
       frm.setQyzt(po.getQyzt());
       if(po.getQyzt().equals("0")){
    	   frm.setQyztmc("运营中");
       }else if(po.getQyzt().equals("1")){
    	   frm.setQyztmc("已关闭");
       }else if(po.getQyzt().equals("2")){
    	   frm.setQyztmc("已停产");
       }
       frm.setZt(po.getZt());
       frm.setFqpfks(po.getFqpfks());
       frm.setFspfks(po.getFspfks());
       frm.setZsygs(po.getZsygs());
       frm.setGfdfcgs(po.getGfdfcgs());
       frm.setJsxmmc(po.getJsxmmc());
       frm.setYyzzzch(po.getYyzzzch());
       frm.setSgxmmc(po.getSgxmmc());
       frm.setGcdd(po.getGcdd());
       frm.setXqyzcmc(po.getXqyzcmc());
       frm.setIsactive(po.getIsactive());
       frm.setXqyzcmc(po.getXqyzcmc());
       frm.setIsactive(po.getIsactive());
       if(po.getHylx()!=null&&po.getHylx()!=""){
			TDataIndustry tDataIndustry = (TDataIndustry) this.get(TDataIndustry.class, po.getHylx());
       	frm.setHylx(tDataIndustry.getId());
       	frm.setHylxmc(tDataIndustry.getName());
        }else{
       	 frm.setHylx(po.getHylx());
        }
    }


@Override
public TDataLawobjf getLawobjfInfo(TDataLawobjf lawobjf) {
	// TODO Auto-generated method stub
	lawobjf = (TDataLawobjf) this.get(TDataLawobjf.class, lawobjf.getId());
	return lawobjf;
}


@Override
public TDataLawobjf getLawobjfById(String id) {
	// TODO Auto-generated method stub
	TDataLawobjf lawobjf=(TDataLawobjf) this.dao.get(TDataLawobjf.class, id);
	return lawobjf;
}

 }

