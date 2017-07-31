package com.hnjz.common.util.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.multipart.MultipartFile;

import com.hnjz.app.data.po.TDataLawobj;
import com.hnjz.app.data.po.TDataLawobjdic;
import com.hnjz.common.upload.FileUpDownUtil;
import com.hnjz.common.upload.UploadFileType;
import com.hnjz.common.util.DateUtil;
import com.hnjz.common.util.StringUtil;

/**
 * 根据工作表、sheet名称、坐标值读取指定值
 * 
 * @param workbook
 *			Excel工作表
 * @param sheetName
 *			sheet名称
 * @param x
 *			行坐标
 * @param y
 *			列坐标
 * @return String
 * @throws Exception
 * @throws IOException
 */
@SuppressWarnings("deprecation")
public class ExcelUtil {
	private Logger log = Logger.getLogger(this.getClass());

	/** 文件路径 */
	private String fileAddress = null;

	/** 输入流对象 */
	private InputStream is = null;

	/** 输出流对象 */
	private OutputStream os = null;

	/** Execl对象 */
	private HSSFWorkbook workbook = null;

	public ExcelUtil() {
	}

	public ExcelUtil(String filePath) throws IOException {
		this.fileAddress = filePath;
		is = new FileInputStream(new File(fileAddress));
		workbook = new HSSFWorkbook(is);
		closeRes();
	}

	public ExcelUtil(InputStream is) throws IOException {
		workbook = new HSSFWorkbook(is);
		is.close();
	}

	public static File setValue(File file, File file2, File xmlFile,
			Map<String, List<Object>> valueMap, boolean isOneModel)
			throws Exception {
		ConfigObj configObj = XMLUtil.readXML(xmlFile);// 读取xml配置文件返回要读取的参数
		return setValue(file, file2, configObj, valueMap, isOneModel);
	}

	public static File setValue(File file, File file2, File xmlFile,
			Map<String, List<Object>> valueMap) throws Exception {
		ConfigObj configObj = XMLUtil.readXML(xmlFile);// 读取xml配置文件返回要读取的参数
		return setValue(file, file2, configObj, valueMap);
	}

	public static File setValue(File file, File xmlFile,
			Map<String, List<Object>> valueMap) throws Exception {
		ConfigObj configObj = XMLUtil.readXML(xmlFile);// 读取xml配置文件返回要读取的参数
		return setValue(file, null, configObj, valueMap);
	}

	private static void autoCopysheet(Set<String> set, HSSFWorkbook workbook) {

		for (String str : set) {
			HSSFSheet c = workbook.cloneSheet(0);
			workbook.setSheetName(workbook.getSheetIndex(c), str);

		}
		workbook.removeSheetAt(0);
	}

