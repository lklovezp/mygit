/**
 * hnjz.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.hnjz.sys.dic;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.hnjz.common.AppException;
import com.hnjz.common.FyWebResult;
import com.hnjz.common.YnEnum;
import com.hnjz.common.dao.domain.FyResult;
import com.hnjz.common.dao.domain.QueryCondition;
import com.hnjz.common.domain.Combobox;
import com.hnjz.common.manager.ManagerImpl;
import com.hnjz.common.security.CtxUtil;
import com.hnjz.common.security.OperateUtil;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.sys.po.TSysDic;
import com.hnjz.sys.po.TSysRole;
import com.hnjz.sys.po.TSysRoot;
import com.hnjz.sys.po.TSysUser;
import com.hnjz.sys.po.TSysUserRole;

/**
 * ���Ź���Manager
 * 
 * @author wumi
 * @version $Id: DicManager.java, v 0.1 Jan 15, 2013 5:05:39 PM wumi Exp $
 */
@Service("DicManagerImpl")
public class DicManagerImpl extends ManagerImpl implements DicManager {

	/**
	 * ��ѯ���ɱ༭��ҳ�б�
	 * 
	 * @return �б�
	 * @throws Exception
	 */
	public FyWebResult queryDic(String type, String name, String page,
			String pageSize) throws Exception {

		QueryCondition data = new QueryCondition();
		data.setPageSize(pageSize);
		StringBuilder sql = new StringBuilder();
		sql.append("from TSysDic where type = :type");
		data.put("type", type);
		if (StringUtils.isNotBlank(name)) {
			sql
					.append(" and (name like :name or code like :name or describe like:name )");
			data.putLike("name", name);
		}
		sql.append(" order by code,orderby");
		FyResult pos = dao.find(sql.toString(), data, Integer.parseInt(page));

		FyWebResult fy = new FyWebResult(pos);
		List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
		Map<String, String> row = null;
		List<TSysDic> dics = pos.getRe();
		for (TSysDic ele : dics) {
			row = new HashMap<String, String>();
			row.put("id", ele.getId());
			row.put("dicType", ele.getType());
			row.put("code", ele.getCode());
			row.put("name", ele.getName());
			row.put("note", ele.getDescribe());
			row.put("mandatory", "Y".equals(ele.getMandatory())?"��":"��");
			row.put("orderby", String.valueOf(ele.getOrderby()));
			row.put("operate", OperateUtil.getOperate(ele.getId()));
			rows.add(row);
		}
		fy.setRows(rows);
		return fy;
	}

	/**
	 * ɾ������
	 * 
	 * @param id
	 */
	public void removeDic(String id) {
		this.dao.remove(TSysDic.class, id);
	}

	/**
	 * ��ձ�
	 * 
	 * @param id
	 */
	public void removeDicData() {
		this.dao.removeFindObjs("from TSysDic");
	}

	/**
	 * ����
	 * 
	 * @param {@link DicForm}
	 */
	public void saveDicData(DicForm frm) throws AppException {
		TSysDic po = null;
		TSysUser user = CtxUtil.getCurUser();
		Date cur = new Date();
		if (StringUtils.isNotBlank(frm.getId())) {
			po = (TSysDic) this.get(TSysDic.class, frm.getId());
		} else {
			po = new TSysDic();
			po.setCreateby(user);
			po.setCreated(cur);
		}
		po.setType(frm.getTypeId());
		po.setCode(frm.getCode());
		po.setName(frm.getName());
		po.setDescribe(frm.getNote());
		po.setOrderby(frm.getOrder());

		po.setIsActive(YnEnum.Y.getCode());
		po.setUpdateby(user);
		po.setUpdated(cur);
		// ������ 2015-2-13 ����ʱ��������Ҫ���Ӵ�ѡ�
		po.setIsdefaultsel(YnEnum.N.getCode());
		// ����Ȩ 2015-05-11 Ϊ�������������Ƿ�����ֶ�
		po.setMandatory(frm.getMandatory());
		this.dao.save(po);
	}

