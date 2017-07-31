package com.hnjz.sys.po;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 作者：赵文明
 * 生成日期：2015-2-13
 * 功能描述：
数据字典
 *
 */
public class TSysDic extends BaseObject {

	private static final long serialVersionUID = -6717808853347436718L;

	private String code;

	private String name;

	private String type;

	private String isdefaultsel;
	
	/** （是否必填）附件类型使用**/
	private String mandatory;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMandatory() {
		return mandatory;
	}

	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}

	public String getIsdefaultsel() {
		return isdefaultsel;
	}

	public void setIsdefaultsel(String isdefaultsel) {
		this.isdefaultsel = isdefaultsel;
	}

	/**
	* 选择页面的数据
	* @return
	*/
	public Map<String, Object> toSelMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", getId());
		map.put("name", getName());
		map.put("code", getCode());
		return map;
	}
}