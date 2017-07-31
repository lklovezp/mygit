package com.hnjz.app.data.xxgl.lawdoc;

import java.util.List;

import org.json.JSONArray;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.common.FyWebResult;



/**
 * 
 * ���ߣ�renzhengquan
 * �������ڣ�2015-3-17
 * ����������
	ִ�������ֵ�manager�ӿ�
 *
 */
public interface LawdocManager {
	
	/**
	 * 
	 * �������ܣ���ѯ���ϴ����ļ��б����½�ִ���ļ�ʹ�ã�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<LawdocForm> queryNewUploadLawdoc(String pid);
	
	/**
	 * 
	 * �������ܣ���õ�ǰĿ¼����������ֵ
	
	 * ���������
	
	 * ����ֵ��
	 */
	public Integer getMaxorder(String pid);
	
	/**
	 * 
	 * �������ܣ�
	
	 * ���������pid-Ŀ¼id��uuid-��ʱĿ¼id��data-json�ַ���[id-ִ���ļ�id��ֵ��keywords-ִ���ļ��ؼ�����ֵ��orderby-��������]
	
	 * ����ֵ��
	 */
	public void saveLawdoc(String pid,String uuid,String data);
	
	/**
	 * 
	 * �������ܣ�ɾ������ִ���ļ�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void removeLawdoc(String id);

	/**
	 * 
	 * �������ܣ�����pidɾ��Ŀ¼�µ�����ִ���ļ�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void removeLawdocByPid(String pid);
	
	/**
	 * 
	 * �������ܣ���ҳ��ѯִ���ļ��б�
	
	 * ���������
	 * @param pid:Ŀ¼id
	 * @param title:����
	 * @param keywords:�ؼ���
	 * @param canDel:�Ƿ��ɾ��
	 * @param page:��ǰҳ
	 * @param pageSize:ÿҳ��ʾ����
	
	 * ����ֵ��
	 */
	public FyWebResult queryLawdocList(String pid,String title,String keywords,String canDel,String page,String pageSize);

	/**
	 * 
	 * �������ܣ���ȡִ���ļ�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public LawdocForm getLawdocInfo(String id);
	
	/**
	 * 
	 * �������ܣ����µ���ִ���ļ�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void updateLawdoc(LawdocForm lawdocForm,MultipartFile file);
	
	/**
	 * 
	 * �������ܣ��ն˲�ѯִ���ļ�ͨ�ýӿ�
	
	 * ���������pid-Ŀ¼id��title-���⡢keywords-�ؼ��ʡ�page-��ǰҳ��pageSize-ÿҳ��ʾ����
	
	 * ����ֵ��
	 */
	public FyWebResult queryLawdocListForMobile(String pid, String title, String keywords, String page, String pageSize);

	 /**
     * 
     * �������ܣ�����ѡ���ִ���ļ���������ظ���
    
     * ����������������͡�����id��ѡ����ļ�id���ö��Ÿ�����
    
     * ����ֵ��
     */
	public void saveChooseeLawdoc(String fileType,String applyId,String fileid);

	/**
	 * 
	 * �������ܣ������������Ͳ�ѯִ���ļ�
	
	 * ���������
	
	 * ����ֵ��
	 */
	public FyWebResult queryLawdocListByTasktype(String tasktype, String title, String keywords, String page, String pageSize);

	String getmysqlDir(String dirid);
}