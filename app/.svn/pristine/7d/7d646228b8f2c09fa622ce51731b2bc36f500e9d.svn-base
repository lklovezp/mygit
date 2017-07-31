/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.version;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.QRUtil;
import com.hnjz.sys.po.TSysUser;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.po.TDataVersion;

/**
 * 版本管理的manager
 * 
 * @author wumi
 * @version $Id: VersionManagerImpl.java, v 0.1 2013-3-25 下午03:28:05 wumi Exp $
 */
@Service("versionManagerImpl")
public class VersionManagerImpl extends ManagerImpl implements
		VersionManager {
	/** 日志 */
	private static final Log log = LogFactory
			.getLog(VersionManagerImpl.class);
	@Autowired
	private CommonManager commonManager;
	@Override
	public FyWebResult queryVersion(String code, String name, String isActive, String page, String pageSize) {
		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append("from TDataVersion where 1 = 1 ");
		if (StringUtils.isNotBlank(code)) {
			sql.append(" and code like :code");
			data.putLike("code", code);
		}
		if (StringUtils.isNotBlank(name)) {
			sql.append(" and name like :name");
			data.putLike("name", name);
		}
		if (StringUtils.isNotBlank(isActive)) {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", isActive);
		} else {
			sql.append(" and isActive = :isActive ");
			data.put("isActive", YnEnum.Y.getCode());
		}
		
		sql.append(" order by orderby ");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));
		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		List<TDataVersion> versions = pos.getRe();
		Map<String, String> dataObject = null;
		for (TDataVersion ele : versions) {
			dataObject = new HashMap<String, String>();
			dataObject.put("id", ele.getId());
			dataObject.put("code", ele.getCode());
			dataObject.put("name", ele.getName());
			if (ele.getType().equals("0")){
				dataObject.put("type", "终端");
			} else if(ele.getType().equals("1")) {
				dataObject.put("type", "离线版");
			} else if(ele.getType().equals("2")) {
				dataObject.put("type", "离线数据包");
			} else if(ele.getType().equals("3")) {
				dataObject.put("type", "后台帮助文档");
			} else if(ele.getType().equals("4")) {
				dataObject.put("type", "终端帮助文档");
			} else if(ele.getType().equals("5")) {
				dataObject.put("type", "手机端");
			}
			
			dataObject.put("describe", ele.getDescribe());
			dataObject.put("isActive", ele.getIsActive().equals(YnEnum.Y.getCode()) ? "可用" : "不可用");
			dataObject.put("isforce", ele.getIsforce().equals(YnEnum.Y.getCode()) ? "是" : "否");
			dataObject.put("operate", OperateUtil.getOperate(ele.getId()));
			rows.add(dataObject);
		}
		fy.setRows(rows);
		log.debug(fy);
		return fy;
	}

	@Override
	public void versionInfo(VersionForm frm) {
		TDataVersion po = (TDataVersion) this.dao.get(TDataVersion.class, frm.getId());
		List<TDataFile> filePo = this.dao.find("from TDataFile where pid = ?", frm.getId());;
		
		frm.setId(po.getId());
		frm.setName(po.getName());
		frm.setCode(po.getCode());
		frm.setDescribe(po.getDescribe());
		frm.setIsforce(po.getIsforce());
		frm.setType(po.getType());
		frm.setDescribe(po.getDescribe());
		frm.setOrderby(po.getOrderby());
		frm.setIsActive(po.getIsActive());
		for (int i = 0; i < filePo.size(); i++) {
			frm.setFilePath(filePo.get(i).getName());
		}
	}

	@Override
	public void saveVersion(VersionForm frm, MultipartFile file, HttpServletRequest request) {
		TDataVersion po = null;
		TDataFile filePo = null;
		try {
			// 名称不能重复
			StringBuilder hsq = new StringBuilder();
			hsq.append("select count(id) from TDataVersion where (code = :code or name = :name) and type = :type");
			QueryCondition data = new QueryCondition();
			data.put("code", frm.getCode());
			data.put("name", frm.getName());
			data.put("type", frm.getType());
			long count = (Long) this.dao.find(hsq.toString(), data).get(0);
			if (StringUtils.isNotBlank(frm.getId())){
				po = (TDataVersion)this.get(TDataVersion.class, frm.getId());
				// 当为编辑状态并且文件大小不为0 即上传了新的文件 执行删除  下方保存文件同理
				if (file != null && file.getSize() != 0) {
					List<TDataFile> files = this.find("from TDataFile where pid = '" + frm.getId() + "'" );
					if (files.size() > 0){
						filePo = files.get(0);
						// 删除文件
						FileUpDownUtil.delFile(FileUpDownUtil.path + UploadFileType.CLIENT.getPath() + File.separator + filePo.getOsname());
					} else {
						filePo = new TDataFile();
					}
				}
			} else {
				if (count > 0) {
					new AppException("版本内容不能重复。");
					return;
				} else {
					// 帮助文档
					if (frm.getType().equals("3")){
						count = (Long) this.dao.find("select count(id) from TDataVersion where type = '3'").get(0);
					}
					if (count > 0){
						po = (TDataVersion) this.dao.find("from TDataVersion where type = '3'").get(0);
					} else {
						po = new TDataVersion();
						filePo = new TDataFile();
					}
				}
			}
			TSysUser curUser = CtxUtil.getCurUser();
			Date cur = new Date();
			// 保存版本表数据
			if (StringUtils.isNotBlank(frm.getIsforce())) {
				po.setIsforce(YnEnum.Y.getCode());
			} else {
				po.setIsforce(YnEnum.N.getCode());
			}
			if (StringUtils.isNotBlank(frm.getIsActive())) {
				po.setIsActive(YnEnum.Y.getCode());
			} else {
				po.setIsActive(YnEnum.N.getCode());
			}
			String type = "";
			if (frm.getType().equals("0")){
				type = FileTypeEnums.APPZD.getCode();
			} else if (frm.getType().equals("1")){
				type = FileTypeEnums.APPPC.getCode();
			} else if (frm.getType().equals("2")){
				type = FileTypeEnums.APPPCDATA.getCode();
			} else if (frm.getType().equals("3")){
				type = FileTypeEnums.APPWEBHELP.getCode();
			} else if (frm.getType().equals("4")){
				type = FileTypeEnums.APPMOHELP.getCode();
			} else if (frm.getType().equals("5")){
				type = FileTypeEnums.APPMOBLIE.getCode();
			}
			
			po.setCode(frm.getCode());
			po.setName(frm.getName());
			po.setDescribe(frm.getDescribe());
			po.setType(frm.getType());
			po.setOrderby(frm.getOrderby());
			po.setCreated(cur);
			po.setCreateby(curUser);
			po.setUpdateby(curUser);
			po.setUpdated(cur);
			po = (TDataVersion)this.dao.save(po);
			
			if (file != null && file.getSize() != 0){
				// 保存文件
				commonManager.saveFile(filePo, file, po.getId(), type, UploadFileType.CLIENT);
				if (type.equals(FileTypeEnums.APPZD.getCode())) {
					FileUpDownUtil util = new FileUpDownUtil();
					// 将文件复制到static//temp//apk路径下方便手机端下载
					String path = util.copyToStatic(file.getInputStream(), file.getOriginalFilename(), "apk");
					String staticPath = path.substring(0, path.lastIndexOf("temp//apk"));
					String imgPath = "";
					//java 获取请求 URL
					//请求协议 http 或 https
					String url = request.getScheme()+"://";
					// 请求服务器
					url += request.getHeader("host");
					url += request.getRequestURI().substring(0, request.getRequestURI().lastIndexOf("/")) + "//static//";
					// 工程名
					url += "temp//apk//" + file.getOriginalFilename();
					imgPath = staticPath + "app//images//QR.png";
					// 生成二维码
					QRUtil handler = new QRUtil();
					handler.encoderQRCode(url, imgPath, "png");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeVersion(String id) {
		try {
			TDataVersion del = (TDataVersion) this.dao.get(TDataVersion.class, id);
			del.setIsActive(YnEnum.N.getCode());
			this.dao.save(del);
			
//			List<TDataFile> filePo = this.find("from TDataFile where pid = '" + id + "'" );
//			for (int i = 0; i < filePo.size(); i++) {
//				// 删除文件
//				FileUpDownUtil.delFile(FileUpDownUtil.path + UploadFileType.CLIENT.getPath() + File.separator + filePo.get(i).getOsname());
//				this.dao.remove(TDataFile.class, filePo.get(i).getId());
//			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void downloadVersion(String id, HttpServletResponse res) {
		TDataFile filePo = (TDataFile) (this.dao.find("from TDataFile where pid = ?", id)).get(0);
		try {
			String path = File.separator + UploadFileType.CLIENT.getPath() + File.separator + filePo.getOsname();
			FileUpDownUtil.downloadFile(res, path, filePo.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getVersion() {
		List<TDataVersion> vs = this.dao.find("from TDataVersion where type = '0' and isActive = 'Y' order by code desc");
		
		int max = 0;
		HashMap<String, String> map = new HashMap<String, String>();
		
		for (int i = 0; i < vs.size(); i++) {
			try{
				if (max == 0){
					max = Integer.parseInt(vs.get(i).getCode());
					map.put("name", vs.get(i).getName());
				}
				if (Integer.parseInt(vs.get(i).getCode()) > max){
					max = Integer.parseInt(vs.get(i).getCode());
					map.clear();
					map.put("name", vs.get(i).getName());
				}
			} catch (Exception e){
				
			}
		}
		
		if (map.size() > 0){
			return map.get("name");
		} else {
			return "V2.0.0";
		}
	}
	
	@Override
	public HashMap<String, Object> getVersionMo() {
		HashMap<String, Object> m = new HashMap<String, Object>();
		List<TDataVersion> vs = this.dao.find("from TDataVersion where type = '0' and isActive = 'Y' order by code desc");
		
		int max = 0;
		HashMap<String, String> map = new HashMap<String, String>();
		
		for (int i = 0; i < vs.size(); i++) {
			try {
				if (max == 0){
					max = Integer.parseInt(vs.get(i).getCode());
					map.put("code", vs.get(i).getCode());
					map.put("name", vs.get(i).getName());
					map.put("isforce", vs.get(i).getIsforce());
				}
				if (Integer.parseInt(vs.get(i).getCode()) > max){
					max = Integer.parseInt(vs.get(i).getCode());
					map.clear();
					map.put("code", vs.get(i).getCode());
					map.put("name", vs.get(i).getName());
					map.put("isforce", vs.get(i).getIsforce());
				}
			} catch (Exception e){
				
			}
		}
		
		if (map.size() > 0){
			m.put("code", map.get("code"));
			m.put("name", map.get("name"));
			m.put("isforce", map.get("isforce"));
		} else {
			m.put("code", null);
			m.put("name", "V2.0.0");
			m.put("isforce", false);
		}
		return m;
	}

	@Override
	public void downApk(HttpServletResponse res) throws AppException{
		//List<TDataFile> filePo = this.dao.find("from TDataFile where type = ?", FileTypeEnums.APPZD.getCode());
		List<TDataFile> filePo = this.dao.find("from TDataFile where type = '5201' order by updated desc");
		//select * from t_data_file t where t.type_='5201' order by t.updated_ desc
		try {
			if (!(filePo.size() > 0)){
				throw new AppException("无安装文件可下载。");
			}
			String path = File.separator + UploadFileType.CLIENT.getPath() + File.separator + filePo.get(0).getOsname();
			FileUpDownUtil.downloadFile(res, path, filePo.get(0).getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void downMoblieApkApk(HttpServletResponse res) throws AppException{
		List<TDataFile> filePo = this.dao.find("from TDataFile where type = '5206' order by updated desc");
		try {
			if (!(filePo.size() > 0)){
				throw new AppException("无安装文件可下载。");
			}
			String path = File.separator + UploadFileType.CLIENT.getPath() + File.separator + filePo.get(0).getOsname();
			FileUpDownUtil.downloadFile(res, path, filePo.get(0).getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public HashMap<String, Object> getMoblieVersionMo() {
		HashMap<String, Object> m = new HashMap<String, Object>();
		List<TDataVersion> vs = this.dao.find("from TDataVersion where type = '5' and isActive = 'Y' order by code desc");
		
		int max = 0;
		HashMap<String, String> map = new HashMap<String, String>();
		
		for (int i = 0; i < vs.size(); i++) {
			try {
				if (max == 0){
					max = Integer.parseInt(vs.get(i).getCode());
					map.put("code", vs.get(i).getCode());
					map.put("name", vs.get(i).getName());
					map.put("isforce", vs.get(i).getIsforce());
				}
				if (Integer.parseInt(vs.get(i).getCode()) > max){
					max = Integer.parseInt(vs.get(i).getCode());
					map.clear();
					map.put("code", vs.get(i).getCode());
					map.put("name", vs.get(i).getName());
					map.put("isforce", vs.get(i).getIsforce());
				}
			} catch (Exception e){
				
			}
		}
		
		if (map.size() > 0){
			m.put("code", map.get("code"));
			m.put("name", map.get("name"));
			m.put("isforce", map.get("isforce"));
		} else {
			m.put("code", null);
			m.put("name", "V2.0.0");
			m.put("isforce", false);
		}
		return m;
	}
}
