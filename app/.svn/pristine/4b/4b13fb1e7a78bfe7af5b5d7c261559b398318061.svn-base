package com.hnjz.app.data.xxgl.fsaq;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.data.enums.LawobjOutColunmEnum;
import com.hnjz.app.data.po.TBizWxpjbxx;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.po.TDataFsaqjcxx;
import com.hnjz.app.data.po.TDataGywry;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataSc;
import com.hnjz.app.data.po.TDataYy;
import com.hnjz.app.data.xxgl.lawobj.LawobjManager;
import com.hnjz.app.data.zfdx.gywry.GywryManager;
import com.hnjz.app.data.zfdx.sc.ScManager;
import com.hnjz.app.data.zfdx.yy.YyManager;
import com.hnjz.app.work.comUtil.VelocityUtil;
import com.hnjz.app.work.danger.AirForm;
import com.hnjz.app.work.danger.AirProjectForm;
import com.hnjz.app.work.danger.HjfxffcsForm;
import com.hnjz.app.work.danger.QyhjyjczjjyzyForm;
import com.hnjz.app.work.danger.QyhxwzqkfcpForm;
import com.hnjz.app.work.danger.QyhxwzqkylForm;
import com.hnjz.app.work.danger.QyhxwzqkzycpForm;
import com.hnjz.app.work.danger.QyjbqkForm;
import com.hnjz.app.work.danger.WaterForm;
import com.hnjz.app.work.danger.WaterProjectForm;
import com.hnjz.app.work.enums.ZfdxLx;
import com.hnjz.app.work.wszz.WszzManager;
import com.hnjz.app.work.zx.BlMainForm;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.PoiUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.po.TSysOrg;

@Service("fsaqManagerImpl")
public class FsaqManagerImpl extends ManagerImpl implements FsaqManager{
	private static final Log log=LogFactory.getLog(FsaqManagerImpl.class);
	@Autowired
	private WszzManager wszzManager;
	@Autowired
	private CommonManager commonManager;
	@Autowired
	private CommWorkManager commWorkManager;
	@Autowired
	private LawobjManager lawobjManager;
	@Autowired
	private GywryManager tdatagywryManager;
	@Autowired
	private YyManager tdatayyManager;
	@Autowired
	private ScManager tdatascManager;
	
