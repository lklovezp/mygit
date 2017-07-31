package com.hnjz.app.work.wszz;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.data.po.TBizJdssdhz;
import com.hnjz.app.data.po.TBizTzgzsdhz;
import com.hnjz.app.data.po.TBizXqgztz;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.PoiUtil;
import com.hnjz.common.util.excel.ExcelUtil;
import com.hnjz.sys.po.TSysOrg;
@Service("wszzManagerImpl")
public class WszzManagerImpl extends ManagerImpl implements WszzManager,ServletContextAware{
	private ServletContext servletContext;
	@Autowired
	private CommonManager commonManager;
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	@Override
	public TSysOrg getOrgbyUser(String userId)throws Exception {
		TSysOrg org = null;

		String hsql = "select r from TSysUserOrg ur,TSysOrg r where r.isActive = 'Y' and ur.org.id = r.id and ur.user.id = ?";
		List<TSysOrg> re = this.dao.find(hsql, userId);
		if (re.isEmpty()) {
			return null;
		} else {
			org = re.get(0);
		}
		return org;
	}

	@Override
	public HjwfxwtzForm findHjwfxwtzFormById(String taskId,String taskTypeId)
			throws Exception {
		HjwfxwtzForm hj=new HjwfxwtzForm();
		List<TBizXqgztz> list=this.dao.find("from TBizXqgztz t where t.isActive='Y' and t.taskId=? and t.taskTypeId=? ", taskId,taskTypeId);
		
		if(list.size()>0){
			TBizXqgztz tx=list.get(0);
			hj.setId(tx.getId());
			hj.setCode(tx.getCode());
			hj.setContent(tx.getContent());
			hj.setCorpName(tx.getCorpName());
			hj.setCreater(CtxUtil.getCurUser());
			hj.setCreateTime(new Date());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
			if(tx.getDcDate()!=null){
				String str=sdf.format(tx.getDcDate());
				hj.setDcDate(str);
			}
			
			hj.setIsActive(tx.getIsActive());
			if(tx.getQxDate()!=null){
			String qxstr=sdf.format(tx.getQxDate());
			hj.setQxDate(qxstr);
			}
			hj.setRef(tx.getRef());
			hj.setRules(tx.getRules());
			hj.setShortName(tx.getShortName());
			hj.setTaskId(tx.getTaskId());
			hj.setTaskTypeId(tx.getTaskTypeId());
			hj.setTitle(tx.getTitle());
			hj.setUpdateby(CtxUtil.getCurUser());
			hj.setUpdateTime(new Date());
			hj.setXqqContent(tx.getXqqContent());
			return hj;
		}else{
			return null;	
		}
		
	}

