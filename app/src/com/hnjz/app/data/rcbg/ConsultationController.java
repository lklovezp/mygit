package com.hnjz.app.data.rcbg;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileForm;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.po.TDataMail;
import com.hnjz.app.data.po.TDataMailyj;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.LogUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
/**
 * 
 * ���ߣ�likun
 * ����������
		���̹���
 *
 */
@Controller
public class ConsultationController {
	private static final Log log=LogFactory.getLog(ConsultationController.class);
	@Autowired
	private ConsultationManager conManager;
	@Autowired
	private CommonManager commonManager;
	 /** ���Ź��� */
    @Autowired
    private OrgManager orgManager;
	/**
	 * ��ת�����ͻ��̽���
	 * */
	@RequestMapping(value="/sendConsultation.htm")
	public String queryInformation(ModelMap model,String title){
		TSysUser cur = CtxUtil.getCurUser();
		TSysOrg curOrg = orgManager.getOrgByUserid(cur.getId());
		if(curOrg != null){
			if("a0000000000000000000000000000000".equals(curOrg.getArea().getId())){
				model.put("isZd", "0");
			}
		}
		model.addAttribute("title", title);
		return "app/data/rcbg/sendConsultation";
	}
	/**
	 * ��ѯ�û�Tree
	 * 
	 * */
	@RequestMapping(value = "/queryUserTree.htm")
	public String taskUserPubQuery(ModelMap model, String methodname, String multi,String ids, String org) {
		try {
			List<String> id = new ArrayList<String>();
			if(StringUtil.isNotBlank(ids)){
				for (int i = 0; i < ids.split(",").length; i++) {
					id.add(ids.split(",")[i]);
				}
			}
			JSONArray re = new JSONArray();
			//0 �ǰ����ţ�1 �ǰ�����
			if("0".equals(org)){
				re =conManager.queryUserByArea(id);
			}else{
				re =conManager.queryUserByGroup(id);
			}
			model.addAttribute("menu", re.toString());
			model.addAttribute("methodname", methodname);
			model.addAttribute("multi", multi);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "app/data/rcbg/userZtree";
	}
	/**
	 * ����mail��Ϣ
	 * 
	 * */
	@RequestMapping(value = "/saveMailForm.json")
	public void saveMailForm(ModelMap model, MailForm mailForm, String note) {
		try {
			//if (log.isDebugEnabled()) {log.debug("������Ϣ:" + LogUtil.m(mailForm));}
			mailForm.setContent(note);
			TDataMail tm=conManager.saveConsultation(mailForm);
			model.addAttribute("mailId", tm.getId());
			JsonResultUtil.suncess(model, "���ͳɹ���");
		} catch (Exception e) {
			JsonResultUtil.fail(model, "����ʧ�ܣ�");
			e.printStackTrace();
		}
	}
	/**
	 * ����mail��Ϣ
	 * 
	 * */
	@RequestMapping(value = "/saveSendConsultation.json")
	public void saveSendConsultation(ModelMap model, MailForm mailForm, String note) {
		try {
			//if (log.isDebugEnabled()) {log.debug("������Ϣ:" + LogUtil.m(mailForm));}
			mailForm.setContent(note);
			TDataMail tm=conManager.saveSendConsultation(mailForm);
			model.addAttribute("mailId", tm.getId());
			JsonResultUtil.suncess(model, "���ͳɹ���");
		} catch (Exception e) {
			JsonResultUtil.fail(model, "����ʧ�ܣ�");
			e.printStackTrace();
		}
	}
	/**
	 * ��ת�����ջ���
	 * */
	@RequestMapping(value="/recConsultation.htm")
	public String recConsultation(ModelMap model,String title){
		model.addAttribute("title", title);
		return "app/data/rcbg/recConsultList";
	}
	/**
	 *���ջ����б���ѯ
	 */
	@RequestMapping(value = "/queryRecConsultList.json", produces = "application/json")
	public void queryRecConsultList(ModelMap model,String startTime,String endTime,
			String pfrId,String topic, String page,String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = conManager.queryRecConsultation("Y", startTime, endTime,
					pfrId,topic, page, pageSize);
			 model.addAttribute("arr", re.getRows());
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}
	/**
	 * �鿴�����ջ�������
	 * */
	@RequestMapping(value="/recMailInfo.htm")
	public String recMailInfo(ModelMap model,String id){
		try {
			conManager.saveIsRead(id);
			MailForm mailForm=conManager.queryMailByMailId(id);
			TDataMail tm=conManager.findMailByMailId(conManager.queryRecMailById(id).getMailId());
			String isHuiFu=conManager.ishuiFu(tm.getRecList(), CtxUtil.getUserId(), tm.getId());
			IsReadForm isReadForm=conManager.queryIsReadFormByMailId(id, "");
			List<MailyjForm> yjList=conManager.queryAllYiJianByMailId(mailForm.getId());
			List<FileForm> list=conManager.queryFileFormList(mailForm.getId());
			model.addAttribute("isHuiFu", isHuiFu);
			model.addAttribute("list", list);
			model.addAttribute("mailForm", mailForm);
			model.addAttribute("recMailId", id);
			model.addAttribute("isReadForm", isReadForm);
			model.addAttribute("yjList", yjList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/data/rcbg/recMailInfo";
	}
	/**
	 * �鿴���ѷ���������
	 * */
	@RequestMapping(value="/sendMailInfo.htm")
	public String sendMailInfo(ModelMap model,String id){
		try {
			MailForm mailForm=conManager.queryMailById(id);
			IsReadForm isReadForm=conManager.queryIsReadFormById(id, "");
			List<MailyjForm> yjList=conManager.queryAllYiJianByMailId(mailForm.getId());
			List<FileForm> list=conManager.queryFileFormList(id);
			model.addAttribute("list", list);
			model.addAttribute("mailForm", mailForm);
			model.addAttribute("recMailId", id);
			model.addAttribute("isReadForm", isReadForm);
			model.addAttribute("yjList", yjList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/data/rcbg/sendMailInfo";
	}
	/**
	 * ��ת���ظ����ҳ��
	 * */
	@RequestMapping(value="/huiFuYiJian.htm")
	public String huiFuYiJian(ModelMap model,String mailId,String recMailId){
		try {
			model.addAttribute("mailId", mailId);
			model.addAttribute("recMailId", recMailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/data/rcbg/huiFuYiJian";
	}
	/**
	 * ֱ�ӹرհ�ťʱ��ת���ظ����ҳ��
	 * */
	@RequestMapping(value="/guanBiHuiFu.htm")
	public String guanBiHuiFu(ModelMap model,String mailId,String recMailId){
		try {
			model.addAttribute("mailId", mailId);
			model.addAttribute("recMailId", recMailId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "app/data/rcbg/guanBiHuiFu";
	}
	
	/**
	 * ���������Ϣ
	 * 
	 * */
	@RequestMapping(value = "/saveYiJian.json")
	public void saveYiJian(ModelMap model, MailyjForm mailyjForm, String recMailId,String yjId) {
		try {
			//if (log.isDebugEnabled()) {log.debug("������Ϣ:" + LogUtil.m(mailyjForm));}
			//mailForm.setContent(note);
			//TDataMail tm=conManager.saveConsultation(mailForm);
			TDataMailyj tm=conManager.saveYiJian(mailyjForm, recMailId,yjId);
			model.addAttribute("yjId", tm.getId());
			JsonResultUtil.suncess(model, "�ظ��ɹ���");
		} catch (Exception e) {
			JsonResultUtil.fail(model, "�ظ�ʧ�ܣ�");
			e.printStackTrace();
		}
	}
	/**
	 * ��ת���ѷ�����
	 * */
	@RequestMapping(value="/yiSendMail.htm")
	public String yiSendMail(ModelMap model,String title){
		model.addAttribute("title", title);
		return "app/data/rcbg/yiSendMailList";
	}
	
	/**
	 *�ѷ������б���ѯ
	 */
	@RequestMapping(value = "/queryYiSendConsultList.json", produces = "application/json")
	public void queryYiSendConsultList(ModelMap model,String startTime,String endTime,
			String recId,String topic,String page,String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = conManager.queryYiSendConsultation("Y",startTime ,endTime,recId,topic,page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}
	/**
	 *ɾ�����ջ���
	 */
	@RequestMapping(value = "/delRecMail.json", produces = "application/json")
	public void delRecMail(ModelMap model,String id) {
		try {
			conManager.delRecMailById(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			JsonResultUtil.fail(model, "ɾ��ʧ�ܣ�");
			log.error("��ѯ������", e);
		}
	}
	/**
	 *ɾ�����ջ���
	 */
	@RequestMapping(value = "/delYiSendMail.json", produces = "application/json")
	public void delYiSendMail(ModelMap model,String id) {
		try {
			conManager.delYiSendMailById(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			JsonResultUtil.fail(model, "ɾ��ʧ�ܣ�");
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * 
	 * �������ܣ��򿪹����ϴ�ҳ��
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/uploadSendmailFuJian.htm")
	public String uploadSendmail(ModelMap model, HttpServletRequest request,FileForm frm,String mailId) {
		String JSESSIONID = request.getSession().getId();
		//frm.setExtension(commonManager.getExtension(code));
		frm.setPid(mailId);
		frm.setFileType(CtxUtil.getUserId());
		//frm.setPath("XXGL");
		model.addAttribute("JSESSIONID", JSESSIONID);
		return "app/data/rcbg/uploadSendMail";
	}
	/**
	 * 
	 * �������ܣ��򿪹����ϴ�ҳ��
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/uploadYJFuJian.htm")
	public String uploadYJFuJian(ModelMap model, HttpServletRequest request,FileForm frm,String mailId,String yjId) {
		String JSESSIONID = request.getSession().getId();
		//frm.setExtension(commonManager.getExtension(code));
		frm.setPid(yjId);
		frm.setFileType(CtxUtil.getUserId());
		//frm.setPath("XXGL");
		model.addAttribute("JSESSIONID", JSESSIONID);
		return "app/data/rcbg/uploadYJFuJan";
	}
	
	@RequestMapping(value = "/saveSendMailFuJian")
	public void saveSendMailFuJian(ModelMap model, @RequestParam(value = "upload_file", required = true) MultipartFile multipartFile,
			HttpServletResponse httpServletResponse, HttpServletRequest request, PrintWriter writer) {
		try {
			
			//TDataFile tDataFile=unzipManager.uploadFile(multipartFile, request);
			//unzipManager.unZipAndGetData(is, td, path, outUnZipPath);
			String fileType="HSFJFSHSFJ";
			TDataFile tDataFile=conManager.saveFuJian(multipartFile, request, "", fileType);
			model.addAttribute("mailId", tDataFile.getPid());
			writer.print("{\"resultcode\":0,\"msg\":\"�ϴ��ɹ���\"}");
			
		} catch (AppException e) {
			log.error(e.getMessage(),e);
			writer.print("{\"resultcode\":1,\"msg\":\"�ϴ�ʧ�ܡ�\"}");
		}catch (Exception e){
			log.error(" ���ܳ�����",e);
			writer.print("{\"resultcode\":1,\"msg\":\"ϵͳʧ�ܡ�\"}");
		}
		
	}
	@RequestMapping(value = "/saveYJFuJian")
	public void saveYJFuJian(ModelMap model, @RequestParam(value = "upload_file", required = true) MultipartFile multipartFile,
			HttpServletResponse httpServletResponse, HttpServletRequest request, PrintWriter writer) {
		try {
			
			//TDataFile tDataFile=unzipManager.uploadFile(multipartFile, request);
			//unzipManager.unZipAndGetData(is, td, path, outUnZipPath);
			String fileType="HSFJFSHSFJ";
			TDataFile tDataFile=conManager.saveYJFuJian(multipartFile, request, "", fileType);
			model.addAttribute("mailId", tDataFile.getPid());
			writer.print("{\"resultcode\":0,\"msg\":\"�ϴ��ɹ���\"}");
			
		} catch (AppException e) {
			log.error(e.getMessage(),e);
			writer.print("{\"resultcode\":1,\"msg\":\"�ϴ�ʧ�ܡ�\"}");
		}catch (Exception e){
			log.error(" ���ܳ�����",e);
			writer.print("{\"resultcode\":1,\"msg\":\"ϵͳʧ�ܡ�\"}");
		}
		
	}
	/**
	 * 
	 * �������ܣ���ѯ���̸�����Ϣ
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/getFileList.json")
	public void getFileList(ModelMap model, String mailId) {
		try {
//			log.info("cehsi==========="+mailId);
//			log.info("cehsi==========="+mailId);
//			log.info("cehsi==========="+mailId);
			List<Map<String, String>> listMap=conManager.queryFileList(mailId);
			model.addAttribute("listMap", listMap);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}
	/**
	 * 
	 * �������ܣ���ѯ�������������Ϣ
	 * 
	 * ���������
	 * 
	 * ����ֵ��
	 */
	@RequestMapping(value = "/getFileListByUserId.json")
	public void getFileListByUserId(ModelMap model, String yjId) {
		try {
//			log.info("cehsi==========="+mailId);
//			log.info("cehsi==========="+mailId);
//			log.info("cehsi==========="+mailId);
			
			List<Map<String, String>> listMap=conManager.queryFileListByUserId(yjId, CtxUtil.getUserId());
			model.addAttribute("listMap", listMap);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}
	
	/**
	 * ɾ������¼
	 * 
	 * @param id:�����ļ�id
	 */
	@RequestMapping(value = "/removeMailFuJian.json")
	public void removeJcbl(ModelMap model, String id) {
		try {
			commonManager.removeFile(id);//ɾ���ļ�
			
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	/**
	 * ��ת���ɹ�������
	 * */
	@RequestMapping(value="/sendSuccess.htm")
	public String sendSuccess(ModelMap model,String title){
		model.addAttribute("title", title);
		return "app/data/rcbg/sendSuccess";
	}
	
	/**
	 * ͨ��Id��ѯ����
	 * 
	 * @param id:�����ļ�id
	 */
	@RequestMapping(value = "/findMailById.json")
	public void findMailById(ModelMap model, String id) {
		try {
			MailForm mf=conManager.queryMailById(id);//ɾ���ļ�
			String topic="";
			if(mf.getTopic().length()>4){
				topic=mf.getTopic().substring(0, 4);
			}else{
				topic=mf.getTopic();
			}
			model.addAttribute("topic", topic);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ͨ��recId��ѯ����
	 * 
	 * @param id:�����ļ�id
	 */
	@RequestMapping(value = "/findMailByRecId.json")
	public void findMailByRecId(ModelMap model, String id) {
		try {
			MailForm mf=conManager.queryMailByMailId(id);//ɾ���ļ�
			String topic="";
			if(mf.getTopic().length()>4){
				topic=mf.getTopic().substring(0, 4);
			}else{
				topic=mf.getTopic();
			}
			model.addAttribute("topic",topic );
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
}