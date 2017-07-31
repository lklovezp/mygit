package com.hnjz.app.common;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.IndexManager;
import com.hnjz.app.data.enums.JcxEnum;
import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.po.TBizBlmbcs;
import com.hnjz.app.work.po.TBizMoreCheck;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.user.UserManager;

/**
 * 
 * 作者：renzhengquan 生成日期：2015-3-9 功能描述： 公共功能控制层
 * 
 */
@Controller
public class CommonController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(CommonController.class);
	@Autowired
	private CommonManager commonManager;
	
	@Autowired
	private CommWorkManager commWorkManager;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
    private IndexManager     indexManager;
	/**
	 * 
	 * 函数介绍：控制类型下拉列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/kzlxList.json")
	@ResponseBody
	public List<Combobox> kzlxList(ModelMap model) {
		return commonManager.queryKzlxList();
	}

	
	/**
	 * 
	 * 函数介绍：控制类型下拉列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/typetree.json")
	@ResponseBody
	public List<Combobox> typeList(ModelMap model) {
		return commonManager.queryLawobjtypeTree();
	}
	/**
	 * 
	 * 函数介绍：获取字典中数据下拉列表 （ 通用接口）
	 * 
	 * 输入参数：type:字典类型
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/dicList.json")
	@ResponseBody
	public List<Combobox> dicList(ModelMap model, String type) {
		return commonManager.queryTSysDicList(type);
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
	 * 函数介绍：企业状态（运营中、已关闭、已停产）下拉列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/qyztList.json")
	@ResponseBody
	public List<Combobox> qyztList(ModelMap model) {
		return commonManager.queryQyztList();
	}
	
	/**
	 * 
	 * 函数介绍：状态(是否)下拉列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/sfList.json")
	@ResponseBody
	public List<Combobox> sfList(ModelMap model) {
		return commonManager.querySfList();
	}

	/**
	 * 
	 * 函数介绍：行业下拉列表
	 * 
	 * 输入参数：lawobjType-执法对象类型
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/industryList.json")
	@ResponseBody
	public List<Combobox> industryList(ModelMap model, String lawobjType) {
		return commonManager.queryIndustryList(lawobjType);
	}

	/**
	 * 
	 * 函数介绍：附件类型列表
	 * 
	 * 输入参数：附件类型编号，支持模糊查询
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/fjlxList.json")
	@ResponseBody
	public List<Combobox> fjlxList(ModelMap model, String enumName) {
		return commonManager.queryFjlxList(enumName);
	}

	/**
	 * 
	 * 函数介绍：执法对象所属行政区树
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/regionTree.json")
	@ResponseBody
	public List<ComboboxTree> regionTree(ModelMap model) {
		return commonManager.queryRegionTree();
	}
    
	/**
	 * 
	 * 函数介绍：网格树
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/wgTree.json")
	@ResponseBody
	public List<Combobox> wgTree(ModelMap model) {
		return commonManager.queryWgTree();
	}
	/**
	 * 
	 * 函数介绍：执法对象所属监管部门树
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/orgTree.json")
	@ResponseBody
	public List<ComboboxTree> orgTree(ModelMap model) {
		return commonManager.queryOrgTree();
	}
	
	/**
	 * 
	 * 函数介绍：违法类型下拉树
	 * 
	 * 输入参数：无
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/illegalTypeList.json")
	@ResponseBody
	public List<ComboboxTree> illegalTypeList(ModelMap model, String ids) {
		return commonManager.queryIllegalTypeList(ids);
	}
	
	@RequestMapping(value = "/illegalTypeByTasktypeList.json")
	@ResponseBody
	public List<ComboboxTree> illegalTypeByTasktypeList(ModelMap model, String ids,String tasktype) {
		return commonManager.queryIllegalTypeByTasktypeList(ids,tasktype);
	}

	/**
	 * 
	 * 函数介绍：任务类型下拉树
	 * 
	 * 输入参数：无
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/taskTypeTreeCombo.json")
	@ResponseBody
	public List<ComboboxTree> taskTypeTreeCombo(ModelMap model, String oper, String markId) {
		return commonManager.queryTaskTypePubTree(markId);
	}
	
	/**
	 * 查询当前用户下的区域
	 */
	@RequestMapping(value = "/queryAreaCombo.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryArea() {
		try {
			return commonManager.queryAreaCombo(CtxUtil.getAreaId());
		} catch (Exception e) {
			log.error("查询错误：", e);
		}
		return null;
	}
	/**
	 * 查询当前用户及以下的区域
	 */
	@RequestMapping(value = "/queryAreaComboWithCur.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryAreaComboWithCur() {
		try {
			return commonManager.queryAreaComboWithCur(CtxUtil.getAreaId());
		} catch (Exception e) {
			log.error("查询错误：", e);
		}
		return null;
	}
	
	/**
	 * 查询当前用的区域,如果是admin查询所有的区域
	 */
	@RequestMapping(value = "/queryAreaCur.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryAreaCur() {
		try {
			return commonManager.queryAreaCur(CtxUtil.getAreaId());
		} catch (Exception e) {
			log.error("查询错误：", e);
		}
		return null;
	}
	
	/**
	 * 
	 * 函数介绍：获取任务状态下拉列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/queryRwztCombo.json", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryRwztCombo() {
		return commonManager.queryRwztCombo();
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
		String code = FileTypeEnums.getTypeByEnumName(frm.getFileType());
		frm.setExtension(commonManager.getExtension(code));
		
		String JSESSIONID = request.getSession().getId();
		model.addAttribute("JSESSIONID", JSESSIONID);
		return "common/upload";
	}
	
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
	public void downTemplate(String lawObjType, HttpServletResponse res) {
		commonManager.downTemplate(lawObjType, res);
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
	public void importTemplate(ModelMap model, String lawObjType, @RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			commonManager.importTemplate(lawObjType, file);
			
			writer.print("{\"state\":true,\"msg\":\"上传成功\"}");
		} catch (Exception e) {
			log.error("保存信息错误：", e);
			writer.print("{\"state\":false,\"msg\":\"" + e.getMessage() + "\"}");
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
	 * 文件导出带基本信息通用方法
	 * 
	 * @param id:单个文件id
	 */
	@RequestMapping(value = "/exportFile")
	public void exportFile(String id, String applyId, HttpServletResponse res) {
		try {
			commonManager.exportFile(id, applyId, res);
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
	 * 删除监察笔录
	 * 
	 * @param id:单个文件id
	 */
	@RequestMapping(value = "/removeJcbl.json")
	public void removeJcbl(ModelMap model, String id) {
		try {
			commonManager.removeFile(id);//删除文件
			//删除
			TBizMoreCheck mc = commWorkManager.getMoreCheck(id);
			if(mc!=null){
				commWorkManager.removeMoreCheck(mc);
			}
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发生错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * 删除在线制作监察笔录
	 * 
	 * @param id:单个文件id
	 */
	@RequestMapping(value = "/removeZxzzJcbl.json")
	public void removeZxzzJcbl(ModelMap model, String id) {
		try {
			commonManager.removeFile(id);//删除文件
			//删除
			TBizBlmbcs bc = commWorkManager.getBlmbc(id);
			if(bc!=null){
				commWorkManager.removeBlmbc(bc);
			}
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
	 * @param id:单个文件id
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
    public void queryFileList(ModelMap model,String pid, String canDel,String fileType,String tableId, String page,String rows) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = commonManager.queryFileList(pid, canDel,fileType,tableId, page, rows);
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
	 * 函数介绍：选择执法对象
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/selectLawobj.htm")
	public String choseeLawobj(ModelMap model,String lawobjtype,String rwid,String rwlxIds,String fzbs){
		String biaoshi = indexManager.sysVer;
		model.put("rwid", rwid);
		List<Map<String, String>> rwlxlistMap = commWorkManager
				.getTaskTypeByTaskId(rwid);
		model.put("rwlxlistMap", rwlxlistMap);
		String rwlxId = rwlxIds;
		for (int i = 0; i < rwlxlistMap.size(); i++) {
			if (i < rwlxlistMap.size() - 1) {
				rwlxIds += rwlxlistMap.get(i).get("id") + ",";
			} else {
				rwlxIds += rwlxlistMap.get(i).get("id");
			}
		}
		if(StringUtils.isNotBlank(rwlxIds)){
			model.put("rwlxIds", rwlxIds);
		}else{
			model.put("rwlxIds", rwlxId);
		}
		
		model.put("lawobjtype", lawobjtype);
		if(StringUtils.isNotBlank(fzbs)){
			model.put("ymbiaoshi", fzbs);
		}
		//默认显示本部门信息
		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
		if(tSysOrg!=null){
			model.put("orgId", tSysOrg.getId());
		}
		if("1".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeGywry";
			}else{
				return "app/data/xxgl/lawobj/choseeGywry";
			}
		}else if("2".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeJsxm";
			}else{
				return "app/data/xxgl/lawobj/choseeJsxm";
			}
		}else if("3".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeYyxx";
			}else{
				return "app/data/xxgl/lawobj/choseeYyxx";
			}
		}else if("4".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeGlxx";
			}else{
				return "app/data/xxgl/lawobj/choseeGlxx";
			}
		}else if("5".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeJzgd";
			}else{
				return "app/data/xxgl/lawobj/choseeJzgd";
			}
		}else if("6".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeScxx";
			}else{
				return "app/data/xxgl/lawobj/choseeScxx";
			}
		}else if("7".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeXqyz";
			}else{
				return "app/data/xxgl/lawobj/choseeXqyz";
			}
		}else if("8".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeFwy";
			}else{
				return "app/data/xxgl/lawobj/choseeFwy";
			}
		}else if("9".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeYsy";
			}else{
				return "app/data/xxgl/lawobj/choseeYsy";
			}
		}else if("10".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeZzy";
			}else{
				return "app/data/xxgl/lawobj/choseeZzy";
			}
		}else if("11".equals(lawobjtype)){
			if("0".equals(biaoshi)){
				return "app/data/xxgl/lawobj/lxchoseeYly";
			}else{
				return "app/data/xxgl/lawobj/choseeYly";
			}
		}
		return null;
	}
	
	/**
	 * 
	 * 函数介绍：保存任务中选择的执法对象
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/saveChoseeLawobj.json")
	public void saveChoseeLawobj(ModelMap model,String rwid,String lawobjtype,String rows){
		try {
			JSONArray array = new JSONArray(rows);
			String result = commonManager.saveChoseeLawobj(rwid, lawobjtype, array, CtxUtil.getCurUser());
			if(result.equals("success")){
				JsonResultUtil.suncess(model, "保存成功！");
			}else{
				JsonResultUtil.fail(model, result);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 函数介绍：保存任务中选择的执法对象
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/querySelectLawobjList.json")
	@ResponseBody
	public List<TaskandlawobjForm> querySelectLawobjList(ModelMap model,String rwid,String lawobjtype){
		return commonManager.querySelectLawobjList(rwid,lawobjtype);
	}
	
	/**
	 * 
	 * 函数介绍：跳转到选择执法对象公用页面
	
	 * 输入参数：lawobjtype 执法对象类型
	
	 * 返回值：
	 */
	@RequestMapping(value = "/selectLawobjPage.htm")
	public String selectLawobjPage(ModelMap model, String lawobjtype){
		model.put("lawobjtype", lawobjtype);
		if("01".equals(lawobjtype)){
			return "common/zdpf_choseeGywry";
		}else if("02".equals(lawobjtype)){
			return "common/zdpf_choseeJsxm";
		}else if("03".equals(lawobjtype)){
			return "common/zdpf_choseeYyxx";
		}else if("04".equals(lawobjtype)){
			return "common/zdpf_choseeGlxx";
		}else if("05".equals(lawobjtype)){
			return "common/zdpf_choseeJzgd";
		}else if("06".equals(lawobjtype)){
			return "common/zdpf_choseeScxx";
		}else if("07".equals(lawobjtype)){
			return "common/zdpf_choseeXqyz";
		}else if("08".equals(lawobjtype)){
			return "common/zdpf_choseeFwy";
		}else if("09".equals(lawobjtype)){
			return "common/zdpf_choseeYsy";
		}else if("10".equals(lawobjtype)){
			return "common/zdpf_choseeZzy";
		}else if("11".equals(lawobjtype)){
			return "common/zdpf_choseeYly";
		}
		return null;
	}
	
	/**
	 * 
	 * 函数介绍：任务日志：操作类型列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/queryWorkLogTypeList.json")
	@ResponseBody
	public List<Combobox> queryWorkLogTypeList(){
		return WorkLogType.getTimeTypes();
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
	 * 
	 * 函数介绍：检查项枚举列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/getJcxEnumList.json")
	@ResponseBody
	public List<Combobox> getJcxEnumList(ModelMap model) {
		return JcxEnum.getJcxEnumList();
	}
	
	/**
	 * 
	 * 函数介绍：检查项枚举列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/getJcmbByTaskType.json")
	@ResponseBody
	public List<Combobox> getJcmbByTaskType(ModelMap model, String tasktype) {
		return commonManager.getJcmbByTaskType(tasktype);
	}
	
	/**
	 * 
	 * 函数介绍：经纬度控件
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/jwdPubWindow.htm")
	public String jwdPubWindow() {
		return "common/jwdPubWindow";
	}
	/**
	 * 
	 * 函数介绍：获取执法对象名称下拉列表 （ 通用接口）
	 * 
	 * 输入参数：type:字典类型
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/zfdxmcList.json")
	@ResponseBody
	public List<Combobox> zfdxmcList(ModelMap model) {
		return commonManager.queryzfdxmcList();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "common/imgView";
	}

}
