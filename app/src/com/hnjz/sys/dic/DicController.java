/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.dic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.JsonResultUtil;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.util.LogUtil;
import com.hnjz.sys.po.TSysDic;
import com.hnjz.sys.po.TSysRole;
import com.hnjz.sys.po.TSysRoot;

/**
 * �ֵ�Controller
 * 
 * @author ljf
 * @version $Id: DicController.java, v 0.1 Jan 15, 2013 5:04:04 PM ljf Exp $
 * 
 *          2013-04-11 �ֵ�ѡ��Υ�����ͣ�[�б����ɶ�ѡ] zn [add] dicSelPage �ֵ�ѡ��ҳ�� dicSelList
 *          �ֵ�ѡ������
 */
@Controller
public class DicController {

	/** ��־ */
	private static final Log log = LogFactory.getLog(DicController.class);

	@Autowired
	private DicManager dicManager;

	/**
	 * ��ʼ������
	 */
	@RequestMapping(value = "/dicMain.htm", method = RequestMethod.GET)
	public String dicMain(ModelMap model, String title) {
		JSONArray arr = new JSONArray();
		try {

			List<String[]> re = dicManager.queryRootCode();
			for (int i=0;re.size()>i;i++) {
				Object[] str = re.get(i);
				JSONObject obj = new JSONObject();
				obj.put("id", str[0].toString());
				obj.put("name",str[2].toString() );
				obj.put("href", "dicList.htm?type=" + str[1].toString());
				arr.put(obj);
			}
			
			
		} catch (Exception e) {
			log.error("", e);
		}
		model.addAttribute("menu", arr.toString());
		model.addAttribute("title", title);
		return "sys/dic/dicMain";
	}
	/**
	 * ���Ӻ��޸�ҳ��
	 * @return
	 */
	@RequestMapping(value = "dicRoot.htm")
	public String operation(ModelMap model,DicForm df){
		DicForm cDicForm = new DicForm();
		if (StringUtils.isNotBlank(df.getId())) {
			TSysRoot po = (TSysRoot) dicManager.get(TSysRoot.class, df.getId());
			cDicForm.setId(po.getId());
			cDicForm.setName(po.getName());
			cDicForm.setCode(po.getPid());
			model.addAttribute("dicFrom", cDicForm);
			model.addAttribute("dic", "1");
			return "sys/dic/dicRoot";
		}
		return "sys/dic/dicRoot";
	}
	/**
	 * ���Ӻ��޸�ҵ����
	 * @param model
	 * @param df
	 * @throws Exception
	 */
	@RequestMapping(value = "addAndUpdate.json",produces = "application/json")
	public void addAndUpdate(ModelMap model,DicForm df) throws Exception{
		try {
			 dicManager.findRootxh(df);
			 model.addAttribute("result", "����ɹ���");
		} catch (AppException e) {
			log.error("�������", e);
			model.addAttribute("result","ϵͳ��æ�����Ժ����ԣ�");
		}
		
	}
	
	@RequestMapping(value="selectData.json",produces = "application/json")
	public void selectData(ModelMap model,String pid){
		try {
			dicManager.findRootxhcc(pid);
		} catch (Exception e) {
			log.error("�������", e);
			model.addAttribute("result","��Ų����ظ�");
		}
		
	}
	/**
	 * �ֵ�ɾ��
	 * @param model
	 * @param xh
	 * @param name
	 * @param id
	 */
	@RequestMapping(value = "removeDicRoot.json",produces = "application/json")
	public void removeDic(ModelMap model,String xh,String name,String id){
		try {
			dicManager.deleteData(id);
			model.addAttribute("result","�ֵ�ɾ���ɹ�");
		} catch (Exception e) {
			log.error("", e);
			model.addAttribute("result","ϵͳ��æ�����Ժ����ԣ�");
		}
		
	}

	/**
	 * �ֵ���ҳ��ʾ
	 * 
	 * @return ��ʼ����
	 */
	@RequestMapping(value = "/dicHome.htm")
	public String dicHome(ModelMap model, String type) {
		model.addAttribute("type", type);
		return "sys/dic/dicHome";
	}

