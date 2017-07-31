package com.hnjz.app.data.wg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataWg;
import com.hnjz.app.data.rcbg.ConsultationController;
import com.hnjz.app.drafter.drafterSend.DrafterForm;
import com.hnjz.app.drafter.po.TBizDrafter;
import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.LogUtil;
import com.hnjz.sys.org.OrgManager;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.user.UserForm;

/**
 * 
 * @author xuyaxing
 *
 */

@Controller
public class WgController {
    
	private static final Log log=LogFactory.getLog(ConsultationController.class);
	 
	@Autowired
	private WgManager  wgManager;
	
	@Autowired
	private OrgManager orgManager;
	
	
	
	/**
     * 跳转到列表页面
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/wgList.htm")
	public String wgList(ModelMap model,String title, String name)throws Exception{
    	model.put("title", title);
    	JSONArray orgs = orgManager.queryOrg(name, "Y");//部门树
		model.addAttribute("orgList", orgs.toString());
		return "sys/wg/wgList";
	}
    
    /**
     * 查询网格信息
     */
    @RequestMapping(value = "/queryWgList.json" , produces = "application/json")
    public void querywgxxList(ModelMap model,String wgmc,String orgid,String page,String pageSize){ 
    	try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = wgManager.queryWg(wgmc, orgid, page, pageSize);
			 model.addAttribute("arr", re.getRows());
			JsonResultUtil.fyWeb(model, re);

		} catch (Exception e) {
			log.error("查询出错：", e);
		}
    }	
    
    /**
     * 编辑网格信息
     * @throws Exception 
     */
    @RequestMapping(value = "/editWg.htm")
	public String wgEdit(ModelMap model, WgForm frm,String orgid) throws Exception {
    	
    	if (log.isDebugEnabled()) {
			log.debug("id:" + frm.getId());
		}
		if (StringUtils.isNotBlank(frm.getId())) {
			this.wgManager.editWg(frm);
			model.put("userId", frm.getUserid());
			model.addAttribute("title", "编辑网格");
			model.addAttribute("orgid", orgid);
			
		} else {
			model.addAttribute("title", "新建网格");
			model.addAttribute("orgid", orgid);
		}
		return "sys/wg/wgEdit";
    }
    
    /**
     * 保存网格
     */
    @RequestMapping(value = "/saveWg.json", produces = "application/json")
	public void saveWg(ModelMap model, WgForm frm, HttpServletResponse response) {
    	response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			if (log.isDebugEnabled()) {
				log.debug("表单信息:" + LogUtil.m(frm));
			}
			wgManager.saveWg(frm);
			model.remove("wgForm");
			
			writer.print("{\"state\":true,\"msg\":\"保存成功\"}");
		} catch (AppException e) {
			log.error("保存信息错误：", e);
			writer.print("{\"state\":false,\"msg\":\"" + e + "\"}");
		}
    	
    }
    
    /**
     *删除网格
     */
    @RequestMapping(value = "/delWg.json", produces = "application/json")
   	public void delWg(ModelMap model, String ids) {
    	try {
    		 String[] idArray = ids.split(",");
    		 for (String id : idArray) {  
                 wgManager.delWg(id);
    		}
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			log.error("删除网格信息错误：", e);
			JsonResultUtil.fail(model, "删除失败！");
		}
    }
    
    /**
     * 查看网格
     * 跳转到网格查看详情界面
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/WgInfo.htm")
    public String wgInfo(ModelMap model,WgForm frm) {
    	try {
    		model.put("userId", frm.getUserid());
			wgManager.getWg(frm);
			model.addAttribute("title", "查看网格");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "sys/wg/wgInfo";
    }
}
