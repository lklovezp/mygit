package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;
import com.hnjz.sys.po.TSysDic;

/**
 * T_DATA_INDUSTRY ʵ����
 * ���ߣ�������
 * �������ڣ�Fri Feb 27 15:15:03 CST 2015
 * ������������ҵ
 */ 

@SuppressWarnings("serial")
public class TDataIndustry extends BaseObject {
	/** ���� */
	private String name;
	/**  */
	private String pid;
    /**��ҵ���� */
	private String code;
	/** ִ����������
01	��ҵ��ȾԴ
02	������Ŀ
03	ҽԺ
04	��¯
05	��������
06	����
07	������ֳ
08  ����ҵ
09  ��ʳҵ
10  ��������ҵ
11  ����ҵ */
	private String lawobjtype;
	/** ��ҵ��תִ���������� */
	private String tolawobjtype;

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
	public void setLawobjtype(String lawobjtype){
		this.lawobjtype = lawobjtype;
	}
	public String getLawobjtype(){
		return lawobjtype;
	}
	public void setTolawobjtype(String tolawobjtype) {
		this.tolawobjtype = tolawobjtype;
	}
	public String getTolawobjtype() {
		return tolawobjtype;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
