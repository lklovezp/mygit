package com.hnjz.app.data.xxgl.lawdoc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.IndexManager;
import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.po.TDataLawdoc;
import com.hnjz.app.data.po.TDataLawdocdir;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.FileUtil;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-17
 * 功能描述：
	执法对象字典manager实现层
 *
 */
@Service("lawDocManager")
public class LawdocManagerImpl extends ManagerImpl implements LawdocManager{
	
	@Autowired
	private CommonManager commonManager;
	
	@Autowired
	private IndexManager     indexManager;
	
	@Override
	public List<LawdocForm> queryNewUploadLawdoc(String pid) {
		List<LawdocForm> result = new ArrayList<LawdocForm>();
		List<TDataLawdoc> list = this.dao.find("from TDataLawdoc d where d.isActive = 'N' and d.dirid = ? order by d.created ",pid);
		Integer maxorder = this.getMaxorder(pid);
		for(TDataLawdoc tDataLawdoc : list){
			LawdocForm lawdoc = new LawdocForm(tDataLawdoc.getId(),tDataLawdoc.getName(),tDataLawdoc.getDirid(),tDataLawdoc.getKeywords(),tDataLawdoc.getFileid(),tDataLawdoc.getIsActive(),++maxorder);
			result.add(lawdoc);
		}
		return result;
	}

	@Override
	public Integer getMaxorder(String pid) {
		List<Object> list = this.dao.findBySql("select max(orderby_) from t_data_Lawdoc d where d.dirid_ = ?",pid);
		return list.get(0)==null?0:((BigDecimal)list.get(0)).intValue();
	}

