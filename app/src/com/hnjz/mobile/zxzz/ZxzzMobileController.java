/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.mobile.zxzz;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.work.po.TBizAutomoniter;
import com.hnjz.app.work.zx.BlMainForm;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.app.work.zxzz.ZxzzForm;
import com.hnjz.app.work.zxzz.ZxzzManager;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.wf.AbsJbpmController;

/**
 * 在线制作的Controller
 * 作者：zhangqingfeng
 * 生成日期：07/15, 2016
 * 功能描述：
 *
 */
@Controller
public class ZxzzMobileController extends AbsJbpmController{

	@Autowired
    private ZxzzManager    zxzzManager;
	@Autowired
	private CommWorkManager commWorkManager;
	
	/**
     * 在线页面
     * 
     * @param applyId 任务Id
     * @param model
     */
    @RequestMapping(value = "/onlineCreate.mo")
    public void xwbl(String applyId, String zzmblx, ModelMap model, ZxzzForm zxzzForm) {
    	try {
        	//Work work = workManager.get(applyId);
        	List<TBizAutomoniter> tbizautomoniterList = zxzzManager.getList(applyId, zzmblx);
        	List<Map<String, String>> zfdxlistMap = commWorkManager.zfdxTableData(applyId);
        	String zfdwmc = "";
        	String kzlx = "";
        	if(zfdxlistMap.size() > 0){
        		zfdwmc = zfdxlistMap.get(0).get("lawobjname");//被检查单位名称
        	}
        	model.addAttribute("zfdwmc", zfdwmc);
            //model.addAttribute("work", work);
            model.addAttribute("zzmblx", zzmblx);
            if(zzmblx.equals("101") || zzmblx.equals("201") || zzmblx.equals("301")){
            	if(tbizautomoniterList != null && tbizautomoniterList.size() > 0){
            		zxzzForm.setSf10000001(tbizautomoniterList.get(0).getAns());
            		zxzzForm.setSf10000002(tbizautomoniterList.get(1).getAns());
            		zxzzForm.setSf10000003(tbizautomoniterList.get(2).getAns());
            		zxzzForm.setSf10000004(tbizautomoniterList.get(3).getAns());
            		zxzzForm.setSf10000005(tbizautomoniterList.get(4).getAns());
            		zxzzForm.setSf10000006(tbizautomoniterList.get(5).getAns());
            		zxzzForm.setSf10000007(tbizautomoniterList.get(6).getAns());
            		zxzzForm.setSf10000008(tbizautomoniterList.get(7).getAns());
            		zxzzForm.setSf10000009(tbizautomoniterList.get(8).getAns());
            		zxzzForm.setSf10000010(tbizautomoniterList.get(9).getAns());
            		zxzzForm.setSf10000011(tbizautomoniterList.get(10).getAns());
            		zxzzForm.setSf10000012(tbizautomoniterList.get(11).getAns());
            		zxzzForm.setSf10000013(tbizautomoniterList.get(12).getAns());
            		zxzzForm.setSf10000014(tbizautomoniterList.get(13).getAns());
            		zxzzForm.setSf10000015(tbizautomoniterList.get(14).getAns());
            		zxzzForm.setSf10000016(tbizautomoniterList.get(15).getAns());
            		zxzzForm.setSf10000017(tbizautomoniterList.get(16).getAns());
            		zxzzForm.setSf10000018(tbizautomoniterList.get(17).getAns());
            		zxzzForm.setSf10000019(tbizautomoniterList.get(18).getAns());
            		zxzzForm.setSf10000020(tbizautomoniterList.get(19).getAns());
            		zxzzForm.setSf10000021(tbizautomoniterList.get(20).getAns());
            		zxzzForm.setSf10000022(tbizautomoniterList.get(21).getAns());
            		zxzzForm.setSf10000023(tbizautomoniterList.get(22).getAns());
            		zxzzForm.setSf10000024(tbizautomoniterList.get(23).getAns());
            		zxzzForm.setSf10000025(tbizautomoniterList.get(24).getAns());
            		zxzzForm.setSf10000026(tbizautomoniterList.get(25).getAns());
            		zxzzForm.setSf10000027(tbizautomoniterList.get(26).getAns());
            		zxzzForm.setSf10000028(tbizautomoniterList.get(27).getAns());
            		zxzzForm.setSf10000029(tbizautomoniterList.get(28).getAns());
            		zxzzForm.setSf10000030(tbizautomoniterList.get(29).getAns());
            		zxzzForm.setSf10000031(tbizautomoniterList.get(30).getAns());
            		zxzzForm.setSf10000032(tbizautomoniterList.get(31).getAns());
            		zxzzForm.setSf10000033(tbizautomoniterList.get(32).getAns());
            		zxzzForm.setSf10000034(tbizautomoniterList.get(33).getAns());
            		zxzzForm.setSf10000035(tbizautomoniterList.get(34).getAns());
            		zxzzForm.setSf10000036(tbizautomoniterList.get(35).getAns());
            		zxzzForm.setSf10000037(tbizautomoniterList.get(36).getAns());
            		zxzzForm.setSf10000038(tbizautomoniterList.get(37).getAns());
            		zxzzForm.setSf10000039(tbizautomoniterList.get(38).getAns());
            		zxzzForm.setSf10000040(tbizautomoniterList.get(39).getAns());
            		zxzzForm.setSf10000041(tbizautomoniterList.get(40).getAns());
            		zxzzForm.setSf10000042(tbizautomoniterList.get(41).getAns());
            		zxzzForm.setSf10000043(tbizautomoniterList.get(42).getAns());
            		zxzzForm.setSf10000044(tbizautomoniterList.get(43).getAns());
            		zxzzForm.setSf10000045(tbizautomoniterList.get(44).getAns());
            		zxzzForm.setSf10000046(tbizautomoniterList.get(45).getAns());
            		zxzzForm.setSf10000047(tbizautomoniterList.get(46).getAns());
            		zxzzForm.setSf10000048(tbizautomoniterList.get(47).getAns());
            		zxzzForm.setSf10000049(tbizautomoniterList.get(48).getAns());
            		zxzzForm.setSf10000050(tbizautomoniterList.get(49).getAns());
            		zxzzForm.setSf10000051(tbizautomoniterList.get(50).getAns());
            		zxzzForm.setSf10000052(tbizautomoniterList.get(51).getAns());
            		zxzzForm.setRemark(tbizautomoniterList.get(0).getRemark());
    			}
            	model.addAttribute("zxzzForm", zxzzForm);
            }
            if(zzmblx.equals("102") || zzmblx.equals("202") || zzmblx.equals("302")){
            	if(tbizautomoniterList != null && tbizautomoniterList.size() > 0){
            		zxzzForm.setSf10000001(tbizautomoniterList.get(0).getAns());
            		zxzzForm.setSf10000002(tbizautomoniterList.get(1).getAns());
            		zxzzForm.setSf10000003(tbizautomoniterList.get(2).getAns());
            		zxzzForm.setSf10000004(tbizautomoniterList.get(3).getAns());
            		zxzzForm.setSf10000005(tbizautomoniterList.get(4).getAns());
            		zxzzForm.setSf10000006(tbizautomoniterList.get(5).getAns());
            		zxzzForm.setSf10000007(tbizautomoniterList.get(6).getAns());
            		zxzzForm.setSf10000008(tbizautomoniterList.get(7).getAns());
            		//多选的两块
            		zxzzForm.setSf10000009(tbizautomoniterList.get(8).getAns());
            		zxzzForm.setSf10000010(tbizautomoniterList.get(9).getAns());
            		zxzzForm.setSf10000011(tbizautomoniterList.get(10).getAns());
            		zxzzForm.setSf10000012(tbizautomoniterList.get(11).getAns());
            		zxzzForm.setSf10000013(tbizautomoniterList.get(12).getAns());
            		zxzzForm.setSf10000014(tbizautomoniterList.get(13).getAns());
            		zxzzForm.setSf10000015(tbizautomoniterList.get(14).getAns());
            		zxzzForm.setSf10000016(tbizautomoniterList.get(15).getAns());
            		zxzzForm.setSf10000017(tbizautomoniterList.get(16).getAns());
            		zxzzForm.setSf10000018(tbizautomoniterList.get(17).getAns());
            		zxzzForm.setSf10000019(tbizautomoniterList.get(18).getAns());
            		zxzzForm.setSf10000020(tbizautomoniterList.get(19).getAns());
            		zxzzForm.setSf10000021(tbizautomoniterList.get(20).getAns());
            		zxzzForm.setSf10000022(tbizautomoniterList.get(21).getAns());
            		zxzzForm.setRemark(tbizautomoniterList.get(0).getRemark());
    			}
            	model.addAttribute("zxzzForm", zxzzForm);
            }
            
            if(zzmblx.equals("103")){
            	if(tbizautomoniterList != null && tbizautomoniterList.size() > 0){
            		zxzzForm.setSf10000001(tbizautomoniterList.get(0).getAns());
            		zxzzForm.setSf10000002(tbizautomoniterList.get(1).getAns());
            		zxzzForm.setSf10000003(tbizautomoniterList.get(2).getAns());
            		zxzzForm.setSf10000004(tbizautomoniterList.get(3).getAns());
            		zxzzForm.setSf10000005(tbizautomoniterList.get(4).getAns());
            		zxzzForm.setSf10000006(tbizautomoniterList.get(5).getAns());
            		zxzzForm.setSf10000007(tbizautomoniterList.get(6).getAns());
            		zxzzForm.setSf10000008(tbizautomoniterList.get(7).getAns());
            		zxzzForm.setSf10000009(tbizautomoniterList.get(8).getAns());
            		zxzzForm.setSf10000010(tbizautomoniterList.get(9).getAns());
            		zxzzForm.setSf10000011(tbizautomoniterList.get(10).getAns());
            		zxzzForm.setSf10000012(tbizautomoniterList.get(11).getAns());
            		//多选
            		zxzzForm.setSf10000013(tbizautomoniterList.get(12).getAns());
            		zxzzForm.setSf10000014(tbizautomoniterList.get(13).getAns());
            		zxzzForm.setSf10000015(tbizautomoniterList.get(14).getAns());
            		zxzzForm.setSf10000016(tbizautomoniterList.get(15).getAns());
            		zxzzForm.setSf10000017(tbizautomoniterList.get(16).getAns());
            		zxzzForm.setSf10000018(tbizautomoniterList.get(17).getAns());
            		zxzzForm.setSf10000019(tbizautomoniterList.get(18).getAns());
            		zxzzForm.setSf10000020(tbizautomoniterList.get(19).getAns());
            		zxzzForm.setSf10000021(tbizautomoniterList.get(20).getAns());
            		zxzzForm.setSf10000022(tbizautomoniterList.get(21).getAns());
            		zxzzForm.setSf10000023(tbizautomoniterList.get(22).getAns());
            		zxzzForm.setSf10000024(tbizautomoniterList.get(23).getAns());
            		zxzzForm.setSf10000025(tbizautomoniterList.get(24).getAns());
            		zxzzForm.setRemark(tbizautomoniterList.get(0).getRemark());
    			}
            	model.addAttribute("zxzzForm", zxzzForm);
            }
           
            if(zzmblx.equals("203") || zzmblx.equals("304")){
            	if(tbizautomoniterList != null && tbizautomoniterList.size() > 0){
            		zxzzForm.setSf10000001(tbizautomoniterList.get(0).getAns());
            		zxzzForm.setSf10000002(tbizautomoniterList.get(1).getAns());
            		zxzzForm.setSf10000003(tbizautomoniterList.get(2).getAns());
            		zxzzForm.setSf10000004(tbizautomoniterList.get(3).getAns());
            		zxzzForm.setSf10000005(tbizautomoniterList.get(4).getAns());
            		zxzzForm.setSf10000006(tbizautomoniterList.get(5).getAns());
            		zxzzForm.setSf10000007(tbizautomoniterList.get(6).getAns());
            		zxzzForm.setSf10000008(tbizautomoniterList.get(7).getAns());
            		zxzzForm.setSf10000009(tbizautomoniterList.get(8).getAns());
            		zxzzForm.setSf10000010(tbizautomoniterList.get(9).getAns());
            		zxzzForm.setSf10000011(tbizautomoniterList.get(10).getAns());
            		//多选
            		zxzzForm.setSf10000012(tbizautomoniterList.get(11).getAns());
            		zxzzForm.setSf10000013(tbizautomoniterList.get(12).getAns());
            		zxzzForm.setSf10000014(tbizautomoniterList.get(13).getAns());
            		zxzzForm.setSf10000015(tbizautomoniterList.get(14).getAns());
            		zxzzForm.setSf10000016(tbizautomoniterList.get(15).getAns());
            		zxzzForm.setSf10000017(tbizautomoniterList.get(16).getAns());
            		zxzzForm.setSf10000018(tbizautomoniterList.get(17).getAns());
            		zxzzForm.setSf10000019(tbizautomoniterList.get(18).getAns());
            		zxzzForm.setSf10000020(tbizautomoniterList.get(19).getAns());
            		zxzzForm.setSf10000021(tbizautomoniterList.get(20).getAns());
            		zxzzForm.setSf10000022(tbizautomoniterList.get(21).getAns());
            		zxzzForm.setSf10000023(tbizautomoniterList.get(22).getAns());
            		zxzzForm.setSf10000024(tbizautomoniterList.get(23).getAns());
            		zxzzForm.setSf10000025(tbizautomoniterList.get(24).getAns());
            		zxzzForm.setSf10000026(tbizautomoniterList.get(25).getAns());
            		zxzzForm.setSf10000027(tbizautomoniterList.get(26).getAns());
            		zxzzForm.setSf10000028(tbizautomoniterList.get(27).getAns());
            		zxzzForm.setSf10000029(tbizautomoniterList.get(28).getAns());
            		zxzzForm.setSf10000030(tbizautomoniterList.get(29).getAns());
            		zxzzForm.setSf10000031(tbizautomoniterList.get(30).getAns());
            		zxzzForm.setSf10000032(tbizautomoniterList.get(31).getAns());
            		zxzzForm.setRemark(tbizautomoniterList.get(0).getRemark());
    			}
            	model.addAttribute("zxzzForm", zxzzForm);
            }
            
            if(zzmblx.equals("204")){
            	if(tbizautomoniterList != null && tbizautomoniterList.size() > 0){
            		zxzzForm.setSf10000001(tbizautomoniterList.get(0).getAns());
            		zxzzForm.setSf10000002(tbizautomoniterList.get(1).getAns());
            		zxzzForm.setSf10000003(tbizautomoniterList.get(2).getAns());
            		zxzzForm.setSf10000004(tbizautomoniterList.get(3).getAns());
            		zxzzForm.setSf10000005(tbizautomoniterList.get(4).getAns());
            		zxzzForm.setSf10000006(tbizautomoniterList.get(5).getAns());
            		zxzzForm.setSf10000007(tbizautomoniterList.get(6).getAns());
            		//多选
            		zxzzForm.setSf10000008(tbizautomoniterList.get(7).getAns());
            		zxzzForm.setSf10000009(tbizautomoniterList.get(8).getAns());
            		zxzzForm.setSf10000010(tbizautomoniterList.get(9).getAns());
            		zxzzForm.setSf10000011(tbizautomoniterList.get(10).getAns());
            		zxzzForm.setSf10000012(tbizautomoniterList.get(11).getAns());
            		zxzzForm.setSf10000013(tbizautomoniterList.get(12).getAns());
            		zxzzForm.setSf10000014(tbizautomoniterList.get(13).getAns());
            		zxzzForm.setSf10000015(tbizautomoniterList.get(14).getAns());
            		zxzzForm.setSf10000016(tbizautomoniterList.get(15).getAns());
            		zxzzForm.setSf10000017(tbizautomoniterList.get(16).getAns());
            		zxzzForm.setSf10000018(tbizautomoniterList.get(17).getAns());
            		zxzzForm.setSf10000019(tbizautomoniterList.get(18).getAns());
            		zxzzForm.setSf10000020(tbizautomoniterList.get(19).getAns());
            		zxzzForm.setRemark(tbizautomoniterList.get(0).getRemark());
    			}
            	model.addAttribute("zxzzForm", zxzzForm);
            }
            
            if(zzmblx.equals("303")){
            	if(tbizautomoniterList != null && tbizautomoniterList.size() > 0){
            		zxzzForm.setSf10000001(tbizautomoniterList.get(0).getAns());
            		zxzzForm.setSf10000002(tbizautomoniterList.get(1).getAns());
            		zxzzForm.setSf10000003(tbizautomoniterList.get(2).getAns());
            		zxzzForm.setSf10000004(tbizautomoniterList.get(3).getAns());
            		zxzzForm.setSf10000005(tbizautomoniterList.get(4).getAns());
            		zxzzForm.setSf10000006(tbizautomoniterList.get(5).getAns());
            		zxzzForm.setSf10000007(tbizautomoniterList.get(6).getAns());
            		zxzzForm.setSf10000008(tbizautomoniterList.get(7).getAns());
            		zxzzForm.setSf10000009(tbizautomoniterList.get(8).getAns());
            		zxzzForm.setSf10000010(tbizautomoniterList.get(9).getAns());
            		zxzzForm.setSf10000011(tbizautomoniterList.get(10).getAns());
            		zxzzForm.setSf10000012(tbizautomoniterList.get(11).getAns());
            		//多选
            		zxzzForm.setSf10000013(tbizautomoniterList.get(12).getAns());
            		zxzzForm.setSf10000014(tbizautomoniterList.get(13).getAns());
            		zxzzForm.setSf10000015(tbizautomoniterList.get(14).getAns());
            		zxzzForm.setSf10000016(tbizautomoniterList.get(15).getAns());
            		zxzzForm.setSf10000017(tbizautomoniterList.get(16).getAns());
            		zxzzForm.setSf10000018(tbizautomoniterList.get(17).getAns());
            		zxzzForm.setSf10000019(tbizautomoniterList.get(18).getAns());
            		zxzzForm.setSf10000020(tbizautomoniterList.get(19).getAns());
            		zxzzForm.setSf10000021(tbizautomoniterList.get(20).getAns());
            		zxzzForm.setSf10000022(tbizautomoniterList.get(21).getAns());
            		zxzzForm.setSf10000023(tbizautomoniterList.get(22).getAns());
            		zxzzForm.setSf10000024(tbizautomoniterList.get(23).getAns());
            		zxzzForm.setSf10000025(tbizautomoniterList.get(24).getAns());
            		zxzzForm.setSf10000026(tbizautomoniterList.get(25).getAns());
            		zxzzForm.setSf10000027(tbizautomoniterList.get(26).getAns());
            		zxzzForm.setSf10000028(tbizautomoniterList.get(27).getAns());
            		zxzzForm.setSf10000029(tbizautomoniterList.get(28).getAns());
            		zxzzForm.setSf10000030(tbizautomoniterList.get(29).getAns());
            		zxzzForm.setSf10000031(tbizautomoniterList.get(30).getAns());
            		zxzzForm.setRemark(tbizautomoniterList.get(0).getRemark());
    			}
            	model.addAttribute("zxzzForm", zxzzForm);
            }
            
            if(zzmblx.equals("205") || zzmblx.equals("305")){
            	if(tbizautomoniterList != null && tbizautomoniterList.size() > 0){
            		zxzzForm.setSf10000001(tbizautomoniterList.get(0).getAns());
            		zxzzForm.setSf10000002(tbizautomoniterList.get(1).getAns());
            		zxzzForm.setSf10000003(tbizautomoniterList.get(2).getAns());
            		zxzzForm.setSf10000004(tbizautomoniterList.get(3).getAns());
            		zxzzForm.setSf10000005(tbizautomoniterList.get(4).getAns());
            		zxzzForm.setSf10000006(tbizautomoniterList.get(5).getAns());
            		zxzzForm.setSf10000007(tbizautomoniterList.get(6).getAns());
            		zxzzForm.setSf10000008(tbizautomoniterList.get(7).getAns());
            		zxzzForm.setSf10000009(tbizautomoniterList.get(8).getAns());
            		zxzzForm.setSf10000010(tbizautomoniterList.get(9).getAns());
            		zxzzForm.setSf10000011(tbizautomoniterList.get(10).getAns());
            		//多选
            		zxzzForm.setSf10000012(tbizautomoniterList.get(11).getAns());
            		zxzzForm.setSf10000013(tbizautomoniterList.get(12).getAns());
            		zxzzForm.setSf10000014(tbizautomoniterList.get(13).getAns());
            		zxzzForm.setSf10000015(tbizautomoniterList.get(14).getAns());
            		zxzzForm.setSf10000016(tbizautomoniterList.get(15).getAns());
            		zxzzForm.setSf10000017(tbizautomoniterList.get(16).getAns());
            		zxzzForm.setSf10000018(tbizautomoniterList.get(17).getAns());
            		zxzzForm.setSf10000019(tbizautomoniterList.get(18).getAns());
            		zxzzForm.setSf10000020(tbizautomoniterList.get(19).getAns());
            		zxzzForm.setSf10000021(tbizautomoniterList.get(20).getAns());
            		zxzzForm.setSf10000022(tbizautomoniterList.get(21).getAns());
            		zxzzForm.setSf10000023(tbizautomoniterList.get(22).getAns());
            		zxzzForm.setSf10000024(tbizautomoniterList.get(23).getAns());
            		zxzzForm.setSf10000025(tbizautomoniterList.get(24).getAns());
            		zxzzForm.setSf10000026(tbizautomoniterList.get(25).getAns());
            		zxzzForm.setRemark(tbizautomoniterList.get(0).getRemark());
    			}
            	model.addAttribute("zxzzForm", zxzzForm);
            }
            
            if(zzmblx.equals("206") || zzmblx.equals("306")){
            	if(tbizautomoniterList != null && tbizautomoniterList.size() > 0){
            		zxzzForm.setSf10000001(tbizautomoniterList.get(0).getAns());
            		zxzzForm.setSf10000002(tbizautomoniterList.get(1).getAns());
            		zxzzForm.setSf10000003(tbizautomoniterList.get(2).getAns());
            		zxzzForm.setSf10000004(tbizautomoniterList.get(3).getAns());
            		zxzzForm.setSf10000005(tbizautomoniterList.get(4).getAns());
            		zxzzForm.setSf10000006(tbizautomoniterList.get(5).getAns());
            		zxzzForm.setSf10000007(tbizautomoniterList.get(6).getAns());
            		zxzzForm.setSf10000008(tbizautomoniterList.get(7).getAns());
            		zxzzForm.setSf10000009(tbizautomoniterList.get(8).getAns());
            		zxzzForm.setSf10000010(tbizautomoniterList.get(9).getAns());
            		zxzzForm.setSf10000011(tbizautomoniterList.get(10).getAns());
            		zxzzForm.setSf10000012(tbizautomoniterList.get(11).getAns());
            		zxzzForm.setSf10000013(tbizautomoniterList.get(12).getAns());
            		//多选
            		zxzzForm.setSf10000014(tbizautomoniterList.get(13).getAns());
            		zxzzForm.setSf10000015(tbizautomoniterList.get(14).getAns());
            		zxzzForm.setSf10000016(tbizautomoniterList.get(15).getAns());
            		zxzzForm.setSf10000017(tbizautomoniterList.get(16).getAns());
            		zxzzForm.setSf10000018(tbizautomoniterList.get(17).getAns());
            		zxzzForm.setSf10000019(tbizautomoniterList.get(18).getAns());
            		zxzzForm.setSf10000020(tbizautomoniterList.get(19).getAns());
            		zxzzForm.setSf10000021(tbizautomoniterList.get(20).getAns());
            		zxzzForm.setSf10000022(tbizautomoniterList.get(21).getAns());
            		zxzzForm.setSf10000023(tbizautomoniterList.get(22).getAns());
            		zxzzForm.setSf10000024(tbizautomoniterList.get(23).getAns());
            		zxzzForm.setSf10000025(tbizautomoniterList.get(24).getAns());
            		zxzzForm.setSf10000026(tbizautomoniterList.get(25).getAns());
            		zxzzForm.setSf10000027(tbizautomoniterList.get(26).getAns());
            		zxzzForm.setSf10000028(tbizautomoniterList.get(27).getAns());
            		zxzzForm.setSf10000029(tbizautomoniterList.get(28).getAns());
            		zxzzForm.setSf10000030(tbizautomoniterList.get(29).getAns());
            		zxzzForm.setSf10000031(tbizautomoniterList.get(30).getAns());
            		zxzzForm.setSf10000032(tbizautomoniterList.get(31).getAns());
            		zxzzForm.setRemark(tbizautomoniterList.get(0).getRemark());
    			}
            	model.addAttribute("zxzzForm", zxzzForm);
            }
            
            if(zzmblx.equals("207") || zzmblx.equals("307")){
            	if(tbizautomoniterList != null && tbizautomoniterList.size() > 0){
            		zxzzForm.setSf10000001(tbizautomoniterList.get(0).getAns());
            		zxzzForm.setSf10000002(tbizautomoniterList.get(1).getAns());
            		zxzzForm.setSf10000003(tbizautomoniterList.get(2).getAns());
            		zxzzForm.setSf10000004(tbizautomoniterList.get(3).getAns());
            		//多选的
            		zxzzForm.setSf10000005(tbizautomoniterList.get(4).getAns());
            		zxzzForm.setSf10000006(tbizautomoniterList.get(5).getAns());
            		zxzzForm.setSf10000007(tbizautomoniterList.get(6).getAns());
            		zxzzForm.setSf10000008(tbizautomoniterList.get(7).getAns());
            		zxzzForm.setRemark(tbizautomoniterList.get(0).getRemark());
    			}
            	model.addAttribute("zxzzForm", zxzzForm);
            }
            
            if(zzmblx.equals("104") || zzmblx.equals("208") || zzmblx.equals("308")){
            	if(tbizautomoniterList != null && tbizautomoniterList.size() > 0){
            		zxzzForm.setSf10000001(tbizautomoniterList.get(0).getAns());
            		zxzzForm.setSf10000002(tbizautomoniterList.get(1).getAns());
            		zxzzForm.setSf10000003(tbizautomoniterList.get(2).getAns());
            		//多选的
            		zxzzForm.setSf10000004(tbizautomoniterList.get(3).getAns());
            		zxzzForm.setSf10000005(tbizautomoniterList.get(4).getAns());
            		zxzzForm.setSf10000006(tbizautomoniterList.get(5).getAns());
            		zxzzForm.setSf10000007(tbizautomoniterList.get(6).getAns());
            		zxzzForm.setSf10000008(tbizautomoniterList.get(7).getAns());
            		zxzzForm.setSf10000009(tbizautomoniterList.get(8).getAns());
            		zxzzForm.setRemark(tbizautomoniterList.get(0).getRemark());
    			}
            	model.addAttribute("zxzzForm", zxzzForm);
            }
            
            if(zzmblx.equals("105") || zzmblx.equals("209") || zzmblx.equals("309")){
            	//行政区码 + 核查部门（1省级，2市级，3县级）+ 污染源类型（1废水、2废气）+ 年份 + 顺序号
            	String kzlxcode = zxzzManager.getKzlx(zfdxlistMap.get(0).get("lawobjid"));//被检查企业的控制类型查询需要的id
        		if("1".equals(kzlxcode)){
        			kzlx = "国控";
        		}else if("2".equals(kzlxcode)){
        			kzlx = "省控";
        		}else if(!"1".equals(kzlxcode) && !"2".equals(kzlxcode)){
        			kzlx = "其他";
        		}
            	String code = "";
            	String xzqm = "";
            	String bmbh = "";//核查部门（1省级，2市级，3县级）
            	String wrlx = "";
            	String nf = "";
            	//默认显示本部门信息
        		TSysOrg	tSysOrg = userManager.getOrgbyUser(CtxUtil.getUserId());
        		TSysArea tSysArea =  tSysOrg.getArea();
        		if("0".equals(tSysArea.getType())){
        			bmbh="01";
        		}else if("1".equals(tSysArea.getType())){
        			bmbh="02";
        		}else if("2".equals(tSysArea.getType())){
        			bmbh="03";
        		}
        		if(tSysOrg!=null){
        			xzqm = tSysOrg.getArea().getCode();//区域的行政区码
        		}
        		if(zzmblx.equals("105")){
        			wrlx = "01";
        		}else{
        			wrlx = "02";
        		}
        		Calendar a=Calendar.getInstance();
        		nf = String.valueOf(a.get(Calendar.YEAR));
        		
        		code = xzqm + bmbh + wrlx + nf;
        		//企业控制类型
        		//TDataLawobj lawobj = manager.getLawobjInfo(lawobj);
        		
        		//检查人列表
        		List<Combobox> jscrList = commWorkManager.ryghCombo(applyId);
				String jcrNames = "";
				for (int k = 0; k < jscrList.size(); k++) {
					if (k > 0) {
						jcrNames += ",";
					}
					jcrNames += jscrList.get(k).getName();
				}
				//环保负责人
				BlMainForm blMainForm = commWorkManager.getBlMainFormData(applyId);
				model.addAttribute("blMainForm", blMainForm);
    			model.addAttribute("jcrNames",jcrNames);
        		model.addAttribute("wjbh", code);
        		model.addAttribute("kzlx", kzlx);
        		
            	if(tbizautomoniterList != null && tbizautomoniterList.size() > 0){
            		zxzzForm.setSf10000001(tbizautomoniterList.get(0).getAns());
            		zxzzForm.setSf10000002(tbizautomoniterList.get(1).getAns());
            		zxzzForm.setSf10000003(tbizautomoniterList.get(2).getAns());
            		zxzzForm.setSf10000004(tbizautomoniterList.get(3).getAns());
            		zxzzForm.setSf10000005(tbizautomoniterList.get(4).getAns());
            		zxzzForm.setSf10000006(tbizautomoniterList.get(5).getAns());
            		zxzzForm.setSf10000007(tbizautomoniterList.get(6).getAns());
            		zxzzForm.setSf10000008(tbizautomoniterList.get(7).getAns());
            		zxzzForm.setSf10000009(tbizautomoniterList.get(8).getAns());
            		zxzzForm.setSf10000010(tbizautomoniterList.get(9).getAns());
            		zxzzForm.setSf10000011(tbizautomoniterList.get(10).getAns());
            		zxzzForm.setSf10000012(tbizautomoniterList.get(11).getAns());
            		zxzzForm.setSf10000013(tbizautomoniterList.get(12).getAns());
            		zxzzForm.setSf10000014(tbizautomoniterList.get(13).getAns());
            		zxzzForm.setSf10000015(tbizautomoniterList.get(14).getAns());
            		zxzzForm.setSf10000016(tbizautomoniterList.get(15).getAns());
            		zxzzForm.setSf10000017(tbizautomoniterList.get(16).getAns());
            		zxzzForm.setSf10000018(tbizautomoniterList.get(17).getAns());
            		zxzzForm.setSf10000019(tbizautomoniterList.get(18).getAns());
            		zxzzForm.setSf10000020(tbizautomoniterList.get(19).getAns());
            		zxzzForm.setSf10000021(tbizautomoniterList.get(20).getAns());
            		zxzzForm.setSf10000022(tbizautomoniterList.get(21).getAns());
            		zxzzForm.setSf10000023(tbizautomoniterList.get(22).getAns());
            		zxzzForm.setSf10000024(tbizautomoniterList.get(23).getAns());
            		zxzzForm.setSf10000025(tbizautomoniterList.get(24).getAns());
            		zxzzForm.setSf10000026(tbizautomoniterList.get(25).getAns());
            		zxzzForm.setSf10000027(tbizautomoniterList.get(26).getAns());
            		zxzzForm.setSf10000028(tbizautomoniterList.get(27).getAns());
            		zxzzForm.setSf10000029(tbizautomoniterList.get(28).getAns());
            		zxzzForm.setSf10000030(tbizautomoniterList.get(29).getAns());
            		zxzzForm.setSf10000031(tbizautomoniterList.get(30).getAns());
            		zxzzForm.setSf10000032(tbizautomoniterList.get(31).getAns());
            		zxzzForm.setSf10000033(tbizautomoniterList.get(32).getAns());
            		zxzzForm.setSf10000034(tbizautomoniterList.get(33).getAns());
            		zxzzForm.setSf10000035(tbizautomoniterList.get(34).getAns());
            		zxzzForm.setSf10000036(tbizautomoniterList.get(35).getAns());
            		zxzzForm.setSf10000037(tbizautomoniterList.get(36).getAns());
            		zxzzForm.setSf10000038(tbizautomoniterList.get(37).getAns());
            		zxzzForm.setSf10000039(tbizautomoniterList.get(38).getAns());
            		zxzzForm.setSf10000040(tbizautomoniterList.get(39).getAns());
            		zxzzForm.setSf10000041(tbizautomoniterList.get(40).getAns());
            		zxzzForm.setSf10000042(tbizautomoniterList.get(41).getAns());
            		zxzzForm.setSf10000043(tbizautomoniterList.get(42).getAns());
            		zxzzForm.setSf10000044(tbizautomoniterList.get(43).getAns());
            		zxzzForm.setSf10000045(tbizautomoniterList.get(44).getAns());
            		zxzzForm.setSf10000046(tbizautomoniterList.get(45).getAns());
            		zxzzForm.setSf10000047(tbizautomoniterList.get(46).getAns());
            		zxzzForm.setSf10000048(tbizautomoniterList.get(47).getAns());
            		zxzzForm.setSf10000049(tbizautomoniterList.get(48).getAns());
            		zxzzForm.setSf10000050(tbizautomoniterList.get(49).getAns());
            		zxzzForm.setSf10000051(tbizautomoniterList.get(50).getAns());
            		zxzzForm.setSf10000052(tbizautomoniterList.get(51).getAns());
            		zxzzForm.setSf10000053(tbizautomoniterList.get(52).getAns());
            		zxzzForm.setSf10000054(tbizautomoniterList.get(53).getAns());
            		zxzzForm.setSf10000055(tbizautomoniterList.get(54).getAns());
            		zxzzForm.setSf10000056(tbizautomoniterList.get(55).getAns());
            		zxzzForm.setSf10000057(tbizautomoniterList.get(56).getAns());
            		zxzzForm.setSf10000058(tbizautomoniterList.get(57).getAns());
            		zxzzForm.setSf10000059(tbizautomoniterList.get(58).getAns());
            		zxzzForm.setSf10000060(tbizautomoniterList.get(59).getAns());
            		zxzzForm.setSf10000061(tbizautomoniterList.get(60).getAns());
            		zxzzForm.setSf10000062(tbizautomoniterList.get(61).getAns());
            		zxzzForm.setSf10000063(tbizautomoniterList.get(62).getAns());
            		zxzzForm.setSf10000064(tbizautomoniterList.get(63).getAns());
            		zxzzForm.setSf10000065(tbizautomoniterList.get(64).getAns());
            		zxzzForm.setSf10000066(tbizautomoniterList.get(65).getAns());
            		zxzzForm.setSf10000067(tbizautomoniterList.get(66).getAns());
            		zxzzForm.setSf10000068(tbizautomoniterList.get(67).getAns());
            		zxzzForm.setSf10000069(tbizautomoniterList.get(68).getAns());
            		zxzzForm.setSf10000070(tbizautomoniterList.get(69).getAns());
            		zxzzForm.setSf10000071(tbizautomoniterList.get(70).getAns());
            		if(zzmblx.equals("105")){
            			zxzzForm.setSf10000072(tbizautomoniterList.get(71).getAns());
            			zxzzForm.setSf10000073(tbizautomoniterList.get(72).getAns());
            			zxzzForm.setSf10000074(tbizautomoniterList.get(73).getAns());
                		zxzzForm.setSf10000075(tbizautomoniterList.get(74).getAns());
            		}else{
            			zxzzForm.setSf10000074(tbizautomoniterList.get(71).getAns());
                		zxzzForm.setSf10000075(tbizautomoniterList.get(72).getAns());
            		}
            		zxzzForm.setRemark(tbizautomoniterList.get(0).getRemark());
    			}
            	model.addAttribute("zxzzForm", zxzzForm);
            }
        } catch (Exception e) {
            log.error("", e);
            e.printStackTrace();
        }
    }
    
    /**
     * 保存在线制作页面结果（只保存）
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/saveZxzzs.mo", produces = "application/json")
    public void saveZxzzs(String applyId, String zzmblx, ModelMap model,ZxzzForm zxzzForm) {
    	try {
    		//保存询问笔录及问题项
    		zxzzManager.saveXwbl(zxzzForm, applyId, zzmblx);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
    /**
     * 保存在线制作页面结果（统一的保存、制作方法）
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/saveZxzz.mo", produces = "application/json")
    public void saveXwbl(String applyId, String zzmblx, ModelMap model,ZxzzForm zxzzForm) {
    	try {
    		//保存询问笔录及问题项
    		zxzzManager.saveXwbl(zxzzForm, applyId, zzmblx);
    		//生成询问笔录doc文件
    		zxzzManager.saveShengchengXwbl(applyId, zzmblx);
			JsonResultUtil.suncess(model, "保存成功！");
		} catch (Exception e) {
			log.error("保存错误：", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
	
}
