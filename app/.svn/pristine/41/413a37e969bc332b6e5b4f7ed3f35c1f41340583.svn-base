/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.configchecktemplate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.data.po.TDataLawobjtypetasktype;
import com.hnjz.app.data.po.TDataTasktype;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.dic.DicManager;
import com.hnjz.sys.dic.DicTypeEnum;

/**
 * 版本管理的manager
 * 
 * @author wumi
 * @config $Id: ConfigManagerImpl.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
@Service("configManagerImpl")
public class ConfigManagerImpl extends ManagerImpl implements
		ConfigManager {
	/** 日志 */
	private static final Log log = LogFactory
			.getLog(ConfigManagerImpl.class);
	@Autowired
	private CommonManager commonManager;
	
	@Autowired
	private DicManager dicManager;
	
	@Override
	public FyWebResult queryConfig(String tasktypeid, String lawobjtype, String isexecchecklist, String page, String pageSize) {
		QueryCondition data = new QueryCondition();
		if (!StringUtil.isNotBlank(pageSize)){
			pageSize = "20";
		}
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append("select a.id_, b.name_ as tasktypeid, c.name_ as lawobjtype, a.is_exec_checklist from T_DATA_LAWOBJTYPETASKTYPE a left join t_Data_Tasktype b on a.tasktypeid_ = b.code_ left join t_data_lawobjtype c on a.lawobjtype_ = c.id_ where b.isActive_ = 'Y' ");
		if (StringUtils.isNotBlank(tasktypeid)) {
			sql.append(" and a.tasktypeid_ = :tasktypeid");
			data.put("tasktypeid", tasktypeid);
		}
		if (StringUtils.isNotBlank(lawobjtype)) {
			sql.append(" and a.lawobjtype_ = :lawobjtype");
			data.put("lawobjtype", lawobjtype);
		}
		if (StringUtils.isNotBlank(isexecchecklist)) {
			sql.append(" and a.IS_EXEC_CHECKLIST = :isexecchecklist");
			data.put("isexecchecklist", isexecchecklist);
		}
		FyResult pos = dao.find(sql.toString(), Integer.parseInt(page), Integer.parseInt(pageSize), data.getCanshu());
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<Object[]> configs = pos.getRe();
		Map<String, String> dataObject = null;
		for (Object[] ele : configs) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele[0].toString());
			dataObject.put("tasktypeid", ele[1].toString());
			dataObject.put("lawobjtype", ele[2].toString());
			dataObject.put("isexecchecklist", ele[3]==null?"":dicManager.getNameByTypeAndCode(DicTypeEnum.ZXFS.getCode(), ele[3].toString()));
			dataObject.put("operate", OperateUtil.getOperate(ele[0].toString()));
			rows.add(dataObject);
		}
		fy.setRows(rows);
		log.debug(fy);
		return fy;
	}

	@Override
	public void configInfo(ConfigForm frm) {
		TDataLawobjtypetasktype po = (TDataLawobjtypetasktype) this.dao.get(TDataLawobjtypetasktype.class, frm.getId());
		List<TDataFile> filePo = this.dao.find("from TDataFile where pid = ?", frm.getId());;
		
		TDataTasktype tasktype = (TDataTasktype) this.dao.find("from TDataTasktype where code = ?", po.getTasktypeid()).get(0);
		TDataLawobjtype dic = (TDataLawobjtype) this.dao.find("from TDataLawobjtype where id=?", po.getLawobjtype()).get(0);
		frm.setId(po.getId());
		frm.setIsexecchecklist(po.getIsexecchecklist());
		frm.setTasktypeid(tasktype.getName());
		frm.setLawobjtype(dic.getName());
		for (int i = 0; i < filePo.size(); i++) {
			frm.setFilePath(filePo.get(i).getName());
		}
	}

	@Override
	public void saveConfig(ConfigForm frm, MultipartFile file) {
		TDataLawobjtypetasktype po = null;
		TDataFile filePo = new TDataFile();
		try {
			po = (TDataLawobjtypetasktype)this.get(TDataLawobjtypetasktype.class, frm.getId());
			// 如果执行检查单删除之前可能上传的文件
			List<TDataFile> files = this.find("from TDataFile where pid = '" + frm.getId() + "'" );
			for (int i = 0; i < files.size(); i++) {
				filePo = files.get(i);
				// 删除文件
				FileUpDownUtil.delFile(FileUpDownUtil.path + UploadFileType.CLIENT.getPath() + File.separator + filePo.getOsname());
				// 删除文件记录
				this.dao.remove(filePo);
			}
			
			if (StringUtils.isNotBlank(frm.getIsexecchecklist())) {
				po.setIsexecchecklist(frm.getIsexecchecklist());
			}
			this.dao.save(po);
			// 如果不执行检查单
			if ("1".equals(frm.getIsexecchecklist())){
				if (null != file && file.getSize() != 0){
					// 保存文件
					commonManager.saveFile(filePo, file, po.getId(), FileTypeEnums.JCMB.getCode(), UploadFileType.CLIENT);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeConfig(String id) {
		try {
			TDataLawobjtypetasktype del = (TDataLawobjtypetasktype) this.dao.get(TDataLawobjtypetasktype.class, id);
			
			List<TDataFile> filePo = this.find("from TDataFile where pid = '" + id + "'" );
			for (int i = 0; i < filePo.size(); i++) {
				// 删除文件
				FileUpDownUtil.delFile(FileUpDownUtil.path + UploadFileType.CLIENT.getPath() + File.separator + filePo.get(i).getOsname());
				this.dao.remove(TDataFile.class, filePo.get(i).getId());
			}
			
			this.dao.remove(del);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public Map<String, String> queryJcmbInfo(String tasktypeid, String lawobjtype) {
		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("select a.id_, b.name_ as tasktypeid, c.name_ as lawobjtype, a.is_exec_checklist from  T_DATA_LAWOBJTYPETASKTYPE a left join t_Data_Tasktype b on a.tasktypeid_ = b.code_ left join t_Data_Lawobjtype c on a.lawobjtype_ = c.id_ where  1=1");
		if (StringUtils.isNotBlank(tasktypeid)) {
			sql.append(" and a.tasktypeid_ = :tasktypeid");
			data.put("tasktypeid", tasktypeid);
		}
		if (StringUtils.isNotBlank(lawobjtype)) {
			sql.append(" and a.lawobjtype_ = :lawobjtype");
			data.put("lawobjtype", lawobjtype);
		}
		List<Object[]> pos = dao.findBySql(sql.toString(), data.getCanshu());
		Map<String, String> dataObject = new HashMap<String, String>();
		if(pos!=null&&pos.size()>0){
			Object[] ele=pos.get(0);
			dataObject.put("id", ele[0].toString());
			dataObject.put("tasktypeid", ele[1].toString());
			dataObject.put("lawobjtype", ele[2].toString());
			dataObject.put("isexecchecklist", ele[3].toString());
			//文件id
			List<TDataFile> filePo = this.dao.find("from TDataFile where pid = ?", ele[0].toString());
			if(filePo!=null&&filePo.size()>0){
				dataObject.put("jcmbFileId", filePo.get(0).getId());
			}
		}
		return dataObject;
	}

	@Override
	public void downloadCheckTemplate(String id, HttpServletResponse res) {
		TDataFile filePo = (TDataFile) (this.dao.find("from TDataFile where pid = ?", id)).get(0);
		try {
			String path = File.separator + UploadFileType.CLIENT.getPath() + File.separator + filePo.getOsname();
			FileUpDownUtil.downloadFile(res, path, filePo.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
