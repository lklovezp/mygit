package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_VERSION ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:02 CST 2015
 * �����������汾��Ϣ
 */ 

@SuppressWarnings("serial")
public class TDataVersion extends BaseObject {
	/** �汾�ţ���20150101 */
	private String code;
	/** �汾���ƣ���V2.0.0 */
	private String name;
	/** �Ƿ�ǿ�ư�װ������ Y/N */
	private String isforce;
	/** ��װ������
0 �ն�
1 ������
2 �������ݰ�
3 �����ĵ�
 */
	private String type;

	public void setCode(String code){
		this.code = code;
	}
	public String getCode(){
		return code;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setIsforce(String isforce){
		this.isforce = isforce;
	}
	public String getIsforce(){
		return isforce;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return type;
	}
}
