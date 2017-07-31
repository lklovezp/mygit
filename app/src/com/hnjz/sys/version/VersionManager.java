/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.version;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * ��ҵ������manager
 * 
 * @author wumi
 * @version $Id: VersionManager.java, v 0.1 2013-3-25 ����03:28:05 wumi Exp $
 */
public interface VersionManager extends Manager {

	/**
	 * ��ѯ�б�����
	 * @param pageSize2 
	 * @param pageSize2 
	 * @param page2 
	 * 
	 * @return ���ܲ˵��б�
	 * @throws Exception
	 */
	public FyWebResult queryVersion(String code, String name, String isActive, String page, String pageSize);

	/**
	 * �༭һ���汾��Ϣ
	 * 
	 * @param frm �汾����
	 * @return ��
	 */
	public void versionInfo(VersionForm frm);
	
	/**
	 * ����һ���汾��Ϣ
	 * @param file 
	 * @param request 
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param ConfigForm �汾�ı���
	 * @return �汾�ĳ�ʼ����
	 */
	public void saveVersion(VersionForm frm, MultipartFile file, HttpServletRequest request);

	/**
	 * ɾ��һ���汾��Ϣ
	 * 
	 * @param id �汾id
	 * @return ��
	 */
	public void removeVersion(String id);

	/**
	 * ���ذ�װ��
	 * 
	 * @param id �汾id
	 * @return ��
	 */
	public void downloadVersion(String id, HttpServletResponse res);
	
	/**
	 * ȡ�������汾
	 * 
	 * @param 
	 * @return ��
	 */
	public String getVersion();

	/**
	 * ȡ�������汾
	 * 
	 * @param 
	 * @return ��
	 */
	public HashMap<String, Object> getVersionMo();
	
	/**
	 * ���ذ����ĵ�
	 * 
	 * @return ��
	 */
	public void downApk(HttpServletResponse res) throws AppException;

	/**
	 * �����ֻ���
	 * 
	 * @return ��
	 * @throws AppException 
	 */
	public void downMoblieApkApk(HttpServletResponse res) throws AppException;

	/**
	 * ��ȡ�ֻ��˰汾
	 * 
	 * @return ��
	 * @throws AppException 
	 */
	public HashMap<String, Object> getMoblieVersionMo();

}