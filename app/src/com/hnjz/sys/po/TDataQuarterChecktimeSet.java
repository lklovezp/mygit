package com.hnjz.sys.po;

import java.util.Date;

/**
 * ���ȳ��ʱ��ʵ����
 * @author shiqiuhan
 * @created 2016-3-17,����03:06:49
 */
public class TDataQuarterChecktimeSet extends BaseObject {

	private static final long serialVersionUID = 1517474713052732108L;

	private String year;	//���

	private String quarter;		//����
	
	private Date time;	//���ʱ��
	
	private String executed;	//�Ƿ���ִ�� Y��ִ�� Nδִ��
	
	private TSysArea area;	//����

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getExecuted() {
		return executed;
	}

	public void setExecuted(String executed) {
		this.executed = executed;
	}

	public TSysArea getArea() {
		return area;
	}

	public void setArea(TSysArea area) {
		this.area = area;
	}

}