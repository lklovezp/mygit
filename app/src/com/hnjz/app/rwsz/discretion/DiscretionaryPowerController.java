package com.hnjz.app.rwsz.discretion;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnjz.app.rwsz.po.DiscretionaryPower;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.domain.Combobox;
import com.hnjz.sys.dic.DicManager;
import com.hnjz.sys.dic.DicTypeEnum;

/**
 * 
 * ���ɲ���Ȩ
 * 
 * @author xuguanghui
 * @version $Id: DiscretionaryPowerController.java, v 0.1 Aug 6, 2013 3:44:18 PM
 *          Administrator Exp $
 */
@Controller
public class DiscretionaryPowerController {
    private static final Log          log = LogFactory.getLog(DiscretionaryPowerController.class);
    @Autowired
    private DiscretionaryPowerManager discretionaryPowerManager;
    @Autowired
    private DicManager                dicManager;

    /**
     * ���ɲ���Ȩ�����ĳ�ʼ����
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/discretionPage.htm")
    public String discretionList(ModelMap model,String zid) {
        try {
            JSONArray arr = discretionaryPowerManager.queryAllArray(zid);
            model.addAttribute("arr", arr.toString());
            model.addAttribute("zid",zid);
            model.addAttribute("name",DiscreCodeEnum.ZDFL.getText());
        } catch (JSONException e) {
            log.error("��ѯ����", e);
        }
        return "app/rwsz/discretion/discretionPage";
    }

    /**
     * �������ɲ���Ȩ
     * 
     * @param model
     * @param po
     */
    @RequestMapping(value = "/saveDiscretion.json", produces = "application/json")
    public void saveDiscretion(ModelMap model, String id, String name, String contentnote,
                               String gjc, String pid, String type, String code) {
        try {
            if (StringUtils.isNotBlank(id)) {// �޸�
                DiscretionaryPower po = discretionaryPowerManager.getDiscretionById(id);
                if (null != po) {
                    po.setName(name);
                    po.setContent(contentnote);
                    po.setGjc(gjc);
                    po.setCode(code);
                    discretionaryPowerManager.save(po);
                } else {
                    log.error("�޸�ʧ�ܣ�");
                    JsonResultUtil.fail(model, "�޸���Ϣʧ�ܣ�");
                }
            } else {// ����
                DiscretionaryPower po = new DiscretionaryPower();
                po.setName(name);
                po.setContent(contentnote);
                po.setGjc(gjc);
                po.setPid(pid);
                if (StringUtils.isBlank(type)) {
                    type = "01";
                }
                po.setCode(code);
                discretionaryPowerManager.save(po);
                model.addAttribute("id", po.getId());
                model.addAttribute("type", po.getCode());
            }
            JsonResultUtil.suncess(model, "����ɹ���");
        } catch (Exception e) {
            log.error("����ʧ�ܣ�", e);
            JsonResultUtil.fail(model, e.getMessage());
        }
    }

    /**
     * ת������ҳ��
     * 
     * @param pid
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/addDiscretionNode.htm")
    public String addDiscretionNode(String pid, String id, ModelMap model) {
        if (StringUtils.isNotBlank(pid)) {
            DiscretionaryPower po = discretionaryPowerManager.getDiscretionById(pid);
            model.addAttribute("code", po.getCode());
            List<DiscretionaryPower> plist = discretionaryPowerManager.getParents(pid);
            model.addAttribute("plist", plist);
        }
        if (StringUtils.isNotBlank(id)) {
            DiscretionaryPower po = discretionaryPowerManager.getDiscretionById(id);
            model.addAttribute("po", po);
            DiscreCodeEnum dis = DiscreCodeEnum.getByCode(po.getCode());
            model.addAttribute("note", dis.getText());
            int code=-2;
            if(StringUtils.isNotBlank(po.getCode())){
            	code=Integer.parseInt(po.getCode())-1;
            }
            model.addAttribute("code",code);
        }
        model.addAttribute("pid", pid);
        return "app/rwsz/discretion/addDiscretionNode";
    }

    /**
     * ɾ��
     * 
     * @param id
     * @param model
     */
    @RequestMapping(value = "/removeDiscretion.json", produces = "application/json")
    public void removeDiscretion(String id, ModelMap model) {
        List<DiscretionaryPower> list = discretionaryPowerManager.getChildNodesById(id);
        if (null != list && list.size() > 0) {
            JsonResultUtil.fail(model, "�˽ڵ������ӽڵ㣬����ɾ����");
            return;
        }
        try {
            discretionaryPowerManager.remove(id);
            JsonResultUtil.suncess(model, "�ڵ�ɾ���ɹ���");
        } catch (Exception e) {
            log.error("ɾ��ʧ�ܣ�", e);
            JsonResultUtil.fail(model, "�ڵ�ɾ��ʧ�ܣ�");
        }
    }

