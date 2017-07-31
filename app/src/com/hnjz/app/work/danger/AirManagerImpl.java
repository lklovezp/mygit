package com.hnjz.app.work.danger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.enums.LawobjOutColunmEnum;
import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TDataChecklisttemplate;
import com.hnjz.app.data.po.TDataDqhjbhmbfb;
import com.hnjz.app.data.po.TDataDqhjjbzk;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjdic;
import com.hnjz.app.data.po.TDataLawobjf;
import com.hnjz.app.data.po.TDataQyhxwzqkzycp;
import com.hnjz.app.data.po.TDataShjmbfb;
import com.hnjz.app.data.xxgl.lawobj.LawobjManager;
import com.hnjz.app.data.xxgl.lawobj.LawobjManagerImpl;
import com.hnjz.app.work.comUtil.VelocityUtil;
import com.hnjz.app.work.enums.HjzlEnum;
import com.hnjz.app.work.enums.TaskUserEnum;
import com.hnjz.app.work.po.TBizTaskandlawobj;
import com.hnjz.app.work.po.TBizTaskandtasktype;
import com.hnjz.app.work.po.TBizTaskuser;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.PoiUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.po.TSysUserOrg;
@Service("airManagerImpl")
public class AirManagerImpl extends ManagerImpl implements AirManager{
	private static final Log log=LogFactory.getLog(AirController.class);
    @Autowired
	private CommonManager commonManager;
    @Autowired
    private DangerManager dangerManager;
    @Autowired
    private HjfxffcsManager hjfxffcsManager;
    @Autowired
    private QyhjyjczjjyzyManager qyhjyjczjjyzyManager;
    @Autowired
    private WaterManager waterManager;
    @Autowired
    private LawobjManager lawobjManager;
    @Autowired
    private QyjbqkManager qyjbqkManager;
   	@Override
	public void saveAirProjectForm(AirProjectForm airProjectForm) throws AppException {
		if(StringUtils.isNotBlank(airProjectForm.getId())){
			TDataDqhjbhmbfb ts=(TDataDqhjbhmbfb) this.get(TDataDqhjbhmbfb.class, airProjectForm.getId());
			ts.setAreaId(CtxUtil.getAreaId());
			ts.setBhmbmc(airProjectForm.getBhmbmc());
			ts.setJd(airProjectForm.getJd());
			ts.setJl(airProjectForm.getJl());
			ts.setLx(airProjectForm.getLx());
			ts.setSljb(airProjectForm.getSljb());
			ts.setSswjgn(airProjectForm.getSswjgn());
			ts.setWzfw(airProjectForm.getWzfw());
			ts.setWd(airProjectForm.getWd());
			ts.setCreater(CtxUtil.getCurUser());
			ts.setCreateTime(new Date());
			ts.setIsActive("Y");
			ts.setPid(airProjectForm.getPid());
			ts.setUpdateby(CtxUtil.getCurUser());
			ts.setUpdateTime(new Date());
			this.dao.save(ts);
		}else{
			TDataDqhjbhmbfb ts=new TDataDqhjbhmbfb();
			ts.setAreaId(CtxUtil.getAreaId());
			ts.setBhmbmc(airProjectForm.getBhmbmc());
			ts.setJd(airProjectForm.getJd());
			ts.setJl(airProjectForm.getJl());
			ts.setLx(airProjectForm.getLx());
			ts.setSljb(airProjectForm.getSljb());
			ts.setSswjgn(airProjectForm.getSswjgn());
			ts.setWzfw(airProjectForm.getWzfw());
			ts.setWd(airProjectForm.getWd());
			ts.setCreater(CtxUtil.getCurUser());
			ts.setCreateTime(new Date());
			ts.setIsActive("Y");
			ts.setPid(airProjectForm.getPid());
			ts.setUpdateby(CtxUtil.getCurUser());
			ts.setUpdateTime(new Date());
			this.dao.save(ts);
		}
		
	}

