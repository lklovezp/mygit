package com.hnjz.app.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Thread.State;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.IndexManager;
import com.hnjz.app.data.enums.LawobjOutColunmEnum;
import com.hnjz.app.data.enums.PublicColumnEnum;
import com.hnjz.app.data.enums.TaskTypeCode;
import com.hnjz.app.data.po.TDataChecklisttemplate;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.po.TDataIllegaltype;
import com.hnjz.app.data.po.TDataIndustry;
import com.hnjz.app.data.po.TDataLawdoc;
import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjdic;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.data.po.TDataRegion;
import com.hnjz.app.data.po.TDataTasktype;
import com.hnjz.app.data.po.TDataWg;
import com.hnjz.app.data.wg.WgManager;
import com.hnjz.app.data.xxgl.lawobj.LawobjManager;
import com.hnjz.app.data.zfdx.lawobjtype.ZfdxManager;
import com.hnjz.app.work.beans.ResultBean;
import com.hnjz.app.work.dao.WorkDao;
import com.hnjz.app.work.enums.WorkState;
import com.hnjz.app.work.enums.ZfdxLx;
import com.hnjz.app.work.po.TBizTaskandlawobj;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.FileUtil;
import com.hnjz.common.util.FileZipUtil;
import com.hnjz.common.util.PoiUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.common.util.excel.ExcelUtil;
import com.hnjz.common.util.jacob.Excel2Html;
import com.hnjz.common.util.jacob.Word2Html;
import com.hnjz.sys.area.AreaManager;
import com.hnjz.sys.dic.DicTypeEnum;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysDic;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.tasktype.TaskTypeManager;

/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-9
 * ����������
		��������Managerʵ�ֲ�
 *
 */
@Service("commonManager")
public class CommonManagerImpl extends ManagerImpl implements CommonManager {
	
	@Autowired
	private ServletContext sc;
	
	@Autowired
    private WorkDao                 workDao;
	
	@Autowired
    private CommWorkManager    commWorkManager;
	
	@Autowired
	private IndexManager     indexManager;
	
	@Autowired
	private OrgManager orgManager;
	
	@Autowired
	private AreaManager areaManager;
	
	@Autowired
    private TaskTypeManager    taskTypeManager;
	
	@Autowired
	private LawobjManager lawobjManager;
	
	@Autowired
	private WgManager  wgManager;
	
	@Autowired
	private ZfdxManager zfdxManager;
	
	@Override
	public List<Combobox> queryKzlxList() {
		List<Combobox> listResult = new ArrayList<Combobox>();
		List<TSysDic> list = this.find(" from TSysDic t where t.type = ? order by t.code, t.orderby", DicTypeEnum.KZLX.getCode());
		for(TSysDic dic : list){
			Combobox combobox = new Combobox(dic.getCode(),dic.getName());
			listResult.add(combobox);
		}
		return listResult;
	}
	
	@Override
	public String getDicNameByTypeAndCode(String type,String code) {
		String name="";
		List<TSysDic> list = this.find(" from TSysDic t where t.type = ? and t.code = ? order by t.orderby", type,code);
		if(list!=null&&list.size()>0){
			name=list.get(0).getName();
		}
		return name;
	}
	
	@Override
	public List<Combobox> queryTSysDicList(String type) {
		List<Combobox> listResult = new ArrayList<Combobox>();
		List<TSysDic> list = this.find(" from TSysDic t where t.type = ? order by t.orderby ", type);
		for(TSysDic dic : list){
			Combobox combobox;
			if("29".equals(type)){
				combobox = new Combobox(dic.getId(),dic.getName(), dic.getDescribe());
			}else{
				combobox = new Combobox(dic.getCode(),dic.getName(), dic.getDescribe());
			}
			if(DicTypeEnum.JJCD.getCode().equals(type)){//�����̶ȼ�����
				if(dic.getCode().equals("1")){
					combobox.setExt("20");
				}else if(dic.getCode().equals("2")){
					combobox.setExt("7");
				}else if(dic.getCode().equals("3")){
					combobox.setExt("3");
				}
			}
			listResult.add(combobox);
		}
		return listResult;
	}
	
	@Override
	public List<Combobox> queryZtList() {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult.add(new Combobox("Y","��Ч"));
		listResult.add(new Combobox("N","��Ч"));
		return listResult;
	}
	
	@Override
	public List<Combobox> queryQyztList() {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult.add(new Combobox("0","��Ӫ��"));
		listResult.add(new Combobox("1","�ѹر�"));
		listResult.add(new Combobox("2","��ͣ��"));
		return listResult;
	}
	
	@Override
	public List<Combobox> querySfList() {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult.add(new Combobox("Y","��"));
		listResult.add(new Combobox("N","��"));
		return listResult;
	}

	@Override
	public List<Combobox> queryIndustryList(String lawobjType) {
		List<Combobox> listResult = new ArrayList<Combobox>();
		List<TDataIndustry> list = null;
		if (StringUtil.isNotBlank(lawobjType)){
			list = this.find(" from TDataIndustry t where t.isActive = 'Y' and t.lawobjtype= ? order by t.lawobjtype, t.name", lawobjType);
		} else {
			list = this.find(" from TDataIndustry t order by t.lawobjtype, t.name");
		}
		for(TDataIndustry tDataIndustry : list){
			TDataLawobjtype type=(TDataLawobjtype) this.get(TDataLawobjtype.class, tDataIndustry.getLawobjtype());
			Combobox combobox = new Combobox(tDataIndustry.getId(), tDataIndustry.getName() + "(" + type.getName() + ")");
			listResult.add(combobox);
		}
		return listResult;
	}

	@Override
	public List<Combobox> queryFjlxList(String enumName) {
		if("GYWRY".equals(enumName)){
			List<Combobox> listResult = new ArrayList<Combobox>();
			listResult.add(new Combobox("3100","��ҵ��ȾԴ.��֯��������ɨ���"));
			listResult.add(new Combobox("3101","��ҵ��ȾԴ.�ֳ�����ʾ��ͼ"));
			listResult.add(new Combobox("3102","��ҵ��ȾԴ.Ӫҵִ��ɨ���"));
			listResult.add(new Combobox("3103","��ҵ��ȾԴ.��������֤ɨ���"));
			listResult.add(new Combobox("3104","��ҵ��ȾԴ.����"));
			return listResult;	
			
		}else{
			return FileTypeEnums.getTypeListByEnumName(enumName);
		}
	}
	
	
	
	@Override
	public List<ComboboxTree> queryRegionTree(){
		List<TDataRegion> list = null;
		//����Ա������
		if (!CtxUtil.getCurUser().getId().equals("a0000000000000000000000000000000")){
			TSysArea area = (TSysArea) this.get(TSysArea.class,CtxUtil.getAreaId());
			if(area.getType().equals("1")){
				list = this.find(" from TDataRegion r where r.pid = ? order by r.type,r.orderby ",area.getCode().substring(0, 4)+"00");
				return regionTreeHelp(list, area.getCode().substring(0, 4)+"00");
			}else if(area.getType().equals("2")){
				list = this.find(" from TDataRegion r where r.id = ? order by r.type,r.orderby ",area.getCode());
				return regionTreeHelp(list, area.getCode());
			}else{
				list = this.find(" from TDataRegion r where r.id != ? order by r.type,r.orderby ","660000");
				return regionTreeHelp(list, "660000");
			}
		}else{
			list = this.find(" from TDataRegion r where r.id != ? order by r.type,r.orderby ","660000");
			return regionTreeHelp(list, "660000");
		}
	}
	

	
	@Override
	public List<ComboboxTree> queryOrgTree(){
		//�������߰�ı�ʶ�жϣ����Բ�ѯ��Ӧ��sql��䣩
		String biaoshi = indexManager.sysVer;
		List<TSysOrg> list = new ArrayList<TSysOrg>();
		TSysOrg pOrg = null;
		if("0".equals(biaoshi)){
			TSysOrg pOrg1 = null;
			pOrg1 = orgManager.getOrgByUserid(CtxUtil.getUserId());//��ǰ�û����ڲ���
			if("0".equals(pOrg1.getType())){
				pOrg = orgManager.getOrgByUserid(CtxUtil.getUserId());//��ǰ�û����ڲ���
			}else{
				pOrg = orgManager.getOrgByUserid(CtxUtil.getUserId()).getOrg();//��ǰ�û����ڲ���
			}
		}else{
			pOrg = orgManager.getOrgByUserid(CtxUtil.getUserId()).getOrg();//��ǰ�û����ڲ���
		}
		String areaId = CtxUtil.getAreaId();
		// ������ǳ�������Ա ����������
		if (!CtxUtil.getCurUser().getId().equals("a0000000000000000000000000000000") && StringUtils.isNotBlank(areaId)) {
			/**��ѯ�����������¼����� */
			List<TSysArea> areas = areaManager.getChAreas(areaId);
			List<TSysOrg> temp = new ArrayList<TSysOrg>();
			for(int i = 0; i < areas.size(); i++){
				temp = this.dao.find("from TSysOrg where isActive='Y' and area.id =? order by orderby",areas.get(i).getId());
				list.addAll(temp);
			}
		}
		if(pOrg != null && pOrg.getOrg()!=null){
			return orgTreeHelp(list,pOrg.getOrg().getId());
		}
		return orgTreeHelp(list,null);
	}
	
	
	
	/**
	 * 
	 * �������ܣ�ͨ��id��õ�ǰ�ڵ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	private List<ComboboxTree> regionTreeHelp(List<TDataRegion> list,String pid){
		List<ComboboxTree> listTree = new ArrayList<ComboboxTree>();
		for(TDataRegion tDataRegion : list){
			if((null == pid && null==tDataRegion.getPid()) || (null != pid && null != tDataRegion.getPid() && pid.equals(tDataRegion.getPid())) ){
				ComboboxTree comboboxTree = new ComboboxTree(tDataRegion.getId(),tDataRegion.getName(),tDataRegion.getPid(),tDataRegion.getType());
				comboboxTree.setChildren(this.regionTreeHelp(list, tDataRegion.getId()));
				listTree.add(comboboxTree);
			}
		}
		return listTree;
	}
	
	/**
	 * 
	 * �������ܣ�ͨ��id��õ�ǰ�ڵ������
	
	 * ���������
	
	 * ����ֵ��
	 */
	private List<ComboboxTree> orgTreeHelp(List<TSysOrg> list,String pid){
		List<ComboboxTree> listTree = new ArrayList<ComboboxTree>();
		for(TSysOrg tSysOrg : list){
			 if(null != pid && null != tSysOrg.getOrg() && pid.equals(tSysOrg.getOrg().getId())){
				ComboboxTree comboboxTree = new ComboboxTree(tSysOrg.getId(),tSysOrg.getName(),tSysOrg.getOrg().getId(),tSysOrg.getType());
				comboboxTree.setChildren(this.orgTreeHelp(list, tSysOrg.getId()));
				listTree.add(comboboxTree);
			 }else if(null == pid && null==tSysOrg.getOrg()){
				ComboboxTree comboboxTree = new ComboboxTree(tSysOrg.getId(),tSysOrg.getName(),null,tSysOrg.getType());
				comboboxTree.setChildren(this.orgTreeHelp(list, tSysOrg.getId()));
				listTree.add(comboboxTree);
			 }
		}
		return listTree;
	}
	
	

	@Override
	public List<ComboboxTree> queryIllegalTypeList(String ids) {
		String hql = "";
		ids = ids == null ? "" : ids;
		String notin = "";
		for (int i = 0; i < ids.split(",").length; i++) {
			if (i > 0){
				notin += ",";
			}
			notin += "'" + ids.split(",")[i] + "'";
		}
		if (StringUtil.isNotBlank(ids)){
			hql = " from TDataIllegaltype where isActive = 'Y' and id not in("+notin+") order by orderby";
		} else {
			hql = " from TDataIllegaltype where isActive = 'Y' order by orderby";
		}
		List<TDataIllegaltype> list = this.dao.find(hql);
		return this.illegalTypeTreeHelp(list, null, 1);
	}
	
	@Override
	public List<ComboboxTree> queryIllegalTypeByTasktypeList(String ids,String tasktype){
		String hql = "";
		ids = ids == null ? "" : ids;
		String notin = "";
		for (int i = 0; i < ids.split(",").length; i++) {
			if (i > 0){
				notin += ",";
			}
			notin += "'" + ids.split(",")[i] + "'";
		}
		
		if (StringUtil.isNotBlank(ids)){
			hql = "select i from TDataIllegaltype i,TDataTasktypeandillegaltype t where t.illegaltypeid = i.id and i.isActive = 'Y' and i.id not in("+notin+") ";
		} else {
			hql = "select i from TDataIllegaltype i,TDataTasktypeandillegaltype t where t.illegaltypeid = i.id and i.isActive = 'Y' ";
		}
		//����tasktype��code��ȡ��������id
		TDataTasktype t= taskTypeManager.getTaskTypeByCode(tasktype);
		if(t!=null){
			hql += " and t.tasktypeid = '"+t.getId()+"'";	
		}
		hql += " order by i.orderby";
		List<TDataIllegaltype> list = this.dao.find(hql);
		return this.illegalTypeTreeHelp(list, null, 1);
		
	}
	
