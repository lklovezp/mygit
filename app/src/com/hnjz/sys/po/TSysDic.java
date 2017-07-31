package com.hnjz.sys.po;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * ���ߣ�������
 * �������ڣ�2015-2-13
 * ����������
�����ֵ�
 *
 */
public class TSysDic extends BaseObject {

	private static final long serialVersionUID = -6717808853347436718L;

	private String code;

	private String name;

	private String type;

	private String isdefaultsel;
	
	/** ���Ƿ�����������ʹ��**/
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
	* ѡ��ҳ�������
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