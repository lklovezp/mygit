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
 * 作者：renzhengquan
 * 生成日期：2015-3-9
 * 功能描述：
		公共功能Manager实现层
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
			if(DicTypeEnum.JJCD.getCode().equals(type)){//紧急程度加天数
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
		listResult.add(new Combobox("Y","有效"));
		listResult.add(new Combobox("N","无效"));
		return listResult;
	}
	
	@Override
	public List<Combobox> queryQyztList() {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult.add(new Combobox("0","运营中"));
		listResult.add(new Combobox("1","已关闭"));
		listResult.add(new Combobox("2","已停产"));
		return listResult;
	}
	
	@Override
	public List<Combobox> querySfList() {
		List<Combobox> listResult = new ArrayList<Combobox>();
		listResult.add(new Combobox("Y","是"));
		listResult.add(new Combobox("N","否"));
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
			listResult.add(new Combobox("3100","工业污染源.组织机构代码扫描件"));
			listResult.add(new Combobox("3101","工业污染源.现场勘查示意图"));
			listResult.add(new Combobox("3102","工业污染源.营业执照扫描件"));
			listResult.add(new Combobox("3103","工业污染源.排污许可证扫描件"));
			listResult.add(new Combobox("3104","工业污染源.其他"));
			return listResult;	
			
		}else{
			return FileTypeEnums.getTypeListByEnumName(enumName);
		}
	}
	
	
	
	@Override
	public List<ComboboxTree> queryRegionTree(){
		List<TDataRegion> list = null;
		//管理员看所有
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
		//添加离线版的标识判断（可以查询对应的sql语句）
		String biaoshi = indexManager.sysVer;
		List<TSysOrg> list = new ArrayList<TSysOrg>();
		TSysOrg pOrg = null;
		if("0".equals(biaoshi)){
			TSysOrg pOrg1 = null;
			pOrg1 = orgManager.getOrgByUserid(CtxUtil.getUserId());//当前用户所在部门
			if("0".equals(pOrg1.getType())){
				pOrg = orgManager.getOrgByUserid(CtxUtil.getUserId());//当前用户所在部门
			}else{
				pOrg = orgManager.getOrgByUserid(CtxUtil.getUserId()).getOrg();//当前用户所在部门
			}
		}else{
			pOrg = orgManager.getOrgByUserid(CtxUtil.getUserId()).getOrg();//当前用户所在部门
		}
		String areaId = CtxUtil.getAreaId();
		// 如果不是超级管理员 加区域限制
		if (!CtxUtil.getCurUser().getId().equals("a0000000000000000000000000000000") && StringUtils.isNotBlank(areaId)) {
			/**查询本区域及所有下级区域 */
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
	 * 函数介绍：通过id获得当前节点的子项
	
	 * 输入参数：
	
	 * 返回值：
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
	 * 函数介绍：通过id获得当前节点的子项
	
	 * 输入参数：
	
	 * 返回值：
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
		//根据tasktype的code获取任务类型id
		TDataTasktype t= taskTypeManager.getTaskTypeByCode(tasktype);
		if(t!=null){
			hql += " and t.tasktypeid = '"+t.getId()+"'";	
		}
		hql += " order by i.orderby";
		List<TDataIllegaltype> list = this.dao.find(hql);
		return this.illegalTypeTreeHelp(list, null, 1);
		
	}
	
	private List<ComboboxTree> illegalTypeTreeHelp(List<TDataIllegaltype> list, String pid, int level){
		//添加离线版的标识判断（可以查询对应的sql语句）
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
	 * 查询当前用户及以下的区域
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
	 * 查询当前用的区域,如果是admin查询所有的区域
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
	 * 查询当前用户下的区域
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
	 * 查询指定区域的下级区域
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
			// 上传文件并保存数据到数据库
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
			// 上传文件并保存数据到数据库
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
			// 上传文件并保存数据到数据库
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
		// 文件id
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		TDataLawdoc lawdoc = new TDataLawdoc(FileUtil.getFileNameNoEx(filename),pid,FileUtil.getFileNameNoEx(filename),uuid);
		this.save(lawdoc);
		return this.saveFile(lawdoc, is, fileenumtype, path, filename, size);
	}
	
	public TDataFile saveFile(TDataFile filePo, MultipartFile file, String pid, String fileenumtype, UploadFileType path){
		try {
			// 上传文件并保存数据到数据库
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
		// 保存数据到数据库
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
		List<TDataLawobjdic> lawobjDic = this.dao.find("from TDataLawobjdic where lawobjtypeid = ? and colchiname != ? and colchiname != ? order by orderby ", lawObjType,"创建人","所属执法对象id");
		String title = ((TSysDic)this.dao.find("from TSysDic where type = 5 and code = ?", lawObjType).get(0)).getName();

		LinkedHashMap<String, List<LinkedHashMap<String, Object>>> data=this.getExeclData(lawObjType);
		// 生成excel模板
		InputStream is = ExcelUtil.genTemplate(lawobjDic, title, data);
		try {
			res.setHeader("Content-Disposition", "attachment;filename=" + new String((title + "导入模板.xls").getBytes("GB2312"), "ISO-8859-1"));
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
		// 读取用户上传的excel将其中的数据封装成listmap
		ArrayList<Map<String, Object>> data1 = ExcelUtil.readLawObjExcel(file);
		Map<String, Object> map=null;
		List<Map<String, Object>> data =new ArrayList<Map<String,Object>>();
		for(int i=0;i<data1.size();i++){
			map=new HashMap<String, Object>();
			map=data1.get(i);
			map.put("创建人", CtxUtil.getCurUser().getId());
			data.add(map);
		}
		// 去的当前执法对象类型所有执法对象的name，用于校验name是否重复。
		List<String> names = getColumnValue(lawObjType,lawObjType + PublicColumnEnum.mc.getCode());
		// 此执法对象name字典配置
		TDataLawobjdic mcDic = (TDataLawobjdic) this.dao.find("from TDataLawobjdic where lawobjtypeid = '" + lawObjType + "' and enumname = ?", lawObjType + PublicColumnEnum.mc.getCode()).get(0);
		
		if (data.size() > 0){
			ArrayList<Map<String, Object>> enlist = new ArrayList<Map<String,Object>>();
			Map<String, Object> enmap = null;
			// 循环数据map将map中中文key值和执法对象字典表对比替换成英文列名
			for (int i = 0; i < data.size(); i++) {
				enmap = new HashMap<String, Object>();
				// 循环字典表
				for (int j = 0; j < lawobjDic.size(); j++) {
					// 如果数据map中key值有包含字典表中的中文字段名时
					if (data.get(i).containsKey(lawobjDic.get(j).getColchiname())){
						// 当数据为空且数据库中配置此项不能为空时抛出异常
						if(StringUtils.isBlank(String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname()))) && lawobjDic.get(j).getMandatory().equals("Y")){
							throw new AppException("第" + String.valueOf(i + 1) + "行" + lawobjDic.get(j).getColchiname() + "不能为空！");
						}
						// name非法字符判断
						/*if (lawobjDic.get(j).getEnumname().equals(lawObjType + PublicColumnEnum.mc.getCode())){
							if (String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())).contains("\\") || String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())).contains("/")
									|| String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())).contains(":") || String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())).contains("*")
									|| String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())).contains("?") || String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())).contains("\"")
									|| String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())).contains("<") || String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())).contains(">")
									|| String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())).contains("|")){
								throw new AppException("第" + String.valueOf(i + 1) + "行单位name中不能包含【\\/:*?\"<>|】非法字符。");
							}
						}*/
						if (lawobjDic.get(j).getEnumname().equals(lawObjType + PublicColumnEnum.mc.getCode()) && names.contains(String.valueOf(data.get(i).get(lawobjDic.get(j).getColchiname())))){
							throw new AppException("第" + String.valueOf(i + 1) + "行数据中"+lawobjDic.get(j).getColchiname()+"重复，请重新检查后上传！");
						}
						// 将数据map中此中文key对应的value取出放到新的map中并以与此中文name对应的英文列名作为key
						enmap.put(lawobjDic.get(j).getColengname(), data.get(i).get(lawobjDic.get(j).getColchiname()));
					}
				}
				// 将map添加到list中
				enlist.add(enmap);
			}
			StringBuffer sqlBuf = new StringBuffer();
			String uuid = "";
			// 循环已经转化好的listmap按照记录条数拼接成插入sql 并循环插入数据库
			for (int i = 0; i < enlist.size(); i++) {
				sqlBuf = new StringBuffer();
				uuid = UUID.randomUUID().toString().replaceAll("-", "");
				// 插入固定的id和执法对象类型两列
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
			if(FileTypeEnums.ZFWJGLZFWJ.getCode().equals(fileType)){//保存执法文件
				tDataFile = this.saveLawdoc(is, pid, fileType, path, fileName, size);
			}else{
				tDataFile = this.saveFile(is, pid, fileType, path, fileName, size);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("上传失败。");
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
			//添加离线版的标识判断（可以查询对应的sql语句）
			String biaoshi = indexManager.sysVer;
			List<Map<String, String>> rwlxlistMap = commWorkManager.getTaskTypeByTaskId(applyId);// 任务类型列表
			for(int i = 0; i < rwlxlistMap.size(); i++){
				if(rwlxlistMap.get(i).get("id").equals(TaskTypeCode.WXFW.getCode())){
					if (null != applyId && !"".equals(applyId.trim())) {
						//获取企业的基本信息
						Map<String, String> zfdxMap = new HashMap<String, String>();
						List<Map<String, String>> zfdxlistMap = commWorkManager.zfdxTableData(applyId);
						if (zfdxlistMap != null && zfdxlistMap.size() == 1) {
							zfdxMap = zfdxlistMap.get(0);
							/********************赋值结束****************/
							long start = System.currentTimeMillis();
							//System.out.println("用poi生成word开始时间：" + start);
							String tempPath=sc.getRealPath(File.separator) + "excel/wxfwtymb.doc";
							//poi实现word操作
							Map<String, String> param = new HashMap<String, String>();
							TDataLawobj lawobj = new TDataLawobj();
							lawobj.setId(zfdxMap.get("lawobjid"));
							//所属行业
							String hyid = "";
							//组织机构代码
							String zzdm="";
							//邮编
							String postcode="";
							//企业规模
							String qygm="";
							//投产日期
							String tcrq="";
							//控制类型
							String kzlx="";
							//环保部门
							String hbbmid="";
							//联系人
							String lxr="";
							//联系电话
							String lxrdh="";
							//中心经度
							String jd="";
							//中心纬度
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
										jd = arr[0]+"度";
									}else{
										jd = "";
									}
								}else if(arr.length == 2){
									jd = arr[0]+"度"+arr[1]+"分";
								}else if(arr.length == 3){
									jd = arr[0]+"度"+arr[1]+"分"+arr[2]+"秒";
								}
							}
							if(StringUtils.isNotBlank(wd)){
								String arr[] = wd.split(",");
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
							}
							if(StringUtils.isNotBlank(zfdxMap.get("lawobjname"))){
								param.put("$dwmc$", zfdxMap.get("lawobjname"));//单位名称
							}else{
								param.put("$dwmc$", "");//单位名称
							}
							if(StringUtils.isNotBlank(zfdxMap.get("address")) && "null" != zfdxMap.get("address")){
								param.put("$dwdz$", zfdxMap.get("address"));//单位地址
							}else{
								param.put("$dwdz$", "");//单位地址
							}
							if(StringUtils.isNotBlank(postcode) && "null" != postcode){
								param.put("$postcode$", postcode);//邮编
							}else{
								param.put("$postcode$", "");//邮编
							}
							if(StringUtils.isNotBlank(zfdxMap.get("manager"))){
								param.put("$fzr$", zfdxMap.get("manager"));//负责人
							}else{
								param.put("$fzr$", "");//负责人
							}
							if(StringUtils.isNotBlank(zzdm) && "null" != zzdm){
								param.put("$zzdm$", zzdm);//法人代码
							}else{
								param.put("$zzdm$", "");//法人代码
							}
							if(StringUtils.isNotBlank(sshy) && "null" != sshy){
								param.put("$sshy$", sshy);//所属行业
							}else{
								param.put("$sshy$", "");//所属行业
							}
							if(StringUtils.isNotBlank(qygm) && "null" != qygm){
								param.put("$qygm$", qygm);//企业规模
							}else{
								param.put("$qygm$", "");//企业规模
							}
							if(StringUtils.isNotBlank(kzlx) && "null" != kzlx){
								param.put("$kzlx$", this.getDicNameByTypeAndCode(DicTypeEnum.KZLX.getCode(), kzlx));//企业控制类型
							}else{
								param.put("$kzlx$", "");//企业控制类型
							}
							if(StringUtils.isNotBlank(hbbm) && "null" != hbbm){
								param.put("$hbbm$", hbbm);//环保部门
							}else{
								param.put("$hbbm$", "");//环保部门
							}
							if(StringUtils.isNotBlank(zfdxMap.get("bjcr")) && "null" != zfdxMap.get("bjcr")){
								param.put("$lxr$", zfdxMap.get("bjcr"));//联系人
							}else{
								param.put("$lxr$", "");//联系人
							}
							if(StringUtils.isNotBlank(zfdxMap.get("lxdh")) && "null" != zfdxMap.get("lxdh")){
								param.put("$lxrdh$", zfdxMap.get("lxdh"));//联系人电话
							}else{
								param.put("$lxrdh$", "");//联系人电话
							}
							if(StringUtils.isNotBlank(tcrq) && "null" != tcrq){
								param.put("$tcrq$", tcrq);//投产日期
							}else{
								param.put("$tcrq$", "");//投产日期
							}
							if(StringUtils.isNotBlank(jd)){
								param.put("$jd$", jd);//经度
							}else{
								param.put("$jd$", "");//经度
							}
							if(StringUtils.isNotBlank(wd)){
								param.put("$wd$", wd);//纬度
							}else{
								param.put("$wd$", "");//纬度
							}
							//String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath()) + UUID.randomUUID();
							String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath());
							String newfile = PoiUtil.createWord(tempPath, dirPath, param);
							File file = new File(newfile);
							String path = UploadFileType.WORK.getPath() + File.separator + file.getName();
							//下载文件
							FileUpDownUtil.downloadFile(res, path, zfdxMap.get("lawobjname")+"危险废物检查表.doc");
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
				// 删除文件
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
					// 删除文件
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
					// 删除文件
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
					// 删除文件
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
					// 删除文件
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
		
		//附件类型
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
				operate = "<a class='b-link' onclick='review(this)' id='"+String.valueOf(obj[0])+","+String.valueOf(obj[3])+"' >预览</a>";
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
		//添加离线版的id判断（可以查询对应的sql语句）
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
		
		//附件类型
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
				operate = "<a class='b-link' onclick='review(this)' id='"+String.valueOf(obj[0])+","+String.valueOf(obj[3])+"' >预览</a>";
			}else if(String.valueOf(obj[2]).lastIndexOf(".")!=-1 && ext1.contains(String.valueOf(obj[2]).substring(String.valueOf(obj[2]).lastIndexOf("."), String.valueOf(obj[2]).length()).toLowerCase())) {
				operate = "<a class='b-link' onclick='imgview(this)' id='"+String.valueOf(obj[0])+","+String.valueOf(obj[3])+"' >预览</a>";
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
	
	//按附件类型和业务id查询附件
	@Override
	public List<TDataFile> queryFileList(String pid,String fileType) {
		String sql = null;
		sql = "from TDataFile f where pid = ? and type = ?";
		fileType = FileTypeEnums.getTypeByEnumName(fileType);
		List<TDataFile> objList= this.dao.find(sql,pid,fileType);
		return objList;
	}
	
	//按业务id查询附件
	@Override
	public List<TDataFile> queryFileList(String pid){
		String sql = null;
		sql = "from TDataFile f where pid = ? ";
		List<TDataFile> objList= this.dao.find(sql,pid);
		return objList;
	}
	
	//按业务id查询附件,去掉某种类型附件
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
		//添加离线版的id判断（可以查询对应的sql语句）
		String biaoshi = indexManager.sysVer;
		String sql = null;
		String[] canDelArr=canDel.split(",");//多附件对应删除操作
		String[] fileTypeArr=fileType.split(",");//多附件类型Enumname
		String[] fileTypeCodeArr=new String[fileTypeArr.length];//多附件类型code
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
		
		//附件类型
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
		//添加离线版的id判断（可以查询对应的sql语句）
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
		String[] canDelArr=canDel.split(",");//多附件对应删除操作
		String[] fileTypeArr=fileType.split(",");//多附件类型Enumname
		String[] fileTypeCodeArr=new String[fileTypeArr.length];//多附件类型code
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
		
		//附件类型
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
						operate = "<a class='b-link' onclick='review(this)' id='"+String.valueOf(obj[0])+","+String.valueOf(obj[3])+"' >预览</a>";
						operateList.add("yl");
					}else if (String.valueOf(obj[2]).lastIndexOf(".")!=-1 && ext1.contains(String.valueOf(obj[2]).substring(String.valueOf(obj[2]).lastIndexOf("."), String.valueOf(obj[2]).length()).toLowerCase())) {
						operate = "<a class='b-link' onclick='imgview(this)' id='"+String.valueOf(obj[0])+","+String.valueOf(obj[3])+"' >预览</a>";
						operateList.add("yl");
					}
					if ((canDelArr[i]==null || canDelArr[i].equals("Y"))&&(createby.equals(user.getId()))) {//只能删除自己上传的附件
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
				//删除取消的污染源信息
				for(TBizTaskandlawobj taskandlawobj : list){
					boolean has = false;
					for(int i=0;i<array.length();i++){
						JSONObject obj = array.getJSONObject(i);
						if(obj.has("lawobjid") && obj.getString("lawobjid").equals(taskandlawobj.getLawobjid())){
							has = true;
							break;
						}
					}
					if(!has){//不存在就删除
						if(StringUtil.isNotBlank(taskandlawobj.getNewtaskid())){
							return "专项子任务已分工，不能删除执法对象！";
						}else{
							this.remove(taskandlawobj);
							/**
							 * 一下两种情况清：1、单个执法对象；2、多个执法对象（之前是非专项）；
							 */
							//任务类型
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
								//清任务类型、办理内容
								//2、任务类型
								if(!"13".equals(rwlxIds)){
									commWorkManager.saveDelRWLX(rwid);
								}
								//3、办理页面以及子任务
								commWorkManager.saveDelBL(rwid);
							}
							ResultBean rb = commWorkManager.showBlPage(rwid);
							if(array.length()>0&&!rb.getResult()){
								//清任务类型、办理内容
								//2、任务类型
								if(!"13".equals(rwlxIds)){
									commWorkManager.saveDelRWLX(rwid);
								}
								//3、办理页面以及子任务
								commWorkManager.saveDelBL(rwid);
							}
						}
					}
				}
				//更新任务和执法对象关联表
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
			
			// 先删除旧文件
			List<TDataFile> listFile = this.find("from TDataFile f where f.pid = ? and type = ?", pid, fileType);
			if(listFile.size() > 0){
				for(TDataFile filePo : listFile){
					String dirPath = FileUpDownUtil.path.concat(filePo.getRelativepath());
					// 删除文件
					new File(dirPath + "//" + filePo.getOsname()).delete();
					this.dao.remove(TDataFile.class, filePo.getId());
				}
			}
			// 保存文件
			tDataFile = this.saveFile(is, pid, fileType, path, fileName, size);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("上传失败。");
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
			// 压缩文件夹路径
			String	path = "";
			String	path1 = "";
			String  path2 = "";
			String  path3 = "";
			String  path4 = "";//准备资料的文件路径
			String  path5 = "";//勘察笔录
			String  path6 = "";//询问笔录
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
			// 将源文件拷贝到压缩目录下
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
			// 压缩
			FileZipUtil zip = new FileZipUtil();
			zip.zipFolder(path, path + ".zip");
			// 下载
			FileUpDownUtil.downloadFile(res, path.substring(FileUpDownUtil.path.length() - 1, path.length()) + ".zip", time + ".zip");
			// 删除压缩文件
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
						// 当线程一直不结束 20秒之后直接中断循环
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
				// 拷贝文件到static/temp/preview/pic下
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
				// 读取txt内容
				FileReader fr=new FileReader(sourcePath);
				//可以换成工程目录下的其他文本文件
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
			// 连接ftp服务器
			ftpClient.connect(url, port);
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftpClient.login(userName, passWord);// 登录
			// 设置文件传输类型为二进制
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			// 获取ftp登录应答代码
			reply = ftpClient.getReplyCode();
			// 验证是否登陆成功
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				System.err.println("FTP server refused connection.");
			}
			// 转移到FTP服务器目录至指定的目录下
			ftpClient.changeWorkingDirectory(new String(path.getBytes(encoding), encoding));
			// 获取文件列表
			/*FTPFile[] ffs = ftpClient.listFiles();
			for (FTPFile ff : ffs) {
				if (ff.getName().equals(fileName)) {
					is = ftpClient.retrieveFileStream(ff.getName());
					break;
				}
			}*/
			is = ftpClient.retrieveFileStream(fileName);
			// 连接不能注销
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
		//管理员看所有
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
		// 所属行政区
				for (TDataRegion r : rs) {
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", r.getId());
					ot.put("name", r.getName());
					lt.add(ot);
				}
				data.put("所属行政区", lt);
		//经度，纬度书写格式
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", "87,16,12");
				ot.put("name", "例如：东经87度16分12秒,填写值格式参照标识列");
				lt.add(ot);
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", "43,11,13");
				ot.put("name", "例如：北纬43度11分13秒,填写值格式参照标识列");
				lt.add(ot);
				data.put("经纬度", lt);
		// 行业
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				cs = this.queryIndustryList(lawObjType);
				for (Combobox c : cs) {
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", c.getId());
					ot.put("name", c.getName());
					lt.add(ot);
				}
				data.put("行业", lt);
		// 控制类型字典
				
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				cs = this.queryKzlxList();
				for (Combobox c : cs) {
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", c.getId());
					ot.put("name", c.getName());
					lt.add(ot);
				}
				data.put("控制类型", lt);
				
		// 网格化责任人
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				List<TSysUser> us = this.dao.find("from TSysUser where areaId = ? and issys = ? and bizType = ? and isActive ='Y' ", CtxUtil.getCurUser().getAreaId(), YnEnum.N.getCode(), "0");
						for (TSysUser u : us) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", u.getId());
							ot.put("name", u.getName());
							lt.add(ot);
					}
				data.put("网格化责任人", lt);
		// 状态
		lt = new ArrayList<LinkedHashMap<String,Object>>();
		cs = this.queryZtList();
		for (Combobox c : cs) {
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", c.getId());
			ot.put("name", c.getName());
			lt.add(ot);
		}
		data.put("状态", lt);
		// 所属监管部门
		lt = new ArrayList<LinkedHashMap<String,Object>>();
		List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
		for (TSysOrg u : org) {
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", u.getId());
			ot.put("name", u.getName());
			lt.add(ot);
		}
		data.put("所属监管部门", lt);
		// 运营状态
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			String yzt[]=new String []{"0","1","2"};
			for (int i = 0; i < yzt.length; i++) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", yzt[i]);
				if(yzt[i].equals("0")){
				  ot.put("name", "运营中");
				}
				if(yzt[i].equals("1")){
					ot.put("name", "已关闭");
				}
				if(yzt[i].equals("2")){
					ot.put("name", "已停产");
				}
				lt.add(ot);
			}
			data.put("企业状态", lt);
			// 注册类型
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.queryTSysDicList("30");
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("注册类型", lt);
			// 国控类型
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.queryTSysDicList("32");
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("国控类型", lt);
			//隶属关系
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.queryTSysDicList("31");
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("隶属关系", lt);
			//是否收费企业
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.querySfList();
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("是否收费企业", lt);
			//和央企关系
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.queryTSysDicList("33");
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("和央企关系", lt);
		}else if(lawObjType.equals("02")){
			//经度，纬度书写格式
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "87,16,12");
			ot.put("name", "例如：东经87度16分12秒,填写值格式参照标识列");
			lt.add(ot);
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "43,11,13");
			ot.put("name", "例如：北纬43度11分13秒,填写值格式参照标识列");
			lt.add(ot);
			data.put("经纬度", lt);
			// 所属行政区
			//管理员看所有
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			ot = new LinkedHashMap<String, Object>();
			for (TDataRegion r : rs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", r.getId());
				ot.put("name", r.getName());
				lt.add(ot);
			}
			data.put("所属行政区", lt);
			// 审批机关字典
			
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.queryTSysDicList("10");
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("审批机关", lt);
			
			// 建设进度及生产状态
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.queryTSysDicList("8");
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("建设进度及生产状态", lt);
			// 建设性质
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.queryTSysDicList("9");
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("建设性质", lt);
			// 行业类型
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.queryIndustryList(lawObjType);
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("行业类型", lt);
			// 网格化责任人
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysUser> us = this.dao.find("from TSysUser where areaId = ? and issys = ? and bizType = ? and isActive ='Y' ", CtxUtil.getCurUser().getAreaId(), YnEnum.N.getCode(), "0");
					for (TSysUser u : us) {
						ot = new LinkedHashMap<String, Object>();
						ot.put("id", u.getId());
						ot.put("name", u.getName());
						lt.add(ot);
				}
			data.put("网格化责任人", lt);
			// 所属监管部门
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
			for (TSysOrg u : org) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", u.getId());
				ot.put("name", u.getName());
				lt.add(ot);
			}
			data.put("所属监管部门", lt);
			// 状态
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			cs = this.queryZtList();
			for (Combobox c : cs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", c.getId());
				ot.put("name", c.getName());
				lt.add(ot);
			}
			data.put("状态", lt);
		}else if(lawObjType.equals("03")|lawObjType.equals("04")){
			// 所属行政区
				//管理员看所有
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				ot = new LinkedHashMap<String, Object>();
				for (TDataRegion r : rs) {
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", r.getId());
					ot.put("name", r.getName());
					lt.add(ot);
				}
				data.put("所属行政区", lt);
				//经度，纬度书写格式
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", "87,16,12");
				ot.put("name", "例如：东经87度16分12秒,填写值格式参照标识列");
				lt.add(ot);
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", "43,11,13");
				ot.put("name", "例如：北纬43度11分13秒,填写值格式参照标识列");
				lt.add(ot);
				data.put("经纬度", lt);
				// 网格化责任人
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				List<TSysUser> us = this.dao.find("from TSysUser where areaId = ? and issys = ? and bizType = ? and isActive ='Y' ", CtxUtil.getCurUser().getAreaId(), YnEnum.N.getCode(), "0");
				for (TSysUser u : us) {
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", u.getId());
					ot.put("name", u.getName());
					lt.add(ot);
				}
				data.put("网格化责任人", lt);	
				// 所属监管部门
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
				for (TSysOrg u : org) {
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", u.getId());
					ot.put("name", u.getName());
					lt.add(ot);
				}
				data.put("所属监管部门", lt);
				// 状态
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				cs = this.queryZtList();
				for (Combobox c : cs) {
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", c.getId());
					ot.put("name", c.getName());
					lt.add(ot);
					}
				data.put("状态", lt);
				// 运营状态
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				String yzt[]=new String []{"0","1","2"};
				for (int i = 0; i < yzt.length; i++) {
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", yzt[i]);
					if(yzt[i].equals("0")){
			        ot.put("name", "运营中");
				}
				if(yzt[i].equals("1")){
					ot.put("name", "已关闭");
				}
				if(yzt[i].equals("2")){
			    	ot.put("name", "已停产");
				}
					lt.add(ot);
				}
				data.put("企业状态", lt);
		}else if(lawObjType.equals("05")){
			
			//经度，纬度书写格式
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "87,16,12");
			ot.put("name", "例如：东经87度16分12秒,填写值格式参照标识列");
			lt.add(ot);
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "43,11,13");
			ot.put("name", "例如：北纬43度11分13秒,填写值格式参照标识列");
			lt.add(ot);
			data.put("经纬度", lt);
			// 所属行政区
						//管理员看所有
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						ot = new LinkedHashMap<String, Object>();
						for (TDataRegion r : rs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", r.getId());
							ot.put("name", r.getName());
							lt.add(ot);
						}
						data.put("所属行政区", lt);
			// 网格化责任人
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysUser> us = this.dao.find("from TSysUser where areaId = ? and issys = ? and bizType = ? and isActive ='Y' ", CtxUtil.getCurUser().getAreaId(), YnEnum.N.getCode(), "0");
			for (TSysUser u : us) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", u.getId());
				ot.put("name", u.getName());
				lt.add(ot);
			}
			data.put("网格化责任人", lt);
			// 状态
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						cs = this.queryZtList();
						for (Combobox c : cs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", c.getId());
							ot.put("name", c.getName());
							lt.add(ot);
							}
						data.put("状态", lt);
			// 所属监管部门
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
			for (TSysOrg u : org) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", u.getId());
				ot.put("name", u.getName());
				lt.add(ot);
			}
			data.put("所属监管部门", lt);
			
			// 企业状态
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			String yzt[]=new String []{"0","1","2"};
			for (int i = 0; i < yzt.length; i++) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", yzt[i]);
				if(yzt[i].equals("0")){
		        ot.put("name", "运营中");
			}
			if(yzt[i].equals("1")){
				ot.put("name", "已关闭");
			}
			if(yzt[i].equals("2")){
		    	ot.put("name", "已停产");
			}
				lt.add(ot);
			}
			data.put("企业状态", lt);	
		}else if(lawObjType.equals("06")){
			
			// 所属行政区
						//管理员看所有
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						ot = new LinkedHashMap<String, Object>();
						for (TDataRegion r : rs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", r.getId());
							ot.put("name", r.getName());
							lt.add(ot);
						}
						data.put("所属行政区", lt);
				// 行业类型
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						cs = this.queryIndustryList(lawObjType);
						for (Combobox c : cs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", c.getId());
							ot.put("name", c.getName());
							lt.add(ot);
						}
						data.put("行业", lt);
				//经度，纬度书写格式
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						ot = new LinkedHashMap<String, Object>();
						ot.put("id", "87,16,12");
						ot.put("name", "例如：东经87度16分12秒,填写值格式参照标识列");
						lt.add(ot);
						ot = new LinkedHashMap<String, Object>();
						ot.put("id", "43,11,13");
						ot.put("name", "例如：北纬43度11分13秒,填写值格式参照标识列");
						lt.add(ot);
						data.put("经纬度", lt);
			// 网格化责任人
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysUser> us = this.dao.find("from TSysUser where areaId = ? and issys = ? and bizType = ? and isActive ='Y' ", CtxUtil.getCurUser().getAreaId(), YnEnum.N.getCode(), "0");
			for (TSysUser u : us) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", u.getId());
				ot.put("name", u.getName());
				lt.add(ot);
			}
			data.put("网格化责任人", lt);
			// 状态
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						cs = this.queryZtList();
						for (Combobox c : cs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", c.getId());
							ot.put("name", c.getName());
							lt.add(ot);
							}
						data.put("状态", lt);
			// 所属监管部门
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
			for (TSysOrg u : org) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", u.getId());
				ot.put("name", u.getName());
				lt.add(ot);
			}
			data.put("所属监管部门", lt);
			
			// 企业状态
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			String yzt[]=new String []{"0","1","2"};
			for (int i = 0; i < yzt.length; i++) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", yzt[i]);
				if(yzt[i].equals("0")){
		        ot.put("name", "运营中");
			}
			if(yzt[i].equals("1")){
				ot.put("name", "已关闭");
			}
			if(yzt[i].equals("2")){
		    	ot.put("name", "已停产");
			}
				lt.add(ot);
			}
			data.put("企业状态", lt);
		}else if(lawObjType.equals("07")){
			// 所属行政区
			//管理员看所有
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			ot = new LinkedHashMap<String, Object>();
			for (TDataRegion r : rs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", r.getId());
				ot.put("name", r.getName());
				lt.add(ot);
			}
			data.put("所属行政区", lt);
			//经度，纬度书写格式
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "87,16,12");
			ot.put("name", "例如：东经87度16分12秒,填写值格式参照标识列");
			lt.add(ot);
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "43,11,13");
			ot.put("name", "例如：北纬43度11分13秒,填写值格式参照标识列");
			lt.add(ot);
			data.put("经纬度", lt);
			// 网格化责任人
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysUser> us = this.dao.find("from TSysUser where areaId = ? and issys = ? and bizType = ? and isActive ='Y' ", CtxUtil.getCurUser().getAreaId(), YnEnum.N.getCode(), "0");
			for (TSysUser u : us) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", u.getId());
				ot.put("name", u.getName());
				lt.add(ot);
			}
			data.put("网格化责任人", lt);	
			// 状态
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						cs = this.queryZtList();
						for (Combobox c : cs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", c.getId());
							ot.put("name", c.getName());
							lt.add(ot);
							}
						data.put("状态", lt);
			// 所属监管部门
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
			for (TSysOrg u : org) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", u.getId());
				ot.put("name", u.getName());
				lt.add(ot);
			}
			data.put("所属监管部门", lt);
			
			// 运营状态
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			String yzt[]=new String []{"0","1","2"};
			for (int i = 0; i < yzt.length; i++) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", yzt[i]);
				if(yzt[i].equals("0")){
		        ot.put("name", "运营中");
			}
			if(yzt[i].equals("1")){
				ot.put("name", "已关闭");
			}
			if(yzt[i].equals("2")){
		    	ot.put("name", "已停产");
			}
				lt.add(ot);
			}
			data.put("企业状态", lt);
		}else if(lawObjType.equals("08")){
			// 所属行政区
						//管理员看所有
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						ot = new LinkedHashMap<String, Object>();
						for (TDataRegion r : rs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", r.getId());
							ot.put("name", r.getName());
							lt.add(ot);
						}
						data.put("所属行政区", lt);
						//经度，纬度书写格式
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						ot = new LinkedHashMap<String, Object>();
						ot.put("id", "87,16,12");
						ot.put("name", "例如：东经87度16分12秒,填写值格式参照标识列");
						lt.add(ot);
						ot = new LinkedHashMap<String, Object>();
						ot.put("id", "43,11,13");
						ot.put("name", "例如：北纬43度11分13秒,填写值格式参照标识列");
						lt.add(ot);
						data.put("经纬度", lt);
						//底商
						lt = new ArrayList<LinkedHashMap<String,Object>>();
					    ot = new LinkedHashMap<String, Object>();
						ot.put("id", "Y");
						ot.put("name", "是");
						lt.add(ot);
						ot = new LinkedHashMap<String, Object>();
						ot.put("id", "N");
						ot.put("name", "否");
						lt.add(ot);
						data.put("底商,住宅,冬季供暖,集中供热", lt);
						// 所属监管部门
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
						for (TSysOrg u : org) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", u.getId());
							ot.put("name", u.getName());
							lt.add(ot);
						}
						data.put("所属监管部门", lt);
						
						// 运营状态
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						String yzt[]=new String []{"0","1","2"};
						for (int i = 0; i < yzt.length; i++) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", yzt[i]);
							if(yzt[i].equals("0")){
					        ot.put("name", "运营中");
						}
						if(yzt[i].equals("1")){
							ot.put("name", "已关闭");
						}
						if(yzt[i].equals("2")){
					    	ot.put("name", "已停产");
						}
							lt.add(ot);
						}
						data.put("企业状态", lt);
						// 状态
									lt = new ArrayList<LinkedHashMap<String,Object>>();
									cs = this.queryZtList();
									for (Combobox c : cs) {
										ot = new LinkedHashMap<String, Object>();
										ot.put("id", c.getId());
										ot.put("name", c.getName());
										lt.add(ot);
										}
									data.put("状态", lt);
						
		}else if(lawObjType.equals("09")){
			// 所属行政区
			//管理员看所有
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			ot = new LinkedHashMap<String, Object>();
			for (TDataRegion r : rs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", r.getId());
				ot.put("name", r.getName());
				lt.add(ot);
			}
			data.put("所属行政区", lt);
			//经度，纬度书写格式
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "87,16,12");
			ot.put("name", "例如：东经87度16分12秒,填写值格式参照标识列");
			lt.add(ot);
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "43,11,13");
			ot.put("name", "例如：北纬43度11分13秒,填写值格式参照标识列");
			lt.add(ot);
			data.put("经纬度", lt);
			//底商
			lt = new ArrayList<LinkedHashMap<String,Object>>();
		    ot = new LinkedHashMap<String, Object>();
			ot.put("id", "Y");
			ot.put("name", "是");
			lt.add(ot);
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "N");
			ot.put("name", "否");
			lt.add(ot);
			data.put("底商,住宅,是否正常运行,排油烟管道是否超出楼顶", lt);
			// 所属监管部门
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
			for (TSysOrg u : org) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", u.getId());
				ot.put("name", u.getName());
				lt.add(ot);
			}
			data.put("所属监管部门", lt);
			
			// 运营状态
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			String yzt[]=new String []{"0","1","2"};
			for (int i = 0; i < yzt.length; i++) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", yzt[i]);
				if(yzt[i].equals("0")){
		        ot.put("name", "运营中");
			}
			if(yzt[i].equals("1")){
				ot.put("name", "已关闭");
			}
			if(yzt[i].equals("2")){
		    	ot.put("name", "已停产");
			}
				lt.add(ot);
			}
			data.put("企业状态", lt);
			// 状态
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						cs = this.queryZtList();
						for (Combobox c : cs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", c.getId());
							ot.put("name", c.getName());
							lt.add(ot);
							}
						data.put("状态", lt);
		}else if(lawObjType.equals("10")){
			// 所属行政区
						//管理员看所有
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						ot = new LinkedHashMap<String, Object>();
						for (TDataRegion r : rs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", r.getId());
							ot.put("name", r.getName());
							lt.add(ot);
						}
						data.put("所属行政区", lt);
						//经度，纬度书写格式
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						ot = new LinkedHashMap<String, Object>();
						ot.put("id", "87,16,12");
						ot.put("name", "例如：东经87度16分12秒,填写值格式参照标识列");
						lt.add(ot);
						ot = new LinkedHashMap<String, Object>();
						ot.put("id", "43,11,13");
						ot.put("name", "例如：北纬43度11分13秒,填写值格式参照标识列");
						lt.add(ot);
						data.put("经纬度", lt);
						//底商
						lt = new ArrayList<LinkedHashMap<String,Object>>();
					    ot = new LinkedHashMap<String, Object>();
						ot.put("id", "Y");
						ot.put("name", "是");
						lt.add(ot);
						ot = new LinkedHashMap<String, Object>();
						ot.put("id", "N");
						ot.put("name", "否");
						lt.add(ot);
						data.put("底商,住宅,隔声降噪措施,喷、刷油漆", lt);
						// 所属监管部门
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
						for (TSysOrg u : org) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", u.getId());
							ot.put("name", u.getName());
							lt.add(ot);
						}
						data.put("所属监管部门", lt);
						
						// 运营状态
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						String yzt[]=new String []{"0","1","2"};
						for (int i = 0; i < yzt.length; i++) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", yzt[i]);
							if(yzt[i].equals("0")){
					        ot.put("name", "运营中");
						}
						if(yzt[i].equals("1")){
							ot.put("name", "已关闭");
						}
						if(yzt[i].equals("2")){
					    	ot.put("name", "已停产");
						}
							lt.add(ot);
						}
						data.put("企业状态", lt);
						// 状态
									lt = new ArrayList<LinkedHashMap<String,Object>>();
									cs = this.queryZtList();
									for (Combobox c : cs) {
										ot = new LinkedHashMap<String, Object>();
										ot.put("id", c.getId());
										ot.put("name", c.getName());
										lt.add(ot);
										}
									data.put("状态", lt);	
		}else if(lawObjType.equals("11")){
			// 所属行政区
			//管理员看所有
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			ot = new LinkedHashMap<String, Object>();
			for (TDataRegion r : rs) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", r.getId());
				ot.put("name", r.getName());
				lt.add(ot);
			}
			data.put("所属行政区", lt);
			//经度，纬度书写格式
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "87,16,12");
			ot.put("name", "例如：东经87度16分12秒,填写值格式参照标识列");
			lt.add(ot);
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "43,11,13");
			ot.put("name", "例如：北纬43度11分13秒,填写值格式参照标识列");
			lt.add(ot);
			data.put("经纬度", lt);
			//底商
			lt = new ArrayList<LinkedHashMap<String,Object>>();
		    ot = new LinkedHashMap<String, Object>();
			ot.put("id", "Y");
			ot.put("name", "是");
			lt.add(ot);
			ot = new LinkedHashMap<String, Object>();
			ot.put("id", "N");
			ot.put("name", "否");
			lt.add(ot);
			data.put("底商,住宅", lt);
			// 所属监管部门
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			List<TSysOrg> org = this.dao.find("from TSysOrg where areaid_ = ? and isactive_ = ? ", CtxUtil.getCurUser().getAreaId(), YnEnum.Y.getCode());
			for (TSysOrg u : org) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", u.getId());
				ot.put("name", u.getName());
				lt.add(ot);
			}
			data.put("所属监管部门", lt);
			
			// 运营状态
			lt = new ArrayList<LinkedHashMap<String,Object>>();
			String yzt[]=new String []{"0","1","2"};
			for (int i = 0; i < yzt.length; i++) {
				ot = new LinkedHashMap<String, Object>();
				ot.put("id", yzt[i]);
				if(yzt[i].equals("0")){
		        ot.put("name", "运营中");
			}
			if(yzt[i].equals("1")){
				ot.put("name", "已关闭");
			}
			if(yzt[i].equals("2")){
		    	ot.put("name", "已停产");
			}
				lt.add(ot);
			}
			data.put("企业状态", lt);
			// 状态
						lt = new ArrayList<LinkedHashMap<String,Object>>();
						cs = this.queryZtList();
						for (Combobox c : cs) {
							ot = new LinkedHashMap<String, Object>();
							ot.put("id", c.getId());
							ot.put("name", c.getName());
							lt.add(ot);
							}
						data.put("状态", lt);	
		}
		// 企业生产状态
				lt = new ArrayList<LinkedHashMap<String,Object>>();
				    ot = new LinkedHashMap<String, Object>();
					ot.put("id", "Y");
					ot.put("name", "全年性生产");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "N");
					ot.put("name", "季节性生产");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "例：N,01,02,03");
					ot.put("name", "季节性生产书写格式");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "01");
					ot.put("name", "1月");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "02");
					ot.put("name", "2月");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "03");
					ot.put("name", "3月");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "04");
					ot.put("name", "4月");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "05");
					ot.put("name", "5月");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "06");
					ot.put("name", "6月");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "07");
					ot.put("name", "7月");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "08");
					ot.put("name", "8月");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "09");
					ot.put("name", "9月");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "10");
					ot.put("name", "10月");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "11");
					ot.put("name", "11月");
					lt.add(ot);
					ot = new LinkedHashMap<String, Object>();
					ot.put("id", "12");
					ot.put("name", "12月");
					lt.add(ot);
					data.put("企业生产状态", lt);
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