	@Override
	public void saveLawdoc(String pid, String uuid,String data) {
		try {
			JSONObject obj = new JSONObject(data);
			JSONArray arr = obj.getJSONArray("rows");
			for(int i=0;i<arr.length();i++){
				JSONObject jsonobj = arr.getJSONObject(i);
				TDataLawdoc tDataLawdoc = (TDataLawdoc) this.get(TDataLawdoc.class, jsonobj.getString("id"));
				tDataLawdoc.setDirid(pid);
				tDataLawdoc.setName(jsonobj.has("name")?jsonobj.getString("name"):"");
				tDataLawdoc.setKeywords(jsonobj.has("keywords")?jsonobj.getString("keywords"):"");
				//tDataLawdoc.setOrderby(jsonobj.has("orderby")?jsonobj.getInt("orderby"):0);
				tDataLawdoc.setIsActive("Y");
				this.save(tDataLawdoc);
			}
			this.removeLawdocByPid(uuid);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeLawdoc(String id) {
		TDataLawdoc tDataLawdoc = (TDataLawdoc) this.get(TDataLawdoc.class, id);
		//删除文件和文件表信息
		commonManager.removeFile(tDataLawdoc.getFileid());
		//删除执法文件表信息
		this.remove(tDataLawdoc);
	}
	
	@Override
	public void removeLawdocByPid(String pid) {
		//删除多余文件
		List<TDataLawdoc> list = this.dao.find("from TDataLawdoc d where d.dirid = ? ", pid);
		if (list.size() > 0) {
			for (TDataLawdoc doc : list) {
				this.removeLawdoc(doc.getId());
			}
		}
	}

	@Override
	public FyWebResult queryLawdocList(String pid, String title, String keywords,String canDel, String page, String pageSize) {
		List<String> ext = new ArrayList<String>();
		ext.add(".jpg");
		ext.add(".png");
		ext.add(".bmp");
		ext.add(".jpeg");
		ext.add(".doc");
		ext.add(".docx");
		ext.add(".txt");
		//添加离线版的标识判断（可以查询对应的sql语句）
		String biaoshi = indexManager.sysVer;
		StringBuffer sql = new StringBuffer(" select d.id_,d.name_,d.keywords_, ");
		if("0".equals(biaoshi)){
			sql.append(" d.dirid_");
		}else{
			sql.append(" (SELECT LISTAGG(NAME_, '/') WITHIN GROUP(ORDER BY ROWNUM DESC) FROM T_DATA_lawdocdir  START WITH ID_ = d.DIRID_ CONNECT BY PRIOR PID_ = ID_) AS DirPATH_");
		}
		sql.append(" ,d.fileid_,f.name_ filename,f.size_");
		sql.append(" from t_data_lawdoc d left join t_data_lawdocdir r on d.dirid_ = r.id_  ");
		sql.append(" left join t_data_file f on d.fileid_ = f.id_ ");
		sql.append(" where d.isactive_ = 'Y' ");
		Map<String, Object> condition = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(pid)){
			sql.append(" and d.dirid_ = :pid ");
			condition.put("pid", pid);
		}
		if(StringUtils.isNotBlank(title)){
			sql.append(" and d.name_ like :name ");
			condition.put("name", "%"+title+"%");
		}
		if(StringUtils.isNotBlank(keywords)){
			sql.append(" and d.keywords_ like :keywords ");
			condition.put("keywords", "%"+keywords+"%");
		}
		sql.append(" order by r.orderby_,d.updated_ desc");
		FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("name", String.valueOf(obj[1]));
			dataObject.put("keywords", String.valueOf(obj[2]));
			if("0".equals(biaoshi)){
				dataObject.put("dirpath", this.getmysqlDir(String.valueOf(obj[3])));
			}else{
				dataObject.put("dirpath", String.valueOf(obj[3]));
			}
			dataObject.put("fileid", String.valueOf(obj[4]));
			String operate = "";
			if (obj[5]!=null && String.valueOf(obj[5]).lastIndexOf(".")!=-1 &&  ext.contains(String.valueOf(obj[5]).substring(String.valueOf(obj[5]).lastIndexOf("."), String.valueOf(obj[5]).length()).toLowerCase())) {
				operate = "<a class='b-link' onclick='review(this)' id='"+String.valueOf(obj[4])+","+String.valueOf(obj[6])+"' >预览</a>";
			}
			if (canDel==null || canDel.equals("Y")) {
				String operatee="<a class='b-link' onclick='edit(this)' id='"+String.valueOf(obj[0])+"' >编辑</a>";
				operate += operatee+OperateUtil.getDloadOperate(String.valueOf(obj[4]))+OperateUtil.getDeleteOperate(String.valueOf(obj[0]));
			} else {
				operate += OperateUtil.getDloadOperate(String.valueOf(obj[4]));
			}
			dataObject.put("operate", operate);
			rows.add(dataObject);
		}
		return fy;
	}

	@Override
	public LawdocForm getLawdocInfo(String id) {
		TDataLawdoc tDataLawdoc = (TDataLawdoc) this.get(TDataLawdoc.class, id);
		TDataFile tDataFile = (TDataFile) this.get(TDataFile.class, tDataLawdoc.getFileid());
		LawdocForm lawdoc = new LawdocForm(tDataLawdoc.getId(),tDataLawdoc.getName(),tDataLawdoc.getDirid(),tDataLawdoc.getKeywords(),tDataLawdoc.getFileid(),tDataFile.getName(),tDataLawdoc.getIsActive(),tDataLawdoc.getOrderby());
		return lawdoc;
	}

	@Override
	public void updateLawdoc(LawdocForm lawdocForm, MultipartFile file) {
		TDataLawdoc tDataLawdoc = (TDataLawdoc) this.get(TDataLawdoc.class, lawdocForm.getId());
		tDataLawdoc.setName(lawdocForm.getName());
		tDataLawdoc.setDirid(lawdocForm.getDirid());
		tDataLawdoc.setKeywords(lawdocForm.getKeywords());
		//tDataLawdoc.setOrderby(lawdocForm.getOrderby());
		if(file!=null && file.getSize()>0){
			TDataFile tDataFile = new TDataFile();
			tDataFile = commonManager.saveFile(tDataFile, file, tDataLawdoc.getId(), FileTypeEnums.ZFWJGLZFWJ.getCode(), UploadFileType.ZFWJ);
			tDataLawdoc.setFileid(tDataFile.getId());
			this.save(tDataLawdoc);
			commonManager.removeFile(lawdocForm.getFileid());
		}else{
			this.save(tDataLawdoc);
		}
	}