	@Override
	public FyWebResult airProjectList(String pid, String isActive, String page,
			String pageSize) throws AppException {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder(" from TDataDqhjbhmbfb t where t.isActive='Y' and ");
		if (StringUtils.isNotBlank(pid)) {
			sql.append(" t.pid=:pid");
			data.put("pid", pid);
		}
		sql.append(" order by t.updateTime desc ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		
		List<TDataDqhjbhmbfb> whyLists = pos.getRe();
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		Map<String, String> dataObject = null;
		for (TDataDqhjbhmbfb tq : whyLists) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(tq.getId()));
			dataObject.put("pid", String.valueOf(tq.getPid()));
			dataObject.put("bhmbmc", String.valueOf(tq.getBhmbmc()));
			dataObject.put("lx", tq.getLx()==null?"":String.valueOf(tq.getLx()));
			dataObject.put("sljb", String.valueOf(tq.getSljb()));
			dataObject.put("jd", String.valueOf(tq.getJd()));
			dataObject.put("wd", String.valueOf(tq.getWd()));
			dataObject.put("jl", String.valueOf(tq.getJl()));
			dataObject.put("wzfw", String.valueOf(tq.getWzfw()));
			dataObject.put("sswjgn", String.valueOf(tq.getSswjgn()));
			dataObject.put("isActive", "Y".equals(String.valueOf(tq.getIsActive()))?"有效":"无效");
			//dataObject.put("operate", OperateUtil.getOperate(String.valueOf(tq.getId())));
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate","<a id='"+tq.getId()+"' class='b-link' onclick='infoAirProject(this)'>查看</a> <a id='"+tq.getId()+"' class='b-link' onclick='modifyAirProject(this)'>修改</a> <a id='"+tq.getId()+"' class='b-link' onclick='delAirProject(this)'>删除</a> ");
			
			
			rows.add(dataObject);
		}
		
		fy.setRows(rows);
		
