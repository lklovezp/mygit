package com.hnjz.app.work.zxzz;

import java.util.List;
import java.util.Map;

import com.hnjz.app.work.po.TBizAutomoniter;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

/**
 * ��������RwglManager
 * ���ߣ�zhangqingfeng
 * �������ڣ�Mar 9, 2015
 * ����������
 *
 */
public interface ZxzzManager extends Manager{
	
	/**
	 * 
	 * �������ܣ�����ѯ�ʱ�¼��������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveXwbl(ZxzzForm zxzzForm, String applyId, String wflx);
	
	/**
	 * 
	 * �������ܣ�����ѯ�ʱ�¼doc�ļ�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveShengchengXwbl(String applyId,String wflx);

	public List<TBizAutomoniter> getList(String applyId, String mblx);

	FyWebResult queryZxzzFileList(String pid, String canDel, String fileType, String page, String rows);

	public String getKzlx(String kzlx);
	
	/**
	 * 
	 * �������ܣ�ÿ������ģ���¼����
	 * ���������applyId:����id;
	 * ����ֵ��
	 */
	public void saveBlmbcs(String taskId, String tmplateId, String fileId, String fileCount);

	/**
	 * 
	 * �������ܣ���ȡ��������ģ��
	 * ���������applyId:����id;bllx:��¼����;
	 * ����ֵ��
	 */
	public List<Map<String, String>> getZxzzFiles(String applyId,String bllx);

	/**
	 * 
	 * �������ܣ���ȡ���������ĺϲ��ĵ�
	 * ���������applyId:����id;bllx:��¼����;
	 * ����ֵ��
	 */
	public String saveCopyFile(String applyId, String bllx);
	
}