	@Override
	public void saveHjwfxwtzFormById(HjwfxwtzForm hjwfxwtzForm)
			throws Exception {
		if(StringUtils.isNotBlank(hjwfxwtzForm.getId())){
			TBizXqgztz tx=(TBizXqgztz)this.get(TBizXqgztz.class, hjwfxwtzForm.getId());
			tx.setCode(hjwfxwtzForm.getCode());
			tx.setContent(hjwfxwtzForm.getContent());
			tx.setCorpName(hjwfxwtzForm.getCorpName());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//Сд��mm��ʾ���Ƿ���  
			String dstr=hjwfxwtzForm.getDcDate();
			if(!dstr.equals("")){
				Date date=sdf.parse(dstr);
				tx.setDcDate(date);
			}			
			tx.setIsActive("Y");
			
			SimpleDateFormat qxsdf=new SimpleDateFormat("yyyy-MM-dd");//Сд��mm��ʾ���Ƿ���  
			String qxdstr=hjwfxwtzForm.getQxDate();
			if(!qxdstr.equals("")){
			Date qxdate=sdf.parse(qxdstr);
			tx.setQxDate(qxdate);
			}
			tx.setRef(hjwfxwtzForm.getRef());
			tx.setRules(hjwfxwtzForm.getRules());
			tx.setShortName(hjwfxwtzForm.getShortName());
			tx.setTaskId(hjwfxwtzForm.getTaskId());
			tx.setTaskTypeId(hjwfxwtzForm.getTaskTypeId());
			tx.setTitle(hjwfxwtzForm.getTitle());
			tx.setUpdateby(CtxUtil.getCurUser());
			tx.setUpdateTime(new Date());
			tx.setXqqContent(hjwfxwtzForm.getXqqContent());
			this.dao.save(tx);
		}else{
			TBizXqgztz tx=new TBizXqgztz();
			tx.setCode(hjwfxwtzForm.getCode());
			tx.setContent(hjwfxwtzForm.getContent());
			tx.setCorpName(hjwfxwtzForm.getCorpName());
			tx.setCreater(CtxUtil.getCurUser());
			tx.setCreateTime(new Date());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//Сд��mm��ʾ���Ƿ���  
			String dstr=hjwfxwtzForm.getDcDate();  
			if(!dstr.equals("")){
			Date date=sdf.parse(dstr);
			tx.setDcDate(date);
			}
			tx.setIsActive("Y");
			SimpleDateFormat qxsdf=new SimpleDateFormat("yyyy-MM-dd");//Сд��mm��ʾ���Ƿ���  
			String qxdstr=hjwfxwtzForm.getQxDate();  
			if(!qxdstr.equals("")){
			Date qxdate=qxsdf.parse(qxdstr);
			tx.setQxDate(qxdate);
			}
			tx.setRef(hjwfxwtzForm.getRef());
			tx.setRules(hjwfxwtzForm.getRules());
			tx.setShortName(hjwfxwtzForm.getShortName());
			tx.setTaskId(hjwfxwtzForm.getTaskId());
			tx.setTaskTypeId(hjwfxwtzForm.getTaskTypeId());
			tx.setTitle(hjwfxwtzForm.getTitle());
			tx.setUpdateby(CtxUtil.getCurUser());
			tx.setUpdateTime(new Date());
			tx.setXqqContent(hjwfxwtzForm.getXqqContent());
			this.dao.save(tx);
		}
		
	}

	@Override
	public HashMap<String, String> buildTzd(HjwfxwtzForm hjwfxwtzForm) throws Exception {
		HashMap<String, String> ret = new HashMap<String, String>();
		// ����word���õ�����
		Map<String, String> paraMap = new HashMap<String, String>();
				// ����
				paraMap.put("$title$", hjwfxwtzForm.getTitle());
				
				// ����
				paraMap.put("$shortName$", hjwfxwtzForm.getShortName());

				// ��
				paraMap.put("$code$", hjwfxwtzForm.getCode());
				// ��֪ͨ��λ����
				paraMap.put("$corpName$", hjwfxwtzForm.getCorpName());
				// ���ʱ��
				if(null==hjwfxwtzForm.getDcDate()){
					//��
					paraMap.put("$jcn$", "");
					// ��
					paraMap.put("$jcy$", "");
					// ��
					paraMap.put("$jcr$", "");
				}else{
					String[] dcDate=hjwfxwtzForm.getDcDate().split("-");
					//��
					paraMap.put("$jcn$", dcDate[0]);
					// ��
					paraMap.put("$jcy$", dcDate[1]);
					// ��
					paraMap.put("$jcr$", dcDate[2]);
				}
			
				// ���� 
				paraMap.put("$content$", hjwfxwtzForm.getContent());
				// Υ���涨
				paraMap.put("$rules$", hjwfxwtzForm.getRules());
				// ����ʱ��
				if(null==hjwfxwtzForm.getQxDate()){
					paraMap.put("$xqn$","");
					paraMap.put("$xqy$", "");
					paraMap.put("$xqr$", "");
				}else{
					String[] qxDate=hjwfxwtzForm.getQxDate().split("-");
					paraMap.put("$xqn$",qxDate[0]);
					paraMap.put("$xqy$", qxDate[1]);
					paraMap.put("$xqr$", qxDate[2]);
				}
				paraMap.put("$xqqContent$", hjwfxwtzForm.getXqqContent());
				paraMap.put("$ref$", hjwfxwtzForm.getRef());

				String classPath = this.getClass().getResource("").getPath();
				classPath = java.net.URLDecoder.decode(classPath, "utf-8");
				// ģ��·��
				String templatePath = null;
				// ���ģ��·��
				templatePath = classPath.substring(0, classPath.lastIndexOf("/WEB-INF/")) + "//excel//xqtz//hjwfxwxqgztzmb.doc";
				
				// ���ɵ�·��
				String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath());
				String filePath = PoiUtil.createWord(templatePath, dirPath, paraMap);
				File file = new File(filePath);
				// ��ɾ����word��
//				List<TDataFile> listFile = this.find("from TDataFile f where f.pid = ? and f.type=? ", hjwfxwtzForm.getTaskId(),"1714");
//				if(listFile.size()>0){
//					for(TDataFile filePo : listFile){
//						// ɾ���ļ�
//						new File(dirPath + "//" + filePo.getOsname()).delete();
//						this.dao.remove(TDataFile.class, filePo.getId());
//					}
//				}
			
