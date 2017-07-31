package com.hnjz.app.work.rwgl;

import java.util.Map;

import javax.servlet.ServletContext;

import org.json.JSONArray;

import com.hnjz.app.work.po.Work;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysUser;

/**
 * �������RwglManager
 * ���ߣ�xb
 * �������ڣ�Mar 9, 2015
 * ����������
�����������ɷ����Ѱ����񡢴�������
 *
 */
public interface RwglManager extends Manager{
	
////////////////////////////////////////////////////�����ɷ�ģ��////////////////////////////////////////////////////	
	/**
	 * 
	 * �������ܣ���ѯ�������ɷ��б���
	 * ���������rwmc���������ƣ�rwly��������Դ��
	 * ����ֵ��FyWebResult
	 */
	public FyWebResult getRwpfList(String rwmc,String rwly, String page, String pageSize)throws Exception;
	/**
	 * 
	 * �������ܣ���ѯ�������ɷ��б���
	 * ���������rwmc���������ƣ�rwly��������Դ��
	 * ����ֵ��FyWebResult
	 */
	public FyWebResult getRcbgRwpfList(String rwmc,String rwly,String rwlx ,String page, String pageSize)throws Exception;
	
	/**
	 * ɾ�����ɵ�����
	 * �������ܣ�
	 * ���������
	 * ����ֵ��
	 */
	public void saveDelwork(String id) throws AppException;
	
	
////////////////////////////////////////////////////��������ģ��////////////////////////////////////////////////////
	/**
	 * 
	 * �������ܣ�
	��ѯ�����������б���
	 * ���������
	rwmc���������ƣ�rwly��������Դ��pfr���ɷ��ˣ�rwzt������״̬��
	 * ����ֵ��FyWebResult
	 */
	public FyWebResult getDbrwList(String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String xfdjId, String lx, String page, String pageSize)throws Exception;
	
////////////////////////////////////////////////////�Ѱ�����ģ��////////////////////////////////////////////////////
	/**
	 * 
	 * �������ܣ�
	��ѯ���Ѱ������б���
	 * ���������
	rwmc���������ƣ�rwzt������״̬��
	 * ����ֵ��FyWebResult
	 */
	public FyWebResult getYbrwList(String zfdxType,String rwmc,String rwly,String rwzt,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime,String tasktype, String pfrId, String zbrId, String page, String pageSize)throws Exception;
	
	/**
	 * 
	 * �������ܣ�
	���������õ���taskId
	 * ���������
	
	 * ����ֵ��
	 */
	public String getTaskIdByWorkId(String workId);
	
	/**
	 * 
	 * �������ܣ����ҳ�������Ϣ
	
	 * ���������
	
	 * ����ֵ��
	 */
	public Map<String, Object> getShInfo(String applyId);
	
	/**
	 * 
	 * �������ܣ�����ҳ�������Ϣ
	
	 * ���������
	
	 * ����ֵ��
	 */
	public Map<String, Object> taskDetailJBXX(String applyId);
	
	/**
	 * 
	 * �������ܣ�����ҳ��������ת��¼
	
	 * ���������
	
	 * ����ֵ��
	 */
	public Map<String, Object> taskDetailRWLZJL(String applyId);
	
	/**
	 * 
	 * �������ܣ�����ҳ��������ת��¼���˻������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public Map<String, Object> taskDetailRWLZJL_THYJ(String applyId);
	
	/**
	 * 
	 * �������ܣ�����ҳ�汨������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public Map<String, Object> taskDetailBGXQ(String applyId);
	
	/**
	 * ��ȡ�û�������������
	 */
	public int getDbrwCount(TSysUser u);
	
	/**
	 * 
	 * �������ܣ�
	�������б����д�����������Զ��ɷ��󶽲�����������Ա�Ĵ����б���
	 * ���������
	 * ����ֵ��
	 */
	public void saveHdcTask(String applyId);
	
	/**
	 * 
	 * �������ܣ�
	�Ƿ��ϱ��ϼ�
	 * ���������
	
	 * ����ֵ��
	 */
	public Boolean isSB(String applyId);
	
	/**
	 * 
	 * �������ܣ�
	��ȡ����������
	 * ���������
	
	 * ����ֵ��
	 */
	public TSysUser getXpr(String applyId);
	
	/**
	 * 
	 * �������ܣ�
	��ȡ�������������
	 * ���������
	
	 * ����ֵ��
	 */
	public Work getXpWork(String applyId);
	
	/**
	 * 
	 * �������ܣ�
	��ʼ����״̬��Ϊ�������С�
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveRwzxStart(String applyId);
	
////////////////////////////////////////////////////�½�����ģ��////////////////////////////////////////////////////
	/**
	 * 
	 * �������ܣ���ѯ�����б���
	 * ���������rwmc���������ƣ�createTime�����ڴ���ʱ�䡣
	 * ����ֵ��FyWebResult
	 */
	public FyWebResult getJzList(String rwmc, String pfStarttime, String pfEndtime, String page, String pageSize)throws Exception;	
	