		return fy;
	}

	@Override
	public AirProjectForm editAirProjectForm(AirProjectForm airProjectForm)
			throws AppException {
		TDataDqhjbhmbfb ts=(TDataDqhjbhmbfb) this.get(TDataDqhjbhmbfb.class, airProjectForm.getId());
		airProjectForm.setAreaId(ts.getAreaId());
		airProjectForm.setBhmbmc(ts.getBhmbmc());
		airProjectForm.setCreater(ts.getCreater());
		airProjectForm.setCreateTime(ts.getCreateTime());
		airProjectForm.setIsActive(ts.getIsActive());
		airProjectForm.setJd(ts.getJd());
		airProjectForm.setJl(ts.getJl());
		airProjectForm.setLx(ts.getLx());
		airProjectForm.setPid(ts.getPid());
		airProjectForm.setSljb(ts.getSljb());
		airProjectForm.setSswjgn(ts.getSswjgn());
		airProjectForm.setWzfw(ts.getWzfw());
		airProjectForm.setUpdateby(ts.getUpdateby());
        airProjectForm.setUpdateTime(ts.getUpdateTime());
        airProjectForm.setVersion(ts.getVersion());
        airProjectForm.setWd(ts.getWd());
		return airProjectForm;
	}

	@Override
	public AirProjectForm infoAirProjectForm(AirProjectForm airProjectForm)
			throws AppException {
		TDataDqhjbhmbfb ts=(TDataDqhjbhmbfb) this.get(TDataDqhjbhmbfb.class, airProjectForm.getId());
		airProjectForm.setAreaId(ts.getAreaId());
		airProjectForm.setBhmbmc(ts.getBhmbmc());
		airProjectForm.setCreater(ts.getCreater());
		airProjectForm.setCreateTime(ts.getCreateTime());
		airProjectForm.setIsActive(ts.getIsActive());
		airProjectForm.setJd(ts.getJd());
		airProjectForm.setJl(ts.getJl());
		airProjectForm.setLx(ts.getLx());
		airProjectForm.setPid(ts.getPid());
		airProjectForm.setSljb(ts.getSljb());
		airProjectForm.setSswjgn(ts.getSswjgn());
		airProjectForm.setWzfw(ts.getWzfw());
		airProjectForm.setUpdateby(ts.getUpdateby());
        airProjectForm.setUpdateTime(ts.getUpdateTime());
        airProjectForm.setVersion(ts.getVersion());
        airProjectForm.setWd(ts.getWd());
		return airProjectForm;
	}

	@Override
	public void removeAirProject(String id) throws AppException {
		TDataDqhjbhmbfb ts=(TDataDqhjbhmbfb) this.get(TDataDqhjbhmbfb.class,id);
		ts.setIsActive("N");
		ts.setUpdateby(CtxUtil.getCurUser());
		ts.setUpdateTime(new Date());
	}

	@Override
	public void saveAirForm(AirForm airForm) throws AppException {
		if(StringUtils.isNotBlank(airForm.getId())){
			TDataDqhjjbzk ts=(TDataDqhjjbzk) this.get(TDataDqhjjbzk.class, airForm.getId());
			ts.setAreaId(CtxUtil.getAreaId());
			ts.setType(airForm.getType());
			ts.setCreater(CtxUtil.getCurUser());
			ts.setCreateTime(new Date());
			ts.setIsActive("Y");
			ts.setPid(airForm.getPid());
			ts.setUpdateby(CtxUtil.getCurUser());
			ts.setUpdateTime(new Date());
			this.dao.save(ts);
		}else{
			TDataDqhjjbzk ts=new TDataDqhjjbzk();
			ts.setAreaId(CtxUtil.getAreaId());
			ts.setType(airForm.getType());
			ts.setCreater(CtxUtil.getCurUser());
			ts.setCreateTime(new Date());
			ts.setIsActive("Y");
			ts.setPid(airForm.getPid());
			ts.setUpdateby(CtxUtil.getCurUser());
			ts.setUpdateTime(new Date());
			this.dao.save(ts);
		}
		
		
	}

	@Override
	public FyWebResult airList(String pid, String isActive, String page,
			String pageSize) throws AppException {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder(" from TDataDqhjjbzk t where t.isActive='Y' and ");
		if (StringUtils.isNotBlank(pid)) {
			sql.append(" t.pid=:pid");
			data.put("pid", pid);
		}
		sql.append(" order by t.updateTime desc ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		
		List<TDataDqhjjbzk> whyLists = pos.getRe();
		String userId = CtxUtil.getCurUser().getId();//当前用户id
		Map<String, String> dataObject = null;
		for (TDataDqhjjbzk tq : whyLists) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(tq.getId()));
			dataObject.put("pid", String.valueOf(tq.getPid()));
			dataObject.put("type", String.valueOf(HjzlEnum.getNote(tq.getType())));
			dataObject.put("isActive", "Y".equals(String.valueOf(tq.getIsActive()))?"有效":"无效");
			//dataObject.put("operate", OperateUtil.getOperate(String.valueOf(tq.getId())));
			//非管理员角色只具有修改、查看本人添加的执法对象的权限
			
				//非管理员角色对非本人添加企业只具有查看权限，管理员可以增删改查所有企业，通过后台权限配置
				dataObject.put("operate"," <a id='"+tq.getId()+"' class='b-link' onclick='modifyAir(this)'>修改</a> <a id='"+tq.getId()+"' class='b-link' onclick='delAir(this)'>删除</a> ");
			
			
			rows.add(dataObject);
		}
		
		fy.setRows(rows);
		
		return fy;
	}

	@Override
	public AirForm editAirForm(AirForm airForm) throws AppException {
		TDataDqhjjbzk ts=(TDataDqhjjbzk) this.get(TDataDqhjjbzk.class, airForm.getId());
		airForm.setAreaId(ts.getAreaId());
		airForm.setType(ts.getType());
		airForm.setCreater(ts.getCreater());
		airForm.setCreateTime(ts.getCreateTime());
		airForm.setIsActive(ts.getIsActive());
		airForm.setPid(ts.getPid());
		airForm.setUpdateby(ts.getUpdateby());
		airForm.setUpdateTime(ts.getUpdateTime());
		airForm.setVersion(ts.getVersion());
		
		return airForm;
	}

	@Override
	public void removeAir(String id) throws AppException {
		TDataDqhjjbzk ts=(TDataDqhjjbzk) this.get(TDataDqhjjbzk.class,id);
		ts.setIsActive("N");
		ts.setUpdateby(CtxUtil.getCurUser());
		ts.setUpdateTime(new Date());
		
	}

	@Override
	public HashMap<String, String> buildWhpListRecord(String pid)throws Exception {
		HashMap<String, String> ret = new HashMap<String, String>();
		// 生成危化品所用的数据
		Map<String, String> paraMap = new HashMap<String, String>();
		// 根据不同执法对象类型生成不同检查单
		String classPath = this.getClass().getResource("").getPath();
		classPath = java.net.URLDecoder.decode(classPath, "utf-8");
		// 模板路径
		String templatePath = null;
		String filePath="";
	    String dirPath="";
	    VelocityContext vc= new VelocityContext();
	    //获取企业化学情况基本信息
	    FyWebResult jbxxFormWeb=qyjbqkManager.queryQyjbqkFormList(pid, "-1", "-1");
	    List<Map<String, String>> qyjbqkFormList= jbxxFormWeb.getRows();
	    QyjbqkForm qyjbqkForm=null;
		if(qyjbqkFormList.size()>=1){
			qyjbqkForm=new QyjbqkForm();
			TDataLawobjf tl=new TDataLawobjf();
			tl.setId(pid);
			qyjbqkForm.setId(qyjbqkFormList.get(0).get("id"));
			qyjbqkForm=qyjbqkManager.queryQyjbqkForm(tl, qyjbqkForm);
			qyjbqkForm.setSfbzya("1".equals(qyjbqkForm.getSfbzya())?"有":"无");
			qyjbqkForm.setSfpjwj("1".equals(qyjbqkForm.getSfpjwj())?"是":"否");
			qyjbqkForm.setSftf("1".equals(qyjbqkForm.getSftf())?"有":"无");
		//	listWater.add(waterForm);
		}else{
			TDataLawobjf tl=new TDataLawobjf();
			tl.setId(pid);
			qyjbqkForm=qyjbqkManager.queryQyjbqkForm(tl, qyjbqkForm);
		}
		vc.put("qyjbqkForm", qyjbqkForm);
		//获取企业化学物质情况数据
		FyWebResult fyQyhxwzZycp=dangerManager.whpContentMainList(pid, "Y", "-1", "-1");
	    List<Map<String, String>>	 rows=fyQyhxwzZycp.getRows();
	    
	    int s=0;
	    List<QyhxwzqkzycpForm> listZycp=new ArrayList<QyhxwzqkzycpForm>();
		for(int i=0;i<rows.size();i++){
			QyhxwzqkzycpForm zycp=new QyhxwzqkzycpForm();
			zycp.setId(rows.get(i).get("id"));
			s=i+1;
			zycp.setOrder(String.valueOf(s));
			zycp=dangerManager.infoQyhxwzqkzycpForm(zycp);
        listZycp.add(zycp);
		}
		//log.info("测试数据listZycp:==="+listZycp.get(0).getSpm());
		vc.put("listZycp", listZycp);
		//副产品
		FyWebResult fcp=dangerManager.whpContentFcpList(pid, "Y", "-1", "-1");
		List<Map<String, String>>	 fcpRows=fcp.getRows();
	    List<QyhxwzqkfcpForm> listFcp=new ArrayList<QyhxwzqkfcpForm>();
		for(int i=0;i<fcpRows.size();i++){
			QyhxwzqkfcpForm fcpForm=new QyhxwzqkfcpForm();
			fcpForm.setId(fcpRows.get(i).get("id"));
			s=0;
			s=i+1;
			fcpForm.setOrder(String.valueOf(s));
			fcpForm=dangerManager.infoQyhxwzqkfcpForm(fcpForm);
        listFcp.add(fcpForm);
		}
		
		//log.info("测试数据listZycp:==="+listZycp.get(0).getSpm());
		vc.put("listFcp", listFcp);
		//原料
		FyWebResult yl=dangerManager.whpContentYlList(pid, "Y", "-1", "-1");
		List<Map<String, String>>	 ylRows=yl.getRows();
	    List<QyhxwzqkylForm> listYl=new ArrayList<QyhxwzqkylForm>();
		for(int i=0;i<ylRows.size();i++){
			QyhxwzqkylForm ylForm=new QyhxwzqkylForm();
			ylForm.setId(ylRows.get(i).get("id"));
			s=0;
			s=i+1;
			ylForm.setOrder(String.valueOf(s));
			ylForm=dangerManager.infoQyhxwzqkylForm(ylForm);
        listYl.add(ylForm);
		}
		
		vc.put("listYl", listYl);
		//企业环境风险防范措施
		FyWebResult fxcs=hjfxffcsManager.hjfxffcsList(pid, "Y", "-1", "-1");
		List<Map<String, String>>	 fxcsRows=fxcs.getRows();
		List<HjfxffcsForm> listFxcs=new ArrayList<HjfxffcsForm>();
		for(int i=0;i<fxcsRows.size();i++){
			HjfxffcsForm fxcsForm=new HjfxffcsForm();
			fxcsForm.setId(fxcsRows.get(i).get("id"));
			s=0;
			s=i+1;
			fxcsForm.setOrder(String.valueOf(s));
			fxcsForm=hjfxffcsManager.infoHjfxffcsForm(fxcsForm);
			listFxcs.add(fxcsForm);
		}
		
		vc.put("listFxcs", listFxcs);
		//企业环境应急处置及救援资源
		FyWebResult yjcz=qyhjyjczjjyzyManager.qyhjyjczjjyzyListByTypeAndTime(pid, "Y", "-1", "-1");
		List<Map<String, String>>	 yjczRows=yjcz.getRows();
		List<QyhjyjczjjyzyForm> listYjcz=new ArrayList<QyhjyjczjjyzyForm>();
		for(int i=0;i<yjczRows.size();i++){
			QyhjyjczjjyzyForm yjczForm=new QyhjyjczjjyzyForm();
			yjczForm.setId(yjczRows.get(i).get("id"));
			s=0;
			s=i+1;
			yjczForm.setOrder(String.valueOf(s));
			yjczForm=qyhjyjczjjyzyManager.infoQyhjyjczjjyzyForm(yjczForm);
			listYjcz.add(yjczForm);
		}
		
		vc.put("listYjcz", listYjcz);
		//水环境基本状况
		FyWebResult water=waterManager.waterList(pid, "Y", "-1", "-1");
		List<Map<String, String>>	 waterRows=water.getRows();
		//List<WaterForm> listWater=new ArrayList<WaterForm>();
		WaterForm waterForm=null;
		if(waterRows.size()>=1){
			waterForm=new WaterForm();
			waterForm.setId(waterRows.get(0).get("id"));
			waterForm=waterManager.infoWaterForm(waterForm);
		//	listWater.add(waterForm);
		}else{
			waterForm=new WaterForm();
		}
		vc.put("waterForm", waterForm);
		//水环境保护目标分布
		FyWebResult waterProject=waterManager.waterProjectList(pid, "Y", "-1", "-1");
		List<Map<String, String>>	 waterProjectRows=waterProject.getRows();
		List<WaterProjectForm> listWaterProject=new ArrayList<WaterProjectForm>();
		for(int i=0;i<waterProjectRows.size();i++){
			WaterProjectForm waterProjectForm=new WaterProjectForm();
			waterProjectForm.setId(waterProjectRows.get(i).get("id"));
			s=0;
			s=i+1;
			waterProjectForm.setOrder(String.valueOf(s));
			waterProjectForm=waterManager.infoWaterProjectForm(waterProjectForm);
			listWaterProject.add(waterProjectForm);
		}
		
		vc.put("listWaterProject", listWaterProject);
		//大气环境基本状况
		
		FyWebResult air=this.airList(pid, "Y", "-1", "-1");
		List<Map<String, String>>	 airRows=air.getRows();
		//List<WaterForm> listWater=new ArrayList<WaterForm>();
		AirForm airForm=null;
		if(airRows.size()>=1){
			airForm=new AirForm();
			airForm.setId(airRows.get(0).get("id"));
			airForm.setType(airRows.get(0).get("type"));
			//log.info("ceshishuju:===="+airRows.get(0).get("type"));
					//	listWater.add(waterForm);
		}else{
			airForm=new AirForm();
		}
		//log.info("airFormType:===="+airForm.getType());
		vc.put("airForm", airForm);
		//大气环境保护目标分布		
		FyWebResult airProject=this.airProjectList(pid, "Y", "-1", "-1");
		List<Map<String, String>>	 airProjectRows=airProject.getRows();
		List<AirProjectForm> listAirProject=new ArrayList<AirProjectForm>();
		for(int i=0;i<airProjectRows.size();i++){
			AirProjectForm airProjectForm=new AirProjectForm();
			airProjectForm.setId(airProjectRows.get(i).get("id"));
			s=0;
			s=i+1;
			airProjectForm.setOrder(String.valueOf(s));
			airProjectForm=this.infoAirProjectForm(airProjectForm);
			listAirProject.add(airProjectForm);
		}
		
		vc.put("listAirProject", listAirProject);	
		templatePath = classPath.substring(0, classPath.lastIndexOf("/WEB-INF/")) + "//excel//whp//";
		// 生成的路径
		dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath());
		filePath =VelocityUtil.createDoc(vc, templatePath, "whpMb.vm", dirPath);

		File file = new File(filePath);
		// 先删除旧word单
		List<TDataFile> listFile = this.find("from TDataFile f where f.pid = ? and f.type = ?", pid,FileTypeEnums.GYWRYWHPJCB.getCode());
		if(listFile.size()>0){
			for(TDataFile filePo : listFile){
				// 删除文件
				new File(dirPath + "//" + filePo.getOsname()).delete();
				this.dao.remove(TDataFile.class, filePo.getId());
			}
		}
	
		// 保存数据库的名称
		String oName = null;
		TDataLawobjf lawobjf=(TDataLawobjf) this.get(TDataLawobjf.class, pid);
		String lawobjfName=lawobjf.getDwmc();
		//log.info("测试数据"+testname);
		oName =lawobjfName+"环境风险及化学品.doc";
		//31数据字典和enums定义的类型
		TDataFile filePo = commonManager.saveFile(new TDataFile(), file, oName, pid, FileTypeEnums.GYWRYWHPJCB.getCode(), UploadFileType.WORK.getPath());
		ret.put("id", filePo.getId());
		ret.put("name", filePo.getName());
		return ret;
	}

}