				// �������ݿ������
				String oName = null;
		
				//log.info("��������"+testname);
				oName ="����Υ����Ϊ���ڸ���֪ͨ.doc";
				//31�����ֵ��enums���������
				TDataFile filePo = commonManager.saveFile(new TDataFile(), file, oName, hjwfxwtzForm.getTaskId(), "1714", UploadFileType.WORK.getPath());
				ret.put("id", filePo.getId());
				ret.put("name", filePo.getName());
		return ret;
	}

	@Override
	public HashMap<String, String> buildJdssdhz(JdssdhzForm jdssdhzForm)
			throws Exception {
		HashMap<String, String> ret = new HashMap<String, String>();

		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		//��ѯ����
		JdssdhzForm jExecl = new JdssdhzForm();
		List jList = new ArrayList();
		jExecl.setAddress(jdssdhzForm.getAddress());
		//ʦ�������֡�2016��XX��
		String code="ʦ�������֡�"+jdssdhzForm.getTimeName()+"��"+jdssdhzForm.getCode()+"��";
		jExecl.setCode(code);
		jExecl.setName(jdssdhzForm.getName());
		//����ʦ�����������ʹ��֤
		jExecl.setTitle(jdssdhzForm.getTitle()+"�ʹ��֤");
		jExecl.setType(jdssdhzForm.getType());
		jExecl.setUserName(jdssdhzForm.getUserName());
		jList.add(jExecl);
		map.put("jdssdhzFormQuery", jList);
		String realPath = servletContext.getRealPath(File.separator);
		//try {
			File file = ExcelUtil.setValue(
					new File(FileUpDownUtil.path.concat(
							UploadFileType.WORK.getPath()).concat(
							UUID.randomUUID().toString().replaceAll("-", ""))),
					new File(realPath
							+ "excel/xqtz/jdssdhz.xls"),
					new File(realPath
							+ "excel/xqtz/jdssdhz.xml"), map,
					false);
			String classPath = this.getClass().getResource("").getPath();
			classPath = java.net.URLDecoder.decode(classPath, "utf-8");
			// ���ɵ�·��
			String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath());
			// ��ɾ����word��
