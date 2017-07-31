package com.hnjz.app.work.danger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.data.enums.LawobjOutColunmEnum;
import com.hnjz.app.data.po.TBizWxpjbxx;
import com.hnjz.app.data.po.TDataDqhjbhmbfb;
import com.hnjz.app.data.po.TDataFwy;
import com.hnjz.app.data.po.TDataGywry;
import com.hnjz.app.data.po.TDataIndustry;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.data.po.TDataRegion;
import com.hnjz.app.data.po.TDataWg;
import com.hnjz.app.data.po.TDataYy;
import com.hnjz.app.data.xxgl.lawobj.LawobjManager;
import com.hnjz.app.data.zfdx.fwy.FwyManager;
import com.hnjz.app.data.zfdx.gywry.GywryManager;
import com.hnjz.app.data.zfdx.lawobjf.LawobjfManager;
import com.hnjz.app.data.zfdx.yy.YyManager;
import com.hnjz.app.work.enums.ZfdxLx;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.StringUtil;

@Service("qyjbqkManagerImpl")
public class QyjbqkManagerImpl extends ManagerImpl implements QyjbqkManager{
	@Autowired
	private LawobjManager lawobjManager;
	@Autowired
	private LawobjfManager tdatalawobjfManager;
	
	@Autowired
	private GywryManager tdatagywryManager;
	@Autowired
	private YyManager  tdatayyManager;
	@Autowired
	private FwyManager tdatafwyManager;
	
