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
 * 部门管理Manager
 * 
 * @author wumi
 * @version $Id: DicManager.java, v 0.1 Jan 15, 2013 5:05:39 PM wumi Exp $
 */
@Service("DicManagerImpl")
public class DicManagerImpl extends ManagerImpl implements DicManager {

	/**
	 * 查询不可编辑分页列表
	 * 
	 * @return 列表
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
			row.put("mandatory", "Y".equals(ele.getMandatory())?"是":"否");
			row.put("orderby", String.valueOf(ele.getOrderby()));
			row.put("operate", OperateUtil.getOperate(ele.getId()));
			rows.add(row);
		}
		fy.setRows(rows);
		return fy;
	}

	/**
	 * 删除功能
	 * 
	 * @param id
	 */
	public void removeDic(String id) {
		this.dao.remove(TSysDic.class, id);
	}

	/**
	 * 清空表
	 * 
	 * @param id
	 */
	public void removeDicData() {
		this.dao.removeFindObjs("from TSysDic");
	}

	/**
	 * 保存
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
		// 赵文明 2015-2-13 开发时，界面需要增加此选项。
		po.setIsdefaultsel(YnEnum.N.getCode());
		// 任正权 2015-05-11 为附件类型添加是否必填字段
		po.setMandatory(frm.getMandatory());
		this.dao.save(po);
	}

	/**
	 * 保存
	 * 
	 * @param {@link DicForm}
	 * @throws FileNotFoundException
	 */
	public void dicDataBackUp() throws AppException, FileNotFoundException {

		String path = FileUpDownUtil.path;
		OutputStream output = new FileOutputStream(path + "\\兵团环保字典备份.xls");
		BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);

		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建单元格样式
		HSSFCellStyle cellStyleTitle = wb.createCellStyle();
		// 指定单元格居中对齐
		cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		cellStyleTitle.setWrapText(true);
		// ------------------------------------------------------------------
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 指定单元格居中对齐
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);
		// ------------------------------------------------------------------
		// 设置单元格字体
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		cellStyleTitle.setFont(font);

		TSysDic dicData = new TSysDic();
		List<TSysDic> dicDataList = new ArrayList<TSysDic>();

		QueryCondition data = new QueryCondition();

		List<DicTypeEnum> dicTypeEnumList = DicTypeEnum.getListEnum();
		for (DicTypeEnum dicTypeEnum : dicTypeEnumList) {
			HSSFSheet sheet = wb.createSheet(dicTypeEnum.getText());
			// 创建报表头部
			// exportExcel.createNormalHead("Excel导出信息", 4);
			// 定义第一行
			HSSFRow row1 = sheet.createRow(0);
			HSSFCell cell1 = row1.createCell(0);

			// 第一行第一列

			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString("代码"));
			// 第一行第二列
			cell1 = row1.createCell(1);
			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString("名称"));

			// 第一行第三列
			cell1 = row1.createCell(2);
			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString("排序"));

			// 第一行第四列
			cell1 = row1.createCell(3);
			cell1.setCellStyle(cellStyleTitle);
			cell1.setCellValue(new HSSFRichTextString("备注"));

			// 定义第二行
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
	 * 保存
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
	 * 查询
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
	 * 无分页查询,根据type
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
	 * 按类型查询全部（用于选择页面id,name）
	 * 
	 * @param type
	 *            字典类型，定义见枚举DicTypeEnum
	 * @return 某个类型下定义的所有字典集合
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
	 * 查询某个字典类型下的所有数据(code,name)
	 * 
	 * @param dicType
	 *            字典类型{@link DicTypeEnum}
	 * @return 某个类型下定义的所有字典集合
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
	 * 查询某个字典类型下的所有数据(code,name)
	 * 
	 * @param dicType
	 *            字典类型{@link DicTypeEnum}
	 * @return 某个类型下定义的所有字典集合
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
	 * 查询违法类型
	 * 
	 * @return
	 */
	public List<Map<String, Object>> queryIllegalType() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("id", "0");
		root.put("name", "违法类型");
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
	 * 查询某个字典类型下的所有数据(code,name)
	 * 
	 * @param dicType
	 *            字典类型{@link DicTypeEnum}
	 * @return 某个类型下定义的所有字典集合
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
	 * 通过code查询某个字典数据(code)
	 * 
	 * @param code
	 *            字典编号
	 * @return 某个字典数据
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
	 * 查询所有的根目录
	 */
	@Override
	public List<String[]> queryRootCode() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM T_SYS_ROOT a where STATE_='Y' ORDER BY to_number(PID_)");
		List<String[]> list = dao.findBySql(sql.toString());
		return list;
	}
	/**
	 * 根据序号查询是否重复
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
	 * 根据id删除根目录
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
	 * 添加根目录
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
				throw new AppException("序号不能重复！");
			}
	}

}
