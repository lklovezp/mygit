package com.hnjz.app;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hnjz.common.util.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.data.po.TDataFile;

/**
 * 
 * 作者：时秋寒
 * 
 */
@Controller
public class CommonController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(CommonController.class);
	@Autowired
	private CommonManager commonManager;
	
	@Autowired
	private Dao dao;
	
	/**
	 * 
	 * 函数介绍：打开单附件上传页面
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/singleUploadPage.htm")
	public String singleUploadPage(ModelMap model, FileForm frm) {
		String code = FileTypeEnums.getTypeByEnumName(frm.getFileType());
		String ext = commonManager.getExtension(code);
		String e = "";
		for (int i = 0; i < ext.split(";").length; i++) {
			if (i > 0){
				e += ";";
			}
			e += ext.split(";")[i].substring(2, ext.split(";")[i].length());
		}
		frm.setExtension(e);
		return "common/singleUpload";
	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadfiles")
	public void fileupload(ModelMap model, @RequestParam(value = "upload_file", required = true) MultipartFile multipartFile,
			HttpServletResponse httpServletResponse, HttpServletRequest request, PrintWriter writer) {
		try {
			commonManager.uploadFile(multipartFile, request);
			writer.print("{\"resultcode\":0,\"msg\":\"上传成功。\"}");
		} catch (AppException e) {
			e.printStackTrace();
			writer.print("{\"resultcode\":1,\"msg\":\"上传失败。\"}");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadSingleFile")
	public void uploadSingleFile(ModelMap model, @RequestParam(value = "file", required = true) MultipartFile multipartFile,
			HttpServletResponse response, HttpServletRequest request) {
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			
			writer = response.getWriter();
			commonManager.uploadSingleFile(multipartFile, request);
			writer.print("{\"state\":\"true\",\"msg\":\"上传成功。\"}");
		} catch (Exception e) {
			e.printStackTrace();
			writer.print("{\"state\":\"false\",\"msg\":\"上传失败。\"}");
		}
	}
	/**
	 * 
	 * 函数介绍：状态（有效无效）下拉列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/ztList.json")
	@ResponseBody
	public List<Combobox> ztList(ModelMap model) {
		return commonManager.queryZtList();
	}
	/**
	 * 
	 * 函数介绍：打开公用导入页面
	 * 
	 * 输入参数：执法对象类型
	 * 
	 * 返回值：执法对象类型
	 */
	@RequestMapping(value = "/importPage.htm")
	public String importPage(ModelMap model, String lawObjType) {
		model.addAttribute("lawObjType", lawObjType);
		return "common/import";
	}
	
	/**
	 * 
	 * 函数介绍：生成模板
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/downTemplate")
	public void downTemplate(HttpServletResponse res) {
		commonManager.downTemplate(res);
	}

	/**
	 * 
	 * 函数介绍：生成模板
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/importTemplate.htm")
	public void importTemplate(ModelMap model, @RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		String tishi = "请下载正确的模板！";
		try {
			writer = response.getWriter();
			commonManager.importTemplate(file);
			writer.print("{\"state\":true,\"msg\":\"上传成功\"}");
		} catch (Exception e) {
			log.error("保存信息错误：", e);
			writer.print("{\"state\":false,\"msg\":\"" + tishi + "\"}");
		}
	}
	
	/**
	 * 文件下载通用方法
	 * 
	 * @param id:单个文件id
	 */
	@RequestMapping(value = "/downloadFile")
	public void downloadFile(String id, HttpServletResponse res) {
		try {
			commonManager.downloadFile(id, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 单个文件删除通用方法
	 * 
	 * @param id:单个文件id
	 */
	@RequestMapping(value = "/removeFile.json")
	public void removeFile(ModelMap model, String id) {
		try {
			commonManager.removeFile(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	
	
	/**
	 * 通过pid删除文件 通用方法
	 * 
	 * @param pid:单个文件id
	 */
	@RequestMapping(value = "/removeFileByPid")
	public void removeFileByPid(String pid) {
		try {
			commonManager.removeFileByPid(pid);
		} catch (Exception e) {
			log.error("发生错误：", e);
		}
	}
	
	/**
	 * 
	 * 函数介绍：通过pid查询附件列表
	
	 * 输入参数：pid ： 任务id
	 * 	canDel ：是否有删除按钮
	
	 * 返回值：
	 */
	@RequestMapping(value = "/queryFileList.json")
    public void queryFileList(ModelMap model,String pid, String canDel,String fileType,String tableId ) {
    	try {
//    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = commonManager.queryFileList(pid, canDel,fileType,tableId);
    		JsonResultUtil.fyWeb(model, re);
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("查询出错：", e);
    	}
    }
	
	/**
	 * 
	 * 函数介绍：通过pid查询附件列表
	
	 * 输入参数：pid ： 任务id
	 * 	canDel ：是否有删除按钮
	
	 * 返回值：
	 */
	@RequestMapping(value = "/queryFileListMulfileType.json")
    public void queryFileListMulfileType(ModelMap model,String pid, String canDel,String fileType,String tableId, String page,String rows) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = commonManager.queryFileListMulfileType(pid, canDel,fileType,tableId, page, rows);
    		JsonResultUtil.fyWeb(model, re);
    	} catch (Exception e) {
    		e.printStackTrace();
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
	@RequestMapping(value = "/uploadPage.htm")
	public String uploadPage(ModelMap model, FileForm frm, HttpServletRequest request) {
		//String code = FileTypeEnums.getTypeByEnumName(frm.getFileType());
		frm.setExtension("*.doc;*.docx;*.pdf;*.png;*.jpg;*.jpeg;*.bmp;*.xls;*.xlsx");
		String JSESSIONID = request.getSession().getId();
		model.addAttribute("JSESSIONID", JSESSIONID);
		return "common/upload";
	}
	
	/**
	 * 文件下载通用方法
	 * 
	 * @param id:单个文件id
	 */
	@RequestMapping(value = "/downZipFile")
	public void downZipFile(String id, HttpServletResponse res) {
		try {
			List<String> ids = new ArrayList<String>();
			for (int i = 0; i < id.split(",").length; i++) {
				ids.add(id.split(",")[i]);
			}
			commonManager.downZipFile(ids, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件预览
	 * 
	 * @param id:文件id
	 */
	@RequestMapping(value = "/preview.htm")
	public String preview(ModelMap model, String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			HashMap<String, Object> data = commonManager.preview(id, request);
			model.addAttribute("content", data.get("content"));
			model.addAttribute("ext", data.get("ext"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "common/preview";
	}

	/**
	 * 图片预览
	 *
	 * @param id:文件id
	 */
	@RequestMapping(value = "/imgView.htm")
	public String imgView(ModelMap model, String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			HashMap<String, Object> data = commonManager.imgView(id,request);
			model.addAttribute("filePath",data.get("filePath"));
			model.addAttribute("last",data.get("last"));
			model.addAttribute("next",data.get("next"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "common/imgView";
	}

	/**
	 * 图片预览
	 *
	 * @param id:文件id
	 */
	@RequestMapping(value = "/pdfView.htm")
	public String pdfView(ModelMap model, String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			HashMap<String, Object> data = commonManager.pdfView(id,request);
			model.addAttribute("filePath",data.get("filePath"));
			model.addAttribute("last",data.get("last"));
			model.addAttribute("next",data.get("next"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "common/pdfView";
	}
	
	/**
	 * 金格中间件word和excel的预览
	 * 把文件生成到服务器目录下
	 * @param id:文件id
	 */
	@RequestMapping(value = "/docpreview.htm")
	public String docpreview(ModelMap model, String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			HashMap<String, Object> data = commonManager.docView(id, request);
			model.addAttribute("filePath",data.get("filePath"));
			model.addAttribute("last",data.get("last"));
			model.addAttribute("next",data.get("next"));
			model.addAttribute("fileExtension","."+data.get("fileExtension"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "common/docView";
	}
	
	/**
     * 在线预览图片
     */
    @RequestMapping("/showImage.do")
    public @ResponseBody
    void showImage(String path) throws IOException {
        getResponse().setContentType("text/html; charset=UTF-8");
        getResponse().setContentType("image/jpeg");
        String fullFileName = getRealPath("/upload/" + path);
        FileInputStream fis = new FileInputStream(fullFileName);
        OutputStream os = getResponse().getOutputStream();
        try {
            int count = 0;
            byte[] buffer = new byte[1024 * 1024];
            while ((count = fis.read(buffer)) != -1)
                os.write(buffer, 0, count);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null)
                os.close();
            if (fis != null)
                fis.close();
        }
    }

	private String getRealPath(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private ServletResponse getResponse() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
