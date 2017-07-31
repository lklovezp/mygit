package com.hnjz.mobile.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.IndexManager;
import com.hnjz.app.common.CommonManager;
import com.hnjz.app.common.FileTypeEnums;
import com.hnjz.app.data.po.TDataFile;
import com.hnjz.app.data.xxgl.fsaq.FsaqManager;
import com.hnjz.app.work.enums.WorkLogType;
import com.hnjz.app.work.po.TBizBlmbcs;
import com.hnjz.app.work.po.TBizMoreCheck;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.dao.Dao;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.domain.ComboboxTree;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.area.AreaManager;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.po.TSysUserRole;
import com.hnjz.sys.user.UserManager;
import com.hnjz.sys.user.UserPubQueryManager;

/**
 * 
 * 作者：renzhengquan 生成日期：2015-3-9 功能描述： 公共功能控制层（终端）
 * 
 */
@Controller
public class CommonMobileController {

	/** 日志 */
	private static final Log log = LogFactory.getLog(CommonMobileController.class);
	
	@Autowired
	private CommonManager commonManager;
	
	@Autowired
	private CommonMobileManager commonMobileManager;
	
	@Autowired
	private CommWorkManager commWorkManager;
	
	@Autowired
	private UserPubQueryManager userPubQueryManager;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
    private IndexManager     indexManager;
	
	@Autowired
	private AreaManager  areaManager;
	
	@Autowired
	private FsaqManager fsaqManager;
	
	 /** 部门管理 */
    @Autowired
    private OrgManager              orgManager;
    
    @Autowired
	private Dao dao;
	/**
	 * 
	 * 函数介绍：控制类型下拉列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/kzlxList.mo")
	@ResponseBody
	public List<Combobox> kzlxList(ModelMap model) {
		return commonManager.queryKzlxList();
	}

	/**
	 * 
	 * 函数介绍：获取字典中数据下拉列表 （ 通用接口）
	 * 
	 * 输入参数：type:字典类型
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/dicList.mo")
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
	@RequestMapping(value = "/ztList.mo")
	@ResponseBody
	public List<Combobox> ztList(ModelMap model) {
		return commonManager.queryZtList();
	}
	
	/**
	 * 
	 * 函数介绍：状态(是否)下拉列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/sfList.mo")
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
	@RequestMapping(value = "/industryList.mo")
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
	@RequestMapping(value = "/fjlxList.mo")
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
	@RequestMapping(value = "/regionTree.mo")
	@ResponseBody
	public List<ComboboxTree> regionTree(ModelMap model) {
		return commonManager.queryRegionTree();
	}

	/**
	 * 
	 * 函数介绍：违法类型下拉树
	 * 
	 * 输入参数：无
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/illegalTypeList.mo")
	@ResponseBody
	public List<ComboboxTree> illegalTypeList(ModelMap model, String ids, String type) {
		return commonManager.queryIllegalTypeByTasktypeList(ids, type);
	}

	/**
	 * 
	 * 函数介绍：任务类型下拉树
	 * 
	 * 输入参数：无
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/taskTypeTreeCombo.mo")
	public void taskTypeTreeCombo(ModelMap model,String lawobjtype, String markId, HttpServletResponse response) {
		try {
			JSONArray array = commonMobileManager.queryTasktypeTree(lawobjtype, markId);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(array.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询当前用户下的区域
	 */
	@RequestMapping(value = "/queryAreaCombo.mo", produces = "application/json")
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
	 * 
	 * 函数介绍：获取任务状态下拉列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/queryRwztCombo.mo", produces = "application/json")
	@ResponseBody
	public List<Combobox> queryRwztCombo() {
		return commonManager.queryRwztCombo();
	}
	
