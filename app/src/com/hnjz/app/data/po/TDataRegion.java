package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;

/**
 * T_DATA_REGION ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:03 CST 2015
 * ��������������������
 */ 

@SuppressWarnings("serial")
public class TDataRegion extends BaseObject {
	/** ��ʦ������ */
	private String name;
	/**  */
	private String pid;
	/** ����
1 ����
2 ʦ
3 �ų�
 */
	private String type;

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setPid(String pid){
		this.pid = pid;
	}
	public String getPid(){
		return pid;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return type;
	}
}