	@Override
	public FyWebResult queryLawdocListForMobile(String pid, String title, String keywords, String page, String pageSize) {
		StringBuffer sql = new StringBuffer(" select d.id_,d.name_,d.keywords_, ");
		sql.append(" (SELECT LISTAGG(NAME_, '/') WITHIN GROUP(ORDER BY ROWNUM DESC) FROM T_DATA_lawdocdir  START WITH ID_ = d.DIRID_ CONNECT BY PRIOR PID_ = ID_) AS DirPATH_");
		sql.append(" ,d.fileid_,f.name_ filename,f.size_ filesize ");
		sql.append(" from t_data_lawdoc d  ");
		sql.append(" left join t_data_lawdocdir r on d.dirid_ = r.id_ ");
		sql.append(" left join t_data_file f on d.id_ = f.pid_  ");
		sql.append(" where d.isactive_ = 'Y' ");
		Map<String, Object> condition = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(pid)){
			sql.append(" and d.dirid_ = :pid ");
			condition.put("pid", pid);
		}
		if(StringUtils.isNotBlank(title)){
			sql.append(" and d.name_ like :name ");
			condition.put("name", "%"+title+"%");
		}
		if(StringUtils.isNotBlank(keywords)){
			sql.append(" and d.keywords_ like :keywords ");
			condition.put("keywords", "%"+keywords+"%");
		}
		sql.append(" order by r.orderby_,d.orderby_");
		FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("title", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("keywords", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("dirpath", obj[3]==null?"":String.valueOf(obj[3]));
			dataObject.put("url", obj[4]==null?"":"/download.mo?id="+String.valueOf(obj[4]));
			if(obj[5] != null && FileUtil.isImage(String.valueOf(obj[5]))){
				dataObject.put("imageUrl", obj[4] == null ? "" : "/downThumbnailImage.mo?id="+String.valueOf(obj[4]));
			}
			dataObject.put("fileId", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("filename", obj[5]==null?"":String.valueOf(obj[5]));
			Long filesize = Long.valueOf(obj[6]==null?"0":String.valueOf(obj[6]));
			dataObject.put("filesize", FileUtil.sizeFormat(filesize));
			rows.add(dataObject);
		}
		return fy;
	}
	

	@Override
	public void saveChooseeLawdoc(String fileType, String applyId, String fileid) {
		fileType = FileTypeEnums.getTypeByEnumName(fileType);
		String[] fileidStr = fileid.split(",");
		try {
			for(String str : fileidStr){
				TDataFile oldFile = (TDataFile) this.get(TDataFile.class, str);
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				File file = new File(FileUpDownUtil.path.concat(oldFile.getRelativepath() + File.separator + oldFile.getOsname()));
				FileUpDownUtil.copyFile(new FileInputStream(file), uuid, UploadFileType.WORK.getPath(), "");
				TDataFile newFile = new TDataFile(applyId,uuid,oldFile.getName(),oldFile.getSize(),fileType,UploadFileType.WORK.getPath());
				this.save(newFile);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public FyWebResult queryLawdocListByTasktype(String tasktype, String title, String keywords, String page, String pageSize) {
		StringBuffer sql = new StringBuffer(" select d.id_,d.name_,d.keywords_, ");
		sql.append(" (SELECT LISTAGG(NAME_, '/') WITHIN GROUP(ORDER BY ROWNUM DESC) FROM T_DATA_lawdocdir  START WITH ID_ = d.DIRID_ CONNECT BY PRIOR PID_ = ID_) AS DirPATH_");
		sql.append(" ,d.fileid_,f.name_ filename,f.size_ filesize ");
		sql.append(" from t_data_lawdoc d  ");
		sql.append(" left join t_data_lawdocdir r on d.dirid_ = r.id_ ");
		sql.append(" left join t_data_file f on d.id_ = f.pid_  ");
		sql.append(" where d.isactive_ = 'Y' ");
		Map<String, Object> condition = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(tasktype)){
			sql.append(" and d.dirid_ in (select DIRID_ from T_Data_Dirandtasktype where TASKTYPEID_ =:tasktype)  ");
			condition.put("tasktype", tasktype);
		}
		if(StringUtils.isNotBlank(title)){
			sql.append(" and d.name_ like :name ");
			condition.put("name", "%"+title+"%");
		}
		if(StringUtils.isNotBlank(keywords)){
			sql.append(" and d.keywords_ like :keywords ");
			condition.put("keywords", "%"+keywords+"%");
		}
		sql.append(" order by r.orderby_,d.orderby_");
		FyResult pos = this.dao.find(sql.toString(), Integer.parseInt(page), pageSize==null?0:Integer.parseInt(pageSize), condition);
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		fy.setRows(rows);
		List<Object[]> listLawobj = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] obj : listLawobj) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", String.valueOf(obj[0]));
			dataObject.put("title", obj[1]==null?"":String.valueOf(obj[1]));
			dataObject.put("keywords", obj[2]==null?"":String.valueOf(obj[2]));
			dataObject.put("dirpath", obj[3]==null?"":String.valueOf(obj[3]));
			dataObject.put("url", obj[4]==null?"":"/download.mo?id="+String.valueOf(obj[4]));
			if(obj[5] != null && FileUtil.isImage(String.valueOf(obj[5]))){
				dataObject.put("imageUrl", obj[4] == null ? "" : "/downThumbnailImage.mo?id="+String.valueOf(obj[4]));
			}
			dataObject.put("fileId", obj[4]==null?"":String.valueOf(obj[4]));
			dataObject.put("filename", obj[5]==null?"":String.valueOf(obj[5]));
			Long filesize = Long.valueOf(obj[6]==null?"0":String.valueOf(obj[6]));
			dataObject.put("filesize", FileUtil.sizeFormat(filesize));
			rows.add(dataObject);
		}
		return fy;
	}
	
