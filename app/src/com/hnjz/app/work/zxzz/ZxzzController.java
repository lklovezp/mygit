/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.app.work.zxzz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnjz.app.work.po.TBizAutomoniter;
import com.hnjz.app.work.po.Work;
import com.hnjz.app.work.zx.BlMainForm;
import com.hnjz.app.work.zx.CommWorkManager;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.sys.po.TSysArea;
import com.hnjz.sys.po.TSysOrg;
import com.hnjz.wf.AbsJbpmController;

/**
 * ����������Controller
 * ���ߣ�zhangqingfeng
 * �������ڣ�12/23, 2015
 * ����������

 *
 */
@Controller
public class ZxzzController extends AbsJbpmController {
	
	@Autowired
    private ZxzzManager    zxzzManager;
	@Autowired
	private CommWorkManager commWorkManager;
	
	/**
     * ����ҳ��
     * 
     * @param applyId ����Id
     * @param model
     */
    @RequestMapping(value = "/onlineCreate.htm")
    public String xwbl(String applyId, String zzmblx, ModelMap model, ZxzzForm zxzzForm) {
    	String url = "";
    	try {
        	Work work = workManager.get(applyId);
        	List<TBizAutomoniter> tbizautomoniterList = zxzzManager.getList(applyId, zzmblx);
        	List<Map<String, String>> zfdxlistMap = commWorkManager.zfdxTableData(applyId);
        	String zfdwmc = "";
        	String kzlx = "";
        	if(zfdxlistMap.size() > 0){
        		zfdwmc = zfdxlistMap.get(0).get("lawobjname");//����鵥λ����
        	}
        	model.addAttribute("zfdwmc", zfdwmc);
            model.addAttribute("work", work);
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
            	if(zzmblx.equals("101")){
            		url = "app/work/zxzz/sslxjc";
                }
            	
            	if(zzmblx.equals("201")){
                 	url = "app/work/zxzz/uvsslxjc";
                }
                 
                if(zzmblx.equals("301")){
                 	url = "app/work/zxzz/Ksslxjc";
                }
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
            		//��ѡ������
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
            	 if(zzmblx.equals("102")){
            		 url = "app/work/zxzz/dyzdjc";
                 }
            	 if(zzmblx.equals("202")){
                 	url = "app/work/zxzz/uvzdjc";
                 }
            	 if(zzmblx.equals("302")){
                 	url = "app/work/zxzz/Kzdjc";
                 }
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
            		//��ѡ
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
            	if(zzmblx.equals("103")){
                	url = "app/work/zxzz/cemsjc";
                }
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
            		//��ѡ
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
            	if(zzmblx.equals("203")){
                	url = "app/work/zxzz/ztuvjc";
                }
            	if(zzmblx.equals("304")){
                	url = "app/work/zxzz/Kztjc";
                }
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
            		//��ѡ
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
            	if(zzmblx.equals("204")){
                	url = "app/work/zxzz/zwuvjc";
                }
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
            		//��ѡ
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
            	if(zzmblx.equals("303")){
                	url = "app/work/zxzz/Kcodjc";
                }
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
            		//��ѡ
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
            	if(zzmblx.equals("205")){
                	url = "app/work/zxzz/aduvjc";
                }
            	if(zzmblx.equals("305")){
                	url = "app/work/zxzz/Kadjc";
                }
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
            		//��ѡ
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
            	 if(zzmblx.equals("206")){
                 	url = "app/work/zxzz/jsuvjc";
                 }
            	 if(zzmblx.equals("306")){
                 	url = "app/work/zxzz/Kjsjc";
                 }
            }
            
            if(zzmblx.equals("207") || zzmblx.equals("307")){
            	if(tbizautomoniterList != null && tbizautomoniterList.size() > 0){
            		zxzzForm.setSf10000001(tbizautomoniterList.get(0).getAns());
            		zxzzForm.setSf10000002(tbizautomoniterList.get(1).getAns());
            		zxzzForm.setSf10000003(tbizautomoniterList.get(2).getAns());
            		zxzzForm.setSf10000004(tbizautomoniterList.get(3).getAns());
            		//��ѡ��
            		zxzzForm.setSf10000005(tbizautomoniterList.get(4).getAns());
            		zxzzForm.setSf10000006(tbizautomoniterList.get(5).getAns());
            		zxzzForm.setSf10000007(tbizautomoniterList.get(6).getAns());
            		zxzzForm.setSf10000008(tbizautomoniterList.get(7).getAns());
            		zxzzForm.setRemark(tbizautomoniterList.get(0).getRemark());
    			}
            	model.addAttribute("zxzzForm", zxzzForm);
            	 if(zzmblx.equals("207")){
                 	url = "app/work/zxzz/lluvjc";
                 }
            	 if(zzmblx.equals("307")){
                 	url = "app/work/zxzz/Klljc";
                 }
            }
            
            if(zzmblx.equals("104") || zzmblx.equals("208") || zzmblx.equals("308")){
            	if(tbizautomoniterList != null && tbizautomoniterList.size() > 0){
            		zxzzForm.setSf10000001(tbizautomoniterList.get(0).getAns());
            		zxzzForm.setSf10000002(tbizautomoniterList.get(1).getAns());
            		zxzzForm.setSf10000003(tbizautomoniterList.get(2).getAns());
            		//��ѡ��
            		zxzzForm.setSf10000004(tbizautomoniterList.get(3).getAns());
            		zxzzForm.setSf10000005(tbizautomoniterList.get(4).getAns());
            		zxzzForm.setSf10000006(tbizautomoniterList.get(5).getAns());
            		zxzzForm.setSf10000007(tbizautomoniterList.get(6).getAns());
            		zxzzForm.setSf10000008(tbizautomoniterList.get(7).getAns());
            		zxzzForm.setSf10000009(tbizautomoniterList.get(8).getAns());
            		zxzzForm.setRemark(tbizautomoniterList.get(0).getRemark());
    			}
            	model.addAttribute("zxzzForm", zxzzForm);
            	 if(zzmblx.equals("104")){
                 	url = "app/work/zxzz/sjzdjc";
                 }
            	 if(zzmblx.equals("208")){
                 	url = "app/work/zxzz/sjuvjc";
                 }
            	 if(zzmblx.equals("308")){
                 	url = "app/work/zxzz/Ksjjc";
                 }
            }
            
            if(zzmblx.equals("105") || zzmblx.equals("209") || zzmblx.equals("309")){
            	//�������� + �˲鲿�ţ�1ʡ����2�м���3�ؼ���+ ��ȾԴ���ͣ�1��ˮ��2������+ ��� + ˳���
            	String kzlxcode = zxzzManager.getKzlx(zfdxlistMap.get(0).get("lawobjid"));//�������ҵ�Ŀ������Ͳ�ѯ��Ҫ��id
        		if("1".equals(kzlxcode)){
        			kzlx = "����";
        		}else if("2".equals(kzlxcode)){
        			kzlx = "ʡ��";
        		}else if(!"1".equals(kzlxcode) && !"2".equals(kzlxcode)){
        			kzlx = "����";
        		}
            	String code = "";
            	String xzqm = "";
            	String bmbh = "";//�˲鲿�ţ�1ʡ����2�м���3�ؼ���
            	String wrlx = "";
            	String nf = "";
            	//Ĭ����ʾ��������Ϣ
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
        			xzqm = tSysOrg.getArea().getCode();//�������������
        		}
        		if(zzmblx.equals("105")){
        			wrlx = "01";
        		}else{
        			wrlx = "02";
        		}
        		Calendar a=Calendar.getInstance();
        		nf = String.valueOf(a.get(Calendar.YEAR));
        		
        		code = xzqm + bmbh + wrlx + nf;
        		//��ҵ��������
        		//TDataLawobj lawobj = manager.getLawobjInfo(lawobj);
        		
        		//������б�
        		List<Combobox> jscrList = commWorkManager.ryghCombo(applyId);
				String jcrNames = "";
				for (int k = 0; k < jscrList.size(); k++) {
					if (k > 0) {
						jcrNames += ",";
					}
					jcrNames += jscrList.get(k).getName();
				}
				//����������
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
            	if(zzmblx.equals("105")){
                	url = "app/work/zxzz/lxxcdf";
                }
            	
            	if(zzmblx.equals("209")){
                	url = "app/work/zxzz/dfuvjc";
                }
                 
            	if(zzmblx.equals("309")){
                	url = "app/work/zxzz/Kdfjc";
                }
            }
        } catch (Exception e) {
            log.error("", e);
            e.printStackTrace();
        }
        return url;
    }
    
    /**
     * ������������ҳ������ͳһ�ı��桢����������
     * @param applyId
     * @param model
     */
    @RequestMapping(value = "/saveZxzz.json", produces = "application/json")
    public void saveXwbl(String applyId, String zzmblx, ModelMap model,ZxzzForm zxzzForm) {
    	try {
    		//����ѯ�ʱ�¼��������
    		zxzzManager.saveXwbl(zxzzForm, applyId, zzmblx);
    		//����ѯ�ʱ�¼doc�ļ�
    		zxzzManager.saveShengchengXwbl(applyId, zzmblx);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			log.error("�������", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
    }
    
    /**
	 * ��ȡ���������ĸ���
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/getZxzzFile.json", produces = "application/json")
	public void getKcxwblFile(ModelMap model, String applyId, String canDel, String fileType, String page, String rows) {
		try {
			FyWebResult kcxwblFileMap = null;
			kcxwblFileMap = zxzzManager.queryZxzzFileList(applyId, canDel, fileType, page, rows);
			model.put("kcxwblFileMap", kcxwblFileMap);

		} catch (Exception e) {
			log.error("��ѯ����ѯ�ʱ�¼����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ��ȥ��������ģ��Ļ��ԣ��Զ����
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/getZxzzFiles.json", produces = "application/json")
	public void getKcxwblFile(String applyId, String bllx, ModelMap model) {
		try {
			List<Map<String, String>> kcxwblFileMap = new ArrayList<Map<String, String>>();
			kcxwblFileMap = zxzzManager.getZxzzFiles(applyId, bllx);
			model.put("kcxwblFileMap", kcxwblFileMap);
		} catch (Exception e) {
			log.error("��ѯ����ѯ�ʱ�¼����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	/**
	 * ��ȥ��������ģ��Ļ��ԣ��Զ����
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/getCopyFiles.json", produces = "application/json")
	public void saveCopyFile(String applyId, String bllx, ModelMap model) {
		try {
			String id = null;
			id = zxzzManager.saveCopyFile(applyId, bllx);
			model.put("id", id);
		} catch (Exception e) {
			log.error("��ѯ����ѯ�ʱ�¼����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
    
}