	@Override
	public QyjbqkForm queryQyjbqkForm(TDataLawobjf lawobjf,QyjbqkForm qyjbqkForm) throws Exception {
		TDataLawobjf lawobjfInfo = tdatalawobjfManager.getLawobjfInfo(lawobjf);
		TDataGywry  gywryInfo=tdatagywryManager.getGywry(lawobjf.getId());
		TDataYy yyInfo=tdatayyManager.getyy(lawobjf.getId());
		//TDataFwy fwyInfo=tdatafwyManager.getfwy(lawobjf.getId());
		TDataLawobjtype lawobjtype=(TDataLawobjtype) this.dao.get(TDataLawobjtype.class, lawobjfInfo.getLawobjtype().getId());
		String lawobjType=lawobjtype.getId();
		String dwmc=lawobjfInfo.getDwmc();  //单位名称
		String zzjgdm="";                  //组织机构代码
		if(lawobjfInfo.getLawobjtype().getId().equals("1")){
			zzjgdm=gywryInfo.getZzjgdm();  
		}else if(lawobjfInfo.getLawobjtype().getId().equals("3")){
			zzjgdm=yyInfo.getZzjgdm();  
		}else{
			zzjgdm=lawobjfInfo.getYyzzzch();
		}
		
		String fddbr=lawobjfInfo.getFddbr();  //法定代表人
		String ssxzqdm=lawobjfInfo.getSsxzq(); //所属行政区代码
		String ssxzq=this.findTDataRegionByRegionId(ssxzqdm).getName();;   //所属行政区
		String dwdz=lawobjfInfo.getDwdz();  //单位地址
		String jd=lawobjfInfo.getJd();   //经度
		String wd=lawobjfInfo.getWd();   //纬度
		String hbfzr=lawobjfInfo.getHbfzr(); //环保负责人
		String hbfzrdh=lawobjfInfo.getHbfzrdh(); //环保负责人电话
		String hylx="";   //行业类型
		String hylxdm="";   //行业代码
		String sswgdm=lawobjfInfo.getSswgqy();  //所属网格代码
		String sswg=this.findWgById(sswgdm).getWgmc();   //所属网格
		String wgms=this.findWgById(sswgdm).getMs();
		
		List<TBizWxpjbxx> list=this.dao.find("from TBizWxpjbxx t where t.lawobjId =?", lawobjf.getId());
		QyjbqkForm qyForm=new QyjbqkForm();
		//把经纬度改成度分秒
		
		if(StringUtil.isNotBlank(jd)&& !jd.equals(",,")){
			String[] arr=jd.split(",");
			if(arr.length == 1){
				if("null" != arr[0]){
					jd = arr[0]+"度";
					
				}else{
					jd = "";
				}
			}else if(arr.length == 2){
				jd = arr[0]+"度"+arr[1]+"分";
				
			}else if(arr.length == 3){
				jd = arr[0]+"度"+arr[1]+"分"+arr[2]+"秒";
				
			}
			
		}else{
			jd="";
			
		}
		if(StringUtil.isNotBlank(wd)&& !wd.equals(",,")){
			String[] arr=wd.split(",");
			if(arr.length == 1){
				if("null" != arr[0]){
					wd = arr[0]+"度";
					
				}else{
					wd = "";
				}
			}else if(arr.length == 2){
				wd = arr[0]+"度"+arr[1]+"分";
			
			}else if(arr.length == 3){
				wd = arr[0]+"度"+arr[1]+"分"+arr[2]+"秒";
				
			}
			
		}else{
			wd="";
		}
		if(list.size()>0){
			TBizWxpjbxx tw=list.get(0);
			qyForm.setAreaId(CtxUtil.getCurUser().getAreaId());
			qyForm.setCode(zzjgdm);
			qyForm.setCqmj(tw.getCqmj());
			qyForm.setCreater(CtxUtil.getCurUser());
			qyForm.setCreateTime(new Date());
			qyForm.setCzhm(tw.getCzhm());
			qyForm.setDwmc(dwmc);
			qyForm.setDwszd(dwdz);
			qyForm.setFddbr(fddbr);
			qyForm.setIsActive(tw.getIsActive());
			qyForm.setLawobjId(tw.getLawobjId());
			qyForm.setLawobjTypeId(tw.getLawobjTypeId());
			qyForm.setNscsj(tw.getNscsj());
			qyForm.setQh(tw.getQh());
			qyForm.setQyhjglrName(StringUtil.isNotBlank(hbfzr)?hbfzr:"");
			qyForm.setQyhjglrPhone(StringUtil.isNotBlank(hbfzrdh)?hbfzrdh:"");
			qyForm.setSfbzya(tw.getSfbzya());
			qyForm.setSfpjwj(tw.getSfpjwj());
			qyForm.setSftf(tw.getSftf());
			qyForm.setSftfcs(tw.getSftfcs());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
			if(tw.getSftfDate()!=null){
			String str=sdf.format(tw.getSftfDate());
			qyForm.setSftfDate(str);
			}
			qyForm.setUpdateby(tw.getUpdateby());
			qyForm.setUpdateTime(tw.getUpdateTime());
			qyForm.setXzqh(ssxzq);
			qyForm.setXzqhCode(ssxzqdm);
			qyForm.setYqmc(tw.getYqmc());
			qyForm.setYzbm(tw.getYzbm());
			qyForm.setZbdh(tw.getZbdh());
			qyForm.setZcz(tw.getZcz());
			qyForm.setZxjd(StringUtil.isNotBlank(jd)?jd:"");
			qyForm.setZxwd(StringUtil.isNotBlank(wd)?wd:"");
			qyForm.setHydm(StringUtil.isNotBlank(hylxdm)?hylxdm:"");
			qyForm.setHylb(StringUtil.isNotBlank(hylx)?hylx:"");
			qyForm.setSswgqy(sswg);
			qyForm.setSsqgdm(sswgdm);
			qyForm.setWgms(wgms);
			qyForm.setDhhm(tw.getDhhm());
			return qyForm;
		}else{
			qyForm.setLawobjId(lawobjf.getId());
			qyForm.setLawobjTypeId(lawobjType);
			qyForm.setAreaId(CtxUtil.getCurUser().getAreaId());
			qyForm.setCode(zzjgdm);
			qyForm.setCreater(CtxUtil.getCurUser());
			qyForm.setCreateTime(new Date());
			qyForm.setDwmc(dwmc);
			qyForm.setDwszd(dwdz);
			qyForm.setFddbr(fddbr);
			qyForm.setQyhjglrName(StringUtil.isNotBlank(hbfzr)?hbfzr:"");
			qyForm.setQyhjglrPhone(StringUtil.isNotBlank(hbfzrdh)?hbfzrdh:"");
			qyForm.setUpdateby(CtxUtil.getCurUser());
			qyForm.setUpdateTime(new Date());
			qyForm.setXzqh(ssxzq);
			qyForm.setXzqhCode(ssxzqdm);
			qyForm.setZxjd(StringUtil.isNotBlank(jd)?jd:"");
			qyForm.setZxwd(StringUtil.isNotBlank(wd)?wd:"");
			qyForm.setHydm(StringUtil.isNotBlank(hylxdm)?hylxdm:"");
			qyForm.setHylb(StringUtil.isNotBlank(hylx)?hylx:"");
			qyForm.setSswgqy(sswg);
			qyForm.setSsqgdm(sswgdm);
			qyForm.setWgms(wgms);
			return qyForm;
		}
		
	}
	@Override
	public TBizWxpjbxx quserTBizWxpjbxxById(String lawobjId,
			QyjbqkForm qyjbqkForm) throws Exception {
		if(StringUtils.isNotBlank(qyjbqkForm.getId())){
			TBizWxpjbxx tw=(TBizWxpjbxx) this.get(TBizWxpjbxx.class, qyjbqkForm.getId());
			return tw;
		}else{
			TBizWxpjbxx tw=null;
			return tw;
		}
		
	}
	
