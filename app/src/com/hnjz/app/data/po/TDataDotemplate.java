package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_DOTEMPLATE ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:04 CST 2015
 * �����������������ģ��
 */ 

@SuppressWarnings("serial")
public class TDataDotemplate extends BaseObject {
	/**  */
	private String name;
	/**  */
	private Long auditlevel;
	/** ��̨������ת��ַ */
	private String weburl;
	/** �ն˰�����ת��ַ */
	private String mobileurl;

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setAuditlevel(Long auditlevel){
		this.auditlevel = auditlevel;
	}
	public Long getAuditlevel(){
		return auditlevel;
	}
	public void setWeburl(String weburl){
		this.weburl = weburl;
	}
	public String getWeburl(){
		return weburl;
	}
	public void setMobileurl(String mobileurl){
		this.mobileurl = mobileurl;
	}
	public String getMobileurl(){
		return mobileurl;
	}
}