//			List<TDataFile> listFile = this.find("from TDataFile f where f.pid = ? and f.type=? ", jdssdhzForm.getTaskId(),"1715");
//			if(listFile.size()>0){
//				for(TDataFile filePo : listFile){
//					// ɾ���ļ�
//					new File(dirPath + "//" + filePo.getOsname()).delete();
//					this.dao.remove(TDataFile.class, filePo.getId());
//				}
//			}
		
			// �������ݿ������
			String oName = null;
	
			//log.info("��������"+testname);
			oName ="���������������ʹ��ִ.xls";
			//31�����ֵ��enums���������
			TDataFile filePo = commonManager.saveFile(new TDataFile(), file, oName, jdssdhzForm.getTaskId(), "1715", UploadFileType.WORK.getPath());
			ret.put("id", filePo.getId());
			ret.put("name", filePo.getName());
	        return ret;
	}
	@Override
	public void saveJdssdhzFormById(JdssdhzForm jdssdhzForm) throws Exception {
		if(StringUtils.isNotBlank(jdssdhzForm.getId())){
			TBizJdssdhz tj=(TBizJdssdhz) this.get(TBizJdssdhz.class, jdssdhzForm.getId());
			tj.setAddress(jdssdhzForm.getAddress());
			tj.setCode(jdssdhzForm.getCode());
			tj.setIsActive("Y");
			tj.setName(jdssdhzForm.getName());
			tj.setTaskId(jdssdhzForm.getTaskId());
			tj.setTaskTypeId(jdssdhzForm.getTaskTypeId());
			tj.setTimeName(jdssdhzForm.getTimeName());
			tj.setTitle(jdssdhzForm.getTitle());
			tj.setType(jdssdhzForm.getType());
			tj.setUpdateby(CtxUtil.getCurUser());
			tj.setUpdateTime(new Date());
			tj.setUserName(jdssdhzForm.getUserName());
			this.dao.save(tj);
		}else{
			TBizJdssdhz tj=new TBizJdssdhz();
			tj.setAddress(jdssdhzForm.getAddress());
			tj.setCode(jdssdhzForm.getCode());
			tj.setCreater(CtxUtil.getCurUser());
			tj.setCreateTime(new Date());
			tj.setIsActive("Y");
			tj.setName(jdssdhzForm.getName());
			tj.setTaskId(jdssdhzForm.getTaskId());
			tj.setTaskTypeId(jdssdhzForm.getTaskTypeId());
			tj.setTimeName(jdssdhzForm.getTimeName());
			tj.setTitle(jdssdhzForm.getTitle());
			tj.setType(jdssdhzForm.getType());
			tj.setUpdateby(CtxUtil.getCurUser());
			tj.setUpdateTime(new Date());
			tj.setUserName(jdssdhzForm.getUserName());
			this.dao.save(tj);
			
		}
	}

	@Override
	public JdssdhzForm findJdssdhzFormById(String taskId, String taskTypeId)
			throws Exception {
		JdssdhzForm hj=new JdssdhzForm();
		List<TBizJdssdhz> list=this.dao.find("from TBizJdssdhz t where t.isActive='Y' and t.taskId=? and t.taskTypeId=? ", taskId,taskTypeId);
		
		if(list.size()>0){
			TBizJdssdhz tx=list.get(0);
			hj.setId(tx.getId());
			hj.setCode(tx.getCode());
			hj.setTimeName(tx.getTimeName());
			hj.setAddress(tx.getAddress());
			hj.setName(tx.getName());
			hj.setType(tx.getType());
			hj.setUserName(tx.getUserName());
			hj.setIsActive(tx.getIsActive());
			hj.setTaskId(tx.getTaskId());
			hj.setTaskTypeId(tx.getTaskTypeId());
			hj.setTitle(tx.getTitle());
			hj.setUpdateby(CtxUtil.getCurUser());
			hj.setUpdateTime(new Date());
			return hj;
		}else{
			return null;	
		}
		
	}
	@Override
	public HashMap<String, String> buildTzgzssdhz(TzgzssdhzForm tzgzssdhzForm) throws Exception {
		HashMap<String, String> ret = new HashMap<String, String>();

		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		//��ѯ����
		TzgzssdhzForm jExecl = new TzgzssdhzForm();
		List jList = new ArrayList();
		jExecl.setAddress(tzgzssdhzForm.getAddress());
		//ʦ�������֡�2016��XX��
		String code="ʦ�������֡�"+tzgzssdhzForm.getTimeName()+"��"+tzgzssdhzForm.getCode()+"��";
		jExecl.setCode(code);
		jExecl.setName(tzgzssdhzForm.getName());
		//����ʦ�����������ʹ��֤
		jExecl.setTitle(tzgzssdhzForm.getTitle()+"�ʹ��֤");
		jExecl.setType(tzgzssdhzForm.getType());
		jExecl.setUserName(tzgzssdhzForm.getUserName());
		jList.add(jExecl);
		map.put("tzgzssdhzFormQuery", jList);
		String realPath = servletContext.getRealPath(File.separator);
		//try {
			File file = ExcelUtil.setValue(
					new File(FileUpDownUtil.path.concat(
							UploadFileType.WORK.getPath()).concat(
							UUID.randomUUID().toString().replaceAll("-", ""))),
					new File(realPath
							+ "excel/xqtz/tzgzssdhz.xls"),
					new File(realPath
							+ "excel/xqtz/tzgzssdhz.xml"), map,
					false);
			String classPath = this.getClass().getResource("").getPath();
			classPath = java.net.URLDecoder.decode(classPath, "utf-8");
			// ���ɵ�·��
			String dirPath = FileUpDownUtil.path.concat(UploadFileType.WORK.getPath());
//			// ��ɾ����word��
//			List<TDataFile> listFile = this.find("from TDataFile f where f.pid = ? and f.type=? ", tzgzssdhzForm.getTaskId(),"1716");
//			if(listFile.size()>0){
//				for(TDataFile filePo : listFile){
//					// ɾ���ļ�
//					new File(dirPath + "//" + filePo.getOsname()).delete();
//					this.dao.remove(TDataFile.class, filePo.getId());
//				}
//			}
		
			// �������ݿ������
			String oName = null;
	
			//log.info("��������"+testname);
			oName ="����֤����֪���ʹ��֤.xls";
			//31�����ֵ��enums���������
			TDataFile filePo = commonManager.saveFile(new TDataFile(), file, oName, tzgzssdhzForm.getTaskId(), "1716", UploadFileType.WORK.getPath());
			ret.put("id", filePo.getId());
			ret.put("name", filePo.getName());
	        return ret;
	}
	@Override
	public void saveTzgzssdhzFormById(TzgzssdhzForm tzgzssdhzForm)
			throws Exception {
		if(StringUtils.isNotBlank(tzgzssdhzForm.getId())){
			TBizTzgzsdhz tj=(TBizTzgzsdhz) this.get(TBizTzgzsdhz.class, tzgzssdhzForm.getId());
			tj.setAddress(tzgzssdhzForm.getAddress());
			tj.setCode(tzgzssdhzForm.getCode());
			tj.setIsActive("Y");
			tj.setName(tzgzssdhzForm.getName());
			tj.setTaskId(tzgzssdhzForm.getTaskId());
			tj.setTaskTypeId(tzgzssdhzForm.getTaskTypeId());
			tj.setTimeName(tzgzssdhzForm.getTimeName());
			tj.setTitle(tzgzssdhzForm.getTitle());
			tj.setType(tzgzssdhzForm.getType());
			tj.setUpdateby(CtxUtil.getCurUser());
			tj.setUpdateTime(new Date());
			tj.setUserName(tzgzssdhzForm.getUserName());
			this.dao.save(tj);
		}else{
			TBizTzgzsdhz tj=new TBizTzgzsdhz();
			tj.setAddress(tzgzssdhzForm.getAddress());
			tj.setCode(tzgzssdhzForm.getCode());
			tj.setCreater(CtxUtil.getCurUser());
			tj.setCreateTime(new Date());
			tj.setIsActive("Y");
			tj.setName(tzgzssdhzForm.getName());
			tj.setTaskId(tzgzssdhzForm.getTaskId());
			tj.setTaskTypeId(tzgzssdhzForm.getTaskTypeId());
			tj.setTimeName(tzgzssdhzForm.getTimeName());
			tj.setTitle(tzgzssdhzForm.getTitle());
			tj.setType(tzgzssdhzForm.getType());
			tj.setUpdateby(CtxUtil.getCurUser());
			tj.setUpdateTime(new Date());
			tj.setUserName(tzgzssdhzForm.getUserName());
			this.dao.save(tj);
			
		}
		
	}
	@Override
	public TzgzssdhzForm findTzgzssdhzFormById(String taskId, String taskTypeId)
			throws Exception {
		TzgzssdhzForm td=new TzgzssdhzForm();
		List<TBizTzgzsdhz> list=this.dao.find("from TBizTzgzsdhz t where t.isActive='Y' and t.taskId=? and t.taskTypeId=? ", taskId,taskTypeId);
		
		if(list.size()>0){
			TBizTzgzsdhz tx=list.get(0);
			td.setId(tx.getId());
			td.setCode(tx.getCode());
			td.setTimeName(tx.getTimeName());
			td.setAddress(tx.getAddress());
			td.setName(tx.getName());
			td.setType(tx.getType());
			td.setUserName(tx.getUserName());
			td.setIsActive(tx.getIsActive());
			td.setTaskId(tx.getTaskId());
			td.setTaskTypeId(tx.getTaskTypeId());
			td.setTitle(tx.getTitle());
			td.setUpdateby(CtxUtil.getCurUser());
			td.setUpdateTime(new Date());
			return td;
		}else{
			return null;	
		}
	}

}