	@Override
	public TDataWg findWgById(String wgid) throws Exception {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(wgid)){
			TDataWg tr=(TDataWg) this.get(TDataWg.class, wgid);
			return tr;
		}else{
			return null;
		}
	}
	
	@Override
	public TDataRegion findTDataRegionByRegionId(String regionId)
			throws Exception {
		if(StringUtils.isNotBlank(regionId)){
			TDataRegion tr=(TDataRegion) this.get(TDataRegion.class, regionId);
			return tr;
		}else{
			return null;
		}
	}
	@Override
	public TDataIndustry findIndustryById(String id) throws Exception {
		if(StringUtils.isNotBlank(id)){
			TDataIndustry ti=(TDataIndustry) this.get(TDataIndustry.class, id);
			return ti;
		}else{
		return null;
		}
	}
	@Override
	public void saveQyFrom(QyjbqkForm qyjbqkForm) throws Exception {
		String sql = " delete from  t_biz_wxpjbxx t where t.LAWOBJID_= '"+qyjbqkForm.getLawobjId()+"'";
		
			this.dao.exec(sql);
			TBizWxpjbxx tw=new TBizWxpjbxx();
			tw.setCreater(CtxUtil.getCurUser());
			tw.setCreateTime(new Date());
			tw.setAreaId(CtxUtil.getAreaId());
			tw.setCqmj(qyjbqkForm.getCqmj());
			tw.setCzhm(qyjbqkForm.getCzhm());
			tw.setIsActive("Y");
			tw.setLawobjId(qyjbqkForm.getLawobjId());
			tw.setLawobjTypeId(qyjbqkForm.getLawobjTypeId());
			tw.setNscsj(qyjbqkForm.getNscsj());
			tw.setQh(qyjbqkForm.getQh());
			tw.setSfbzya(qyjbqkForm.getSfbzya());
			tw.setSfpjwj(qyjbqkForm.getSfpjwj());
			tw.setSftf(qyjbqkForm.getSftf());
			tw.setSftfcs(qyjbqkForm.getSftfcs());
			tw.setDhhm(qyjbqkForm.getDhhm());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
			String dstr=qyjbqkForm.getSftfDate();
			if(!dstr.equals("")){
				Date date=sdf.parse(dstr);
				tw.setSftfDate(date);
			}
			tw.setUpdateby(CtxUtil.getCurUser());
			tw.setUpdateTime(new Date());
			tw.setYqmc(qyjbqkForm.getYqmc());
			tw.setYzbm(qyjbqkForm.getYzbm());
			tw.setZbdh(qyjbqkForm.getZbdh());
			tw.setZcz(qyjbqkForm.getZcz());
			this.dao.save(tw);
		
		
	}
	@Override
	public FyWebResult queryQyjbqkFormList(String lawobjId,String page,String pageSize)throws Exception {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder(" from TBizWxpjbxx t where t.isActive='Y' and ");
		if (StringUtils.isNotBlank(lawobjId)) {
			sql.append(" t.lawobjId=:lawobjId");
			data.put("lawobjId", lawobjId);
		}
		sql.append(" order by t.updateTime desc ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		
		List<TBizWxpjbxx> whyLists = pos.getRe();
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		Map<String, String> dataObject = null;
		TDataLawobjf lawobjf =new TDataLawobjf();
		lawobjf.setId(lawobjId);
		for (TBizWxpjbxx tw : whyLists) {
			QyjbqkForm qyjbqkForm=new QyjbqkForm();
			qyjbqkForm.setId(tw.getId());
			QyjbqkForm qf=this.queryQyjbqkForm(lawobjf, qyjbqkForm);
			dataObject = new HashMap<String, String>();
			dataObject.put("dwmc", String.valueOf(qf.getDwmc()));
			dataObject.put("code", String.valueOf(qf.getCode()));
			dataObject.put("dwszd", String.valueOf(qf.getDwszd()));
			dataObject.put("id", String.valueOf(tw.getId()));
			dataObject.put("lawobjId", String.valueOf(tw.getLawobjId()));
			dataObject.put("qh", String.valueOf(tw.getQh()));
			dataObject.put("zbdh", tw.getZbdh()==null?"":String.valueOf(tw.getZbdh()));
			dataObject.put("czhm", String.valueOf(tw.getCzhm()));
			dataObject.put("yzbm", String.valueOf(tw.getYzbm()));
			dataObject.put("lawobjTypeId", String.valueOf(tw.getLawobjTypeId()));
			dataObject.put("yqmc", String.valueOf(tw.getYqmc()));
			dataObject.put("nscsj", String.valueOf(tw.getNscsj()));
			dataObject.put("zcz", String.valueOf(tw.getZcz()));
			dataObject.put("cqmj", String.valueOf(tw.getCqmj()));
			dataObject.put("sfbzya", String.valueOf(tw.getSfbzya()));
			dataObject.put("sfpjwj", String.valueOf(tw.getSfpjwj()));
			dataObject.put("sftf", String.valueOf(tw.getSftf()));
			dataObject.put("sftfcs", String.valueOf(tw.getSftfcs()));
			dataObject.put("isActive", "Y".equals(String.valueOf(tw.getIsActive()))?"有效":"无效");
			//dataObject.put("operate", OperateUtil.getOperate(String.valueOf(tq.getId())));
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate","<a id='"+tw.getId()+"' class='b-link' onclick='infoJbxx(this)'>查看</a> <a id='"+tw.getId()+"' class='b-link' onclick='modifyJbxx(this)'>修改</a> <a id='"+tw.getId()+"' class='b-link' onclick='delJbxx(this)'>删除</a> ");
			
			
			rows.add(dataObject);
		}
		
		fy.setRows(rows);
		
		return fy;
	}
	@Override
	public void delJbxx(String id) throws Exception {
		String sql = " delete from  t_biz_wxpjbxx t where t.ID_= '"+id+"'";
		
		this.dao.exec(sql);
		
	}
	

}