	/**
	 * 
	 * 函数介绍：任务日志：操作类型列表
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/queryWorkLogTypeList.mo")
	@ResponseBody
	public List<Combobox> queryWorkLogTypeList(){
		return WorkLogType.getTimeTypes();
	}
	
	
	/**
	 * 文件下载通用方法(终端)
	 * 
	 * @param id:单个文件id
	 */
	@RequestMapping(value = "/download.mo")
	public void download(String id, HttpServletResponse res) {
		try {
			commonManager.downloadFile(id, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件导出通用方法(终端)
	 * 
	 * @param id:单个文件id
	 */
	@RequestMapping(value = "/exportFile.mo")
	public void exportFile(String id, String applyId, HttpServletResponse res){
		try {
			commonManager.exportFile(id, applyId, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载缩略图(终端)
	 * 
	 * @param id:单个文件id
	 */
	@RequestMapping(value = "/downThumbnailImage.mo")
	public void downThumbnailImage(String id, HttpServletResponse res) {
		try {
			commonManager.downThumbnailImage(id, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 函数介绍：通过pid查询附件列表
	
	 * 输入参数：pid ： 任务id
	 * 	canDel ：是否有删除按钮
	
	 * 返回值：
	 */
	@RequestMapping(value = "/queryFileList.mo")
    public void queryFileList(ModelMap model,String pid, String fileType, String page,String pageSize) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = commonMobileManager.queryFileList(pid, fileType, page, pageSize);
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
	@RequestMapping(value = "/queryFileListMulfileType.mo")
    public void queryFileListMulfileType(ModelMap model,String pid, String canDel,String fileType, String page,String rows) {
    	try {
    		page = StringUtils.defaultIfBlank(page, "1");
    		FyWebResult re = commonManager.queryFileListMulfileType(pid, canDel,fileType,"", page, rows);
    		JsonResultUtil.fyWeb(model, re);
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("查询出错：", e);
    	}
    }
	
	
	@RequestMapping(value = "/taskUserPubQuery.mo")
	public void taskUserPubQuery(ModelMap model, String all,String dispalyAll, String id, String notShowZj, String showExist,String rwlx, String isXp, String condition, HttpServletResponse response) {
		try {
			TSysUser cur = CtxUtil.getCurUser();
			TSysOrg zdOrg = orgManager.getOrgByUserid(cur.getId());
    		List<String> ids = new ArrayList<String>();
			if (StringUtil.isNotBlank(id)){
				for (int i = 0; i < id.split(",").length; i++) {
					ids.add(id.split(",")[i]);
				}
			}
			JSONArray re = new JSONArray();
			boolean isAll = StringUtils.equals(all, "true");
			List<TSysUserRole> rczydata = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'RCZY')", cur.getId());
			List<TSysUserRole> xfzydata = this.dao.find("from TSysUserRole where user.id = ? and role.id = (select id from TSysRole where isActive='Y' and describe = 'XFZY')", cur.getId());
    		if(zdOrg != null){
    			if("a0000000000000000000000000000000".equals(zdOrg.getArea().getId())){
    				if("0".equals(condition)){
    					re = userPubQueryManager.taskUserPubQuery(all,"", dispalyAll,"",ids, notShowZj,showExist,"");
    				}else{
    					if("0".equals(isXp)){
        					re = userPubQueryManager.taskUserPubQueryByRoleWithout(all,"",dispalyAll, "", ids, notShowZj,showExist,"4");
        				}else{
        					if("24".equals(rwlx) && rczydata.size() != 0){
            	    			re = userPubQueryManager.ZdtaskUserPubQueryByRoleWithout(all,"",dispalyAll, "", ids, notShowZj,showExist,"5");
            	    		}else if("24".equals(rwlx) && rczydata.size() == 0){
            	    			isAll = false;
            	    			all = "false";
            	    			re = userPubQueryManager.taskUserPubQuery(all,"", dispalyAll,"",ids, notShowZj,showExist,"");
            	    		}else if("13".equals(rwlx) && xfzydata.size() != 0){
            	    			re = userPubQueryManager.ZdtaskUserPubQueryByRoleWithout(all,"",dispalyAll, "", ids, notShowZj,showExist,"5");
            	    		}else if("13".equals(rwlx) && xfzydata.size() == 0){
            	    			isAll = false;
            	    			all = "false";
            	    			re = userPubQueryManager.taskUserPubQuery(all,"", dispalyAll,"",ids, notShowZj,showExist,"");
            	    		}else{
            	    			isAll = false;
            	    			re = userPubQueryManager.taskUserPubQuery(all,"", dispalyAll,"",ids, notShowZj,showExist,"");
            	    		}
        				}
    				}
    			}else{
	    			if("0".equals(condition)){
	    				re = userPubQueryManager.taskUserPubQuery(all,"", dispalyAll,"",ids, notShowZj,showExist,"0");
	    			}else{
	    				if("24".equals(rwlx) && rczydata.size() != 0){
	    	    			re = userPubQueryManager.taskUserPubQuery(all,"",dispalyAll, "", ids, notShowZj,showExist,"");
	    	    		}else if("13".equals(rwlx) && xfzydata.size() != 0){
	    	    			re = userPubQueryManager.taskUserPubQuery(all,"",dispalyAll, "", ids, notShowZj,showExist,"");
	    	    		}else{
	    	    			isAll = false;
	    	    			re = userPubQueryManager.taskUserPubQuery("","", dispalyAll,"",ids, notShowZj,showExist,"");
	    	    		}
	    			}
    			}
    		}
			response.setContentType("application/json; charset=UTF-8");
			// 是否查看所有
			// 当前用户所属部门
			TSysOrg curOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
			if(isAll){
				if("0".equals(condition)){
					response.getWriter().print(this.treeHelp(re, null, 0));
				}else{
					if(!"a0000000000000000000000000000000".equals(zdOrg.getArea().getId()) && "24".equals(rwlx) && rczydata.size() != 0){
						response.getWriter().print(this.treeHelp(re, curOrg.getOrg()==null?null:curOrg.getOrg().getId(), 0));
					}else if(!"a0000000000000000000000000000000".equals(zdOrg.getArea().getId()) && "13".equals(rwlx) && rczydata.size() != 0){
						response.getWriter().print(this.treeHelp(re, curOrg.getOrg()==null?null:curOrg.getOrg().getId(), 0));
					}else{
						response.getWriter().print(this.treeHelp(re, null, 0));
					}
				}
			}else{
				response.getWriter().print(this.treeHelp(re, curOrg.getOrg()==null?null:curOrg.getOrg().getId(), 0));
			}
		} catch (Exception e) {
			log.error("查询错误：", e);
		}
	}
	
	private JSONArray treeHelp(JSONArray list, String pid, int level){
		JSONArray j = new JSONArray();
		try {
			for (int i = 0; i < list.length(); i++) {
				if((null == pid && StringUtil.isBlank(String.valueOf(list.getJSONObject(i).get("pid")))) || (null != pid && StringUtil.isNotBlank(String.valueOf(list.getJSONObject(i).get("pid"))) && pid.equals(list.getJSONObject(i).get("pid"))) ){
					HashMap<String, Object> obj = new HashMap<String, Object>();
					obj.put("id", list.getJSONObject(i).get("id"));
					obj.put("pid", list.getJSONObject(i).get("pid"));
					obj.put("nocheck", list.getJSONObject(i).get("nocheck"));
					obj.put("iconSkin", list.getJSONObject(i).get("iconSkin"));
					obj.put("name", list.getJSONObject(i).get("name"));
					obj.put("isorg", list.getJSONObject(i).get("isorg"));
					obj.put("level", level);
					level++;
					JSONArray a = this.treeHelp(list, list.getJSONObject(i).get("id").toString(), level);
					obj.put("children", a);
					j.put(obj);
					level--;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping(value = "/uploadfile.mo")
	public void uploadfile(ModelMap model, @RequestParam(value = "file", required = true) MultipartFile multipartFile,HttpServletRequest request
			) {
		try {
			TDataFile tDataFile = commonManager.uploadFile(multipartFile, request);
			model.addAttribute("state", true);
			model.addAttribute("msg", "上传成功");
			model.addAttribute("id", tDataFile.getId());
			model.addAttribute("url", "/download.mo?id="+tDataFile.getId());
			model.addAttribute("filename", tDataFile.getName());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("state", false);
			model.addAttribute("msg", "上传失败");
		}
	}
	
	/**
	 * 
	 * 函数介绍：单附件上传接口
	
	 * 输入参数：
	
	 * 返回值：
	 */
	@RequestMapping(value = "/uploadSingleFile.mo")
	public void uploadSingleFile(ModelMap model, @RequestParam(value = "file", required = true) MultipartFile multipartFile,
			 HttpServletRequest request) {
		try {
			TDataFile tDataFile = commonManager.uploadSingleFile(multipartFile, request);
			model.addAttribute("state", true);
			model.addAttribute("msg", "上传成功");
			model.addAttribute("id", tDataFile.getId());
			model.addAttribute("url", "/download.mo?id="+tDataFile.getId());
			model.addAttribute("filename", tDataFile.getName());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("state", false);
			model.addAttribute("msg", "上传失败");
		}
	}
	
	/**
	 * 单个文件删除通用方法
	 * 
	 * @param id:单个文件id
	 */
	@RequestMapping(value = "/removeFile.mo")
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
	 * 删除再次检查检查笔录方法
	 * 
	 * @param id:单个文件id
	 */
	@RequestMapping(value = "/removeJcbl.mo")
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
	@RequestMapping(value = "/removeZxzzJcbl.mo")
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
	 * 
	 * 函数介绍：查询任务办理过程中的附件列表
	
	 * 输入参数：pid ： 任务id
	 * 	canDel ：是否有删除按钮
	
	 * 返回值：
	 */
	@RequestMapping(value = "/queryWorkBlFileList.mo")
    public void queryWorkBlFileList(ModelMap model,String rwid) {
    	try {
    		JSONArray array = commonMobileManager.queryWorkBlFileList(rwid);
    		model.addAttribute("result", array.toString());
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("查询出错：", e);
    	}
    }
	/**
	 * 
	 * 函数介绍：查询任务报告过程中的附件列表
	
	 * 输入参数：pid ： 任务id
	 * 	canDel ：是否有删除按钮
	
	 * 返回值：
	 */
	@RequestMapping(value = "/queryWorkBglxFileList.mo")
	public void queryWorkBglxFileList(ModelMap model,String rwid) {
		try {
			JSONArray array = commonMobileManager.queryWorkBglxFileList(rwid);
			model.addAttribute("result", array.toString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("查询出错：", e);
		}
	}
	
	/**
	 * 
	 * 函数介绍：打开单附件上传页面
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/singleUploadExt.mo")
	public void singleUploadExt(ModelMap model, String type) {
		String code = FileTypeEnums.getTypeByEnumName(type);
		String ext = commonManager.getExtension(code);
		String e = "";
		if (StringUtil.isNotBlank(ext)){
			for (int i = 0; i < ext.split(";").length; i++) {
				if (i > 0){
					e += ";";
				}
				e += ext.split(";")[i].substring(2, ext.split(";")[i].length());
			}
		} else {
			ext = "";
		}
		model.addAttribute("ext", ext);
	}
	
	/**
	 * 编辑一个用户信息
	 * 
	 * @param id
	 *            用户Id
	 * @return 用户管理的初始界面
	 */
	@RequestMapping(value = "/getUserInfo.mo")
	public void getUserInfo(ModelMap model, String id) {
		HashMap<String, Object> data = this.commonMobileManager.getUserInfo(id);
		model.addAttribute("data", data);
	}
	
	/**
	 * 保存密码
	 * 
	 * @param id
	 *            用户Id
	 */
	@RequestMapping(value = "/savePas.mo", produces = "application/json")
	public void savePas(ModelMap model, String pass, String newPass, String confirmPass) {
		try {
			String msg = commonMobileManager.savePas(pass, newPass, confirmPass);
			model.addAttribute("state", true);
			model.addAttribute("msg", msg);
		} catch (AppException e) {
			log.error("保存信息错误：", e);
			model.addAttribute("state", false);
			model.addAttribute("msg", e.getMessage());
		}
	}
	
	@RequestMapping(value = "/isExistsHelpDoc.mo")
    public void isExistsHelpDoc(ModelMap model) throws AppException {
    	try {
    		indexManager.isExistsHelpDoc(FileTypeEnums.APPMOHELP.getCode());
    		model.addAttribute("state", true);
		} catch (Exception e) {
			model.addAttribute("state", false);
			model.addAttribute("msg", e.getMessage());
		}
    }
	
	/**
     * 开发帮助界面，显示最近执行的Controller
     * 
     * @return
     */
    @RequestMapping(value = "/downHelpDoc.mo")
    public void help(ModelMap model, HttpServletResponse res) throws AppException {
    	try {
    		indexManager.downHelpDoc(res, FileTypeEnums.APPMOHELP.getCode());
		} catch (Exception e) {
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
    /**
	 * 
	 * 函数介绍：检查项枚举列表
	 * 
	 * 输入参数：
	 * 
	 * 返回值：
	 */
	@RequestMapping(value = "/getJcmbByTaskType.mo")
	@ResponseBody
	public List<Combobox> getJcmbByTaskType(ModelMap model, String tasktype) {
		return commonManager.getJcmbByTaskType(tasktype);
	}
	
	/**
	 * 函数介绍：执法对象所属监管部门树
	 * 输入参数：
	 * 返回值：
	 */
	@RequestMapping(value = "/orgTree.mo")
	@ResponseBody
	public List<ComboboxTree> orgTree(ModelMap model) {
		return commonManager.queryOrgTree();
	}
	
	/**
	 * 查询当前用户及以下的区域
	 */
	@RequestMapping(value = "/queryAreaComboWithCur.mo")
	@ResponseBody
	public List<Combobox> queryAreaComboWithCur(String userId) {
		try {
			TSysArea area = areaManager.getAreaByUser(userId);
			if(area!=null){
				return commonManager.queryAreaComboWithCur(area.getId());
			}
		} catch (Exception e) {
			log.error("查询错误：", e);
		}
		return null;
	}
	
	/**
	 * 导出辐射安全基本信息表
	 * */
	@RequestMapping(value="/exportFsaqWord.mo")
	public void exportFsaqWord(HttpServletResponse response,ModelMap model,String lawobjId,String lawobjType,String biaoshi,String appleId){
		try {
			fsaqManager.createFsaqWord(response, lawobjId, lawobjType, biaoshi,appleId);
			JsonResultUtil.suncess(model, "导出成功！");
		} catch (Exception e) {
			JsonResultUtil.fail(model, "导出失败！");
			e.printStackTrace();
		}
	}
	
}