    /**
     * �鿴����
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/discretionInfo.htm")
    public String discretionInfo(String id, ModelMap model) {
        DiscretionaryPower po = discretionaryPowerManager.getDiscretionById(id);
        List<DiscretionaryPower> plist = discretionaryPowerManager.getParents(id);
        model.addAttribute("plist", plist);
        List<DiscretionaryPower> childList = new ArrayList<DiscretionaryPower>();
        childList = discretionaryPowerManager.getChildNodesById(id);
    	String name="";
        if(null!=po){
        	int intcode=Integer.valueOf(po.getCode());
        	name=DiscreCodeEnum.getTextByCode(intcode+1+"");
        }
        model.addAttribute("name", name);
        model.addAttribute("po", po);
        model.addAttribute("childList", childList);
        return "app/rwsz/discretion/discretionInfo";
    }

    /**
     * ����Υ������id�������ɲ���Ȩ
     * 
     * @param wflxId
     * @param model
     * @return
     */
    @RequestMapping(value = "/viewDiscretion.htm")
    public String viewDiscretion(String wflxId, ModelMap model) {
        List<DiscretionaryPower> list = discretionaryPowerManager.getNodesByIllegalTypeId(wflxId);
        model.addAttribute("list", list);
        return "app/rwsz/discretion/viewDiscretion";
    }

    /**
     * ת���༭ҳ��
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/editDiscretionNode.htm")
    public String editDiscretionNode(String id, ModelMap model) {
        DiscretionaryPower po = discretionaryPowerManager.getDiscretionById(id);
        model.addAttribute("po", po);
        model.addAttribute("code", po.getCode());
        return "app/rwsz/discretion/editDiscretionNode";
    }

    /**
     * ת���༭ҳ��
     * 
     * @param type
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/editWflx.htm")
    public String editWflx(String wflx, ModelMap model) {
        try {
        	if(StringUtils.isBlank(wflx)){
            	wflx="40288ac440338d87014033aa4dba00e6";
            }
            JSONArray arr = discretionaryPowerManager.queryAllArrayByWflx(wflx);
            model.addAttribute("menu", arr.toString());
            model.addAttribute("wflx", wflx);
        } catch (JSONException e) {
            log.error("��ѯ����", e);
        }
        return "app/rwsz/discretion/editWflx";
    }

    @RequestMapping(value = "/queryWflx.json", produces = "application/json")
    @ResponseBody
    public List<Combobox> queryWflx() {
//        return dicManager.queryDicDataId(DicTypeEnum.WFLX);
        return dicManager.queryDicDataId(null);
    }

    /**
     * ���ɲ���ȨΥ������
     */
    @RequestMapping(value = "/queryDisCode.json", produces = "application/json")
    @ResponseBody
    public List<Combobox> queryDisCode(String code) {
        if (StringUtils.isBlank(code)) {
            code = "-9";
        }
        return DiscreCodeEnum.getTypes(Integer.parseInt(code));
    }
    @RequestMapping(value = "/queryDisNote.json", produces = "application/json")
    public void queryDisNote(ModelMap model, String code) {
    	DiscreCodeEnum en = DiscreCodeEnum.getByCode(code);
    	model.addAttribute("name", en.getName());
    	model.addAttribute("note", en.getNote());
    }
    @RequestMapping(value = "/savewflx.json", produces = "application/json")
    public void savewflx(ModelMap model, String disid, String wflx) {
        try {
            String[] ids = disid.split(",");
            discretionaryPowerManager.saveWflx(ids, wflx);
            JsonResultUtil.suncess(model, "����ɹ���");
        } catch (Exception e) {
            log.error("", e);
            JsonResultUtil.fail(model, "����ʧ�ܣ�");
        }

    }
}