	/**
	 * ����
	 * 
	 * @param {@link DicForm}
	 * @throws FileNotFoundException
	 */
	public void dicDataBackUp() throws AppException, FileNotFoundException {

		String path = FileUpDownUtil.path;
		OutputStream output = new FileOutputStream(path + "\\���Ż����ֵ䱸��.xls");
		BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);

		HSSFWorkbook wb = new HSSFWorkbook();
		// ������Ԫ����ʽ
		HSSFCellStyle cellStyleTitle = wb.createCellStyle();
		// ָ����Ԫ����ж���
		cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// ָ����Ԫ��ֱ���ж���
		cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// ָ������Ԫ��������ʾ����ʱ�Զ�����
		cellStyleTitle.setWrapText(true);
		// ------------------------------------------------------------------
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// ָ����Ԫ����ж���
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// ָ����Ԫ��ֱ���ж���
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// ָ������Ԫ��������ʾ����ʱ�Զ�����
		cellStyle.setWrapText(true);
		// ------------------------------------------------------------------
		// ���õ�Ԫ������
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("����");
		font.setFontHeight((short) 200);
		cellStyleTitle.setFont(font);

		TSysDic dicData = new TSysDic();
		List<TSysDic> dicDataList = new ArrayList<TSysDic>();

		QueryCondition data = new QueryCondition();

