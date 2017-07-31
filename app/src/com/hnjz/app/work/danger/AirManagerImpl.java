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
		String userId = CtxUtil.getCurUser().getId();//��ǰ�û�id
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
			dataObject.put("isActive", "Y".equals(String.valueOf(tq.getIsActive()))?"��Ч":"��Ч");
			//dataObject.put("operate", OperateUtil.getOperate(String.valueOf(tq.getId())));
			//�ǹ���Ա��ɫֻ�����޸ġ��鿴�������ӵ�ִ�������Ȩ��
			
				//�ǹ���Ա��ɫ�ԷǱ���������ҵֻ���в鿴Ȩ�ޣ�����Ա������ɾ�Ĳ�������ҵ��ͨ����̨Ȩ������
				dataObject.put("operate","<a id='"+tq.getId()+"' class='b-link' onclick='infoAirProject(this)'>�鿴</a> <a id='"+tq.getId()+"' class='b-link' onclick='modifyAirProject(this)'>�޸�</a> <a id='"+tq.getId()+"' class='b-link' onclick='delAirProject(this)'>ɾ��</a> ");
			
			
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
		String userId = CtxUtil.getCurUser().getId();//��ǰ�û�id
		Map<String, String> dataObject = null;
		for (TDataDqhjjbzk tq : whyLists) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(tq.getId()));
			dataObject.put("pid", String.valueOf(tq.getPid()));
			dataObject.put("type", String.valueOf(HjzlEnum.getNote(tq.getType())));
			dataObject.put("isActive", "Y".equals(String.valueOf(tq.getIsActive()))?"��Ч":"��Ч");
			//dataObject.put("operate", OperateUtil.getOperate(String.valueOf(tq.getId())));
			//�ǹ���Ա��ɫֻ�����޸ġ��鿴�������ӵ�ִ�������Ȩ��
			
				//�ǹ���Ա��ɫ�ԷǱ���������ҵֻ���в鿴Ȩ�ޣ�����Ա������ɾ�Ĳ�������ҵ��ͨ����̨Ȩ������
				dataObject.put("operate"," <a id='"+tq.getId()+"' class='b-link' onclick='modifyAir(this)'>�޸�</a> <a id='"+tq.getId()+"' class='b-link' onclick='delAir(this)'>ɾ��</a> ");
			
			
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
		// ����Σ��Ʒ���õ�����
		Map<String, String> paraMap = new HashMap<String, String>();
		// ���ݲ�ִͬ�������������ɲ�ͬ��鵥
		String classPath = this.getClass().getResource("").getPath();
		classPath = java.net.URLDecoder.decode(classPath, "utf-8");
		// ģ��·��
		String templatePath = null;
		String filePath="";
	    String dirPath="";
	    VelocityContext vc= new VelocityContext();
	    //��ȡ��ҵ��ѧ���������Ϣ
	    FyWebResult jbxxFormWeb=qyjbqkManager.queryQyjbqkFormList(pid, "-1", "-1");
	    List<Map<String, String>> qyjbqkFormList= jbxxFormWeb.getRows();
	    QyjbqkForm qyjbqkForm=null;
		if(qyjbqkFormList.size()>=1){
			qyjbqkForm=new QyjbqkForm();
			TDataLawobjf tl=new TDataLawobjf();
			tl.setId(pid);
			qyjbqkForm.setId(qyjbqkFormList.get(0).get("id"));
			qyjbqkForm=qyjbqkManager.queryQyjbqkForm(tl, qyjbqkForm);
			qyjbqkForm.setSfbzya("1".equals(qyjbqkForm.getSfbzya())?"��":"��");
			qyjbqkForm.setSfpjwj("1".equals(qyjbqkForm.getSfpjwj())?"��":"��");
			qyjbqkForm.setSftf("1".equals(qyjbqkForm.getSftf())?"��":"��");
		//	listWater.add(waterForm);
		}else{
			TDataLawobjf tl=new TDataLawobjf();
			tl.setId(pid);
			qyjbqkForm=qyjbqkManager.queryQyjbqkForm(tl, qyjbqkForm);
		}
		vc.put("qyjbqkForm", qyjbqkForm);
		//��ȡ��ҵ��ѧ�����������
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
		//log.info("��������listZycp:==="+listZycp.get(0).getSpm());
		vc.put("listZycp", listZycp);
		//����Ʒ
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
		
		//log.info("��������listZycp:==="+listZycp.get(0).getSpm());
		vc.put("listFcp", listFcp);
		//ԭ��
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
		//��ҵ�������շ�����ʩ
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
		//��ҵ����Ӧ�����ü���Ԯ��Դ
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
		//ˮ��������״��
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
		//ˮ��������Ŀ��ֲ�
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
		//������������״��
		
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
		//������������Ŀ��ֲ�		
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
		// ���ɵ�·��
		dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath());
		filePath =VelocityUtil.createDoc(vc, templatePath, "whpMb.vm", dirPath);

		File file = new File(filePath);
		// ��ɾ����word��
		List<TDataFile> listFile = this.find("from TDataFile f where f.pid = ? and f.type = ?", pid,FileTypeEnums.GYWRYWHPJCB.getCode());
		if(listFile.size()>0){
			for(TDataFile filePo : listFile){
				// ɾ���ļ�
				new File(dirPath + "//" + filePo.getOsname()).delete();
				this.dao.remove(TDataFile.class, filePo.getId());
			}
		}
	
		// �������ݿ������
		String oName = null;
		TDataLawobjf lawobjf=(TDataLawobjf) this.get(TDataLawobjf.class, pid);
		String lawobjfName=lawobjf.getDwmc();
		//log.info("��������"+testname);
		oName =lawobjfName+"�������ռ���ѧƷ.doc";
		//31�����ֵ��enums���������
		TDataFile filePo = commonManager.saveFile(new TDataFile(), file, oName, pid, FileTypeEnums.GYWRYWHPJCB.getCode(), UploadFileType.WORK.getPath());
		ret.put("id", filePo.getId());
		ret.put("name", filePo.getName());
		return ret;
	}

}