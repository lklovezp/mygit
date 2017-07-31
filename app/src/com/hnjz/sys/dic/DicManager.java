/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.dic;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysDic;

/**
 * ���Ź���Manager
 * 
 * @author wumi
 * @version $Id: DicManager.java, v 0.1 Jan 15, 2013 5:05:39 PM wumi Exp $
 */
public interface DicManager extends Manager {

	/**
	 * ��ѯ���ɱ༭��ҳ�б�
	 * 
	 * @return �б�
	 * @throws Exception
	 */
	public FyWebResult queryDic(String type, String name, String page,
			String pageSize) throws Exception;

	/**
	 * ɾ������
	 * 
	 * @param id
	 */
	public void removeDic(String id);

	/**
	 * ��ձ�
	 * 
	 * @param id
	 */
	public void removeDicData();

	/**
	 * ����
	 * 
	 * @param {@link DicForm}
	 */
	public void saveDicData(DicForm frm) throws AppException;

	/**
	 * ����
	 * 
	 * @param {@link DicForm}
	 * @throws FileNotFoundException
	 */
	public void dicDataBackUp() throws AppException, FileNotFoundException;

	/**
	 * ����
	 * 
	 * @param {@link DicForm}
	 */
	public int saveBatchDicData(List<DicForm> dicFormList) throws AppException;

	/**
	 * ��ѯ
	 * 
	 * @throws Exception
	 */
	public JSONArray dicTypeQuery() throws Exception;

	/**
	 * �޷�ҳ��ѯ,����type
	 * 
	 * @throws Exception
	 */
	public JSONArray dicDateQuery(String type) throws Exception;

	/**
	 * �����Ͳ�ѯȫ��������ѡ��ҳ��id,name��
	 * 
	 * @param type
	 *            �ֵ����ͣ������ö��DicTypeEnum
	 * @return ĳ�������¶���������ֵ伯��
	 */
	public List<Object> findByType(String type);

	/**
	 * ��ѯĳ���ֵ������µ���������(code,name)
	 * 
	 * @param dicType
	 *            �ֵ�����{@link DicTypeEnum}
	 * @return ĳ�������¶���������ֵ伯��
	 */
	public List<Combobox> queryDicData(DicTypeEnum dicType);

	/**
	 * ��ѯĳ���ֵ������µ���������(code,name)
	 * 
	 * @param dicType
	 *            �ֵ�����{@link DicTypeEnum}
	 * @return ĳ�������¶���������ֵ伯��
	 */
	public List<Combobox> queryDicDataByCode(String diccode);

	/**
	 * ��ѯΥ������
	 * 
	 * @return
	 */
	public List<Map<String, Object>> queryIllegalType();

	/**
	 * ��ѯĳ���ֵ������µ���������(code,name)
	 * 
	 * @param dicType
	 *            �ֵ�����{@link DicTypeEnum}
	 * @return ĳ�������¶���������ֵ伯��
	 */
	public List<Combobox> queryDicDataId(DicTypeEnum dicType);
	
	/**
	 * 
	 * �������ܣ�ͨ��type��code�������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getNameByTypeAndCode(String type,String code);
	
	/**
	 * ͨ��code��ѯĳ���ֵ�����(code)
	 * 
	 * @param code
	 *            �ֵ���
	 * @return ĳ���ֵ�����
	 */
	public TSysDic queryDicByCode(String type, String code);
	
	/**
	 * ��ѯ�ֵ��ȫ����Ŀ¼
	 * @return
	 */
	public List<String[]> queryRootCode();
	/**
	 * �����ֵ���Ų鿴�Ƿ��ظ�
	 * @param xh
	 * @return
	 */
	public void findRootxh(DicForm df) throws Exception;
	/**
	 * ����idɾ����Ŀ¼
	 * @param id
	 */
	public void deleteData(String id);
	
	/**
	 * ���Ӹ�Ŀ¼��Ϣ
	 * @param name
	 * @param id
	 * @param userId
	 * @return
	 */
	public void addData(String name,String id,String userId,String dateString);
	
	public void findRootxhcc(String id) throws AppException;
}