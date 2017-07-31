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
	 * 查询所用用户并封装成box
	 * */
	public JSONArray queryUserByArea(List<String> id)throws Exception;
	/**
	 * 查询所用用户并封装成box
	 * */
	public JSONArray queryUserByGroup(List<String> id)throws Exception;
	/**
	 * 发送会商时，保存会商信息
	 * */
	public TDataMail saveConsultation(MailForm mailForm)throws Exception;
	/**
	 * 发送会商时，保存会商信息
	 * */
	public TDataMail saveSendConsultation(MailForm mailForm)throws Exception;
	/**
	 * 查询已收会商
	 * */
	public FyWebResult queryRecConsultation(String isActive,String startTime,String endTime,
			String pfrId,String topic, String page,String pageSize)throws Exception;
	/**
	 * 根据mailID查找Mail
	 * */
	public TDataMail findMailByMailId(String mailId)throws Exception;
	/**
	 * 根据recmailID查找Mail详情，并封装成Form
	 * */
	public MailForm queryMailByMailId(String mailId)throws Exception;
	/**
	 * 根据mailID查找Mail详情，并封装成Form
	 * */
	public MailForm queryMailById(String mailId)throws Exception;
	/**
	 * 根据用户的ids查询出符合条件的用户名称
	 * 
	 * @param ids
	 *            用户登录名
	 * @return 用户名称，用";"隔开
	 */
	public String getUserNames(String ids)throws Exception;
	/**
	 * 根据ID查找TDataRecmail
	 * */
	public TDataRecmail queryRecMailById(String id)throws Exception;
	/**
	 * 根据ID查找TDataRecmail详情并封装成RecMailForm
	 * */
	public RecMailForm queryRecMailFormById(String id)throws Exception;
	/**
	 * 根据recmailId和阅读状态查找详情并封装成RecMailForm
	 * */
	public IsReadForm queryIsReadFormByMailId(String id,String isRead)throws Exception;
	/**
	 * 根据mailId和阅读状态查找详情并封装成RecMailForm
	 * */
	public IsReadForm queryIsReadFormById(String id,String isRead)throws Exception;
	/**
	 * 根据将阅读状态封装成String
	 * */
	public String queryNameAndIsRead(List<TDataRecmail> list)throws Exception;
	/**
	 * 根据将阅读状态封装成String
	 * */
	public String queryNameAndNoRead(List<TDataRecmail> list)throws Exception;
	
	/**
	 * 保存回复意见
	 * */
	public TDataMailyj saveYiJian(MailyjForm mailyjForm,String recMailId,String yjId)throws Exception;
	/**
	 * 查询所有回复意见
	 * */
	public List<MailyjForm> queryAllYiJianByMailId(String mailId)throws Exception;
	/**
	 * 查询已发会商
	 * */
	public FyWebResult queryYiSendConsultation(String isActive,String startTime,String endTime,
			String recId,String topic,String page,String pageSize)throws Exception;
	/**
	 * 删除已收会商
	 * */
	public void delRecMailById(String id)throws Exception;
	/**
	 * 删除已发会商
	 * */
	public void delYiSendMailById(String id)throws Exception;
	/**
	 * 保存发送会商时的附件
	 * */
	public TDataFile saveFuJian(MultipartFile multipartFile,HttpServletRequest request,String pid,String fileType)throws AppException;
	/**
	 * 根据mailId查询会商附加
	 * */
	public List<Map<String, String>> queryFileList(String pid)throws Exception;
	/**
	 * 根据mailId查询会商附加封装成FileForm
	 * */
	public List<FileForm> queryFileFormList(String pid)throws Exception;
	/**
	 * 设置阅读状态
	 * */
	public TDataRecmail saveIsRead(String id)throws Exception;
	/**
	 * 根据mailId和当前用户查询会商附加
	 * */
	public List<Map<String, String>> queryFileListByUserId(String pid,String userId)throws Exception;
	/**
	 * 保存会商时的意见附件
	 * */
	public TDataFile saveYJFuJian(MultipartFile multipartFile,HttpServletRequest request,String pid,String fileType)throws AppException;
	
	/**
	 * 根据接收人列表和当前用户查询是否回复
	 * */
	public String ishuiFu(String recListId,String userId,String mailId)throws Exception;
	
	/**
	 * 查询未读会商的数量
	 * @return
	 */
	public int getwdhsCount();
	
	
}