	private List<ComboboxTree> illegalTypeTreeHelp(List<TDataIllegaltype> list, String pid, int level){
		//�������߰�ı�ʶ�жϣ����Բ�ѯ��Ӧ��sql��䣩
		String biaoshi = indexManager.sysVer;
		List<ComboboxTree> listTree = new ArrayList<ComboboxTree>();
		for(TDataIllegaltype ele : list){
			if("0".equals(biaoshi)){
				if((null == pid && StringUtils.isBlank(ele.getPid())) || (null != pid && StringUtils.isNotBlank(ele.getPid()) && pid.equals(ele.getPid())) ){
					ComboboxTree comboboxTree = new ComboboxTree(ele.getId(),ele.getName(), ele.getPid(), String.valueOf(level));
					level++;
					comboboxTree.setChildren(this.illegalTypeTreeHelp(list, ele.getId(), level));
					level--;
					listTree.add(comboboxTree);
				}
			}else{
				if((null == pid && null==ele.getPid()) || (null != pid && null != ele.getPid() && pid.equals(ele.getPid())) ){
					ComboboxTree comboboxTree = new ComboboxTree(ele.getId(),ele.getName(), ele.getPid(), String.valueOf(level));
					level++;
					comboboxTree.setChildren(this.illegalTypeTreeHelp(list, ele.getId(), level));
					level--;
					listTree.add(comboboxTree);
				}
			}
		}
		return listTree;
	}

	@Override
	public List<Combobox> queryTaskTypeCombo() {
		List<Combobox> listResult = new ArrayList<Combobox>();
		List<TDataTasktype> list = this.find("from TDataTasktype order by orderby");
		for(TDataTasktype po : list){
			Combobox combobox = new Combobox(po.getCode(), po.getName());
			listResult.add(combobox);
		}
		return listResult;
	}
	
	
	

	@Override
	public List<ComboboxTree> queryTaskTypePubTree(String markId) {
		List<TDataTasktype> list = this.find(" from TDataTasktype where isActive = 'Y' order by orderby");
		if("zfjc".equals(markId)){
			for(int i=0;i<list.size();i++){
				if("13".equals(list.get(i).getCode()) || "24".equals(list.get(i).getCode())){
					list.remove(i);
				}
			}
			return this.taskTypeTreeHelp(list, null, markId);
		}else{
			return this.taskTypeTreeHelp(list, null, markId);
		}
	}
	
	private List<ComboboxTree> taskTypeTreeHelp(List<TDataTasktype> list,String pid ,String markId){
		List<ComboboxTree> listTree = new ArrayList<ComboboxTree>();
		if("1".equals(markId)){
			for(TDataTasktype ele : list){
				if((StringUtils.isBlank(pid) && StringUtils.isBlank(ele.getPid())) || (StringUtils.isNotBlank(pid) && StringUtils.isNotBlank(ele.getPid()) && pid.equals(ele.getPid())) ){
					if("10".equals(ele.getCode()) || "12".equals(ele.getCode()) || "15".equals(ele.getCode())){
						ComboboxTree comboboxTree = new ComboboxTree(ele.getCode(),ele.getName());
						comboboxTree.setChildren(this.taskTypeTreeHelp(list, ele.getId(), markId));
						listTree.add(comboboxTree);
					}
				}
			}
		}else{
			for(TDataTasktype ele : list){
				if((StringUtils.isBlank(pid) && StringUtils.isBlank(ele.getPid())) || (StringUtils.isNotBlank(pid) && StringUtils.isNotBlank(ele.getPid()) && pid.equals(ele.getPid())) ){
					ComboboxTree comboboxTree = new ComboboxTree(ele.getCode(),ele.getName());
					comboboxTree.setChildren(this.taskTypeTreeHelp(list, ele.getId(), ""));
					listTree.add(comboboxTree);
				}
			}
		}
		return listTree;
	}
	/**
	 * ��ѯ��ǰ�û������µ�����
	 * 
	 * @return
	 */
	public List<Combobox> queryAreaComboWithCur(String areaId) {
		List<Combobox> re = new ArrayList<Combobox>();
		String hsql = "from TSysArea where area.id = ? and isActive='Y' order by orderby";
		List<TSysArea> as = this.dao.find(hsql, areaId);
		TSysArea area = (TSysArea)dao.get(TSysArea.class, areaId);
		re.add(new Combobox(area.getId(), area.getName()));
		for (TSysArea ele : as) {
			re.add(new Combobox(ele.getId(), ele.getName()));
		}
		return re;
	}
	/**
	 * ��ѯ��ǰ�õ�����,�����admin��ѯ���е�����
	 */
	public List<Combobox> queryAreaCur(String areaId) {
		List<Combobox> re = new ArrayList<Combobox>();
		if(StringUtils.isNotBlank(areaId)){
			TSysArea area = (TSysArea)dao.get(TSysArea.class, areaId);
			re.add(new Combobox(area.getId(), area.getName()));
		}else{
			String hsql = "from TSysArea where isActive='Y' order by orderby";
			List<TSysArea> as = this.dao.find(hsql);
			for (TSysArea ele : as) {
				re.add(new Combobox(ele.getId(), ele.getName()));
			}
		}
		return re;
	}
	/**
	 * ��ѯ��ǰ�û��µ�����
	 * 
	 * @return
	 */
	public List<Combobox> queryAreaCombo(String areaId) {
		List<Combobox> re = new ArrayList<Combobox>();
		String hsql = "from TSysArea where area.id = ? and isActive='Y' order by orderby";
		List<TSysArea> as = this.dao.find(hsql, areaId);
		for (TSysArea ele : as) {
			re.add(new Combobox(ele.getId(), ele.getName()));
		}
		return re;
	}
	
	
	@Override
	public List<Combobox> queryWgTree() {
		// TODO Auto-generated method stub
		List<Combobox> re = new ArrayList<Combobox>();
		String areaId = CtxUtil.getAreaId();
		String sql = "from TDataWg where areaid=? and isActive='Y'";
		List<TDataWg> as = this.dao.find(sql, areaId);
		for (TDataWg ele : as) {
			re.add(new Combobox(ele.getId(),ele.getWgmc()));
		}
		return re;
	}
	
	@Override
	public List<Combobox> queryLawobjtypeTree() {
		// TODO Auto-generated method stub
		List<Combobox> re = new ArrayList<Combobox>();
		
		String sql = "from TDataLawobjtype where isactive='Y'";
		List<TDataLawobjtype> as = this.dao.find(sql);
		for (TDataLawobjtype ele : as) {
			re.add(new Combobox(ele.getId(),ele.getName()));
		}
		return re;
	}
	/**
	 * ��ѯָ��������¼�����
	 * 
	 * @return
	 */
	public List<Combobox> queryAreaComboByAreaId(String areaId) {
		List<Combobox> re = new ArrayList<Combobox>();
		String hsql = "from TSysArea where area.id = ? order by orderby";
		List<TSysArea> as = this.dao.find(hsql, areaId);
		for (TSysArea ele : as) {
			re.add(new Combobox(ele.getId(), ele.getName()));
		}
		return re;
	}
	