	@Override
	public FsaqForm queryFsaqForm(String lawobjId, String id,
			String lawobjTypeId) throws Exception {
		TDataLawobjf lawobjf= (TDataLawobjf) this.dao.get(TDataLawobjf.class, lawobjId);
		TDataGywry  gywryInfo=tdatagywryManager.getGywry(lawobjf.getId());
		TDataYy yyInfo=tdatayyManager.getyy(lawobjf.getId());
		TDataSc scInfo=tdatascManager.getsc(lawobjf.getId());
		lawobjf.setId(lawobjId);
		String dwmc=lawobjf.getDwmc();  //单位名称
		String fddbr=lawobjf.getFddbr(); //法定代表人
		String fddbrdh=lawobjf.getFddbrdh(); //法定代表人电话
		String adress=lawobjf.getDwdz();  // 单位地址
		String hbfzrName=lawobjf.getHbfzr(); //环保负责人
		String hbfzrPhone=lawobjf.getHbfzrdh(); //环保负责人电话
		String yzbm=""; //邮编  
		if(lawobjf.getLawobjtype().getId().equals("1")){
			 yzbm=gywryInfo.getYb();  
		}else if(lawobjf.getLawobjtype().getId().equals("3")){
			 yzbm=yyInfo.getYzbm();  
		}else if(lawobjf.getLawobjtype().getId().equals("7")){
			yzbm=scInfo.getYzbm();
		}
		 
		
		
		List<TDataFsaqjcxx> list=this.dao.find("from TDataFsaqjcxx t where t.lawobjId =? and t.lawobjTypeId =? ", lawobjf.getId(),lawobjTypeId);
		FsaqForm fsaqForm=new FsaqForm();
		if(list.size()>0){
			TDataFsaqjcxx tw=list.get(0);
			fsaqForm.setAdress(StringUtil.isNotBlank(adress)?adress:"");
			fsaqForm.setDwmc(StringUtil.isNotBlank(dwmc)?dwmc:"");
			fsaqForm.setFddbr(StringUtil.isNotBlank(fddbr)?fddbr:"");
			fsaqForm.setFddbrdh(StringUtil.isNotBlank(fddbrdh)?fddbrdh:"");
			fsaqForm.setHbfzr(StringUtil.isNotBlank(hbfzrName)?hbfzrName:"");
			fsaqForm.setHbfzrdh(StringUtil.isNotBlank(hbfzrPhone)?hbfzrPhone:"");
			fsaqForm.setYzbm(StringUtil.isNotBlank(yzbm)?yzbm:"");
			fsaqForm.setId(tw.getId());
			fsaqForm.setAqfh(tw.getAqfh());
			fsaqForm.setAqfhjgmc(tw.getAqfhjgmc());
			fsaqForm.setAreaId(tw.getAreaId());
			fsaqForm.setDh(tw.getDh());
			fsaqForm.setFjfsylsqk(tw.getFjfsylsqk());
			fsaqForm.setFjfsysanl(tw.getFjfsysanl());
			fsaqForm.setFjfsysil(tw.getFjfsysil());
			fsaqForm.setFjfsysillsqk(tw.getFjfsysillsqk());
			fsaqForm.setFjfsysilwzhd(tw.getFjfsysilwzhd());
			fsaqForm.setFsyerl(tw.getFsyerl());
			fsaqForm.setFsysanl(tw.getFsysanl());
			fsaqForm.setFsysil(tw.getFjfsysil());
			fsaqForm.setFsywul(tw.getFsywul());
			fsaqForm.setFsyyil(tw.getFsyyil());
			fsaqForm.setFsyzs(tw.getFsyzs());
			fsaqForm.setFzr(tw.getFzr());
			fsaqForm.setGzrysl(tw.getGzrysl());
			fsaqForm.setHgzrs(tw.getHgzrs());
			fsaqForm.setIsActive(tw.getIsActive());
			fsaqForm.setJljcrs(tw.getJljcrs());
			fsaqForm.setLawobjId(tw.getLawobjId());
			fsaqForm.setLawobjTypeId(tw.getLawobjTypeId());
			fsaqForm.setXkzh(tw.getXkzh());
			fsaqForm.setXkzlfw(tw.getXkzlfw());
			fsaqForm.setXl(tw.getXl());
			fsaqForm.setYxqnrs(tw.getYxqnrs());
			fsaqForm.setHbfzrcz(tw.getHbfzrcz());
			fsaqForm.setHbfzremail(tw.getHbfzremail());
			fsaqForm.setZy(tw.getZy());
			fsaqForm.setZysxzzerl(tw.getZysxzzerl());
			fsaqForm.setZysxzzsanl(tw.getZysxzzsanl());
			fsaqForm.setZysxzzyil(tw.getZysxzzyil());
			fsaqForm.setZysxzzzs(tw.getZysxzzzs());
		}else{
			fsaqForm.setAdress(StringUtil.isNotBlank(adress)?adress:"");
			fsaqForm.setDwmc(StringUtil.isNotBlank(dwmc)?dwmc:"");
			fsaqForm.setFddbr(StringUtil.isNotBlank(fddbr)?fddbr:"");
			fsaqForm.setFddbrdh(StringUtil.isNotBlank(fddbrdh)?fddbrdh:"");
			fsaqForm.setHbfzr(StringUtil.isNotBlank(hbfzrName)?hbfzrName:"");
			fsaqForm.setHbfzrdh(StringUtil.isNotBlank(hbfzrPhone)?hbfzrPhone:"");
			fsaqForm.setLawobjId(lawobjId);
			fsaqForm.setLawobjTypeId(lawobjTypeId);
			fsaqForm.setYzbm(StringUtil.isNotBlank(yzbm)?yzbm:"");
		}
		return fsaqForm;
	}
	@Override
	public void saveFsaqForm(FsaqForm fsaqForm) throws Exception {
		TDataFsaqjcxx  tf=new TDataFsaqjcxx();
		if(StringUtil.isNotBlank(fsaqForm.getId())){
			tf=(TDataFsaqjcxx)this.get(TDataFsaqjcxx.class,fsaqForm.getId());
			tf.setAqfh(fsaqForm.getAqfh());
			tf.setAqfhjgmc(fsaqForm.getAqfhjgmc());
			tf.setDh(fsaqForm.getDh());
			tf.setFjfsylsqk(fsaqForm.getFjfsylsqk());
			tf.setFjfsysanl(fsaqForm.getFjfsysanl());
			tf.setFjfsysil(fsaqForm.getFjfsysil());
			tf.setFjfsysillsqk(fsaqForm.getFjfsysillsqk());
			tf.setFjfsysilwzhd(fsaqForm.getFjfsysilwzhd());
			tf.setFsyerl(fsaqForm.getFsyerl());
			tf.setFsysanl(fsaqForm.getFsysanl());
			tf.setFsysil(fsaqForm.getFjfsysil());
			tf.setFsywul(fsaqForm.getFsywul());
			tf.setFsyyil(fsaqForm.getFsyyil());
			tf.setFsyzs(fsaqForm.getFsyzs());
			tf.setFzr(fsaqForm.getFzr());
			tf.setGzrysl(fsaqForm.getGzrysl());
			tf.setHgzrs(fsaqForm.getHgzrs());
			tf.setJljcrs(fsaqForm.getJljcrs());
			tf.setLawobjId(fsaqForm.getLawobjId());
			tf.setLawobjTypeId(fsaqForm.getLawobjTypeId());
			tf.setXkzh(fsaqForm.getXkzh());
			tf.setXkzlfw(fsaqForm.getXkzlfw());
			tf.setXl(fsaqForm.getXl());
			tf.setYxqnrs(fsaqForm.getYxqnrs());
			tf.setHbfzrcz(fsaqForm.getHbfzrcz());
			tf.setHbfzremail(fsaqForm.getHbfzremail());
			tf.setZy(fsaqForm.getZy());
			tf.setZysxzzerl(fsaqForm.getZysxzzerl());
			tf.setZysxzzsanl(fsaqForm.getZysxzzsanl());
			tf.setZysxzzyil(fsaqForm.getZysxzzyil());
			tf.setZysxzzzs(fsaqForm.getZysxzzzs());
			tf.setUpdateby(CtxUtil.getCurUser());
			tf.setUpdateTime(new Date());
		}else{
			tf.setAqfh(fsaqForm.getAqfh());
			tf.setAqfhjgmc(fsaqForm.getAqfhjgmc());
			tf.setAreaId(CtxUtil.getAreaId());
			tf.setDh(fsaqForm.getDh());
			tf.setFjfsylsqk(fsaqForm.getFjfsylsqk());
			tf.setFjfsysanl(fsaqForm.getFjfsysanl());
			tf.setFjfsysil(fsaqForm.getFjfsysil());
			tf.setFjfsysillsqk(fsaqForm.getFjfsysillsqk());
			tf.setFjfsysilwzhd(fsaqForm.getFjfsysilwzhd());
			tf.setFsyerl(fsaqForm.getFsyerl());
			tf.setFsysanl(fsaqForm.getFsysanl());
			tf.setFsysil(fsaqForm.getFjfsysil());
			tf.setFsywul(fsaqForm.getFsywul());
			tf.setFsyyil(fsaqForm.getFsyyil());
			tf.setFsyzs(fsaqForm.getFsyzs());
			tf.setFzr(fsaqForm.getFzr());
			tf.setGzrysl(fsaqForm.getGzrysl());
			tf.setHgzrs(fsaqForm.getHgzrs());
			tf.setIsActive("Y");
			tf.setJljcrs(fsaqForm.getJljcrs());
			tf.setLawobjId(fsaqForm.getLawobjId());
			tf.setLawobjTypeId(fsaqForm.getLawobjTypeId());
			tf.setXkzh(fsaqForm.getXkzh());
			tf.setXkzlfw(fsaqForm.getXkzlfw());
			tf.setXl(fsaqForm.getXl());
			tf.setYxqnrs(fsaqForm.getYxqnrs());
			tf.setHbfzrcz(fsaqForm.getHbfzrcz());
			tf.setHbfzremail(fsaqForm.getHbfzremail());
			tf.setZy(fsaqForm.getZy());
			tf.setZysxzzerl(fsaqForm.getZysxzzerl());
			tf.setZysxzzsanl(fsaqForm.getZysxzzsanl());
			tf.setZysxzzyil(fsaqForm.getZysxzzyil());
			tf.setZysxzzzs(fsaqForm.getZysxzzzs());
			tf.setUpdateby(CtxUtil.getCurUser());
			tf.setUpdateTime(new Date());
			tf.setCreater(CtxUtil.getCurUser());
			tf.setCreateTime(new Date());
		}
		this.dao.save(tf);
	}
	@Override
	public HashMap<String, String> createFsaqWord(HttpServletResponse response,String lawobjId,String lawobjType, String biaoshi,String appleId) throws Exception {
		HashMap<String, String> ret = new HashMap<String, String>();
		Map<String, String> paraMap = new HashMap<String, String>();
		// 生成word所用的数据
		FsaqForm fsaqForm=this.queryFsaqForm(lawobjId, "", lawobjType);
		BlMainForm blMainForm = commWorkManager.getBlMainFormData(appleId);
		
				// 标题
		TSysOrg o = wszzManager.getOrgbyUser( CtxUtil.getUserId());
		        paraMap.put("$title$", o.getUnitname());
				paraMap.put("$dwmc$", fsaqForm.getDwmc());
				paraMap.put("$adress$", fsaqForm.getAdress());
				paraMap.put("$hbfzr$", blMainForm.getBlZfdxForm().getBjcr());
				paraMap.put("$hbfzrdh$", blMainForm.getBlZfdxForm().getLxdh());
				paraMap.put("$hbfzrcz$", fsaqForm.getHbfzrcz());
				paraMap.put("$hbfzremail$", fsaqForm.getHbfzremail());
				paraMap.put("$yzbm$", fsaqForm.getYzbm());
				paraMap.put("$fddbr$", fsaqForm.getFddbr());
				paraMap.put("$fddbrdh$", fsaqForm.getFddbrdh());
				paraMap.put("$xkzh$", fsaqForm.getXkzh());
				paraMap.put("$xkzlfw$", fsaqForm.getXkzlfw());
				paraMap.put("$aqfh$", fsaqForm.getAqfh());
				paraMap.put("$aqfhjgmc$", fsaqForm.getAqfhjgmc());
				paraMap.put("$fzr$", fsaqForm.getFzr());
				paraMap.put("$xl$", fsaqForm.getXl());
				paraMap.put("$zy$", fsaqForm.getZy());
				paraMap.put("$dh$", fsaqForm.getDh());
				paraMap.put("$gzrysl$",fsaqForm.getGzrysl()==null?"":fsaqForm.getGzrysl().toString());
				paraMap.put("$hgzrs$", fsaqForm.getHgzrs()==null?"":fsaqForm.getHgzrs().toString());
				paraMap.put("$yxqnrs$",fsaqForm.getYxqnrs()==null?"":fsaqForm.getYxqnrs().toString());
				paraMap.put("$jljcrs$",fsaqForm.getJljcrs()==null?"":fsaqForm.getJljcrs().toString());
				paraMap.put("$fsyzs$", fsaqForm.getFsyzs()==null?"":fsaqForm.getFsyzs().toString());
				paraMap.put("$fsyyil$",fsaqForm.getFsyyil()==null?"":fsaqForm.getFsyyil().toString());
				paraMap.put("$fsyerl$",fsaqForm.getFsyerl()==null?"":fsaqForm.getFsyerl().toString());
				paraMap.put("$fsysanl$",fsaqForm.getFsysanl()==null?"":fsaqForm.getFsysanl().toString());
				paraMap.put("$fsysil$", fsaqForm.getFsysil()==null?"":fsaqForm.getFsysil().toString());
				paraMap.put("$fsywul$", fsaqForm.getFsywul()==null?"":fsaqForm.getFsywul().toString());
				paraMap.put("$fjfsysanl$",fsaqForm.getFjfsysanl()==null?"":fsaqForm.getFjfsysanl().toString());
				paraMap.put("$fjfsylsqk$",fsaqForm.getFjfsylsqk()==null?"":fsaqForm.getFjfsylsqk());
				paraMap.put("$fjfsysil$",fsaqForm.getFjfsysil()==null?"":fsaqForm.getFjfsysil().toString());
				paraMap.put("$wzhd$", fsaqForm.getFjfsysilwzhd()==null?"":fsaqForm.getFjfsysilwzhd().toString());
				paraMap.put("$fjfsysillsqk$",fsaqForm.getFjfsysillsqk()==null?"":fsaqForm.getFjfsysillsqk());
				paraMap.put("$zysxzzzs$",fsaqForm.getZysxzzzs()==null?"":fsaqForm.getZysxzzzs().toString());
				paraMap.put("$zysxzzyil$",fsaqForm.getZysxzzyil()==null?"":fsaqForm.getZysxzzyil().toString());
				paraMap.put("$zysxzzerl$",fsaqForm.getZysxzzerl()==null?"":fsaqForm.getZysxzzerl().toString());
				paraMap.put("$zysxzzsanl$",fsaqForm.getZysxzzsanl()==null?"":fsaqForm.getZysxzzsanl().toString());
				
				
				// 环线
				
				String classPath = this.getClass().getResource("").getPath();
				classPath = java.net.URLDecoder.decode(classPath, "utf-8");
				// 模板路径
				String templatePath = null;
				// 生成word的名称
				String oName = null;
				TDataLawobj lawobj=(TDataLawobj) this.get(TDataLawobj.class, lawobjId);
				String lawobjName=lawobjManager.getNameByLawobj(lawobj);
				// 检查模板路径
				if(lawobjType.equals(ZfdxLx.GYWRY.getCode())){
					if("1".equals(biaoshi)){
						oName =lawobjName+"Ⅲ类源监督检查表.doc";
						templatePath = classPath.substring(0, classPath.lastIndexOf("/WEB-INF/")) + "//excel//fsaq//gywrysly.doc";
					}else if("2".equals(biaoshi)){
						oName =lawobjName+"固定式Ⅲ、Ⅳ和Ⅴ类源监督检查表.doc";
						templatePath = classPath.substring(0, classPath.lastIndexOf("/WEB-INF/")) + "//excel//fsaq//gywrygds.doc";
					}else if("3".equals(biaoshi)){
						oName =lawobjName+"移动式Ⅲ、Ⅳ和Ⅴ类源监督检查表.doc";
						templatePath = classPath.substring(0, classPath.lastIndexOf("/WEB-INF/")) + "//excel//fsaq//gywryyds.doc";
					}
				}else if(lawobjType.equals(ZfdxLx.YY.getCode())){
					if("1".equals(biaoshi)){
						oName =lawobjName+"Ⅲ类源监督检查表.doc";
						templatePath = classPath.substring(0, classPath.lastIndexOf("/WEB-INF/")) + "//excel//fsaq//yysly.doc";
					}else if("2".equals(biaoshi)){
						oName =lawobjName+"固定式Ⅲ、Ⅳ和Ⅴ类源监督检查表.doc";
						templatePath = classPath.substring(0, classPath.lastIndexOf("/WEB-INF/")) + "//excel//fsaq//yygds.doc";
					}else if("3".equals(biaoshi)){
						oName =lawobjName+"移动式Ⅲ、Ⅳ和Ⅴ类源监督检查表.doc";
						templatePath = classPath.substring(0, classPath.lastIndexOf("/WEB-INF/")) + "//excel//fsaq//yyyds.doc";
					}
				}else if(lawobjType.equals(ZfdxLx.SC.getCode())){
					if("1".equals(biaoshi)){
						oName =lawobjName+"Ⅲ类源监督检查表.doc";
						templatePath = classPath.substring(0, classPath.lastIndexOf("/WEB-INF/")) + "//excel//fsaq//scsly.doc";
					}else if("2".equals(biaoshi)){
						oName =lawobjName+"固定式Ⅲ、Ⅳ和Ⅴ类源监督检查表.doc";
						templatePath = classPath.substring(0, classPath.lastIndexOf("/WEB-INF/")) + "//excel//fsaq//scgds.doc";
					}else if("3".equals(biaoshi)){
						oName =lawobjName+"移动式Ⅲ、Ⅳ和Ⅴ类源监督检查表.doc";
						templatePath = classPath.substring(0, classPath.lastIndexOf("/WEB-INF/")) + "//excel//fsaq//scyds.doc";
					}
				}
				
				// 生成的路径
				String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath());
				String filePath = PoiUtil.createWord(templatePath, dirPath, paraMap);
				File file = new File(filePath);
			   
				String path = UploadFileType.WORK.getPath() + File.separator + file.getName();
				//下载文件
				FileUpDownUtil.downloadFile(response,path,oName);
			
		return ret;
	} 

}