	/**
	 * �������ܣ���ʼ����״̬��Ϊ�������С�
	 * ���������
	 * ����ֵ��
	 */
	public void saveXml(String applyId);

	void setServletContext(ServletContext arg0);

	FyWebResult queryJcblFileList(String pid, String canDel, String fileType,
			String page, String rows);
	
	/**
	 * ��������ʱ�õ��ɷ����ɷ�ʱ��ʾ���
	 * @param applyId
	 * @return
	 */
	public String getPsyj(String applyId);

	/**
	 * 
	 * �������ܣ���ѯ���ֳ���������б���
	 * ���������rwmc���������ƣ�rwzt������״̬��
	 * ����ֵ��FyWebResult
	 * @throws Exception 
	 */
	public FyWebResult getXcjcrwList(String rwmc, String rwly, String pfrId,String rwzt, String tasktype, String zfdxType, String pfStarttime,String pfEndtime, String gdStarttime, String gdEndtime,String page, String pageSize) throws Exception;
	
	/**
	 * 
	 * �������ܣ���ѯ���ŷ�Ͷ�������б���
	 * ���������rwmc���������ƣ�rwzt������״̬��
	 * ����ֵ��FyWebResult
	 * @throws Exception
	 */
	public FyWebResult getXftsrwList(String rwmc, String rwly, String pfrId,String rwzt, String tasktype, String zfdxType, String pfStarttime,String pfEndtime, String gdStarttime, String gdEndtime,String xfdjbId,String page, String pageSize) throws Exception;
	/**
	 * ��ҳ��ѯ��������ǰ6��
	 * @return
	 */
	public String dbQuery();
	
	/**
	 * ��ҳ��ѯ�Ѱ�����ǰ6��
	 * @return
	 */
	public String ybQuery();
	
	/**
	 * ��������
	 * @param work
	 * @throws AppException
	 */
	public void savework(Work work) throws AppException;
	/**
	 * �ճ��칫�����б���ѯ
	 */
	public FyWebResult getRcbgrwList(String rwmc, String rwly, String pfrId, String rwzt, String tasktype, String zfdxType, String pfStarttime, String pfEndtime, String gdStarttime, String gdEndtime,
			String xfdjbId, String page, String pageSize) throws Exception;
	
	/**
	 * 
	 * �������ܣ���ȡ�����ɷ���
	 * ���������
	 * ����ֵ��
	 */
	public TSysUser getPfr(String applyId);
	
	/**
	 * 
	 * �������ܣ���ѯ��ִ�������������б���
	 * ���������rwmc���������ƣ�rwly��������Դ��pfr���ɷ��ˣ�rwzt������״̬��
	 * ����ֵ��FyWebResult
	 */
	public FyWebResult getZfjcDbrwList(String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String xfdjId, String page, String pageSize)throws Exception;
	
	/**
	 * 
	 * �������ܣ���ѯ���ŷ�Ͷ�ߴ��������б���
	 * ���������rwmc���������ƣ�rwly��������Դ��pfr���ɷ��ˣ�rwzt������״̬��
	 * ����ֵ��FyWebResult
	 */
	public FyWebResult getXftsDbrwList(String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String xfdjId, String page, String pageSize)throws Exception;
	
	/**
	 * 
	 * �������ܣ���ѯ���ճ��칫���������б���
	 * ���������rwmc���������ƣ�rwly��������Դ��pfr���ɷ��ˣ�rwzt������״̬��
	 * ����ֵ��FyWebResult
	 */
	public FyWebResult getRcbgDbrwList(String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String xfdjId, String page, String pageSize)throws Exception;
	
	/**
	 * 
	 * �������ܣ���ѯ������ʵ���б���
	 * ���������rwmc���������ƣ�rwly��������Դ��pfr���ɷ��ˣ�rwzt������״̬��
	 * ����ֵ��FyWebResult
	 */
	public FyWebResult getRwslList(String rwmc,String rwly,String pfrId,String rwzt,String tasktype, String zfdxType,String pfStarttime,String pfEndtime,String gdStarttime,String gdEndtime, String xfdjId, String page, String pageSize)throws Exception;
	
	/**
	 * 
	 * �������ܣ���ѯ����ȡ�������������ؽڵ㷽��
	 * ���������
	 * ����ֵ��FyWebResult
	 */
	public JSONArray preSubmitNodePubQuery();
	
	/**
	 * ��ȡ�û�������������
	 */
	public int getYqrwCount(TSysUser u);
}