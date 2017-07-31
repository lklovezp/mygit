package com.hnjz.app.data.zfdx.gl;


import java.text.ParseException;
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

import com.hnjz.app.data.po.TDataFwy;
import com.hnjz.app.data.po.TDataGl;
import com.hnjz.app.data.po.TDataGywry;
import com.hnjz.app.data.po.TDataIndustry;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.data.po.TDataRegion;
import com.hnjz.app.data.po.TDataWg;
import com.hnjz.app.data.po.TDataXgjl;
import com.hnjz.app.data.zfdx.FzUtils;
import com.hnjz.app.data.zfdx.gywry.GywryForm;
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

import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;

/**
 * TDataGl管理的manager
 * 
 * @author xuyaxing
 * @version 
 */
 @Service("TDataGlManagerImpl")
public class GlManagerImpl extends ManagerImpl implements GlManager{


/** 日志 */
	private static final Log log = LogFactory.getLog(GlManagerImpl.class);

	



    
    
    /**
	 * 保存TDataGl
	 * 
	 * @param frm
	 * @throws AppException
	 */
	public TDataLawobjf saveGl(GlForm frm) throws AppException{
	
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
		
		TDataLawobjf po =  null;
		TDataLawobjf poold = new TDataLawobjf();
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
       po.setUpdated(cur);
       po.setLawobjtype(lawobjtype);
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
    
		
		
       TDataGl po1= null;
		
       TDataGl po1old= new TDataGl();
   
    
		if(frm.getLawobjfid()!=null && frm.getLawobjfid()!=""){
			po1= (TDataGl) this.getGl(frm.getLawobjfid());
			FzUtils.converJavaBean(po1old, po1);
			lawobjf = (TDataLawobjf) this.dao.get(TDataLawobjf.class, frm.getLawobjfid());
			po1.setLawobjf(lawobjf);
		}else{
			po1=new TDataGl();
			po1.setLawobjf(po);
		}
		
       
       po1.setPwxkz(frm.getPwxkz());
       
       
       po1.setWryfzsssl(frm.getWryfzsssl());
       
       
       po1.setZzjgdm(frm.getZzjgdm());
       
       po1.setUpdated(cur);
       po1.setYt(frm.getYt());
       
       
       po1.setGls(frm.getGls());
       
       
       po1.setZd(frm.getZd());
       
       po1.setIsActive(YnEnum.Y.getCode());
       
       
       String zxzd=FzUtils.contrastObj(po1old, po1);
    
    this.save(po1);
    TDataXgjl xgjl=new TDataXgjl();
    
    
    xgjl.setZfdxid(po.getId());
    xgjl.setCjr(po.getCjr());
    xgjl.setCjsj(po.getCjsj());
    xgjl.setXgnr(fxzd+" "+zxzd);
    xgjl.setXgr(user.getId());
    xgjl.setXgsj(cur);
    this.dao.save(xgjl);
    return po;
    
    }


/**
	 * 编辑查看修改TDataGl
	 * 
	 * @param id
	 *            TDataGl的ID
	 */
    @Override
	public void editGl(GlForm frm){
       TDataGl po=this.getGl(frm.getId());
       
       if(null !=po.getLawobjf()){
    	   TDataLawobjf lawobjf=(TDataLawobjf) this.dao.get(TDataLawobjf.class,po.getLawobjf().getId());
    	   frm.setId(lawobjf.getId());
    	   frm.setLawobjfid(lawobjf.getId());
       if(null !=lawobjf.getJsxm()){
    	   TDataLawobjf jsxm=(TDataLawobjf)this.dao.get(TDataLawobjf.class,lawobjf.getJsxm().getId());
    	   frm.setJsxmid(jsxm.getId());
    	   frm.setJsxmmc(jsxm.getDwmc());
       }
           frm.setIsold(lawobjf.getIsold());
       if(null !=lawobjf.getLawobjtype()){
    	  TDataLawobjtype lawobjtype = (TDataLawobjtype) this.dao.get(TDataLawobjtype.class, lawobjf.getLawobjtype().getId());
    	  frm.setLawobjtypeid(lawobjtype.getId());
    	  frm.setLawobjtypename(lawobjtype.getName());
       }
           frm.setDwmc(lawobjf.getDwmc());
           frm.setDwdz(lawobjf.getDwdz());
           frm.setFddbr(lawobjf.getFddbr());
           frm.setFddbrdh(lawobjf.getFddbrdh());
           frm.setHbfzr(lawobjf.getHbfzr());
           frm.setHbfzrdh(lawobjf.getHbfzrdh());
           if(null !=lawobjf.getCjr()){
        	   TSysUser user=(TSysUser) this.dao.get(TSysUser.class, lawobjf.getCjr());
        	   frm.setCjr(user.getName());
           }else{
        	   TSysUser use=CtxUtil.getCurUser();
        	   frm.setCjr(use.getName());
           }
                
           
           frm.setSsjgbm(lawobjf.getSsjgbm());
          
           if(lawobjf.getSsjgbm()!=null){
        	   TSysOrg orgs=(TSysOrg) this.dao.get(TSysOrg.class, lawobjf.getSsjgbm());
        	   frm.setSsjgbmmc(orgs.getName());
           }
           
          
           frm.setSsxzq(lawobjf.getSsxzq());
           TDataRegion regions=(TDataRegion) this.dao.get(TDataRegion.class, lawobjf.getSsxzq());
           frm.setSsxzqmc(regions.getName());
           frm.setSswgqy(lawobjf.getSswgqy());
           TDataWg wgs=(TDataWg) this.dao.get(TDataWg.class, lawobjf.getSswgqy());
           frm.setSswgqymc(wgs.getWgmc());
           frm.setQysczt(lawobjf.getQysczt());
           frm.setJd(lawobjf.getJd());
           frm.setWd(lawobjf.getWd());
         //把经纬度改成度分秒
  		 String jd="";
  		 String wd="";
  		 String jddu="";
  		 String jdfen="";
  		 String jdmiao="";
  		 String wddu="";
  		 String wdfen="";
  		 String wdmiao="";
  		if(StringUtil.isNotBlank(lawobjf.getJd())&& !lawobjf.getJd().equals(",,")){
  			String[] arr=lawobjf.getJd().split(",");
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
  		if(StringUtil.isNotBlank(lawobjf.getWd())&& !lawobjf.getWd().equals(",,")){
  			String[] arr=lawobjf.getWd().split(",");
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
       if(lawobjf.getQysczt().equals("Y")){
    	   frm.setQysczt1("全年性生产");
       }else{
		   String ss="季节性生产 : ";
		   String[] strs=lawobjf.getQysczt().split(",");
		   for(int i=1;i<strs.length;i++){
			   ss +=strs[i]+"月  ";
		   }
		   frm.setQysczt1(ss);
      }
           frm.setQyzt(lawobjf.getQyzt());
           if(lawobjf.getQyzt().equals("0")){
        	   frm.setQyztmc("运营中");
           }else if(lawobjf.getQyzt().equals("1")){
        	   frm.setQyztmc("已关闭");
           }else if(lawobjf.getQyzt().equals("2")){
        	   frm.setQyztmc("已停产");
           }
           frm.setZt(lawobjf.getZt());
           frm.setFqpfks(lawobjf.getFqpfks());
           frm.setFspfks(lawobjf.getFspfks());
           frm.setZsygs(lawobjf.getZsygs());
           frm.setGfdfcgs(lawobjf.getGfdfcgs());
           frm.setJsxmmc(lawobjf.getJsxmmc());
           frm.setYyzzzch(lawobjf.getYyzzzch());
           frm.setSgxmmc(lawobjf.getSgxmmc());
           frm.setGcdd(lawobjf.getGcdd());
           frm.setXqyzcmc(lawobjf.getXqyzcmc());
           frm.setIsactive(lawobjf.getIsactive());
           if(lawobjf.getHylx()!=null&&lawobjf.getHylx()!=""){
           TDataIndustry tDataIndustry = (TDataIndustry) this.get(TDataIndustry.class,lawobjf.getHylx());
        	frm.setHylx(tDataIndustry.getId());
        	frm.setHylxmc(tDataIndustry.getName());
            }
       }else{
           frm.setId("");
       }
       
       
       frm.setPwxkz(po.getPwxkz());
       
       
       frm.setWryfzsssl(po.getWryfzsssl());
       
       
       frm.setZzjgdm(po.getZzjgdm());
       
       
       frm.setYt(po.getYt());
       
       
       frm.setGls(po.getGls());
       
       
       frm.setZd(po.getZd());
       
       
    }





	@Override
	public TDataGl getGl(String lawobjid) {
		// TODO Auto-generated method stub
		TDataGl gl = null;
		String hsql = " from TDataGl where lawobjf.id=?";
		List<TDataGl> re = this.dao.find(hsql, lawobjid);
		if (re.isEmpty()) {
			return null;
		} else {
			gl = re.get(0);
		}

		return gl;
	}


	@Override
	public void delGl(String lawobjid) {
		// TODO Auto-generated method stub
		TDataGl del = this.getGl(lawobjid);
		del.setIsActive(YnEnum.N.getCode());
		this.dao.save(del);
	}

}