	@Override
	public TDataFile saveFile(InputStream is, String pid, String fileenumtype, String path, String filename, Long size){
		TDataFile filePo = null;
		try {
			filePo = new TDataFile();
			// �ϴ��ļ����������ݵ����ݿ�
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			FileUpDownUtil.copyFile(is, uuid, path, "");
			TSysUser curUser = CtxUtil.getCurUser();
			Date cur = new Date();
			filePo.setPid(pid);
			filePo.setName(filename);
			filePo.setExtension(FileUtil.getExtensionName(filename));
			filePo.setSize(size);
			filePo.setType(fileenumtype);
			filePo.setOsname(uuid);
			filePo.setRelativepath(path);
			filePo.setCreateby(curUser);
			filePo.setCreated(cur);
			filePo.setIsActive(YnEnum.Y.getCode());
			filePo.setOrderby(0);
			filePo.setUpdateby(curUser);
			filePo.setUpdated(cur);
			this.dao.save(filePo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePo;
	}
	
	@Override
	public TDataFile saveFile(TDataFile t){
		TDataFile filePo = null;
		filePo = new TDataFile();
		Date cur = new Date();
		filePo.setPid(t.getPid());
		filePo.setName(t.getName());
		filePo.setExtension(t.getExtension());
		filePo.setSize(t.getSize());
		filePo.setType(t.getType());
		filePo.setOsname(t.getOsname());
		filePo.setRelativepath(t.getRelativepath());
		filePo.setCreateby(t.getCreateby());
		filePo.setCreated(cur);
		filePo.setIsActive(YnEnum.Y.getCode());
		filePo.setOrderby(0);
		filePo.setUpdateby(t.getUpdateby());
		filePo.setUpdated(cur);
		this.dao.save(filePo);
		return filePo;
	}
	
	@Override
	public TDataFile saveFile(InputStream is, String pid, String fileenumtype, String path, String filename, Long size,TSysUser giveUser){
		TDataFile filePo = null;
		try {
			filePo = new TDataFile();
			// �ϴ��ļ����������ݵ����ݿ�
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			FileUpDownUtil.copyFile(is, uuid, path, "");
			//TSysUser curUser = CtxUtil.getCurUser();
			Date cur = new Date();
			filePo.setPid(pid);
			filePo.setName(filename);
			filePo.setExtension(FileUtil.getExtensionName(filename));
			filePo.setSize(size);
			filePo.setType(fileenumtype);
			filePo.setOsname(uuid);
			filePo.setRelativepath(path);
			filePo.setCreateby(giveUser);
			filePo.setCreated(cur);
			filePo.setIsActive(YnEnum.Y.getCode());
			filePo.setOrderby(0);
			filePo.setUpdateby(giveUser);
			filePo.setUpdated(cur);
			this.dao.save(filePo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePo;
	}
	
	@Override
	public TDataFile saveFile(TDataLawdoc lawdoc,InputStream is, String fileenumtype, String path, String filename, Long size){
		TDataFile filePo = null;
		try {
			filePo = new TDataFile();
			// �ϴ��ļ����������ݵ����ݿ�
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			FileUpDownUtil.copyFile(is, uuid, path, "");
			TSysUser curUser = CtxUtil.getCurUser();
			Date cur = new Date();
			filePo.setPid(lawdoc.getId());
			filePo.setName(filename);
			filePo.setExtension(FileUtil.getExtensionName(filename));
			filePo.setSize(size);
			filePo.setType(fileenumtype);
			filePo.setOsname(uuid);
			filePo.setRelativepath(path);
			filePo.setCreateby(curUser);
			filePo.setCreated(cur);
			filePo.setIsActive(YnEnum.Y.getCode());
			filePo.setOrderby(0);
			filePo.setUpdateby(curUser);
			filePo.setUpdated(cur);
			this.dao.save(filePo);
			lawdoc.setFileid(filePo.getId());
			this.save(lawdoc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePo;
	}
	@Override
	public TDataFile saveLawdoc(InputStream is, String pid, String fileenumtype, String path, String filename, Long size){
		// �ļ�id
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		TDataLawdoc lawdoc = new TDataLawdoc(FileUtil.getFileNameNoEx(filename),pid,FileUtil.getFileNameNoEx(filename),uuid);
		this.save(lawdoc);
		return this.saveFile(lawdoc, is, fileenumtype, path, filename, size);
	}
	
	public TDataFile saveFile(TDataFile filePo, MultipartFile file, String pid, String fileenumtype, UploadFileType path){
		try {
			// �ϴ��ļ����������ݵ����ݿ�
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			
			FileUpDownUtil.copyFile(file.getInputStream(), uuid, path.getPath(), "");
			TSysUser curUser = CtxUtil.getCurUser();
			Date cur = new Date();
			filePo.setPid(pid);
			filePo.setName(file.getOriginalFilename());
			filePo.setExtension(FileUtil.getExtensionName(file.getOriginalFilename()));
			filePo.setSize(file.getSize());
			filePo.setType(fileenumtype);
			filePo.setOsname(uuid);
			filePo.setRelativepath(path.getPath());			
			filePo.setCreateby(curUser);
			filePo.setCreated(cur);
			filePo.setIsActive(YnEnum.Y.getCode());
			filePo.setOrderby(0);
			filePo.setUpdateby(curUser);
			filePo.setUpdated(cur);
			
			filePo = (TDataFile) this.dao.save(filePo);
			return filePo;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public TDataFile saveFile(TDataFile filePo, File file, String oName, String pid, String fileenumtype, String filePath){
		// �������ݵ����ݿ�
		TSysUser curUser = CtxUtil.getCurUser();
		Date cur = new Date();
		filePo.setPid(pid);
		filePo.setName(oName);
		filePo.setExtension(FileUtil.getExtensionName(oName));
		filePo.setSize(file.length());
		filePo.setType(fileenumtype);
		filePo.setOsname(file.getName());
		filePo.setRelativepath(filePath);
		filePo.setCreateby(curUser);
		filePo.setCreated(cur);
		filePo.setIsActive(YnEnum.Y.getCode());
		filePo.setOrderby(0);
		filePo.setUpdateby(curUser);
		filePo.setUpdated(cur);
		
		filePo = (TDataFile) this.dao.save(filePo);
		return filePo;
	}
	
	public List<Combobox> queryRwztCombo() {
		return WorkState.getTypes();
	}

	@Override
	public void downTemplate(String lawObjType, HttpServletResponse res) {
		List<TDataLawobjdic> lawobjDic = this.dao.find("from TDataLawobjdic where lawobjtypeid = ? and colchiname != ? and colchiname != ? order by orderby ", lawObjType,"������","����ִ������id");
		String title = ((TSysDic)this.dao.find("from TSysDic where type = 5 and code = ?", lawObjType).get(0)).getName();

		LinkedHashMap<String, List<LinkedHashMap<String, Object>>> data=this.getExeclData(lawObjType);
		// ����excelģ��
		InputStream is = ExcelUtil.genTemplate(lawobjDic, title, data);
		try {
			res.setHeader("Content-Disposition", "attachment;filename=" + new String((title + "����ģ��.xls").getBytes("GB2312"), "ISO-8859-1"));
			res.setContentType("application/x-msdownload");
			OutputStream os = res.getOutputStream();
			byte[] b = new byte[1024];
			int length;
			while ((length = is.read(b)) > 0) {
				os.write(b, 0, length);
			}
			is.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void importTemplate(String lawObjType, MultipartFile file) throws AppException{
		List<TDataLawobjdic> lawobjDic = this.dao.find("from TDataLawobjdic where lawobjtypeid = ?", lawObjType);
		// ��ȡ�û��ϴ���excel�����е����ݷ�װ��listmap
		ArrayList<Map<String, Object>> data1 = ExcelUtil.readLawObjExcel(file);
		Map<String, Object> map=null;
		List<Map<String, Object>> data =new ArrayList<Map<String,Object>>();
		for(int i=0;i<data1.size();i++){
			map=new HashMap<String, Object>();
			map=data1.get(i);
			map.put("������", CtxUtil.getCurUser().getId());
			data.add(map);
		}
		// ȥ�ĵ�ǰִ��������������ִ�������name������У��name�Ƿ��ظ���
		List<String> names = getColumnValue(lawObjType,lawObjType + PublicColumnEnum.mc.getCode());
		// ��ִ������name�ֵ�����
		TDataLawobjdic mcDic = (TDataLawobjdic) this.dao.find("from TDataLawobjdic where lawobjtypeid = '" + lawObjType + "' and enumname = ?", lawObjType + PublicColumnEnum.mc.getCode()).get(0);
		
		if (data.size() > 0){
			ArrayList<Map<String, Object>> enlist = new ArrayList<Map<String,Object>>();
			Map<String, Object> enmap = null;
			// ѭ������map��map������keyֵ��ִ�������ֵ���Ա��滻��Ӣ������
			for (int i = 0; i < data.size(); i++) {
				enmap = new HashMap<String, Object>();
				// ѭ���ֵ��
				for (int j = 0; j < lawobjDic.size(); j++) {
					// �������map��keyֵ�а����ֵ���е������ֶ���ʱ
					if (data.get(i).containsKey(lawobjDic.get(j).getColchiname())){
						// ������Ϊ�������ݿ������ô����Ϊ��ʱ�׳��쳣
						if(StringUtils.isBlank(String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname()))) && lawobjDic.get(j).getMandatory().equals("Y")){
							throw new AppException("��" + String.valueOf(i + 1) + "��" + lawobjDic.get(j).getColchiname() + "����Ϊ�գ�");
						}
						// name�Ƿ��ַ��ж�
						/*if (lawobjDic.get(j).getEnumname().equals(lawObjType + PublicColumnEnum.mc.getCode())){
							if (String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())).contains("\\") || String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())).contains("/")
									|| String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())).contains(":") || String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())).contains("*")
									|| String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())).contains("?") || String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())).contains("\"")
									|| String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())).contains("<") || String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())).contains(">")
									|| String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())).contains("|")){
								throw new AppException("��" + String.valueOf(i + 1) + "�е�λname�в��ܰ�����\\/:*?\"<>|���Ƿ��ַ���");
							}
						}*/
						if (lawobjDic.get(j).getEnumname().equals(lawObjType + PublicColumnEnum.mc.getCode()) && names.contains(String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())))){
							throw new AppException("��" + String.valueOf(i + 1) + "��������"+lawobjDic.get(j).getColchiname()+"�ظ��������¼����ϴ���");
						}
						// ������map�д�����key��Ӧ��valueȡ���ŵ��µ�map�в����������name��Ӧ��Ӣ��������Ϊkey
						enmap.put(lawobjDic.get(j).getColengname(), data.get(i).get(lawobjDic.get(j).getColchiname()));
					}
				}
				// ��map���ӵ�list��
				enlist.add(enmap);
			}
			StringBuffer sqlBuf = new StringBuffer();
			String uuid = "";
			// ѭ���Ѿ�ת���õ�listmap���ռ�¼����ƴ�ӳɲ���sql ��ѭ���������ݿ�
			for (int i = 0; i < enlist.size(); i++) {
				sqlBuf = new StringBuffer();
				uuid = UUID.randomUUID().toString().replaceAll("-", "");
				// ����̶���id��ִ��������������
				sqlBuf.append("insert into T_DATA_LAWOBJ ( ID_, LAWOBJTYPE_, VERSION_");
				Object[] fields = enlist.get(i).keySet().toArray();
				StringBuffer field = new StringBuffer(" ");
				StringBuffer value = new StringBuffer("'" + uuid + "', '" + lawObjType + "', 0 ");
				for (int j = 0; j < fields.length; j++) {
					field.append(", " + fields[j]);
					value.append(", '" + enlist.get(i).get(fields[j]) + "'");
				}
				String[] oFileds = new String[]{"AREAID_", "UPDATED_", "UPDATEBY_", "CREATED_", "CREATEBY_"};
				String[] oValues = new String[]{"'" + CtxUtil.getAreaId() + "'", "sysdate", "'" + CtxUtil.getCurUser().getId() + "'", "sysdate", "'" + CtxUtil.getCurUser().getId() + "'"};
				for (int j = 0; j < oFileds.length; j++) {
					field.append(", " + oFileds[j]);
					value.append(", " + oValues[j]);
				}
				sqlBuf.append(field);
				sqlBuf.append(") values (");
				sqlBuf.append(value);
				sqlBuf.append(")");
				
				this.dao.exec(sqlBuf.toString());
			}
		}
	}
	
	@Override
	public TDataFile uploadFile(MultipartFile multipartFile,
			HttpServletRequest request) throws AppException {
		TDataFile tDataFile = null;
		try {
			InputStream is = multipartFile.getInputStream();
			String pid = request.getParameter("pid");
			String fileType = request.getParameter("fileType");
			fileType = FileTypeEnums.getTypeByEnumName(fileType);
			String path = UploadFileType.getPathByEnumName(request.getParameter("path"));
			String fileName = multipartFile.getOriginalFilename();
			Long size = multipartFile.getSize();
			if(FileTypeEnums.ZFWJGLZFWJ.getCode().equals(fileType)){//����ִ���ļ�
				tDataFile = this.saveLawdoc(is, pid, fileType, path, fileName, size);
			}else{
				tDataFile = this.saveFile(is, pid, fileType, path, fileName, size);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("�ϴ�ʧ�ܡ�");
		}
		return tDataFile;
	}

	@Override
	public String getExtension(String fileType) {
		TSysDic dic = (TSysDic)this.dao.find("from TSysDic where code = ?", fileType).get(0);
		return dic.getDescribe();
	}
	
	@Override
	public void downloadFile(String id, HttpServletResponse res) {
		TDataFile file = (TDataFile)this.get(TDataFile.class,id);
		try {
			String path = file.getRelativepath() + File.separator + file.getOsname();
			FileUpDownUtil.downloadFile(res, path, file.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void exportFile(String id, String applyId, HttpServletResponse res) {
		try {
			//�������߰�ı�ʶ�жϣ����Բ�ѯ��Ӧ��sql��䣩
			String biaoshi = indexManager.sysVer;
			List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(applyId);// ���������б�
			for(int i = 0; i < rwlxlistMap.size(); i++){
				if(rwlxlistMap.get(i).get("id").equals(TaskTypeCode.WXFW.getCode())){
					if (null != applyId && !"".equals(applyId.trim())) {
						//��ȡ��ҵ�Ļ�����Ϣ
						Map<String, String> zfdxMap = new HashMap<String, String>();
						List<Map<String, String>> zfdxlistMap = commWorkManager.zfdxTableData(applyId);
						if (zfdxlistMap != null && zfdxlistMap.size() == 1) {
							zfdxMap = zfdxlistMap.get(0);
							/********************��ֵ����****************/
							long start = System.currentTimeMillis();
							//System.out.println("��poi����word��ʼʱ�䣺" + start);
							String tempPath=sc.getRealPath(File.separator) + "excel/wxfwtymb.doc";
							//poiʵ��word����
							Map<String, String> param = new HashMap<String, String>();
							TDataLawobj lawobj = new TDataLawobj();
							lawobj.setId(zfdxMap.get("lawobjid"));
							//������ҵ
							String hyid = "";
							//��֯��������
							String zzdm="";
							//�ʱ�
							String postcode="";
							//��ҵ��ģ
							String qygm="";
							//Ͷ������
							String tcrq="";
							//��������
							String kzlx="";
							//��������
							String hbbmid="";
							//��ϵ��
							String lxr="";
							//��ϵ�绰
							String lxrdh="";
							//���ľ���
							String jd="";
							//����γ��
							String wd="";
							if("01".equals(zfdxMap.get("lawobjtype"))){
								hyid=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.gywry_hy.getCode()));
								zzdm=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.gywry_zzjgdm.getCode()));
								postcode=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.gywry_yb.getCode()));
								qygm=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.gywry_qygm.getCode()));
								tcrq=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.gywry_tcrq.getCode()));
								kzlx=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.gywry_kzlx.getCode()));
								hbbmid=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.gywry_ssjgbm.getCode()));
								lxr=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.gywry_hbfzr.getCode()));
								lxrdh=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.gywry_hbfzrlxdh.getCode()));
								jd=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.gywry_jd.getCode()));
								wd=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.gywry_wd.getCode()));
							}else if("03".equals(zfdxMap.get("lawobjtype"))){
								hyid=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.yy_hy.getCode()));
								zzdm=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.yy_zzjgdm.getCode()));
								postcode=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.yy_yb.getCode()));
								qygm=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.yy_qygm.getCode()));
								tcrq=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.yy_tcrq.getCode()));
								hbbmid=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.yy_ssjgbm.getCode()));
								lxr=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.yy_hbfzr.getCode()));
								lxrdh=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.yy_hbfzrlxdh.getCode()));
								jd=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.yy_jd.getCode()));
								wd=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.yy_wd.getCode()));
							}else if("06".equals(zfdxMap.get("lawobjtype"))){
								hyid=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.sc_hy.getCode()));
								postcode=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.sc_yb.getCode()));
								hbbmid=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.sc_ssjgbm.getCode()));
								lxr=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.sc_hbfzr.getCode()));
								lxrdh=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.sc_hbfzrlxdh.getCode()));
								jd=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.sc_jd.getCode()));
								wd=lawobjManager.getValueByColumnName(lawobj, lawobjManager.getColumnNameByEnumname(LawobjOutColunmEnum.sc_wd.getCode()));
							}
							String sshy = null;
							try {
								TDataIndustry ti=(TDataIndustry) this.get(TDataIndustry.class, hyid);
								sshy = ti.getName();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							String hbbm = null;
							try {
								TSysOrg org = (TSysOrg) this.get(TSysOrg.class, hbbmid);
								hbbm = org.getName();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(StringUtils.isNotBlank(jd)){
								String arr[] = jd.split(",");
								if(arr.length == 1){
									if("null" != arr[0]){
										jd = arr[0]+"��";
									}else{
										jd = "";
									}
								}else if(arr.length == 2){
									jd = arr[0]+"��"+arr[1]+"��";
								}else if(arr.length == 3){
									jd = arr[0]+"��"+arr[1]+"��"+arr[2]+"��";
								}
							}
							if(StringUtils.isNotBlank(wd)){
								String arr[] = wd.split(",");
								if(arr.length == 1){
									if("null" != arr[0]){
										wd = arr[0]+"��";
									}else{
										wd = "";
									}
								}else if(arr.length == 2){
									wd = arr[0]+"��"+arr[1]+"��";
								}else if(arr.length == 3){
									wd = arr[0]+"��"+arr[1]+"��"+arr[2]+"��";
								}
							}
							if(StringUtils.isNotBlank(zfdxMap.get("lawobjname"))){
								param.put("$dwmc$", zfdxMap.get("lawobjname"));//��λ����
							}else{
								param.put("$dwmc$", "");//��λ����
							}
							if(StringUtils.isNotBlank(zfdxMap.get("address")) && "null" != zfdxMap.get("address")){
								param.put("$dwdz$", zfdxMap.get("address"));//��λ��ַ
							}else{
								param.put("$dwdz$", "");//��λ��ַ
							}
							if(StringUtils.isNotBlank(postcode) && "null" != postcode){
								param.put("$postcode$", postcode);//�ʱ�
							}else{
								param.put("$postcode$", "");//�ʱ�
							}
							if(StringUtils.isNotBlank(zfdxMap.get("manager"))){
								param.put("$fzr$", zfdxMap.get("manager"));//������
							}else{
								param.put("$fzr$", "");//������
							}
							if(StringUtils.isNotBlank(zzdm) && "null" != zzdm){
								param.put("$zzdm$", zzdm);//���˴���
							}else{
								param.put("$zzdm$", "");//���˴���
							}
							if(StringUtils.isNotBlank(sshy) && "null" != sshy){
								param.put("$sshy$", sshy);//������ҵ
							}else{
								param.put("$sshy$", "");//������ҵ
							}
							if(StringUtils.isNotBlank(qygm) && "null" != qygm){
								param.put("$qygm$", qygm);//��ҵ��ģ
							}else{
								param.put("$qygm$", "");//��ҵ��ģ
							}
							if(StringUtils.isNotBlank(kzlx) && "null" != kzlx){
								param.put("$kzlx$", this.getDicNameByTypeAndCode(DicTypeEnum.KZLX.getCode(), kzlx));//��ҵ��������
							}else{
								param.put("$kzlx$", "");//��ҵ��������
							}
							if(StringUtils.isNotBlank(hbbm) && "null" != hbbm){
								param.put("$hbbm$", hbbm);//��������
							}else{
								param.put("$hbbm$", "");//��������
							}
							if(StringUtils.isNotBlank(zfdxMap.get("bjcr")) && "null" != zfdxMap.get("bjcr")){
								param.put("$lxr$", zfdxMap.get("bjcr"));//��ϵ��
							}else{
								param.put("$lxr$", "");//��ϵ��
							}
							if(StringUtils.isNotBlank(zfdxMap.get("lxdh")) && "null" != zfdxMap.get("lxdh")){
								param.put("$lxrdh$", zfdxMap.get("lxdh"));//��ϵ�˵绰
							}else{
								param.put("$lxrdh$", "");//��ϵ�˵绰
							}
							if(StringUtils.isNotBlank(tcrq) && "null" != tcrq){
								param.put("$tcrq$", tcrq);//Ͷ������
							}else{
								param.put("$tcrq$", "");//Ͷ������
							}
							if(StringUtils.isNotBlank(jd)){
								param.put("$jd$", jd);//����
							}else{
								param.put("$jd$", "");//����
							}
							if(StringUtils.isNotBlank(wd)){
								param.put("$wd$", wd);//γ��
							}else{
								param.put("$wd$", "");//γ��
							}
							//String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath()) + UUID.randomUUID();
							String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath());
							String newfile = PoiUtil.createWord(tempPath, dirPath, param);
							File file = new File(newfile);
							String path = UploadFileType.WORK.getPath() + File.separator + file.getName();
							//�����ļ�
							FileUpDownUtil.downloadFile(res, path, zfdxMap.get("lawobjname")+"Σ�շ������.doc");
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setServletContext(ServletContext arg0) {
		this.sc = arg0;
	}
	
	@Override
	public void downThumbnailImage(String id, HttpServletResponse res) {
		TDataFile file = (TDataFile)this.get(TDataFile.class, id);
		try {
			String path = file.getRelativepath() + File.separator + file.getOsname();
			FileUpDownUtil.downThumbnailImage(res, path, file.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeFile(String id) {
		try {
			TDataFile file = (TDataFile)this.get(TDataFile.class,id);
			if(file!=null){
				// ɾ���ļ�
				FileUpDownUtil.delFile(file.getRelativepath() + File.separator + file.getOsname());
				this.dao.remove(TDataFile.class, file.getId());
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void removeFileByPid(String pid) {
		try {
			List<TDataFile> listFile = this.find("from TDataFile f where f.pid = ?	",pid);
			if(listFile.size()>0){
				for(TDataFile file : listFile){
					// ɾ���ļ�
					FileUpDownUtil.delFile(file.getRelativepath() + File.separator + file.getOsname());
					this.dao.remove(TDataFile.class, file.getId());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void removeFileByPidAndFileType(String pid,String fileenumtype) {
		try {
			List<TDataFile> listFile = this.find("from TDataFile f where f.pid = ? and f.type=?	",pid,fileenumtype);
			if(listFile.size()>0){
				for(TDataFile file : listFile){
					this.dao.remove(TDataFile.class, file.getId());
					// ɾ���ļ�
					FileUpDownUtil.delFile(file.getRelativepath() + File.separator + file.getOsname());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void removeAllBlFileByPid(String pid) {
		try {
			List<TDataFile> listFile = this.find("from TDataFile f where f.pid = ? and f.type not in ('2500','2501','2502') ",pid);
			if(listFile.size()>0){
				for(TDataFile file : listFile){
					this.dao.remove(TDataFile.class, file.getId());
					// ɾ���ļ�
					FileUpDownUtil.delFile(file.getRelativepath() + File.separator + file.getOsname());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void removeBlFileByPidAndEnumtypeNames(String pid,String enumtypeNames) {
		try {
			String[] enumtypeArr=enumtypeNames.split(",");//Enumname
			String[] enumtypeCodeArr=new String[enumtypeArr.length];//code
			for(int i=0;i<enumtypeArr.length;i++){
				enumtypeCodeArr[i]=FileTypeEnums.getTypeByEnumName(enumtypeArr[i]);
			}
			
			List<TDataFile> listFile = this.find("from TDataFile f where f.pid = ? and f.type in("+StringUtil.convertSqlInArray(enumtypeCodeArr)+") ",pid);
			if(listFile.size()>0){
				for(TDataFile file : listFile){
					this.dao.remove(TDataFile.class, file.getId());
					// ɾ���ļ�
					FileUpDownUtil.delFile(file.getRelativepath() + File.separator + file.getOsname());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public FyWebResult queryFileList(String pid, String canDel,String fileType,String page, String rows) {
		List<String> ext = new ArrayList<String>();
		ext.add(".jpg");
		ext.add(".png");
		ext.add(".bmp");
		ext.add(".jpeg");
		ext.add(".doc");
		ext.add(".docx");
		ext.add(".txt");
		Map<String,Object> condition = new HashMap<String,Object>();
		String sql = "select f.id_,d.name_ filetype,f.name_,f.size_,f.pid_,f.type_ from t_data_file f left join t_sys_dic d on f.type_ = d.code_ where d.type_='4' and pid_ = :pid";
		condition.put("pid", pid);
		
		//��������
		if(StringUtils.isNotBlank(fileType)){
			sql+=" and d.code_=:fileTypeCode ";
			fileType = FileTypeEnums.getTypeByEnumName(fileType);
			condition.put("fileTypeCode", fileType);
		}
		
		sql+=" order by d.code_, f.CREATED_ desc";
		
		FyResult pos = this.dao.find(sql, Integer.parseInt(page), rows==null?0:Integer.parseInt(rows), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
		fy.setRows(rowsList);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		String name = "";
		for (Object[] obj : listLawobj) {
			if (String.valueOf(obj[1]).contains(".")){
				name = String.valueOf(obj[1]).substring((String.valueOf(obj[1]).lastIndexOf(".") + 1), String.valueOf(obj[1]).length());
			}
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("url", "/download.mo?id="+String.valueOf(obj[0]));
			if(obj[2] != null && FileUtil.isImage(String.valueOf(obj[2]))){
				dataObject.put("imageUrl", obj[0] == null ? "" : "/downThumbnailImage.mo?id="+String.valueOf(obj[0]));
			}
			dataObject.put("filetype", name);
			dataObject.put("filename", String.valueOf(obj[2]));
			long filesize = Long.valueOf(String.valueOf(obj[3]));
			dataObject.put("filesize1", String.valueOf(obj[3]));
			dataObject.put("filesize", FileUtil.sizeFormat(filesize));
			String operate = "";
			if (String.valueOf(obj[2]).lastIndexOf(".")!=-1 && ext.contains(String.valueOf(obj[2]).substring(String.valueOf(obj[2]).lastIndexOf("."), String.valueOf(obj[2]).length()).toLowerCase())) {
				operate = "<a class='b-link' onclick='review(this)' id='"+String.valueOf(obj[0])+","+String.valueOf(obj[3])+"' >Ԥ��</a>";
			}
			if (canDel==null || canDel.equals("Y")) {
				operate += OperateUtil.getDloadOperate(String.valueOf(obj[0]))+OperateUtil.getDeleteOperate(String.valueOf(obj[0]));
			} else {
				operate += OperateUtil.getDloadOperate(String.valueOf(obj[0]));
			}
			
			dataObject.put("operate", operate);
			dataObject.put("filetypecode", String.valueOf(obj[5]));
			rowsList.add(dataObject);
		}
		return fy;
	}

	@Override
	public FyWebResult queryFileList(String pid, String canDel,String fileType,String tableId,String page, String rows) {
		//�������߰��id�жϣ����Բ�ѯ��Ӧ��sql��䣩
		String biaoshi = indexManager.sysVer;
		String sql = null;
		List<String> ext = new ArrayList<String>();
		List<String> ext1 = new ArrayList<String>();
		ext1.add(".jpg");
		ext1.add(".png");
		ext1.add(".bmp");
		ext1.add(".jpeg");
		ext.add(".doc");
		ext.add(".docx");
		ext.add(".txt");
		Map<String,Object> condition = new HashMap<String,Object>();
		if(biaoshi.equals("0")){
			sql = "select f.id_,d.name_ filetype,f.name_,f.size_,f.pid_,f.type_ from t_data_file f left join t_sys_dic as d on f.type_ = d.code_ where pid_ = :pid";
		}else{
			sql = "select f.id_,d.name_ filetype,f.name_,f.size_,f.pid_,f.type_ from t_data_file f left join t_sys_dic d on f.type_ = d.code_ where pid_ = :pid";
		}
		condition.put("pid", pid);
		
		//��������
		if(StringUtils.isNotBlank(fileType)){
			sql+=" and d.code_=:fileTypeCode ";
			fileType = FileTypeEnums.getTypeByEnumName(fileType);
			condition.put("fileTypeCode", fileType);
		}
		
		sql+=" order by d.code_, f.CREATED_ desc";
		
		FyResult pos = this.dao.find(sql, Integer.parseInt(page), rows==null?0:Integer.parseInt(rows), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
		fy.setRows(rowsList);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		String name = "";
		for (Object[] obj : listLawobj) {
			if (String.valueOf(obj[1]).contains(".")){
				name = String.valueOf(obj[1]).substring((String.valueOf(obj[1]).lastIndexOf(".") + 1), String.valueOf(obj[1]).length());
			}else{
				name = String.valueOf(obj[1]);
			}
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("filetype", name);
			dataObject.put("filename", String.valueOf(obj[2]));
			long filesize = Long.valueOf(String.valueOf(obj[3]));
			dataObject.put("filesize", FileUtil.sizeFormat(filesize));
			String operate = "";
			if (String.valueOf(obj[2]).lastIndexOf(".")!=-1 && ext.contains(String.valueOf(obj[2]).substring(String.valueOf(obj[2]).lastIndexOf("."), String.valueOf(obj[2]).length()).toLowerCase())) {
				operate = "<a class='b-link' onclick='review(this)' id='"+String.valueOf(obj[0])+","+String.valueOf(obj[3])+"' >Ԥ��</a>";
			}else if(String.valueOf(obj[2]).lastIndexOf(".")!=-1 && ext1.contains(String.valueOf(obj[2]).substring(String.valueOf(obj[2]).lastIndexOf("."), String.valueOf(obj[2]).length()).toLowerCase())) {
				operate = "<a class='b-link' onclick='imgview(this)' id='"+String.valueOf(obj[0])+","+String.valueOf(obj[3])+"' >Ԥ��</a>";
			}
			if (canDel==null || canDel.equals("Y")) {
				operate += OperateUtil.getDloadOperate(String.valueOf(obj[0]))+OperateUtil.getDeleteOperate(String.valueOf(obj[0]),tableId);
			} else {
				operate += OperateUtil.getDloadOperate(String.valueOf(obj[0]));
			}
			
			dataObject.put("operate", operate);
			rowsList.add(dataObject);
		}
		return fy;
	}
	
	//���������ͺ�ҵ��id��ѯ����
	@Override
	public List<TDataFile> queryFileList(String pid,String fileType) {
		String sql = null;
		sql = "from TDataFile f where pid = ? and type = ?";
		fileType = FileTypeEnums.getTypeByEnumName(fileType);
		List<TDataFile> objList= this.dao.find(sql,pid,fileType);
		return objList;
	}
	
	//��ҵ��id��ѯ����
	@Override
	public List<TDataFile> queryFileList(String pid){
		String sql = null;
		sql = "from TDataFile f where pid = ? ";
		List<TDataFile> objList= this.dao.find(sql,pid);
		return objList;
	}
	
	//��ҵ��id��ѯ����,ȥ��ĳ�����͸���
	@Override
	public List<TDataFile> queryNoSomeFileList(String pid,String fileType1,String fileType2) {
		String sql = null;
		sql = "from TDataFile f where pid = ? and type != ? and type != ?";
		fileType1 = FileTypeEnums.getTypeByEnumName(fileType1);
		fileType2 = FileTypeEnums.getTypeByEnumName(fileType2);
		List<TDataFile> objList= this.dao.find(sql,pid,fileType1,fileType2);
		return objList;
	}
	
	@Override
	public FyWebResult queryFileListMulfileType(String pid, String canDel,String fileType,String page, String rows) {
		//�������߰��id�жϣ����Բ�ѯ��Ӧ��sql��䣩
		String biaoshi = indexManager.sysVer;
		String sql = null;
		String[] canDelArr=canDel.split(",");//�฽����Ӧɾ������
		String[] fileTypeArr=fileType.split(",");//�฽������Enumname
		String[] fileTypeCodeArr=new String[fileTypeArr.length];//�฽������code
		for(int i=0;i<fileTypeArr.length;i++){
			fileTypeCodeArr[i]=FileTypeEnums.getTypeByEnumName(fileTypeArr[i]);
		}
		
		Map<String,Object> condition = new HashMap<String,Object>();
		if(biaoshi.equals("0")){
			sql = "select f.id_,d.name_ filetype,f.name_,f.size_,f.pid_,f.type_ from t_data_file f left join t_sys_dic d on f.type_ = d.code_ where pid_ = :pid and d.type_ = '4'";
		}else{
			sql = "select f.id_,d.name_ filetype,f.name_,f.size_,f.pid_,f.type_ from t_data_file f left join t_sys_dic d on f.type_ = d.code_ where pid_ = :pid";
		}
		condition.put("pid", pid);
		
		//��������
		if(StringUtils.isNotBlank(fileType)){
			sql+=" and d.code_ in("+StringUtil.convertSqlInArray(fileTypeCodeArr)+") ";
		}
		
		sql+=" order by d.code_, f.CREATED_ desc";
		
		FyResult pos = this.dao.find(sql, Integer.parseInt(page), rows==null?0:Integer.parseInt(rows), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
		fy.setRows(rowsList);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		String name = "";
		for (Object[] obj : listLawobj) {
			if (String.valueOf(obj[1]).contains(".")){
				name = String.valueOf(obj[1]).substring((String.valueOf(obj[1]).lastIndexOf(".") + 1), String.valueOf(obj[1]).length());
			}
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("filetype", name);
			dataObject.put("filetypecode", String.valueOf(obj[5]));
			dataObject.put("filename", String.valueOf(obj[2]));
			Long filesize = Long.valueOf(String.valueOf(obj[3]));
			dataObject.put("filesize", FileUtil.sizeFormat(filesize));
			
			//type
			String typeCode=obj[5].toString();
			for(int i=0;i<fileTypeCodeArr.length;i++){
				if(typeCode.equals(fileTypeCodeArr[i].toString())){
					if (canDelArr[i]==null || canDelArr[i].equals("Y")) {
						dataObject.put("operate", OperateUtil.getDloadOperate(String.valueOf(obj[0]))+OperateUtil.getDeleteOperate(String.valueOf(obj[0])));	
					} else {
						dataObject.put("operate", OperateUtil.getDloadOperate(String.valueOf(obj[0])));
					}
				}
			}
			
			rowsList.add(dataObject);
		}
		return fy;
	}
	
	@Override
	public FyWebResult queryFileListMulfileType(String pid, String canDel,String fileType,String tableId,String page, String rows) {
		//�������߰��id�жϣ����Բ�ѯ��Ӧ��sql��䣩
		String biaoshi = indexManager.sysVer;
		String sql = null;
		List<String> ext = new ArrayList<String>();
		List<String> ext1 = new ArrayList<String>();
		ext1.add(".jpg");
		ext1.add(".png");
		ext1.add(".bmp");
		ext1.add(".jpeg");
		ext.add(".doc");
		ext.add(".docx");
		ext.add(".txt");
		String[] canDelArr=canDel.split(",");//�฽����Ӧɾ������
		String[] fileTypeArr=fileType.split(",");//�฽������Enumname
		String[] fileTypeCodeArr=new String[fileTypeArr.length];//�฽������code
		for(int i=0;i<fileTypeArr.length;i++){
			fileTypeCodeArr[i]=FileTypeEnums.getTypeByEnumName(fileTypeArr[i]);
		}
		
		Map<String,Object> condition = new HashMap<String,Object>();
		if(biaoshi.equals("0")){
			sql = "select f.id_,d.name_ filetype,f.name_,f.size_,f.pid_,f.type_,f.createby_ from t_data_file f left join t_sys_dic as d on f.type_ = d.code_ where d.type_='4' and pid_ = :pid";
		}else{
			sql = "select f.id_,d.name_ filetype,f.name_,f.size_,f.pid_,f.type_,f.createby_ from t_data_file f left join t_sys_dic d on f.type_ = d.code_ where d.type_='4'  and pid_ = :pid";
		}
		condition.put("pid", pid);
		
		//��������
		if(StringUtils.isNotBlank(fileType)){
			sql+=" and d.code_ in("+StringUtil.convertSqlInArray(fileTypeCodeArr)+") ";
		}
		
		sql+=" order by d.code_, f.CREATED_ desc";
		
		FyResult pos = this.dao.find(sql, Integer.parseInt(page), rows==null?0:Integer.parseInt(rows), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rowsList = new ArrayList<Map<String, String>>();
		fy.setRows(rowsList);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		String name = "";
		for (Object[] obj : listLawobj) {
			if (String.valueOf(obj[1]).contains(".")){
				name = String.valueOf(obj[1]).substring((String.valueOf(obj[1]).lastIndexOf(".") + 1), String.valueOf(obj[1]).length());
			}
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("url", "/download.mo?id="+String.valueOf(obj[0]));
			if(obj[2] != null && FileUtil.isImage(String.valueOf(obj[2]))){
				dataObject.put("imageUrl", obj[0] == null ? "" : "/downThumbnailImage.mo?id="+String.valueOf(obj[0]));
			}
			dataObject.put("filetype", name);
			dataObject.put("filename", String.valueOf(obj[2]));
			Long filesize = Long.valueOf(String.valueOf(obj[3]));
			dataObject.put("filesize", FileUtil.sizeFormat(filesize));
			
			//type
			String typeCode=obj[5].toString();
			//createby
			String createby=obj[6].toString();
			TSysUser user = CtxUtil.getCurUser();
			for(int i=0;i<fileTypeCodeArr.length;i++){
				if(typeCode.equals(fileTypeCodeArr[i].toString())){
					String operate = "";
					List<String> operateList=new ArrayList<String>();
					if (String.valueOf(obj[2]).lastIndexOf(".")!=-1 && ext.contains(String.valueOf(obj[2]).substring(String.valueOf(obj[2]).lastIndexOf("."), String.valueOf(obj[2]).length()).toLowerCase())) {
						operate = "<a class='b-link' onclick='review(this)' id='"+String.valueOf(obj[0])+","+String.valueOf(obj[3])+"' >Ԥ��</a>";
						operateList.add("yl");
					}else if (String.valueOf(obj[2]).lastIndexOf(".")!=-1 && ext1.contains(String.valueOf(obj[2]).substring(String.valueOf(obj[2]).lastIndexOf("."), String.valueOf(obj[2]).length()).toLowerCase())) {
						operate = "<a class='b-link' onclick='imgview(this)' id='"+String.valueOf(obj[0])+","+String.valueOf(obj[3])+"' >Ԥ��</a>";
						operateList.add("yl");
					}
					if ((canDelArr[i]==null || canDelArr[i].equals("Y"))&&(createby.equals(user.getId()))) {//ֻ��ɾ���Լ��ϴ��ĸ���
						operate +=OperateUtil.getDloadOperate(String.valueOf(obj[0]))+OperateUtil.getDeleteOperate(String.valueOf(obj[0]),tableId);	
						operateList.add("xz");
						operateList.add("sc");
					} else {
						operate +=OperateUtil.getDloadOperate(String.valueOf(obj[0]));
						operateList.add("xz");
					}
					dataObject.put("operate", operate);
					String operateStr = "";
					for(int m=0;m<operateList.size();m++){
						if(m<operateList.size()-1){
							operateStr+=operateList.get(m)+",";
						}else{
							operateStr+=operateList.get(m);
						}
					}
					dataObject.put("operateStr", operateStr);
				}
			}
			
			rowsList.add(dataObject);
		}
		return fy;
	}
	
	@Override
	public String saveChoseeLawobj(String rwid,String lawobjtype,JSONArray array, TSysUser user){
		try {
			if(StringUtils.isNotBlank(rwid)){
//				String sql = "delete from T_BIZ_TASKANDLAWOBJ where TASKID_ = '"+rwid+"'";
//				this.dao.exec(sql);
				List<TBizTaskandlawobj> list = this.dao.find("from TBizTaskandlawobj t where t.taskid = ?", rwid);
				//ɾ��ȡ������ȾԴ��Ϣ
				for(TBizTaskandlawobj taskandlawobj : list){
					boolean has = false;
					for(int i=0;i<array.length();i++){
						JSONObject obj = array.getJSONObject(i);
						if(obj.has("lawobjid") && obj.getString("lawobjid").equals(taskandlawobj.getLawobjid())){
							has = true;
							break;
						}
					}
					if(!has){//�����ھ�ɾ��
						if(StringUtil.isNotBlank(taskandlawobj.getNewtaskid())){
							return "ר���������ѷֹ�������ɾ��ִ������";
						}else{
							this.remove(taskandlawobj);
							/**
							 * һ����������壺1������ִ������2�����ִ������֮ǰ�Ƿ�ר���
							 */
							//��������
							List<Map<String, String>> rwlxlistMap = commWorkManager
			    					.getTaskTypeByTaskId(rwid);
			    			String rwlxIds = "";
			    			for (int i = 0; i < rwlxlistMap.size(); i++) {
			    				if (i < rwlxlistMap.size() - 1) {
			    					rwlxIds += rwlxlistMap.get(i).get("id") + ",";
			    				} else {
			    					rwlxIds += rwlxlistMap.get(i).get("id");
			    				}
			    			}
							if(array.length()==1){
								//���������͡���������
								//2����������
								if(!"13".equals(rwlxIds)){
									commWorkManager.saveDelRWLX(rwid);
								}
								//3������ҳ���Լ�������
								commWorkManager.saveDelBL(rwid);
							}
							ResultBean rb = commWorkManager.showBlPage(rwid);
							if(array.length()>0&&!rb.getResult()){
								//���������͡���������
								//2����������
								if(!"13".equals(rwlxIds)){
									commWorkManager.saveDelRWLX(rwid);
								}
								//3������ҳ���Լ�������
								commWorkManager.saveDelBL(rwid);
							}
						}
					}
				}
				//���������ִ�����������
				if(array.length()>0){
					for(int i=0;i<array.length();i++){
						JSONObject obj = array.getJSONObject(i);
						TBizTaskandlawobj tBizTaskandlawobj = null;
						for(TBizTaskandlawobj taskandlawobj : list){
							if(obj.has("lawobjid") && obj.getString("lawobjid").equals(taskandlawobj.getLawobjid())){
								tBizTaskandlawobj = taskandlawobj;
								break;
							}
						}
						if(tBizTaskandlawobj == null){
							tBizTaskandlawobj = new TBizTaskandlawobj();
							tBizTaskandlawobj.setCreateby(user);
							tBizTaskandlawobj.setCreated(new Date(System.currentTimeMillis()));
						}
						tBizTaskandlawobj.setTaskid(rwid);
						tBizTaskandlawobj.setLawobjtype(lawobjtype);
						tBizTaskandlawobj.setLawobjid(!obj.has("lawobjid")?"":obj.getString("lawobjid"));
						tBizTaskandlawobj.setLawobjname(!obj.has("lawobjname")?"":obj.getString("lawobjname"));
						if(obj.has("address")){
							tBizTaskandlawobj.setAddress(StringUtils.isBlank(obj.getString("address"))?"":obj.getString("address"));
						}
						if(obj.has("fddbr")){
							tBizTaskandlawobj.setManager(StringUtils.isBlank(obj.getString("fddbr"))?"":obj.getString("fddbr"));
						}
						if(obj.has("fddbrlxdh")){
							tBizTaskandlawobj.setManagermobile(StringUtils.isBlank(obj.getString("fddbrlxdh"))?"":obj.getString("fddbrlxdh"));
						}
						if(obj.has("hbfzr")){
							tBizTaskandlawobj.setBjcr(StringUtils.isBlank(obj.getString("hbfzr"))?"":obj.getString("hbfzr"));
						}
						if(obj.has("hbfzrlxdh")){
							tBizTaskandlawobj.setLxdh(StringUtils.isBlank(obj.getString("hbfzrlxdh"))?"":obj.getString("hbfzrlxdh"));
						}
						if(obj.has("regionid")){
							tBizTaskandlawobj.setRegionid(StringUtils.isBlank(obj.getString("regionid"))?"":obj.getString("regionid"));
						}
						if(obj.has("dwmc")){
							tBizTaskandlawobj.setXqyzDwmc(StringUtils.isBlank(obj.getString("dwmc"))?"":obj.getString("dwmc"));
						}
						tBizTaskandlawobj.setIsActive("Y");
						tBizTaskandlawobj.setUpdateby(user);
						tBizTaskandlawobj.setUpdated(new Date(System.currentTimeMillis()));
						this.save(tBizTaskandlawobj);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	@Override
	public List<TaskandlawobjForm> querySelectLawobjList(String rwid,String lawobjtype){
		List<TaskandlawobjForm> formList = new ArrayList<TaskandlawobjForm>();
		List<TBizTaskandlawobj> list = this.dao.find("from TBizTaskandlawobj t where t.taskid = ? and t.lawobjtype = ? ", rwid, lawobjtype);
		for(TBizTaskandlawobj tBizTaskandlawobj : list){
			formList.add(new TaskandlawobjForm(tBizTaskandlawobj.getId(),tBizTaskandlawobj.getLawobjid(),tBizTaskandlawobj.getLawobjname(),tBizTaskandlawobj.getRegionid(),tBizTaskandlawobj.getAddress(),tBizTaskandlawobj.getManager(),tBizTaskandlawobj.getManagermobile(),tBizTaskandlawobj.getBjcr(),tBizTaskandlawobj.getLxdh(),tBizTaskandlawobj.getNewtaskid()));
		}
		return formList;
	}

	@Override
	public TDataFile uploadSingleFile(MultipartFile multipartFile,
			HttpServletRequest request) throws AppException {
		TDataFile tDataFile = null;
		try {
			InputStream is = multipartFile.getInputStream();
			String pid = request.getParameter("pid");
			String fileType = request.getParameter("fileType");
			fileType = FileTypeEnums.getTypeByEnumName(fileType);
			String path = UploadFileType.getPathByEnumName(request.getParameter("path"));
			String fileName = multipartFile.getOriginalFilename();
			Long size = multipartFile.getSize();
			
			// ��ɾ�����ļ�
			List<TDataFile> listFile = this.find("from TDataFile f where f.pid = ? and type = ?", pid, fileType);
			if(listFile.size() > 0){
				for(TDataFile filePo : listFile){
					String dirPath = FileUpDownUtil.path.concat(filePo.getRelativepath());
					// ɾ���ļ�
					new File(dirPath + "//" + filePo.getOsname()).delete();
					this.dao.remove(TDataFile.class, filePo.getId());
				}
			}
			// �����ļ�
			tDataFile = this.saveFile(is, pid, fileType, path, fileName, size);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("�ϴ�ʧ�ܡ�");
		}
		return tDataFile;
	}

	@Override
	public void downZipFile(List<String> ids, HttpServletResponse res) {
		try {
			String biaoshi = indexManager.sysVer;
			String in = "";
			for (int i = 0; i < ids.size(); i++) {
				if (i > 0){
					in += ",";	
				}
				in += "'" + ids.get(i) + "'";
			}
			List<Object[]> files = this.dao.find("select a.name, a.osname, a.type, a.relativepath, b.orderby, '' as num from TDataFile a, TSysDic b where b.type='4' and a.type = b.code and a.id in (" + in + ") order by b.orderby");
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String time = sdf.format(date);
			// ѹ���ļ���·��
			String	path = "";
			String	path1 = "";
			String  path2 = "";
			String  path3 = "";
			String  path4 = "";//׼�����ϵ��ļ�·��
			String  path5 = "";//�����¼
			String  path6 = "";//ѯ�ʱ�¼
			if("0".equals(biaoshi)){
				path = FileUpDownUtil.path + File.separator + UploadFileType.TEMP.getPath() + File.separator + time;
				path1 = FileUpDownUtil.path + File.separator + UploadFileType.TEMP.getPath() + File.separator + time + File.separator + UploadFileType.QITA.getPath();
				path2 = FileUpDownUtil.path + File.separator + UploadFileType.TEMP.getPath() + File.separator + time + File.separator + UploadFileType.JCJL.getPath();
				path3 = FileUpDownUtil.path + File.separator + UploadFileType.TEMP.getPath() + File.separator + time + File.separator + UploadFileType.JCJLSMJ.getPath();
				path4 = FileUpDownUtil.path + File.separator + UploadFileType.TEMP.getPath() + File.separator + time + File.separator + UploadFileType.ZBZL.getPath();
				path5 = FileUpDownUtil.path + File.separator + UploadFileType.TEMP.getPath() + File.separator + time + File.separator + UploadFileType.KCBL.getPath();
				path6 = FileUpDownUtil.path + File.separator + UploadFileType.TEMP.getPath() + File.separator + time + File.separator + UploadFileType.XWBL.getPath();
			}else{
				path = FileUpDownUtil.path + File.separator + UploadFileType.TEMP.getPath() + File.separator + time;
			}
			File dir = new File(path);
			if (!dir.exists()){
				dir.mkdir();
			}
			String sourcePath = "";
			// ��Դ�ļ�������ѹ��Ŀ¼��
			int num = 1;
			Object order = null;
			String flag = String.valueOf(files.get(0)[4]);
			for (int i = 0; i < files.size(); i++) {
				if (i > 0){
					if (!flag.equals(String.valueOf(files.get(i)[4]))){
						num++;
						flag = String.valueOf(files.get(i)[4]);
					}
					if (num < 10){
						order = "0" + String.valueOf(num);
					} else {
						order = String.valueOf(num);
					}
				} else {
					order = "0" + String.valueOf(num);
				}
				files.get(i)[5] = order;
				sourcePath = FileUpDownUtil.path + File.separator + String.valueOf(files.get(i)[3]) + File.separator + String.valueOf(files.get(i)[1]);
				if("0".equals(biaoshi)){
					if(!files.get(i)[0].equals("workInfo.xml")){
						if(files.get(i)[2].equals("1000") || files.get(i)[2].equals("1100") || files.get(i)[2].equals("1200") || files.get(i)[2].equals("1300") || files.get(i)[2].equals("1400") || files.get(i)[2].equals("1500") || files.get(i)[2].equals("1700")
								|| files.get(i)[2].equals("1800") || files.get(i)[2].equals("1900") || files.get(i)[2].equals("1914") || files.get(i)[2].equals("2000") || files.get(i)[2].equals("2100") || files.get(i)[2].equals("2200") || files.get(i)[2].equals("2300")){
							FileUtil.copyFile(sourcePath, path2, String.valueOf(files.get(i)[5]) + String.valueOf(files.get(i)[0]));
						} 
						else if(files.get(i)[2].equals("1001") || files.get(i)[2].equals("1101") || files.get(i)[2].equals("1201") || files.get(i)[2].equals("1301") || files.get(i)[2].equals("1401") || files.get(i)[2].equals("1503") || files.get(i)[2].equals("1600")  ||  files.get(i)[2].equals("1601") || files.get(i)[2].equals("1603") || files.get(i)[2].equals("1701")
								|| files.get(i)[2].equals("1801") || files.get(i)[2].equals("1901") || files.get(i)[2].equals("2001") || files.get(i)[2].equals("2101") || files.get(i)[2].equals("2201") || files.get(i)[2].equals("2301") || files.get(i)[2].equals("6602")){
							FileUtil.copyFile(sourcePath, path1, String.valueOf(files.get(i)[5]) + String.valueOf(files.get(i)[0]));
						}
						else if(files.get(i)[2].equals("1605") ){
							FileUtil.copyFile(sourcePath, path3, String.valueOf(files.get(i)[5]) + String.valueOf(files.get(i)[0]));
						}
						else if(files.get(i)[2].equals("1007") || files.get(i)[2].equals("1107") || files.get(i)[2].equals("1207") || files.get(i)[2].equals("1307") || files.get(i)[2].equals("1407") || files.get(i)[2].equals("1510") || files.get(i)[2].equals("1707") || files.get(i)[2].equals("1807")
								|| files.get(i)[2].equals("2007") || files.get(i)[2].equals("2207") || files.get(i)[2].equals("2307")){
							FileUtil.copyFile(sourcePath, path2, String.valueOf(files.get(i)[5]) + String.valueOf(files.get(i)[0]));
						}
						else if(files.get(i)[2].equals("1006") || files.get(i)[2].equals("1106") || files.get(i)[2].equals("1206") || files.get(i)[2].equals("1306") || files.get(i)[2].equals("1406") || files.get(i)[2].equals("1509") || files.get(i)[2].equals("1614") || files.get(i)[2].equals("1706")
								|| files.get(i)[2].equals("1806") || files.get(i)[2].equals("1906") || files.get(i)[2].equals("2006") || files.get(i)[2].equals("2106") || files.get(i)[2].equals("2206") || files.get(i)[2].equals("2306") || files.get(i)[2].equals("6601")){
							FileUtil.copyFile(sourcePath, path4, String.valueOf(files.get(i)[5]) + String.valueOf(files.get(i)[0]));
						}
						else if(files.get(i)[2].equals("1602")){
							FileUtil.copyFile(sourcePath, path5, String.valueOf(files.get(i)[5]) + String.valueOf(files.get(i)[0]));
						}
						else if(files.get(i)[2].equals("1604")){
							FileUtil.copyFile(sourcePath, path6, String.valueOf(files.get(i)[5]) + String.valueOf(files.get(i)[0]));
						}
						else{
							FileUtil.copyFile(sourcePath, path1, String.valueOf(files.get(i)[5]) + String.valueOf(files.get(i)[0]));
						}
					}else{
						FileUtil.copyFile(sourcePath, path, String.valueOf(files.get(i)[0]));
					}
				}else{
					FileUtil.copyFile(sourcePath, path, String.valueOf(files.get(i)[5]) + String.valueOf(files.get(i)[0]));
				}
			}
			// ѹ��
			FileZipUtil zip = new FileZipUtil();
			zip.zipFolder(path, path + ".zip");
			// ����
			FileUpDownUtil.downloadFile(res, path.substring(FileUpDownUtil.path.length() - 1, path.length()) + ".zip", time + ".zip");
			// ɾ��ѹ���ļ�
			FileUtil.removeFolder(path);
			FileUtil.removeFile(path + ".zip");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public HashMap<String, Object> preview(String id, HttpServletRequest request) {
		String projPath = request.getSession().getServletContext().getRealPath("/");
		String httpPath = request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/preview.htm"));
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		String content = null;
		String extension = null;
		try {
			TDataFile po = (TDataFile) this.dao.get(TDataFile.class, id);
			
			extension = po.getExtension().toLowerCase();
			if (extension.equals("docx") || extension.equals("doc")){
				String sourcePath = FileUpDownUtil.path + File.separator + po.getRelativepath() + File.separator + po.getOsname();
				String htmlPath = projPath + "/static/temp/preview/" + po.getOsname() + ".html";
				Word2Html.wordToHtml(sourcePath, htmlPath);
				content = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n";
				content += "<html>\r\n";
				content += "<head>\r\n";
				content += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n";
				content += "</HEAD>\r\n";
				content += "<body>\r\n";
				content += "<iframe src=\"" + httpPath + "/static/temp/preview/" + po.getOsname() + ".html" + "\" width=\"100%\" height=\"580\" align=\"middle\" frameborder=\"0\"></iframe>\r\n";
				content += "</body>\r\n";
				content += "</html>\r\n";
			} else if (extension.equals("xls") || extension.equals("xlsx")) {
				String sourcePath = FileUpDownUtil.path + File.separator + po.getRelativepath() + File.separator + po.getOsname();
				String htmlPath = projPath + "/static/temp/preview/" + po.getOsname() + ".html";
				
				Thread t = Excel2Html.excelToHtml(sourcePath.replace("/", "\\").replace("\\\\", "\\"), htmlPath.replace("/", "\\").replace("\\\\", "\\"));
				
				int i = 0;
				while(!t.getState().name().equals(State.TERMINATED.name())){
					Thread.sleep(200);
					i += 1;
					if (i == 100) {
						// ���߳�һֱ������ 20��֮��ֱ���ж�ѭ��
						break;
					}
				}
				content = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n";
				content += "<html>\r\n";
				content += "<head>\r\n";
				content += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n";
				content += "</HEAD>\r\n";
				content += "<body>\r\n";
				content += "<iframe scrolling=\"no\" src=\"" + httpPath + "/static/temp/preview/" + po.getOsname() + ".html" + "\" width=\"100%\" height=\"580\" frameborder=\"0\"></iframe>\r\n";
				content += "</body>\r\n";
				content += "</html>\r\n";
			} else if (extension.equals("jpg") || extension.equals("png") || extension.equals("bmp") || extension.equals("jpeg")) {
				// �����ļ���static/temp/preview/pic��
				String sourcePath = FileUpDownUtil.path + File.separator + po.getRelativepath() + File.separator + po.getOsname();
				FileUtil.copyFile(sourcePath, projPath + "/static/temp/preview/", po.getOsname());
				content = "<html>\r\n";
				content += "<head>\r\n";
				content += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n";
				content += "</HEAD>\r\n";
				content += "<body>\r\n";
				content += "<img src=\"" + httpPath + "/static/temp/preview/" + po.getOsname() + "\">\r\n";
				content += "</body>";
			} else if (extension.equals("txt")) {
				String sourcePath = FileUpDownUtil.path + File.separator + po.getRelativepath() + File.separator + po.getOsname();
				content = "<html>\r\n";
				content += "<head>\r\n";
				content += "<meta http-equiv=Content-Type content=\"text/html; charset=UTF-8\">\r\n";
				content += "</HEAD>\r\n";
				content += "<body>\r\n";
				// ��ȡtxt����
				FileReader fr=new FileReader(sourcePath);
				//���Ի��ɹ���Ŀ¼�µ������ı��ļ�
				BufferedReader br = new BufferedReader(fr);
				String s = null;
				content += "";
				while((s = br.readLine()) != null){
					content += s.replaceAll(" ", "&nbsp;").replaceAll("	", "&nbsp;&nbsp;&nbsp;&nbsp;") + "<br>\r\n";
				}
				br.close();
				content += "</body>";
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		data.put("content", content);
		data.put("ext", extension);
		return data;
	}

	@Override
	public List<String> getColumnValue(String lawobjtype,String enumCode) {
		TDataLawobjdic jsxmmcDic = (TDataLawobjdic) this.dao.find("from TDataLawobjdic where enumname = ?", enumCode).get(0);
		String jsxmmc = jsxmmcDic.getColengname();
		String sql = "select " + jsxmmc + " as jsxmmc from T_DATA_LAWOBJ where lawobjtype_ = ? ";
		List<String> names = this.dao.findBySql(sql,lawobjtype);
		return names;
	}

	@Override
	public List<String> findBySql(String sql, Object... canshu) {
		return this.dao.findBySql(sql, canshu);
	}

	@Override
	public InputStream downFtpFile(String url, String path, String fileName, String userName, String passWord, int port) {
		InputStream is = null;
		FTPClient ftpClient = new FTPClient();
		try {
			String encoding = "UTF-8";
			int reply;
			ftpClient.setControlEncoding(encoding);
			// ����ftp������
			ftpClient.connect(url, port);
			// �������Ĭ�϶˿ڣ�����ʹ��ftp.connect(url)�ķ�ʽֱ������FTP������
			ftpClient.login(userName, passWord);// ��¼
			// �����ļ���������Ϊ������
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			// ��ȡftp��¼Ӧ�����
			reply = ftpClient.getReplyCode();
			// ��֤�Ƿ��½�ɹ�
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				System.err.println("FTP server refused connection.");
			}
			// ת�Ƶ�FTP������Ŀ¼��ָ����Ŀ¼��
			ftpClient.changeWorkingDirectory(new String(path.getBytes(encoding), encoding));
			// ��ȡ�ļ��б�
			/*FTPFile[] ffs = ftpClient.listFiles();
			for (FTPFile ff : ffs) {
				if (ff.getName().equals(fileName)) {
					is = ftpClient.retrieveFileStream(ff.getName());
					break;
				}
			}*/
			is = ftpClient.retrieveFileStream(fileName);
			// ���Ӳ���ע��
//			ftpClient.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return is;
	}
	
	
	@Override
	public List<Map<String, String>> getIsBtlist(String fileType){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		String[] str = fileType.split(",");
		for(int i=0;i<str.length;i++){
			List<TSysDic> listDic = this.dao.find("from TSysDic where type = ? and code = ? ", DicTypeEnum.FJLX.getCode(), FileTypeEnums.getTypeByEnumName(str[i]));
			if(listDic.size()>0){
				Map<String,String> map = new HashMap<String,String>();
				map.put("fileTypeEnumName", str[i]);
				map.put("isBT", listDic.get(0).getMandatory());
				list.add(map);
			}
		}
		return list;
	}

	@Override
	public List<Combobox> getJcmbByTaskType(String tasktype) {
		List<TDataChecklisttemplate> templates = this.dao.find("from TDataChecklisttemplate where tasktypeid = ? and iscurver='Y' and isActive = ? order by name asc ", tasktype, YnEnum.Y.getCode());
		List<Combobox> cs = new ArrayList<Combobox>();
		for (int i = 0; i < templates.size(); i++) {
			cs.add(new Combobox(templates.get(i).getId(), templates.get(i).getName()));
		}
		return cs;
	}
	
	@Override
	public List<Combobox> queryzfdxmcList() {
		List<Combobox> listResult = new ArrayList<Combobox>();
		List<TBizTaskandlawobj> list = this.find(" from TBizTaskandlawobj t where 1 = 1");
		for(TBizTaskandlawobj dic : list){
			Combobox combobox = new Combobox(dic.getId(),dic.getLawobjname());
			listResult.add(combobox);
		}
		return listResult;
	}

	@Override
	public LinkedHashMap<String, List<LinkedHashMap<String, Object>>> getExeclData(
			String lawObjType) {
		LinkedHashMap<String, List<LinkedHashMap<String, Object>>> data = new LinkedHashMap<String, List<LinkedHashMap<String,Object>>>();
		List<LinkedHashMap<String, Object>> lt = new ArrayList<LinkedHashMap<String,Object>>();
		LinkedHashMap<String, Object> ot = new LinkedHashMap<String, Object>();
		List<Combobox> cs = null;
		//����Ա������
		List<TDataRegion> rs=null;
		if (!CtxUtil.getCurUser().getId().equals("a0000000000000000000000000000000")){
			TSysArea area = (TSysArea) this.get(TSysArea.class,CtxUtil.getAreaId());
			if(area.getType().equals("1")){
				rs = this.find(" from TDataRegion r where r.pid = ? order by r.orderby ",area.getCode().substring(0, 4)+"00");
			}else if(area.getType().equals("2")){
				rs = this.find(" from TDataRegion r where r.id = ? order by r.orderby ",area.getCode());
			}else{
				rs = this.find(" from TDataRegion r where r.id != ? order by r.orderby ","660000");
			}
		}else{
			rs = this.dao.find(" from TDataRegion order by orderby asc");					
		}
		if(lawObjType.equals("01")){
		// ����������
				for (TDataRegion r : rs) {
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", r.getId());
					ot.put("name", r.getName());
					lt.add(ot);
				}
				data.put("����������", lt);
		//���ȣ�γ����д��ʽ
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", "87,16,12");
				ot.put("name", "���磺����87��16��12��,��дֵ��ʽ���ձ�ʶ��");
				lt.add(ot);
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", "43,11,13");
				ot.put("name", "���磺��γ43��11��13��,��дֵ��ʽ���ձ�ʶ��");
				lt.add(ot);
				data.put("��γ��", lt);
		// ��ҵ
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				cs = this.queryIndustryList(lawObjType);
				for (Combobox c : cs) {
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", c.getId());
					ot.put("name", c.getName());
					lt.add(ot);
				}
				data.put("��ҵ", lt);
		// ���������ֵ�
				
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				cs = this.queryKzlxList();
				for (Combobox c : cs) {
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", c.getId());
					ot.put("name", c.getName());
					lt.add(ot);
				}
				data.put("��������", lt);
				
		// ����������
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				List<TSysUser> us = this.dao.find("from TSysUser where areaId = ? and issys = ? and bizType = ? and isActive ='Y' ", CtxUtil.getCurUser().getAreaId(), YnEnum.N.getCode(), "0");
						for (TSysUser u : us) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", u.getId());
							ot.put("name", u.getName());
							lt.add(ot);
					}
				data.put("����������", lt);
		// ״̬
		lt = new ArrayList<LinkedHashMap<String,Object>>();
		cs = this.queryZtList();
		for (Combobox c : cs) {
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", c.getId());
			ot.put("name", c.getName());
			lt.add(ot);
		}
		data.put("״̬", lt);
		// ������ܲ���
		lt = new ArrayList<LinkedHashMap<String,Object>>();
		List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
		for (TSysOrg u : org) {
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", u.getId());
			ot.put("name", u.getName());
			lt.add(ot);
		}
		data.put("������ܲ���", lt);
		// ��Ӫ״̬
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			String yzt[]=new String []{"0","1","2"};
			for (int i = 0; i < yzt.length; i++) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", yzt[i]);
				if(yzt[i].equals("0")){
				  ot.put("name", "��Ӫ��");
				}
				if(yzt[i].equals("1")){
					ot.put("name", "�ѹر�");
				}
				if(yzt[i].equals("2")){
					ot.put("name", "��ͣ��");
				}
				lt.add(ot);
			}
			data.put("��ҵ״̬", lt);
			// ע������
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.queryTSysDicList("30");
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("ע������", lt);
			// ��������
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.queryTSysDicList("32");
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("��������", lt);
			//������ϵ
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.queryTSysDicList("31");
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("������ϵ", lt);
			//�Ƿ��շ���ҵ
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.querySfList();
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("�Ƿ��շ���ҵ", lt);
			//�������ϵ
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.queryTSysDicList("33");
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("�������ϵ", lt);
		}else if(lawObjType.equals("02")){
			//���ȣ�γ����д��ʽ
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "87,16,12");
			ot.put("name", "���磺����87��16��12��,��дֵ��ʽ���ձ�ʶ��");
			lt.add(ot);
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "43,11,13");
			ot.put("name", "���磺��γ43��11��13��,��дֵ��ʽ���ձ�ʶ��");
			lt.add(ot);
			data.put("��γ��", lt);
			// ����������
			//����Ա������
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			ot = new LinkedHashMap<String, Object>();
			for (TDataRegion r : rs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", r.getId());
				ot.put("name", r.getName());
				lt.add(ot);
			}
			data.put("����������", lt);
			// ���������ֵ�
			
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.queryTSysDicList("10");
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("��������", lt);
			
			// ������ȼ�����״̬
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.queryTSysDicList("8");
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("������ȼ�����״̬", lt);
			// ��������
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.queryTSysDicList("9");
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("��������", lt);
			// ��ҵ����
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.queryIndustryList(lawObjType);
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("��ҵ����", lt);
			// ����������
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysUser> us = this.dao.find("from TSysUser where areaId = ? and issys = ? and bizType = ? and isActive ='Y' ", CtxUtil.getCurUser().getAreaId(), YnEnum.N.getCode(), "0");
					for (TSysUser u : us) {
						ot = new LinkedHashMap<String, Object>();
						ot.put("id", u.getId());
						ot.put("name", u.getName());
						lt.add(ot);
				}
			data.put("����������", lt);
			// ������ܲ���
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
			for (TSysOrg u : org) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", u.getId());
				ot.put("name", u.getName());
				lt.add(ot);
			}
			data.put("������ܲ���", lt);
			// ״̬
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.queryZtList();
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("״̬", lt);
		}else if(lawObjType.equals("03")|lawObjType.equals("04")){
			// ����������
				//����Ա������
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				ot = new LinkedHashMap<String, Object>();
				for (TDataRegion r : rs) {
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", r.getId());
					ot.put("name", r.getName());
					lt.add(ot);
				}
				data.put("����������", lt);
				//���ȣ�γ����д��ʽ
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", "87,16,12");
				ot.put("name", "���磺����87��16��12��,��дֵ��ʽ���ձ�ʶ��");
				lt.add(ot);
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", "43,11,13");
				ot.put("name", "���磺��γ43��11��13��,��дֵ��ʽ���ձ�ʶ��");
				lt.add(ot);
				data.put("��γ��", lt);
				// ����������
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				List<TSysUser> us = this.dao.find("from TSysUser where areaId = ? and issys = ? and bizType = ? and isActive ='Y' ", CtxUtil.getCurUser().getAreaId(), YnEnum.N.getCode(), "0");
				for (TSysUser u : us) {
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", u.getId());
					ot.put("name", u.getName());
					lt.add(ot);
				}
				data.put("����������", lt);	
				// ������ܲ���
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
				for (TSysOrg u : org) {
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", u.getId());
					ot.put("name", u.getName());
					lt.add(ot);
				}
				data.put("������ܲ���", lt);
				// ״̬
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				cs = this.queryZtList();
				for (Combobox c : cs) {
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", c.getId());
					ot.put("name", c.getName());
					lt.add(ot);
					}
				data.put("״̬", lt);
				// ��Ӫ״̬
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				String yzt[]=new String []{"0","1","2"};
				for (int i = 0; i < yzt.length; i++) {
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", yzt[i]);
					if(yzt[i].equals("0")){
			        ot.put("name", "��Ӫ��");
				}
				if(yzt[i].equals("1")){
					ot.put("name", "�ѹر�");
				}
				if(yzt[i].equals("2")){
			    	ot.put("name", "��ͣ��");
				}
					lt.add(ot);
				}
				data.put("��ҵ״̬", lt);
		}else if(lawObjType.equals("05")){
			
			//���ȣ�γ����д��ʽ
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "87,16,12");
			ot.put("name", "���磺����87��16��12��,��дֵ��ʽ���ձ�ʶ��");
			lt.add(ot);
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "43,11,13");
			ot.put("name", "���磺��γ43��11��13��,��дֵ��ʽ���ձ�ʶ��");
			lt.add(ot);
			data.put("��γ��", lt);
			// ����������
						//����Ա������
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						ot = new LinkedHashMap<String, Object>();
						for (TDataRegion r : rs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", r.getId());
							ot.put("name", r.getName());
							lt.add(ot);
						}
						data.put("����������", lt);
			// ����������
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysUser> us = this.dao.find("from TSysUser where areaId = ? and issys = ? and bizType = ? and isActive ='Y' ", CtxUtil.getCurUser().getAreaId(), YnEnum.N.getCode(), "0");
			for (TSysUser u : us) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", u.getId());
				ot.put("name", u.getName());
				lt.add(ot);
			}
			data.put("����������", lt);
			// ״̬
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						cs = this.queryZtList();
						for (Combobox c : cs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", c.getId());
							ot.put("name", c.getName());
							lt.add(ot);
							}
						data.put("״̬", lt);
			// ������ܲ���
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
			for (TSysOrg u : org) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", u.getId());
				ot.put("name", u.getName());
				lt.add(ot);
			}
			data.put("������ܲ���", lt);
			
			// ��ҵ״̬
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			String yzt[]=new String []{"0","1","2"};
			for (int i = 0; i < yzt.length; i++) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", yzt[i]);
				if(yzt[i].equals("0")){
		        ot.put("name", "��Ӫ��");
			}
			if(yzt[i].equals("1")){
				ot.put("name", "�ѹر�");
			}
			if(yzt[i].equals("2")){
		    	ot.put("name", "��ͣ��");
			}
				lt.add(ot);
			}
			data.put("��ҵ״̬", lt);	
		}else if(lawObjType.equals("06")){
			
			// ����������
						//����Ա������
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						ot = new LinkedHashMap<String, Object>();
						for (TDataRegion r : rs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", r.getId());
							ot.put("name", r.getName());
							lt.add(ot);
						}
						data.put("����������", lt);
				// ��ҵ����
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						cs = this.queryIndustryList(lawObjType);
						for (Combobox c : cs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", c.getId());
							ot.put("name", c.getName());
							lt.add(ot);
						}
						data.put("��ҵ", lt);
				//���ȣ�γ����д��ʽ
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						ot = new LinkedHashMap<String, Object>();
						ot.put("id", "87,16,12");
						ot.put("name", "���磺����87��16��12��,��дֵ��ʽ���ձ�ʶ��");
						lt.add(ot);
						ot = new LinkedHashMap<String, Object>();
						ot.put("id", "43,11,13");
						ot.put("name", "���磺��γ43��11��13��,��дֵ��ʽ���ձ�ʶ��");
						lt.add(ot);
						data.put("��γ��", lt);
			// ����������
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysUser> us = this.dao.find("from TSysUser where areaId = ? and issys = ? and bizType = ? and isActive ='Y' ", CtxUtil.getCurUser().getAreaId(), YnEnum.N.getCode(), "0");
			for (TSysUser u : us) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", u.getId());
				ot.put("name", u.getName());
				lt.add(ot);
			}
			data.put("����������", lt);
			// ״̬
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						cs = this.queryZtList();
						for (Combobox c : cs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", c.getId());
							ot.put("name", c.getName());
							lt.add(ot);
							}
						data.put("״̬", lt);
			// ������ܲ���
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
			for (TSysOrg u : org) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", u.getId());
				ot.put("name", u.getName());
				lt.add(ot);
			}
			data.put("������ܲ���", lt);
			
			// ��ҵ״̬
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			String yzt[]=new String []{"0","1","2"};
			for (int i = 0; i < yzt.length; i++) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", yzt[i]);
				if(yzt[i].equals("0")){
		        ot.put("name", "��Ӫ��");
			}
			if(yzt[i].equals("1")){
				ot.put("name", "�ѹر�");
			}
			if(yzt[i].equals("2")){
		    	ot.put("name", "��ͣ��");
			}
				lt.add(ot);
			}
			data.put("��ҵ״̬", lt);
		}else if(lawObjType.equals("07")){
			// ����������
			//����Ա������
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			ot = new LinkedHashMap<String, Object>();
			for (TDataRegion r : rs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", r.getId());
				ot.put("name", r.getName());
				lt.add(ot);
			}
			data.put("����������", lt);
			//���ȣ�γ����д��ʽ
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "87,16,12");
			ot.put("name", "���磺����87��16��12��,��дֵ��ʽ���ձ�ʶ��");
			lt.add(ot);
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "43,11,13");
			ot.put("name", "���磺��γ43��11��13��,��дֵ��ʽ���ձ�ʶ��");
			lt.add(ot);
			data.put("��γ��", lt);
			// ����������
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysUser> us = this.dao.find("from TSysUser where areaId = ? and issys = ? and bizType = ? and isActive ='Y' ", CtxUtil.getCurUser().getAreaId(), YnEnum.N.getCode(), "0");
			for (TSysUser u : us) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", u.getId());
				ot.put("name", u.getName());
				lt.add(ot);
			}
			data.put("����������", lt);	
			// ״̬
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						cs = this.queryZtList();
						for (Combobox c : cs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", c.getId());
							ot.put("name", c.getName());
							lt.add(ot);
							}
						data.put("״̬", lt);
			// ������ܲ���
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
			for (TSysOrg u : org) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", u.getId());
				ot.put("name", u.getName());
				lt.add(ot);
			}
			data.put("������ܲ���", lt);
			
			// ��Ӫ״̬
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			String yzt[]=new String []{"0","1","2"};
			for (int i = 0; i < yzt.length; i++) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", yzt[i]);
				if(yzt[i].equals("0")){
		        ot.put("name", "��Ӫ��");
			}
			if(yzt[i].equals("1")){
				ot.put("name", "�ѹر�");
			}
			if(yzt[i].equals("2")){
		    	ot.put("name", "��ͣ��");
			}
				lt.add(ot);
			}
			data.put("��ҵ״̬", lt);
		}else if(lawObjType.equals("08")){
			// ����������
						//����Ա������
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						ot = new LinkedHashMap<String, Object>();
						for (TDataRegion r : rs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", r.getId());
							ot.put("name", r.getName());
							lt.add(ot);
						}
						data.put("����������", lt);
						//���ȣ�γ����д��ʽ
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						ot = new LinkedHashMap<String, Object>();
						ot.put("id", "87,16,12");
						ot.put("name", "���磺����87��16��12��,��дֵ��ʽ���ձ�ʶ��");
						lt.add(ot);
						ot = new LinkedHashMap<String, Object>();
						ot.put("id", "43,11,13");
						ot.put("name", "���磺��γ43��11��13��,��дֵ��ʽ���ձ�ʶ��");
						lt.add(ot);
						data.put("��γ��", lt);
						//����
						lt = new ArrayList<LinkedHashMap<String,Object>>();
					    ot = new LinkedHashMap<String, Object>();
						ot.put("id", "Y");
						ot.put("name", "��");
						lt.add(ot);
						ot = new LinkedHashMap<String, Object>();
						ot.put("id", "N");
						ot.put("name", "��");
						lt.add(ot);
						data.put("����,סլ,������ů,���й���", lt);
						// ������ܲ���
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
						for (TSysOrg u : org) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", u.getId());
							ot.put("name", u.getName());
							lt.add(ot);
						}
						data.put("������ܲ���", lt);
						
						// ��Ӫ״̬
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						String yzt[]=new String []{"0","1","2"};
						for (int i = 0; i < yzt.length; i++) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", yzt[i]);
							if(yzt[i].equals("0")){
					        ot.put("name", "��Ӫ��");
						}
						if(yzt[i].equals("1")){
							ot.put("name", "�ѹر�");
						}
						if(yzt[i].equals("2")){
					    	ot.put("name", "��ͣ��");
						}
							lt.add(ot);
						}
						data.put("��ҵ״̬", lt);
						// ״̬
									lt = new ArrayList<LinkedHashMap<String,Object>>();
									cs = this.queryZtList();
									for (Combobox c : cs) {
										ot = new LinkedHashMap<String, Object>();
										ot.put("id", c.getId());
										ot.put("name", c.getName());
										lt.add(ot);
										}
									data.put("״̬", lt);
						
		}else if(lawObjType.equals("09")){
			// ����������
			//����Ա������
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			ot = new LinkedHashMap<String, Object>();
			for (TDataRegion r : rs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", r.getId());
				ot.put("name", r.getName());
				lt.add(ot);
			}
			data.put("����������", lt);
			//���ȣ�γ����д��ʽ
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "87,16,12");
			ot.put("name", "���磺����87��16��12��,��дֵ��ʽ���ձ�ʶ��");
			lt.add(ot);
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "43,11,13");
			ot.put("name", "���磺��γ43��11��13��,��дֵ��ʽ���ձ�ʶ��");
			lt.add(ot);
			data.put("��γ��", lt);
			//����
			lt = new ArrayList<LinkedHashMap<String,Object>>();
		    ot = new LinkedHashMap<String, Object>();
			ot.put("id", "Y");
			ot.put("name", "��");
			lt.add(ot);
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "N");
			ot.put("name", "��");
			lt.add(ot);
			data.put("����,סլ,�Ƿ���������,�����̹ܵ��Ƿ񳬳�¥��", lt);
			// ������ܲ���
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
			for (TSysOrg u : org) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", u.getId());
				ot.put("name", u.getName());
				lt.add(ot);
			}
			data.put("������ܲ���", lt);
			
			// ��Ӫ״̬
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			String yzt[]=new String []{"0","1","2"};
			for (int i = 0; i < yzt.length; i++) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", yzt[i]);
				if(yzt[i].equals("0")){
		        ot.put("name", "��Ӫ��");
			}
			if(yzt[i].equals("1")){
				ot.put("name", "�ѹر�");
			}
			if(yzt[i].equals("2")){
		    	ot.put("name", "��ͣ��");
			}
				lt.add(ot);
			}
			data.put("��ҵ״̬", lt);
			// ״̬
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						cs = this.queryZtList();
						for (Combobox c : cs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", c.getId());
							ot.put("name", c.getName());
							lt.add(ot);
							}
						data.put("״̬", lt);
		}else if(lawObjType.equals("10")){
			// ����������
						//����Ա������
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						ot = new LinkedHashMap<String, Object>();
						for (TDataRegion r : rs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", r.getId());
							ot.put("name", r.getName());
							lt.add(ot);
						}
						data.put("����������", lt);
						//���ȣ�γ����д��ʽ
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						ot = new LinkedHashMap<String, Object>();
						ot.put("id", "87,16,12");
						ot.put("name", "���磺����87��16��12��,��дֵ��ʽ���ձ�ʶ��");
						lt.add(ot);
						ot = new LinkedHashMap<String, Object>();
						ot.put("id", "43,11,13");
						ot.put("name", "���磺��γ43��11��13��,��дֵ��ʽ���ձ�ʶ��");
						lt.add(ot);
						data.put("��γ��", lt);
						//����
						lt = new ArrayList<LinkedHashMap<String,Object>>();
					    ot = new LinkedHashMap<String, Object>();
						ot.put("id", "Y");
						ot.put("name", "��");
						lt.add(ot);
						ot = new LinkedHashMap<String, Object>();
						ot.put("id", "N");
						ot.put("name", "��");
						lt.add(ot);
						data.put("����,סլ,���������ʩ,�硢ˢ����", lt);
						// ������ܲ���
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
						for (TSysOrg u : org) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", u.getId());
							ot.put("name", u.getName());
							lt.add(ot);
						}
						data.put("������ܲ���", lt);
						
						// ��Ӫ״̬
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						String yzt[]=new String []{"0","1","2"};
						for (int i = 0; i < yzt.length; i++) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", yzt[i]);
							if(yzt[i].equals("0")){
					        ot.put("name", "��Ӫ��");
						}
						if(yzt[i].equals("1")){
							ot.put("name", "�ѹر�");
						}
						if(yzt[i].equals("2")){
					    	ot.put("name", "��ͣ��");
						}
							lt.add(ot);
						}
						data.put("��ҵ״̬", lt);
						// ״̬
									lt = new ArrayList<LinkedHashMap<String,Object>>();
									cs = this.queryZtList();
									for (Combobox c : cs) {
										ot = new LinkedHashMap<String, Object>();
										ot.put("id", c.getId());
										ot.put("name", c.getName());
										lt.add(ot);
										}
									data.put("״̬", lt);	
		}else if(lawObjType.equals("11")){
			// ����������
			//����Ա������
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			ot = new LinkedHashMap<String, Object>();
			for (TDataRegion r : rs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", r.getId());
				ot.put("name", r.getName());
				lt.add(ot);
			}
			data.put("����������", lt);
			//���ȣ�γ����д��ʽ
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "87,16,12");
			ot.put("name", "���磺����87��16��12��,��дֵ��ʽ���ձ�ʶ��");
			lt.add(ot);
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "43,11,13");
			ot.put("name", "���磺��γ43��11��13��,��дֵ��ʽ���ձ�ʶ��");
			lt.add(ot);
			data.put("��γ��", lt);
			//����
			lt = new ArrayList<LinkedHashMap<String,Object>>();
		    ot = new LinkedHashMap<String, Object>();
			ot.put("id", "Y");
			ot.put("name", "��");
			lt.add(ot);
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "N");
			ot.put("name", "��");
			lt.add(ot);
			data.put("����,סլ", lt);
			// ������ܲ���
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
			for (TSysOrg u : org) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", u.getId());
				ot.put("name", u.getName());
				lt.add(ot);
			}
			data.put("������ܲ���", lt);
			
			// ��Ӫ״̬
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			String yzt[]=new String []{"0","1","2"};
			for (int i = 0; i < yzt.length; i++) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", yzt[i]);
				if(yzt[i].equals("0")){
		        ot.put("name", "��Ӫ��");
			}
			if(yzt[i].equals("1")){
				ot.put("name", "�ѹر�");
			}
			if(yzt[i].equals("2")){
		    	ot.put("name", "��ͣ��");
			}
				lt.add(ot);
			}
			data.put("��ҵ״̬", lt);
			// ״̬
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						cs = this.queryZtList();
						for (Combobox c : cs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", c.getId());
							ot.put("name", c.getName());
							lt.add(ot);
							}
						data.put("״̬", lt);	
		}
		// ��ҵ����״̬
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				    ot = new LinkedHashMap<String, Object>();
					ot.put("id", "Y");
					ot.put("name", "ȫ��������");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "N");
					ot.put("name", "����������");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "����N,01,02,03");
					ot.put("name", "������������д��ʽ");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "01");
					ot.put("name", "1��");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "02");
					ot.put("name", "2��");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "03");
					ot.put("name", "3��");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "04");
					ot.put("name", "4��");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "05");
					ot.put("name", "5��");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "06");
					ot.put("name", "6��");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "07");
					ot.put("name", "7��");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "08");
					ot.put("name", "8��");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "09");
					ot.put("name", "9��");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "10");
					ot.put("name", "10��");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "11");
					ot.put("name", "11��");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "12");
					ot.put("name", "12��");
					lt.add(ot);
					data.put("��ҵ����״̬", lt);
		return data;
	}
	
    public HashMap<String, Object> imgView(String id, HttpServletRequest request) {
        String projPath = request.getSession().getServletContext().getRealPath("/");
        String httpPath = request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/imgView"));
        HashMap<String, Object> data = new HashMap<String, Object>();
        String filePath = null;
        try {
            TDataFile po = (TDataFile) this.dao.get(TDataFile.class, id);
            String sourcePath = FileUpDownUtil.path + File.separator + po.getRelativepath() + File.separator + po.getOsname();
            FileUtil.copyFile(sourcePath, projPath + "/static/temp/preview/", po.getOsname() + "." + po.getExtension());
            filePath = httpPath + "/static/temp/preview/" + po.getOsname() + "." + po.getExtension();
            data.put("filePath", filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }



	
}