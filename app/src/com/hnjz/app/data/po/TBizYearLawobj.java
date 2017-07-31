package com.hnjz.app.data.po;

import com.hnjz.sys.po.BaseObject;
import com.hnjz.sys.po.TSysArea;

/**
 * ��ȳ�����ʵ����
 * @author shiqiuhan
 * @created 2016-3-10,����04:07:08
 */
public class TBizYearLawobj extends BaseObject {

	private static final long serialVersionUID = 1517474713052732108L;

	private String year;	//���

	private TDataLawobj lawobj;	//������
	private TDataLawobjf lawobjf;	//������
	public TDataLawobjf getLawobjf() {
		return lawobjf;
	}

	public void setLawobjf(TDataLawobjf lawobjf) {
		this.lawobjf = lawobjf;
	}

	private TSysArea area;	//����
	
	private String lawobjtype;	//ִ����������
	
	private String lawobjname; 	//ִ����������
	
	private String type;	//��ѡ���ͣ�0 �ص���ҵ 1 һ����ҵ 2������ҵ

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public TDataLawobj getLawobj() {
		return lawobj;
	}

	public void setLawobj(TDataLawobj lawobj) {
		this.lawobj = lawobj;
	}

	public TSysArea getArea() {
		return area;
	}

	public void setArea(TSysArea area) {
		this.area = area;
	}

	public String getLawobjtype() {
		return lawobjtype;
	}

	public void setLawobjtype(String lawobjtype) {
		this.lawobjtype = lawobjtype;
	}

	public String getLawobjname() {
		return lawobjname;
	}

	public void setLawobjname(String lawobjname) {
		this.lawobjname = lawobjname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}