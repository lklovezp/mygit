package com.hnjz.app.data.xxgl.complaint;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.data.po.TDataComplaint;
import com.hnjz.app.data.po.TDataLawobjtype;
import com.hnjz.app.data.zfdx.lawobjtype.ZfdxManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;

/**
 * 
 * 作者：renzhengquan
 * 生成日期：2015-3-17
 * 功能描述：
		执法对象字典管理
 *
 */
@Controller
public class ComplaintController {
	 /**日志*/
    private static final Log log = LogFactory.getLog(ComplaintController.class);
    
    @Autowired
    private ComplaintManager complaintManager;
    
    @Autowired
	private ZfdxManager zfdxManager;
    /**
     * 跳转到执法对象字典编辑页面
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/complaintList.htm")
    public String complaintList(ModelMap model,String title) {
    	model.put("title", title);
        return "app/data/xxgl/complaint/complaintList";
    }

    /**
     * 跳转到添加编辑页面
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/complaintEdit.htm")
    public String complaintEdit(ModelMap model,ComplaintForm complaintForm) {
    	if(complaintForm!=null && StringUtils.isNotBlank(complaintForm.getId())){
    		complaintForm = complaintManager.getComplaintInfo(complaintForm);
    	}
    	model.put("complaintForm", complaintForm);
    	return "app/data/xxgl/complaint/complaintEdit";
    }
    
    /**
     * 保存实体
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateComplaint.json")
    public void saveOrUpdateComplaint(ModelMap model,ComplaintForm complaintForm){
    	try {
    		TDataComplaint complaint = complaintManager.saveOrUpdateComplaint(complaintForm);
    		model.put("id", complaint.getId());
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存投诉信息错误！",e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    
    /**
     * 
     * 函数介绍：投诉信息列表数据
    
     * 输入参数：
    
     * 返回值：
     */
    @RequestMapping(value = "/queryComplaintList.json")
    public void queryComplaintList(ModelMap model,String lawobjtypeid,String lawobjname,String lawobjaddress,String starttime,String endtime,String isActive,String lawobjid,String page,String pageSize){
    	try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = complaintManager.queryComplaintList(lawobjtypeid,lawobjname, lawobjaddress, starttime, endtime, isActive, lawobjid, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 跳转到详情页面
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/complaintInfo.htm")
    public String complaintInfo(ModelMap model,ComplaintForm complaintForm){
    	if(complaintForm!=null && StringUtils.isNotBlank(complaintForm.getId())){
    		complaintForm = complaintManager.getComplaintInfo(complaintForm);
    		if(complaintForm.getLawobjtypeid()!=null){
    			TDataLawobjtype type=zfdxManager.gettype(complaintForm.getLawobjtypeid());
    			model.addAttribute("typename", type.getName());
    		}
    	}
    	 return "app/data/xxgl/complaint/complaintInfo";
    }
    
    /**
     * 跳转到详情页面
     * 
     * @param model {@link ModelMap}
     * @return
     */
    @RequestMapping(value = "/deleteComplaint.json")
    public void deleteComplaint(ModelMap model,String id){
    	try {
			complaintManager.removeComplaint(id);
			JsonResultUtil.suncess(model, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除投诉信息错误！",e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
    }
    
    /**
     * 
     * 函数介绍：跳转到选择三产信息界面
    
     * 输入参数：
    
     * 返回值：
     */
    @RequestMapping(value = "/choseeScxxList.htm")
    public String choseeScxxList(ModelMap model){
    	return "app/data/xxgl/complaint/choseeScxxList";
    }
    
    /**
     * 
     * 
     * @param model {@link ModelMap}
     * @return 查询结果页面
     */
    @RequestMapping(value = "/complaintSelectLawobj.htm")
    public String selectLawobj(ModelMap model,String lawobjtypeid) {
    	model.put("lawobjtypeid", lawobjtypeid);
    	if("01".equals(lawobjtypeid)){
    		return "app/data/xxgl/complaint/choseeGywry";
    	}else if("02".equals(lawobjtypeid)){
    		return "app/data/xxgl/complaint/choseeJsxm";
    	}else if("03".equals(lawobjtypeid)){
    		return "app/data/xxgl/complaint/choseeYyxx";
    	}else if("04".equals(lawobjtypeid)){
    		return "app/data/xxgl/complaint/choseeGlxx";
    	}else if("05".equals(lawobjtypeid)){
    		return "app/data/xxgl/complaint/choseeJzgd";
    	}else if("06".equals(lawobjtypeid)){
    		return "app/data/xxgl/complaint/choseeScxx";
    	}else if("07".equals(lawobjtypeid)){
    		return "app/data/xxgl/complaint/choseeXqyz";
    	}else if("08".equals(lawobjtypeid)){
    		return "app/data/xxgl/complaint/choseeFwy";
    	}else if("09".equals(lawobjtypeid)){
    		return "app/data/xxgl/complaint/choseeYsy";
    	}else if("10".equals(lawobjtypeid)){
    		return "app/data/xxgl/complaint/choseeZzy";
    	}else if("11".equals(lawobjtypeid)){
    		return "app/data/xxgl/complaint/choseeYly";
    	}else{
    		return null;
    	}
    }
    
    /**
     * 
     * 函数介绍：跳转到列表界面（信息查询）
    
     * 输入参数：
    
     * 返回值：
     */
    @RequestMapping(value = "/xxcx_complaintList.htm")
    public String xxcx_complaintList(ModelMap model,String lawobjid){
    	model.put("lawobjid", lawobjid);
    	return "app/data/xxcx/complaint/xxcx_complaintList";
    }
    
	public ComplaintManager getComplaintManager() {
		return complaintManager;
	}


	public void setComplaintManager(ComplaintManager complaintManager) {
		this.complaintManager = complaintManager;
	}

}
