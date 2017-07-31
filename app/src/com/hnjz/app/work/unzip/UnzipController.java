package com.hnjz.app.work.unzip;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileForm;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.common.AppException;
import com.hnjz.common.JsonResultUtil;
@Controller
public class UnzipController {
	private static final Log log=LogFactory.getLog(UnzipController.class);
	@Autowired
	private UnzipManager unzipManager;
	@Autowired
	private CommonManager commonManager;
	
	
	//解析上传的rar文件
	    
		@RequestMapping(value = "/jieXiUploadPage.htm")
		public String jieXiUploadPage(ModelMap model, FileForm frm, HttpServletRequest request) {
			String code = FileTypeEnums.getTypeByEnumName(frm.getFileType());
			frm.setExtension(commonManager.getExtension(code));
			frm.setPid("55");
			frm.setPath("XXGL");
			String JSESSIONID = request.getSession().getId();
			model.addAttribute("JSESSIONID", JSESSIONID);
			return "common/jieXiUpload";
		}
		@RequestMapping(value="/findCompany.json", produces = "application/json")
		public void findCompany(ModelMap model,String fileName){
		
			log.info("fileName:=="+fileName);
			try {
				String result=unzipManager.findCompany(fileName);
				log.info("result:=="+result);
				model.addAttribute("msg",result);
				
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		@RequestMapping(value = "/jieXiUploadfiles")
		public void jieXiFileupload(ModelMap model, @RequestParam(value = "upload_file", required = true) MultipartFile multipartFile,
				HttpServletResponse httpServletResponse, HttpServletRequest request, PrintWriter writer) {
			try {
				
				TDataFile tDataFile=unzipManager.uploadFile(multipartFile, request);
				//unzipManager.unZipAndGetData(is, td, path, outUnZipPath);
				
				writer.print("{\"resultcode\":0,\"msg\":\"上传成功。\"}");
				
			} catch (AppException e) {
				log.error(e.getMessage(),e);
				writer.print("{\"resultcode\":1,\"msg\":\"上传失败。\"}");
			}catch (Exception e){
				log.error(" 功能出错：",e);
				writer.print("{\"resultcode\":1,\"msg\":\"系统失败。\"}");
			}
			
		}
		
		
		
}
