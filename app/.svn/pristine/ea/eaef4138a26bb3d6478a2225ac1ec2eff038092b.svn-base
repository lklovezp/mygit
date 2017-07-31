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
 * 作者：likun
 * 功能描述：
		会商功能
 *
 */
@Controller
public class ConsultationController {
	private static final Log log=LogFactory.getLog(ConsultationController.class);
	@Autowired
	private ConsultationManager conManager;
	@Autowired
	private CommonManager commonManager;
	 /** 部门管理 */
    @Autowired
    private OrgManager orgManager;
	/**
	 * 跳转到发送会商界面
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
	 * 查询用户Tree
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
			//0 是按部门，1 是按分组
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
	 * 保存mail信息
	 * 
	 * */
	@RequestMapping(value = "/saveMailForm.json")
	public void saveMailForm(ModelMap model, MailForm mailForm, String note) {
		try {
			//if (log.isDebugEnabled()) {log.debug("表单信息:" + LogUtil.m(mailForm));}
			mailForm.setContent(note);
			TDataMail tm=conManager.saveConsultation(mailForm);
			model.addAttribute("mailId", tm.getId());
			JsonResultUtil.suncess(model, "发送成功！");
		} catch (Exception e) {
			JsonResultUtil.fail(model, "发送失败！");
			e.printStackTrace();
		}
	}
	/**
	 * 保存mail信息
	 * 
	 * */
	@RequestMapping(value = "/saveSendConsultation.json")
	public void saveSendConsultation(ModelMap model, MailForm mailForm, String note) {
		try {
			//if (log.isDebugEnabled()) {log.debug("表单信息:" + LogUtil.m(mailForm));}
			mailForm.setContent(note);
			TDataMail tm=conManager.saveSendConsultation(mailForm);
			model.addAttribute("mailId", tm.getId());
			JsonResultUtil.suncess(model, "发送成功！");
		} catch (Exception e) {
			JsonResultUtil.fail(model, "发送失败！");
			e.printStackTrace();
		}
	}
	/**
	 * 跳转到已收会商
	 * */
	@RequestMapping(value="/recConsultation.htm")
	public String recConsultation(ModelMap model,String title){
		model.addAttribute("title", title);
		return "app/data/rcbg/recConsultList";
	}
	/**
	 *已收会商列表查询
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
			log.error("查询出错：", e);
		}
	}
	/**
	 * 查看到已收会商详情
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
	 * 查看到已发会商详情
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
	 * 跳转到回复意见页面
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
	 * 直接关闭按钮时跳转到回复意见页面
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
	 * 保存意见信息
	 * 
	 * */
	@RequestMapping(value = "/saveYiJian.json")
	public void saveYiJian(ModelMap model, MailyjForm mailyjForm, String recMailId,String yjId) {
		try {
			//if (log.isDebugEnabled()) {log.debug("表单信息:" + LogUtil.m(mailyjForm));}
			//mailForm.setContent(note);
			//TDataMail tm=conManager.saveConsultation(mailForm);
			TDataMailyj tm=conManager.saveYiJian(mailyjForm, recMailId,yjId);
			model.addAttribute("yjId", tm.getId());
			JsonResultUtil.suncess(model, "回复成功！");
		} catch (Exception e) {
			JsonResultUtil.fail(model, "回复失败！");
			e.printStackTrace();
		}
	}
	/**
	 * 跳转到已发会商
	 * */
	@RequestMapping(value="/yiSendMail.htm")
	public String yiSendMail(ModelMap model,String title){
		model.addAttribute("title", title);
		return "app/data/rcbg/yiSendMailList";
	}
	
	/**
	 *已发会商列表查询
	 */
	@RequestMapping(value = "/queryYiSendConsultList.json", produces = "application/json")
	public void queryYiSendConsultList(ModelMap model,String startTime,String endTime,
			String recId,String topic,String page,String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = conManager.queryYiSendConsultation("Y",startTime ,endTime,recId,topic,page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("查询出错：", e);
		}
	}
	/**
	 *删除已收会商
	 */
	@RequestMapping(value = "/delRecMail.json", produces = "application/json")
	public void delRecMail(ModelMap model,String id) {
		try {
			conManager.delRecMailById(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			JsonResultUtil.fail(model, "删除失败！");
			log.error("查询出错：", e);
		}
	}
	/**
	 *删除已收会商
	 */
	@RequestMapping(value = "/delYiSendMail.json", produces = "application/json")
	public void delYiSendMail(ModelMap model,String id) {
		try {
			conManager.delYiSendMailById(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			JsonResultUtil.fail(model, "删除失败！");
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 
	 * 函数介绍：打开公用上传页面
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
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
	 * 函数介绍：打开公用上传页面
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
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
			writer.print("{\"resultcode\":0,\"msg\":\"上传成功。\"}");
			
		} catch (AppException e) {
			log.error(e.getMessage(),e);
			writer.print("{\"resultcode\":1,\"msg\":\"上传失败。\"}");
		}catch (Exception e){
			log.error(" 功能出错：",e);
			writer.print("{\"resultcode\":1,\"msg\":\"系统失败。\"}");
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
			writer.print("{\"resultcode\":0,\"msg\":\"上传成功。\"}");
			
		} catch (AppException e) {
			log.error(e.getMessage(),e);
			writer.print("{\"resultcode\":1,\"msg\":\"上传失败。\"}");
		}catch (Exception e){
			log.error(" 功能出错：",e);
			writer.print("{\"resultcode\":1,\"msg\":\"系统失败。\"}");
		}
		
	}
	/**
	 * 
	 * 函数介绍：查询会商附件信息
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
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
			log.error("查询出错：", e);
		}
	}
	/**
	 * 
	 * 函数介绍：查询会商意见附件信息
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
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
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 删除监察笔录
	 * 
	 * @param id:单个文件id
	 */
	@RequestMapping(value = "/removeMailFuJian.json")
	public void removeJcbl(ModelMap model, String id) {
		try {
			commonManager.removeFile(id);//删除文件
			
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	/**
	 * 跳转到成功发会商
	 * */
	@RequestMapping(value="/sendSuccess.htm")
	public String sendSuccess(ModelMap model,String title){
		model.addAttribute("title", title);
		return "app/data/rcbg/sendSuccess";
	}
	
	/**
	 * 通过Id查询会商
	 * 
	 * @param id:单个文件id
	 */
	@RequestMapping(value = "/findMailById.json")
	public void findMailById(ModelMap model, String id) {
		try {
			MailForm mf=conManager.queryMailById(id);//删除文件
			String topic="";
			if(mf.getTopic().length()>4){
				topic=mf.getTopic().substring(0, 4);
			}else{
				topic=mf.getTopic();
			}
			model.addAttribute("topic", topic);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 通过recId查询会商
	 * 
	 * @param id:单个文件id
	 */
	@RequestMapping(value = "/findMailByRecId.json")
	public void findMailByRecId(ModelMap model, String id) {
		try {
			MailForm mf=conManager.queryMailByMailId(id);//删除文件
			String topic="";
			if(mf.getTopic().length()>4){
				topic=mf.getTopic().substring(0, 4);
			}else{
				topic=mf.getTopic();
			}
			model.addAttribute("topic",topic );
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
}
