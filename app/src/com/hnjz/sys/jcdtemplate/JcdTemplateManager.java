/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.jcdtemplate;

import java.util.List;

import org.json.JSONArray;

import com.hnjz.app.data.po.TDataChecklisttemplate;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * �������͹�����manager
 * 
 * @author wumi
 * @version $Id: TaskTypeManager.java, v 0.1 2013-3-25 ����03:28:05 wumi Exp $
 */
public interface JcdTemplateManager extends Manager {

	/**
	 * ��ѯ���ܲ˵�
	 * 
	 * @return ���ܲ˵��б�
	 * @throws Exception
	 */
	public JSONArray queryJcdTemplate(String name) throws Exception;
	/**
	 * ������ģ��
	 * 
	 * @return ��
	 * @throws AppException
	 */
	public void saveTemplate(TemplateForm frm) throws AppException;
	/**
	 * ������ģ�塢�汾
	 * 
	 * @return ��
	 * @throws AppException
	 */
	public void saveTemplateVersion(TemplateForm frm) throws AppException;
	/**
	 * ��������ģ������תҳ��ӿ�
	 * 
	 * @return ҳ��·��
	 * @throws 
	 */
	public String treeClickJumpPage(TemplateForm frm);
	/**
	 * ��������ģ������תҳ��ӿ�
	 * 
	 * @return ҳ��·��
	 * @throws 
	 */
	public void queryCheckListList(CheckItemForm frm);
	/**
	 * ��������
	 * 
	 * @return ��
	 * @throws AppException
	 */
	public void saveCheckListItem(CheckItemForm frm) throws AppException;
	/**
	 * ��ѯ������б�
	 * @param templateId ���ģ��id
	 * @param page ��ǰҳ��
	 * @param pageSize ÿҳ��¼����
	 * @throws 
	 */
	public FyWebResult queryCheckListItem(String templateId, String page,
			String pageSize);
	/**
	 * ��ѯ���б�
	 * @param itemId �����id
	 * @throws 
	 */
	public FyWebResult queryCheckItemAns(String itemId);
	/**
	 * ������ģ����תҳ��ӿ�
	 * 
	 * @return
	 * @throws 
	 */
	public void addSubTemplate(TemplateForm frm);
	/**
	 * ��ѯ�汾��Ϣ
	 * 
	 * @return ҳ��·��
	 * @throws 
	 */
	public String editTemplateVersion(TemplateForm frm);
	/**
	 * ����Ĭ�ϼ��ģ��
	 * 
	 * @return 
	 * @throws 
	 */
	public void saveDefaultVersion(TemplateForm frm);
	
	/**
	 * ���渴�Ƶİ汾
	 * 
	 * @throws 
	 */
	public void saveCopyVersion(TemplateForm frm) throws AppException;
	
	/**
	 * ������ģ��
	 * 
	 * @throws 
	 */
	public void saveSubTemplate(TemplateForm frm) throws AppException;
	
	/**
	 * ɾ�������
	 * 
	 * @throws 
	 */
	public void removeCheckListItme(CheckItemForm frm) throws AppException;
	
	/**
	 * ɾ���汾
	 * 
	 * @throws 
	 */
	public void removeVersion(TemplateForm frm) throws AppException;
	
	/**
	 * ɾ����ģ��
	 * 
	 * @throws 
	 */
	public void removeSubTemplate(TemplateForm frm) throws AppException;
	
	/**
	 * @throws AppException 
	 * ɾ��ģ��
	 * 
	 * @throws 
	 */
	public void removeTemplate(TemplateForm frm) throws AppException;
	
	/**=====================================���ⲿʹ�õķ���======================================*/
	
	/**
	 * �������ģ����
	 * 
	 * @param ģ��id
	 * @return ���˵�json����
	 */
	public JSONArray templateTree(String templateId) throws AppException;
	
	
	public void saveCopyTemplate(String name, String oldIndustry, String oldTaskType, String newIndustry, String newTaskType) throws AppException;
	
	public TDataChecklisttemplate queryTemplateWithNoId(TemplateForm frm);
	
	
	/**
	 * ��ȡĳ��ģ���µ�������ģ��
	 * @param templateId
	 * @return
	 */
	public List<TDataChecklisttemplate> getChTemplates(String templateId);
	
	/**
	 * ���ݼ��ģ��id��ȡģ��
	 * @param id
	 * @return
	 */
	public TDataChecklisttemplate getChecklisttemplate(String id);
	
}