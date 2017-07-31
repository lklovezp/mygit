package com.hnjz.sys.mobile;

import java.io.IOException;
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

import com.hnjz.Constants;
import com.hnjz.common.AppException;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.YnEnum;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.LogUtil;
import com.hnjz.common.util.StringUtil;
import com.hnjz.sys.po.TSysMobileFunc;
import com.hnjz.sys.role.RoleManager;

/**
 * 
 * �ֻ�����
 * 
 * @author xuguanghui
 * @version $Id: MobileController.java, v 0.1 Apr 22, 2013 4:27:36 PM
 *          Administrator Exp $
 */
@Controller
public class MobileController {

	private static final Log log = LogFactory.getLog(MobileController.class);

	@Autowired
	private MobileManager mobileManager;
	@Autowired
	private RoleManager roleManager;
	
	/**
	 * ���ֻ���Ȩ�޹�������
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return �˵����ܵĳ�ʼ����
	 */
	@RequestMapping(value = "/mobileRight.htm")
	public String mobileRight(ModelMap model, String role, String title,String name) {
		try {
			if (!StringUtil.isNotBlank(role)){
				if (CtxUtil.getCurUser().getId().equals(Constants.ROOT_ROLE)){
					role = Constants.ROOT_ROLE;
				} else {
					List<Combobox> combo;
					combo = roleManager.queryRole();
					role = combo.get(0).getId();
				}
			}
			JSONArray re = mobileManager.queryQx(role);
			model.addAttribute("menu", re.toString());
			model.addAttribute("role", role);
			model.addAttribute("name", name);
			model.addAttribute("title", title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sys/mobile/mobileRight";
	}

	/**
	 * ��ѯ�ֻ�����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/savemobileR.json", produces = "application/json")
	public void savemobileR(ModelMap model, String mobileId, String role) {
		try {
			String[] ids = mobileId.split(",");
			mobileManager.saveRight(ids, role);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (Exception e) {
			log.error("", e);
			JsonResultUtil.fail(model, "����ʧ�ܣ�");
		}

	}

	/**
	 * ���б�ҳ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @return �˵����ܵĳ�ʼ����
	 */
	@RequestMapping(value = "/mobileList.htm")
	public String mobileList(ModelMap model,String title) {
		this.mobileQuery(model, null, YnEnum.Y.getCode());
		model.addAttribute("title", title);
		return "sys/mobile/mobileList";
	}

	/**
	 * ��ѯ�ֻ�����
	 * 
	 * @param model
	 *            {@link ModelMap}
	 */
	@RequestMapping(value = "/mobileQuery.json", produces = "application/json")
	public void mobileQuery(ModelMap model, String name, String isActive) {
		try {
			JSONArray re = mobileManager.queryMobile(name, isActive);
			model.addAttribute("re", re.toString());
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}

	/**
	 * �����ֻ�������Ϣ
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param mobileForm
	 * @return ��ʼ����
	 */
	@RequestMapping(value = "/saveMobile.json", produces = "application/json")
	public void saveMobile(ModelMap model, MobileForm form) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("������Ϣ:" + LogUtil.m(form));
			}
			mobileManager.saveMobile(form);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (AppException e) {
			log.error("������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}

	/**
	 * ���޸��ֻ�����
	 * 
	 * @param id
	 * @return ����
	 */
	@RequestMapping(value = "/editMobile.htm")
	public String editMobile(ModelMap model, MobileForm form) {
		if (log.isDebugEnabled()) {
			log.debug("id:" + form.getId());
		}
		if (StringUtils.isBlank(form.getId())) {
			return "sys/mobile/editMobile";
		}
		TSysMobileFunc po = (TSysMobileFunc) this.mobileManager.get(TSysMobileFunc.class, form.getId());
		form.setId(po.getId());
		form.setName(po.getName());
		form.setDescribe(po.getDescribe());
		
		if (null != po.getFunction()) {
			TSysMobileFunc pMobile = (TSysMobileFunc) this.mobileManager.get(
					TSysMobileFunc.class, po.getFunction().getId());
			form.setPid(pMobile.getId());
			form.setPname(pMobile.getName());
		}
		form.setIsActive(po.getIsActive());
		form.setOrderby(po.getOrderby());
		form.setActivity(po.getActivity());
		if (log.isDebugEnabled()) {
			log.debug("frm:" + LogUtil.m(form));
		}
		return "sys/mobile/editMobile";
	}

	/**
	 * ѡ���ֻ����ܽ���
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/mobilePubQuery.htm")
	public String mobilePubQuery(ModelMap model, String id) {
		try {
			JSONArray re = mobileManager.querySelectMobile(id);
			model.addAttribute("menu", re.toString());
			model.addAttribute("id", id);
		} catch (Exception e) {
			log.error("��ѯ����", e);
		}
		return "sys/mobile/mobilePubQuery";
	}

	/**
	 * ɾ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param id
	 * @return ɾ�����
	 */
	@RequestMapping(value = "/delMobile.json", produces = "application/json")
	public void delMobile(ModelMap model, String id) {
		try {
			mobileManager.removeMobile(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("ɾ��������Ϣ����", e);
			JsonResultUtil.fail(model, e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * �������ܣ���õ�ǰ��½�û���Ȩ�޹���
	
	 * ���������
	
	 * ����ֵ��
	 */
	@RequestMapping(value = "/queryMobileMenu.mo", produces = "application/json")
	public void queryMobileMenu(ModelMap model,HttpServletResponse response){
		try {
			JSONArray array = mobileManager.queryMobileMenu();
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(array.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}