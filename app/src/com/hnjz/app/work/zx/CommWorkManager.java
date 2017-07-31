package com.hnjz.app.work.zx;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.data.po.TDataChecklisttemplate;
import com.hnjz.app.work.beans.ResultBean;
import com.hnjz.app.work.po.TBizBlmbcs;
import com.hnjz.app.work.po.TBizMoreCheck;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.manager.Manager;
import com.hnjz.sys.po.TSysUser;

/**
 * ����ִ��RwglManager
 * ���ߣ�xb
 * �������ڣ�Mar 9, 2015
 * ����������
 *
 */
public interface CommWorkManager extends Manager{
	/**
     * 
     * �������ܣ�
    ��ִ������
     * ���������
    
     * ����ֵ��
     */
	public void saveDelZFDX(String applyId);
	/**
     * 
     * �������ܣ�
    ����������
     * ���������
    
     * ����ֵ��
     */
	public void saveDelRWLX(String applyId);
	/**
     * 
     * �������ܣ�
    ��ִ����������
     * ���������
    
     * ����ֵ��
     */
	public void saveDelZFDXLX(String applyId);
	/**
     * 
     * �������ܣ�
    �����ҳ���Լ�������
     * ���������
    
     * ����ֵ��
     */
	public void saveDelBL(String applyId);
	
	/**
	 * 
	 * �������ܣ�ִ���������ͱ���
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveZfdxType(String applyId,String zfdxType) throws AppException;
	
	/**
	 * 
	 * �������ܣ�ִ���������ͱ���
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveZfdxTypeOnChange(String applyId,String zfdxType,String rwlxIds) throws AppException;
	
	/**
	 * 
	 * �������ܣ�������������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveTaskTypeMultiple(String applyId,String checkedNodesIds,TSysUser curUser) throws AppException;
	
	/**
	 * 
	 * �������ܣ�������������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveTaskTypeMultiple(String applyId,String checkedNodesIds,TSysUser curUser,Map<String,String> jcjlMap,Map<String,String> rcbgDescMap) throws AppException;
	
	/**
	 * 
	 * �������ܣ���������id��ȡ���������б���׼����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<Map<String, String>> getTaskTypeByTaskId(String applyId);
	
	/**
	 * 
	 * �������ܣ�ִ������table��׼����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<Map<String, String>> zfdxTableData(String applyId);
	
	/**
	 * 
	 * �������ܣ���Ա�滮table��׼����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<Map<String, String>> ryghTableData(String applyId);
	/**
	 * 
	 * �������ܣ�Э����Աtable
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<Map<String, String>> xbryTableData(String applyId);
	/**
	 * 
	 * �������ܣ���Ա�滮-ɾ��
	ע�����ɾ������Ա�����õ��ˣ���Ѱ��������б���ļ���˼�¼��Ҳɾ����
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveDelrygh(String applyId,String id) throws AppException;
	
	/**
	 * 
	 * �������ܣ���Ա�滮�����򣨰�����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<Combobox> ryghCombo(String applyId);
	
	/**
	 * 
	 * �������ܣ���Ա����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveRy(String applyId,String ryid,String type) throws AppException;
	/**
	 * 
	 * �������ܣ���Ա����(���)
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveRyMul(String applyId,String ryid,String type) throws AppException;
	/**
	 * 
	 * �������ܣ�
	Э����Ա����(���)�նˡ����塰Э���ˡ��ٱ��档
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveRyMulXbrForMobile(String applyId,String ryid,String type) throws AppException;
	
	/**
	 * 
	 * �������ܣ��������������������������ִ����������+ִ�����������������������ѡ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<ComboboxTree> taskTypeTreeComboByTaskId(String applyId,String zfdxlx);
	
	/**
	 * 
	 * �������ܣ������������������˵��ŷ�Ͷ�ߣ����������ִ����������+ִ�����������������������ѡ��
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<ComboboxTree> taskTypeTreeComboByTaskIdWithoutXf(String applyId,String zfdxlx);
	
	/**
	 * 
	 * �������ܣ����ģ���б����������ҵindustryId���򷵻ظ���ҵ��Ӧģ�壻���û����ҵ���򷵻ظ���������������ģ���б���
	
	 * ���������
	
	 * ����ֵ��
	 */
	public List<TDataChecklisttemplate> getTempList(String taskType,String industryId);
	
	
	/**
	 * 
	 * ��ȡ��������������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public BlMainForm getBlMainFormData(String applyId);
	
	/**
	 * ��ȡ�ŷð�ᵥ����
	 * @param applyId
	 * @return
	 */
	public XfbjdForm getXfbjdform(String applyId);
	