		List<DicTypeEnum> dicTypeEnumList = DicTypeEnum.getListEnum();
		for (DicTypeEnum dicTypeEnum : dicTypeEnumList) {
			HSSFSheet sheet = wb.createSheet(dicTypeEnum.getText());
			// ��������ͷ��
			// exportExcel.createNormalHead("Excel������Ϣ", 4);
			// �����һ��
			HSSFRow row1 = sheet.createRow(0);
			HSSFCell cell1 = row1.createCell(0);

			// ��һ�е�һ��

			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString("����"));
			// ��һ�еڶ���
			cell1 = row1.createCell(1);
			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString("����"));

			// ��һ�е�����
			cell1 = row1.createCell(2);
			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString("����"));

			// ��һ�е�����
			cell1 = row1.createCell(3);
			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString("��ע"));

			// ����ڶ���
			HSSFRow row = sheet.createRow(1);
			HSSFCell cell = row.createCell(1);

			StringBuilder sql = new StringBuilder();
			sql.append("from TSysDic where type = :type");
			data.put("type", dicTypeEnum.getCode());
			sql.append("  order by orderby");
			dicDataList = this.dao.find(sql.toString(), data);
			for (int i = 0; i < dicDataList.size(); i++) {
				dicData = dicDataList.get(i);
				row = sheet.createRow(i + 1);

				cell = row.createCell(0);
				cell.setCellStyle(cellStyle);
				cell
						.setCellValue(new HSSFRichTextString(dicData.getCode()
								+ ""));

				cell = row.createCell(1);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(dicData.getName()));

				cell = row.createCell(2);
				cell.setCellStyle(cellStyle);
				if (dicData.getOrderby() == null) {
					String orderStr = "";
					cell.setCellValue(new HSSFRichTextString(orderStr));
				} else {
					cell.setCellValue(new HSSFRichTextString(String
							.valueOf(dicData.getOrderby())));
				}

				cell = row.createCell(3);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(new HSSFRichTextString(dicData.getDescribe() + ""));
			}
		}
		try {
			bufferedOutPut.flush();
			wb.write(bufferedOutPut);
			bufferedOutPut.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			dicDataList.clear();
		}
	}

	/**
	 * ����
	 * 
	 * @param {@link DicForm}
	 */
	public int saveBatchDicData(List<DicForm> dicFormList) throws AppException {
		List<TSysDic> dicDataList = new ArrayList<TSysDic>();
		for (DicForm dicForm : dicFormList) {
			TSysDic po = new TSysDic();
			TSysUser user = CtxUtil.getCurUser();
			Date cur = new Date();

			po.setCreateby(user);
			po.setCreated(cur);

			po.setType(dicForm.getTypeId());
			po.setCode(dicForm.getCode());
			po.setName(dicForm.getName());
			po.setDescribe(dicForm.getNote());
			po.setOrderby(dicForm.getOrder());

			po.setIsActive(YnEnum.Y.getCode());
			po.setUpdateby(user);
			po.setUpdated(cur);
			dicDataList.add(po);
		}

		return this.dao.batchSave(dicDataList.toArray());
	}

	/**
	 * ��ѯ
	 * 
	 * @throws Exception
	 */
	public JSONArray dicTypeQuery() throws Exception {
		JSONArray re = new JSONArray();
		List<DicTypeEnum> dics = DicTypeEnum.getListEnum();
		JSONObject jsonObj = null;
		for (DicTypeEnum ele : dics) {
			jsonObj = new JSONObject();
			jsonObj.put("id", ele.getCode());
			jsonObj.put("name", ele.getText());
			re.put(jsonObj);
		}
		return re;
	}

	/**
	 * �޷�ҳ��ѯ,����type
	 * 
	 * @throws Exception
	 */
	public JSONArray dicDateQuery(String type) throws Exception {
		JSONArray re = new JSONArray();
		JSONObject jsonObj = null;

		QueryCondition data = new QueryCondition();
		StringBuilder sql = new StringBuilder();
		sql.append("from TSysDic where type = :type");
		data.put("type", type);
		sql.append("  order by orderby");
		List<TSysDic> pos = dao.find(sql.toString(), data);
		for (TSysDic ele : pos) {
			jsonObj = new JSONObject();
			jsonObj.put("id", ele.getId());
			jsonObj.put("dicType", ele.getType());
			jsonObj.put("code", ele.getCode());
			jsonObj.put("name", ele.getName());
			jsonObj.put("note", ele.getDescribe());
			jsonObj.put("type", "u");
			jsonObj.put("pid", "0");
			re.put(jsonObj);
		}

		return re;
	}

	/**
	 * �����Ͳ�ѯȫ��������ѡ��ҳ��id,name��
	 * 
	 * @param type
	 *            �ֵ����ͣ������ö��DicTypeEnum
	 * @return ĳ�������¶���������ֵ伯��
	 */
	public List<Object> findByType(String type) {
		String hql = "from TSysDic where type=? order by orderby";
		List<TSysDic> pos = dao.find(hql, type);
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < pos.size(); i++) {
			list.add(pos.get(i).toSelMap());
		}
		return list;
	}

	/**
	 * ��ѯĳ���ֵ������µ���������(code,name)
	 * 
	 * @param dicType
	 *            �ֵ�����{@link DicTypeEnum}
	 * @return ĳ�������¶���������ֵ伯��
	 */
	public List<Combobox> queryDicData(DicTypeEnum dicType) {
		String hsql = "from TSysDic where  type=? order by orderby";
		List<TSysDic> infoList = dao.find(hsql, dicType.getCode());
		List<Combobox> re = new ArrayList<Combobox>();
		for (TSysDic ele : infoList) {
			re.add(new Combobox(ele.getCode(), ele.getName()));
		}
		return re;
	}

	/**
	 * ��ѯĳ���ֵ������µ���������(code,name)
	 * 
	 * @param dicType
	 *            �ֵ�����{@link DicTypeEnum}
	 * @return ĳ�������¶���������ֵ伯��
	 */
	public List<Combobox> queryDicDataByCode(String diccode) {
		String hsql = "from TSysDic where  type=? order by orderby";
		List<TSysDic> infoList = dao.find(hsql, diccode);
		List<Combobox> re = new ArrayList<Combobox>();
		for (TSysDic ele : infoList) {
			re.add(new Combobox(ele.getCode(), ele.getName()));
		}
		return re;
	}

	/**
	 * ��ѯΥ������
	 * 
	 * @return
	 */
	public List<Map<String, Object>> queryIllegalType() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("id", "0");
		root.put("name", "Υ������");
		root.put("type", "o");
		list.add(root);
		String hql = "from TSysDic where type = 1 order by orderby";
		List<TSysDic> dicList = dao.find(hql);
		for (TSysDic ele : dicList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", ele.getId());
			map.put("name", ele.getName());
			map.put("type", "u");
			map.put("pid", "0");
			list.add(map);
		}
		return list;
	}

	/**
	 * ��ѯĳ���ֵ������µ���������(code,name)
	 * 
	 * @param dicType
	 *            �ֵ�����{@link DicTypeEnum}
	 * @return ĳ�������¶���������ֵ伯��
	 */
	public List<Combobox> queryDicDataId(DicTypeEnum dicType) {
		String hsql = "from TSysDic where  type=? order by orderby";
		List<TSysDic> infoList = dao.find(hsql, dicType.getCode());
		List<Combobox> re = new ArrayList<Combobox>();
		for (TSysDic ele : infoList) {
			re.add(new Combobox(ele.getId(), ele.getName()));
		}
		return re;
	}

	@Override
	public String getNameByTypeAndCode(String type, String code) {
		String hsql = "from TSysDic where  type=? and code = ? ";
		List<TSysDic> list = dao.find(hsql, type, code);
		if (list.size() > 0) {
			return list.get(0).getName();
		}
		return null;
	}
	/**
	 * ͨ��code��ѯĳ���ֵ�����(code)
	 * 
	 * @param code
	 *            �ֵ���
	 * @return ĳ���ֵ�����
	 */
	public TSysDic queryDicByCode(String type, String code){
		String hsql = "from TSysDic where  type=? and code = ? ";
		List<TSysDic> list = dao.find(hsql, type, code);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	/**
	 * ��ѯ���еĸ�Ŀ¼
	 */
	@Override
	public List<String[]> queryRootCode() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM T_SYS_ROOT a where STATE_='Y' ORDER BY to_number(PID_)");
		List<String[]> list = dao.findBySql(sql.toString());
		return list;
	}
	/**
	 * ������Ų�ѯ�Ƿ��ظ�
	 * @throws AppException 
	 */
	public void findRootxh(DicForm df) throws AppException{
		
		TSysRoot po = null;
		TSysUser user = CtxUtil.getCurUser();
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		if (StringUtils.isNotBlank(df.getId())) {
			po = (TSysRoot) this.get(TSysRoot.class, df.getId());
		} else {
			po = new TSysRoot();
			po.setCreateUserId(user.getAreaId());
			po.setPid(df.getCode());
			po.setState(YnEnum.Y.getCode());
		}
		po.setCreateTime(dateString);
		po.setName(df.getName());
		this.dao.save(po);
	}
	/**
	 * ����idɾ����Ŀ¼
	 */
	@Override
	public void deleteData(String id) {
		try {
			TSysRoot del = (TSysRoot) this.dao.get(TSysRoot.class, id);
			del.setState(YnEnum.N.getCode());
			this.dao.save(del);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ���Ӹ�Ŀ¼
	 */
	@Override
	public void addData(String name, String id,String userId,String dateString) {
		TSysRoot root = new TSysRoot();
		//root.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		root.setPid(id);
		root.setName(name);
		root.setState("Y");
		root.setCreateUserId(userId);
		root.setCreateTime(dateString);
		root.setBak("");
		this.dao.save(root);
	}

	@Override
	public void findRootxhcc(String id) throws AppException {
		StringBuilder sql = new StringBuilder();
		QueryCondition data = new QueryCondition();
			sql.append("SELECT PID_ FROM T_SYS_ROOT WHERE PID_ = :xh ");
			data.put("xh", id);
			List<String> list = dao.findBySql(sql.toString(), data.getCanshu());
			
			if(list.size()>0){
				throw new AppException("��Ų����ظ���");
			}
	}

}