	@Override
	public String getmysqlDir(String dirid){
		String str = "";
		List<TDataLawdocdir> lawobjDocdir = this.dao.find("from TDataLawdocdir where id_ = ?", dirid);
		//先默认只有三级目录
		if(lawobjDocdir.size() == 1){
			if(StringUtils.isNotBlank(lawobjDocdir.get(0).getPid())){
				List<TDataLawdocdir> lawobjDocdir1 = this.dao.find("from TDataLawdocdir where id_ = ?", lawobjDocdir.get(0).getPid());
				if(StringUtils.isBlank(lawobjDocdir1.get(0).getPid())){
					str =  lawobjDocdir1.get(0).getName()+ "/" + lawobjDocdir.get(0).getName();
				}else{
					List<TDataLawdocdir> lawobjDocdir2 = this.dao.find("from TDataLawdocdir where id_ = ?", lawobjDocdir1.get(0).getPid());
					if(StringUtils.isBlank(lawobjDocdir2.get(0).getPid())){
						str =  lawobjDocdir2.get(0).getName()+ "/" + lawobjDocdir1.get(0).getName()+ "/" + lawobjDocdir.get(0).getName();
					}else{
						List<TDataLawdocdir> lawobjDocdir3 = this.dao.find("from TDataLawdocdir where id_ = ?", lawobjDocdir1.get(0).getPid());
						if(StringUtils.isBlank(lawobjDocdir3.get(0).getPid())){
							str =  lawobjDocdir3.get(0).getName()+ "/" +lawobjDocdir2.get(0).getName()+ "/" + lawobjDocdir1.get(0).getName()+ "/" + lawobjDocdir.get(0).getName();
						}
					}
				}
			}else{
				str = lawobjDocdir.get(0).getName();
			}
		}else{
			str = lawobjDocdir.get(0).getName();
		}
		return str;
	}
	
}
