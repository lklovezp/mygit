package com.hnjz.common.util.excel;

import java.io.File;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 读取xml配置文件指定的参数类
 * 
 * @author Administrator
 * 
 */
public class XMLUtil {
	
	private static final String SHEET_ELEMENT = "SheetObj";

	private static final String COLNUM_ELEMENT = "ColnumObj";

	private static final String NAME_ATTRIBUTE = "name";

	private static final String KEY_ATTRIBUTE = "key";

	private static final String BEGIN_X_ATTRIBUTE = "beginx";

	private static final String BEGIN_Y_ATTRIBUTE = "beginy";

	private static final String END_X_ATTRIBUTE = "endx";

	private static final String END_Y_ATTRIBUTE = "endy";

	private static final String SPAN_ATTRIBUTE = "span";

	private static final String CLAZZ_ATTRIBUTE = "clazz";

	private static final String X_ATTRIBUTE = "x";

	private static final String Y_ATTRIBUTE = "y";

	/**
	 * 读取xml配置文件
	 */
	public static ConfigObj readXML(File file) {
		ConfigObj configObj = new ConfigObj();
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(file);
			Element root = doc.getRootElement();
			List<?> sheetList = root.elements(XMLUtil.SHEET_ELEMENT);
			for (int i = 0; i < sheetList.size(); i++) {
				Element sheetElement = (Element) sheetList.get(i);
				SheetObj obj = new SheetObj();
				obj.setConfigObj(configObj);
				fillSheetObj(obj, sheetElement);
				configObj.getSheetObjList().add(obj);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return configObj;
	}

	// 填充sheetObj中的属性
	private static void fillSheetObj(SheetObj obj, Element e) {
		if (e != null && obj != null) {
			// 填充sheetObj中的属性

			Attribute a = e.attribute(XMLUtil.NAME_ATTRIBUTE);
			if (a != null) {
				obj.setName(a.getValue());
			}
			a = e.attribute(XMLUtil.BEGIN_X_ATTRIBUTE);
			if (a != null) {
				obj.setBeginx(Integer.parseInt(a.getValue()));
			}
			a = e.attribute(XMLUtil.BEGIN_Y_ATTRIBUTE);
			if (a != null) {
				obj.setBeginy(Integer.parseInt(a.getValue()));
			}
			a = e.attribute(XMLUtil.END_X_ATTRIBUTE);
			if (a != null) {
				obj.setEndx(Integer.parseInt(a.getValue()));
			}
			a = e.attribute(XMLUtil.END_Y_ATTRIBUTE);
			if (a != null) {
				obj.setEndy(Integer.parseInt(a.getValue()));
			}
			a = e.attribute(XMLUtil.SPAN_ATTRIBUTE);
			if (a != null) {
				obj.setSpan(Integer.parseInt(a.getValue()));
			}
			a = e.attribute(XMLUtil.CLAZZ_ATTRIBUTE);
			if (a != null) {
				obj.setClazz(a.getValue());
			}

			a = e.attribute(XMLUtil.KEY_ATTRIBUTE);
			if (a != null) {
				obj.setKey(a.getValue());
			}

			List<?> colnumList = e.elements(XMLUtil.COLNUM_ELEMENT);
			for (int i = 0; i < colnumList.size(); i++) {
				ColnumObj colnum = new ColnumObj();
				colnum.setSheetObj(obj);
				Element element = (Element) colnumList.get(i);
				fillColnumObj(colnum, element);
				obj.getList().add(colnum);
			}
		}
	}

	// 填充colnumObj中的属性
	private static void fillColnumObj(ColnumObj obj, Element e) {
		if (e != null && obj != null) {

			Attribute a = e.attribute(XMLUtil.NAME_ATTRIBUTE);
			if (a != null) {
				obj.setName(a.getValue());
			}
			a = e.attribute(XMLUtil.X_ATTRIBUTE);
			if (a != null) {
				obj.setX(Integer.parseInt(a.getValue()));
			}
			a = e.attribute(XMLUtil.Y_ATTRIBUTE);
			if (a != null) {
				obj.setY(Integer.parseInt(a.getValue()));
			}
		}
	}
}