	/**
	 * 
	 * �������ܣ��������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String saveWorkzxBL(String applyId,BlMainForm blMainForm);
	
	/**
	 * 
	 * �������ܣ���֤����ҳ����ת��true��ר�false��������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public ResultBean showBlPage(String applyId);
	
	/**
	 * 
	 * �������ܣ���ȡ����ѯ�ʱ�¼
	
	 * ���������applyId:����id;bllx:��¼����;
	
	 * ����ֵ��
	 */
	public Map<String, String> getKcxwblFile(String applyId,String bllx);
	
	/**
	 * 
	 * �������ܣ�ͨ�û�ȡ��������Ϣ
	
	 * ���������applyId:����id;fileTypeCode:�ļ�����;
	
	 * ����ֵ��
	 */
	public Map<String, String> getCommonFile(String applyId,String fileTypeEnumName);
	
	
	/**
	 * 
	 * �������ܣ���֤"׼��"��true��ͨ����false����ͨ����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public ResultBean checkBlZB(String applyId);
	/**
	 * 
	 * �������ܣ���֤"����"��true��ͨ����false����ͨ����
	
	 * ���������
	
	 * ����ֵ��
	 */
	public ResultBean checkBlBL(String applyId,String zfdxInfo);
	
	/**
	 * 
	 * �������ܣ���ȡ��������������������б�
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	public List<Combobox> queryBlFileTypeCombo(String rwlx,String zfdxInfo);
	
	/**
	 * 
	 * �������ܣ���ȡ��������������������б���ר���ж�������
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	public List<Combobox> queryBlFileTypeComboZXXDZRW();
	
	/**
	 * 
	 * �������ܣ����浥��ִ����Ϣ
	
	 * ���������applyId-����id��file-����ѹ����
	
	 * ����ֵ��
	 * @throws AppException 
	 */
	public void saveDjMessage(String applyId,MultipartFile file) throws AppException;
	
	/**
	 * 
	 * �������ܣ���ȡ���񱨸���һ�������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public String getFirstShrName(String applyId);
	
	/**
	 * 
	 * �������ܣ������ϱ��Ľӿڣ������ϱ��������ͱ���+��������
	
	 * ���������
	
	 * ����ֵ��
	 */
	public void saveSbRwlxAndFile(String sjid,String sbAreaId,String rwlxIds,List<Map<String, String>> sbFileInfo,List<Map<String, String>> bLspsInfo,Map<String,String> jcjlMap,Map<String,String> rcbgDescMap) throws AppException;
	
	public void saveJcjl(String taskid, String tasktype, String jcjl) throws Exception;
	
	public String jcjl(String taskid, String tasktype);
	
	public void saveDownJcbgmb(String jcmbId, String applyId, String taskType, HttpServletResponse res);
	/**
	 * �����ϴμ�����
	 * @param applyId ����id
	 * @param taskType ��������id
	 * @return
	 * @throws AppException
	 */
	public String getJcjl(String applyId, String taskType);
	
	/**
	 * �����ļ�id�õ���μ��
	 * @param fileId
	 * @return
	 */
	public TBizMoreCheck getMoreCheck(String fileId);
	
	/**
	 * �����ļ�id�õ�����������¼
	 * @param fileId
	 * @return
	 */
	public TBizBlmbcs getBlmbc(String fileId);
	
	/**
	 * ����������������Ͳ�ѯ��ǰ�ڼ��μ��
	 * @param applyId
	 * @param taskType
	 * @return
	 */
	public int getMaxTimes(String applyId,String taskType);
	
	/**
	 * ����idɾ����μ��
	 * @param id
	 */
	public void removeMoreCheck(TBizMoreCheck mc);
	
	/**
	 * ����idɾ������������¼
	 * @param bc
	 */
	public void removeBlmbc(TBizBlmbcs bc);
	
	/**
	 * ��ѯĳ������ĳ�����������µ����ж�μ�����¼
	 * @param applyId
	 * @param taskType
	 * @return
	 */
	public List<TBizMoreCheck> getMoreCheckList(String applyId,String taskType);
	
	/**
	 * ��ѯĳ������ĳ�����������µ����ж�μ�����¼��Ӧ���ļ�����������������
	 * @param pid
	 * @param canDel
	 * @param fileType
	 * @param page
	 * @param rows
	 * @return
	 */
	public FyWebResult queryJcblFileList(String pid, String canDel,String fileType,String page, String rows);
	/**
	 * �����ճ��칫��ע˵��
	 * */
	public void saveRcbg(String taskid, String tasktype, String desc) throws Exception;
	/**
	 * �����ճ��칫��ע˵��
	 * */
	public BlMainForm FindRcbg(String taskid, String tasktype) throws Exception;
	/**
	 * ����ճ��칫��ע˵��
	 * */
	public String getRcbgDesc(String applyId, String taskType);
	/**
	 * ��ȡ�����������������Ա��Ϣ
	 * @param applyId
	 * @return
	 */
	public List<Map<String, String>> ryData(String applyId);
	/**
	 * �ֻ��˻�ȡ�������ͽӿ�
	 */
	public List<Map<String, String>> getTaskTypeByTaskId();
}