	/**
	 * �ļ���Ϣ��ѯ�б�
	 * 
	 * @return ��ʼ����
	 */
	@RequestMapping(value = "/dicList.htm")
	public String dicList(ModelMap model, String type) {
		model.addAttribute("type", type);
		return "sys/dic/dicList";
	}

	/**
	 * ��ѯ���нڵ�
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param name
	 *            ��������
	 */
	@RequestMapping(value = "/dicQuery.json", produces = "application/json")
	public void dicQuery(ModelMap model, String type, String name, String page,
			String pageSize) {
		try {
			page = StringUtils.defaultIfBlank(page, "1");
			FyWebResult re = dicManager.queryDic(type, name, page, pageSize);
			JsonResultUtil.fyWeb(model, re);
		} catch (Exception e) {
			log.error("��ѯ������", e);
		}
	}

	/**
	 * ��ʼ�б�����
	 */
	@RequestMapping(value = "/dicList.htm", method = RequestMethod.GET)
	public ModelAndView dicList(ModelMap model, String type, String title) {
		ModelAndView frm = new ModelAndView("sys/dic/dicList");
		String ti = "";
		try {
			if (null != title && !"".equals(title)) {
				ti = java.net.URLDecoder.decode(title, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			log.error("�������", e);
		}
		frm.addObject("type", type);
		frm.addObject("title", ti);
		return frm;
	}

	/**
	 * �༭
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/editDic.htm")
	public String editDic(ModelMap model, DicForm frm, String type) {
		if (log.isDebugEnabled()) {
			log.debug("id:" + frm.getId());
		}
		if (StringUtils.isBlank(frm.getId())) {
			frm.setDicType(DicTypeEnum.getText(type));
			frm.setTypeId(type);
			return "sys/dic/editDic";
		}
		TSysDic po = (TSysDic) dicManager.get(TSysDic.class, frm.getId());
		frm.setId(po.getId());
		frm.setDicType(DicTypeEnum.getText(po.getType()));
		frm.setTypeId(po.getType());
		frm.setCode(po.getCode());
		frm.setName(po.getName());
		frm.setNote(po.getDescribe());
		frm.setOrder(po.getOrderby());
		frm.setMandatory(po.getMandatory());
		return "sys/dic/editDic";
	}

	/**
	 * �����б�
	 */
	@RequestMapping(value = "/saveDic.json", produces = "application/json")
	public void saveDic(ModelMap model, DicForm frm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("������Ϣ:" + LogUtil.m(frm));
			}
			dicManager.saveDicData(frm);
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (AppException e) {
			log.error("������Ϣ����", e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
	}
	
	/**
	 * �����ֵ�����
	 * @throws FileNotFoundException 
	 */
	@RequestMapping(value = "/dicDataBackUp.json", produces = "application/json")
	public void dicDataBackUp(ModelMap model) throws FileNotFoundException {
		try {
			dicManager.dicDataBackUp();
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (AppException e) {
			log.error("������Ϣ����", e);
			JsonResultUtil.suncess(model, e.getMessage());
		}
	}

	/**
	 * �ֵ�ı��������ϴ�
	 * @throws UnsupportedEncodingException 
	 * 
	 * @throws AppException
	 */
	@RequestMapping(value = "/uploadDicFile.json", produces = "application/json")
	public void uploadDicFile(ModelMap model, String dicfilepath, String isDel) throws UnsupportedEncodingException {

		// ȥ��Excel�ķ���readExcel���÷�������ڲ���Ϊһ��File����
		if(isDel.equals("on")){
			dicManager.removeDicData();
		}
		
		dicfilepath =  URLDecoder.decode(dicfilepath,"UTF-8") ;
		File file = new File(dicfilepath);
		try {
			// ��������������ȡExcel
			InputStream is = new FileInputStream(file.getAbsolutePath());
			// jxl�ṩ��Workbook��
			Workbook wb = Workbook.getWorkbook(is);
			// Excel��ҳǩ����
			int sheet_size = wb.getNumberOfSheets();
			List<DicForm> dicFormList = new ArrayList<DicForm>();
			for (int index = 0; index < sheet_size; index++) {
				// ÿ��ҳǩ����һ��Sheet����
				Sheet sheet = wb.getSheet(index);
				String sheetName = sheet.getName();
				List<DicTypeEnum> dicTypeEnumList = DicTypeEnum.getListEnum();
				for (DicTypeEnum dicTypeEnum : dicTypeEnumList) {
					if (dicTypeEnum.getText().equals(sheetName)) {
						for (int i = 1; i < sheet.getRows(); i++) {
							// sheet.getColumns()���ظ�ҳ��������
							DicForm dicForm = new DicForm();
							dicForm.setTypeId(dicTypeEnum.getCode());
							for (int j = 0; j < sheet.getColumns(); j++) {
								String cellInfo = sheet.getCell(j, i).getContents();
								if (j == 0) {
									dicForm.setCode(cellInfo);
								} else if (j == 1) {
									dicForm.setName(cellInfo);
								} else if (j == 2) {
									if(cellInfo.equals("null")||cellInfo.equals(""))
									{
										dicForm.setOrder(0);
									}else
									{
										dicForm.setOrder(Integer.parseInt(cellInfo));
									}
									
								} else if (j == 3) {
									dicForm.setNote(cellInfo);
								}
							}
							dicFormList.add(dicForm);
						}

					}
				}
			}
			try {
				System.out.println(dicManager.saveBatchDicData(dicFormList));
			} catch (AppException e) {
				e.printStackTrace();
				JsonResultUtil.suncess(model, e.getMessage());
			}
			JsonResultUtil.suncess(model, "����ɹ���");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JsonResultUtil.suncess(model, e.getMessage());
		} catch (BiffException e) {
			e.printStackTrace();
			JsonResultUtil.suncess(model, e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			JsonResultUtil.suncess(model, e.getMessage());
		}

	}

	/**
	 * ɾ��
	 * 
	 * @param model
	 *            {@link ModelMap}
	 * @param result
	 *            {@link BindingResult}
	 * @return
	 */
	@RequestMapping(value = "/removeDic.json", produces = "application/json")
	public void removeDic(ModelMap model, String id) {
		try {
			dicManager.removeDic(id);
			JsonResultUtil.suncess(model, "ɾ���ɹ���");
		} catch (Exception e) {
			log.error("ɾ������", e);
			JsonResultUtil.fail(model, "ɾ��ʧ�ܣ�");
		}
	}

	/**
	 * ����ѡ�����
	 * 
	 * @param id
	 *            �˵�Id
	 * @return �˵����ܵĳ�ʼ����
	 */
	@RequestMapping(value = "/dicTypeQuery.htm")
	public String dicTypeQuery(ModelMap model, String id) {
		try {
			JSONArray re = dicManager.dicTypeQuery();
			model.addAttribute("menu", re.toString());
		} catch (Exception e) {
			log.error("��ѯ�ֵ����ʹ���", e);
		}
		return "sys/dic/dicTypeQuery";
	}

	/**
	 * �ֵ�ѡ��ҳ��
	 */
	@RequestMapping(value = "/sys/dic/dic_sel_page.htm")
	public void dicSelPage(String oper, ModelMap model) {
		model.put("oper", oper);
	}

	/**
	 * �ֵ�ѡ������
	 * 
	 * @param dicType
	 *            �ֵ�����
	 * @param oper
	 *            ���� s-��ѡ��m-��ѡ
	 * @param model
	 */
	@RequestMapping(value = "/dic_sel_list.json", produces = "application/json")
	public void dicSelList(String dicType, ModelMap model) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("rows", dicManager.findByType(dicType));
		} catch (Exception e) {
			log.error("", e);
		}
		JsonResultUtil.show(model, map);
	}
}