	public static File setValue(File file, File file2, ConfigObj configObj,
			Map<String, List<Object>> valueMap, boolean isOneModel)
			throws Exception {

		// 当要使用一个模板生成所有sheet时
		if (isOneModel) {

			HSSFWorkbook workbook = new HSSFWorkbook();
			InputStream is = null;
			if (file2 != null) {
				is = new FileInputStream(file2);
				workbook = new HSSFWorkbook(is);
				if (is != null) {
					is.close();
				}
			}
			// 将模板的内容拷贝出多个sheet，并将其名字修改为何参量valueMap的sheet一致，再将模板中原有的sheet删除。
			autoCopysheet(valueMap.keySet(), workbook);

			if(!file.exists()){
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			OutputStream os = new FileOutputStream(file);

			try {

				List<SheetObj> sheetObjeList = configObj.getSheetObjList();
				if (sheetObjeList.size() >= 0) {
					// 读出配置文件中的填值配置项
					SheetObj sheetObj = sheetObjeList.get(0);
					List<Object> list = null;
					// 按valueMap的sheet进行逐一读取
					for (String sheetName : valueMap.keySet()) {
						// 将配置项中的sheet名称和key均修改为valueMap中的sheet
						sheetObj.setName(sheetName);
						sheetObj.setKey(sheetName);
						// 开始写入操作
						if (StringUtils.isNotBlank(sheetObj.getKey())) {
							list = valueMap.get(sheetName);
							sheetObj.setObjectList(workbook, sheetName, list);
						} else if (StringUtils.isNotBlank(sheetObj.getName())) {
							list = valueMap.get(sheetName);
							sheetObj.setObjectList(workbook, sheetName, list);
						} else {
							for (String k : valueMap.keySet()) {
								List<Object> klist = valueMap.get(k);
								if (StringUtils.isBlank(k)) {
									k = workbook.getSheetAt(0).getSheetName();
								}

								sheetObj.setObjectList(workbook, k, klist);
							}
						}
					}
				}
				workbook.write(os);
				os.flush();
				os.close();
			} catch (Exception e) {
				os.flush();
				os.close();
				throw e;
			}

			return file;

		} else {
			HSSFWorkbook workbook = new HSSFWorkbook();
			InputStream is = null;
			if (file2 != null) {
				is = new FileInputStream(file2);
				workbook = new HSSFWorkbook(is);
				if (is != null) {
					is.close();
				}
			}
			if(!file.exists()){
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			OutputStream os = new FileOutputStream(file);

			try {

				List<SheetObj> sheetObjeList = configObj.getSheetObjList();

				for (SheetObj sheetObj : sheetObjeList) {
					List<Object> list = null;
					String sheetName = null;
					if (StringUtils.isNotBlank(sheetObj.getKey())) {
						list = valueMap.get(sheetObj.getKey());
						sheetObj.setObjectList(workbook, sheetName, list);
					} else if (StringUtils.isNotBlank(sheetObj.getName())) {
						list = valueMap.get(sheetObj.getName());
						sheetObj.setObjectList(workbook, sheetName, list);
					} else {
						for (String k : valueMap.keySet()) {
							List<Object> klist = valueMap.get(k);
							if (StringUtils.isBlank(k)) {
								k = workbook.getSheetAt(0).getSheetName();
							}

							sheetObj.setObjectList(workbook, k, klist);
						}
					}

				}
				workbook.write(os);
				os.flush();
				os.close();
			} catch (Exception e) {
				os.flush();
				os.close();
				throw e;
			}

			return file;
		}

	}

	public static File setValue(File file, File file2, ConfigObj configObj,
			Map<String, List<Object>> valueMap) throws Exception {

		return setValue(file, file2, configObj, valueMap, false);
	}

	public static InputStream setValue2(File file2, File xmlFile,
			Map<String, List<Object>> valueMap) throws Exception {
		ConfigObj configObj = XMLUtil.readXML(xmlFile);// 读取xml配置文件返回要读取的参数
		return setValue2(file2, configObj, valueMap);
	}

	public static InputStream setValue2(File file2, ConfigObj configObj,
			Map<String, List<Object>> valueMap) throws Exception {
		List<SheetObj> sheetObjeList = configObj.getSheetObjList();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		HSSFWorkbook workbook = new HSSFWorkbook();
		InputStream is = null;
		if (file2 != null) {
			is = new FileInputStream(file2);
			workbook = new HSSFWorkbook(is);
		}
		for (SheetObj sheetObj : sheetObjeList) {
			List<Object> list = null;
			if (StringUtils.isNotBlank(sheetObj.getKey())) {
				list = valueMap.get(sheetObj.getKey());
			} else {
				list = valueMap.get(sheetObj.getName());
			}

			sheetObj.setObjectList(workbook, null, list);
		}
		workbook.write(os);
		os.flush();

		byte[] content = os.toByteArray();
		InputStream is2 = new ByteArrayInputStream(content);
		os.close();
		if (is != null) {
			is.close();
		}
		return is2;
	}

	public static boolean setValue(HSSFWorkbook workbook, String value,
			String sheetName, int x, int y) throws Exception, IOException {

		HSSFSheet ws = workbook.getSheet(sheetName);

		if (ws == null) {
			ws = workbook.createSheet(sheetName);

		}
		HSSFRow row = ws.getRow(x);
		if (row == null) {
			row = ws.createRow(x);
		}
		HSSFCell cell = row.getCell(y);
		if (cell == null) {
			cell = row.createCell(y);
		}
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		if (value.indexOf("=") == 0) {
			cell.setCellFormula(value.substring(1));
		} else {
			cell.setCellValue(value);
		}

		return true;
	}

	public static String getValue(HSSFWorkbook workbook, String sheetName,
			int x, int y) throws Exception, IOException {

		HSSFSheet ws = workbook.getSheet(sheetName);
		String value = "";
		if (ws != null) {

			Object o = new Object();
			HSSFRow row = ws.getRow(x);
			if (row == null) {
				return "";
			}
			HSSFCell cell = row.getCell(y);
			if (cell == null) {
				return "";
			}

			if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				o = cell.getStringCellValue();
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {

				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					o = sdf.format(date);
				} else {
					o = cell.getNumericCellValue();
				}

			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
				o = cell.getBooleanCellValue();
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
				o = cell.getCellFormula();
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_ERROR) {
				o = cell.getErrorCellValue();
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
				o = "";
			}
			value = o.toString();
		}
		// is.close();
		return value;
	}

	/**
	 * 根据Excel文件和Xml配置文件所生成类得到要读取的内容
	 * 
	 * @param file
	 *			Excel 文件
	 * @param configObj
	 *			封装Xml文件属性类
	 * @return Map<String, List<Object>> 返回值
	 * @throws Exception
	 */
	public static Map<String, List<Object>> getValue(File file,
			ConfigObj configObj) throws Exception {
		Map<String, List<Object>> outMap = new LinkedHashMap<String, List<Object>>();// 返回值
		List<SheetObj> sheetObjeList = configObj.getSheetObjList();
		InputStream is = new FileInputStream(file);
		HSSFWorkbook workbook = new HSSFWorkbook(is);
		for (SheetObj sheetObj : sheetObjeList) {
			List<Object> list = sheetObj.getObjectList(workbook);
			if (StringUtils.isNotBlank(sheetObj.getKey())) {
				outMap.put(sheetObj.getKey(), list);
			} else {
				outMap.put(sheetObj.getName(), list);
			}

		}
		is.close();
		return outMap;
	}

	/**
	 * 传入Excel文件和Xml配置读取文件
	 * 
	 * @param file
	 *			Excel 文件
	 * @param xmlFile
	 *			Xml配置读取文件
	 * @return Map<String, List<Object>> 返回值
	 * @throws Exception
	 */
	public static Map<String, List<Object>> getValue(File file, File xmlFile)
			throws Exception {
		ConfigObj configObj = XMLUtil.readXML(xmlFile);// 读取xml配置文件返回要读取的参数
		return getValue(file, configObj);
	}

	public void read() throws IOException {
		Integer sheetNo = workbook.getNumberOfSheets();
		for (int i = 0; i < sheetNo; i++) {
			HSSFSheet sheet = workbook.getSheetAt(i);
			int firstRowNum = sheet.getFirstRowNum();
			int lastRowNum = sheet.getLastRowNum();
			for (int j = firstRowNum; j <= lastRowNum; j++) {
				HSSFRow row = sheet.getRow(j);
				if (row != null) {
					Iterator<Cell> cellIt = row.cellIterator();
					while (cellIt.hasNext()) {
						Cell cell = cellIt.next();
						getStringCellValue((HSSFCell) cell);
					}
				}
			}
		}
		closeRes();
	}

	/**
	 * 将第一行的样式应用到下面制定的所有行 （即将x所在行当做样式，应用到下面所有行）
	 * 
	 * @param file
	 *			处理的文件
	 * @param sheetName
	 *			处理的sheet名字
	 * @param x
	 *			要处理的开始行
	 * @param y
	 *			要处理的开始列
	 * @param endx
	 *			处理到哪个行
	 * @param endy
	 *			处理到哪个列
	 * @throws Exception
	 */
	public static void copyStyle(File file, String sheetName, int x, int y,
			int endx, int endy) throws Exception {
		InputStream is = null;
		HSSFWorkbook workbook = new HSSFWorkbook();
		try {
			is = new FileInputStream(file);
			workbook = new HSSFWorkbook(is);
			if (is != null) {
				is.close();
			}
		} catch (Exception e) {
			throw e;
		}

		OutputStream os = new FileOutputStream(file);
		HSSFSheet ws = null;
		if (StringUtils.isNotBlank(sheetName)) {
			ws = workbook.getSheet(sheetName);
		} else {
			ws = workbook.getSheetAt(0);
		}
		try {
			// 循环读取列
			for (int i = y; i <= endy; i++) {
				HSSFCellStyle mode = null;
				int ycount = 0;
				{
					if (ws == null) {
						ws = workbook.createSheet(sheetName);

					}

					HSSFRow row = ws.getRow(x);
					if (row == null) {
						row = ws.createRow(x);
					}
					HSSFCell cell = row.getCell(i);
					if (cell == null) {
						cell = row.createCell(i);
					}

					ycount = ExcelUtil.getMergedRegionYcount(ws, x, i);
					mode = cell.getCellStyle();

				}
				// 循环读取行
				for (int j = x + 1; j <= endx; j++) {

					if (ws == null) {
						ws = workbook.createSheet(sheetName);

					}

					HSSFRow row = ws.getRow(j);
					if (row == null) {
						row = ws.createRow(j);
					}

					for (int k = 0; k < ycount + 1; k++) {
						HSSFCell cell = row.getCell(i + k);
						if (cell == null) {
							cell = row.createCell(i + k);
						}
						// 应用样式
						cell.setCellStyle(mode);
					}
					if (ycount > 0) {
						Region r = new Region(j, (short) (i), j,
								(short) (i + ycount));

						ws.addMergedRegion(r);
					}

				}
				i += ycount;
			}

			workbook.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			os.flush();
			os.close();
			throw e;
		}

	}

	/**
	 * 函数介绍：被调用的输出HTML方法 输入参数：request-HttpServletRequest readRowCross-读的行数，-1全部
	 * hasValueNameList-有值的坐标集合 select-是否选择状态，Y 就只可选择，不可设置 返回值：
	 */
	@SuppressWarnings("unchecked")
	public String[] outputHTML(HttpServletRequest request, int readRowCross,
			List<String> hasValueNameList, String select) throws IOException {
		StringBuffer htmlStr = new StringBuffer();
		StringBuffer positionStr = new StringBuffer();
		List<Object> list = getContentForList();
		for (Object obj : list) {
			Map<String, Object> sheetObj = (Map<String, Object>) obj;
			/****************** 多记录时过滤空行BEGIN ******************/
			if (readRowCross != -1) {
				List<Map<String, List<Map<String, Object>>>> rowList = (List<Map<String, List<Map<String, Object>>>>) sheetObj
						.get("rows");
				List<Map<String, List<Map<String, Object>>>> rowListResult = new ArrayList<Map<String, List<Map<String, Object>>>>();
				boolean hasEmpty = false;
				for (int i = 0; i < rowList.size(); i++) {
					Map<String, List<Map<String, Object>>> rowMap = rowList
							.get(i);
					List<Map<String, Object>> cellList = rowMap.get("cells");
					for (Map<String, Object> cellMap : cellList) {
						if (!cellMap.get("value").toString().equals("")) {
							rowListResult.add(rowList.get(i));
							break;
						}
						if (hasEmpty == false) {
							hasEmpty = true;
							rowMap.put("rowT",
									new ArrayList<Map<String, Object>>());
							rowListResult.add(rowMap);
						}
					}
				}
				sheetObj.put("rows", rowListResult);
			}
			/****************** 多记录时过滤空行END ******************/
			String[] result = getStrForHtmlTable(sheetObj,
					request.getContextPath(), hasValueNameList, 0, select);
			htmlStr.append(result[0]);
			positionStr.append(result[1]);
		}
		return new String[] { htmlStr.toString(), positionStr.toString() };
	}

	/**
	 * 函数介绍：被调用的输出HTML方法 输入参数：request-HttpServletRequest 返回值：
	 */
	@SuppressWarnings("unchecked")
	public String outputHTML(HttpServletRequest request) throws IOException {
		StringBuffer htmlStr = new StringBuffer();
		List<Object> list = getContentForList();
		for (Object obj : list) {
			String[] result = getStrForHtmlTable((Map<String, Object>) obj,
					request.getContextPath(), null, 1, null);
			htmlStr.append(result[0]);
		}
		return htmlStr.toString();
	}

	/**
	 * 函数介绍：输出HTML语句 输入参数： 返回值：
	 */
	@SuppressWarnings("unchecked")
	private String[] getStrForHtmlTable(Map<String, Object> sheetMap,
			String serverName, List<String> hasValueNameList,
			Integer outputType, String select) {
		StringBuffer positions = new StringBuffer();
		StringBuffer str = new StringBuffer();
		str.append("<table class=\"k-0\" width=\"90%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-right:1px solid #98B1C0;border-bottom:1px solid #98B1C0;margin-left:15px;\">");

		List<Map<String, Object>> sheetMap_rows = (List<Map<String, Object>>) sheetMap
				.get("rows");
		// Integer sheetMap_cellNum = (Integer) sheetMap.get("cellNum");
		for (Map<String, Object> rowMap : sheetMap_rows) {
			if (rowMap.get("rowT") != null) {
				str.append("<tbody id=\"myTbody\">");
			}
			str.append("<tr").append(" style:\"height:")
					.append((Float) rowMap.get("rowHeigth")).append("px\">");
			// Float row_rowHeigth = (Float) rowMap.get("rowHeigth");
			List<Map<String, Object>> row_cellList = (List<Map<String, Object>>) rowMap
					.get("cells");
			if (row_cellList.size() > 0) {
				for (Map<String, Object> cellMap : row_cellList) {
					str.append("<td ");
					if (cellMap.get("x_cross") != null
							&& Integer.parseInt(cellMap.get("x_cross")
									.toString()) > 1) {
						str.append("colspan=\"").append(cellMap.get("x_cross"))
								.append("\"");
					}
					if (cellMap.get("y_cross") != null
							&& Integer.parseInt(cellMap.get("y_cross")
									.toString()) > 1) {
						str.append("rowspan=\"").append(cellMap.get("y_cross"))
								.append("\"");
					}
					if (cellMap.get("cellStyle") != null
							&& !cellMap.get("cellStyle").equals("")) {
						str.append(" ").append(cellMap.get("cellStyle"));
					}
					str.append(" id=\"td_").append(cellMap.get("x"))
							.append(",").append(cellMap.get("y")).append("\"");
					str.append(">");
					if (cellMap.get("value").equals("")) {
						if (outputType == 0) {
							StringBuffer objName = new StringBuffer();
							objName.append(cellMap.get("x")).append(",")
									.append(cellMap.get("y"));
							str.append("<img src=\"").append(serverName)
									.append("/style/images/");
							if (hasValueNameList != null
									&& hasValueNameList.contains(objName
											.toString())) {
								str.append("Done Square")
										.append(".gif\" width=\"13\" height=\"13\"  border=\"0\"")
										.append(" id=\"")
										.append(cellMap.get("x"))
										.append(",")
										.append(cellMap.get("y"))
										.append("\"")
										.append(" name=\"")
										.append(cellMap.get("x"))
										.append(",")
										.append(cellMap.get("y"))
										.append("\"")
										.append(" align=\"absmiddle\" style=\"cursor: hand\"");
								if ("Y".equals(select)) {
									str.append(" onclick=\"setFieldValue('")
											.append(objName).append("')\"");
								} else {
									str.append(" onclick=\"setTableValue(this)\"");
								}
								str.append("/>");
							} else {
								str.append("Caution")
										.append(".gif\" width=\"13\" height=\"13\"  border=\"0\"")
										.append(" id=\"")
										.append(cellMap.get("x"))
										.append(",")
										.append(cellMap.get("y"))
										.append("\"")
										.append(" name=\"")
										.append(cellMap.get("x"))
										.append(",")
										.append(cellMap.get("y"))
										.append("\" align=\"absmiddle\" style=\"cursor: hand\"");
								if (!"Y".equals(select)) {
									str.append(" onclick=\"setTableValue(this)\"");
								}
								str.append("/>");
							}
							positions.append(objName).append(";");
						} else if (outputType == 1) {
							str.append("&nbsp;");
						}
					} else {
						str.append(cellMap.get("value"));
					}
					str.append("</td>");
				}
			} else {
				// str.append("<td colspan=\"").append(sheetMap_cellNum).append(
				// "\">&nbsp;</td>");
				// for (int td = 0; td < sheetMap_cellNum; td++) {
				// str.append("<td>&nbsp;</td>");
				// }
			}
			str.append("</tr>");
			if (rowMap.get("rowT") != null) {
				str.append("</tbody>");
			}
		}

		str.append("</table>");
		return new String[] { str.toString(), positions.toString() };
	}

	/**
	 * 函数介绍：整理EXECL内容为集合 输入参数： 返回值：
	 * sheetList[sheetMap{"name-sheet页名称","rows-行集合rowMap{"
	 * row_*-行cellMap{"y-行标",
	 * "x-纵标","y_cross-行跨","x_cross-纵跨","value-值"}"}","cellNum-最大单元格数"}]
	 */
	private List<Object> getContentForList() throws IOException {
		int sheetNo = 1;// workbook.getNumberOfSheets();
		List<Object> sheetList = new ArrayList<Object>();
		for (int i = 0; i < sheetNo; i++) {// SHEET
			Map<String, Object> sheetMap = new HashMap<String, Object>();// SHEET信息
			List<Map<String, Object>> rowList = new ArrayList<Map<String, Object>>();
			int cellNum = 0;
			HSSFSheet sheet = workbook.getSheetAt(i);
			int firstRowNum = sheet.getFirstRowNum();
			int lastRowNum = sheet.getLastRowNum();
			// if (readRowCross != -1) {
			// for (int sIndex = firstRowNum; sIndex <= lastRowNum; sIndex++) {
			// HSSFRow hssfRow = sheet.getRow(sIndex);
			// Iterator<Cell> hssfCellIt = hssfRow.cellIterator();
			// if (hssfCellIt.hasNext()) {
			// if (!getStringCellValue((HSSFCell) hssfCellIt.next())
			// .equals("")) {
			// continue;
			// } else {
			// lastRowNum = sIndex;
			// break;
			// }
			// }
			// }
			// }
			int j = firstRowNum;
			while (j <= lastRowNum) {
				Map<String, Object> rowMap = new LinkedHashMap<String, Object>();
				HSSFRow row = sheet.getRow(j);
				List<Map<String, Object>> cellList = new ArrayList<Map<String, Object>>();
				// int maxRow = 1;
				if (row != null) {
					if (cellNum < row.getPhysicalNumberOfCells()) {
						cellNum = row.getPhysicalNumberOfCells();
					}
					int firstCol = row.getFirstCellNum();// 第一个单元格
					int lastCol = row.getLastCellNum();// 最后一个单元格
					int k = firstCol;// 计数
					if (k >= lastCol) {// 当计数不小于最后单元格进入下一行
					} else {
						while (k < lastCol) {
							HSSFCell cell = row.getCell(k);
							Map<String, Object> cellMap = new HashMap<String, Object>();
							if (cell != null) {
								CellRangeAddress range = getArea(sheet,
										cell.getRowIndex(),
										cell.getColumnIndex());
								if (range != null) {// 为合并单元格
									if (range.getFirstRow() == j
											&& range.getFirstColumn() == k) {
										cellMap.put("y", range.getFirstRow());
										cellMap.put("x", range.getFirstColumn());
										cellMap.put(
												"y_cross",
												range.getLastRow()
														- range.getFirstRow()
														+ 1);
										cellMap.put(
												"x_cross",
												range.getLastColumn()
														- range.getFirstColumn()
														+ 1);
									} else {
										k++;
										continue;
									}
								} else {
									cellMap.put("y", cell.getRowIndex());
									cellMap.put("x", cell.getColumnIndex());
								}
								HSSFCellStyle cellStyle = cell.getCellStyle();
								StringBuffer cellStyleStr = new StringBuffer();
								cellStyleStr.append("style=\"");
								if (cellStyle.getAlignment() == HSSFCellStyle.ALIGN_CENTER) {
									cellStyleStr.append("text-align:center;");
								} else if (cellStyle.getAlignment() == HSSFCellStyle.ALIGN_LEFT) {
									cellStyleStr.append("text-align:left;");
								} else if (cellStyle.getAlignment() == HSSFCellStyle.ALIGN_RIGHT) {
									cellStyleStr.append("text-align:right;");
								}
								HSSFFont cellFont = cellStyle.getFont(workbook);
								cellStyleStr
										.append("font-size:")
										.append(cellFont
												.getFontHeightInPoints())
										.append("px;");
								cellStyleStr
										.append("border-top:1px solid #98B1C0;border-left:1px solid #98B1C0;border-collapse:collapse;border-bottom:0px solid #98B1C0;border-right:0px solid #98B1C0;");
								// cellStyleStr.append("width:").append(
								// sheet.getColumnWidth(k)).append(";");
								cellStyleStr.append("\"");
								cellMap.put("cellStyle", cellStyleStr);
								cellMap.put("value", getStringCellValue(cell));

							} else {
								cellMap.put("y", j);
								cellMap.put("x", k);
								StringBuffer cellStyleStr = new StringBuffer();
								cellStyleStr.append("style=\"");
								cellStyleStr
										.append("border-top:1px solid #98B1C0;border-left:1px solid #98B1C0;border-collapse:collapse;border-bottom:0px solid #98B1C0;border-right:0px solid #98B1C0;");
								cellStyleStr.append("\"");
								cellMap.put("cellStyle", cellStyleStr);
								cellMap.put("value", "&nbsp;");
							}
							cellList.add(cellMap);
							k++;
						}
					}
					rowMap.put("rowHeigth", row.getHeightInPoints());
					rowMap.put("cells", cellList);
					rowList.add(rowMap);
					// if (readRowCross != -1) {
					// int count = -1;
					// if (cellList.size() == 0) {
					// count++;
					// } else {
					// count = -1;
					// for (int cellIndex = 0; cellIndex < cellList.size();
					// cellIndex++) {
					// if (!cellList.get(cellIndex).get("value")
					// .equals("")) {
					// count++;
					// break;
					// }
					// }
					// }
					// if (count == -1) {
					// lastRowNum = j;
					// }
					// }
				}
				j++;
			}
			sheetMap.put("name", sheet.getSheetName());
			sheetMap.put("rows", rowList);
			sheetMap.put("cellNum", cellNum);
			sheetList.add(sheetMap);
		}
		return sheetList;
	}

	/**
	 * 函数介绍：判数某位置是否在合并区 输入参数： 返回值：
	 */
	private CellRangeAddress getArea(HSSFSheet sheet, int row, int col) {
		int MergedNum = sheet.getNumMergedRegions();
		for (int i = 0; i <= MergedNum; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			if (range != null) {
				if (row >= range.getFirstRow() && row <= range.getLastRow()
						&& col >= range.getFirstColumn()
						&& col <= range.getLastColumn()) {
					return range;
				}
			}
		}
		return null;
	}

	/**
	 * 函数介绍：获取某一单元格内容 输入参数： 返回值：
	 */
	private String getStringCellValue(HSSFCell cell) {// 获取单元格数据内容为字符串类型的数据
		if (cell == null) {
			return "";
		}
		HSSFDataFormatter hSSFDataFormatter = new HSSFDataFormatter();
		String strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = hSSFDataFormatter.formatCellValue(cell);
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		return strCell;
	}

	public void exportList(File saveFile, File template,
			Map<String, List<Object>> dataMap) throws IOException {
		is = new FileInputStream(template);
		workbook = new HSSFWorkbook(is);
		for (int index = 0; index < dataMap.size(); index++) {
			HSSFSheet templateSheet = workbook.getSheetAt(index);// 得到模板SHEET
			// 判断格式
			int lastRow = templateSheet.getLastRowNum();
			int startEmptyRow = -1;// 记录开始行
			int endEmptyRow = -1;// 记录开始行
			for (int i = 0; i < lastRow; i++) {
				HSSFRow row = templateSheet.getRow(i);
				Iterator<Cell> cellIt = row.cellIterator();
				int cellCount = 0;// 单元格个数
				int emptyCellCount = 0;// 空单元格个数
				while (cellIt.hasNext()) {
					cellCount++;
					if (!getStringCellValue((HSSFCell) cellIt.next())
							.equals("")) {
						break;
					} else {
						emptyCellCount++;
					}
				}
				if (cellCount == emptyCellCount) {
					if (startEmptyRow == -1) {
						startEmptyRow = i;
					}
					endEmptyRow = i;
				}
			}
			if (startEmptyRow != -1 && endEmptyRow != -1
					&& startEmptyRow - endEmptyRow <= 0) {
				templateSheet.shiftRows(endEmptyRow + 1,
						templateSheet.getLastRowNum(),
						(startEmptyRow - endEmptyRow) - 1);
			}
			if (startEmptyRow == -1) {
				startEmptyRow = lastRow;
			}
			log.debug("记录开始行：" + startEmptyRow);
			List<Object> dataList = dataMap
					.get(dataMap.keySet().toArray()[index]);
			for (int i = startEmptyRow; i < dataList.size(); i++) {
				templateSheet.shiftRows(i, templateSheet.getLastRowNum(), 1);
				Object[] objs = (Object[]) dataList.get(i);
				HSSFRow newRow = templateSheet.createRow(i);
				for (int j = 0; j < objs.length; j++) {
					if (objs[j] != null) {
						newRow.createCell(j).setCellValue(objs[j].toString());
					}
				}
			}
		}
		os = new FileOutputStream(saveFile);
		workbook.write(os);
		closeRes();
	}

	/**
	 * 函数介绍：关闭资源 输入参数： 返回值：
	 */
	public void closeRes() throws IOException {
		if (is != null) {
			is.close();
			is = null;
		}
		if (os != null) {
			os.close();
			os = null;
		}
	}

	public static void addMergedRegion(File file, String sheetName,
			int firstrow, int lastrow, int firstcolumn, int lastcolumn)
			throws Exception {

		InputStream is = null;
		HSSFWorkbook workbook = new HSSFWorkbook();
		try {
			is = new FileInputStream(file);
			workbook = new HSSFWorkbook(is);
			if (is != null) {
				is.close();
			}
		} catch (Exception e) {
			throw e;
		}
		OutputStream os = new FileOutputStream(file);
		HSSFSheet sheet = null;
		if (StringUtils.isNotBlank(sheetName)) {
			sheet = workbook.getSheet(sheetName);
		} else {
			sheet = workbook.getSheetAt(0);
		}
		try {
			sheet.addMergedRegion(new CellRangeAddress(firstrow, // first row
																	// (0-based)
					lastrow, // last row (0-based)
					firstcolumn, // first column (0-based)
					lastcolumn // last column (0-based)
			));
			workbook.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			os.flush();
			os.close();
			throw e;
		}

	}

	/**
	 * 获取合并单元格的框体x方向合并的个数
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public static int getMergedRegionXcount(HSSFSheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();

		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if (row >= firstRow && row <= lastRow) {

				if (column >= firstColumn && column <= lastColumn) {

					return lastRow - firstRow;
				}
			}
		}

		return 0;
	}

	/**
	 * 获取合并单元格的框体Y方向合并的个数
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public static int getMergedRegionYcount(HSSFSheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();

		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if (row >= firstRow && row <= lastRow) {

				if (column >= firstColumn && column <= lastColumn) {

					return lastColumn - firstColumn;
				}
			}
		}

		return 0;
	}

	/**
	 * 判断指定的单元格是否是合并单元格
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public static boolean isMergedRegion(HSSFSheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();

		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {

					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 导出执法对象导入模板
	 * @param lawobjDic 执法对象字典表数据集合
	 * @param data 
	 * @return
	 */
	public static InputStream genTemplate(List<TDataLawobjdic> lawobjDic, String title, LinkedHashMap<String, List<LinkedHashMap<String, Object>>> data){
		InputStream is = null;
		// 第一步，创建一个webbook，对应一个Excel文件 
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("填写页");
		// 第三步，在sheet中添加表头第1行,注意老版本poi对Excel的行数列数有限制short  row 0 被合并为一行
		HSSFRow row = sheet.createRow((int) 1);
		// 第四步，创建单元格，并设置值表头 设置表头居中 
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
		// 背景为红色的必填样式
		HSSFCellStyle styleRed = wb.createCellStyle();
		styleRed.setFillPattern(HSSFCellStyle.DIAMONDS);
		styleRed.setFillForegroundColor(HSSFColor.RED.index);

		styleRed.setFillBackgroundColor(HSSFColor.RED.index);
		//System.out.println(HSSFColor.RED.index);
		// 合并第一行 
		HSSFCell cello = sheet.createRow((int) 0).createCell(0);
		cello.setCellType(Cell.CELL_TYPE_STRING);
		cello.setCellValue(title);
		cello.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(0, (short)0, 0, (short)(lawobjDic.size() - 1)));
		
		// 循环数据制作第二行标题列
		for (int i = 0; i < lawobjDic.size(); i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(lawobjDic.get(i).getColchiname());
			if (lawobjDic.get(i).getMandatory().equals("Y")){
				cell.setCellStyle(styleRed);
				cello.setCellType(Cell.CELL_TYPE_STRING);
			}else{
			cello.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellStyle(style);
			}
		}
		
		// 添加数据sheet
		int s = 0;
		Set set = data.keySet();
		Iterator iter = set.iterator();
		while (iter.hasNext()) {
			String stn = (String) iter.next();
			HSSFSheet st = wb.createSheet(stn);
			List<LinkedHashMap<String, Object>> lt = data.get(stn);
			HSSFRow r = st.createRow(0);
			HSSFCell c = r.createCell(1);
			c.setCellValue("标识");
			c = r.createCell(2);
			c.setCellValue("名称");
			for (int i = 0; i < lt.size(); i++) {
				r = st.createRow(i + 1);
				c = r.createCell(1);
				c.setCellValue(String.valueOf(lt.get(i).get("id")));
				c = r.createCell(2);
				c.setCellValue(String.valueOf(lt.get(i).get("name")));
			}
			s++;
		}
		
		// 第六步，将文件存到指定位置  
		try {
			File file = new File(FileUpDownUtil.path.concat(UploadFileType.TEMP.getPath()).concat(UUID.randomUUID().toString().replaceAll("-", "")));
			FileOutputStream fout = new FileOutputStream(file);
			wb.write(fout);
			is = new FileInputStream(file);
			
			fout.flush();
			fout.close();
			// 在外部关闭
//			is.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	// 读取excel中数据并封装成listmap
	public static ArrayList<Map<String, Object>> readLawObjExcel(MultipartFile file) {
		ArrayList<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		try {
			POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = sheet.getRow(1);
			int colNum = row.getPhysicalNumberOfCells();
			String[] title = new String[colNum];
			
			for (int i = 0; i < colNum; i++) {
				title[i] = row.getCell(i).getStringCellValue();
			}
			
			Map<String, Object> map = null;
			int rowNum = sheet.getLastRowNum();
			for (int i = 2; i <= rowNum; i++) {
				map = new HashMap<String, Object>();
				row = sheet.getRow(i);
				int j = 0;
				while (j < colNum) {
					HSSFCell cell = row.getCell(j);
					HSSFDataFormatter hSSFDataFormatter = new HSSFDataFormatter();
					String strCell = "";
					if (cell != null){
						switch (cell.getCellType()) {
							case HSSFCell.CELL_TYPE_STRING:
								strCell = cell.getStringCellValue();
								break;
							case HSSFCell.CELL_TYPE_NUMERIC:
								strCell = hSSFDataFormatter.formatCellValue(cell);
								break;
							case HSSFCell.CELL_TYPE_BOOLEAN:
								strCell = String.valueOf(cell.getBooleanCellValue());
								break;
							case HSSFCell.CELL_TYPE_BLANK:
								strCell = "";
								break;
							default:
								strCell = String.valueOf(cell.getBooleanCellValue());
								break;
						}
					} else {
						strCell = "";
					}
					
					map.put(title[j], strCell.trim());
					j++;
				}
				data.add(map);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
