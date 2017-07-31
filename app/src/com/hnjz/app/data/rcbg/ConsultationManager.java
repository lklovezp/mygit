package com.hnjz.app.data.rcbg;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.common.FileForm;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.po.TDataMail;
import com.hnjz.app.data.po.TDataMailyj;
import com.hnjz.app.data.po.TDataRecmail;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.manager.Manager;

public interface ConsultationManager extends Manager {
	/**
	 * ��ѯ�����û�����װ��box
	 * */
	public JSONArray queryUserByArea(List<String> id)throws Exception;
	/**
	 * ��ѯ�����û�����װ��box
	 * */
	public JSONArray queryUserByGroup(List<String> id)throws Exception;
	/**
	 * ���ͻ���ʱ�����������Ϣ
	 * */
	public TDataMail saveConsultation(MailForm mailForm)throws Exception;
	/**
	 * ���ͻ���ʱ�����������Ϣ
	 * */
	public TDataMail saveSendConsultation(MailForm mailForm)throws Exception;
	/**
	 * ��ѯ���ջ���
	 * */
	public FyWebResult queryRecConsultation(String isActive,String startTime,String endTime,
			String pfrId,String topic, String page,String pageSize)throws Exception;
	/**
	 * ����mailID����Mail
	 * */
	public TDataMail findMailByMailId(String mailId)throws Exception;
	/**
	 * ����recmailID����Mail���飬����װ��Form
	 * */
	public MailForm queryMailByMailId(String mailId)throws Exception;
	/**
	 * ����mailID����Mail���飬����װ��Form
	 * */
	public MailForm queryMailById(String mailId)throws Exception;
	/**
	 * �����û���ids��ѯ�������������û�����
	 * 
	 * @param ids
	 *            �û���¼��
	 * @return �û����ƣ���";"����
	 */
	public String getUserNames(String ids)throws Exception;
	/**
	 * ����ID����TDataRecmail
	 * */
	public TDataRecmail queryRecMailById(String id)throws Exception;
	/**
	 * ����ID����TDataRecmail���鲢��װ��RecMailForm
	 * */
	public RecMailForm queryRecMailFormById(String id)throws Exception;
	/**
	 * ����recmailId���Ķ�״̬�������鲢��װ��RecMailForm
	 * */
	public IsReadForm queryIsReadFormByMailId(String id,String isRead)throws Exception;
	/**
	 * ����mailId���Ķ�״̬�������鲢��װ��RecMailForm
	 * */
	public IsReadForm queryIsReadFormById(String id,String isRead)throws Exception;
	/**
	 * ���ݽ��Ķ�״̬��װ��String
	 * */
	public String queryNameAndIsRead(List<TDataRecmail> list)throws Exception;
	/**
	 * ���ݽ��Ķ�״̬��װ��String
	 * */
	public String queryNameAndNoRead(List<TDataRecmail> list)throws Exception;
	
	/**
	 * ����ظ����
	 * */
	public TDataMailyj saveYiJian(MailyjForm mailyjForm,String recMailId,String yjId)throws Exception;
	/**
	 * ��ѯ���лظ����
	 * */
	public List<MailyjForm> queryAllYiJianByMailId(String mailId)throws Exception;
	/**
	 * ��ѯ�ѷ�����
	 * */
	public FyWebResult queryYiSendConsultation(String isActive,String startTime,String endTime,
			String recId,String topic,String page,String pageSize)throws Exception;
	/**
	 * ɾ�����ջ���
	 * */
	public void delRecMailById(String id)throws Exception;
	/**
	 * ɾ���ѷ�����
	 * */
	public void delYiSendMailById(String id)throws Exception;
	/**
	 * ���淢�ͻ���ʱ�ĸ���
	 * */
	public TDataFile saveFuJian(MultipartFile multipartFile,HttpServletRequest request,String pid,String fileType)throws AppException;
	/**
	 * ����mailId��ѯ���̸���
	 * */
	public List<Map<String, String>> queryFileList(String pid)throws Exception;
	/**
	 * ����mailId��ѯ���̸��ӷ�װ��FileForm
	 * */
	public List<FileForm> queryFileFormList(String pid)throws Exception;
	/**
	 * �����Ķ�״̬
	 * */
	public TDataRecmail saveIsRead(String id)throws Exception;
	/**
	 * ����mailId�͵�ǰ�û���ѯ���̸���
	 * */
	public List<Map<String, String>> queryFileListByUserId(String pid,String userId)throws Exception;
	/**
	 * �������ʱ���������
	 * */
	public TDataFile saveYJFuJian(MultipartFile multipartFile,HttpServletRequest request,String pid,String fileType)throws AppException;
	
	/**
	 * ���ݽ������б��͵�ǰ�û���ѯ�Ƿ�ظ�
	 * */
	public String ishuiFu(String recListId,String userId,String mailId)throws Exception;
	
	/**
	 * ��ѯδ�����̵�����
	 * @return
	 */
	public int getwdhsCount();
	
	
}